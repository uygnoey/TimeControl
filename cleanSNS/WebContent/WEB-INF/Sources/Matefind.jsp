<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>


<link rel="stylesheet" href="css/newsfeed.css" /> 
<link rel="stylesheet" href="css/matefind.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/button.js"></script>



<script type="text/javascript">

	 	function mateadd(memberCode)
		{
		 	//var test = document.getElementById("${ memberCode}" );
		 	//alert(memberCode);
		 	//alert("TEST");
			var style="";		
			var l_winHeight = $(window).height()/3;
			var l_winWidth = $(window).width()/2;
			style="left="+l_winWidth+",top="+l_winHeight+", width=350,height=150,status=no,toolbar=no, menubar=no, location=no, resizable=no";
	        window.name="parent";	
	        window.open('matepopup.htm?memberCode='+memberCode,'child',style); 
		}


</script>


</head>
<body class="jui" >

	
<div id="top" class="top">
	<c:import url="/header.htm" />
</div>
<div class="wrap">
<br/><br/><br/>
<div id="home">
	<div id="title">
	<img src="img/search.png" width="50" height="50"/>
	 Search Results for <font size="6.8"><%=session.getAttribute("search") %></font><br/>
	</div>
	
<hr width="1000" size="2"  noshade> 
</div>

<div id="result">
	<form action="" method="post" id="resultform" name="resultform">
	<c:choose>
		<c:when test="${list1[0] != null }">
			<div class="result2">
				<br/>
						
				<table style="margin: 0 auto;">	
				<c:forEach var="Member" items="${list1 }">
				<tr>
				<td align="center" style="width:300px; height:50px;"><font size="7">${Member.name  }</font></td>
				<td	align="center" style="width:250px; height:50px;"><font size="4">${Member.email }</font></td>
				<c:if test="${Member.memberCode==mcode }">
					<td><input type="button" value="본 인" id="mine" onclick="" class="btn btn-gray-purple"></td>	
				</c:if>	
			
					<c:set var="check" value="0" />
	
					<c:forEach var="Mate" items="${list2 }">
						<c:choose>
							<c:when test="${Mate.mateMemberCode == Member.memberCode}">
							<td></td>
							<td><input type="button" value="친  구" id="mate" onclick="" class="btn btn-gray-purple"></td>	
							</c:when>
							
							<c:when test="${Mate.mateMemberCode != Member.memberCode}">
							<c:set var="check" value="${check+1 }" />
							</c:when>				
						</c:choose>	
					</c:forEach>	
					
			
						<c:if test="${check ==size && Member.memberCode!=mcode}">
						<c:set var="memberCode" value="${ Member.memberCode}"  />
					    <td><input type="hidden" value="${ memberCode}"  id="memberCode2_${ memberCode}" name="memberCode2_${ memberCode}"/></td>
						<td><input type="button" value="친구추가" id="mateadd__${ memberCode}" onclick="mateadd('${ memberCode}')" class="btn btn-gray-purple"/></td>	
						
						
						</c:if>						
						
			</c:forEach>		
				</table>	

			</div>
		</c:when>
		
		<c:otherwise>
			<div class="result3">
			검색결과가 없습니다.
			</div>
		</c:otherwise>
	</c:choose>

</form>
</div>

<div id="end">
<hr width="1000" size="2"  noshade> 
</div>

<div align="center"> 
<button type="button" class="home btn btn-gray btn-large" onclick="location.href='newsfeed.htm'"><i class="icon-home" ></i> newsfeed</button>
</div>
</div>
</body>
</html>