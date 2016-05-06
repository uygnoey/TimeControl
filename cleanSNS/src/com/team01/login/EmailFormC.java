package com.team01.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ILoginDAO;

public class EmailFormC implements Controller
{
	private ILoginDAO dao;
	
	public void setDao(ILoginDAO dao) {
		this.dao = dao;
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("utf8");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		String name = request.getParameter("hiddenName");
		String pw = request.getParameter("hiddenEmail");
		
		System.out.println(name);
		System.out.println(pw);
		
		try {
			
			String email = dao.emailfind(name, pw);
			session.setAttribute("email", email);
			session.setAttribute("name", name);
			System.out.println(email);
			
			if(email==null)
			{
				//System.out.println("다시 입력");
				mav.setViewName("redirect:noresult.htm");
			}
		
			else 
			{	
				System.out.println("성 공");
				mav.setViewName("redirect:emailfindresult.htm");
				
			}
	
			
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("redirect:noresult.htm");
		}
		
		
		return mav;
	}

}
