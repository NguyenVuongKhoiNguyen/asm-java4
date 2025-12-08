package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.User;

public interface UserDAO extends CrudDAO<User, String> {
	List<User> getAllFavsAndShares();
	User getFavAndShare(String id);
}
