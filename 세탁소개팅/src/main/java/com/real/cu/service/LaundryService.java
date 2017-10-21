package com.real.cu.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Coin;
import com.real.cu.bean.Image;
import com.real.cu.bean.Laundryhandle;
import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Notice;
import com.real.cu.bean.Pointhistory;
import com.real.cu.dao.LaundryDao;
import com.real.cu.dao.ReservationDao;

@Service("laundryservice")
public class LaundryService {

	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;
	@Autowired
	private HttpSession session;
	@Autowired
	private LaundryDao lDao;
	@Autowired
	private BoardService bs;
	private ReservationDao rDao;


	private Image img;

	private ModelAndView mav;

	public ModelAndView execute(MultipartHttpServletRequest multi, int cmd) throws IllegalStateException, IOException {
		switch(cmd){
		case 1:
			coinShopAdd(multi);
			break;
		case 2:
			LaundryAdd(multi);
			break;
		case 3:
			updateCoinShop(multi);
			break;
		case 4:
			updateLaundry(multi);
			break;


		default:
			break;
		}
		return mav;
	}
	private void updateLaundry(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		Image image = new Image();
		Laundryhandle lh = new Laundryhandle();
		LinkedHashMap<Object,Object> lhMap = new LinkedHashMap<Object,Object>();
		nl.setNl_code(session.getAttribute("nl_code").toString());
		nl.setNl_name(multi.getParameter("nl_name"));
		System.out.println("post="+multi.getParameter("post"));
		System.out.println("post="+multi.getParameter("addr1"));
		System.out.println("post="+multi.getParameter("addr2"));
		String address=multi.getParameter("post");
		if(address.length()==0){
			nl.setNl_address(multi.getParameter("nl_address"));
		}else{
			nl.setNl_address(multi.getParameter("post")+"/"+(String)multi.getParameter("addr1")+"/"+(String)multi.getParameter("addr2"));
		}
		/*if(multi.getParameter("post")!=null || multi.getParameter("post") != ""){
            String address = multi.getParameter("post") + multi.getParameter("addr1")+"/"+ multi.getParameter("addr2");
            nl.setNl_address(address);
         }*/
		lDao.UpdateNlAddress(nl);
		String phone = "0"+multi.getParameter("first") +"-"+ multi.getParameter("second") +"-"+ multi.getParameter("third");
		nl.setNl_phone(phone);
		if(multi.getParameter("nl_repair").equals("가능")){
			nl.setNl_repair(1);
		}else{
			nl.setNl_repair(0);
		}
		nl.setNl_open(multi.getParameter("nl_open"));
		nl.setNl_close(multi.getParameter("nl_close"));
		nl.setNl_content(multi.getParameter("nl_content"));
		if(multi.getParameter("nl_timelap").equals("1시간")){
			nl.setNl_timelap(0);
		}else{
			nl.setNl_timelap(1);
		}
		nl.setNl_qty(Integer.valueOf(multi.getParameter("nl_qty")));
		System.out.println("nl_qty="+nl.getNl_name());
		System.out.println("nl_qty="+nl.getNl_permission());
		System.out.println("nl_qty="+nl.getNl_qty());
		if(lDao.updateN_laundry(nl)==1){
			System.out.println("laundry업데이트 성공");
			if(multi.getParameterValues("arrimg")!=null){
				String[] imgList=multi.getParameterValues("arrimg");
				String[] array = imgList[0].split(",");
				for(int i=0; i<array.length;i++){
					System.out.println("여기당"+array[i]);
					image.setImg_code(array[i]);
					lDao.imgDelete(image);
				}
			}
			fileUp(multi,nl.getNl_code(),false);
			if(session.getAttribute("arraydelete")!=null){   //4배수
				String[] arraydelete = (String[]) session.getAttribute("arraydelete");
				for(int i=0; i<arraydelete.length; i++){
					if(i%4 == 3){
						lh.setLh_code(arraydelete[i]);
						lDao.laundryHandleDelete(lh);
					}
				}
			}
			if(session.getAttribute("arrayinsert")!=null){   //3배수
				String[] arrayinsert = (String[]) session.getAttribute("arrayinsert");
				for(int i=0; i<arrayinsert.length; i++){
					if((i%3)==0){
						lhMap.put("lh_kind", arrayinsert[i]);   
					}else if((i%3)==1){
						lhMap.put("lh_laundry", arrayinsert[i]);
					}else if((i%3)==2){
						lhMap.put("lh_payment", Integer.parseInt(arrayinsert[i]));
						lhMap.put("lh_nlcode", nl.getNl_code());
						lhMap.put("lh_code", null);
						lDao.insertLaundryHandle(lhMap);
					}
				}
			}else{
				System.out.println("업데이트 실패");
			}
		}
		session.removeAttribute("array");
		session.removeAttribute("arrayinsert");
		session.removeAttribute("arraydelete");
		session.removeAttribute("nl_code");
		mav.setViewName("forward:/laundryMm");

	}
	private void LaundryAdd(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		String view = null;
		String msg = null;
		N_laundry nl = new N_laundry();
		String id = session.getAttribute("p_id").toString();
		nl.setNl_pid(id);   //========================================session으로 바꿀것
		nl.setNl_name(multi.getParameter("nl_name"));
		String address = multi.getParameter("post") +"/"+ multi.getParameter("addr1")+"/"+ multi.getParameter("addr2");
		nl.setNl_address(address);
		String first = multi.getParameter("first");
		System.out.println("first="+first);
		String phone = "0"+multi.getParameter("first") +"-"+ multi.getParameter("second") +"-"+ multi.getParameter("third");
		nl.setNl_phone(phone);
		if(multi.getParameter("nl_repair").equals("가능")){
			nl.setNl_repair(1);
		}else if(multi.getParameter("nl_repair").equals("불가능")){
			nl.setNl_repair(0);
		}
		nl.setNl_open(multi.getParameter("nl_open"));
		nl.setNl_close(multi.getParameter("nl_close"));
		nl.setNl_content(multi.getParameter("nl_content"));
		if(multi.getParameter("nl_timelap").equals("1시간")){
			nl.setNl_timelap(0);
		}else if(multi.getParameter("nl_timelap").equals("30분")){
			nl.setNl_timelap(1);
		}
		nl.setNl_qty(Integer.valueOf(multi.getParameter("nl_qty")));
		String arr = multi.getParameter("arr");
		System.out.println(arr);
		String[] array = arr.split(",");
		LinkedHashMap<Object,Object> lhMap = new LinkedHashMap<Object,Object>();

		if(lDao.InsertN_laundry(nl)!=0){
			System.out.println(nl.getNl_code());
			fileUp(multi,nl.getNl_code(),true);
			for(int i=0;i<array.length; i++){
				if((i%3)==0){
					lhMap.put("lh_kind", array[i]);   
				}else if((i%3)==1){
					lhMap.put("lh_laundry", array[i]);
				}else if((i%3)==2){
					System.out.println("payment="+array[i]);
					lhMap.put("lh_payment", Integer.parseInt(array[i]));
					lhMap.put("lh_nlcode", nl.getNl_code());
					lhMap.put("lh_code", null);
					lDao.insertLaundryHandle(lhMap);
				}
			}
			session.removeAttribute("array");
			msg = "매장등록 성공";
			view = "BusinessMain";
		}else{
			msg = "매장등록 실패";
			view = "LaundryAdd";   //실패시 홈으로
		}
		mav.addObject("msg", msg);
		mav.setViewName(view);
	}

	private void laundryMm() {
		String id=session.getAttribute("p_id").toString();
		String view = null;
		List<N_laundry> llist = null;
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
		int listcount=lDao.laundryCount(map);
		mav = new ModelAndView();
		llist = lDao.LaundrySelect(map);
		if(llist.size()!=0){
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
			req.setAttribute("boardlist", llist);
			mav.setViewName("CoinShopCare");
			String html = LaundryHtml(llist);
			//System.out.println(html);
			mav.addObject("llisthtml", html);
			req.setAttribute("llisthtml", html);
			view="LaundryCare";
		}else{
			view="BusinessMain";
		}
		mav.setViewName(view);
	}

	private void coinShopMm() {
		mav=new ModelAndView();

		String id=session.getAttribute("p_id").toString();
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
		int listcount=lDao.CoinshopCount(map);
		List<Coin> clist=lDao.CoinListSelect(map);
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
		req.setAttribute("boardlist", clist);
		String CoinShop=listHtml(clist);
		mav.addObject("list",CoinShop);
		mav.setViewName("CoinShopCare");
		/*String id=(String)session.getAttribute("id");*/
	}
	private void shopDetail() {
		mav = new ModelAndView();
		String view = null;
		String nl_code=req.getParameter("nl_code");
		N_laundry nl = new N_laundry();
		nl = lDao.LaundryDetailSelect(nl_code);
		if(nl.getNl_name() != null){
			List<Laundryhandle> lhlist = null;
			lhlist = lDao.HandleSelect(nl_code);
			List<Image> imgList = null;
			imgList = lDao.ImageSelect(nl_code);
			String imghtml = imgListHtml(imgList);
			String ldetailhtml = LaundryDetailHtml(nl, lhlist);
			System.out.println(ldetailhtml);
			mav.addObject("imghtml", imghtml);
			mav.addObject("LaundryDetail", ldetailhtml);
			view = "ShopDetailView";
		}else{
			view = "forward:/laundryMm";
		}
		mav.setViewName(view);
	}
	private String LaundryDetailHtml(N_laundry nl, List<Laundryhandle> lhlist) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>"+nl.getNl_name()+"</h3>");
		sb.append("주소 : "+nl.getNl_address()+"<br/>");
		sb.append("연락처 : "+nl.getNl_phone()+"<br/>");
		sb.append("영업시간 : 오픈 0"+nl.getNl_open()+":00 ~ 마감 "+nl.getNl_close()+":00<br/>");
		if(nl.getNl_repair() == 0){
			sb.append("수선가능 여부 : 불가능<br/>");
		}else{
			sb.append("수선가능 여부 : 가능<br/>");
		}
		sb.append("<a href='./shopDetail?nl_code="+nl.getNl_code()+"'>주요정보</a>");
		sb.append("<a href='./shopReview?nl_code="+nl.getNl_code()+"'>리뷰</a>");
		sb.append("<a href='./shopCart?nl_code="+nl.getNl_code()+"'>예약하기</a>");
		//지도넣기
		sb.append("<table><tr><td colspan='3'>상세가격</td></tr>");
		for(int i=0; i<lhlist.size(); i++){
			Laundryhandle lh = lhlist.get(i);
			sb.append("<tr><td>"+lh.getLh_kind()+"</td><td>"+lh.getLh_laundry()+"</td><td>"+lh.getLh_payment()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<h3>세탁소 홍보글</h3>");
		sb.append("<p>"+nl.getNl_content()+"</p>");
		return sb.toString();
	}


	public String CoinInfoHtml(Coin co) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table id='sta'><tr><td>세탁소명:</td><td>"+co.getCl_name()+"</td></tr>");
		sb.append("<tr><td>주소</td><td>"+co.getCl_address()+"</td></tr>");
		sb.append("<tr><td>연락처</td><td>"+co.getCl_phone()+"</td></tr>");  //주문리스트에서 수량x세탁취급코드
		sb.append("<tr><td>영업시간</td><td>: 오픈"+co.getCl_open()+"~ 마감"+co.getCl_close()+"</td></tr>");
		sb.append("<tr><td>매장주소</td><td>"+co.getCl_address()+"</td></tr>");        
		sb.append("<tr><td>매장 홍보글</td><td>"+co.getCl_content()+"</td></tr>");
		sb.append("</table>");
		return sb.toString();
	}
	private String CoinDetailHtml(Coin co, List<Image> imgList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<table id='sta'><tr><td>세탁소명:"+co.getCl_name()+"</td></tr>");
		sb.append("<tr><td>주소"+co.getCl_address()+"</td></tr>");
		sb.append("<tr><td>연락처"+co.getCl_phone()+"</td></tr>");  //주문리스트에서 수량x세탁취급코드
		sb.append("<tr><td>영업시간: 오픈"+co.getCl_open()+"~ 마감"+co.getCl_close()+"</td></tr>");
		sb.append("</div>");
		session.setAttribute("CLCODE", co.getCl_code());

		return sb.toString();
	}
	private void coinShopUpdate() {
		mav=new ModelAndView();
		System.out.println("된다");
		String cl_code=req.getParameter("cl_code");
		Coin co = new Coin();
		co = lDao.CoinDetailSelect(cl_code);
		co.setCl_open(co.getCl_open());
		co.setCl_close(co.getCl_close());
		List<Image> imgList = null;
		imgList= lDao.ImageSelect(cl_code);
		String imgListHtml=imgListHtml(imgList);
		mav.addObject("co",co);
		mav.addObject("lmgList",imgListHtml);
		mav.setViewName("CoinShopUpdate");
		System.out.println("안된다");
	}

	private String imgListHtml(List<Image> imgList) {
		StringBuilder mb=new StringBuilder();
		for(int i=0; i<imgList.size(); i++){
			Image img=imgList.get(i);
			System.out.println(img.getImg_name());
			mb.append("<p><input type='hidden' name='img_name"+i+"' value='"+img.getImg_code()+"'/>");
			mb.append("<img src='resources/upload/"+img.getImg_name()+"' width='150' height='150'/>");
			mb.append("<a href='#this'class='btn' id='delete' name='delete'>삭제</a></p>");
		}

		return mb.toString();
	}
	private String lListHtml(List<N_laundry> list, LinkedHashMap<String, Object> map) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(list.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>사업자아이디</td><td>전화번호</td></tr>");
			mb.append("</table><br/><br/><input type='button' name='shopdelete' id='shopdelete' value='매장삭제'/>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>사업자아이디</td><td>전화번호</td></tr>");
			for(int i=0; i<list.size();i++){   
				N_laundry nl=list.get(i);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+nl.getNl_code()+"'></td>");
				mb.append("<td><a href='./shopDetail?nl_code="+nl.getNl_code()+"' class='btn'>"+nl.getNl_name()+"</a></td>");
				mb.append("<td>"+nl.getNl_pid()+"</td>");
				mb.append("<td>"+nl.getNl_phone()+"</td>");
			}   mb.append("</table><br/><br/><div id='deletebtn'><a href='./shopdelete' id='button'>매장삭제</a></div>");
		}
		if(list.size()!=0){
			if(page<=1){
				mb.append("<a href='#'>[이전]</a>");
			}else{
				mb.append("<a href='./laundryManage?page="+(page-1)+"&search="+map.get("search")+"'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./laundryManage?page="+a+"&search="+map.get("search")+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a>");
			}else{
				mb.append("<a href='./laundryManage?page="+(page+1)+"&search="+map.get("search")+"'>[다음]</a>");
			}
		}

		return mb.toString();
	}
	private String listHtml(List<Coin> clist) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(clist.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>연락처</td><td>주소</td><td>보유세탁기</td><td>비고</td></tr>");
			mb.append("</table><br/><br/><input type='button' name='coinShopDelete' id='coinShopDelete' value='매장삭제'/>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>연락처</td><td>주소</td><td>보유세탁기</td><td>비고</td></tr>");
			for(int i=0; i<clist.size();i++){   
				Coin co=clist.get(i);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+co.getCl_code()+"'></td>");
				mb.append("<td>"+co.getCl_name()+"</td>");
				mb.append("<td>"+co.getCl_phone()+"</td>");
				mb.append("<td>"+co.getCl_address()+"</td>");
				mb.append("<td>"+co.getCl_qty()+"</td>");
				mb.append("<td><a href='./coinShopDetail?cl_code="+co.getCl_code()+"' class='btn'>상세보기</a><br/><a href='./coinShopUpdate?cl_code="+co.getCl_code()+"' class='btn'>매장수정</a></td></tr>");
			}   mb.append("</table><br/><br/><input type='submit' value='매장삭제' class='btn'/>");
		}
		if(clist.size()!=0){
			if(page<=1){
				mb.append("<a href='#' style='margin-left:40%;'>[이전]</a>");
			}else{
				mb.append("<a href='./coinShopMm?page="+(page-1)+"' style='margin-left:40%;'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./coinShopMm?page="+a+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a>");
			}else{
				mb.append("<a href='./coinShopMm?page="+(page+1)+"'>[다음]</a>");
			}
		}

		return mb.toString();
	}
	private String LaundryHtml(List<N_laundry> llist) {
		StringBuilder sb = new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		sb.append("<table><tr style='text-align:center;'><td>선택</td><td>매장이름</td><td>연락처</td><td>주소</td><td></td></tr>");
		for(int i=0; i<llist.size(); i++){
			N_laundry nl = llist.get(i);
			sb.append("<tr><td><input type='checkbox' name='choice' id='choice' value='"+nl.getNl_code()+"'/></td>");
			sb.append("<td>"+nl.getNl_name()+"</td>");
			sb.append("<td>"+nl.getNl_phone()+"</td>");
			sb.append("<td>"+nl.getNl_address()+"</td>");
			sb.append("<td><a href='./shopDetail?nl_code="+nl.getNl_code()+"' >　상세보기　</a>");
			sb.append("<a href='./laundryUpdate?nl_code="+nl.getNl_code()+"'>　수정하기</a></td></tr>");
		}
		sb.append("</table>");
		sb.append("<a href='#' onclick='./laundryDelete' class='btn'>매장삭제</a>");
		if(llist.size()!=0){
			if(page<=1){
				sb.append("<a href='#'>[이전]</a>");
			}else{
				sb.append("<a href='./laundryMm?page="+(page-1)+"'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./laundryMm?page="+a+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./laundryMm?page="+(page+1)+"'>[다음]</a>");
			}
		}

		return sb.toString();
	}




	private void coinShopAdd(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		Coin co=new Coin();
		co.setCl_name(multi.getParameter("cl_name"));
		co.setCl_address(multi.getParameter("post")+"/"+multi.getParameter("addr1")+"/"+multi.getParameter("addr2"));
		co.setCl_phone(multi.getParameter("first")+"-"+multi.getParameter("second")+"-"+multi.getParameter("third"));
		co.setCl_content(multi.getParameter("cl_content"));
		co.setCl_open(multi.getParameter("cl_open"));
		co.setCl_close(multi.getParameter("cl_close"));
		//co.setCl_pid("ddd");
		co.setCl_pid((String)session.getAttribute("p_id"));
		co.setCl_qty(Integer.parseInt(multi.getParameter("cl_qty")));
		System.out.println(co.getCl_content());
		lDao.coinShopAdd(co);
		System.out.println(co.getCl_code());
		fileUp(multi,co.getCl_code(),true);
		mav.setViewName("BusinessMain");
	}
	private void updateCoinShop(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		Image image=new Image();
		LinkedHashMap<String,Object> fmap=new LinkedHashMap<String,Object>();
		fmap.put("cl_name", (String)multi.getParameter("cl_name"));
		fmap.put("cl_phone", (String)multi.getParameter("cl_phone"));
		fmap.put("cl_content", (String)multi.getParameter("cl_content"));
		fmap.put("cl_open", (String)multi.getParameter("cl_open"));
		fmap.put("cl_close", (String)multi.getParameter("cl_close"));
		fmap.put("cl_phone", (String)multi.getParameter("cl_phone"));
		fmap.put("cl_qty", Integer.parseInt(multi.getParameter("cl_qty")));
		/*co.setCl_code(multi.getParameter("cl_name"));
      co.setCl_phone(multi.getParameter("cl_phone"));
      co.setCl_phone(multi.getParameter("first")+"-"+multi.getParameter("second")+"-"+multi.getParameter("third"));
      co.setCl_content(multi.getParameter("cl_content"));
      co.setCl_open(multi.getParameter("cl_open"));
      co.setCl_close(multi.getParameter("cl_close"));*/
		String address=multi.getParameter("post");
		if(address.length()==0){
			fmap.put("cl_address", (String)multi.getParameter("cl_address"));
			//co.setCl_address(multi.getParameter("cl_address"));
		}else{
			fmap.put("cl_address", (String)multi.getParameter("post")+"/"+(String)multi.getParameter("addr1")+"/"+(String)multi.getParameter("addr2"));
			//co.setCl_address(multi.getParameter("post")+multi.getParameter("addr1")+multi.getParameter("addr2"));
		}
		System.out.println("실행");
		fmap.put("cl_code", (String)multi.getParameter("cl_code"));
		lDao.coinShopUpdate(fmap);
		System.out.println("되냐");

		System.out.println(multi.getParameter("cl_code"));
		/*co.setCl_code(multi.getParameter("cl_code"));*/

		if(multi.getParameterValues("arr")!=null){
			String[] imgList=multi.getParameterValues("arr");
			String[] array = imgList[0].split(",");
			for(int i=0; i<array.length;i++){
				System.out.println("여기당"+array[i]);
				image.setImg_code(array[i]);
				lDao.imgDelete(image);
			}
		}
		/*      if(img1.length!=0){
         for(int i=0; i<img1.length; i++){
            System.out.println(img1[i]);
            for(int j=0; j<array.length; j++){
               System.out.println("arr"+array[j]);
               if(img1[i].equals(array[j])){
               }else if(j==(array.length-1)){
                  image.setImg_code(img1[i]);
                  System.out.println(img1[i]);
                  lDao.imgDelete(image);
                  System.out.println("들어오니");
               }
            }
         }

      }*/System.out.println("실행");

      fileUp(multi,multi.getParameter("cl_code"),false);

      coinShopMm();
	}



	public void fileUp(MultipartHttpServletRequest multi, String code,Boolean frag) throws IllegalStateException, IOException{
		//1.저장경로 찾기
		System.out.println("안넘어와?");
		String root=multi.getSession().getServletContext().getRealPath("/");   //물리적인 주소
		System.out.println(root);
		String path=root+"/resources/upload/";
		/*String path="C://Users/Administrator/Documents/세탁소개팅/CU/src/main/webapp/resources/upload";   //fullPath와 동일
		 */      session.setAttribute("path", path);
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
				 if(frag){
					 lDao.lisencePictureAdd(fMap);
					 System.out.println("성공");
					 mf.transferTo(new File(path+sysFileName));
					 frag=false;
				 }
				 else{
					 System.out.println("들어왔니~~");
					 lDao.shopPictureAdd(fMap);
					 //5.메모리->실제 파일 업로드
					 try {
						 mf.transferTo(new File(path+sysFileName));

					 }catch (IOException e) {
						 e.printStackTrace();
					 }
				 }
			 }
		 }

	}



	public ModelAndView execute(/*Laundryhandle lh,*/int i) {
		switch(i){
		case 1:
			shopListSelect();
			break;
		case 2:
			coinShopMm();
			break;
		case 3:
			coinShopDetail();
			break;
		case 4:
			laundryDelete();
			break;
		case 5:
			laundryUpdatePop();
			break;
		case 6:
			possibleItemUpdate();
			break;
		case 7:
			shopDetail();
			break;
		case 9:
			LaundryAddPop();
			break;
		case 10:
			possibleItem();
			break;
		case 11:
			laundryDelete();
			break;
		case 12:
			laundryMm();
			break;
		case 13:
			laundryCheck();
			break;
		case 14:
			laundryAccept();
			break;
		case 15:
			laundryManage();
			break;
		case 16:
			laundryUpdate();
			break;
		case 18:
			coinShopUpdate();
			break;
		case 19:
			shopdelete();
			break;
		case 20:
			SearchPlace();
			break;
		case 21:
			acceptshopdelete();
			break;
		case 22:
			ShopSearch();
			break;
		case 23:
			coinShopList();
			break;


			//광영 매장찾기
			/*     case 23:
          LaundryFind();
          break;  
			 */



			/*   case 12:
         laundryMm();break;*/
		default:
			break;
		}
		return mav;
	}

	private void ShopSearch() {

	      mav = new ModelAndView();   
	      String view=null;
	      view="ShopDetail";

	      System.out.println("여기옴");      

	      String NLCODE=req.getParameter("nl_code");

	      System.out.println(NLCODE);
	      System.out.println("여기옴1");
	      N_laundry llist=lDao.shopListSearch(NLCODE);   
	      System.out.println("여기옴2");
	      //      int bnum=Integer.parseInt(req.getParameter("bnum"));
	      //      nl.setNl_code(nl_code);
	      String List = ShopHtml(llist);
	      String address[]=llist.getNl_address().split("/");
	      llist.setNl_address(address[1]);
	      String nInfo =bs.shopInfoHtml(llist);
	      mav.addObject("nInfo",nInfo);
	      mav.addObject("laundry",llist);
	      mav.addObject("LLIST",List);
	      mav.addObject("NLCODE",NLCODE);

	      Date dt = new Date();
	      SimpleDateFormat sdf = new SimpleDateFormat("k");  //k로하면 kk로하면 01안먹힘
	      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); 

	      String time = sdf.format(dt);
	      String now = sdf2.format(dt);
	      System.out.println(time);
	      System.out.println(now);
	      mav.addObject("timeover",time);
	      mav.addObject("now",now);
	      String id=session.getAttribute("p_id").toString();
	      List<Mileage> milist = lDao.getmileage(id);   //세션에서아이디값가져와야함

	       int kind = lDao.kindselect(id);
	      //총포인트 계산

	      List<Pointhistory> plist = lDao.getPoint(id);
	      
	       calculationP(plist,kind);
	       
	      
	      int mileage = getmileage(milist);
	      mav.addObject("mg",mileage);

	      mav.setViewName(view);

	      String timeList = showTimeHtml(llist); 
	      mav.addObject("time",timeList);

	      mav.setViewName(view);
	   }
	
	private void calculationP(List<Pointhistory> plist, int kind) {
        int pointplus = 0;
        int pointminus = 0;
        int pointtotal = 0;
        if(kind ==1){
           for(int i=0; i<plist.size(); i++){
              Pointhistory ph = plist.get(i);
              if(ph.getPh_history() == 0){
                 pointminus += ph.getPh_point();
              }else if(ph.getPh_history() == 1){
                 pointplus+=ph.getPh_point();
              }else if(ph.getPh_history() == 2){
                 pointminus += ph.getPh_point();
              }else if(ph.getPh_history() == 4){
                 pointplus += ph.getPh_point();
              }
              
           }
        }else if(kind ==2){
           for(int i=0; i<plist.size(); i++){
              Pointhistory ph = plist.get(i);
              if(ph.getPh_history() == 3){
                 pointminus += ph.getPh_point();
              }else if(ph.getPh_history() == 5){
                 pointplus+=ph.getPh_point();
              }
              
           }
        }
        pointtotal = pointplus - pointminus;
        mav.addObject("pointtotal", pointtotal);
        
     }
	private void coinShopDetail() {
		mav = new ModelAndView();
		String view = null;
		String cl_code=req.getParameter("cl_code");
		Coin co = new Coin();
		co = lDao.CoinDetailSelect(cl_code);
		String address[]=co.getCl_address().split("/");
		co.setCl_address(address[1]);
		if(co.getCl_name() != null){
			List<Image> imgList = null;
			imgList = lDao.ImageSelect(cl_code);
			String ldetailhtml =CoinDetailHtml(co,imgList);
			String nInfo=CoinInfoHtml(co);
			mav.addObject("nInfo",nInfo);
			mav.addObject("ShopDetail", ldetailhtml);
			mav.addObject("co",co);
			view = "CoinShopDetail";
		}else{
			view = "redirect:/coinShopMm";
		}
		mav.setViewName(view);
	}
	private int getmileage(List<Mileage> milist) {
		Mileage m = new Mileage();
		int total = 0;
		for(int i=0;i<milist.size();i++){
			m = milist.get(i);
			if(m.getMg_updown()==0){
				total+=m.getMg_mileage();
			}else{
				total-=m.getMg_mileage();
			}
		}
		return total;

	}


	private String showTimeHtml(N_laundry llist) {
		StringBuilder sb = new StringBuilder();
		N_laundry n = new N_laundry();
		int g=0;
		n = llist;
		int o=Integer.parseInt(n.getNl_open());
		int c=Integer.parseInt(n.getNl_close());
		int size=c-o;
		if(n.getNl_timelap()==0){
			int lap=1;  //1시간
			int i=0;
			n = llist;
			sb.append("<option value='"+o+"'>"+n.getNl_open()+":00"+"</option>");
			for (i =0; i <size ; i++) {
				sb.append("<option value='"+(o+lap)+"'>"+(o+lap)+":00</option>");
				lap++;
			}
		}else{
			int i=0;
			n = llist;

			for (i =0; i <size ; i++) {
				sb.append("<option value='"+i+"'>"+o+":00"+"</option>");
				sb.append("<option value='"+i+"'>"+o+":30"+"</option>");
				o++;
			}
			sb.append("<option value='"+i+"'>"+n.getNl_close()+":00"+"</option>");
		}
		return sb.toString();
	}
	private String ShopHtml(N_laundry llist) {
		StringBuilder sb=new StringBuilder();
		String state=null;
		if(llist.getNl_repair()==0){
			state="수선불가능";
		}else{
			state="수선가능";
		}
		sb.append("<table><tr><td>세탁소명:</td><td>"+llist.getNl_name()+"</td></tr>");
		sb.append("<tr><td>주소</td><td>"+llist.getNl_address()+"</td></tr>");
		sb.append("<tr><td>연락처</td><td>"+llist.getNl_phone()+"</td></tr>");  //주문리스트에서 수량x세탁취급코드
		sb.append("<tr><td>영업시간:</td><td>오픈"+llist.getNl_open()+"~ 마감"+llist.getNl_close()+"</td></tr>");
		sb.append("<tr><td>수선여부:</td><td>"+state+"</td></tr></table>");
		session.setAttribute("NLCODE", llist.getNl_code());
		return sb.toString();
	}

	///@


	private void possibleItemUpdate() {
	      mav = new ModelAndView();
	      N_laundry nl = new N_laundry();
	      laundryBean(nl);

	      String arr = req.getParameter("arr");//원래있던아이템 4배수
	      mav.addObject("arr", arr);
	      System.out.println("arr="+arr);
	      String[] array = arr.split(",");
	      System.out.println("원래있던 아이템="+array[0]);
	      session.setAttribute("array", array);

	      String arrinsert = req.getParameter("arrStrinsert");//추가된 아이템 3배수
	      System.out.println("arrinsert="+arrinsert);
	      mav.addObject("arrinsert", arrinsert);
	      String[] arrayinsert = arrinsert.split(",");
	      System.out.println("추가된 아이템="+arrayinsert[0]);
	      session.setAttribute("arrayinsert", arrayinsert);

	      String handleList = handleListUpdate(array,arrayinsert);
	      mav.addObject("handleList", handleList);
	      req.setAttribute("handleList", handleList);

	      String arrdelete = req.getParameter("arrStrdelete");//삭제할 아이템 4배수
	      System.out.println("arrdelete="+arrdelete);
	      if(arrdelete !=null || arrdelete ==""){
	         mav.addObject("arrdelete", arrdelete);
	         String[] arraydelete = arrdelete.split(",");
	         System.out.println("삭제할 아이템="+arraydelete[0]);
	         session.setAttribute("arraydelete", arraydelete);
	      }
	      
	      System.out.println("여기 들어왔다.");
	      mav.setViewName("forward:./laundryUpdate");
	   }
	private String handleListUpdate(String[] array, String[] arrayinsert) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><td>종류</td><td>품목</td><td>가격</td></tr>");
		for(int i=0; i<array.length; i++){
			if((i%4)==0){
				sb.append("<tr><td>"+array[i]+"</td>");
			}else if((i%4)==1){
				sb.append("<td>"+array[i]+"</td>");
			}else if((i%4)==2){
				sb.append("<td>"+array[i]+"</td></tr>");
			}
		}
		for(int i=0; i<arrayinsert.length; i++){
			if((i%3)==0){
				sb.append("<tr><td>"+arrayinsert[i]+"</td>");
			}else if((i%3)==1){
				sb.append("<td>"+arrayinsert[i]+"</td>");
			}else if((i%3)==2){
				sb.append("<td>"+arrayinsert[i]+"</td></tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void laundryUpdatePop() {
		mav = new ModelAndView();
		String nl_code = session.getAttribute("nl_code").toString();
		System.out.println("laundryUpdatePop nl_code="+nl_code);
		String view = null;
		List<Laundryhandle> lhList = null;
		N_laundry nl = new N_laundry();
		laundryBean(nl);
		if(session.getAttribute("array")==null){
			System.out.println("notsession");
			lhList = lDao.HandleSelect(nl_code);
			String lhListInput = lhListInput(lhList);
			mav.addObject("lhListInput", lhListInput);
			view = "LaundryUpdatePop";
		}else{
			System.out.println("session");
			int j = 1;
			String [] array = (String[]) session.getAttribute("array");//4배수
			String orghandleupdate = orghandleUpdate(array,j);
			mav.addObject("orghandleupdate",orghandleupdate);
			String [] arrayinsert = (String[]) session.getAttribute("arrayinsert");//3배수
			String newhandleupdate = newhandleUpdate(arrayinsert,j);
			mav.addObject("newhandleupdate", newhandleupdate);
			view = "LaundryUpdatePop";
		}
		mav.addObject("check", 2);
		mav.setViewName(view);
	}
	private String newhandleUpdate(String[] arrayinsert, int j2) {
	      StringBuilder sb = new StringBuilder();
	      for(int i=0; i<arrayinsert.length; i++){
	         if((i%3)==0){
	            j2++;
	            sb.append("<tr id='item"+j2+"'><td><input type='text' value='"+arrayinsert[i]+"' disabled class='iText'/></td>");
	         }else if((i%3)==1){
	            sb.append("<td><input type='text' value='"+arrayinsert[i]+"' disabled class='iText'/></td>");
	         }else if((i%3)==2){
	            sb.append("<td><input type='text' value='"+arrayinsert[i]+"' disabled class='iText'/></td>");
	            sb.append("<td><div class='divv'><a href='#' onclick='removeitem("+j2+")' class='button'>-</a></div></td></tr>");
	         }
	      }
	      mav.addObject("num", j2);
	      return sb.toString();
	   }
	private String orghandleUpdate(String[] array, int j) {
	      mav = new ModelAndView();
	      StringBuilder sb = new StringBuilder();

	      sb.append("<div id='original'>");
	      sb.append("<table id='orgitem'><tr><td>종류</td><td>품목</td><td>가격</td><td>삭제</td></tr>");
	      for(int i=0; i<array.length; i++){
	         if(i%4 == 0){
	            sb.append("<tr id='item"+j+"'><td><input type='text' value='"+array[i]+"' disabled class='iText'/></td>");
	         }else if(i%4 == 1){
	            sb.append("<td><input type='text' value='"+array[i]+"' disabled class='iText'/></td>");
	         }else if(i%4 ==2){
	            sb.append("<td><input type='text' value='"+array[i]+"' disabled class='iText'/>");
	         }else if(i%4 ==3){
	            sb.append("<input type='hidden' value='"+array[i]+"'/></td>");
	            sb.append("<td><div class='divv'><a href='#' onclick='removerealitem("+j+")' class='button'>-</a></div></td></tr>");
	            j++;
	         }
	      }
	      mav.addObject("num", j);
	      sb.append("</table>");
	      sb.append("</div>");
	      return sb.toString();
	   }
	private String lhListInput(List<Laundryhandle> lhList) {
	      mav = new ModelAndView();
	      StringBuilder sb = new StringBuilder();
	      int j = 1;
	      sb.append("<div id='original'>");
	      sb.append("<table id='orgitem'><tr><td>종류</td><td>품목</td><td>가격</td><td>삭제</td></tr>");
	      for(int i=0; i<lhList.size(); i++){
	         Laundryhandle lh = lhList.get(i);
	         sb.append("<tr id='item"+j+"'><td><input type='text' value='"+lh.getLh_kind()+"' disabled class='iText'/></td>");
	         sb.append("<td><input type='text' value='"+lh.getLh_laundry()+"' disabled class='iText'/></td>");
	         sb.append("<td><input type='text' value='"+lh.getLh_payment()+"' disabled class='iText'/>");
	         sb.append("<input type='hidden' value='"+lh.getLh_code()+"'/></td>");
	         sb.append("<td><div class='divv'><a href='#' onclick='removerealitem("+j+")' class='button'>-</a></div></td></tr>");
	         j++;
	      }
	      mav.addObject("num", j);
	      sb.append("</table>");
	      sb.append("</div>");
	      return sb.toString();
	   }
	private void laundryUpdate() {
		if(session.getAttribute("nl_code") ==null){
			session.setAttribute("nl_code", req.getParameter("nl_code"));
		}
		String nl_code =session.getAttribute("nl_code").toString();
		mav = new ModelAndView();
		String view = null;
		N_laundry nl = new N_laundry();
		List<Image> imgList = null;
		List<Laundryhandle> lhList = null;
		nl = lDao.LaundryDetailSelect(nl_code);
		if(nl.getNl_name() != null){
			laundryBean(nl);

			imgList = lDao.ImageSelect(nl_code);
			String imglisence = imgLisence(imgList);
			mav.addObject("imglisence", imglisence);
			String imgHtml = imgListUpdate(imgList);
			mav.addObject("imgList", imgHtml);

			if(req.getAttribute("handleList")!=null){
				mav.addObject("handleList", req.getAttribute("handleList"));
			}else{
				lhList = lDao.HandleSelect(nl_code);
				String lhListHtml = lhListHtml(lhList);
				mav.addObject("lhListHtml", lhListHtml);
			}

			view = "LaundryUpdate";

		}else{
			System.out.println("검색실패");
			view = "forward:/laundryMm";
		}
		mav.setViewName(view);
	}
	private String lhListHtml(List<Laundryhandle> lhList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><td>종류</td><td>품목</td><td>가격</td></tr>");
		for(int i=0; i<lhList.size(); i++){
			Laundryhandle lh = lhList.get(i);
			sb.append("<tr><td>"+lh.getLh_kind()+"</td>");
			sb.append("<td>"+lh.getLh_laundry()+"</td>");
			sb.append("<td>"+lh.getLh_payment()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private String imgListUpdate(List<Image> imgList) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<imgList.size(); i++){
			Image img=imgList.get(i);
			if(img.getImg_categori() == 0){
				sb.append("<p><input type='hidden' name='img_name"+i+"' value='"+img.getImg_code()+"'/>");
				sb.append("<img src='resources/upload/"+img.getImg_name()+"' width='150' height='150'/>");
				sb.append("<a href='#this' class='btn' name='delete'>삭제</a></p>");
			}
		}
		return sb.toString();
	}
	private void possibleItem() {
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		laundryBean(nl);
		String kind = req.getParameter("kind");
		mav.addObject("kind", kind);
		String arr = null;
		String[] array = null;
		arr = req.getParameter("arr");
		System.out.println("arr"+arr);
		array = arr.split(",");
		session.setAttribute("array", array);
		mav.addObject("arr", arr);
		String handleList = handleListHtml(array);
		mav.addObject("handleList", handleList);
		mav.setViewName("LaundryAdd");

	}
	private String imgLisence(List<Image> imgList) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<imgList.size(); i++){
			Image img=imgList.get(i);
			if(img.getImg_categori() ==1)
				sb.append("사업자등록증 <br/> <img src='resources/upload/"+img.getImg_name()+"' width='150' height='150'/>");
		}
		return sb.toString();
	}
	private String handleListHtml(String[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><td>종류</td><td>품목</td><td>가격</td></tr>");
		for(int i=0; i<array.length; i++){
			if((i%3)==0){
				sb.append("<tr><td>"+array[i]+"</td>");
			}else if((i%3)==1){
				sb.append("<td>"+array[i]+"</td>");
			}else if((i%3)==2){
				sb.append("<td>"+array[i]+"</td></tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void laundryBean(N_laundry nl) {
		mav = new ModelAndView();
		LinkedHashMap<String,Object> nlMap = new LinkedHashMap<String,Object>();
		if(nl.getNl_name()==null){
			nlMap.put("nl_code", req.getParameter("nl_code"));
			nlMap.put("nl_name", req.getParameter("nl_name"));
			nlMap.put("post", req.getParameter("post"));
			nlMap.put("addr1", req.getParameter("addr1"));
			nlMap.put("addr2", req.getParameter("addr2"));
			nlMap.put("first", req.getParameter("first"));
			System.out.println("first="+req.getParameter("first"));
			nlMap.put("second", req.getParameter("second"));
			nlMap.put("third", req.getParameter("third"));
			nlMap.put("nl_open", req.getParameter("nl_open"));
			nlMap.put("nl_close", req.getParameter("nl_close"));
			nlMap.put("nl_repair", req.getParameter("nl_repair"));
			nlMap.put("nl_timelap", req.getParameter("nl_timelap"));
			nlMap.put("nl_qty", req.getParameter("nl_qty"));
			nlMap.put("nl_content", req.getParameter("nl_content"));
			System.out.println("nl=null=" + req.getParameter("nl_content"));
		}else{
			System.out.println("bean");
			nlMap.put("nl_code", nl.getNl_code());
			nlMap.put("nl_name", nl.getNl_name());
			String post = nl.getNl_address().substring(0, 5);
			String[] addr = nl.getNl_address().split("/");
			String addr1 = addr[0].substring(5);
			System.out.println(addr[0]);
			System.out.println(addr1);
			nlMap.put("nl_address", nl.getNl_address());
			nlMap.put("post", post);
			nlMap.put("addr1", addr1);
			nlMap.put("addr2", addr[1]);
			String[] phone = nl.getNl_phone().split("-");
			String first = phone[0].substring(1);
			nlMap.put("first", first);
			System.out.println("first"+phone[0]);
			nlMap.put("second", phone[1]);
			nlMap.put("third", phone[2]);
			nlMap.put("nl_open", nl.getNl_open());
			nlMap.put("nl_close", nl.getNl_close());
			//nlMap.put("nl_repair", nl.getNl_repair());
			//nlMap.put("nl_timelap",nl.getNl_timelap());
			if(nl.getNl_repair()==0){
				nlMap.put("nl_repair", "불가능");
			}else{
				nlMap.put("nl_repair", "가능");
			}
			if(nl.getNl_timelap()==0){
				nlMap.put("nl_timelap", "1시간");
			}else{
				nlMap.put("nl_timelap", "30분");
			}
			nlMap.put("nl_qty", nl.getNl_qty());
			nlMap.put("nl_content", nl.getNl_content());
			System.out.println("nl_content"+nl.getNl_content());
		}
		mav.addObject("nlMap", nlMap);
	}
	private void LaundryAddPop() {
		mav = new ModelAndView();
		String view = null;
		N_laundry nl = new N_laundry();
		laundryBean(nl);
		mav.addObject("kind", req.getParameter("kind"));
		if(session.getAttribute("array")==null){
			view = "LaundryAddPop";
		}else{
			String [] array = (String[]) session.getAttribute("array");
			String handleupdate = handleUpdate(array);
			mav.addObject("handleupdate",handleupdate);
			view = "LaundryAddPop";
		}
		mav.addObject("check", 1);
		mav.setViewName(view);
	}
	private String handleUpdate(String[] array) {
	      StringBuilder sb = new StringBuilder();
	      int j = 0;
	      for(int i=0; i<array.length; i++){
	         if((i%3)==0){
	            j++;
	            sb.append("<tr id='item"+j+"'><td><input type='text' value='"+array[i]+"' disabled class='iText'/></td>");
	         }else if((i%3)==1){
	            sb.append("<td><input type='text' value='"+array[i]+"' disabled class='iText'/></td>");
	         }else if((i%3)==2){
	            sb.append("<td><input type='text' value='"+array[i]+"' disabled class='iText'/></td>");
	            sb.append("<td><div class='divv'><a href='#' onclick='removeitem("+j+")' class='button'>-</a></div></td></tr>");
	         }
	      }
	      return sb.toString();
	   }
	private void acceptshopdelete() {
		String[] code=req.getParameterValues("chkbox");
		for(int i=0; i<code.length; i++){
			if(code[i].startsWith("c")||code[i].startsWith("C")){
				lDao.Coinacceptshopdelete(code[i]);
			}else{
				lDao.acceptshopdelete(code[i]);
			}
		}laundryCheck();
	}


	private void laundryAccept() {
		String[] code=req.getParameterValues("chkbox");
		for(int i=0; i<code.length; i++){
			if(code[i].startsWith("N")||code[i].startsWith("n")){
				lDao.PerMissionUpdate(code[i]);
			}else{
				lDao.CoinPerMissionUpdate(code[i]);
			}
			laundryCheck();
		}
	}  
	private String CListHtml(List<Coin> list, LinkedHashMap<String, Object> map) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(list.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>사업자아이디</td><td>전화번호</td></tr>");
			mb.append("</table><br/><br/><input type='button' name='shopdelete' id='shopdelete' value='매장삭제'/>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>사업자아이디</td><td>전화번호</td></tr>");
			for(int i=0; i<list.size();i++){   
				Coin co=list.get(i);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+co.getCl_code()+"'></td>");
				mb.append("<td><a href='./coinShopDetail?cl_code="+co.getCl_code()+"'>"+co.getCl_name()+"</td>");
				mb.append("<td>"+co.getCl_pid()+"</td>");
				mb.append("<td>"+co.getCl_phone()+"</td>");
			}   mb.append("</table><br/><br/><input type='submit' value='매장삭제'/>");
		}
		if(list.size()!=0){
			if(page<=1){
				mb.append("<a href='#'>[이전]</a>");
			}else{
				mb.append("<a href='./laundryManage?page="+(page-1)+"&shopchoice=coin&search="+map.get("search")+"'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./laundryManage?page="+a+"&shopchoice=coin"+map.get("search")+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a>");
			}else{
				mb.append("<a href='./laundryManage?page="+(page+1)+"&shopchoice=coin"+map.get("search")+"'>[다음]</a>");
			}
		}

		return mb.toString();
	}

	private void laundryManage() {
		mav=new ModelAndView();
		String lclist=null;
		int page=1;
		int limit=5;
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		int listcount=lDao.LaundryAdminCount();
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("search", req.getParameter("search"));
		if(req.getParameter("search")==null || req.getParameter("search").equals(" ")){
			if(req.getParameter("shopchoice")==null || req.getParameter("shopchoice").equals("laundry")){
				System.out.println("여기냐");
				System.out.println(map.get("startrow"));
				List<N_laundry> nl=lDao.LaundryAdminSelect(map);
				System.out.println("여기 안오지?");
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
				lclist=lListHtml(nl,map);
				mav.addObject("lList",lclist);

				mav.addObject("kind",req.getParameter("choice"));
			}else{
				listcount=lDao.CoinAdminCount();
				List<Coin> list=lDao.CoinAdminSelect(map);
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

				lclist=CListHtml(list,map);
				mav.addObject("lList",lclist);
			}
		}else{
			if(req.getParameter("shopchoice").equals("laundry")){
				listcount=lDao.LaundryKindSelectCount(map);
				List<N_laundry> list=lDao.LaundryKindSelect(map);
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
				lclist=lListHtml(list,map);
				mav.addObject("lList",lclist);
			}else{
				listcount=lDao.CoinKindSelectCount(map);
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
				List<Coin> list=lDao.CoinKindSelect(map);
				lclist=CListHtml(list,map);
				mav.addObject("lList",lclist);
			}
		}
		mav.setViewName("LaundryManage");
	}

	private void laundryCheck() {
		mav = new ModelAndView();
		int page=1;
		int limit=5;
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		int listcount=lDao.laundryCheckCount();
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Integer> map=new LinkedHashMap<String,Integer>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		if(req.getParameter("choice")==null || req.getParameter("choice").equals("laundry")||req.getParameter("choice").equals("all")){
			List<N_laundry> nl=lDao.laundryCheck(map);
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
			req.setAttribute("boardlist", nl);
			String nlList=shopCheckHtml(nl);
			mav.addObject("list",nlList);
			mav.addObject("kind",req.getParameter("choice"));
		}else{
			listcount=lDao.CoinShopCheckCount();
			List<Coin> co=lDao.CoinShopCheck(map);
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
			req.setAttribute("boardlist", co);
			String clList=CoinshopCheckHtml(co);
			mav.addObject("kind",req.getParameter("choice"));
			mav.addObject("list",clList);
		}
		mav.setViewName("LaundryCheck");
	}  

	private String CoinshopCheckHtml(List<Coin> co) {
		System.out.println(co);
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(co.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>매장위치</td><td>신청일</td></tr>");
			mb.append("</table><a href='#' class='btn' name='laundryAccept' id='laundryAccept'>매장승인</a>");
			mb.append("<a href='#'  id='acceptshopdelete ' class='btn'  onclick='acceptshopdelete()'>승인거절</a>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>매장위치</td><td>신청일</td></tr>");
			for(int i=0; i<co.size();i++){   
				Coin cl=co.get(i);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+cl.getCl_code()+"'></td>");
				mb.append("<td><a href='./coinShopDetail?cl_code="+cl.getCl_code()+"'>"+cl.getCl_name()+"</a></td>");
				mb.append("<td>"+cl.getCl_address()+"</td>");
				mb.append("<td>"+cl.getCl_date()+"</td>");
			}mb.append("</table><a href='#' class='btn' name='laundryAccept' id='laundryAccept' onclick='laundryAccept()'>매장승인</a>");
			mb.append("<a href='#'  id='acceptshopdelete ' class='btn'  '>승인거절</a>");
		}
		if(co.size()!=0){
			if(page<=1){
				mb.append("<a href='#' style='margin left=40%;'>[이전]</a>");
			}else{
				mb.append("<a href='./laundryCheck?page="+(page-1)+"&choice=coin' style='margin left=40%;'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./laundryCheck?page="+a+"&choice=coin'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a>");
			}else{
				mb.append("<a href='./laundryCheck?page="+(page+1)+"&choice=coin'>[다음]</a>");
			}}
		return mb.toString();

	}
	private String shopCheckHtml(List<N_laundry> nl) {
		StringBuilder mb=new StringBuilder();
		int page=(int) req.getAttribute("page");
		int maxpage=(int) req.getAttribute("maxpage");
		int startpage=(int) req.getAttribute("startpage");
		int endpage=(int) req.getAttribute("endpage");
		if(nl.size()==0){
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>매장위치</td><td>신청일</td></tr>");
			mb.append("</table><a href='#' class='btn'  name='laundryAccept' id='laundryAccept'>매장승인</a>");
			mb.append("<a href='#'  id='acceptshopdelete ' class='btn'  onclick='acceptshopdelete()'>승인거절</a>");
		}else{
			mb.append("<table>");
			mb.append("<tr><td><input type='checkbox' id='checkAll'></td><td>매장이름</td><td>매장위치</td><td>신청일</td></tr>");
			for(int i=0; i<nl.size();i++){   
				N_laundry nlshop=nl.get(i);
				mb.append("<tr><td><input type='checkbox' name='chkbox' id='chkbox' value='"+nlshop.getNl_code()+"'></td>");
				mb.append("<td><a href='./coinShopDetail?nl_code="+nlshop.getNl_code()+"'>"+nlshop.getNl_name()+"</a></td>");
				mb.append("<td>"+nlshop.getNl_address()+"</td>");
				mb.append("<td>"+nlshop.getNl_date()+"</td>");
			}mb.append("</table><a href='#' class='btn' name='laundryAccept' id='laundryAccept' onclick='laundryAccept()'>매장승인</a>");
			mb.append("<a href='#'  id='acceptshopdelete ' class='btn'>승인거절</a>");
		}
		if(nl.size()!=0){
			if(page<=1){
				mb.append("<a href='#'>[이전]</a>");
			}else{
				mb.append("<a href='./laundryCheck?page="+(page-1)+"&choice=laundry'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					mb.append("<a href='#'>["+a+"]</a>");
				}else{
					mb.append("<a href='./laundryCheck?page="+a+"&choice=laundry'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				mb.append("<a href='#'>[다음]</a></div></div>");
			}else{
				mb.append("<a href='./laundryCheck?page="+(page+1)+"&choice=laundry'>[다음]</a>");
			}}

		return mb.toString();
	}
	private String SelectListHtml(List<N_laundry> nl) {
		StringBuilder mb=new StringBuilder();
		for(int i=0; i<nl.size();i++){   
			N_laundry Nlau=nl.get(i);
			mb.append("일반세탁소<br/>");
			mb.append("<a href='./shopDetail?nl_code="+Nlau.getNl_code()+"'>세탁소명"+Nlau.getNl_name()+"</a><br/>");
			mb.append("주소"+Nlau.getNl_address()+"<br/>");
			mb.append("연락처"+Nlau.getNl_phone()+"<br/><hr/>");
		}
		return mb.toString();
	}
	private String CoinSelectListHtml(List<Coin> co) {
		StringBuilder mb=new StringBuilder();
		for(int i=0; i<co.size();i++){   
			Coin coin=co.get(i);
			mb.append("코인세탁소<br/>");
			mb.append("<a href='./coinShopDetail?cl_code="+coin.getCl_code()+"'>세탁소명"+coin.getCl_name()+"</a><br/>");
			mb.append("주소"+coin.getCl_address()+"<br/>");
			mb.append("연락처"+coin.getCl_phone()+"<br/><hr/>");
		}
		return mb.toString();
	}


	private void SearchPlace() {
		mav = new ModelAndView();
		String CoinSelectList=null;
		String SelectList=null;
		String keyword=req.getParameter("keyword");
		String kind=req.getParameter("kind");
		if(kind.equals("laundry")){
			List<N_laundry> nl=lDao.SearchPlace(keyword);
			if(nl.size()!=0){
				for(int i=0; i<nl.size(); i++){
					N_laundry shop=nl.get(i);
					String[] idx=shop.getNl_address().split("/");
					nl.get(i).setNl_address(idx[1]);
				}
				mav.addObject("shoplist",nl);
				SelectList=SelectListHtml(nl);

				mav.addObject("SelectList",SelectList);

			}  mav.addObject("address", keyword); 
			mav.setViewName("LaundryPlace");
		}else{
			List<Coin> co=lDao.SearchCoinPlace(keyword);
			if(co.size()!=0){
				for(int i=0; i<co.size(); i++){
					Coin cl=co.get(i);
					String[] idx=cl.getCl_address().split("/");
					co.get(i).setCl_address(idx[1]);
				}
				CoinSelectList=CoinSelectListHtml(co);
				mav.addObject("shoplist",co);
				mav.addObject("SelectList",CoinSelectList);
				mav.addObject("address", keyword);
				mav.setViewName("CoinshopFind");
			}
		}

	}


	private void shopListSelect() {
		mav = new ModelAndView();
		String id=(String)session.getAttribute("p_id");
		Member mb;
		mb=lDao.addressSelect(id);
		List<N_laundry>nl=lDao.LaundrymapSelect();
		for(int i=0; i<nl.size(); i++){
			N_laundry list = nl.get(i);
			String[] idx=list.getNl_address().split("/");
			nl.get(i).setNl_address(idx[1]);
		}
		String[] myaddress=mb.getM_address().split("/");
		String SelectList=SelectListHtml(nl);
		mav.addObject("SelectList",SelectList);
		mav.addObject("shoplist",nl);
		mav.addObject("address", myaddress[1]);
		System.out.println(myaddress[1]);
		mav.setViewName("LaundryFind");
	}
	private void coinShopList() {
		mav = new ModelAndView();
		String id=(String)session.getAttribute("p_id");
		Member mb;
		mb=lDao.addressSelect(id);
		List<Coin> co=lDao.CoinmapSelect();
		for(int i=0; i<co.size(); i++){
			Coin list = co.get(i);
			String[] idx=list.getCl_address().split("/");
			co.get(i).setCl_address(idx[1]);
		}
		String[] myaddress=mb.getM_address().split("/");
		String  CoinSelectList=CoinSelectListHtml(co);
		mav.addObject("coinshoplist",co);
		mav.addObject("SelectList",CoinSelectList);
		mav.addObject("address", myaddress[1]);
		mav.setViewName("CoinshopFind");
	}	




	private void shopdelete() {
		mav = new ModelAndView();
		laundryDelete();
		laundryManage();
	}

	private void laundryDelete() {
		mav = new ModelAndView();
		String view = null;
		String[] interest=req.getParameterValues("chkbox");
		System.out.println(interest[0]);
		if(interest[0].startsWith("N")||interest[0].startsWith("n")){
			for(int i=0; i<interest.length; i++){
				if(lDao.LaundryDelete(interest[i])!=0){
					String msg = "삭제되었습니다.";
					mav.addObject("msg", msg);
				}else{
					System.out.println("삭제실패");
				}
			}
			view = "forward:/laundryMm";
		}else if(interest[0].startsWith("c")||interest[0].startsWith("C")){
			for(int i=0; i<interest.length; i++){
				if(lDao.CoinShopDelete(interest[i])!=0){
					String msg = "삭제되었습니다.";
					mav.addObject("msg", msg);
				}else{
					System.out.println("삭제실패");
				}
			}
			view = "forward:/coinShopMm";
		}
		mav.setViewName(view);
	}
}







