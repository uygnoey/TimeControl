package com.team01.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team01.dto.ReplyDTO;

/**
 * @file IReplyDAO.java
 * @author HP
 * @class IReplyDAO
 */

public interface IReplyDAO {
	/**
	 * @Interface IReplyDAO
	 * @param reply
	 * @return int
	 * @throws SQLException
	 * @brief  댓글 작성
	 */
	public int replyAdd(ReplyDTO reply) throws SQLException;
	
	/**
	 * @interface IReplyDAO
	 * @param postCode
	 * @return int
	 * @throws SQLException
	 * @brief 댓글 수 출력
	 */
	public int replyCount(int postCode) throws SQLException;

	/**
	 * @Interface IReplyDAO
	 * @param postCode
	 * @return ArrayList<ReplyDTO>
	 * @throws SQLException
	 * @brief 게시글에 해당하는 댓글 출력
	 */
	public ArrayList<ReplyDTO> replyList(int postCode) throws SQLException;
	
	public ArrayList<ReplyDTO> replyList() throws SQLException;
	
	/**
	 * @interface IReplyDAO
	 * @param reply
	 * @return int
	 * @throws SQLException
	 * @brief 댓글 수정 (update t_reply set replyContent = ? where replyCode = ?)
	 */
	public int replyModify(ReplyDTO reply) throws SQLException;
	
	/**
	 * @interface IReplyDAO
	 * @param replyCode
	 * @return int
	 * @throws SQLException
	 * @brief 댓글 삭제 (update t_reply set delHide = 1 where replyCode = ?)
	 */
	public int replyDelete(int replyCode) throws SQLException;

}
