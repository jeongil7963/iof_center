<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>


<!-- Main content -->
<section class="content">
	<div class="login-box">
		<div class="login-logo">
			<a href="/"><b>IOF</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Login</p>

			<form name="login_form" method="post" action="">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="Id" name="user_id"> <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password" name="user_pw"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
			</form>
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<c:if test="${sessionScope.user.msg == 'fail'}">
							<div style="font-size: 10px; color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
						</c:if>
						<c:if test="${sessionScope.user.msg == 'logout'}">
							<div style="font-size: 10px; color: red">로그아웃하였습니다.</div>
						</c:if>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4">
					<button type="submit" id="user_login" class="btn btn-primary btn-block btn-flat">로그인</button>
				</div>
				<!-- /.col -->
			</div>


			<div class="social-auth-links text-center">
				<p>- OR -</p>
				<a href="${google_url}"><img class="img-responsive" src="/resources/images/google.PNG" ></a>
				<a href="${kakao_url}"><img class="img-responsive" src="/resources/images/kakao.PNG"></a>	
				<a href="${url}"><img class="img-responsive" src="/resources/images/naver.PNG" ></a>
				<img id="facebook_login" class="img-responsive" src="/resources/images/facebook.PNG" style="cursor:pointer">	
			</div>
			
			<form action="" method="post" id="facebook-form" name="facebook_form">
				<input type="hidden" name="scope" value="public_profile, email"/> 					 
			</form>
			
			<!-- /.social-auth-links -->

			<a href="#">I forgot my password</a><br> <a href="/user/regist" class="text-center">Register a new membership</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<script type="text/javascript">
	
	
		$(document).ready(function() {
			$("#user_login").click(function() {
				document.login_form.action = "login";
				document.login_form.method = "POST";
				document.login_form.submit();
			});
			
			$("#facebook_login").click(function() {
				document.facebook_form.action="/connect/facebook";
				document.facebook_form.method="POST";
				document.facebook_form.submit();
			});			
			
		});
	
		
		
	   /*  Kakao.init('eaea98d328db710b1f113b950162162f');
	    function loginWithKakao() {
	      // 로그인 창을 띄웁니다.
	      Kakao.Auth.login({
	        success: function(authObj) {
	        	alert(JSON.stringify(authObj));
	        	$.post( "/callback/kakao", { code: JSON.stringify(authObj) } );
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	    }; */
	
	</script>
</section>
<!-- /.content -->

