<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link rel="icon" href="resources/bootstrap/images/favicon.ico" type="image/x-icon" />
<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<style>
 #button{height:15px;padding:8px; border-radius:2px; margin-left:755px;
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;background:#a4a4a4}
 table{font-size:14px; width:800px; height:180px;}
</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 
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
        <li><a href="#" class="drop">매장관리</a>
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
        <li class="active"><a class="drop" href="#">포인트관리</a>
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
<div class="wrapper" style="background:white;height: 500px; padding-top: 10px;">
 <div class="loginForm" style="margin-left:22%; margin-top:5%;">
   <form name="chk" >
      <select name="kind" id="kind" onChange="kindchange()" style="width:120px; height:32px; color:#63CAC6; margin-top:3%; margin-bottom:3%;">
          <option value="전체">전체내역</option>
          <option value="일반">일반회원</option>
          <option value="사업자">사업자회원</option>
      </select>
      <select name="state" id="state" onChange="kindchange()" style="width:120px; height:32px; color:#63CAC6; margin-top:3%; margin-bottom:3%;">
          <option value="처리전">처리전</option>
          <option value="처리완료">처리완료</option>
      </select>
   </form>
   <form name="pointRequestFrm">
      <div id="list">
         ${returnlist }
      </div>
      <input type="hidden" name="arr"   id="arr"/><br/>
      <a href="#" onclick="acceptadmin()" id="button" >승인</a>
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
      var kind="${kind}";
      console.log("onload kind="+kind);
      var state="${state}";
      console.log("onload state="+state);
      $("#kind option[value='"+kind+"']").attr("selected","selected");
      $("#state option[value='"+state+"']").attr("selected","selected");
   }
</script>
<script>
   function kindchange(){
      var kind = $("#kind option:selected").val();
      var state = $("#state option:selected").val();
      document.chk.action="./pointRequest?kind="+kind+"&state="+state;
      document.chk.submit();
   }
</script>
<script>
   function acceptadmin(){
      var arr = [];
      $("input[name='choice']:checked").each(function(i){
         arr.push($(this).val());
      });
      $('#arr').val(arr);
      console.log(arr);
      document.pointRequestFrm.action = "./pointAccept";
      document.pointRequestFrm.submit();
   }
</script>
</html>