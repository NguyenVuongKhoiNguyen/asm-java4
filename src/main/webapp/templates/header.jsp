<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="sticky-top">
	
	<c:url var="movieUrl" value="/movie"></c:url>
	<c:url var="indexUrl" value="/app"></c:url>
	<c:url var="loginUrl" value="/login"></c:url>
	<c:url var="registerUrl" value="/register"></c:url>
	<c:url var="changePasswordUrl" value="/change-password"></c:url>
	<c:url var="avatarUrl" value="/images/user-profiles-pics"></c:url>
	<c:url var="homeThemeModeUrl" value="/app">
		<c:param name="mode" value="change"></c:param>
	</c:url>
	<c:url var="movieThemeModeUrl" value="/movie">
		<c:param name="mode" value="change"></c:param>
	</c:url>
	<c:url var="movieDetailThemeModeUrl" value="/movie-detail">
		<c:param name="mode" value="change"></c:param>
	</c:url>
	<c:url var="loginThemeModeUrl" value="/login">
		<c:param name="mode" value="change"></c:param>
	</c:url>
	<c:url var="registerThemeModeUrl" value="/register">
		<c:param name="mode" value="change"></c:param>
	</c:url>
	<c:url var="changePasswordThemeModeUrl" value="/change-password">
		<c:param name="mode" value="change"></c:param>
	</c:url>
    <!-- Nav Bar -->
    <nav class="navbar navbar-expand-lg bg-body-secondary">
		
        <!-- Nav Bar Container -->
        <div class="container ">

            <!--Logo-->
            <a class="navbar-brand " href="${indexUrl}">
                <img class="d-inline-block align-text-top" style="width: 30px; height: 24px;" src="./images/logo/logo.png" alt="">
                Zoechip
            </a>

            <!--Hamburger Menu Button-->
            <button class="navbar-toggler " type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent">
                <i class="fa-solid fa-bars"></i>
            </button>

            <!--Hamburger Menu-->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <!-- Nav Link Group -->
                <ul class="navbar-nav me-auto"> <!-- me-auto == justify-content-between -->

                    <!-- Nav Link -->
                    <li class="nav-item">
                        <a class="nav-link " href="${indexUrl}">Home</a>
                    </li>

                    <!-- Nav Link -->
                    <li class="nav-item">
                        <a class="nav-link " href="${movieUrl}">Movies</a>
                    </li>

                    <!-- Nav Link -->
                    <li class="nav-item">
                        <a class="nav-link " href="">Contact Us</a>
                    </li>
					
					<li class="nav-item">
						<c:choose>
							<c:when test="${sessionScope.page == 'home'}">
								<a class="nav-link" href="${homeThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:when test="${sessionScope.page == 'movie'}">
								<a class="nav-link" href="${movieThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:when test="${sessionScope.page == 'movie-detail'}">
								<a class="nav-link" href="${movieDetailThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:when test="${sessionScope.page == 'login'}">
								<a class="nav-link" href="${loginThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:when test="${sessionScope.page == 'register'}">
								<a class="nav-link" href="${registerThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:when test="${sessionScope.page == 'change-password'}">
								<a class="nav-link" href="${changePasswordThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${homeThemeModeUrl}">${sessionScope.themeTxt}</a>		
							</c:otherwise>
						</c:choose>
						
						
					</li>
					
                    <!-- Nav Link Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle " data-bs-toggle="dropdown" href="">Account</a>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <li>
                                <a class="dropdown-item" href="${loginUrl}">Login</a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <a class="dropdown-item" href="${registerUrl}">Register</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${changePasswordUrl}">Change Password</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <!-- Nav Search -->
                <form class="d-flex me-5" action="">
                    <input class="form-control me-2" id="search" type="search" name="" placeholder="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
				<!-- Nav User Profile -->
                <div class="d-flex align-items-center">
                    <img class="rounded-circle img-fluid" style="width: 40px;" src="${avatarUrl}/avatar.png" alt="">
                    <p class="m-0 ms-3"><a class="nav-link" href="">No User</a></p>
                </div>
            </div>
        </div>
    </nav>
</header>


    