package com.real.cu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Answer;
import com.real.cu.bean.Image;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Notice;
import com.real.cu.bean.Person;
import com.real.cu.bean.Request;
import com.real.cu.bean.Review;
import com.real.cu.dao.BoardDao;
import com.real.cu.dao.LaundryDao;

@Service
public class BoardService {

	@Autowired
	private HttpSession session;
	@Autowired
	private BoardDao bDao;
	@Autowired
	private HttpServletRequest req;
	private ModelAndView mav;

	public ModelAndView execute(int i) {
		switch(i){
		case 1:
			myActivity();
			break;
		case 2:
			myActivitySelect();
			break;
		case 3:
			reviewChange();
			break;
		case 5:
			shopReview();
			break;
		case 7:
			shopInfo();
			break;
		case 8:
			reInsertPage();
			break;
		case 11:
			test();
			break;
		case 13:
			noticedetail();
			break;
		case 14:
			noticeDelete();
			break;
		case 15:
			noticeUpdateMv();
			break;
		case 16:
			noticeAdd();
			break;
		case 17:
			noticeUpdate();
			break;	
		case 18:
			requestCheck();
			break;
		case 19:
			requestSelect();
			break;
		case 20:
			Request();
			break;
		case 21:
	         requestAdd();
	         break;
	      case 22:
	         requestAnswer();
	         break;
	      case 23:
	         requestAnswerAdd();
	         break;
	      case 24:
	         answerDetail();
	         break;
	      case 25:
	          PesonrequestAnswer();
	          break;
		default:
			break;
		}
		return mav;
	}
	
	   private void PesonrequestAnswer() {
	         mav = new ModelAndView();
	         String code=req.getParameter("r_code");
	         Request rq=bDao.requestDetailSelect(code);
	         Answer an=bDao.AnswerDetail(code);
	         String answerDetail=answerDetailHtml(rq,an);
	         mav.addObject("test",answerDetail);
	         mav.setViewName("PersonAnswer");
	   }

	private void answerDetail() {
        mav = new ModelAndView();
        String code=req.getParameter("r_code");
        Request rq=bDao.requestDetailSelect(code);
        Answer an=bDao.AnswerDetail(code);
        String answerDetail=answerDetailHtml(rq,an);
        mav.addObject("test",answerDetail);
        mav.setViewName("answerDetail");
  }
	private String answerDetailHtml(com.real.cu.bean.Request rq, Answer an) {
        StringBuilder sb=new StringBuilder(); 
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Date date1 = rq.getR_date();
         String today = df.format(date1);
         sb.append("<input type='hidden' name='r_code' value='"+rq.getR_code()+"'>");
           sb.append("<table><tr><td>문의제목<input type='text' value='"+rq.getR_title()+"'readonly/></td>");
            sb.append("<td>문의날짜<input type='text' value='"+today+"'readonly style='text-align:center'/></td></tr>");
            sb.append("<tr><td colspan='2'>문의내용<br/><textarea rows='5' cols='68' readonly style='text-align:center'/>"+rq.getR_content()+"</textarea></td></tr>");
            sb.append("<tr><td colspan='2'>답변내용<br/><textarea rows='5' cols='68' readonly style='text-align:center'>"+an.getAn_content()+"</textarea><br/>");
            sb.append(" <input type='button' id='send'class='sub' onClick='requestreturn()' value='돌아가기'></td></tr></table>");
         return sb.toString();
      }
	
	private void requestAnswerAdd() {
		mav = new ModelAndView();
		String an_rcode=req.getParameter("r_code");
		String an_content=req.getParameter("an_content");
		Answer aw=new Answer();
		aw.setAn_content(an_content);
		aw.setAn_rcode(an_rcode);
		bDao.AnswerAdd(aw);
		bDao.requestUpdate(aw);
		requestCheck();
	}
	private void requestAnswer() {
		mav = new ModelAndView();
		String r_code=req.getParameter("r_code");
		System.out.println(r_code);
		Request rq=bDao.requestAnswerSelect(r_code);
		System.out.println("안오냐");
		String Answer=AnswerHtml(rq);
		mav.addObject("Answer",Answer);
		mav.setViewName("requestAnswer");
	}
	private String AnswerHtml(Request rq) {
		StringBuilder sb=new StringBuilder(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = rq.getR_date();
		String today = df.format(date1);
		sb.append("<input type='hidden' name='r_code' value='"+rq.getR_code()+"'>");
		sb.append("<table><tr><td>문의제목<input type='text' value='"+rq.getR_title()+"'readonly/></td>");
		sb.append("<td>문의날짜<input type='text' value='"+today+"'readonly style='text-align:center'/></td></tr>");
		sb.append("<tr><td colspan='2'>문의내용<br/><textarea rows='5' cols='68' readonly style='text-align:center'/>"+rq.getR_content()+"</textarea></td></tr>");
		sb.append("<tr><td colspan='2'>답변내용<br/><textarea rows='5' cols='68' name='an_content'></textarea><br/>");
		sb.append(" <input type='submit' id='send'class='sub' value='문의답변하기'></td></tr></table>");
		return sb.toString();
	}
	private void requestAdd() {
		mav = new ModelAndView();
		String id = session.getAttribute("p_id").toString();
		String menu = req.getParameter("menu");
		String msg = null;
		Request r = new Request();
		r.setR_pid(id);
		r.setR_title(req.getParameter("r_title"));
		r.setR_content(req.getParameter("r_content"));
		int count = bDao.requestInsert(r);
		if(count!=0){
			msg = "문의글 등록 성공";
		}else{
			msg = "문의글 등록 실패";
		}
		if(menu.equals("개인")){
			mav.setViewName("PersonMain");
		}else if(menu.equals("사업자")){
			mav.setViewName("BusinessMain");
		}
	}

	private void Request() {
		mav = new ModelAndView();
		String menu = req.getParameter("menu");
		mav.addObject("menu", menu);
		mav.setViewName("Request");
	}

	private void reInsertPage() {
		mav = new ModelAndView();
		String re_nrcode=req.getParameter("re_nrcode");
		String re_nlcode=req.getParameter("re_nlcode");
		Review r = new Review();
		r.setRe_nrcode(re_nrcode);
		r.setRe_nlcode(re_nlcode);
		N_laundry n = bDao.laundryInfo(re_nlcode);
		String reShopInfo = reshopInfoHtml(n);   
		mav.addObject("r",r);
		mav.addObject("ShopInfo",reShopInfo);
		mav.setViewName("ReviewInsert");
	}
	private String reshopInfoHtml(N_laundry n) {
		StringBuilder sb=new StringBuilder();      
		sb.append("<div>");
		sb.append("<table><tr><td>"+n.getNl_name()+"</td>세탁소</tr>");
		sb.append("<tr><td>주소"+n.getNl_address()+"</td></tr>");
		sb.append("<tr><td>연락처"+n.getNl_phone()+"</td></tr>");  //주문리스트에서 수량x세탁취급코드
		sb.append("<tr><td>영업시간: 오픈"+n.getNl_open()+"~ 마감"+n.getNl_close()+"</td></tr>");
		sb.append("<tr><td>수선여부:"+n.getNl_repair()+"</td></tr></table>");
		sb.append("</div>");
		return sb.toString();
	}

	private void noticeUpdate() {
		mav = new ModelAndView();
		String nt_title=req.getParameter("nt_title");
		String nt_content=req.getParameter("nt_content");
		String nt_code=req.getParameter("nt_code");
		int nt_categori=Integer.parseInt(req.getParameter("nt_categori"));
		Notice nt=new Notice();
		nt.setNt_title(nt_title);
		nt.setNt_content(nt_content);
		nt.setNt_code(nt_code);
		nt.setNt_categori(nt_categori);
		bDao.noticeUpdate(nt);
		NoticeManage(4);
	}

	private void noticeUpdateMv() {
		mav = new ModelAndView();
		String nt_title=req.getParameter("nt_title");
		String nt_content=req.getParameter("nt_content");
		String nt_code=req.getParameter("nt_code");
		Notice nt=new Notice();
		nt.setNt_code(nt_code);
		nt.setNt_title(nt_title);
		nt.setNt_content(nt_content);
		mav.addObject("nt",nt);
		mav.setViewName("NoticeUpdate");
	}

	private void noticeDelete() {
		mav = new ModelAndView();
		if(req.getParameterValues("chkbox")!=null){
			String[] nt_code=req.getParameterValues("chkbox");
			for(int i=0; i<nt_code.length; i++){
				bDao.noticeDelete(nt_code[i]);
			}
		}NoticeManage(4);
	}

	private void noticeAdd() {
		mav = new ModelAndView();
		String id=session.getAttribute("p_id").toString();
		int nt_categori=Integer.parseInt(req.getParameter("nt_categori"));
		String nt_title=req.getParameter("nt_title");
		String nt_content=req.getParameter("nt_content");
		Notice nt=new Notice();
		nt.setNt_aid(id);
		nt.setNt_categori(nt_categori);
		nt.setNt_content(nt_content);
		nt.setNt_title(nt_title);
		bDao.NoticeAdd(nt);
		NoticeManage(4);
	}

	private void noticedetail() {
		mav = new ModelAndView();
		String nt_code=req.getParameter("nt_code");
		Notice nt=bDao.noticedetail(nt_code);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = nt.getNt_date();
		String today = df.format(date1);
		mav.addObject("today",today);
		bDao.noticedetailHit(nt_code);
		mav.addObject("nt",nt);
		mav.setViewName("NoticeDetail");
	}   

	   private void myActivity() {
		      mav=new ModelAndView();
		      String id=(String)session.getAttribute("p_id");
		      int page=1;
		      int limit=5;
		      if(req.getParameter("page")!=null){
		         page=Integer.parseInt(req.getParameter("page"));
		      }
		      int startrow=(page-1)*5+1;//읽기시작할 row번호
		      int endrow=startrow+limit-1;//읽을 마지막 row번호
		      LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		      map.put("startrow", startrow);
		      map.put("endrow", endrow);
		      map.put("id", id);
		      int listcount=bDao.reviewCount(map);
		      List<Review> nt=bDao.selectReview(map);
		      int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
		      //현재페이지에 보여줄 시작 페이지수
		      int startpage=(((int)((double)page/5+0.9))-1)*5+1;
		      //현재 페이지에 보여줄 마지막 페이지 수
		      int endpage=startpage+5-1;
		      if(endpage>maxpage) endpage=maxpage;
		      req.setAttribute("page", page);//현재 페이지수
		      req.setAttribute("maxpage", maxpage);//최대 페이지수
		      req.setAttribute("startpage", startpage);
		      req.setAttribute("endpage", endpage);
		      String myActivity=myBoardReviewHtml(nt);
		      mav.addObject("myActivity",myActivity);
		      mav.setViewName("MyActivity");

		   }
	private void myActivitySelect() {
		mav=new ModelAndView();
		String id=(String)session.getAttribute("p_id");
		String choice=req.getParameter("myActivity");
		if(choice.equals("review") || choice.equals("all") || choice==null){
			myActivity();
			mav.addObject("kind","review");
		}else if(choice.equals("Request")){
			int page=1;
			int limit=5;
			if(req.getParameter("page")!=null){
				page=Integer.parseInt(req.getParameter("page"));
			}
			int startrow=(page-1)*5+1;//읽기시작할 row번호
			int endrow=startrow+limit-1;//읽을 마지막 row번호
			LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
			map.put("startrow", startrow);
			map.put("endrow", endrow);
			map.put("id", id);
			int listcount=bDao.requestCount(id);
			List<Request> re=bDao.selectRequest(map);
			int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
			//현재페이지에 보여줄 시작 페이지수
			int startpage=(((int)((double)page/5+0.9))-1)*5+1;
			//현재 페이지에 보여줄 마지막 페이지 수
			int endpage=startpage+5-1;
			if(endpage>maxpage) endpage=maxpage;
			req.setAttribute("page", page);//현재 페이지수
			req.setAttribute("maxpage", maxpage);//최대 페이지수
			req.setAttribute("startpage", startpage);
			req.setAttribute("endpage", endpage);
			String myActivity=myBoardRequestHtml(re);
			mav.addObject("kind","Request");
			mav.addObject("myActivity",myActivity);
			mav.setViewName("MyActivity");
		}
	}
	private String myBoardRequestHtml(List<Request> re) {
	      StringBuilder mb=new StringBuilder();
	      int page=(int) req.getAttribute("page");
	      int maxpage=(int) req.getAttribute("maxpage");
	      int startpage=(int) req.getAttribute("startpage");
	      int endpage=(int) req.getAttribute("endpage");
	      if(re.size()==0){
	         mb.append("문의글이 없습니다.");
	      }else{
	         for(int i=0; i<re.size();i++){   
	            Request rq=re.get(i);

	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            Date date1 = rq.getR_date();
	            String today = df.format(date1);
	            mb.append("<hr/><div>　작성일 : "+today+"</div>");
	            mb.append("<div>　내용 : "+rq.getR_content()+"</div>");
	            if(rq.getR_state()==0){
	               mb.append("<div>　답변상태 : 답변대기 </div>");
	            }else if(rq.getR_state()==1){
	               mb.append("<div>　답변상태 : <a href='./PesonrequestAnswer?r_code="+rq.getR_code()+"'>답변완료</a></div>");
	            }
	         }
	         mb.append("<hr/>");
	      }if(re.size()!=0){
	         if(page<=1){
	            mb.append("<a href='#' style='margin-left=40%';>[이전]</a>");
	         }else{
	            mb.append("<a href='./myActivitySelect?page="+(page-1)+"&myActivity=Request' style='margin-left=40%';>[이전]</a>");
	         }
	         for(int a=startpage; a<=endpage; a++){
	            if(a==page){
	               mb.append("<a href='#'>["+a+"]</a>");
	            }else{
	               mb.append("<a href='./myActivitySelect?page="+a+"&myActivity=Request'>["+a+"]</a>");
	            }
	         }
	         if(page>=maxpage){
	            mb.append("<a href='#'>[다음]</a>");
	         }else{
	            mb.append("<a href='./myActivitySelect?page="+(page+1)+"&myActivity=Request'>[다음]</a>");
	         }}

	      return mb.toString();
	   }

	private void NoticeManage(int check) {
		mav = new ModelAndView();
		if(session.getAttribute("p_id")!=null){
			String id=session.getAttribute("p_id").toString();
			mav.addObject("id",id);}
		if(check==1){
			mav.addObject("menu", "공통");
		}else if(check==2){
			mav.addObject("menu", "개인");
		}else if(check==3){
			mav.addObject("menu", "사업자");
		}else{
			mav.addObject("menu", "관리자");
		}
		String nt_categori=req.getParameter("nt_categori");
		System.out.println(nt_categori);
		int page=1;
		int limit=5;
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		if(nt_categori==null){
			nt_categori="0";
		}
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("nt_categori", Integer.parseInt(nt_categori));
		int listcount=bDao.NoticeCount(map);
		if(Integer.parseInt(nt_categori)==0){
			List<Notice> nt=bDao.selectnotice(map);
			int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
			//현재페이지에 보여줄 시작 페이지수
			int startpage=(((int)((double)page/5+0.9))-1)*5+1;
			//현재 페이지에 보여줄 마지막 페이지 수
			int endpage=startpage+5-1;
			if(endpage>maxpage) endpage=maxpage;
			req.setAttribute("page", page);//현재 페이지수
			req.setAttribute("maxpage", maxpage);//최대 페이지수
			req.setAttribute("startpage", startpage);
			req.setAttribute("endpage", endpage);
			req.setAttribute("boardlist", nt);
			String ntList=shopCheckHtml(nt);
			System.out.println("여기오냐");
			mav.addObject("kind",req.getParameter("nt_categori"));
			mav.addObject("list",ntList);
		}else if(Integer.parseInt(nt_categori)==1){
			listcount=bDao.NoticeCount(map);
			List<Notice> nt=bDao.selectnotice(map);
			int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
			//현재페이지에 보여줄 시작 페이지수
			int startpage=(((int)((double)page/5+0.9))-1)*5+1;
			//현재 페이지에 보여줄 마지막 페이지 수
			int endpage=startpage+5-1;
			if(endpage>maxpage) endpage=maxpage;
			req.setAttribute("page", page);//현재 페이지수
			req.setAttribute("maxpage", maxpage);//최대 페이지수
			req.setAttribute("startpage", startpage);
			req.setAttribute("endpage", endpage);
			req.setAttribute("boardlist", nt);
			String ntList=shopCheckHtml(nt);
			mav.addObject("kind",req.getParameter("nt_categori"));
			mav.addObject("list",ntList);
		}else if(Integer.parseInt(nt_categori)==2){
			listcount=bDao.NoticeCount(map);
			List<Notice> nt=bDao.selectnotice(map);
			int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
			//현재페이지에 보여줄 시작 페이지수
			int startpage=(((int)((double)page/5+0.9))-1)*5+1;
			//현재 페이지에 보여줄 마지막 페이지 수
			int endpage=startpage+5-1;
			if(endpage>maxpage) endpage=maxpage;
			req.setAttribute("page", page);//현재 페이지수
			req.setAttribute("maxpage", maxpage);//최대 페이지수
			req.setAttribute("startpage", startpage);
			req.setAttribute("endpage", endpage);
			req.setAttribute("boardlist", nt);
			String ntList=shopCheckHtml(nt);
			mav.addObject("kind",req.getParameter("nt_categori"));
			mav.addObject("list",ntList);
		}
		mav.setViewName("NoticeManage");

	}   

	private String shopCheckHtml(List<Notice> nt) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(nt.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>제목</td><td>날짜</td><td>조회수</td></tr>");
			mb.append("</table><br/><br/><div id='admincheck' style='display:none'><input type='button' name='noticeAddMv'onClick='noticeAddmv()' value='공지등록'/>");
			mb.append("<input type='submit' value='공지삭제'/></div>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>제목</td><td>날짜</td><td>조회수</td></tr>");
			for(int i=0; i<nt.size();i++){   
				Notice ntselcet=nt.get(i);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = ntselcet.getNt_date();
				String today = df.format(date1);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+ntselcet.getNt_code()+"'></td>");
				mb.append("<td><a href='./noticedetail?nt_code="+ntselcet.getNt_code()+"'>"+ntselcet.getNt_title()+"</a></td>");
				mb.append("<td>"+today+"</td>");
				mb.append("<td>"+ntselcet.getNt_click()+"</td></tr>");
			}  mb.append("</table><br/><br/><div id='admincheck' style='display:none'><input type='button' name='noticeAddMv'onClick='noticeAddmv()' value='공지등록'/>");
			mb.append("<input type='submit' value='공지삭제'/></div>");
		}if(nt.size()!=0){
			if(page<=1){
				mb.append("<a href='#'>[이전]</a>");
			}else{
				mb.append("<a href='./NoticeManage?page="+(page-1)+"&choice='all''>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./NoticeManage?page="+a+"&choice='all''>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a>");
			}else{
				mb.append("<a href='./NoticeManage?page="+(page+1)+"&choice='all''>[다음]</a>");
			}}

		return mb.toString();
	}


	   private String myBoardReviewHtml(List<Review> nt) {
		      StringBuilder mb=new StringBuilder();
		      int page=(int) req.getAttribute("page");
		      int maxpage=(int) req.getAttribute("maxpage");
		      int startpage=(int) req.getAttribute("startpage");
		      int endpage=(int) req.getAttribute("endpage");
		      mb.append("<hr>");
		      if(nt.size()==0){
		         mb.append("리뷰활동이 없습니다.");
		      }else{
		         for(int i=0; i<nt.size();i++){  
		            Review rv=nt.get(i);
		            
		            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		            Date date1 = rv.getRe_date();
		            String today = df.format(date1);
		            
		            //별점점수 변환
		            int rstar=rv.getRe_star();
		            String starIMG = null;

		            if(rstar==1){ 
		               starIMG="star1.PNG";
		            }else if(rstar==2){
		               starIMG="star2.PNG";
		            }else if(rstar==3){
		               starIMG="star3.PNG";
		            }else if(rstar==4){
		               starIMG="star4.PNG";
		            }else if(rstar==5){
		               starIMG="star5.PNG";
		            }else{
		               starIMG="nostar.PNG";
		            }
		            mb.append("<div class='tab'>"+today+"<p>");
		            mb.append("<span style='text-align:center;'>별점<img src='resources/images/"+starIMG+" /'></span>");
		            mb.append(rv.getRe_content()+"<a href='./reviewChange?re_code="+rv.getRe_code()+"'class='btn'>수정</a>");
		            mb.append("</div>");
		            mb.append("<hr>");
		            
		         
		         }
		      }if(nt.size()!=0){
		         if(page<=1){
		            mb.append("<br><br><br><p>  </p><a href='#' style='margin-left=40%';>[이전]</a>");
		         }else{
		            mb.append("<a href='./myActivity?page="+(page-1)+"&myActivity='review''style='margin-left=40%';>[이전]</a>");
		         }
		         for(int a=startpage; a<=endpage; a++){
		            if(a==page){
		               mb.append("<a href='#'>["+a+"]</a>");
		            }else{
		               mb.append("<a href='./myActivity?page="+a+"&myActivity='review''>["+a+"]</a>");
		            }
		         }
		         if(page>=maxpage){
		            mb.append("<a href='#'>[다음]</a>");
		         }else{
		            mb.append("<a href='./myActivity?page="+(page+1)+"&myActivity='review''>[다음]</a>");
		         }}

		      return mb.toString();
		   }
	private void requestSelect() {
	      mav = new ModelAndView();
	      int state = Integer.parseInt(req.getParameter("sele"));
	      String id=(String)session.getAttribute("p_id");
	      int page=1;
	      int limit=5;
	      if(req.getParameter("page")!=null){
	         page=Integer.parseInt(req.getParameter("page"));
	      }
	      List<Request> size = bDao.requestselectCheck(state);
	      int startrow=(page-1)*5+1;//읽기시작할 row번호
	      int endrow=startrow+limit-1;//읽을 마지막 row번호
	      LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
	      map.put("startrow", startrow);
	      map.put("endrow", endrow);
	      map.put("state", state);
	      int listcount=size.size();
	      List<Request> relist = bDao.requestSelectList(map);
	      int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
	      //현재페이지에 보여줄 시작 페이지수
	      int startpage=(((int)((double)page/5+0.9))-1)*5+1;
	      //현재 페이지에 보여줄 마지막 페이지 수
	      int endpage=startpage+5-1;
	      if(endpage>maxpage) endpage=maxpage;
	      req.setAttribute("page", page);//현재 페이지수
	      req.setAttribute("maxpage", maxpage);//최대 페이지수
	      req.setAttribute("startpage", startpage);
	      req.setAttribute("endpage", endpage);
	      List<Person> plist = getperson(relist);
	      String html = requestSelectHtml(relist,plist,state);
	      mav.addObject("state",state);
	      mav.addObject("test",html);
	      mav.setViewName("RequestCheck");
	   }
private String requestSelectHtml(List<com.real.cu.bean.Request> relist, List<Person> plist, int state) {
    StringBuilder sb = new StringBuilder();
    Request re = new Request();
    Person p = new Person();
    int page=(int) req.getAttribute("page");
    int maxpage=(int) req.getAttribute("maxpage");
    int startpage=(int) req.getAttribute("startpage");
    int endpage=(int) req.getAttribute("endpage");
    for(int i=0; i<relist.size(); i++){
       re=relist.get(i);
       p=plist.get(i);
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       Date date = re.getR_date();
       String day = df.format(date);
       if(re.getR_state()==0){
          sb.append("<tr><td>"+re.getR_pid()+"</td>");
          sb.append("<td>"+p.getP_name()+"</td>");
          sb.append("<td>"+p.getP_phone()+"</td>");
          sb.append("<td>답변안함</td>");
          sb.append("<td>"+day+"</td>");
          sb.append("<td class='mar'><a href='./requestAnswer?r_code="+re.getR_code()+"' ><input type='button' value='답변하기'></a></td></tr>");
       }else{
          sb.append("<td>"+re.getR_pid()+"</td>");
          sb.append("<td>"+p.getP_name()+"</td>");
          sb.append("<td>"+p.getP_phone()+"</td>");
          sb.append("<td>답변함</td>");
          sb.append("<td>"+day+"</td>");
          sb.append("<td class='mar'><a href='./answerDetail?r_code="+re.getR_code()+"'><input type='button' value='답변보기'></a></td></tr>");
       }}
       sb.append("</table>");
       if(relist.size()!=0){
          if(page<=1){
             sb.append("<a href='#'style='margin-left:40%';>[이전]</a>");
          }else{
             sb.append("<a href='./requestSelect?page="+(page-1)+"&sele="+state+"' style='margin-left:40%';>[이전]</a>");
          }
          for(int a=startpage; a<=endpage; a++){
             if(a==page){
                sb.append("<a href='#'>["+a+"]</a>");
             }else{
                sb.append("<a href='./requestSelect?page="+a+"&sele="+state+"'>["+a+"]</a>");
             }
          }
          if(page>=maxpage){
             sb.append("<a href='#'>[다음]</a>");
          }else{
             sb.append("<a href='./requestSelect?page="+(page+1)+"&sele="+state+"'>[다음]</a>");
          }}
       return sb.toString();
    }

	private void requestCheck() {
		mav = new ModelAndView();
		String id=(String)session.getAttribute("p_id");
		int page=1;
		int limit=5;
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		int listcount=bDao.requestCheckCount();
		List<Request> relist = bDao.requestCheck(map);
		int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
		//현재페이지에 보여줄 시작 페이지수
		int startpage=(((int)((double)page/5+0.9))-1)*5+1;
		//현재 페이지에 보여줄 마지막 페이지 수
		int endpage=startpage+5-1;
		if(endpage>maxpage) endpage=maxpage;
		req.setAttribute("page", page);//현재 페이지수
		req.setAttribute("maxpage", maxpage);//최대 페이지수
		req.setAttribute("startpage", startpage);
		req.setAttribute("endpage", endpage);

		List<Person> plist = getperson(relist);
		String html = requestHtml(relist,plist);
		mav.addObject("test",html);
		mav.setViewName("RequestCheck");
	}

	private List<Person> getperson(List<Request> relist) {
		Request re = new Request();
		List<Person> plist = new ArrayList<Person>();
		for(int i=0;i<relist.size();i++){
			re = relist.get(i);
			plist.addAll(bDao.getperson(re.getR_pid()));
		}
		return plist;
	}

	private String requestHtml(List<Request> relist, List<Person> plist) {
		StringBuilder sb = new StringBuilder();
		Request re = new Request();
		Person p = new Person();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		for(int i=0; i<relist.size(); i++){
			re=relist.get(i);
			p=plist.get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = re.getR_date();
			String day = df.format(date);
			if(re.getR_state()==0){
				sb.append("<tr><td>"+re.getR_pid()+"</td>");
				sb.append("<td>"+p.getP_name()+"</td>");
				sb.append("<td>"+p.getP_phone()+"</td>");
				sb.append("<td>답변안함</td>");
				sb.append("<td>"+day+"</td>");
				sb.append("<td class='mar'><a href='./requestAnswer?r_code="+re.getR_code()+"' ><input type='button' value='답변하기'></a></td></tr>");
			}else{
				sb.append("<td>"+re.getR_pid()+"</td>");
				sb.append("<td>"+p.getP_name()+"</td>");
				sb.append("<td>"+p.getP_phone()+"</td>");
				sb.append("<td>답변함</td>");
				sb.append("<td>"+day+"</td>");
				sb.append("<td class='mar'><a href='./answerDetail?r_code="+re.getR_code()+"'><input type='button' value='답변보기'></a></td></tr>");
			}}
		sb.append("</table>");
		if(relist.size()!=0){
			if(page<=1){
				sb.append("<a href='#'style='margin-left:40%';>[이전]</a>");
			}else{
				sb.append("<a href='./requestCheck?page="+(page-1)+"' style='margin-left:40%';>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./requestCheck?page="+a+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./requestCheck?page="+(page+1)+"'>[다음]</a>");
			}}
		return sb.toString();
	}

	private void test() {
		mav = new ModelAndView();
		System.out.println("서비스탐");
		mav.setViewName("NoticeManage");
	}
	private void reviewadd(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		String id=(String)session.getAttribute("p_id");
		Review re=new Review();
		re.setRe_pid(id);
		re.setRe_content(multi.getParameter("re_content"));
		re.setRe_nlcode(multi.getParameter("re_nlcode"));
		re.setRe_nrcode(multi.getParameter("re_nrcode"));
		System.out.println(multi.getParameter("re_star"));
		re.setRe_star(Integer.parseInt(multi.getParameter("re_star")));
		bDao.reviewadd(re);
		fileUp(multi,re.getRe_code());
		mav.setViewName("redirect:myActivity");
	}
	public void fileUp(MultipartHttpServletRequest multi, String code) throws IllegalStateException, IOException{
		//1.저장경로 찾기
		System.out.println("안넘어와?");
		String root=multi.getSession().getServletContext().getRealPath("/");   //물리적인 주소
		System.out.println(root);
		String path=root+"/resources/upload/";
		/*String path="C://Users/Administrator/Documents/세탁소개팅/CU/src/main/webapp/resources/upload";   //fullPath와 동일
		 */      
		//2.폴더 생성을 꼭 할것...
		File dir=new File(path);
		if(!dir.isDirectory()){  //upload 폴더 없다면
			dir.mkdir();  //upload 폴더 생성
		}
		//3.파일을 가져오기-일태그 이름들 반환
		Iterator<String> files=multi.getFileNames();   //파일 업로드 2개 이상일때
		while(files.hasNext()){
			LinkedHashMap<String,String> fMap=new LinkedHashMap<String, String>();
			String fileTagName=files.next();
			//파일 메모리에 저장
			MultipartFile mf=multi.getFile(fileTagName);   //실제 파일에 대한 모든 정보 담음
			String oriFileName=mf.getOriginalFilename();   //a.txt
			/*fMap.put("oriFileName", oriFileName);*/
			//4.시스템파일이름 생성  a.txt  ==>112323242424.txt
			String sysFileName=System.currentTimeMillis()+"."
					+oriFileName.substring(oriFileName.lastIndexOf(".")+1);
			if(sysFileName.contains("jpg")){
				System.out.println("안되는거!!");
				System.out.println(sysFileName);
				fMap.put("img_name", sysFileName);
				fMap.put("img_board", code);
				fMap.put("img_code",null);

				bDao.reviewPictureAdd(fMap);
				//5.메모리->실제 파일 업로드
				try {
					mf.transferTo(new File(path+sysFileName));

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	//리뷰 수정후 지움 -광영
	private void shopReview() {
		mav=new ModelAndView();
		String NLCODE=session.getAttribute("NLCODE").toString();

		List<Review> reList= bDao.reviewList(NLCODE);

		String re = reviewListHtml(reList);   


		System.out.println(re);
		//     String re_nlcode=req.getParameter("re_nlcode");
		//     String re_nrcode=req.getParameter("re_nrcode");
		//     System.out.println(re_nlcode+"111"+re_nrcode);

		//      N_laundry LaundryInfo=bDao.shopInfo(NLCODE);
		//      System.out.println(LaundryInfo.getNl_code()+"code");
		//     String Ldinfo=LdInfoHtml(LaundryInfo);
		//      mav.addObject("Ldinfo",Ldinfo);
		//     mav.addObject("re_nrcode",re_nrcode);
		mav.addObject("review",re);

		mav.setViewName("Review");
	} 
	private void shopInfo() {
		mav=new ModelAndView();
		String NLCODE=session.getAttribute("NLCODE").toString();

		N_laundry n = bDao.laundryInfo(NLCODE);

		String nInfo = shopInfoHtml(n);   

		mav.addObject("nInfo",nInfo);
		mav.addObject("laundry",n);
		mav.setViewName("ShopDetailInfo");
	}
	public String shopInfoHtml(N_laundry n) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");        
		sb.append("<tr><td>매장주소</td><td>"+n.getNl_address()+"</td>");        
		sb.append("<tr><td>매장 홍보글</td><td>"+n.getNl_content()+"</td></tr>");
		sb.append("</table>");
		return sb.toString();
	}
	private String reviewListHtml(List<Review> reList) {

		StringBuilder sb=new StringBuilder();

		Review r = new Review();
		sb.append("<table id='rt'>");
		for(int i=0; i<reList.size(); i++){
			r=reList.get(i);
			//리뷰이미지뽑기
			String RE_CODE=r.getRe_code();
			List<Image> imgList= bDao.ImageSelect(RE_CODE);
			Image img = new Image();

			//날짜변경
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
			Date date1 = r.getRe_date();
			String date = df.format(date1);

			//별점점수 변환
			int rstar=r.getRe_star();
			String starIMG = null;

			if(rstar==1){ 
				starIMG="star1.PNG";
			}else if(rstar==2){
				starIMG="star2.PNG";
			}else if(rstar==3){
				starIMG="star3.PNG";
			}else if(rstar==4){
				starIMG="star4.PNG";
			}else if(rstar==5){
				starIMG="star5.PNG";
			}else{
				starIMG="nostar.PNG";
			}

			sb.append("<tr><td>"+r.getRe_pid()+" 고객님  </td>");
			sb.append("<td>별점<img src='resources/images/"+starIMG+"'></td>");  
			sb.append("<td>"+date+"작성</td></tr>");                    //td3 tr1
			sb.append("<tr><td coslpan='3'>"+r.getRe_content()+"</td><td></td><td></td></tr>");  //tr2

			for(int j=0; j<imgList.size();j++){  //이미지리스트
				img=imgList.get(j);
				System.out.println(imgList.size()+"이미지리스트사이즈");
				sb.append("<p><input type='hidden' name='img_name"+j+"'value='"+img.getImg_code()+"'/>");
				sb.append("<tr><td id='imre'><img src='resources/upload/"+img.getImg_name()+"'width='150' height='150'/></td><td></td><td></td></tr>");
			}    //img src='resources/images/"+starIMG+"'>
			System.out.println(img.getImg_name()+"이미지이름");
			sb.append("<tr id='ss'><td coslpan='3'></td><td></td><td></td></tr>");//tr3
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void reviewChange() {
		mav=new ModelAndView();
		String re_code=req.getParameter("re_code");
		System.out.println(re_code);
		N_laundry LaundryInfo=bDao.LaundryInfo(re_code);
		Review re=bDao.reviewInfo(re_code);
		List<Image> img=bDao.ImageSelect(re_code);
		String Ldinfo=LdInfoHtml(LaundryInfo);
		String reInfo=reInfoHtml(re,img);
		mav.addObject("Ldinfo",Ldinfo);
		mav.addObject("reInfo",reInfo);
		mav.setViewName("ReviewUpdate");
	}
	private String reInfoHtml(Review re, List<Image> img) {
		StringBuilder mb=new StringBuilder();
		for(int i=0; i<img.size(); i++){
			Image image=img.get(i);
			System.out.println(image.getImg_name());
			mb.append("<p><input type='hidden' name='img_name"+i+"' value='"+image.getImg_code()+"'/>");
			mb.append("<img src='resources/upload/"+image.getImg_name()+"' width='50' height='50'/>");
			mb.append("<a href='#this'class='btn' id='delete' name='delete'>삭제</a></p>");
		}
		mb.append("<input type='text' name='re_star' value='"+re.getRe_star()+"'>");
		mb.append("<input type='hidden' name='re_code' value='"+re.getRe_code()+"'>");
		mb.append("<textarea rows='10' cols='40' name='re_content' >"+re.getRe_content()+"</textarea>");
		return mb.toString();
	}



	private String LdInfoHtml(N_laundry nl) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h3 style=''>"+nl.getNl_name()+"</h3>");
		sb.append("<span>주소 : "+nl.getNl_address()+"</span><br/>");
		sb.append("<span>연락처 : "+nl.getNl_phone()+"</span><br/>");
		sb.append("<span>영업시간 : 오픈 "+nl.getNl_open()+"시 ~ 마감 "+nl.getNl_close()+"시</span><br/>");
		sb.append("<table><tr><td colspan='3'>상세가격</td></tr>");
		sb.append("</table>");
		sb.append("<h3>세탁소 홍보글</h3>");
		sb.append("<p>"+nl.getNl_content()+"</p>");
		sb.append("<input type='hidden' name='re_nlcode' value='"+nl.getNl_code()+"'>");
		return sb.toString();
	}
	public ModelAndView execute(int check,int i) {
		switch(i){
		case 10:
			NoticeManage(check);
			break;
		default:break;
		}return mav;
	}



	public ModelAndView execute(MultipartHttpServletRequest multi, int cmd) throws IllegalStateException, IOException {
		switch(cmd){
		case 1:
			reviewUpdate(multi);
			break;
		case 2:
			reviewadd(multi);
			break;

		default:
			break;
		}
		return mav;
	}
	private void reviewUpdate(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		Image image=new Image();
		System.out.println("들어오니니");
		LinkedHashMap<String,Object> fmap=new LinkedHashMap<String,Object>();
		fmap.put("re_code", (String)multi.getParameter("re_code"));
		fmap.put("re_content", (String)multi.getParameter("re_content"));
		fmap.put("re_star", Integer.parseInt(multi.getParameter("re_star")));
		fmap.put("re_pid", (String)session.getAttribute("p_id"));//(String)session.getAttribute("id") 변경
		bDao.reviewUpdate(fmap);
		if(multi.getParameterValues("arr")!=null){
			String[] imgList=multi.getParameterValues("arr");
			String[] array = imgList[0].split(",");
			for(int i=0; i<array.length;i++){
				System.out.println("들어오지?????");
				System.out.println(array[i]);
				image.setImg_code(array[i]);
				bDao.imgDelete(image);
			}
		}
		fileUp(multi,multi.getParameter("re_code"));
		myActivity();
	}
}
