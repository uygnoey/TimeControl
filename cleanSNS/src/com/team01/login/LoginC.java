package com.team01.login;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.ILoginDAO;
import com.team01.dao.IMemberDAO;
import com.team01.dto.MemberDTO;

public class LoginC implements Controller
{
	private ILoginDAO dao;
	private IMemberDAO mdao;

	public void setDao(ILoginDAO dao) {
		this.dao = dao;
	}
	
	public void setMdao(IMemberDAO mdao) {
		this.mdao = mdao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		ModelAndView mav = new ModelAndView();
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("password");
		System.out.println(password);
		String time = request.getParameter("timeId");
		//34번째 줄 로 인해 접속 불가 임시 주석 처리_혜미
		//int time = Integer.parseInt(stime);
		//System.out.println(time);
			
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		try
		{			
			result = dao.login(email, password);
			//System.out.println(result.get(0).getName());
			//System.out.println(result.size());
			
			if(result.size()!=0)
			{
			
				HttpSession session = request.getSession();
				session.setAttribute("name", result.get(0).getName());
				session.setAttribute("memberCode", result.get(0).getMemberCode());
				session.setAttribute("email",email);
				
				session.setAttribute("time",time);
				

				
				System.out.println(result.get(0).getMemberCode());
				String myPhoto=mdao.getMyPhoto(result.get(0).getMemberCode());
				session.setAttribute("myPhoto", myPhoto);
			
				   if(session.getAttribute("time").equals("1")) // 없음(default값)
					{ 
					    //System.out.println("test");
					    session.setMaxInactiveInterval(60*60*24); // 24시간
					 	//System.out.println(session.getMaxInactiveInterval());
			
					} 

				   
				   if(session.getAttribute("time").equals("2")) // 15분
					{ 
					  
					    session.setMaxInactiveInterval(60*15);
					//    System.out.println(session.getMaxInactiveInterval());
						   
					} 

				   if(session.getAttribute("time").equals("3")) // 30분
					{ 
					  
					    session.setMaxInactiveInterval(60*30);
					 //   System.out.println(session.getMaxInactiveInterval());
						   
					} 

				   if(session.getAttribute("time").equals("4")) // 1시간
					{ 
					  
					    session.setMaxInactiveInterval(60*60);
					 //   System.out.println(session.getMaxInactiveInterval());
						   
					} 
				   
				   if(session.getAttribute("time").equals("5")) // 2시간
					{ 
					  
					    session.setMaxInactiveInterval(60*60*2);
					//    System.out.println(session.getMaxInactiveInterval());
						   
					} 
	
			
				//페이징 처리를 위한 값 추가
				session.setAttribute("start", "1");
				mav.setViewName("redirect:newsfeed.htm");
			
			}
			
			else
			{
			/*	mav.setViewName("redirect:loginerror.htm");*/
				mav.setViewName("redirect:loginform.htm");
				
			}
		}catch(Exception e)
		{
			System.out.println(e.toString());
			mav.setViewName("redirect:loginform.htm");
			
		//	mav.setViewName("redirect:loginerror.htm");
			
		}
		
	
		return mav;
	}
	
	
	
}
