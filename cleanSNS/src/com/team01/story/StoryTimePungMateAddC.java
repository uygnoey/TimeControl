package com.team01.story;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoryTimePungMateAddC {

	@RequestMapping
	public ModelAndView StoryTimePungMateAdd()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/PungMateTag.jsp");
		
		return mav;
	}
}
