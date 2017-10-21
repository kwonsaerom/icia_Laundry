package com.real.cu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.Notice;
import com.real.cu.bean.Pointhistory;
import com.real.cu.bean.Readypoint;
import com.real.cu.bean.Reservation;
import com.real.cu.bean.Return;
import com.real.cu.dao.PointDao;

@Service("pointservice")
public class PointService {
   private ModelAndView mav;
   
   @Autowired
   private HttpServletRequest req;
   @Autowired
   private HttpSession session;
   @Autowired
   private PointDao pdao;

   public ModelAndView execute(int i) {
      switch(i){
      case 1:
         myPoint();break;
      case 2:
         pointcharge();break;
      case 3:
         PointReturn();break;
      case 4:
         pointexchange();break;
      case 5:
         pointMm();break;
      case 6:
         pointRequest();break;
      case 7:
         pointAccept();break;
      case 8:
         pointMmSelect();break;
      case 9:
         pointAdmin();break;
      case 10:
         pointDelete();break;
      default:
         break;
      }
      return mav;
   }

   

   private void pointDelete() {
      mav = new ModelAndView();
      String view = null;
      String button = req.getParameter("button");
      System.out.println("button="+button);
      String arrStr = req.getParameter("arr");
      System.out.println("arrStr="+arrStr);
      String [] arr = arrStr.split(",");
      String nr_code = null;
      String rp_code = null;
      String member = null;
      String business = null;
      Pointhistory ph = new Pointhistory();
      Readypoint rp = new Readypoint();
      Reservation r = new Reservation();
      //0:nr_code(예약코드), 1:rp_code(대기코드), 2:멤버아이디, 3:사업자아이디
      if(button.equals("승인취소")){
         for(int i=0; i<arr.length; i++){
            if((i%4)==0){
               nr_code = arr[i];
            }else if((i%4)==1){
               rp_code = arr[i];
            }else if((i%4)==2){
               member = arr[i];
            }
         }
            //사업자회원 포인트 insert
            rp = pdao.readypointSelect(nr_code);
            ph.setPh_pid(rp.getRp_upid());
            ph.setPh_point(rp.getRp_point());
            ph.setPh_history(6);
            ph.setPh_process(nr_code);
            pdao.pointinsert(ph);
            //대기포인트 update
            rp.setRp_state(2);
            rp.setRp_code(rp_code);
            pdao.readypointUpdate(rp);
            //예약 update
            r.setNr_state(8);
            r.setNr_code(nr_code);
            pdao.reservationUpdate(r);
            //일반회원 포인트 update
            ph.setPh_pid(member);
            ph.setPh_process(nr_code);
            ph.setPh_history(6);
            pdao.pointUpdate(ph);
            
            view = "forward:/pointMmSelect?kind=최종";

      }else if(button.equals("승인후취소")){
         for(int i=0; i<arr.length; i++){
            if((i%4)==0){
               nr_code = arr[i];
            }else if((i%4)==1){
               rp_code = arr[i];
            }else if((i%4)==2){
               member = arr[i];
            }else if((i%4)==3){
               business = arr[i];
            }
         }
         //사업자회원 포인트 update
         ph.setPh_pid(business);
         ph.setPh_process(nr_code);
         ph.setPh_history(6);
         pdao.pointUpdate(ph);
         //대기포인트 update
         rp.setRp_code(rp_code);
         rp.setRp_state(2);
         pdao.readypointUpdate(rp);
         //예약 update
         r.setNr_code(nr_code);
         r.setNr_state(8);
         pdao.reservationUpdate(r);
         //일반회원 update
         ph.setPh_pid(member);
         ph.setPh_process(nr_code);
         ph.setPh_history(6);
         pdao.pointUpdate(ph);
         
         view = "forward:/pointMmSelect?kind=지급";
      }
      mav.setViewName(view);
   }

   private void pointAdmin() {
      mav = new ModelAndView();
      String view = null;
      String button = req.getParameter("button");
      System.out.println("button="+button);
      String arrStr = req.getParameter("arr");
      System.out.println("arrStr="+arrStr);
      String [] arr = arrStr.split(",");
      String nr_code = null;
      String rp_code = null;
      String member = null;
      String business = null;
      Pointhistory ph = new Pointhistory();
      Readypoint rp = new Readypoint();
      Reservation r = new Reservation();
      //0:nr_code(예약코드), 1:rp_code(대기코드), 2:멤버아이디, 3:사업자아이디
      if(button.equals("승인")){   
         for(int i=0; i<arr.length; i++){
            if((i%4)==0){
               nr_code = arr[i];
            }else if((i%4)==1){
               rp_code = arr[i];
               System.out.println("else if rp_code="+rp_code);
            }
         }
         //사업자회원 포인트 insert
         rp = pdao.readypointSelect(nr_code);
         ph.setPh_pid(rp.getRp_upid());
         ph.setPh_point(rp.getRp_point());
         ph.setPh_history(5);
         ph.setPh_process(nr_code);
         pdao.pointinsert(ph);
         //대기포인트 update
         rp.setRp_state(1);
         rp.setRp_code(rp_code);
         pdao.readypointUpdate(rp);
         //예약 update
         r.setNr_state(7);
         r.setNr_code(nr_code);
         pdao.reservationUpdate(r);
            
         view = "forward:/pointMmSelect?kind=최종";
      }else if(button.equals("다시승인")){
         for(int i=0; i<arr.length; i++){
            if((i%4)==0){
               nr_code = arr[i];
            }else if((i%4)==1){
               rp_code = arr[i];
            }else if((i%4)==2){
               member = arr[i];
            }else if((i%4)==3){
               business = arr[i];
            }
         }
         //사업자회원 포인트 update
         ph.setPh_pid(business);
         ph.setPh_process(nr_code);
         ph.setPh_history(5);
         pdao.pointUpdate(ph);
         //대기포인트 update
         rp.setRp_code(rp_code);
         rp.setRp_state(1);
         pdao.readypointUpdate(rp);
         //예약 update
         r.setNr_code(nr_code);
         r.setNr_state(7);
         pdao.reservationUpdate(r);
         //일반회원 update
         ph.setPh_pid(member);
         ph.setPh_process(nr_code);
         ph.setPh_history(1);
         pdao.pointUpdate(ph);
         
         view = "forward:/pointMmSelect?kind=승인취소";
      }
      mav.setViewName(view);
   }

   private void pointMmSelect() {
      mav = new ModelAndView();
      String kind = req.getParameter("kind");
      System.out.println("kind="+kind);
      //Reservation nr = new Reservation();
      //LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
      List<Reservation> nrlist = null;
      String sbplist = null;
      
      if(kind.equals("대기")){
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
         map.put("state", 1);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(1);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("취소")){
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
         map.put("state", 2);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(2);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("취소완료")){
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
         map.put("state", 6);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(6);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("진행")){
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
         map.put("i", 3);
         map.put("j", 4);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(6);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect2(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("최종")){
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
         map.put("state", 5);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(5);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("지급")){
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
         map.put("state", 7);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(7);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }else if(kind.equals("승인취소")){
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
         map.put("state", 8);
         //카운트할 테이블         
         int listcount=pdao.reservationCount(8);
         //셀렉트할 테이블
         nrlist = pdao.reservationSelect(map);
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
         //makehtml할 메소드
         sbplist = sbplistHtml(nrlist,kind);
      }
      mav.addObject("sbplist", sbplist);
      mav.addObject("kind", kind);
      mav.setViewName("StandbyPoint");
   }

   private String sbplistHtml(List<Reservation> nrlist, String kind) {
      StringBuilder sb = new StringBuilder();
      int page=(int) req.getAttribute("page");
      int maxpage=(int) req.getAttribute("maxpage");
      int startpage=(int) req.getAttribute("startpage");
      int endpage=(int) req.getAttribute("endpage");
      sb.append("<table><tr>");
      if(kind.equals("최종") || kind.equals("지급") || kind.equals("승인취소")){
         sb.append("<td>선택</td>");
      }
      sb.append("<td>예약번호</td><td>이름</td><td>세탁소</td><td>날짜</td><td>포인트</td><td>현황</td></tr>");
      sb.append("<tr>");
      if(kind.equals("최종") || kind.equals("지급") || kind.equals("승인취소")){
         sb.append("<td></td>");
      }
      sb.append("<td></td><td>(연락처)</td><td>(연락처)</td><td></td><td></td><td></td></tr>");
      for(int i=0; i<nrlist.size(); i++){
         Reservation nr = nrlist.get(i);
         Readypoint rp = new Readypoint();
         System.out.println("nr_code="+nr.getNr_code());
         rp = pdao.readypointSelect(nr.getNr_code());
         System.out.println("rp_downid="+rp.getRp_downid());
         String laundryname = pdao.laundryNameSelect(nr.getNr_nlcode());
         String mphone = pdao.personPhoneSelect(rp.getRp_downid());
         String bphone = pdao.personPhoneSelect(rp.getRp_upid());
         String mph_code = null;
         String bph_code = null;
         LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
         if(kind.equals("최종") || kind.equals("지급") || kind.equals("승인취소")){
            map.put("ph_process", nr.getNr_code());
            map.put("ph_pid", rp.getRp_downid());
            if(pdao.phcodeSelect(map)!= null){
               System.out.println("member="+map.get("ph_pid"));
               mph_code = pdao.phcodeSelect(map);
            }
            map.put("ph_pid", rp.getRp_upid());
            System.out.println("business="+map.get("ph_pid"));
            if(pdao.phcodeSelect(map) != null){
               bph_code = pdao.phcodeSelect(map);
            }
         }
         sb.append("<tr>");
         if(kind.equals("최종") || kind.equals("승인취소") || kind.equals("지급")){
            sb.append("<td><input type='checkbox' name='choice' value='"+nr.getNr_code()+","+rp.getRp_code()+","+mph_code+","+bph_code+"'/></td>");
         }
         SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
         Date date1 = nr.getNr_date();
         String today = df.format(date1);
         sb.append("<td>"+today+"-"+nr.getNr_code()+"</td>");
         sb.append("<td>"+nr.getNr_pid()+"</td>");
         sb.append("<td>"+laundryname+"</td>");
         sb.append("<td>"+today+"</td>");
         sb.append("<td>"+rp.getRp_point()+"</td>");
         if(nr.getNr_state() == 1){
            sb.append("<td>수거전</td></tr>");
         }else if(nr.getNr_state() == 2){
            sb.append("<td>취소중</td></tr>");
         }else if(nr.getNr_state() == 3){
            sb.append("<td>세탁중</td></tr>");
         }else if(nr.getNr_state() == 4){
            sb.append("<td>배달중</td></tr>");
         }else if(nr.getNr_state() == 5){
            sb.append("<td>완료</td></tr>");
         }else if(nr.getNr_state() == 6){
            sb.append("<td>취소완료</td></tr>");
         }else if(nr.getNr_state() == 7){
            sb.append("<td>지급완료</td></tr>");
         }else if(nr.getNr_state() == 8){
            sb.append("<td>승인취소</td></tr>");
         }
         sb.append("<tr>");
         if(kind.equals("최종") || kind.equals("지급") || kind.equals("승인취소")){
            sb.append("<td></td>");
         }
         sb.append("<td></td><td>"+mphone+"</td><td>"+bphone+"</td><td></td><td></td><td></td></tr>");
      }
      sb.append("</table>");
      
      if(page<=1){
          sb.append("<a href='#'>[이전]</a>");
       }else{
          sb.append("<a href='./pointMmSelect?page="+(page-1)+"&kind="+kind+"'>[이전]</a>");
       }
       for(int a=startpage; a<=endpage; a++){
          if(a==page){
             sb.append("<a href='#'>["+a+"]</a>");
          }else{
             sb.append("<a href='./pointMmSelect?page="+a+"&kind="+kind+"'>["+a+"]</a>");
          }
       }
       if(page>=maxpage){
          sb.append("<a href='#'>[다음]</a>");
       }else{
          sb.append("<a href='./pointMmSelect?page="+(page+1)+"&kind="+kind+"'>[다음]</a>");
       }
      return sb.toString();
   }

   private void pointAccept() {
      mav = new ModelAndView();
      String arrStr = req.getParameter("arr");
      String [] arr = arrStr.split(",");
      Pointhistory ph = new Pointhistory();
      for(int i=0; i<arr.length; i++){
         int cnt = pdao.returnUpdate(arr[i]);
         if(cnt!=0){
            Return r = pdao.returnCodeSelect(arr[i]);
            System.out.println("pr_pid="+r.getPr_pid());
            ph.setPh_pid(r.getPr_pid());
            ph.setPh_point(r.getPr_point());
            if(r.getPr_flag() == 0){
               ph.setPh_history(2);
            }else if(r.getPr_flag() == 1){
               ph.setPh_history(3);
            }
            System.out.println("rcode="+r.getPr_code());
            ph.setPh_process(r.getPr_code());
            pdao.pointinsert(ph);
         }else{
            System.out.println("update 실패");
         }
      }
      mav.setViewName("forward:/pointRequest?kind=전체&state=처리전");
      
   }

   private void pointRequest() {
      mav = new ModelAndView();
      String kind = req.getParameter("kind");
      String state = req.getParameter("state");
      System.out.println("kind="+kind);
      System.out.println("state="+state);
      List<Return> rlist = null;
      LinkedHashMap<String,Object> rmap = new LinkedHashMap<String,Object>();
      String returnlist = null;
      if(kind.equals("전체")){
         if(state.equals("처리전")){
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
            map.put("i", 0);
            //카운트할 테이블         
            int listcount=pdao.returnAllCount(0);
            //셀렉트할 테이블
            rlist = pdao.returnAllSelect(map);
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
         }else if(state.equals("처리완료")){
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
            map.put("i", 1);
            //카운트할 테이블         
            int listcount=pdao.returnAllCount(1);
            //셀렉트할 테이블
            rlist = pdao.returnAllSelect(map);
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
         }
      }else if(kind.equals("일반")){
         if(state.equals("처리전")){
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
            map.put("i", 1);
            map.put("state", 0);
            map.put("flag", 0);
            //카운트할 테이블         
            int listcount=pdao.returnFlagCount(map);
            //셀렉트할 테이블
            rlist = pdao.returnFlagSelect(map);
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
         }else if(state.equals("처리완료")){
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
            map.put("i", 1);
            map.put("state", 1);
            map.put("flag", 0);
            //카운트할 테이블         
            int listcount=pdao.returnFlagCount(map);
            //셀렉트할 테이블
            rlist = pdao.returnFlagSelect(map);
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
         }
      }else if(kind.equals("사업자")){
         if(state.equals("처리전")){
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
            map.put("i", 1);
            map.put("state", 0);
            map.put("flag", 1);
            //카운트할 테이블         
            int listcount=pdao.returnFlagCount(map);
            //셀렉트할 테이블
            rlist = pdao.returnFlagSelect(map);
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
         }else if(state.equals("처리완료")){
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
            map.put("i", 1);
            map.put("state", 1);
            map.put("flag", 1);
            //카운트할 테이블         
            int listcount=pdao.returnFlagCount(map);
            //셀렉트할 테이블
            rlist = pdao.returnFlagSelect(map);
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
         }
      }
      returnlist = returnList(rlist,kind,state);
      //System.out.println("returnlist="+returnlist);
      mav.addObject("returnlist", returnlist);
      mav.addObject("kind", kind);
      mav.addObject("state", state);
      mav.setViewName("PointManage");
   }

   private String returnList(List<Return> rlist,String kind, String state) {
      StringBuilder sb = new StringBuilder();
      int page=(int) req.getAttribute("page");
      int maxpage=(int) req.getAttribute("maxpage");
      int startpage=(int) req.getAttribute("startpage");
      int endpage=(int) req.getAttribute("endpage");
      sb.append("<table><tr><td>선택</td>");
      sb.append("<td>번호</td><td>분류</td><td>아이디</td><td>포인트</td><td>계좌번호</td><td>날짜</td><td>처리상태</td></tr>");
      for(int i=0; i<rlist.size(); i++){
         Return r = rlist.get(i);
         sb.append("<tr><td><input type='checkbox' name='choice' value='"+r.getPr_code()+"'/></td>");
         sb.append("<td>"+(i+1)+"</td>");
         if(r.getPr_flag() == 0){
            sb.append("<td>환불</td>");
         }else if(r.getPr_flag() == 1){
            sb.append("<td>환전</td>");
         }
         sb.append("<td>"+r.getPr_pid()+"</td>");
         sb.append("<td>"+r.getPr_point()+"</td>");
         sb.append("<td>"+r.getPr_banknum()+"</td>");
         sb.append("<td>"+r.getPr_date()+"</td>");
         if(r.getPr_state() == 0){
            sb.append("<td>처리전</td>");
         }else if(r.getPr_state() ==1){
            sb.append("<td>처리완료</td>");
         }
      }
      sb.append("</table>");
      if(page<=1){
          sb.append("<a href='#'>[이전]</a>");
       }else{
          sb.append("<a href='./pointRequest?page="+(page-1)+"&kind="+kind+"&state="+state+"'>[이전]</a>");
       }
       for(int a=startpage; a<=endpage; a++){
          if(a==page){
             sb.append("<a href='#'>["+a+"]</a>");
          }else{
             sb.append("<a href='./pointRequest?page="+a+"&kind="+kind+"&state="+state+"'>["+a+"]</a>");
          }
       }
       if(page>=maxpage){
          sb.append("<a href='#'>[다음]</a>");
       }else{
          sb.append("<a href='./pointRequest?page="+(page+1)+"&kind="+kind+"&state="+state+"'>[다음]</a>");
       }
      return sb.toString();
   }

   private void pointMm() {
      mav = new ModelAndView();
      String id = session.getAttribute("p_id").toString();   //======================================session변경
      int kind = pdao.kindselect(id);
      String type = req.getParameter("type");
      List<Pointhistory> realplist = null;
      //List<Return> realrlist = null;
      String pointList = null;
      
      List<Pointhistory> plist = pdao.pointselect(id);
      if(type.equals("전체내역")){
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
         //카운트할 테이블         
         int listcount=pdao.pointCount(id);
         //셀렉트할 테이블
         List<Pointhistory> plist1 = pdao.pointMapSelect(map);
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
         //makehtml할 메소드
         pointList = pointListHtml(plist1,kind,type);
      }else if(type.equals("수익내역")){
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
         //카운트할 테이블         
         int listcount=pdao.pointPlusCount(id);
         //셀렉트할 테이블
         realplist = pdao.pointMinusMapSelect(map);
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
         //makehtml할 메소드
         pointList = pointListHtml(realplist,kind,type);
      }else if(type.equals("환전내역")){
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
         //카운트할 테이블         
         int listcount=pdao.pointMinusCount(id);
         //셀렉트할 테이블
         realplist = pdao.pointPlusMapSelect(map);
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
         //makehtml할 메소드
         pointList = pointListHtml(realplist,kind,type);
      }
      
      calculationP(plist,kind);
      
      int page1=1;
     int limit1=5;
     if(req.getParameter("page")!=null){
        page1=Integer.parseInt(req.getParameter("page"));
     }
     int startrow1=(page1-1)*5+1;//읽기시작할 row번호
     int endrow1=startrow1+limit1-1;//읽을 마지막 row번호
     LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
     map.put("startrow", startrow1);
     map.put("endrow", endrow1);
     map.put("id", id);
     //카운트할 테이블         
     int listcount1=pdao.returnCount(id);
     //셀렉트할 테이블
     List<Return> rlist = pdao.returnselect(map);
     int maxpage1=(int)((double)listcount1/limit1+0.95);//0.95를 더해서 올림처리
     //현재페이지에 보여줄 시작 페이지수
     int startpage1=(((int)((double)page1/5+0.9))-1)*5+1;
     //현재 페이지에 보여줄 마지막 페이지 수
     int endpage1=startpage1+5-1;
     if(endpage1>maxpage1) endpage1=maxpage1;
     req.setAttribute("page", page1);//현재 페이지수
     req.setAttribute("maxpage", maxpage1);//최대 페이지수
     req.setAttribute("startpage", startpage1);
     req.setAttribute("endpage", endpage1);
     //makehtml할 메소드
      String returnList = returnListHtml(rlist);
      calculationR(rlist);
      
      mav.addObject("type", type);
      mav.addObject("pointList", pointList);
      mav.addObject("returnList", returnList);
      
      mav.setViewName("PointInfo");
      
   }

   private void calculationR(List<Return> rlist) {
      int returnplus = 0;
      
      for(int i=0; i<rlist.size(); i++){
         Return r = rlist.get(i);
         if(r.getPr_state() == 1){
            returnplus += r.getPr_point();
         }
      }
      
      mav.addObject("returnplus", returnplus);
   }

   private String returnListHtml(List<Return> rlist) {
      StringBuilder sb = new StringBuilder();
      int page=(int) req.getAttribute("page");
      int maxpage=(int) req.getAttribute("maxpage");
      int startpage=(int) req.getAttribute("startpage");
      int endpage=(int) req.getAttribute("endpage");
      sb.append("<table>");
      for(int i=0; i<rlist.size(); i++){
         Return r = rlist.get(i);
         if(r.getPr_state() == 0){
            sb.append("<tr><td>환전처리중</td><td>"+r.getPr_date()+"</td><td>"+r.getPr_point()+"</td></tr>");
         }else if(r.getPr_state() == 1){
            sb.append("<tr><td>환전완료</td><td>"+r.getPr_date()+"</td><td>"+r.getPr_point()+"</td></tr>");
         }
      }
      sb.append("</table>");
      if(page<=1){
          sb.append("<a href='#'>[이전]</a>");
       }else{
          sb.append("<a href='./pointMm?page="+(page-1)+"&type=전체내역'>[이전]</a>");
       }
       for(int a=startpage; a<=endpage; a++){
          if(a==page){
             sb.append("<a href='#'>["+a+"]</a>");
          }else{
             sb.append("<a href='./pointMm?page="+a+"&type=전체내역'>["+a+"]</a>");
          }
       }
       if(page>=maxpage){
          sb.append("<a href='#'>[다음]</a>");
       }else{
          sb.append("<a href='./pointMm?page="+(page+1)+"&type=전체내역'>[다음]</a>");
       }
      return sb.toString();
   }

   private void pointexchange() {
      mav = new ModelAndView();
      String view = null;
      String id = session.getAttribute("p_id").toString();   //====================================session으로 변경
      String kind = req.getParameter("person");
      Return pr = new Return();
      pr.setPr_pid(id);
      pr.setPr_point(Integer.parseInt(req.getParameter("point")));
      pr.setPr_name(req.getParameter("name"));
      String phone = "0"+req.getParameter("first")+"-"+req.getParameter("second")+"-"+req.getParameter("third");
      System.out.println("phone="+phone);
      pr.setPr_phone(phone);
      pr.setPr_bank(req.getParameter("bank"));
      pr.setPr_banknum(req.getParameter("banknum"));
         
      if(kind.equals("개인")){
         pdao.memberReturnInsert(pr);
         view = "forward:/myPoint?type=전체내역";
      }else if(kind.equals("사업자")){
         pdao.businessReturnInsert(pr);
         view = "forward:/pointMm?type=전체내역";
      }
      mav.addObject("msg", "환불신청 되었습니다.");
      mav.setViewName(view);
      
   }

   private void PointReturn() {
	      mav = new ModelAndView();
	      String id =(String)session.getAttribute("p_id");  //==================================== session으로 변경
	      int kind = pdao.kindselect(id);
	      System.out.println("kind="+kind);
	      List<Pointhistory> plist = pdao.pointselect(id);
	      calculationP(plist,kind);
	      String menu = null;
	      if(kind == 1){
	         menu = "개인";
	      }else if(kind == 2){
	         menu = "사업자";
	      }
	      
	      mav.addObject("menu", menu);
	      mav.addObject("kind", kind);
	      mav.setViewName("PointReturn");
	   }

   private void pointcharge() {
      mav = new ModelAndView();
      String id = session.getAttribute("p_id").toString();   //=========================================session으로 변경
      int point = Integer.parseInt(req.getParameter("point"));
      System.out.println("point="+point);
      Pointhistory ph = new Pointhistory();
      ph.setPh_pid(id);
      ph.setPh_point(point);
      ph.setPh_history(4);
      ph.setPh_process("");
      pdao.pointinsert(ph);
      
      mav.setViewName("forward:/myPoint?type=전체내역");
   }
   
   /*private void myPointChange() {
      mav = new ModelAndView();
      String kind = req.getParameter("kind");
      System.out.println("kind="+kind);
      String month = req.getParameter("month");
      System.out.println("month="+month);
      
      
   }*/

   @SuppressWarnings("null")
   private void myPoint() {
      mav = new ModelAndView();
      String id = session.getAttribute("p_id").toString();   //================================================session으로 변경
      String grade = pdao.memberselect(id);
      int kind = pdao.kindselect(id);
      String value = req.getParameter("value");
      String type = req.getParameter("type");
      System.out.println("type="+type);
      String pointList = null; 
      String mileageList = null;
      List<Pointhistory> realplist = null;
      List<Mileage> realmlist = null;
      Mileage m = new Mileage();

      
      List<Pointhistory> plist = pdao.pointselect(id);
      List<Mileage> mlist= pdao.mileageselect(id);
      if(type.equals("전체내역")){
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
         
         int listcount=pdao.pointCount(id);
         List<Pointhistory> plist1 = pdao.pointMapSelect(map);
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
         pointList = pointListHtml(plist1,kind,type);
         if(kind == 1){
            LinkedHashMap<String,Object> map2=new LinkedHashMap<String,Object>();
            map2.put("startrow", startrow);
            map2.put("endrow", endrow);
            map2.put("id", id);
            
            int listcount2=pdao.mileageCount(id);
            List<Mileage> mlist1= pdao.mileageMapSelect(map2);
            int maxpage2=(int)((double)listcount2/limit+0.95);//0.95를 더해서 올림처리
            //현재페이지에 보여줄 시작 페이지수
            int startpage2=(((int)((double)page/5+0.9))-1)*5+1;
            //현재 페이지에 보여줄 마지막 페이지 수
            int endpage2=startpage2+5-1;
            if(endpage2>maxpage2) endpage2=maxpage2;
            req.setAttribute("page", page);//현재 페이지수
            req.setAttribute("maxpage", maxpage2);//최대 페이지수
            req.setAttribute("startpage", startpage2);
            req.setAttribute("endpage", endpage2);
            mileageList = mileageListHtml(mlist1,type);
         }

      }else if(type.equals("사용내역")){
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
         //카운트할 테이블         
         int listcount=pdao.pointPlusCount(id);
         //셀렉트할 테이블
         realplist = pdao.pointPlusMapSelect(map);
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
         //makehtml할 메소드
         pointList = pointListHtml(realplist,kind,type);
         if(kind == 1){
            m.setMg_pid(id);
            m.setMg_updown(1);
            
            LinkedHashMap<String,Object> map2=new LinkedHashMap<String,Object>();
            map2.put("startrow", startrow);
            map2.put("endrow", endrow);
            map2.put("mg_pid", id);
            map2.put("mg_updown", 1);
            //카운트할 테이블         
            System.out.println("mg_pid="+m.getMg_pid());
            System.out.println("mg_updown="+m.getMg_updown());
            int listcount2=pdao.mileagePlmiCount(m);
            //셀렉트할 테이블
            realmlist = pdao.mileagePlmiMapSelect(map2);
            int maxpage2=(int)((double)listcount2/limit+0.95);//0.95를 더해서 올림처리
            //현재페이지에 보여줄 시작 페이지수
            int startpage2=(((int)((double)page/5+0.9))-1)*5+1;
            //현재 페이지에 보여줄 마지막 페이지 수
            int endpage2=startpage2+5-1;
            if(endpage2>maxpage2) endpage2=maxpage2;
            req.setAttribute("page", page);//현재 페이지수
            req.setAttribute("maxpage", maxpage2);//최대 페이지수
            req.setAttribute("startpage", startpage2);
            req.setAttribute("endpage", endpage2);
            //makehtml할 메소드
            mileageList = mileageListHtml(realmlist,type);
         }
      }else if(type.equals("적립내역")){
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
         //카운트할 테이블         
         int listcount=pdao.pointMinusCount(id);
         //셀렉트할 테이블
         realplist = pdao.pointMinusMapSelect(map);
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
         //makehtml할 메소드
         pointList = pointListHtml(realplist,kind,type);
         if(kind == 1){
            m.setMg_pid(id);
            m.setMg_updown(0);
            LinkedHashMap<String,Object> map2=new LinkedHashMap<String,Object>();
            map2.put("startrow", startrow);
            map2.put("endrow", endrow);
            map2.put("mg_pid", id);
            map2.put("mg_updown", 0);
            //카운트할 테이블         
            int listcount2=pdao.mileagePlmiCount(m);
            //셀렉트할 테이블
            realmlist = pdao.mileagePlmiMapSelect(map2);
            int maxpage2=(int)((double)listcount2/limit+0.95);//0.95를 더해서 올림처리
            //현재페이지에 보여줄 시작 페이지수
            int startpage2=(((int)((double)page/5+0.9))-1)*5+1;
            //현재 페이지에 보여줄 마지막 페이지 수
            int endpage2=startpage2+5-1;
            if(endpage2>maxpage2) endpage2=maxpage2;
            req.setAttribute("page", page);//현재 페이지수
            req.setAttribute("maxpage", maxpage2);//최대 페이지수
            req.setAttribute("startpage", startpage2);
            req.setAttribute("endpage", endpage2);
            //makehtml할 메소드
            mileageList = mileageListHtml(realmlist,type);
         }
      }

      calculationP(plist,kind);
      if(kind == 1){
         calculationM(mlist);
      }
      
      mav.addObject("value", value);
      mav.addObject("type", type);
      mav.addObject("grade", grade);
      mav.addObject("pointList", pointList);
      mav.addObject("mileageList", mileageList);

      mav.setViewName("Point");
   }

   private void calculationM(List<Mileage> mlist) {
      int mileageplus = 0;
      int mileageminus = 0;
      int mileagetotal = 0;
      for(int i=0; i<mlist.size(); i++){
         Mileage m = mlist.get(i);
         if(m.getMg_updown() == 0){
            mileageplus += m.getMg_mileage();
         }else if(m.getMg_updown() == 1){
            mileageminus += m.getMg_mileage();
         }
      }
      mileagetotal = mileageplus - mileageminus;
      mav.addObject("mileagetotal", mileagetotal);
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

   private String mileageListHtml(List<Mileage> mlist,String type) {
      StringBuilder sb = new StringBuilder();
      int page=(int) req.getAttribute("page");
      int maxpage=(int) req.getAttribute("maxpage");
      int startpage=(int) req.getAttribute("startpage");
      int endpage=(int) req.getAttribute("endpage");
      sb.append("<table>");
      for(int i=0; i<mlist.size(); i++){
         Mileage m = mlist.get(i);
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Date date1 = m.getMg_date();
         String today = df.format(date1);
         if(m.getMg_history() == 0){
            sb.append("<tr><td>결제</td><td>"+today+"</td><td>+"+m.getMg_mileage()+"</td></tr>");
         }else if(m.getMg_history() == 1){
            sb.append("<tr><td>리뷰</td><td>"+today+"</td><td>+"+m.getMg_mileage()+"</td></tr>");
         }else if(m.getMg_history() == 2){
            sb.append("<tr><td>사용</td><td>"+today+"</td><td>-"+m.getMg_mileage()+"</td></tr>");
         }
      }
      sb.append("</table>");
      if(page<=1){
          sb.append("<a href='#'>[이전]</a>");
       }else{
          sb.append("<a href='./myPoint?page="+(page-1)+"&type="+type+"&value=마일리지'>[이전]</a>");
       }
       for(int a=startpage; a<=endpage; a++){
          if(a==page){
             sb.append("<a href='#'>["+a+"]</a>");
          }else{
             sb.append("<a href='./myPoint?page="+a+"&type="+type+"&value=마일리지'>["+a+"]</a>");
          }
       }
       if(page>=maxpage){
          sb.append("<a href='#'>[다음]</a>");
       }else{
          sb.append("<a href='./myPoint?page="+(page+1)+"&type="+type+"&value=마일리지'>[다음]</a>");
       }
      return sb.toString();
   }

   private String pointListHtml(List<Pointhistory> plist, int kind,String type) {
      StringBuilder sb = new StringBuilder();
      int page=(int) req.getAttribute("page");
      int maxpage=(int) req.getAttribute("maxpage");
      int startpage=(int) req.getAttribute("startpage");
      int endpage=(int) req.getAttribute("endpage");
      if(kind == 1){
         sb.append("<table>");
         for(int i=0; i<plist.size(); i++){
            Pointhistory ph = plist.get(i);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = ph.getPh_date();
            String today = df.format(date1);
            if(ph.getPh_history() == 0){
               sb.append("<tr><td>결제</td><td>"+today+"</td><td>-"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 1){
               sb.append("<tr><td>결제취소</td><td>"+today+"</td><td>+"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 2){
               sb.append("<tr><td>환불</td><td>"+today+"</td><td>-"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 4){
               sb.append("<tr><td>충전</td><td>"+today+"</td><td>+"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 6){
                sb.append("<tr><td>승인취소</td><td>"+today+"</td><td>-"+ph.getPh_point()+"</td></tr>");
            }
            
         }
         sb.append("</table>");
      }else if(kind == 2){
         sb.append("<table>");
         for(int i=0; i<plist.size(); i++){
            Pointhistory ph = plist.get(i);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = ph.getPh_date();
            String today = df.format(date1);
            if(ph.getPh_history() == 3){
               sb.append("<tr><td>환전</td><td>"+today+"</td><td>-"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 5){
               sb.append("<tr><td>세탁완료</td><td>"+today+"</td><td>+"+ph.getPh_point()+"</td></tr>");
            }else if(ph.getPh_history() == 6){
                sb.append("<tr><td>승인취소</td><td>"+today+"</td><td>-"+ph.getPh_point()+"</td></tr>");
             }
            
         }
         sb.append("</table>");
      }
      if(page<=1){
          sb.append("<a href='#'>[이전]</a>");
       }else{
          sb.append("<a href='./myPoint?page="+(page-1)+"&type="+type+"&value=포인트'>[이전]</a>");
       }
       for(int a=startpage; a<=endpage; a++){
          if(a==page){
             sb.append("<a href='#'>["+a+"]</a>");
          }else{
             sb.append("<a href='./myPoint?page="+a+"&type="+type+"&value=포인트'>["+a+"]</a>");
          }
       }
       if(page>=maxpage){
          sb.append("<a href='#'>[다음]</a>");
       }else{
          sb.append("<a href='./myPoint?page="+(page+1)+"&type="+type+"&value=포인트'>[다음]</a>");
       }
      return sb.toString();
   }
   
   

}
       