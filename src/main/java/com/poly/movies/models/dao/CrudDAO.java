package com.poly.movies.models.dao;

import java.util.List;

public interface CrudDAO <Entity, Identification> {
	void create(Entity e);
	void update(Entity e);
	void delete(Identification id);
	List<Entity> findAll();
	Entity findById(Identification id);
}
