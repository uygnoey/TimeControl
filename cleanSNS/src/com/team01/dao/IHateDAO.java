package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.HateDTO;
import com.team01.dto.PostDTO;

public interface IHateDAO
{
	// 싫어요
	public int hate(HateDTO hateDTO) throws SQLException;
		
	// 싫어요 취소
	public int hateCancel(int postCode, int memberCode) throws SQLException;
		
	// 싫어요 검색
	public ArrayList<HateDTO> hateSearch(int memberCode) throws SQLException;
		
	// 싫어요 카운트
	public ArrayList<PostDTO> hateCount() throws SQLException;
	
	// 싫어요 카운트가 좋아요 카운트의 두배이면 숨김처리
	public int hateDelete() throws SQLException;

}
