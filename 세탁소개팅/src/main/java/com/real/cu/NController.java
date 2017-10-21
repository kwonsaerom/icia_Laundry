package com.real.cu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.real.cu.service.NoteService;
@Controller
public class NController {
	 @Autowired
	 private NoteService ns;
	 
	private ModelAndView mav;
	@RequestMapping(value = "/messageSend")
	public ModelAndView messageSend() {
		mav = new ModelAndView();
		mav.setViewName("messageSend");		
		return mav;
	}
	@RequestMapping(value = "/message")
	   public ModelAndView message() {
	      mav = new ModelAndView();
	      mav = ns.execute(2);
	      return mav;
	   }	
	
	@RequestMapping(value = "/sendmessage")
	   public ModelAndView sendmessage() {
	      mav = new ModelAndView();
	      mav = ns.execute(3);
	      return mav;
	   }	
	@RequestMapping(value = "/messagesave")
	   public ModelAndView messagesave() {
	      mav = new ModelAndView();
	      mav = ns.execute(4);
	      return mav;
	   }
	   @RequestMapping(value = "/messagedelete")
	   public ModelAndView messagedelete() {
	      mav = new ModelAndView();
	      mav = ns.execute(5);
	      return mav;
	   }	
	   @RequestMapping(value = "/idSelect")
	   public ModelAndView idSelect() {
	      mav = new ModelAndView();
	      mav = ns.execute(6);
	      return mav;
	   }	
	   @RequestMapping(value = "/recevidSelect")
	   public ModelAndView recevidSelect() {
	      mav = new ModelAndView();
	      mav = ns.execute(7);
	      return mav;
	   }	
	   @RequestMapping(value = "/Messagedetail")
	   public ModelAndView Messagedetail() {
	      mav = new ModelAndView();
	      mav = ns.execute(8);
	      return mav;
	   }	
	   @RequestMapping(value = "/realmessageSend")
	   public ModelAndView realmessageSend() {
	      mav = new ModelAndView();
	      mav = ns.execute(9);
	      return mav;
	   }	
	   
	   	
}
