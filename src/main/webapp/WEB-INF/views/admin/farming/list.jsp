<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>재배 방법</h1>
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
					<form name="search" method="post" action="/admin/farming/list">
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
					<form name="fboardlist" id="fboardlist">	
					<table class="table table-hover">
						<tr>
							<th><input type="checkbox" id="check_all" onclick="" /> </th>
							<th>농작물</th>
							<th>제목</th>
							<th>비고</th>
						</tr>
						<c:forEach items="${farm_list}" var="FarmingVo">
							<tr>
								<th><input type="checkbox"  name = frm_idx_array value='${FarmingVo.frm_idx}' id="frm_idx_array"/></th>
								<td><a href="<c:url value='read?frm_idx=${FarmingVo.frm_idx}'/>" >${FarmingVo.crop_name}</a></td>
								<td>${FarmingVo.frm_title}</td>
								<td><span class="badge bg-red"></span></td>
							</tr>
						</c:forEach>
					</table>
				<form>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" id='jeongil7963' value = "등록" onclick="">
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
								<li class='<c:out value="${idx == pageMaker.page?'current':''}"/>'><a href='list?page=${idx}&Keyword=${Keyword}'>${idx}</a></li>
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
	
 	$("#jeongil7963").click(function() {
		   document.fboardlist.action = "/admin/farming/regist";
		   document.fboardlist.method = "GET";
		   document.fboardlist.submit();
	});
 	$("#seldel").click(function() {
	   document.fboardlist.action = "/admin/farming/check";
	   document.fboardlist.method = "GET";
	   document.fboardlist.submit();
});
 	
});
 
</script>

<!-- /.content -->