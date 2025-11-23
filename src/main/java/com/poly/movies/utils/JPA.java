package com.poly.movies.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//JPA stands for Java Persistence API
public class JPA {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
	
	public static EntityManager openEntityManager() {
		return emf.createEntityManager();
	}
}
