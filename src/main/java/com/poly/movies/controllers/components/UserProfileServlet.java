package com.poly.movies.controllers.components;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.movies.models.dao.UserDAOImpl;
import com.poly.movies.models.entities.Favorite;
import com.poly.movies.models.entities.Share;
import com.poly.movies.models.entities.User;
import com.poly.movies.models.entities.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/user-profile")
@MultipartConfig
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserDAOImpl userDao = new UserDAOImpl();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession s = request.getSession(false);
		
		User userLogin = (User) s.getAttribute("userLogin");
		
		System.out.println(userLogin.getId());
		
		User user = userDao.getFavAndShare(userLogin.getId());
		
		List<Favorite> favoriteList = new ArrayList<>(user.getFavorites());
		List<Share> shareList = new ArrayList<>(user.getShares());
		
		List<Video> favoriteVideo = new ArrayList<>();
		for (Favorite f : favoriteList) favoriteVideo.add(f.getVideo());
		
		List<Video> shareVideoTemp = new ArrayList<>();
		for (Share sh : shareList) shareVideoTemp.add(sh.getVideo());
		Set<Video> shareVideo = new HashSet<Video>(shareVideoTemp);
		
		request.setAttribute("userLogin", user);
		request.setAttribute("favoriteVideo", favoriteVideo);
		request.setAttribute("shareVideo", shareVideo);
		request.getRequestDispatcher("/views/user-profile.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		
		user.setId(id);
		user.setPassword(password);
		user.setEmail(email);
		user.setFullname(fullname);
		
		// isEmpty(): check if a string has length == 0
		// isBlank(): check if a string is empty or only contain whitespace(\t\n) or space
		if (user.getId() == null || user.getId().isBlank()) {
			System.out.println("Some user inputs are empty!!!");
			return;
		}
		
		User temp = userDao.findById(user.getId());
		user.setPhoto(temp.getPhoto());
		user.setCreatedDate(temp.getCreatedDate());
		user.setAdmin(temp.isAdmin());
		
		userDao.update(user);
		doGet(request, response);
	}

}
