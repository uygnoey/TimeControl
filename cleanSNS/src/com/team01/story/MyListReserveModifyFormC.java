package com.team01.story;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IReservePostDAO;
import com.team01.dto.MateDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostDTO;

@Controller
public class MyListReserveModifyFormC {
	
	IReservePostDAO dao;	

	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}

	@RequestMapping
	public ModelAndView myListReserveModifyForm(HttpServletRequest request) throws SQLException
	{//게시글 수정 완료 폼 컨트롤러	
		
		ReservePostDTO dto=new ReservePostDTO();
		dto.setReserveCode(Integer.parseInt(request.getParameter("reserveCode")));
		dto.setPostContent(request.getParameter("postContent"));
		String mateCode = null;  //수정 요망
		dto.setMateCode(mateCode);//받는 사람 코드 처리필요
		
		dao.reservePostModify(dto);
		String[] taglist=null;
		if(request.getParameter("taglistcode")!=null)				
		taglist=request.getParameter("taglistcode").split(",");
		
		
		if(taglist.length>0 && request.getParameter("taglistcode")!="")
		{
			dao.reserveTagModifyBefore(Integer.parseInt(request.getParameter("reserveCode")));
			for (int i = 0; i <taglist.length ; i++) {		
				ReserveMateTagDTO mateDto=new ReserveMateTagDTO();
				mateDto.setReserveCode(Integer.parseInt(request.getParameter("reserveCode")));
				mateDto.setMemberCode(Integer.parseInt(taglist[i]));
			
				dao.reserveTagModify(mateDto);
			}	
		}
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("WEB-INF/Sources/PostCardReserveModifyForm.jsp");		
		mav.addObject("reserveCode",request.getParameter("reserveCode") );
		mav.addObject("name",request.getParameter("name") );
		mav.addObject("mateName", request.getParameter("mateName"));
		mav.addObject("reserveTime",request.getParameter("reserveTime") );
		mav.addObject("reserveDate",request.getParameter("reserveDate") );
		mav.addObject("postContent",request.getParameter("postContent") );
		mav.addObject("taglist",request.getParameter("taglist") );
		mav.addObject("taglistcode",request.getParameter("taglistcode") );	
		mav.addObject("photoURL", request.getParameter("photoURL"));
		mav.addObject("postphotoURL", request.getParameter("postphotoURL"));
	//	System.out.println(request.getParameter("postphotoURL"));
		return mav;
		
	}
}
