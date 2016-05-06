package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReservePostDAO;


public class NewsFeedC implements Controller {

	private IReservePostDAO dao;
	
	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse res) throws Exception {
		
		//비정상 접속시 처리
		HttpSession session=request.getSession();
		if(session.getAttribute("memberCode")==null)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login.htm");
			return mav;
		}	
		
		//예약 게시글 처리
		dao.reservePostDel();		//System.out.println("예약");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/Sources/newsfeed.jsp");
		mav.addObject("myPhoto", session.getAttribute("myPhoto"));
		
		return mav;

	}

}
