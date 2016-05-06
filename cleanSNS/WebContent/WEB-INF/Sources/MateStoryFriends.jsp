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
		
		<%-- 친구 스토리의 친구List View --%>
		<div class="form1">
	
	<div style="padding-top:30px">
		
		<div class="h2" style="color:#36D7B7; font-weight:bolder;">
		Friends List <strong style="float: right;">총&ensp;
		<c:choose>
			<c:when test="${dto.openLimitedCode == 0 }">${count }</c:when>
			<c:otherwise>0</c:otherwise>
		</c:choose>
		명&ensp;</strong>
		</div>
	  	 
	</div>
	<div id="photo" style="border-top:1px solid #36D7B7"></div>
	<br/>
	<c:if test="${dto.openLimitedCode == 0 }">
		<table id="listTable" class="center">
			<c:forEach var="MateInfoDTO" items="${mateList }">
				<tr>
					<td style="width: 200px; height:60px;">
					
					<c:choose>
					<c:when test="${MateInfoDTO.photoURL != null}">
					<img src="${MateInfoDTO.photoURL}"  style="width: 55px; height:55;" />
					</c:when>
					<c:otherwise>
					<img src="img/logo.PNG"  style="width:55px; height:auto;" />
					</c:otherwise>
					</c:choose>						
					
					</td>
					<%-- 이름 클릭하면 친구스토리로 이동(추가사항) --%>
					<td style="width: 150px; height:100;"><a href=""><b>${MateInfoDTO.name }</b></a></td>
						
				</tr>
			</c:forEach>
		</table>
	</c:if>	
	</div>
		
</div>

</body>
</html>