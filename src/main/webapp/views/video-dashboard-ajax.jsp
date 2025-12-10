<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	HttpSession s = request.getSession();
	s.setAttribute("page", "user-ajax");
%>
<!DOCTYPE html>
<html  data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>User CRUD — AJAX Version</title>
<link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
    integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo/logo.png" />
</head>
<body>
	<jsp:include page="../templates/dashboard-header.jsp"></jsp:include>	
  	<jsp:include page="../templates/dashboard-side-menu.jsp"></jsp:include>
	
	<!-- USER FORM -->
	<div class="container card mb-4 p-3">
		<h2 class="mb-4">User CRUD — AJAX Version</h2>
		<form id="userForm" enctype="multipart/form-data">
			<div class="row g-3">
				<div class="col-md-6">
					<label>Username</label> <input type="text" class="form-control"
						name="id" id="id" required />
				</div>
				<div class="col-md-6">
					<label>Password</label> <input type="text" class="form-control"
						name="password" id="password" required />
				</div>
				<div class="col-md-6">
					<label>Email</label> <input type="email" class="form-control"
						name="email" id="email" required />
				</div>
				<div class="col-md-6">
					<label>Full Name</label> <input type="text" class="form-control"
						name="fullname" id="fullname" />
				</div>
				<div class="col-md-6">
					<label>Photo</label> <input type="file" class="form-control"
						name="photo" id="photo"/>
				</div>
				<div class="col-md-3 d-flex align-items-end">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="admin"
							name="admin" /> <label for="admin">Admin</label>
					</div>
				</div>
			</div>

			<div class="mt-3 d-flex justify-content-center gap-2">
				<button id="btnCreate" type="button" class="btn btn-primary" onclick="createUser()">Create</button>
				<button id="btnUpdate" type="button" class="btn btn-warning" onclick="updateUser()">Update</button>
				<button id="btnDelete" type="button" class="btn btn-danger" onclick="deleteUserInForm()">Delete</button>
				<button type="reset" class="btn btn-secondary" onclick="clearForm()">Clear</button>
			</div>
		</form>
	</div>
	<!-- USER TABLE -->
	<table class="container table table-bordered table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Password</th>
				<th>Email</th>
				<th>Fullname</th>
				<th>Role</th>
				<th>Created Date</th>
				<th>Photo</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody id="userTable"></tbody>
	</table>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous">
	</script>
	<script>
	
		let cachedUsers = [];
	
		function loadUsers() {
		    fetch("<%=request.getContextPath()%>/user-ajax")  
		        .then(response => response.json())
		        .then(users => {
		        	
		        	cachedUsers = users;
		            let tbody = document.getElementById("userTable");
		            tbody.innerHTML = ""; 
	
		            users.forEach(user => {

						const date = new Date(user.createdDate );
						const formatted = date.toLocaleDateString('en-GB');
		            	 
		            	let row = `
		            	    <tr>
		            	        <td>\${user.id}</td>
		            	        <td>\${user.password}</td>
		            	        <td>\${user.email}</td>
		            	        <td>\${user.fullname}</td>
		            	        <td>\${user.admin ? '✔' : 'X'}</td>  
		            	        <td>\${formatted}</td>
		            	        <td>\${user.photo}</td>
		            	        <td>
		            	            <button class="btn btn-warning" onclick="editUser('\${user.id}')">Edit</button>
		            	            <button class="btn btn-danger" onclick="deleteUser('\${user.id}')">Delete</button>
		            	        </td>
		            	    </tr>
		            	`;
		                tbody.innerHTML += row;
		            });
		        })
		        .catch(err => {
		            console.error("Error loading users:", err);
		        });
		}
		
		function editUser(userId) {
		    let user = cachedUsers.find(u => u.id === userId);
		    if (!user) return;

		    document.getElementById("id").value = user.id;
		    document.getElementById("password").value = user.password;
		    document.getElementById("email").value = user.email;
		    document.getElementById("fullname").value = user.fullname;
		    document.getElementById("admin").checked = user.admin;

		    document.getElementById("id").disabled = true;
		    document.getElementById("photo").disabled = true;

		    document.getElementById("btnCreate").disabled = true;
		    document.getElementById("btnUpdate").disabled = false;
		    document.getElementById("btnDelete").disabled = false;
			
		    
		    window.scrollTo({ top: 0, behavior: 'smooth' });
		}
		
		function clearForm() {
		    document.getElementById("userForm").reset();

		    document.getElementById("id").disabled = false;
		    document.getElementById("photo").disabled = false;

		    document.getElementById("btnCreate").disabled = false;
		    document.getElementById("btnUpdate").disabled = true;
		    document.getElementById("btnDelete").disabled = true;

		}
		
		
		function createUser() {
			//Get data from form
		    let form = document.getElementById("userForm");
		    let formData = new FormData(form);
			
		    // Send AJAX POST request
		    fetch("<%=request.getContextPath()%>/user-ajax", {
		        method: "POST",
		        body: formData //send it to param
		    })
		    .then(response => {
		        if (!response.ok) {
		            throw new Error("Failed to create user");
		        }
		        return response.text();
		    })
		    .then(result => {
		        alert(result + "Create oke");     // Show success message
		        loadUsers();       // Reload table
		        clearForm();       // Reset form
		    })
		    .catch(err => {
		        console.error("Create user error:", err);
		        alert("Error creating user!");
		    });
		}

		function deleteUser(id) {
			
		    if (!confirm("Are you sure you want to delete this user?")) {
		        return;
		    }

		    fetch("<%=request.getContextPath()%>/user-ajax?id=" + encodeURIComponent(id), {
		        method: "DELETE"
		    }) //have to encode URI so it doesn't mess up URL with by some special chars
		    .then(response => {
		        if (!response.ok) {
		            throw new Error("Failed to delete user");
		        }
		        return response.text();
		    })
		    .then(result => {
		        alert("User deleted successfully!");
		        loadUsers();
		        clearForm();   
		    })
		    .catch(error => {
		        console.error("Error:", error);
		        alert("Delete failed!");
		    });
		}
		
		function deleteUserInForm() {
		    let id = document.getElementById("id").value.trim();

		    if (id === "") {
		        alert("Please enter an ID to delete.");
		        return;
		    }

		    deleteUser(id);
		}
		
		function updateUser() {
			const id = document.getElementById("id").value;
		    // find the existing user in cachedUsers
		    const existingUser = cachedUsers.find(u => u.id === id);

		    if (!existingUser) {
		        alert("User not found!");
		        return;
		    }

		    const user = {
		        id: id,
		        password: document.getElementById("password").value,
		        email: document.getElementById("email").value,
		        fullname: document.getElementById("fullname").value,
		        admin: document.getElementById("admin").checked,
		        createdDate: existingUser.createdDate, // preserve existing date
		        photo: existingUser.photo              // preserve existing photo
		    };

		    fetch("<%=request.getContextPath()%>/user-ajax", {
		        method: "PUT",
		        headers: { "Content-Type": "application/json" },
		        body: JSON.stringify(user)
		    })
		    .then(res => res.text())
		    .then(result => {
		        alert(result);
		        loadUsers();
		        clearForm();
		    })
		    .catch(err => {
		        console.error(err);
		        alert("Error updating user!");
		    });
		}
		
		clearForm();
		loadUsers();
	</script>
</body>
</html>