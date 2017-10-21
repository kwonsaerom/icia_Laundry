package com.real.cu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Laundryhandle;
import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Orderlist;
import com.real.cu.bean.Person;
import com.real.cu.bean.Readypoint;
import com.real.cu.bean.Reservation;
import com.real.cu.dao.ReservationDao;

@Service 
public class ReservationService {

	private ModelAndView mav;	//modelanview excute사용시
	@Autowired
	private ReservationDao rDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;

	public ModelAndView execute(int cmd){
		switch(cmd){
		case 9:
			reservationList();
			break;
		case 10:
			selectState();
			break;
		case 11:
			laundryProcessing();
			break;
		case 12:
			ReservationShop();
			break;
		}
		return mav;
	}
	public ModelAndView execute(int cmd, N_laundry nl){
		switch(cmd){
		case 1:
			personReservation();
			break;
		case 2:
			shopDetailCart(nl);
			break;
		case 4:
			shopDetailCart(nl);	
			break;	
		case 6:
			coinPayComplete();
			break;
		case 10:
			givetakeSelect(nl);
			break;
		case 11:
			delete(nl);
			break;
		case 12:
			mgSubmit(nl);
			break;

		}
		return mav;
	}
	private void mgSubmit(N_laundry nl) {

		mav = new ModelAndView();
		int MG=nl.getNl_repair();
		System.out.println(MG);

		int Money = (int) session.getAttribute("ALL");
		int AllMoney = Money-MG;

		System.out.println(AllMoney+"마일리지??");
		mav.addObject("finalMoney", AllMoney);
		mav.setViewName("ShopDetailCart3");
	}
	private void delete(N_laundry nl) {

		mav = new ModelAndView();
		int elem=nl.getNl_qty();
		System.out.println("delete");
		System.out.println(elem);
		System.out.println("elem");

		int money=(int) session.getAttribute("ALL");

		if(elem==1){
			int w1=(int) session.getAttribute("w1");
			money=money-w1;
		}else if(elem==2){
			int w2=(int) session.getAttribute("w2");
			money=money-w2;
		}else if(elem==3){
			int w3=(int) session.getAttribute("w3");
			money=money-w3;
		}else if(elem==4){
			int w4=(int) session.getAttribute("w4");
			money=money-w4;
		}else if(elem==5){
			int w5=(int) session.getAttribute("w5");
			money=money-w5;
		}else if(elem==6){
			int w6=(int) session.getAttribute("w6");
			money=money-w6;
		}else if(elem==7){
			int w7=(int) session.getAttribute("w7");
			money=money-w7;
		}

		String NRCODE=session.getAttribute("NRCODE").toString();
		int ONUM = elem;

		Orderlist o = new Orderlist();
		o.setO_nrcode(NRCODE);
		o.setO_num(ONUM);

		rDao.deleteOrderList(o);

		session.setAttribute("ALL",money);

		mav.addObject("elem", elem);
		mav.addObject("money", "가격:"+money);
		System.out.println(money);
		System.out.println("안되잖아");
		mav.setViewName("ShopDetailCart2");

	}
	public ModelAndView execute(int cmd, N_laundry nl,String text,String date){
		switch(cmd){

		case 16:
			reservationAdN(nl,text,date);
			break;
		}
		return mav;
	}


	private void laundryProcessing() {
		mav = new ModelAndView();
		String arr[] = request.getParameterValues("check");
		int state =Integer.parseInt(request.getParameter("statebtn"));
		String nlcode = request.getParameter("nlcode");
		System.out.println("엔엘코드="+nlcode);
		LinkedHashMap<String,Object> fMap=new LinkedHashMap<String,Object>();
		for(int i=0;i<arr.length;i++){
			fMap.put("arr",arr[i]);
			fMap.put("state",state);
			rDao.laundryProcessing(fMap);
			System.out.println(fMap);
		}
		System.out.println("잘나옵니다");
		mav.setViewName("redirect:reservationList?nlcode="+nlcode+"");
	}
	private void selectState() {
		mav = new ModelAndView();
		int state = Integer.parseInt(request.getParameter("text"));
		String nlcode = request.getParameter("nlcode");
		mav.addObject("kind",state);
		String id = session.getAttribute("p_id").toString();
		int page=1;
		int limit=5;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int startrow=(page-1)*5+1;
		int endrow=startrow+limit-1;
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("startrow",startrow);
		map.put("endrow",endrow);
		map.put("nlcode",nlcode);
		map.put("state",state);
		System.out.println("정상");
		System.out.println(nlcode);
		int listcount=rDao.getselectcnt(map).size(); //세탁소코드로 그세탁소의 예약내역을 가져온다.
		System.out.println("선택후 리스트카운트"+listcount);
		System.out.println("스타트로우"+startrow);
		System.out.println("엔드로우"+endrow);
		List<Reservation> rlist=rDao.getselectreservation(map); //세탁소코드로 그세탁소의 예약내역을 가져온다.
		System.out.println("알리스트뽑아옴"+rlist);
		int maxpage=(int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/5+0.9))-1)*5+1;
		int endpage=startpage+5-1;
		if(endpage>maxpage){
			endpage=maxpage;
		}
		request.setAttribute("page",page);
		request.setAttribute("maxpage",maxpage);
		request.setAttribute("startpage",startpage);
		request.setAttribute("endpage",endpage);
		List<Person> plist = getperson(rlist);            //person리스트를 가져온다
		List<Member> mlist = getmember(plist);
		List<Orderlist> olist = getorder(rlist);
		List<Laundryhandle> hlist = gethandle(olist);
		String List = selectListHtml(rlist,plist,mlist,olist,hlist,nlcode,state);
		mav.addObject("nlcode",nlcode);
		mav.addObject("RLIST",List);
		mav.addObject("state",state);
		mav.setViewName("ReservationInfo");
	}
	private String selectListHtml(List<Reservation> rlist, List<Person> plist, List<Member> mlist,List<Orderlist> olist, List<Laundryhandle> hlist, String nlcode, int state1) {
		StringBuilder sb=new StringBuilder();
		Reservation r = new Reservation();
		Person p = new Person();
		Member m = new Member();
		Orderlist o = new Orderlist();
		Laundryhandle h = new Laundryhandle();
		int page=(int)request.getAttribute("page");
		int maxpage=(int)request.getAttribute("maxpage");
		int startpage=(int)request.getAttribute("startpage");
		int endpage=(int)request.getAttribute("endpage");
		sb.append("<table id='top' border='1'>");
		sb.append("<tr>");
		sb.append("<td><input type='checkbox' name='check' id='0'></td>");
		sb.append("<td>이름</td>");
		sb.append("<td>번호</td>");
		sb.append("<td>세탁물 종류</td>");
		sb.append("<td>주소</td>");
		sb.append("<td>진행현황</td>");
		sb.append("</tr>");
		for(int i=1; i-1<rlist.size(); i++){
			r=rlist.get(i-1);
			p=plist.get(i-1);
			m=mlist.get(i-1);
			sb.append("<tr><td><input type='checkbox' name='check' value='"+r.getNr_code()+"'>"+r.getNr_code()+"</td>");
			sb.append("<td>"+r.getNr_pid()+"</td>");
			sb.append("<td>"+p.getP_phone()+"</td><td>");
			for(int j=0;j<olist.size();j++){
				o=olist.get(j);
				h=hlist.get(j);
				if(r.getNr_code().equals(o.getO_nrcode())){
					sb.append(h.getLh_laundry()+o.getO_qty()+"개<br>");
				}
			}
			sb.append("</td><td>"+m.getM_address()+"</td>");
			if(r.getNr_state()==1){
				String state="수거전";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==2){
				String state="취소중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==3){
				String state="세탁중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==4){
				String state="배달중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==5){
				String state="완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==6){
				String state="취소완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==7){
				String state="지급완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==8){
				String state="승인취소";
				sb.append("<td>"+state+"</td></tr>");
			}
		}
		sb.append("</table>");
		if(rlist.size()!=0){
			if(page<=1){
				sb.append("<a href='#' style='margin-left:40%';>[이전]</a>");
			}else{
				sb.append("<a href='./selectState?page="+(page-1)+"&nlcode="+nlcode+"&text="+state1+"style='margin-left:40%';'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./selectState?page="+a+"&nlcode="+nlcode+"&text="+state1+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./selectState?page="+(page+1)+"&nlcode="+nlcode+"&text="+state1+"'>[다음]</a>");
			}
		}
		return sb.toString();
	}
	private List<Laundryhandle> gethandle(List<Orderlist> olist) {
		Orderlist o = new Orderlist();
		List<Laundryhandle> hlist = new ArrayList<Laundryhandle>();
		for(int i=0;i<olist.size();i++){
			o = olist.get(i);
			hlist.addAll(rDao.handle(o.getO_lhcode()));
		}
		return hlist;
	}
	private List<Reservation> getreservationcode(List<N_laundry> llist) {
		N_laundry l = new N_laundry();
		List<Reservation> rlist = new ArrayList<Reservation>();
		for(int i=0;i<llist.size();i++){
			l = llist.get(i);
			rlist.addAll(rDao.reservationList(l.getNl_code()));
		}
		return rlist;
	}

	private List<Orderlist> getorder(List<Reservation> rlist) {
		Reservation r = new Reservation();
		List<Orderlist> olist = new ArrayList<Orderlist>();
		for(int i=0;i<rlist.size();i++){
			r = rlist.get(i);
			olist.addAll(rDao.getordercode(r.getNr_code()));
		}
		return olist;
	}

	private List<Member> getmember(List<Person> plist) {
		Person p = new Person();
		List<Member> mlist = new ArrayList<Member>();
		for(int i=0;i<plist.size();i++){
			p = plist.get(i);
			mlist.addAll(rDao.getMember(p.getP_id()));
		}
		return mlist;
	}

	private List<Person> getperson(List<Reservation> rlist) {
		Reservation r = new Reservation();
		List<Person> plist = new ArrayList<Person>();
		for(int i=0;i<rlist.size();i++){
			r = rlist.get(i);
			plist.addAll(rDao.getPerson(r.getNr_pid()));
		}
		return plist;
	}

	private String getlist(List<N_laundry> llist) {
		N_laundry nl = new N_laundry();
		String code = null;
		for(int i=0;i<llist.size();i++){
			nl=llist.get(i);
			code = nl.getNl_code();
		}
		return code ;
	}

	private String reservaionListHtml(List<Reservation> rlist, List<Person> plist, List<Member> mlist, List<Orderlist> olist, List<Laundryhandle> hlist, String nlcode) {
		StringBuilder sb=new StringBuilder();
		Reservation r = new Reservation();
		Person p = new Person();
		Member m = new Member();
		Orderlist o = new Orderlist();
		Laundryhandle h = new Laundryhandle();
		int page=(int)request.getAttribute("page");
		int maxpage=(int)request.getAttribute("maxpage");
		int startpage=(int)request.getAttribute("startpage");
		int endpage=(int)request.getAttribute("endpage");
		sb.append("<table id='top' border='1'>");
		sb.append("<tr>");
		sb.append("<td><input type='checkbox' name='check' id='0'></td>");
		sb.append("<td>이름</td>");
		sb.append("<td>번호</td>");
		sb.append("<td>세탁물 종류</td>");
		sb.append("<td>주소</td>");
		sb.append("<td>진행현황</td>");
		sb.append("</tr>");
		for(int i=1; i-1<rlist.size(); i++){
			r=rlist.get(i-1);
			p=plist.get(i-1);
			m=mlist.get(i-1);
			sb.append("<tr><td><input type='checkbox' name='check' value='"+r.getNr_code()+"'>"+r.getNr_code()+"</td>");
			sb.append("<td>"+r.getNr_pid()+"</td>");
			sb.append("<td>"+p.getP_phone()+"</td><td>");
			for(int j=0;j<olist.size();j++){
				o=olist.get(j);
				h=hlist.get(j);
				if(r.getNr_code().equals(o.getO_nrcode())){
					sb.append(h.getLh_laundry()+o.getO_qty()+"개<br>");
				}
			}
			sb.append("</td><td>"+m.getM_address()+"</td>");
			if(r.getNr_state()==1){
				String state="수거전";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==2){
				String state="취소중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==3){
				String state="세탁중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==4){
				String state="배달중";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==5){
				String state="완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==6){
				String state="취소완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==7){
				String state="지급완료";
				sb.append("<td>"+state+"</td></tr>");
			}else if(r.getNr_state()==8){
				String state="승인취소";
				sb.append("<td>"+state+"</td></tr>");
			}
		}
		sb.append("</table>");
		if(rlist.size()!=0){
			if(page<=1){
				sb.append("<a href='#'style='margin-left:40%';>[이전]</a>");
			}else{
				sb.append("<a href='./reservationList?page="+(page-1)+"&nlcode="+nlcode+"style='margin-left:40%';'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./reservationList?page="+a+"&nlcode="+nlcode+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./reservationList?page="+(page+1)+"&nlcode="+nlcode+"'>[다음]</a>");
			}
		}
		return sb.toString();
	}
	private void givetakeSelect(N_laundry nl) {

		mav = new ModelAndView();

		String NLCODE=session.getAttribute("NLCODE").toString();
		List<N_laundry> tList = rDao.searchTime(NLCODE);
		String timeList = showTimeHtml(tList); 
		mav.addObject("time",timeList);
		//////	포인트결제 띄우기
		String LH_LAUNDRY=nl.getNl_content();
		int QTY=nl.getNl_qty();
		int NUM=nl.getNl_repair();

		LinkedHashMap<String, String> lMap=new LinkedHashMap<String,String>();
		lMap.put("NLCODE", NLCODE);
		lMap.put("THING", LH_LAUNDRY);

		List<Laundryhandle> lList = rDao.searchMoney(lMap);
		Laundryhandle h = new Laundryhandle();
		h=lList.get(0);

		int w=h.getLh_payment()*QTY;

		if(NUM==1){
			//	ArrayList<Integer> alist = new ArrayList<Integer>();
			//	request.setAttribute("alist",alist);
			int ALL=0;
			ALL+=w;
			mav.addObject("w1",w);
			mav.addObject("money",ALL);
			session.setAttribute("ALL",ALL);
			session.setAttribute("w1", w);
		}else{
			int ALL=(int) session.getAttribute("ALL");
			if(NUM==2){
				ALL+=w;
				mav.addObject("w2",w);
				session.setAttribute("w2", w);
			}else if(NUM==3){
				ALL+=w;
				mav.addObject("w3",w);
				session.setAttribute("w3", w);
			}else if(NUM==4){
				mav.addObject("w4",w);
				session.setAttribute("w4", w);
				ALL+=w;
			}else if(NUM==5){
				mav.addObject("w5",w);
				session.setAttribute("w5", w);
				ALL+=w;
			}
			session.setAttribute("ALL",ALL);

			mav.addObject("money",ALL);
		}

		//	ArrayList<Integer> alist= (ArrayList<Integer>) request.getAttribute("alist");
		//	alist.add(w);
		//	int aw=alist.get(NUM-1);
		//	System.out.println(aw);
		System.out.println("리스트정보확인2222");
		mav.setViewName("ShopDetailCart2");
	}

	private void reservationAdN(N_laundry nl, String text, String date) {
		mav = new ModelAndView();
		List<Reservation> rlist=rDao.reservationAdN2(); //세탁소코드로 그세탁소의 예약내역을 가져온다.
		List<N_laundry> llist= getlaundry(rlist);
		if(text==null && date==null){
			String adrList = adrHtml(llist,rlist);
			mav.addObject("adrList",adrList);
		}else{ 
			String adrList = adrHtml2(llist,rlist,text,date);
			mav.addObject("adrList",adrList);
		}
		mav.setViewName("laundryReservAd");
	}

	private String adrHtml2(List<N_laundry> llist, List<Reservation> rlist, String text,String date) {
		StringBuilder sb = new StringBuilder();
		N_laundry n = new N_laundry();
		Reservation r = new Reservation();
		sb.append("<table border='1'><tr><td>세탁소명</td><td>세탁소주소</td><td>세탁소연락처</td><td>사업자아이디</td><td>예약코드</td><td>예약손님아이디</td><td>예약일자</td><td>예약현황</td></tr>");
		for(int i=0; i<rlist.size(); i++){
			n=llist.get(i);
			r=rlist.get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = r.getNr_date();
			String today = df.format(date1);
			if(n.getNl_code().equals(r.getNr_nlcode())){
				if(n.getNl_name().equals(text) || today.equals(date)){
					sb.append("<tr><td>"+n.getNl_name()+"</td>");        
					sb.append("<td>"+n.getNl_address()+"</td>");
					sb.append("<td>"+n.getNl_phone()+"</td>");
					sb.append("<td>"+n.getNl_pid()+"</td>");
					sb.append("<td>"+r.getNr_code()+"</td>");
					sb.append("<td>"+r.getNr_pid()+"</td>");
					sb.append("<td>"+today+"</td>");
					sb.append("<td>"+r.getNr_state()+"</td></tr>");
				}
			}
		}
		sb.append("</table>");
		return sb.toString();
	}

	private List<N_laundry> getlaundry(List<Reservation> rlist) {
		Reservation r = new Reservation();
		List<N_laundry> llist = new ArrayList<N_laundry>();
		for(int i=0;i<rlist.size();i++){
			r = rlist.get(i);
			llist.addAll(rDao.getcode(r.getNr_nlcode()));
		}
		return llist;
	}

	private String adrHtml(List<N_laundry> llist, List<Reservation> rlist) {
		StringBuilder sb = new StringBuilder();
		N_laundry n = new N_laundry();
		Reservation r = new Reservation();
		sb.append("<table border='1'><tr><td><b>세탁소명</b></td><td><b>세탁소주소</b></td><td><b>세탁소연락처</b></td><td><b>사업자아이디</b></td><td><b>예약코드</b></td><td><b>예약손님아이디</b></td><td><b>예약일자</b></td><td><b>예약현황</b></td></tr>");
		for(int i=0; i<rlist.size(); i++){
			n=llist.get(i);
			r=rlist.get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = r.getNr_date();
			String today = df.format(date1);
			if(n.getNl_code().equals(r.getNr_nlcode())){
				sb.append("<tr><td>"+n.getNl_name()+"</td>");        
				sb.append("<td>"+n.getNl_address()+"</td>");
				sb.append("<td>"+n.getNl_phone()+"</td>");
				sb.append("<td>"+n.getNl_pid()+"</td>");
				sb.append("<td>"+r.getNr_code()+"</td>");
				sb.append("<td>"+r.getNr_pid()+"</td>");
				sb.append("<td>"+today+"</td>");
				if(r.getNr_state()==1){
					sb.append("<td>수거전</td></tr>");
				}else if(r.getNr_state()==2){
					sb.append("<td>취소중</td></tr>");
				}else if(r.getNr_state()==3){
					sb.append("<td>세탁중</td></tr>");
				}else if(r.getNr_state()==4){
					sb.append("<td>배달중</td></tr>");
				}else if(r.getNr_state()==5){
					sb.append("<td>최종완료</td></tr>");
				}
			}
		}
		sb.append("</table>");
		return sb.toString();
	}

	private void shopCart(N_laundry nl) {
		mav = new ModelAndView();	
		String NLCODE=nl.getNl_code();
		System.out.println(NLCODE);
		Laundryhandle h = new Laundryhandle();
		N_laundry n=rDao.laundryCart(NLCODE);
		List<Laundryhandle> hList= rDao.searchhandle(NLCODE);
	}

	private void coinPayComplete() {
		N_laundry n = new N_laundry();
		Reservation r = new Reservation();
		Readypoint rp=new Readypoint();
		String give=request.getParameter("gi");
		String take=request.getParameter("ta");
		String gSelect=request.getParameter("gs");
		String tSelect=request.getParameter("ts");
		String gdate=request.getParameter("gd");
		String tdate=request.getParameter("td");
		String mgPoint=request.getParameter("mg");  //마일리지
		int mg= Integer.parseInt(mgPoint);           //마일리지변환
		String nrcode = session.getAttribute("NRCODE").toString();
		String PID=(String)session.getAttribute("p_id");          //회원아이디 세션에서 가져왔다고 친다.
		Mileage m = new Mileage();
		m.setMg_pid(PID);
		m.setMg_mileage(mg);
		rDao.updateMileage(m);
		int pointAll = (int)session.getAttribute("ALL");            //마일리지 사용전 금액
		int point =pointAll-mg;
		String NLCODE = (String) session.getAttribute("NLCODE");            //세탁소코드
		n.setNl_code(NLCODE);
		r.setNr_code(rDao.getnrcode(NLCODE));
		n.setNl_pid(rDao.getbusiness(NLCODE));
		System.out.println(n.getNl_pid());
		//대기포인트
		rp.setRp_point(point);
		rp.setRp_downid(PID);   //회원
		rp.setRp_upid(n.getNl_pid()); //사업자
		rp.setRp_nrcode(r.getNr_code());
		//예약
		r.setNr_deliverydate(tdate);
		r.setNr_pickupdate(gdate);
		r.setNr_delivery(tSelect);
		r.setNr_pickup(gSelect);
		r.setNr_deliverytime(take);
		r.setNr_pickuptime(give);
		r.setNr_nlcode(NLCODE);
		r.setNr_pid(PID);
		r.setNr_code(nrcode);
		rDao.updateReservation(r);  //예약하기 update
		rDao.insertReadypoint(rp);
		mav.setViewName("PersonMain");
	}
	private void shopDetailCart(N_laundry nl){

		mav = new ModelAndView();   
		String view=null;
		System.out.println("shopdetailcart");
		//   String NLCODE=nl.getNl_code();
		Laundryhandle h = new Laundryhandle();

		String T_NAME=nl.getNl_content();
		System.out.println(T_NAME);
		System.out.println("$$$$$$$$$$$$$$$");

		if(T_NAME==null)
		{   
			String NLCODE=nl.getNl_code();
			session.setAttribute("NLCODE", nl.getNl_code());
			N_laundry n=rDao.laundryCart(NLCODE);
			List<Laundryhandle> hList= rDao.searchhandle(NLCODE);
			List<Laundryhandle> bList= rDao.searchhandle1(NLCODE);
			List<Laundryhandle> sList= rDao.searchhandle2(NLCODE);
			String li = shopCartHtml(hList,bList,sList);   
			List<N_laundry> tList = rDao.searchTime(NLCODE);
			Reservation r = new Reservation();
			String PID =session.getAttribute("p_id").toString();
			r.setNr_pid(PID);
			r.setNr_nlcode(NLCODE);
			String NULL=rDao.selectReservation(r);
			if(NULL!=null){
				//if(NULL.equals("NULL")){
				rDao.deleteReservation(r);
				rDao.createReservation(r);
				//}
			}else{
				rDao.createReservation(r);
			}
			String NRCODE = rDao.nrcode(PID);
			session.setAttribute("NRCODE", NRCODE);
			String timeList = showTimeHtml(tList); 
			mav.addObject("time",timeList);
			mav.addObject("li",li);
			mav.setViewName("ShopDetailCart");
		}else{
			String NLCODE=session.getAttribute("NLCODE").toString();
			T_NAME=request.getParameter("selectedValue");
			System.out.println(T_NAME);
			System.out.println(NLCODE);

			LinkedHashMap<String, String> fMap=new LinkedHashMap<String,String>();
			fMap.put("NLCODE", NLCODE);
			fMap.put("T_NAME", T_NAME);

			List<Laundryhandle> sList2= rDao.searchhandle3(fMap);

			String li2 = shopCartHtml2(sList2);
			System.out.println(li2);
			mav.addObject("li2",li2);
			mav.setViewName("ShopDetailCart");
		}       
	}

	private String showTimeHtml(List<N_laundry> tList) {
		StringBuilder sb = new StringBuilder();
		N_laundry n = new N_laundry();
		int g=0;
		n = tList.get(g);
		int o=Integer.parseInt(n.getNl_open());
		int c=Integer.parseInt(n.getNl_close());
		int size=c-o;
		if(n.getNl_timelap()==0){
			int lap=1;  //1시간

			int i=0;
			n = tList.get(i);
			sb.append("<option value='"+i+"'>"+n.getNl_open()+":00"+"</option>");

			for (i =0; i <size ; i++) {
				sb.append("<option value='"+i+"'>"+(o+lap)+":00</option>");
				lap++;
			}
		}else{
			int i=0;
			n = tList.get(i);
			for (i =0; i <size ; i++) {
				sb.append("<option value='"+i+"'>"+o+":00"+"</option>");
				sb.append("<option value='"+i+"'>"+o+":30"+"</option>");
				o++;
			}
			sb.append("<option value='"+i+"'>"+n.getNl_close()+":00"+"</option>");
		}
		return sb.toString();
	}

	private String shopCartHtml2(List<Laundryhandle> sList2) {
		StringBuilder sb2 = new StringBuilder();
		Laundryhandle h = new Laundryhandle();
		sb2.append("<option value='선택'>"+"소분류"+"</option>");
		for (int i = 0; i < sList2.size(); i++) {
			h = sList2.get(i);
			sb2.append("<option value='"+h.getLh_laundry()+"'>" + h.getLh_laundry() + "</option>");
		}
		return sb2.toString();
	}


	private String shopCartHtml(List<Laundryhandle> hList, List<Laundryhandle> bList, List<Laundryhandle> sList) {
		StringBuilder sb=new StringBuilder();		
		Laundryhandle h = new Laundryhandle();
		sb.append("<select name='p_t_name' id='p_t_name' onchange='tname();'>");
		sb.append("<option value='선택'>"+"대분류"+"</option>");
		for(int i=0; i<bList.size(); i++){
			h=bList.get(i);
			sb.append("<option value='"+h.getLh_kind()+"'>"+h.getLh_kind()+"</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}

	private void reservationList() {
		mav = new ModelAndView();
		String id=session.getAttribute("p_id").toString();
		int state = 9;
		int page=1;
		int limit=5;
		String nlcode = request.getParameter("nlcode");
		request.setAttribute("nlcode", nlcode);
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int startrow=(page-1)*5+1;
		int endrow=startrow+limit-1;
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("startrow",startrow);
		map.put("endrow",endrow);
		map.put("nlcode",nlcode);
		int listcount=rDao.getcnt(nlcode).size(); //세탁소코드로 그세탁소의 예약내역을 가져온다.
		System.out.println("선택전 리스트카운트"+listcount);
		List<Reservation> rlist=rDao.getreservation(map); //세탁소코드로 그세탁소의 예약내역을 가져온다.

		int maxpage=(int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/5+0.9))-1)*5+1;
		int endpage=startpage+5-1;
		if(endpage>maxpage){
			endpage=maxpage;
		}
		request.setAttribute("page",page);
		request.setAttribute("maxpage",maxpage);
		request.setAttribute("startpage",startpage);
		request.setAttribute("endpage",endpage);
		List<Person> plist = getperson(rlist);            //person리스트를 가져온다
		List<Member> mlist = getmember(plist);
		List<Orderlist> olist = getorder(rlist);
		System.out.println("오리스트"+olist.size());
		List<Laundryhandle> hlist = gethandle(olist);
		System.out.println("핸들리스트"+hlist.size());
		String List = reservaionListHtml(rlist,plist,mlist,olist,hlist,nlcode);
		mav.addObject("BRLIST",List);
		mav.setViewName("ReservationInfo");
	}
	private void personReservation() {
		mav = new ModelAndView();   
		String id=(String)session.getAttribute("p_id");
		String view=null;
		view="PersonReservation";
		int page=1;
		int limit=5;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("id",id);
		int listcount=rDao.ReservationCount(id);
		List<Reservation> rlist=rDao.personReservation(map);
		List<Orderlist> olist = getorder(rlist);
		int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
		//현재페이지에 보여줄 시작 페이지수
		int startpage=(((int)((double)page/5+0.9))-1)*5+1;
		//현재 페이지에 보여줄 마지막 페이지 수
		int endpage=startpage+5-1;
		if(endpage>maxpage) endpage=maxpage;
		request.setAttribute("page", page);//현재 페이지수
		request.setAttribute("maxpage", maxpage);//최대 페이지수
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		String List = personReservationHtml(rlist,id,olist);   
		mav.addObject("RLIST",List);
		mav.setViewName(view);
	}

	private String personReservationHtml(List<Reservation> rlist,String id, List<Orderlist> olist) {
		StringBuilder sb=new StringBuilder();      
		Reservation r = new Reservation();
		Orderlist o = new Orderlist();
		int page=(int) request.getAttribute("page");
		int maxpage=(int) request.getAttribute("maxpage");
		int startpage=(int) request.getAttribute("startpage");
		int endpage=(int) request.getAttribute("endpage");
		for(int i=0; i<rlist.size(); i++){
			r=rlist.get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = r.getNr_date();
			String today = df.format(date1);
			System.out.println("엔알코드"+r.getNr_code());
			//int pay = rDao.getreadypoint(r.getNr_code());
			sb.append("<table>");
			sb.append("<tr><td>예약일자"+today+"</td></tr>");
			sb.append("<tr><td>예약상품");
			//      sb.append("------------------------------------------------------------");
			for(int j=0;j<olist.size();j++){
				o=olist.get(j);
			sb.append(o.getO_lhcode()+" ");
			}
			sb.append("</tr></td>");
			sb.append("<tr><td>예약금액"+"</td></tr>");  //주문리스트에서 수량x세탁취급코드
			if(r.getNr_state()==1){
				sb.append("<td>수거전</td></tr>");
			}else if(r.getNr_state()==2){
				sb.append("<td>취소중</td></tr>");
			}else if(r.getNr_state()==3){
				sb.append("<td>세탁중</td></tr>");
			}else if(r.getNr_state()==4){
				sb.append("<td>배달중</td></tr>");
			}else if(r.getNr_state()==5){
				sb.append("<td>최종완료</td></tr>");
			}
			sb.append("</table>");

			int reCount = rDao.reviewCount(r);
			System.out.println(reCount+"reCount");

			if(reCount==0){
				sb.append("<a href='./ReviewInsert?re_nrcode="+r.getNr_code()+"&re_nlcode="+r.getNr_nlcode()+"&re_pid="+r.getNr_pid()+"'class='sub'>리뷰작성</a><br><br>");
			}else if(reCount==1){
				sb.append("<a href='./myActivity?re_nrcode="+r.getNr_code()+"&re_nlcode="+r.getNr_nlcode()+"&re_pid="+r.getNr_pid()+"'class='sub'>리뷰완료</a><br><br>");
			}

			//   sb.append("------------------------------------------------------------<br>");
		}   

		sb.append("<br><br>");

		if(rlist.size()!=0){
			if(page<=1){
				sb.append("<a href='#' style='margin-left:20%;'>[이전]</a>");
			}else{
				sb.append("<a href='./personReservation?page="+(page-1)+"' style='margin-left:20%;'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./personReservation?page="+a+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./personReservation?page="+(page+1)+"'>[다음]</a>");
			}
		}

		return sb.toString();
	}

	private void ReservationShop() {
		mav = new ModelAndView();
		String id = session.getAttribute("p_id").toString();
		int page=1;
		int limit=5;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int startrow=(page-1)*5+1;//읽기시작할 row번호
		int endrow=startrow+limit-1;//읽을 마지막 row번호
		LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("id",id);

		List<N_laundry> llist = rDao.getshop(map);          //로그인한 사장의 세탁소정보를 가져온다.
		int listcount=llist.size();
		int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림처리
		//현재페이지에 보여줄 시작 페이지수
		int startpage=(((int)((double)page/5+0.9))-1)*5+1;
		//현재 페이지에 보여줄 마지막 페이지 수
		int endpage=startpage+5-1;
		if(endpage>maxpage) endpage=maxpage;
		request.setAttribute("page", page);//현재 페이지수
		request.setAttribute("maxpage", maxpage);//최대 페이지수
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		String shopcare= MakeHtmlLaundry(llist);
		mav.addObject("shopcare",shopcare);
		mav.setViewName("ReservationShop");

	}

	private String MakeHtmlLaundry(List<N_laundry> llist) {
		StringBuilder sb=new StringBuilder();
		N_laundry n= new N_laundry();
		int page=(int) request.getAttribute("page");
		int maxpage=(int) request.getAttribute("maxpage");
		int startpage=(int) request.getAttribute("startpage");
		int endpage=(int) request.getAttribute("endpage");


		for(int i=0;i<llist.size();i++){
			n= llist.get(i);
			sb.append("<tr>");
			sb.append("<td>"+n.getNl_name()+"</td>");
			sb.append("<td>"+n.getNl_address()+"</td>");
			sb.append("<td><a href='./reservationList?nlcode="+n.getNl_code()+"' class='btn'>예약상세보기</a></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		if(llist.size()!=0){
			if(page<=1){
				sb.append("<a href='#' style='margin-left:40%';>[이전]</a>");
			}else{
				sb.append("<a href='./ReservationShop?page="+(page-1)+"style='margin-left:40%';'>[이전]</a>");
			}
			for(int a=startpage; a<=endpage; a++){
				if(a==page){
					sb.append("<a href='#'>["+a+"]</a>");
				}else{
					sb.append("<a href='./ReservationShop?page="+(page-1)+"'>["+a+"]</a>");
				}
			}
			if(page>=maxpage){
				sb.append("<a href='#'>[다음]</a>");
			}else{
				sb.append("<a href='./ReservationShop?page="+(page-1)+"'>[다음]</a>");
			}
		}
		return sb.toString();
	}
}
