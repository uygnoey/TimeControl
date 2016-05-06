package com.team01.postView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPostDAO;

public class RListC implements Controller {
	
	private IPostDAO dao;	

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		HttpSession session=request.getSession();
		int memberCode=(int)session.getAttribute("memberCode");
		
		int count=dao.rightListCount(memberCode);
			
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/RList.jsp");
		mav.addObject("rcount", count);
		
		return mav;
	}
}
