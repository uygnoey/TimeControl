package com.team01.story;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.MateDAO;
import com.team01.dto.MateDTO;

public class DelMateC implements Controller{

	private MateDAO dao;

	public void setDao(MateDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String mateMemberCode = request.getParameter("mateCode");
		HttpSession session = request.getSession();
		int memberCode = (int)session.getAttribute("memberCode");
		
		MateDTO dto = new MateDTO();
		dto.setMemberCode(memberCode);
		dto.setMateMemberCode(Integer.parseInt(mateMemberCode));
		
		ModelAndView mav = new ModelAndView();
		try{
			
			dao.delMate(dto);
			
		}catch(Exception e){
			System.out.println(e.toString());			
		}
		
		mav.setViewName("storymatelist.htm");
		
		return mav;
	}
	
	
}
