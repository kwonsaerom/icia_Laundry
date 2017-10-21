package com.real.cu.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.Pointhistory;
import com.real.cu.bean.Readypoint;
import com.real.cu.bean.Reservation;
import com.real.cu.bean.Return;

@Repository("pointdao")
public class PointDao {

   @Autowired
   private SqlSessionTemplate sqlSession;
   
   public String memberselect(String id) {
      return sqlSession.selectOne("point.memberselect", id);
   }
   
   public List<Mileage> mileageselect(String id) {
      return sqlSession.selectList("point.mileageselect", id);
   }
   public void pointinsert(Pointhistory ph) {
      sqlSession.insert("point.pointinsert", ph);
   }
   public void memberReturnInsert(Return pr) {
      sqlSession.insert("point.memberReturnInsert", pr);
   }
   public void businessReturnInsert(Return pr) {
      sqlSession.insert("point.businessReturnInsert", pr);
   }
   public List<Return> returnselect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.returnselect", map);
   }
   public int kindselect(String id) {
      return sqlSession.selectOne("point.kindselect", id);
   }
   public List<Return> returnAllSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.returnAllSelect",map);
   }
   public List<Return> returnFlagSelect(LinkedHashMap<String, Object> rmap) {
      return sqlSession.selectList("point.returnFlagSelect", rmap);
   }
   public int returnUpdate(String code) {
      return sqlSession.update("point.returnUpdate", code);
   }
   public Return returnCodeSelect(String code) {
      return sqlSession.selectOne("point.returnCodeSelect", code);
   }
   public List<Reservation> reservationSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.reservationSelect", map);
   }
   public List<Reservation> reservationSelect2(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.reservationSelect2", map);
   }
   public Readypoint readypointSelect(String nr_code) {
      return sqlSession.selectOne("point.readypointSelect", nr_code);
   }
   public String personPhoneSelect(String id) {
      return sqlSession.selectOne("point.personPhoneSelect", id);
   }
   public String laundryNameSelect(String nl_code) {
      return sqlSession.selectOne("point.laundryNameSelect", nl_code);
   }
   public String phcodeSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectOne("point.phcodeSelect", map);
   }
   public void readypointUpdate(Readypoint rp) {
      sqlSession.update("point.readypointUpdate", rp);
   }
   public void reservationUpdate(Reservation r) {
      sqlSession.update("point.reservationUpdate", r);
   }
   public void pointUpdate(Pointhistory ph) {
      sqlSession.update("point.pointUpdate", ph);
   }
   public List<Pointhistory> pointPlusSelect(String id) {
      return sqlSession.selectList("point.pointPlusSelect", id);
   }
   public List<Mileage> mileagePlmiSelect(Mileage m) {
      return sqlSession.selectList("point.mileagePlmiSelect", m);
   }
   public List<Pointhistory> pointMinusSelect(String id) {
      return sqlSession.selectList("point.pointMinusSelect", id);
   }
   public int pointCount(String id) {
      return sqlSession.selectOne("point.pointCount", id);
   }
   public int mileageCount(String id) {
      return sqlSession.selectOne("point.mileageCount", id);
   }
   public int pointPlusCount(String id) {
      return sqlSession.selectOne("point.pointPlusCount", id);
   }
   public int pointMinusCount(String id) {
      return sqlSession.selectOne("point.pointMinusCount", id);
   }
   public List<Pointhistory> pointMapSelect(LinkedHashMap<String, Object> map) {
         return sqlSession.selectList("point.pointMapSelect", map);
   }
   public List<Pointhistory> pointselect(String id) {
      return sqlSession.selectList("point.pointselect", id);
   }
   public List<Mileage> mileageMapSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.mileageMapSelect", map);
   }
   public List<Pointhistory> pointPlusMapSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.pointPlusMapSelect", map);
   }

   public int mileagePlmiCount(Mileage m) {
      return sqlSession.selectOne("point.mileagePlmiCount", m);
   }

   public List<Mileage> mileagePlmiMapSelect(LinkedHashMap<String, Object> map2) {
      return sqlSession.selectList("point.mileagePlmiMapSelect", map2);
   }

   public List<Pointhistory> pointMinusMapSelect(LinkedHashMap<String, Object> map) {
      return sqlSession.selectList("point.pointMinusMapSelect", map);
   }

   public int returnAllCount(int i) {
      return sqlSession.selectOne("point.returnAllCount", i);
   }

   public int returnFlagCount(LinkedHashMap<String, Object> map) {
      return sqlSession.selectOne("point.returnFlagCount", map);
   }

   public int returnCount(String id) {
      return sqlSession.selectOne("point.returnCount", id);
   }

   public int reservationCount(int state) {
      return sqlSession.selectOne("point.reservationCount", state);
   }
   
   
   
   
}