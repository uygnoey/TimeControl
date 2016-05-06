package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @file FooterController.java
 * @author YeonGyu
 * @class FooterController
 * @note footer부분 페이지 뿌려주기
 */

public class FooterC implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("WEB-INF/Sources/footer.jsp");
		
		return mav;
	}

}
