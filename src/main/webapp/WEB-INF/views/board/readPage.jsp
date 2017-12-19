<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>

    <!-- Main content -->
    <section class="content">
      <div class="row">
      <!-- left column -->
      <div class="col-md-12">
        <!-- general form elements -->
        <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title">READ BOARD</h3>
        </div><!-- /.box-header -->
<%-- 
 <form role="form" action="modifyPage" method="post">
    
    <input type='hidden' name='bno' value ="${boardVO.bno}">
    <input type='hidden' name='page' value ="${cri.page}">
    <input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
    
 </form>   
     --%>
  <div class="box-body">
    <div class="form-group">
      <label for="exampleInputEmail1">Title</label>
      <input type="text" name='title' class="form-control" 
         value="${board.title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Content</label>
      <textarea class="form-control"  name="content" rows="3" 
      readonly="readonly">${board.content}</textarea>
    </div>
    <div class="form-group">
      <label for="exampleInputEmail1" >Writer</label>
      <input type="text" name="writer" class="form-control" 
        value="${board.writer}" readonly="readonly">
    </div>
  </div><!-- /.box-body -->
  
  <div class="row">
		<div class="col-md-12">

			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for="exampleInputEmail1">Writer</label> 
					<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter"> 
					<label for="exampleInputEmail1">Reply Text</label> 
					<input class="form-control" type="text"	placeholder="REPLY TEXT" id="newReplyText">

				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="button" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
				</div>
			</div>

		
			<!-- The time line -->
			<ul class="timeline">
			  <!-- timeline time label -->
				<li class="time-label" id="repliesDiv">
				  	<span class="bg-green">
				    Replies List <small id='replycntSmall'> [ 000 ] </small>
			    	</span>
			  	</li>
			</ul>
			<ul id="rlist" >
					<c:forEach items="${board.rlist }" var="reply">
						<li>
							${reply.rcontent }
							<a href="javascript:removeReply(${reply.rseq},${reply.bno })">x</a>
						</li>
					</c:forEach>
			</ul>
		   
			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->

  <div class="box-footer">
	
	<c:if test="${ loginUser.uname == board.writer }">
	<button type="submit" id="modify" class="btn btn-warning">Modify</button>
	<button type="submit" id="remove" class="btn btn-danger">REMOVE</button>
	</c:if>
	
    <button type="submit" id="list" class="btn btn-primary">GO LIST </button>
  </div>


<script>

function removeReply(seq,bno){
	
	$.ajax({
		url : "replyRemove",
		type : "post",
		data : {
					rseq : seq,
					bno : bno
				},
		dataType : "json",
		success : function(ary) {
			//alert(ary.length);
			$("#rlist").empty();
			var txt = "";
			$.each(ary,function(idx, obj){
				
				txt += "<li class='time-label'>" + obj.rcontent;
				txt += "<a href='javascript:removeReply(" + obj.rseq + ","+ obj.bno + ")'>x</a>";
				txt += "</li>";
				
			});
			
			$("#rlist").append(txt);
		}
	});
}

$(document).ready(function(){
	$("#list").on('click',function(){
		location.href = "listPage";
	});
	
	$("#modify").on('click',function(){
		//location.href = "modifyPage?bno="+${board.bno}+"&title="+${board.title}+"&content="+${board.content};
		location.href = "modifyPage?bno="+${board.bno};
	});
	
	$("#remove").on('click',function(){
		location.href = "removePage?bno="+${board.bno};
	});
	
	$("#replyAddBtn").click(function(){
		$.ajax({
			url : "replyInsert",
			type : "post",
			data : {
						rwriter : $("#newReplyWriter").val(),
						rcontent : $("#newReplyText").val(),
						bno : ${board.bno}
					},
			dataType : "json",
			success : function(ary) {
				//alert(ary.length);
				$("#rlist").empty();
				var txt = "";
				$.each(ary,function(idx, obj){
					
					txt += "<li class='time-label'>" + obj.rcontent;
					txt += "<a href='javascript:removeReply(" + obj.rseq + ","+ obj.bno + ")'>x</a>";
					txt += "</li>";
					
				});
				
				$("#rlist").append(txt);
			}
		});
	});
});



/*  
 * 
 $(document).on("click", ".btn-warning", function(){
		var formObj = $("form[role='form']");
		formObj.attr("action", "/board/modifyPage.do");
		formObj.attr("method", "get");		
		formObj.submit();
	});

	$(document).on("click", ".btn-danger", function(){
		var formObj = $("form[role='form']");
		if(confirm("게시물을 삭제하시겠습니까?")){	
			formObj.attr("action", "/board/removePage.do");
			formObj.submit();
		}
	});
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	if(${board.writer==sessionScope.loginUser.uname}
	  || ${sessionScope.loginUser.uname=='관리자'}){
		$(".box-footer").html(
				'<button type="submit" class="btn btn-warning">Modify</button> '
			   +'<button type="submit" class="btn btn-danger">REMOVE</button> '
			   +'<button type="submit" class="btn btn-primary">GO LIST </button>');
	}
});

$(document).on("click", ".btn-warning", function(){
	var formObj = $("form[role='form']");
	formObj.attr("action", "/board/modifyPage.do");
	formObj.attr("method", "get");		
	formObj.submit();
});

$(document).on("click", ".btn-danger", function(){
	var formObj = $("form[role='form']");
	if(confirm("게시물을 삭제하시겠습니까?")){	
		formObj.attr("action", "/board/removePage.do");
		formObj.submit();
	}
});

$(document).on("click", ".btn-primary", function(){
	var formObj = $("form[role='form']");
	formObj.attr("method", "get");
	formObj.attr("action", "/board/listPage.do");
	formObj.submit();
});
*/
</script>


  
  
        </div><!-- /.box -->
      </div><!--/.col (left) -->
 
      </div>   <!-- /.row -->
    </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    
<%@include file="../include/footer.jsp" %>
  