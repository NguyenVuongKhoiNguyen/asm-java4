package com.poly.movies.controllers.components;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.entities.User;
import com.poly.movies.utils.XDate;
import com.poly.movies.utils.Xfile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UserServlet
 */
@MultipartConfig
@WebServlet({"/user", "/user/*"})
public class UserServlet extends HttpServlet implements CrudController {
	private static final long serialVersionUID = 1L;
    
	UserDAOImpl userDao = new UserDAOImpl();
	
	List<User> userList = userDao.findAll();
	
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
		
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			switch (pathInfo) {
			case "/table-delete":
				delete(request, response);
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
			default:
				throw new IllegalArgumentException("Unexpected value: " + pathInfo);
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
		
		
		
		user.setPhoto(photoPart.getSubmittedFileName());
		
		Xfile.saveFile(photoPart, photoPath);
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
		
		user.setCreatedDate(userDao.findById(user.getId()).getCreatedDate());
		
		
		Part photoPart = request.getPart("photo");
		String photoName = photoPart.getSubmittedFileName();
		if (photoName.isBlank()) {
			user.setPhoto(userDao.findById(user.getId()).getPhoto());
		} else {
			user.setPhoto(photoName);
			Xfile.saveFile(photoPart, photoPath);
			HttpSession s = request.getSession();
			s.setAttribute("userLogin", user);
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
			String posterPath = "D:\\eclipse-workspace\\Movies\\src\\main\\webapp\\images\\user-profiles-pics";
			Xfile.deleteFile(posterPath, user.getPhoto());
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

}
