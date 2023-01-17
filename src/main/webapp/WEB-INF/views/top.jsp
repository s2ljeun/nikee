<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nikee</title>
	<!-- Bootstrap & Jquery -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<script src="resources/js/jquery-3.6.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	
	<!-- carousel -->
	<link href="/resources/css/carousel.css" rel="stylesheet">
	
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/index">NIKEE</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
       		<c:if test="${empty userDetail}">
        		<li class="nav-item">
					<a class="nav-link" href="/login" >로그인</a>
				</li>
			</c:if>
			<c:if test="${not empty userDetail}">
				<li class="nav-item">
					<a class="nav-link" href="/logoutProc">로그아웃</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/mypage">마이페이지</a>
				</li>
			</c:if>
			<c:if test="${not empty userDetail}">
        		<li class="nav-item">
					<a class="nav-link" href="/cart" >장바구니</a>
				</li>
			</c:if>
        </ul>
      </div>
    </div>
  </nav>
</header>