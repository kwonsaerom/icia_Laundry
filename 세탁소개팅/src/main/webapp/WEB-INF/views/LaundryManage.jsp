<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link rel="icon" href="resources/bootstrap/images/favicon.ico" type="image/x-icon" />
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<style>
     @import url(//fonts.googleapis.com/earlyaccess/hanna.css);
        @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
body{
background:white;
}
table{
   text-align:center;
}
.rounded{
           width:100px;
           height:100px;
        }
.re_star, .re_content, .re_date{
   border:white;
}
#button{height:15px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
#deletebtn{width: 100px;margin-bottom: 1%;margin-left: 92%;}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
<script type="text/javascript">
function selectChange(){
      var str= $("#choice option:selected").val();
    $('#shopchoice').val(str);
      document.chk.action="./laundryManage"
      document.chk.submit();
   }
      
      
function laundryAccept(){
   document.chk.action="./laundryAccept"
   document.chk.submit();
}
function acceptshopdelete(){
   document.chk.action="./acceptshopdelete"
   document.chk.submit();
}
</script>
<script type="text/javascript">
$(function(){
   $('#checkAll').click(function(){
      if($('#checkAll').prop('checked')){
      $('input[name=chkbox]:checkbox').each(function(){
         $(this).prop('checked',true);
      });
      }else{
         $('input[name=chkbox]:checkbox').each(function(){
            $(this).prop('checked',false);
         });
      }
   })
});
</script>
<body>
<div class="wrapper row1">
  <header id="header" class="clear" style="width:92%;">
  <a href="./logout" style="float:right;">로그아웃</a><br/>
    <div id="check" style="display:none">
    <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
    <img src="resources\bootstrap\images\new.png" style="width:50px; height:50px; float:right;"></a></div>
     <div id="check1" style="display:block">
     <a href="./message" onClick="window.open(this.href, '', 'width=700, height=400'); return false;">
   <img src="resources\bootstrap\images\message.png" style="width:60px; height:60px; float:right;"></a>
     </div>
    <div id="logo">
      <a href="./AdminMain"><img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:9%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li class="active"><a href="#" class="drop">매장관리</a>
            <ul>
               <li><a href="./laundryCheck">세탁소 승인 및 확인</a></li>
               <li><a href="./laundryManage">세탁소 관리</a></li>
            </ul>
        </li>
        <li><a href="./laundryReservAdN"" >일반세탁소예약관리</a>
        </li>
     <li><a class="drop" href="#">회원관리</a>
             <ul>
               <li><a href="./blackList">블랙리스트현황</a></li>
               <li><a href="./adminDeleteMV">회원탈퇴</a></li>
            </ul>
        </li>
        <li><a class="drop" href="#">포인트관리</a>
             <ul>
               <li><a href="./pointRequest?kind=전체&state=처리전">포인트 환전요청</a></li>
               <li><a href="./pointMmSelect?kind=대기">대기 중 포인트 확인</a></li>
            </ul>
        </li>
        <li><a class="drop" href="#">Q & A</a>
             <ul>
                 <li><a href="./NoticeManage4">공지사항 수정/등록</a></li>
               <li><a href="./requestCheck">1:1 문의 확인/수정</a></li>
            </ul>
        </li>
      </ul>
    </nav>
  </header>
</div>
<div class="warpper" style="background:white; height: 60%; width:92%; margin-top:2%; margin-bottom:10%; padding-top: 10px;">
 <div class="laundryDeleteFrm" style="margin-left:14%; margin-top:4%; width:90%; height:600px;"  >
   <form name="chk" action="shopdelete" style="margin-right:10%;">
   <span style="font-size:30px; font-family:'Hanna'; margin-left:40%;">세탁소 관리</span><br/><br/><br/>
    <select name="choice" id="choice" onChange="selectChange()" style="width: 120px; height: 32px; color: #63CAC6; margin-bottom:2%;">
         <option value="all">선택하세요</option>
         <option value="laundry">일반세탁소</option>
         <option value="coin">코인세탁소</option>
    </select>
   ${lList}
   
   <input type="hidden" id="shopchoice" name="shopchoice"/>
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
window.onload=function(){
    var choice="${kind}";
    $("#choice option[value='"+choice+"']").attr("selected","selected");
}
</script>
</html>