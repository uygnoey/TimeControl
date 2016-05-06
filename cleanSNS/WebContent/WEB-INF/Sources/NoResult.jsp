<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
</head>
<body>
<div align= 'center'>
<br/>
<%=session.getAttribute("name")%>님의 <br/>
 이메일(E-mail)을 찾지 못하였습니다.<br/>
 이름, 비밀번호를 확인해주세요.<br/>
	<br/>
	<input type="button" id="checkBtn" value="확인" onclick="self.close();" class="btn btn-gray-purple">

</div>
</body>
</html>