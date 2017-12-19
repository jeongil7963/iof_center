<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="<c:url value='/resources/ckeditor/ckeditor.js' />"></script>    
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>재배 방법</h1>
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
					<h3 class="box-title">Farming form</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<div class="box-body">
				<form class="form-horizontal" id="form1" name="form1" enctype="multipart/form-data" >
					
					<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">농작물  </label>
              		<div class="col-sm-4">
              		<select class="form-control" name="crop_idx" id="crop_idx">
                        <option value="false">선택해주세요.</option>
                        <c:if test="${!empty crop}">
						<c:forEach var="crop" items="${crop}" >
					  	<option value="${crop.crop_idx}">${crop.crop_name}</option>
						</c:forEach>
						</c:if>
                     </select>
                     </div>
              		</div>
               <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">제목</label>
    
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="frm_title" name="frm_title" placeholder="">
                    </div>
              </div>
              <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">내용</label>
                   <div class="col-sm-8" id="cententDiv"  >
                    <textarea cols="80" id="frm_contents" name="frm_contents" rows="10"></textarea>
                   </div>
              </div>
              <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">첨부파일</label>
                    <div id="fileDiv" class="col-sm-10">
                     <div  class="col-sm-10">
            		<p><input type="file" id="file" name="file"></p>
            		<a href="this"></a>
            		 </div>
            		<div class="col-sm-2" >
               		 </div>
        		</div>
              </div>
            <div class="col-sm-12" style="text-align: center;">
            <a class="btn btn-info" id="addFile" style="text-align: center;">파일 추가</a>
            <a class="btn btn-default" id="btnList" href="<c:url value='list'/>"> 목록</a>
            <input type="hidden" value='${random}' name="random"/>
			<input type="button" class="btn btn-info" id="btnRegist" value="등록"  >
            </div>
            </form>
            </div>
	<script>
	    $(document).ready(function(){
        CKEDITOR.replace( 'frm_contents', {//해당 이름으로 된 textarea에 에디터를 적용
            width:'100%',
            height:'400px',
            
            filebrowserImageUploadUrl: '<c:url value='/admin/uploadfiles/ckeditorImageUpload?random=${random}'/>'
        });      
	   });
    </script>
			</div>
		</div>
	</div>
</section>

    <%@ include file="/WEB-INF/views/include/include-body-jspf.jsp" %>
    <script type="text/javascript">
        
     
        $(document).ready(function(){

        	$("#addFile").on("click", function(e){ //파일 추가 버튼
                e.preventDefault();
                fn_addFile();
            });
        });
         
        function fn_addFile(){
            var str = "<p><input type='file' name='file'></p>";
            $("#fileDiv").append(str);
        }
         
    </script>
    <script>
    $(document).ready(function() {	
		
		$('#btnRegist').on("click", function() {
			if ($('#crop_idx').val() == "false") {
				alert("농작물을 입력하지 않았습니다.")
				$('#crop_idx').focus();
				return false;
			}
			document.form1.action = "/admin/farming/regist";
			document.form1.method = "POST";
			document.form1.submit();
			});
		});
	
	</script>
