<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TimeOut SNS</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="css/newsfeed.css" />
<link rel="stylesheet" href="jui/jui.min.css" />
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/jui.min.js"></script>
<script type="text/javascript" src="jui/js/base.js"></script>
<script type="text/javascript" src="jui/js/core.js"></script>
<script type="text/javascript" src="jui/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jui/js/ui/modal.js"></script>
<script type="text/javascript" src="jui/js/uix/window.js"></script>
<script type="text/javascript" src="js/jquery.popupWindow.js"></script>
<script type="text/javascript" src="js/reply.js"></script>
<script type="text/javascript">
jui.ready([ "uix.window" ], function(window) {
	replyEditFormPop = window("#replyEditPopup", { 
		width: 355, 
		height: 200, 
		left: 820, 
		top: 135
	});
});


	// 명진 추가
	function likeInsert(postcode) {
		var postCode = document.getElementById("postCode").value;
		$("#like_" + postCode).submit();

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
				<%-- 게시글 뷰 --%>
				<div id="" class="category1Card">
					<div id="content_${post.postCode }">
						<c:choose>
							<c:when test="${post.pungCheck ==0}">
								<%-- 게시글 카드 --%>
								<table class="table table-simple table-hover">
									<%-- 멤버 이름, 작성일 --%>
									<thead>
										<tr>
											<th colspan="2">
												<div class="h5">
													<c:choose>
														<c:when test="${post.photoURL != null}">
															<img src="${post.photoURL}"
																style="width: 30px; height: auto;" />
														</c:when>
														<c:otherwise>
															<img src="img/logo.PNG"
																style="width: 30px; height: auto;" />
														</c:otherwise>
													</c:choose>
													<a href="matestory.htm?mateCode=${post.memberCode }">${post.name}</a>
													<c:if test="${post.mateName != null }">
														<strong> <font color="#078196"><font
																size="1.5px">&nbsp;from</font> ${post.mateName }</font></strong>
													</c:if>
												</div>

											</th>
											<th valign="bottom" style="width: 120px"><c:if
													test="${post.pungCode != 0}">
													<font color="red"><strong>${post.pungCode}
															분 후 펑예정</strong></font>
													<br />
												</c:if>
												<div class="h6">${post.postDate}</div></th>

											<%-- 주희 --%>
											<th valign="bottom" style="padding-left: 70px;"><c:if
													test="${post.memberCode == memberCode}">
													<form action="updatepost.htm" method="post"
														id="delForm_${post.postCode }" name="delForm">
														<a href="#" onclick="updtBtn('${post.postCode }')"><i
															class="icon-unorderedlist"
															style="font-size: 16px; color: Purple;"></i></a> <a href="#"
															onclick="deleteSubmit('${post.postCode }')"><i
															class="icon-unorderedlist"
															style="font-size: 16px; color: black;"></i></a> <input
															type="hidden" id="delPostCode" name="delPostCode"
															value="${post.postCode }">
													</form>
												</c:if></th>
										</tr>
									</thead>
									<tbody>
										<%-- 게시글 표시 부분 --%>
										<tr>
											<td id="contentsTd_${post.postCode }" colspan="4"
												style="padding: 10px;">
												<div id="contentsDiv_${post.postCode }">
													<c:forEach var="mate" items="${mateList }">
														<c:if test="${post.postCode == mate.postCode }">
															<strong><font color="#078196">@${mate.name }</font></strong>
														</c:if>
													</c:forEach>
													${post.postContent} <br />
													<c:if test="${post.postphotoURL  != null}">
														<img src="${post.postphotoURL }"
															style="max-width: 400px; height: auto;">
													</c:if>
												</div>
											</td>
										</tr>
										<%-- 좋아요 싫어요 - 명진 수정--%>
										<%-- 좋아요 싫어요 - 명진 수정--%>
										<tr>
											<td><img src="img/like.png" alt="" width="10"
												height="10" /> <c:set var="likeCheck" value="0"></c:set> <c:forEach
													var="like" items="${likeList }">
													<c:if test="${like.postCode == post.postCode}">
														<c:set var="likeCheck" value="1"></c:set>
													</c:if>
												</c:forEach> <c:choose>
													<c:when test="${likeCheck==1}">
														<a
															href="likeCancel.htm?check=${check }&postCode=${post.postCode}&tableNameCode=1&codeName=${post.postCode}&alertDate=${alertDate}">좋아요
															취소</a>
													</c:when>
													<c:otherwise>
														<a
															href="like.htm?check=${check }&postCode=${post.postCode}&tableNameCode=1&codeName=${post.postCode}&alertDate=${alertDate}">좋아요</a>
													</c:otherwise>
												</c:choose></td>
											<td id="likeCount">${post.likeCount}</td>

											<td><img src="img/dislike.png" alt="" width="10"
												height="10" /> <c:set var="hateCheck" value="0"></c:set> <c:forEach
													var="hate" items="${hateList}">
													<c:if test="${hate.postCode == post.postCode}">
														<c:set var="hateCheck" value="1"></c:set>
													</c:if>
												</c:forEach> <c:choose>
													<c:when test="${hateCheck == 1}">
														<a href="hateCancel.htm?check=${check}&postCode=${post.postCode}&tableNameCode=1&codeName=${post.postCode}&alertDate=${alertDate}">싫어요 취소</a>
													</c:when>
													<c:otherwise>
														<a href="hate.htm?check=${check}&postCode=${post.postCode}&tableNameCode=1&codeName=${post.postCode}&alertDate=${alertDate}">싫어요</a>
													</c:otherwise>
												</c:choose></td>

											<td id="hateCount">${post.hateCount}</td>
										</tr>
										<%-- 댓글 달기 --%>
										<tr>
											<td colspan="4">
												<form action="replyinsert.htm"
													id="replyForm${post.postCode}" method="post">
													<input type="hidden" id="postCode${post.postCode}" name="postCode"
														value="${post.postCode}" />
													<div class="group group-rect" style="display: inline;">
														<textarea name="replyContent"
															id="replyContent${post.postCode}"
															class="replyContent input" cols="" rows="1"></textarea>
														<button id="replySub" class="replySub btn"
															value="${post.postCode}" type="button">댓글달기</button>
													</div>
												</form>
											</td>
										</tr>
										<%-- 댓글 뷰 --%>
										<c:forEach var="reply" items="${replyList}">
											<c:if test="${reply.postCode == post.postCode}">
												<tr>
													<td colspan="4">
														<table class="table table-simple table-hover">
															<%-- 댓글 글쓴 사람 이름, 댓글 작성일 --%>
															<thead>
																<tr>
																	<th
																		style="background: #F8FFFF; font-size: 9px; border-bottom: 0px">
																		<div class="h6" id="${reply.memberCode}-${reply.name}">${reply.name}</div>
																	</th>
																	<th
																		style="background: #F8FFFF; font-size: 9px; border-bottom: 0px">
																		<div class="h6">${reply.replyDate}</div>
																	</th>
																</tr>
															</thead>
															<%-- 댓글 내용 --%>
															<tbody>
																<tr>
																	<td colspan="2" id="replyContentView"
																		class="replyContentView">${reply.replyContent}<c:if
																			test="${reply.memberCode == memberCode }">
																			<div class="group replyEditDel">
																				<button value="${reply.replyCode}"
																					class="replyEdit btn btn-small" type="button">
																					<i class="icon-edit"></i>
																				</button>
																				<form action="replyDelete.htm" method="post"
																					style="display: inline-block;"
																					id="replyDel${reply.replyCode}">
																					<input type="hidden" name="replyCode"
																						id="replyCode${reply.replyCode}" value="${reply.replyCode}"
																						class="replyCode" />
																					<button value="${reply.replyCode}" id="replyDel"
																						class="replyDel btn btn-small" type="button">
																						<i class="icon-trashcan"></i>
																					</button>
																				</form>
																			</div>
																		</c:if>
																	</td>
																</tr>
															</tbody>
														</table>

													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<%-- 펑게시일경우 --%>
							<c:otherwise>
								<table class="table table-simple table-hover">

									<thead>
										<tr>
											<th colspan="2"
												style="background: #980000; border-top: 1px solid #FF0000; border-bottom: 1px solid #FF0000">
												<div class="h5" style="color: white;">
													<c:choose>
														<c:when test="${post.photoURL != null}">
															<img src="${post.photoURL}"
																style="width: 30px; height: 30px;" />
														</c:when>
														<c:otherwise>
															<img src="img/logo.PNG"
																style="width: 30px; height: 30px;" />
														</c:otherwise>
													</c:choose>
													${post.name}
													<c:if test="${post.mateName != null }">
														<strong> <font color="white"><font
																size="1.5px">&nbsp;from</font> ${post.mateName }</font></strong>
													</c:if>
												</div>

											</th>
											<th valign="bottom"
												style="width: 100px; background: #980000; border-top: 1px solid #FF0000; border-bottom: 1px solid #FF0000">
												<div style="color: white; font-size: 30px">★펑★</div>

											</th>

											<%-- 주희 --%>
											<th valign="bottom"
												style="padding-left: 70px; background: #980000; border-top: 1px solid #FF0000; border-bottom: 1px solid #FF0000">

											</th>
										</tr>
									</thead>
									<tbody>
										<%-- 게시글 표시 부분 --%>
										<tr>
											<td colspan="4" style="padding: 10px;"><c:forEach
													var="mate" items="${mateList }">
													<c:if test="${post.postCode == mate.postCode }">
														<strong><font color="#078196">@${mate.name }</font></strong>
													</c:if>
												</c:forEach> ${post.postContent} <br /> <c:if
													test="${post.postphotoURL  != null}">
													<img src="${post.postphotoURL }"
														style="max-width: 400px; height: auto;">
												</c:if></td>
										</tr>

									</tbody>
								</table>

							</c:otherwise>
						</c:choose>
					</div>
					<br /> <br />
				</div>
				<div id="replyEditPopup" class="replyEditPopup msgbox msgbox-detail">
					<div class="head">
						댓글 수정
						<a href="#" class='close'><i class='icon-exit icon-gray'></i></a>
					</div>
					<div class="body">
						<textarea name="replyContentEdit" id="replyContentEdit" cols="55" rows="5"></textarea>
						<button type="button" id="replyEditSubmit" class="btn btn-gray-black btn-flat btn-rect">수정</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>