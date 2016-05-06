<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="css/memberInsert.css">

<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>

<script type="text/javascript" src="js/jquery.popupWindow.js" ></script>

<style type="text/css">
.err
{
   display: none;
   font-size: small;
   color: red;
}
</style>



<script type="text/javascript">

function sessionEmail()
   {
	   var sessionEmail = document.getElementById("sessionEmail").value;
	   if(sessionEmail != "")
	   {
		   alert("이미 로그인 중입니다.");
		   window.close();
	   }
   }
   
$(document).ready(function(){
	
	$("#submitBtn").click(function() {
		 if ($("#email").val() == '') {
			alert("이메일을 입력해야합니다.");
			return;
		} 
		 
		 var name = document.getElementById("name").value;
	      var email = document.getElementById("email").value;
	      var pw = document.getElementById("pw").value;
	      var pwCheck = document.getElementById("pwCheck").value;
	      
	      var nameMsg = document.getElementById("nameMsg");
	      var emailMsg = document.getElementById("emailMsg");
	      var pwMsg = document.getElementById("pwMsg");
	      var pwCheckMsg = document.getElementById("pwCheckMsg");

	      
	      var limitCheck1 = /[A-Za-z가-힣]/;  // 영문 대문자, 소문자, 한글 사용여부
	      
	      var limitCheck2 = /[~!@\#$%<>^&*\()\-=+_\’a-zA-Z0-9]/;   // 특수문자 사용여부, 영문 소문자, 대문자, 숫자 사용여부

	      
	       if(name=="" || !limitCheck1.test(name) || name.length>20 || name.length<2)
	       {
	          nameMsg.style.display = "inline"; 
	           return; 
	       }
	       
	       if(email=="")
	       {
	          emailMsg.style.display = "inline"; 
	           return; 
	       }
	       
	       if(pw=="" || !(limitCheck2.test(pw)) || pw.length<6 || pw.length>30)
	       {
	          pwMsg.style.display = "inline"; 
	           return; 
	       }
	       
	       if(pw != pwCheck)
	       {
	          pwCheckMsg.style.display = "inline"; 
	           return; 
	       }
			
		 
		 
			$.ajax({
				url : "emailcheck.htm",
				type : "GET",
				data : "email="+$("#email").val(),
				success : function(data) //data : 파라미터 값. 여기서는 Ajax.jsp에 있는 ${count}의 값을 받는다.
					{
						if (data == 0) 
						{
							alert("사용가능한 이메일입니다.");
							
							
							
							 $("#memberInsert").submit();
						       
						       alert("       회원가입이 완료되었습니다.\n       이메일 인증을 해주세요!");
						
						       
						} 
						 else 
						{
							alert("이미 존재하는 이메일입니다.");
							 location.replace('memberinsertform.htm');
						} 
				} 
			});
		});

	});


	
</script>
</head>
<body onload="sessionEmail()">
<input type="hidden" value="${sessionEmail }" id="sessionEmail" />
   <div align="right">
       <h1>[회원가입]</h1>
       <form action="memberinsert.htm" method="post" id="memberInsert">
       	
          <table>
             <tr>
                <td style="font-size:12px;" align="right"><font color=#808080>2~20자  한글/영문 대소문자 구분</font></td>
                <td>
                   <input type="text" id="name" name="name" class="input" placeholder="이 름 (Name)">
                </td>
             </tr>
                
             <tr>
                <td></td>                   
                <td><span class="err" id="nameMsg">잘 못 입력하셨습니다.</span></td>
             </tr>
         
             <tr>
                <td></td>
                <td>
                   <input type="text" id="email" name="email" class="input" placeholder="이 메 일 (E-Mail)">
                </td>
             </tr>
             
             <tr>
                <td></td>                   
                <td><span class="err" id="emailMsg">잘 못 입력하셨습니다.</span></td>
             </tr>
             
             <tr>
                <td style="font-size:12px;" align="right"><font color=#808080>6~30자  영문 대소문자, 숫자, 특수문자 사용</font></td>
                <td>
                   <input type="password" id="pw" name="pw" class="input" placeholder="비밀번호 (Password)">
                </td>
             </tr>
             
             <tr>
                <td></td>                   
                <td><span class="err" id="pwMsg">잘 못 입력하셨습니다.</span></td>
             </tr>
             
             <tr>
                <td></td>
                <td>
                   <input type="password" id="pwCheck" name="pwCheck" class="input" placeholder="비밀번호 확인(Password Check)">
                </td>
             </tr>
                
             <tr>
                <td></td>                   
                <td><span class="err" id="pwCheckMsg">비밀번호가 일치하지 않습니다.</span></td>
             </tr>
             
             <tr>
                <td></td>
                <td>
                   <input type="button" value="다시작성" id="resetBtn" class="resetBtn" onclick="location.href='/cleanSNS/memberinsertform.htm'">&nbsp;&nbsp;&nbsp;&nbsp; 
                   <input type="button" value="가입하기" id="submitBtn" class="submitBtn" /> 
                </td>
             </tr>
                
          </table>
          </form>
   </div>
</body>
</html>