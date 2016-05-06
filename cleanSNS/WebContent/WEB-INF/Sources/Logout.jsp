<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>
<script type="text/javascript">


</script>

</head>
<body class="jui">
<div id="top" class="top">

		<%-- 사이트 로고 표시 --%>
		<div id="divLogo" class="divLogo">
			<a href=""><img src="img/logo.png" /></a>
		</div>
		
</div>	

<div id="category" class="category">	
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/>
<h1><center>로그아웃 성공완료 되었습니다.</center></h1>
<a href='loginform.htm'><h2><center>로그인 창으로 돌아가기</center></h2></a>
</div>

</body>
</html>