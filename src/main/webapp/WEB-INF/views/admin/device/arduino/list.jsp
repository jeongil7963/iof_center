<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>아두이노</h1>
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
					<form name="search" method="post" action="/admin/device/raspberry/list">
					<div class="box-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="keyword" class="form-control pull-right" placeholder="Search" value='${keyword}'>
							<div class="input-group-btn">
								<button type="submit" class="btn btn-default">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
					</form>
					
					<a href="../weather/list">     
                <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center">
                  	기상정보
                </div>
              	 	 </a>
                
                 <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center">
                  	 아두이노 
                </div>
                
                	<a href="../raspberry/list">
                <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center">
                   	 라즈베리파이
                </div>
                	</a> 
               
					
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive no-padding">	
					<form name="fboardlist" id="fboardlist">	
					<table class="table table-hover">
						<tr>
							<th><input type="checkbox" id="check_all" onclick="" /> </th>
							<th>텃밭 이름</th>
							<th>디바이스 이름</th>
							<th>센서</th>
							<th>상태</th>
						</tr>
						<c:forEach items="${arduinoList}" var="ard">
							<tr>
								<th><input type="checkbox" value='${ard.ard_idx}' name="ard_idx"/></th>
								<td><a href="<c:url value='read?ard_idx=${ard.ard_idx}'/>" >${ard.ard_cell_name}</a></td>
								<td>${ard.ard_name}</td>
								<td>${ard.ard_sensor1_title}</td>
								<td>${ard.ard_meno}</td>
								<td><span class="badge bg-red"></span></td>
							</tr>
						</c:forEach>
					</table>
				<form>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" id='regist' value = "등록" onclick="">
							<i class="fa fa-pencil"></i> 등록
						</button>
					</div>
					<button type="reset" class="btn btn-default" id="seldel" value="선택삭제" onclick="">
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
		<!-- /.col -->
	</div>
	<!-- Your Page Content Here -->
</section>
<script>
$(document).ready(function() {
	//# = id
	//. = class
	$('#check_all').on("click", function(){
		if($("#check_all").prop("checked")){
			$("input[type=checkbox]").prop("checked", true);
		}else{
			$("input[type=checkbox]").prop("checked", false);
			}
});
	
 	$("#regist").click(function() {
		   document.fboardlist.action = "/admin/device/arduino/regist";
		   document.fboardlist.method = "GET";
		   document.fboardlist.submit();
	});
 	$("#seldel").click(function() {
 		
 	   document.fboardlist.action = "/admin/device/arduino/delete";
	   document.fboardlist.method = "GET";
	   document.fboardlist.submit();
});
 	
});
 
</script>

<!-- /.content -->