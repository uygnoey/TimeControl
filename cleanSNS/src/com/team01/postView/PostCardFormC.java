package com.team01.postView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostCardFormC {	
		
		@RequestMapping
		public ModelAndView postList()
		{
			
			ModelAndView mav=new ModelAndView();			
			mav.setViewName("WEB-INF/Sources/PostCardForm.jsp");
			
			return mav;		
		}
		
}

	
	

