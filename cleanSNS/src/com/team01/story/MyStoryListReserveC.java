package com.team01.story;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IPostDAO;
import com.team01.dao.IReplyDAO;
import com.team01.dao.IReservePostDAO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.ReplyDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostDTO;

@Controller
public class MyStoryListReserveC {

	private IReservePostDAO dao;
	
	private IReplyDAO rDao;

	public void setrDao(IReplyDAO rDao) {
		this.rDao = rDao;
	}

	public void setDao(IReservePostDAO dao) {
		this.dao = dao;
	}
	@RequestMapping
	public ModelAndView myStoryList(HttpServletRequest request) throws SQLException
	{
		HttpSession session=request.getSession();
		int memberCode=(int)session.getAttribute("memberCode");
		
		//비정상 접속시 처리	
		if(session.getAttribute("memberCode")==null)
		{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("login.htm");
				return mav;
		}	
						
	
				
		//리스트 불러오기		
		String page=(String) request.getParameter("start");
				
		int start=Integer.parseInt(page);
		int end=start+9;
		
		//총 카운트 불러오기
		//마지막글인지 확인
		int count=dao.getReservePostCount(memberCode);
		
		if(count==start)
			end=start;
		
		List<ReservePostDTO> list=new ArrayList<ReservePostDTO>();
		ArrayList<ReplyDTO> replyList =new ArrayList<ReplyDTO>();
		ArrayList<ReserveMateTagDTO> mateList=new ArrayList<ReserveMateTagDTO>();
		
		if(start<=count)		
		{		
			list = dao.getReservePostList(memberCode,start,end);
			mateList=dao.reserveMateTagList();//친구 태그 불러오기	
			replyList = rDao.replyList();
		}
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("mateList", mateList);
		mav.addObject("replyList", replyList);	
		mav.addObject("count",count);
		
		
		mav.setViewName("WEB-INF/Sources/PostCardReserve.jsp");

		return mav;
		
		
	}
	
}
