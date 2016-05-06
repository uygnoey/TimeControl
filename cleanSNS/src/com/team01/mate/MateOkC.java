package com.team01.mate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMateFindDAO;
import com.team01.dto.MateDTO;

public class MateOkC implements Controller
{
	private IMateFindDAO dao;
	
	
	public void setDao(IMateFindDAO dao) {
		this.dao = dao;
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");	
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		String memberCode = String.valueOf(session.getAttribute("memberCode")); // 내 코드
		int myCode = Integer.parseInt(memberCode); // 내 멤버코드
		System.out.println(myCode);
		
		String mCode  = request.getParameter("mateCode");
		int mateCode = Integer.parseInt(mCode);
		
		String mateName = request.getParameter("mateName");
		String flag = request.getParameter("flag");
		System.out.println(flag);
		
		mav.addObject("mateName", mateName);
		
		int result=0;
		MateDTO mate = new MateDTO();
		mate.setMateMemberCode(mateCode);
		mate.setMemberCode(myCode);

		if(flag.equals("o"))
		{
			System.out.println("수락");
			result = dao.mateOk(mate);
			System.out.println(result);
			mav.setViewName("WEB-INF/Sources/MateAccept.jsp");				
		}
			
		else if(flag.equals("x"))
		{
			System.out.println("거절");
			result = dao.mateNo(mate);
			System.out.println(result);
			mav.setViewName("WEB-INF/Sources/MateReject.jsp");
		}
		
		return mav;
	}

}
