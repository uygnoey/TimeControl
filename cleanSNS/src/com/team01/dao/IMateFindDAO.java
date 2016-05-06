package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.AlertDTO;
import com.team01.dto.MateDTO;
import com.team01.dto.MateStayDTO;
import com.team01.dto.MemberDTO;
import com.team01.dto.MemberStayDTO;

public interface IMateFindDAO 
{
	public ArrayList<MemberDTO> mateFind(String search) throws SQLException;	// 친구 찾기
	public ArrayList<MateDTO> matelist(int membercode) throws SQLException;		// 친구 찾기 결과 리스트
	public int materequest(MateStayDTO mate) throws SQLException;				// 친구 요청
	public int mateOk(MateDTO mate) throws SQLException;						// 친구 요청 수락(추가)
	public int mateNo(MateDTO mate) throws SQLException;						// 친구 요청 거절

	
	
}