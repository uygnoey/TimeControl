package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostDTO;

@Controller
public class StoryTimePungModifyResultC {

	private IPostDAO dao;	
	
	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView storyTimePungModifyResult(HttpServletRequest request)
	{
		PostDTO dto=new PostDTO();
		dto.setPostCode(Integer.parseInt(request.getParameter("postCode")));
		dto.setPostContent(request.getParameter("postContent"));
		String mateCode = null;  //수정 요망
		dto.setMateCode(mateCode);//받는 사람 코드 처리필요
		
		try {
			dao.pungPostModify(dto);
			String[] taglist=null;
			if(request.getParameter("taglistcode")!=null)				
			taglist=request.getParameter("taglistcode").split(",");
			
			
			if(taglist.length>0 && request.getParameter("taglistcode")!="")
			{
				
					dao.pungTagModifyBefore(Integer.parseInt(request.getParameter("postCode")));
					for (int i = 0; i <taglist.length ; i++) {		
						MateTagDTO mateDto=new MateTagDTO();
						mateDto.setPostCode(Integer.parseInt(request.getParameter("postCode")));
						mateDto.setMemberCode(Integer.parseInt(taglist[i]));
					
						dao.pungTagModify(mateDto);
					}			
			}
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/StoryTimePungModifyResult.jsp");
		mav.addObject("photoURL", request.getParameter("photoURL"));
		mav.addObject("postCode",request.getParameter("postCode") );
		mav.addObject("name",request.getParameter("name") );
		mav.addObject("mateName", request.getParameter("mateName"));
		mav.addObject("pungCode",request.getParameter("pungCode") );
		mav.addObject("postDate",request.getParameter("postDate") );
		mav.addObject("postContent",request.getParameter("postContent") );
		mav.addObject("taglist",request.getParameter("taglist") );
		mav.addObject("taglistcode",request.getParameter("taglistcode") );	
		mav.addObject("postPhotoURL", request.getParameter("postPhotoURL"));
		
		return mav;
		
	}
}
