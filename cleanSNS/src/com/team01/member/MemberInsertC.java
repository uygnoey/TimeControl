package com.team01.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IMemberStayDAO;
import com.team01.dto.MemberStayDTO;
import com.team01.mail.MailIdentify;
import com.team01.mail.MailTransterObject;

public class MemberInsertC implements Controller {
	private IMemberStayDAO dao;

	public void setDao(IMemberStayDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		ModelAndView mav = new ModelAndView();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");

		try {

			MemberStayDTO member = new MemberStayDTO();

			member.setName(name);
			member.setEmail(email);
			member.setPw(pw);

			dao.insert(member);

			/* 인증 메일 발송 시작 */
			// 인증 코드 생성
			MailIdentify mi = new MailIdentify();
			String identify = mi.MailIdentify(name);

			// 인증코드 발송
			MailTransterObject mto = new MailTransterObject();
			mto.sendIdentify(email, name, identify);

			// 인증코드 세션 등록
			session.setAttribute(email, identify);
			session.setMaxInactiveInterval(24 * 60 * 60);
			// 테스트
			System.out.println("Send email : " + email);
			System.out.println("Send sessionCode  : "
					+ session.getAttribute(email));
			/* 인증 메일 발송 끝 */

			mav.setViewName("redirect:memberinsertform.htm");

		} catch (Exception e) {

			mav.setViewName("redirect:underconstruction.htm");

			System.out.println(e.toString());
			e.printStackTrace();

		}

		return mav;
	}
}