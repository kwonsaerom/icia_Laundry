<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>BusinessUpdate</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
 @import url(//fonts.googleapis.com/earlyaccess/hanna.css);
        @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
.rounded{
           width:100px;
           height:100px;
        }
   .iText,.iText2,.iText3{
   height:34px;
   margin-bottom:5px;
   }
   a{
   color:darkcyan;
   }
 .loginForm .box{width:326px}
 .loginForm .box .iText{width:270px;margin:0 0;padding:10px 12px;border:1px solid #e1e1e1}
 .loginForm .box .fright{float:right;}
 .loginForm .box p{margin-top:1em}
 .loginForm .box p span,
.btn{height:15px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
 .insert{height:15px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;;text-align:center;background:#a4a4a4}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function oneCheckbox(a){
    var obj = document.getElementsByName("p_gender");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != a){
            obj[i].checked = false;
        }
        
    }
}


$(function() {
    $('.iText2').keyup(function() {
       $('font[name=pwCheck]').text('');
    });

    $('.iText3').keyup(function() {
       if ($('.iText2').val() != $('.iText3').val()) {
          $('span[name=pwCheck]').text('');
          $('span[name=pwCheck]').html("암호틀림");
       } else {
          $('span[name=pwCheck]').text('');
          $('span[name=pwCheck]').html("암호맞음");
       }
    });
 });
 
 function pwCheck(){
   document.update.action="./pwCheck3";
   document.update.submit();
 }
 
 function update(check){
     $('#email1').prop("disabled",false);
     $('#email2').prop("disabled",false);
     $('#email3').prop("disabled",false);
    var gender = $('#gender').val();
       console.log(gender);
         if(gender=="남"){
            $('#gender1').prop("checked",true);
         }else if(gender=="여"){
            $('#gender2').prop("checked",true);
         }
         
    if(check==1){
       alert("비밀번호가 확인되었습니다.");
      $('#updateSubmit').css("display","block");
    }
   if(check==2){
      alert("비밀번호가 틀렸습니다.");
   }if(check==3){
      alert("이메일 전송 완료");
      $('#updateSubmit').css("display","block");
       $('#numCheck').css("display","block");
       $('.emailInput').prop("disabled",false);
       $('#btn1').css("display","none");
       $('#num').prop("disabled",false);
   }
}
 
 function emailSend(){
    $('#numCheck').css("display","block");
    document.update.action="./joinVCsend6";
    document.update.submit();
 }
 
 function authnumCheck(){
    var num = $('#num').val();
     var r_num = $('#r_num').val();
    if(r_num==num){
       alert("이메일 인증이 완료되었습니다.");
       $('#updateSubmit2').prop("disabled",false);
    }else{
       alert("인증번호가 맞지 않습니다.");
    }
 }

 function businessUpdate(){
    document.update.action="./businessUpdate";
    document.update.submit();
 }
 </script>
<body onLoad="update(${check})">
<div class="wrapper row1">
  <header id="header" class="clear" style="width:92%;"> 
  <a href="./logout" style="float:right;">로그아웃</a><a  href="./businessUpdateMV"style="float:right;">개인정보수정　|　</a><br/>
    <div id="check" style="display:none">
    <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
    <img src="resources\bootstrap\images\new.png" style="width:50px; height:50px; float:right;"></a></div>
     <div id="check1" style="display:block">
     <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
    <img src="resources\bootstrap\images\message.png" style="width:50px; height:50px; float:right; "></a>
     </div>
    <div id="logo">
      <a href="./BusinessMain"><a href="./BusinessMain"> <img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:11%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li><a href="./HomeInfoMV3">홈페이지소개</a></li>
        <li><a href="#" class="drop">매장등록</a>
            <ul>
               <li><a href="./LaundryAddmv">일반세탁소</a></li>
               <li><a href="./CoinShopAddmv">코인세탁소</a></li>
            </ul>
        </li>
        <li><a href="./ReservationShop">예약현황</a>
        </li>
        <li><a class="drop" href="#">마이페이지</a>
             <ul>
               <li><a href="./businessMyPage">정보</a></li>
               <li><a href="./laundryMm">일반세탁소 관리</a></li>
               <li><a href="./coinShopMm">코인세탁소 관리</a></li>
               <li><a href="./pointMm?type=전체내역">포인트 관리</a></li>
            </ul>
        </li>
        <li><a class="drop" href="#">Q & A</a>
        <ul>
            <li><a href="./NoticeManage3">공지사항</a></li>
            <li><a href="request?menu=사업자">1:1 문의</a></li>
         </ul>
      </ul>
    </nav>
  </header>
</div>
<div class="wrapper" style="background:white; height: 620px; padding-top: 3%;">
 <div class="joinForm" style="margin-top:3%; margin-left:27%;">
          <form name="update" method="post">
<h1 style="font-family: 'Hanna'; margin-left:15%; font-size:30px;">개인정보수정 페이지</h1><br/>
       <span style="font-size:15px;">아이디 : </span><span style="font-size:15px;">${p_id}</span><br/>
      <input type="password" style="margin-top:5px;"name="pw" placeholder="현재 비밀번호" class="iText" value="${pw}"/>
         <a href="#" onclick="pwCheck()" class="btn">확인</a><br/>
         <input type="password" name="p_pw" placeholder="비밀번호" class="iText2" value="${p_pw}"/>
        <input type="password" name="p_pw2" placeholder="비밀번호 확인" class="iText3" value="${p_pw}"/><span id="duplicate" name="pwCheck" style=" margin-left:5px; color:red;">${pwCheck}</span><br/>
         <input type="text" value="${p.p_name}" name="p_name" class="iText"/><br/>
         <input type="checkbox" name="p_gender" id="gender1" value="남" onclick="oneCheckbox(this)" />남자 
            <input type="checkbox" name="p_gender" id="gender2" value="여" onclick="oneCheckbox(this)" />여자<br/>
            <input type="text" style="margin-top:5px;"name="email1"  id="email1" class="iText" value="${email1}" onfocus="this.value='';" class="emailInput"/> 
            @ <input type="text" name="email2" id="email2" class="iText" value="" /> 
            <select class="iText" name="email3" id="email3"onchange="email_change()">
               <option value="0">선택하세요</option>
               <option value="naver.com">naver.com</option>
               <option value="nate.com">nate.com</option>
               <option value="9">직접입력</option>
            </select>
             <a href="#" id="emailSendBtn" class="btn"  style="width:100px;" onclick="emailSend()">인증번호 발송</a><br/>
        <input type="text" name="authnum" placeholder="인증번호" id="num" class="iText" disabled/> <a href="#" class="btn" onclick="authnumCheck()">확인</a><br/>
            <select name="phone1" id="phone1" class="iText" style="width:90px;" >
               <option value="010">010</option>
               <option value="011">011</option>
               <option value="017">017</option>
               <option value="018">018</option>
               <option value="019">019</option>
               </select> 
               - <input type="text" class="iText" style="width:90px;" name="phone2" id="phone2" value="${phone2}"/> - <input type="text" style="width:90px; margin-bottom:5px;" class="iText" name="phone3" id="phone3" value="${phone3}"/><br/>
         <span style="font-size:15px;">사업자 등록번호 : ${b.b_lisence}</span>
         <div id="updateSubmit" style="display:none">
         <input type="hidden" id="r_num" value="${r_num}"/><br/><br/>
         　　　　　　　　　　　　　　　<a href="#" style="margin-top:5px;" class="btn" id="updateSubmit2" onclick="businessUpdate()" disabled>수정하기</a>
         </div>
               <input type="hidden" id="email7" name="email7" value="${email7}"/>
                  <input type="hidden" id="flag" name="flag" value="${flag}"/>
                  <input type="hidden" id="gender" name="gender" value="${p_gender}"/>
                  <input type="hidden" id="lisence" name="lisence" value="${b.b_lisence}"/>
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
$(document).ready(function email2(){
	   var flag = $('#flag').val();
	   var phone1="${phone1}";
	   console.log(flag);
	   console.log(phone1);
	   $("#phone1 option[value='" +phone1+ "']").attr("selected","selected");
	   $("#email3 option[value='" +email7+ "']").attr("selected","selected");
	      var email7 = $('#email7').val();
	      $('#email2').val(email7);
	      $('#email3').val(0);
	      console.log(email7);
	         if(flag==1){
	            $('#email2').attr("disabled",false);
	            $('#email2').val(email7);
	            $('#email3').val(email7);
	         }else if(flag==2){
	            $('#email2').val(email7);
	            $('#email3').val(9);
	             $('#email2').attr("disabled",false);
	         }else if(flag==5){
	            var email1 = ${email1};
	         $('#email1').val(email1);
	         $('#email2').val(email7);
	         $('#email3').val(0);
	         $('#email2').attr("disabled",true);
	      }
	});

function email_change(){
   var text = $("#email3 option:selected").val();
   var email = $('#email2').val();
         if(text == '0'){
             $('#email2').val("");
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


</script>
</html>