package com.real.cu.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.real.cu.bean.Coin;
import com.real.cu.bean.Image;
import com.real.cu.bean.Laundryhandle;
import com.real.cu.bean.Member;
import com.real.cu.bean.Mileage;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Pointhistory;

@Repository("laundrydao")
public class LaundryDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Coin CoinDetailSelect(String cl_code) {
		return  sqlSession.selectOne("laundry.CoinDetailSelect", cl_code);
	}


	public List<Image> ImageSelect(String nl_code) {
		return sqlSession.selectList("laundry.ImageSelect",nl_code);
	}
	public void coinShopAdd(Coin co){
		sqlSession.insert("laundry.coinShopAdd",co);

	}

	public void lisencePictureAdd(LinkedHashMap<String, String> fMap) {
		sqlSession.insert("laundry.lisencePictureAdd",fMap);
	}

	public void shopPictureAdd(LinkedHashMap<String, String> fMap) {
		sqlSession.insert("laundry.shopPictureAdd",fMap);      
	}

	public List<Coin> CoinListSelect(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("laundry.CoinListSelect",map);
	}
	public int LaundryDelete(String string) {
		return sqlSession.delete("laundry.LaundryDelete", string);
	}
	public int CoinShopDelete(String string) {
		return sqlSession.delete("laundry.CoinShopDelete", string);
	}


	public void coinShopUpdate(LinkedHashMap<String, Object> fmap) {
		sqlSession.update("laundry.coinShopUpdate",fmap);
	}


	public List<N_laundry> LaundryAdminSelect(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("laundry.LaundryAdminSelect",map);
	}


	public List<Coin> CoinAdminSelect(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("laundry.CoinAdminSelect",map);
	}


	public List<N_laundry> LaundryKindSelect(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("laundry.LaundryKindSelect",map);
	}


	public List<Coin> CoinKindSelect(LinkedHashMap<String, Object> map) {

		return sqlSession.selectList("laundry.CoinKindSelect",map);
	}


	public Member addressSelect(String id) {
		return sqlSession.selectOne("laundry.addressSelect",id);
	}


	public List<N_laundry> SearchPlace(String keyword) {
		return sqlSession.selectList("laundry.SearchPlace",keyword);
	}


	public List<Coin> SearchCoinPlace(String keyword) {
		return sqlSession.selectList("laundry.SearchCoinPlace",keyword);
	}


	public List<N_laundry> laundryCheck() {
		return sqlSession.selectList("laundry.laundryCheck");
	}


	  public List<Coin> CoinShopCheck(LinkedHashMap<String, Integer> map) {
	       return sqlSession.selectList("laundry.CoinShopCheck",map);
	   }

	public void PerMissionUpdate(String code) {
		sqlSession.update("laundry.PerMissionUpdate",code);
	}


	public void CoinPerMissionUpdate(String code) {
		sqlSession.update("laundry.CoinPerMissionUpdate",code);
	}
	public void UpdateNlAddress(N_laundry nl) {
		sqlSession.update("laundry.UpdateNlAddress", nl);
	}

	public void acceptshopdelete(String code) {
		sqlSession.delete("laundry.acceptshopdelete",code);
	}
	public void Coinacceptshopdelete(String code) {
		sqlSession.delete("laundry.Coinacceptshopdelete",code);
	}
	public int InsertN_laundry(N_laundry nl) {
		return sqlSession.insert("laundry.InsertN_laundry", nl);
	}
	public void insertLaundryHandle(LinkedHashMap<Object, Object> lhMap) {
		sqlSession.insert("laundry.insertLaundryHandle", lhMap);
	}
	public List<N_laundry> LaundrySelect(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("laundry.LaundrySelect",map);
	}
	public N_laundry LaundryDetailSelect(String nl_code) {
		return sqlSession.selectOne("laundry.LaundryDetailSelect", nl_code);
	}
	public List<Laundryhandle> HandleSelect(String nl_code) {
		return sqlSession.selectList("laundry.HandleSelect", nl_code);
	}
	public int updateN_laundry(N_laundry nl) {
		return sqlSession.update("laundry.updateN_laundry", nl);
	}
	public void laundryHandleDelete(Laundryhandle lh) {
		sqlSession.delete("laundry.laundryHandleDelete", lh);
	}


	public void imgDelete(Image image) {
		sqlSession.delete("laundry.imgDelete", image);

	}


	public N_laundry shopListSearch(String NLCODE) {

		return sqlSession.selectOne("laundry.shopListSearch",NLCODE);
	}


	public int CoinshopCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("laundry.CoinshopCount",map); 
	}


	public int laundryCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("laundry.laundryCount",map); 
	}
	public List<N_laundry> laundryCheck(LinkedHashMap<String, Integer> map) {
		return sqlSession.selectList("laundry.laundryCheck",map);
	}
	public int laundryCheckCount() {
		return sqlSession.selectOne("laundry.laundryCheckCount");
	}
	public int CoinShopCheckCount() {
		return sqlSession.selectOne("laundry.CoinShopCheckCount");
	}


	public int LaundryAdminCount() {
		return sqlSession.selectOne("laundry.LaundryAdminCount"); 
	}


	public int CoinAdminCount() {
		return sqlSession.selectOne("laundry.CoinAdminCount"); 
	}


	public int LaundryKindSelectCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("laundry.LaundryKindSelectCount",map); 
	}


	public int CoinKindSelectCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("laundry.CoinKindSelectCount",map); 
	}


	public List<N_laundry> LaundrymapSelect() {
		return sqlSession.selectList("laundry.LaundrymapSelect");
	}


	public List<Coin> CoinmapSelect() {
		return sqlSession.selectList("laundry.CoinmapSelect");
	}
	public List<Mileage> getmileage(String id) {
	 return sqlSession.selectList("laundry.getmileage",id);
		      
		   }
    public List<Pointhistory> getPoint(String id) {
        return sqlSession.selectList("laundry.getPoint", id);
     }

  public int kindselect(String id) {
      return sqlSession.selectOne("laundry.kindselect", id);
  }







}