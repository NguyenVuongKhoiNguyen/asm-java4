<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession s = request.getSession();
	
	String mode = request.getParameter("mode");

	s.setAttribute("page", "register");
	
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
<title>Register</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
        integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>

	<jsp:include page="../templates/header.jsp"></jsp:include>
	
    <div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header text-center bg-success text-white">
                    <h4>Register</h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
                        <!-- User ID -->
                        <div class="mb-3">
                            <label for="id" class="form-label">User ID</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="Enter your ID" required>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                        </div>

                        <!-- Full Name -->
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter your full name" required>
                        </div>

                        <!-- Submit -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Register</button>
                        </div>

                        <!-- Link to login -->
                        <div class="mt-3 text-center">
                            <a href="login.jsp">Already have an account? Login</a>
                        </div>
                    </form>
                </div>
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