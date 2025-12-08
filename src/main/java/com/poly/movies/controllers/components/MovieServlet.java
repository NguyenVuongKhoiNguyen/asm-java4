package com.poly.movies.controllers.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet({"/movie", "/movie/*"})
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VideoDAOImpl videoDao = new VideoDAOImpl();
	
	List<Video> videoList = new ArrayList<>();
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		videoList = videoDao.findAll();
		
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.equals("/search")) {
				String movieName = request.getParameter("search").trim().toLowerCase();
				if (movieName != null) {
					if (!movieName.isBlank()) {
						videoList.removeIf(video -> !video.getTitle().toLowerCase().contains(movieName));
					}
				}
			}
		}
		
		request.setAttribute("videoList", videoList);
		request.getRequestDispatcher("/views/movie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
