<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">아두이노 Regist</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form name="fboardlist" id = "fboardlist" class="form-horizontal" method="post" action="regist">
				 <div class="box-body">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">지역  </label>
              			<div class="col-sm-4"> 
              				<select class="form-control" name=region_idx id="region_select">
                        		<option value="false">선택해주세요.</option>
                        			<c:if test="${!empty region_list}">
										<c:forEach var="region_list" items="${region_list}" varStatus="i">
											<option class="region_chk" value="${region_list.region_idx}" />${region_list.region_name}
										</c:forEach>
									</c:if>
                    		</select>
                   		</div>
              	</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유닛 이름</label>
						<div class="col-sm-4" >
							<select class="form-control" name=unit_idx id="checkbox_append">
								<option value="false">선택해주세요.</option>
							</select>
						</div>
				</div>
              	<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">텃밭  </label>
              		<div class="col-sm-4">
              			<select class="form-control" name=cell_idx id="checkbox_append3">
                        	<option value="false">선택해주세요.</option>
                    	</select>
                     </div>
              	</div>
              		
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">디바이스 이름</label>
    
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="ard_name" name="ard_name" placeholder="">
                    </div>
              </div>
              
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">센서 1</label>
    
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="ard_sensor1_title" name="ard_sensor1_title" placeholder="">
                    </div>
              </div>
              
              <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">메모</label>
    
                    <div class="col-sm-8" >
                      <input type="text" class="form-control" id="ard_meno" name="ard_meno" placeholder="">
                    </div>
              </div>
              	
              	<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유저 아이디</label>
						<div class="col-sm-8">
							<div class="col-sm-4" style="padding-left: 0px;" id="text">
								<input type="text" class="form-control" style="padding-left: 0px;" id="regist_id" name="user_id">
							</div>
							<div class="col-sm-8" style="padding-left: 0px;">
								<button type="submit" id="search_id" class="btn btn-default">검색</button>
							</div>
						</div>
				</div>
				
				<div class="form-group" id="search_select" style="display:none;">
					<label for="inputPassword3" class="col-sm-2 control-label">아이디 검색</label>
					<div class="col-sm-4">
              			<select class="form-control" id="search_option">
                        
                    	</select>
                     </div>
				</div>
			</div>
					<!-- /.box-body -->
				<div class="box-footer" >
					<div class="pull-right">
						<input type="hidden" name ="idChk" id="idChk" />
						<input type="hidden" name="ras_region_idx" id="ras_region_idx" />
						<input type="hidden" name="ras_unit_idx" id="ras_unit_idx" />
						<button type="submit" class="btn btn-primary" id='regist' value = "등록" onclick="">
							<i class="fa fa-pencil"></i> 등록
						</button>
					</div>
					<a class="btn btn-default" id="btnList" href="/admin/device/arduino/list"> 목록</a>
				</div>
				</form>
				</div>
			</div>
		</div>
		
		<!-- /.col -->
	

	<!--/row -->
</section>
<!-- /Main content -->

<script type="text/javascript">
					$(document).ready(function() {
						$('#search_id').on('click', function(evt) {
							$("#search_option").empty();
							if ($('#regist_id').val().length < 2) {
								alert("2글자 이상 적어주세요")
								$('#regist_id').focus();
								return false;
							}
							
							$('#search_select').css("display", "block");
							evt.preventDefault();
							var first='<option value="false">아이디를 고르세요</option>';
							$('#search_option').append(first);
							
							$.ajax({
								type : 'post',
								url : '/user/search_id',
								data : {"user_id" : $('#regist_id').val()},
								success : function(data) {
									var jbAry = new Array();
									for(var i = 0; i<data.user_list.length; i++){
										var str = '<option value="';
										str += data.user_list[i].user_id;
										str += '">';
										str += data.user_list[i].user_id;
										str += '</option>';
										$('#search_option').append(str);
									}
								}
							
							}); //end ajax    
						}); //end on
						
						$('#search_option').on('change', function(e) {
							e.preventDefault();
							
							$('#regist_id').val($(this).val());
						    $('#search_select').css("display", "none");
						}); 
						 
						//이제까지는 아이디 검색 -------
						
						
						$("#region_select").on("change", function(evt) {
							$("#checkbox_append").empty();
							$("#checkbox_append3").empty();
							 $("#ras_region_idx").val($(this).val());
							
							var _s = $("#region_select").val();
							var fisrt_unit = '<option value="false">선택해주세요.</option>';
							$('#checkbox_append').append(fisrt_unit);
							$.ajax({
								url : "/admin/crop/check_unit",
								type : "post",
								data : {"delSeqNo" : _s	},
								success : function(data) {
									for (var i=0; i<data.UnitList.length; i++){
										var str = '<option id = "unit_idx" class="unit_chk" value="';
										str += data.UnitList[i].unit_idx;
										str += '">';
										str += data.UnitList[i].unit_name;
										str += '</option>';
										$('#checkbox_append').append(str);									
										}
									}
									

								
								});								
						});
						
						$("#checkbox_append").on("change", function(evt) {
							$('#checkbox_append3').empty();
							$("#ras_unit_idx").val($(this).val());
							var _s = $("#checkbox_append").val();
							
							$.ajax({
								url : "/admin/cell/ard_select_cell",
								type : "post",
								data : {"delSeqNo" : _s	},
								success : function(data) {
									console.log(data.CellTempList);
									var firstch = '<option value="false">선택해주세요.</option>';
									$('#checkbox_append3').append(firstch);
									for (var i=0; i<data.CellTempList.length; i++){
										var str = '<option class="cell_chk" value="';
											str += data.CellTempList[i].cell_idx;
											str += '">';
											str += data.CellTempList[i].cell_name;
											str += "</option>";
										$('#checkbox_append3').append(str);										
								}
							}
								
								
						});
							
						});
					});
				</script>
				
				<script>
    $(document).ready(function() {	
    	
		$('#regist').on("click", function() {
			
			if ($('#regist_id').val() != $('#search_option').val()) {
				alert("아이디 수정하지마세욧~!!!");
				$('#regist_id').focus();
				return false;
			}
			
			
		/* 	var chk_region = document.getElementsByName('region_idx');
			var sel_type = null;
			for(var i=0;i<chk_region.length;i++){
				if(chk_region[i].checked == true){ 
					sel_type = chk_region[i].value;
				}
			}
			if(sel_type == null){
	                alert("지역을 선택하세요"); 
				return false;
			} 
			****라디오버튼 유효성검사
			
			var chk_unit = document.getElementsByName('unit_idx');
			var unit_sel_type = null;
			for(var i=0;i<chk_unit.length;i++){
				if(chk_unit[i].checked == true){ 
					unit_sel_type = chk_unit[i].value;
				}
			}
			
			if(unit_sel_type == null){
	                alert("유닛을 선택해주세요"); 
				return false;
			}
			*/
			if ($('#region_select').val() == "false") {
				alert("지역을 입력하지 않았습니다.")
				$('#region_select').focus();
				return false;
			}
			
			if ($('#checkbox_append').val() == "false") {
				alert("유닛을 입력하지 않았습니다.")
				$('#checkbox_append').focus();
				return false;
			}
			
			if ($('#checkbox_append3').val() == "false") {
				alert("텃밭을 입력하지 않았습니다.")
				$('#checkbox_append3').focus();
				return false;
			}
			
			if ($('#ras_camera_operatingtime').val() == "false") {
				alert("카메라를 입력하지 않음")
				$('#ras_camera_operatingtime').focus();
				return false;
			}
			
			if ($('#ras_watering_operatingtime').val() == "false") {
				alert("관수를 입력하지 않음")
				$('#ras_watering_operatingtime').focus();
				return false;
			}
			
			/* if ($('#idChk').val() != "N") {
				alert("아이디가 맞지 않습니다.")
				$('#idChk').focus();
				return false;
			} */
				
				document.fboardlist.action = "/admin/device/arduino/regist";
				document.fboardlist.method = "POST";
				document.fboardlist.submit();
			
			
			
			
			});
		});
	
	</script>	