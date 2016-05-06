

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>

<script type="text/javascript" src="jui/js/ui/modal.js"></script>
<script type="text/javascript" src="jui/js/uix/windowC.js"></script>
<script type="text/javascript">
jui.ready([ "uix.window" ], function(window) {
	replyEditFormPop = window("#replyEditPopup", { 
		modal: true
	});
});
</script>
</head>
<body class="jui" >
	<div id="container">
		<div id="top" class="top">
			<c:import url="/header.htm" />
		</div>
		<div class="wrap">
			<div id="left" class="left">
				<c:import url="/side.htm" />
			</div>
			<div id="center" class="right">
				<c:import url="/content.htm" />
				<div id="replyEditPopup" class="replyEditPopup msgbox msgbox-detail" style="width: 340px; height: 200px; left: 276px; top: 317px;">
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
			</div>
		</div>
	</div>
</body>
</html>