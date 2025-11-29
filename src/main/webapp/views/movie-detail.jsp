<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	HttpSession s = request.getSession();
	
	String mode = request.getParameter("mode");
	
	s.setAttribute("page", "movie-detail");

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
<title>Movie Detail</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body class="">
	<c:url var="posterUrl" value="/images/movies"></c:url>
	

	<jsp:include page="../templates/header.jsp"></jsp:include>
	
	<section class="container mt-5">
        <div class="ratio ratio-16x9">
            <iframe src="${pageContext.request.contextPath}/videos/${video.video}" allowfullscreen></iframe>
        </div>
    </section>
	
	<section class="container mt-5">
        <div class="row bg-body-secondary mt-3 rounded-5">
            <div class="row px-5 py-4">
                <aside class="col-sm-2 p-0">
                    <div class="row">
                        <img class="p-0" src="${posterUrl}/${video.poster}" alt="">
                    </div>
                    <div class="row mt-3">
                        <p class=" m-0 p-0">${video.likes} <span class="text-success"><i class="fa-solid fa-thumbs-up"></i></span> • ${video.dislikes} <span class="text-danger"><i class="fa-solid fa-thumbs-down"></i></span> • ${video.views} Views</p>
                    </div>
                    <div class="row py-3">
                        <hr class=" m-0">
                    </div>
                    <div class="row">
                        <a class="nav-link  bg-success text-center col-lg-5 py-1" href=""><i class="fa-solid fa-thumbs-up"></i> Like</a>
                        <a class="nav-link  bg-secondary text-center col-lg-5 py-1 ms-auto" href=""><i class="fa-solid fa-thumbs-down"></i> Dislike</a>
                    </div>
                </aside>
                <article class="col-sm-9 ms-auto">
                    <div class="row">
                        <div class="w-auto p-0">
                        	<%
                        		HttpSession se = request.getSession();
                        		System.out.println(se.getAttribute("userLogin"));
                        	%>
                    		<c:choose>
                    			<c:when test="${sessionScope.userLogin == null}">
                    				<a class="btn btn-success" href="${pageContext.request.contextPath}/login?pageChange=detail">
                    					<i class="fa-solid fa-share"></i> Share
                    				</a>
                    			</c:when>
                    			<c:otherwise>
                    				<a class="btn btn-success" data-bs-toggle="modal" data-bs-target="#shareModal">
                    					<i class="fa-solid fa-share"></i> Share
                    				</a>
                    			</c:otherwise>
                    		</c:choose>
                        </div>
                        <div class="w-auto p-0 ms-3">
                        	<c:choose>
                        		<c:when test="${sessionScope.userLogin == null}">
                        			<a class="btn btn-success" href="${pageContext.request.contextPath}/login?pageChange=detail">
                        				${userFavVid == null ? '<i class="fa-regular fa-bookmark"></i>' : '<i class="fa-solid fa-bookmark"></i>'} Add to favorite
                        			</a>
                        		</c:when>
                        		<c:otherwise>
                        			<a class="btn btn-success" href="${pageContext.request.contextPath}/movie-detail/favorite">
                        				${userFavVid == null ? '<i class="fa-regular fa-bookmark"></i>' : '<i class="fa-solid fa-bookmark"></i>'} Add to favorite
                        			</a>
                        		</c:otherwise>
                        	</c:choose>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="shareModal" tabindex="-1" >
                            <div class="modal-dialog">
                                <div class="modal-content bg-body-secondary">
                                    <div class="modal-header border-0">
                                        <h1 class="modal-title fs-5 " id="exampleModalLabel">Enter Email</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form class="container" action="">
                                        <input class="form-control border-0" type="email" name="email" id="" placeholder="Enter Your Friend Email Here">
	                                    <div class="modal-footer border-0">
	                                        <button type="button" class="btn btn-success" formaction="/movie-detail/share">Submit</button>
	                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <h1 class=" m-0 p-0">${video.title}</h1>
                    </div>
                    <div class="row mt-3">
                        <div class="p-0 d-flex ">
                            <p class=" m-0 border p-1 rounded">HD</p>
                            <p class=" m-0 border p-1 rounded ms-3"><span class="text-warning">IMDB:</span> ${video.imdb}</p>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <p class=" m-0 p-0">${video.description}</p>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm-5 p-0">
                            <p class=" m-0">
                            	<span class="fw-semibold">Release: </span>
                            	<fmt:formatDate value="${video.releasedDate}" pattern="dd/MM/yyyy" />
                            </p>
                            <p class=" m-0"><span class="fw-semibold">Genre: </span>${video.genre}</p>
                            <p class=" m-0"><span class="fw-semibold">Country: </span>${video.country}</p>
                        </div>
                        <div class="col-sm-5 p-0">
                            <p class=" m-0"><span class="fw-semibold">Duration: </span>${video.duration} minute</p>
                            <p class=" m-0"><span class="fw-semibold">Studio: </span>${video.production}</p>
                        </div>
                    </div>
                </article>
            </div>
        </div>
    </section>
	
	<jsp:include page="../templates/footer.jsp"></jsp:include>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
    </script>
</body>
</html>