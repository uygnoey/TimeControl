<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>

<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript">

/* 
 $(document).ready(function() {
	
	$("#okBtn").click(function() {	
		$.ajax({
			url : "mateok.htm",
			type : "POST",
			data : "mateCode="+$("#mateCode").val() + "&mateName="+$("#mateName").val(),	
					// data : ajax의 메소드
			success : function(data) {	// data : Ajax 가 성공적으로 처리된 후 받아오는 값.
	
				// JavaScript 로 받을 때에는 무조건 문자열로 받아오게 된다.
				// (넘겨줄 떄 int 형으로 넘겨줬더라도 JavaScript로 받아올 때는 문자열로 받아옴.)
				// 그렇기 떄문에 문자열로 받아온 데이터를 넘버형태로 바꿔줘야 한다.
				// 그 방법은 parseInt()를 사용한다.
				
				parseInt(data);
				var memberCode = parseInt($("#memberCode").val());
				 
				if (data==memberCode){

					$("#popupform").submit();
					
				}
				else
				{
					// alert("비밀번호가 일치하지 않습니다.");
					//$("#memberupdate")[0].reset();	// 비밀번호 쓰는 곳만 리셋.
					//return; 
					
				}	
			}
		})
	})
}) 
 */

 </script>

</head>

<body>
<form action="mateok.htm" id="popupform" method="post" class="popupform">
<div align='center'>

	
		<br/>
		<b>"${mateName }"</b> 님의 친구 요청을<br/>
		수락 하시겠습니까?<br/><br/><br/>
		<input type="hidden" name="flag" id="flag" value="o"/>
	
		<input type="submit" id="okBtn" class="okBtn" value="수 락" onclick="this.form.flag.value='o';" class="btn btn-gray-purple">
			
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
		<input type="submit" id="noBtn" class="noBtn" value="거 절" onclick="this.form.flag.value='x';" class="btn btn-gray-purple"><br/>
		
		<input type="hidden" id="mateCode" name="mateCode" value="${mateCode }"><br/>
		<input type="hidden" id="mateName" name="mateName" value="${mateName }">
		
		
		
		
		
		
</div>			
</form>
<body >
</html>