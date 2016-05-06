package com.team01.mate;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.MateDAO;
import com.team01.dto.MemberDTO;

public class MateTagSearchC implements Controller{

	private MateDAO dao;

	public void setDao(MateDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		int memberCode = (int)session.getAttribute("memberCode");
		String name = request.getParameter("text");
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		try{
			
			list = dao.tagList(memberCode,name);
			
			
			mav.addObject("list", list);
			System.out.println(list);
			mav.setViewName("WEB-INF/Sources/MateTag.jsp");
		
		}catch(Exception e){
			
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
	
}
