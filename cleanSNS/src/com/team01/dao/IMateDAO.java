package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.MateDTO;
import com.team01.dto.MateInfoDTO;
import com.team01.dto.MemberDTO;

public interface IMateDAO {

	public ArrayList<MemberDTO> tagList(int memberCode, String name) throws SQLException;
	public  ArrayList<MemberDTO> allList(int memberCode,int start) throws SQLException; 
	public int listCount(int memberCode) throws SQLException; 
	public ArrayList<MateDTO> mateList(int memberCode) throws SQLException; 
	public ArrayList<MateInfoDTO> infoList(int memberCode) throws SQLException;
	public int updateMateGroup(MateDTO dto) throws SQLException;
	public int delMate(MateDTO dto)throws SQLException; 
	public ArrayList<MateInfoDTO> mateInfoList(int mateCode) throws SQLException;
	
}
