package com.real.cu;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.bean.N_laundry;
import com.real.cu.service.NoteService;
import com.real.cu.service.ReservationService;



@Controller
public class RController {

	private ModelAndView mav;

	@Autowired
	private ReservationService rs;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private NoteService ns;
	@RequestMapping(value = "/personReservation", method = RequestMethod.GET)
	public ModelAndView PersonReservation() {
		N_laundry nl = new N_laundry();
		mav=rs.execute(1,nl);  
		int count=ns.messageCheck();
	    mav.addObject("count",count);
		return mav;
	}

	@RequestMapping(value = "/shopCart")
	public ModelAndView reply(@RequestParam(value="userId") String NLCODE){
		System.out.println("아작스들어옴");
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		nl.setNl_code(NLCODE);
		System.out.println(NLCODE);
		mav=rs.execute(2,nl);
		return mav;
	}

	@RequestMapping(value = "/shopCart2")
	public ModelAndView reply2(@RequestParam(value="selectedValue") String T_NAME){
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		nl.setNl_content(T_NAME);
		System.out.println(T_NAME);
		mav=rs.execute(4,nl);
		return mav;
	}

	   @RequestMapping(value = "/laundryReservAdN", method = RequestMethod.GET)
	   public ModelAndView LaundryReservAd() throws ParseException {
	      String text=request.getParameter("text");
	      String date=request.getParameter("date");
	      mav = new ModelAndView();
	      N_laundry nl = new N_laundry();
	      mav=rs.execute(16,nl,text,date);
	      mav.setViewName("laundryReservAd");
	      return mav;
	   }   

	@RequestMapping(value = "/givetakeSelect", method = RequestMethod.GET)
	public ModelAndView givetakeSelect() {
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		String LH = request.getParameter("LH_LAUNDRY");              // 품목 소분류  
		int QTY = Integer.parseInt(request.getParameter("QTY"));	// 수량
		int NUM = Integer.parseInt(request.getParameter("NUM"));	// 추가된줄
		nl.setNl_content(LH);
		nl.setNl_qty(QTY);
		nl.setNl_repair(NUM);
		mav=rs.execute(10,nl);
		return mav;
	}
	@RequestMapping(value = "/givetakeSelect2", method = RequestMethod.GET)
	public ModelAndView givetakeSelect2() {
			mav = new ModelAndView();
			N_laundry nl = new N_laundry();
			int elem = Integer.parseInt(request.getParameter("elem"));
			nl.setNl_qty(elem);
			System.out.println(elem+"번쨰줄 지움");
			mav=rs.execute(11,nl);
		return mav;

	}
	@RequestMapping(value = "/coinPayComplete", method = RequestMethod.GET)
	public ModelAndView coinPayComplete() {
		mav = new ModelAndView();
		N_laundry nl = new N_laundry();
		mav=rs.execute(6,nl);
		return mav;
	}
	@RequestMapping(value = "/reservationList")
	   public ModelAndView reservationList() {
	      mav = new ModelAndView();
	      mav=rs.execute(9);
	      return mav;
	   }
	 @RequestMapping(value = "/selectState")
	   public ModelAndView selectState() {
	      mav = new ModelAndView();
	      mav=rs.execute(10);
	      return mav;
	   }
	  @RequestMapping(value = "/laundryProcessing", method = RequestMethod.GET)
	   public ModelAndView laundryProcessing() {
	      mav = new ModelAndView();
	      mav=rs.execute(11);
	      return mav;
	   }
	  @RequestMapping(value = "/ReservationShop")
      public ModelAndView ReservationShop() {
         mav = new ModelAndView();
         mav=rs.execute(12);
         int count=ns.messageCheck();
         mav.addObject("count",count);
         return mav;
      }
	
	@RequestMapping(value = "/shopCart3")
	public ModelAndView reply3(@RequestParam(value="selectedValue2") String T_NAME2) {
			mav = new ModelAndView();
		//	mav=rs.execute(6);
			
			System.out.println(T_NAME2);
			System.out.println("카트33333333");
			mav.setViewName("ShopDetailCart2");
			return mav;
	}
	
// 광영 8월 27일 추가
	 @RequestMapping(value = "/mgSubmit")
	   public ModelAndView mgSubmit(@RequestParam(value="mg") String MG) {
	         mav = new ModelAndView();
	         
	         N_laundry nl = new N_laundry();
	         int MGpoint =Integer.parseInt(MG);
	         nl.setNl_repair(MGpoint);
	         
	         System.out.println(MG);
	         System.out.println("마일리지적용");
	         mav=rs.execute(12,nl);
	         
	         
	         return mav;
	   }
	
}
