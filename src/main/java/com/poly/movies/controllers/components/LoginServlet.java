package com.poly.movies.controllers.components;

import java.io.IOException;

import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.entities.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	User user = null;
	UserDAOImpl userDao = new UserDAOImpl();
	
	String previousPage = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		previousPage = request.getParameter("previousPage");
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (id == null || password == null || id.isEmpty() || password.isEmpty()) {
			doGet(request, response);
			return; //if not, the rest of the method still run event request depatcher or redirect are being execute
		}
		else {
			user = userDao.findById(id);
		}
		
		if (user == null) {
			request.setAttribute("message", "Wrong Username!!!");
			doGet(request, response);
			return;
		}
		
		if (!user.getPassword().equals(password)) {
			request.setAttribute("message", "Wrong Password!!!");
			doGet(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userLogin", userDao.findById(id));
		
		if (user.isAdmin()) {
			response.sendRedirect(request.getContextPath() + "/video");
			return;
		}
		
		if (previousPage != null) {
			if (previousPage.equals("movie-detail")) {
				response.sendRedirect(request.getContextPath() + "/movie-detail");
			}
			if (previousPage.equals("movie")) {
				response.sendRedirect(request.getContextPath() + "/movie");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/app");
		}
		previousPage = null;
	}

}
