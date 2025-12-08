<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.poly.movies.models.entities.User" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-body-secondary sticky-top">
	    <div class="container-fluid">
	
	      <button class="btn btn-outline-success me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#sideMenu">
	      	<i class="fa-solid fa-bars"></i>
	      </button>
	
	      <a class="navbar-brand " href="${pageContext.request.contextPath}/app">
              <img class="d-inline-block align-text-top" style="width: 30px; height: 24px;" src="${pageContext.request.contextPath}/images/logo/logo.png" alt="">
              Zoechip
          </a>
          
	
	      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	
	      <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
	        <ul class="navbar-nav mb-2 mb-lg-0">
	          <!-- User Profile Dropdown -->
	          <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button"
	              data-bs-toggle="dropdown" aria-expanded="false">
	              <%-- <%
	              	HttpSession s = request.getSession();
	              	User user = (User)s.getAttribute("userLogin");
	              	System.out.println(user.getPhoto());
	              %> --%>
	              <img src="${pageContext.request.contextPath}/images/user-profiles-pics/${sessionScope.userLogin.photo}" alt="User" class="rounded-circle me-2" width="30"
	                height="30">
	              ${sessionScope.userLogin.fullname}
	            </a>
	            <ul class="dropdown-menu dropdown-menu-end">
	              <li><a class="dropdown-item" href="#">Profile</a></li>
	              <li>
	                <hr class="dropdown-divider">
	              </li>
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
	            </ul>
	          </li>
	        </ul>
	      </div>
	    </div>
	</nav>