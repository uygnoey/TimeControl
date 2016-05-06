package com.team01.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ILikeDAO;
import com.team01.dto.LikeDTO;


// 좋아요 취소 처리.
public class LikeCancelC implements Controller
{
	private ILikeDAO dao;

	public void setDao(ILikeDAO dao)
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
		
		// 게시글코드랑 멤버코드 받아옴.
		int memberCode = (int)session.getAttribute("memberCode");
		int postCode = Integer.parseInt(request.getParameter("postCode"));
		String check = request.getParameter("check");
		
		try
		{
			LikeDTO likeDTO = new LikeDTO();
			
			likeDTO.setMemberCode(memberCode);
			likeDTO.setPostCode(postCode);
			
			
			/*// 좋아요 카운트가 0보다 작을 때에는 좋아요 취소가 되지 않게.
			ArrayList<PostDTO> list = new ArrayList<PostDTO>();
			list = dao.likeCount();
			for (int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getLikeCount() >= 0)
					dao.likeCancel(postCode, memberCode);
			}*/
			
			dao.likeCancel(postCode, memberCode);
			
			
			// 뉴스피드에서 좋아요취소 클릭하면 다시 뉴스피드 페이지로, 마이스토리에서 좋아요취소 클릭하면 다시 마이스토리 페이지로.
			if(check.equals("mystory"))
				mav.setViewName("redirect:storyview.htm");
			else if (check.equals("alert")) { // 연규 추가 ( 알림 부분)
				int tableNameCode = Integer.parseInt(request.getParameter("tableNameCode"));
				int codeName = Integer.parseInt(request.getParameter("codeName"));
				String alertDate = request.getParameter("alertDate");
				
				mav.addObject("tableNameCode", tableNameCode);
				mav.addObject("codeName", codeName);
				mav.addObject("alertDate", alertDate);
				
				mav.setViewName("redirect:alertconfirm.htm");
			} else
				mav.setViewName("redirect:newsfeed.htm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
	
}
