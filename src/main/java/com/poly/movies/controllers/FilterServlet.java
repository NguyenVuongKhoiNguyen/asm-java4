package com.poly.movies.controllers;

import java.io.IOException;

import com.poly.movies.models.entities.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FilterServlet
 */
@WebFilter({"/video", "/user"})
public class FilterServlet implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession s = req.getSession(false);
		
		User user = (s != null) ? (User) s.getAttribute("userLogin") : null;
		
		if (user == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if (!user.isAdmin()) {
			res.sendRedirect(req.getContextPath() + "/app");
			return;
		} 
		
		
		chain.doFilter(req, res);
	}
}
