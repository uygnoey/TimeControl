<%--게시 내용 수정완료 폼 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 댓글 내용 체크 --%>
<head>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/lib/jquery-2.0.0.min.js" ></script>
<script type="text/javascript" src="js/json2.js" ></script> 
<script type="text/javascript" src="js/jquery.popupWindow.js" ></script> 
<script type="text/javascript" src="js/reply.js"></script>
<script type="text/javascript">
function deleteSubmit(){	
	
	document.getElementById("delForm").submit();
	
}

function modify(post,code,postname,postmateName,postreserveTime,postreserveDate){	
	
	var mates=$("input[id='mate_"+code+"']");
	var matecodes=$("input[id='hidden_"+code+"']");
	var count=mates.size();
	
	var url="postcardreservemodify.htm";
	var tag="";	
	
	for(var i=0; i<count; i++)
		tag+=mates[i].value+",";
	
	var tagcode="";
	for(var i=0; i<count; i++)
		tagcode+=matecodes[i].value+",";
	
	
	
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
//	alert(photoURL);
	
	if(document.getElementById("postphotoURL_"+code)!=null)	
	{ 		
		 postphotoURL=document.getElementById("postphotoURL_"+code).value;
		 postphotoURL2=postphotoURL.substring(4);
		 postphotoURL="img\\"+postphotoURL2;
	}
//	alert(postphotoURL);
		
	$.post(url,{reserveCode:code,postContent:post,name:postname,mateName:postmateName,reserveTime:postreserveTime,reserveDate:postreserveDate,taglist:tag,taglistcode:tagcode,tagCount:count,photoURL:photoURL,postphotoURL:postphotoURL},function(data){		
		$("#content_"+code).html(data);			
	});		
}

</script>

</head>
<%-- 게시글 뷰 --%>
<div id="form" class="category1Card" style="background:#E6FFFF;padding:0">

	
	<div id="content_${reserveCode}">
	<%-- 게시글 카드 --%>
	<table class="table table-simple table-hover" style="background:#B2EBF4;width:580px;">
		<%-- 멤버 이름, 작성일 --%>
		<thead>
			<tr>
				<th colspan="2" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;" >
					<div class="h5">
					<c:choose>
						<c:when test="${photoURL != \"\"}">
						<img src="${photoURL}"  style="width:30px; height:auto;" />
						<input type="hidden" id="photoURL_${reserveCode}" name="photoURL_${reserveCode}" value="${photoURL }">
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:30px; height:auto;" />
						<input type="hidden" id="photoURL_${reserveCode}" name="photoURL_${reserveCode}" value="${photoURL }">
						</c:otherwise>
					</c:choose>						
					${name} 
					<c:if test="${!empty mateName}">
					<strong> <font color="#078196"><font size="1.5px">&nbsp;from</font> ${mateName }</font></strong>
					</c:if>
					</div>
				
				</th>
				<th valign="bottom" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;width: 200px">
					<div class="h6">
					<font color="red"><strong>${reserveTime} 게시 예정</strong></font><br/>
					${reserveDate}</div>
				</th>
				
				<%-- 주희 --%>
				<th valign="bottom" style="padding-left: 70px; background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9">
									
					<form action="postreservedelete.htm" method="post" id="delForm" name="delForm">						
						<a href="#" onclick="modify('${postContent}','${reserveCode}',' ${name}','${mateName }','${reserveTime}','${reserveDate}');"><i class="icon-unorderedlist" style="font-size: 16px; color: Purple;" ></i></a>	
						<a href="#" onclick="deleteSubmit()"><i class="icon-unorderedlist" style="font-size: 16px; color: black;"></i></a>												
						<input type="hidden" id="delPostCode" name="delPostCode" value="${reserveCode }">
					</form>
			
					
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- 게시글 표시 부분 --%>
			<tr>
				<td colspan="4" style="padding:10px;" >	
						
						${postContent}<br/>
						<c:if test="${postphotoURL  != null}">
						<img src="${postphotoURL }" style="max-width:400px; height:auto;">
						<input type="hidden" id="postphotoURL_${reserveCode}" name="postphotoURL_${reserveCode}" value="${postphotoURL }">
						</c:if>	
						<br/><br/>						
						<strong><font color="#078196" >Tag.	</font></strong>					
						<c:forEach var="mate" items="${fn:split(taglist,',')}">						
							<input type="text" id="mate_${reserveCode}" value="${mate }" style="border:0;width:80px"> 						
								
						</c:forEach>
						<c:forEach var="code" items="${fn:split(taglistcode,',')}">						
							<input type="hidden" id="hidden_${reserveCode}" value="${code }" style="border:0;width:80px"> 						
								
						</c:forEach>
			
				</td>
			</tr>			
			</tbody>
		</table>
		</div>
</div>
