package com.team01.reply;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReplyDAO;

/**
 * @file ReplyDeleteC.java
 * @author YeonGyu
 * @class ReplyDeleteC
 */

public class ReplyDeleteC implements Controller {

	private IReplyDAO dao;

	public void setDao(IReplyDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {

		// 로그인 세션 처리
		HttpSession session = request.getSession();
		if (session.getAttribute("name") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}
		ModelAndView mav = new ModelAndView();

		int replyCode = Integer.parseInt(request.getParameter("replyCode"));

		try {
			
			dao.replyDelete(replyCode);

			mav.setViewName("redirect:newsfeed.htm");
			
		} catch (SQLException e) {
		
			mav.setViewName("redirect:underconstruction,htm");
			
			e.printStackTrace();
			
		}


		return mav;

	}

}
