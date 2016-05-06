<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<script type="text/javascript">
$(document).ready(function(){

	 list();
});

var num=1;
var url="categorymateaddlist.htm";
var searchnum=1;
		
function list()
{
		$.post(url,{start:num},function(data)
			{
				$(data).appendTo('#list');
				num=num+5;
			}
		
		);
}

$(window).scroll(function()
	{
	 	var dheight = $(document).height();
	 	var sheight = $(window).scrollTop() + $(window).height();
	
	 	 if(num > 1 && dheight == sheight){
	 		list();
	 	  }	
	}

);

function search()
{
	document.getElementById("list").innerHTML="";
	var url="categoryMateSearch.htm";
	var searchMate=document.getElementById("searchMate").value;

	$.post(url,{start:searchnum,mate:searchMate},function(data)
	{		
		$(data).appendTo('#list');
		searchnum=searchnum+5;
	}
		
	);
	
}

	var count=0;
	var max=5;
	
	function checkcount(obj)
	{
		var myMax=document.getElementById("mateCount");
		
		if(myMax<max)
			max=myMax;
		
		if(count==max)
		{	
			alert("친구 분류는"+max+"명까지만 가능 합니다.");	
			obj.checked=false;
			return;
		}
		else
			check(obj);
	}

	function check(obj)
	{			
		if(obj.checked && count==0)
		{	
			document.getElementById("mate1").value=obj.value.split(',')[0];
			document.getElementById("hidden1").value=obj.value.split(',')[1];
			count=1;		
		}		
		else if(obj.checked && count==1)
		{	document.getElementById("mate2").value=obj.value.split(',')[0];
			document.getElementById("hidden2").value=obj.value.split(',')[1];
			count=2;
		}
		else if(obj.checked && count==2)
		{	document.getElementById("mate3").value=obj.value.split(',')[0];
			document.getElementById("hidden3").value=obj.value.split(',')[1];
			count=3;
		}
		else if(obj.checked && count==3)
		{	document.getElementById("mate4").value=obj.value.split(',')[0];
			document.getElementById("hidden4").value=obj.value.split(',')[1];
			count=4;
		}
		else if(obj.checked && count==4)
		{	document.getElementById("mate5").value=obj.value.split(',')[0];
			document.getElementById("hidden5").value=obj.value.split(',')[1];
			count=5;
		}
			
		
		if(!obj.checked && count==1)
		{	
			document.getElementById("mate1").value=null;
			count=0;		
		}		
		else if(!obj.checked && count==2)
		{	document.getElementById("mate2").value=null;
			count=1;
		}
		else if(!obj.checked && count==3)
		{	document.getElementById("mate3").value=null;
			count=2;
		}
		else if(!obj.checked && count==4)
		{	document.getElementById("mate4").value=null;
			count=3;
		}
		else if(!obj.checked && count==5)
		{	document.getElementById("mate5").value=null;
			count=4;
		}
		
	}

	function submitCheck()
	{//실제 추가
		
		window.opener.PumngModifyForm.mate1.value="";
		window.opener.PumngModifyForm.mate2.value="";
		window.opener.PumngModifyForm.mate3.value="";
		window.opener.PumngModifyForm.mate4.value="";
		window.opener.PumngModifyForm.mate5.value="";
		window.opener.PumngModifyForm.hidden1.value="";
		window.opener.PumngModifyForm.hidden2.value="";
		window.opener.PumngModifyForm.hidden3.value="";
		window.opener.PumngModifyForm.hidden4.value="";
		window.opener.PumngModifyForm.hidden5.value="";
			
		window.opener.PumngModifyForm.mate1.value=document.getElementById("mate1").value;
		window.opener.PumngModifyForm.mate2.value=document.getElementById("mate2").value;
		window.opener.PumngModifyForm.mate3.value=document.getElementById("mate3").value;
		window.opener.PumngModifyForm.mate4.value=document.getElementById("mate4").value;
		window.opener.PumngModifyForm.mate5.value=document.getElementById("mate5").value;
		window.opener.PumngModifyForm.hidden1.value=document.getElementById("hidden1").value;
		window.opener.PumngModifyForm.hidden2.value=document.getElementById("hidden2").value;
		window.opener.PumngModifyForm.hidden3.value=document.getElementById("hidden3").value;
		window.opener.PumngModifyForm.hidden4.value=document.getElementById("hidden4").value;
		window.opener.PumngModifyForm.hidden5.value=document.getElementById("hidden5").value;
	
		self.close(); //자식창 닫기
		document.mateAddListForm.submit();//부모창으로 전송
	}
	
	

</script>

</head>
<body class="jui">
<form id="mateAddListForm">
<div class="mateAdd">
<div class="mateAddTop">
	<div class="mateAddheader">
		▶ 펑 게시글에 분류할 친구 리스트 <br/>
	</div>
		<input type="text" readonly="readonly" id="mate1" name="mate1" class="mateAddInput">
		<input type="text" readonly="readonly" id="mate2" name="mate2" class="mateAddInput">
		<input type="text" readonly="readonly" id="mate3" name="mate3" class="mateAddInput">
		<input type="text" readonly="readonly" id="mate4" name="mate4" class="mateAddInput">
		<input type="text" readonly="readonly" id="mate5" name="mate5" class="mateAddInput">
		<input type="hidden" id="hidden1" name="hidden1" value="">
		<input type="hidden" id="hidden2" name="hidden2" value="">
		<input type="hidden" id="hidden3" name="hidden3" value="">
		<input type="hidden" id="hidden4" name="hidden4" value="">
		<input type="hidden" id="hidden5" name="hidden5" value="">
		
							

	<br/>
	<br/>
	
	<div style="margin:5px;">
	<table>
		<tr>
		<td>
		친구 검색 <input type="text" id="searchMate" name="searchMate">
		</td>
		<td>
		<input type="button" id="searchBtn" name="searchBtn" value="검색" onclick="search();" style="float:left;padding-left:5px;padding-right:5px;" class="reserveOption btn btn-black-gray btn-flat btn-rect">
		</td>
		<td width="130px" align="right">
		<input type="button" id="addBtn" name="addBtn" value="추가" onclick="submitCheck();" style="float:right;padding-left:10px;padding-right:10px" class="reserveOption btn btn-black-gray btn-flat btn-rect">
		</td>
		</tr>
	</table>
	</div>
</div>

<div id="list" style="margin:10px">
	
</div>

</div>
</form>
</body>
</html>