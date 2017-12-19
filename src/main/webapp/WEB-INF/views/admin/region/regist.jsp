<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>지역 정보 관리</h1>
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
					<div class="box-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">지역명</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" id="region_name" name="region_name" required placeholder="지역명">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">주소</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" id="region_addr" name="region_addr" required placeholder="주소">
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
				
				<script>
					$(document).ready(function() {
						$('#btnCancel').on("click", function(evt) {
							evt.preventDefault();
							self.location = "list";
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