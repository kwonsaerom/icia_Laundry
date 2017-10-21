package com.real.cu.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.real.cu.bean.Coin;
import com.real.cu.bean.N_laundry;
import com.real.cu.bean.Note;
import com.real.cu.bean.Person;
@Repository
public class NoteDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	public int messageCheck(String id) {
		return sqlSession.selectOne("note.messageCheck", id);
	}
	public List<Note> messageForm(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("note.messageForm",map);
	}
	public List<Note> sendmessage(LinkedHashMap<String, Object> map) {
		return sqlSession.selectList("note.sendmessage",map);
	}
	public void Messagesave(Note no) {
		sqlSession.update("note.Messagesave",no);
	}
	public void Messagedelete(Note no) {
		sqlSession.delete("note.Messagedelete",no);
	}
	public List<N_laundry> laundryId(String n_takeid) {
		return sqlSession.selectList("note.laundryId",n_takeid);
	}
	public Note MessageDetail(String n_code) {
		return sqlSession.selectOne("note.MessageDetail", n_code);
	}
	public List<Coin> coinId(String n_takeid) {
		return sqlSession.selectList("note.coinId",n_takeid);
	}
	public void realmessageSend(Note nt) {
		sqlSession.insert("note.realmessageSend",nt);
	}
	public void MessageUpdate(String n_code) {
		sqlSession.update("note.MessageUpdate",n_code);      
	}
	public String shopIdSelect(String code){
		return sqlSession.selectOne("note.shopIdSelect", code);
	}
	public void acceptshopdeleteSend(LinkedHashMap<String, Object> map) {
		sqlSession.insert("note.acceptshopdeleteSend",map);
	}
	public String CoinshopIdSelect(String code) {
		return sqlSession.selectOne("note.CoinshopIdSelect", code);
	}
	public void CoinacceptshopdeleteSend(LinkedHashMap<String, Object> map) {
		sqlSession.insert("note.CoinacceptshopdeleteSend",map);
	}
	public int messageCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("note.messageCount",map); 
	}
	public int sendmessageCount(LinkedHashMap<String, Object> map) {
		return sqlSession.selectOne("note.sendmessageCount",map); 
	}
	public List<Person> personId(String n_takeid) {
	      return sqlSession.selectList("note.personId",n_takeid);
	   }
}