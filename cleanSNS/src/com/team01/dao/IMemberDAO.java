package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.MemberDTO;

public interface IMemberDAO
{
	// 회원가입
	public int memberInsert(MemberDTO memberDTO) throws SQLException;
	
	// 회원정보수정(정보업데이트)
	public int memberUpdate(MemberDTO memberDTO) throws SQLException;
	
	// 회원탈퇴
	public int memberRemove(MemberDTO memberDTO) throws SQLException;
	
	// 중복체크
	public int emailCheck(String email) throws SQLException; // 비밀번호 찾기 할 때 사용.
	public int emailCheck2(String email) throws SQLException; // 회원가입 할 때 사용.
	
	//공개 범위 업데이트
	public int setopenLimited(int memberCode,int openLimitedCode) throws SQLException;
	
	// 비밀번호 업데이트
	public int passwordUpdate(String email, String password) throws SQLException;

	//프로필 사진 가져오기
	public String getMyPhoto(int memberCode) throws SQLException;
	
}