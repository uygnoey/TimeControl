<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="js/replyUpdate.js"></script>
</head>
<body class="jui">
	<table class="table table-simple" style="width: 325px;">
		<thead>
			<tr>
				<th style="background: #F8FFFF; font-size: 9px; border-bottom: 0px">
					댓글수정
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<form action="replyupdate.htm" id="replyUpdateForm${replyCode}"
						method="post">
						<input type="hidden" name="replyCode" value="${replyCode}" />
						<div class="group group-rect" style="display: inline;">
							<textarea name="replyContent" id="replyContent${replyCode}"
								class="replyContentUpdate input" cols="" rows="1"  style="height: 35px; width: 250px;"></textarea>
							<button id="replySub" class="replySub btn" value="${replyCode}"
								type="button" style="height: 35px;">댓글수정</button>
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
</body>
</html>