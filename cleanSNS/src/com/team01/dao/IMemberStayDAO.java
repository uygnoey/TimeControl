package com.team01.dao;

import java.sql.SQLException;

import com.team01.dto.MemberDTO;
import com.team01.dto.MemberStayDTO;

public interface IMemberStayDAO {
	
	/**
	 * @file IMemberStateDAO.java
	 * @param member
	 * @return int
	 * @throws SQLException
	 * @interface IMemberStateDAO
	 * @brief 멤버 대기 회원 가입
	 */
	public int insert(MemberStayDTO memberStay) throws SQLException;
	
	/**
	 * @file IMemberStateDAO.java
	 * @param email
	 * @return int
	 * @throws SQLException
	 * @interface IMemberStateDAO
	 * @brief 회원가입 성공시 멤버 테이블로 이동
	 */
	public int registerSuccess(String email) throws SQLException;
}
