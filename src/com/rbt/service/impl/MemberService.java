/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemberService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IAftersalesDao;
import com.rbt.dao.IExpressfundDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMemberchannelDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IPrintstyleDao;
import com.rbt.dao.IPrintstyletemDao;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Expressfund;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.service.IMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员信息表Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 09:38:55 CST 2014
 */
@Service
public class MemberService extends GenericService<Member,String> implements IMemberService {
	
	@Autowired
	IMemberDao memberDao;
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IExpressfundDao expressfundDao;
	@Autowired
	private IMemberinterDao memberinterDao;
	@Autowired
	private IMemberuserDao memberuserDao;
	@Autowired
	private IMemberchannelDao memberchannelDao;
	@Autowired
	private IAftersalesDao aftersalesDao;
	@Autowired
	private IPrintstyleDao printstyleDao;
	@Autowired
	private IPrintstyletemDao printstyletemDao;
	
	@Autowired
	public MemberService(IMemberDao memberDao) {
		super(memberDao);
		this.memberDao = memberDao;
	}
	public String insertMember(Member member){
		return this.memberDao.insertMember(member);
	}
	public Member getByCustName(String cust_name) {
		return this.memberDao.getByCustName(cust_name);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 7:16:19 PM
	 * @Method Description : 会员创建时初始化插入的数据
	 */
	public void insertInit(String cust_id){
		// 将会员cust_id插入到memberfund表余额中，如果存在不作改动
		Memberfund mf = this.memberfundDao.get(cust_id);
		if(mf==null){
			Memberfund memberfund = new Memberfund();
			memberfund.setCust_id(cust_id);
			memberfund.setFreeze_num("0.00");
			memberfund.setFund_num("0.00");
			memberfund.setUse_num("0.00");
			this.memberfundDao.insert(memberfund);
		}
		// 向expressfund表直通车资金插入主键cust_id,如果存在不作改动
		Expressfund  epf = this.expressfundDao.get(cust_id);
		if(epf==null){
			Expressfund expressfund = new Expressfund();
			expressfund.setCust_id(cust_id);
			expressfund.setSummoney(0.00);
			this.expressfundDao.insert(expressfund);
		}
		// memberinter积分表插入主键cust_id,如果存在不作改动
		Memberinter mbi = this.memberinterDao.get(cust_id);
		if(mbi==null){
			Memberinter memberinter = new Memberinter();
			memberinter.setCust_id(cust_id);
			memberinter.setFund_num("0.00");
			this.memberinterDao.insert(memberinter);
		}
		// 审核通过向memberuser加入验证默认值如手机验证邮箱验证 如果存在不作改动
		Memberuser mu = this.memberuserDao.getPKByCustID(cust_id);
		if (mu.getIs_check_email() == null) {
			mu.setIs_check_email("1");
		}
		if (mu.getIs_check_mobile() == null) {
			mu.setIs_check_mobile("1");
		}
		this.memberuserDao.update(mu);
	}
	
	public String getCustName(String cust_id) {
		 String cust_name="";
		 Member mber=new Member();
	   	 mber=memberDao.get(cust_id);
	   	 if(mber!=null&&mber.getCust_name()!=null){
	   		 cust_name=mber.getCust_name().toString();
	   	 }
		 return cust_name;
	}
	public boolean isAllow(String user_name,String cfg_mb_notallow) {
		String notallow_name = "";
		notallow_name = SysconfigFuc.getSysValue(cfg_mb_notallow);
		String[] notallow_names = notallow_name.split("\\|");
		for(int i=0;i<notallow_names.length;i++){
			System.out.print(notallow_names[i]);
			if (user_name.contains(notallow_names[i]) ) {
				return true;
				
			}
		}
		return false;
	}
	public void webInit(String cust_id) {
		Memberfund memberfund = new Memberfund();
		memberfund.setCust_id(cust_id);
		memberfund.setUse_num("0.00");
		memberfund.setFund_num("0.00");
		memberfund.setFreeze_num("0.00");
		memberfundDao.insert(memberfund);

		// 将审核的ID插入到memberfund表余额中，如果存在不作改动
		Map mfMap = new HashMap();
		mfMap.put("cust_id", cust_id);
		List memberfundList = memberfundDao.getList(mfMap);
		if (memberfundList.size() == 0 || memberfundList == null) {
			Memberfund e_memberfund = new Memberfund();
			e_memberfund.setCust_id(cust_id);
			e_memberfund.setFreeze_num("0.00");
			e_memberfund.setFund_num("0.00");
			e_memberfund.setUse_num("0.00");
			this.memberfundDao.insert(e_memberfund);
		}

		// 向expressfund表直通车资金插入主键cust_id,如果存在不作改动
		Map efMap = new HashMap();
		efMap.put("cust_id", cust_id);
		List expressfundList = this.expressfundDao.getList(efMap);
		if (expressfundList.size() == 0 || expressfundList == null) {
			Expressfund expressfund = new Expressfund();
			expressfund.setCust_id(cust_id);
			expressfund.setSummoney(0.00);
			expressfundDao.insert(expressfund);
		}

		// memberinter积分表插入主键cust_id,如果存在不作改动
		Map miMap = new HashMap();
		miMap.put("cust_id", cust_id);
		List memberinterList = this.memberinterDao.getList(miMap);
		if (memberinterList.size() == 0 || memberinterList == null) {
			Memberinter memberinter = new Memberinter();
			memberinter.setCust_id(cust_id);
			memberinter.setFund_num("0.00");
			memberinterDao.insert(memberinter);
		}
		
	}
	public List is_exist_user(String username) {
		Map pageMap = new HashMap();
		pageMap.put("user_name", username);
		List loginuserList = this.memberuserDao.getList(pageMap);
		if (loginuserList != null && loginuserList.size() == 0) {
			pageMap.put("u-email", username);
			pageMap.remove("user_name");// 清除user_name
			//pageMap.put("is_check_email", "0");
			loginuserList = this.memberuserDao.getList(pageMap);
			if (loginuserList != null && loginuserList.size() == 0) {
				pageMap.put("u-cellphone", username);
				pageMap.remove("u-email");// 清除email
				pageMap.remove("is_check_email");
				//pageMap.put("is_check_mobile", "0");
				loginuserList = this.memberuserDao.getList(pageMap);
				if (loginuserList != null && loginuserList.size() == 0) {
					return loginuserList;
				}
			}
		}
		return loginuserList;
	}
	public void insertChangeSeller(String cust_id) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 各个区域的会员数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getAreaMember(Map map) throws Exception{
		return this.memberDao.getAreaMember(map);
	}
	
	/**
	 * 各个区域的会员消费的总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getAreaTotalAmountList(Map map) throws Exception{	
		return this.memberDao.getAreaTotalAmountList(map);
	}
	
}

