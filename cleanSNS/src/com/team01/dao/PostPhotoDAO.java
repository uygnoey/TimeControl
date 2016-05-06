package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;




import com.team01.dto.MateInfoDTO;
import com.team01.dto.PostPhotoDTO;

public class PostPhotoDAO implements IPostPhotoDAO {

	private DataSource dataSource;

	public void setDatasource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	//스토리 - 사진 리스트 가져오기
	public ArrayList<PostPhotoDTO> getMyPhotoList(int memberCode,int start,int last)
	{
		ArrayList<PostPhotoDTO> list=new ArrayList<PostPhotoDTO>();
		
		String sql="select * from ( select p.postCode,p.photoURL,"
				+ "rank() over(order by p.postCode desc) as rn  "
				+ "from t_post t right join  t_postPhoto p on t.postCode="
				+ "p.postCode where t.memberCode=?  and t.delHide=0 and t.pungCode=0) where rn>=? and rn<=?";
		
		Connection conn;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			stmt.setInt(2, start);
			stmt.setInt(3, last);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
				PostPhotoDTO dto=new PostPhotoDTO();
				dto.setPostCode(rs.getInt("postCode"));
				dto.setPhotoURL(rs.getString("photoURL"));
				
				
				list.add(dto);
				
			}
			
			conn.close();
			stmt.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	//내 게시글 사진 카운트
	public int getMyPhotoCount(int memberCode)
	{
		int result=0;
		
		String sql="select count(*) as count from t_post t right "
				+ "join  t_postPhoto p on t.postCode=p.postCode where t.memberCode=?  and t.delHide=0 and t.pungCode=0 ";
		
		Connection conn;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next())
			{
				result=rs.getInt("count");				
			}
			
			conn.close();
			stmt.close();		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return result;
	}

	// 친구 스토리 - 프로필 사진 가져오기
	@Override
	public MateInfoDTO getMateMainPhoto(int mateCode) throws SQLException {
		
		MateInfoDTO dto = new MateInfoDTO();
		Connection conn = dataSource.getConnection();
		String sql="select PHOTOURL, NAME, OPENLIMITEDCODE"
				+ " from(select my.PHOTOURL, me.NAME, my.MEMBERCODE, me.OPENLIMITEDCODE"
					 + " from t_myPhoto my, t_member me"
					 + " where my.MEMBERCODE(+) = me.MEMBERCODE)"
				+ " where MEMBERCODE=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mateCode);
		ResultSet rs = pstmt.executeQuery(); 
		while(rs.next()){
			
			dto.setName(rs.getString("NAME"));
			dto.setPhotoURL(rs.getString("PHOTOURL"));
			dto.setMateMemberCode(mateCode);
			dto.setOpenLimitedCode(rs.getInt("OPENLIMITEDCODE")); 
		}
			
		rs.close();
		conn.close();		

		return dto;
	}
	
	
	
	
	
}

	