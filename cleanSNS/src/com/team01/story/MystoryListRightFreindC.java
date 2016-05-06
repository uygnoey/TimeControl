package com.team01.story;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.ICategoryDAO;
import com.team01.dao.IMateDAO;
import com.team01.dto.Category2MateDTO;
import com.team01.dto.MateInfoDTO;

@Controller
public class MystoryListRightFreindC {

	private IMateDAO mateDAO;
	
	public void setMateDAO(IMateDAO mateDAO) {
		this.mateDAO = mateDAO;
	}
	
	@RequestMapping
	public ModelAndView mystoryListRightFreind(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		int memberCode = (int)session.getAttribute("memberCode");
		
		
		ArrayList<MateInfoDTO> mateList = new ArrayList<MateInfoDTO>();
	
		int check=0;
		int count=0;
		
		//친구 리스트 불러오기
		try{
			
			count=mateDAO.listCount(memberCode);
			
			if(count>0)
			{
				if(count>8)
					count=8;//최대 카운트 세팅
				mateList = mateDAO.infoList(memberCode);
				check=1;
			}
		}catch(Exception e){
			
			System.out.println(e.toString());			
		}
		
			ModelAndView mav = new ModelAndView();
			mav.addObject("mateList", mateList);
			mav.addObject("check", check);
			mav.addObject("count", count);
			mav.setViewName("WEB-INF/Sources/StoryMyListFriends.jsp");
			
			return mav;	
		
	}
}
 