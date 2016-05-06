package com.team01.post;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPostDAO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;

public class PostUpdateFormC implements Controller{

	private IPostDAO dao;
	
	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String postCode = request.getParameter("postCode");
		PostDTO postDto = dao.postGet(Integer.parseInt(postCode));
		ArrayList<MateTagDTO> tagDtoList = dao.postTagGet(Integer.parseInt(postCode));

		mav.addObject("postDto", postDto);
		mav.addObject("tagDtoList", tagDtoList);
		mav.setViewName("WEB-INF/Sources/UpdateForm.jsp");
		
		return mav;
	}
	
	
}
