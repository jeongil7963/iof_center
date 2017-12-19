<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>디바이스</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
        <div class="box box-info" style="height:500px">
            <section class="content">
                
                        
                         <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center"> <a href="../device/weather/list">
                    기상장비
                    </a>
                </div>
                           

                <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center"><a href="../device/arduino/list">
                    아두이노
                    </a>
                </div>

				
                <div class="col-sm-2" style="float:right; border:solid 1px; width:179px; height:30px; text-align:center"> <a href="../device/raspberry/list">
                    라즈베리파이
                    </a>
                </div>
                
              <div class="col-xs-12">
                                    
                                  
                                  <div class="box" style="margin-top:20px">
                                      
                                      <div class="box-header">
                                        <div class="col-xs-2">
                                       
                                       
                                      </div>
                                      <div class="col-xs-8" style="height:30px;">
                                        <div class="col-xs-13">
                                          <div class="form-group" style="float:right;">
                                             
                                              <select class="form-control" style="height:30px">
                                                <option>지역</option>
                                                <option>option 2</option>
                                                <option>option 3</option>
                                                <option>option 4</option>
                                                <option>option 5</option>
                                              </select>
                                            </div>
                                        <!-- /.box -->
                                      </div>
                                    </div>
                                    <div class="col-xs-1">
                                        <div class="box-tools">
                                          <div class="input-group input-group-sm" style="width: 150px;">
                                            <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">
                          
                                            <div class="input-group-btn">
                                              <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                      <!-- /.box-header -->
                                      <div class="box-body table-responsive no-padding">
                                        <table class="table table-hover">
                                          <tbody><tr>
                                            <th>밭 이름</th>
                                            <th>작물 이름</th>
                                            <th>지역 이름</th>
                                            <th>구역</th>
                                            <th>선택</th>
                                            
                                          </tr>
                                          <tr>
                                            <td>183</td>
                                            <td>John Doe</td>
                                            <td>11-7-2014</td>
                                            <td><span class="label label-success">Approved</span></td>
                                            <td></td>
                                          </tr>
                                          <tr>
                                            <td>219</td>
                                            <td>Alexander Pierce</td>
                                            <td>11-7-2014</td>
                                            <td><span class="label label-warning">Pending</span></td>
                                            <td></td>
                                          </tr>
                                          <tr>
                                            <td>657</td>
                                            <td>Bob Doe</td>
                                            <td>11-7-2014</td>
                                            <td><span class="label label-primary">Approved</span></td>
                                            <td></td>
                                          </tr>
                                          <tr>
                                            <td>175</td>
                                            <td>Mike Doe</td>
                                            <td>11-7-2014</td>
                                            <td><span class="label label-danger">Denied</span></td>
                                            <td></td>
                                          </tr>
                                        </tbody></table>
                                      </div>
                                      <!-- /.box-body -->
                                    </div>
                                    <!-- /.box -->
                                  </div>
                              <!-- /.box -->
                            
                            <!-- /.col -->
                            <div class="col-md-6">
                
                                <div class="icorn" style="float:right">
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">등록</button>
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">수정</button>
                                   
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">선택삭제</button>
                                </div>
                              <!-- /.box -->
                            </div>
                            <!-- /.col -->
                         
                        
                
                      
                
                    </section>
          </div>
      <!-- Your Page Content Here -->

    </section>
<!-- /.content -->