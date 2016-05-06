package com.team01.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ILikeDAO;
import com.team01.dao.IPostDAO;
import com.team01.dao.LikeDAO;
import com.team01.dto.LikeDTO;

// 좋아요 Insert, 좋아요 Count 처리.
public class LikeC implements Controller
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
		//request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		ModelAndView mav = new ModelAndView();
	
		
		// 게시글코드랑 멤버코드 받아옴.
		int memberCode = (int)session.getAttribute("memberCode");
		String postCode = request.getParameter("postCode");
		

	//	System.out.println("좋아요"+postCode);

		String check=request.getParameter("check");
		
		//System.out.println("check"+check);
		try
		{
			LikeDTO likedto = new LikeDTO();

			likedto.setMemberCode(memberCode);
			likedto.setPostCode(Integer.parseInt(postCode));
			
			//System.out.println(likedto.getPostCode());

			
			dao.like(likedto);


			// 뉴스피드에서 좋아요 클릭하면 다시 뉴스피드 페이지로, 마이스토리에서 좋아요 클릭하면 다시 마이스토리 페이지로.
			if(check.equals("mystory"))
			{	
				System.out.println(request.getParameter("check"));
				mav.setViewName("redirect:storyview.htm");

			} else if (check.equals("alert")) { // 연규 추가 ( 알림 부분)
				int tableNameCode = Integer.parseInt(request.getParameter("tableNameCode"));
				int codeName = Integer.parseInt(request.getParameter("codeName"));
				String alertDate = request.getParameter("alertDate");
				
				mav.addObject("tableNameCode", tableNameCode);
				mav.addObject("codeName", codeName);
				mav.addObject("alertDate", alertDate);
				
				mav.setViewName("redirect:alertconfirm.htm");
			}
			else

			mav.setViewName("redirect:newsfeed.htm");


		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return mav;
	
	}
	
	
}
