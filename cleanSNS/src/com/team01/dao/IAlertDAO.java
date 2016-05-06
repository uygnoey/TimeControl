package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.AlertDTO;

public interface IAlertDAO {

	// 친구 관련 알림
	public ArrayList<AlertDTO> mateAlertSelect(int memberCode) throws SQLException;
	
	// 게시글 관련 알림
	public ArrayList<AlertDTO> postAlertSelect(int memberCode) throws SQLException;
	
	// 댓글 관련 알림
	public ArrayList<AlertDTO> replyAlertSelect(int memberCode) throws SQLException;
	
	// 알림 확인 처리
	public int confirmAlert(int memberCode, int tableNameCode, int codeName, String alertDate) throws SQLException;
	
	// 알림 관련 부분 삭제 여부 판단
	public boolean delCheck (int memberCode, int tableNameCode, int codeName, String alertDate) throws SQLException;
	
	// 6개월 기간 만료 알림 삭제 처리
	public int autoDelAlert() throws SQLException;
	
	// 알림 카운트
	public int countAlert(int memberCode) throws SQLException;
}
