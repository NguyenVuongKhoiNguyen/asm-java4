<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="userUrl" value="/user"></c:url>
<c:url var="videoUrl" value="/video"></c:url>
<c:url var="favoriteUrl" value="/favorite"></c:url>
<c:url var="shareUrl" value="/share"></c:url>
<aside class="collapse show pt-3 px-3 me-3 bg-body-secondary" id="sideBarA">
    <a class="navbar-brand " href="">
        <img class="d-inline-block align-text-top" style="width: 30px; height: 24px;" src="${pageContext.request.contextPath}/images/logo/logo.png" alt="">
        Zoechip
    </a>
    <hr class="">
    <ul class=" list-unstyled m-0 d-flex flex-column gap-3">
        <li><a class="nav-link " href="${userUrl}"><i class="fa-solid fa-user"></i> Users</a></li>
        <li><a class="nav-link " href="${favoriteUrl}"><i class="fa-solid fa-bookmark"></i> Favorite</a></li>
        <li><a class="nav-link " href="${shareUrl}"><i class="fa-solid fa-share"></i> Share</a></li>
        <li><a class="nav-link " href="${videoUrl}"><i class="fa-solid fa-clapperboard"></i> Movies</a></li>
    </ul>
</aside>
<aside class="collapse pt-3 px-3 me-3 bg-body-secondary" id="sideBarB">
    <a class="navbar-brand " href="">
        <img class="d-inline-block align-text-top" style="width: 30px; height: 24px;" src="${pageContext.request.contextPath}/images/logo/logo.png" alt="">
    </a>
    <hr class="">
    <ul class=" list-unstyled m-0 d-flex flex-column gap-3">
        <li><a class="nav-link " href="${userUrl}"><i class="fa-solid fa-user"></i></a></li>
        <li><a class="nav-link " href="${favoriteUrl}"><i class="fa-solid fa-bookmark"></i></a></li>
        <li><a class="nav-link " href="${shareUrl}"><i class="fa-solid fa-share"></i></a></li>
        <li><a class="nav-link " href="${videoUrl}"><i class="fa-solid fa-clapperboard"></i></a></li>
    </ul>
</aside>
