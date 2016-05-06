package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.LikeDTO;
import com.team01.dto.PostDTO;

public class LikeDAO implements ILikeDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}

	// 좋아요
	@Override
	public int like(LikeDTO likeDTO) throws SQLException
	{
		int result=0;
		
		Connection conn = dataSource.getConnection();
	
		String sql = "insert into t_like(postCode, memberCode) values (?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, likeDTO.getPostCode());
		pstmt.setInt(2, likeDTO.getMemberCode());
		
		result = pstmt.executeUpdate();
	
		System.out.println("좋아요");
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	// 좋아요 검색
	@Override
	public ArrayList<LikeDTO> likeSearch(int memberCode) throws SQLException
	{
		ArrayList<LikeDTO> result = new ArrayList<LikeDTO>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "select postCode from t_like where memberCode=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memberCode);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			LikeDTO likeDTO = new LikeDTO();
		//	System.out.println(rs.getInt("postCode"));
			likeDTO.setPostCode(rs.getInt("postCode"));
			
			result.add(likeDTO);
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return result;
	}

	// 좋아요 취소
	@Override
	public int likeCancel(int postCode, int memberCode) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "delete from t_like where postCode=? and memberCode=?";	// 어떤 게시글에 어떤 사람이 취소를 했는지 알아야 하기 떄문에 조건절에 게시글코드와 멤버코드를 넣는다.
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, postCode);
		pstmt.setInt(2, memberCode);
		
		result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
		
		return result;	
	}
	
	// 좋아요 카운트
	public ArrayList<PostDTO> likeCount() throws SQLException
	{
		ArrayList<PostDTO> result = new ArrayList<PostDTO>();
		
		Connection conn = dataSource.getConnection();
		
	/*	String sql = "select count(*) as count, postCode from t_like";	// 어떤 게시글에 대한 좋아요 카운트인지 알아야 하기 떄문에 postCode 와 count를 모두 검색한다.
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			LikeDTO likeDTO = new LikeDTO();
			
			likeDTO.setCount(rs.getInt("count"));
			likeDTO.setPostCode(rs.getInt("postCode"));
		}*/
		
		String sql = "select likeCount, postCode from t_post";
		
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
}
