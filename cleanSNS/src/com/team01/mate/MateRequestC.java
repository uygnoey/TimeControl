package com.team01.mate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.MateFindDAO;
import com.team01.dto.AlertDTO;
import com.team01.dto.MateStayDTO;

public class MateRequestC implements Controller
{

	private MateFindDAO dao;

	public void setDao(MateFindDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
	
		String memberCode2 = request.getParameter("hidden");	// 친구 멤버코드
		//System.out.println(memberCode2);
		int mateCode = Integer.parseInt(memberCode2);
		//System.out.println(mateCode);
		
		String memberCode = String.valueOf(session.getAttribute("memberCode")); // 내 코드
		int myCode = Integer.parseInt(memberCode); // 내 멤버코드
		//System.out.println(myCode);
		
		
		try {
		
			
			MateStayDTO mate = new MateStayDTO();	
			mate.setMemberCode(myCode);
			mate.setMateMemberCode(mateCode);
			
			int i =dao.materequest(mate);
			System.out.println(i);
			mav.setViewName("WEB-INF/Sources/MateAdd.jsp");
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			mav.setViewName("WEB-INF/Sources/MateError.jsp");
		}
	
		
		return mav;
	}

}
