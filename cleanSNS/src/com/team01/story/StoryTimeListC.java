package com.team01.story;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;
import com.team01.dao.IPostPhotoDAO;
import com.team01.dao.IReservePostDAO;

@Controller
public class StoryTimeListC {

	private IReservePostDAO rdao;	
	private IPostDAO pdao;
	
	public void setRdao(IReservePostDAO rdao) {
		this.rdao = rdao;
	}

	public void setPdao(IPostDAO pdao) {
		this.pdao = pdao;
	}

	@RequestMapping
	public ModelAndView storyTimeList(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int memberCode=(int)session.getAttribute("memberCode");
		
		int rcount=0;
		int pcount=0;
		
		try {
			rcount=rdao.getReservePostCount(memberCode);
			pcount=pdao.getPungListCount(memberCode);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("rcount", rcount);
		mav.addObject("pcount", pcount);
		
		mav.setViewName("WEB-INF/Sources/StoryTime.jsp");
		return mav;
	}
}
