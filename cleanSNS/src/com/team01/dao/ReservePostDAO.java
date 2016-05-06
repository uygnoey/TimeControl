package com.team01.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.PostPhotoDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostDTO;
import com.team01.dto.ReservePostPhotoDTO;

public class ReservePostDAO implements IReservePostDAO {

	private DataSource dataSource;	// DB연결

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	//예약 게시글 게시
	public int reservePostDel()
	{
		int result=0;	
	
		String sql="select count(*) as count from t_reservePost  where TO_CHAR(reserveTime,'yyyy-mm-dd hh24')<=TO_CHAR(SYSDATE,'yyyy-mm-dd hh24') and delHide!=1";
		String rsql="{call prc_reservPost_del}";				
		Connection conn;
		try {
			conn = dataSource.getConnection();			

			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			if(rs.next())
			{	//System.out.println("-----------------------");
				//System.out.println("진입"+rs.getInt("count"));
				if(rs.getInt("count")>0)
				{
					Connection conn2=dataSource.getConnection();
					CallableStatement pstmt=conn2.prepareCall(rsql);
					pstmt.executeQuery();//예약 게시글을 일반 게시글로 옮기과 동시에 삭제처리	
					pstmt.close();
					conn2.close();
				}
				
			}		
			
			rs.close();
			stmt.close();					
			conn.close();	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
			
	}
	
	// 예약 게시글 추가
		public int reservePostAdd(PostDTO dto) throws SQLException {
			// TODO Auto-generated method stub
						
			String sql="{call prc_reservePost_add(?,?,?,?,?,?)}";
			Connection conn=dataSource.getConnection();
			
			CallableStatement stmt=conn.prepareCall(sql);
			stmt.setInt(1, dto.getMemberCode());
			stmt.setString(2, dto.getPostContent());
			stmt.setString(3, dto.getMateCode());
			stmt.setInt(4, dto.getPungCode());
			String reserveTime=dto.getYear()+dto.getMonth()+dto.getDate()+" "+dto.getHour()+"0000";			
			stmt.setString(5, reserveTime);
			stmt.registerOutParameter(6, Types.INTEGER);
						
			int result=0;
			stmt.executeUpdate();
			
			result=(int) stmt.getObject(6);			
			
			conn.close();
			stmt.close();
			
			return result;
		}

		//예약 게시글 친구 태그
		@Override
		public int reserveTagAdd(MateTagDTO dto) throws SQLException {
			// TODO Auto-generated method stub
			
			String sql="insert into t_reserveMateTag(reserveCode,memberCode) values(?,?)";
			
			Connection conn=dataSource.getConnection();		
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, dto.getPostCode());
			stmt.setInt(2, dto.getMemberCode());
			
			int result=0;
			result=stmt.executeUpdate();
			
			conn.close();
			stmt.close();		
			
			return result;
		}


		//예약 게시글 사진 추가
		@Override
		public int reservePhotoAdd(ReservePostPhotoDTO dto) throws SQLException {
			// TODO Auto-generated method stub
			String sql="insert into t_reservePostPhoto(reserveCode,photoURL) values(?,?)";
			
			Connection conn=dataSource.getConnection();		
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, dto.getReserveCode());
			stmt.setString(2, dto.getPhotoURL());
			
			int result=0;
			result=stmt.executeUpdate();
			
			conn.close();
			stmt.close();		
			
			return result;
		}

		//예약 게시글 삭제
		@Override
		public int reservePostDelete(int reserveCode ) throws SQLException {

			String sql="update t_reservePost set delHide=1 where reserveCode=?";
			
			Connection conn=dataSource.getConnection();		
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, reserveCode);
					
			int result=0;
			result=stmt.executeUpdate();
			
			conn.close();
			stmt.close();		
			
			return result;
		}


		//예약 게시글 가져오기
		@Override
		public ArrayList<ReservePostDTO> getReservePostList(int memberCode,int start,int end)
				throws SQLException {
			// TODO Auto-generated method stub
			ArrayList<ReservePostDTO> list=new ArrayList<ReservePostDTO>();
			String sql="{call pre_reserverPost_list(?,?,?,?)";
						
			Connection conn;
			try {
				conn = dataSource.getConnection();				
				
				CallableStatement stmt=conn.prepareCall(sql);
				stmt.setInt(1, memberCode);
				stmt.setInt(2, start);
				stmt.setInt(3, end);
				stmt.registerOutParameter(4, OracleTypes.CURSOR);
				
				stmt.executeUpdate();
				ResultSet rs=(ResultSet) stmt.getObject(4);
				
				while(rs.next())
				{
					ReservePostDTO dto=new ReservePostDTO();
					dto.setMemberCode(rs.getInt("memberCode"));
					dto.setReserveCode(rs.getInt("reserveCode"));
					dto.setName(rs.getString("name"));
					dto.setPhotoURL(rs.getString("photoURL"));
					dto.setPostContent(rs.getString("postContent"));
					dto.setReserveDate(rs.getString("reserveDate"));
					dto.setPungCode(rs.getInt("pungCode"));
					dto.setReserveTime(rs.getString("reserveTime"));
					dto.setMateCode(rs.getString("mateCode"));
					dto.setPostphotoURL(rs.getString("postphotoURL"));
					dto.setMateName(rs.getString("mateName"));
									
					list.add(dto);
					
				}
				rs.close();
				stmt.close();			
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}


		//예약 게시글 카운트
		@Override
		public int getReservePostCount(int memberCode) throws SQLException {
			// TODO Auto-generated method stub
			String sql="select count(*) as count from t_reservePost  where memberCode=? and delHide=0";
			
			Connection conn=dataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, memberCode);
			
			int result=0;
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				result=rs.getInt("count");
			
			rs.close();
			conn.close();		
			
			return result;
		}


		//예약 게시글 친구 태그
		@Override
		public ArrayList<ReserveMateTagDTO> reserveMateTagList() throws SQLException {
			
			ArrayList<ReserveMateTagDTO> list=new ArrayList<ReserveMateTagDTO>();
			String sql="select reserveCode,memberCode, ( select name from t_member where  memberCode=t.memberCode) as name from t_reserveMateTag t";
			Connection conn;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt=conn.prepareCall(sql);
								
				ResultSet rs=stmt.executeQuery();
				while(rs.next())
				{	
					ReserveMateTagDTO dto=new ReserveMateTagDTO();
					dto.setReserveCode(rs.getInt("reserveCode"));
					dto.setName(rs.getString("name"));
					dto.setMemberCode(rs.getInt("memberCode"));
					
					
					list.add(dto);
				
				}
				
				stmt.close();
				rs.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
		
		//예약 게시글 수정 업데이트
		public void reservePostModify(ReservePostDTO dto)
		{
			String sql="update t_reservePost set postContent=?,mateCode=? where reserveCode=?";
			
			
			PreparedStatement stmt;
			try {
				
				Connection conn=dataSource.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dto.getPostContent());
				stmt.setString(2, dto.getMateCode());
				stmt.setInt(3, dto.getReserveCode());
				
				stmt.executeUpdate();
				
				conn.close();
				stmt.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		public void reserveTagModifyBefore(int reserveCode)
		{
			String sql="delete from t_reserveMateTag where reserveCode=?";			
		
			try {
				Connection conn=dataSource.getConnection();
				
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setInt(1, reserveCode);
				stmt.executeUpdate();			
				
				conn.close();
				stmt.close();
		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
	
		
		//예약 게시글 수정_친구 태그 수정
		public void reserveTagModify(ReserveMateTagDTO dto)
		{			
			String sql2="insert into t_reserveMateTag(reserveCode,memberCode) values(?,?)";
			
		
			try {
				Connection conn=dataSource.getConnection();				
				
				PreparedStatement stmt2=conn.prepareStatement(sql2);
				stmt2.setInt(1, dto.getReserveCode());
				stmt2.setInt(2, dto.getMemberCode());
				
				stmt2.executeUpdate();
				
				conn.close();
				stmt2.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
	
}
