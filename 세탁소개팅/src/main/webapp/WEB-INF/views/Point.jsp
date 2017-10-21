
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<html>
<head>
<title>Vendor</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css"
   rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css"
   rel="stylesheet" type="text/css">
<style>
.text_border {
   text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px
      black;
   -moz-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px
      black;
   -webkit-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0
      -1.5px black;
}

#mileage {
   display: none;
}

#point {
   display: block;
}
</style>
</head>
<!-- JAVASCRIPTS -->
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script>
<script
   src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script>
<body id="top">
   <div class="wrapper row1" style="height: 300px;">
      <header id="header" class="clear" style="width: 92%;">
         <a href="./logout" style="float: right;">로그아웃</a><a
            href="./personUpdateMV" style="float: right;">개인정보수정 | </a><br />
         <div id="check" style="display: none">
            <a href="./message"
               onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
               <img src="resources\bootstrap\images\new.png"
               style="width: 50px; height: 50px; float: right;">
            </a>
         </div>
         <div id="check1" style="display: block">
            <a href="./message"
               onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
               <img src="resources\bootstrap\images\message.png"
               style="width: 50px; height: 50px; float: right;">
            </a>
         </div>
         <div id="logo">
            <a href="./PersonMainMV"> <img
               src="resources\bootstrap\images\img_t21_006.jpg"
               style="margin-left: 9%; margin-top: 2%; margin-bottom: 2%;" /></a>
         </div>
         <nav id="mainav">
            <ul class="clear">
               <li><a href="./HomeInfoMV2">홈페이지소개</a></li>
               <li><a href="#" class="drop">마이페이지</a>
                  <ul>
                     <li><a href="./personMyPage">나의 정보</a></li>
                     <li><a href="./personReservation">예약내역</a></li>
                     <li><a href='./myPoint?type=전체내역&value=포인트'>나의포인트</a></li>
                     <li><a href="./myActivity">나의 활동</a></li>
                  </ul></li>
               <li><a href="#" class="drop">매장찾기</a>
                  <ul>
                     <li><a href="./shopListSelect">일반세탁소</a></li>
                     <li><a href="./coinShopList">코인세탁소</a></li>
                  </ul></li>
               <li><a class="drop" href="#">Q & A</a>
                  <ul>
                     <li><a href="./requestCheck">1:1문의</a></li>
                     <li><a href="./coinShopMm">자주묻는 질문</a></li>
                  </ul></li>
               <li><a href="./NoticeManage2">공지사항</a>
            </ul>
         </nav>

      </header>
   </div>
   <div class="wrapper" style="background-color: white;">
      <div id="pointf">
         <div style="margin-bottom: 50px; height: 450px;">
            <div style="margin-left: 22%;">
               고객님은 현재 ${grade }등급입니다.<br /> 혜택 적립포인트 <span id="percent"></span>
               적립<br /> <a href='./PointAdd'>포인트 충전 |</a> <a href='./PointReturn'>
                  포인트 환불</a>
            </div>

            <table style="margin-top: 10px; width: 700px; margin-left: 22%;">
               <tr>
                  <td><div onclick="point()" style="cursor: pointer">사용가능
                        적립 포인트</div></td>
                  <td><div onclick="mileage()" style="cursor: pointer">사용가능
                        적립 마일리지</div></td>
               </tr>
               <tr>
                  <td>${pointtotal }</td>
                  <td>${mileagetotal }</td>
               </tr>
            </table>
            <form name="chk">
               <input type="hidden" id="value" name="value" /> <select
                  name="type" id="type" onChange="kindchange()"
                  style="width: 120px; height: 32px; margin-left: 22%; color: #63CAC6;">
                  <option value="전체내역">전체내역</option>
                  <option value="사용내역">사용내역</option>
                  <option value="적립내역">적립내역</option>
               </select>
            </form>
            <div>
               <div id="point"
                  style="width: 700px; margin-left: 22%; margin-top: 10px;">
                  ${pointList }</div>
               <div id="mileage"
                  style="width: 700px; margin-left: 22%; margin-top: 10px;">
                  ${mileageList }</div>
            </div>
         </div>
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
         <p class="fl_left">Copyright &copy; 2017 - All Rights Reserved</p>
      </div>
   </div>
</body>
<script>
   window.onload = function(){
      var grade = "${grade}";
      if(grade == "브론즈"){
         $("#percent").text("1%");
      }else if(grade == "실버"){
         $("#percent").text("1.5%");
      }else if(grade == "골드"){
         $("#percent").text("2%");
      }else if(grade == "플래티넘"){
         $("#percent").text("2.5%");
      }else if(grade == "다이아"){
         $("#percent").text("3%");
      }
    if("${count}"!=0){
      var con = document.getElementById("check");
     var con1 = document.getElementById("check1");
     con.style.display = 'block';
     con1.style.display = 'none';
       }else{
      var con = document.getElementById("check");
    var con1 = document.getElementById("check1");
        con.style.display = 'none';
     con1.style.display = 'block';
                }
           }
       </script>
<script>
window.onload=function(){
      var type="${type}";
      console.log("onload type="+type);
      $("#type option[value='"+type+"']").attr("selected","selected");
      
      var value="${value}";
      console.log("value="+value);
      if(value=="포인트" || value==""){
         var mileage = document.getElementById("mileage");
          var point = document.getElementById("point");
          mileage.style.display = "none";
          point.style.display = "block" ;
      }else if(value=="마일리지"){
         var mileage = document.getElementById("mileage");
          var point = document.getElementById("point");
          mileage.style.display = "block";
          point.style.display = "none" ;
      }
      
   }
   function point(){
      var mileage = document.getElementById("mileage");
      var point = document.getElementById("point");
      mileage.style.display = "none";
      point.style.display = "block" ;
   }
   function mileage(){
      var mileage = document.getElementById("mileage");
      var point = document.getElementById("point");
      mileage.style.display = "block";
      point.style.display = "none" ;
   }
</script>
<script>
   function kindchange(){
      var type = $("#type option:selected").val();
      //var month = $("#month option:selected").val();
      var mileage = document.getElementById("mileage");
      var point = document.getElementById("point");
      var value="";
      if(mileage.style.display == "block"){
         value = "마일리지";
      }else if(point.style.display == "block"){
         value = "포인트";
      }
      console.log("value="+value);
      $("#value").val(value);
      document.chk.action="./myPoint?type="+type;
      document.chk.submit();
   }
</script>


</html>