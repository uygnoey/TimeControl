package com.team01.mate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MatePopupC implements Controller

{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		String memberCode = request.getParameter("memberCode");
		System.out.println(memberCode);
		mav.addObject("memberCode", memberCode);
		mav.setViewName("WEB-INF/Sources/MatePopup.jsp");
		
		return mav;
	}

}
