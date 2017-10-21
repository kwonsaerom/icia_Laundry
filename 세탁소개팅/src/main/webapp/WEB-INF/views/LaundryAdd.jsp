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
body{background-color: white;}
.text_border {
   text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -moz-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
   -webkit-text-shadow: -1.5px 0 black, 0 1.5px black, 1.5px 0 black, 0 -1.5px black;
}
.button{height:40px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;text-align:center;background:#a4a4a4}
#size{font-size:16px;}
.iText{height:34px;margin-bottom:0.5%;width:200px;padding:10px 12px;border:1px solid #e1e1e1}
textarea{height:150px;margin-bottom:0.5%;width:65%;padding:10px 12px;border:1px solid #e1e1e1}

.filebox label { display: inline-block; padding: .5em .75em;font-size: 14px;font-weight:700; line-height: normal; vertical-align: middle; background-color: #a4a4a4; color:white; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; }
.filebox input[type="file"] { /* 파일 필드 숨기기 */ position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px;  clip:rect(0,0,0,0); border: 0; }
.filebox input[type=text] {vertical-align:middle;display:inline-block;line-height:28px;font-size:12px;padding:0;height:34px;margin-bottom:0.5%;width:200px;padding:10px 12px;border:1px solid #e1e1e1}
table{margin-top:1.5%;width:500px;margin-bottom:0;}


</style>
</head>
<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script>
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
</script>
<script type="text/javascript">   
        var gfv_count = 1;
        $(document).ready(function(){
            $("#addFile").on("click", function(e){ //파일 추가 버튼
                 e.preventDefault();
                 fn_addFile();
             });
              
             $("a[name='delete']").on("click", function(e){ //삭제 버튼
                 e.preventDefault();
                 fn_deleteFile($(this));
             });
         });
        
         function fn_addFile(){
            /* if((2+gfv_count)<=5){
               console.log("num="+(2+gfv_count));
                var str = "<div class='filebox'><label for='file"+(2+gfv_count)
                +"'>매장사진<input type='file' id='file"+(2+gfv_count) +"' name='img_name"
                +(2+gfv_count)+"'/></label><input type='text' readonly='readonly' title='File Route' id='text"
                +(2+gfv_count)+"' style='margin-left:0.6%;'/><a href='#this' class='btn' name='delete' style='margin-left:0.5%;'>삭제</a></div>";
                gfv_count++;
                $("#fileDiv").append(str);
                $("a[name='delete']").on("click", function(e){ //삭제 버튼
                    e.preventDefault();
                    fn_deleteFile($(this));
                });
            } */
            console.log("gfv_count="+gfv_count);
            var list = document.getElementById("div"+(2+gfv_count));
            list.style.display = 'block';
            gfv_count++;
            
         }

         function fn_deleteFile(obj){
             obj.parent().css('display','none');
             gfv_count--;
         }
         
         $(function(){
              if('${msg}'=="매장등록 성공"){
                 alert('${msg}');
              }else if('${msg}'=="매장등록 실패"){
                 alert('${msg}');
              }
           });
</script>
<script type="text/javascript">
   
   function insertPopup(){
      var nl_name=$("#nl_name").val();
      var post=$("#sample6_postcode").val();
      var addr1=$("#sample6_address").val();
      var addr2=$("#sample6_address2").val();
      var first=$("#first").val();
      if(first==null){
         first="01";
      }
      var second=$("#second").val();
      var third=$("#third").val();
      var nl_open=$("#nl_open").val();
      var nl_close=$("#nl_close").val();
      var kind = "등록";
      //window.name = "부모창 이름";
      window.name = "LaundryAdd.jsp";
      //window.open("자식창 이름", "불러올 자식 창의 닉네임", "팝업창 옵션");
      window.open("./LaundryAddPop?nl_name="+nl_name+"&post="+post+"&addr1="+addr1+"&addr2="+addr2+
            "&first="+first+"&second="+second+"&third="+third+"&nl_open="+nl_open+"&nl_close="+
            nl_close+"&kind="+kind,"", "width = 670, height = 420, resizabla = no, scrollbars = no, status = no");
      /* document.laundryform.action="./LaundryAddPop";
      document.laundryform.submit();*/
   }
   
</script>
<script>
window.onload = function(){
     var list = document.getElementById("handlelist");
     if("${handleList }"!=""){
        //style="overflow:scroll; width:518px; height:200px;margin-top:0.5%;"
        //$("#handlelist").css({'overflow':'scroll','width':'518px','height':'200px','margin-top':'0.5%'});
        /* $("#handlelist").css("overflow","scroll");
        $("#handlelist").css("width","518px");
        $("#handlelist").css("height","200px");
        $("#handlelist").css("margin-top","0.5%"); */
        list.style.overflow = 'scroll';
        list.style.width = '518px';
        list.style.height = '200px';
        //list.style.margin-top = '10px';
     }
     if("${nlMap.first}"!=""){
        //if("${nlMap.nl_open}"!=""){
           var first=${nlMap.first};
           //var open=${nlMap.nl_open};
           //var close=${nlMap.nl_close};
           $('#first').val(first);
           //$('#nl_open').val(open);
           //$('#nl_close').val(close);
        //}
     }
        
   }
 

</script> 
<body id="top">
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
        <li class="active"><a href="#" class="drop">매장등록</a>
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
<div class="wrapper" style="background-color:white; height: 80%; margin-top:5%;" >
   <div class="loginForm" style="margin-left:28%; ">
   <form action="laundryAdd" name="laundryform" method="post" enctype="multipart/form-data">
       <input type="text" class="iText" name="nl_name" id="nl_name" value="${nlMap.nl_name }" placeholder="세탁소 이름"/> </br> 
       <input type="text" class="iText" name="post" value="${nlMap.post }" id="sample6_postcode" placeholder="우편번호" />
            <a href="#" onclick="sample6_execDaumPostcode()" class="button">우편번호찾기</a></br> 
            <input type="text" class="iText" name="addr1" value="${nlMap.addr1}" placeholder="주소" id="sample6_address" /> 
            <input type="text" class="iText" name="addr2" value="${nlMap.addr2}" id="sample6_address2" placeholder="상세주소" /> <br /> 
       <select name="first" id="first" style="width:198px; height:32px;color:#888888;border:1px solid #e1e1e1">
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
         - <input type="text" class="iText" name="second" id="second" value="${nlMap.second}" placeholder="앞번호"/>
         - <input type="text" class="iText" name="third" id="third" value="${nlMap.third}" placeholder="뒷번호"/></br>
         <div style="margin-top:1.3%;"><a href="#" onclick="insertPopup()" class="button" name="laundryhandle">가능물품 및 가격 등록</a></div> 
         <div id="handlelist" style="margin-top:0.5%;">${handleList }</div> 
         <input type="hidden" name="arr" value="${arr }"/> 
          <select name="nl_open" id="nl_open" style="width:198px; height:32px;color:#888888;border:1px solid #e1e1e1;margin-top:0.5%;">
               <option value="">오픈시간</option>
               <option value="1">01:00</option>
               <option value="2">02:00</option>
               <option value="3">03:00</option>
               <option value="4">04:00</option>
               <option value="5">05:00</option>
               <option value="6">06:00</option>
               <option value="7">07:00</option>
               <option value="8">08:00</option>
               <option value="9">09:00</option>
               <option value="10">10:00</option>
               <option value="11">11:00</option>
               <option value="12">12:00</option>
               <option value="13">13:00</option>
               <option value="14">14:00</option>
               <option value="15">15:00</option>
               <option value="16">16:00</option>
               <option value="17">17:00</option>
               <option value="18">18:00</option>
               <option value="19">19:00</option>
               <option value="20">20:00</option>
               <option value="21">21:00</option>
               <option value="22">22:00</option>
               <option value="23">23:00</option>
               <option value="24">24:00</option>
         </select> 
         <select name="nl_close" id="nl_close" style="width:198px; height:32px;color:#888888;border:1px solid #e1e1e1">
               <option value="">마감시간</option>
               <option value="1">01:00</option>
               <option value="2">02:00</option>
               <option value="3">03:00</option>
               <option value="4">04:00</option>
               <option value="5">05:00</option>
               <option value="6">06:00</option>
               <option value="7">07:00</option>
               <option value="8">08:00</option>
               <option value="9">09:00</option>
               <option value="10">10:00</option>
               <option value="11">11:00</option>
               <option value="12">12:00</option>
               <option value="13">13:00</option>
               <option value="14">14:00</option>
               <option value="15">15:00</option>
               <option value="16">16:00</option>
               <option value="17">17:00</option>
               <option value="18">18:00</option>
               <option value="19">19:00</option>
               <option value="20">20:00</option>
               <option value="21">21:00</option>
               <option value="22">22:00</option>
               <option value="23">23:00</option>
               <option value="24">24:00</option>
         </select> 
       
      <div style="width:300px;margin-top:0.8%;">수선 가능여부 <input   type="checkbox" name="nl_repair" value="가능" onclick="oneCheckbox(this)"/>가능
             <input type="checkbox"   name="nl_repair" value="불가능" onclick="oneCheckbox(this)"/>불가능
             <input type="hidden" id="nl_repair" name="nl_repair"/></div> 
      <div style="width:300px; margin-top:1%; margin-bottom:1%;">예약시간간격<input type="checkbox" name="nl_timelap" value="1시간" onclick="oneCheckbox1(this)"/>1시간
             <input   type="checkbox" name="nl_timelap" value="30분" onclick="oneCheckbox1(this)"/>30분
             <input type="hidden" id="nl_timelap" name="nl_timelap"/></div>
         <input type="text" class="iText" name="nl_qty" id="nl_qty" value="${nlMap.nl_qty }" placeholder="예약가능횟수(숫자만 입력)" /><br/> 
      <div class="filebox">
         <label for="b_lisence">사업자 등록증
            <input type="file" name="b_lisence" id="b_lisence" />
         </label>
         <input type="text" readonly="readonly" title="File Route" id="text"/>
      </div>

      <div id="fileDiv">
         매장사진 (2장 이상) 
         <a href="#this" class="btn" id="addFile">파일 추가</a>
            <div class="filebox" id="div1" style="margin-top:0.5%;">
               <label for="file">매장사진
                  <input type="file" id="file" name="img_name1"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text1"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
          </div>
          
            <div class="filebox" id="div2">
               <label for="file2">매장사진
                  <input type="file" id="file2" name="img_name2"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text2"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
          </div>

            <div class="filebox" id="div3" style="display:none;">
               <label for="file3">매장사진
                  <input type="file" id="file3" name="img_name3"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text3"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
          </div>

            <div class="filebox" id="div4" style="display:none;">
               <label for="file4">매장사진
                  <input type="file" id="file4" name="img_name4"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text4"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
          </div>

            <div class="filebox" id="div5" style="display:none;">
               <label for="file5">매장사진
                  <input type="file" id="file5" name="img_name5"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text5"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
          </div>

      </div>
      <textarea   name="nl_content" id="nl_content" placeholder="홍보글">${nlMap.nl_content }</textarea></br> 
     <div style="margin-top:1%; margin-left:30%; margin-bottom: 5%;"><input type="submit" value="등록하기" class="button"/></div>
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
   function oneCheckbox(a){
       var obj = document.getElementsByName("nl_repair");
       for(var i=0; i<obj.length; i++){
           if(obj[i] != a){
               obj[i].checked = false;
           }
           
       }
       
   }
   function oneCheckbox1(a){
      var obj1 = document.getElementsByName("nl_timelap");
      for(var i=0; i<obj1.length; i++){
           if(obj1[i] != a){
               obj1[i].checked = false;
           }
           
       }
   }
   

</script>
<script>

   $('.filebox input[name="b_lisence"]').change(function() {
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text').val(fileName);
      } else {
         $('#text').val('파일 ' + fileCount + '개');
      }
   });
   $('.filebox input[name="img_name1"]').change(function() {
      console.log("11111");
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text1').val(fileName);
      } else {
         $('#text1').val('파일 ' + fileCount + '개');
      }
   });
   $('.filebox input[name="img_name2"]').change(function() {
      console.log("22222");
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text2').val(fileName);
      } else {
         $('#text2').val('파일 ' + fileCount + '개');
      }
   });
   $('.filebox input[name="img_name3"]').change(function() {
      console.log("3333");
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text3').val(fileName);
      } else {
         $('#text3').val('파일 ' + fileCount + '개');
      }
   });
   $('.filebox input[name="img_name4"]').change(function() {
      console.log("4444");
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text4').val(fileName);
      } else {
         $('#text4').val('파일 ' + fileCount + '개');
      }
   });
   $('.filebox input[name="img_name5"]').change(function() {
      console.log("5555");
       var fileName = $(this).val();
      var fileCount = $(this).get(0).files.length;
      if ($(this).get(0).files.length == 1) {
         $('#text5').val(fileName);
      } else {
         $('#text5').val('파일 ' + fileCount + '개');
      }
   });
</script>
</html>