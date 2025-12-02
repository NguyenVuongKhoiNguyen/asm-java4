package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.User;
import com.poly.movies.utils.JPA;

import jakarta.persistence.EntityManager;

public class UserDAOImpl implements UserDAO {

	@Override
	public void create(User e) {
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
	public void update(User e) {
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
			User u = em.find(User.class, id);
			if (u != null) {
				em.remove(u);
			} else {
				System.out.println("Can't find user");
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
	public List<User> findAll() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<User> beanList = null;
		
		try {
			beanList = em.createQuery("select v from User v", User.class).getResultList();
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		User bean = null;
		
		try {
			bean =  em.find(User.class, id);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
	}

	@Override
	public User getUserFavoriteById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		User bean = null;
		
		try {
			//left join fetch means keep the left side even though the right side is empty
			//the left is user right is favorites
			bean = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.favorites WHERE u.id = :uid", User.class)
					.setParameter("uid", id)
					.getSingleResult();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
	}

	@Override
	public List<User> getUserFavorites() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<User> beanList = null;
		
		try {
			beanList = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.favorites", User.class)
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return beanList;
	}

	@Override
	public User getUserShareById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		User bean = null;
		
		try {
			//left join fetch means keep the left side even though the right side is empty
			//the left is user right is favorites
			bean = em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.shares WHERE u.id = :uid", User.class)
					.setParameter("uid", id)
					.getSingleResult();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
	}

	@Override
	public List<User> getUserShares() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<User> beanList = null;
		
		try {
			beanList = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.shares", User.class)
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return beanList;
	}
	public static void main(String[] args) {
		UserDAOImpl userDao = new UserDAOImpl();
		List<User> userList = userDao.getUserShares();
		userList.forEach(u -> {
			System.out.println(u.getShares().size());
		});
	}
}
