<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var num2=1;


$(document).ready(function(){
	
	list2();
});

function list2()
{
	var url="myStoryListReserve.htm";
	
	
	$.post(url,{start:num2},function(data)
	{
		
		$("#myreserve").append(data);
		num2=num2+10;		
	
		
	});		
}

$(window).scroll(function(){
	  var dheight = $(document).height();
	  var sheight = $(window).scrollTop() + $(window).height();
	  
	  var count=document.getElementById("count2").value;
	
	  if( count>=num2 &&  num2 > 1 && dheight == sheight)
	  {	 
		  list2();
	  }
});


</script>
</head>
<body>
<input type="hidden" id="count2" value="${count }">
<div id="myreserve">
</div>

</body>
</html>