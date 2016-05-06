package com.team01.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPostDAO;

public class PostDeleteC implements Controller{

	private IPostDAO dao;

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String delPostCode = request.getParameter("delPostCode");
		
		try{
			
			dao.delPost(Integer.parseInt(delPostCode));
			
		}catch(Exception e){
			
			System.out.println(e.toString());
		}
		
		mav.setViewName("redirect:newsfeed.htm");
		
		return mav;
	}
	
	
}
