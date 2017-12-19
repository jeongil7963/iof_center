<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>


<!-- Main content -->
<section class="content">
	<div class="login-box">
		<div class="login-logo">
			<a href="/"><b>IOF</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">회원 정보 수정</p>

			<form name="modify_check" method="post" action="">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="Id" name="user_id" value = "${sessionScope.user.user_id}" readonly > <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password" name="user_pw"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
			</form>
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<c:if test='${sessionScope.msg == "check_fail"}'>
							<div style="font-size: 10px; color: red">비밀번호가 일치하지 않습니다.</div>
						</c:if>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4">
					<button type="submit" id="user_login" class="btn btn-primary btn-block btn-flat">로그인</button>
				</div>
				<!-- /.col -->
			</div>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<script type="text/javascript">
	$(document).ready(function() {
		$("#user_login").click(function() {
				document.modify_check.action = "modify_check";
				document.modify_check.method = "POST";
				document.modify_check.submit();
		});
	});
	</script>
</section>
<!-- /.content -->

