<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>

<script type="text/javascript">


	
 	function popup()
 	{
	
		
		//self.close(); //자식창 닫기
		//document.emailform.submit();//부모창으로 전송
		//location.reload(true);
		
		//0.부모창으로 부터 값 받아와서 hidden에 저장하기
		
		document.getElementById("hiddenEmail").value=window.opener.document.all.email.value;
		document.getElementById("hiddenPassword").value=window.opener.document.all.password.value;
		checkForm.submit();
				
		//1.해당 창이 뜨자마자 emailFormC.java 로 가서 이메일 판단 하기

		//2.emailFormC.java에서 판단 값 받아와서 결과 값 뿌려주기
		
		
 
 	}
</script>
</head>

<body onload="popup()">
<div>
<form action="login.htm" id="checkForm" method="post">

		<br/><br/>
		
		<input type="button" id="checkBtn" value="확인" onclick="self.close();">
		<input type="hidden" id="hiddenEmail" name="hiddenEmail" value="">
		<input type="hidden" id="hiddenPassword" name="hiddenPassword" value="">
</form>
</div>			
<body >
</html>