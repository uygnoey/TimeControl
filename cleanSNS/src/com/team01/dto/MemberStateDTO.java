package com.team01.dto;

// 멤버상태
public class MemberStateDTO
{
	private int memberStateCode;
	// 멤버상태코드
	
	private String memberState;
	// 멤버상태 (회원/비회원)

	// getter / setter 구성
	public int getMemberStateCode()
	{
		return memberStateCode;
	}

	public void setMemberStateCode(int memberStateCode)
	{
		this.memberStateCode = memberStateCode;
	}

	public String getMemberState()
	{
		return memberState;
	}

	public void setMemberState(String memberState)
	{
		this.memberState = memberState;
	}
}
