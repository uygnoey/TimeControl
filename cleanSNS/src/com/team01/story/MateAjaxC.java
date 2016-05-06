package com.team01.story;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MateAjaxC implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String c = request.getParameter("category");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ca", c);
		mav.setViewName("WEB-INF/Sources/MateAjax.jsp");
		return mav;
	}

	
}
