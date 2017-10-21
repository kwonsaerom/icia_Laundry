<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CoinShopDetail</title>

<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 

<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=99e91fbe1b2c343821dfa986b88cb1b6&libraries=services,clusterer,drawing"></script>
<style>
 @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);


.btn{
       border-radius:2px; padding:8px;
 font-size:14px;font-weight:700;color:#fff;text-align:center;background:#a4a4a4;
 font-size:13px;
font-family:fantasy;
 }
.total{
	margin-left: 20%;
}
#map2{
	margin-top:10px;
	width: 730px;
}
</style>
</head>
<body>
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
      <img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:11%; margin-top:-10px; margin-bottom:2%;"/>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li><a href="index.html">홈페이지소개</a></li>
        <li><a href="#" class="drop">마이페이지</a>
            <ul>
               <li><a href="./personMyPage">나의 정보</a></li>
               <li><a href="./personReservation">예약내역</a></li>
               <li><a href="./pointMm?type=전체내역">포인트 관리</a></li>
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
               <li><a href="./laundryMm">1:1문의</a></li>
               <li><a href="./coinShopMm">자주묻는 질문</a></li>
            </ul>
        </li>
        <li><a href="./NoticeManage">공지사항</a>
      </ul>
    </nav>   
  </header>
</div>
   <!-- 매장정보 DIV -->
   <div class="total">
   <div id="map" style="width:730px;height:380px;position:relative;overflow:hidden;"></div> 
        <div id="map2">${nInfo }</div>
</div>
      <!--결제창 AJAX  -->
   <!--예약하기 DIV-->

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
 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=99e91fbe1b2c343821dfa986b88cb1b6"></script>
<script>
      var container = document.getElementById('map');
      var options = {
         center: new daum.maps.LatLng(33.450701, 126.570667),
         level: 3
      };

      var map = new daum.maps.Map(container, options);
      
      var geocoder = new daum.maps.services.Geocoder();
      var positions=[];
      var infowindow=[];
      
         geocoder.addressSearch('${co.getCl_address()}', function(result, status) {
              // 정상적으로 검색이 완료됐으면 
              if (status === daum.maps.services.Status.OK) {

                 var coords = new daum.maps.LatLng(result[0].y, result[0].x);

                 // 결과값으로 받은 위치를 마커로 표시합니다
                 var marker = new daum.maps.Marker({
                     map: map,
                     position: coords
                 });

                 // 인포윈도우로 장소에 대한 설명을 표시합니다
                 var infowindow = new daum.maps.InfoWindow({
                     content: '<div style="width:150px;text-align:center;padding:6px 0;">${co.getCl_name()}</div>'
                 });
                 infowindow.open(map, marker);
                 map.setCenter(coords);
                 // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
             } 
         });   
         
   </script> 
<script>

function addHandle(){
   
    var give2 = $("#give2 option:selected").val();
   var take2 = $("#take2 option:selected").val();
 
    var gSelect = $('input[name=serviceType]').val();
    var tSelect = $('input[name=serviceType2]').val();
    
    var gdate =$('input[name=date1]').val();
    var tdate =$('input[name=date2]').val();
    var mg =$('input[name=mg]').val();
   
    location.href="./coinPayComplete?gi="+give2+"&ta="+take2+"&gs="+gSelect+"&ts="+tSelect+"&gd="+gdate+"&td="+tdate+"&mg="+mg;
}

</script>
<script type="text/javascript">




$(function() {
   
   var now = '${now}';
   var time = '${timeover}';
   
    $( "#date1" ).datepicker({
        minDate: 0,
         dateFormat: "yy-mm-dd",
         changeMonth: true, 
         changeYear: true,
         nextText: '다음 달',
         prevText: '이전 달',
         showOn: "both", 
         //resources/
         buttonImage: "images/date.png",
         buttonImageOnly: true,
         beforeShow: function(input, inst){
         //현재값 저장 input parameter 클릭했을때
         },
         onSelect:function (dateText, inst){
            //변경시
         if(now==dateText){
         $("#give2 > option[value='${timeover}']").prevAll().attr("disabled", "disabled"); //value값으로 선택 
        
         }else{
        $("#give2").attr("selected", "selected");
          
        }
       }//onselect:function
    }); //datepicker
});

$(function() {
   var now = '${now}';
   var time = '${timeover}';
   
    $( "#date2" ).datepicker({
        minDate: 1,
         dateFormat: "yy-mm-dd",
         changeMonth: true, 
         changeYear: true,
         nextText: '다음 달',
         prevText: '이전 달',
         showOn: "both", 
         buttonImageOnly: true,
         buttonImage: "/images/date.png",
         
         beforeShow: function(input, inst){  //현재클릭했을때
             },
         onSelect:function (dateText, inst){ //클릭변경시
            //변경시
      
         if(now==dateText){
         $("#take > option[value='${timeover}']").prevAll().attr("disabled", "disabled"); //value값으로 선택 
        
         }else{
        $("#take").attr("selected", "selected");
        }
       }//onselect:function
    }); //datepicker      
});

$(document).ready(function() {
    //최상단 체크박스 클릭
    $("#givatakeBtn").click(function() {
       $('.gtSelect').show();   /*숨기기 hide() 보이기 show() */
    })})
</script>

<link type="text/css"
   href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"
   rel="stylesheet">
<script type="text/javascript"
   src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
   src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>



<script>    
$(document).ready(function() {
    //최상단 체크박스 클릭
    $("#info").click(function() {
       $('.mgPoint').show();
       $('.mg').show();
       $('.selectP').show();   /*숨기기 hide() 보이기 show() */
       $('#mgSubmit').show();
       $('#givatakeBtn').show();
    })})

//마일리지 적용    
 function mgSubmit(url, position){
   var mg = $("#mg").val();      //사용마일리지
   var allmg=$("#allmg").val(); //보유마일리지
   var allData = {"mg":mg};
   if(allmg<mg){
       alert("보유마일리지를 초과하였습니다");
       $("#mg").val('');
       $("#mg").focus();
      // $("#mg").text('');
       return false;
   }
   
     var mg = document.getElementById("mgP");
    mg.style.display = 'block';  
   $.ajax({
      url:url,
      type:"get",
      data: allData,   

      success:function(html){
         $(position).html(html);
      },
      error:function(error){
      console.log(error);
      }
   });
}
    
function additem(url, position){
      var first=$('select[name=p_t_name]').val();
      var second=$('select[name=p_name]').val();
      var price=$('input[name=price]').val();
     
      // tr태그의 마지막 번째를 구해 id="item"의 형태로 만들어 lastItemNo에 대입
        var lastItemNo = $("#itemadd tr:last").attr("id").replace("item", "");
      console.log(lastItemNo);
        //새로 추가 할 경우 두번째 tr 값을 복사하여 newitem변수에 대입
        var newitem = '<tr id="item'+(parseInt(lastItemNo)+1)+'"><td id="one'+(parseInt(lastItemNo)+1)+'" name="one'+(parseInt(lastItemNo)+1)+'">'+'<span class="span'+(parseInt(lastItemNo)+1)+'">'+first+'</span>'+
        '</td><td name="two'+(parseInt(lastItemNo)+1)+'">'+second+'</td><td name="three'+(parseInt(lastItemNo)+1)+'">'+price+
      '</td><td><input type="button" id="dBtn2" onclick="removeitem('+(parseInt(lastItemNo)+1)+')" value="-"/></td></tr>';
      $('#itemadd').append(newitem);
      
      $('input[name=price]').val('');
      
       var con = document.getElementById("addM");
      var con1 = document.getElementById("deleteM");
      var mg = document.getElementById("mgP");
      con.style.display = 'block';
      con1.style.display = 'none'; 
      mg.style.display='none';
      var params = "LH_LAUNDRY=" + second + "&QTY=" + price + "&NUM=" + lastItemNo;
      $.ajax({
         url:url,
         type:"get",
         data: params,
         
         success:function(html){
            $(position).html(html);
         },
         error:function(error){
         console.log(error);
         }
      });
      var arr = [];
      var arrStr;
      
   }
function removeitem(elem, position) {
    $('#item'+elem).remove();
    console.log(elem);
    
   var allData={"elem":(elem-1)};
  // var lastItemNo = $('#one td:last').val;
  // tr태그의 마지막 번째를 구해 id="item"의 형태로 만들어 lastItemNo에 대입
 var lastItemNo = $("#itemadd tr:last").attr("id").replace("item", "");
  
 var con = document.getElementById("addM");
 var con1 = document.getElementById("deleteM");
 var mg = document.getElementById("mgP");
 con.style.display = 'none';
 con1.style.display = 'block'; 
 mg.style.display = 'none'; 
      $.ajax({
          url: './givetakeSelect2',
          type:"get",
          data: allData,

          success:function(html)
          {
             $('#deleteM').html(html);
             console.log(html);
          },
          error:function(error)
          {
             console.log(error);
          }
       });
      
 } 
//매장정보 AJAX
   function AJ1(url, position){   
   var info = document.getElementById("SHOPINFO");
   var review = document.getElementById("REVIEW");
   var pay= document.getElementById("RESERVATION");
   info.style.display = 'block';
   review.style.display = 'none'; 
   pay.style.display='none';
}


//예약하기AJAX
   function AJ2(url, position){   
      
      var userId = $("#userId").val();
      var allData = { "userId":userId};
         var info = document.getElementById("SHOPINFO");
         var review = document.getElementById("REVIEW");
         var pay= document.getElementById("RESERVATION");
         info.style.display = 'none';
         review.style.display = 'none'; 
         pay.style.display='block';
   $.ajax({
         url:url,
         type:"get",
         data: allData,   

         success:function(html){
            $(position).html(html);
         },
         error:function(error){
         console.log(error);
         }
      });
   }
//리뷰AJAX
      function AJ3(url, position){   
      
      var userId = $("#userId").val(); //NLCODE
      var allData = { "userId":userId};
      
      
         var info = document.getElementById("SHOPINFO");
         var review = document.getElementById("REVIEW");
         var pay= document.getElementById("RESERVATION");
         
         info.style.display = 'none';
         review.style.display = 'block'; 
         pay.style.display='none';
      $.ajax({
         url:url,
         type:"get",
         data: allData,   

         success:function(html){
            $(position).html(html);
         },
         error:function(error){
         console.log(error);
         }
      });
   }
</script>
<script type="text/javascript">
 //수거시간 수령방법 날짜
   $(document).ready(function() {
                 
                if("homegive" == "homegive"){
                 
                    $('input:radio[name="serviceType"][value="homegive"]').prop('checked', true);
                    $("select[name='homegiveMainCategory']").val("2").attr("selected", "selected");
                    $( "#viewGiveCategory" ).hide();
                    $( "#viewHomeGiveCategory" ).show();
                }
                 
                $("input[name='serviceType']:radio").change(function () {
                        var serviceType = this.value;
                                         
                        if(serviceType == "homegive"){//스포츠인 경우
                            $( "#viewGiveCategory" ).hide();
                            $( "#viewHomeGiveCategory" ).show();
                        }else if(serviceType == "give"){//공연/전시인 경우
                            $( "#viewGiveCategory" ).show();
                            $( "#viewHomeGiveCategory" ).hide();
                        }
                    });
           });
    </script>
<script type="text/javascript">
            $(document).ready(function() {
                 
                if("hometake" == "hometake"){
                 
                    $('input:radio[name="serviceType2"][value="hometake"]').prop('checked', true);
                    $("select[name='hometakeMainCategory']").val("2").attr("selected", "selected");
                    $( "#viewTakeCategory" ).hide();
                    $( "#viewHomeTakeCategory" ).show();
                }
                 
                $("input[name='serviceType2']:radio").change(function () {
                    var serviceType2 = this.value;
                    
                        if(serviceType2 == "hometake"){
                            $( "#viewTakeCategory" ).hide();
                            $( "#viewHomeTakeCategory" ).show();
                        }else if(serviceType2 == "take"){
                            $( "#viewTakeCategory" ).show();
                            $( "#viewHomeTakeCategory" ).hide();
                        }                                  
                    });
            });
 </script>
</html>