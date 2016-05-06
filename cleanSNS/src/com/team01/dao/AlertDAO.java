package com.team01.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.AlertDTO;

public class AlertDAO implements IAlertDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 친구 알림 부분
	@Override
	public ArrayList<AlertDTO> mateAlertSelect(int MemberCode)
			throws SQLException {

		ArrayList<AlertDTO> mateAlertList = new ArrayList<AlertDTO>();

		Connection conn = dataSource.getConnection();

		String sql = "select memberCode, name, mateMemberCode, mateName, alertDate, "
				+ "confirm, tableNameCode, tableName, delHide from view_alert_m "
				+ "where memberCode = ? and delHide = 0 "
				+ "order by alertDate desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, MemberCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			AlertDTO alert = new AlertDTO();

			alert.setMemberCode(rs.getInt("memberCode"));
			alert.setName(rs.getString("name"));
			alert.setMateCode(rs.getInt("mateMemberCode"));
			alert.setMateName(rs.getString("mateName"));
			alert.setAlertDate(rs.getString("alertDate"));
			alert.setConfirm(rs.getInt("confirm"));
			alert.setTableNameCode(rs.getInt("tableNameCode"));
			alert.setTableName(rs.getString("tableName"));
			alert.setDelHide(rs.getInt("delHide"));

			mateAlertList.add(alert);

		}

		rs.close();
		pstmt.close();
		conn.close();

		return mateAlertList;

	}

	// 게시글 알림 부분
	@Override
	public ArrayList<AlertDTO> postAlertSelect(int memberCode)
			throws SQLException {

		ArrayList<AlertDTO> postAlertList = new ArrayList<AlertDTO>();

		Connection conn = dataSource.getConnection();

		String sql = "select memberCode, name, mateMemberCode, mateName, "
				+ "postCode, alertDate, confirm, tableNameCode, tableName, "
				+ "delHide from view_alert_post "
				+ "where memberCode = ? and delHide = 0 "
				+ "order by alertDate desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			AlertDTO postAlert = new AlertDTO();

			postAlert.setMemberCode(rs.getInt("memberCode"));
			postAlert.setName(rs.getString("name"));
			postAlert.setMateCode(rs.getInt("mateMemberCode"));
			postAlert.setMateName(rs.getString("mateName"));
			postAlert.setPostCode(rs.getInt("postCode"));
			postAlert.setAlertDate(rs.getString("alertDate"));
			postAlert.setConfirm(rs.getInt("confirm"));
			postAlert.setTableNameCode(rs.getInt("tableNameCode"));
			postAlert.setTableName(rs.getString("tableName"));
			postAlert.setDelHide(rs.getInt("delHide"));

			postAlertList.add(postAlert);

		}

		rs.close();
		pstmt.close();
		conn.close();

		return postAlertList;

	}

	// 댓글 알림 부분
	@Override
	public ArrayList<AlertDTO> replyAlertSelect(int memberCode)
			throws SQLException {

		ArrayList<AlertDTO> replyAlertList = new ArrayList<AlertDTO>();

		Connection conn = dataSource.getConnection();

		String sql = "select memberCode, memberName, replyCode, postCode, replyMemberCode, "
				+ "replyMemberName, alertDate, confirm, tableNameCode, tableName, delhide "
				+ "from view_alert_reply "
				+ "where memberCode = ? and delHide = 0 "
				+ "order by alertDate desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			AlertDTO replyAlert = new AlertDTO();

			replyAlert.setMemberCode(rs.getInt("memberCode"));
			replyAlert.setName(rs.getString("memberName"));
			replyAlert.setReplyCode(rs.getInt("replyCode"));
			replyAlert.setPostCode(rs.getInt("postCode"));
			replyAlert.setReplyMemberCode(rs.getInt("replyMemberCode"));
			replyAlert.setReplyMemberName(rs.getString("replyMemberName"));
			replyAlert.setAlertDate(rs.getString("alertDate"));
			replyAlert.setConfirm(rs.getInt("confirm"));
			replyAlert.setTableNameCode(rs.getInt("tableNameCode"));
			replyAlert.setTableName(rs.getString("tableName"));
			replyAlert.setDelHide(rs.getInt("delHide"));

			replyAlertList.add(replyAlert);

		}

		rs.close();
		pstmt.close();
		conn.close();

		return replyAlertList;
	}

	// 알림 확인
	@Override
	public int confirmAlert(int memberCode, int tableNameCode, int codeName,
			String alertDate) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_alert set confirm = 1 "
				+ "where memberCode = ? " + "and tableNameCode = ? "
				+ "and codeName = ? "
				+ "and alertDate = to_date(?, 'yyyy-mm-dd HH24:mi:ss')";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberCode);
		pstmt.setInt(2, tableNameCode);
		pstmt.setInt(3, codeName);
		pstmt.setString(4, alertDate);

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;

	}

	// 알림 관련 부분 삭제 여부 체크
	@Override
	public boolean delCheck(int memberCode, int tableNameCode, int codeName, String alertDate)
			throws SQLException {

		boolean check = false;

		int delCheck = 0;

		Connection conn = dataSource.getConnection();

		String sql = "";

		if (tableNameCode == 0)
			sql = "select memberStayCode from t_member where memberCode = ?";
		else if (tableNameCode == 1)
			sql = "select delHide from t_post where postCode = ?";
		else if (tableNameCode == 2)
			sql = "select delHide from t_reply where replyCode = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		System.out.println(sql + codeName);

		pstmt.setInt(1, codeName);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			delCheck = rs.getInt(1);

			System.out.println("delCheck : " + delCheck);

			if (delCheck == 1) {
				
				check = true;
				
				String sql2 = "update t_alert set confirm = 1, delHide = 1 "
						+ "where memberCode = ? " + "and tableNameCode = ? "
						+ "and codeName = ? "
						+ "and alertDate = to_date(?, 'yyyy-mm-dd HH24:mi:ss')";

				PreparedStatement pstmt2 = conn.prepareStatement(sql2);

				pstmt2.setInt(1, memberCode);
				pstmt2.setInt(2, tableNameCode);
				pstmt2.setInt(3, codeName);
				pstmt2.setString(4, alertDate);

				int result = pstmt2.executeUpdate();
				
				System.out.println("sql2 : " + sql2);
				System.out.println("memberCode : " + memberCode);
				System.out.println("tableNameCode : " + tableNameCode);
				System.out.println("codeName : " + codeName);
				System.out.println("alertDate : " + alertDate);
				System.out.println("result : " + result);

				pstmt2.close();
				
			}

		}

		rs.close();
		pstmt.close();
		conn.close();

		return check;

	}

	// 알림 6개월 만료 삭제
	@Override
	public int autoDelAlert() throws SQLException {
		
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "{call prc_alert}";
		
		CallableStatement cstmt = conn.prepareCall(sql);
		
		result = cstmt.executeUpdate();
		
		cstmt.close();
		conn.close();
		
		return result;
		
	}

	// 알림 카운트
	@Override
	public int countAlert(int memberCode) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "select count(*) as count from t_alert where memberCode = ? and confirm = 0";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			result = rs.getInt("count");
		}

		rs.close();
		pstmt.close();
		conn.close();

		return result;

	}
	
	

}
