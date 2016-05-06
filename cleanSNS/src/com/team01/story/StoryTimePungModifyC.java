package com.team01.story;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoryTimePungModifyC {

	@RequestMapping
	public ModelAndView storyTimePungModify(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/StoryTimePungModify.jsp");
		mav.addObject("postCode", request.getParameter("postCode"));
		mav.addObject("postContent", request.getParameter("postContent"));
		mav.addObject("name", request.getParameter("name"));		
		mav.addObject("mateName", request.getParameter("mateName"));
		mav.addObject("postDate", request.getParameter("postDate"));
		mav.addObject("tagCount", request.getParameter("tagCount"));
		mav.addObject("taglist", request.getParameter("taglist"));
		mav.addObject("taglistcode",request.getParameter("taglistcode"));
		mav.addObject("photoURL", request.getParameter("photoURL"));
		mav.addObject("postPhotoURL", request.getParameter("postPhotoURL"));
		mav.addObject("pungCode", request.getParameter("pungCode"));
		
		return mav;
	}
}
