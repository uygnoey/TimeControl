package com.team01.mateStory;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMateDAO;
import com.team01.dao.IPostPhotoDAO;
import com.team01.dto.MateInfoDTO;

public class MateStoryFsC implements Controller{

	private IPostPhotoDAO dao;
	private IMateDAO mateDao;
	
	public void setMateDao(IMateDAO mateDao) {
		this.mateDao = mateDao;
	}

	public void setDao(IPostPhotoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String mateCode = request.getParameter("mateCode");		
		ModelAndView mav = new ModelAndView();
		MateInfoDTO dto = new MateInfoDTO();
		ArrayList<MateInfoDTO> mateList = new ArrayList<MateInfoDTO>();
		int count = 0;
		
		try{
			
			dto = dao.getMateMainPhoto(Integer.parseInt(mateCode));
			mateList = mateDao.mateInfoList(Integer.parseInt(mateCode));
			count = mateDao.listCount(Integer.parseInt(mateCode));
			
		}catch(Exception e){
			System.out.println(e.toString());			
		}
		
		mav.addObject("mateCode", mateCode);
		mav.addObject("dto", dto);
		mav.addObject("mateList", mateList);
		mav.addObject("count", count);
		mav.setViewName("WEB-INF/Sources/MateStoryFriends.jsp");
		
		return mav; 
	}

	
}
