package com.team01.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ICategoryDAO;
import com.team01.dao.IMemberDAO;
import com.team01.dao.MemberDAO;
import com.team01.dto.CategoryDTO;
import com.team01.dto.MemberDTO;

public class MemberUpdateC implements Controller
{
	private ICategoryDAO dao;

	private IMemberDAO dao2;



	// set 구성
	public void setDao(ICategoryDAO dao)
	{
		this.dao = dao;
	}



	public void setDao2(IMemberDAO dao2)
	{
		this.dao2 = dao2;
	}

	// 정보 업데이트 처리----------------------------------------------------------

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		// 이메일 없을 떄 처리. 회원가입 제대로 안됐을 때.
		if(session.getAttribute("email")==null)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login.htm");
			return mav;
		}	
		
		ModelAndView mav = new ModelAndView();
		
		// 이름, 비밀번호 JSP 에서 가져옴. 세션에서 이메일, 멤버코드 가져옴.
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String email = (String)session.getAttribute("email");
		int memberCode = (int)session.getAttribute("memberCode");
		
		try
		{
			MemberDTO dto = new MemberDTO();
			
			// dto에 멤버코드, 이메일, 이름, 비밀번호 넣어줌.
			dto.setMemberCode(memberCode);
			dto.setEmail(email);
			dto.setName(name);
			dto.setPw(pw);

			// 업데이트 메소드 사용
			dao2.memberUpdate(dto);
		
			// 바뀐 이름 세션에 저장
			session.setAttribute("name", name);
	
			mav.addObject("name", name);
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		
		
		/*카테고리 처리------------------------------------------------------------------*/
		//카테고리1사용 확인 체크
		//int memberCode=(int) session.getAttribute("memberCode");
		String ca1=request.getParameter("ca1");
		String ca2=request.getParameter("ca2");
		
		//추가할 멤버코드 받아오기
		ArrayList<Integer> matecode=new ArrayList<Integer>();
		if(request.getParameter("hidden1")!="")
			matecode.add(Integer.parseInt(request.getParameter("hidden1")));
		if(request.getParameter("hidden2")!="")
			matecode.add(Integer.parseInt(request.getParameter("hidden2")));
		if(request.getParameter("hidden3")!="")
			matecode.add(Integer.parseInt(request.getParameter("hidden3")));
		if(request.getParameter("hidden4")!="")
			matecode.add(Integer.parseInt(request.getParameter("hidden4")));
		if(request.getParameter("hidden5")!="")
			matecode.add(Integer.parseInt(request.getParameter("hidden5")));
		
				
		if(ca1!="")
		{//카테고리 사용시 카테고리1,2여부와 상관 없이 1에 대해 진행
			
			if(dao.checkCategory1Use(memberCode)==0)			
				dao.setCategory1Title(memberCode, ca1);
			else
				dao.Category1TitleModify(memberCode, ca1);
						
		}
		if(ca2!=""){
			//카테고리2 사용자
			
			//카테고리2 사용시(기존의 사용여부와 상관 없이 1로 세팅)
			dao.setCategory2Use(memberCode);
			
			if(dao.getCategory2Title(memberCode)==null)
			{	//기존에 카테고리2 미사용자시
				dao.setCategory2Title(memberCode, ca2, matecode);
				
			}
			
			else
				dao.Category2TitleModify(memberCode, ca2, matecode);
			
		}			
		
		/*공개범위 처리-----------------------------------------------------------------*/
		int openLimitedCode=Integer.parseInt(request.getParameter("range"));
		dao2.setopenLimited(memberCode, openLimitedCode);
		
		mav.setViewName("storyview.htm");
		
		return mav;
		
	}
}
