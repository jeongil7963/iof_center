<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<!-- Logo -->
<a href="index2.html" class="logo">IOF</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top">
	<!-- Sidebar toggle button-->
	<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span></a>
	<div class="navbar-custom-menu">
		<ul class="nav navbar-nav">
			<!-- Messages: style can be found in dropdown.less-->
			<c:if test="${sessionScope.user.user_id == null}">
				<li class="dropdown messages-menu"><a href="/user/regist" class="dropdown-toggle"> 회원가입 </a></li>
				<li class="dropdown messages-menu"><a href="/user/login" class="dropdown-toggle"> 로그인 </a></li>	
			</c:if>
			<c:if test="${sessionScope.user.user_id != null}">
				<c:if test="${sessionScope.user.user_social == null }">
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">마이 프로필</a></li>
					<li class="dropdown messages-menu"><a href="/user/logout" class="dropdown-toggle">로그아웃</a></li>
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">${sessionScope.user.user_id}</a></li>
				</c:if>
				<c:if test="${sessionScope.user.user_social eq 'naver' }">
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">마이 프로필</a></li>
					<li class="dropdown messages-menu"><a href="/user/logout" class="dropdown-toggle">로그아웃</a></li>
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">네이버로 로그인</a></li>
				</c:if>
				<c:if test="${sessionScope.user.user_social eq 'facebook' }">
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">마이 프로필</a></li>
					<li class="dropdown messages-menu"><a href="/user/logout" class="dropdown-toggle">로그아웃</a></li>
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">페이스북으로 로그인</a></li>
				</c:if>
				<c:if test="${sessionScope.user.user_social eq 'kakao' }">
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">마이 프로필</a></li>
					<li class="dropdown messages-menu"><a href="/user/logout" class="dropdown-toggle">로그아웃</a></li>
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">카카오로 로그인</a></li>
				</c:if>
				<c:if test="${sessionScope.user.user_social eq 'google' }">
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">마이 프로필</a></li>
					<li class="dropdown messages-menu"><a href="/user/logout" class="dropdown-toggle">로그아웃</a></li>
					<li class="dropdown messages-menu"><a href="/user/modify_check" class="dropdown-toggle">구글로 로그인</a></li>
				</c:if>
			</c:if>
		</ul>
	</div>
</nav>