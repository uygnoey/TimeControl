<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="right" class="right">
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div>
		<form action="pwresult.htm" method="post" id="passwordFindForm">
			<div class="h1">비밀번호(Password) 찾기</div>
			<br /> <br /> <br />
			<div class="group">
				<input type="email" class="input input-rect" id="email"
					style="width: 300px; height: 60px;" id="email" name="email"
					placeholder="이메일(E-mail)을 입력해주세요." />
				<button type="button" id="pwLost" value="확인"
					class="btn btn-gray-black btn-flat btn-rect"
					style="width: 80px; height: 60px; text-align: center">확인</button>
			</div>
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br /> <br /> <br />
			<br /> <br /> <br />

			<div id="font">
				비밀번호(Password)찾기 서비스를 이용하여 비밀번호를 찾으실 경우에는,<br /> 해당 비밀번호는 삭제되며 입력하신
				이메일주소로 새로운 비밀번호를 보내드립니다.<br /> 이메일 확인 후 설정에서 비밀번호를 꼭 변경해주세요.<br />
			</div>

			<br />
			<br />
			<br />


			<!-- <a href='loginform.htm'><img src="img/home.png" width="60" height="60"></a>
 -->
			<div id="home">
				<h3>
					<a href='loginform.htm'>로그인 하러 가기</a>
				</h3>
			</div>
			<br /> <br />
		</form>
	</div>
</div>