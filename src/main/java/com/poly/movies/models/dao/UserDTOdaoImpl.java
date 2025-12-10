package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.User;
import com.poly.movies.models.entities.UserDTO;
import com.poly.movies.utils.JPA;

import jakarta.persistence.EntityManager;

public class UserDTOdaoImpl implements UserDTOdao {

	@Override
	public void create(UserDTO e) {
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
	public void update(UserDTO e) {
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
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		List<UserDTO> beanList = null;
		
		try {
			beanList = em.createQuery("select u from UserDTO u", UserDTO.class).getResultList();
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return beanList;
	}

	@Override
	public UserDTO findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = JPA.openEntityManager();
		
		UserDTO bean = null;
		
		try {
			bean =  em.find(UserDTO.class, id);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		return bean;
	}
	
	public static void main(String[] args) {
		UserDTOdaoImpl userDTOdao = new UserDTOdaoImpl();
		
		List<UserDTO> userList = userDTOdao.findAll();
		
		userList.forEach(u -> {
			System.out.println(u);
		});
	}

}
