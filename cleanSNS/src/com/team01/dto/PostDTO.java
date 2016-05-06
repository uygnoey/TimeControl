package com.team01.dto;

public class PostDTO {

	private String postContent, postDate,name,photoURL,postphotoURL,mateName; 		// 게시내용, 게시날짜,이름,프로필 사진url,게시사진url
	
	private int postCode, memberCode, pungCode 	// 게시글코드, 멤버코드, 펑코드
			  , likeCount, hateCount	// 좋아요카운트, 싫어요카운트
			  , delHide,pungCheck; 						// 삭제여부 //펑 적용 여부
	private String mateCode; //받은사람코드
	
	private String year,month,date,hour;
	// setter/getter 구성
	
	public String getName() {
		return name;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
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
	public String getPostphotoURL() {
		return postphotoURL;
	}
	public void setPostphotoURL(String postphotoURL) {
		this.postphotoURL = postphotoURL;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public int getPungCode() {
		return pungCode;
	}
	public void setPungCode(int pungCode) {
		this.pungCode = pungCode;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getHateCount() {
		return hateCount;
	}
	public void setHateCount(int hateCount) {
		this.hateCount = hateCount;
	}
	public String getMateCode() {
		return mateCode;
	}
	public void setMateCode(String mateCode) {
		this.mateCode = mateCode;
	}
	public int getDelHide() {
		return delHide;
	}
	public void setDelHide(int delHide) {
		this.delHide = delHide;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String maonth) {
		this.month = maonth;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public int getPungCheck() {
		return pungCheck;
	}
	public void setPungCheck(int pungCheck) {
		this.pungCheck = pungCheck;
	}
	


	
}
