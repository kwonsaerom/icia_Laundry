package com.real.cu.bean;

import java.util.Date;

public class Notice {
   String nt_code;
   String nt_aid;
   String nt_title;
   String nt_content;
   Date nt_date;
   int nt_click;
   int nt_categori;
   
   public int getNt_categori() {
      return nt_categori;
   }
   public void setNt_categori(int nt_categori) {
      this.nt_categori = nt_categori;
   }
   public String getNt_code() {
      return nt_code;
   }
   public void setNt_code(String nt_code) {
      this.nt_code = nt_code;
   }
   public String getNt_aid() {
      return nt_aid;
   }
   public void setNt_aid(String nt_aid) {
      this.nt_aid = nt_aid;
   }
   public String getNt_title() {
      return nt_title;
   }
   public void setNt_title(String nt_title) {
      this.nt_title = nt_title;
   }
   public String getNt_content() {
      return nt_content;
   }
   public void setNt_content(String nt_content) {
      this.nt_content = nt_content;
   }
   public Date getNt_date() {
      return nt_date;
   }
   public void setNt_date(Date nt_date) {
      this.nt_date = nt_date;
   }
   public int getNt_click() {
      return nt_click;
   }
   public void setNt_click(int nt_click) {
      this.nt_click = nt_click;
   }
}