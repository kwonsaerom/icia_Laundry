<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check</title>
<style>
.iText{width:190px;margin:0 0;padding:10px 12px;border:1px solid #e1e1e1}
.btn{height:15px;padding:8px; border-radius:2px; 
 font-size:14px;font-weight:700;color:#fff;margin-top:10px;text-align:center;
 text-decoration:none; background:#a4a4a4}
 
 @import url(//fonts.googleapis.com/earlyaccess/hanna.css);
 @import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
</style>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<script type="text/javascript">
//자동으로 팝업 창이 닫히게 하는 함수
function closePop(form){
   //form의 target을 부모 창으로 설정함
   form.target = opener.name;
   form.submit();
   
   if(opener!=null){
      opener.insert=null;
      self.close();
   }
}
function enterSubmit(form){
   //엔터키로 같은 동작
   if(event.keyCode==13){
      event.cancelBubble=true;
      closePop(form);
   }
}

</script>
<script>
//이메일 인증
   function eCheck(){
      var form = document.authenform;
      var Num = ${Num}; 
      console.log(Num);
      if(!form.authnum.value){
         alert("인증번호를 입력하세요");
         return false;
      }
      if(form.authnum.value!=Num){
         alert("틀린 인증번호입니다. 인증번호를 다시 입력해주세요.");
         form.authnum.value="";
         return false;
      }
      if(form.authnum.value==Num){
         alert("인증완료");
         //opener.document.join.mailCheck.value="인증완료";
         self.close();
         $(opener.location).attr("href","javascript:show()");
      }
   }
</script>
<body>
<h5 style="margin-left:30%;">인증번호 7자리를 입력하세요</h5><br/>
<div class="container" style="margin:auto;">
   <form method="post" name="authenform" target="JoinMember.jsp">
      <input type="text" style="margin-left:20%" id="authnum" name="authnum" class="iText">
      <a href="#" onclick="eCheck()" class="btn">인증하기</a><br/><br/><br/>
      <a href="#" onclick="back()" class="btn" style="margin-left:40%;">취소</a>
   </form>
</div>
</body>
</html>