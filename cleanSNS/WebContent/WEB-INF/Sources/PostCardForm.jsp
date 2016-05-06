<%--PostCard.jsp
	PostCardC.java -컨트롤러
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>

</head>
<body class="jui">

<%-- 게시글 뷰 --%>
<div id="" class="category1Card">


	<%-- 게시글 카드 --%>
	<table class="table table-simple table-hover">
		<%-- 멤버 이름, 작성일 --%>
		<thead>
			<tr>
				<th colspan="2">
					<div class="h5"><img src="img/logo.PNG" style="width:30px; height:auto;" /> Timeout Sns</div>
				</th>
				<th colspan="2">
					<div class="h6"></div>
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- 게시글 표시 부분 --%>
			<tr>
				<td colspan="4" style="padding:10px; color:gray;">카테고리2를 사용하여 보고자 하는 게시글을 분류하여 볼 수 있습니다. </br>
								카테고리2 분류는 설정에서 가능하며, 카테고리2분류 제목도 구성 가능 합니다.
				</td>
			</tr>
			<%-- 좋아요 싫어요 --%>
			<tr>
				<td><img src="img/like.png" alt="" width="10" height="10" /> 좋아요</td>
				<td>10</td>
				<td><img src="img/dislike.png" alt="" width="10" height="10" /> 싫어요</td>
				<td>2</td>
			</tr>
			<%-- 댓글 달기 --%>
			<tr>
				<td colspan="4">
					<div class="group group-rect" style="display: inline;">
						<input type="text" name="replyContent" id="" class="replyContent input" />
						<a class="btn">댓글달기</a>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<br />
	<br />
</div>
</body>