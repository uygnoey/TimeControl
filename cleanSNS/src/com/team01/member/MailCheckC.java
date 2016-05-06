package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;

public class MailCheckC implements Controller{

	private IMemberDAO dao;

	public void setDao(IMemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String email = req.getParameter("email");
		
		int count = dao.emailCheck(email);
		
		System.out.println(count);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("count", count);
		
		mav.setViewName("WEB-INF/Sources/MailCheckAJAX.jsp");
		
		return mav;
	}
	
	
	
}
