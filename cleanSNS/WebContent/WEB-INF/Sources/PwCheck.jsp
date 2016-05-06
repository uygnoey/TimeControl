<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//int memberCode = request.getParameter("memberCode");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/story.css" />
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="js/PwCheckAjax.js"></script>

<style type="text/css">
.err
{
	display: none;
	font-size: small;
	color: red;
}

html{padding:0px; overflow-y:scroll;} /* html에만 스크롤을 걸어준다. */
</style>

<script type="text/javascript">

/* 	function check() 
	{ 
		var pw = document.getElementById("pw").value;
		var memberCode = document.getElementById("memberCode").value;
		
		if(pw != memberCode)
		{
			alert("비밀번호가 일치하지 않습니다.");
			location.replace('pwcheckform.htm');
		}
		else
		{
			location.replace('memberupdateform.htm');
		}
	} */

</script>

</head>
<body class="jui" >

<div>

	<div id="top" class="top">
		<c:import url="/header.htm" />
	</div>
	
<div class="wrap">	
		<div class="story">
			<form action="pwcheckform.htm" method="post">
				<table >
					<tr>
					<td style="width:30px;"></td>
					<td align="left" >
					<c:choose>
						<c:when test="${myPhoto != null}">
						<img src="${myPhoto}"  style="width:180px; height:auto;" />
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:180px; height:auto;" />
						</c:otherwise>
					</c:choose>			
					</td>
					<td align="left" style="width:230px; color:white; font-weight:bold"><br/><br/><br/><br/><br/>&nbsp;${name}</td>
						
					<td align="right" style="width:800px;"><br/><br/><br/><br/><br/><input type="submit" value="정보 업데이트" id="submitBtn" class="reserveOption btn btn-black-gray btn-flat btn-rect" ></td>
					</tr>
				</table>
				<table class="storyTable">
					<tr>
						<td align="left" width=950px>
							<a href="newsfeed.htm">뉴스피드</a>
						</td> 
						<td align="center">
							<a href="storyview.htm">스토리</a>
						</td> 
						<td align="center" >
							<a href="">사진</a>
						</td>
						<td align="center" >
							<a href="">친구</a>
						</td>
						<td align="center" >
							<a href="">예약게시글</a>
						</td>
					</tr> 
				</table> 
			</form>

		</div>
	    
		
</div>
	<br/>
	
	<div class="letter">
		<div >
			<table style="padding-top:5px">
			<tr>
			<td width="1200px">
			<font color="white"><strong>정보업데이트</strong></font>
			</td>		
			</tr>
			</table>
		</div>	
	</div>
	
	<div class="bottom"> 
		<form action="memberupdateform.htm" method="post" id="memberupdate">
		<input type="hidden" value="${memberCode }" id="memberCode" />
		<input type="hidden" value="${email }" id="email" />
		<br/><br/><br/><br/>
			<table style="margin: 0 auto;">
				<tr>
					<td>${name}님, 비밀번호를 입력해주세요.</td>
				</tr>
			</table>
			<table style="margin: 0 auto; margin-top: 30px;">
				<tr>
					<td><img src="img/login1.png" width="50" height="50"/></td>
					<td><input type="password" style="width:250px; height:50px;" id="pw" name="pw" placeholder="비밀번호 입력"></td>
					<td><input type="button" value="확인" id="checkBtn" name="checkBtn" style="width:50px; height:50px;" class="reserveOption btn btn-black-gray btn-flat btn-rect" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	
</div>

</body>
</html>