<%--게시 예약글  첫 폼 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 댓글 내용 체크 --%>
<head>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script  type="text/javascript" src="jui/lib/jquery-2.0.0.min.js" ></script>
<script type="text/javascript" src="js/json2.js" ></script> 
<script type="text/javascript" src="js/jquery.popupWindow.js" ></script> 
<script type="text/javascript" src="js/reply.js"></script>
<script type="text/javascript">
function deleteSubmit2(code,count){	

	if(confirm("정말 삭제하시겠습니까?"))
	{	
		
		$.post("postreservedelete.htm",{delPostCode:code,count:count},function(data){
			$("#content_"+code).html(data);
			
		});
	}	
}

function modify2(photoURL,post,code,postname,postmateName,postDate,postPhotoURL,pungCode){	
	


	var mates=$("input[id='mate2_"+code+"']");
	var matecodes=$("input[id='hidden2_"+code+"']");
	var count=mates.size();
	
	
	var url="mystorypungmodify.htm";
	var tag="";	
	
	for(var i=0; i<count; i++)
	{	tag+=mates[i].value+",";
	
	}

	var tagcode="";
	for(var i=0; i<count; i++)
	{	tagcode+=matecodes[i].value+",";

	}
	var photoURL2="";
	
	if(photoURL.length>0)
	{
		photoURL2=photoURL.substring(3);
		photoURL="img\\"+photoURL2;
	}	
	
	$.post(url,{postCode:code,postContent:post,name:postname,mateName:postmateName,postDate:postDate,taglist:tag,taglistcode:tagcode,tagCount:count,photoURL:photoURL,postPhotoURL:postPhotoURL,pungCode:pungCode},function(data){		
		$("#content2_"+code).html(data);			
	});	
	

}

</script>

</head>
<%-- 게시글 뷰 --%>
<div id="form" class="category1Card" style="background:#E6FFFF">

	
	<div id="content2_${postCode}">		
	<%-- 게시글 카드 --%>
	<table class="table table-simple table-hover" style="background:#B2EBF4">
		<%-- 멤버 이름, 작성일 --%>
		<thead>
			<tr>
				<th colspan="2" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9" >
					<div class="h5">
					<c:choose>
						<c:when test="${photoURL != null}">
						<img src="${photoURL}"  style="width:30px; height:auto;" />
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:30px; height:auto;" />
						</c:otherwise>
					</c:choose>		
					
					
					${name}
					<c:if test="${mateName != '' }">
					<strong> <font color="#078196"><font size="1.5px">&nbsp;from</font> ${mateName }</font></strong></c:if></div>
					
				</th>
				<th valign="bottom" style="background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9;width: 200px">
					<div class="h6">
					${postDate} <font color="red"><strong>${pungCode} 분 후 펑예정</strong></font> </div>
				</th>
				
				<%-- 주희 --%>
				<th valign="bottom" style="padding-left: 70px; background:#B2CCFF; border-top:1px solid #4374D9;border-bottom:1px solid #4374D9">
									
					<form action="postreservedelete.htm" method="post" id="delForm2" name="delForm">						
						<a href="#" onclick="modify2('${photoURL}','${postContent}','${postCode}','${name}','${mateName}','${postDate}','${postphotoURL}','${pungCode}');"><i class="icon-unorderedlist" style="font-size: 16px; color: Purple;" ></i></a>	
						<a href="javascript:deleteSubmit2('${postCode}','${count }');" ><i class="icon-unorderedlist" style="font-size: 16px; color: black;"></i></a>												
						<input type="hidden" id="delPostCode2" name="delPostCode2" value="${postCode }">
					</form>
					
					
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- 게시글 표시 부분 --%>
			<tr>
				<td colspan="4" style="padding:10px;" >	
						
						${postContent}
						<br/>
						<c:if test="${postphotoURL  != null}">
						<img src="${postphotoURL }" style="max-width:400px; height:auto;">
						</c:if>
						<br/><br/>						
						<strong><font color="#078196" >Tag.	</font></strong>					
						<c:forEach var="mate" items="${fn:split(taglist,',')}">						
							<input type="text" id="mate_${postCode}" value="${mate }" style="border:0;width:80px"> 						
								
						</c:forEach>
						<c:forEach var="code" items="${fn:split(taglistcode,',')}">						
							<input type="hidden" id="hidden_${postCode}" value="${code }" style="border:0;width:80px"> 						
								
						</c:forEach>
			
				</td>
			</tr>			
			</tbody>
		</table>
		</div>

</div>
