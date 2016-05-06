package com.team01.story;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;

@Controller
public class StoryTimePungC {
	
	private IPostDAO dao;	

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView storyTimePung(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int memberCode=(int) session.getAttribute("memberCode");
		
		ArrayList<PostDTO> list=new ArrayList<PostDTO>();
		ArrayList<MateTagDTO> mateList=new ArrayList<MateTagDTO>();
		
		
		//리스트 불러오기		
		String page=(String) request.getParameter("start");
						
		int start=Integer.parseInt(page);
		int end=start+9;
				
		//총 카운트 불러오기
		//마지막글인지 확인
		int count=0;
		try {
			count = dao.getPungListCount(memberCode);
			
			if(count==start)
				end=start;		
			
			list=dao.getPungList(memberCode, start, end);
			mateList=dao.mateTagList();//친구 태그 불러오기
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("mateList", mateList);
		mav.addObject("count", count);
	
		mav.setViewName("WEB-INF/Sources/StoryTimePung.jsp");
		return mav;
	}
}
