package com.team01.story;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.MateDAO;
import com.team01.dto.MateDTO;

public class MateAjax2C implements Controller{

	private MateDAO dao;
	
	public void setDao(MateDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("name")==null){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}
		
		int memberCode = (int)session.getAttribute("memberCode");
		
		String mate = request.getParameter("mate");
		String code = request.getParameter("code");	// 친구멤버코드 받기
		
		MateDTO dto = new MateDTO();
		dto.setMemberCode(memberCode);
		dto.setMateMemberCode(Integer.parseInt(code));
		dto.setMateGroupCode(Integer.parseInt(mate));
		
		try{
		
			dao.updateMateGroup(dto);
			
		}catch(Exception e){
			System.out.println(e.toString());			
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", mate);
		mav.setViewName("WEB-INF/Sources/MateAjax2.jsp");
		return mav;
	}
}
