<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>BusinessMain</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
.text_border {
   text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -moz-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -webkit-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
}
 .rounded{
           width:100px;
           height:100px;
        }
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script>
jQuery(function ($) {
    $.supersized({
      slide_interval: 5000,
      transition: 1,
      transition_speed: 100,
        slides: [{
            image: 'resources/bootstrap/images/demo/slider/캡처.PNG',
            
        }, {
            image: 'resources/bootstrap/images/demo/slider/캡처1.PNG',
            image: 'resources/bootstrap/images/demo/slider/캡처2.PNG',
        }]
    });
});

</script>
<body>
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
<div class="wrapper">
  <div id="slider"> 
    <div id="slidewrap">
      <div class="heading"><span id="slidecaption"></span></div>
    </div>
  </div>
</div>
<div class="wrapper" style="background:#f8f8f8; height:340px; padding-top: 10px; width:100%;">
      <div id="info" style="margin-top:3%; margin-left:9%; float:left; width:25%; margin-bottom:3%;">
      <div class="img">
         <span><img src="resources/bootstrap/images/최고.PNG" style="margin-left:35%; margin-bottom:8%; width: 70px;height: 75px;"/></span>
      </div>
      <div class="write">
         <b style="margin-left:22%; margin-top:10%; margin-bottom:5%; font-size:24px; font-family:'Hanna';">믿을만한 품질</b>
         <p style="font-size:16px; text-align:center;">주위에 숨어계시던 동네 세탁소의 <br/> 세탁 장인들이 고객님들의 세탁물을<br/>최적의 서비스로 세탁해드립니다.
         <br/>고객님의 소중한 세탁물은<br/>저희 세탁소개팅과 함께하세요.</p>
      </div>
      </div>
       <div id="info2" style="margin-top:3%; margin-left:3%; float:left; width:25%; margin-bottom:3%;">
      <div class="img">
         <span><img src="resources/bootstrap/images/컴퓨터.PNG" style="margin-left:40%; margin-bottom:8%; width: 70px;height: 75px;"/></span>
      </div>
      <div class="write">
         <b style="margin-left:18%; font-size:24px; margin-top:10%; margin-bottom:5%; font-family:'Hanna';">간편한 예약서비스</b>
         <p style="font-size:16px; text-align:center;">온라인을 통해 간편한 예약이 가능합니다.<br/>내가 원하는 시간과 날짜에<br/>예약이 가능해 편리합니다.<br/>
         다른 이용 고객들의 리뷰를 통해
         <br/>고객님들께 필요한 정보를 제공합니다.</p>
      </div>
      </div>
       <div id="info3" style="margin-top:3%; margin-left:4%; float:left; width:25%; margin-bottom:3%;">
      <div class="img">
         <span><img src="resources/bootstrap/images/시간.PNG" style="margin-left:36%; margin-bottom:8%; width: 80px;height: 75px;"/></span>
      </div>
      <div class="write">
         <b style="margin-left:10%; font-size:24px; margin-bottom:5%; font-family:'Hanna';">원하는 시간에 언제든지</b>
         <p style="font-size:16px; text-align:center;">세탁소에 갈 시간 없는 현대인들을 위한 <br/>최고의 시간절약 시스템.
         <br/>세탁소개팅을 통해 더욱 더<br/>간편하게 세탁물을 맡기고 받아보세요.<br/>시간과 장소에 구애받지 않는 장점까지!</p>
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
    <p class="fl_left">　　Copyright &copy; 2017 - All Rights Reserved </p>
  </div>
</div>
</body>
<script>
window.onload=function(){
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
</html>