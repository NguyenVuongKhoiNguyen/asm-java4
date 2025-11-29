package com.poly.movies.models.dao;

import com.poly.movies.models.entities.Favorite;

public interface FavoriteDAO extends CrudDAO<Favorite, String>{
	Favorite findUserFavoriteVideo(String userId, String videoId);
}
