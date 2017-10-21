package com.real.cu.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Coin;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Note;
import com.real.cu.bean.Person;
import com.real.cu.bean.Review;
import com.real.cu.dao.NoteDao;


@Service
public class NoteService {

	@Autowired
	private HttpSession session;
	@Autowired
	private NoteDao nDao;
	@Autowired
	private HttpServletRequest req;

	private ModelAndView mav;
	public ModelAndView execute(int i) {
		switch(i){
		case 1:
			messageCheck();
			break;
		case 2:
			message();
			break;
		case 3:
			sendmessage();
			break;
		case 5:
			messagedelete();
			break;
		case 6:
			idSelect();
			break;
		case 7:
			recevidSelect();
			break;
		case 8:
			Messagedetail();
			break;
		case 9:
			realmessageSend();
			break;
		case 10:
			acceptshopdeleteSend();
			break;


		default:
			break;
		}
		return mav;
	}


	private void acceptshopdeleteSend() {
		String[] code=req.getParameterValues("chkbox");
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		for(int i=0; i<code.length; i++){
			if(code[i].startsWith("c")||code[i].startsWith("C")){
				String id=nDao.CoinshopIdSelect(code[i]);
				System.out.println("1");
				System.out.println("2");
				map.put("id", id);
				map.put("n_code",null);
				map.put("code", code[i]);
				map.put("title", "매장승인취소알림");
				map.put("content", "매장승인심사를 통과하지 못했습니다.");
				System.out.println("3");
				nDao.acceptshopdeleteSend(map);
			}else{
				System.out.println("4");
				String id=nDao.shopIdSelect(code[i]);
				System.out.println(id);
				System.out.println("5");
				map.put("id", id);
				map.put("n_code",null);
				map.put("code", code[i]);
				map.put("title", "매장승인취소알림");
				map.put("content", "매장승인심사를 통과하지 못했습니다.");
				System.out.println("6");
				nDao.acceptshopdeleteSend(map);
			}
		}
	}



	private void realmessageSend() {
		//String N_SENDID=(String) session.getAttribute("id");
		String n_sendid=(String) session.getAttribute("p_id");
		String n_takeid=req.getParameter("n_takeid");
		String n_title=req.getParameter("n_title");
		String n_content=req.getParameter("n_content");
		Note nt=new Note();
		nt.setN_content(n_content);
		nt.setN_takeid(n_takeid);
		nt.setN_sendid(n_sendid);
		nt.setN_title(n_title);
		nDao.realmessageSend(nt);
		message();   }


	private void Messagedetail() {
		String N_code=req.getParameter("code");
		Note no=nDao.MessageDetail(N_code);
		nDao.MessageUpdate(N_code);
		String result=MessageDetailHtml(no);
		mav.addObject("result", result);
		mav.setViewName("Message");
	}
	private String MessageDetailHtml(Note no) {
		StringBuilder mb=new StringBuilder();
		mb.append("<table><tr><td>제목:"+no.getN_title()+"</td><td>보낸사람:"+no.getN_sendid()+"</td>");
		mb.append("<td>보낸날짜:"+no.getN_date()+"</td>");
		mb.append("<tr><td colspan='3'>보낸내용:"+no.getN_content()+"</td></tr></table>");
		mb.append("<input type='submit' onclick='messagelist();' class='sub' value='목록'/>");
		return mb.toString();
	}
	private void recevidSelect() {
		mav=new ModelAndView();
		mav.addObject("n_takeid",req.getParameter("n_takeid"));
		mav.setViewName("messageSend");
	}

	private void messagedelete() {
		String[] code=req.getParameterValues("chkbox");
		Note no=new Note();
		for(int i=0;i<code.length;i++){
			no.setN_code(code[i]);
			nDao.Messagedelete(no);
		}
		message();
	}      
	   private void idSelect() {
		      mav=new ModelAndView();
		      String selectresult = null;
		      String n_takeid=req.getParameter("n_takeid");
		      String choice=req.getParameter("choice");
		      System.out.println(choice);
		      if(choice.equals("admin")){
		         req.setAttribute("n_takeid", "admin");
		          mav.setViewName("messageSend");
		      }else{
		      if(n_takeid.equals("")){
		         req.setAttribute("n_takeid", "검색할 이름을 입력하세요");
		         mav.setViewName("messageSend");
		      }else{
		         if(choice.equals("laundry")){
		            List<N_laundry> ms=nDao.laundryId(n_takeid);
		            selectresult=selectresult(ms);
		         }else if(choice.equals("coin")){
		            List<Coin> co=nDao.coinId(n_takeid);
		            selectresult=selectCoinresult(co);
		         }else if(choice.equals("person")){
		            List<Person> ps=nDao.personId(n_takeid);
		            selectresult=selectPersonresult(ps);
		         }
		         mav.addObject("selectresult", selectresult);
		         mav.setViewName("idSelect");
		      }
		      }
		   }
	   
	   private String selectPersonresult(List<Person> ps) {
		      StringBuilder mb=new StringBuilder();
		      String n_takeid=req.getParameter("n_takeid");
		      mb.append("<table><tr><td colspan='2'><h2>"+n_takeid+"에대한 검색결과</h2></td></tr>");
		      
		      for(int i=0; i<ps.size(); i++){
		         Person msid=ps.get(i);
		         mb.append("<tr><td><a href='./recevidSelect?n_takeid="+msid.getP_id()+"' >"+msid.getP_id()+"</a></td>");
		         mb.append("<td>"+msid.getP_phone()+"</td></tr>");
		      }
		      mb.append("</table>");
		      return mb.toString();
		   }
	   
	private String selectresult(List<N_laundry> ms) {
		StringBuilder mb=new StringBuilder();
		String n_takeid=req.getParameter("n_takeid");
		mb.append("<table><tr><td colspan='2'><h2>"+n_takeid+"에대한 검색결과</h2></td></tr>");
		
		for(int i=0; i<ms.size(); i++){
			N_laundry msid=ms.get(i);
			msid.setNl_pid(ms.get(i).getNl_pid());
			mb.append("<tr><td><a href='./recevidSelect?n_takeid="+msid.getNl_pid()+"' >"+msid.getNl_pid()+"</a></td>");
			mb.append("<td>"+msid.getNl_name()+"</td></tr>");
		}
		mb.append("</table>");
		return mb.toString();
	}
	private String selectCoinresult(List<Coin> co) {
		StringBuilder mb=new StringBuilder();
		String n_takeid=req.getParameter("n_takeid");
		mb.append("<table><tr><td colspan='2'><h2>"+n_takeid+"에대한 검색결과</h2></td></tr>");
		for(int i=0; i<co.size(); i++){
			Coin msid = co.get(i);
			System.out.println(co.get(i).getCl_pid()+"머냐");
			mb.append("<tr><td><a href='./recevidSelect?n_takeid="+msid.getCl_pid()+"' >"+msid.getCl_pid()+"</a></td>");
			mb.append("<td>"+msid.getCl_name()+"</td></tr>");
		}
		mb.append("</table>");
		return mb.toString();
	}


	

	private void message() {
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
		int listcount=nDao.messageCount(map);
		List<Note> nt=nDao.messageForm(map);
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
		String ntlist=ntlistHtml(nt);
		mav.addObject("result",ntlist);
		mav.setViewName("Message");

	}
	private void sendmessage() {
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
		int listcount=nDao.sendmessageCount(map);
		List<Note> nt=nDao.sendmessage(map);
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
		String ntlist=ntlistHtml(nt);
		mav.addObject("result",ntlist);
		mav.setViewName("Message");
	}
	private String ntlistHtml(List<Note> nt) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(nt.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>제목</td><td>보낸사람</td><td>날짜</td></tr>");
			mb.append("</table><br/><br/><div class='right'><input type='submit' class='sub'  onclick='messageSend();' value='쪽지보내기'/></div>");
		}else{
			Note n=nt.get(0);
			if(n.getN_sendid().equals((String)session.getAttribute("p_id"))){
				/*mb.append("<h4>보낸쪽지함</h4>");*/
				mb.append("<table><<tr><td><input type='checkbox' id='checkAll'>");
				mb.append("</td><td>제목</td><td>받는사람</td><td>날짜</td></tr>");
				mb.append("</tr>");
				for(int i=0; i<nt.size();i++)
				{   
					Note ns=nt.get(i);
					mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+ns.getN_code()+"'></td>");
					mb.append("<td><a href='./Messagedetail?code="+ns.getN_code()+"'>"+ns.getN_title()+"</a></td>");
					mb.append("<td>"+ns.getN_takeid()+"</td>");
					mb.append("<td>"+ns.getN_date()+"</td></tr>");
				}
				mb.append("</table><hr/>");
				if(nt.size()!=0){
					if(page<=1){
						mb.append("<a href='#' class='mar'>[이전]</a>");
					}else{
						mb.append("<a href='./sendmessage?page="+(page-1)+"'class='mar'>[이전]</a>");
					}
					for(int a=startpage; a<=endpage; a++){
						if(a==page){
							mb.append("<a href='#'>["+a+"]</a>");
						}else{
							mb.append("<a href='./sendmessage?page="+a+"'>["+a+"]</a>");
						}
					}
					if(page>=maxpage){
						mb.append("<a href='#'>[다음]</a>");
					}else{
						mb.append("<a href='./sendmessage?page="+(page+1)+"'>[다음]</a>");
					}}
			}else{
				/*mb.append("<h4>받은쪽지함</h4>");*/
				mb.append("<table><thead><tr><td><input type='checkbox' id='checkAll'>");
				mb.append("</td><td>제목</td><td>보낸사람</td><td>날짜</td></tr>");
				for(int i=0; i<nt.size();i++){
					Note ns=nt.get(i);
					mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+ns.getN_code()+"'></td>");
					mb.append("<td><a href='./Messagedetail?code="+ns.getN_code()+"'>"+ns.getN_title()+"</a></td>");
					mb.append("<td>"+ns.getN_sendid()+"</td>");
					mb.append("<td>"+ns.getN_date()+"</td></tr>");
				}
					mb.append("</table><hr/>");
				if(nt.size()!=0){
					if(page<=1){
						mb.append("<a href='#' class='mar'>[이전]</a>");
					}else{
						mb.append("<a href='./message?page="+(page-1)+"'class='mar'>[이전]</a>");
					}
					for(int a=startpage; a<=endpage; a++){
						if(a==page){
							mb.append("<a href='#'>["+a+"]</a>");
						}else{
							mb.append("<a href='./message?page="+a+"'>["+a+"]</a>");
						}
					}
					if(page>=maxpage){
						mb.append("<a href='#'>[다음]</a>");
					}else{
						mb.append("<a href='./message?page="+(page+1)+"'>[다음]</a>");
					}}
			}
			
			mb.append("<div class='right'><input type='submit' class='sub' onclick='messageSend();' value='쪽지보내기'/>");
			mb.append("<input type='submit' class='sub' id='messagedelete' value='삭제'/></div>");
		}return mb.toString();
	}

	public int messageCheck() {
		String id=(String)session.getAttribute("p_id");
		int count=nDao.messageCheck(id);
		System.out.println(count);
		return count;
	}
}
