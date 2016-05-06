package com.team01.postView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CleanerFormC implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/Sources/Cleaner.jsp");
		return mav;
	}

	

}
