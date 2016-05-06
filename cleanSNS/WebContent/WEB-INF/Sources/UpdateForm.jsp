<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script src="js/postWrite.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	/*  $("#updtSubmiBtn").click(function(){
		 
		 var postCode = $("#postCode").val();
		
		if($("#area").val()=="" || $("#area").val()==null)
		{
			alert("게시글을 작성 해야 합니다!!");
			return; // submit 액션 중단
		}
		
		$("#postWriteForm").submit();
		window.opener.contentsTd_.value = $("#area").val();
		self.close();
		
	}); */ 
	
	$("#tagCancle").click(function(){
		
		$("#tagT").val("");
		$("#tagCount").val("0");
		$("#code1").val("");
		$("#code2").val("");
		$("#code3").val("");
		$("#code4").val("");
		$("#code5").val("");

	});
	
});

 function updtSubmit(postCode){
	
	 var postcode = postCode;
	if($("#area").val()=="" || $("#area").val()==null)
	{
		alert("게시글을 작성 해야 합니다!!");
		return; // submit 액션 중단
	}
	
	$("#postWriteForm").submit();
	window.opener.document.getElementById("contentsDiv_"+postcode).value = $("#area").val(); 
	self.close();
} 



</script>
</head>
<body>
<form action="updatepost.htm" method="post" id="postWriteForm" name="postWriteForm">
		<div id="postWrite" style="padding-top: 10px;">
			<div><h5 style="color: red;">※ 게시글 수정 시 친구태그는 초기화 됩니다. 다시 태그 해 주세요!!</h5></div>
			<div style="line-height: 190%;">Tag. &nbsp;
				<input type="text" name="tagT" id="tagT" readonly="readonly"
				       style="background: transparent; border: 0px; width: 344px;"
				       value= "<%-- <c:forEach var="MateTagDTO" items="${tagDtoList }">${MateTagDTO.name }</c:forEach>--%>">
				<input type="button" value="태그취소" id="tagCancle">
				<input type="hidden" id="tagCount" name="tagCount" value="0">
				<input type="hidden" id="postCode" name="postCode" value="${postDto.postCode }">
  				<%-- tag된 멤버코드 저장 --%>			
				<input type="hidden" id="code1" name="code1" value="">
				<input type="hidden" id="code2" name="code2" value="">
				<input type="hidden" id="code3" name="code3" value="">
				<input type="hidden" id="code4" name="code4" value="">
				<input type="hidden" id="code5" name="code5" value="">
				
			</div>
			<%-- 게시글 작성 --%>
			<textarea id="area" name="area" cols="66" rows="10">${postDto.postContent }</textarea>
			<%-- 왼쪽 옵션 버튼 --%>
			<div id="leftBtn" style="float: left;" >
				<%-- 친구 태그 --%>
				<input type="button" value="친구태그" id="tagBtn" class="mateTag btn btn-black-gray btn-flat btn-rect"
				       name="tagBtn"/>
				<%-- 사진 추가 --%>
				<input type="button" value="사진변경" id="photoBtn" class="postPhoto btn btn-black-gray btn-flat btn-rect"
				       name="photoBtn" />
			</div>
			<%--  오른쪽 옵션 버튼 --%>
			<div id="rightBtn" style="float: right;"> 
				<%-- 게시글 작성 버튼 --%>
				<input type="button" value="수 정" id="updtSubmiBtn" class="postSubmit btn btn-black-gray btn-flat btn-rect"
				       name="updtSubmiBtn" onclick="updtSubmit('${postDto.postCode }')"/>
			</div>		

		</div>
</form>
</body>
</html>