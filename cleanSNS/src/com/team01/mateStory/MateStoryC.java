package com.team01.mateStory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPostPhotoDAO;
import com.team01.dto.MateInfoDTO;

public class MateStoryC implements Controller {

	private IPostPhotoDAO dao;
	
	public void setDao(IPostPhotoDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String mateCode = request.getParameter("mateCode");		
		ModelAndView mav = new ModelAndView();
		MateInfoDTO dto = new MateInfoDTO();
		try{
			
			dto = dao.getMateMainPhoto(Integer.parseInt(mateCode));
			
		}catch(Exception e){
			System.out.println(e.toString());			
		}
		
		mav.addObject("mateCode", mateCode);
		mav.addObject("dto", dto);
		mav.setViewName("WEB-INF/Sources/MateStory.jsp");
		return mav;
	}

	
	
	
}
