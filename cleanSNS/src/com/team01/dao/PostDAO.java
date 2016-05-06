package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.PostPhotoDTO;
import com.team01.dto.ReserveMateTagDTO;

import oracle.jdbc.OracleTypes;

public class PostDAO implements IPostDAO {

	private DataSource dataSource; // DB연결

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 게시물 테이블에 레코드 삽입
	@Override
	public int postAdd(PostDTO dto) throws SQLException {

		int postCode = 0;
		Connection conn = dataSource.getConnection();
		String sql = "insert into t_post(postCode, memberCode, postContent"
				+ ", pungCode, mateCode)"
				+ " values(postSeq.nextval, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getMemberCode());
		pstmt.setString(2, dto.getPostContent());
		pstmt.setInt(3, dto.getPungCode());
		pstmt.setString(4, dto.getMateCode());
		pstmt.executeUpdate();
		pstmt.close();

		// 방금 insert된 postCode 가져오기
		String sql2 = "select max(postCode) as postCode from t_post where memberCode=?";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, dto.getMemberCode());
		ResultSet rs = pstmt2.executeQuery();
		while (rs.next()) {
			postCode = rs.getInt("postCode");
		}
		rs.close();
		pstmt2.close();
		conn.close();

		return postCode; // 방금 t_post에 insert된 postCode 반환
	}

	// 메인 글 전부 불러오기 - 카테고리 미사용자
	@Override
	public List<PostDTO> allList(int memberCode, int start, int end) {
		List<PostDTO> list = new ArrayList<PostDTO>();
		String sql = "{call pre_post_list(?,?,?,?)}";

		// reservePostDel();//예약 게시글 작업

		Connection conn;
		try {
			conn = dataSource.getConnection();

			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.setInt(2, start);
			stmt.setInt(3, end);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);

			stmt.executeUpdate();
			ResultSet rs = (ResultSet) stmt.getObject(4);

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setMemberCode(rs.getInt("memberCode"));
				dto.setPostCode(rs.getInt("postCode"));
				dto.setName(rs.getString("name"));
				dto.setPhotoURL(rs.getString("photoURL"));
				dto.setPostContent(rs.getString("postContent"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setPungCode(rs.getInt("pungCode"));
				dto.setLikeCount(rs.getInt("likeCount"));
				dto.setHateCount(rs.getInt("hateCount"));
				dto.setMateCode(rs.getString("mateCode"));
				dto.setPostphotoURL(rs.getString("postphotoURL"));
				dto.setMateName(rs.getString("mateName"));

				// 펑 삭제 여부 확인:펑미적용-0 펑 적용-1
				dto.setPungCheck(0);

				if (rs.getInt("pungCode") != 0) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
					Date postTime = format.parse(rs.getString("postDate"));

					Calendar pungTime = Calendar.getInstance();
					pungTime.setTime(postTime);
					pungTime.add(Calendar.MINUTE, (rs.getInt("pungCode")));
					Date pungTime2 = pungTime.getTime();

					Calendar now = Calendar.getInstance();
					now.getTime();
					
					Date nowTime = now.getTime();
					long a=(pungTime2.getTime()-nowTime.getTime())/(1000*60);
					//System.out.println("nowTime2-postTime2"+a);
					dto.setPungCode((int)a);
					
					if (a <= 0) // 포스팅 시간이 펑 시간보다 작을 경우
					{	dto.setPungCheck(1);					
					
					}				


				}

				list.add(dto);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	// 카테고리 사용자 - 왼쪽 카테고리

	@Override
	public List<PostDTO> leftList(int memberCode, int start, int end) {

		List<PostDTO> list = new ArrayList<PostDTO>();
		String sql = "{call pre_post_leftList(?,?,?,?)}";

		// reservePostDel();//예약 게시글 작업

		Connection conn;
		try {
			conn = dataSource.getConnection();

			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.setInt(2, start);
			stmt.setInt(3, end);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);

			stmt.executeUpdate();
			ResultSet rs = (ResultSet) stmt.getObject(4);

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPostCode(rs.getInt("postCode"));
				dto.setMemberCode(rs.getInt("memberCode"));
				dto.setName(rs.getString("name"));
				dto.setPhotoURL(rs.getString("photoURL"));
				dto.setPostContent(rs.getString("postContent"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setPungCode(rs.getInt("pungCode"));
				dto.setLikeCount(rs.getInt("likeCount"));
				dto.setHateCount(rs.getInt("hateCount"));
				dto.setMateCode(rs.getString("mateCode"));
				dto.setPostphotoURL(rs.getString("postphotoURL"));
				dto.setMateName(rs.getString("mateName"));

				// 펑 삭제 여부 확인:펑미적용-0 펑 적용-1
				dto.setPungCheck(0);

				if (rs.getInt("pungCode") != 0) {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
					Date postTime = format.parse(rs.getString("postDate"));

					Calendar pungTime = Calendar.getInstance();
					pungTime.setTime(postTime);
					pungTime.add(Calendar.MINUTE, (rs.getInt("pungCode")));
					Date pungTime2 = pungTime.getTime();

					Calendar now = Calendar.getInstance();
					now.getTime();
					
					Date nowTime = now.getTime();
					long a=(pungTime2.getTime()-nowTime.getTime())/(1000*60);
					//System.out.println("nowTime2-postTime2"+a);
					dto.setPungCode((int)a);
					
					if (a <= 0) // 포스팅 시간이 펑 시간보다 작을 경우
					{	dto.setPungCheck(1);					
					
					}				

				}

				list.add(dto);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// 카테고리 사용자 - 오른쪽 카테고리
	@Override
	public List<PostDTO> rightList(int memberCode, int start, int end) {

		List<PostDTO> list = new ArrayList<PostDTO>();
		String sql = "{call pre_post_rightList(?,?,?,?)}";

		// reservePostDel();//예약 게시글 작업

		Connection conn;
		try {
			conn = dataSource.getConnection();

			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.setInt(2, start);
			stmt.setInt(3, end);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);
			stmt.executeUpdate();
			ResultSet rs = (ResultSet) stmt.getObject(4);

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPostCode(rs.getInt("postCode"));
				dto.setMemberCode(rs.getInt("memberCode"));
				dto.setName(rs.getString("name"));
				dto.setPhotoURL(rs.getString("photoURL"));
				dto.setPostContent(rs.getString("postContent"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setPungCode(rs.getInt("pungCode"));
				dto.setLikeCount(rs.getInt("likeCount"));
				dto.setHateCount(rs.getInt("hateCount"));
				dto.setMateCode(rs.getString("mateCode"));
				dto.setPostphotoURL(rs.getString("postphotoURL"));
				dto.setMateName(rs.getString("mateName"));

				// 펑 삭제 여부 확인:펑미적용-0 펑 적용-1
				dto.setPungCheck(0);

				if (rs.getInt("pungCode") != 0) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
					Date postTime = format.parse(rs.getString("postDate"));

					Calendar pungTime = Calendar.getInstance();
					pungTime.setTime(postTime);
					pungTime.add(Calendar.MINUTE, (rs.getInt("pungCode")));
					Date pungTime2 = pungTime.getTime();

					Calendar now = Calendar.getInstance();
					now.getTime();
					
					Date nowTime = now.getTime();
					long a=(pungTime2.getTime()-nowTime.getTime())/(1000*60);
					//System.out.println("nowTime2-postTime2"+a);
					dto.setPungCode((int)a);
					
					if (a <= 0) // 포스팅 시간이 펑 시간보다 작을 경우
					{	dto.setPungCheck(1);					
					
					}				


				}

				list.add(dto);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int reservePostDel() {
		int result = 0;

		String sql = "select count(*) as count from t_reservePost  where TO_CHAR(reserveTime,'yyyy-mm-dd hh24')<=TO_CHAR(SYSDATE,'yyyy-mm-dd hh24') and delHide!=1";
		String rsql = "{call prc_reservPost_del}";
		Connection conn;
		try {
			conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) { // System.out.println("-----------------------");
								// System.out.println("진입"+rs.getInt("count"));
				if (rs.getInt("count") > 0) {
					Connection conn2 = dataSource.getConnection();
					CallableStatement pstmt = conn2.prepareCall(rsql);
					pstmt.executeQuery();// 예약 게시글을 일반 게시글로 옮기과 동시에 삭제처리
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

	// 카테고리 미사용자 게시글 리스트 카운트
	@Override
	public int allListCount(int memberCode) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "{call pre_post_listCount(?,?)}";

		Connection conn;

		try {
			conn = dataSource.getConnection();
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.registerOutParameter(2, Type.INT);

			stmt.executeQuery();

			result = stmt.getInt(2);

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 카테고리1 사용자 게시글 리스트 카운트
	@Override
	public int leftListCount(int memberCode) {

		int result = 0;
		String sql = "{call pre_post_leftListCount(?,?)}";

		Connection conn;

		try {
			conn = dataSource.getConnection();
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.registerOutParameter(2, Type.INT);

			stmt.executeQuery();

			result = stmt.getInt(2);

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 카테고리2 사용자 게시글 리스트 카운트
	@Override
	public int rightListCount(int memberCode) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "{call pre_post_rightListCount(?,?)}";

		Connection conn;

		try {
			conn = dataSource.getConnection();
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.registerOutParameter(2, Type.INT);

			stmt.executeQuery();

			result = stmt.getInt(2);

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 친구 태그 불러오기
	@Override
	public ArrayList<MateTagDTO> mateTagList() {

		ArrayList<MateTagDTO> list = new ArrayList<MateTagDTO>();
		String sql = "{call prc_postMate_list(?)}";
		Connection conn;
		try {
			conn = dataSource.getConnection();
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);

			stmt.executeUpdate();
			ResultSet rs = (ResultSet) stmt.getObject(1);
			while (rs.next()) {
				MateTagDTO dto = new MateTagDTO();
				dto.setPostCode(rs.getInt("postCode"));
				dto.setName(rs.getString("name"));
				// System.out.println("name : "+rs.getString("name"));

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

	// 게시물 정보 가져오기(1개)
	public PostDTO postGet(int postCode) throws SQLException {

		PostDTO dto = new PostDTO();
		Connection conn = dataSource.getConnection();
		String sql = "select postCode, postContent from t_post where postCode = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, postCode);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			dto.setPostCode(rs.getInt("postCode"));
			dto.setPostContent(rs.getString("postContent"));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return dto;
	}

	// 게시물 태그 정보 가져오기(게시물 1개에 있는 태그들)
	@Override
	public ArrayList<MateTagDTO> postTagGet(int postCode) throws SQLException {

		ArrayList<MateTagDTO> dtoList = new ArrayList<MateTagDTO>();
		Connection conn = dataSource.getConnection();
		String sql = "select t.postcode, t.memberCode, t2.name"
				+ " from(select postcode, memberCode from t_mateTag where postCode = ?) t, t_member t2"
				+ " where t.memberCode = t2.memberCode";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, postCode);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			MateTagDTO dto = new MateTagDTO();
			dto.setPostCode(rs.getInt("postcode"));
			dto.setMemberCode(rs.getInt("memberCode"));
			dto.setName(rs.getString("name"));
			dtoList.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();

		return dtoList;
	}

	// 친구태그 테이블에 레코드 삽입
	@Override
	public int tagAdd(ArrayList<MateTagDTO> list) throws SQLException {

		int result = 0;
		int result2 = 0;
		Connection conn = dataSource.getConnection();
		String sql = "insert into t_mateTag(postCode, memberCode) values(?, ?)";
		String sql2 = "insert into t_alert(memberCode, TABLENAMECODE, codeName, alertDate, confirm, delHide)"
			    + " values(?, 1, ?, sysdate, 0, 0)";

		for (MateTagDTO dto : list) {
			// 친구태그 테이블 삽입
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPostCode());
			pstmt.setInt(2, dto.getMemberCode());
			result += pstmt.executeUpdate();
			pstmt.close();
			// 알림테이블 삽입
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, dto.getMemberCode());
			pstmt2.setInt(2, dto.getPostCode());
			result2 += pstmt2.executeUpdate();
			pstmt2.close();
		}
		
		
		

		conn.close();
		return result;
	}

	// 게시물사진 테이블에 레코드 삽입
	@Override
	public int photoAdd(PostPhotoDTO dto) throws SQLException {
		System.out.println("dto.getPostCode()"+dto.getPostCode());
		int result = 0;
		Connection conn = dataSource.getConnection();
		String sql = "insert into t_postPhoto(postCode, photoURL) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getPostCode());
		pstmt.setString(2, dto.getPhotoURL());
		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	// 게시물 수정
	@Override
	public int postUpdate(int postCode, String postContent) throws SQLException {

		Connection conn = dataSource.getConnection();
		String sql = "update t_post set postContent = ? where postCode = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, postContent);
		pstmt.setInt(2, postCode);
		int result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	// 친구태그 수정(1개) - 사실상 추가
	@Override
	public int tagUpdate(int postCode, String mateCode) throws SQLException {

		Connection conn = dataSource.getConnection();
		String sql = "insert into t_mateTag(postCode, memberCode) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, postCode);
		pstmt.setString(2, mateCode);
		int result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
		return result;
	}

	// 사진 수정
	@Override
	public int photoUpdate(String url) throws SQLException {

		return 0;
	}

	// 예약 게시글 추가
	@Override
	public int reservePostAdd(PostDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into t_reservePost(reserveCode,memberCode,postContent,"
				+ "reserveDate,mateCode,pungCode,reserveTime) values(reserveSeq.nextval,"
				+ "?,?,default,?,?,to_date(?,'YYYYMMDD HH24miss'))";

		Connection conn = dataSource.getConnection();

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, dto.getMemberCode());
		stmt.setString(2, dto.getPostContent());
		stmt.setString(3, dto.getMateCode());
		stmt.setInt(4, dto.getPungCode());
		String reserveTime = dto.getYear() + dto.getMonth() + dto.getDate()
				+ " " + dto.getHour() + "0000";
		stmt.setString(5, reserveTime);

		int result = 0;
		result = stmt.executeUpdate();

		conn.close();
		stmt.close();

		return result;
	}

	// 예약 게시글 친구 태그
	@Override
	public int reserveTagAdd(MateTagDTO dto) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "insert into t_reserveMateTag(reserveCode,memberCode) values(?,?)";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, dto.getPostCode());
		stmt.setInt(2, dto.getMemberCode());

		int result = 0;
		result = stmt.executeUpdate();

		conn.close();
		stmt.close();

		return result;
	}

	// 예약 게시글 사진 추가
	@Override
	public int reservePhotoAdd(PostPhotoDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into t_reservePostPhoto(reserveCode,photoURL) values(?,?)";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, dto.getPostCode());
		stmt.setString(2, dto.getPhotoURL());

		int result = 0;
		result = stmt.executeUpdate();

		conn.close();
		stmt.close();

		return result;
	}

	// 예약 게시글 삭제
	@Override
	public int reservePostDelete(int reserveCode) throws SQLException {

		String sql = "update t_reservePostPhoto set delHide=1 where reserveCode=?";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reserveCode);

		int result = 0;
		result = stmt.executeUpdate();

		conn.close();
		stmt.close();

		return result;
	}

	// 게시글 삭제
	@Override
	public int delPost(int postCode) throws SQLException {

		int result = 0;
		Connection conn = dataSource.getConnection();
		String sql = "update t_post set delHide = 1 where postCode = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, postCode);
		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();
		return result;
	}

	// 친구태그 삭제
	public int delMateTag(int postCode, int memberCode) throws SQLException {

		int result = 0;

		// 친구태그 테이블 삭제
		Connection conn = dataSource.getConnection();
		String sql = "delete from t_mateTag where postCode = ? and memberCode = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, postCode);
		pstmt.setInt(2, memberCode);
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();

		/*
		 * // 태그되서 작동한 알림 삭제 - 보류 String sql2 =
		 * "delete from t_alert where memberCode = ? and tableNameCode = 1 and codeName = ?"
		 * ; PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		 * pstmt2.setInt(1, memberCode); pstmt2.setInt(2, postCode); result +=
		 * pstmt2.executeUpdate(); pstmt2.close();
		 */

		return result;

	}

	// 내스토리 게시글 받아오기
	public ArrayList<PostDTO> getmyStory(int memberCode, int start, int end) {
		ArrayList<PostDTO> list = new ArrayList<PostDTO>();
		String sql = "{call myList(?,?,?,?)}";

		Connection conn;
		try {
			conn = dataSource.getConnection();

			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.setInt(2, start);
			stmt.setInt(3, end);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);

			stmt.executeUpdate();
			ResultSet rs = (ResultSet) stmt.getObject(4);

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setMemberCode(rs.getInt("memberCode"));
				dto.setPostCode(rs.getInt("postCode"));
				dto.setName(rs.getString("name"));
				dto.setPhotoURL(rs.getString("photoURL"));
				dto.setPostContent(rs.getString("postContent"));
				dto.setPostDate(rs.getString("postDate"));
				dto.setPungCode(rs.getInt("pungCode"));
				dto.setLikeCount(rs.getInt("likeCount"));
				dto.setHateCount(rs.getInt("hateCount"));
				dto.setMateCode(rs.getString("mateCode"));
				dto.setPostphotoURL(rs.getString("postphotoURL"));
				dto.setMateName(rs.getString("mateName"));

				// 펑 삭제 여부 확인:펑미적용-0 펑 적용-1
				dto.setPungCheck(0);

				if (rs.getInt("pungCode") != 0) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
					Date postTime = format.parse(rs.getString("postDate"));

					Calendar pungTime = Calendar.getInstance();
					pungTime.setTime(postTime);
					pungTime.add(Calendar.MINUTE, (rs.getInt("pungCode")));
					Date pungTime2 = pungTime.getTime();

					Calendar now = Calendar.getInstance();
					now.getTime();
					
					Date nowTime = now.getTime();
					long a=(pungTime2.getTime()-nowTime.getTime())/(1000*60);
					//System.out.println("nowTime2-postTime2"+a);
					dto.setPungCode((int)a);
					
					if (a <= 0) // 포스팅 시간이 펑 시간보다 작을 경우
					{	dto.setPungCheck(1);					
					
					}				


				}

				list.add(dto);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	// 마이 스토리 게시글 카운트
	@Override
	public int myListCount(int memberCode) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "{call pre_post_myListCount(?,?)}";

		Connection conn;

		try {
			conn = dataSource.getConnection();
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setInt(1, memberCode);
			stmt.registerOutParameter(2, Type.INT);

			stmt.executeQuery();

			result = stmt.getInt(2);

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 펑 게시글 펑 작동
	@Override
	public int pungPostDelete() throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		// 펑옵션으로 삭제된 게시글 이미지 삽입 프로시저 호출
		String sql = "{call prc_pung}";

		CallableStatement cstmt = conn.prepareCall(sql);

		result = cstmt.executeUpdate();

		cstmt.close();	
		conn.close();

		return result;

	}

	//펑 게시글 가져오기
	@Override
	public ArrayList<PostDTO> getPungList(int memberCode, int start, int end)
			throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<PostDTO> list=new ArrayList<PostDTO>();
		
		String sql="{call pre_pungPost_list(?,?,?,?)";
		
		Connection conn=dataSource.getConnection();
		
		CallableStatement stmt=conn.prepareCall(sql);
		stmt.setInt(1, memberCode);
		stmt.setInt(2, start);
		stmt.setInt(3, end);
		stmt.registerOutParameter(4, OracleTypes.CURSOR);
	
		stmt.executeQuery();
		
		ResultSet rs=(ResultSet) stmt.getObject(4);
		
		while(rs.next())
		{
			PostDTO dto = new PostDTO();
			dto.setMemberCode(rs.getInt("memberCode"));
			dto.setPostCode(rs.getInt("postCode"));
			dto.setName(rs.getString("name"));
			dto.setPhotoURL(rs.getString("photoURL"));
			dto.setPostContent(rs.getString("postContent"));
			dto.setPostDate(rs.getString("postDate"));
			dto.setPungCode(rs.getInt("pungCode"));
			dto.setMateCode(rs.getString("mateCode"));
			dto.setPostphotoURL(rs.getString("postphotoURL"));
			dto.setMateName(rs.getString("mateName"));
						
			// 펑 삭제 여부 확인:펑미적용-0 펑 적용-1
			dto.setPungCheck(0);

			try {
			if (rs.getInt("pungCode") != 0) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				Date postTime = format.parse(rs.getString("postDate"));

				Calendar pungTime = Calendar.getInstance();
				pungTime.setTime(postTime);
				pungTime.add(Calendar.MINUTE, (rs.getInt("pungCode")));
				Date pungTime2 = pungTime.getTime();

				Calendar now = Calendar.getInstance();
				now.getTime();
				
				Date nowTime = now.getTime();
				long a=(pungTime2.getTime()-nowTime.getTime())/(1000*60);
				//System.out.println("nowTime2-postTime2"+a);
				dto.setPungCode((int)a);
				
				if (a <= 0) // 포스팅 시간이 펑 시간보다 작을 경우
				{	dto.setPungCheck(1);					
				
				}				

			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			list.add(dto);

		}
		
		stmt.close();
		rs.close();
		conn.close();
		
		
		return list;
	}

	//펑 게시글 카운트
	@Override
	public int getPungListCount(int memberCode) throws SQLException {
		// TODO Auto-generated method stub
		int result=0;
		String sql="select count(*) as count from t_post where memberCode=? and delhide=0 and postContent != ' ' and pungCode>0";
		
		Connection conn=dataSource.getConnection();
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, memberCode);
		
		ResultSet rs=stmt.executeQuery();
		if(rs.next())
			result=rs.getInt("count");
		
		stmt.close();
		conn.close();		
		
		
		return result;
	}
	@Override
	public void pungPostModify(PostDTO dto)
	{//펑 게시글 수정 : 게시글 내용만
		String sql="update t_post set postContent=? where postCode=?";
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, dto.getPostContent());
			stmt.setInt(2, dto.getPostCode());
			
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//펑 게시글 친구 태그 수정- 수정 전 삭제
	public void pungTagModifyBefore(int postCode)
	{
		String sql="delete from t_mateTag where postCode=?";			
	
		try {
			Connection conn=dataSource.getConnection();
			
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, postCode);
			stmt.executeUpdate();			
			
			conn.close();
			stmt.close();
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}


	
	//펑 게시글 친구 태그 수정
	public void pungTagModify(MateTagDTO dto)
	{			
		String sql2="insert into t_mateTag(postCode,memberCode) values(?,?)";
		
	
		try {
			Connection conn=dataSource.getConnection();				
			
			PreparedStatement stmt2=conn.prepareStatement(sql2);
			stmt2.setInt(1, dto.getPostCode());
			stmt2.setInt(2, dto.getMemberCode());
			
			stmt2.executeUpdate();
			
			conn.close();
			stmt2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	//펑 게시글 유저가 삭제
	public void pungPostDelete(int postCode)
	{
		String sql="update t_post set delHide=1 where postCode=?";
				
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postCode);
			
			stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 알림 게시글 가져오기
	@Override
	public PostDTO alertPost(int postCode) throws SQLException {

		Connection conn = dataSource.getConnection();
		
		PostDTO pDto = new PostDTO();
		
		String sql = "select postCode, memberCode, name, postContent, "
				+ "photoURL, postDate, pungCode, likeCount, hateCount, "
				+ "mateCode, mateName, delHide "
				+ "from view_alert_postView where postCode = ? and delHide = 0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, postCode);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			
			pDto.setPostCode(rs.getInt("postCode"));
			pDto.setMemberCode(rs.getInt("memberCode"));
			pDto.setName(rs.getString("name"));
			pDto.setPostContent(rs.getString("postContent"));
			pDto.setPostphotoURL(rs.getString("photoURL"));
			pDto.setPostDate(rs.getString("postDate"));
			pDto.setPungCode(rs.getInt("pungCode"));
			pDto.setLikeCount(rs.getInt("likeCount"));
			pDto.setHateCount(rs.getInt("hateCount"));
			pDto.setMateCode(rs.getString("mateCode"));
			pDto.setMateName(rs.getString("mateName"));
			pDto.setDelHide(rs.getInt("delHide"));
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return pDto;
	}
 
	// 알림 친구태그 가져오기
	@Override
	public ArrayList<MateTagDTO> alertMateTag(int postCode) throws SQLException {
		
		Connection conn = dataSource.getConnection();
		
		ArrayList<MateTagDTO> mtList = new ArrayList<MateTagDTO>();
		
		String sql = "select postCode, memberCode, name from view_alert_mateTag where postCode = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, postCode);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			MateTagDTO mtDto = new MateTagDTO();
			
			mtDto.setPostCode(rs.getInt("postCode"));
			mtDto.setMemberCode(rs.getInt("memberCode"));
			mtDto.setName(rs.getString("name"));
			
			mtList.add(mtDto);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return mtList;
	}
	
}
