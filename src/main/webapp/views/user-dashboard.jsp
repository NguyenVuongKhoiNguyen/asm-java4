<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	HttpSession s = request.getSession();
	s.setAttribute("page", "user");
%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>
	<c:url value="/user" var="userUrl"></c:url>
	<jsp:include page="../templates/dashboard-header.jsp"></jsp:include>
	<jsp:include page="../templates/dashboard-side-menu.jsp"></jsp:include>
	
	<div class="container-fluid">
	    <div class="row">
	
	      <!-- Sidebar -->
	      <div class="col-3">
	        <div class="mt-3 position-sticky" style="top: 70px;">
	          <form class="d-flex justify-content-between mb-3" action="${pageContext.request.contextPath}/user/search" method="post">
	          	<div class="d-flex flex-column w-100 gap-2">
		          	<div class="d-flex gap-2">
			            <input class="form-control" type="search" name="videoId" placeholder="Enter Video Id" value="${searchVideoId == null ? '' : searchVideoId}">
			            <button type="submit" class="btn btn-outline-success"><i class="fa-solid fa-magnifying-glass"></i></button>
		          	</div>
		   			<select class="form-select" name="findBy">
		   				<option value="" disabled selected>Find By</option>
		   				<option value="favorite">Favorite</option>
		   				<option value="share">Share</option>
		   			</select>
	          	</div>
	          </form>
	          <div class="list-group position-sticky" style="top: 55px;">
	            <a class="list-group-item list-group-item-action" data-bs-toggle="collapse" href="#menuProducts">
	                Filter By Role ▼
	            </a>
	            <div class="collapse" id="menuProducts">
	                <a class="list-group-item list-group-item-action ps-4" href="#">Admin</a>
	                <a class="list-group-item list-group-item-action ps-4" href="#">User</a>
	            </div>
	          </div>
	        </div>
	      </div>
	
	      <!-- Main Content -->
	      <div class="col-9 mt-3">
	
	        <!-- Post News Form -->
	        <div class="card mb-4">
	          <div class="card-header bg-body-secondary text-white">User Form</div>
	          <div class="card-body">
	            <form method="post" enctype="multipart/form-data">
	              <div class="mb-3">
	                <label for="id" class="form-label">Username</label>
	                <input type="text" id="id" class="form-control" name="id" placeholder="Username" value="${editUser.id}" ${editUser == null ? '' : 'readonly'}>
	              </div>
	              
	              <div class="mb-3">
	                <label for="id" class="form-label">Password</label>
	                <input type="text" id="id" class="form-control" name="password" placeholder="Password" value="${editUser.password}">
	              </div>
	              
	              <div class="mb-3">
	                <label for="id" class="form-label">Email</label>
	                <input type="text" id="id" class="form-control" name="email" placeholder="Email" value="${editUser.email}">
	              </div>
	              
	              <div class="mb-3">
	                <label for="id" class="form-label">Fullname</label>
	                <input type="text" id="id" class="form-control" name="fullname" placeholder="Fullname" value="${editUser.fullname}">
	              </div>
	              
	              <div class="mb-3">
	                <label for="photo" class="form-label">Photo</label>
	                <input class="form-control" type="file" id="photo" name="photo" ${editUser != null ? 'disabled' : '' }>
	              </div>
	              
	              <div class="mb-3">
	       			<p class="mb-1">Admin</p>
	              	
	              	<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" value="true" id="isAdmin" name="admin" ${editUser.admin == true ? 'checked' : '' }>
					  <label class="form-check-label" for="isAdmin">Yes</label>
					</div>
					
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" value="false" id="notAdmin" name="admin" ${editUser.admin == false ? 'checked' : ''}>
					  <label class="form-check-label" for="notAdmin">No</label>
					</div>
	              </div>
	              
	              <div class="d-flex justify-content-center">
	                <div class="d-flex gap-3">
	                  <button type="submit" class="btn btn-success" formaction="${userUrl}/create" ${editUser != null ? 'disabled' : ''}>Create</button>
	                  <button type="submit" class="btn btn-warning" formaction="${userUrl}/update" ${editUser != null ? '' : 'disabled' }>Update</button>
	                  <button type="submit" class="btn btn-danger" formaction="${userUrl}/delete" ${editUser != null ? '' : 'disabled' }>Delete</button>
	                  <button type="submit" class="btn btn-info" formaction="${userUrl}/clear">Clear</button>
	                </div>
	              </div>
	
	            </form>
	          </div>
	        </div>
	
	        <!-- Manage News Table -->
	        <div class="card">
	          <div class="card-header bg-body-secondary text-white">
	            User Table
	          </div>
	          <div class="card-body table-responsive">
	            <table class="table table-striped table-hover">
	              <thead>
	                <tr>
	                  <th>#</th>
	                  <th>Created Date</th>
	                  <th>Username</th>
	                  <th>Password</th>
	                  <th>Email</th>
	                  <th>Fullname</th>
	                  <th>Photo</th>
	                  <th>Admin</th>
	                </tr>
	              </thead id="userTable"> 
	              	 <c:choose>
	              		<c:when test="${foundedUsers != null}">
	              			<c:forEach var="user" items="${foundedUsers}" varStatus="status">
				                <tr>
				                  <td>${status.count}</td>
				                  <td><fmt:formatDate value="${user.createdDate}" pattern="dd/MM/yyyy"/></td>
				                  <td>${user.id}</td>
				                  <td>${user.password}</td>
				                  <td>${user.email}</td>
				                  <td>${user.fullname}</td>
				                  <td>${user.photo}</td>						
				                  <td>${user.admin == true ? '✔' : 'X'}</td>   		                  
				                  <td>
									<a href="${userUrl}/edit?id=${user.id}" class="btn btn-warning">Edit</a>
									<a href="${userUrl}/table-delete?id=${user.id}" class="btn btn-danger">Delete</a>
				                  </td>
				                </tr>
			              	</c:forEach>
	              		</c:when>
	              		<c:otherwise>
	              			<c:forEach var="user" items="${userList}" varStatus="status">
				                <tr>
				                  <td>${status.count}</td>
				                  <td><fmt:formatDate value="${user.createdDate}" pattern="dd/MM/yyyy"/></td>
				                  <td>${user.id}</td>
				                  <td>${user.password}</td>
				                  <td>${user.email}</td>
				                  <td>${user.fullname}</td>
				                  <td>${user.photo}</td>						
				                  <td>${user.admin == true ? '✔' : 'X'}</td>   		                  
				                  <td>
									<a href="${userUrl}/edit?id=${user.id}" class="btn btn-warning">Edit</a>
									<a href="${userUrl}/table-delete?id=${user.id}" class="btn btn-danger">Delete</a>
				                  </td>
				                </tr>
			              	</c:forEach>
	              		</c:otherwise>
	              	</c:choose>
	              </tbody>
	            </table>
	          </div>
	        </div>
	      </div>
	    </div>
	</div>
				
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">


	</script>
</body>
</html>