package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.Share;
import com.poly.movies.utils.JPA;

import jakarta.persistence.EntityManager;

public class ShareDAOImpl implements ShareDAO {

	@Override
	public void create(Share e) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}	
	}

	@Override
	public void update(Share e) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
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
			Share s = em.find(Share.class, id);
			if (s != null) {
				em.remove(s);
			} else {
				System.out.println("Can't find favorite");
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Share> findAll() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<Share> beanList = null;
		
		try {
			beanList = em.createQuery("select s from Share s", Share.class).getResultList();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}

	@Override
	public Share findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		Share bean = null;
		
		try {
			bean = em.find(Share.class, id);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
		
	}
	public static void main(String[] args) {
		//ShareDAOImpl shareDao = new ShareDAOImpl();
//		List<Share> shareList = shareDao.findAll();
//		shareList.forEach(s -> {
//			System.out.println(s);
//		});
		
//		Share share = new Share();
//		share.setUserId("alice01");
//		share.setVideoId("V001");
//		share.setSharedDate(XDate.now());
//		share.setEmail("georgefloyd@gmail.com");
//		shareDao.create(share);
//		System.out.println(share);
		
	}
}
