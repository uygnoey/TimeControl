package com.team01.alert;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IAlertDAO;
import com.team01.dto.AlertDTO;

public class AlertC implements Controller {

	private IAlertDAO dao;

	public void setDao(IAlertDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		ArrayList<AlertDTO> alertMatetList = null;
		ArrayList<AlertDTO> alertPostList = null;
		ArrayList<AlertDTO> alertReplyList = null;

		ModelAndView mav = new ModelAndView();

		int memberCode = 0;

		try {

			req.setCharacterEncoding("utf-8");

			// 로그인 세션 처리
			HttpSession session = req.getSession();
			if (session.getAttribute("name") == null) {
				mav.setViewName("redirect:loginform.htm");
				return mav;
			}

			memberCode = (int) session.getAttribute("memberCode");

			alertMatetList = dao.mateAlertSelect(memberCode);
			alertPostList = dao.postAlertSelect(memberCode);
			alertReplyList = dao.replyAlertSelect(memberCode);

			for (AlertDTO alert_ : alertMatetList) {
				if (memberCode == alert_.getMemberCode())
					System.out.println(alert_.getMateName());
			}

			for (AlertDTO alDto : alertPostList) {
				System.out.println(alDto.getPostCode());
			}

			dao.autoDelAlert();
			
			mav.addObject("memberCode", memberCode);
			mav.addObject("alertPostList", alertPostList);
			mav.addObject("alertMateList", alertMatetList);
			mav.addObject("alertReplyList", alertReplyList);

			mav.setViewName("WEB-INF/Sources/Alert.jsp");

		} catch (Exception e) {

			mav.setViewName("redirect:underconstruction.htm");
			e.printStackTrace();
			System.out.println(e.toString());

		}

		return mav;

	}

}
