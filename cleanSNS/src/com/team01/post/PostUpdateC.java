package com.team01.post;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IPostDAO;
import com.team01.dto.MateTagDTO;

public class PostUpdateC implements Controller{

	private IPostDAO dao;

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		String postContent = request.getParameter("area");
		String postCode = request.getParameter("postCode");
		
		ArrayList<MateTagDTO> dtoList = new ArrayList<MateTagDTO>();		
		
		String code1 = request.getParameter("code1");
		String code2 = request.getParameter("code2");
		String code3 = request.getParameter("code3");
		String code4 = request.getParameter("code4");
		String code5 = request.getParameter("code5");
		
		String[] array = {code1, code2, code3, code4, code5}; 
			
		
		try{
			
			dao.postUpdate(Integer.parseInt(postCode), postContent);
			dtoList = dao.postTagGet(Integer.parseInt(postCode));
			
			// 기존 친구 태그 삭제
			for(MateTagDTO dto : dtoList)				
				dao.delMateTag(Integer.parseInt(postCode), dto.getMemberCode());
			
			// 새로운 친구 태그 추가
			for(String s : array){
				if(s.equals(""))
					break;
				dao.tagUpdate(Integer.parseInt(postCode), s);
			}
			
			/*for(String s : array){				
				if(s.equals(""))
					break;
				int i=0;
				for(MateTagDTO dto : dtoList){
					
					if(dto.getMemberCode() != Integer.parseInt(s)){
						i++;
						if(i==dtoList.size())
							
					}
						
					
				}
			}*/
			
			
		}catch(Exception e){
			
			System.out.println(e.toString());
		}
		
		mav.setViewName("redirect:newsfeed.htm");
		
		return mav;
	}
	
	
}
