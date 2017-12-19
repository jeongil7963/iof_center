<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>유닛 등록</h1>
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
				<form class="form-horizontal" name="regist_form" method="post" action="regist">
					<input name="user_id" value="${sessionScope.user.user_id}" type='hidden'> <input name="unit_code" id="unit_code_in" value="" type='hidden'>
					<div class="box-body">
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">지역명</label>
							<div class="col-sm-2">
								<c:if test="${!empty region}">
									<select id="selectBox" name="region_idx" style="width: 80px;">
										<c:forEach var="region" items="${region}" varStatus="i">
											<option value="${region.region_idx}" data-id="${region.region_code}">${region.region_name}</option>
										</c:forEach>
									</select>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">유닛명</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="unit_name_input" name="unit_name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">셀 개수</label>
							<div class="col-sm-1">
								<select name="unit_cell_row" style="width: 80px;">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</div>
							<div class="col-sm-1">
								<select name="unit_cell_col" style="width: 80px;">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">가용 개수</label>

							<div class="col-sm-2">
								<input type="text" class="form-control" id="unit_cell_use_cnt_input" name="unit_cell_use_cnt" onkeydown="onlyNumber(this)">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">관리자명</label>

							<div class="col-sm-2">
								<input type="text" class="form-control" id="unit_manager_input" name="unit_manager">
							</div>
						</div>
					</div>
				</form>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-default" id="btnCancel">Cancel</button>
					<button type="submit" class="btn btn-info pull-right" id="btnRegist">Regist</button>
				</div>
				<!-- /.box-footer -->
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!--/row -->
	<script>
	$(document).ready(function() {	
		$('#btnCancel').on("click", function() {
			self.location = "list";
			});
		
		$('#btnRegist').on("click", function() {
			$('#unit_code_in').val($('#selectBox option:selected').attr("data-id"));
			
			if ($('#unit_cell_use_cnt_input').val() == "") {
				alert("가용 개수를 입력하지 않았습니다.")
				$('#unit_cell_use_cnt_input').focus();
				return false;
			}

			if ($('#unit_manager_input').val() == "") {
				alert("관리자명을 입력하지 않았습니다.")
				$('#unit_manager_input').focus();
				return false;
			}

			if ($('#unit_name_input').val() == "") {
				alert("유닛명를 입력하지 않았습니다.")
				$('#unit_name_input').focus();
				return false;
			}

			document.regist_form.action = "regist";
			document.regist_form.method = "POST";
			document.regist_form.submit();
			});
		});
	
	function onlyNumber(obj) {
	    $(obj).keyup(function(){
	         $(this).val($(this).val().replace(/[^0-9]/g,""));
	    }); 
	}
	</script>
</section>
<!-- /Main content -->