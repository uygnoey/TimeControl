package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;
import com.team01.dao.IReservePostDAO;

@Controller
public class MyListReserveC {
	private IReservePostDAO dao;	

	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping
	public ModelAndView myStory(HttpServletRequest request) throws SQLException
	{	
		
		HttpSession session=request.getSession();
		int memberCode=(int)session.getAttribute("memberCode");
		
		int count=dao.getReservePostCount(memberCode);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/myStoryListReserve.jsp");
		
		return mav;
		
	}
}
