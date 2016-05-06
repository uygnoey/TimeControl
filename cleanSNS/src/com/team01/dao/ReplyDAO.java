package com.team01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.team01.dto.ReplyDTO;

public class ReplyDAO implements IReplyDAO {

	private DataSource dataSource;

	// DB연결
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int replyAdd(ReplyDTO reply) throws SQLException {

		int result;

		Connection conn = dataSource.getConnection();

		String sql = "insert into t_reply(replyCode, memberCode, postCode, replyContent) "
				+ "values(replySeq.nextval, ?, ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, reply.getMemberCode());
		pstmt.setInt(2, reply.getPostCode());
		pstmt.setString(3, reply.getReplyContent());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;
	}

	// 댓글 수 출력
	public int replyCount(int postCode) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "select count(*) as count "
				+ "from view_reply where postCode = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, postCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
			result = rs.getInt("count");

		rs.close();
		pstmt.close();
		conn.close();

		return result;

	}

	// 댓글 리스트 받기
	@Override
	public ArrayList<ReplyDTO> replyList(int postCode) throws SQLException {

		ArrayList<ReplyDTO> result = new ArrayList<ReplyDTO>();

		Connection conn = dataSource.getConnection();

		String sql = "select replyCode, postCode, memberCode, photoURL, "
				+ "name, replyContent, replyDate from view_reply "
				+ "where delHide = 0 and postCode = ?"
				+ "order by replyCode desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, postCode);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ReplyDTO reply = new ReplyDTO();

			reply.setReplyCode(rs.getInt("replyCode"));
			reply.setPostCode(rs.getInt("postCode"));
			reply.setMemberCode(rs.getInt("memberCode"));
			reply.setPhotoURL(rs.getString("photoURL"));
			reply.setName(rs.getString("name"));
			reply.setReplyContent(rs.getString("replyContent"));
			reply.setReplyDate(rs.getString("replyDate"));

			result.add(reply);

		}

		rs.close();
		pstmt.close();
		conn.close();

		return result;
	}

	// 댓글 리스트 받기
	@Override
	public ArrayList<ReplyDTO> replyList() throws SQLException {

		ArrayList<ReplyDTO> result = new ArrayList<ReplyDTO>();

		Connection conn = dataSource.getConnection();

		String sql = "select replyCode, postCode, memberCode, photoURL, "
				+ "name, replyContent, replyDate from view_reply "
				+ "where delHide = 0"
				+ "order by replyCode desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ReplyDTO reply = new ReplyDTO();

			reply.setReplyCode(rs.getInt("replyCode"));
			reply.setPostCode(rs.getInt("postCode"));
			reply.setMemberCode(rs.getInt("memberCode"));
			reply.setPhotoURL(rs.getString("photoURL"));
			reply.setName(rs.getString("name"));
			reply.setReplyContent(rs.getString("replyContent"));
			reply.setReplyDate(rs.getString("replyDate"));

			result.add(reply);

		}

		rs.close();
		pstmt.close();
		conn.close();

		return result;
	}

	// 댓글 수정
	@Override
	public int replyModify(ReplyDTO reply) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_reply set replyContent = ? where replyCode = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, reply.getReplyContent());
		pstmt.setInt(2, reply.getReplyCode());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;

	}

	// 댓글 삭제
	@Override
	public int replyDelete(int replyCode) throws SQLException {

		int result = 0;

		Connection conn = dataSource.getConnection();

		String sql = "update t_reply set delHide = 1 where replyCode = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, replyCode);

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;

	}

}
