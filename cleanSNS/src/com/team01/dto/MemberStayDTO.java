package com.team01.dto;

// 멤버가입대기
public class MemberStayDTO
{
	private int memberStayCode;
	// 대기코드
	
	private String email, name, pw;
	// 이메일, 이름, 비밀번호

	// getter / setter 구성
	public int getMemberStayCode()
	{
		return memberStayCode;
	}

	public void setMemberStayCode(int memberStayCode)
	{
		this.memberStayCode = memberStayCode;
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
