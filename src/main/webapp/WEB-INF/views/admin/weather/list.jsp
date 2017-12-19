<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Responsive Hover Table</h3>

					<div class="box-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="table_search"
								class="form-control pull-right" placeholder="Search">

							<div class="input-group-btn">
								<button type="submit" class="btn btn-default">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover">
						<tr>
							<th><input type="checkbox" /></th>
							<th>CODE</th>
							<th>지역이름</th>
							<th>주소</th>
							<th>유닛개수</th>
						</tr>
						<c:forEach items="${list}" var="RegionVO">
							<tr>
								<th><input type="checkbox" /></th>
								<td>${RegionVO.region_code}</td>
								<td><a href='readPage?bno=${RegionVO.region_idx}'>${RegionVO.region_name}</a></td>
								<td>${RegionVO.region_addr}</td>
								<td><span class="badge bg-red"></span></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<!-- /.box-body -->
				

				<div class="box-footer">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" id='newBtn'>
							<i class="fa fa-pencil"></i> 등록
						</button>
					</div>
					<button type="reset" class="btn btn-default">
						<i class="fa fa-trash-o"></i> 선택삭제
					</button>
				</div>
				<script>
					$(document).ready(function() {

						
						$('#newBtn').on("click", function(evt) {
							evt.preventDefault();
							self.location = "regist";
						});
					});
				</script>
				
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<li class="paginate_button previous disabled"
								id="example2_previous"><a href="#" aria-controls="example2"
								data-dt-idx="0" tabindex="0">Previous</a></li>
							<li class="paginate_button active"><a href="#"
								aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
							<li class="paginate_button "><a href="#"
								aria-controls="example2" data-dt-idx="2" tabindex="0">2</a></li>
							<li class="paginate_button "><a href="#"
								aria-controls="example2" data-dt-idx="3" tabindex="0">3</a></li>
							<li class="paginate_button "><a href="#"
								aria-controls="example2" data-dt-idx="4" tabindex="0">4</a></li>
							<li class="paginate_button "><a href="#"
								aria-controls="example2" data-dt-idx="5" tabindex="0">5</a></li>
							<li class="paginate_button "><a href="#"
								aria-controls="example2" data-dt-idx="6" tabindex="0">6</a></li>
							<li class="paginate_button next" id="example2_next"><a
								href="#" aria-controls="example2" data-dt-idx="7" tabindex="0">Next</a></li>
						</ul>
					</div>
				</div>

			</div>


		</div>

		<!-- /.col -->
	</div>
	<!-- Your Page Content Here -->

</section>
<!-- /.content -->