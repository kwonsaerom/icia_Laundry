<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>HomeInfo</title>
<meta charset="utf-8">
<link rel="icon" href="resources/bootstrap/images/favicon.ico" type="image/x-icon" />
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<style>
 @import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
 @import url(//fonts.googleapis.com/earlyaccess/hanna.css);
 @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
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


<body>
<div class="wrapper row1">
  <header id="header" class="clear" style="width:100%;"> 
    <c:set var="menu">${menu}</c:set>
    <c:choose>
    <c:when  test="${menu eq '공통'}"> 
  <a href="./JoinMemberMV" style="float:right;">Join</a><a  href="./LoginMV"style="float:right;">Login　|　</a>
    <div id="logo">
      <a href="./"><img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:11%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li class="active"><a href="./HomeInfoMV">홈페이지소개</a></li>
        <li><a href="./LaundryMethodMv">세탁방법안내</a></li>
        <li><a href="./shoplogin">매장찾기</a></li>
        <li><a href="./NoticeManage">공지사항</a>
      </ul>
    </nav>
    </c:when>
    <c:when  test="${menu eq '개인'}">
    <a href="./logout" style="float:right;">로그아웃</a><a  href="./personUpdateMV"style="float:right;">개인정보수정　|　</a><br/>
    <div id="check" style="display:none">
    <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
    <img src="resources\bootstrap\images\new.png" style="width:50px; height:50px; float:right;"></a></div>
  	<div id="check1" style="display:block">
  	<a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
   <img src="resources\bootstrap\images\message.png" style="width:50px; height:50px; float:right;"></a>
  	</div>
    <div id="logo">
    <a href="./PersonMainMV">  <img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:9%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li class="active"><a href="./HomeInfoMV2">홈페이지소개</a></li>
        <li><a href="#" class="drop">마이페이지</a>
            <ul>
               <li><a href="./personMyPage">나의 정보</a></li>
               <li><a href="./personReservation">예약내역</a></li>
               <li><a href='./myPoint?type=전체내역&value=포인트'>나의포인트</a></li>
               <li><a href="./myActivity">나의 활동</a></li>
            </ul>
        </li>
        <li><a href="#" class="drop">매장찾기</a>
        	<ul>
        		<li><a href="./shopListSelect">일반세탁소</a></li>
        		<li><a href="./coinShopList">코인세탁소</a></li>
        	</ul>
        </li>
        <li><a class="drop" href="#">Q & A</a>
             <ul>
               <li><a href="./NoticeManage2">공지사항</a></li>
               <li><a href="./request?menu=개인">1:1문의</a></li>
            </ul>
        </li>
      </ul>
    </nav>
    </c:when>
    
    <c:when  test="${menu eq '사업자'}">
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
    </c:when>
     </c:choose>
    
  </header>
</div>
<div class="warpper" style="background:white; width:100%; padding-top: 10px;">
 <div class="loginForm" style="margin-left:12%; margin-top:4%; height:650px; width:90%;" >
       <h1 style="font-family: 'Hanna'; font-size:30px; margin-bottom:5px; color:#6d6d6d;">빨래는 세탁소개팅과 함께.</h1><br/>
      <img src="resources\bootstrap\images\22095218_1.jpg" style="width: 300px; float:left;"/>
      <span style="clear:left;">　원하는 시간과 장소에서 세탁물을 맡기고 깨끗한 옷을 받아보세요!<br/><br/>　<b style="font-family: 'Jeju Gothic'; font-size:17px;">앉아서 편안하게.</b><br/>　세탁소에 직접 들리지 말고 세탁소개팅을 이용하여 세탁을 맡겨주세요. <br/>　원하는 시간에 세탁물을 문 앞까지 픽업해드리고, 배달해드립니다.
      <br/><br/>　<b style="font-family: 'Jeju Gothic'; font-size:17px;">시간을 자유롭게</b><br/>　세탁소개팅은 사이트를 통해 고객님께서 편한시간에 세탁물을 맡기고 받을 수 있는 서비스를 제공합니다.<br/>
      　혹시나 예약한 날짜에 갑자기 약속이 생겨도 수거 1시간 전까지는 예약을 변경하실 수 있습니다.<br/>
      <br/><br/>　<b style="font-family: 'Jeju Gothic'; font-size:17px;">소중한 내 옷을 걱정없이</b><br/>　가격은 얼마인지, 무엇을 맡겼는지 더이상 생각하지 않으셔도 됩니다. 세탁소개팅의 세탁소마다의 정확한 가격표를 누구나, 어디서든 보실 수 있습니다.<br/>
      　또한, 내가 맡긴 물품과 예약 내역을 한 눈에 볼 수 있습니다.<br/><br/>
      <b style="font-family: 'Jeju Gothic'; font-size:17px;">　세탁소개팅이 당신을 기다립니다.</b><br/>
      　세탁소개팅은 단순히 세탁소와 연결시켜주는 사이트가 아닙니다. <br/>　소중한 옷을 더 오랫동안 입으실 수 있도록 최적의 솔루션을 제공하는 것이 저희의 임무입니다.<br/>
      　언제든지 고객님들이 필요한 시간에 최고의 세탁서비스를 제공하기 위하여 최선을 다하겠습니다.
      </span>   
      <br/><br/><br/><br/><br/>
      <p style=" margin-right:10%; float:right; font-size:30px;font-family: 'Nanum Pen Script';  ">세탁소개팅</p>
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
window.onload = function(){
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