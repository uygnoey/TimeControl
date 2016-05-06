<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var num=1;


$(document).ready(function(){
	
	list();
});

function list()
{
	var matecode = document.getElementById("mateCode").value;
	var url="mateStoryList.htm";
	
	
	$.post(url,{start:num,mateCode:matecode},function(data)
	{
		
		$("#mylist").append(data);
		num=num+10;		
	
		
	});		
}

$(window).scroll(function(){
	  var dheight = $(document).height();
	  var sheight = $(window).scrollTop() + $(window).height();
	  
	  var count=document.getElementById("count").value;
	
	  if( count>=num &&  num > 1 && dheight == sheight)
	  {	 
		  list();
	  }
});


</script>
</head>
<body>
<input type="hidden" value="${mateCode }" id="mateCode">
<input type="hidden" id="count" value="${count }">
<div id="mylist">
</div>

</body>
</html>