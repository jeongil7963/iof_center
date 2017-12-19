<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="content-header">
	<h1>재배 방법</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		<li class="active">Here</li>
	</ol>
</section>
<section class="content">
        <div class="box box-info">
        <div class="box-body">
          <form class="form-horizontal">
            
           		 <div style="height:90px">
                    <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">농작물  </label>
                            <div class="col-sm-4"> 
                            		<p> ${farm.crop_name}</p>
                            </div>
                          </div>
                  </div>  
                  <div style="height:90px">      
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">제목</label>
                    <div class="col-sm-4">
                     		 <p> ${farm.frm_title}</p>
                    </div>
                  </div>
                   </div>
                  <div style="height:90px"> 
                  <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">내용</label>
                    <div class="col-sm-8" >
                     <p> ${farm.frm_contents}</p>
                    </div>
                  </div>
                  </div> 
                  <div class="col-sm-7">
                  </div> 
                  
            <!-- /.box-footer -->
          </form>
          
          <c:forEach items="${uploadedfile}" var="uploadfiles">
          
          <div class="col-sm-12" style="text-align: left;">
           	첨부파일 <a href="<c:url value='./download?frm_f_idx=${uploadfiles.frm_f_idx}'/>" > ${uploadfiles.frm_f_filename_org}</a>
          </div>
           </c:forEach>
						<!-- <a href="/bbs2/download.ktds?fname=${article.fname}">${article.fname}</a> -->
         <div class="col-sm-12" style="text-align: center;">
					<div class="box-footer">
						<button class="btn btn-default" id="btnList" onclick=""> 목록</button>
						<a class="btn btn-info" href="<c:url value='modify?frm_idx=${farm.frm_idx}'/>" >수정</a>
					</div>
		</div>			
		 </div>
    <!-- /.content -->
        </div>
        </section>
        <script>
			$(document).ready(function() {
				$('#btnList').on("click", function(evt) {
					evt.preventDefault();
					self.location = "list";
				});
				
			});
		</script>