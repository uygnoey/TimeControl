<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<c:if test="${count -1 ==0 }"><%--펑이 하나도 없을 경우 기본 폼으로 보여주기 --%>
<c:import url="/mystorypungform.htm"/>


</c:if>
</body>
</html>