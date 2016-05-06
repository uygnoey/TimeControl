package com.team01.alert;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IAlertDAO;

public class AlertMateConfirmC implements Controller {

	private IAlertDAO dao;

	public void setDao(IAlertDAO dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		int memberCode = Integer.parseInt(req.getParameter("memberCode"));
		int tableNameCode = Integer.parseInt(req.getParameter("tableNameCode"));
		int codeName = Integer.parseInt(req.getParameter("codeName"));
		String alertDate = req.getParameter("alertDate");
		
		try {
			dao.confirmAlert(memberCode, tableNameCode, codeName, alertDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
