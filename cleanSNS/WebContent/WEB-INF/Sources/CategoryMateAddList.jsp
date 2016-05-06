<%@page import="com.team01.dto.MateDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/story.css" />
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
</head>
<body>
<div>
<c:forEach var="item" items="${list}">
	<table class="mateList">	
		<tr>
		<td width="20px" height="20px" rowspan="2">
			<input type="checkbox" name="chaeckboxGroup" id="chaeckboxGroup" value="${item.name },${item.memberCode }" onclick="checkcount(this);"/>
		</td>
		<td rowspan="2" width="60px">
			<img src="img/logo.PNG" style="width:60px; height:60px;">
		</td>
		<td>	
			${item.name }
		</td> 
		</tr>
		<tr>
		
		<td style="color:gray">${item.email}	
			
		</td>
		</tr>	
	
</table>

</c:forEach>


</div>

</body>
</html>