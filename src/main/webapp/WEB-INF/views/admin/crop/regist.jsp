<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>농작물 정보 관리</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12 col-xs-12">

			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">Region Form</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal" name=exf1 method="POST" action="regist">
					<input type="hidden" name="user_id" value="${sessionScope.user.user_id}">
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">지역</label>

							<div class="col-sm-10">
								<c:if test="${!empty region}">
									<c:forEach var="region" items="${region}" varStatus="i">
										<input class="region_chk" type="checkbox" name="region_idx" value="${region.region_idx}" data-code="${region.region_code}" />${region.region_name} 
										</c:forEach>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">농작지</label>

							<div class="col-sm-10" id="checkbox_append"></div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">작물 이름</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="crop_name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">파종 시기</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="crop_timing">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">추천 토양</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="crop_soil">
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-default" id="btnCancel">Cancel</button>
						<button type="submit" class="btn btn-info pull-right">Regist</button>
					</div>
					<!-- /.box-footer -->
				</form>

				<script type="text/javascript">
					$(document).ready(function() {
						$('#btnCancel').on("click", function(evt) {
							evt.preventDefault();
							self.location = "list";
						});		
						
						$(".region_chk").on("click", function(evt) {							
							$("#checkbox_append").empty();
							var chk = document.getElementsByName("region_idx");							
							var _s = "";							
							var chk2 = 1;
							var checkCnt = 0;						
							var len = chk.length;							
							for (var i = 0; i < len; i++) {
								if (chk[i].checked == true) {
									if(chk2 ==1){
										_s += chk[i].value;
										chk2++;
									}else{
										_s += "," + chk[i].value;
									}
								}
							}							
							$.ajax({
								url : "/admin/crop/check_unit",
								type : "post",
								data : {"delSeqNo" : _s	},
								success : function(data) {
									console.log(data.UnitList);	
									for (var i=0; i<data.UnitList.length; i++){
										var str = '<input type="checkbox" name="unit_idx" value=';
										str += data.UnitList[i].unit_idx;
										str += '>';
										str += data.UnitList[i].unit_name;
										$('#checkbox_append').append(str);
										}
									}
								});								
						});		
					});
					
				
				</script>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!--/row -->
</section>
<!-- /Main content -->