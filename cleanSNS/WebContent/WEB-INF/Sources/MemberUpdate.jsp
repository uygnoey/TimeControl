<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/story.css" />
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>

<style type="text/css">
.err
{
	display: none;
	font-size: small;
	color: red;
}

html{padding:0px; overflow-y:scroll;} /* html에만 스크롤을 걸어준다. */
</style>

<script type="text/javascript">

$(document).ready(function(){
	
	var mate=document.getElementById("hiddenall").value;

	if(mate.length>=2)
	document.getElementById("hidden1").value=mate.split(" ")[0];
	if(mate.length>=4)
	document.getElementById("hidden2").value=mate.split(" ")[1];
	if(mate.length>=6)
	document.getElementById("hidden3").value=mate.split(" ")[2];
	if(mate.length>=8)
	document.getElementById("hidden4").value=mate.split(" ")[3];
	if(mate.length>=10)
	document.getElementById("hidden5").value=mate.split(" ")[4];
	
});
	
	function out() 
	{
		var outCheck = confirm("정말 탈퇴하시겠습니까?");

		
		if (outCheck == true){    //확인
			
			$("#memberRemove").submit();
		}

		else{   //취소
		    return;
		}

	}
	
	
	function checkbyte(checkText)
	{	//카테고리 글자수 확인
		var obj="";
		
		if(checkText=="1")
			 obj = document.getElementById("ca1").value;
		else if (checkText=="2")
			obj = document.getElementById("ca2").value;	
		
		
		var len = 0;
		for (var i=0;i<obj.length; i++) 
	    {			
	       var n = obj.charCodeAt(i); 
	       var nv = obj.charAt(i); 
	       
		   if ((n>= 0)&&(n<256)) 
			   len ++; 
	        else len += 3; // 한글이면 3byte로 계산한다.
        

	       if (len>30) 
	    	   alert("한글 10자 / 영문 30자까지만 입력이 가능합니다.");
	      }	    		
	}
	
	
	
	function saveBtn()
	{//카테고리 입력 확인
		
		var name = document.getElementById("name").value;
		var pw = document.getElementById("pw").value;
		var pwCheck = document.getElementById("pwCheck").value;
		
		var nameMsg = document.getElementById("nameMsg");
		var pwMsg = document.getElementById("pwMsg");
		var pwCheckMsg = document.getElementById("pwCheckMsg");

		
		var limitCheck1 = /[A-Za-z가-힣]/;  // 영문 대문자, 소문자, 한글 사용여부
		
		var limitCheck2 = /[~!@\#$%<>^&*\()\-=+_\’a-zA-Z0-9]/;	// 영문 소문자, 대문자, 숫자, 특수문자 사용여부
		
	    if(name=="" || !(limitCheck1.test(name)) || name.length>20 || name.length<2)
	    {
	    	nameMsg.style.display = "inline"; 
	        return; 
	    }
	    
	    if(pw=="" || !(limitCheck2.test(pw)) || pw.length<6 || pw.length>30)
	    {
	    	pwMsg.style.display = "inline"; 
	        return; 
	    }
	    
	    if(pw != pwCheck)
	    {
	    	pwCheckMsg.style.display = "inline"; 
	        return; 
	    }
		
		if(document.getElementById("ca1").value==""&& document.getElementById("ca2").value=="" )
		{	 alert("카테고리 제목을 입력해야 합니다.");
			 return;
		}	    
		else if(document.getElementById("ca2").value!="" && document.getElementById("mateList").value=="")
		{
			 alert("친구를 1명이상 추가해야 합니다.");
			 return;
		}
		
		document.getElementById("memberUpdateForm").submit();
		
		//$("#memberUpdateForm").submit();
	    
	    alert("정보업데이트가 완료되었습니다.");
				
	}
	
	
$(document).ready(function()
{		
	$("#addMate").click(function(){
				
		var style="";		
		var l_winHeight = $(window).height()/3;
		var l_winWidth = $(window).width()/2;

		style="left="+l_winWidth+",top="+l_winHeight+", width=470,height=600,status=no,toolbar=no, menubar=no, location=no, resizable=no";
		window.name="parent";	
		window.open('categorymateadd.htm', 'child', style);
				
		
	});
});
	
</script>
</head>
<body class="jui" >
	<div id="container" class="container">
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
						
					<td align="right" style="width:800px;"><br/><br/><br/><br/><br/><input type="submit" value="정보 업데이트" id="submitBtn" class="reserveOption btn btn-black-gray btn-flat btn-rect" ></td>
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
							<a href="">사진</a>
						</td>
						<td align="center" >
							<a href="">친구</a>
						</td>
						<td align="center" >
							<a href="">예약게시글</a>
						</td>
					</tr> 
				</table> 

			</form>
		</div>
	    
		
</div>
	<br/>
	<form action="memberupdate.htm" method="post" id="memberUpdateForm" >
	<input type="hidden" value="${memberCode }" id="memberCode" name="memberCode" />

			<div class="letter">
				<div>
					<table>
						<tr>
							<td width="1200px"><font color="white"><strong>정보업데이트</strong></font>
							</td>
							<td><input type="button" value="저장" id="storeBtn"
								name="storeBtn" onclick="saveBtn()" style="float: right;"
								class="reserveOption btn btn-black-gray btn-flat btn-rect">
							</td>
						</tr>
					</table>
				</div>
			</div>


			<div class="memberupdate"> 
		
			<table>
				<tr>
				<th >▶ 개인정보수정</th>
				</tr>
				<tr>
					<td width="200px">이메일(E-mail)</td>
					<td width="200px"> ${email }</td>
				</tr>
			
				<tr>
					<td>이름(Name)</td>
	 				<td>
	 					<input type="text" id="name" name="name" placeholder="이 름 (Name)" value="${name}">
	 				</td>
	 				<td style="font-size:12px;" align="left">
	 					<font color=#808080>2~20자  한글/영문 대소문자 구분</font>
	 				</td>
	 			</tr>
	 			 
	 			<tr>
	 				<td></td>	 					
	 				<td><span class="err" id="nameMsg">잘 못 입력하셨습니다.</span></td>
	 			</tr>
	 			
	 			<tr>
	 				<td>비밀번호(Password)</td>
	 				<td>
	 					<input type="password" id="pw" name="pw" placeholder="비밀번호 (Password)">
	 				</td>
	 				<td style="font-size:12px;" align="left"><font color=#808080>6~30자  영문 대소문자, 숫자, 특수문자 사용 가능</font></td>
	 			</tr>
	 			
	 			<tr>
	 				<td></td>	 					
	 				<td><span class="err" id="pwMsg">잘 못 입력하셨습니다.</span></td>
	 			</tr>
	 			
	 			<tr>
	 				<td>비밀번호확인(passwordCheck)</td>
	 				<td>
	 					<input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인(Password Check)">
	 				</td>
	 			</tr>
	 				
	 			<tr>
	 				<td></td>	 					
	 				<td><span class="err" id="pwCheckMsg">비밀번호가 일치하지 않습니다.</span></td>
	 			</tr>
			</table>

			<table style="border-top:1px solid #36D7B7">		
			
				<tr>
				<th>
					▶ 카테고리 이름 관리
				</th>
				</tr>
			
				<tr>
	 				<td width="200px" >카테고리 1</td>
	 				<td width="200px" >
	 					<input type="text" id="ca1" name="ca1" placeholder="나의 소중한 친구들 소식" value="${ca1 }" onkeyup="checkbyte(1);"/>
	 				</td>
	 				<td style="font-size:12px;" align="left">
	 					<font color=#808080>한글 10자 / 영문 30자</font>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td></td>	 					
	 				<td><span class="err" id="ca1Msg">글자수를 초과하였습니다.</span></td>
	 			</tr>
	 			<tr>
	 				<td>카테고리 2</td>
	 				<td >
	 					<input type="text" id="ca2" name="ca2" placeholder="카테고리 분류 이용하기"  value="${ca2 }" onkeyup="checkbyte(2);"/>
	 				</td>
	 				<td style="font-size:12px;" align="left">
	 					<font color=#808080>한글 10자 / 영문 30자</font>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td></td>	 					
	 				<td><span class="err" id="ca2Msg">글자수를 초과하였습니다.</span></td>
	 			</tr>
	 			<tr>
	 			<td></td>
	 		
	 			<td>
	 				<input type="text" readonly="readonly" id="mateList" name="mateList"  value="${mate }" class="mateAddInput"></td>
					 			
	 			<td><input type="button" value="친구추가" id="addMate" name="addMate" onclick="" style="width:70px" class="reserveOption btn btn-black-gray btn-flat btn-rect">
	 				<font color=#808080>친구 추가를 통해 카테고리2에 친구를 추가 분류해 놓으세요.</font></td>
	 			<td><input type="hidden" id="hiddenall" name="hiddenall" value="${mateCode }">
	 				<input type="hidden" value="" id="hidden1" name="hidden1">
	 				<input type="hidden" value="" id="hidden2" name="hidden2">
	 				<input type="hidden" value="" id="hidden3" name="hidden3">
	 				<input type="hidden" value="" id="hidden4" name="hidden4">
	 				<input type="hidden" value="" id="hidden5" name="hidden5">
	 			</td>
	 			</tr>
	 		</table>	
	 		 			
	 			<table style="border-top:1px solid #36D7B7">
		 			<tr >
						<th >▶ 공개 범위 설정</th>
					</tr>
				
					<tr>
						<td width="200px"><input type="radio" value="0" name="range" checked="checked" style="width:20px">전체공개</td>						
						<td width="200px"><input type="radio" value="1" name="range" style="width:20px">친구공개</td>
						<td style="width:800px"></td>
					</tr>
				</table>
				</div>
			</form>
				
			 <form action="memberremove.htm" method="post"  id="memberRemove">   <%--memberremove.htm --%>
			

				<br/>

				<div style="float:right" style="width="200px;">
					<input type="button" value="회원탈퇴" id="outBtn" name="outBtn" onclick="out()" style="width:70px" class="reserveOption btn btn-black-gray btn-flat btn-rect">
				</div>	
			</form>
			<br/><br/><br/><br/><br/>
	</div>




</body>
</html>