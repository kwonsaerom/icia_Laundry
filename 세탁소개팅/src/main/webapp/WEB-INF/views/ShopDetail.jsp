<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- JAVASCRIPTS --> 
<script src="resources/bootstrap/layout/scripts/jquery.min.js"></script> 
<script src="resources/bootstrap/layout/scripts/supersized/supersized.min.js"></script> 

<link href="resources\bootstrap\layout\styles\layout.css" rel="stylesheet" type="text/css" media="all">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=99e91fbe1b2c343821dfa986b88cb1b6&libraries=services,clusterer,drawing"></script>
<style>
 @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
.tab{
width:800px;

}


#header2{
	width:700px;
	margin-left: 22%;
}
#SHOPINFO{
	margin-left: 21%;
}

#nn{
	width:700px;
	margin-left: 22%;
	margin-bottom: 1%;
	text-align: center;
}
.printM{
	width:500px;
	margin-left: 10%;
	margin-top: 1%;
}
.sub2{
	width: 33%;
}
.selectP{
	margin-left: 22%;
	 width: 710px;
}
#md {
    border: 1px solid #ddd;
    width: 710px;
    margin-left: 22%;
}

#su {
    position: relative;
    right: -430px;
    top: -30px;
}
#dasi{
	margin-left:50%;
}
#gt {
    position: relative;
    right: -278px;
    width: 710px;
    border: 1px solid #ddd;
    height: 500px;
}
#mgSubmit {
    position: relative;
    right: 100px;
    margin-left: 100px;
}
#test{
    position: relative;
    width: 350px;
    right: -350px;
	top:-270px;
}

}
#test2{
width:350px;
}

 #ah{
margin-left:-53%;
}
 
#givatakeBtn {
    margin-left: -36%;
}

#printR{
width:700px;
position:relative;
right:-300px;
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
    <a href="./PersonMainMV">  <img src="resources\bootstrap\images\img_t21_006.jpg" style="margin-left:9%; margin-top:2%; margin-bottom:2%;"/></a>
    </div>
    <nav id="mainav">
      <ul class="clear">
        <li><a href="./HomeInfoMV2">홈페이지소개</a></li>
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
               <li><a href="./requestCheck">1:1문의</a></li>
               <li><a href="./coinShopMm">자주묻는 질문</a></li>
            </ul>
        </li>
        <li><a href="./NoticeManage2">공지사항</a>
      </ul>
    </nav>
  </header>
</div>
<!-- asdsad -->

   <div id="header2">
      ${LLIST} <input id="userId" type="hidden"
         value="${NLCODE}"> 
   </div>
   
   
   
   
   
   
   

   <input type="hidden" id="pointtotal" class="pointtotal" name="pointtotal" value="${pointtotal}">
  <div id="nn">
   <table id="nn2">
    <tr><td class="sub2"><a href="javascript:AJ1('shopInfo','#printI')" id="info2">주요정보</a></td>
       <td class="sub2"><a href="javascript:AJ2('shopCart','#printM')" id="info">예약하기</a></td>
        <td class="sub2"><a href="javascript:AJ3('shopReview','#printR')" id="info3">리뷰</a></td></tr>
   </table>
   </div>


   <div id="REVIEW"> 
   		<div id="printR"></div>  
   </div>



   <!-- 매장정보 DIV -->
   <div id="SHOPINFO">
   <div id="map" style="width:730px;height:380px;position:relative;overflow:hidden;"></div> 
        <div id="map2">${nInfo }
        
        </div>
    
      <!-- <div id="printI"></div> -->
   
   </div>

   <!--예약하기 DIV-->
   <div id="RESERVATION" style="display:none">
      <div id="printM"></div>
      <!--결제창 AJAX  -->

      <div class="selectP" style="display: none">
         <!--none 숨기기 block 보이기 -->
     		  <span id="su"> 수량<input type="number" name="price" width="1px"></span> <br><br><br>
  

         <form action="possibleItem" name="HandleForm" id="HandleForm">
            <table id="itemadd">
               <tr id="item1">
                  <td name="one1">품목</td>
                  <td name="two1">종류</td>
                  <td name="three1">수량</td>
                  <td>삭제</td>
               </tr>
            </table>
            <input type="hidden" id="m" value="0">
         </form>
      </div>
            <a href="./shopDetail?nl_code=${NLCODE}">
            <input type="button" class="sub" id="dasi" value="다시예약하기"></a><br><br>

      <div id="program5"></div>
    
   <div id="md"> 
      <div id="addM" class="addM"></div>
      <!-- 추가한 품목 가격 -->
      <div id="deleteM" class="addM"></div>
      <!-- 마이너스 품목 가격-->
      <br>
      <div class="mgPoint" style="display: none">보유마일리지:${mg}</div><br><br>
         <input type="hidden" value="${mg}" id="allmg" class="allmg"> 
      <div class="mg" style="display: none">
         마일리지사용:<input type="text" id="mg" name="mg" placeholder="마일리지입력"
            value="0">
             <a href="javascript:mgSubmit('mgSubmit','#mgP')"><input
         type="button" class="sub" id="mgSubmit" value="마일리지적용" style="display: none"></a>
            
      </div>
      <div id="mgP" class="mgP"></div>
      <!--마일리지 적용후 총가격-->
      
    <br> 
         
         
       </div>  
       
       <div id="gt">
         <input type="button" class="sub" id="givatakeBtn" value="수거방법선택" style="display: none">

      <br> <br> <input type="hidden" value="${timeover}" />
    
      <div class="gtSelect" style="display: none">
         <form name="gtForm">
         <div id="test2">
            수거방법/시간: <input type="radio" name="serviceType" value="give" checked><span>
               수거</span> <input type="radio" name="serviceType" value="homegive"><span>방문수거</span>
            <br> 날짜: <input type="datepicker" id="date1" name="date1"
               placeholder="날짜를 입력하세요."> <br> <br> <span
               id="viewGiveCategory"> <select
               class="form-control giveMainCategory" name="giveMainCategory"
               id="give2" style="width: 200px" size=10>
                  <option disabled="disabled">집으로 찾아오는 수거</option> ${time}
            </select>
            </span> <span id="viewHomeGiveCategory" style="display: none"> <select
               class="form-control homegiveMainCategory"
               name="homegiveMainCategory" id="give2" style="width: 200px" size=10>
                  <option disabled=”disabled”>세탁소 찾아가서 맡김</option> ${time}
            </select>
            </span> <br> <br>
            </div>
            <!--수령-->
            <div id="test">
            수령방법/시간: <input type="radio" name="serviceType2" value="take"
               checked><span> 수령</span> <input type="radio"
               name="serviceType2" value="hometake"><span>방문수령</span> <br>
            날짜: <input type="datepicker" id="date2" name="date2"
               placeholder="날짜를 입력하세요."> <br> <br> <span
               id="viewTakeCategory"> <select
               class="form-control takeMainCategory" name="takeMainCategory"
               id="take2" style="width: 200px" size=10>
                  <option disabled=”disabled”>세탁소에서 배달</option> ${time}
            </select>
            </span> <span id="viewHomeTakeCategory" style="display: none"> <select
               class="form-control hometakeMainCategory"
               name="hometakeMainCategory" id="take2" style="width: 200px" size=10>
                  <option disabled=”disabled”>세탁소 방문해서 받기</option> ${time}
            </select>
            </span> <input type="button" onclick="addHandle()" id="ah" class="sub" value="결제하기" />
            </div>
		            
         </form>
      </div>
   </div>
   </div>
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
     <ul class="nospace">
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
      
         geocoder.addressSearch('${laundry.getNl_address()}', function(result, status) {
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
                     content: '<div style="width:150px;text-align:center;padding:6px 0;">${laundry.getNl_name()}</div>'
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
         buttonImage: "/resources/images/date2.png",
         buttonImageOnly: true,
         beforeShow: function(input, inst){
         //현재값 저장 input parameter 클릭했을때
        //    alert("지금값");
         },
         //$("img.ui-datepicker-trigger").attr("style"," width='50' height='50'"); //이미지버튼 style적용
         
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
         buttonImage: "/resources/images/date2.png",
         
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
</script>


<script>
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
    
 console.log(lastItemNo);
 
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