package com.real.cu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.real.cu.bean.Business;
import com.real.cu.bean.Member;
import com.real.cu.bean.Person;

@Repository
public class MemberDao {
   @Autowired
   private SqlSessionTemplate sqlSession;

   public String getSecurityPwd(String p_id) {
      return sqlSession.selectOne("member.getSecurityPwd",p_id);
   }

   public int getLoginResult(Person p) {
      return sqlSession.selectOne("member.getLoginResult",p);
   }

   public int getFlag(String p_id) {
      return sqlSession.selectOne("member.getFlag", p_id);
   }

   public int idCheck(String p_id) {
      return sqlSession.selectOne("member.idCheck", p_id);
   }

   public int personInsert(Person p) {
      return sqlSession.insert("member.personInsert", p);
   }

   public int memberInsert(Member m) {
      return sqlSession.insert("member.memberInsert", m);
   }

   public int businessInsert(Business b) {
      return sqlSession.insert("member.businessInsert", b);
   }

   public int emailCheck(String to1) {
      return sqlSession.selectOne("member.emailCheck", to1);
   }

   public int findId(Person p) {
      return sqlSession.selectOne("member.findId", p);
   }

   public String getId(Person p) {
      return sqlSession.selectOne("member.getId", p);
   }

   public int findPw(Person p) {
      return sqlSession.selectOne("member.findPw", p);
   }

   public int changePw(Person p) {
      return sqlSession.update("member.changePw", p);
   }

   public Person personUpdatePage(String p_id) {
      return sqlSession.selectOne("member.personUpdatePage",p_id);
   }

   public Member memberUpdatePage(String p_id) {
      return sqlSession.selectOne("member.memberUpdatePage",p_id);
   }

   public int personUpdate(Person p) {
      return sqlSession.update("member.personUpdate", p);
   }

   public int memberUpdate(Member m) {
      return sqlSession.update("member.memberUpdate", m);
   }

   public int personDelete(String p_id) {
      return sqlSession.delete("member.personDelete", p_id);
   }

   public Business businessUpdatePage(String p_id) {
      return sqlSession.selectOne("member.businessUpdatePage", p_id);
   }

   public List<Person> getPersonList() {
      return sqlSession.selectList("member.getPersonList");
   }

   public List<Person> getMemberList() {
      return sqlSession.selectList("member.getMemberList");
   }

   public List<Person> getBusinessList() {
      return sqlSession.selectList("member.getBusinessList");
   }

   public List<Person> getBlackList() {
      return sqlSession.selectList("member.getBlackList");
   }

   public int addBlackList(String p_id) {
      return sqlSession.update("member.addBlackList", p_id);
   }

   public int removeBlack(String p_id) {
      return sqlSession.update("member.removeBlack", p_id);
   } 
   
   public List<Person> getMemberList2() {
      return sqlSession.selectList("member.getMemberList2");
   }

   public List<Person> getBusinessList2() {
      return sqlSession.selectList("member.getBusinessList2");
   }

   public List<Person> getDeleteList() {
      return sqlSession.selectList("member.getDeleteList");
   }
   
   public int addDeleteList(String p_id) {
      return sqlSession.update("member.addDeleteList", p_id);
   }

   public int emailCheck2(Person p) {
      return sqlSession.selectOne("member.emailCheck2", p);
   }

   public int emailCheck3(Person p) {
      return sqlSession.selectOne("member.emailCheck3", p);
   }

   public int emailCheck4(Person p) {
      return sqlSession.selectOne("member.emailCheck4", p);
   }
}