package com.team01.story;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoryMyPhotoZoomC {

	@RequestMapping
	public ModelAndView storyMyPhotoZoom(HttpServletRequest request)
	{
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("url", "img\\"+ request.getParameter("url"));
		mav.setViewName("WEB-INF/Sources/StoryMyPhotoZoom.jsp");
		return mav;
	}
	
}
