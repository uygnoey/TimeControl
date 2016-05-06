package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;

@Controller
public class StoryTimePungDeleteResultC {
	
	private IPostDAO dao;	

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView storyTimePungDeleteResult(HttpServletRequest request)
	{
		int delPostCode=Integer.parseInt(request.getParameter("delPostCode"));
		
		try {
			dao.pungPostDelete(delPostCode);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/StoryTimePungDelete.jsp");
		mav.addObject("count", request.getParameter("count"));
		return mav;
	}
}
