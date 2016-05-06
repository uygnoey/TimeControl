package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;
import com.team01.dao.MemberDAO;

public class MemberInsertFormC implements Controller
{
	private IMemberDAO dao;
	
	public void setDao(MemberDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		mav.addObject("sessionEmail", email);
		mav.setViewName("WEB-INF/Sources/MemberInsert.jsp");
		return mav;
		
		
	}
	
	
	

	//session.getAttribute(key) : 세션에 저장되어 있는 key에 해당하는 value 값을 얻는다.
	
	// session.setAttribute(key, value) : 세션에 새로운 key, value 값 등록
}
