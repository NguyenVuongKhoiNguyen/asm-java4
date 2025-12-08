package com.poly.movies.controllers.components;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.entities.User;
import com.poly.movies.utils.XStr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
		
		Map<String, String> cookiesMap = getUserCookies(request);
		String username = cookiesMap.get("username");
		String password = cookiesMap.get("password");
		if (username != null || password != null) {
			if (username.isBlank() || password.isBlank()) return;
			request.setAttribute("username", username);
			request.setAttribute("password", XStr.decodeB64(password));
		}
		
		previousPage = request.getParameter("previousPage");
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		String rememberMe = request.getParameter("rememberMe");
				
		if (id == null || password == null || id.isEmpty() || password.isEmpty()) {
			doGet(request, response);
			return; //if not, the rest of the method still run event request depatcher or redirect are being execute
		}
		
		User user = userDao.findById(id);
		
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
		
		if (rememberMe != null) {
			setUserToCookies(response, "username", id, "password", XStr.encodeB64(password));
		} else {
			destroyAllCookies(request, response);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userLogin", user);
		
		if (user.isAdmin()) {
			session.setAttribute("isAdmin", user.isAdmin());
			response.sendRedirect(request.getContextPath() + "/video");
			previousPage = null;
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
	
	private void setUserToCookies(HttpServletResponse response, String... args) {
		
		Map<String, String> map = new HashMap<>();
		
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i+1]);
		}
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Cookie cookie = new Cookie(entry.getKey(), entry.getValue());
			cookie.setMaxAge(36000);
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}
	}
	
	private Map<String, String> getUserCookies(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		
		Map<String, String> map = new HashMap<>();
		for (Cookie c : cookies) {
			map.put(c.getName(), c.getValue());
		}
		
		return map;
	}
	
	private void destroyAllCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
	    if (cookies == null) return;

	    for (Cookie cookie : cookies) {
	        cookie.setValue("");
	        cookie.setPath("/");
	        cookie.setMaxAge(0);    
	        response.addCookie(cookie);
	    }
	}
}
