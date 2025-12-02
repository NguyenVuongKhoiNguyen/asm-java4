package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.User;

public interface UserDAO extends CrudDAO<User, String> {
	User getUserFavoriteById(String id);
	List<User> getUserFavorites();
	User getUserShareById(String id);
	List<User> getUserShares();
}
