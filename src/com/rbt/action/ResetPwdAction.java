/*
 
 * Package:com.rbt.action
 * FileName: CommparaAction.java 
 */
package com.rbt.action;

import java.util.List;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IShopconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能  系统信息action类
 * @author  创建人 HXK
 * @date  创建日期  July 6, 2014
 */
@Controller
public class ResetPwdAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = -4903741339231855792L;
	/*******************实体层********************/
	private Member member;
	public Memberuser memberuser;
	public Shopconfig shopconfig;
	public Memberfund memberfund;
	/*******************业务层接口****************/
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberuserService memberuserservice;
	@Autowired
	private IShopconfigService shopconfigservice;
	@Autowired
	private IMemberfundService memberfundService;
	/*********************集合********************/
	public List memberList;//会员信息
	
	/*********************字段********************/
	
	public String cust_id_s;
	public String oldshop;
	public String oldusername;
	public String reset_passwd;
	public String con_repasswd;

	/**
	 * @author :LSQ
	 * @date : Feb 19, 2014 3:31:38 PM
	 * @Method Description :重置密码
	 */
	@SuppressWarnings("unchecked")
	public String resetpwd() throws Exception {
		// 密码不能为空
		if (ValidateUtil.isRequired(this.reset_passwd)) {
			this.addFieldError("reset_passwd", "重置密码不能为空");
		} else if (ValidateUtil.isAlphasLimtLength(reset_passwd)) {
			this.addFieldError("reset_passwd", "重置密码只能由6-20个字母、数字、下划线组成");
		}
		// 确认密码和确认密码比较
		if (!this.reset_passwd.equals(this.con_repasswd)) {
			this.addFieldError("con_repasswd", "重置密码与确认密码不一致，请重新输入");
		}

		if (super.ifvalidatepass) {
			return resetview();
		}
		// 对密码加密
		String passwd = this.reset_passwd;
		if (passwd != null && !passwd.equals("")) {
			passwd = Md5.getMD5(passwd.getBytes());
		} else {
			passwd = null;
		}
		memberfund =this.memberfundService.get(this.session_cust_id);
		if(memberfund!=null){
			String pay_psw=memberfund.getPay_passwd();
			if(pay_psw.equals(passwd)){
				this.addFieldError("reset_passwd", "登陆密码不能和支付密码一样！");
			}else {
				memberuser.setPasswd(passwd);
			}
		}else {
			memberuser.setPasswd(passwd);
		}
		
		if (memberuser != null && !memberuser.equals("")) {
			this.memberuserservice.updatePassword(memberuser);
		}

		this.addActionMessage("重置密码成功！");
		return resetview();
	}

	/**
	 * @author :LSQ
	 * @date : Feb 19, 2014 4:12:43 PM
	 * @Method Description :进入重置密码
	 */

	public String resetview() throws Exception {

		String id = this.cust_id_s;
		if (id == null || id.equals("")) {
			member = new Member();
			memberuser = new Memberuser();
			shopconfig = new Shopconfig();
		} else {
			member = this.memberService.get(id);
			memberuser = this.memberuserservice.getPKByCustID(id);
			shopconfig = this.shopconfigservice.getByCustID(id);
		}
		if (memberuser == null || memberuser.equals("")) {
			memberuser = new Memberuser();
		}
		if (shopconfig == null || shopconfig.equals("")) {
			shopconfig = new Shopconfig();
		}

		return goUrl("resetpassword");
	}

	public List getMemberList() {
		return memberList;
	}

	/**
	 * @param memberList
	 *            the MemberList to set
	 */
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (member == null) {
			member = new Member();
		}
		String id = this.member.getCust_id();
		if (!this.validateFactory.isDigital(id)) {
			member = this.memberService.get(id);
		}

	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public Shopconfig getShopconfig() {
		return shopconfig;
	}

	public void setShopconfig(Shopconfig shopconfig) {
		this.shopconfig = shopconfig;
	}

}
