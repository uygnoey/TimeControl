package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.PostPhotoDTO;

public interface IPostDAO {
  
	// 게시글 작성 관련
	public int postAdd(PostDTO dto) throws SQLException;
	public int tagAdd(ArrayList<MateTagDTO> list) throws SQLException;
	public int photoAdd(PostPhotoDTO dto) throws SQLException;	
	
	// 게시글 삭제
	public int delPost(int postCode) throws SQLException;
	public int delMateTag(int postCode, int memberCode) throws SQLException;
	
	// 게시글, 친구태그, 사진 수정
	public int postUpdate(int postCode, String postContent) throws SQLException;
	public int tagUpdate(int postCode, String mateCode) throws SQLException;
	public int photoUpdate(String url) throws SQLException;
	
	
	//게시글 뷰 관련
	public List<PostDTO> allList(int memberCode,int start,int end);
	public int allListCount(int memberCode);
	public List<PostDTO> leftList(int memberCode,int start,int end);
	public int leftListCount(int memberCode);
	public List<PostDTO> rightList(int memberCode,int start,int end);
	public int rightListCount(int memberCode);
	public PostDTO postGet(int postCode) throws SQLException;
	public ArrayList<MateTagDTO> postTagGet(int postCode) throws SQLException;
	
	//친구 태그 불러오기
	public ArrayList<MateTagDTO> mateTagList();	
	//예약게시글작성
	//예약게시글친구작성	
	//예약게시글사진작성	
	//예약게시글삭제
	public int reservePostDel();
	public int reservePostAdd(PostDTO dto) throws SQLException;
	public int reserveTagAdd(MateTagDTO dto) throws SQLException;
	public int reservePhotoAdd(PostPhotoDTO dto) throws SQLException;
	public int reservePostDelete(int reserveCode) throws SQLException;
	
	//내 스토리 리스트
	public ArrayList<PostDTO> getmyStory(int memberCode,int start,int end) throws SQLException;
	public int myListCount(int memberCode)throws SQLException;
	
	//펑 게시글 펑 작동
	public int pungPostDelete() throws SQLException;
	
	//펑 게시글 가져오기
	public ArrayList<PostDTO> getPungList(int memberCode,int start,int end) throws SQLException;
	//펑 게시글 카운트
	public int getPungListCount(int memberCode) throws SQLException;
	//펑 게시글 수정
	public void pungPostModify(PostDTO dto)throws SQLException;
	//펑 게시글 수정- 친구 태그
	public void pungTagModifyBefore(int postCode)throws SQLException;
	public void pungTagModify(MateTagDTO dto)throws SQLException;
	
	//펑 게시글 유저가 삭제
	public void pungPostDelete(int postCode)throws SQLException;
	
	// 알림 게시글 가져오기
	public PostDTO alertPost(int postCode) throws SQLException;
	// 알림 친구태그 가져오기
	public ArrayList<MateTagDTO> alertMateTag(int postCode) throws SQLException;
	
}
