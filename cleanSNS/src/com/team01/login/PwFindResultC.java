package com.team01.login;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberDAO;
import com.team01.mail.MailTransterObject;

public class PwFindResultC implements Controller{
	
	private IMemberDAO dao;

	public void setDao(IMemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		
		String email = req.getParameter("email");
		
		// 비밀번호 생성
		PasswordCreatorM pcm = new PasswordCreatorM();
		
		String password = pcm.passwordCreator();
		
		try {
			dao.passwordUpdate(email, password);
			
			MailTransterObject mto = new MailTransterObject();
			
			mto.sendPassword(email, name, password);
			
			mav.setViewName("redirect:loginform.htm");
			
		} catch (SQLException e) {
			
			mav.setViewName("redirect:underconstruction.htm");
			e.printStackTrace();
		}
		
		
		
		return mav;
		
	}
	
	
	
}
