package com.team01.member;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.MateDAO;
import com.team01.dto.MateDTO;
import com.team01.dto.MemberDTO;

@Controller
public class CategoryMateAddListC {
	
	MateDAO dao;	

	public void setDao(MateDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView CategoryMateAddList(HttpServletRequest request) throws SQLException
	{
		
		//멤버 코드 세션처리
		HttpSession session=request.getSession();	
		
		//비정상 접속시 처리	
		if(session.getAttribute("memberCode")==null)
		{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("login.htm");
				return mav;
		}	
		
		//System.out.println(Integer.parseInt(request.getParameter("start")));
				
		int memberCode=(int)session.getAttribute("memberCode");
		
		int count=dao.listCount(memberCode);
	
		ArrayList<MemberDTO> list=dao.allList(memberCode, Integer.parseInt(request.getParameter("start")));
				
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/CategoryMateAddList.jsp");
		
		return mav;
	}
}
