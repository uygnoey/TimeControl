package com.team01.dto;

/**
 * @file ReplyDTO.java
 * @author YeonGyu
 * @class ReplyDTO
 */

/*
 * desc view_reply 
 * 이름 널 유형 
 * ------------ -------- -------------- 
 * REPLYCODE 	NOT NULL NUMBER 
 * POSTCODE 	NOT NULL NUMBER 
 * MEMBERCODE 	NOT NULL NUMBER 
 * NAME			NOT NULL VARCHAR2(20) 
 * PHOTOURL 			 VARCHAR2(100) 
 * REPLYCONTENT NOT NULL VARCHAR2(4000)
 * REPLYDATE 	NOT NULL DATE DELHIDE NUMBER
 */

public class ReplyDTO {

	// 댓글코드, 멤버코드, 게시글코드, 삭제여부
	int replyCode, postCode, memberCode, delHide;
	
	// 댓글내용, 댓글작성날짜
	String name, photoURL, replyContent, replyDate;

	// setter / getter 구성
	public int getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public int getDelHide() {
		return delHide;
	}

	public void setDelHide(int delHide) {
		this.delHide = delHide;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

}
