package com.poly.movies.controllers.components;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.dao.UserDTOdaoImpl;
import com.poly.movies.models.entities.User;
import com.poly.movies.models.entities.UserDTO;
import com.poly.movies.utils.XDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UserWithAjaxServlet
 */
@WebServlet("/user-ajax")
@MultipartConfig
public class UserWithAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserDTOdaoImpl userDTOdao = new UserDTOdaoImpl();
	
	List<UserDTO> userList = new ArrayList<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserWithAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		userList = userDTOdao.findAll();
		userList.sort(Comparator.comparing(UserDTO::getCreatedDate).reversed());
		
		Gson gson = new Gson();
		
		String json = gson.toJson(userList);

	    response.getWriter().write(json);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO user = new UserDTO();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user);
		//check if form is empty
		if (user.getId() == null || user.getId().isBlank()) {
			System.out.println("Some user inputs are empty!!!");
			return;
		}
		
		String method = request.getParameter("method");
		if ("PUT".equalsIgnoreCase(method)) {
			UserDTO temp = userDTOdao.findById(user.getId());
			user.setPhoto(temp.getPhoto());
			user.setCreatedDate(temp.getCreatedDate());
			userDTOdao.update(user);
			return;
		}
		
		user.setCreatedDate(XDate.now());
		
		Part photoPart = request.getPart("photo");
		String photoName = photoPart.getSubmittedFileName();
		System.out.println(photoName);
		if (photoName.isBlank()) {
			user.setPhoto("avata.png");
			userDTOdao.create(user);
			return;
		}
		
		
		String applicationPath = request.getServletContext().getRealPath("");
		String photoUploadPath = applicationPath + File.separator + "images" + File.separator + "user-profiles-pics";
		
		File userProfileDir = new File(photoUploadPath);
		if (!userProfileDir.exists()) userProfileDir.mkdirs();
		
		photoPart.write(photoUploadPath + File.separator + photoName);
		
		user.setPhoto(photoName);
		
		userDTOdao.create(user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Gson gson = new Gson();
		 UserDTO user = gson.fromJson(request.getReader(), UserDTO.class);
		 System.out.println(user.getId());
		 userDTOdao.update(user);
	}
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if (id != null) {
			userDTOdao.delete(id);
		}
	}
	
}
