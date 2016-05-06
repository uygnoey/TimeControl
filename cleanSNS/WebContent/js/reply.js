/**
 * 댓글 내용 체크 댓글 삽입 댓글 삭제 자바스크립트
 */

$(document).ready(
		function() {

			$(document).ready(function() {
				var idCode;
				
				$(".replyEdit").click(function() {
					idCode = $(this).val();
					replyEditFormPop.show();
				});
				
				
				$("#replyEditSubmit").click(function() {
					replyEditFormPop.hide();
					$.ajax({
						url:"replyupdate.htm",
						type: "post",
						data: "replyCode=" + idCode + "&replyContent=" + $("#replyContentEdit").val(),
						success: function() {
							location.reload();
						}
					});
				});
				
				$(".replySub").click(function() {
					idCode = $(this).val();
					
					if ($("#replyContent" + idCode).val() == '') {
						alert("댓글 내용을 입력해야합니다.");
						return;
					}
					
					$.ajax({
						url: "replyinsert.htm",
						type: "post",
						data: "postCode=" + $("#postCode" + idCode).val() + "&replyContent=" + $("#replyContent" + idCode).val(),
						success: function() {
							location.reload();
						}
						
					});
				});
				
				$(".replyDel").click(function() {
					
					idCode = $(this).val();
					
					if (confirm("삭제 하시겠습니까?") == true) {
						
						$.ajax({
							url:"replyDelete.htm",
							type: "post",
							data: "replyCode="+$("#replyCode"+idCode).val(),
							success: function() {
								location.reload();
							}
						});
					
					}
					
				});
				
			});
		
		});