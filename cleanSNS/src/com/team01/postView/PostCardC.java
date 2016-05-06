package com.team01.postView;

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
import com.team01.dao.IReplyDAO;
import com.team01.dao.PostDAO;
import com.team01.dto.HateDTO;
import com.team01.dto.LikeDTO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.ReplyDTO;

@Controller
public class PostCardC   {
	
	private PostDAO dao;

	public void setDao(PostDAO dao) {
		this.dao = dao;
	}
	
	private IReplyDAO rDao;
	
	public void setrDao(IReplyDAO rDao) {
		this.rDao = rDao;
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
	public ModelAndView postList(HttpServletRequest request) throws SQLException
	{
		
		//int memberCode=2; //테스트 멤버
				
		//멤버 코드 세션처리
		HttpSession session=request.getSession();	
		
		//비정상 접속시 처리	
		if(session.getAttribute("memberCode")==null)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login.htm");
			return mav;
		}	

		ModelAndView mav = new ModelAndView();

		
		try {
			// 펑게시글 삭제
			dao.pungPostDelete();

			//System.out.println("dao.pungPostDelete() : " + dao.pungPostDelete());
			
			// 싫어요 숨김 메소드
			hateDAO.hateDelete();

			// 테스트
			System.out.println("dao.pungPostDelete() : " + dao.pungPostDelete());

			
		} catch (Exception e) {
			
		//	System.out.println("dao.pungPostDelete() Not Called ");
			
			mav.setViewName("redirect:underconstruction.htm");
			e.printStackTrace();
			
		}
		
		int memberCode=(int) session.getAttribute("memberCode");
		
		//리스트 불러오기-9개씩
		String page=(String) request.getParameter("start");
		
		int start=Integer.parseInt(page);
		int end=start+9;

		//마지막글인지 확인
		int count=dao.allListCount(memberCode);

		
		if(count==start)
			end=start;
		
		List<PostDTO> list=new ArrayList<PostDTO>();
		ArrayList<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
		ArrayList<MateTagDTO> mateList=new ArrayList<MateTagDTO>();
		ArrayList<LikeDTO> likeList = new ArrayList<LikeDTO>();	// 명진 추가
		ArrayList<HateDTO> hateList = new ArrayList<HateDTO>();	// 명진 추가

		int size = 0;	// 명진 추가

		if(start<=count)		
		{			
			list=dao.allList(memberCode,start,end);
			mateList=dao.mateTagList();//친구 태그 불러오기	
			replyList = rDao.replyList();
			likeList = likeDAO.likeSearch(memberCode);	// 명진 추가
			//hateList = hateDAO.hateSearch(memberCode);	// 명진 추가
			size = likeList.size();	// 명진 추가

		}
		mav.addObject("list", list);
		mav.addObject("replyList", replyList);
		mav.addObject("mateList", mateList);
		mav.addObject("memberCode", memberCode); // 주희 추가
		mav.addObject("likeList", likeList);	// 명진 추가
		mav.addObject("hateList", hateList);	// 명진 추가
		mav.addObject("size", size);	// 명진 추가
		mav.addObject("check", "newsfeed");
		
		mav.setViewName("WEB-INF/Sources/PostCard.jsp");
		
		return mav;		
	}
}
