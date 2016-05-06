$(document).ready(function()
{	
	$("#submiBtn").click(function()
	{
		if($("#area").val()=="" || $("#area").val()==null)
		{
			alert("게시글을 작성 해야 합니다!!");
			return; // submit 액션 중단
		}
	
			// submit 액션 처리
			$("#postWriteForm").submit();
			$("#pungForm").submit();
			

	});


	$("#resertBtn").click(function(){
		

		var style="";		
		var l_winHeight = $(window).height()/3;
		var l_winWidth = $(window).width()/2;

		style="left="+l_winWidth+",top="+l_winHeight+", width=400,height=300,status=no,toolbar=no, menubar=no,scrollbars=no, location=no, resizable=no";
		window.name="parent";	
		window.open('reserveform.htm', 'popup1', style);
		
		


	});
	
	
	$("#tagBtn").click(function(){
		
		if($("#tagCount").val() == "5"){
			
			alert("태그는 5명까지 가능합니다~!!");
			return;			
		}
		
		window.name="parent";
		window.open('matetagform.htm', 'popup1', 'left=70,top=70, width=400,height=200, toolbar=yes, menubar=no, scrollbars=yes, resizable=0');
	});
	
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
/*
function reset(){
	document.tagT.value=" ";			//text 지우기
	document.tagCount.value="0";	//count 지우기
	document.code1.value=" ";
	document.code2.value=" ";
	document.code3.value=" ";
	document.code4.value=" ";
	document.code5.value=" ";
}
*/

function updtsubmit() {
	
	
	close();
}


