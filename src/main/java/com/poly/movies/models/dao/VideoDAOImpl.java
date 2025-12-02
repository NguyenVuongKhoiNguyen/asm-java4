package com.poly.movies.models.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.poly.movies.models.entities.Video;
import com.poly.movies.utils.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

public class VideoDAOImpl implements VideoDAO {
	
	@Override
	public void create(Video e) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Video e) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		try {
			em.getTransaction().begin();
			Video e = em.find(Video.class, id);
			if (e != null) {
				em.remove(e);
			} else {
				System.out.println("Can't find User");
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<Video> beanList = null;
		
		try {
			beanList = em.createQuery("select v from Video v", Video.class).getResultList();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}

	@Override
	public Video findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		Video bean = null;
		
		try {
			bean = em.find(Video.class, id);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Video> getTrendingVideos() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<Video> beanList = null;
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("GetTrendingVideos", Video.class);
			beanList = query.getResultList();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Video> getTop7ImdbScore() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		List<Video> beanList = null;
		
		try {
			beanList = em.createNativeQuery("EXEC GetTop7ImdbScore",  Video.class).getResultList();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Video> getTop7RecentReleased() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		List<Video> beanList = null;
		
		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery("GetTop7RecentReleased", Video.class);
			beanList = query.getResultList();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}

	@Override
	public Set<Video> getAllFavsAndShares() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		Set<Video> beanSet = null;

		try {
			List<Video> beanList = em.createQuery("SELECT v FROM Video v LEFT JOIN FETCH v.favorites LEFT JOIN FETCH v.shares", Video.class).getResultList();
			beanSet = new HashSet<>(beanList);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanSet;
	}

	@Override
	public Video getFavAndShare(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		Video video = null;

		try {
			video = em.createQuery("SELECT v FROM Video v LEFT JOIN FETCH v.favorites LEFT JOIN FETCH v.shares WHERE v.id = :id", Video.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return video;
	}
	
	public static void main(String[] args) {
		VideoDAOImpl videoDao = new VideoDAOImpl();
		Video video = videoDao.getFavAndShare("V001");
		System.out.println(video.getTitle() + " - " + video.getFavorites().size() + " - " + video.getShares().size());
		
//		Set<Video> videoSet = videoDao.getAllFavsAndShares();
//		videoSet.forEach(v -> {
//			System.out.println(v.getId() + " - " + v.getFavorites().size() + " - " + v.getShares().size());
//		});
	}
}
