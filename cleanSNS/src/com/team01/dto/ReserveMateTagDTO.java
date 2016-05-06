/*예약친구태그
desc t_reserveMateTag
이름          널        유형     
----------- -------- ------ 
RESERVECODE NOT NULL NUMBER 예약코드
MEMBERCODE  NOT NULL NUMBER 멤버코드
 */
package com.team01.dto;

public class ReserveMateTagDTO {
	private int reserveCode,memberCode;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
