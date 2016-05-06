package com.team01.postView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ICleanerDAO;


public class CleanerC implements Controller
{
	private ICleanerDAO dao;
	
	public void setDao(ICleanerDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		
		ModelAndView mav = new ModelAndView();
		String radio = request.getParameter("radio");
		System.out.println(radio);
		
		int num = Integer.parseInt(radio);
		int count=0;
		
		try {
			if(num==1)
			{
				count = dao.cleaner1();
				System.out.println(count);

			}
			
			else if(num==2)
			{
				count= dao.cleaner2();
				System.out.println(count);
			}
			
			else if(num==3)
			{
				count=dao.cleaner3();
				System.out.println(count);
			}
			
			else if(num==4)
			{
				count=dao.cleaner4();
				System.out.println(count);
			}
			mav.setViewName("redirect:newsfeed.htm");
			
			
		} catch (Exception e) {
		
			System.out.println(e.toString());
			
		}
		
	
		return mav;
	}

}
