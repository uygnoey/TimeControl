package com.team01.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.team01.dto.MemberDTO;
import com.team01.dto.MemberStayDTO;

public class MemberStayDAO implements IMemberStayDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 회원 가입 대기 insert
	@Override
	public int insert(MemberStayDTO memberStay) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "insert into t_memberStay(memberStayCode, email, name, pw) "
				+ "values(memberStaySeq.nextval, ?, ?, CRYPTIT.ENCRYPT(?, ?))";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, memberStay.getEmail());
		pstmt.setString(2, memberStay.getName());
		pstmt.setString(3, memberStay.getPw());
		pstmt.setString(4, memberStay.getEmail());

		System.out.println("멤버대기테이블"+memberStay.getEmail());
		
		result = pstmt.executeUpdate();

		return result;

	}

	// 이메일 성공시 멤버 테이블로 이동
	@Override
	public int registerSuccess(String email) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		// 인증 성공시 멤버대기 테이블에서 멤버 테이블로 이동 및 삭제 프로시저 호출
		String sql = "{call prc_emailSuccess(?)}";

		CallableStatement cstmt = conn.prepareCall(sql);

		cstmt.setString(1, email);

		result = cstmt.executeUpdate();

		cstmt.close();
		conn.close();

		return result;

	}

}
