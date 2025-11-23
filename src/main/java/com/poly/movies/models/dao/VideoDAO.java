package com.poly.movies.models.dao;

import java.util.List;

import com.poly.movies.models.entities.Video;

public interface VideoDAO extends CrudDAO<Video, String> {
	List<Video> getTrendingVideos();
	List<Video> getTop7ImdbScore();
	List<Video> getTop7RecentReleased();
}
