package com.poly.movies.controllers.components;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.entities.Favorite;
import com.poly.movies.models.entities.Share;
import com.poly.movies.models.entities.User;
import com.poly.movies.utils.XDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/user", "/user/*"})
@MultipartConfig
public class UserServlet extends HttpServlet implements CrudController {
	private static final long serialVersionUID = 1L;
    
	UserDAOImpl userDao = new UserDAOImpl();
	
	List<User> userList = new ArrayList<>();
	
	String photoPath = "D:\\eclipse-workspace\\Movies\\src\\main\\webapp\\images\\user-profiles-pics";
	
	User editUser = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		userList = userDao.getAllFavsAndShares();
		
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			switch (pathInfo) {
			case "/table-delete":
				delete(request, response);
				userList = userDao.getAllFavsAndShares();
				break;
			case "/edit":
				edit(request, response);
				break;
			}
		}
		
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/views/user-dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
				
		if (pathInfo != null) {
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
					editUser = null;
					request.setAttribute("editUser", editUser);
					break;
				case "/search":
					search(request, response);
					break;
			}
		}
		doGet(request, response);
	}
	
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check if form is empty
		if (user.getId() == null || user.getId().isBlank()) {
			System.out.println("Some user inputs are empty!!!");
			return;
		}
		
		user.setCreatedDate(XDate.now());
		
		Part photoPart = request.getPart("photo");
		String photoName = photoPart.getSubmittedFileName();
		
		String applicationPath = request.getServletContext().getRealPath("");
		String photoUploadPath = applicationPath + File.separator + "images" + File.separator + "user-profiles-pics";
		
		File userProfileDir = new File(photoUploadPath);
		if (!userProfileDir.exists()) userProfileDir.mkdirs();
		
		photoPart.write(photoUploadPath + File.separator + photoName);
		
		user.setPhoto(photoName);
		
		userDao.create(user);
		userList = userDao.findAll();
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// isEmpty(): check if a string has length == 0
		// isBlank(): check if a string is empty or only contain whitespace(\t\n) or space
		if (user.getId() == null || user.getId().isBlank()) {
			System.out.println("Some user inputs are empty!!!");
			return;
		}
		
		for (User u : userList) {
			if (u.getId().equals(user.getId())) {
				user.setCreatedDate(u.getCreatedDate());
				user.setPhoto(u.getPhoto());
			}
		}
		
		userDao.update(user);
		
		userList = userDao.findAll();
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if (id != null) {
			User user = userDao.findById(id);
			userDao.delete(id);
			userList = userDao.findAll();
		}
		
	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if (id != null) {
			editUser = userDao.findById(id);
			request.setAttribute("editUser", editUser);
		}
	}

	@Override
	public void tableDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		delete(request, response);
	}
	
	public void sortUserByFavoriteASC() {
		
		Collections.sort(userList, new Comparator<User>() {
			
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o1.getUserFavoritesSize() - o2.getUserFavoritesSize();
			}
		});
	}
	
	public void sortUserByFavoriteDESC() {
		Collections.sort(userList, new Comparator<User>() {
			
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o2.getUserFavoritesSize() - o1.getUserFavoritesSize();
			}
		});
	}
	
	public void sortUserByShareASC() {
		userList.sort(Comparator.comparing(User::getUserSharesSize));
	}
	
	public void sortUserByShareDESC() {
		userList.sort(Comparator.comparing(User::getUserSharesSize).reversed());
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) {
		String videoId = request.getParameter("videoId");
		String findBy = request.getParameter("findBy");
		
		System.out.println(videoId + " - " + findBy);
		
		if (videoId == null || findBy == null || findBy.isBlank() || userList == null) {
			request.setAttribute("searchVideoId", "Choose findBy first or input's empty");
			return;
		}
		
		List<User> foundedUsers = new ArrayList<>();
		
		
		if (findBy.equals("favorite")) {
			 for (User u : userList) {
				 for (Favorite f : u.getFavorites()) {
					 if (f.getVideo().getId().equals(videoId)) {
						 foundedUsers.add(u);
						 break;
					 }
				 }
			 }
		}
		
		if (findBy.equals("share")) {
			for (User u : userList) {
				for (Share sh : u.getShares()) {
					if (sh.getVideo().getId().equals(videoId)) {
						foundedUsers.add(u);
						break;
					}
				}
			}
		} 
		
		
		
		if (!foundedUsers.isEmpty()) { 
			request.setAttribute("foundedUsers", foundedUsers); 
			request.setAttribute("searchVideoId", videoId);
		} else request.setAttribute("searchVideoId", "No User Found");
	}
}
