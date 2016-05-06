package com.team01.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.CategoryDAO;
import com.team01.dao.ICategoryDAO;
import com.team01.dao.IMemberDAO;
import com.team01.dto.Category2MateDTO;

public class MemberUpdateFormC implements Controller
{
	CategoryDAO  dao;
	
	public void setDao(CategoryDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		//회원의 카테고리 정보 가져오기
		HttpSession session=request.getSession();
		int memberCode=(int) session.getAttribute("memberCode");
		ArrayList<Category2MateDTO> mateList=new ArrayList<Category2MateDTO>();
		
		ModelAndView mav = new ModelAndView();	
		String ca1=dao.getCategory1Title(memberCode);
		String ca2="";
		String mate="";
		String mateCode="";
		
		if(ca1!=null)
		{//카테고리 사용자일 경우
			mav.addObject("ca1", ca1);
			
			if(dao.getCategory2Title(memberCode)!=null)
			{	//카테고리2 사용자일 경우 타이들과 친구 리스트 받아오기
				ca2=dao.getCategory2Title(memberCode);
				mateList=dao.getMateInfoes(memberCode);
				
				//친구 리스트 텍스트로 변환
				for(Category2MateDTO i:mateList)
				{	
					mate+=i.getMateName()+" ";
					mateCode+=i.getMateSaveCode()+" ";
					System.out.println("mateCode"+mateCode);
				
				}
				mav.addObject("ca2", ca2);
				mav.addObject("mate", mate);
				mav.addObject("mateCode", mateCode);
			}			
		}	
			
		mav.setViewName("WEB-INF/Sources/MemberUpdate.jsp");
		return mav;
	}
}
