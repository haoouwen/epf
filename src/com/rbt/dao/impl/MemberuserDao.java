/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemberuserDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberuserDao;
import com.rbt.model.Memberuser;

/**
 * @function 功能  会员登陆账号信息表dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 10:06:41 CST 2014
 */
@Repository
public class MemberuserDao extends GenericDao<Memberuser,String> implements IMemberuserDao {
	
	public MemberuserDao() {
		super(Memberuser.class);
	}
	
	//通过cust_id获取memberuser的对象
	public Memberuser getPKByCustID(String cust_id) {
		return  (Memberuser)this.getSqlMapClientTemplate().queryForObject("memberuser.getPkByCustId", cust_id);
	}

	public void updatePassword(Memberuser memberuser) {
		this.getSqlMapClientTemplate().update("memberuser.updatepassword",
				memberuser);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IMemberuserDao#insertMemberuser(com.rbt.model.Memberuser)
	 */
	public String insertMemberuser(Memberuser memberuser) {
		return (String) this.getSqlMapClientTemplate().insert(
				"memberuser.insert", memberuser);
	}

	public Memberuser getMemberuserByusername(String username) {
		return (Memberuser) this.getSqlMapClientTemplate().queryForObject("memberuser.getByName",username);
	}

	public void deleteMembuser(String user_name) {
		this.getSqlMapClientTemplate().delete(getModelName()+".deleteMembuser", user_name);
	}

	public String insertSysUser(Memberuser memberuser) {
		return (String) this.getSqlMapClientTemplate().insert(
				"memberuser.insertSysUser", memberuser);
	}

		
	
	
}

