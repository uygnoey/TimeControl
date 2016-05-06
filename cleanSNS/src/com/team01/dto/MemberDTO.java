package com.team01.dto;

// 멤버
public class MemberDTO
{
	private int memberCode, memberStateCode, openLimitedCode;
	// 멤버코드, 멤버상태코드, 공개범위코드
	
	private String email, name, pw;
	// 이메일, 이름, 비밀번호
	
	// getter / setter 구성
	public int getMemberCode()
	{
		return memberCode;
	}
	public void setMemberCode(int memberCode)
	{
		this.memberCode = memberCode;
	}
	public int getMemberStateCode()
	{
		return memberStateCode;
	}
	public void setMemberStateCode(int memberStateCode)
	{
		this.memberStateCode = memberStateCode;
	}
	public int getOpenLimitedCode()
	{
		return openLimitedCode;
	}
	public void setOpenLimitedCode(int openLimitedCode)
	{
		this.openLimitedCode = openLimitedCode;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPw()
	{
		return pw;
	}
	public void setPw(String pw)
	{
		this.pw = pw;
	}
}
