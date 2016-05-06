package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UnderConstructionC implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("WEB-INF/Sources/UnderConstruction.html");
		
		return mav;
		
	}

}
