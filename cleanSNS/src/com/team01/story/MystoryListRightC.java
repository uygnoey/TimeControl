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
public class MystoryListRightC {
	
	IPostPhotoDAO dao;	
	
	public void setDao(IPostPhotoDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView mystoryListRight(HttpServletRequest request)
	{//내 스토리의 최근사진 view
		ArrayList<PostPhotoDTO> list=new ArrayList<PostPhotoDTO>();
		HttpSession session=request.getSession();
		int memberCode=(int)(session.getAttribute("memberCode"));
		int check=0;
		int count=0;
		
		try {
			count=dao.getMyPhotoCount(memberCode);
			
			if(count >0)			
			{
				if(count>4)
					count=4;//최대 카운트 세팅
				
				list=dao.getMyPhotoList(memberCode,0,4);
				check=1;
			}
	
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		ModelAndView mav=new ModelAndView();
		mav.addObject("myPhotoList", list);
		mav.addObject("check", check);
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/StoryMyListPhoto.jsp");
				
		return mav;
		
	}

}
