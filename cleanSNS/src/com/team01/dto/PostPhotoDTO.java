package com.team01.dto;

public class PostPhotoDTO {

	private int postCode;		// 게시글코드
	private String photoURL;	// 사진
	
	// setter/getter 구성
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	
	
}
