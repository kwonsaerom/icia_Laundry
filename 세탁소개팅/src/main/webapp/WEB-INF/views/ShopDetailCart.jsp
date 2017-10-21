<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 --%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<style>
.pick{
	margin-left: 22%;
	width:480px;
}



.sub{
	position:relative;
	right:-255px;
}
.pick select{
height:40px;width:150px;padding:10px;border:1px solid #e1e1e1; font-size: 14px;
	}
.iText{height:34px;margin-bottom:0.5%;width:150px;padding:10px 12px;border:1px solid #e1e1e1}
</style>

</head>
 
<script>
function tname() {
    var selectBox = document.getElementById("p_t_name"); /*  매장코드 변경*/
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var allData={"selectedValue":selectedValue}
    console.log(selectedValue);
    $.ajax({
       url: './shopCart2',
       type:"get",
       data: allData,
       success:function(data)
       {
          console.log(data);
          
          $('#program').html(data);
       },
       error:function(error)
       {
          console.log(error);
       }
    });
}

function tname2(url,position) {
    var selectBox = document.getElementById("program"); /*  셀렉트박스 변경시 name*/
    var selectedValue2 = selectBox.options[selectBox.selectedIndex].value;
    var allData={"selectedValue2":selectedValue2}
    console.log(selectedValue2);
    $.ajax({
       url: url,
       type:"get",
       data: allData,

   	success:function(html){
		$(position).html(html);
		  $(position).html(data);
	},
	error:function(error){
	console.log(error);
	}
});
}
</script>

<body>
   <div class="pick">
      <form name="pickform" method="get">
      
         	대분류 : ${li}
    		소분류 :<select id="program" name="p_name" onchange="javascript:tname2('shopCart3','#program5');"><option value="선택">프로그램 선택</option></select>
    		         ${handle} <a href="javascript:additem('givetakeSelect','#addM')">
         <input type="button" class="sub" id="addItemBtn" value="+"></a>
	  </form>
</div>  ${li2}

</body>
</html>