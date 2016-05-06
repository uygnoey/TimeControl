package com.team01.mate;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMateFindDAO;
import com.team01.dao.MateFindDAO;
import com.team01.dto.MateDTO;
import com.team01.dto.MemberDTO;

public class MateFindC implements Controller
{
	private IMateFindDAO dao;
	
	public void setDao(MateFindDAO dao) {
		this.dao = dao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		String search = request.getParameter("search");
	
		HttpSession session = request.getSession();
		ArrayList<MemberDTO> member = new ArrayList<MemberDTO>();
		ArrayList<MateDTO> result = new ArrayList<MateDTO>();
		
		
		try {
				session.setAttribute("search", search);				
			
				// 검색 결과 리스트
				member = dao.mateFind(search);
				mav.addObject("list1", member);
				
				//System.out.println(session.getAttribute("memberCode"));
			
				String memberCode1 = String.valueOf(session.getAttribute("memberCode"));
				int memberCode2 = Integer.parseInt(memberCode1);
				//System.out.println(memberCode2);
				result = dao.matelist(memberCode2);
				int size = result.size();
				mav.addObject("list2", result);	// 로그인한 사람의 친구 목록 리스트
				mav.addObject("size", size);
				mav.addObject("mcode", memberCode2);
				
				
				mav.setViewName("WEB-INF/Sources/Matefind.jsp");
			
				
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return mav;
	}

}

				