<!-- 예약글 수정 페이지 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<link rel="stylesheet" href="css/reservepost.css" />
<script type="text/javascript">


function serchMate()
{
	var style="";		
	var l_winHeight = $(window).height()/3;
	var l_winWidth = $(window).width()/2;

	style="left="+l_winWidth+",top="+l_winHeight+", width=470,height=600,status=no,toolbar=no, menubar=no, location=no, resizable=no";
	window.name="parent";	
	window.open('reservemateadd.htm', 'child', style);
}

function confirm(code,name,mateName,reserveTime,reserveDate,photoURL)
{
	var postContent=document.getElementById("post").value;
	//친구 태그 처리
	
	var mates="";
	var matesCode="";
	
	if(document.getElementById("mate1").value!="")
	{	mates+=document.getElementById("mate1").value+",";
		matesCode+=document.getElementById("hidden1").value+","; 
	}
	                                     
	if(document.getElementById("mate2").value!="")
	{	mates+=document.getElementById("mate2").value+",";
		matesCode+=document.getElementById("hidden2").value+",";
	}
	if(document.getElementById("mate3").value!="")
	{	mates+=document.getElementById("mate3").value+",";
		matesCode+=document.getElementById("hidden3").value+",";
	}
	if(document.getElementById("mate4").value!="")
	{	mates+=document.getElementById("mate4").value+",";
		matesCode+=document.getElementById("hidden4").value+",";
	}
	if(document.getElementById("mate5").value!="")
	{	mates+=document.getElementById("mate5").value+",";
		matesCode+=document.getElementById("hidden5").value+",";
	}

	var photoURL=null;
	var photoURL2=null;
	
	if(document.getElementById("photoURL_"+code)!=null)
	{
		photoURL=document.getElementById("photoURL_"+code).value;
	 	photoURL2=photoURL.substring(4);
	 	photoURL="img\\"+photoURL2;
	}
	
	var postphotoURL=null;
	var postphotoURL2=null;
	//alert(photoURL);
	
	if(document.getElementById("postphotoURL_"+code)!=null)	
	{ 		
		 postphotoURL=document.getElementById("postphotoURL_"+code).value;
		 postphotoURL2=postphotoURL.substring(4);
		 postphotoURL="img\\"+postphotoURL2;
	}

	$.post("myStoryListReserveModify.htm",{reserveCode:code,name:name,mateName:mateName,taglist:mates,taglistcode:matesCode,reserveTime:reserveTime,reserveDate:reserveDate,postContent:postContent,photoURL:photoURL,postphotoURL:postphotoURL},function(data){
		
		$("#modify_"+code).html(data);
		
	});
}

function cancle(code,name,mateName,taglist,taglistcode,reserveTime,reserveDate,postContent)
{
	var photoURL=null;
	var photoURL2=null;
	
	if(document.getElementById("photoURL_"+code)!=null)
	{
		photoURL=document.getElementById("photoURL_"+code).value;
	 	photoURL2=photoURL.substring(4);
	 	photoURL="img\\"+photoURL2;
	}
	
	var postphotoURL=null;
	var postphotoURL2=null;
	alert(photoURL);
	
	if(document.getElementById("postphotoURL_"+code)!=null)	
	{ 		
		 postphotoURL=document.getElementById("postphotoURL_"+code).value;
		 postphotoURL2=postphotoURL.substring(4);
		 postphotoURL="img\\"+postphotoURL2;
	}
	alert(postphotoURL);
	$.post("myStoryListReserveModify.htm",{reserveCode:code,name:name,mateName:mateName,taglist:taglist,taglistcode:taglistcode,reserveTime:reserveTime,reserveDate:reserveDate,postContent:postContent,photoURL:photoURL,postphotoURL:postphotoURL},function(data){
		
		$("#modify_"+code).html(data);
		
	});
}

</script>
</head>
<form id="ModifyForm">
<div id="modify_${reserveCode}">
<table class="table table-simple table-hover" style="background:#B2EBF4;width:580px">
		<%-- 멤버 이름, 작성일 --%>
		<thead>
			<tr>
				<th colspan="2" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;width: 230px" >
					<div class="h5">
					<c:choose>
						<c:when test="${photoURL != null}">
						<img src="${photoURL}"  style="width:30px; height:auto;" />
						<input type="hidden" id="photoURL_${reserveCode}" name="photoURL_${reserveCode}" value="${photoURL }">
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:30px; height:auto;" />
						<input type="hidden" id="photoURL_${reserveCode}" name="photoURL_${reserveCode}" value="${photoURL }">
						</c:otherwise>
					</c:choose>						
					${name}
					<c:if test="${mateName != '' }">
					<strong> <font color="#078196"><font size="1.5px">&nbsp;from</font> ${mateName }</font></strong></c:if></div>
					
				</th>
				<th valign="bottom" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;width: 200px">
					<div class="h6">
					<font color="red"><strong>${reserveTime} 게시 예정</strong></font><br/>
					${reserveDate}</div>
				</th>
				
				<%-- 주희 --%>
				<th colspan="2" valign="bottom" style="padding-left: 70px; background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9">
					
				</th>
				
			</tr>
		</thead>
		<tbody>
		<tr >
		<td colspan="5" rowspan="7">
			<strong><font color="#078196" >Tag.</font></strong>
			<c:if test="${tagCount >0}">
			<c:forEach var="i" items="${fn:split(taglist,',')}" varStatus="num">
			 <input type="text" id="mate${num.count }" value="${i }" style="width:80px;border-top:0;border-left:0;border-right:0;border-bottom:1px solid #4374D9">
			</c:forEach>
			<c:forEach var="i" items="${fn:split(taglistcode,',')}" varStatus="num">
			 <input type="hidden" id="hidden${num.count  }" value="${i }" style="width:80px;border-top:0;border-left:0;border-right:0;border-bottom:1px solid #4374D9">
			</c:forEach>
			</c:if>
			
			<!-- 친구 태그가 5명이하일 때 처리 -->
			<c:if test="${tagCount < 5}">
				<c:forEach var="i" begin="${tagCount +1}" end="5" step="1">
				 <input type="text" id="mate${i }" value="" style="width:80px;border-top:0;border-left:0;border-right:0;border-bottom:1px solid #4374D9">
			 	<input type="hidden" id="hidden${i }" value="" style="width:80px;border-top:0;border-left:0;border-right:0;border-bottom:1px solid #4374D9">
				
				</c:forEach>
			</c:if>
			<br/><br/>
			
			<textarea id="post" rows="5" cols="90">${postContent}	</textarea>	
			<c:if test="${postphotoURL  != \"\"}">
			<img src="${postphotoURL }" style="max-width:400px; height:auto;">
			<input type="hidden" id="postphotoURL_${reserveCode}" name="postphotoURL_${reserveCode}" value="${postphotoURL }">
						
					
			</c:if>	
			<br/><br/>
			<table>
			<tr>
			<td style="width:500px">
			<input type="button" value="친구태그" onclick="serchMate();"style="background:#4374D9"class="postSubmit btn btn-black-gray btn-flat btn-rect" ></td>
			<td>
			<input type="button" value="확인" onclick="confirm('${reserveCode}','${name}','${mateName }','${reserveTime}','${reserveDate}','${photoURL }')" style="float:right;background:#4374D9"class="postSubmit btn btn-black-gray btn-flat btn-rect" ></td>
			<td>
			<input type="button" value="취소" onclick="cancle('${reserveCode}','${name}','${mateName }','${taglist }','${taglistcode }','${reserveTime}','${reserveDate}','${postContent}')"  style="float:right;background:#4374D9"class="postSubmit btn btn-black-gray btn-flat btn-rect"  ></td>
			</tr>
			</table>
		</td>
	</tr>
</tbody>
</table>
</div>
<br/><br/>

</form>
