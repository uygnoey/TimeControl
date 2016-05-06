package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;

public class PwCheckFormC implements Controller
{
	private IMemberDAO dao;
	
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		// 세션에 있는 이메일이랑 멤버코드를 가져옴.
		String email = (String)session.getAttribute("email");
		int memberCode = (int)session.getAttribute("memberCode");
		
		// 비밀번호 일치여부를 확인하기 위해 멤버코드와 이메일을 PwCheck.jsp 로 보냄.
		mav.addObject("memberCode", memberCode);
		mav.addObject("email", email);
		
		mav.setViewName("WEB-INF/Sources/PwCheck.jsp");
		return mav;
	}
}