package com.real.cu.bean;

import java.util.Date;


public class Request {
   String r_code;
   String r_pid;
   String r_title;
   String r_content;
   Date r_date;
   int r_state;
   
   public String getR_content() {
      return r_content;
   }
   public void setR_content(String r_content) {
      this.r_content = r_content;
   }
   public String getR_code() {
      return r_code;
   }
   public void setR_code(String r_code) {
      this.r_code = r_code;
   }
   public String getR_pid() {
      return r_pid;
   }
   public void setR_pid(String r_pid) {
      this.r_pid = r_pid;
   }
   public String getR_title() {
      return r_title;
   }
   public void setR_title(String r_title) {
      this.r_title = r_title;
   }
   public Date getR_date() {
      return r_date;
   }
   public void setR_date(Date r_date) {
      this.r_date = r_date;
   }
   public int getR_state() {
      return r_state;
   }
   public void setR_state(int r_state) {
      this.r_state = r_state;
   }
   
}