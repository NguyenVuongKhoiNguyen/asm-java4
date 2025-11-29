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
	
	String pageDetail = null;
	
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
		pageDetail = request.getParameter("pageChange");
		System.out.println(pageDetail);
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (id == null || password == null || id.isEmpty() || id.isEmpty()) {
			doGet(request, response);
		}
		else {
			user = userDao.findById(id);
		}
		
		if (user == null) {
			System.out.println("Wrong username");
			doGet(request, response);
		}
		
		if (!user.getPassword().equals(password)) {
			System.out.println("Wrong password");
			doGet(request, response);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userLogin", userDao.findById(id));
		if (!user.isAdmin()) {
			if (pageDetail != null) {
				response.sendRedirect(request.getContextPath() + "/movie-detail");
			} else {
				response.sendRedirect(request.getContextPath() + "/app");
			}
			pageDetail = null;
			return;
		}
		
		response.sendRedirect(request.getContextPath()+ "/video");
	}

}
