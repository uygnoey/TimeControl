package com.team01.dto;

public class MateTagDTO {

	private int postCode, memberCode;	// 게시글코드. (태그된)멤버코드	
	private String name;
	
	// setter/getter 구성	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
}
