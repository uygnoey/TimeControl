<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>친구 태그 검색</title>
<link rel="stylesheet" href="css/mateTag.css"/>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#searchBtn").click(function(){
		
		if($("#text").val()==""){
			
			$("#errMsg").css("display", "inline");
			return;
		}
		
		$("#search").submit();
		
	});
	
	$("#calcleBtn").click(function(){
		
		close();
	});	
	
});

function send(){
	
	var obj = document.getElementById("tagForm");
	var content = window.opener.postWriteForm.tagT.value;
	var count = parseInt(window.opener.postWriteForm.tagCount.value);
	//var mateCode = $('input[name="mateCode"]');
	if(typeof(obj.tag.checked)){	
	
		var value = obj.tag.value;
		var lstvalue = value.split('/');
		var name = lstvalue[0];
		var code = lstvalue[1];		
		
		if(window.opener.postWriteForm.code1.value == code){
			alert("이미 태그된 친구입니다.");
			return;
		}else if(window.opener.postWriteForm.code2.value == code){
			alert("이미 태그된 친구입니다.");
			return;
		}else if(window.opener.postWriteForm.code3.value == code){
			alert("이미 태그된 친구입니다.");
			return;
		}else if(window.opener.postWriteForm.code4.value == code){
			alert("이미 태그된 친구입니다.");
			return;
		}else if(window.opener.postWriteForm.code5.value == code){
			alert("이미 태그된 친구입니다.");
			return; 
		}
		
		
		window.opener.postWriteForm.tagT.value = content+"   "+name;
		window.opener.postWriteForm.tagCount.value = ++count;
		
		if(count == 1){				
			window.opener.postWriteForm.code1.value = code;
		} else if(count == 2){
			window.opener.postWriteForm.code2.value = code;	
		} else if(count == 3){
			window.opener.postWriteForm.code3.value = code;	
		} else if(count == 4){
			window.opener.postWriteForm.code4.value = code;	
		} else if(count == 5){
			window.opener.postWriteForm.code5.value = code;	
		}			
		
	}else{
	
		
		for(var i=0; i<obj.tag.length; i++){
			
			if(obj.tag[i].checked == true){
					
				var value = obj.tag[i].value;
				var lstvalue = value.split('/');
				var name = lstvalue[0];
				var code = lstvalue[1];
				alert(name+"/"+code);
				
				
				if(window.opener.postWriteForm.code1.value == code){
					alert("이미 태그된 친구입니다.");
					return;
				}else if(window.opener.postWriteForm.code2.value == code){
					alert("이미 태그된 친구입니다.");
					return;
				}else if(window.opener.postWriteForm.code3.value == code){
					alert("이미 태그된 친구입니다.");
					return;
				}else if(window.opener.postWriteForm.code4.value == code){
					alert("이미 태그된 친구입니다.");
					return;
				}else if(window.opener.postWriteForm.code5.value == code){
					alert("이미 태그된 친구입니다.");
					return; 
				}
				
				
				window.opener.postWriteForm.tagT.value = content+"   "+name;
				window.opener.postWriteForm.tagCount.value = ++count;
				
				if(count == 1){				
					window.opener.postWriteForm.code1.value = code;
				} else if(count == 2){
					window.opener.postWriteForm.code2.value = code;	
				} else if(count == 3){
					window.opener.postWriteForm.code3.value = code;	
				} else if(count == 4){
					window.opener.postWriteForm.code4.value = code;	
				} else if(count == 5){
					window.opener.postWriteForm.code5.value = code;	
				}			
			}	
		}		
	}
	
	self.close(); //자식창 닫기
	document.search.submit();//부모창으로 전송
	
}

</script>
</head>
<body>
<div class="parents">
	<%-- 친구 검색 box--%>
	<div id="searchArea" class="son1">
	<form action="matetagsearch.htm" method="post" id="search" name="search">
		<p style="line-height: 190%; color: gray;">
		이름을 입력해주세요. ex) JMJ
		<input type="text" id="text" name="text" class="size1" autofocus="autofocus">&ensp;
		<input type="button" value="검  색" id="searchBtn" class="size2"><br/>
		</p>
		<span id="errMsg">검색어를 입력해 주세요.</span>
	</form> 
	</div>
	
	<%-- 친구 검색 결과 list --%>
	
	<c:choose>
		<c:when test="${list[0] != null }">
			<div id="listArea" class="son2">
				<br/>
				<form action="" method="post" id="tagForm" name="tagForm">
				<c:forEach var="MemberDTO" items="${list }">
					<input type="radio" id="tag" name="tag" 
						   value="${MemberDTO.name}/${MemberDTO.memberCode }"> ${MemberDTO.name } / ${MemberDTO.email }<br>			
				</c:forEach>
					
				
				<br/>
				<input type="button" value="취소" class="size2" id="calcleBtn">&ensp;&ensp;
				<input type="button" value="추가" class="size2" id="addBtn" onclick="send()">
				</form>
			</div>
			
		</c:when>
		
		<c:when test="${list[0] == null}">	
			<div class="son2">
				
			</div>		
		</c:when>
	
	</c:choose>
</div>
</body>
</html>