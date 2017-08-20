/*
  
 
 * Package:com.rbt.webaction;
 * FileName: WebmsgcheckAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.rbt.action.BaseAction;
import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Memberuser;
import com.rbt.model.Memberuser;
import com.rbt.model.Msgcheck;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.rbt.service.impl.MemberuserService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Method Description :前台验证邮箱
 * @author : HZX
 * @date : Feb 19, 2014 9:59:43 AM
 */
@Controller
public class WebmsgcheckAction extends WebbaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Msgcheck msgcheck;
	public Memberuser memberuser;
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
	public String cp_type;//验证类型

	/**
	 * @author HZX
	 * @Method Description : 验证邮件
	 * @date : Feb 17, 2014 2:48:46 PM
	 */
				
	public String  onclickemail() throws Exception{
			//定义request对象
			HttpServletRequest request =getRequest();
			request.setCharacterEncoding("UTF-8");
			String cp_mark_id = request.getParameter("c_mark_id");
			String cust_id= request.getParameter("cust_id");
			Map checkMap = new HashMap();
			checkMap.put("cp_mark_id", cp_mark_id);
			checkMap.put("minute", "1");
			List list = msgcheckService.getList(checkMap);
			getResponse().setCharacterEncoding("UTF-8");
			if(list!=null && list.size() > 0){
				memberuser =this.memberuserService.getPKByCustID(cust_id);
				memberuser.setIs_check_email("0");
				memberuserService.update(memberuser);
				//this.addActionMessage("邮箱验证成功！");
				return goUrl("successforemail");
			}
			else{
				memberuser =this.memberuserService.getPKByCustID(cust_id);
				memberuser.setIs_check_email("1");
				memberuserService.update(memberuser);
				//this.addActionMessage("验证已过时，请重新输入邮箱验证！");
				return goUrl("failforemail");
			}
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
	
	public String getCp_type() {
		return cp_type;
	}
	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
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
	
}

