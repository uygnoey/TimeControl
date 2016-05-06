/**
 * 
 */
$(document).ready(function() {

	$("#pwLost").click(function() {

		if ($("#email").val() == '') {
			alert("이메을 입력해야합니다.");
			return;
		}

		$.ajax({

			type : "GET",
			url : "mailcheck.htm",
			data : "email=" + $("#email").val(),
			success : function(data) {
				
				if (data == 0) {
					alert("존재하지 않는 이메일입니다.");
					return;
				}

				// submit() 액션 실행
				$("#passwordFindForm").submit();

			}

		});

	});
});