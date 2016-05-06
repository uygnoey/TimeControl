/**
 * 정보업데이트 하기 전 비밀번호 체크 Ajax파일
 */

$(document).ready(function() {
	$("#checkBtn").click(function() {
		if($("#pw").val() == '') {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		$.ajax({
			url : "pwcheck.htm",
			type : "POST",
			data : "pw="+$("#pw").val() + "&email="+$("#email").val(),	// data : ajax의 메소드
			success : function(data) {	// data : Ajax 가 성공적으로 처리된 후 받아오는 값.
	
				// JavaScript 로 받을 때에는 무조건 문자열로 받아오게 된다.
				// (넘겨줄 떄 int 형으로 넘겨줬더라도 JavaScript로 받아올 때는 문자열로 받아옴.)
				// 그렇기 떄문에 문자열로 받아온 데이터를 넘버형태로 바꿔줘야 한다.
				// 그 방법은 parseInt()를 사용한다.
				
				parseInt(data);
				var memberCode = parseInt($("#memberCode").val());
				
				if (data==memberCode){

					$("#memberupdate").submit();
					
				}
				else
				{
					alert("비밀번호가 일치하지 않습니다.");
					$("#memberupdate")[0].reset();	// 비밀번호 쓰는 곳만 리셋.
					return;
					
				}
			}
		})
	})
})




