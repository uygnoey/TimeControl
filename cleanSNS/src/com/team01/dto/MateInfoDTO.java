// 내 친구 리스트 뿌리줄때 필요 한 정보 DTO
package com.team01.dto;

public class MateInfoDTO {

	private int mateMemberCode, mateGroupCode, openLimitedCode;	// 친구멤버코드, 친구분류코드, 공개범위
	private String name, photoURL; // 이름, 프로필사진
	
	public int getOpenLimitedCode() {
		return openLimitedCode;
	}
	public void setOpenLimitedCode(int openLimitedCode) {
		this.openLimitedCode = openLimitedCode;
	}
	public int getMateMemberCode() {
		return mateMemberCode;
	}
	public void setMateMemberCode(int mateMemberCode) {
		this.mateMemberCode = mateMemberCode;
	}
	public int getMateGroupCode() {
		return mateGroupCode;
	}
	public void setMateGroupCode(int mateGroupCode) {
		this.mateGroupCode = mateGroupCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	
}
