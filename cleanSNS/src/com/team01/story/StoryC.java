package com.team01.story;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IReservePostDAO;
import com.team01.dao.ReservePostDAO;

public class StoryC implements Controller
{
	private IReservePostDAO dao;
	
	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}
	
	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		//request.setCharacterEncoding("utf8");
		
		HttpSession session=request.getSession();
		int memberCode=(int) session.getAttribute("memberCode");
		String name=(String) session.getAttribute("name");
		
		int count=dao.getReservePostCount(memberCode);
		
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("name", name);
		mav.addObject("count", count);
		mav.addObject("myPhoto", session.getAttribute("myPhoto"));
		mav.setViewName("WEB-INF/Sources/Story.jsp");
		return mav;
	}
	
}
