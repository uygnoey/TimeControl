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
<script type="text/javascript" src="jui/js/ui/modal.js"></script>
<script type="text/javascript" src="jui/js/uix/window.js"></script>

<style type="text/css">
html{padding:0px; overflow-y:scroll;} /* html에만 스크롤을 걸어준다. */
</style>

<script type="text/javascript">
jui.ready([ "uix.window" ], function(window) {
	myPhoto = window("#myPhoto", { 
		width: 340, 
		height: 410, 
		left: 500, 
		top: 100
	});
	replyEditFormPop = window("#replyEditPopup", { 
		width: 340,
		height: 200,
		left: 276,
		top: 317,
		modal: true
	});
});
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
							<img src="${myPhoto}" style="width:180px; height:auto;" onclick="javascript:myPhoto.show();"/>
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
				<div class="h2" style="display: inline; color:#36D7B7; font-weight:bolder;">
				나의 스토리
				</div>
			</div>
				<c:import url="/myStory.htm"/>
			</div>
		</div>
		
		<div id="reserveList" class="myStrotyList">
			<div class="category catRight">
			<div id="storyTitle2" class="storyTitle2">
				<div class="h2" style="display: inline; color:#6B66FF; font-weight:bolder;">
				
				</div>
			</div>
				<div style="padding-top:30px">
				<c:import url="/mystoryphoto.htm"/> <%--내 최근 사진 --%>
				</div>
				<div >
				 <c:import url="/mystoryfriend.htm"/> <%--내 친구들 --%>
				</div>
			
			</div>
		</div>

</div>


</body>


<div id="myPhoto" class="msgbox msgbox-detail">
	<div class="head"><div class="detail"></div>
	<a href="#" class='close'><i class='icon-exit icon-gray'></i></a>
	</div>
		<div class="body">
			<img src="${myPhoto}"  style="width:300px; height:auto;" />
		</div>
</div>

<%-- 댓글 수정 폼 --%>
<div id="replyEditPopup" class="replyEditPopup msgbox msgbox-detail"
	style="width: 340px; height: 200px; left: 276px; top: 317px;">
	<div class="head">
		댓글 수정 <a href="#" class='close'><i class='icon-exit icon-gray'></i></a>
	</div>
	<div class="body">
		<textarea name="replyContentEdit" id="replyContentEdit" cols="55"
			rows="5"></textarea>
		<button type="button" id="replyEditSubmit"
			class="btn btn-gray-black btn-flat btn-rect">수정</button>
	</div>
</div>

</html>