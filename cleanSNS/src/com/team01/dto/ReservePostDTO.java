/*예약게시글 테이블
DESC t_reservePost
이름          널        유형             
----------- -------- -------------- 
RESERVECODE NOT NULL NUMBER   		예약코드      
MEMBERCODE  NOT NULL NUMBER   		멤버코드     
POSTCONTENT NOT NULL VARCHAR2(4000) 게시내용
RESERVEDATE NOT NULL DATE      		예약날짜    
PUNGCODE             NUMBER     	펑코드    
RESERVETIME NOT NULL DATE       	예약시간    
MATECODE             NUMBER     	받는 사람(멤머코드)    
DELHIDE              NUMBER     	삭제 여부
 */
package com.team01.dto;

public class ReservePostDTO {
	
	private int reserveCode,memberCode,pungCode,delHide;
	private String postContent,reserveDate,reserveTime,name,photoURL,mateName,postphotoURL,mateCode;
	
	public int getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(int reserveCode) {
		this.reserveCode = reserveCode;
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
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
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

	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getPostphotoURL() {
		return postphotoURL;
	}
	public void setPostphotoURL(String postphotoURL) {
		this.postphotoURL = postphotoURL;
	}
	
}
