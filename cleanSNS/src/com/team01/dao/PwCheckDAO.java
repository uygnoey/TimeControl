package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.team01.dto.MemberDTO;

public class PwCheckDAO implements IPwCheckDAO
{
	private DataSource dataSource;
	
	// setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	// 비밀번호 일치 여부
	@Override
	public int matchPw(String email, String pw) throws SQLException
	{
		int result=0;			// memberCode를 찾는거니까 int.
		
		Connection conn = dataSource.getConnection();
		
		String sql = "select memberCode from t_member "
				+ "where email=? and pw=CRYPTIT.ENCRYPT(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, email);
		pstmt.setString(2, pw);
		pstmt.setString(3, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			result = rs.getInt("memberCode");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
}
