<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<div style="font-size:12px; color: #36D7B7; border-top:1px solid #36D7B7; padding-top:5px;margin-top:5px;padding-bottom:5px;margin-bottom:5px">
	나의 친구들
	</div>
<c:choose>
<c:when test="${check==1 }">
<a href="storymatelist.htm">
	<table>	
	<tr>
	<c:choose>
	<c:when test="${count>=4 }">
		<c:forEach var="i" begin="0" end="3" step="1">
		<td>
			<img id="${mateList[i].photoURL }" src="${mateList[i].photoURL }"  style="width:140px;height:170px">
		</td>
		</c:forEach>
		<tr>
		<c:forEach var="i" begin="4" end="${count-1 }" step="1">
		<td>
			<img id="${mateList[i].photoURL }" src="${mateList[i].photoURL }"  style="width:140px;height:170px">
		</td>
		</c:forEach>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="i" begin="0" end="${count-1 }" step="1">
		<td>
			<img id="${mateList[i].photoURL }" src="${mateList[i].photoURL }"  style="width:140px;height:170px">
		</td>
		</c:forEach>
	
	
	</c:otherwise>
	</c:choose>
	
	</tr>	
	</table>
</a>
</c:when>
<c:otherwise>
	<font color="gray">친구 추가를 통해 친구를 추가해 보세요.</font>
</c:otherwise>
</c:choose>

</div>

</body>
</html>