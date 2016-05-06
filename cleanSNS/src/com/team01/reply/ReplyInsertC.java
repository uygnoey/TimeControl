package com.team01.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReplyDAO;
import com.team01.dto.ReplyDTO;

public class ReplyInsertC implements Controller {

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
		
		try {

			request.setCharacterEncoding("utf8");

			int postCode = Integer.parseInt(request.getParameter("postCode"));

			// int memberCode =
			// Integer.parseInt(request.getParameter("memberCode"));
			// 세션에서 멤버코드 얻어오기
			int memberCode = (int) session.getAttribute("memberCode");
			String replyContent = request.getParameter("replyContent");

			ReplyDTO reply = new ReplyDTO();

			reply.setPostCode(postCode);
			reply.setMemberCode(memberCode);
			reply.setReplyContent(replyContent);

			dao.replyAdd(reply);

			mav.setViewName("redirect:newsfeed.htm");

		} catch (Exception e) {

			mav.setViewName("redirect:underconstruction,htm");

			e.printStackTrace();
			
		}

		return mav;
	}

}
