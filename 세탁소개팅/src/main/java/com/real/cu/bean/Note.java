package com.real.cu.bean;

import java.util.Date;

public class Note {
   String n_code;
   Date n_date;
   String n_title;
   String n_content;
   String n_sendid;
   String n_takeid;
   int n_state;
   public String getN_code() {
      return n_code;
   }
   public void setN_code(String n_code) {
      this.n_code = n_code;
   }
   public String getN_title() {
      return n_title;
   }
   public void setN_title(String n_title) {
      this.n_title = n_title;
   }
   public Date getN_date() {
      return n_date;
   }
   public void setN_date(Date n_date) {
      this.n_date = n_date;
   }
   public String getN_content() {
      return n_content;
   }
   public void setN_content(String n_content) {
      this.n_content = n_content;
   }
   public String getN_sendid() {
      return n_sendid;
   }
   public void setN_sendid(String n_sendid) {
      this.n_sendid = n_sendid;
   }
   public String getN_takeid() {
      return n_takeid;
   }
   public void setN_takeid(String n_takeid) {
      this.n_takeid = n_takeid;
   }
   public int getN_state() {
      return n_state;
   }
   public void setN_state(int n_state) {
      this.n_state = n_state;
   }
}