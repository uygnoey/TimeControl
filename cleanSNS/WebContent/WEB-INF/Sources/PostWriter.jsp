<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script src="js/postWrite.js"></script>
<script type="text/javascript">
	jui.ready([ "uix.window" ], function(window) {
		pung = window("#pung", {
			width : 310,
			height : 150
		});
	});
	
	function fileSubmit(name) {
		
		document.getElementById("fileName").value = name;	// 사진 경로 표시하기
		
	}
	
	// 펑시간 표시
	/* $(document).ready(function() {
		$("#pungBtn").click(function() {
			if ($("#pungMinute").val() == 0 && $("#pungHour").val() == 0) {
				$("#pungTime").appendTo("");
			} else {
				var pungTime = ($("#pungHour").val() * 60) + $("#pungMinute").val();
				$("#pungTime").appendTo(pungTime);
			}
			
			pung.hide();
		});
	}); */
	
</script>

</head>
<body class="jui">
<%-- 게시글 작성 폼 --%>
	<form action="postwriter.htm" method="post" id="postWriteForm" name="postWriteForm" enctype="multipart/form-data">
		<div id="postWrite" class="divPostInput">
			<div id="postWrite" class="divPostInput group">
				<div id="tag" class="h5">
					Tag. &nbsp; <input type="text" name="tagT" id="tagT"
						class="input input-rect disabled" readonly="readonly"
						style="background: transparent; border: 0px; width: 868px;" /> <input
						type="button" value="태그취소" id="tagCancle"
						class="mateTag btn btn-black-gray btn-flat btn-rect"> <input
						type="hidden" id="tagCount" name="tagCount" value="0">
					<%-- tag된 멤버코드 저장 --%>
					<input type="hidden" id="code1" name="code1" value="default"> <input
						type="hidden" id="code2" name="code2" value="default"> <input
						type="hidden" id="code3" name="code3" value="default"> <input
						type="hidden" id="code4" name="code4" value="default"> <input
						type="hidden" id="code5" name="code5" value="default">

				</div>
				<%-- 게시글 작성 --%>
				<textarea id="area" name="area" class="postInput input" cols="30" rows="5" placeholder="오늘 기분을 글로 표현해보아요 ^ ^"></textarea>
				<%-- 왼쪽 옵션 버튼 --%>
				<div id="leftBtn" class="inline_left postBtn">
					<%-- 친구 태그 --%>
					<input type="button" value="친구태그" id="tagBtn"
						class="mateTag btn btn-black-gray btn-flat btn-rect" name="tagBtn" />
					<%-- 사진 추가 --%>			
					 <input type="file" id="fileupload" name="fileupload" style="position: left;">			

					
				</div>
					
				<%--  오른쪽 옵션 버튼 --%>
				<div id="rightBtn" class="inline_right postBtn">
					<input type="text" id="reserv" name="reserv" readonly="readonly"
						style="width: 130px; border: 0px; color: red; font-size: 12px" />
					<input type="hidden" id="hiddenReserv" name="hiddenReserv" value="default"> 
					<input type="hidden" id="hiddenReservDate" name="hiddenReservDate" value="default">
					<input type="hidden" id="hiddenReservTime" name="hiddenReservTime" value="default"> 
					<input type="hidden" id="hiddenReservYear" name="hiddenReservYear" value="default">
					<input type="hidden" id="hiddenReservMonth"	name="hiddenReservMonth" value="default">
					<span id="pungTime" style="color: red;"></span>
					<%-- 예약 옵션 --%>
					<input type="button" value="예약" id="resertBtn"
						class="reserveOption btn btn-black-gray btn-flat btn-rect"
						name="reserveOption" onclick="" />
					<%-- 펑 옵션 --%>
					<input type="button" onclick="javascript:pung.show();" value="펑옵션"
						id="pungBtn"
						class="pungOption btn btn-black-gray btn-flat btn-rect"
						name="pungOption" />
					<%-- 게시글 작성 버튼 --%>
					<input type="button" value="글작성" id="submiBtn"
						class="postSubmit btn btn-black-gray btn-flat btn-rect"
						name="submiBtn" onclick="submit()" />
				</div>

			</div>

		</div>

		<div id="pung" class="msgbox msgbox-detail" style="left: 300px; top: 70px;">
			<div class="head">[펑 옵션] 게시글 삭제 시간 설정</div>
			<div class="body">
				<div class="h4">자동 삭제될 시간을 설정해주세요.</div>
				<br /> <input type="number" class="input input-small input-black"
					name="pungHour" id="pungHour" min="00" max="23" value="0" />
				시간&nbsp; <input type="number" class="input input-small input-black"
					name="pungMinute" id="pungMinute" min="00" max="50" step="10"
					value="0" /> 분 후 삭제
				<button id="pungBtn" class="btn btn-black-purple btn-flat btn-rect btn-mini"
					onclick="javascript:pung.hide()"  type="button">확인</button>
			</div>
		</div>
	</form>
	

</body>
</html>