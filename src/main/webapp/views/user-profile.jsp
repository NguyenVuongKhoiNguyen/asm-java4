<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	HttpSession s = request.getSession();
	
	String mode = request.getParameter("mode");

	s.setAttribute("page", "user-profile");
	
	if (mode != null && mode.equals("change")) {
		if (s.getAttribute("theme").equals("dark")) {
			s.setAttribute("theme", "light");
			s.setAttribute("themeTxt", "Dark Mode");
		} else {
			s.setAttribute("theme", "dark");
			s.setAttribute("themeTxt", "Light Mode");
		}
	} 
%>
<!DOCTYPE html>
<html data-bs-theme="${sessionScope.theme}">
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>
	<c:url var="posterUrl" value="/images/movies"></c:url>
	
	<jsp:include page="../templates/header.jsp"></jsp:include>
	<div class="container mt-5">
    	<h2>Update User Profile</h2>
    	<form action="${pageContext.request.contextPath}/user-profile" method="post" enctype="multipart/form-data">
	        <div class="mb-3">
	            <label for="id" class="form-label">User ID</label>
	            <input type="text" class="form-control" id="id" name="id" value="${userLogin.id}" readonly>
	        </div>
	
	        <div class="mb-3">
	            <label for="password" class="form-label">Password</label>
	            <input type="text" class="form-control" id="" name="password" value="${userLogin.password}">
	        </div>
	
	        <div class="mb-3">
	            <label for="email" class="form-label">Email</label>
	            <input type="email" class="form-control" id="email" name="email" value="${userLogin.email}">
	        </div>
	
	        <div class="mb-3">
	            <label for="fullname" class="form-label">Full Name</label>
	            <input type="text" class="form-control" id="fullname" name="fullname" value="${userLogin.fullname}">
	        </div>
	
	        <div class="mb-3 form-check">
	            <input type="checkbox" class="form-check-input" id="admin" name="admin" ${userLogin.admin ? 'checked' : ''} disabled>
	            <label class="form-check-label" for="admin">Admin</label>
	        </div>
	
	        <div class="mb-3">
	            <label for="createdDate" class="form-label">Created Date</label>
	            <input type="text" class="form-control" id="createdDate" name="createdDate" value="${userLogin.createdDate}" readonly>
	        </div>
	
	        <button type="submit" class="btn btn-primary">Update</button>
	        <button type="reset" class="btn btn-secondary">Clear</button>
	    </form>
	</div>
	
	<div class="container mt-5">
	    <h2>2-Panel Tabs Example</h2>
	
	    <!-- Nav tabs -->
	    <ul class="nav nav-tabs" id="myTab" role="tablist">
	        <li class="nav-item" role="presentation">
	            <button class="nav-link active" id="panel1-tab" data-bs-toggle="tab" data-bs-target="#panel1" type="button" role="tab" aria-controls="panel1" aria-selected="true">Favorites</button>
	        </li>
	        <li class="nav-item" role="presentation">
	            <button class="nav-link" id="panel2-tab" data-bs-toggle="tab" data-bs-target="#panel2" type="button" role="tab" aria-controls="panel2" aria-selected="false">Shares</button>
	        </li>
	    </ul>
	
	    <!-- Tab content -->
	    <div class="tab-content mt-3" id="myTabContent">
	        <div class="tab-pane fade show active" id="panel1" role="tabpanel" aria-labelledby="panel1-tab">
	            <div class="d-flex flex-wrap justify-content-start gap-1 mt-3">
			        <c:forEach var="video" items="${favoriteVideo}">
				    	<c:url var="movieDetailUrl" value="/movie-detail">
				    	 	<c:param name="id" value="${video.id}"></c:param>
				     	</c:url>
				        <div class="card ">
			                <div class="card-img">
			                    <a class="nav-link" href="${movieDetailUrl}">
			                        <img class="img-fluid" src="${posterUrl}/${video.poster}" alt="">
			                    </a>
			                </div>
			                <div class="card-body">
		                        <h5 class="card-title" style="font-size: 15px;">
		                        	<c:choose>
		                        		<c:when test="${fn:length(video.title) < 18}">
		                        			<a class="nav-link" href="${movieDetailUrl}">
		                        				${video.title}
		                        			</a> 
		                        		</c:when>
			                        	<c:otherwise>
			                        		<a class="nav-link" href="${movieDetailUrl}">
				                        		${fn:substring(video.title, 0, 17)}...
			                        		</a>
			                        	</c:otherwise>
		                        	</c:choose>
		                        </h5>
			                    <div class="d-flex justify-content-between align-items-center">
		                            <p class="card-text  m-0" style="font-size: 12px;">
		                            	<a class="nav-link" href="${movieDetailUrl}">
		                    	        	${fn:substring(video.releasedDate, 0, 4)} • ${fn:substring(video.duration, 0, 4)}m
		                            	</a>
		                            </p>
		                            <p class="card-text m-0 border p-1 rounded" style="font-size: 12px;">
		                            	<a class="nav-link" href="${movieDetailUrl}">
			                            	IMDB: ${video.imdb}
		                            	</a>
		                            </p>
			                    </div>
			                </div>
			            </div>
			        </c:forEach>
		        </div>
	        </div>
	        <div class="tab-pane fade" id="panel2" role="tabpanel" aria-labelledby="panel2-tab">
	            <div class="d-flex flex-wrap justify-content-start gap-1 mt-3">
			        <c:forEach var="video" items="${shareVideo}">
				    	<c:url var="movieDetailUrl" value="/movie-detail">
				    	 	<c:param name="id" value="${video.id}"></c:param>
				     	</c:url>
				        <div class="card ">
			                <div class="card-img">
			                    <a class="nav-link" href="${movieDetailUrl}">
			                        <img class="img-fluid" src="${posterUrl}/${video.poster}" alt="">
			                    </a>
			                </div>
			                <div class="card-body">
		                        <h5 class="card-title" style="font-size: 15px;">
		                        	<c:choose>
		                        		<c:when test="${fn:length(video.title) < 18}">
		                        			<a class="nav-link" href="${movieDetailUrl}">
		                        				${video.title}
		                        			</a> 
		                        		</c:when>
			                        	<c:otherwise>
			                        		<a class="nav-link" href="${movieDetailUrl}">
				                        		${fn:substring(video.title, 0, 17)}...
			                        		</a>
			                        	</c:otherwise>
		                        	</c:choose>
		                        </h5>
			                    <div class="d-flex justify-content-between align-items-center">
		                            <p class="card-text  m-0" style="font-size: 12px;">
		                            	<a class="nav-link" href="${movieDetailUrl}">
		                    	        	${fn:substring(video.releasedDate, 0, 4)} • ${fn:substring(video.duration, 0, 4)}m
		                            	</a>
		                            </p>
		                            <p class="card-text m-0 border p-1 rounded" style="font-size: 12px;">
		                            	<a class="nav-link" href="${movieDetailUrl}">
			                            	IMDB: ${video.imdb}
		                            	</a>
		                            </p>
			                    </div>
			                </div>
			            </div>
			        </c:forEach>
		        </div>
	        </div>
	    </div>
	</div>
	
	<jsp:include page="../templates/footer.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
    </script>
</body>
</html>