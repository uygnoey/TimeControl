package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.HateDTO;
import com.team01.dto.PostDTO;

public class HateDAO implements IHateDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// 싫어요
	@Override
	public int hate(HateDTO hateDTO) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "insert into t_hate(postCode, memberCode) values(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, hateDTO.getPostCode());
		pstmt.setInt(2, hateDTO.getMemberCode());
		
		result = pstmt.executeUpdate();
		
		pstmt.cancel();
		conn.close();
		
		return result;
	}

	// 싫어요 취소
	@Override
	public int hateCancel(int postCode, int memberCode) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "delete from t_hate where postCode=? and memberCode=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, postCode);
		pstmt.setInt(2, memberCode);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 싫어요 검색
	@Override
	public ArrayList<HateDTO> hateSearch(int memberCode) throws SQLException
	{
		ArrayList<HateDTO> result = new ArrayList<HateDTO>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "select postCode from t_hate where memberCode=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memberCode);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			HateDTO hateDTO = new HateDTO();
			
			hateDTO.setPostCode(rs.getInt("postCode"));
			
			result.add(hateDTO);
		}
		
		pstmt.close();
		conn.close();
		rs.close();
		
		return result;
	}

	// 싫어요 카운트
	@Override
	public ArrayList<PostDTO> hateCount() throws SQLException
	{
		ArrayList<PostDTO> result = new ArrayList<PostDTO>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "select hateCount, postCode from t_post";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			PostDTO postDTO = new PostDTO();
			
			postDTO.setLikeCount(rs.getInt("likeCount"));
			postDTO.setPostCode(rs.getInt("postCode"));
			
			result.add(postDTO);
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return result;
	}

	// 싫어요 카운트가 좋아요 카운트의 두배이면 숨김처리
	public int hateDelete() throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		// 프로시저 호출
		String sql = "{call prc_hate}";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result; 
	}
}
