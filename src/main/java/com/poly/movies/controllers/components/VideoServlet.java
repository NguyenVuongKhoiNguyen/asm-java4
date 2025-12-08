package com.poly.movies.controllers.components;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Favorite;
import com.poly.movies.models.entities.Share;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class VideoServler
 */
@MultipartConfig
@WebServlet({"/video","/video/*"})
public class VideoServlet extends HttpServlet implements CrudController {
	private static final long serialVersionUID = 1L;
       
	VideoDAOImpl videoDao = new VideoDAOImpl();
	
	List<Video> videoList = new ArrayList<>();

	
	Video editVideo = null;
	
	String posterPath = "D:\\eclipse-workspace\\Movies\\src\\main\\webapp\\images\\movies";
	String videoPath = "D:\\eclipse-workspace\\Movies\\src\\main\\webapp\\videos";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		videoList = videoDao.getAllFavsAndShares();
		
		String pathInfo = request.getPathInfo();
		
		if (pathInfo != null) {
			switch (pathInfo) {
				case "/edit":
					edit(request, response);
					break;
				case "/table-delete":
					tableDelete(request, response);
					videoList = videoDao.getAllFavsAndShares();
					break;
				case "/desc-most-views":
					sortVideoByViewsDESC();
					break;
				case "/desc-most-favs":
					sortVideoByFavoritesDESC();
					break;
				case "/desc-most-shares":
					sortVideoBySharesDESC();
					break;
				case "/asc-most-views":
					sortVideoByViewsASC();
					break;
				case "/asc-most-favs":
					sortVideoByFavoritesASC();
					break;
				case "/asc-most-shares":
					sortVideoBySharesASC();
					break;
			}
		}
		
		request.setAttribute("newVideoId", generateNewVideoId());
		request.setAttribute("videoList", videoList);
		request.getRequestDispatcher("/views/video-dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null) {
			doGet(request, response);
			return;
		}
		
		switch (pathInfo) {
			case "/create":
				create(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			case "/clear":
				request.setAttribute("editVideo", null);
				break;
			case "/search":
				search(request, response);
				break;
		}
		
		doGet(request, response);
	}
	
	private String generateNewVideoId() {

		String lastId = videoDao.findAll().get(videoDao.findAll().size() - 1).getId();
		int newIdDigit = Integer.parseInt(lastId.substring(1)) + 1;
		if (newIdDigit < 10) {
			return "V00" + newIdDigit;
		} else if (newIdDigit >= 10 && newIdDigit < 100) {
			return "V0" + newIdDigit;
		} else {
			return "V" + newIdDigit;
		}
	}

	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Video video = new Video();
		
		DateConverter converter = new DateConverter();
		converter.setPattern("dd/MM/yyyy"); // match your input format
		ConvertUtils.register(converter, java.util.Date.class);
		
		try {
			BeanUtils.populate(video, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Part posterPart = request.getPart("poster");
		Part videoPart = request.getPart("video");
		
		String posterName = posterPart.getSubmittedFileName();
		String videoName = videoPart.getSubmittedFileName();
		
		if(posterName.isBlank() || videoName.isBlank()) {
			return;
		}
		
		String applicationPath = request.getServletContext().getRealPath("");
		String posterUploadPath = applicationPath + File.separator + "images" + File.separator + "movies";
		String videoUploadPath = applicationPath + File.separator + "videos";
		
		File uploadPosterDir = new File(posterUploadPath);
		File uploadVideoDir = new File(videoUploadPath);
		
		if (!uploadPosterDir.exists()) uploadPosterDir.mkdirs();
		if (!uploadVideoDir.exists()) uploadVideoDir.mkdir();
		
		posterPart.write(posterUploadPath + File.separator + posterName);
		videoPart.write(videoUploadPath + File.separator + videoName);
		
		video.setPoster(posterPart.getSubmittedFileName());
		video.setVideo(videoPart.getSubmittedFileName());
		
		videoDao.create(video);
		
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Video video = new Video();
		
		DateConverter converter = new DateConverter();
		converter.setPattern("dd/MM/yyyy"); // match your input format
		ConvertUtils.register(converter, java.util.Date.class);
		
		try {
			BeanUtils.populate(video, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get and set poster and video name
		Video vi = null;
		for (Video v : videoList) {
			if (v.getId().equals(video.getId())) {
				vi = v;
				break;
			}
		}
		video.setPoster(vi.getPoster());
		video.setVideo(vi.getVideo());
		
		videoDao.update(video);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		for (Video video : videoList) {
			if (video.getId().equals(id)) {
				videoDao.delete(id);
				break;
			}
		}
	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		for (Video video : videoList) {
			if (video.getId().equals(id)) {
				editVideo = video;
				request.setAttribute("editVideo", editVideo);
			}
		}
	}

	@Override
	public void tableDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		delete(request, response);
	}
	
	public void sortVideoByFavoritesASC() {
		videoList.sort(Comparator.comparing(Video::getFavoritesSize));
	}
	public void sortVideoByFavoritesDESC() {
		videoList.sort(Comparator.comparing(Video::getFavoritesSize).reversed());
	}
	
	public void sortVideoBySharesASC() {
		videoList.sort((video, other) -> Integer.compare(video.getSharesSize(), other.getSharesSize()));
	}
	
	public void sortVideoBySharesDESC() {
		videoList.sort((video, other) -> {return Integer.compare(other.getSharesSize(), video.getSharesSize());});
	}
	
	public void sortVideoByViewsASC() {
		
		Collections.sort(videoList, new Comparator<Video>() {

			@Override
			public int compare(Video o1, Video o2) {
				// TODO Auto-generated method stub
				return o1.getViews() - o2.getViews();
			}
			
		});
	}
	
	public void sortVideoByViewsDESC() {
		
		Collections.sort(videoList, new Comparator<Video>() {

			@Override
			public int compare(Video o1, Video o2) {
				// TODO Auto-generated method stub
				return o2.getViews() - o1.getViews();
			}
			
		});
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) {
		
		String userFullname = request.getParameter("userFullname");
		String findBy = request.getParameter("findBy");
		
		if (userFullname == null || findBy == null || findBy.isBlank() || videoList == null) {
			request.setAttribute("searchUserFullname", "Choose findBy first or input's empty");
			return;
		}
		
		List<Video> foundedVideos = new ArrayList<>();
		
		if (findBy.equals("favorite")) {
			 for (Video v : videoList) {
				 for (Favorite f : v.getFavorites()) {
					 if (f.getUser().getFullname().equals(userFullname)) {
						 foundedVideos.add(v);
						 break;
					 }
				 }
			 }
		} else if (findBy.equals("share")) {
			for (Video v : videoList) {
				for (Share sh : v.getShares()) {
					if (sh.getUser().getFullname().equals(userFullname)) {
						foundedVideos.add(v);
						break;
					}
				}
			}
		} else {
			return;
		}
		
		if (!foundedVideos.isEmpty()) { 
			request.setAttribute("foundedVideos", foundedVideos);
			request.setAttribute("searchUserFullname", userFullname);
		} else request.setAttribute("searchUserFullname", "No Video Found");
	}
 }
