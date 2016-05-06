package com.team01.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.CategoryDAO;


public class ContentC implements Controller{

	private CategoryDAO dao;

	public void setDao(CategoryDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse res) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		//int memberCode=2;
		//세션에서 멤버코드 얻어오기
		HttpSession session=request.getSession();
		int memberCode=(int) session.getAttribute("memberCode");		
		
		String ca1Title=dao.getCategory1Title(memberCode);
		String ca2Title=null;		
		
		if(ca1Title!=null)			
		{	mav.addObject("ca1Title", ca1Title);
			session.setAttribute("ca1Title", ca1Title);
			session.setAttribute("ca2Use", "N");
		
			if(dao.getCategory2Title(memberCode)!=null)
			{
				ca2Title=dao.getCategory2Title(memberCode);
				mav.addObject("ca2Use", "Y");
				mav.addObject("ca2Title", ca2Title);
				session.setAttribute("ca2Title", ca2Title);
				session.setAttribute("ca2Use", "Y");
			}
		}		
				
		mav.setViewName("WEB-INF/Sources/content.jsp");		
		
		return mav;
	}
	
}
