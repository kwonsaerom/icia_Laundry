<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="icon" href="resources/bootstrap/images/favicon.ico" type="image/x-icon" />
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<style>
   table{
      margin-left: 30%;
      width: 420px;
      height: 300px;
   }
   select{
      height: 30px;
   }
   b{
      margin-left: 30%;
      margin-top: 10%;
   }
.sub{
    border-radius:2px; padding:8px;
 font-size:14px;font-weight:700;color:#fff;text-align:center;background:#a4a4a4
 }
</style>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<body>
<b>공지사항</b>
<form name="aa" action="noticeUpdate">
<input type="hidden" name="nt_code" value="${nt.nt_code }"/>
<table>
   <tr>
      <td>분류</td>
      <td><select name="nt_categori" id="shopchoice" onChange="selectChange()" >
         <option value="0">공용</option>
            <option value="1">고객</option>
            <option value="2">사업자</option></select>
        </td>
   </tr>
   <tr>
      <td>제목</td>
      <td><input type="text" name="nt_title" value="${nt.nt_title}"/></td>
   </tr>
   <tr>
      <td>내용</td>
      <td><textarea rows="10" cols="40" name="nt_content">${nt.nt_content }</textarea></td>
   </tr>
   <tr>
      <td colspan="2" style="text-align: center;"><a href="#" class="sub">공지등록</a>　　<a href="#" onclick="cancel()" class="sub">취소</a></td>
   </tr>
</table>
</form>
</body>
<script>
   function cancel(){
         history.back();
   }
</script>
</html>