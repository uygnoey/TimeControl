<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>

</head>

<body>
<div align='center'>
<br/>
	<%=session.getAttribute("name")%>님의 이메일(E-mail)은 <br/>
	<b><font color=#0100FF><%=session.getAttribute("email")%></font></b> 입니다.
	<br/><br/>
	<input type="button" id="checkBtn" value="확 인" onclick="self.close(); " class="btn btn-gray-purple">
		<br/><br/>
		

</div>			
<body >
</html>