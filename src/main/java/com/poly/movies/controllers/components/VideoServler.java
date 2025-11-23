package com.poly.movies.controllers.components;

import java.io.IOException;
import java.util.List;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VideoServler
 */
@WebServlet("/video")
public class VideoServler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VideoDAOImpl videoDao = new VideoDAOImpl();
	
	List<Video> videoList= videoDao.findAll();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoServler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		videoList.forEach(v ->{
			System.out.println(v);
		});
		
		request.setAttribute("videoList", videoList);
		request.getRequestDispatcher("/views/video-dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
