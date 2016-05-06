package com.team01.dao;

import java.sql.SQLException;

public interface ICleanerDAO 
{
	// 1년 이하 삭제
	public int cleaner1() throws SQLException;
	
	// 2년 이하 삭제
	public int cleaner2() throws SQLException;
	
	// 3년 이상 삭제
	public int cleaner3() throws SQLException; 
	
	// 전체 삭제
	public int cleaner4() throws SQLException; 
		
}
