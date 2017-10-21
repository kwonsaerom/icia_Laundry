package com.real.cu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.service.NoteService;
import com.real.cu.service.PointService;

@Controller
public class PController {
   private ModelAndView mav;
   
   @Autowired
   @Qualifier("pointservice")
   PointService ps;
   
   @Autowired
   private NoteService ns;
/*   @RequestMapping(value = "/PersonMain")
   public ModelAndView PersonMain() {
      mav = new ModelAndView();
      System.out.println("PersonMain");
      mav.setViewName("PersonMain");      
      return mav;
   }*/
   @RequestMapping(value = "/myPoint")
   public ModelAndView myPoint() {
      mav = new ModelAndView();
      mav = ps.execute(1);
      int count=ns.messageCheck();
      mav.addObject("count",count);
      return mav;
   }
   @RequestMapping(value = "/PointAdd")
   public ModelAndView PointAdd() {
      mav = new ModelAndView();
      System.out.println("PointAdd");
      mav.setViewName("PointAdd");      
      return mav;
   }
   @RequestMapping(value = "/pointcharge")
   public ModelAndView pointcharge() {
      mav = new ModelAndView();
      System.out.println("pointcharge");
      mav = ps.execute(2);
      return mav;
   }
   @RequestMapping(value = "/PointReturn")
   public ModelAndView PointReturn() {
      mav = new ModelAndView();
      System.out.println("PointReturn");
      mav = ps.execute(3);
      return mav;
   }
   @RequestMapping(value = "/pointexchange")
   public ModelAndView pointexchange() {
      mav = new ModelAndView();
      System.out.println("pointexchange");
      mav = ps.execute(4);
      return mav;
   }
   @RequestMapping(value = "/pointMm")
   public ModelAndView pointMm() {
      mav = new ModelAndView();
      mav = ps.execute(5);
      int count=ns.messageCheck();
      mav.addObject("count",count);
      return mav;
   }
   @RequestMapping(value = "/pointRequest")
   public ModelAndView pointRequest() {
      mav = new ModelAndView();
      System.out.println("pointRequest");
      mav = ps.execute(6);
      return mav;
   }
   @RequestMapping(value = "/pointAccept")
   public ModelAndView pointAccept() {
      mav = new ModelAndView();
      System.out.println("pointAccept");
      mav = ps.execute(7);
      return mav;
   }
   @RequestMapping(value = "/pointMmSelect")
   public ModelAndView pointMmSelect() {
      mav = new ModelAndView();
      System.out.println("pointMmSelect");
      mav = ps.execute(8);
      return mav;
   }
   @RequestMapping(value = "/pointAdmin")
   public ModelAndView pointAdmin() {
      mav = new ModelAndView();
      System.out.println("pointAdmin");
      mav = ps.execute(9);
      return mav;
   }
   @RequestMapping(value = "/pointDelete")
   public ModelAndView pointDelete() {
      mav = new ModelAndView();
      System.out.println("pointDelete");
      mav = ps.execute(10);
      return mav;
   }
}