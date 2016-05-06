<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 예약하기</title>
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/layout.js"></script>
<script type="text/javascript">
	
	
	$(document).ready(function(){  
		
		
			//오늘 날짜 확인
			var date=new Date();
			//현재 시간 이후 정각부터
			var hour=date.getHours();
			var day=date.getDate();
			var month=date.getMonth()+1;
			var year=date.getFullYear();
			
			var list=document.getElementById("list");			
			
			for(var i=2; i<24; i++)
			{
				if((i+hour)<24)
					list+="<option value="+i+">"+year+"년 "+ month+"월 "+ day+"일 "+(i+hour)+"시"+"</option>";
				else
					list+="<option value="+i+">"+year+"년 "+month+"월 "+ (day+1)+"일 "+((i+hour)-24)+"시"+"</option>";
				if((i+hour)==23)
					list+="<option value="+i+">"+"--------------------------------"+"</option>";
						
			}			
			$("#list").append(list);
			
	});

	function send()
	{		
		var select=document.getElementById("list").value;
		var date=new Date();
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var hour=date.getHours();
		var day=date.getDate();
		var result=Number(hour)+Number(select);
		
		if(result>=24)
		{
			day=day+1;
			result=result-24;
		}
		
		window.opener.postWriteForm.reserv.value=day+"일 " + result+"시 정각에 게시";
		window.opener.postWriteForm.hiddenReserv.value=select;
		window.opener.postWriteForm.hiddenReservTime.value=hour;
		window.opener.postWriteForm.hiddenReservDate.value=day;
		window.opener.postWriteForm.hiddenReservYear.value=year;
		window.opener.postWriteForm.hiddenReservMonth.value=month;
	
		self.close(); //자식창 닫기
		document.listForm.submit();//부모창으로 전송
		
	}
		   
</script>
</head>
<body>

<div >
<form name="listForm" id="listForm" method="post" >
<font color="red" size="2">
<strong>※ 예약 게시글 주의사항</strong><br/><br/>
1.예약 설정은 [2시간] 뒤 부터 가능 합니다.
2.예약 설정은 [매 정각] 으로 예약이 가능합니다.<br/>
3.[23시간] 후까지만 예약이 가능 합니다.<br/>
4.예약이 완료된 글에 대해서는 수정이 [불가능]하며 <br/>
 &nbsp;&nbsp;&nbsp;삭제 후 재작성만 가능합니다.<br/>
5.예약 게시글 확인은 [내 스토리]에서 확인이 가능 합니다.<br/></font>
<br/>


<select id="list" style="height: 50px; align: center; margin-left:30px; font-size:20px; background:#F2FFED">

</select>
<font style="font-weight:bold">에 </font>
<input type="button" value="게시" id="submitBtn" name="submitBtn" style="height:30px; width:50px" class="reserveOption btn btn-black-gray btn-flat btn-rect"
	onclick="send()">

<br/>
</form>
</div>

</body>
</html>