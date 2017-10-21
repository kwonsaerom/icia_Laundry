<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
body{
	background-color: white;
}
td{
font-size:14px;
color:gray;
}
.right{
text-align: right;
}
.sub{
    border-radius:2px; padding:8px;
 font-size:14px;font-weight:700;color:#fff;text-align:cewnter;background:#a4a4a4;
 }
 .mar{
 margin-left: 35%;
 }
 #mainav{
 width:650px;
 margin-top: 10%;
 }
</style>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<body>

 <nav id="mainav">
<form  name ="aa" action="messagedelete"   method="post">
<li><a href='./message'>받은 쪽지함</a></li>
<li><a href='./sendmessage'>보낸 쪽지함</a></li>

${result}
</form>
</nav>
</body>
<script>
$(function(){
	$('#checkAll').click(function(){
		if($('#checkAll').prop('checked')){
		$('input[name=chkbox]:checkbox').each(function(){
			$(this).prop('checked',true);
		});
		}else{
			$('input[name=chkbox]:checkbox').each(function(){
				$(this).prop('checked',false);
			});
		}
	})
});

function messageSend(){
	document.aa.action="./messageSend";
	document.aa.submit();
}
function messagelist(){
	document.aa.action="./message";
	document.aa.submit();
}

function messagesave(){
	document.aa.action="./messagesave";
	document.aa.submit();
}
</script>
</html>