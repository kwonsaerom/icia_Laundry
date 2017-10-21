
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
<title>Vendor</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
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
 .loginForm .box .iText{width:300px;margin:0 0;padding:10px 12px;border:1px solid #e1e1e1}
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


 <script type="text/javascript">
   function insertPopup(){
//window.name = "부모창 이름";
window.name = "JoinMember.jsp";
//window.open("자식창 이름", "불러올 자식 창의 닉네임", "팝업창 옵션");
window.open("./Check","insert",
"width = 560, height = 210, resizabla = no, scrollbars = no, status = no");
   }
</script>
<script>

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
</script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>

function sample6_execDaumPostcode() {
   new daum.Postcode(
   {
   oncomplete : function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var fullAddr = ''; // 최종 주소 변수
      var extraAddr = ''; // 조합형 주소 변수
      // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
   fullAddr = data.roadAddress;
      } else { // 사용자가 지번 주소를 선택했을 경우(J)
   fullAddr = data.jibunAddress;
      }
      // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
      if (data.userSelectedType === 'R') {
   //법정동명이 있을 경우 추가한다.
   if (data.bname !== '') {
      extraAddr += data.bname;
   }
   // 건물명이 있을 경우 추가한다.
   if (data.buildingName !== '') {
      extraAddr += (extraAddr !== '' ? ', '
   + data.buildingName : data.buildingName);
   }
   // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
   fullAddr += (extraAddr !== '' ? ' (' + extraAddr
   + ')' : '');
      }
      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
      document.getElementById('sample6_address').value = fullAddr;

      // 커서를 상세주소 필드로 이동한다.
      document.getElementById('sample6_address2').focus();
   }
   }).open();
      }



function memberInsert(){
   document.join.action="./memberInsert";
   document.join.submit();
}

function businessInsert(){
   document.join.action="./businessInsert";
   document.join.submit();
}

function idCheck2(){
   document.join.action="./idCheck2";
   document.join.submit();
}

function idCheck(){
   document.join.action="./idCheck";
   document.join.submit();
}

function emailSend(){
      document.join.action="./joinVCsend";
      document.join.submit();
      insertPopup();
}


function emailSend2(){
      document.join.action="./joinVCsend2";
      document.join.submit();
      insertPopup();
   }

function selectList(selectNum){
   var obj1 = document.getElementById("member");
   var obj2 = document.getElementById("business");
   
   if(selectNum=='0'){
obj1.style.display="block";
obj2.style.display="none";
   }else{
obj1.style.display="none";
obj2.style.display="block";
   }
}

function formCheck(save){
   if(save==1){
      selectList('0');
   }else if(save==2){
      selectList('1');
   }
   
}
 </script>
 
<body onLoad="formCheck(${save})">
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
<div class="wrapper" style="background:white; height: 620px; padding-top: 3%;">
 <div class="joinForm" style="margin-top:3%; margin-left:27%;">
        <form name="join" method="post">
      <a href="#" onclick="selectList('0')">개인회원가입 |</a> 
      <a href="#" onclick="selectList('1')"> 사업자회원가입</a> 
      <div id="member" style="display:block;margin-top: 15px;">
            <input type="text" placeholder="아이디" class="iText" name="p_id" value="${m_id}" />
            <a href="#" onclick="idCheck()" class="btn"/>중복확인</a>
            <span id="idCheck" name="idCheck" style="color:red;">　${idCheck}</span><br/>
            <input type="password" placeholder="비밀번호" class="iText2" name="p_pw" value="${p_pw}" />
            <input type="password" placeholder="비밀번호 확인" class="iText3" name="p_pw2" value="${p_pw}"/>
            <span id="duplicate" name="pwCheck" style="color:red;">${pwCheck}</span><br/>
            <input type="text" placeholder="이름" class="iText" name="p_name" value="${p_name}" /><br/>
            <input type="checkbox" id="gender1" name="p_gender" value="남" onclick="oneCheckbox(this)" style="margin-top:8px; margin-bottom:10px;"/>남자 
            <input type="checkbox" id="gender2" name="p_gender" value="여" onclick="oneCheckbox(this)" style="margin-top:8px; margin-bottom:10px;"/>여자<br/>
            <select name="phone1" id="phone1" style="width:100px; height:32px;">
            <option value="010">010</option>
            <option value="011">011</option>
            <option value="017">017</option>
            <option value="018">018</option>
            <option value="019">019</option>
            </select>
             - <input type="text"  placeholder="앞번호" name="phone2" class="iText" value="${phone2}"/> - <input type="text"  placeholder="뒷번호" name="phone3" class="iText" value="${phone3}"/><br/>
            <input type="text" name="email1" id="email1" class="iText" placeholder="이메일" value="${email1}" onfocus="this.value='';" /> 
            @ <input type="text" name="email2" class="iText" id="email2" disabled /> 
            <select name="email3" id="email3" onchange="email_change()"  class="iText" style="width:100px; height:32px;">
               <option value="0">선택하세요</option>
               <option value="naver.com">naver.com</option>
               <option value="nate.com">nate.com</option>
               <option value="9">직접입력</option>
            </select>
            <a href="#"  id="mailCheck" onclick="emailSend()" class="btn" disabled>인증메일 전송하기</a>
            <span class="checkMail" style="color:red; width:20px;"></span>
            <br/>
            <input type="text" name="post" id="sample6_postcode" placeholder="우편번호" class="iText"/> 
            <a href="#" onclick="sample6_execDaumPostcode()" class="btn" id="postSearch" disabled/>우편번호 찾기</a><br/>
            <input type="text" name="addr1" placeholder="주소" class="iText" id="sample6_address" /> 
            <input type="text" name="addr2" class="iText" id="sample6_address2" placeholder="상세주소" /><br/><br/><br/><br/><br/>
            　　　　　　　　　　　　　　　　　<a href="#" class="insert" onclick="memberInsert()" disabled>회원가입</a>
      </div>
      
      <div id="business" style="display:none;margin-top: 15px;">
            <td><input type="text" placeholder="아이디" name="b_id" value="${b_id}" class="iText"/> 
            <a href="#" onclick="idCheck2()" class="btn"/>중복확인</a>
            <span id="idCheck" name="idCheck" style="color:red;">　${idCheck2}</span> <br/>
            <input type="password" placeholder="비밀번호" class="iText2" name="p_pw3" value="${p_pw}" />
            <td><input type="password" placeholder="비밀번호 확인" class="iText3" name="p_pw4" value="${p_pw}" />
            <span id="duplicate" name="pwCheck" style="color:red;">${pwCheck}</span><br/>
            <input type="text" placeholder="이름" class="iText" name="p_name2" value="${p_name}" /><br/>
            <input type="checkbox"  id="gender3" name="p_gender2" value="남"onclick="oneCheckbox(this)" style="margin-top:8px; margin-bottom:10px;"/>남자 
            <input type="checkbox"  id="gender4" name="p_gender2" value="여" onclick="oneCheckbox(this)" style="margin-top:8px; margin-bottom:10px;"/>여자<br/>
            <select name="phone4" id="phone4" style="width:100px; height:32px;">
               <option value="010">010</option>
               <option value="011">011</option>
               <option value="017">017</option>
               <option value="018">018</option>
               <option value="019">019</option>
               </select> 
               - <input type="text" name="phone5"  placeholder="앞번호" class="iText" value="${phone5}"/> - <input type="text" placeholder="뒷번호"  class="iText" name="phone6" value="${phone6}"/><br/>
            <input type="text" name="email4" id="email4"  class="iText" placeholder="이메일" value="${email4}" onfocus="this.value='';" /> 
            @ <input type="text" name="email5" id="email5" class="iText" value="" disabled /> 
            <select name="email6" id="email6" onchange="email_change2()" style="width:100px; height:32px;">
               <option value="0">선택하세요</option>
               <option value="naver.com">naver.com</option>
               <option value="nate.com"/>nate.com</option>
               <option value="9">직접입력</option>
            </select>
            <a href="#" onclick="insertPopup()">
            <a href="#"  id="mailCheck2" onclick="emailSend2()" class="btn" disabled/>인증메일 전송하기</a><span class="checkMail" style="color:red; width:20px;"></span>
            <br/></a>
            <td><input type="text" placeholder="사업자 등록번호"  class="iText" name="b_lisence"/><br/><br/><br/><br/>
            　　　　　　　　　　　　　　　　　<a href="#" class="insert" onclick="businessInsert()" disabled>회원가입</a>
   </div>
               <input type="hidden" id="email7" name="email7" value="${email7}"/>
                  <input type="hidden" id="flag" name="flag" value="${flag}"/>
                  <input type="hidden" id="check" name="check" value="${check}"/>
                  <input type="hidden" id="gender" name="gender" value="${p_gender}"/>
                   
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
   

function show(){
   $('#mailCheck').css("display","none");
   $('#mailCheck2').css("display","none");
   $('#postSearch').prop("disabled",false);
   $('.checkMail').text("인증완료");
   $('#button').css("display","none");
   $('#button2').css("display","none");
   $('.insert').attr("disabled",false);
}

$(document).ready(function email2(){
   var flag = $('#flag').val();
   var check = $('#check').val();
   console.log(flag);
   console.log(check);
   if(flag==1||flag==2||flag==5){
      if(check==3){
         $('#button').attr("disabled",true);
         $('#mailCheck').attr("disabled",false);
         $('#postSearch').attr("disabled",false);
      }
      var gender = $('#gender').val();
      if(gender=="남"){
         $('#gender1').prop("checked",true);
      }else if(gender=="여"){
         $('#gender2').prop("checked",true);
      }
      var phone1="${phone1}";
      console.log(phone1);
      $("#phone1 option[value='" +phone1+ "']").attr("selected","selected");
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
      if(check==3){
         $('#button2').attr("disabled",true);
         $('#mailCheck2').attr("disabled",false);
      }
      var gender = $('#gender').val();
      if(gender=="남"){
         $('#gender3').prop("checked",true);
      }else if(gender=="여"){
         $('#gender4').prop("checked",true);
      }
      var phone4="${phone4}";
      console.log(phone1);
      $("#phone4 option[value='" +phone4+ "']").attr("selected","selected");
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
         $('#email4').val("");
         $('#email5').val("");
         $('#email6').val(0);
         $('#email5').attr("disabled",true);
      }
   }
});
function oneCheckbox(a){
    var obj = document.getElementsByName("p_gender");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != a){
            obj[i].checked = false;
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
</script>
</html>
