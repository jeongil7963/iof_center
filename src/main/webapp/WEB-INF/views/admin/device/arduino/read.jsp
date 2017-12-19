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
					<h3 class="box-title">Arduino read</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form name="fboardlist" id = "fboardlist" class="form-horizontal" >
				 <div class="box-body">
				
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">지역</label>
						<div class="col-sm-4">
							<p class="form-control">${region.region_name}</p>
							<input type="hidden" value="${region.region_idx}" /> 
						</div>
					</div>
					
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유닛 이름</label>
					<div class="col-sm-4"> 
						<p class="form-control">${unit.unit_name}</p>
						<input type="hidden" value="${unit.unit_idx}" /> 
					</div>
				</div>
              	
              	<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">텃밭  </label>
              		<div class="col-sm-4">
              			<p class="form-control">${cell.cell_name}</p>
						<input type="hidden" value="${cell.cell_idx}" />
                     </div>
              	</div>
              		
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">디바이스 이름</label>
    
                    <div class="col-sm-4">
              			<p class="form-control">${ard.ard_name}</p>
						<input type="hidden" name="ras_idx" value="${ard.ard_idx}" />
                     </div>
              </div>
              
               <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">센서1  </label>
              		<div class="col-sm-4">
                        <p class="form-control">${ard.ard_sensor1_title}</p>
                     </div>
              	</div>
              
               <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">메모 </label>
              		<div class="col-sm-4">
              			 <p class="form-control">${ard.ard_meno}</p>
                     </div>
              	</div>
              	
              	<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">유저 아이디</label>
						<div class="col-sm-8">
							<div class="col-sm-4" style="padding-left: 0px;" id="text">
								 <p class="form-control">${ard.user_id}</p>
							</div>
						</div>
				</div>
			</div>
					<!-- /.box-body -->
				<div class="box-footer" >
					<div class="pull-right">
					<input type="hidden" name ="idChk" id="idChk" />
						<a class="btn btn-primary" href="<c:url value='detail?ard_idx=${ard.ard_idx}'/>" >수정</a>
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
        