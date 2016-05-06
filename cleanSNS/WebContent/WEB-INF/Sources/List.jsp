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

<script type="text/javascript">
var num = 1; 
var maxcount=0;

$(document).ready(function(){

	test();

});

function test(){ 
		
	var url="leftnewsfeed.htm";
	maxcount=document.getElementById("lcount").value;
	
	if('<%=session.getAttribute("ca2Use")%>'=="N")
	{
		url="allnewsfeed.htm";
		maxcount=document.getElementById("count").value;
	}
	
	$.post(url, { start : num}, function(data) 
			
			{
				$(data).appendTo('mark');
				num=num+10;
			});

}

 $(window).scroll(function(){
  var dheight = $(document).height();
  var sheight = $(window).scrollTop() + $(window).height();
  
  if(maxcount>=num && num > 1 && dheight == sheight){
	  test();
  }
 });


</script>


</head>
<body>
<input type="hidden" id="count" value="${count }">
<input type="hidden" id="lcount" value="${lcount }">
<mark></mark>
</body>
