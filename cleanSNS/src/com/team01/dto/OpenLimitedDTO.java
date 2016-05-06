package com.team01.dto;

/**
 * @file OpenLimitedDTO.java
 * @author YeonGyu
 * @class OpenLimitedDTO
 */

public class OpenLimitedDTO {

	// 공개범위코드
	int openLimitedCode;
	// 공개범위
	String range;

	public int getOpenLimitedCode() {
		return openLimitedCode;
	}

	public void setOpenLimitedCode(int openLimitedCode) {
		this.openLimitedCode = openLimitedCode;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

}
