package com.team01.dao;

import java.sql.SQLException;

import com.team01.dto.MemberDTO;

public interface IPwCheckDAO
{
	// 비밀번호 확인
	public int matchPw(String email, String pw) throws SQLException;

}
