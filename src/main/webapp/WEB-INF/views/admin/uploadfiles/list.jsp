<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>첨부파일</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12 col-xs-12">
			<div class="box">
				<div class="box-header">
					<form name="search" method="post" action="/admin/uploadfiles/list">
					<div class="box-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="Keyword" class="form-control pull-right" placeholder="Search" value='${Keyword}'>
							<div class="input-group-btn">
								<button type="submit" class="btn btn-default">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
					</form>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover">
						<tr>
							<th><input type="checkbox" id="th_checkAll" onclick="allChk();" /></th>
							<th>IDX(재배방법)</th>
							<th>파일이름</th>
							<th>경로</th>
							<th>사이즈</th>
							<th>다운로드</th>
						</tr>
						<c:forEach items="${upload_list}" var="uploadfilesVo">
							<tr>
								<th><input type="checkbox" name="checkRow" id="checkRow" value='${uploadfilesVo.frm_f_idx}' /></th>
								<td>${uploadfilesVo.pageBegin}</td>
								<td>${uploadfilesVo.frm_f_filename_org}</td>
								<td>${uploadfilesVo.frm_f_filepath}</td>
								<td>${uploadfilesVo.frm_f_size}</td>
								<td><a href="<c:url value='./download?frm_f_idx=${uploadfilesVo.frm_f_idx}'/>" > ${uploadfilesVo.frm_f_filename_org}</a></td>
								<td><span class="badge bg-red"></span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<!-- /.box-body -->
				<div class="box-footer">
					
					<button type="reset" class="btn btn-default" id="admin_user_del" name="delBtn">
						<i class="fa fa-trash-o"></i> 선택삭제
					</button>
				</div>
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li class="paginate_button next"><a href='list?page=${pageMaker.start -1}'>이전</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="idx">
								<li class='<c:out value="${idx == pageMaker.page?'current':''}"/>'><a href='list?page=${idx}&keyword=${keyword}'>${idx}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li class="paginate_button next"><a href='list?page=${pageMaker.end+1}'>다음</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- /.content -->

<script>
$(document).ready(function() {

	$("#admin_user_del").click(function() {
		alert("삭제할꼬얌");

		var chk = document.getElementsByName("checkRow"); // 체크박스객체를 담는다
		var len = chk.length; //체크박스의 전체 개수
		var checkRow = ''; //체크된 체크박스의 value를 담기위한 변수
		var checkCnt = 0; //체크된 체크박스의 개수
		var checkLast = ''; //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
		var rowid = ''; //체크된 체크박스의 모든 value 값을 담는다
		var cnt = 0;

		for (var i = 0; i < len; i++) {
			if (chk[i].checked == true) {
				checkCnt++; //체크된 체크박스의 개수
				checkLast = i; //체크된 체크박스의 인덱스
			}
		}
		console.log("checkCnt length : " + checkCnt);

		for (var i = 0; i < len; i++) {
			if (chk[i].checked == true) { //체크가 되어있는 값 구분
				checkRow = chk[i].value;
				console.log("checkRow : ");
				if (checkCnt == 1) { //체크된 체크박스의 개수가 한 개 일때,
					rowid += checkRow; //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
				} else { //체크된 체크박스의 개수가 여러 개 일때,
					if (i == checkLast) { //체크된 체크박스 중 마지막 체크박스일 때,
						rowid += checkRow; //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
					} else {
						rowid += checkRow + ','; //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
					}
				}
				cnt++;
				checkRow = ''; //checkRow초기화.
			}
		}

		if (cnt == 0) {
			alert("삭제할 사용자를 체크해 주세요");
			return;
		}
		console.log(rowid);
		
		var agree = confirm("삭제 하시겠습니까?");
		if (agree) {
			$.ajax({
				url : "/admin/uploadfiles/delete",
				type : "post",
				data : {
					"delSeqNo" : rowid
				},
				success : function(data) {
					location.reload();
				}
			});
		}
		return false;
	});
});

function allChk(obj) {
	if ($("#th_checkAll").is(':checked')) {
		$("input[name=checkRow]").prop("checked", true);
	} else {
		$("input[name=checkRow]").prop("checked", false);
	}
}
</script>