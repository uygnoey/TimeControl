package com.team01.member;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.MateDAO;

@Controller
public class CategoryMateAddC {
	
	MateDAO dao;	

	public void setDao(MateDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping
	public ModelAndView categoryMateAdd(HttpServletRequest request) throws SQLException
	{
		//멤버 코드 세션처리
		HttpSession session=request.getSession();	
				
		//비정상 접속시 처리	
		if(session.getAttribute("memberCode")==null)
		{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("login.htm");
				return mav;
		}		
						
		int memberCode=(int)session.getAttribute("memberCode");
				
		int count=dao.listCount(memberCode);
		ModelAndView mav=new ModelAndView();
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/CategoryMateAdd.jsp");
		
		return mav;
	}
}
