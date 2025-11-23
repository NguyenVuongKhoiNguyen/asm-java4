package com.poly.movies.controllers.components;

import java.io.IOException;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MovieDetailServlet
 */
@WebServlet("/movie-detail")
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	VideoDAOImpl videoDao = new VideoDAOImpl();

	Video video = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession s = request.getSession();
		s.setAttribute("page", "movie-detail");
		
		String videoId = request.getParameter("id");
		if (videoId != null) {
			video = videoDao.findById(videoId);
		}
		
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/movie-detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
