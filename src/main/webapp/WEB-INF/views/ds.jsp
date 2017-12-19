<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>종합페이지</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
			<li class="active">Here</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-info">
			<section class="content">

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>Select</label> <select class="form-control">
								<option>option 1</option>
								<option>option 2</option>
								<option>option 3</option>
								<option>option 4</option>
								<option>option 5</option>
							</select>
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-6">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">ON/OFF</h3>
							</div>
							<div class="box-body">
								<a class="btn btn-app"> <i class="fa fa-play"></i> ON

								</a> <a class="btn btn-app"> <i class="fa fa-pause"></i> OFF
								</a>
							</div>
							<!-- /.box-body -->
						</div>

						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>


				<div class="row">
					<div class="col-md-6">
						<!-- AREA CHART -->
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">디바이스1</h3>
								<h5 class="box-title_2">수분센서/차트</h5>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="chart">
									<canvas id="areaChart" style="height: 70px; width: 509px;" height="70" width="509"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

						<!-- DONUT CHART -->
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title">기상정보</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<canvas id="pieChart" style="height: 171px; width: 343px;" height="171" width="343"></canvas>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col (LEFT) -->
					<div class="col-md-6">
						<!-- LINE CHART -->
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">사진</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="chart">
									<img style="height: 100px" src="./dist/img/photo1.png" alt="Attachment">
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

						<!-- BAR CHART -->
						<div class="box box-success">
							<div class="box-header with-border">
								<h3 class="box-title">토양 작물 밭에 대한 정보</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="chart">
									<canvas id="barChart" style="height: 100px; width: 343px;" height="100" width="343"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col (RIGHT) -->
				</div>
				<!-- /.row -->

			</section>
		</div>
		<!-- Your Page Content Here -->
	</section>
	<!-- /.content -->