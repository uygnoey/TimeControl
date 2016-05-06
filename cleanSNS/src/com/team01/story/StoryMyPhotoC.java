package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostPhotoDAO;

@Controller
public class StoryMyPhotoC {
	
	IPostPhotoDAO dao;	

	public void setDao(IPostPhotoDAO dao) {
		this.dao = dao;
	}	
	
	@RequestMapping
	public ModelAndView storyMyPhoto(HttpServletRequest request)
	{
		int count=0;
		
		HttpSession session=request.getSession();
		try {
			count=dao.getMyPhotoCount((int)session.getAttribute("memberCode"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/StoryMyPhoto.jsp");
		
		return mav;
	}

}
