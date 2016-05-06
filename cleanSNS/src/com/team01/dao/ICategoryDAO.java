/*카테고리 관련 DAO 들 */
package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;



import com.team01.dto.Category2MateDTO;


public interface ICategoryDAO {
	public String getCategory1Title(int memberCode) throws SQLException;
	public String getCategory2Title(int memberCode)throws SQLException;
	public String getCategory2Use(int memberCode)throws SQLException;
	
	public int setCategory1Title(int memberCode,String title)throws SQLException;
	public int setCategory2Title(int memberCode,String title,ArrayList<Integer> mateCode)throws SQLException;
	
	public int Category1TitleModify(int memberCode,String title)throws SQLException;
	public int Category2TitleModify(int memberCode,String title,ArrayList<Integer> mateList)throws SQLException;
	
	public int checkCategory1Use(int memberCode) throws SQLException;
	public int setCategory2Use(int memberCode) throws SQLException;
	
	//카테고리2 친구 리스트 가져오기
	public ArrayList<Category2MateDTO> getMateInfoes(int memberCode) throws SQLException;
}
