<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>아두이노 수정</h1>
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
					<h3 class="box-title">Arduino Regist</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form name="fboardlist" id = "fboardlist" class="form-horizontal" method="post" action="detail">
				  <div class="box-body">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">사진</label>
						<div class="col-sm-10">
							<div class="col-sm-5">사진</div> 
          					<span class="glyphicon glyphicon-play col-sm-5" aria-hidden="true">동작</span>
          					<div>날짜 : ${ard.ard_updatetime}</div>
						</div>
					</div>
				</div>
				<input type="hidden" id = "region_org_option" value="${region.region_idx}">
			<div class="box-body" style="border-top:1px solid;">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">지역  </label>
              			<div class="col-sm-4"> 
              				<select class="form-control" name=region_idx id="region_select">
                        		<c:if test="${!empty region_list}">
										<c:forEach var="region_list" items="${region_list}" varStatus="i">
											<option class="region_chk" value="${region_list.region_idx}" >${region_list.region_name}</option>
										</c:forEach>
								</c:if>
                    		</select>
                   		</div>
              	</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유닛 이름</label>
						<div class="col-sm-4" >
							<select class="form-control" name=unit_idx id="checkbox_append">
								<option id = "unit_org_option" value="${unit.unit_idx}">${unit.unit_name}</option>
							</select>
						</div>
				</div>
              	<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">텃밭  </label>
              		<div class="col-sm-4">
              			<select class="form-control" name=cell_idx id="checkbox_append3">
                        	<option id= "cell_org_option" value='${cell.cell_idx}'>${cell.cell_name}</option>
                    	</select>
                     </div>
              	</div>
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">디바이스 이름</label>
    
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="ard_name" name="ard_name" value="${ard.ard_name}" />
                    </div>
              </div>
               
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">센서 1</label>
    
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="ard_sensor1_title" name="ard_sensor1_title" value="${ard.ard_sensor1_title}" />
                    </div>
              </div>
              
              <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">메모</label>
    
                    <div class="col-sm-8" >
                      <input type="text" class="form-control" id="ard_meno" name="ard_meno" value="${ard.ard_meno}">
                    </div>
              </div>
               
              	<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유저 아이디</label>
						<div class="col-sm-8">
							<div class="col-sm-4" style="padding-left: 0px;" id="text">
								<input type="text" class="form-control" style="padding-left: 0px;" id="regist_id" name="user_id" placeholder="${ard.user_id}">
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
				
			<div class="box-body" style="border-top:1px solid;">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">DEVICE CODE</label>
					<div class="col-sm-6">
						<p class="form-control">${ard.ard_code}</p>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">API KEY</label>
					<div class="col-sm-6">
						<p class="form-control">${ard.api_key}</p>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">CHANNEL</label>
					<div class="col-sm-6">
						<p class="form-control">채널 미정</p>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">상태</label>
					<div class="col-sm-6">
						<p class="form-control">상태 미정</p>
					</div>
				</div>
			</div>
				
			</div>
					<!-- /.box-body -->
				<div class="box-footer" >
					<div class="pull-right">
						<input type="hidden" name="ard_idx" value="${ard.ard_idx}">		
						<input type="hidden" name ="idChk" id="idChk" />
						<input type="hidden" name="ard_region_idx" id="ard_region_idx" value="${region.region_idx}" />
						<input type="hidden" name="ard_unit_idx" id="ard_unit_idx" value="${unit.unit_idx}"/>
						<input type="hidden" name="org_ard_cell_idx" id="org_ard_cell_idx" value='${cell.cell_idx}'/>
						<button type="submit" class="btn btn-primary" id='regist' value = "등록" onclick="">
							<i class="fa fa-pencil"></i> 등록
						</button>
					</div>
					<a class="btn btn-default" id="btnList" href="/admin/device/arduino/list"> 취소</a>
				</div>
				</form>
				</div>
			</div>
		</div>
		<!-- /.col -->
	<!--/row -->
</section>
<!-- /Main content -->
<script>
$(document).ready(function() {
	
	var region_length = $(".region_chk").length;
		for(var i = 0; i<region_length; i++) {
			if($("#region_org_option").val() == $(".region_chk").eq(i).attr('value')){
				$(".region_chk").eq(i).attr('selected', 'true');
			}
		}
	
	var region = document.getElementById("region_org_option");
		
		var _s = $("#region_org_option").val();
		$.ajax({
			url : "/admin/crop/check_unit",
			type : "post",
			data : {"delSeqNo" : _s	},
			success : function(data) {
				var fisrt_unit = '<option value="false">선택해주세요.</option>';
				for (var i=0; i<data.UnitList.length; i++){
					
					if(data.UnitList[i].unit_idx == null){
						$('#checkbox_append').append(fisrt_unit);
					}
					
					if(data.UnitList[i].unit_idx != $("#unit_org_option").val()){
					
						var str = '<option id = "unit_idx" class="unit_chk" value="';
							str += data.UnitList[i].unit_idx;
							str += '">';
							str += data.UnitList[i].unit_name;
							str += '</option>';
					
							$('#checkbox_append').append(str);		
						}
					}
				}
	});
		
		var _s = $("#unit_org_option").val();
		
		$.ajax({
			url : "/admin/cell/ard_select_cell",
			type : "post",
			data : {"delSeqNo" : _s	},
			success : function(data) {
				console.log(data.CellTempList);
				for (var i=0; i<data.CellTempList.length; i++){
					if(data.CellTempList[i].cell_idx != $("#cell_org_option").val()){
					var str = '<option class="cell_chk" value="';
						str += data.CellTempList[i].cell_idx;
						str += '">';
						str += data.CellTempList[i].cell_name;
						str += "</option>";
					$('#checkbox_append3').append(str);			
					}
			}
		}
	});
	});

</script>

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
							
							});   
						}); 
						
						$('#search_option').on('change', function(e) {
							e.preventDefault();
							
							$('#regist_id').val($(this).val());
						    $('#search_select').css("display", "none");
						}); 
						
						//아이디 체크
						
						$("#region_select").on("change", function(evt) {
							
							$("#checkbox_append").empty();
							$("#checkbox_append3").empty();
							$("#ard_region_idx").val($(this).val());
							 
							
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
							$("#ard_unit_idx").val($(this).val());
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
			
			document.fboardlist.action = "/admin/device/arduino/detail";
			document.fboardlist.method = "POST";
			document.fboardlist.submit();
			});
		});
	
</script>
