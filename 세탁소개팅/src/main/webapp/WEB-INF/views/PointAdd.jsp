 <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Vendor</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
.text_border {
   text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -moz-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -webkit-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
}
#button{height:40px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:300px;text-align:center;background:#a4a4a4}
#size{font-size:16px;}
.iText{width:300px;margin:0 0;padding:10px 12px;border:1px solid #e1e1e1}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<body id="top">
<div class="wrapper row1">
  <header id="header" class="clear" style="width:92%;">
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
        <li><a href="./HomeInfoMV2">홈페이지소개</a></li>
        <li class="active"><a href="#" class="drop">마이페이지</a>
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
  </header>
</div>
<div class="wrapper" style="background-color:white; height: 450px;">
   <div>
      <form name="pointFrm" >
         <div style="margin-left: 22%;">
            <div id="size">포인트 충전</div><br/>
            충전할 포인트 <select name="point" id="point" onChange="pointchange()" style="width:230px; height:32px;color:#888888;">
               <option value="">충전할 포인트를 선택해주세요</option>
               <option value="5000">5,000</option>
               <option value="10000">10,000</option>
               <option value="15000">15,000</option>
               <option value="20000">20,000</option>
            </select><br /> <br /> 결제수단
         </div>
         <table style="margin-top: 10px; width: 700px; margin-left: 22%;">
            <tr>
               <td>입금계좌선택</td>
               <td><select name="bank" style="width:204px; height:32px;color:#888888;">
                     <option value="">입금계좌를 선택해주세요</option>
                     <option value="농협">농협 111-1111-1111-11</option>
                     <option value="기업">기업 222-222222-22-222</option>
                     <option value="국민">국민 333333-33-333333</option>
               </select></td>
            </tr>
            <tr>
               <td>입금자명</td>
               <td><input type="text" name="name" class="iText" placeholder="입금자명을 써주세요"/></td>
            </tr>
            <tr>
               <td>입금금액</td>
               <td><span id="price"></span><br /> (선택하신 계좌로 3일 이내에 입금해주시지
                  않으면 자동 취소됩니다.)</td>
            </tr>
         </table>
         <a href="#" onclick="charge()" id="button" style="margin-left:48%; ">충전하기</a>
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
   function pointchange(){
      var point = $("#point option:selected").val();
      $("#price").text(point+"원");
      
   }
   function charge(){
       document.pointFrm.action="./pointcharge";
       document.pointFrm.submit();
   }
   
</script>
</html>
