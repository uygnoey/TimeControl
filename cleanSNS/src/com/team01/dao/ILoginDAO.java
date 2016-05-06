package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.MemberDTO;

public interface ILoginDAO {
	
	// 로그인
	public ArrayList<MemberDTO> login(String email, String pw) throws SQLException;
	
	//이메일 찾기
	public String emailfind(String name, String pw) throws SQLException;
	
}
