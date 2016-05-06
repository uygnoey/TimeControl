package com.team01.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.MateDTO;
import com.team01.dto.MateStayDTO;
import com.team01.dto.MemberDTO;

public class MateFindDAO implements IMateFindDAO 
{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	@Override
	// 친구 찾기
	public ArrayList<MemberDTO> mateFind(String search) throws SQLException {

		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Connection conn= dataSource.getConnection();
		String sql = "select membercode,name, email"
				+ " from t_member where name=?"
				+ " and memberStateCode=0";		
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, search);
				
		ResultSet rs = psmt.executeQuery();

		while(rs.next())
		{
		
			MemberDTO member = new MemberDTO();
			member.setMemberCode(rs.getInt("membercode"));
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString("email"));
		
			result.add(member);

		}
		
		rs.close();
		psmt.close();
		conn.close();
					
		return result;
		

	}


	// 친구 목록
	@Override
	public ArrayList<MateDTO> matelist(int membercode) throws SQLException {
		
		ArrayList<MateDTO> result = new ArrayList<MateDTO>();
		Connection conn= dataSource.getConnection();
		String sql = "select mateMemberCode from t_mate where membercode=?";		
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, membercode);
		
		ResultSet rs = psmt.executeQuery();

	
		while(rs.next())
		{
		
			MateDTO mate = new MateDTO();
	
			mate.setMateMemberCode(rs.getInt("mateMemberCode"));
			result.add(mate);

		}
		
		rs.close();
		psmt.close();
		conn.close();
					
		return result;
		
	}


	// 친구 요청
	@Override
	public int materequest(MateStayDTO mate) throws SQLException
	{
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql="{call  prc_mateStay_insert(?,?)}";
		
		CallableStatement psmt = conn.prepareCall(sql);
		psmt.setInt(1, mate.getMemberCode());
		psmt.setInt(2, mate.getMateMemberCode());
		result = psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		return result;
	}



	// 친구 추가(친구 요청 수락)
	@Override
	public int mateOk(MateDTO mate) throws SQLException 
	{
		int result=0;
		Connection conn = dataSource.getConnection(); 
		String sql="{call prc_mateOk_insert(?,?) }";
		
		CallableStatement psmt = conn.prepareCall(sql);
		psmt.setInt(1, mate.getMemberCode());
		psmt.setInt(2, mate.getMateMemberCode());
		result=psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		return result;
	}


	// 친구 요청 거절
	@Override
	public int mateNo(MateDTO mate) throws SQLException {
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql="{call prc_mateNo_insert(?,?)}";
		CallableStatement psmt = conn.prepareCall(sql);
		psmt.setInt(1,mate.getMemberCode());
		psmt.setInt(2, mate.getMateMemberCode());
		result=psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		return result;
	}
	

	
	

	
	
}
