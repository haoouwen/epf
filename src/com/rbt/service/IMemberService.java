/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemberService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;
import com.rbt.model.Member;

/**
 * @function 功能 会员信息表Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Fri Jan 04 09:38:55 CST 2014
 */

public interface IMemberService extends IGenericService<Member,String>{
	public String insertMember(Member member);
	//根据会员名称获取会员对象
	public Member getByCustName(String cust_name);
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 7:16:19 PM
	 * @Method Description : 会员创建时初始化插入的数据
	 */
	public void insertInit(String cust_id);
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 7:16:19 PM
	 * @Method Description : 会员创建时或审核时初始化插入的数据
	 */
	public void insertChangeSeller(String cust_id);
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 3:06:25 PM
	 * @Method Description :取会员名
	 */
	public String getCustName(String cust_id);
	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 9:45:57 AM
	 * @Method Description :判断注册名是否允许
	 */
	public boolean isAllow(String user_name, String cfg_mb_notallow);
	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 10:56:02 AM
	 * @Method Description :前台会员初始化设置
	 */
	public void webInit(String cust_id);
	/**
	 * @author : HZX
	 * @date : Feb 17, 2014 11:15:15 AM
	 * @Method Description :判断是否存在该用户名，邮箱，手机号
	 */
	public List is_exist_user(String username);
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

