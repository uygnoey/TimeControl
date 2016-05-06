package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;
import com.team01.dao.MemberDAO;
import com.team01.dto.MemberDTO;

public class MemberInsertC implements Controller
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
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("email")==null)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:memberinsertform.htm");
			return mav;
		}
		
		
		
		ModelAndView mav = new ModelAndView();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pwCheck");
		
		try
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setName(name);
			dto.setEmail(email);
			dto.setPw(pw);
			dto.setPw(pwCheck);
			
			dao.memberInsert(dto);
			
			mav.setViewName("redirect:loginform.htm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
