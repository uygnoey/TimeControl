package com.team01.story;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ICategoryDAO;
import com.team01.dao.IMateDAO;
import com.team01.dto.Category2MateDTO;
import com.team01.dto.MateInfoDTO;

public class StoryMateListFormC implements Controller{

	private IMateDAO mateDAO;
	private ICategoryDAO cDAO;
	
	public void setMateDAO(IMateDAO mateDAO) {
		this.mateDAO = mateDAO;
	}
	public void setcDAO(ICategoryDAO cDAO) {
		this.cDAO = cDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		int memberCode = (int)session.getAttribute("memberCode");
		
		// 로그인 세션 확인
		if(session.getAttribute("name")==null){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}
		
		ModelAndView mav = new ModelAndView();
		ArrayList<MateInfoDTO> mateList = new ArrayList<MateInfoDTO>();
		ArrayList<Category2MateDTO> caList = new ArrayList<Category2MateDTO>();
		int count=0;
		
		//친구 리스트 불러오기
		try{
			mateList = mateDAO.infoList(memberCode);
			count = mateDAO.listCount(memberCode);				
			caList = cDAO.getMateInfoes(memberCode);	// 카테고리 2에 있는 친구들			

		}catch(Exception e){
			
			System.out.println(e.toString());
			
		}
		
		
		mav.addObject("mateList", mateList);
		mav.addObject("count", count);
		mav.addObject("caList", caList);
		mav.setViewName("WEB-INF/Sources/StoryMateList.jsp");
		return mav;
	}
	
}
