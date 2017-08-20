/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemberDao.java 
 */
package com.rbt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberDao;
import com.rbt.model.Member;

/**
 * @function 功能  会员信息表dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 09:38:55 CST 2014
 */
@Repository
public class MemberDao extends GenericDao<Member,String> implements IMemberDao {
	
	public MemberDao() {
		super(Member.class);
	}
	public String insertMember(Member member){
		  return (String) this.getSqlMapClientTemplate().insert("member.insert",member);
		}
	public Member getByCustName(String cust_name) {
		return (Member) this.getSqlMapClientTemplate().queryForObject("member.getByCustName",cust_name);
	}
	
	/**
	 * 各个区域的会员数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getAreaMember(Map map) throws Exception{
		HashMap areaMemberMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getAreaMember",map);
		return areaMemberMap;
	}
	
	/**
	 * 各个区域的会员消费的总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getAreaTotalAmountList(Map map) throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getAreaTotalAmountList",map);
	}
}

