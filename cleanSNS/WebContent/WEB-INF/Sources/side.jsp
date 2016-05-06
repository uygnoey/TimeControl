<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/jquery.popupWindow.js" ></script>
<script type="text/javascript">

/*
	$(".cleanerPopup").popupWindow({
		
		centerScreen:1,
		
		windowURL : 'cleaner.htm',
		windowName : 'cleaner.htm'
		
		
	});	
	
	opener.parent.location.reload();
	window.close();
});


*/

</script>

<div class="side">
	<!--  <div id="divProfile" class="divProfile">
		
		<img src="img/logo.PNG" alt="${name}" style="width:60px; height:60px;" />
		<div class="sideh4" >		
			<a href=""  >${name}</a>
		</div>
	</div> -->
	<div id="divMenu" class="divMenu">
		<ul>
			<li>
			<c:choose>
			<c:when test="${myPhoto != null}">
			<img src="${myPhoto}"  style="width:60px; height:auto;" />
			</c:when>
			<c:otherwise>
			<img src="img/logo.PNG"  style="width:60px; height:auto;" />
			</c:otherwise>
			</c:choose>
			<a href="storyview.htm" >${name}</a>
			</li>
			<li>
				<a href="">
					<span>뉴스피드</span>
				</a>
			</li>
			<li>
				<a href="storymatelist.htm">
					<span>친구보기</span>
				</a>
			</li>
		
			<li>
				<a href="storytimelist.htm">
					<span>타임아웃</span>
				</a>
			</li>
				<li>
<!--  	<a href='cleaner.htm' class="cleanerPopup"><span>클리너</span></a> -->
	
	
	  <a href='#' onClick="window.open('cleaner.htm', 'CLIENT_WINDOW', 
			'resizable=no scrollbars=no width=300 height=210 left=800 top=180')"><span>클리너</span></a> 
		
			
			
		
			</li>
		</ul>
	</div>
</div>