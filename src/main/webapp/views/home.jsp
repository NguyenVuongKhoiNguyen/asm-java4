<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	HttpSession s = request.getSession();
	
	String mode = request.getParameter("mode");
	System.out.println("Theme Mode Var " + mode);

	if (mode != null && mode.equals("change")) {
		if (s.getAttribute("theme").equals("dark")) {
			s.setAttribute("theme", "light");
			s.setAttribute("themeTxt", "Light Mode");
		} else {
			s.setAttribute("theme", "dark");
			s.setAttribute("themeTxt", "Dark Mode");
		}
	} 
%>
<!DOCTYPE html>
<html data-bs-theme="${sessionScope.theme}">
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>
	<c:url var="posterUrl" value="/images/movies" ></c:url>
	<c:url var="templateUrl" value="/templates"></c:url>
	
	<jsp:include page="../templates/header.jsp"></jsp:include>
	
    <section class="mt-5">
        <div class="container">
            <h1 class="">Trending</h1>
        </div>
        <div class="container d-flex flex-wrap justify-content-start gap-1 mt-3">
	        <c:forEach var="video" items="${trendingList}">
		    	<c:url var="movieDetailUrl" value="/movie-detail">
		    	 	<c:param name="id" value="${video.id}"></c:param>
		     	</c:url>
		        <div class="card bg-body-secondary">
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
                            <p class="card-text  m-0 border p-1 rounded" style="font-size: 12px;">
                            	<a class="nav-link" href="${movieDetailUrl}">
	                            	IMDB: ${video.imdb}
                            	</a>
                            </p>
	                    </div>
	                </div>
	            </div>
	        </c:forEach>
        </div>
    </section>

    <section class="mt-5">
        <div class="container">
            <h1 class="">Latest Movies</h1>
        </div>
        <div class="container d-flex flex-wrap justify-content-start gap-1 mt-3">
        	<c:forEach var="video" items="${top7RecentReleasedList}">
		    	<c:url var="movieDetailUrl" value="/movie-detail">
		    	 	<c:param name="id" value="${video.id}"></c:param>
		     	</c:url>
		        <div class="card bg-body-secondary">
	                <div class="card-img">
	                    <a class="nav-link" href="${movieDetailUrl}">
	                        <img class="img-fluid" src="${posterUrl}/${video.poster}" alt="">
	                    </a>
	                </div>
	                <div class="card-body">
                        <h5 class="card-title" style="font-size: 15px;">
                        	<c:choose>
                        		<c:when test="${fn:length(video.title) < 18}">
                        			<a class="nav-link " href="${movieDetailUrl}">
                        				${video.title}
                        			</a> 
                        		</c:when>
	                        	<c:otherwise>
	                        		<a class="nav-link " href="${movieDetailUrl}">
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
                            <p class="card-text  m-0 border p-1 rounded" style="font-size: 12px;">
                            	<a class="nav-link " href="${movieDetailUrl}">
	                            	IMDB: ${video.imdb}
                            	</a>
                            </p>
	                    </div>
	                </div>
	            </div>
	        </c:forEach>
        </div>
    </section>

    <section class="mt-5">
        <div class="container">
            <h1 class="">Highest IMDB Score</h1>
        </div>
        <div class="container d-flex flex-wrap justify-content-start gap-1 mt-3">
        	<c:forEach var="video" items="${top7ImdbList}">
		    	<c:url var="movieDetailUrl" value="/movie-detail">
		    	 	<c:param name="id" value="${video.id}"></c:param>
		     	</c:url>
		        <div class="card bg-body-secondary">
	                <div class="card-img">
	                    <a class="nav-link" href="${movieDetailUrl}">
	                        <img class="img-fluid" src="${posterUrl}/${video.poster}" alt="">
	                    </a>
	                </div>
	                <div class="card-body">
                        <h5 class="card-title" style="font-size: 15px;">
                        	<c:choose>
                        		<c:when test="${fn:length(video.title) < 18}">
                        			<a class="nav-link " href="${movieDetailUrl}">
                        				${video.title}
                        			</a> 
                        		</c:when>
	                        	<c:otherwise>
	                        		<a class="nav-link " href="${movieDetailUrl}">
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
                            <p class="card-text  m-0 border p-1 rounded" style="font-size: 12px;">
                            	<a class="nav-link " href="${movieDetailUrl}">
	                            	IMDB: ${video.imdb}
                            	</a>
                            </p>
	                    </div>
	                </div>
	            </div>
	        </c:forEach>
        </div>
    </section>

    <jsp:include page="../templates/footer.jsp"></jsp:include>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
    </script>
</body>
</html>