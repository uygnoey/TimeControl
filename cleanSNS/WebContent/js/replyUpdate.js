/**
 * 댓글 업데이트 시 내용 체크
 */
$(document).ready(function() {
	
	$(".replySub").click(function() {
		
		if ($("#replyContent" + $(this).val()).val() == '') {
			alert("댓글 내용을 입력해야합니다.");
			return;
		}

		// submit() 액션 실행
		$("#replyUpdateForm" + $(this).val()).submit();
		opener.parent.location.reload();
		window.close();
	});
	
});