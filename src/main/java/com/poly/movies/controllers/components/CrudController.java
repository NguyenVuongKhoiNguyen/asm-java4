package com.poly.movies.controllers.components;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CrudController {
	void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	void tableDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
