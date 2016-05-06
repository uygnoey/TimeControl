package com.team01.dto;

public class MateStayDTO {
	
	private int mateStayCode, memberCode, mateMemberCode;
	// 참조내용코드(친구대기코드), 멤버코드, 친구코드(멤버코드)

	public int getMateStayCode() {
		return mateStayCode;
	}

	public void setMateStayCode(int mateStayCode) {
		this.mateStayCode = mateStayCode;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public int getMateMemberCode() {
		return mateMemberCode;
	}

	public void setMateMemberCode(int mateMemberCode) {
		this.mateMemberCode = mateMemberCode;
	}
	
	

}
