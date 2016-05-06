<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>

<script type="text/javascript">
 	function popup()
 	{	
 		var test = document.getElementById("hidden").value;
 		//alert(test);
		resultform.submit();

 	}
</script>
</head>

<body onload="popup()">
<div>
<form action="materequest.htm" id="resultform" method="post">

		<br/><br/>
		
		<input type="submit" id="checkBtn" value="확인" onclick="self.close();" class="btn btn-gray-purple">
		<input type="text" id="hidden" name="hidden" value="${memberCode }">
</form>
</div>			
<body >
</html>