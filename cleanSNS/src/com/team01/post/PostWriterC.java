package com.team01.post;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team01.dao.IPostDAO;
import com.team01.dao.IReservePostDAO;
import com.team01.dao.PostDAO;
import com.team01.dto.MateTagDTO;
import com.team01.dto.PostDTO;
import com.team01.dto.PostPhotoDTO;
import com.team01.dto.ReserveMateTagDTO;
import com.team01.dto.ReservePostPhotoDTO;

public class PostWriterC implements Controller{

	private IPostDAO dao;
	private IReservePostDAO rdao;

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}	

	public void setRdao(IReservePostDAO rdao) {
		this.rdao = rdao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		// 로그인 세션 처리
		HttpSession session = request.getSession(); 
		if(session.getAttribute("name")==null){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:loginform.htm");
			return mav;
		}
		
		
		
		MultipartRequest multi = null;
		int sizeLimit = 10*1024*1024; // 10메가
		//서버 컴퓨터 주소로 변경 필요
		String savePath ="D:\\자바교육\\프로젝트\\파이널\\1st\\cleanSNS\\WebContent\\img";
			
		try{
			
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());			
					
		}catch(Exception e){
					
			e.printStackTrace();
		}
		
		Enumeration files=multi.getFileNames();
		String file=(String) files.nextElement();
		String filename=multi.getFilesystemName(file);
		
		System.out.println("filename"+filename);
		int memberCode = (int)session.getAttribute("memberCode");
		ModelAndView mav = new ModelAndView();
		
		
		// 게시내용 받아오기
		String postContent = multi.getParameter("area");

		int pungHour = 0;
		int pungMinute = 0;
		if (!multi.getParameter("pungHour").isEmpty())
			pungHour = Integer.parseInt(multi.getParameter("pungHour")); // 펑코드 시간 받아오기
		if (!multi.getParameter("pungMinute").isEmpty())
			pungMinute = Integer.parseInt(multi.getParameter("pungMinute")); // 펑코드 분 받아오기

		// 테스트
		/*
		 * System.out.println("pungHour : " + pungHour);
		 * System.out.println("pungMinute : " + pungMinute);
		 * System.out.println("pungCode1 : " + (pungHour * 60 + pungMinute));
		 */
		
		int pungCode = (pungHour * 60) + pungMinute;  // 시간 * 60 + 분(분으로 된 값을 넣기)
		// 펑코드값 받아오기 끝
		
		
		String mateCode = null;  //수정 요망
				
		// 친구태그 받아오기

		String code1 = multi.getParameter("code1");
		String code2 = multi.getParameter("code2");
		String code3 = multi.getParameter("code3");
		String code4 = multi.getParameter("code4");
		String code5 = multi.getParameter("code5");
		int postCode=0;
		

		PostDTO dto = new PostDTO();
		dto.setMemberCode(memberCode);
		dto.setPostContent(postContent);
		dto.setPungCode(pungCode);
		dto.setMateCode(mateCode);	// 받는사람 코드가 있는경우 따로 작성요망(수정해야함)						
		
		// 친구태그 테이블에 레코드 삽입 메소드 불러오기
		//String[] array = {code1, code2, code3, code4, code5}; 
			
		try{
			//일반 게시 일 경우 처리
			if(multi.getParameter("hiddenReserv").equals("default"))
			{				
						
				// 게시물 테이블에 레코드 삽입 메소드 불러오기
				postCode = dao.postAdd(dto);
				//System.out.println(postCode);
				
				if(filename!=null)
				{
					//사진 첨부 시작
					PostPhotoDTO pdto=new PostPhotoDTO();
					pdto.setPostCode(postCode);
					pdto.setPhotoURL("img\\"+filename);
					
					dao.photoAdd(pdto);
				}
				
				
				//친구 태그 처리		
				
				// 친구태그 테이블에 레코드 삽입 메소드 불러오기
				
				if(!code1.equals("default"))
				{	
					String[] array = {code1, code2, code3, code4, code5}; 				
					ArrayList<MateTagDTO> list = new ArrayList<MateTagDTO>();
				
				
					for(String s : array){
							//System.out.println(s);
						if(s.equals(" "))//태그되는 친구 코드 있는 값만 dto생성 해서 arraylist에 넣어준다
							break;
						MateTagDTO tagDto = new MateTagDTO();
							
						tagDto.setPostCode(postCode);
						tagDto.setMemberCode(Integer.parseInt(s));				
											
						list.add(tagDto);
						
					}
					
					int tagInsert= dao.tagAdd(list);	
				}//친구 태그 끝
				
				
				
			}//일반 게시물 삽입 끝
			
			//예약 게시글 처리			
			else if(!multi.getParameter("hiddenReserv").equals("default") )	
			{		
				int reserve=Integer.parseInt(multi.getParameter("hiddenReserv")); //예약 시간
				int reservMonth=Integer.parseInt(multi.getParameter("hiddenReservMonth"));//예약한 월
				int reservTime=Integer.parseInt(multi.getParameter("hiddenReservTime")); //예약한 시각
				int reservDate=Integer.parseInt(multi.getParameter("hiddenReservDate")); //예약한 일
							
				
				if(Calendar.DATE > reservDate || (reservTime+ reserve) < Calendar.HOUR    )  //예약 게시될 글이 현재 시각 보다 작을 경우
				{
					mav.setViewName("redirect:newsfeed.htm");				
					return mav;
				}
									
				dto.setYear(multi.getParameter("hiddenReservYear"));
				
				
				int time=reservTime+reserve;
				String resultTime=Integer.toString(time);
				String resultMonth=Integer.toString(reservMonth);
				String resultDate=Integer.toString(reservDate);
				
				if(time>=24) //24시 이상이 되었을 경우 처리
				{	
					time=time-24;
					resultTime=Integer.toString(time);
				}
				if(time<10) //1자리수 일 경우 처리 - 오라클 떄문에 앞에 0 붙여줘야 함
					resultTime="0"+time;
				
				if(reservMonth<10)
					resultMonth="0"+reservMonth;
				
				if(reservDate<10)
					resultDate="0"+reservDate;
				
				dto.setMonth(resultMonth);
				dto.setDate(resultDate);
				dto.setHour(resultTime);
				
				//System.out.println("년도"+request.getParameter("hiddenReservYear"));
				//System.out.println("월"+request.getParameter("hiddenReservMonth"));
				//System.out.println("일"+request.getParameter("hiddenReservDate"));
				//System.out.println("시"+resultTime);
								
				int reserveCode=0;    
				reserveCode=rdao.reservePostAdd(dto);//예약 게시글 게시
				
				
				if(filename!=null)
				{
					//사진 첨부 시작
					ReservePostPhotoDTO rpdto=new ReservePostPhotoDTO();
					rpdto.setReserveCode(reserveCode);
					rpdto.setPhotoURL("img\\"+filename);
					
					rdao.reservePhotoAdd(rpdto);
				}
				
				
				
				
				if(!code1.equals("default"))
				{	
					String[] array = {code1, code2, code3, code4, code5}; 	
					//친구 태그 처리						
					ArrayList<MateTagDTO> list = new ArrayList<MateTagDTO>();
								
					for(String s : array){
							//System.out.println(s);
						if(s == "")	//태그되는 친구 코드 있는 값만 dto생성 해서 arraylist에 넣어준다
							break;
						MateTagDTO tagDto = new MateTagDTO();
							
						tagDto.setPostCode(reserveCode);
						tagDto.setMemberCode(Integer.parseInt(s));				
								
						list.add(tagDto);
					}
							
					for (MateTagDTO mateTagDTO : list) {
						rdao.reserveTagAdd(mateTagDTO);
					}	
				}
				
			}//예약 게시물 삽입 끝
			



			
					/*
					  if(포토가 있을 경우)			
						dao.reservePhotoAdd(dto);
					*/	
			
		
			
		
			mav.setViewName("redirect:newsfeed.htm");
			
		} catch(Exception e){
			
			mav.setViewName("redirect:underconstruction.htm");
			e.printStackTrace();
			System.out.println(e.toString());
			
		}
				
				
		return mav;
	}
	
	
	
}
