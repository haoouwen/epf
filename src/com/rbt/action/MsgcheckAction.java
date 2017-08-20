/*
 
 * Package:com.rbt.action
 * FileName: MsgcheckAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Memberuser;
import com.rbt.model.Msgcheck;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商城的活动管理action类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Oct 10 16:38:35 CST 2014
 */
@Controller
public class MsgcheckAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Memberuser memberuser;
	private Msgcheck msgcheck;
	/*******************业务层接口****************/
	@Autowired
	private IMsgcheckService msgcheckService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	
	/*********************集合********************/
	
	public List msgcheckList;//商城的活动管理信息
	/*********************字段********************/
	
	public String u_email;
	public String u_phone;
	public String cp_type;//1.注册的验证码，0,会员登录错误密码,3,手机验证，4，邮箱验证,5,获取积分
	public String cp_phone;//验证手机号码
	public String commentrand;//验证码
	public String i_c;

	/**
	 * 方法描述：返回新增记录商城的活动管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	/**
	 * @author HZX
	 * @Method Description : 返回验证邮箱页面
	 * @date : Feb 16, 2014 9:58:44 AM
	 */
	public String checkemail() throws Exception {
		return goUrl("checkemail");
	}
	/**
	 * @Method Description :跳转到验证成功页面
	 * @author : HZX
	 * @date : Feb 19, 2014 10:28:58 AM
	 */
	public String success() throws Exception {
		return goUrl("success");
	}
	/**
	 * @author HZX
	 * @Method Description : 返回验证手机页面
	 * @date : Feb 16, 2014 9:58:44 AM
	 */
		public String checkmobile() throws Exception {
			return goUrl("checkmobile");
		}

		
		
	/**
	 * 方法描述：新增记录商城的活动管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(msgcheck);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.msgcheckService.insert(msgcheck);
		this.addActionMessage("新增记录商城的活动管理成功！");
		this.msgcheck = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商城的活动管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(msgcheck);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.msgcheckService.update(msgcheck);
		this.addActionMessage("修改记录商城的活动管理成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商城的活动管理信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.msgcheckService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商城的活动管理成功");
		}else{
			this.addActionMessage("删除记录商城的活动管理成功失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("cp_type", "1,3,4");
		//根据页面提交的条件找出信息总数
		int count=this.msgcheckService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		msgcheckList = this.msgcheckService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * @author HZX
	 * @Method Description : 验证手机验证码正确性
	 * @date : Feb 16, 2014 1:23:13 PM
	 */
	public void   checkPhoneCode() throws IOException{
		//定义request对象
		HttpServletRequest request =getRequest();
		request.setCharacterEncoding("UTF-8");
		String cp_phone = request.getParameter("phone");
		String cp_check = request.getParameter("cp_check");
		Map checkMap = new HashMap();
		checkMap.put("cp_phone", cp_phone);
		checkMap.put("cp_check", cp_check);
		checkMap .put("out_minute", "1");
		List outList = this.msgcheckService.getList(checkMap);
		Map map = new HashMap();
		map.put("cp_phone", cp_phone);
		map.put("cp_check", cp_check);
		map.put("minute", "1");
		List list = msgcheckService.getList(map);
		PrintWriter out = getResponse().getWriter();
		getResponse().setCharacterEncoding("UTF-8");
		if(outList!=null && outList.size() > 0){
			out.write("1");
		} else if(list!=null && list.size() > 0) {
			out.write("2");
		} else {
			out.write("3");
		}
	}
	/**
	 * @author HZX
	 * @Method Description : 验证手机验证码正确性通过表单提交
	 * @date : Feb 16, 2014 1:23:13 PM
	 */
	public String    checkPhoneCodebyform() throws IOException{
		// 随机验证码
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		// 验证验证码是否正确
		if (!sysrand.equals(commentrand)) {
			this.addFieldError("commentrand", "验证码输入不正确");
			i_c = "1";
			return goUrl("checkmobile");
		}
		cp_phone = msgcheck.getCp_phone();
		String cp_check = msgcheck.getCp_check();
		boolean ckfage=msgcheckService.checkMsgListToUse(cp_phone, cp_check);
		getResponse().setCharacterEncoding("UTF-8");
		if(ckfage==true){
			memberuser =this.memberuserService.getPKByCustID(this.session_cust_id);
			memberuser.setIs_check_mobile("0");
			memberuser.setCellphone(cp_phone);
			memberuserService.update(memberuser);
			this.addActionMessage("手机验证成功！");
			return goUrl("success");
		}
		else{
			memberuser =this.memberuserService.getPKByCustID(this.session_cust_id);
			memberuser.setIs_check_mobile("1");
			memberuserService.update(memberuser);
			this.addActionMessage("验证码已过时，请重新输入手机号码并获取验证码！");
			return goUrl("checkmobile");
		}
	}
	/**
	 * @author HZX
	 * @Method Description : 发送验证邮件
	 * @date : Feb 17, 2014 1:19:32 PM
	 */
   public String    sendcheckemail() throws Exception{
	   cp_phone = msgcheck.getCp_phone();
		String cp_check = msgcheck.getCp_check();
		boolean ckfage=msgcheckService.checkMsgListToUse(cp_phone, cp_check);
		getResponse().setCharacterEncoding("UTF-8");
		if(ckfage==true){
			memberuser =this.memberuserService.getPKByCustID(this.session_cust_id);
			memberuser.setIs_check_email("0");
			memberuser.setEmail(cp_phone);
			memberuserService.update(memberuser);
			this.addActionMessage("邮箱验证成功！");
			return goUrl("tocheckemail");
		}
		else{
			memberuser =this.memberuserService.getPKByCustID(this.session_cust_id);
			memberuser.setIs_check_email("1");
			memberuserService.update(memberuser);
			this.addActionMessage("验证码已过时，请重新输入邮箱号码并获取验证码！");
			return goUrl("checkemail");
		}
	}
   
	/**
	 * 方法描述：根据主键找出记录商城的活动管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.msgcheck.getId();
		if(id==null || id.equals("")){
			msgcheck = new Msgcheck();
		}else{
			msgcheck = this.msgcheckService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MsgcheckList
	 */
	public List getMsgcheckList() {
		return msgcheckList;
	}
	/**
	 * @param msgcheckList
	 *  the MsgcheckList to set
	 */
	public void setMsgcheckList(List msgcheckList) {
		this.msgcheckList = msgcheckList;
	}
	/**
	 * @return the msgcheck
	 */
	public Msgcheck getMsgcheck() {
		return msgcheck;
	}
	/**
	 * @param Msgcheck
	 *            the msgcheck to set
	 */
	public void setMsgcheck(Msgcheck msgcheck) {
		this.msgcheck = msgcheck;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(msgcheck == null){
			msgcheck = new Msgcheck();
		}
		String id = this.msgcheck.getId();
		if(!this.validateFactory.isDigital(id)){
			msgcheck = this.msgcheckService.get(id);
		}
	}
	public Memberuser getMemberuser() {
		return memberuser;
	}
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getCp_type() {
		return cp_type;
	}
	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}

}

