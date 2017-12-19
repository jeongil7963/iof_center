<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 

<!DOCTYPE html> 
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<title><tiles:getAsString name="title" ignore="true"/></title> 
	
    <!-- Bootstrap 3.3.6 -->
  	<link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />">
  	<!-- Font Awesome -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  	<!-- Ionicons -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  	<!-- Theme style -->
  	<link rel="stylesheet" href="<c:url value='/resources/dist/css/AdminLTE.min.css' />">
  	<!-- AdminLTE Skins. Choose a skin from the css/skins
       	 folder instead of downloading all of them to reduce the load. -->
  	<link rel="stylesheet" href="<c:url value='/resources/dist/css/skins/_all-skins.min.css' />">
	  <!-- iCheck -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/iCheck/flat/blue.css' />">
  	<!-- Morris chart -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/morris/morris.css' />">
  	<!-- jvectormap -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css' />">
  	<!-- Date Picker -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/datepicker/datepicker3.css' />">
  	<!-- Daterange picker -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/daterangepicker/daterangepicker.css' />">
  	<!-- bootstrap wysihtml5 - text editor -->
  	<link rel="stylesheet" href="<c:url value='/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css' />">

  	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  	<!--[if lt IE 9]>
  	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  	<![endif]-->
    
</head> 

<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">
	
		<header class="main-header" id="header">
			<tiles:insertAttribute name="header" /> 
		</header> 
		
		<aside class="main-sidebar" id="leftmenu">
			<tiles:insertAttribute name="leftmenu" /> 
		</aside>
		
		<div class="content-wrapper" id="siteContent">
			<tiles:insertAttribute name="body" /> 
		</div>
		
		
  		<footer class="main-footer" id="footer">
			<tiles:insertAttribute name="footer" /> 
  		</footer>
		
		<aside class="control-sidebar control-sidebar-dark" id="rightmenu">	
			<tiles:insertAttribute name="rightmenu" ignore="true"/> 
		</aside>
		
	  	<div class="control-sidebar-bg"></div>
  	
	</div>
	
	<!-- jQuery 2.2.3 -->
	<script src="<c:url value='/resources/plugins/jQuery/jquery-2.2.3.min.js' />"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
	  $.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.6 -->
	<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
	<!-- daterangepicker -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script src="<c:url value='/resources/plugins/daterangepicker/daterangepicker.js' />"></script>
	<!-- datepicker -->
	<script src="<c:url value='/resources/plugins/datepicker/bootstrap-datepicker.js' />"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="<c:url value='/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js' />"></script>
	<!-- Slimscroll -->
	<script src="<c:url value='/resources/plugins/slimScroll/jquery.slimscroll.min.js' />"></script>
	<!-- FastClick -->
	<script src="<c:url value='/resources/plugins/fastclick/fastclick.js' />"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value='/resources/dist/js/app.min.js' />"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<c:url value='/resources/dist/js/demo.js' />"></script>
</body> 
</html>
