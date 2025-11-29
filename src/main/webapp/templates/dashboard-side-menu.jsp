<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="offcanvas offcanvas-start" tabindex="-1" id="sideMenu">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title">Menu</h5>
    <button class="btn-close" data-bs-dismiss="offcanvas"></button>
  </div>

  <div class="offcanvas-body">
    <ul class="list-group">
      <li class="list-group-item bg-body-secondary">Dashboard</li>
      <li class="list-group-item ${sessionScope.page == 'user' ? 'active' : ''}"><a class="nav-link" href="${pageContext.request.contextPath}/user">User</a></li>
      <li class="list-group-item ${sessionScope.page == 'video' ? 'active' : ''}"><a class="nav-link" href="${pageContext.request.contextPath}/video">Video</a></li>
      <li class="list-group-item ${sessionScope.page == 'newsletter' ? 'active' : ''}"><a class="nav-link" href="">Newsletter</a></li>
      <li class="list-group-item ${sessionScope.page == 'like' ? 'active' : ''}"><a class="nav-link" href="">Like</a></li>
      <li class="list-group-item ${sessionScope.page == 'share' ? 'active' : ''}"><a class="nav-link" href="">Share</a></li>
      <li class="list-group-item ${sessionScope.page == 'comment' ? 'active' : ''}"><a class="nav-link" href="">Comment</a></li>
    </ul>
  </div>
</div>