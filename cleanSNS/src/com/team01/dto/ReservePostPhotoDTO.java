/*예약게시글 사진
Desc t_reservePostPhoto
이름          널        유형            
----------- -------- ------------- 
RESERVECODE NOT NULL NUMBER        예약코드      
PHOTOURL    NOT NULL VARCHAR2(100) 멤버코드
 */
package com.team01.dto;

public class ReservePostPhotoDTO {
	private int reserveCode;
	private String photoURL;
	
	public int getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(int reserveCode) {
		this.reserveCode = reserveCode;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
}
