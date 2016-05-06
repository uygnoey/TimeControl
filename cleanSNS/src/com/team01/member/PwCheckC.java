package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPwCheckDAO;
import com.team01.dto.MemberDTO;

public class PwCheckC implements Controller
{
	private IPwCheckDAO dao;

	public void setDao(IPwCheckDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		
		// 로그인할 때 세션에 저장되어 있던 이메일과 변경할 비밀번호를 가져옴.
		String email = (String)session.getAttribute("email");
		String pw = request.getParameter("pw");
		
		//System.out.println(pw);
		//System.out.println(email);
		
		try
		{
			// 로그인할 때 적용된 멤버코드와 비밀번호 일부여부를 확인하기 위해 사용한 비밀번호의 일치를 판단하기 위해 matchPw 메소드를 정수형으로 저장.
			int memberCode = dao.matchPw(email, pw);
			mav.addObject("memberCode", memberCode);
			
			mav.setViewName("WEB-INF/Sources/PwCheckAjax.jsp");
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
