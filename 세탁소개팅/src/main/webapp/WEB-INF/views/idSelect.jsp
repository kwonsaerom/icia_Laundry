<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<style>
body{
	background-color: white;
}
  .mar{
 	margin-left: 20%;
 	margin-top: 15%;
 	width:450px;
 	text-align: center;
 } 
 .sub{
    border-radius:2px; padding:8px;
 font-size:14px;font-weight:700;color:#fff;text-align:cewnter;background:#a4a4a4;
 
 }
</style>

<body>
<script>
function noteReturn(){
    location.href="./messageSend";
 }
</script>
<div class="mar">
${selectresult}
 <input type="button"onclick="noteReturn()"class='sub' value="다시검색">
</div>
</body>
</html>