package com.team01.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;
import com.team01.dao.MemberDAO;
import com.team01.dto.MemberDTO;

public class MemberEmailCheckC implements Controller
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
		String newEmail = request.getParameter("email");	// 가입할 이메일
		int count = dao.emailCheck2(newEmail);

		ModelAndView mav = new ModelAndView();
		
		try
		{
			mav.addObject("count", count);
			
			
			/*result = dao.emailCheck();
			
			for(MemberDTO uemail : result)
			{
				if(newEmail.equals(uemail.getEmail()))
				{
					check = 0;
					mav.addObject("check", check);
				}
				else
				{
					check = 1;
					mav.addObject("check", check);
				}
			}*/
		} 
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		mav.setViewName("WEB-INF/Sources/Ajax.jsp");
		return mav;
	}
}
