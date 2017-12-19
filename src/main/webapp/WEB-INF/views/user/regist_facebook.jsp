<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>

<section class="content-header">
	<h1>유저 회원 수정 페이지</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="box box-info">
		<form class="form-horizontal" name="modify_form" method="post" action="">
			<div class="box-body">
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">아이디</label>
					<div class="col-sm-2">
						<input type="hidden" class="form-control" value="${user_id}" name="user_id">
						<input type="text" class="form-control" value="${nickname}" name="user_nickname" readonly>
					</div>
				</div>				
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">이름</label>

					<div class="col-sm-2">
						<input type="text" class="form-control" id="modify_name" name="user_name" value="${user_name}" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">이메일</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="modify_email" value="${user_email}" name="user_email" readonly>
					</div>
					<button type="submit" id="overlap_email" class="btn btn-default">중복확인</button>
					 ex) ya63kr@nate.com
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">연락처</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="modify_phone"  name="user_phone">
					</div>
					'-' 없이 입력하세요.
				</div>

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">주소</label> <input type="button" class="btn btn-default" value="우편번호 찾기" onclick="sample3_execDaumPostcode()">
					<div class="col-sm-2">
						<input type="text" class="form-control" id="sample3_postcode" value="${dto.user_zipcode}" name="user_zipcode">
						<div id="wrap" style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 0; position: relative">
							<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label"></label>

					<div class="col-sm-4">
						<input type="text" class="form-control" id="sample3_address" name="user_addr1" >
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label"></label>

					<div class="col-sm-4">
						<input type="text" class="form-control" id="modify_addr3" name="user_addr2" >
					</div>
				</div>
			</div>
		</form>
		<div class="box-footer">
			<div class="col-sm-5">
				<button type="submit" class="btn btn-info pull-right" id='modify_in'>등록</button>
			</div>
			<div class="col-sm-1">
				<button type="submit" class="btn btn-info pull-right" id='modify_cancel'>취소</button>
			</div>
		</div>
	</div>
	<!-- /.box-body -->


	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	<script>
		// 우편번호 찾기 찾기 화면을 넣을 element
		var element_wrap = document.getElementById('wrap');

		function foldDaumPostcode() {
			// iframe을 넣은 element를 안보이게 한다.
			element_wrap.style.display = 'none';
		}

		function sample3_execDaumPostcode() {
			// 현재 scroll 위치를 저장해놓는다.
			var currentScroll = Math.max(document.body.scrollTop,
					document.documentElement.scrollTop);
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var fullAddr = data.address; // 최종 주소 변수
							var extraAddr = ''; // 조합형 주소 변수

							// 기본 주소가 도로명 타입일때 조합한다.
							if (data.addressType === 'R') {
								//법정동명이 있을 경우 추가한다.
								if (data.bname !== '') {
									extraAddr += data.bname;
								}
								// 건물명이 있을 경우 추가한다.
								if (data.buildingName !== '') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
								fullAddr += (extraAddr !== '' ? ' ('
										+ extraAddr + ')' : '');
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample3_postcode').value = data.zonecode; //5자리 새우편번호 사용
							document.getElementById('sample3_address').value = fullAddr;

							// iframe을 넣은 element를 안보이게 한다.
							// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
							element_wrap.style.display = 'none';

							// 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
							document.body.scrollTop = currentScroll;
						},
						// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
						onresize : function(size) {
							element_wrap.style.height = size.height + 'px';
						},
						width : '100%',
						height : '100%'
					}).embed(element_wrap);

			// iframe을 넣은 element를 보이게 한다.
			element_wrap.style.display = 'block';
		}
	</script>

	<script type="text/javascript">
		function sendIt() {
			var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;						 
			
			if ($('#modify_phone').val() == "") {
				alert("연락처를 입력하지 않았습니다.")
				$('#modify_phone').focus();
				return false;
			}

			if ($('#sample3_postcode').val() == "") {
				alert("주소를 입력하지 않았습니다.")
				$('#sample3_postcode').focus();
				return false;
			}
			if ($('#sample3_address').val() == "") {
				alert("주소를 입력하지 않았습니다.")
				$('#sample3_address').focus();
				return false;
			}
			if ($('#modify_addr3').val() == "") {
				alert("주소를 입력하지 않았습니다.")
				$('#modify_addr3').focus();
				return false;
			}
		
			if (!regPhone.test($('#modify_phone').val())) {
				alert("잘못된 휴대폰 번호입니다. 숫자만 입력하세요.");
				$("#modify_phone").focus();
				return false;
			}
			
		
		}

		$(document).ready(function() {

			$("#modify_cancel").click(function() {
				location.href = "/user/login";
			});

			$("#modify_in").click(function() {
				res = sendIt();
				if (res == false) {
					
				} else {
					alert("회원 가입을 하였습니다.");
					document.modify_form.action = "/user/regist_facebook";
					document.modify_form.method = "POST";
					document.modify_form.submit();
				}
			});

		});

	</script>
	<!-- /.box-footer -->
	<!-- /.content -->
</section>