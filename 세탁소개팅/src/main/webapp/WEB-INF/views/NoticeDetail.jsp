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
<script src="resources/js/jquery-3.2.1.min.js"></script>
<style>
table{
   width: 500px;
   height: 320px;
   margin-left: 30%;
   text-align: center;
   margin-top: 5%;
}
.title{
   height: 10%;
}
.content{
   height: 5%;
}
.sub{
    border-radius:2px; padding:8px; 
 font-size:14px;font-weight:700;color:#fff;text-align:center;background:#a4a4a4
 }
 body{
 }
</style>
<body>
<div class="wrapper row1">
  <header id="header" class="clear" style="width:92%;"> 
  <a href="./logout" style="float:right;">로그아웃</a><br/>
    <div id="check" style="display:none">
    <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
    <img src="resources\bootstrap\images\new.png" style="width:50px; height:50px; float:right;"></a></div>
     <div id="check1" style="display:block">
     <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
   <img src="resources\bootstrap\images\message.png" style="width:60px; height:60px; float:right;"></a>
     </div>
    <div id="logo">
      <a href="./AdminMain"><img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:9%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li><a href="#" class="drop">매장관리</a>
            <ul>
               <li><a href="./laundryCheck">세탁소 승인 및 확인</a></li>
               <li><a href="./laundryManage">세탁소 관리</a></li>
            </ul>
        </li>
        <li><a href="./laundryReservAdN"" >일반세탁소예약관리</a>
        </li>
     <li><a class="drop" href="#">회원관리</a>
             <ul>
               <li><a href="./blackList">블랙리스트현황</a></li>
               <li><a href="./adminDeleteMV">회원탈퇴</a></li>
            </ul>
        </li>
        <li><a class="drop" href="#">포인트관리</a>
             <ul>
               <li><a href="./pointRequest?kind=전체&state=처리전">포인트 환전요청</a></li>
               <li><a href="./pointMmSelect?kind=대기">대기 중 포인트 확인</a></li>
            </ul>
        </li>
        <li class="active"><a class="drop" href="#">Q & A</a>
             <ul>
                 <li><a href="./NoticeManage4">공지사항 수정/등록</a></li>
               <li><a href="./requestCheck">1:1 문의 확인/수정</a></li>
            </ul>
        </li>
      </ul>
  </header>
</div>
<form name="aa" action="noticeUpdateMv">
   <input type="hidden" name="nt_code" value="${nt.nt_code }"/>
   <input type="hidden" name="nt_title" value="${nt.nt_title }"/>
   <input type="hidden" name="nt_content" value="${nt.nt_content }"/>
<table>
      <tr>
         <td class="title">제목　 <input type="text" name="nt_title" value="${nt.nt_title}" disabled /></td>
         <td>날짜　 ${today}<br></td>
      </tr>
   <tr>
      <td colspan="2" class="content">내용<br></td>
   </tr>　　　　　
   <tr>
      <td colspan="2"><textarea name="nt_content" disabled style="width: 500px; height: 200px;">${nt.nt_content }</textarea></td>
   </tr>
   <tr>
      <td colspan="2" ><a href="#" class="sub">수정</a>　　<a href="#" onclick="noticedelete()" class="sub">삭제</a></td>
   </tr>
   
</table>
</form>
</body>

<script>
function noticedelete(){
   document.aa.action="./noticeDelete";
   document.aa.submit();
}
</script>
</html>