<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="css/alert.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/js/ui/dropdown.js"></script>
<script type="text/javascript" src="jui/js/uix/tab.js"></script>
<script type="text/javascript">
	jui.ready([ "uix.tab" ], function(tab) {
		tab_1 = tab("#tab_alert", {
			event : {
				change : function(data) {
					//alert(data.text);
					//$(data.id).show();
				},
				changemenu : function(data) {
					alert(data.text);
				}
			},
			target : "#tab_alert_content",
			index : 0,
			drag : true
		});
	});

	
	$(document).ready(
			function() {
				$(".alertConfirm").click(
						function() {
							//alert("$(this).val() : " + $(this).val());
							//alert("form id : " + $("#form" + $(this).val()).id());
							var idCode = $(this).val();
							$.ajax({

								url : "alertcheck.htm",
								type : "post",
								data : "memberCode="
										+ $("#m" + $(this).val()).val()
										+ "&tableNameCode="
										+ $("#t" + $(this).val()).val()
										+ "&codeName="
										+ $("#c" + $(this).val()).val()
										+ "&alertDate="
										+ $("#d" + $(this).val()).val(),
								dataType : "json",
								success : function(data) {

									//alert(data.check);

									if (data.check) {

										alert("삭제된 게시글 입니다.");
										$(".w" + idCode).hide();
										return;

									}

									$("#form" + idCode).submit();

								}
							});
						});
				
				$(".alertMateConfirm").click(function() {
					$.ajax({
						url: "alertmateconfirm.htm",
						type : "post",
						data : "memberCode="
								+ $("#m" + $(this).val()).val()
								+ "&tableNameCode="
								+ $("#t" + $(this).val()).val()
								+ "&codeName="
								+ $("#c" + $(this).val()).val()
								+ "&alertDate="
								+ $("#d" + $(this).val()).val(),
						success : function() {
							location.reload();
						}
					});
				});
			});
	
	function popup(mateCode, mateName) {
		//alert(mateCode);
		//alert(mateName);
		var style = "";
		var l_winHeight = $(window).height() / 3;
		var l_winWidth = $(window).width() / 2;
		style = "left="
				+ l_winWidth
				+ ",top="
				+ l_winHeight
				+ ", width=350,height=150,status=no,toolbar=no, menubar=no, location=no, resizable=no";
		window.name = "parent";
		window.open('alertpopup.htm?mateCode=' + mateCode + '&mateName='
				+ mateName, 'child', style);

	}
</script>
</head>
<body class="jui">
	<div id="container">
		<div id="top" class="top">
			<c:import url="/header.htm" />
		</div>
		<div class="wrap">
			<div id="left" class="left">
				<c:import url="/side.htm" />
			</div>
			<div id="center" class="right">
				<div class="alert_body">
					<%-- Tab Menu --%>
					<ul id="tab_alert" class="pill pill-top">
						<li class="active"><a href="#mate">친구 알림</a>
							<div class="anchor"></div></li>
						<li><a href="#post">게시글 알림</a></li>
						<li><a href="#reply">댓글 알림</a></li>
						<%--
					<li class="menu">
						<a href="#">
							Menu<i class="icon-arrow1 icon-white"></i>
						</a>
					</li> --%>
					</ul>

					<%-- Tab Content --%>
					<div id="tab_alert_content" class="tab-contents">
						<%-- 친구 알림 부분 --%>
						<div id="mate">
							<table class="table table-simple table-hover">
								<thead>
									<tr>
										<th class="alert_content">알림 내용</th>
										<th class="alert_date" style="text-align: center;">알림 시간</th>
										<th class="alert_confirm" style="text-align: center;">알림
											확인</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="mAlert" items="${alertMateList}">
										<%-- <c:if test="${mAlert.memberCode == memberCode}"> --%>
										<c:choose>
											<c:when test="${mAlert.tableNameCode == 3}">
												<tr>
													<td class="alert_content"><c:if test="${mAlert.confirm == 0 }"><span style="color: red; font-size: small;">new</span></c:if>&nbsp;${mAlert.mateName}</a>님이
														${mAlert.name}님에게 친구요청을 하였습니다. </td>
													<c:set var="mateCode" value="${mAlert.mateCode}" />
													<%-- <td>"${mateCode }"</td> --%>
													<c:set var="mateName" value="${mAlert.mateName}" />
													<%-- 		<td>"${mateName }"</td>
 --%>
													<td class="alert_date">${mAlert.alertDate}</td>
													<td class="alert_confirm">

														<button type="button"
															id="popup'${ mateCode}', '${mateName }'"
															onclick="popup('${ mateCode}', '${mateName }')"
															class="btn btn-gray-purple btn-flat btn-rect btn-mini"
															value="mS${mAlert.mateCode}">요청수락/거절</button>
													</td>
												</tr>
											</c:when>
											<c:when test="${mAlert.tableNameCode == 4}">
												<tr>
													<td class="alert_content"><c:if test="${mAlert.confirm == 0 }"><span style="color: red; font-size: small;">new</span></c:if>&nbsp;${mAlert.mateName}님이
														${mAlert.name}님과 친구가 되었습니다.</td>
													<td class="alert_date">${mAlert.alertDate}</td>
													<td class="alert_confirm">
														<form action="alertmateconfirm.htm" id="form${mAlert.mateCode}" method="post">
															<input type="hidden" name="memberCode" id="m${mAlert.mateCode}" value="${mAlert.memberCode}" /> 
															<input type="hidden" name="tableNameCode" id="t${mAlert.mateCode}" value="${mAlert.tableNameCode}" /> 
															<input type="hidden" name="codeName" id="c${mAlert.mateCode}" value="${mAlert.mateCode}" /> 
															<input type="hidden" name="alertDate" id="d${mAlert.mateCode}" value="${mAlert.alertDate}" />
															<button
																class="alertMateConfirm btn btn-gray-purple btn-flat btn-rect btn-mini"
																type="button" value="${mAlert.mateCode}">
																<i class="icon-check"></i> 알림 확인
															</button>
														</form>
													</td>
												</tr>
											</c:when>
										</c:choose>
										<%-- </c:if> --%>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%-- 게시글 알림 부분 --%>
						<div id="post">
							<table class="table table-simple table-hover">
								<thead>
									<tr>
										<th class="alert_content">알림 내용</th>
										<th class="alert_date" style="text-align: center;">알림 시간</th>
										<th class="alert_confirm" style="text-align: center;">알림
											확인</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pAlert" items="${alertPostList}">
										<tr class="w${pAlert.postCode}">
											<td class="alert_content"><c:if test="${mAlert.confirm == 0 }"><span style="color: red; font-size: small;">new</span></c:if>&nbsp;${pAlert.mateName}님이
													${pAlert.name}님을 태그했습니다.</td>
											<td class="alert_date">${pAlert.alertDate}</td>

											<td class="alert_confirm">
												<form action="alertconfirm.htm" id="form${pAlert.postCode}"
													method="post">
													<input type="hidden" name="memberCode" id="m${pAlert.postCode}"
														value="${pAlert.memberCode}" /> <input type="hidden"
														name="tableNameCode" id="t${pAlert.postCode}"
														value="${pAlert.tableNameCode}" /> <input type="hidden"
														name="codeName" id="c${pAlert.postCode}" value="${pAlert.postCode}" />
													<input type="hidden" name="alertDate" id="d${pAlert.postCode}"
														value="${pAlert.alertDate}" />
													<button
														class="alertConfirm btn btn-gray-purple btn-flat btn-rect btn-mini"
														type="button" value="${pAlert.postCode}">
														<i class="icon-check"></i> 게시글 확인
													</button>
												</form></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%-- 댓글 알림 부분 --%>
						<div id="reply">
							<table class="table table-simple table-hover">
								<thead>
									<tr>
										<th class="alert_content">알림 내용</th>
										<th class="alert_date" style="text-align: center;">알림 시간</th>
										<th class="alert_confirm" style="text-align: center;">알림
											확인</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="rAlert" items="${alertReplyList}">
										<tr class="w${rAlert.replyCode}">
											<td class="alert_content"><c:if test="${mAlert.confirm == 0 }"><span style="color: red; font-size: small;">new</span></c:if>&nbsp;${rAlert.replyMemberName}님이
													${rAlert.name}님의 게시글에 댓글을 달았습니다.</td>
											<td class="alert_date">${rAlert.alertDate}</td>

											<td class="alert_confirm">
												<form action="alertconfirm.htm" id="form${rAlert.replyCode}" method="post">
													<input type="hidden" name="memberCode" id="m${rAlert.replyCode}" value="${rAlert.memberCode}" /> 
													<input type="hidden" name="tableNameCode" id="t${rAlert.replyCode}" value="${rAlert.tableNameCode}" /> 
													<input type="hidden" name="codeName" id="c${rAlert.replyCode}" value="${rAlert.replyCode}" /> 
													<input type="hidden" name="postCode" id="postCode" value="${rAlert.postCode}" />
													<input type="hidden" name="alertDate" id="d${rAlert.replyCode}" value="${rAlert.alertDate}" />
													<button
														class="alertConfirm btn btn-gray-purple btn-flat btn-rect btn-mini"
														type="button" value="${rAlert.replyCode}">
														<i class="icon-check"></i> 댓글 확인
													</button>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<%-- Tab Menu Dropdown --%>
					<%--
				<script data-jui="#tab_alert" data-tpl="menu" type="text/template">
					<div class="dropdown">
						<div class="anchor"></div>
						<ul style="width: 120px">
							<li>친구알림 삭재</li>
							<li>댓글알림 삭제</li>
							<li>게시글알림 삭제</li>
						</ul>
					</div>
				</script> --%>
				</div>
			</div>
		</div>
	</div>

	<%-- 게시글 뷰 --%>
	<!-- <div class="msgbox msgbox-detail" style="width: 350px; height: 150px;">
		<div class="head">
			<span id="name" class="name"></span>
			<div id="postDate" class="detail"></div>
			<a href="#" class='close'><i class='icon-exit icon-gray'></i></a>
		</div>
		<div class="body">
			<p id="postContent" class="postContent"></p>
		</div>
	</div> -->
</body>
</html>