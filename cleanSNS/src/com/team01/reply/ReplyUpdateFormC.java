package com.team01.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReplyDAO;

public class ReplyUpdateFormC implements Controller {

	private IReplyDAO dao;

	public void setDao(IReplyDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		int replyCode = Integer.parseInt(req.getParameter("replyCode"));

		ModelAndView mav = new ModelAndView();

		mav.addObject("replyCode", replyCode);

		mav.setViewName("WEB-INF/Sources/ReplyUpdate.jsp");

		return mav;

	}

}
