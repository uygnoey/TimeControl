package com.team01.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IHateDAO;
import com.team01.dto.HateDTO;

// 싫어요 취소 처리.
public class HateCancelC implements Controller
{
	private IHateDAO dao;

	public void setDao(IHateDAO dao)
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
		
		// 멤버코드랑 게시글코드 받아옴.
		int memberCode = (int) session.getAttribute("memberCode");
		int postCode = Integer.parseInt(request.getParameter("postCode"));
		String check = request.getParameter("check");
		try
		{
			HateDTO hateDTO = new HateDTO();
			
			hateDTO.setMemberCode(memberCode);
			hateDTO.setPostCode(postCode);
			
			// 싫어요 취소 메소드
			dao.hateCancel(postCode, memberCode);
			
			
			// 뉴스피드에서 싫어요 클릭하면 다시 뉴스피드 페이지로, 마이스토리에서 싫어요 클릭하면 다시 마이스토리 페이지로.
			if(check.equals("mystory"))
				mav.setViewName("redirect:storyview.htm");
			else if (check.equals("alert")){ // 연규 추가 ( 알림 부분)
				int tableNameCode = Integer.parseInt(request.getParameter("tableNameCode"));
				int codeName = Integer.parseInt(request.getParameter("codeName"));
				String alertDate = request.getParameter("alertDate");
				
				mav.addObject("tableNameCode", tableNameCode);
				mav.addObject("codeName", codeName);
				mav.addObject("alertDate", alertDate);
				
				mav.setViewName("redirect:alertconfirm.htm");
			} else
				mav.setViewName("redirect:newsfeed.htm");
			
			// redirect는 다시 자기페이지로 전환될 때 전에 수행했던 기능들을 차단하고 페이지전환이 된다.
			// redirect 없이 자기페이지를 불러오면 전에 수행했던 기능들을 다시 수행하면서 페이지 전환이 된다. (새로고침이 이와 같은 경우.)
			// 좋아요/싫어요의 경우 처음에 redirect 없이 자기페이지를 불러오도록 코딩을 했을 때에는 좋아요를 클릭했을 떄 새로고침이 되면서
			// 좋아요 테이블에 여러건의 같은 데이터가 삽입이 되었다.
			// redirect를 사용하고 난 후에는 좋아요를 클릭하더라도 전에 수행했던 기능들을 차단하고 페이지 전환이 되기 때문에
			// 페이지 전환시 수행된 좋아요 한번의 기능 즉, 한 건의 정보만 좋아요 테이블에 삽입된다.
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
	
	
}
