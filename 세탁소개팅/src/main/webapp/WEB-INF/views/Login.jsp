  <!DOCTYPE html>
<!--
Template Name: Vendor
Author: <a href="http://www.os-templates.com/">OS Templates</a>
Author URI: http://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: http://www.os-templates.com/template-terms
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link rel="icon" href="resources/bootstrap/images/favicon.ico" type="image/x-icon" />
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<style>
.rounded{
           width:100px;
           height:100px;
        }
 .loginForm{position:relative;width:435px}
 .loginForm .box{width:326px}
 .loginForm .box .iText{width:300px;margin:0 5px;padding:10px 12px;border:1px solid #e1e1e1}
 .loginForm .box .fright{float:right;}
 .loginForm .box p{margin-top:1em}
 .loginForm .box p span,
 .loginForm .box p input{height:18px;font-size:11px;color:#737373;line-height:18px;vertical-align:middle}
 .loginForm .loginBtn{display:block;position:absolute;top:0;right:0;width:80px;height:60px;padding:10px;border-radius:5px;
 font-size:14px;font-weight:700;color:#fff;margin-top:5px;line-height:60px;text-align:center;text-shadow:1px 1px 1px #004773;background:#333}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script>
   function login(){
      document.loginFrm.action="./login";
      document.loginFrm.submit();
   }
   
   function loginCheck(check){
      if(check==1)
         alert("아이디나 비밀번호가 틀렸습니다.");
      if(check==2)
         alert("입력된 정보의 회원이 없습니다.");
      if(check==3)
         alert("탈퇴한 회원입니다.");
      var msg="${msg}";
      if(msg!=""){
         alert(msg);
         }
      }
           
   
</script>
<body onLoad="loginCheck(${check})">
<div class="wrapper row1">
  <header id="header" class="clear" style="width:92%;"> 
  <a href="./JoinMemberMV" style="float:right;">Join</a><a  href="./LoginMV"style="float:right;">Login　|　</a>
    <div id="logo">
      <a href="./"><img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:11%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li><a href="./HomeInfoMV">홈페이지소개</a></li>
        <li><a href="./LaundryMethodMv">세탁방법안내</a></li>
        <li><a href="./shoplogin">매장찾기</a></li>
        <li><a href="./NoticeManage">공지사항</a>
      </ul>
    </nav>
  </header>
</div>
<div class="wrapper" style="background:white;height: 650px; padding-top: 10px;">
 <div class="loginForm" style="margin-left:35%; margin-top:14%;">
     <form name="loginFrm" method="post">
           <div class="box">
            <input type="text" class="iText" placeholder="아이디" name="p_id" style="margin-bottom: 10px;">
            <br>
            <input type="password" name="p_pw" class="iText" placeholder="비밀번호">
            <p>
              <span class="fright"><a href="./JoinMemberMV">회원가입　　　</a></span>
            </p>
            <p>
              <span class="fright"><a href="./SearchMemberMV">아이디 | 비밀번호 찾기　　　</a></span>
            </p>
          </div>
          <a href="#" onclick="login()" class="loginBtn">로그인</a>
        </form>
      </div>
    </div>
    
<div class="wrapper row4" style="border-top-width: 0px;">
  <footer id="footer" class="clear" style="margin-left: 0px; margin-right: 0px; height: 160px; padding-bottom:10px;">
    <div class="one_third" style="width:20%; margin-right: 3%; margin-left:6%;">
    <h6 class="title">STAFF</h6>
     <ul class="nospace" >
        <li class="push10"><b>팀장 :</b> 김광영</li>
        <li class="push10"><b>팀원 :</b> 주민기, 박상욱, 허예림, 권새롬</li>
        <li class="push10"><b>팀이름 :</b> <b style="color:orange">C</b>lean <b style="color:white">U</b>p</li>
      </ul>
      </div>
    <div class="one_third" style="width:20%; margin-right: 3%; margin-left:5%;">
    <h6 class="title">WORKING</h6>
     <ul class="nospace" >
        <li class="push10"><span class="icon-time"></span> Mon. - Fri. 9:00am - 7:00pm</li>
        <li class="push10"><span class="icon-phone"></span> +08 (123) 456 7890</li>
        <li><span class="icon-envelope-alt"></span> tofha56@gmail.com</li>
      </ul>
      </div>
        <div class="one_third" style="width:30%;margin-right: 2%;margin-left:5%;">
     <h6 class="title">COMPANY INFO</h6>
      <address class="push30">
      <ul class="nospace">
      <li class="push10"><b>Company Name :</b> 세탁소개팅</li>
      <li class="push10"><b>Street Name :</b> 인천 남구 학익동 매소홀로  태승빌딩 5층</li>
    <!--   <li class="push10">매소홀로  태승빌딩 5층</li> -->
      <li class="push10"><b>Postcode/Zip :</b> 22123</li>
      </ul>
      </address>
    </div>
  </footer>
</div>
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <p class="fl_left">　　Copyright &copy; 2017 - All Rights Reserved </p>
  </div>
</div>
</body>
</html>