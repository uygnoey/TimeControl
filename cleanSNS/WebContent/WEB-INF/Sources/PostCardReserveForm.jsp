<%--예약글 예시폼 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 댓글 내용 체크 --%>
<head>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.popupWindow.js" ></script> 
<script type="text/javascript" src="js/reply.js"></script>

</head>
<%-- 게시글 뷰 --%>
<div id="" class="category1Card" style="background:#E6FFFF">


	<%-- 게시글 카드 --%>
	<table class="table table-simple table-hover" style="background:#B2EBF4">
		<%-- 멤버 이름, 작성일 --%>
		<thead>
			<tr>
				<th colspan="2" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9" >
					<div class="h5"><img src="img/logo.PNG" style="width:30px; height:auto;" /> CleanSNS</div>
					
				</th>
				<th valign="bottom" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;width: 200px">
					<div class="h6">
					<font color="gray"><strong>2시간 후 부터 게시 가능</strong></font><br/>
					</div>
				</th>
				
				<%-- 주희 --%>
				<th valign="bottom" style="padding-left: 70px; background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9">
					<form action="postreservedelete.htm" method="post" id="delForm" name="delForm">						
						<a href="#" onclick=""><i class="icon-unorderedlist" style="font-size: 16px; color: Purple;"></i></a>	
						<a href="#" onclick=""><i class="icon-unorderedlist" style="font-size: 16px; color: black;"></i></a>												
					</form>					
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- 게시글 표시 부분 --%>
			<tr>
				<td colspan="4" style="padding:10px;" >
				<font color="gray">게시글 작성시 "예약" 버튼을 통해 글을 예약 할 수 있습니다. <br/>
				예약 글은 최소 2시간 이후부터 예약 설정이 가능합니다.</font></td>
			</tr>			
			</tbody>
		</table>
		<br />
		<br />

</div>
