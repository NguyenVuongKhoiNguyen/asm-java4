package com.poly.movies.controllers;

import java.io.IOException;
import java.util.List;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.User;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet({"/app", "/logout"})
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	VideoDAOImpl videoDao = new VideoDAOImpl();
	
	
	List<Video> trendingList = videoDao.getTrendingVideos();
	List<Video> top7ImdbList = videoDao.getTop7ImdbScore();
	List<Video> top7RecentReleasedList = videoDao.getTop7RecentReleased();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession s = request.getSession(false);
		
		String logout = request.getServletPath();
		if (logout != null && logout.equals("/logout")) {
			
			s.removeAttribute("userLogin");
			if (s.getAttribute("isAdmin") != null) s.removeAttribute("isAdmin");
			
			String page = (String) s.getAttribute("page");
			if (page.equals("movie-detail")) {
				response.sendRedirect(request.getContextPath() + "/movie-detail");
				return;
			}
			if (page.equals("movie")) {
				response.sendRedirect(request.getContextPath() + "/movie");
				return;
			}
		}
		
		request.setAttribute("trendingList", trendingList);
		request.setAttribute("top7ImdbList", top7ImdbList);
		request.setAttribute("top7RecentReleasedList", top7RecentReleasedList);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
