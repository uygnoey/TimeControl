package com.team01.mate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MateResponseC implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String mateCode = request.getParameter("mateCode");
		String mateName = request.getParameter("mateName");
		
		System.out.println(mateCode);
		System.out.println(mateName);
		
		mav.addObject("mateName", mateName);
		mav.addObject("mateCode", mateCode);
		
		mav.setViewName("WEB-INF/Sources/MateResponse.jsp");
		return mav;
	}


}
