package com.poly.movies.controllers.components;

import java.io.IOException;

import com.poly.movies.models.dao.FavoriteDAOImpl;
import com.poly.movies.models.dao.ShareDAOImpl;
import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Favorite;
import com.poly.movies.models.entities.Share;
import com.poly.movies.models.entities.User;
import com.poly.movies.models.entities.Video;
import com.poly.movies.utils.XDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MovieDetailServlet
 */
@WebServlet({"/movie-detail", "/movie-detail/*"})
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	VideoDAOImpl videoDao = new VideoDAOImpl();
	FavoriteDAOImpl favoriteDao = new FavoriteDAOImpl();
	ShareDAOImpl shareDao = new ShareDAOImpl();
	
	Video video = null;
	User user = null;
	int favorites = 0;
	int shares = 0;
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
		Favorite userFavVid = null;
		
		String videoId = request.getParameter("id");
		if (videoId != null) {
			video = videoDao.getFavAndShare(videoId);
			favorites = video.getFavorites().size();
			shares = video.getShares().size();
			video.setViews(video.getViews() + 1);
			videoDao.update(video);
		}
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("userLogin");
		if (user != null) {
			userFavVid = favoriteDao.findUserFavoriteVideo(user.getId(), video.getId());
		}
		
		String pathInfo = request.getPathInfo();
		if (pathInfo != null && pathInfo.equals("/favorite")) {
			Favorite favorite = new Favorite();
			favorite.setUserId(user.getId());
			favorite.setVideoId(video.getId());
			favorite.setLikedDate(XDate.now());
			favoriteDao.create(favorite);
			userFavVid = favoriteDao.findUserFavoriteVideo(user.getId(), video.getId());
		}
		
		request.setAttribute("favorites", favorites);
		request.setAttribute("shares", shares);
		request.setAttribute("userFavVid", userFavVid);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/movie-detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Favorite userFavVid = null;
		
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null) {
			doGet(request, response);
			return;
		}
		
		String email = request.getParameter("email");
		if (email == null || email.isBlank()) {
			doGet(request, response);
			return;
		}
		
		if (pathInfo.equals("/share")) {
			Share share = new Share();
			share.setUserId(user.getId());
			share.setVideoId(video.getId());
			share.setSharedDate(XDate.now());
			share.setEmail(email);
			shareDao.create(share);
		}
		request.setAttribute("userFavVid", userFavVid);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/views/movie-detail.jsp").forward(request, response);
	}

}
