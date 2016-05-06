<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>TimeOut SNS</title>
<link rel="stylesheet" href="jui/jui.min.css" />

<script type="text/javascript">

	function cleaner()
	{
		cleanerform.submit();
		alert("클리너가 완료 되었습니다.");
		window.close();

	}


</script>
</head>

<body>

<form action="cleanerform.htm" method="post" id="cleanerform">
<div align='center'>

	<input type="radio" name="radio" value="1" checked> 1년 이상 게시글 삭제<br/><br/>
	<input type="radio" name="radio" value="2"> 2년 이상 게시글 삭제<br/><br/>
	<input type="radio" name="radio" value="3"> 3년 이상 게시글 삭제<br/><br/>
	<input type="radio" name="radio" value="4"> 전체 게시글 삭제<br/><br/>
	<br/>
	
	<input type="button" value="취 소" id="cancel" onClick="javascript:opener.location.href='newsfeed.htm';self.close();"
			class="btn btn-gray-purple">
   &nbsp;&nbsp;&nbsp;&nbsp;	
	<input type="button" value="확 인" id="ok" onClick="cleaner()" class="btn btn-gray-purple">
	
	
<!-- 	<input type="button" value="취소" id="cancel" onclick="location.href='newsfeed.htm'">&nbsp;&nbsp; -->
<!-- 	<input type="button" value="확 인" id="cleanerBtn" onclick="cleaner()"> -->


</div>
</form>

</body>
</html>
