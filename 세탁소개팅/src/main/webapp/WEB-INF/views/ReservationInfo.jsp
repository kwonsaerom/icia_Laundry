<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Vendor</title>
<meta charset="utf-8">
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">
<style>
 .iText2{
 height:32px;
 }
.text_border {
   text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -moz-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -webkit-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
}
.top{
   background-color :#AFEEEE;
}
td{
   padding: 10px;
}
 .rounded{
           width:100px;
           height:100px;
        }
.margin{
   margin-left: 40%;
}
body{
background-color: white;
}
.btn{
   height:12px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
   function chan(){
      var text = $("#state option:selected").val();
      $('#text').val(text);
      $('#nlcode').val('${nlcode}');
      
      alert('${nlcode}');
      if(text==9){
         document.logFrm.action = "./reservationList";
         document.logFrm.submit();
      }else{
         document.logFrm.action = "./selectState";
         document.logFrm.submit();
      }
   }
   
   $(document).ready(function(){
      var st = '${state}';
      console.log("st값"+st);
      if(st==1){
         $('#two').css("display","block");
      }else if (st==3){
         $('#one').css("display","block");
         $('#three').css("display","block");
      }else if(st==4){
         $('#two').css("display","block");
         $('#four').css("display","block");
      }else if(st==5){
         $('#three').css("display","block");
      }
   })

   $(function() {
         $('#nlcode').val('${nlcode}');
      $('#one').click(function() {
         alert($('#nlcode').val);
         $('#statebtn').val($("#one").attr('class'));
         document.logFrm.action = "./laundryProcessing";
         document.logFrm.submit();
      })
      $('#two').click(function() {
         alert($('#nlcode').val);
         $('#statebtn').val($("#two").attr('class'));
         document.logFrm.action = "./laundryProcessing";
         document.logFrm.submit();
      })
      $('#three').click(function() {
         alert($('#nlcode').val);
         $('#statebtn').val($("#three").attr('class'));
         document.logFrm.action = "./laundryProcessing";
         document.logFrm.submit();
      })
      $('#four').click(function() {
         alert($('#nlcode').val);
         $('#statebtn').val($("#four").attr('class'));
         document.logFrm.action = "./laundryProcessing";
         document.logFrm.submit();
      })
   }); 
</script>
<script>
window.onload = function(){
    var kind="${kind}";
       $("#state option[value='"+kind+"']").attr("selected","selected");
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
   $(document).ready(function() {
      //최상단 체크박스 클릭
      $("#0").click(function() {
         //클릭되었으면
         if ($("#0").prop("checked")) {
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=check]").prop("checked", true);
            //클릭이 안되있으면
         } else {
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=check]").prop("checked", false);
         }
      })
   })
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
        <li class="active"><a href="./ReservationShop">예약현황</a>
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
   <form name="logFrm" method="get" style="margin-left: 10%;height:550px; margin-right: 10%;">
      <select id=state class=iText2 style="margin-bottom:10px;">
         <option value=9>전체</option>
         <option value=1>수거전</option>
         <option value=2>취소중</option>
         <option value=3>세탁중</option>
         <option value=4>배달중</option>
         <option value=5>완료</option>
         <option value=6>취소완료</option>
         <option value=7>지급완료</option>
         <option value=8>승인취소</option>
      </select> <input type="hidden" name=text id=text> 
      <input type="hidden" name=btnchoice id=btnchoice> 
      <input type="hidden" name=nlcode id=nlcode value="${nlcode}"> 
      <a href="#" class="btn" onclick="chan()">검색</a>
      <br>
         ${BRLIST}${RLIST}
      <div style="float:right; display: inline-flex; padding-bottom: 10px;">
      <input type="button" value="수거취소" id="one" class="1" style="display:none"> 
      <input type="button" value="수거완료" id="two" class="3" style="display:none"> 
      <input type="button" value="세탁완료" id="three" class="4" style="display:none">
      <input type="button" value="배달완료" id="four" class="5" style="display:none"> 
      <input type="hidden" name="statebtn" id="statebtn" /> 
      </div>
   </form>
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