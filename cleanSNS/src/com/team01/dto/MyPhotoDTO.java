package com.team01.dto;

// 프로필 사진
public class MyPhotoDTO
{
	private int memberCode;
	// 멤버 코드 
	
	private String photoURL;
	// 사진경로

	// getter / setter 구성
	public int getMemberCode()
	{
		return memberCode;
	}

	public void setMemberCode(int memberCode)
	{
		this.memberCode = memberCode;
	}

	public String getPhotoURL()
	{
		return photoURL;
	}

	public void setPhotoURL(String photoURL)
	{
		this.photoURL = photoURL;
	}
}
