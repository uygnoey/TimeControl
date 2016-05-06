package com.team01.mateStory;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;

public class MateListC {

	private IPostDAO dao;	

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping
	public ModelAndView myStory(HttpServletRequest request) throws SQLException
	{	
		
		// 로그인 세션 처리
		HttpSession session = request.getSession(); 
		if(session.getAttribute("name")==null){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}
		
		String mateCode = request.getParameter("mateCode");
		
		int count=dao.myListCount(Integer.parseInt(mateCode)); 
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("count", count);
		mav.addObject("mateCode", mateCode);
		mav.setViewName("WEB-INF/Sources/mateStoryList.jsp");
		
		return mav;
		
	}
}
