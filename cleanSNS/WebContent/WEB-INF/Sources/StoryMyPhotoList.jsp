<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function zoom(url)
{
	var style="";		
	var l_winHeight = $(window).height()/3;
	var l_winWidth = $(window).width()/4;
	
	var img=new Image();
	img.src=("img\\"+url);	

	style="left="+l_winWidth+",top="+l_winHeight+", width="+(img.width+20)+",height="+(img.height+20)+",status=no,toolbar=no, menubar=no, location=no, resizable=no";
	window.name="parent";	
	window.open('storymyphotozoom.htm?url='+url, 'child', style);
}


</script>

</head>
<body>
<table>
<c:set var="photo" value="${myPhotoList }"></c:set>
<c:forEach var="i" begin="0" end="${nowcount-1}" step="5">		
	<tr>	
		<c:forEach var="j" begin="0" end="4" step="1" >		
			<c:if test="${myPhotoList[i+j].photoURL != null }">
			
			<td style="border:1px solid gray;width:150px;height:200px;">
				<img id="${myPhotoList[i+j].photoURL }" src="${myPhotoList[i+j].photoURL }" onclick="zoom('${myPhotoList[i+j].photoURL.substring(4) }')" style="width:235px;height:200px">

			
			</td>
			</c:if>
		</c:forEach>	
	</tr>
</c:forEach>
</table>

</body>
</html>