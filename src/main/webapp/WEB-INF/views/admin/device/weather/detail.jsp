<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>기상정보</h1>
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
              
                <div class="col-sm-6" style="border-right-style: solid;  height:240px; text-align:center; margin-top:0px; 
                margin-bottom:0px;"> 
                
                <img style="margin:auto;" src="data:image/jpeg;base64,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" alt="사진 없음"> 

                   
                </div>
                <div class="col-sm-4">
                    
                            <a class="btn btn-app">
                                    <i class="fa fa-play"></i> ON
                                    
                                  </a>
                                  <a class="btn btn-app">
                                        <i class="fa fa-pause"></i> OFF
                                      </a>
                                      
                                      <div style="border:solid 1px; text-align:center; height:25px; width:200px">날짜</div>
                                  
                    
                                    </div>
              
                    
            </div>
<!-- content 2-->
            <div class="box box-info">
                    
                      <div class="box-body">
                            <div class="form-group col-sm-6">

                                    <label for="inputEmail3" class="col-sm-2 control-label">텃밭 </label>
                    
                                    <div class="col-sm-4">
                                            <select class="form-control" style="width:348px">
                                                    <option>option 1</option>
                                                    <option>option 2</option>
                                                    <option>option 3</option>
                                                    <option>option 4</option>
                                                    <option>option 5</option>
                                                  </select>
                                    </div>
                                  </div>  

                                  <div class="form-group col-sm-6">
                                        <label for="inputEmail3" class="col-sm-2 control-label">카메라</label>
                        
                                        <div class="col-sm-4">
                                          <input type="text" class="form-control" id="inputEmail3" placeholder="" style="width:348px">
                                        </div>
                                      </div>

                                      <div class="form-group col-sm-6">
                                            <label for="inputEmail3" class="col-sm-2 control-label">디바이스 이름</label>
                            
                                            <div class="col-sm-4">
                                              <input type="text" class="form-control" id="inputEmail3" placeholder="" style="width:348px">
                                            </div>
                                          </div>


                                          <div class="form-group col-sm-6">
                                                <label for="inputEmail3" class="col-sm-2 control-label">관수</label>
                                
                                                <div class="col-sm-4">
                                                  <input type="text" class="form-control" id="inputEmail3" placeholder="" style="width:348px">
                                                </div>
                                              </div>


                                             
                                      
                      </div>
       
              
              
                
               
            <!-- content 2-->

            <!-- content 3-->
            <div class="box box-info">
                    
                      <div class="box-body">
                                 
                      </div>
       
              
              
                
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Device Code</label>
  
                  <div class="col-sm-4">
                    <input type="text" class="form-control" id="inputPassword3" placeholder="name">
                  </div>
                </div>
                
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
                    <label>
                      <input type="checkbox"> Remember me
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <!-- content 3-->

            <!-- /.box-body -->
            <div class="box-footer">
              <button type="submit" class="btn btn-default">삭제</button>
              <button type="submit" class="btn btn-default">수정</button>
              <button type="submit" class="btn btn-info pull-right">등록</button>
            </div>
            <!-- /.box-footer -->
          
        </div></form></div></section>
<!-- /.content -->