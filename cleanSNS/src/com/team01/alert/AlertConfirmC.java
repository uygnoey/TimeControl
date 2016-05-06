package com.team01.alert;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.team01.dao.IAlertDAO;
import com.team01.dao.IHateDAO;
import com.team01.dao.ILikeDAO;
import com.team01.dao.IPostDAO;
import com.team01.dao.IReplyDAO;
import com.team01.dto.HateDTO;
import com.team01.dto.LikeDTO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.ReplyDTO;

public class AlertConfirmC implements Controller {

	private IAlertDAO dao;

	public void setDao(IAlertDAO dao) {
		this.dao = dao;
	}

	private IPostDAO pDao;

	public void setpDao(IPostDAO pDao) {
		this.pDao = pDao;
	}

	private IReplyDAO rDao;

	public void setrDao(IReplyDAO rDao) {
		this.rDao = rDao;
	}

	private ILikeDAO likeDAO;

	public void setLikeDAO(ILikeDAO likeDAO) {
		this.likeDAO = likeDAO;
	}

	private IHateDAO hateDAO;

	public void setHateDAO(IHateDAO hateDAO) {
		this.hateDAO = hateDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) {

		// 멤버 코드 세션처리
		HttpSession session = req.getSession();

		// 비정상 접속시 처리
		if (session.getAttribute("memberCode") == null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login.htm");
			return mav;
		}

		int memberCode = (int) session.getAttribute("memberCode");
		
		//int memberCode = Integer.parseInt(req.getParameter("memberCode"));
		int tableNameCode = Integer.parseInt(req.getParameter("tableNameCode"));
		int codeName = Integer.parseInt(req.getParameter("codeName"));
		String alertDate = req.getParameter("alertDate");

		//System.out.println("loginMemberCode : " + loginMemberCode);
		System.out.println("memberCode : " + memberCode);
		
		PostDTO pDto = null;
		ArrayList<MateTagDTO> mtList = null;
		ArrayList<ReplyDTO> rList = null;
		ArrayList<LikeDTO> likeList = new ArrayList<LikeDTO>();
		ArrayList<HateDTO> hateList = new ArrayList<HateDTO>();

		int size = 0;

		ModelAndView mav = new ModelAndView();
		try {

			if (tableNameCode == 1 || tableNameCode == 2) {

				dao.confirmAlert(memberCode, tableNameCode, codeName, alertDate);

				if (tableNameCode == 1) {
					
					pDto = pDao.alertPost(codeName);
					mtList = pDao.alertMateTag(codeName);
					System.out.println("codeName : " + codeName);
					System.out.println("pDto.getPostCode() : "+pDto.getPostCode());
					rList = rDao.replyList(codeName);
					
				} else if (tableNameCode == 2) {
					
					int postCode = Integer.parseInt(req.getParameter("postCode"));
					
					pDto = pDao.alertPost(postCode);
					mtList = pDao.alertMateTag(postCode);
					rList = rDao.replyList(postCode);
					
				}
				
				likeList = likeDAO.likeSearch(memberCode);
				hateList = hateDAO.hateSearch(memberCode);
				size = likeList.size();

				mav.addObject("post", pDto);
				mav.addObject("mateList", mtList);
				mav.addObject("replyList", rList);
				mav.addObject("likeList", likeList);
				mav.addObject("hateList", hateList);
				mav.addObject("alertDate", alertDate);
				mav.addObject("size", size);
				mav.addObject("check", "alert");
				mav.setViewName("WEB-INF/Sources/AlertPostView.jsp");

			} else if (tableNameCode == 4) {

				dao.confirmAlert(memberCode, tableNameCode, codeName, alertDate);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return mav;

	}

	/*
	 * JSON 시도 public JSONObject handleRequest(HttpServletRequest req,
	 * HttpServletResponse res) {
	 * 
	 * JSONObject jOb = new JSONObject(); JSONArray jArray = new JSONArray();
	 * 
	 * int memberCode = Integer.parseInt(req.getParameter("memberCode")); int
	 * tableNameCode = Integer.parseInt(req.getParameter("tableNameCode")); int
	 * codeName = Integer.parseInt(req.getParameter("codeName")); String
	 * alertDate = req.getParameter("alertDate");
	 * 
	 * PostDTO pDto = null; ArrayList<MateTagDTO> mtList = null;
	 * ArrayList<ReplyDTO> rList = null;
	 * 
	 * try {
	 * 
	 * if (tableNameCode == 1 || tableNameCode == 2) {
	 * 
	 * pDto = pDao.alertPost(codeName); mtList = pDao.alertMateTag(codeName);
	 * 
	 * if (!pDto.getPostContent().equals(" ") &&
	 * !pDto.getPhotoURL().equals("img/pung.png")) {
	 * 
	 * rList = rDao.replyList(codeName);
	 * 
	 * }
	 * 
	 * jOb.put("postCode", pDto.getPostCode()); jOb.put("memberCode",
	 * pDto.getMemberCode()); jOb.put("name", pDto.getName());
	 * jOb.put("postContent", pDto.getPostContent()); jOb.put("postPhotoUrl",
	 * pDto.getPostphotoURL()); jOb.put("postDate", pDto.getPostDate());
	 * jOb.put("pungCode", pDto.getPungCode()); jOb.put("likeCount",
	 * pDto.getLikeCount()); jOb.put("hateCount", pDto.getHateCount());
	 * jOb.put("mateCode", pDto.getMateCode()); jOb.put("mateName",
	 * pDto.getMateName()); jOb.put("delHide", pDto.getDelHide());
	 * 
	 * for (MateTagDTO mateTag : mtList) {
	 * 
	 * JSONObject jObMateTag = new JSONObject();
	 * 
	 * jObMateTag.put("mateTagPostCode", mateTag.getPostCode());
	 * jObMateTag.put("mateTagMemberCode", mateTag.getMemberCode());
	 * jObMateTag.put("mateTagName", mateTag.getName());
	 * 
	 * jArray.add(jObMateTag);
	 * 
	 * }
	 * 
	 * jOb.putAll((Map<String, String>) jArray);
	 * 
	 * } else if (tableNameCode == 3) {
	 * 
	 * }
	 * 
	 * dao.confirmAlert(memberCode, tableNameCode, codeName, alertDate);
	 * 
	 * 
	 * } catch (SQLException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return jOb; }
	 */

}
