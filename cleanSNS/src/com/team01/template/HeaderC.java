package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IAlertDAO;

/**
 * @file headerController.java
 * @author YeonGyu
 * @class headerController
 */

public class HeaderC implements Controller {

	private IAlertDAO aDao;
	
	public void setaDao(IAlertDAO aDao) {
		this.aDao = aDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf8");

		HttpSession session = req.getSession();
		
		int memberCode = (int) session.getAttribute("memberCode");
		
		ModelAndView mav = new ModelAndView();
		
		int alertCount = aDao.countAlert(memberCode);
		
		mav.addObject("alertCount", alertCount);
		
		mav.setViewName("WEB-INF/Sources/header.jsp");

		return mav;
	}

}
