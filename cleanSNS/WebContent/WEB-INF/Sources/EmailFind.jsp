<%@page import="com.team01.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE htm>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="css/content.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">

	
 
 	$(document).ready(function() {

		$("#check").click(function() {

			if ($("#name").val() == '' && $("#password").val() == '') {

				alert("이름, 비밀번호를 입력해주세요");

			} else if ($("#password").val() == '') {

				alert("비밀번호를 입력해주세요.");

			} else if ($("#name").val() == '') {

				alert("이름을 입력해주세요.");

			} else {

	
				var style="";		
				var l_winHeight = $(window).height()/3;
				var l_winWidth = $(window).width()/2;
				style="left="+l_winWidth+",top="+l_winHeight+", width=350,height=150,status=no,toolbar=no, menubar=no, location=no, resizable=no";
		        window.name="parent";	
		        window.open('popup.htm','child',style);
				
		    	//$("#emailform").submit();
				//location.reload(true);
	
		    	}

		});
	});
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
		<br/><br/><br/><br/><br/><br/><br />
		<br/><br/><br/><br/><br/>
		
		<div>
			<form action="" method="post" id="emailform" name="emailform">
				<h1>이메일(E-mail) 찾기</h1>
				<table>
					<tr>
						<td><input type="text" style="width: 300px; height: 60px;"
							id="name" name="name" placeholder="이름(Name)을 입력해주세요." /></td>
					</tr>

					<tr></tr>

					<tr>
						<td><input type="password"
							style="width: 300px; height: 60px;" id="password" name="password"
							placeholder="비밀번호(Password)를 입력해주세요." width="300" height="50" /></td>
					</tr>
				</table>
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 

				<input type="button" id="check" value="확 인" class="btn btn-gray-purple"/> <br />
				<br /> <br />
				<div id="font">
					이메일(E-mail)과 비밀번호(Password)를 모두 분실하였을 경우에는,<br /> 친구에게 회원님의 프로필로 가서
					이름(Name)과 이메일(E-mail)확인을<br /> 요청해주세요. <br /> 다른 사람이 계정을 도용한 것으로
					의심된다면 사이버 수사대의 도움을 받으세요.<br />
				</div>
				<br /> <br />
			</form>
		</div>
	</div>

</body>
</html>