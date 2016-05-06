package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SideC implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse res) throws Exception {
		
		
		HttpSession session=request.getSession();
		String name=(String) session.getAttribute("name");
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.addObject("myPhoto", session.getAttribute("myPhoto"));
		mav.setViewName("WEB-INF/Sources/side.jsp");

		return mav;

	}

}
