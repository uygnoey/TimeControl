package com.team01.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReplyDAO;
import com.team01.dto.ReplyDTO;

public class ReplyUpdateC implements Controller {

	private IReplyDAO dao;

	public void setDao(IReplyDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		// 로그인 세션 처리
		HttpSession session = req.getSession();
		if (session.getAttribute("name") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}

		ReplyDTO reply = new ReplyDTO();

		ModelAndView mav = new ModelAndView();

		try {

			req.setCharacterEncoding("utf8");

			reply.setReplyCode(Integer.parseInt(req.getParameter("replyCode")));
			reply.setReplyContent(req.getParameter("replyContent"));

			dao.replyModify(reply);

			mav.setViewName("redirect:newsfeed.htm");

		} catch (Exception e) {

			mav.setViewName("redirect:underconstruction,htm");

			e.printStackTrace();

		}

		return mav;
	}

}
