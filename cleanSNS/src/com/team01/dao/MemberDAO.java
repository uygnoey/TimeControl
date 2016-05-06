package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.MemberDTO;

public class MemberDAO implements IMemberDAO {
	private DataSource dataSource; // 연결

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 회원가입
	@Override
	public int memberInsert(MemberDTO memberDTO) throws SQLException {
		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "insert into t_member(memberCode, email, name, pw)"
				+ " values (memberSeq.nextval, ?, ?"
				+ ", CRYPTIT.ENCRYPT(?, ?))";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, memberDTO.getEmail());
		pstmt.setString(2, memberDTO.getName());
		pstmt.setString(3, memberDTO.getPw());
		pstmt.setString(4, memberDTO.getEmail());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	// 정보 업데이트
	@Override
	public int memberUpdate(MemberDTO memberDTO) throws SQLException {
		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_member"
				+ " set name=?, pw=CRYPTIT.ENCRYPT(?, ?)"
				+ " where memberCode = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, memberDTO.getName());
		pstmt.setString(2, memberDTO.getPw());
		pstmt.setString(3, memberDTO.getEmail());
		pstmt.setInt(4, memberDTO.getMemberCode());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	// 회원탈퇴
	@Override
	public int memberRemove(MemberDTO memberDTO) throws SQLException {
		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_member set memberStateCode=1 where memberCode=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberDTO.getMemberCode());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	
	
	// 중복체크  - 비밀번호 찾기 할 때.
	@Override
	public int emailCheck(String email) throws SQLException {

		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "select count(*) as count from t_member where email = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
			result = rs.getInt("count");
		
		return result;
		
	}
	
	// 중복체크 2 - 회원가입 할 때.
		@Override
		public int emailCheck2(String email) throws SQLException {
			int result = 0;

			Connection conn = dataSource.getConnection();

			String sql1 = "select count(*) as count from t_member where email=?";
			String sql2 = "select count(*) as count from t_memberStay where email=?";

			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			
			pstmt1.setString(1, email);
			pstmt2.setString(1, email);
			
			ResultSet rs1 = pstmt1.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();

			while (rs1.next()) 
			{
				result = rs1.getInt("count");
			}

			while (rs2.next()) 
			{
				result += rs2.getInt("count");
			}
			rs1.close();
			pstmt1.close();
			rs2.close();
			pstmt2.close();
			conn.close();

			return result;
		}
		
		
	// 공개 범위 업데이트
	@Override
	public int setopenLimited(int memberCode, int openLimitedCode)
			throws SQLException {
		String sql = "update t_member set openLimitedCode=? where memberCode=?";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, openLimitedCode);
		stmt.setInt(2, memberCode);

		int result = 0;
		result = stmt.executeUpdate();

		return result;
	}

	// 비밀번호 업데이트
	@Override
	public int passwordUpdate(String email, String password)
			throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_member set pw = CRYPTIT.ENCRYPT(?, ?) where email = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, password);
		pstmt.setString(2, email);
		pstmt.setString(3, email);

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;

	}
	
	//프로필 사진 가져오기
	public String getMyPhoto(int memberCode)
	{
		String result = null;

		Connection conn;
		try {
			conn = dataSource.getConnection();
			String sql = "select photoURL from t_myphoto where memberCode = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberCode);	

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getString("photoURL");

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return result;
	}

}
