package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IReservePostDAO;

@Controller
public class MyListReserveDeleteC {

	private IReservePostDAO dao;
	
	
	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView myListReserveDelete(HttpServletRequest request)
	{
		
		int delPostCode=Integer.parseInt(request.getParameter("delPostCode"));
		
		try {
			
			dao.reservePostDelete(delPostCode);
									
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ModelAndView mav=new ModelAndView();	
		mav.setViewName("WEB-INF/Sources/PostCardReserveDelete.jsp");
		mav.addObject("count", request.getParameter("count"));
	
		return mav;
		
		
	}
}
