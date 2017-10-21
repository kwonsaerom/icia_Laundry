package com.real.cu.bean;

import java.util.Date;

public class Pointhistory {
   String ph_code;
   String ph_pid;
   int ph_point;
   int ph_history;
   Date ph_date;
   String ph_process;
   public String getPh_process() {
      return ph_process;
   }
   public void setPh_process(String ph_process) {
      this.ph_process = ph_process;
   }
   public String getPh_code() {
      return ph_code;
   }
   public void setPh_code(String ph_code) {
      this.ph_code = ph_code;
   }
   public String getPh_pid() {
      return ph_pid;
   }
   public void setPh_pid(String ph_pid) {
      this.ph_pid = ph_pid;
   }
   public int getPh_point() {
      return ph_point;
   }
   public void setPh_point(int ph_point) {
      this.ph_point = ph_point;
   }
   public int getPh_history() {
      return ph_history;
   }
   public void setPh_history(int ph_history) {
      this.ph_history = ph_history;
   }
   public Date getPh_date() {
      return ph_date;
   }
   public void setPh_date(Date ph_date) {
      this.ph_date = ph_date;
   }
}