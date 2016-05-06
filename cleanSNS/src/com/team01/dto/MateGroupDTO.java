package com.team01.dto;

// 친구 분류 
public class MateGroupDTO
{
	private int mateGroupCode;
	// 친구 분류 코드
	
	private String mateState;
	// 친구 상태 (친구/아는사람)

	// getter / setter 구성
	public int getMateGroupCode()
	{
		return mateGroupCode;
	}

	public void setMateGroupCode(int mateGroupCode)
	{
		this.mateGroupCode = mateGroupCode;
	}

	public String getMateState()
	{
		return mateState;
	}

	public void setMateState(String mateState)
	{
		this.mateState = mateState;
	}
}
