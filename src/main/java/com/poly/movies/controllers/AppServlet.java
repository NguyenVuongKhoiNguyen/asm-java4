package com.poly.movies.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Video;

import jakarta.mail.Session;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/app")
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
		HttpSession s = request.getSession();
		s.setAttribute("page", "home");
		
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
		doGet(request, response);
	}

}
