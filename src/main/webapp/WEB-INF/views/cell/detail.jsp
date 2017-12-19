<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="content-header">
	<h1>밭 상세 페이지</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="box box-info">
		<form class="form-horizontal">
			<div class="box-body">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">밭 이름</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="inputPassword3" placeholder="이름 입력">
					</div>
					<div class="col-sm-1">
						<div style="border: 1px solid black; width: 80px; padding: 3px;">현재이름</div>
					</div>
					<div class="col-sm-1">
						<div style="float: left">
							<input type="button" value="입력">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">지역</label>
						<div class="col-sm-2">
							<div style="border: 1px solid black; width: 80px; padding: 3px;">현재 지역</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">작물</label>
					<div class="col-sm-2">
						<div style="border: 1px solid black; width: 80px; padding: 3px;">현재작물</div>
					</div>
					<label for="inputPassword3" class="col-sm-4 control-label">위치</label>
					<div class="col-sm-3">
						<div style="border: 1px solid black; width: 200px; padding: 3px; height: 100px">현재 위치</div>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group">
						<label style="margin-left: 10px;" for="inputPassword3" class="col-sm-2 control-label">토양</label>
						<div class="col-sm-2">
							<div style="border: 1px solid black; width: 80px; padding: 3px;">현재토양</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- content 2-->
	<div class="box box-info">
		<form class="form-horizontal">
			<div class="box-body"></div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-4 control-label"> 날짜 </label>

				<div class="form-group"></div>
				<div class="col-sm-5" style="height: 300px; border: solid 1px; margin-left: 60px">
					<img style="width: 100%"
						src="data:image/jpeg;base64,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"
						alt="사진 없음">
				</div>
				<div class="col-sm-5" style="height: 300px; border: solid 1px; margin-left: 60px">
					<div class="_chart" style="border: solid 1px; width: 99%; height: 90px">chart</div>
					<div class="_comment" style="border: solid 1px; width: 99%; height: 90px">comment</div>
					<div class="ONOFF_BOX" style="border: solid 1px; width: 99%; height: 90px">on off</div>
				</div>
			</div>
		</form>
	</div>
	<!-- content 2-->

	<!-- content 3-->
	<div class="box box-info">
		<form class="form-horizontal">
			<div class="box-body"></div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">API_KEY</label>

				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputPassword3" placeholder="name">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Channel</label>

				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputEmail3" placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">상태</label>

				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputPassword3" placeholder="">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> Remember me
						</label>
					</div>
				</div>
			</div>
			</form>
	</div>
	<!-- content 3-->
	<!-- /.box-body -->
	<div class="box-footer">
		<button type="submit" id="cell_detail_back_bt" class="btn btn-info pull-right">돌아가기</button>
		<button type="submit" id="cell_modify_bt" class="btn btn-info pull-right">수정</button>
	</div>
	<!-- /.box-footer -->
</section>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#cell_detail_back_bt").click(function() {
			location.href = "/cell/list";
		});

		$("#cell_modify_bt").click(function() {
			location.href = "/cell/modify";
		});

	});
</script>