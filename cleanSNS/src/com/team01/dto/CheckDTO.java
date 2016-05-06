package com.team01.dto;

/**
 * @file CheckDTO.java
 * @author HP
 * @class CheckDTO
 */

/*
 * desc t_check
 * 이름         널        유형          
 * ---------- -------- ----------- 
 * CHECKCODE  NOT NULL NUMBER      
 * CHECKSTATE NOT NULL VARCHAR2(1)
 */

public class CheckDTO {

	// 확인코드
	int checkCode; 
	// 확인 상태
	String checkState; 

	// getter / setter 구성
	public int getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(int checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

}
