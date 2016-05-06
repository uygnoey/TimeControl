package com.team01.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import com.team01.dto.MateDTO;
import com.team01.dto.MateInfoDTO;
import com.team01.dto.MemberDTO;

public class MateDAO implements IMateDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<MemberDTO> tagList(int memberCode, String name) throws SQLException {
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = dataSource.getConnection();
		String sql="select mem, name, email"
				+ " from(select t1.mem, t2.name, t2.email"
					 + " from(select matememberCode as mem"
					 	  + " from t_mate"
					 	  + " where memberCode=?)t1, t_member t2"
					 + " where t1.mem=t2.memberCode) T"
				+ " where name = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.setInt(1, memberCode);
		pstmt.setString(2, name);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			
			MemberDTO dto = new MemberDTO();
			dto.setMemberCode(rs.getInt("mem"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			
			list.add(dto);		
		}
		rs.close();
		conn.close();
		
		return list;
	}

	//친구 리스트 불러오기 
	@Override
	public ArrayList<MemberDTO> allList(int memberCode,int start) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql="{call  prc_categoryMate_add(?,?,?)";
		
		Connection conn=dataSource.getConnection();
		CallableStatement stmt=conn.prepareCall(sql);
		stmt.setInt(1, memberCode);
		stmt.setInt(2, start);
		stmt.registerOutParameter(3, OracleTypes.CURSOR);
		
		stmt.executeQuery();
		
		ResultSet rs=(ResultSet) stmt.getObject(3);
		
		while(rs.next()){
			
			MemberDTO dto = new MemberDTO();
			dto.setMemberCode(rs.getInt("mateMemberCode"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			
			list.add(dto);		
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return list;
	}

	//친구 리스트 카운트
	@Override
	public int listCount(int memberCode) throws SQLException {
		
		String sql="select count(*) as count from t_mate where memberCode=?";
		
		Connection conn=dataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);		
		stmt.setInt(1,memberCode);
		
		int result=0;
		ResultSet rs = stmt.executeQuery();
		rs = stmt.executeQuery();
		while(rs.next())
			result = rs.getInt("count");
			
		rs.close();
		stmt.close();
		conn.close();
		
		return result;
	}

	// 친구 리스트
	@Override
	public ArrayList<MateDTO> mateList(int memberCode) throws SQLException {
		
		ArrayList<MateDTO> list = new ArrayList<MateDTO>();
		Connection conn = dataSource.getConnection();
		String sql = "select memberCode, mateMemberCode, mateGroupCode from t_mate where memberCode = ?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, memberCode);
		
		pstmt.close();
		conn.close(); 
		return list;
	}

	// 친구 정보들 불러오기
	@Override
	public ArrayList<MateInfoDTO> infoList(int memberCode) throws SQLException {
		
		ArrayList<MateInfoDTO> list = new ArrayList<MateInfoDTO>();
		Connection conn = dataSource.getConnection();
		String sql = "select t2.mateMemberCode, t2.mateGroupCode, t2.name, mp.photoURL"
				  + " from  (select t.mateMemberCode, t.mateGroupCode, m.name"
				         + " from (select mateMemberCode, mateGroupCode"
				               + " from t_mate"
				               + " where memberCode = ?)t, t_member m"
				         + " where t.mateMemberCode = m.memberCode)t2, t_myPhoto mp"
				  + " where t2.mateMemberCode = mp.memberCode(+)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberCode);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			
			MateInfoDTO dto = new MateInfoDTO();
			dto.setMateMemberCode(rs.getInt("mateMemberCode"));
			dto.setMateGroupCode(rs.getInt("mateGroupCode"));
			dto.setName(rs.getString("name"));
			dto.setPhotoURL(rs.getString("photoURL"));
			
			list.add(dto);			
		}
		
		rs.close();
		pstmt.close();
		conn.close(); 
		
		return list;
	}

	@Override
	public int updateMateGroup(MateDTO dto) throws SQLException {
		
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_mate set mateGroupCode = ? where memberCode = ? and mateMemberCode = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getMateGroupCode());
		pstmt.setInt(2, dto.getMemberCode());
		pstmt.setInt(3, dto.getMateMemberCode());
		
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return result;
	}

	// 친구 삭제(끊기)
	@Override
	public int delMate(MateDTO dto) throws SQLException {
		
		int result=0;
		Connection conn = dataSource.getConnection();
		String sql = "delete from t_mate where membercode=? and mateMemberCode=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getMemberCode());
		pstmt.setInt(2, dto.getMateMemberCode());
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public ArrayList<MateInfoDTO> mateInfoList(int mateCode) throws SQLException {
		
		ArrayList<MateInfoDTO> list = new ArrayList<MateInfoDTO>();
		Connection conn = dataSource.getConnection();
		String sql = "select t3.mateMemberCode as mateMemberCode, t3.name as name, t3.openLimitedCode as openLimitedCode, t4.photoURL as photoURL"
				  + " from (select t.mateMemberCode, t2.name, t2.openLimitedCode"
				  		+ " from (select mateMemberCode"
				  			  + " from t_mate"
				  			  + " where memberCode=?)t, t_member t2"
				  		+ " where t.mateMemberCode = t2.memberCode)t3, t_myPhoto t4"
				  + " where t3.mateMemberCode = t4.memberCode(+)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mateCode);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			
			MateInfoDTO dto = new MateInfoDTO();
			dto.setMateMemberCode(rs.getInt("mateMemberCode"));
			dto.setName(rs.getString("name")); 
			dto.setOpenLimitedCode(rs.getInt("openLimitedCode"));
			dto.setPhotoURL(rs.getString("photoURL")); 
			
			list.add(dto);
		}
		
		rs.close();
		conn.close();
		
		return list;
	}
	
	
	
	

	
	
	
	
}
