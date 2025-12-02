<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	HttpSession s = request.getSession();
	s.setAttribute("page", "video");
%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Video Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>
	<c:url value="/video" var="videoUrl"></c:url>
	
	<jsp:include page="../templates/dashboard-header.jsp"></jsp:include>	
  	<jsp:include page="../templates/dashboard-side-menu.jsp"></jsp:include>

	<div class="container-fluid">
	    <div class="row">
	
	      <!-- Sidebar -->
	      <div class="col-3">
	        <div class="mt-3 position-sticky" style="top: 70px;">
	          <form class="d-flex justify-content-between mb-3" action="">
	            <input class="form-control w-75" type="search" name="" placeholder="Enter Name">
	            <button class="btn btn-outline-success"><i class="fa-solid fa-magnifying-glass"></i></button>
	          </form>
	          <div class="list-group position-sticky" style="top: 55px;">
	            <a class="list-group-item list-group-item-action" data-bs-toggle="collapse" href="#menuProducts">
	                Filter By Released Date ▼
	            </a>
	            <div class="collapse" id="menuProducts">
	                <a class="list-group-item list-group-item-action ps-4" href="#">Date</a>
	                <a class="list-group-item list-group-item-action ps-4" href="#">Month</a>
	                <a class="list-group-item list-group-item-action ps-4" href="#">Year</a>
	                <a class="list-group-item list-group-item-action ps-4" href="#">All time</a>
	            </div>
	            <a class="list-group-item list-group-item-action">Most Favorites</a>
	            <a class="list-group-item list-group-item-action">Most Shares</a>
	            <a class="list-group-item list-group-item-action">Most Views</a>
	          </div>
	        </div>
	      </div>
	
	      <!-- Main Content -->
	      <div class="col-9 mt-3">
	
	        <!-- Post News Form -->
	        <div class="card mb-4">
	          <div class="card-header bg-body-secondary text-white">Video Form</div>
	          <div class="card-body">
	            <form method="post" enctype="multipart/form-data">
	              <div class="mb-3">
	                <label for="id" class="form-label">Id</label>
	                <input type="text" id="id" class="form-control" name="id" placeholder="Video Id" value="${editVideo == null ? newVideoId : editVideo.id}" readonly>
	              </div>
	              
	              <div class="mb-3">
	                <label for="title" class="form-label">Title</label>
	                <input type="text" id="title" class="form-control" name="title" placeholder="Video Title" value="${editVideo == null ? '' : editVideo.title}">
	              </div>
	              
	              <div class="mb-3">
	                <label for="poster" class="form-label">Poster</label>
	                <input class="form-control" id="poster" type="file" name="poster" ${editVideo != null ? 'disabled' : ''}>
	              </div>
	              
	              <div class="mb-3">
	                <label for="duration" class="form-label">Duration</label>
	                <input type="text" id="duration" class="form-control" name="duration" placeholder="Video Duration" value="${editVideo == null ? '' : editVideo.duration}">
	              </div>	
	              
	              <div class="mb-3">
	                <label for="released-date" class="form-label">Released Date</label>
	                <c:set var="inputDate" value="${editVideo == null ? '' : editVideo.releasedDate}"></c:set>
	                <input type="text" id="released-date" class="form-control" name="releasedDate" placeholder="Video Released Date" value="<fmt:formatDate value='${inputDate}' pattern='dd/MM/yyyy'/>">
	              </div>	
	              
	              <div class="mb-3">
	                <label for="genre" class="form-label">Genre</label>
	                <input type="text" id="genre" class="form-control" name="genre" placeholder="Video Genre" value="${editVideo == null ? '' : editVideo.genre}">
	              </div>	
	              
	              <div class="mb-3">
	                <label for="" class="form-label">Country</label>
	                <input type="text" class="form-control" name="country" placeholder="Video Country" value="${editVideo == null ? '' : editVideo.country}">
	              </div>	
	              
	              <div class="mb-3">
	                <label for="imdb" class="form-label">IMDB</label>
	                <input type="text" id="imdb" class="form-control" name="imdb" placeholder="Video IMDB" value="${editVideo == null ? '' : editVideo.imdb}">
	              </div>	
					
	              <div class="mb-3">
	                <label for="description" class="form-label">Description</label>
	                <textarea class="form-control" id="description" rows="4" placeholder="Video Description" name="description">${editVideo == null ? '' : editVideo.description}</textarea>
	              </div>
	              
	              <div class="mb-3">
	                <label for="" class="form-label">Video</label>
	                <input class="form-control" type="file" name="video" ${editVideo != null ? 'disabled' : ''}>
	              </div>
	              
	              <div class="mb-3">
	       			<p class="mb-1">Active</p>
	              	
	              	<div class="form-check form-check-inline">
					  <input class="form-check-input" value="true" type="radio" id="active" name="active" ${editVideo.active == true ? 'checked' : ''}>
					  <label class="form-check-label" for="active">Yes</label>
					</div>
					
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" value="false" id="inactive" name="active" ${editVideo.active == false ? 'checked' : ''}>
					  <label class="form-check-label" for="inactive">No</label>
					</div>
	              </div>
	              
	              <div class="mb-3">
	                <label for="production" class="form-label">Production</label>
	                <input type="text" id="production" class="form-control" name="production" placeholder="Video Production" value="${editVideo == null ? '' : editVideo.production}">
	              </div>
	              
	              <div class="mb-3">
	       			<p class="mb-1">Trending</p>
	              	
	              	<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" value="true" id="onTrending" name="trending" ${editVideo.trending == true ? 'checked' : '' }>
					  <label class="form-check-label" for="onTrending">Yes</label>
					</div>
					
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" value="false" id="offTrending" name="trending" ${editVideo.trending == false ? 'checked' : ''}>
					  <label class="form-check-label" for="offTrending">No</label>
					</div>
	              </div>
	              
	              <div class="d-flex justify-content-center">
	                <div class="d-flex gap-3">
	                  <button type="submit" class="btn btn-success" formaction="${videoUrl}/create" ${editVideo != null ? 'disabled' : ''}>Create</button>
	                  <button type="submit" class="btn btn-warning" formaction="${videoUrl}/update"${editVideo != null ? '' : 'disabled'}>Update</button>
	                  <button type="submit" class="btn btn-danger" formaction="${videoUrl}/delete"${editVideo != null ? '' : 'disabled'}>Delete</button>
	                  <button type="submit" class="btn btn-info" formaction="${videoUrl}/clear">Clear</button>
	                </div>
	              </div>
	
	            </form>
	          </div>
	        </div>
	
	        <!-- Manage News Table -->
	        <div class="card">
	          <div class="card-header bg-body-secondary text-white">
	            News Table
	          </div>
	          <div class="card-body table-responsive">
	            <table class="table table-striped table-hover">
	              <thead>
	                <tr>
	                  <th>#</th>
	                  <th>ID</th>
	                  <th>Title</th>
	                  <th>Poster</th>
	                  <th>Duration</th>
	                  <th>Released Date</th>
	                  <th>Genre</th>
	                  <th>Country</th>
	                  <th>IMDB</th>
	                  <th>Description</th>
	                  <th>Video</th>
	                  <th>Views</th>
	                  <th>Active</th>
	                  <th>Production</th>
	                  <th>Trending</th>
	                  <th>Favorite</th>
	                  <th>Share</th>
	                </tr>
	              </thead>
	              <tbody>
	              	<c:forEach var="video" items="${videoSet}" varStatus="status">
		                <tr>
		                  <td>${status.count}</td>
		                  <td>${video.id}</td>
		                  <td>${fn:length(video.title) > 20 ? fn:substring(video.title, 0, 20) : video.title}</td>
		                  <td>${video.poster}</td>
		                  <td>${video.duration}</td>
		                  <td><fmt:formatDate value="${video.releasedDate}" pattern="dd/MM/yyyy"/></td>
		                  <td>${video.genre}</td>
		                  <td>${video.country}</td>
		                  <td>${video.imdb}</td>
		                  <td>${fn:length(video.description) > 20 ? fn:substring(video.description, 0, 20) : video.description}</td>
		                  <td>${video.video}</td>
		                  <td>${video.views}</td>
		                  <td>${video.active == true ? '✔' : 'X'}</td>
		                  <td>${video.production}</td>
		                  <td>${video.trending == true ?  '✔' :  'X'}</td>
   		                  <td>${video.favoritesSize}</td>
   		                  <td>${video.sharesSize}</td>
		                  <td>
							<a href="${videoUrl}/edit?id=${video.id}" class="btn btn-warning">Edit</a>
							<a href="${videoUrl}/table-delete?id=${video.id}" class="btn btn-danger">Delete</a>
		                  </td>
		                </tr>
	              	</c:forEach>
	              </tbody>
	            </table>
	          </div>
	        </div>
	      </div>
	    </div>
	</div>
				
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
     			 integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
	</script>
</body>
</html>