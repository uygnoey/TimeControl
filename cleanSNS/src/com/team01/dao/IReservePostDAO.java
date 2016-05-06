package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.PostPhotoDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostDTO;
import com.team01.dto.ReservePostPhotoDTO;

public interface IReservePostDAO {

		//예약게시글작성
		//예약게시글친구작성	
		//예약게시글사진작성	
		//예약게시글삭제
		public int reservePostDel() throws SQLException;
		public int reservePostAdd(PostDTO dto) throws SQLException;
		public int reserveTagAdd(MateTagDTO dto) throws SQLException;
		public int reservePhotoAdd(ReservePostPhotoDTO dto) throws SQLException;
		public int reservePostDelete(int reserveCode) throws SQLException;
		
		//예약 게시글 리스트
		public ArrayList<ReservePostDTO> getReservePostList(int memberCode,int start,int end)throws SQLException;
		public int getReservePostCount(int memberCode) throws SQLException;
		
		//예약 게시글 친구 태그
		public ArrayList<ReserveMateTagDTO> reserveMateTagList()throws SQLException;
		
		//예약게시글 수정
		public void reservePostModify(ReservePostDTO dto)throws SQLException;
		//예약게시글 수정 전 친구태그 수정
		public void reserveTagModifyBefore(int reserveCode)throws SQLException;
		//예약 게시글 수정_친구 태그 수정
		public void reserveTagModify(ReserveMateTagDTO dto)throws SQLException;
		
}
