<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	function matefind()
	{
		var search = document.getElementById("search").value;
		
		if(search=="")
		{
				alert("검색할 친구의 이름을 입력해주세요.");
		}	
		else
			{
				searchForm.submit();
			}
	}

</script>

<%-- header 상단 레이아웃 부분 --%>
<div id="wrapHeader" class="wrapHeader">

	<%-- 왼쪽에 표시할 컨텐츠 --%>
	<div class="headerLeft">
	
		<%-- 사이트 로고 표시 --%>
		<div id="divLogo" class="divLogo">
			<a href="newsfeed.htm"><img src="img/logo1.png" align="middle" style="width:145px;height:auto;"></a>
		</div>
		
		<%-- 친구 찾기 부분 --%>
		<div id="divSearch" class="divSearch"  >
			<form action="matefind.htm" method="post" id="searchForm" class="searchForm">
				<div class="group group-rect" >
					<a class="btn" onclick="matefind()">
						<i class="icon-search icon-pulse"></i>
					</a>
					<input type="text" name="search" id="search" class="input input-rect" placeholder="친구찾기"/>
				</div>
			</form>
		</div>
	</div>
	
	<%-- 오른쪽에 표시할 컨텐츠 --%>
	<div class="headerRight">
	
		<%-- 알림 부분 --%>
		<div id="divAlert" class="divAlert">
			<div class="h5" style="display: inline">
				<a href="alert.htm"> <i class="icon-checkmark"
					style="font-size: 14px;"></i> <small>${alertCount}개의 알림</small>
				</a>
			</div>
		</div>
		
		<%-- 로그아웃 부분 --%>
		<div id="divLogout" class="divLogout">
			<%-- 자동로그아웃 설정 시 표시될 부분 --%>
			<div class="h5" style="display: inline; "><i class="icon-caution icon-pulse" style="font-size: 14px; color: red;"></i><small style="color:#C3272B;">${logoutTime} <%=session.getMaxInactiveInterval()/60%> 분 후 로그아웃</small></div>
			<%-- 로그아웃 버튼 --%>
			<form action="" method="post" class="logoutForm">
				<input type="button" value="로그아웃" id="logoutBtn" class="btn btn-gray-black btn-flat btn-rect" name="logoutBtn" onclick="location.href='logout.htm'" />
			</form>
		</div>
	</div>
</div>