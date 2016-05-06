package com.team01.alert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IAlertDAO;

public class AlertDelCheckC implements Controller{

	private IAlertDAO dao;

	public void setDao(IAlertDAO dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		JSONObject jOb = new JSONObject();

		int memberCode = Integer.parseInt(req.getParameter("memberCode"));
		int tableNameCode = Integer.parseInt(req.getParameter("tableNameCode"));
		int codeName = Integer.parseInt(req.getParameter("codeName"));
		String alertDate = req.getParameter("alertDate");

		boolean check = dao.delCheck(memberCode, tableNameCode, codeName, alertDate);
		
		if (check) 
			dao.confirmAlert(memberCode, tableNameCode, codeName, alertDate);
		
		jOb.put("check", check);

		mav.addObject("json", jOb);
		mav.setViewName("WEB-INF/Sources/AlertDelCheck.jsp");
		
		return mav;
	}

}
