<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<section class="content-header">
	<h1>cell페이지</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Home Page</h3>
					<br> <a href="user/login">로그인</a> <br> <a href="user/regist">회원가입</a> <br> <a href="user/modify_check">회원정보수정</a> <br> 회원 아이디 : ${sessionScope.user.user_id} <br> 회원 레벨 : ${sessionScope.user.user_level}

				</div>

				<button id="shoot">사진 촬영</button>
				<button id="option">옵션 설정</button>
				<button id="water_start">관수 시작</button>
				<button id="water_stop">관수 중지</button><br><br>
				
				상하 반전 : <input id="vr"><br>
				좌우 반전 : <input id="hr"><br>
				명암 : <input id="ct" value="0"><br>
				채도 : <input id="sa" value="0"><br>
				밝기 : <input id="br" value="50"><br>
				<button id="camera_option">설정 적용 </button>					
				<!-- /.box-header -->
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/socket.io.js"/>"></script>
<script>
	var socket = io('http://localhost:5000');
	socket.on('connect', function() {
	});

	socket.on('839ca2c5d60342309677f6f65ffc809c', function(evt) {
		console.log(evt);
	});
	
	$(function() {

		$('#shoot').on("click", function(evt) {
			var obj = {
				user_token : "839ca2c5d60342309677f6f65ffc809c",
				api_key : "58a7ff45425f4d6d809c023ee1790aa2",
				msg : "shoot"
			};
			socket.emit('chat', obj);
		});

		$('#option').on("click", function(evt) {
			var obj = {
				user_token : "839ca2c5d60342309677f6f65ffc809c",
				api_key : "58a7ff45425f4d6d809c023ee1790aa2",
				msg : "option"
			};
			socket.emit('chat', obj);
		});

		$('#water_start').on("click", function(evt) {
			var obj = {
				user_token : "839ca2c5d60342309677f6f65ffc809c",
				api_key : "58a7ff45425f4d6d809c023ee1790aa2",
				msg : "water_start"
			};
			socket.emit('chat', obj);
		});

		$('#water_stop').on("click", function(evt) {
			var obj = {
				user_token : "839ca2c5d60342309677f6f65ffc809c",
				api_key : "58a7ff45425f4d6d809c023ee1790aa2",
				msg : "water_stop"
			};
			socket.emit('chat', obj);
		});
		
		$('#camera_option').on("click", function(evt) {
			var aaa = $('#vr').val();
			var bbb = $('#hr').val();
			var ccc = $('#ct').val();
			var ddd = $('#sa').val();
			var eee = $('#br').val();
			
			var obj = {
					user_token : "839ca2c5d60342309677f6f65ffc809c",
					api_key : "58a7ff45425f4d6d809c023ee1790aa2",
					msg : "camera_option",
					vr : aaa,
					hr : bbb,
					ct : ccc,
					sa : ddd,
					br : eee
			};
			socket.emit('chat', obj);
		});
	});

	socket.on('disconnect', function() {
	});
</script>
