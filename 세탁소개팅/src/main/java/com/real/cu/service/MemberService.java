package com.real.cu.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Business;
import com.real.cu.bean.Member;
import com.real.cu.bean.Person;
import com.real.cu.dao.MemberDao;


@Service
public class MemberService {

   @Autowired
   private HttpSession session;
   @Autowired
   private MemberDao mDao;
   @Autowired
   private HttpServletRequest req;
   @Autowired
   private NoteService ns;

   ModelAndView mav;                                                                                                                                              


   public ModelAndView execute(Person p, int check, int cmd) {
      switch(cmd){
      case 5: 
         joinVCsend(p,check); break;
      default:break;
      }
      return mav;
   }

   public ModelAndView execute(Person p, int cmd) {
      switch(cmd){
      case 1:
         login(p); break;
      case 2:
         memberInsert(p); break;
      case 3:
         businessInsert(p); break;
      case 12:
         personUpdate(p); break;
      case 16:
         businessUpdate(p); break;
      default:break;
      }
      return mav;
   }


   public ModelAndView execute(int cmd) {
      switch(cmd){
      case 6:
         findId(); break;
      case 7:
         findPw(); break;
      case 8:
         personUpdateMV(); break;
      case 9:
         personMyPage(); break;
      case 13:
         memberDelete(); break;
      case 14:
         businessUpdateMV(); break;
      case 15:
         businessMyPage(); break;
      case 17:
         businessDelete(); break;
      case 18:
         blackList(); break;
      case 21:
         adminDeleteMV(); break;
      case 22:
          logout(); break;
      default:break;
      }
      return mav;
   }


   private void logout() {
	   mav=new ModelAndView();
	   session.invalidate();
	   mav.setViewName("home");
}

public ModelAndView execute(int check, int cmd){
      switch(cmd){
      case 4:
         idCheck(check); break;
      case 5: 
         joinVCsend(check); break;
      case 10:
         pwCheck(check); break;
      case 11:
         vcCheck(check); break;
      case 19:
         selectMemberList(check); break;
      case 20:
         classChange(check); break;
      case 22:
         selectList(check); break;
      case 23:
         personDelete(check); break;
      }
      return mav;
   }


   private void personDelete(int check) {
      if(check==0||check==1||check==2){
         String id[] = req.getParameterValues("tot");
         for(int i=0;i<id.length;i++){
            System.out.println(id[i]);
            String p_id=id[i];
            int result = mDao.addDeleteList(p_id);
            if(result!=0){
               System.out.println("�쉶�썝�깉�눜 �꽦怨�"+i);
            }else{
               System.out.println("�쉶�썝�깉�눜 �떎�뙣"+i);
            }
         }if(check==0){
            System.out.println("�쟾泥�");
            req.setAttribute("menu", "tot");
         }else if(check==1){
            req.setAttribute("menu", "member");
            System.out.println("媛쒖씤");
         }else if(check==2){
            req.setAttribute("menu", "business");
            System.out.println("�궗�뾽�옄");
         }
      }selectList(2);
   }


   private void selectList(int check) {
      mav = new ModelAndView();
      List<Person> blist=null;
      if(check==1){
         String menu = req.getParameter("val");
         if(menu.equals("tot")){
            adminDeleteMV();
         }else if(menu.equals("member")){
            blist=mDao.getMemberList2();
            if(blist!=null){
               System.out.println("硫ㅻ쾭 由ъ뒪�듃 �꽦怨�");
               String list=makeMemberHtml2(blist);
               mav.addObject("list", list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("硫ㅻ쾭 由ъ뒪�듃 �떎�뙣");
               mav.addObject("check", 1);
               mav.setViewName("forward:/adminDeleteMV");
            }
         }else if(menu.equals("business")){
            blist=mDao.getBusinessList2();
            if(blist!=null){
               System.out.println("�궗�뾽�옄 由ъ뒪�듃 �꽦怨�");
               String list=makeBusinessHtml2(blist);
               mav.addObject("list", list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("�궗�뾽�옄 由ъ뒪�듃 �떎�뙣");
               mav.addObject("check", 1);
               mav.setViewName("forward:/adminDeleteMV");
            }
         }else{
            blist=mDao.getDeleteList();
            if(blist!=null){
               System.out.println("�깉�눜�쉶�썝 由ъ뒪�듃 �꽦怨�");
               String list=makeDeleteHtml(blist);
               mav.addObject("list",list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("�깉�눜�쉶�썝 由ъ뒪�듃 �떎�뙣");
               mav.addObject("check",1);
               mav.setViewName("forward:/adminDeleteMv");
            }
         }
      }else if(check==2){
         String menu = (String) req.getAttribute("menu");
         if(menu.equals("tot")){
            adminDeleteMV();
         }else if(menu.equals("member")){
            blist=mDao.getMemberList2();
            if(blist!=null){
               System.out.println("硫ㅻ쾭 由ъ뒪�듃 �꽦怨�2");
               String list=makeMemberHtml2(blist);
               mav.addObject("list", list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("硫ㅻ쾭 由ъ뒪�듃 �떎�뙣2");
               mav.addObject("check", 1);
               mav.setViewName("forward:/adminDeleteMV");
            }
         }else if(menu.equals("business")){
            blist=mDao.getBusinessList2();
            if(blist!=null){
               System.out.println("�궗�뾽�옄 由ъ뒪�듃 �꽦怨�2");
               String list=makeBusinessHtml2(blist);
               mav.addObject("list", list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("�궗�뾽�옄 由ъ뒪�듃 �떎�뙣2");
               mav.addObject("check", 1);
               mav.setViewName("forward:/adminDeleteMV");
            }
         }else{
            blist=mDao.getDeleteList();
            if(blist!=null){
               System.out.println("�깉�눜�쉶�썝 由ъ뒪�듃 �꽦怨�2");
               String list=makeDeleteHtml(blist);
               mav.addObject("list",list);
               mav.setViewName("AdminDelete");
            }else{
               System.out.println("�깉�눜�쉶�썝 由ъ뒪�듃 �떎�뙣2");
               mav.addObject("check",1);
               mav.setViewName("forward:/adminDeleteMv");
            }
         }

      }
   }

   private String makeDeleteHtml(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='delete' style='height:28px' onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='delete'selected='selected'>�깉�눜�쉶�썝</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td></tr>");
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      return sb.toString();
   }


   private String makeBusinessHtml2(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='delete' style='height:28px' onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business' selected='selected'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='delete'>�깉�눜�쉶�썝</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td><td>釉붾옓由ъ뒪�듃 �긽�깭</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
         sb.append("<td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td>");
         System.out.println(sb);
         if(p.getP_state()==0){
            sb.append("<td> X </td></tr>");
         }else{
            sb.append("<td> O </td></tr>");
         }
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='personDelete(2)'>�깉�눜�떆�궎湲�</a>");
      return sb.toString();
   }

   private String makeMemberHtml2(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='delete' style='height:28px' onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member' selected='selected'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='delete'>�깉�눜�쉶�썝</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td><td>釉붾옓由ъ뒪�듃 �긽�깭</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
         sb.append("<td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td>");
         if(p.getP_state()==0)
         {
            sb.append("<td> X </td></tr>");
         }else{
            sb.append("<td> O </td></tr>");
         }
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='personDelete(1)'>�깉�눜�떆�궎湲�</a>");
      return sb.toString();
   }

   private void adminDeleteMV() {
      mav = new ModelAndView();
      List<Person> blist=null;
      blist = mDao.getPersonList();
      if(blist!=null){
         System.out.println("�꽦怨�");
         String list=makePersonHtml2(blist);
         mav.addObject("list", list);
         mav.setViewName("AdminDelete");
      }else{
         System.out.println("�떎�뙣");
         mav.addObject("check", 1);
         mav.setViewName("AdminMain");
      }
   }


   private String makePersonHtml2(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='delete' style='height:28px' onchange='searchList()'>");
      sb.append("<option value='tot' selected='selected'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='delete'>�깉�눜�쉶�썝</delete>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td><td>�쉶�썝援щ텇</td><td>釉붾옓由ъ뒪�듃 �긽�깭</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         if(p.getP_state()==0){
            sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
            sb.append("<td>" + p.getP_id() + "</td>");
            sb.append("<td>" + p.getP_name() + "</td>");
            sb.append("<td>" + p.getP_email() + "</td>");
            sb.append("<td>" + p.getP_phone() + "</td>");
            if(p.getP_flag()==1){
               sb.append("<td>媛쒖씤�쉶�썝</td>");
               sb.append("<td> X </td></tr>");
            }else{
               sb.append("<td>�궗�뾽�옄�쉶�썝</td>");
               sb.append("<td> X </td></tr>");
            }
         }else if(p.getP_state()==1){   
            sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
            sb.append("<td>" + p.getP_id() + "</td>");
            sb.append("<td>" + p.getP_name() + "</td>");
            sb.append("<td>" + p.getP_email() + "</td>");
            sb.append("<td>" + p.getP_phone() + "</td>");
            if(p.getP_flag()==1){
               sb.append("<td>�씪諛섑쉶�썝</td>");
               sb.append("<td> O </td></tr>");
            }else{
               sb.append("<td>�궗�뾽�옄�쉶�썝</td>");
               sb.append("<td> O </td></tr>");
            }
         }
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='personDelete(0)'>�깉�눜�떆�궎湲�</a>");
      return sb.toString();
   }

   private void classChange(int check){
      if(check==0||check==1||check==2){
         String[] id= req.getParameterValues("tot");
         for(int i=0; i<id.length; i++){
            String p_id=id[i];
            mDao.addBlackList(p_id);
         }if(check==0){
            req.setAttribute("menu", "tot");
         }else if(check==1){
            req.setAttribute("menu", "member");
         }else if(check==2){
            req.setAttribute("menu", "business");
         }
      }else if(check==3){
         String[] id= req.getParameterValues("black");
         for(int i=0; i<id.length; i++){
            String p_id=id[i];
            mDao.removeBlack(p_id);
         }req.setAttribute("menu", "black");
      }selectMemberList(2);
   }


   private void selectMemberList(int check) {
      mav = new ModelAndView();
      List<Person> blist=null;
      if(check==1){
         String menu=req.getParameter("val");
         if(menu.equals("tot")){
            blackList();
         }else if(menu.equals("member")){
            blist=mDao.getMemberList();
            if(blist!=null){
               String li=makeMemberHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }else if(menu.equals("business")){
            blist=mDao.getBusinessList();
            if(blist!=null){
               String li=makeBusinessHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }else{
            blist=mDao.getBlackList();
            if(blist!=null){
               String li=makeBlackHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }
      }else if(check==2){
         String menu=(String) req.getAttribute("menu");
         if(menu.equals("tot")){
            blackList();
         }else if(menu.equals("member")){
            blist=mDao.getMemberList();
            if(blist!=null){
               String li=makeMemberHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }else if(menu.equals("business")){
            blist=mDao.getBusinessList();
            if(blist!=null){
               String li=makeBusinessHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }else{
            blist=mDao.getBlackList();
            if(blist!=null){
               String li=makeBlackHtml(blist);
               mav.addObject("li", li);
               mav.setViewName("BlackList");
            }else{
               mav.addObject("check", 1);
               mav.setViewName("forward:/blackList");
            }
         }
      }
   }


   private String makeBlackHtml(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='person' style='height:28px' onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝 由ъ뒪�듃</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='blacklist' selected='selected'>釉붾옓由ъ뒪�듃</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td><td>�쉶�썝援щ텇</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td><input type='checkbox' name='black' value='"+p.getP_id()+"'/></td>");
         sb.append("<td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td>");
         if(p.getP_flag()==1){
            sb.append("<td>媛쒖씤�쉶�썝</td></tr>");
         }else if(p.getP_flag()==2){
            sb.append("<td>�궗�뾽�옄�쉶�썝</td></tr>");
         }
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='change(3)'>釉붾옓由ъ뒪�듃 異붽�</a>");
      return sb.toString();
   }

   private String makeBusinessHtml(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='person' style='height:28px'  onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business' selected='selected'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='blacklist'>釉붾옓由ъ뒪�듃</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
         sb.append("<td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td></tr>");
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='change(2)'>釉붾옓由ъ뒪�듃 異붽�</a>");
      return sb.toString();
   }

   private String makeMemberHtml(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='person' style='height:28px'  onchange='searchList()'>");
      sb.append("<option value='tot'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member' selected='selected'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='blacklist'>釉붾옓由ъ뒪�듃</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
         sb.append("<td>" + p.getP_id() + "</td>");
         sb.append("<td>" + p.getP_name() + "</td>");
         sb.append("<td>" + p.getP_email() + "</td>");
         sb.append("<td>" + p.getP_phone() + "</td></tr>");
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='change(1)'>釉붾옓由ъ뒪�듃 異붽�</a>");
      return sb.toString();
   }

   private void blackList() {
      mav = new ModelAndView();
      List<Person> blist=null;
      blist = mDao.getPersonList();
      if(blist!=null){
         System.out.println("�꽦怨�");
         String li=makePersonHtml(blist);
         mav.addObject("li", li);
         mav.setViewName("BlackList");
      }else{
         System.out.println("�떎�뙣");
         mav.addObject("check", 1);
         mav.setViewName("AdminMain");
      }
   }




   private String makePersonHtml(List<Person> blist) {
      StringBuilder sb = new StringBuilder();
      System.out.println(blist.size());
      sb.append("<select id='selectBox' name='person' style='height:28px'  onchange='searchList()'>");
      sb.append("<option value='tot' selected='selected'>�쟾泥댄쉶�썝</option>");
      sb.append("<option value='member'>媛쒖씤�쉶�썝</option>");
      sb.append("<option value='business'>�궗�뾽�옄�쉶�썝</option>");
      sb.append("<option value='blacklist'>釉붾옓由ъ뒪�듃</option>");
      sb.append("</select><span>��</span>");
      sb.append(makeHtml());
      sb.append("<table border='1' style='text-align:center; width:900px;'>");
      sb.append("<tr><td>援щ텇</td><td>�븘�씠�뵒</td><td>�씠由�</td><td>�씠硫붿씪</td><td>�쟾�솕踰덊샇</td><td>�쉶�썝援щ텇</td><td>釉붾옓由ъ뒪�듃 �긽�깭</td></tr>");
      for (int i = 0; i < blist.size() ; i++) {
         Person p = blist.get(i);
         if(p.getP_state()==0){
            sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
            sb.append("<td>" + p.getP_id() + "</td>");
            sb.append("<td>" + p.getP_name() + "</td>");
            sb.append("<td>" + p.getP_email() + "</td>");
            sb.append("<td>" + p.getP_phone() + "</td>");
            if(p.getP_flag()==1){
               sb.append("<td>媛쒖씤�쉶�썝</td>");
               sb.append("<td> X </td></tr>");
            }else{
               sb.append("<td>�궗�뾽�옄�쉶�썝</td>");
               sb.append("<td> X </td></tr>");
            }
         }else if(p.getP_state()==1){   
            sb.append("<tr><td><input type='checkbox' name='tot' value='"+p.getP_id()+"'/></td>");
            sb.append("<td>" + p.getP_id() + "</td>");
            sb.append("<td>" + p.getP_name() + "</td>");
            sb.append("<td>" + p.getP_email() + "</td>");
            sb.append("<td>" + p.getP_phone() + "</td>");
            if(p.getP_flag()==1){
               sb.append("<td>�씪諛섑쉶�썝</td>");
               sb.append("<td> O </td></tr>");
            }else{
               sb.append("<td>�궗�뾽�옄�쉶�썝</td>");
               sb.append("<td> O </td></tr>");
            }
         }
         System.out.println(sb);
      }
      sb.append("</table>");
      sb.append("<br/>");
      sb.append("<a href='#' style='float:right' class='btn' onclick='addBlack()'>釉붾옓由ъ뒪�듃 異붽�</a>");
      return sb.toString();
   }


   private StringBuilder makeHtml(){
      StringBuilder sb = new StringBuilder();
      sb.append("<p><input type='hidden' id='val' name='val'/></p>");
      return sb;
   }

   private void businessDelete() {
      mav = new ModelAndView();
      String p_id = (String) session.getAttribute("p_id");
      if(mDao.personDelete(p_id)!=0){
         session.invalidate();
         mav.addObject("check", 4);
         mav.setViewName("home");
      }else{
         mav.addObject("check", 2);
         mav.setViewName("BusinessMain");
      }

   }

   private void businessMyPage() {
      mav = new ModelAndView();
      String p_id = (String) session.getAttribute("p_id");
      Person p = new Person();
      p = mDao.personUpdatePage(p_id);
      p.setP_gender(p.getP_gender().substring(0,1)+"�옄");
      Business b = new Business();
      b = mDao.businessUpdatePage(p_id);
      mav.addObject("b",b);
      mav.addObject("p",p);
      mav.setViewName("BusinessMyPage");
   }

   private void businessUpdate(Person p) {
      mav = new ModelAndView();
      String view=null;
      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
      p.setP_pw(pwdEncoder.encode(p.getP_pw()));
      String p_id = (String) session.getAttribute("p_id");
      p.setP_id(p_id);
      p.setP_phone(req.getParameter("phone1")+"-"+req.getParameter("phone2")+"-"
            +req.getParameter("phone3"));
      String val = req.getParameter("email3");
      if(val.equals("9")){
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email2");
         mav.addObject("email7",email2);
         p.setP_email(email1+"@"+email2);
      }else{
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email3");
         mav.addObject("email7",email2);
         p.setP_email(email1+"@"+email2);
      }
      Business b = new Business();
      String b_pid=(String) session.getAttribute("p_id");
      b.setB_pid(b_pid);
      int lisence = Integer.valueOf(req.getParameter("lisence"));
      b.setB_lisence(lisence);
      if(mDao.personUpdate(p)!=0){
         mav.addObject("check", 3);
         session.invalidate(); //�꽭�뀡�뿉 ���옣�맂 �젙蹂� 紐⑤몢 �궘�젣
         view="home";
      }else{
         mav.addObject("check", 1);
         view="BusinessMain";
      }mav.setViewName(view);
   }


   private void businessUpdateMV() {
      String p_id = (String) session.getAttribute("p_id");
      Person p = new Person();
      p = mDao.personUpdatePage(p_id);
      String[] email = p.getP_email().split("@");
      String email1 = email[0];
      String email2 = email[1];
      String[] phone = p.getP_phone().split("-");
      String phone1 = phone[0];
      String phone2 = phone[1];
      String phone3 = phone[2];
      Business b = new Business();
      b = mDao.businessUpdatePage(p_id);
      RandomNum();
      mav.addObject("email1", email1);
      mav.addObject("email7", email2);
      mav.addObject("phone1", phone1);
      mav.addObject("phone2", phone2);
      mav.addObject("phone3", phone3);
      mav.addObject("p_gender", p.getP_gender().substring(0, 1));
      mav.addObject("b", b);
      mav.addObject("p",p);
      mav.setViewName("BusinessUpdate");

   }


   private void memberDelete() {
      mav = new ModelAndView();
      String p_id = (String) session.getAttribute("p_id");
      if(mDao.personDelete(p_id)!=0){
         session.invalidate();
         mav.addObject("check", 4);
         mav.setViewName("home");
      }else{
         mav.addObject("check", 2);
         mav.setViewName("PersonMain");
      }
   }


   private void personMyPage() {
      mav = new ModelAndView();
      String p_id = (String) session.getAttribute("p_id");
      Person p = new Person();
      p = mDao.personUpdatePage(p_id);
      p.setP_gender(p.getP_gender().substring(0,1)+"�옄");

      Member m = new Member();
      m = mDao.memberUpdatePage(p_id);
      String[] addr = m.getM_address().split("/");
      String addr1=addr[1];
      String addr2=addr[2];
      m.setM_address(addr1+" "+addr2);
      mav.addObject("m",m);
      mav.addObject("p",p);
      mav.setViewName("PersonMyPage");
   }


   private void personUpdate(Person p) {
      mav = new ModelAndView();
      String view=null;
      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
      p.setP_pw(pwdEncoder.encode(p.getP_pw()));
      p.setP_phone(req.getParameter("phone1")+"-"+req.getParameter("phone2")+"-"
            +req.getParameter("phone3"));
      String val = req.getParameter("email3");
      if(val.equals("9")){
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email2");
         p.setP_email(email1+"@"+email2);
      }else{
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email3");
         p.setP_email(email1+"@"+email2);
      }
      Member m = new Member();
      String m_pid=(String) session.getAttribute("p_id");
      m.setM_pid(m_pid);
      m.setM_address(req.getParameter("post")+"/"+req.getParameter("addr1")+"/"+
            req.getParameter("addr2"));
      if(mDao.personUpdate(p)!=0){
         if(mDao.memberUpdate(m)!=0){
            mav.addObject("check", 3);
            session.invalidate(); //�꽭�뀡�뿉 ���옣�맂 �젙蹂� 紐⑤몢 �궘�젣
            view="home";
         }else{mav.addObject("check", 1);
         view="PersonMain";
         }
      }mav.setViewName(view);
   }


   private void joinVCsend(Person p, int check) {
	      mav = new ModelAndView();
	      updatemail();
	      mav.addObject("p", p);
	      mav.addObject("pw", req.getParameter("pw"));
	      mav.addObject("p_pw", p.getP_pw());
	      mav.addObject("p_gender", p.getP_gender().substring(0, 1));
	      mav.addObject("email1", req.getParameter("email1"));
	      String val = req.getParameter("email3");
	      if(val.equals("9")){
	         mav.addObject("email7" , req.getParameter("email2"));
	         mav.addObject("flag", 2);
	      }else if(val.equals("0")){
	         mav.addObject("email7" , req.getParameter("email2"));
	          mav.addObject("flag",5);
	      }else{
	         mav.addObject("email7" , req.getParameter("email3"));
	         mav.addObject("flag",1);
	      }
	      mav.addObject("phone1", req.getParameter("phone1"));
	      mav.addObject("phone2", req.getParameter("phone2"));
	      mav.addObject("phone3", req.getParameter("phone3"));
	      String r_num = (String) session.getAttribute("r_num");
	      mav.addObject("r_num", r_num);
	      mav.addObject("check", 3);
	      if(check==1){
	         mav.addObject("post", req.getParameter("post"));
	         mav.addObject("addr1", req.getParameter("addr1"));
	         mav.addObject("addr2", req.getParameter("addr2"));
	         mav.setViewName("PersonUpdate");
	      }else if(check==2){
	         Business b = new Business();
	         String p_id = (String) session.getAttribute("p_id");
	         b = mDao.businessUpdatePage(p_id);
	         mav.addObject("b", b);
	         mav.setViewName("BusinessUpdate");
	      }
	   }

   //update�떆 �씤利앸찓�씪 �쟾�넚�떆 �궗�슜
   private void updatemail() { 
      Person p = new Person();
      mav=new ModelAndView();
      String host = "smtp.gmail.com"; //smtp 二쇱냼
      String subject = "�꽭�긽�냼媛쒗똿 �씤利앸찓�씪 �쟾�넚"; 
      String fromName = "�꽭�긽�냼媛쒗똿 愿�由ъ옄";
      String from = "tofha56@gmail.com";  //蹂대궡�뒗 �궗�엺 二쇱냼
      String val = req.getParameter("email3");
      mav.addObject("p_gender",req.getParameter("p_gender2"));
      mav.addObject("phone1",req.getParameter("phone1"));
      mav.addObject("phone2",req.getParameter("phone2"));
      mav.addObject("phone3",req.getParameter("phone3"));
      if(val.equals("9")){
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email2");
         mav.addObject("email7", email2);
         p.setP_email(email1+"@"+email2);
      }else{
         String email1 = req.getParameter("email1");
         String email2 = req.getParameter("email3");
         mav.addObject("email7", email2);
         p.setP_email(email1+"@"+email2);
      }
      String to1 = p.getP_email();
      System.out.println(to1);
      String authNum = RandomNum();
      System.out.println(authNum);
      String content = "�씤利앸쾲�샇 : [" + authNum + "]";
      try{
         Properties props = new Properties();
         //G-mail SMTP 蹂대궡�뒗 怨쇱젙
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", host);
         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.port", "465");
         props.put("mail.smtp.user", from);
         props.put("mail.smtp.auth", "true");

         Session mailSession = Session.getInstance(props,
               new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("tofha56","kk578578578");
            }
         });
         Message msg = new MimeMessage(mailSession);
         msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(
               fromName, "UTF-8", "B")));

         InternetAddress[] address1 = { new InternetAddress(to1) };
         msg.setRecipients(Message.RecipientType.TO, address1); 
         msg.setSubject(subject); 
         msg.setSentDate(new java.util.Date()); // 蹂대궡�뒗 �궇吏�
         msg.setContent(content, "text/html; charset=euc-kr");

         Transport.send(msg); 
         session.setAttribute("r_num", authNum);
      }catch (MessagingException e){
         e.printStackTrace();
      }catch (Exception e){
         e.printStackTrace();
      }
   }


   private void pwCheck(int save) {
      mav=new ModelAndView();
      Person p = new Person();
      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
      String pw = req.getParameter("pw");
      String p_id = (String) session.getAttribute("p_id");
      p = mDao.personUpdatePage(p_id);
      System.out.println(p.getP_pw());
      if(save==1){
         String[] email = p.getP_email().split("@");
         String email1 = email[0];
         String email2 = email[1];
         String[] phone = p.getP_phone().split("-");
         String phone1 = phone[0];
         String phone2 = phone[1];
         String phone3 = phone[2];
         Member m = new Member();
         m = mDao.memberUpdatePage(p_id);
         String[] addr = m.getM_address().split("/");
         String post=addr[0];
         String addr1=addr[1];
         String addr2=addr[2];
         mav.addObject("p_gender", p.getP_gender().substring(0, 1));
         mav.addObject("email1", email1);
         mav.addObject("email7", email2);
         mav.addObject("post", post);
         mav.addObject("addr1",addr1);
         mav.addObject("addr2", addr2);
         mav.addObject("phone1", phone1);
         mav.addObject("phone2", phone2);
         mav.addObject("phone3", phone3);
         mav.addObject("p",p);
         if(pwdEncoder.matches(pw,p.getP_pw())){
            mav.addObject("pw", pw);
            mav.addObject("check", 1);
            mav.setViewName("PersonUpdate");
         }else{
            mav.addObject("check", 2);
            mav.setViewName("PersonUpdate");
         }
      }else if(save==2){
         mav.setViewName("MemberDelete");
         if(pwdEncoder.matches(pw,p.getP_pw())){
            mav.addObject("pw", pw);
            mav.addObject("check", 1);
            mav.setViewName("MemberDelete");
         }else{
            mav.addObject("check", 2);
            mav.setViewName("MemberDelete");
         }
      }else if(save==3){
         mav.setViewName("BusinessUpdate");
         String[] email = p.getP_email().split("@");
         String email1 = email[0];
         String email2 = email[1];
         String[] phone = p.getP_phone().split("-");
         String phone1 = phone[0];
         String phone2 = phone[1];
         String phone3 = phone[2];
         mav.addObject("p_gender",p.getP_gender().substring(0, 1));
         mav.addObject("email1", email1);
         mav.addObject("email7", email2);
         mav.addObject("phone1", phone1);
         mav.addObject("phone2", phone2);
         mav.addObject("phone3", phone3);
         mav.addObject("p",p);
         Business b = new Business();
         b = mDao.businessUpdatePage(p_id);
         mav.addObject("b", b);
         if(pwdEncoder.matches(pw, p.getP_pw())){
            mav.addObject("check", 1);
            mav.addObject("pw", pw);
            mav.setViewName("BusinessUpdate");
         }else{
            mav.addObject("check", 2);
            mav.setViewName("BusinessUpdate");
         }
      }else if(save==4){
         mav.setViewName("BusinessDelete");
         if(pwdEncoder.matches(pw,p.getP_pw())){
            mav.addObject("pw", pw);
            mav.addObject("check", 1);
            mav.setViewName("BusinessDelete");
         }else{
            mav.addObject("check", 2);
            mav.setViewName("BusinessDelete");
         }
      }
   }


   private void personUpdateMV() {
	      String p_id = (String) session.getAttribute("p_id");
	      Person p = new Person();
	      p = mDao.personUpdatePage(p_id);
	      String[] email = p.getP_email().split("@");
	      String email1 = email[0];
	      String email2 = email[1];
	      String[] phone = p.getP_phone().split("-");
	      String phone1 = phone[0];
	      String phone2 = phone[1];
	      String phone3 = phone[2];
	      Member m = new Member();
	      m = mDao.memberUpdatePage(p_id);
	      String[] addr = m.getM_address().split("/");
	      String post=addr[0];
	      String addr1=addr[1];
	      String addr2=addr[2];
	      RandomNum();
	      mav.addObject("p_gender",p.getP_gender().substring(0,1));
	      System.out.println(p.getP_gender());
	      mav.addObject("email1", email1);
	      mav.addObject("email7", email2);
	      mav.addObject("post", post);
	      mav.addObject("addr1",addr1);
	      mav.addObject("addr2", addr2);
	      mav.addObject("phone1", phone1);
	      mav.addObject("phone2", phone2);
	      mav.addObject("phone3", phone3);
	      mav.addObject("p",p);
	      mav.setViewName("PersonUpdate");
	   }


	   private void findPw() {
	      mav = new ModelAndView();
	      Person p = new Person();
	      int find = (int) session.getAttribute("find"); //�씠硫붿씪 �쟾�넚 �썑 ���옣�떆耳� �넃�� 媛� (1: �엫�떆鍮꾨�踰덊샇, 2:�씤利앸찓�씪)
	      if(find==1){ //email�쓣 蹂대궦 �썑 �꽭�뀡�뿉 ���옣�떆�궓 媛믪씠 �뱾�뼱�삤硫� �떎�뻾
	         System.out.println("�엫�떆鍮꾨�踰덊샇 �쟾�넚 �썑 �떎�뻾�릺�뒗 findPw");
	         String r_num = (String) session.getAttribute("r_num"); //�씠硫붿씪 �쟾�넚�떆 �떞�븘�몦 �엫�떆鍮꾨�踰덊샇
	         String p_id = req.getParameter("p_id");
	         p.setP_id(p_id);
	         BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
	         //�엫�떆鍮꾨�踰덊샇瑜� 蹂듯샇�솕�떆耳쒖꽌 ���옣
	         String p_pw = pwdEncoder.encode(r_num);
	         p.setP_pw(p_pw);
	         System.out.println(p.getP_id());
	         System.out.println(p.getP_pw());
	         int uResult = mDao.changePw(p);
	         System.out.println(uResult);
	         if(uResult!=0){ //update �꽦怨듭떆
	            System.out.println("update�꽦怨�");
	            session.removeAttribute("p_id");
	            session.removeAttribute("find");
	            mav.addObject("check", 2);
	            mav.setViewName("home");
	         }else{ //update �떎�뙣�떆
	            System.out.println("update �떎�뙣");
	            mav.addObject("check", 4);
	            mav.setViewName("SearchMember"); //鍮꾨�踰덊샇瑜� 李얠� 紐삵뻽�뒿�땲�떎.
	         }
	         session.removeAttribute("find");
	      }else{ //email�쓣 蹂대궡湲� �쟾�뿉�굹 �꽭�뀡�뿉 媛믪씠 ���옣�릺吏� �븡�쓣 寃쎌슦
	         System.out.println("�씤利앸찓�씤 �쟾�넚�븯湲� �쟾�씠誘�濡� �꽆�뼱媛꾨떎!");
	         p.setP_id(req.getParameter("p_id"));
	         String val = req.getParameter("email6");
	         if(val.equals("9")){
	            String email1 = req.getParameter("email4");
	            String email2 = req.getParameter("email5");
	            p.setP_email(email1+"@"+email2);
	         }else{
	            String email1 = req.getParameter("email4");
	            String email2 = req.getParameter("email6");
	            p.setP_email(email1+"@"+email2);
	         }
	         int result = mDao.findPw(p);
	         if(result==1){ //�엯�젰�맂 �젙蹂대�� �넻�빐 鍮꾨�踰덊샇瑜� 李얠븯�떎硫�
	            session.setAttribute("p_id", req.getParameter("p_id"));
	            String email = p.getP_email();
	            searchMail(email);
	         }else{//�엯�젰�맂 �젙蹂대�� �넻�빐 鍮꾨�踰덊샇瑜� 李얠� 紐삵뻽�떎硫�
	            mav.addObject("check", 3);
	            mav.setViewName("SearchMember");
	         }
	      }
	      session.removeAttribute("num");
	   }


	   //鍮꾨�踰덊샇 蹂�寃쎌떆 �궗�슜�븯�뒗 �씠硫붿씪 �쟾�넚
	   private void searchMail(String email) { //�엫�떆鍮꾨�踰덊샇 �쟾�넚
	      String view = null;
	      Person p = new Person();
	      System.out.println("�엫�떆鍮꾨�踰덊샇 �쟾�넚�븳�떎!");
	      mav=new ModelAndView();
	      String host = "smtp.gmail.com"; //smtp 二쇱냼
	      String subject = "�꽭�긽�냼媛쒗똿 �엫�떆 鍮꾨�踰덊샇 �쟾�넚"; 
	      String fromName = "�꽭�긽�냼媛쒗똿 愿�由ъ옄";
	      String from = "tofha56@gmail.com";  //蹂대궡�뒗 �궗�엺 二쇱냼
	      String to1 = email;
	      System.out.println(email);
	      String authNum = RandomNum();
	      System.out.println(authNum);
	      String content = "�엫�떆鍮꾨�踰덊샇 : [" + authNum + "]";
	      try{
	         Properties props = new Properties();
	         //G-mail SMTP 蹂대궡�뒗 怨쇱젙
	         props.put("mail.smtp.starttls.enable", "true");
	         props.put("mail.transport.protocol", "smtp");
	         props.put("mail.smtp.host", host);
	         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	         props.put("mail.smtp.port", "465");
	         props.put("mail.smtp.user", from);
	         props.put("mail.smtp.auth", "true");

	         Session mailSession = Session.getInstance(props,
	               new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("tofha56","kk578578578");
	            }
	         });
	         Message msg = new MimeMessage(mailSession);
	         msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(
	               fromName, "UTF-8", "B")));

	         InternetAddress[] address1 = { new InternetAddress(to1) };
	         msg.setRecipients(Message.RecipientType.TO, address1); 
	         msg.setSubject(subject); 
	         msg.setSentDate(new java.util.Date()); // 蹂대궡�뒗 �궇吏�
	         msg.setContent(content, "text/html; charset=euc-kr");

	         Transport.send(msg); 
	         session.setAttribute("r_num", authNum);
	         mav.addObject("Num", authNum);
	      }catch (MessagingException e){
	         e.printStackTrace();
	      }catch (Exception e){
	         e.printStackTrace();
	      }
	      session.setAttribute("find", 1);
	      findPw();
	   }


	   private void findId() {
	      mav = new ModelAndView();
	         Person p = new Person();
	         int flag = Integer.valueOf(req.getParameter("flag"));
	         p.setP_name(req.getParameter("p_name"));
	         mav.addObject("p_name", req.getParameter("p_name"));
	         String email1 = req.getParameter("email1");
	         mav.addObject("email1", email1);
	         if(flag==2){
	            String email2 = req.getParameter("email2");
	            p.setP_email(email1+"@"+email2);
	            mav.addObject("email7", email2);
	            mav.addObject("flag",2);
	         }else{
	            String email2 = req.getParameter("email3");
	            mav.addObject("email7", email2);
	            p.setP_email(email1+"@"+email2);
	            mav.addObject("flag",1);
	         }
	         int result = mDao.findId(p);
	         if(result==1){
	            String p_id = mDao.getId(p);
	            mav.addObject("p_id",p_id);
	            mav.addObject("authnum", req.getParameter("authnum"));
	            mav.addObject("check", 1); 
	            mav.setViewName("SearchMember");
	         }else{
	            mav.addObject("check", 2); 
	            mav.setViewName("SearchMember");
	         }session.removeAttribute("Num");
	      }

	   
	   private void businessInsert(Person p) {
	      mav = new ModelAndView();
	      String view = null;
	      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
	      p.setP_id(req.getParameter("b_id"));
	      System.out.println(p.getP_id());
	      //�엯�젰�븳 �븫�샇瑜� 蹂듯샇�솕�떆耳쒖꽌 ���옣
	      p.setP_pw(pwdEncoder.encode(p.getP_pw()));
	      String val = req.getParameter("email6");
	      if(val.equals("9")){
	         String email1 = req.getParameter("email4");
	         String email2 = req.getParameter("email5");
	         mav.addObject("email7",email2);
	         p.setP_email(email1+"@"+email2);
	      }else{
	         String email1 = req.getParameter("email4");
	         String email2 = req.getParameter("email6");
	         mav.addObject("email7",email2);
	         p.setP_email(email1+"@"+email2);
	      }
	      p.setP_phone(req.getParameter("phone4")+"-"+req.getParameter("phone5")+
	            "-"+req.getParameter("phone6"));
	      p.setP_gender(req.getParameter("p_gender2"));
	      p.setP_flag(2);
	      p.setP_state(0);
	      p.setP_name(req.getParameter("p_name2"));
	      if(mDao.personInsert(p)!= 0){
	         Business b = new Business();
	         b.setB_pid(p.getP_id());
	         String lisence = req.getParameter("b_lisence");
	         b.setB_lisence(Integer.valueOf(lisence));
	         if(mDao.businessInsert(b)!=0){
	            mav.addObject("check", 1);
	            session.invalidate();
	            view="home";
	         }else{
	            mav.addObject("check", 3);
	            view="JoinMember";
	         }
	      }mav.setViewName(view);
	   }

	   private void memberInsert(Person p) {
	      mav = new ModelAndView();
	      session.removeAttribute("p_id");
	      String view = null;
	      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
	      //�엯�젰�븳 �븫�샇瑜� 蹂듯샇�솕�떆耳쒖꽌 ���옣
	      p.setP_pw(pwdEncoder.encode(p.getP_pw()));
	      String val = req.getParameter("email3");
	      if(val.equals("9")){
	         String email1 = req.getParameter("email1");
	         String email2 = req.getParameter("email2");
	         mav.addObject("email7",email2);
	         p.setP_email(email1+"@"+email2);
	      }else{
	         String email1 = req.getParameter("email1");
	         String email2 = req.getParameter("email3");
	         mav.addObject("email7",email2);
	         p.setP_email(email1+"@"+email2);
	      }
	      p.setP_phone(req.getParameter("phone1")+"-"+req.getParameter("phone2")+
	            "-"+req.getParameter("phone3"));
	      p.setP_gender(req.getParameter("p_gender"));
	      p.setP_flag(1);
	      p.setP_state(0);
	      if(mDao.personInsert(p)!= 0){
	         Member m = new Member();
	         m.setM_pid(p.getP_id());
	         m.setM_ggrade("釉뚮줎利�");
	         m.setM_address(req.getParameter("post")+"/"+req.getParameter("addr1")+"/"
	               +req.getParameter("addr2"));
	         if(mDao.memberInsert(m)!=0){
	            mav.addObject("check", 1);
	            session.invalidate();
	            view="home";
	         }else{
	            mav.addObject("check", 3);
	            view="JoinMember";
	         }
	      }mav.setViewName(view);
	   }


	   private void idCheck(int check){
	      if(check==1){
	         String p_id=req.getParameter("p_id");
	         System.out.println("�쉶�썝");
	         if(mDao.idCheck(p_id)==0){
	            mav.addObject("m_id", p_id);
	            mav.addObject("idCheck", "�궗�슜 媛��뒫�븳 �븘�씠�뵒");
	            mav.addObject("save", 1);
	            mav.addObject("check", 3);
	            mav.addObject("phone1", req.getParameter("phone1"));
	            mav.addObject("flag", 5);
	         }else{
	            mav.addObject("idCheck","以묐났�맂 �븘�씠�뵒");
	            mav.addObject("save", 1);   
	         }
	      }else{
	         String p_id=req.getParameter("b_id");
	         System.out.println("�궗�뾽�옄");
	         if(mDao.idCheck(p_id)==0){
	            mav.addObject("b_id", p_id);
	            mav.addObject("idCheck2", "�궗�슜 媛��뒫�븳 �븘�씠�뵒");
	            mav.addObject("phone4", req.getParameter("phone4"));
	            mav.addObject("save", 2);
	            mav.addObject("check", 3);
	            mav.addObject("flag", 6);
	         }else{
	            mav.addObject("idCheck2", "以묐났�맂 �븘�씠�뵒");
	            mav.addObject("save", 2);
	         }
	      }
	      mav.setViewName("JoinMember");

	   }

	   private void vcCheck(int check) {
	      mav=new ModelAndView();
	      String Num = RandomNum();
	      session.setAttribute("Num", Num);
	      System.out.println(Num);
	      if(check==3){
	         mav.setViewName("JoinMember");
	      }else if(check==4){
	         mav.setViewName("SearchMember");
	      }
	   }

	   //�궃�닔諛쒖깮
	   public String RandomNum(){
	      StringBuffer buffer = new StringBuffer();
	      for(int i=0; i<=6; i++){
	         int n = (int) (Math.random() * 10);
	         buffer.append(n);
	      }
	      return buffer.toString();
	   }

	   //�쉶�썝媛��엯 �븣 �궗�슜�븯�뒗 �씠硫붿씪 �쟾�넚
	   private void joinVCsend(int check) {
	      mav=new ModelAndView();
	      Person p = new Person();
	      mav.addObject("idCheck", "�궗�슜 媛��뒫�븳 �븘�씠�뵒");
	      System.out.println(check);
	      if(check==1||check==3){
	         String val = req.getParameter("email3");
	         String email1 = req.getParameter("email1");
	         mav.addObject("email1", email1);
	         if(val.equals("9")){
	            System.out.println("�븯�씠");
	            String email2 = req.getParameter("email2");
	            mav.addObject("flag", 2);
	            mav.addObject("email7",email2);
	            p.setP_email(email1+"@"+email2);
	         }else{
	            System.out.println("�븯�씠222");
	            String email2 = req.getParameter("email3");
	            mav.addObject("flag", 1);
	            mav.addObject("email7",email2);
	            p.setP_email(email1+"@"+email2);
	         }
	         String email = p.getP_email();
	         p.setP_name(req.getParameter("p_name"));
	         System.out.println(email);
	            if(check==3){
	            p.setP_name(req.getParameter("p_name"));
	            System.out.println("泥댄겕3 email="+p.getP_email());
	            System.out.println("泥댄겕3 name="+p.getP_name());
	            int num = mDao.emailCheck2(p); //�븘�씠�뵒 李얘린 以� �씠由꾧낵 留욌뒗 �씠硫붿씪�씠 �엳�뒗吏� 泥댄겕
	            System.out.println("num="+num);
	               if(num==0){ //�씪移섑븯�뒗 �젙蹂닿� �뾾�쑝硫�(check==3)
	                  System.out.println("�뿬湲곗빞!");
	                  mav.addObject("check", 7);
	                  mav.addObject("email1", null);
	                  mav.addObject("flag", 5);
	                  mav.setViewName("SearchMember");
	               }else{ //�씪移섑븯�뒗 �젙蹂닿� �엳�떎硫�
	                  join(email);
	                  mav.addObject("p_name", p.getP_name());
	                  mav.addObject("check", 6);
	                  mav.setViewName("SearchMember");
	               }
	            }else{//check媛� 1�씪 �븣
	                  p.setP_id(req.getParameter("p_id"));
	                  mav.addObject("m_id", p.getP_id());
	                  mav.addObject("p_pw", req.getParameter("p_pw"));
	                  mav.addObject("p_name", req.getParameter("p_name"));
	                  mav.addObject("p_gender", req.getParameter("p_gender"));
	                  mav.addObject("phone1", req.getParameter("phone1"));
	                  mav.addObject("phone2", req.getParameter("phone2"));
	                  mav.addObject("phone3", req.getParameter("phone3"));
	                  int result = mDao.emailCheck4(p);
	                  System.out.println(result);
	                  if(result==0){
	                  join(email);
	                  mav.addObject("save", 1);
	                  mav.setViewName("JoinMember");
	                  }else{
	                     mav.addObject("email1", null);
	                     mav.addObject("flag",5);
	                     mav.setViewName("JoinMember");
	               }
	            }
	            
	      }else{
	         System.out.println("�븯�씠");
	         String email1 = req.getParameter("email4");
	         mav.addObject("email4", email1);
	         String val = req.getParameter("email6");
	         if(val.equals("9")){
	            String email2 = req.getParameter("email5");
	            mav.addObject("email7", email2);
	            mav.addObject("flag", 4);
	            p.setP_email(email1+"@"+email2);
	         }else{
	            String email2 = req.getParameter("email6");
	            mav.addObject("email7", email2);
	            mav.addObject("flag", 3);
	            p.setP_email(email1+"@"+email2);
	         }
	         String email = p.getP_email();
	         if(check==4){
	            p.setP_id(req.getParameter("p_id"));
	            mav.addObject("p_id", req.getParameter("p_id"));
	            int num = mDao.emailCheck3(p); //鍮꾨�踰덊샇 李얘린 以� �븘�씠�뵒�� �씪移섑븯�뒗 �씠硫붿씪�씠�씠 �엳�뒗吏� 泥댄겕
	            System.out.println("num2="+num);
	            if(num==0){
	               mav.addObject("email4", null);
	               mav.addObject("flag", 6);
	               mav.addObject("check", 7);
	               mav.setViewName("SearchMember");
	            }else{
	               join(email);
	               mav.addObject("check",6);
	               mav.addObject("pid", req.getParameter("p_id"));
	               mav.setViewName("SearchMember");
	               session.setAttribute("find", 2);
	            }
	         }else{
	            join(email);
	            System.out.println("�씠硫붿씪 �쟾�넚");
	            mav.addObject("b_id",req.getParameter("b_id"));
	            mav.addObject("p_name",req.getParameter("p_name2"));
	            mav.addObject("p_gender", req.getParameter("p_gender2"));
	            mav.addObject("p_pw",req.getParameter("p_pw3"));
	            mav.addObject("phone4", req.getParameter("phone4"));
	            mav.addObject("phone5", req.getParameter("phone5"));
	            mav.addObject("phone6", req.getParameter("phone6"));
	            mav.addObject("save", 2);
	            mav.setViewName("JoinMember");
	         }
	         
	      }
	   }

	   


	   private void join(String email){
	      System.out.println(email);
	      String host = "smtp.gmail.com"; //smtp 二쇱냼
	      String subject = "�꽭�긽�냼媛쒗똿 �씤利앸찓�씪"; 
	      String fromName = "�꽭�긽�냼媛쒗똿 愿�由ъ옄";
	      String from = "tofha56@gmail.com";  //蹂대궡�뒗 �궗�엺 二쇱냼
	      //String to1 = email1+"@"+email;
	      String authNum = (String) session.getAttribute("Num"); //RandomNum�븿�닔瑜� 諛쒖깮�떆耳쒖꽌 ���옣�맂 �궃�닔
	      System.out.println(authNum);
	      String content = "�씤利앸쾲�샇 : [" + authNum + "]";
	      try{
	         Properties props = new Properties();
	         //G-mail SMTP 蹂대궡�뒗 怨쇱젙
	         props.put("mail.smtp.starttls.enable", "true");
	         props.put("mail.transport.protocol", "smtp");
	         props.put("mail.smtp.host", host);
	         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	         props.put("mail.smtp.port", "465");
	         props.put("mail.smtp.user", from);
	         props.put("mail.smtp.auth", "true");

	         Session mailSession = Session.getInstance(props,
	               new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("tofha56","kk578578578");
	            }
	         });
	         Message msg = new MimeMessage(mailSession);
	         msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(
	               fromName, "UTF-8", "B")));

	         InternetAddress[] address1 = { new InternetAddress(email) };
	         msg.setRecipients(Message.RecipientType.TO, address1); 
	         msg.setSubject(subject); 
	         msg.setSentDate(new java.util.Date()); // 蹂대궡�뒗 �궇吏�
	         msg.setContent(content, "text/html; charset=euc-kr");

	         Transport.send(msg); 
	         session.setAttribute("Num", authNum);
	         mav.addObject("Num", authNum);
	      }catch (MessagingException e){
	         e.printStackTrace();
	      }catch (Exception e){
	         e.printStackTrace();
	      }
	   }
	   
	   private void login(Person p) {
		      mav=new ModelAndView();
		      String view = null;
		      BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		      System.out.println(p.getP_id());
		      String pwdEncode = mDao.getSecurityPwd(p.getP_id());
		      System.out.println("pw="+pwdEncode);
		      if(pwdEncode!=null){
		         if(pwdEncoder.matches(p.getP_pw(), pwdEncode)){
		            p.setP_id(req.getParameter("p_id"));
		            p.setP_pw(pwdEncode);
		            System.out.println(mDao.getLoginResult(p));
		            if(mDao.getLoginResult(p)!=0){ //濡쒓렇�씤 寃곌낵
		               session.setAttribute("p_id", p.getP_id());
		               System.out.println(p.getP_id());
		               mav.addObject("p", p);
		               int flag=mDao.getFlag(p.getP_id());
		               if(flag==1){
		                  System.out.println("�쉶�썝");
		                  int count=ns.messageCheck();
		        	      mav.addObject("count",count);
		                  view="PersonMain";
		               }else if(flag==2){
		                  System.out.println("�궗�뾽�옄");
		                  int count=ns.messageCheck();
		        	      mav.addObject("count",count);
		                  view="BusinessMain";
		               }else if(flag==3){//�뵆�옒洹� �솗�씤 else
		                  System.out.println("愿�由ъ옄");
		                  int count=ns.messageCheck();
		        	      mav.addObject("count",count);
		                  view="AdminMain";
		               }else{
		                  System.out.println("�깉�눜�쉶�썝");
		                  mav.addObject("check",3);
		                  view="Login";
		               }
		            }else{//濡쒓렇�씤 寃곌낵 else
		               mav.addObject("check",1);
		               view="Login";
		            }
		         }else{//鍮꾨�踰덊샇 �씪移� else
		            mav.addObject("check",1);
		            view="Login";
		         }
		      }else{//id瑜� �넻�븳 pw�쓽 媛� else
		         mav.addObject("check",2);
		         view="Login";
		      }
		     
		      mav.setViewName(view);
		      return;
		   }
		}