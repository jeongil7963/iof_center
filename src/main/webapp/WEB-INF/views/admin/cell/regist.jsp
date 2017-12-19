<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>셀 등록</h1>
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
				<form class="form-horizontal" method="post" action="regist">
				<input type="hidden" id="unit_code_sub" name="unit_code" value="" >
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">지역</label>

							<div class="col-sm-10">
								<c:if test="${!empty region}">
									<c:forEach var="region" items="${region}" varStatus="i">
										<input class="region_chk" type="radio" name="region_idx" value="${region.region_idx}" data-code="${region.region_code}" />${region.region_name} 
										</c:forEach>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">유닛 이름</label>

							<div class="col-sm-10" id="checkbox_append"></div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">텃밭 위치</label>

							<div class="col-sm-10">
								<table border="1" id="checkbox_append3">
									
								</table>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">텃밭 이름</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" id="region_addr" name="cell_name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">작물</label>

							<div class="col-sm-10" id="checkbox_append2"></div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">유저 아이디</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" id="regist_id" name="user_id" value="">
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
						$('#overlap').on('click', function(evt) {
							evt.preventDefault();
							$.ajax({
								type : 'POST',
								url : '/user/check_id',
								data : {
									"user_id" : $('#regist_id').val()
								},
								success : function(data) {
									if ($.trim(data) == 0) {
										alert("없는 아이디입니다.");
										$("#idChk").val('Y');
									} else {
										alert("아이디가 맞습니다.");
										$("#idChk").val('N');
									}
								}
							}); //end ajax    
						}); //end on    
						
						$('#btnCancel').on("click", function(evt) {
							evt.preventDefault();
							self.location = "list";
						});		
						
						$(".region_chk").on("click", function(evt) {							
							$("#checkbox_append").empty();
							
							$("#checkbox_append3").empty();
							$(".unit_chk").unbind('click');
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
										var str = '<input type="radio" name="unit_idx" class="unit_chk" value=';
										str += data.UnitList[i].unit_idx;
										str += " data-row=";
										str += data.UnitList[i].unit_cell_row;
										str += " data-col=";
										str += data.UnitList[i].unit_cell_col;
										str += " data-idx=";
										str += data.UnitList[i].unit_idx;
										str += " data-code=";
										str += data.UnitList[i].unit_code;
										str += '>';
										str += data.UnitList[i].unit_name;
										$('#checkbox_append').append(str);										
										}
									 $("input:radio[name=unit_idx]").click(function() 
											 { 						
									
										 $("#unit_code_sub").val($(this).attr("data-code"));
										 }); 					
	
									//작물 추가
									$(".unit_chk").on("click", function(evt) {
										$('#checkbox_append3').empty();
										
										for(var i=0; i < $(this).attr('data-row'); i++ ){
											var str = '<tr>';										
											for(var j=0; j < $(this).attr('data-col'); j++ ){
												if( i == '0' )
													{
														str += '<td><input type="radio" id="' + j + '" name="cell_code" value=' + i + j + '>' + i + j + '</td>';												
													}
												else{
														str += '<td><input type="radio" id="' + i+j + '" name="cell_code" value=' + i + j + '>' + i + j + '</td>';												
													}
												}
											str += '</tr>';
											$('#checkbox_append3').append(str);
										}
										//사용 불가능 cell
										$.ajax({
											url : "/admin/cell/check_cell",
											type : "post",
											data : {"delSeqNo" : $(this).attr('data-idx') },
											success : function(data) {									
												for (var i=0; i<data.CellList.length; i++){
														var temp = data.CellList[i].cell_code;
														var strArray = temp.split('-');
														var num = 0;
														num =  parseInt(strArray[2]);														
														console.log(num);
														$("#"+num).attr( "disabled",true );
													}															
												}
											});		
										
									
										
										$("#checkbox_append2").empty();
										var chk = document.getElementsByName("unit_idx");							
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
											url : "/admin/cell/check_crop",
											type : "post",
											data : {"delSeqNo" : _s	},
											success : function(data) {									
												for (var i=0; i<data.CropList.length; i++){
													var str = '<input  type="radio" name="crop_idx" value=';
													str += data.CropList[i].crop_idx;
													str += '>';
													str += data.CropList[i].crop_name;
													$('#checkbox_append2').append(str);
													}														
											}
											});								
									});	
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