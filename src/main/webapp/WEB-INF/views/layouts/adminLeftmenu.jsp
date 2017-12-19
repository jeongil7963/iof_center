<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- sidebar: style can be found in sidebar.less -->

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
		<li><a href="/admin/region/list"> <i class="fa fa-circle-o text-danger"></i><span>지역정보</span></a></li>
		<li><a href="/admin/unit/list"> <i class="fa fa-circle-o text-red"></i> <span>유닛정보</span></a></li>
		<li><a href="/admin/cell/list"> <i class="fa fa-circle-o text-yellow"></i> <span>셀정보</span></a></li>
		<li><a href="/admin/user/list"> <i class="fa fa-circle-o text-green"></i> <span>회원정보</span></a></li>
		<li><a href="/admin/crop/list"> <i class="fa fa-circle-o text-success"></i> <span>농작물</span></a></li>
		<li><a href="/admin/farming/list"> <i class="fa fa-circle-o text-aqua"></i> <span>재배방법</span></a></li>
		<li><a href="/admin/device/raspberry/list"> <i class="fa fa-circle-o text-blue"></i> <span>디바이스</span></a></li>
		<li><a href="/admin/uploadfiles/list"> <i class="fa fa-circle-o text-purple"></i><span>첨부 파일</span></a></li>
	</ul>
</section>