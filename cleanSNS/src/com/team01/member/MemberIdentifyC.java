package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberStayDAO;

public class MemberIdentifyC implements Controller {

	private IMemberStayDAO dao;

	public void setDao(IMemberStayDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		// 세션
		HttpSession session = req.getSession();

		// 이메일 및 코드 받기
		String email = req.getParameter("email");
		String identify = req.getParameter("identify");

		// 이메일에 대한 세션 코드 받기
		String sessionCode = (String) session.getAttribute(email);

		// 테스트
		System.out.println("test");
		System.out.println("Receive email : " + email);
		System.out.println("Receive sessionCode  : " + sessionCode);
		System.out.println("Receive identifyCode : " + identify);

		ModelAndView mav = new ModelAndView();

		try {
			if (identify == null || sessionCode == null) {

				mav.setViewName("redirect:registerFailure.htm");

			}
			// 인증 성공
			else if (identify.equals(sessionCode)) {

				// 세션 삭제
				session.removeAttribute(email);

				// DB 멤버 대기에서 멤버로 이동
				dao.registerSuccess(email);

				// 페이지 리다이렉트
				mav.setViewName("redirect:registersuccess.htm");

				// 인증 실패
			} else {

				// 페이지 리다이렉트
				mav.setViewName("redirect:registerFailure.htm");

			}
		} catch (Exception e) {

			mav.setViewName("redirect:underconstruction,htm");
			e.printStackTrace();

		}

		return mav;

	}

}
