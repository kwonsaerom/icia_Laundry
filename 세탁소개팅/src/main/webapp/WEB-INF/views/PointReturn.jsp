 <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
#button{height:15px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
.float{float: left;}
#size{font-size:16px;}
.iText{height:34px;margin-bottom:5px;width:200px;margin:0 0;padding:10px 12px;border:1px solid #e1e1e1}
#returndiv {margin-bottom:10px;}
.space{margin-bottom:10px;}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<body id="top">
<div class="wrapper row1" style="height: 300px;">
  <header id="header" class="clear" style="width:92%;"> 
  <c:set var="kind">${menu}</c:set>
     <c:choose>
        <c:when test="${menu eq '개인'}">
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
        </c:when>
        <c:when test="${menu eq '사업자'}">
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
        <li class="active"><a class="drop" href="#">마이페이지</a>
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
<div class="wrapper" style="background-color:white; height:450px;">
   <div id="returndiv" style="margin-left: 30%;">
   <input type="hidden" id="kind" value="${kind }"/>
   <form name="pointreturnFrm">
      <div id="size">포인트 <span id="span"></span>하기</div><br/>
         <div class="space">
         <input type="radio" name="person"  id="member" value="개인"/>개인회원
             <input type="radio" name="person" id="business" value="사업자"/>사업자회원<br/>
       </div>
       <div class="space">
            현재포인트  ${pointtotal }포인트<br/>
       </div>
         <input type="text" name="point" id="point" class="space iText" onkeyup="returnpoint()" placeholder="환불포인트"/><br/>
       <div class="space">
            환불 후 잔여포인트 <span id="balance"></span><br/>
       </div>
      <input type="text" name="name" class=" space iText" onFocus="checkpoint()" placeholder="이름"/><br/>
      <select name="first" id="first" class="space" style="width:198px; height:32px;color:#888888;">
               <option value="10">010</option>
               <option value="11">011</option>
               <option value="17">017</option>
               <option value="2">02</option>
               <option value="31">031</option>
               <option value="32">032</option>
               <option value="33">033</option>
               <option value="41">041</option>
               <option value="42">042</option>
               <option value="43">043</option>
               <option value="44">044</option>
               <option value="51">051</option>
               <option value="52">052</option>
               <option value="53">053</option>
               <option value="54">054</option>
               <option value="55">055</option>
               <option value="61">061</option>
               <option value="62">062</option>
               <option value="63">063</option>
               <option value="64">064</option>
            </select>
            - <input type="text" class="space iText" name="second" id="second" placeholder="앞번호"/>
            - <input type="text" class="space iText" name="third" id="third" placeholder="뒷번호"/></br>
            <select name="bank" class="space" style="width:198px; height:32px;color:#888888;">
               <option value="">은행선택</option>
               <option value="우리">우리</option>
               <option value="신한">신한</option>
               <option value="NH농협">NH농협</option>
               <option value="KB국민">KB국민</option>
               <option value="하나">하나</option>
               <option value="외환">외환</option>
               <option value="IBK기업">IBK기업</option>
               <option value="새마을">새마을</option>
               <option value="부산">부산</option>
               <option value="경남">경남</option>
               <option value="광주">광주</option>
               <option value="전북">전북</option>
               <option value="신협">신협</option>
               <option value="SC제일">SC제일</option>
               <option value="KDB산업">KDB산업</option>
               <option value="대구">대구</option>
               <option value="제주">제주</option>
               <option value="우체국">우체국</option>
               <option value="수협">수협</option>
            </select>
      &nbsp;&nbsp;<input type="text" name="banknum" class="space iText"  placeholder="계좌번호('-'제외하고 작성해주세요)" style="
    width: 256px;"/><br/>
      <div style="margin-top:100px;">
         <a href="#" onclick="pointreturn()" id="button" style="margin-left:25%;"><span id="return"></span></a>
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
   window.onload = function(){
      var kind = $("#kind").val();
      console.log("kind="+kind);
      
      if(kind == '개인'){
         $("#return").text("환불하기");
         $("#span").text("환불");
         $("#member").prop('checked', true);
         $("#business").prop('disabled', true);
      }else if(kind == '사업자'){
         $("#return").text("환전하기");
         $("#span").text("환전");
         $("#business").prop('checked', true);
         $("#member").prop('disabled', true);
      }
      
      
   }
   function returnpoint(){
      var point = $("#point").val();
      var realpoint = ${pointtotal};
      if(realpoint<point){
         alert(realpoint+"포인트를 초과하였습니다.");
         $("#point").val('');
         $("#balance").text('');
      }else{
         $("#balance").text(realpoint-point);
      }
      
   }
   function checkpoint(){
      var point = $("#point").val();
      var kind =  $('input:radio[name="person"]:checked').val();
      console.log(kind);
      if(kind == "개인"){
         if(point<3000){
            alert("3000포인트 이상 환불 가능합니다.");
            $("#point").val('');
            $("#point").focus();
            $("#balance").text('');
            return false;
         }
      }else if(kind == "사업자"){
         if(point<5000){
            alert("5000포인트 이상 환전 가능합니다.");
            $("#point").val('');
            $("#point").focus();
            $("#balance").text('');
            return false;
         }
      }
   }
   function pointreturn(){
      document.pointreturnFrm.action="./pointexchange";
       document.pointreturnFrm.submit();
   }
   $(function(){
        if('${msg}'=="환불신청 되었습니다."){
           alert('${msg}');
        }
    });
</script>
</html>