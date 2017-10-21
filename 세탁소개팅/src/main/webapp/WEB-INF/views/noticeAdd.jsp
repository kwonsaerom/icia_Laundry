<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<body>
<input type="text" value="공지사항" disabled/>
<form action="noticeAdd">
분류 <select name="nt_categori" id="shopchoice" onChange="selectChange()" >
         <option value="0">공용</option>
         <option value="1">고객</option>
         <option value="2">사업자</option>
         </select><br/>
제목 <input type="text" name="nt_title"/><br/>
내용 <textarea rows="10" cols="40" name="nt_content"></textarea>
<input type="submit" value="공지등록"/>
<input type="button" onclick="cancel()"value="취소"/>
</form>
</body>
<script>
	function cancel(){
 		  history.back();
	}
</script>
</html>