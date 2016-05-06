package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexC implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession();
		
		if (session.getAttribute("memberCode") != null) {
			mav.setViewName("newsfeed.htm");
		} else {
			mav.setViewName("login.htm");
		}
		
		return mav;
	}

}
