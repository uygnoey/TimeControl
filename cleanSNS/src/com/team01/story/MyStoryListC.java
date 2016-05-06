package com.team01.story;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team01.dao.IHateDAO;
import com.team01.dao.ILikeDAO;
import com.team01.dao.IPostDAO;
import com.team01.dao.IReplyDAO;
import com.team01.dto.HateDTO;
import com.team01.dto.LikeDTO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.ReplyDTO;

@Controller
public class MyStoryListC {
		
	private IPostDAO dao;
	
	private IReplyDAO rDao;

	public void setrDao(IReplyDAO rDao) {
		this.rDao = rDao;
	}

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	// 명진 추가
	private ILikeDAO likeDAO;

	public void setLikeDAO(ILikeDAO likeDAO)
	{
		this.likeDAO = likeDAO;
	}
	
	private IHateDAO hateDAO;
	
	public void setHateDAO(IHateDAO hateDAO)
	{
		this.hateDAO = hateDAO;
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
		int count=dao.myListCount(memberCode);
		
		if(count==start)
			end=start;
		
		List<PostDTO> list=new ArrayList<PostDTO>();
		ArrayList<ReplyDTO> replyList =new ArrayList<ReplyDTO>();
		ArrayList<MateTagDTO> mateList=new ArrayList<MateTagDTO>();
		ArrayList<LikeDTO> likeList = new ArrayList<LikeDTO>();	// 명진 추가
		ArrayList<HateDTO> hateList = new ArrayList<HateDTO>();	// 명진 추가
		
		int size = 0;	// 명진 추가
		
		if(start<=count)		
		{		
			list = dao.getmyStory(memberCode,start,end);
			mateList=dao.mateTagList();//친구 태그 불러오기	
			replyList = rDao.replyList();
			likeList = likeDAO.likeSearch(memberCode);	// 명진 추가
			hateList = hateDAO.hateSearch(memberCode);	// 명진 추가
			size = likeList.size();	// 명진 추가

		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("replyList", replyList);
		mav.addObject("mateList", mateList);
		mav.addObject("check", "mystory");
		mav.addObject("likeList", likeList);	// 명진 추가
		mav.addObject("hateList", hateList);	// 명진 추가
		mav.addObject("size", size);	// 명진 추가
		mav.addObject("check", "mystory");

		mav.setViewName("WEB-INF/Sources/PostCard.jsp");
		System.out.println("test");
		return mav;
		
		
	}
}
