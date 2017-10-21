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
 @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
body{
background:white;
}
.rounded{
           width:100px;
           height:100px;
        }
       
 .loginForm{position:relative;width:435px}
 .loginForm .box{width:326px}
 .iText2{
 height:32px;
 }
 .iText{width:180px; height:32px; margin:0 5px;padding:10px 12px; border:1px solid #e1e1e1;border:1px solid #e1e1e1; margin-bottom:1%;}
 .loginForm .box .fright{float:right;}
 .loginForm .box p{margin-top:1em}
 .loginForm .box p span,
 .loginForm .box p input{height:18px;font-size:11px;color:#737373;line-height:18px;vertical-align:middle}
 .btn{height:10px;padding:6px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
 </style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script>
function correctNum(){
   var num = ${Num};
      var flag = $('#flag').val();
      if(flag==1||flag==2){
         if(num==$('#authnum').val()){
         $('#button').css("display","none");
         $('.btn').css("display","none");
         $('#msg').css("display","block");
         $('#findId').css("display","block");
         }
      }else if(flag==3||flag==4){
         if(num==$('#authnum2').val()){
         $('#button2').css("display","none");
         $('#msg2').css("display","block");
         $('#findPw').css("display","block");
         }
      }
}
      

function email_change(){
   var text = $("#email3 option:selected").val();
   var email = $('#email2').val();
         if(text == '0'){
             $('#email2').val("")
             $('#email2').attr("disabled",true);
         }else if(text == '9'){
            $('#email2').val("");
             $('#email2').attr("disabled",false);
              $("#email2").focus();
         }else{
            $('#email2').val(text);
             $('#email2').attr("disabled",true);
         }
      }

    
function email_change2(){
    var text = $("#email6 option:selected").val();
       if(text == '0'){
        $('#email5').val("");
        $('#email5').attr("disabled",true);
       }else if(text == '9'){
       $('#email5').val("");
        $('#email5').attr("disabled",false);
        $('#email5').focus();
       } else{
        $('#email5').val(text);
        $('#email5').attr("disabled",true);
       }
    } 
    
    
   function emailSend(){
      var text =$("#email3 option:selected").val();
      if(text == '0'){
          alert("도메인 주소를 입력해주세요.");
      }else{
         document.search.action="./joinVCsend3";
         document.search.submit();
      }
   }
   
   function emailSend2(){
      var text =$("#email6 option:selected").val();
      if(text=='0'){
         alert("도메인 주소를 입력해주세요.");
      }else{
         document.search.action="./joinVCsend4";
         document.search.submit();
      }
   }
   
   function find1(){
      document.search.action="./findId";
      document.search.submit();
   }
   
   function find2(){
      document.search.action="./findPw";
      document.search.submit();
   }
   
   
</script>
<script>
$(document).ready(function email2(){
   var flag = $('#flag').val();
   console.log(flag);
   if(flag==1||flag==2){
      var email7 = $('#email7').val();
      console.log(email7);
         if(flag==1){
            $('#email2').val(email7);
            $('#email3').val(email7);
         }else if(flag==2){
            $('#email2').val(email7);
            $('#email3').val(9);
             $('#email2').attr("disabled",false);
         }else if(flag==5){
         $('#email1').val("");
         $('#email2').val("");
         $('#email3').val(0);
         $('#email2').attr("disabled",true);
      }
   }else{
      var email7 = $('#email7').val();
      console.log(email7);
         if(flag==3){
            $('#email5').val(email7);
            $('#email6').val(email7);
         }else if(flag==4){
            $('#email5').val(email7);
            $('#email6').val(9);
            $('#email5').attr("disabled",false);
         }else if(flag==6){
         alert("여기 들어옴");
         $('#email4').val("");
         $('#email5').val("");
         $('#email6').val(0);
         $('#email5').attr("disabled",true);
      }
   }

});



</script>
 <body onload="searchFrm(${check})">
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
<div class="warpper" style="background:white; height:680px; width:100%; padding-top: 10px;">
 <div class="loginForm" style="margin-left:20%; margin-top:3%;">
<form name="search" method="post">
      <div id="idSearch" style="padding:5%; width: 620px; height: 210px; border:1px solid #e1e1e1;">
         <h1 style="font-family: 'Jeju Gothic'; font-size:25px;">아이디 찾기</h1>
               <input type="text" placeholder="이름" class="iText" name="p_name" id="name" value="${p_name}"/><br/>
                  <input type="text" name="email1" class="iText"  placeholder="이메일" id="email1" value="${email1}" onfocus="this.value='';" /> 
                  @ <input type="text" name="email2" class="iText"  value="도메인주소" id="email2" disabled/> 
                  <select name="email3" id="email3"  onchange="email_change()" class="iText2">
                     <option value="0" >선택하세요</option>
                     <option value="naver.com">naver.com</option>
                     <option value="nate.com">nate.com</option>
                     <option value="9">직접입력</option>
                  </select>
                  <a href="#" id="correct" class="btn" onclick="emailSend()">인증번호 전송</a><br/>
               <input type="text" id="authnum" name="authnum" class="iText"  value="${authnum}" placeholder="인증번호" disabled/>
               <a href="#" class="btn" id="button" onclick="correctNum()" disabled>확인</a><br/>
              　　<a href="#" class="btn" id="findId" style="display:none; width:120px; height: 20px;float:right;" onclick="find1()">아이디찾기</a>
         <br/>
         <h2 id="idView" style="font-family: 'Jeju Gothic'; font-size:17px;display:none; color:red;">　　아이디는 ${p_id}입니다.</h2>
      </div>
      <br/><br/><br/>
      <div id="pwSearch" style="padding:5%; width: 620px;height: 210px; border:1px solid #e1e1e1;">
         <h1 style="font-family: 'Jeju Gothic'; font-size:25px;">비밀번호 찾기</h1>
            <input type="text" placeholder="아이디" name="p_id" value="${pid}" class="iText" /><br/>
                  <input type="text" name="email4" placeholder="이메일" class="iText"  id="email4" value="${email4}" onfocus="this.value='';" /> 
                  @ <input type="text" name="email5" value="도메인주소" class="iText"  id="email5" disabled/>
                  <select name="email6" id="email6"  onchange="email_change2()" class="iText2">
                     <option value="0">선택하세요</option>
                     <option value="naver.com">naver.com</option>
                     <option value="nate.com">nate.com</option>
                     <option value="9">직접입력</option>
                  </select>
                  <a href="#" value="인증번호 전송" onclick="emailSend2()" class="btn">인증번호 전송</a>
                 <div style="float:left">
               <input type="text" id="authnum2" class="iText" name="authnum2" value="${authnum2}" placeholder="인증번호" disabled/>
               <a href="#" onclick="correctNum()" id="button2" class="btn" disabled>확인</a>
               </div>
               <a href="#" id="findPw" style="display:none; float:right; width:120px; height: 20px;" class="btn" onclick="find2()">비밀번호 찾기</a>
                  <input type="hidden" id="email7" name="email7" value="${email7}"/>
                  <input type="hidden" id="flag" name="flag" value="${flag}"/>
         <br/>
      </div>
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
<script>
function searchFrm(check){
   console.log(check);
   var obj = document.getElementById("idView");
   if(check==1){
      alert("아이디를 찾았습니다.");
      obj.style.display="block";
      $('#name').prop("disabled",true);
      $('#email1').prop("disabled",true);
      $('#email3').prop("disabled",true);
      $('#correct').css("display","none");
   }else if(check==2){
      alert("아이디를 찾지 못했습니다.");
   }else if(check==3){
      alert("입력하신 정보가 맞지 않습니다.");
   }else if(check==4){
      alert("비밀번호를 찾지 못했습니다.");
   }else if(check==5){
      alert("인증메일을 확인해주세요.");
   }else if(check==6){
      alert("이메일 전송 완료");
      var flag = $('#flag').val();
      if(flag==1||flag==2){
         $('#authnum').attr("disabled",false);
         $('#button').attr("disabled",false);
         $('#correct').attr("value","재전송");
      }else if(flag==3||flag==4){
         $('#authnum2').attr("disabled",false);
         $('#button2').attr("disabled",false);
         $('#correct2').attr("value","재전송");
      }
   }else if(check==7){
      alert("등록된 정보가 없습니다.");
   }
}


</script>
</html>