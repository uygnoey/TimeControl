package com.team01.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IHateDAO;
import com.team01.dao.IPostDAO;
import com.team01.dto.HateDTO;

public class HateC implements Controller
{
	private IHateDAO dao;

	public void setDao(IHateDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		ModelAndView mav = new ModelAndView();
		
		// 게시글코드, 멤버코드 받아옴.
		int memberCode = (int) session.getAttribute("memberCode");
		int postCode = Integer.parseInt(request.getParameter("postCode"));
		String check = request.getParameter("check");
		
		try
		{
			HateDTO hateDTO = new HateDTO();
			
			hateDTO.setMemberCode(memberCode);
			hateDTO.setPostCode(postCode);
			
			// 싫어요 추가 메소드
			dao.hate(hateDTO);
			
			// 뉴스피드에서 싫어요 클릭하면 다시 뉴스피드 페이지로, 마이스토리에서 싫어요 클릭하면 다시 마이스토리 페이지로.
			if(check.equals("mystory"))
				mav.setViewName("redirect:storyview.htm");
			else if (check.equals("alert")) { // 연규 추가 ( 알림 부분)
				int tableNameCode = Integer.parseInt(request.getParameter("tableNameCode"));
				int codeName = Integer.parseInt(request.getParameter("codeName"));
				String alertDate = request.getParameter("alertDate");
				
				mav.addObject("tableNameCode", tableNameCode);
				mav.addObject("codeName", codeName);
				mav.addObject("alertDate", alertDate);
				
				mav.setViewName("redirect:alertconfirm.htm");
			} else
				mav.setViewName("redirect:newsfeed.htm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
	
}
