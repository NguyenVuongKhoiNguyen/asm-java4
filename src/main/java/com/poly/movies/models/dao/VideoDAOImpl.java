package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.Video;
import com.poly.movies.utils.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

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
	
	public static void main(String[] args) {
		VideoDAOImpl videoDao = new VideoDAOImpl();
		
		List<Video> videoList = videoDao.getTop7RecentReleased();
		videoList.forEach(v -> {
			System.out.println(v);
		});
		
		/*
		List<Video> videoList = videoDao.getTop7ImdbScore();
		videoList.forEach(v -> {
			System.out.println(v);
		});
		*/
		
		/*
		List<Video> videoList = videoDao.getTrendingVideos();
		for (Video v : videoList) {
			System.out.println(v);
		}
		*/
		
		/*
		List<Video> videoList = videoDao.findAll();
		videoList.forEach(v -> {
			System.out.println(v);
		});
		*/
	}


}
