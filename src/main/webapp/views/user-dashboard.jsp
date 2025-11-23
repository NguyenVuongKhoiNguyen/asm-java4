<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
<title>User Dashboard</title>
</head>
<body>
	<section>
		<div class="d-flex">
			<jsp:include page="../templates/left-side-menu.jsp"></jsp:include>
			
			<article class="w-100">
                <div class="row py-3 px-3 bg-body-secondary" style="z-index: 9999; position: sticky; top: 0;">
                    <div class="col-sm-4">
                        <button class="btn btn-outline-success " type="button" data-bs-toggle="collapse"
                                data-bs-target="#sideBarA, #sideBarB">
                            <i class="fa-solid fa-bars"></i>
                        </button>
                    </div>
                    <div class="col-sm-4">
                        <form class="d-flex" action="">
                            <input class="form-control me-2" id="search" type="search" name="" placeholder="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                    <div class="col-sm-4">
                        <div class="d-flex align-items-center align-items-center float-end">
                            <img class="rounded-circle img-fluid" style="width: 40px;" src="${pageContext.request.contextPath}/images/user-profiles-pics/avatar.png" alt="">
                            <p class="m-0"><a class="nav-link text-white" href="">No User</a></p>
                        </div>
                    </div>
                </div>
                <div class="row mt-5">
                    <div class="w-auto mx-auto bg-body-secondary p-5 rounded-4">
                        <h3 class="">User</h3>
                        <form class="mt-3" action="">
                            <input class="form-control" type="text" placeholder="Username" name="id">
                            <input class="form-control mt-3" type="text" placeholder="Password" name="password">
                            <input class="form-control mt-3" type="email" placeholder="Email" name="email">
                            <input class="form-control mt-3" type="text" placeholder="Fullname" name="fullname">
                            <input class="form-control mt-3" type="text" placeholder="Photo" name="photo">
                            <div class="mt-3 d-flex gap-3 w-100" name="admin">
                            	<h5>Role</h5>
                            	<div class="form-check">
									<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
									 <label class="form-check-label" for="flexRadioDefault1">
									    Admin
									 </label>
								</div>
								<div class="form-check">
									  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
									  <label class="form-check-label" for="flexRadioDefault2">
									    User
									  </label>
								</div>
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-success">Create</button>
                                <button class="btn btn-info">Update</button>
                                <button class="btn btn-danger">Delete</button>
                                <button class="btn btn-secondary">Clear</button>
                            </div>
                        </form>
                    </div>
                </div>
                    <div class="row mt-5">
                        <table class="table">
	                       <thead>
	                           <tr>
	                           <th scope="col">#</th>
	                           <th scope="col">Username</th>
	                           <th scope="col">Password</th>
	                           <th scope="col">Email</th>
	                           <th scope="col">Fullname</th>
	                           <th scope="col">Photo</th>
	                           <th scope="col">Admin</th>
	                           <th scope="col"></th>
	                           </tr>
	                       </thead>
	                       <tbody>
	                       		<c:forEach var="user" items="${userList}" varStatus="status">
		                           <tr>
		                               <th>${status.count}</th>
		                               <td>${user.id}</td>
		                               <td>${user.password}</td>
		                               <td>${user.email}</td>
		                               <td>${user.fullname}</td>
		                               <td>${user.photo}</td>
		                               <td>${user.admin}</td>
		                               <td><button class="btn btn-warning">Edit</button></td>
		                           </tr>
	                       		</c:forEach>
	                       </tbody>
                    </table>
                </div>
            </article>
		</div>
	</section>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
    </script>
</body>
</html>