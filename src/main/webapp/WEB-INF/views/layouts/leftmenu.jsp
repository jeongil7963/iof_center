<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="sidebar">
	<!-- search form -->
	<form action="#" method="get" class="sidebar-form">
		<div class="input-group">
			<input type="text" name="q" class="form-control" placeholder="Search..."><span class="input-group-btn">
				<button type="submit" name="search" id="search-btn" class="btn btn-flat">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
	</form>
	<ul class="sidebar-menu">
		<li><a href="/user/modify_check"><i class="fa fa-circle-o text-white"></i><span>${sessionScope.user.user_id}</span></a></li>
		<li class="header">MAIN NAVIGATION</li>
		<li><a href="/ds"><i class="fa fa-circle-o text-red"></i><span>종합 정보</span></a></li>
		<li><a href="/cell/list"><i class="fa fa-circle-o text-yellow"></i><span>밭 정보</span></a></li>
		<li><a href="/admin/device/raspberry/list"><i class="fa fa-circle-o text-green"></i><span>디바이스 정보</span></a></li>
		<li><a href="#"><i class="fa fa-circle-o text-aqua"></i><span>지역 정보</span></a></li>
	</ul>
</section>