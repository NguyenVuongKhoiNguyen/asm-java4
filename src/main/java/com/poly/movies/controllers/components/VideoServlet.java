package com.poly.movies.controllers.components;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.poly.movies.models.dao.VideoDAOImpl;
import com.poly.movies.models.entities.Video;
import com.poly.movies.utils.Xfile;

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
	
	Set<Video> videoSet = videoDao.getAllFavsAndShares();
	
	List<Video> videoList = new ArrayList<>(videoSet);

	
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
		
		sortVideoByShareDESC();
		for(Video v : videoList)
			System.out.println(v.getId() + " - " + v.getSharesSize());
		
		String pathInfo = request.getPathInfo();
		
		if (pathInfo != null) {
			switch (pathInfo) {
			case "/edit":
				edit(request, response);
				break;
			case "/table-delete":
				tableDelete(request, response);
				break;
			case "/most-views":
				break;
			case "/most-favs":
				break;
			case "/most-shares":
				break;
			}
		}
		
		request.setAttribute("newVideoId", generateNewVideoId());
		request.setAttribute("videoSet", videoSet);
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
		default:
			throw new IllegalArgumentException("Unexpected value: " + pathInfo);
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
		
		Xfile.saveFile(posterPart, posterPath);
		Xfile.saveFile(videoPart, videoPath);
		
		video.setPoster(posterPart.getSubmittedFileName());
		video.setVideo(videoPart.getSubmittedFileName());
		
		videoDao.create(video);
		videoSet = videoDao.getAllFavsAndShares();
		
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		Video vi = null;
		for (Video v : videoSet) {
			if (v.getId().equals(video.getId())) {
				vi = v;
				break;
			}
		}
		
		video.setPoster(vi.getPoster());
		video.setVideo(vi.getVideo());
		
		videoDao.update(video);
		videoSet = videoDao.getAllFavsAndShares();
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		for (Video video : videoSet) {
			if (video.getId().equals(id)) {
				videoDao.delete(id);
				videoSet = videoDao.getAllFavsAndShares();
				break;
			}
		}
	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		for (Video video : videoSet) {
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
	
	public void sortVideoByFavoriteASC() {
		videoList.sort(Comparator.comparing(Video::getFavoritesSize));
	}
	public void sortVideoByFavoriteDESC() {
		videoList.sort(Comparator.comparing(Video::getFavoritesSize).reversed());
	}
	
	public void sortVideoBySharesASC() {
		videoList.sort((video, other) -> Integer.compare(video.getSharesSize(), other.getSharesSize()));
	}
	
	public void sortVideoByShareDESC() {
		videoList.sort((video, other) -> {return Integer.compare(other.getSharesSize(), video.getSharesSize());});
	}
 }
