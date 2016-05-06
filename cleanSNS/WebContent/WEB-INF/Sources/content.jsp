<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 게시글 작성폼 import --%>
<c:import url="/postwriterform.htm"></c:import>

<div id="category">
	<div class="category catLeft">
		<div id="category1Title" class="category1Title">
			<div class="h2" style="display: inline; color:#36D7B7; font-weight:bolder;">${ca1Title}</div>
						
		</div>
		
		<c:set var="ca2Use" value="${ca2Use }"/>	
		
		<c:choose>		
		<c:when test="${ca2Use == 'N'}">
			<%--<c:import url="/allnewsfeed.htm"/>--%>
			<c:import url="/list.htm"/>
			<c:set var="ca2Title" value="카테고리2 사용하기"/> 
		</c:when>
		<c:otherwise>
			<%--<c:import url="/leftnewsfeed.htm"/> --%>
			<c:import url="/list.htm"/>
			<c:set var="ca2Title" value="${ca2Title }"/> 		
		</c:otherwise>
		</c:choose>
			
	</div>
		
	<div class="category catRight">
		<div id="category2Title" class="category2Title">
			<div class="h2" style="display: inline; color:#36D7B7; font-weight:bolder;">
				<c:choose>	
					<c:when test="${ca2Use == 'N'}">
						<font color="gray">  ${ca2Title } </font>
					</c:when>
				<c:otherwise>${ca2Title }</c:otherwise>
				</c:choose>						
			</div>	
		</div>
		<c:choose>		
			<c:when test="${ca2Use == 'Y'}">
				<%--<c:import url="/rightnewsfeed.htm"/>--%>
				<c:import url="/rlist.htm"/>
			</c:when>
			<c:otherwise>
				<%--<c:import url="/newsfeedform.htm"/>--%>
				<c:import url="/rlist.htm"/>
			</c:otherwise>
		</c:choose>
	</div>
</div>