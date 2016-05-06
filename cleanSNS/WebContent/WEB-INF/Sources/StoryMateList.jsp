<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구LIST</title>
<link rel="stylesheet" href="css/story.css" />
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>
<script type="text/javascript" src="jui/js/ui/dropdown.js"></script>

<script type="text/javascript">
function enlargeImage1()
{
	image1.height="300" // 커졌을때 이미지 크기
}
function dropImage1()
{
	image1.height="129" // 보통,작아졌을때 이미지 크기
}

</script>

<script type="text/javascript">

jui.ready([ "ui.dropdown" ], function(dropdown) {
	
		
		dd_1 = dropdown("#dd_"+$("#caBtn").val(), {	
			event: {
				change: function(data) {
					ajaxRequest(data.value);
				}
			}
		});
	
	// 드랍박스 친구or아는사람 선택 했을 때 ajax와 친구변경함수 실행
	dd_m = dropdown("#dd_m", {
		event: {
			change: function(data) {
				ajaxRequest2(data.value);
			}
		}
	});
});



/* function ajaxRequest(data){
	
	$.post("mateajax.htm", {category : data}, function(d){
		if(d == 1)
			$("#caBtn").val("카테고리 1");		
		else if(d == 2)
			$("#caBtn").val("카테고리 2");
		
	
	});
} */

function ajaxRequest2(matecode){
	
	var data = $("#group_"+matecode).val();
	var Code = matecode;
	$.post("mateajax2.htm?code="+Code, {mate : data}, function(d){
		if(d == 0){
			$("#mateBtn_"+matecode).val("친      구");	
			$("#mBtn_"+matecode).val("아는사람으로 설정");
			$("#group_"+matecode).val(1);
		}
		else if(d == 1){
			$("#mateBtn_"+matecode).val("아는사람");
			$("#mBtn_"+matecode).val("친구로 복귀");
			$("#group_"+matecode).val(0);
		}
		
		
	});
}

function delMate(){
	
	var check = confirm("정말 친구를 끊겠습니까?");
	if(check){
		$("#delMateForm").submit();
	}
	else
		alert("친구 끊기를 취소 하셨습니다.");
	
	
}




</script>

<style type="text/css">
html{padding:0px; overflow-y:scroll;} /* html에만 스크롤을 걸어준다. */
</style>

</head>
<body class="jui" >

	
<div id="top" class="top">
	<c:import url="/header.htm" />
</div>
		
<div class="wrap">	
		<div class="story">
			<form action="pwcheckform.htm" method="post">
				<table >
					<tr>
					<td style="width:30px;"></td>

					<td align="left" >
					<c:choose>
						<c:when test="${myPhoto != null}">
						<img src="${myPhoto}"  style="width:180px; height:auto;" />
						</c:when>
						<c:otherwise>
						<img src="img/logo.PNG"  style="width:180px; height:auto;" />
						</c:otherwise>
					</c:choose>							
					</td>
					<td align="left" style="width:230px; color:white; font-weight:bold"><br/><br/><br/><br/><br/>&nbsp;${name}</td>

						
					<td align="right" style="width:800px;"><br/><br/><br/><br/><br/><input type="submit" value="정보 업데이트" id="submitBtn" style="border:1px solid #36D7B7" class="reserveOption btn btn-black-gray btn-flat btn-rect" ></td>
					</tr>
				</table>
				<table class="storyTable">
					<tr>
						<td align="left" width=950px>
							<a href="newsfeed.htm">뉴스피드</a>
						</td> 
						<td align="center">
							<a href="storyview.htm">스토리</a>
						</td> 
						<td align="center" >
							<a href="storymyphoto.htm">사진</a>
						</td>
						<td align="center" >
							<a href="storymatelist.htm">친구</a>
						</td>
						<td align="center" >
							<a href="">예약게시글</a>
						</td>
					</tr> 
				</table> 

			</form>
		</div>
		
	<%-- 친구 리스트 View 부분 --%>
	<div class="form1">
	
	<div style="padding-top:30px">
		
		<div class="h2" style="color:#36D7B7; font-weight:bolder;">
		My Friends List <strong style="float: right;">총&ensp;${count }명&ensp;</strong>
		</div>
	  	 
	</div>
	<div id="myPoto" style="border-top:1px solid #36D7B7"></div>
	<br/>
	
	<table id="listTable" class="center">
		<c:forEach var="MateInfoDTO" items="${mateList }">
			<tr>
				<td style="width: 100px; height:60px;">
				
				<!-- <img src="img/profile.png" style="width: 55px; "> -->
				
				<c:choose>
				<c:when test="${MateInfoDTO.photoURL != null}">
				<img src="${MateInfoDTO.photoURL}"  style="width: 55px; height:55;" />
				</c:when>
				<c:otherwise>
				<img src="img/logo.PNG"  style="width:55px; height:auto;" />
				</c:otherwise>
				</c:choose>						
				
				</td>
				<%-- 이름 클릭하면 친구스토리로 이동(추가사항) --%>
				<td style="width: 150px; height:100;"><a href=""><b>${MateInfoDTO.name }</b></a></td>
				<td style="width: 100px;">
				
					<div style="display: inline-block; position: relative;">
					<div class="group">
					<%-- 카운트 변수 선언 --%>
					<c:set var="count" value="0"/>
					<c:forEach var="Category2MateDTO" items="${caList }">
						<c:choose>
							<c:when test="${MateInfoDTO.mateMemberCode  == Category2MateDTO.mateSaveCode }">
								<button type="button" id="caBtn" name="caBtn" class="btn btn-while btn-rect" value="${MateInfoDTO.mateMemberCode }" onclick="<!-- dd_1.show(); -->">카테고리 2</button>
							</c:when>
							<c:otherwise>
								<c:set var="count" value="${count + 1 }"/>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:if test="${count == caList.size() }">
						<button type="button" id="caBtn" name="caBtn" class="btn btn-while btn-rect" value="${MateInfoDTO.mateMemberCode }" onclick="<!-- dd_1.show(); -->">카테고리 1</button>
					
					</c:if>
						
					</div>	
						<input type="hidden" value="${MateInfoDTO.mateMemberCode }" id="caMateCode_${MateInfoDTO.mateMemberCode }" name="caMateCode">		
						<div id="dd_${MateInfoDTO.mateMemberCode }" class="dropdown dropdown-large">	
							<div class="anchor"></div>	
							<ul style="width: 100px;">
								<li value="1">카테고리 1</li>
								<li class="divider"></li>
								<li value="2">카테고리 2</li>
							</ul>					
						</div>
					</div>
				
				</td>
				<td style="width: 100px;">
				
					<div style="display: inline-block; position: relative;">
					<div class="group">
					<c:choose>
						<c:when test="${MateInfoDTO.mateGroupCode == 0 }">
							<input type="button" id="mateBtn_${MateInfoDTO.mateMemberCode}" name="mateBtn" value="친        구" class="btn btn-while btn-rect" onclick="">
						</c:when>
						<c:otherwise>
							<input type="button" id="mateBtn_${MateInfoDTO.mateMemberCode}" name="mateBtn" value="아는사람" class="btn btn-while btn-rect" onclick="">
						</c:otherwise>
					</c:choose>
						<div id="dd_m" class="dropdown dropdown-large">
							<div class="anchor"></div>	
							<ul style="width: 100px;">
								<li value="0">친&ensp;&ensp;&ensp;&ensp;구</li>
								<li class="divider"></li>
								<li value="1">아는사람</li>
							</ul>
						</div>
					</div>
					</div>
				</td>
				<td style="width: 70px;"></td>
				<td style="width: 150px;">
					 <c:choose>
						<c:when test="${MateInfoDTO.mateGroupCode == 0 }">
							<input type="hidden" value="${MateInfoDTO.mateGroupCode + 1 }" id="group_${MateInfoDTO.mateMemberCode}">
							<input type="button" value="아는사람으로 설정" id="mBtn_${MateInfoDTO.mateMemberCode}" class="btn btn-purple btn-rect" onclick="ajaxRequest2('${MateInfoDTO.mateMemberCode}')">
						</c:when>
						
						<c:otherwise>
							<input type="hidden" value="${MateInfoDTO.mateGroupCode - 1 }" id="group_${MateInfoDTO.mateMemberCode}">
							<input type="button" value="친구로 복귀" id="mBtn_${MateInfoDTO.mateMemberCode}" class="btn btn-purple btn-rect" onclick="ajaxRequest2('${MateInfoDTO.mateMemberCode}')">
						</c:otherwise> 	
					</c:choose> 										
				</td>
				<td>
				<form action="delMate.htm?mateCode=${MateInfoDTO.mateMemberCode}" method="post" id="delMateForm" name="delMateForm" >				
					<input type="button" value="친구 끊기" id="delBtn_${MateInfoDTO.mateMemberCode}" class="reserveOption btn btn-black-gray btn-flat btn-rect" onclick="delMate()">
				</form>
				</td>		
			</tr>
		</c:forEach>
	</table>	
	</div>
	
</div>
</body>
</html>