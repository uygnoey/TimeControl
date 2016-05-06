package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;
import com.team01.dto.MemberDTO;

public class MemberRemoveC implements Controller
{
	private IMemberDAO dao;

	// set 구성
	public void setDao(IMemberDAO dao)
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
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}	
		
		ModelAndView mav = new ModelAndView();
		
		int memberCode = (int)session.getAttribute("memberCode");
		
		try
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setMemberCode(memberCode);
			
		//	dto.setName(name);
		//	dto.setPw(pw);
		//	dto.setPw(pwCheck);
		
			dao.memberRemove(dto);
		
		//	mav.addObject("name", name);
			mav.setViewName("redirect:loginform.htm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		session.invalidate();
		
		return mav;
		
	}
	
	
}
