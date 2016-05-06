package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.team01.dto.Category2MateDTO;

@Repository("CategoryDAO")
public class CategoryDAO implements ICategoryDAO {

	@Resource(name="oracleDataSource")
	private DataSource DataSource;
		
	public void setDataSource(DataSource dataSource) {
		DataSource = dataSource;
	}
	
	@Override
	public String getCategory1Title(int memberCode) {
		// TODO Auto-generated method stub
		String sql="select categoryTitle from t_category where memberCode=?";
		String result=null;
		
		try {
			
			Connection conn=DataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				result=rs.getString("categoryTitle");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}

	@Override
	public String getCategory2Title(int memberCode) {
		String sql="select categoryTitle from t_category2 where memberCode=?";
		String result=null;
		
		try {
			
			Connection conn=DataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				result=rs.getString("categoryTitle");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}

	@Override
	public String getCategory2Use(int memberCode) {
		String sql="select (select checkState from t_check where checkCode=t.ca2Check) as checkState from t_category t where memberCode=?";
		String result=null;
		
		try {
			
			Connection conn=DataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				result=rs.getString("checkState");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}

	//카테고리1 사용자
	@Override
	public int setCategory1Title(int memberCode,String title) throws SQLException {
		//카테고리 1사용시
		//카테고리 제목 저장
		String sql="insert into t_category(memberCode, categoryTitle,ca2Check) values(?,?,DEFAULT)";
		
		Connection conn=DataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, memberCode);
		stmt.setString(2, title);
		
		int result=0;
		result=stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return result;
	}

	//카테고리2 사용자
	@Override
	public int setCategory2Title(int memberCode,String title,ArrayList<Integer> mateCode) throws SQLException {
		//카테고리1 테이블에 사용여부 저장
		//카테고리2 테이블에 제목 저장
		//카테고리2 친구목록에 친구 저장
		String sql1="insert into t_category2(memberCode, categoryTitle) values(?,?)";
		String sql2="insert into t_category2Mate(memberCode, mateSaveCode) values(?,?)";
		
		Connection conn=DataSource.getConnection();
		
		PreparedStatement stmt1=conn.prepareStatement(sql1);
		stmt1.setInt(1,memberCode );
		stmt1.setString(2, title);
		
		int result=0;
		result=stmt1.executeUpdate();
		
		for(Integer i:mateCode)
		{
			System.out.println("친구코드:"+i);
			PreparedStatement stmt2=conn.prepareStatement(sql2);
			stmt2.setInt(1,memberCode );
			stmt2.setInt(2, i);
			
			result=stmt2.executeUpdate();
			stmt2.close();
		}
		
		stmt1.close();		
		conn.close();
		
		return result;
	}

	//카테고리1 제목 수정
	@Override
	public int Category1TitleModify(int memberCode,String title) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update t_category set categoryTitle=? where memberCode=?";
		
		Connection conn=DataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, title);
		stmt.setInt(2, memberCode);
		
		int result=0;
		result=stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return result;
	}

	//카테고리2 제목 수정
	@Override
	public int Category2TitleModify(int memberCode,String title,ArrayList<Integer> mateList) throws SQLException {
		// 카테고리2 제목 수정시 친구로 전부 리셋 되도록.
		String sql1="update t_category2 set categoryTitle=? where memberCode=?";
		String sql2="delete from t_category2Mate where memberCode=?";
		String sql3="insert into t_category2Mate(memberCode, mateSaveCode) values(?,?)";
		
		Connection conn=DataSource.getConnection();
		int result=0;
		
		PreparedStatement stmt1=conn.prepareStatement(sql1);
		stmt1.setString(1, title);
		stmt1.setInt(2,memberCode );
		
		
		result=stmt1.executeUpdate();		
		stmt1.close();
		
		PreparedStatement stmt2=conn.prepareStatement(sql2);
		stmt2.setInt(1,memberCode );
		
		result=stmt2.executeUpdate();
		stmt2.close();
	
		
		for (Integer mate : mateList) {
			System.out.println(mate);			
			PreparedStatement stmt3=conn.prepareStatement(sql3);
			stmt3.setInt(1, memberCode);
			stmt3.setInt(2, mate );
			
			result=stmt3.executeUpdate();
			stmt3.close();
	
		}	
				
		conn.close();
		
		return result;
	
	}
	
	public int checkCategory1Use(int memberCode) throws SQLException
	{//카테고리1 사용여부 확인
		String sql="select count(*) as count from t_category where memberCode=?";
		
		Connection conn=DataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1,memberCode);
		
		int result=0;
		
		result=stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return result;
		
		
	}
	public int setCategory2Use(int memberCode) throws SQLException
	{//카테고리2 사용자에 대해 카테고리2 사용여부 세팅
		String sql="update t_category set ca2Check=1 where memberCode=?";
		
		Connection conn=DataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1,memberCode);
		
		int result=0;
		
		result=stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return result;
		
		
	}

	@Override
	public ArrayList<Category2MateDTO> getMateInfoes(int memberCode) throws SQLException {
		// 카테고리2 사용자 친구 리스트 불러오기
		ArrayList<Category2MateDTO> list=new ArrayList<Category2MateDTO>();
		String sql="select mateSaveCode,(select name from t_member where memberCode=t.mateSaveCode) as mateName from t_category2Mate t where memberCode=?";
		
		Connection conn=DataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1,memberCode);
		
		ResultSet rs=stmt.executeQuery();
		
		while(rs.next())
		{
			Category2MateDTO dto=new Category2MateDTO();
			dto.setMateSaveCode(rs.getInt("mateSaveCode"));
			dto.setMateName(rs.getString("mateName"));
			
			list.add(dto);
		}		
		
		rs.close();
		stmt.close();
		conn.close();
		
		return list;
	}
	
	

	
}
