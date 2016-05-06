package com.team01.dto;

/**
 * @file AlertDTO.java
 * @author YeonGyu
 * @class AlertDTO
 */

/*
 * desc t_alert
 * 이름            널        유형     
 * ------------- -------- ------ 
 * MEMBERCODE    NOT NULL NUMBER 
 * TABLENAMECODE NOT NULL NUMBER 
 * CODENAME      NOT NULL NUMBER 
 * ALERTDATE     NOT NULL DATE   
 * CONFIRM       NOT NULL NUMBER 
 * DELHIDE                NUMBER     
 * 
 * desc view_alert_member
 * 이름            널        유형           
 * ------------- -------- ------------ 
 * MEMBERCODE    NOT NULL NUMBER       
 * NAME          NOT NULL VARCHAR2(20) 
 * TABLENAMECODE NOT NULL NUMBER       
 * TABLENAME     NOT NULL VARCHAR2(40) 
 * MATECODE      NOT NULL NUMBER       
 * MATENAME      NOT NULL VARCHAR2(20) 
 * ALERTDATE     NOT NULL DATE         
 * CONFIRM       NOT NULL NUMBER       
 * DELHIDE                NUMBER       
 * 
 * desc view_alert_post
 * 이름            널        유형           
 * ------------- -------- ------------ 
 * MEMBERCODE    NOT NULL NUMBER       
 * NAME          NOT NULL VARCHAR2(20) 
 * TABLENAMECODE NOT NULL NUMBER       
 * TABLENAME     NOT NULL VARCHAR2(40) 
 * POSTCODE      NOT NULL NUMBER       
 * ALERTDATE     NOT NULL DATE         
 * CONFIRM       NOT NULL NUMBER       
 * DELHIDE                NUMBER       
 * 
 * desc view_alert_reply
 * 이름              널        유형           
 * --------------- -------- ------------ 
 * MEMBERCODE      NOT NULL NUMBER       
 * MEMBERNAME      NOT NULL VARCHAR2(20) 
 * REPLYCODE       NOT NULL NUMBER       
 * REPLYMEMBERCODE NOT NULL NUMBER       
 * REPLYMEMBERNAME NOT NULL VARCHAR2(20) 
 * ALERTDATE       NOT NULL DATE         
 * CONFIRM         NOT NULL NUMBER       
 * DELHIDE                  NUMBER   
 */

public class AlertDTO {

	// 멤버코드, 참조테이블코드, 참조내용코드, 삭제여부
	int memberCode, tableNameCode, codeName, mateCode, postCode, replyCode,
			replyMemberCode, confirm, delHide;
	// 이름, 알림받은날짜
	String name, tableName, mateName, replyMemberName, alertDate;

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public int getTableNameCode() {
		return tableNameCode;
	}

	public void setTableNameCode(int tableNameCode) {
		this.tableNameCode = tableNameCode;
	}

	public int getCodeName() {
		return codeName;
	}

	public void setCodeName(int codeName) {
		this.codeName = codeName;
	}

	public int getMateCode() {
		return mateCode;
	}

	public void setMateCode(int mateCode) {
		this.mateCode = mateCode;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
	}

	public int getReplyMemberCode() {
		return replyMemberCode;
	}

	public void setReplyMemberCode(int replyMemberCode) {
		this.replyMemberCode = replyMemberCode;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getReplyMemberName() {
		return replyMemberName;
	}

	public void setReplyMemberName(String replyMemberName) {
		this.replyMemberName = replyMemberName;
	}

	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}

}