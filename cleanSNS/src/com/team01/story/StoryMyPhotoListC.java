package com.team01.story;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostPhotoDAO;
import com.team01.dto.PostPhotoDTO;

@Controller
public class StoryMyPhotoListC {
	
	IPostPhotoDAO dao;
	

	public void setDao(IPostPhotoDAO dao) {
		this.dao = dao;
	}


	@RequestMapping
	public ModelAndView storyMyPhotoList(HttpServletRequest request)
	{
		
		ArrayList<PostPhotoDTO> list=new ArrayList<PostPhotoDTO>();
		int count=0;
		HttpSession session=request.getSession();
		int start=0;
		int last=0;
			
		try {
			count=dao.getMyPhotoCount((int)session.getAttribute("memberCode"));
						
			start=Integer.parseInt(request.getParameter("start"));
			last=start+14;
			
			if(last>=count)
				last=count;
			
			list=dao.getMyPhotoList((int)session.getAttribute("memberCode"),start,last);
			//System.out.println(start+"와"+last);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("myPhotoList", list);
		mav.addObject("nowcount", list.size());
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/StoryMyPhotoList.jsp");
		
		return mav;
	}
}
