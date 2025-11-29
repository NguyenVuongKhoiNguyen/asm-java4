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
	
    <section class="container mt-5 d-flex"> <!-- d-flex: !important -->
        <div class="w-auto mx-auto bg-body-secondary p-5 rounded-4">
            <h3>Register</h3>
            <form class="mt-3" action="">
                <input class="form-control" type="text" placeholder="Username" name="id">
                <input class="form-control mt-3" type="text" placeholder="Password" name="password">
                <input class="form-control mt-3" type="text" placeholder="Password Again" name="confirmPassword">
                <input class="form-control mt-3" type="email" placeholder="Email" name="email">
                <input class="form-control mt-3" type="text" placeholder="Fullname" name="fullname">
                <button class="btn btn-success mt-3 float-end">Submit</button>
            </form>
        </div>
    </section>

	<jsp:include page="../templates/footer.jsp"></jsp:include>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous">
    </script>
</body>
</html>