package com.real.cu.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.real.cu.bean.Laundryhandle;
import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Orderlist;
import com.real.cu.bean.Person;
import com.real.cu.bean.Readypoint;
import com.real.cu.bean.Reservation;

@Repository
public class ReservationDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Reservation> personReservation(LinkedHashMap<String, Object> map) {

		return sqlSession.selectList("Reservation.personReservation",map);
	}
	public int reviewCount(Reservation r) {
		return sqlSession.selectOne("Reservation.reviewCount",r);
	}
	public N_laundry laundryCart(String NLCODE) {

		return sqlSession.selectOne("Reservation.laundryCart",NLCODE);
	}

	public List<Laundryhandle> searchhandle(String NLCODE) {
		return sqlSession.selectList("Reservation.laundryhandle", NLCODE);
	}

	public List<Laundryhandle> searchhandle1(String NLCODE) {

		return sqlSession.selectList("Reservation.laundryhandle1", NLCODE);
	}

	public List<Laundryhandle> searchhandle2(String NLCODE) {

		return sqlSession.selectList("Reservation.laundryhandle2", NLCODE);
	}

	public List<Laundryhandle> searchhandle3(LinkedHashMap<String, String> fMap) {

		return sqlSession.selectList("Reservation.laundryhandle3", fMap);
	}

	public List<Reservation> reservationAdN2() {
		return sqlSession.selectList("Reservation.reservationAdN2");
	}

	public List<N_laundry> reservationAdN1() {
		return sqlSession.selectList("Reservation.reservationAdN1");
	}


	public List<N_laundry> getcode(String nr_nlcode) {
		return sqlSession.selectList("Reservation.getcode",nr_nlcode);
	}

	public List<N_laundry> searchTime(String NLCODE) {
		return sqlSession.selectList("Reservation.searchTime",NLCODE);
	}

	public List<Laundryhandle> searchMoney(LinkedHashMap<String, String> lMap) {
		return sqlSession.selectList("Reservation.searchMoney",lMap);
	}

	public void insertReservation(Reservation r) {

		sqlSession.insert("Reservation.insertReservation", r);
	}

	public void insertReadypoint(Readypoint rp) {
		sqlSession.insert("Reservation.insertReadypoint", rp);

	}

	public String getnrcode(String NLCODE) {
		return sqlSession.selectOne("Reservation.getnrcode",NLCODE);
	}

	public String getbusiness(String NLCODE) {
		return sqlSession.selectOne("Reservation.getbusiness",NLCODE);
	}
	public List<Reservation> reservationList(String code) {
		return sqlSession.selectList("Reservation.reservationList",code);
	}
	public List<N_laundry> getshop(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("Reservation.getshop",map);
	}

	public List<Person> getPerson(String pid) {
		return sqlSession.selectList("Reservation.getPerson",pid);
	}

	public List<Member> getMember(String id) {
		return sqlSession.selectList("Reservation.getMember",id);
	}

	public List<Orderlist> getordercode(String nrcode) {
		return sqlSession.selectList("Reservation.getrodercode",nrcode);
	}
	public void laundryProcessing(LinkedHashMap<String, Object> fMap) {
		sqlSession.update("Reservation.laundryProcessing",fMap);
	}

	public List<Laundryhandle> handle(String o_lhcode) {
		return sqlSession.selectList("Reservation.handle",o_lhcode);
	}
	public int ReservationCount(String id) {
		return sqlSession.selectOne("Reservation.ReservationCount",id);
	}
	public void createOrderList(Orderlist o) {
		sqlSession.insert("Reservation.createOrderList",o);
	}
	public void createReservation(Reservation r) {
		sqlSession.insert("Reservation.createReservation", r);
	}

	public String nrcode(String PID) {
		return sqlSession.selectOne("Reservation.nrcode",PID);
	}
	public void deleteOrderList(Orderlist o) {
		sqlSession.delete("Reservation.deleteOrderList", o);

	}
	public void updateReservation(Reservation r) {
		sqlSession.update("Reservation.updateReservation", r);
	}
	public String selectReservation(Reservation r) {
		return sqlSession.selectOne("Reservation.selectReservation",r);
	}

	public void deleteReservation(Reservation r) {

		sqlSession.delete("Reservation.deleteReservation", r);

	}
	public void updateMileage(Mileage m) {
		sqlSession.update("Reservation.updateMileage",m);
	}
	public List<Reservation> getcnt(String nlcode) {
		return sqlSession.selectList("Reservation.getcnt",nlcode);
	}
	public List<Reservation> getreservation(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("Reservation.getreservation",map);
	}
	public List<Reservation> getselectreservation(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("Reservation.getselectreservation",map);
	}
	public List<N_laundry> getselectcnt(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("Reservation.getselectcnt",map);
	}
	public Integer getreadypoint(String nr_code) {
		return sqlSession.selectOne("Reservation.getreadypoint",nr_code);
	}


}
