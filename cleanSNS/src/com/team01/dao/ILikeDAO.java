package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.LikeDTO;
import com.team01.dto.PostDTO;

public interface ILikeDAO 
{
	// 좋아요
	public int like(LikeDTO likeDTO) throws SQLException;
	
	// 좋아요 취소
	public int likeCancel(int postCode, int memberCode) throws SQLException;
	
	// 좋아요 검색
	public ArrayList<LikeDTO> likeSearch(int memberCode) throws SQLException;
	
	// 좋아요 카운트
	public ArrayList<PostDTO> likeCount() throws SQLException;

}
