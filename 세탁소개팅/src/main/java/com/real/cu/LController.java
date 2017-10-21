package com.real.cu;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.service.LaundryService;
import com.real.cu.service.NoteService;

@Controller
public class LController {

   private ModelAndView mav;
   @Autowired
   private HttpServletRequest req;
   @Autowired
   private NoteService ns;
   
   @Autowired
   @Qualifier("laundryservice")
   private LaundryService ls;
   @RequestMapping(value = "/PersonMain")
   public ModelAndView PersonMain() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.setViewName("PersonMain");      
      return mav;
   }
   @RequestMapping(value = "/LaundryMethodMv")
   public ModelAndView LoundryMethodMv() {
      mav = new ModelAndView();
      mav.setViewName("LaundryMethod");
      return mav;
   }
   @RequestMapping(value = "/BusinessMain")
   public ModelAndView BusinessMain() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.setViewName("BusinessMain");      
      return mav;
   }
   @RequestMapping(value = "/shopListSelect")
   public ModelAndView shopListSelect() {
      mav = new ModelAndView();
      mav=ls.execute(1);
      int count=ns.messageCheck();
      mav.addObject("count",count);
      return mav;
   }
   @RequestMapping(value = "/AdminMain")
   public ModelAndView AdminMain() {
      mav = new ModelAndView();
      int count=ns.messageCheck();
      mav.addObject("count",count);
      mav.setViewName("AdminMain");      
      return mav;
   }
   /*   @RequestMapping(value = "/laundrychoice", method = RequestMethod.GET)
   public ModelAndView laundrychoice() {
      mav = new ModelAndView();
      mav=ls.execute(19);       
      return mav;
   }
    */   @RequestMapping(value = "/laundryManage", method = RequestMethod.GET)
    public ModelAndView laundryManage() {
       mav = new ModelAndView();
       mav=ls.execute(15);    
       return mav;
    }
/*    @RequestMapping(value = "/laundryManagechange", method = RequestMethod.GET)
    public ModelAndView laundryManagechange(@RequestParam(value="selectedValue") String shopchoice) {
       mav = new ModelAndView();
         
       return mav;
    }*/
    @RequestMapping(value = "/coinShopUpdate", method = RequestMethod.GET)
    public ModelAndView coinShopUpdate() {
       System.out.println("cotroller");
       mav = new ModelAndView();
       mav=ls.execute(18);       
       return mav;
    }
    

    @RequestMapping(value = "/CoinShopAddmv", method = RequestMethod.GET)
    public ModelAndView CoinShopAddmv() {
       mav = new ModelAndView();
       mav.setViewName("CoinShopAdd");      
       return mav;
    }
    @RequestMapping(value = "/coinShopAdd",method = RequestMethod.POST)
    public ModelAndView coinShopAdd(MultipartHttpServletRequest multi) throws IllegalStateException, IOException{
       mav = new ModelAndView();
       mav=ls.execute(multi,1); 
       return mav;
    }

    @RequestMapping(value = "/laundrysearch")
    public ModelAndView laundrysearch(){
       mav = new ModelAndView();
       mav=ls.execute(19); 
       //mav.setViewName("BusinessMain");   
       return mav;
    }
    @RequestMapping(value = "/shopdelete")
    public ModelAndView shopdelete(){
       mav = new ModelAndView();
       mav=ls.execute(19); 
       return mav;
    }
    @RequestMapping(value = "/coinShopDetail")
    public ModelAndView coinShopDetail(){
       mav = new ModelAndView();
       mav=ls.execute(3); 
       return mav;
    }
    @RequestMapping(value = "/shopDetail")
    public ModelAndView ShopDetail(){
       mav = new ModelAndView();
    //   mav=ls.execute(7);  상욱
       mav=ls.execute(22); 
       return mav;
    }  @RequestMapping(value = "/coinShopList")
    public ModelAndView coinShopList(){
        mav = new ModelAndView();
     //   mav=ls.execute(7);  상욱
        mav=ls.execute(23);
        int count=ns.messageCheck();
        mav.addObject("count",count);
        return mav;
    }
    
/*	//매장찾기 광영
	@RequestMapping(value = "/laundryFind", method = RequestMethod.GET)
	public ModelAndView LaundryFind() {
		System.out.println("!!");
		mav=ls.execute(22);  
		return mav;
	}
*/
    
    
    
    @RequestMapping(value = "/laundryCheck")
    public ModelAndView laundryCheck(){
       mav = new ModelAndView();
       mav=ls.execute(13); 
       return mav;
    }
    @RequestMapping(value = "/SearchPlace")
    public ModelAndView SearchPlace(){
       mav = new ModelAndView();
       mav=ls.execute(20); 
       int count=ns.messageCheck();
       mav.addObject("count",count);
       return mav;
    }
    @RequestMapping(value = "/laundryAccept")
    public ModelAndView laundryAccept(){
       mav = new ModelAndView();
       mav=ls.execute(14); 
       return mav;
    }
    @RequestMapping(value = "/acceptshopdelete")
    public ModelAndView acceptshopdelete(){
       mav = new ModelAndView();
       mav=ns.execute(10);
       mav=ls.execute(21); 
       return mav;
    }
    
    @RequestMapping(value = "/laundryDelete")
    public ModelAndView laundryDelete(){
       mav = new ModelAndView();
       mav=ls.execute(11); 
       return mav;
    }
    @RequestMapping(value = "/coinShopDelete")
    public ModelAndView coinShopDelete(){
       mav = new ModelAndView();
       mav=ls.execute(4); 
       //mav.setViewName("BusinessMain");   
       return mav;
    }
    @RequestMapping(value = "/shoplogin")
    public ModelAndView shoplogin(){
       mav = new ModelAndView();
       req.setAttribute("msg", "로그인이 필요합니다.");
       mav.setViewName("Login");
       return mav;
    }
    ////////////////////
    @RequestMapping(value = "/LaundryAddmv")
    public ModelAndView LaundryAddmv() {
       mav = new ModelAndView();
       mav.setViewName("LaundryAdd");      
       return mav;
    }

    @RequestMapping(value = "/updateCoinShop")
    public ModelAndView updateCoinShop(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
       mav = new ModelAndView();
       mav=ls.execute(multi,3);
       return mav;
    }
    @RequestMapping(value = "/laundryAdd")
    public ModelAndView laundryAdd(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
       System.out.println("laundryAdd");
       mav = new ModelAndView();
       mav=ls.execute(multi,2);
       return mav;
    }
    @RequestMapping(value = "/coinShopMm")
    public ModelAndView coinShopMm() {
       mav = new ModelAndView();
       mav=ls.execute(2);       
       return mav;
    }
    @RequestMapping(value = "/LaundryAddPop")
    public ModelAndView LaundryAddPop() {
       mav = new ModelAndView();
       mav=ls.execute(9);
       return mav;
    }
    @RequestMapping(value = "/possibleItem")
    public ModelAndView possibleItem() {
       mav = new ModelAndView();
       mav=ls.execute(10);
       return mav;
    }
    @RequestMapping(value = "/laundryMm")
    public ModelAndView laundryMm() {
       mav = new ModelAndView();
       mav = ls.execute(12);
       return mav;
    }
    @RequestMapping(value = "/laundryUpdate")
      public ModelAndView laundryUpdate() {
         mav = new ModelAndView();
         mav = ls.execute(16);
         return mav;
      }
    @RequestMapping(value = "/laundryUpdatePop")
      public ModelAndView laundryUpdatePop() {
         mav = new ModelAndView();
         mav = ls.execute(5);
         return mav;
      }
    @RequestMapping(value = "/possibleItemUpdate")
      public ModelAndView possibleItemUpdate() {
         mav = new ModelAndView();
         mav = ls.execute(6);
         return mav;
      }
    
    @RequestMapping(value = "/updateLaundry")
      public ModelAndView updateLaundry(MultipartHttpServletRequest multi) throws IllegalStateException, IOException {
         mav = new ModelAndView();
         mav=ls.execute(multi,4);
         return mav;
      }
    
    
	//매장찾기
/*	@RequestMapping(value = "/laundryFind", method = RequestMethod.GET)
	public ModelAndView LaundryFind() {
		System.out.println("!!");
		mav=ls.execute(1);  
		return mav;
	}
*/
    
    
}