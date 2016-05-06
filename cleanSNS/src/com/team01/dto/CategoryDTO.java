package com.team01.dto;

public class CategoryDTO {

	private int memberCode, ca2Check;	// 멤버코드, 카테고리2(확인코드)
	private String categoryTitle;		// 카테고리1제목
	
	// setter/getter 구성
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public int getCa2Check() {
		return ca2Check;
	}
	public void setCa2Check(int ca2Check) {
		this.ca2Check = ca2Check;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
	
}
