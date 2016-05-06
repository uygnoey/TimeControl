package com.team01.story;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PostCardReserveModifyC {

	@RequestMapping
	public ModelAndView PostCardReserveModify(HttpServletRequest request) throws UnsupportedEncodingException	
	{//예약게시글 수정 폼 컨트롤러
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("reserveCode", request.getParameter("reserveCode"));
		mav.addObject("postContent", request.getParameter("postContent"));
		mav.addObject("name", request.getParameter("name"));		
		mav.addObject("mateName", request.getParameter("mateName"));
		mav.addObject("reserveTime", request.getParameter("reserveTime"));
		mav.addObject("reserveDate", request.getParameter("reserveDate"));
		mav.addObject("tagCount", request.getParameter("tagCount"));
		mav.addObject("taglist", request.getParameter("taglist"));
		mav.addObject("taglistcode",request.getParameter("taglistcode"));
		mav.addObject("photoURL", request.getParameter("photoURL"));
		mav.addObject("postphotoURL", request.getParameter("postphotoURL"));
	//	System.out.println(request.getParameter("postphotoURL"));
	
		mav.setViewName("WEB-INF/Sources/PostCardReserveModify.jsp");
	

		return mav;
	}
}
