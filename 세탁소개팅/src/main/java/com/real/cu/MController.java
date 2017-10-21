package com.real.cu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.Person;
import com.real.cu.service.MemberService;
import com.real.cu.service.NoteService;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes("person")
@Controller
public class MController {
   @Autowired
   private MemberService ms;
   
   private ModelAndView mav;
   @Autowired
   private NoteService ns;
   @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView Home() {
		mav = new ModelAndView();
		mav.setViewName("home");		
		return mav;
	}
   @RequestMapping(value = "/LoginMV", method = RequestMethod.GET)
   public ModelAndView LoginMV() {
      mav = new ModelAndView();
      mav.setViewName("Login");      
      return mav;
   }

   @RequestMapping(value = "/JoinMemberMV")
   public ModelAndView joinMemberMV() {
      mav = new ModelAndView();
      mav.setViewName("redirect:/vcCheck");      
      return mav;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public ModelAndView login(Person p) {
      mav = new ModelAndView();
      mav = ms.execute(p, 1);
      return mav;
   }

   //email 전송 (1)
   @RequestMapping(value = "/joinVCsend")
   public ModelAndView joinVCsend() {
      mav = new ModelAndView();
      mav = ms.execute(1,5);
      return mav;
   }

   //email 전송 (2)
   @RequestMapping(value = "/joinVCsend2")
   public ModelAndView joinVCsend2() {
      mav = new ModelAndView();
      mav = ms.execute(2,5);
      return mav;
   }

   //email 전송 (3)
   @RequestMapping(value = "/joinVCsend3")
   public ModelAndView joinVCsend3() {
      mav = new ModelAndView();
      mav = ms.execute(3,5);
      return mav;
   }

   //email 전송 (4)
   @RequestMapping(value = "/joinVCsend4")
   public ModelAndView joinVCsend4() {
      mav = new ModelAndView();
      mav = ms.execute(4,5);
      return mav;
   }

   
   @RequestMapping(value = "/joinVCsend5")
   public ModelAndView joinVCsend5(Person p) {
      mav = new ModelAndView();
      mav = ms.execute(p,1,5);
      return mav;
   }
   
   @RequestMapping(value = "/joinVCsend6")
   public ModelAndView joinVCsend6(Person p) {
      mav = new ModelAndView();
      mav = ms.execute(p,2,5);
      return mav;
   }

   @RequestMapping(value = "/Check")
   public ModelAndView Check() {
      mav = new ModelAndView();
      mav.setViewName("Check");      
      return mav;
   }

   @RequestMapping(value = "/vcCheck")
   public ModelAndView vcCheck() {
      mav = ms.execute(3,11);
      return mav;
   }

   @RequestMapping(value = "/vcCheck2")
   public ModelAndView vcCheck2() {
      mav = ms.execute(4,11);
      return mav;
   }

   @RequestMapping(value = "/memberInsert")
   public ModelAndView memberInsert(Person p) {
      mav = ms.execute(p,2);
      return mav;
   }

   @RequestMapping(value = "/businessInsert")
   public ModelAndView businessInsert(Person p) {
      mav = ms.execute(p,3);
      return mav;
   }

   @RequestMapping(value = "/idCheck")
   public ModelAndView idCheck() {
      mav = ms.execute(1,4);
      return mav;
   }

   @RequestMapping(value = "/idCheck2")
   public ModelAndView idCheck2() {
      mav = ms.execute(2,4);
      return mav;
   }

   @RequestMapping(value = "/SearchMemberMV")
   public ModelAndView searchMemberMV() {
      mav = new ModelAndView();
      mav.setViewName("redirect:/vcCheck2");      
      return mav;
   }

   @RequestMapping(value = "/findId")
   public ModelAndView findId() {
      mav = new ModelAndView();
      mav = ms.execute(6);      
      return mav;
   }

   @RequestMapping(value = "/findPw")
   public ModelAndView findPw() {
      mav = new ModelAndView();
      mav = ms.execute(7);      
      return mav;
   }

   @RequestMapping(value = "/personUpdateMV")
   public ModelAndView PersonUpdateMV() {
      mav = new ModelAndView();
      mav = ms.execute(8);
      int count=ns.messageCheck();
      mav.addObject("count",count);
      return mav;
   }


   @RequestMapping(value = "/personMyPage")
   public ModelAndView personMyPage() {
      mav = new ModelAndView();
      mav = ms.execute(9);   
      int count=ns.messageCheck();
      mav.addObject("count",count);
      return mav;
   }

   @RequestMapping(value = "/pwCheck1")
   public ModelAndView pwCheck1() {
      mav = new ModelAndView();
      mav = ms.execute(1,10);   
      return mav;
   }
   
   @RequestMapping(value = "/pwCheck2")
   public ModelAndView pwCheck2() {
      mav = new ModelAndView();
      mav = ms.execute(2,10);   
      return mav;
   }
   
   @RequestMapping(value = "/personUpdate")
   public ModelAndView PersonUpdate(Person p) {
      mav = new ModelAndView();
      mav = ms.execute(p,12);   
      return mav;
   }
   
   @RequestMapping(value = "/MemberDeleteMV")
   public ModelAndView MemberDeleteMV() {
      mav = new ModelAndView();
      mav.setViewName("MemberDelete");
      return mav;
   }
   
   @RequestMapping(value = "/memberDelete")
   public ModelAndView memberDelete() {
      mav = new ModelAndView();
      mav = ms.execute(13);
      return mav;
   }
   
   @RequestMapping(value = "/businessUpdateMV")
   public ModelAndView businessUpdateMV() {
      mav = new ModelAndView();
      mav = ms.execute(14);
      return mav;
   }
   
   @RequestMapping(value = "/pwCheck3")
   public ModelAndView pwCheck3() {
      mav = new ModelAndView();
      mav = ms.execute(3,10);   
      return mav;
   }
   
   @RequestMapping(value = "/businessMyPage")
   public ModelAndView businessMyPage() {
      mav = new ModelAndView();
      mav = ms.execute(15);   
      return mav;
   }
   
   @RequestMapping(value = "/businessUpdate")
   public ModelAndView businessUpdate(Person p) {
      mav = new ModelAndView();
      mav = ms.execute(p,16);
      return mav;
   }
   
   @RequestMapping(value = "/BusinessDeleteMV")
   public ModelAndView BusinessDeleteMV() {
      mav = new ModelAndView();
      mav.setViewName("BusinessDelete");
      return mav;
   }
   
   @RequestMapping(value = "/businessDelete")
   public ModelAndView businessDelete() {
      mav = new ModelAndView();
      mav = ms.execute(17);
      return mav;
   }
   
   @RequestMapping(value = "/pwCheck4")
   public ModelAndView pwCheck4() {
      mav = new ModelAndView();
      mav = ms.execute(4,10);   
      return mav;
   }
   
   @RequestMapping(value = "/blackList")
   public ModelAndView blackList() {
      mav = new ModelAndView();
      mav = ms.execute(18);
      return mav;
   }
   
   @RequestMapping(value = "/AdminMainMV")
   public ModelAndView AdminMainMV() {
      mav = new ModelAndView();
      mav.setViewName("AdminMain");
      return mav;
   }
   
   @RequestMapping(value = "/selectMemberList")
   public ModelAndView selectMemberList() {
      mav = new ModelAndView();
      mav = ms.execute(1,19);
      return mav;
   }
   
   @RequestMapping(value = "/changeClass")
   public ModelAndView changeClass() {
      mav = new ModelAndView();
      mav = ms.execute(0,20);
      return mav;
   }
   
   @RequestMapping(value = "/changeClass1")
   public ModelAndView changeClass1() {
      mav = new ModelAndView();
      mav = ms.execute(1,20);
      return mav;
   }
   
   @RequestMapping(value = "/changeClass2")
   public ModelAndView changeClass2() {
      mav = new ModelAndView();
      mav = ms.execute(2,20);
      return mav;
   }
   
   @RequestMapping(value = "/changeClass3")
   public ModelAndView changeClass3() {
      mav = new ModelAndView();
      mav = ms.execute(3,20);
      return mav;
   }
   
   @RequestMapping(value = "/adminDeleteMV")
   public ModelAndView adminDeleteMV() {
      mav = new ModelAndView();
      mav = ms.execute(21);
      return mav;
   }
   
   @RequestMapping(value = "/selectList")
   public ModelAndView selectList() {
      mav = new ModelAndView();
      mav = ms.execute(1,22);
      return mav;
   }
   
   @RequestMapping(value = "/personDelete")
   public ModelAndView personDelete() {
      mav = new ModelAndView();
      mav = ms.execute(0,23);
      return mav;
   }
   
   @RequestMapping(value = "/personDelete2")
   public ModelAndView personDelete2() {
      mav = new ModelAndView();
      mav = ms.execute(1,23);
      return mav;
   }
   
   @RequestMapping(value = "/personDelete3")
   public ModelAndView personDelete3() {
      mav = new ModelAndView();
      mav = ms.execute(2,23);
      return mav;
   }
   
   @RequestMapping(value = "/HomeInfoMV")
   public ModelAndView HomeInfoMV() {
      mav = new ModelAndView();
      mav.addObject("menu","공통");
      mav.setViewName("HomeInfo");
      return mav;
   }
   
   @RequestMapping(value = "/HomeInfoMV2")
   public ModelAndView HomeInfoMV2() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.addObject("menu","개인");
      mav.setViewName("HomeInfo");
      return mav;
   }
   
   @RequestMapping(value = "/HomeInfoMV3")
   public ModelAndView HomeInfoMV3() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.addObject("menu","사업자");
      mav.setViewName("HomeInfo");
      return mav;
   }
   @RequestMapping(value = "/logout")
   public ModelAndView logout() {
      mav = new ModelAndView();
      mav = ms.execute(22);
      return mav;
   }
   @RequestMapping(value = "/PersonMainMV")
   public ModelAndView PersonMainMV() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.setViewName("PersonMain");
      return mav;
   }
   
   @RequestMapping(value = "/BusinessMainMV")
   public ModelAndView BusinessMainMV() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.setViewName("BusinessMain");
      return mav;
   }
}   
   
   
   
   
   
   
   
