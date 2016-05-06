
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
var num2 = 1; 
var maxcount2=0;

$(document).ready(function(){

	test2();

});

function test2(){ 
		
	var url="rightnewsfeed.htm";
	maxcount2=document.getElementById("rcount").value;
	
	if('<%=session.getAttribute("ca2Use")%>'=="N")
	{		
		url="newsfeedform.htm";
	}	
	
	$.post(url, { start : num2}, function(data) 
			
			{
				
				$("#mark2").append(data);
				num2=num2+10;
			});

}

 $(window).scroll(function(){
  var dheight = $(document).height();
  var sheight = $(window).scrollTop() + $(window).height();
  
  
  if(maxcount2>=num2 && num2 > 1 && dheight == sheight && '<%=session.getAttribute("ca2Use")%>'=="Y")
  {	 
	  test2();
  }
 });


</script>


</head>

<body>
<input type="hidden" id="rcount" value="${rcount }">
<div id="mark2"></div>
</body>
