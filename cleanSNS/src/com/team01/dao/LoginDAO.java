package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.MemberDTO;


public class LoginDAO implements ILoginDAO
{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}
	
	// 로그인
	@Override
	public ArrayList<MemberDTO> login(String email, String pw) throws SQLException 
	{
		ArrayList<MemberDTO> result=new ArrayList<MemberDTO>();
		Connection conn = dataSource.getConnection();
		String sql="select memberCode, name from t_member "
				+ "where email=? and pw=CRYPTIT.ENCRYPT(?"
				+ ", (select email from t_member where email=?))"
				+ "and memberStateCode=0";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, email);
		psmt.setString(2, pw);
		psmt.setString(3, email);
		
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next())
		{
			MemberDTO member = new MemberDTO();
			member.setMemberCode(rs.getInt("memberCode"));
			member.setName(rs.getString("name"));
			result.add(member);
		//	System.out.println(result.get(0).getMemberCode());
		//	System.out.println(result.get(0).getName());

		}
		
		
		rs.close();
		psmt.close();
		conn.close();
		
		return result;
	}

	// 이메일 찾기
	@Override
	public String emailfind(String name, String pw) throws SQLException {

		String result=null;
		Connection conn = dataSource.getConnection();
		
		
		String sql = "select email from t_member where name=? and"
				+ " pw=CRYPTIT.ENCRYPT(?, (select email from t_member"
				+ " where email in (select email from t_member where name=?))) "
				+ " and memberStateCode=0";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, pw);
		psmt.setString(3, name);
		
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next())
				result = rs.getString("email");
				
		
		rs.close();
		psmt.close();
		conn.close();

		return result;
	}

	
}
