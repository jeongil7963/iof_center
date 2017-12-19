<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

%>
<html> 
<body> 

<script type="text/javascript"> 
var message = '${msg}'; 
var returnUrl = "${url}"; 

alert(message); 
document.location.href = '${redirect}';
</script></body></html> 