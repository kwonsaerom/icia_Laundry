<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script>
function idSelect(){
   document.nameForm.action = './idSelect';
   document.nameForm.submit();
}
</script>
<style>
body{
   background-color: white;
}
.sub{
    border-radius:2px; padding:8px;
 font-size:14px;font-weight:700;color:#fff;text-align:cewnter;background:#a4a4a4;
 
 }
 #send{
 margin-left:36%;
 }
  .mar{
    margin-left: 20%;
    margin-top: 15%;
    width:450px;
 } 
 #choice{
 margin-top: 7px;
 }
</style>
</head>
<body>
<div class="mar">
       
         <form name="nameForm" action="realmessageSend" method="get">
         <table>
            <tr>
               <td><select name="choice" id="choice" >
               <option value="all">-선택하세요-
               <option value="laundry">일반세탁소</option>
               <option value="coin">코인세탁소</option>
               <option value="admin">관리자</option>
               <option value="person">개인</option>
               </select>
               </td>
               <td>매장명<input type="text"  name="n_takeid" id="n_takeid"  value="${n_takeid}">
                   <input type="button" id="select" class='sub' value="검색" class='btn btn-primary'onClick="idSelect()">
                </td>
            </tr>
            <tr>
               <td colspan="2">
                  제목
                     <input type="text"  name="n_title" style="text-align:center; width:370px;">
            </td>
            </tr>
            <tr>
               <td colspan="2"> 쪽지내용<br/>
                        　<textarea rows="5" cols="46" name="n_content"></textarea>
                     <br/>
                     <input type="submit" id="send"class='sub' value="쪽지보내기">
               </td>
              </tr>
              </table>
      </form>
      </div>
</body>

</html>