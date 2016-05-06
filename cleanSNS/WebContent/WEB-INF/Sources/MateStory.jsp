<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="jui/js/ui/layout.js"></script>

<style type="text/css">
html{padding:0px; overflow-y:scroll;} /* html에만 스크롤을 걸어준다. */
</style>

<script type="text/javascript">
function enlargeImage1()
{
	image1.height="300" // 커졌을때 이미지 크기
}
function dropImage1()
{
	image1.height="129" // 보통,작아졌을때 이미지 크기
}

</script>


</head>
<body class="jui" >

	
<div id="top" class="top">
	<c:import url="/header.htm" />
</div>
		
<div class="wrap">	
		<div class="story">
			<form action="" method="post">
				<table >
					<tr>
					<td style="width:30px;"></td>

					<td align="left" >
					<c:choose>
						<c:when test="${dto.photoURL != null}">
						<img src="${dto.photoURL}"  style="width:180px; height:auto;" />
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:180px; height:auto;" />
						</c:otherwise>
					</c:choose>							
					</td>
					<td align="left" style="width:230px; color:white; font-weight:bold"><br/><br/><br/><br/><br/>&nbsp;${dto.name}</td>

					</tr>
				</table>
				<table class="storyTable">
					<tr>
						<td align="left" width=950px>
							<a href="newsfeed.htm">뉴스피드</a>
						</td> 
						<td align="center">
							<a href="matestory.htm?mateCode=${dto.mateMemberCode }">스토리</a>
						</td> 
						<td align="center" >
							<a href="matestoryphoto.htm?mateCode=${dto.mateMemberCode }">사진</a>
						</td>
						<td align="center" >
							<a href="matestorymate.htm?mateCode=${dto.mateMemberCode }">친구</a>
						</td>
					</tr> 
				</table> 

			</form>
		</div>

		<div id="myList" class="myStrotyList">
			
			<div class="category catLeft">
			<div id="storyTitle" class="storyTitle">
				<div class="h2" style="display: inline; color:#36D7B7; font-weight:bolder;">
				${dto.name } 스토리
				</div>
			</div>
				 <c:import url="/mateStory.htm?mateCode=${dto.mateMemberCode }"/> 
			</div>
		</div>
		
		<div id="reserveList" class="myStrotyList">
			<div class="category catRight">
			<div id="storyTitle2" class="storyTitle2">
				<div class="h2" style="display: inline; color:#6B66FF; font-weight:bolder;">
				
				</div>
			</div>
				<%-- <div style="padding-top:30px">
				<c:import url=""/> 친구 최근 사진
				</div>
				<div >
				<c:import url=""/> 친구 친구들
				</div> --%>
			
			</div>
		</div>

</div>

</body>
</html>