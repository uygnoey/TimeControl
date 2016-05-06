package com.team01.member;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.MateDAO;
import com.team01.dto.MemberDTO;

@Controller
public class CategoryMateSearchC {
	MateDAO dao;	

	public void setDao(MateDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping
	public ModelAndView categoryMateSearch(HttpServletRequest request) throws SQLException
	{//카테고리 친구 추가시 검색 기능
		//멤버 코드 세션처리
		HttpSession session=request.getSession();	
				
		//비정상 접속시 처리	
		if(session.getAttribute("memberCode")==null)
		{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("login.htm");
				return mav;
		}			
						
		int memberCode=(int)session.getAttribute("memberCode");
		String name=request.getParameter("mate");
		
		ArrayList<MemberDTO> list=dao.tagList(memberCode,name);
		
		 
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("WEB-INF/Sources/CategoryMateAddList.jsp");
		
		return mav;
		 
	}

}
