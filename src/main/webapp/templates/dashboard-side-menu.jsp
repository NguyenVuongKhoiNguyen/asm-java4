<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="offcanvas offcanvas-start" tabindex="-1" id="sideMenu">
  <div class="offcanvas-header">
  	<h5 class="offcanvas-title">
        <img class="d-inline-block align-text-top" style="width: 30px; height: 24px;" src="${pageContext.request.contextPath}/images/logo/logo.png" alt="">
        Zoechip
    </h5>
    <!-- <h5 class="offcanvas-title">Menu</h5> -->
    <button class="btn-close" data-bs-dismiss="offcanvas"></button>
  </div>

  <div class="offcanvas-body">
    <ul class="list-group">
      <li class="list-group-item bg-body-secondary">Dashboard</li>
      <li class="list-group-item ${sessionScope.page == 'user' ? 'active' : ''}"><a class="nav-link" href="${pageContext.request.contextPath}/user">User</a></li>
      <li class="list-group-item ${sessionScope.page == 'video' ? 'active' : ''}"><a class="nav-link" href="${pageContext.request.contextPath}/video">Video</a></li>
      <li class="list-group-item ${sessionScope.page == 'user-ajax' ? 'active' : ''}"><a class="nav-link" href="${pageContext.request.contextPath}/views/video-dashboard-ajax.jsp">User With Ajax</a></li>
    </ul>
  </div>
</div>