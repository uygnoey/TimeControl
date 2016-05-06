package com.team01.dto;

public class LikeDTO {

	private int postCode, memberCode;	// 게시글코드, 멤버코드

	//setter/getter 구성
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
}
