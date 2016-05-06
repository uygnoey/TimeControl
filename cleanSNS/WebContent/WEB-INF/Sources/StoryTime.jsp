<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

						
					<td align="right" style="width:800px;"><br/><br/><br/><br/><br/><input type="submit" value="정보 업데이트" id="submitBtn" style="border:1px solid #36D7B7" class="reserveOption btn btn-black-gray btn-flat btn-rect" ></td>
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
							<a href="storymyphoto.htm">사진</a>
						</td>
						<td align="center" >
							<a href="storymatelist.htm">친구</a>
						</td>
						<td align="center" >
							<a href="storytimelist.htm">타임게시글</a>
						</td>
					</tr> 
				</table> 

			</form>
		</div>
		
	<div id="myList" class="myStrotyList">
			
			<div class="category catLeft">
			<div id="storyTitle" class="storyTitle">
				<div class="h2" style="display: inline; color:#6B66FF; font-weight:bolder;">
				예약 게시글
				</div>
			</div>
				<c:set var="count" value="${rcount}"></c:set>
				<c:choose>
				<c:when test="${rcount >= 1 }">
					<c:import url="/myStoryreserve.htm"/>
				</c:when>
				<c:otherwise>
				<c:import url="/myStoryreserveform.htm"/>
				
				</c:otherwise>
				</c:choose>
			</div>
	</div>
	<div id="reserveList" class="myStrotyList">
			<div class="category catRight">
			<div id="storyTitle2" class="storyTitle2">
				<div class="h2" style="display: inline; color:#6B66FF; font-weight:bolder;">
					펑 게시글
				</div>				
			</div>
				<c:set var="count" value="${pcount}"></c:set>
				<c:choose>
				<c:when test="${pcount >= 1 }">
					<c:import url="/mystorypunglist.htm"/>
				</c:when>
				<c:otherwise>
				<c:import url="/mystorypungform.htm"/>
				
				</c:otherwise>
				</c:choose>
			</div>
	</div>
</div>
</body>
</html>