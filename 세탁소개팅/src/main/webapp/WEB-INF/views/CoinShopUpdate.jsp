<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
.iText{height:34px;margin-bottom:1%;width:200px;padding:10px 12px;border:1px solid #e1e1e1;margin-top: 1%;}
textarea{height:150px;margin-bottom:0.5%;width:65%;padding:10px 12px;border:1px solid #e1e1e1}

.filebox label { display: inline-block; padding: .5em .75em;font-size: 14px;font-weight:700; line-height: normal; vertical-align: middle; background-color: #a4a4a4; color:white; cursor: pointer; border: 1px solid #ebebeb; border-bottom-color: #e2e2e2; border-radius: .25em; }
.filebox input[type="file"] { /* 파일 필드 숨기기 */ position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px;  clip:rect(0,0,0,0); border: 0; }
.filebox input[type=text] {vertical-align:middle;display:inline-block;line-height:28px;font-size:12px;padding:0;height:34px;margin-bottom:0.5%;width:200px;padding:10px 12px;border:1px solid #e1e1e1}
table{margin-top:1.5%;width:534px;margin-bottom:0;}
.image{width:300px;float:left;margin-right:3%;margin-bottom:1%;}
.imgbtn{width: 40px;margin-left: 90%;}

</style>
<script src="resources/js/jquery-3.2.1.min.js"></script>
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
var arr= [];
var gfv_count = 1;
$(document).ready(function(){
      $("#addFile").on("click", function(e){ //파일 추가 버튼
        e.preventDefault();
        fn_addFile();
    });
     
    $("a[name='delete']").on("click", function(e){ //삭제 버튼
        e.preventDefault();
    console.log($(this).html());
       
        fn_deleteFile($(this));
    });
});
function fn_addFile() {
    var list = document.getElementById("div"+(1+gfv_count));
     list.style.display = 'block';
     gfv_count++;
  }

  function fn_deleteFile(obj) {
     console.log(obj.parent().html());
     $("#saveDiv").append(obj.parent().html());
     obj.parent().remove();
  }
function doDisplay(){
    var con = document.getElementById("addressChange");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}


</script>
</head>
<style>
</style>
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
      <a href="./BusinessMain"> <img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:11%; margin-top:2%; margin-bottom:2%;"/></a>
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


<div class="wrapper" style="background-color:white; height: 80%;">
	<div class="loginForm" style="margin-left:28%; ">
	<form name="updateForm"action="updateCoinShop" method="post" enctype="multipart/form-data">
	<input type="text" name="cl_name" value="${co.cl_name}" class="iText"/><br/>
	${co.cl_address}<input type="hidden" name="cl_address" value="${co.cl_address}"/>
	<a href="javascript:doDisplay();">주소변경</a>
	<div id="addressChange" style="display:none">
      <input type="text" name="post" id="sample6_postcode" placeholder="우편번호" class="iText"/>
      <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호찾기" /><br/> 
      <input type="text" name="addr1" placeholder="주소" id="sample6_address" class="iText"/> 
      <input type="text" name="addr2" id="sample6_address2" placeholder="상세주소" class="iText"/> 
         </div>
         <br/>
         
         
<input type="text" name="cl_phone" value="${co.cl_phone}" class="iText"/><br/>
	오픈<select name="cl_open" id="cl_open" style="width:198px; height:32px;color:#888888;border:1px solid #e1e1e1">
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
         <option value="19">19:00</option>
         <option value="20">20:00</option>
         <option value="21">21:00</option>
         <option value="22">22:00</option>
         <option value="23">23:00</option>
         <option value="24">24:00</option>
      </select>
      마감 <select name="cl_close" id="cl_close" style="width:198px; height:32px;color:#888888;border:1px solid #e1e1e1">
         <option value="1">01:00</option>
         <option value="2">02:00</option>
         <option value="3">03:00</option>
         <option value="4">04:00</option>
         <option value="5">05:00</option>
         <option value="6">06:00</option>
         <option value="7">07:00</option>
         <option value="8">08:00</option>
         <option value="9">09:00</option>
         <option value="0">10:00</option>
         <option value="11">11:00</option>
         <option value="12">12:00</option>
         <option value="13">13:00</option>
         <option value="14">14:00</option>
         <option value="15">15:00</option>
         <option value="16">16:00</option>
         <option value="17">17:00</option>
         <option value="19">19:00</option>
         <option value="20">20:00</option>
         <option value="21">21:00</option>
         <option value="22">22:00</option>
         <option value="23">23:00</option>
         <option value="24">24:00</option>
      </select><br>
       세탁기개수 <input type="text" name="cl_qty" value="${co.cl_qty}" class="iText" style="margin-top:0.5%;"/> <br>
      
      <div id="fileDiv1">
      사진 등록 및 변경 <a href="#this" class="btn" id="addFile">파일 추가</a><br/>
      <div class="filebox" id="div1" style="margin-top: 0.5%;">
      	<label for="file">파일선택
      <input type="file" id="file" name="img_name1">
      </label>
      <input type="text" readonly="readonly" title="File Route" id="text1"/>
       <a href="#this"class="btn" id="delete" name="delete">삭제</a>
      </div>
              <div class="filebox" id="div2" style="margin-top:0.5%;display:none">
               <label for="file2">파일선택
                  <input type="file" id="file2" name="img_name2"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text2"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
      </div>
        <div class="filebox" id="div3" style="margin-top:0.5%;display:none">
               <label for="file3">파일선택
                  <input type="file" id="file3" name="img_name3"/> 
               </label>
               <input type="text" readonly="readonly" title="File Route" id="text3"/>
               <a href="#this" class="btn" id="delete" name="delete">삭제</a>
      </div>
      
      
      </div>
      <input type="hidden" id="cl_code" name="cl_code" value="${co.cl_code}"/>
      <input type="hidden" id="arr" name="arr"/>
      <div id="saveDiv" style="display:none"> 
      </div>
      
      <div id="fileDiv">
      ${lmgList }
      </div>
      가격 및 상세정보 <br/>   <textarea rows="10" cols="40" name="cl_content">${co.cl_content }</textarea><br/>
      <div style="width: 500px;margin-left: 28%;margin-top: 2%;">
      <a href="#" onclick="updateCoinShop()" class="button">수정하기</a>
      </div>
      </form>
      </div>
</div>
</body>
<script>
var arr = [];
var arrStr;
function updateCoinShop(){
   $('#saveDiv input').each(function(i){
      arr.push($(this).val());
      arrStr = arr.toString();
      $('#arr').val(arrStr);
   });
   document.updateForm.action="./updateCoinShop";
   document.updateForm.submit();
}

window.onload = function(){
var open=${co.cl_open};
var close=${co.cl_close};
    $('#cl_open').val(open);
    $('#cl_close').val(close);
  
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