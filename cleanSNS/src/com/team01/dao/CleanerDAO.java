package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CleanerDAO implements ICleanerDAO
{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int cleaner1() throws SQLException {
		
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_Post set delHide=1 where (to_date(sysdate,'yyyy-mm-dd') - to_date(postdate,'yyyy-mm-dd')) >= 365";
	
		PreparedStatement psmt = conn.prepareStatement(sql);
		result=psmt.executeUpdate();
		
		psmt.close();	
		conn.close();
				
		return result;
				
		
	}

	@Override
	public int cleaner2() throws SQLException {

		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_Post set delHide=1 where (to_date(sysdate,'yyyy-mm-dd') - to_date(postdate,'yyyy-mm-dd')) >= (365*2)";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		result=psmt.executeUpdate();

		psmt.close();
		conn.close();
		
		return result;
				
	}

	@Override
	public int cleaner3() throws SQLException {
		
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_Post set delHide=1 where (to_date(sysdate,'yyyy-mm-dd') - to_date(postdate,'yyyy-mm-dd')) >= (365*3)";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		result=psmt.executeUpdate();

		psmt.close();
		conn.close();
		
		return result;
		
	}
	
	@Override
	public int cleaner4() throws SQLException {
		
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_Post set delHide=1 where delHide=0";
		
		PreparedStatement psmt = conn.prepareStatement(sql);
		result=psmt.executeUpdate();

		psmt.close();
		conn.close();
		
		return result;
		
	}

	
	

}
