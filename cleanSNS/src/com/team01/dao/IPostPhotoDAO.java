package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.MateInfoDTO;
import com.team01.dto.PostPhotoDTO;

public interface IPostPhotoDAO {

	public ArrayList<PostPhotoDTO> getMyPhotoList(int memberCode,int start,int last) throws SQLException;
	public int getMyPhotoCount(int memberCode) throws SQLException;
	public MateInfoDTO getMateMainPhoto(int mateCode) throws SQLException;
}
