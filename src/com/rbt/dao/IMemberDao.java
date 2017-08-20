/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemberDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Member;
import com.rbt.model.Memberuser;

/**
 * @function 功能 会员信息表dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Fri Jan 04 09:38:55 CST 2014
 */

public interface IMemberDao extends IGenericDao<Member,String>{
	/**
	 * 方法描述：插入会员并且返回该会员ID
	 * 
	 * @param member
	 */
	public String insertMember(Member member);
	//通过会员名获取对象
	public Member getByCustName(String cust_name);
	/**
	 * 各个区域的会员数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getAreaMember(Map map) throws Exception;
	
	/**
	 * 各个区域的会员消费的总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getAreaTotalAmountList(Map map) throws Exception;
}

