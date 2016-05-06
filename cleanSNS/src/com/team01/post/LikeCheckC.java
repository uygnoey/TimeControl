package com.team01.post;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ILikeDAO;
import com.team01.dao.LikeDAO;
import com.team01.dto.LikeDTO;

public class LikeCheckC implements Controller
{
	private ILikeDAO dao;

	public void setDao(LikeDAO dao)
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
		
		ArrayList<LikeDTO> postCodeArrayList = new ArrayList<LikeDTO>();
		
		int memberCode = (int)session.getAttribute("memberCode");
		//int postCode = (int)session.getAttribute("postCode");
		
		try
		{
			// 좋아요 검색한 결과를 list 형태로 받아서 저장.
			postCodeArrayList = dao.likeSearch(memberCode);
			
			mav.addObject("postCodeArrayList", postCodeArrayList);
			
			//mav.setViewName("WEB-INF/Sources/LikeAjax.jsp");
			mav.setViewName("WEB-INF/Sources/PostCard.jsp");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
		
	}
	
	
	
}
