/*
 
 * Package:com.rbt.action
 * FileName: MemberuserAction.java 
 */
package com.rbt.action;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.MessageAltFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.model.Memprotect;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemprotectService;
import com.rbt.service.IMsgcheckService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员信息表action类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 15:56:45 CST 2014
 */
@Controller
public class MemberuserAction extends AdminBaseAction implements Preparable{
	private static final String MESSAGE_VALUE = "1";
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Memberuser memberuser;
	public Member member;
	private Memprotect memprotect;
	public Memberfund memberfund;
	/*******************业务层接口****************/
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private IMemprotectService memprotectService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IMsgcheckService msgcheckService;
	
	/*********************集合********************/
	public List memberuserList;//会员信息表信息
	public List commparaList;//系统参数信息
	public List memprotectList;//会员密保信息
	
	/*********************字段********************/
	public String message;//提示信息
	public String oldpasswd;//重置密码
	public String newpasswd;//新密码
	public String newphone;//修改手机
	public String confirmpasswd;//确认密码
	public String newemail;//修改邮箱
	public String pass_strength;//密码强度
	public String passwd_ques;//用户问题提示
	public String passwd_selfques;//自定义问题提示
	public String apasswd_answer_s;//密保答案
	public String memprotect_id;//密保ID
	public boolean is_update=false;//修改标识
	 public String cp_check;
	/**
	 * 方法描述：返回新增会员信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	
	/**
	 * @Method Description :判断是跳转到修改密保页面还是跳转到设置页面
	 * @author : HZX
	 * @date : Feb 21, 2014 4:19:48 PM
	 */
	public String isNotSetUp() throws Exception{
		memprotect = new Memprotect();
		memprotect=this.memprotectService.getByCustID(this.session_cust_id);
		if(memprotect==null){
			return gosetque();
		}else{
			return gocheckque();
		}
	}
	/**
	 * @Method Description :跳转到修改密保页面
	 * @author : HZX
	 * @date : Feb 21, 2014 10:23:22 AM
	 */
	public String goupdateque() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("para_code", "psque_type");
		commparaList = this.commparaService.getList(pageMap);
		return goUrl("updateque");
	}
	/**
	 * @Method Description :跳转到设置密保页面
	 * @author : HZX
	 * @date : Feb 21, 2014 10:23:22 AM
	 */
	public String gosetque() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("para_code", "psque_type");
		commparaList = this.commparaService.getList(pageMap);
		return goUrl("setque");
	}
	/**
	 * @Method Description :设置密保
	 * @author : HZX
	 * @throws Exception 
	 * @date : Feb 21, 2014 5:28:48 PM
	 */
	
	public String setque() throws Exception{
		memprotect =new Memprotect();
		//判断是用户用系统问题还是自定义问题
			if(!this.validateFactory.isRequired(passwd_ques)&&this.validateFactory.isRequired(passwd_selfques)&&!this.validateFactory.isRequired(apasswd_answer_s)){
				memprotect.setCust_id(this.session_cust_id);
				memprotect.setQuestion(passwd_ques);
				memprotect.setAnswer(apasswd_answer_s);
				this.memprotectService.insert(memprotect);
			}else {
				if(this.validateFactory.isRequired(passwd_ques)&&!this.validateFactory.isRequired(passwd_selfques)&&!this.validateFactory.isRequired(apasswd_answer_s)){
					memprotect.setCust_id(this.session_cust_id);
					memprotect.setQuestion(passwd_selfques);
					memprotect.setAnswer(apasswd_answer_s);
					this.memprotectService.insert(memprotect);
				}
				else {
						this.addFieldError("passwd_selfques", "提示问题不能为空，请选择系统问题或自定义问题。");
						return gosetque();
					}
				}
			
		if(this.validateFactory.isRequired(apasswd_answer_s)){
			this.addFieldError("apasswd_answer_s", "答案不能为空");
			return gosetque();
		}
		this.addActionMessage("设置密保成功");
		this.setMessage(MESSAGE_VALUE);
		passwd_ques=null;
		passwd_selfques=null;
		apasswd_answer_s=null;
		apasswd_answer_s=null;
		return gocheckque();
	}
	
	/**
	 * @Method Description :跳转到验证密保
	 * @author : HZX
	 * @date : Feb 22, 2014 1:06:45 PM
	 */
	public String gocheckque() throws Exception {
		memprotect = new Memprotect();
		memprotect=this.memprotectService.getByCustID(this.session_cust_id);
		return goUrl("checkque");
	}
	
	/**
	 * @Method Description :验证密保
	 * @author : HZX
	 * @date : Feb 22, 2014 1:06:45 PM
	 */
	public String checkque() throws Exception {
		memprotect = new Memprotect();
		memprotect=this.memprotectService.getByCustID(this.session_cust_id);
		Map pageMap = new HashMap();
		pageMap.put("answer", apasswd_answer_s);
		pageMap.put("cust_id", this.session_cust_id);
		memprotectList = this.memprotectService.getList(pageMap);
		if(memprotectList==null||memprotectList.size()==0){
			this.addActionMessage("验证密保失败！");
			return gocheckque();
		}
		return goupdateque();
	}
	
	/**
	 * @Method Description :重置密保
	 * @author : HZX
	 * @date : Feb 22, 2014 1:06:45 PM
	 */
	public String updateque() throws Exception {
		memprotect =new Memprotect();
		//判断是用户用系统问题还是自定义问题
			if(!this.validateFactory.isRequired(passwd_ques)&&this.validateFactory.isRequired(passwd_selfques)&&!this.validateFactory.isRequired(apasswd_answer_s)){
				memprotect.setCust_id(this.session_cust_id);
				memprotect.setId(memprotect_id);
				memprotect.setQuestion(passwd_ques);
				memprotect.setAnswer(apasswd_answer_s);
				this.memprotectService.update(memprotect);
			}else {
				if(this.validateFactory.isRequired(passwd_ques)&&!this.validateFactory.isRequired(passwd_selfques)&&!this.validateFactory.isRequired(apasswd_answer_s)){
					memprotect.setCust_id(this.session_cust_id);
					memprotect.setId(memprotect_id);
					memprotect.setQuestion(passwd_selfques);
					memprotect.setAnswer(apasswd_answer_s);
					this.memprotectService.update(memprotect);
				}
				else {
						this.addFieldError("passwd_selfques", "提示问题不能为空，请选择系统问题或自定义问题。");
						return goupdateque();
					}
				}
			
		if(this.validateFactory.isRequired(apasswd_answer_s)){
			this.addFieldError("apasswd_answer_s", "答案不能为空");
			return goupdateque();
		}
		
		this.addActionMessage("重置密保成功");
		this.setMessage(MESSAGE_VALUE);
		passwd_ques=null;
		passwd_selfques=null;
		apasswd_answer_s=null;
		apasswd_answer_s=null;
		return gocheckque();
	}
	
	/**
	 * 方法描述：新增会员信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.memberuserService.insert(memberuser);
		this.addActionMessage("新增会员信息表成功！");
		this.memberuser = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateuser() throws Exception {
		is_update=true;
		super.commonValidateField(memberuser);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberuserService.update(memberuser);
		this.addActionMessage("修改会员信息表成功！");
		return view();
	}
	/**
	 * @author HZX
	 * @Method Description : 跳转到修改密码页面
	 * @date : Jan 15, 2014 10:01:14 AM
	 */

	public String updatePw() {
		memberuser=this.memberuserService.get(this.session_user_id);
		if(memberuser==null){
			memberuser=new Memberuser();
		}
		 
		 return goUrl("updatePw");

	}
	/**
	 * @author HZX
	 * @throws UnsupportedEncodingException
	 * @Method Description : 修改密码
	 * @date : Jan 14, 2014 5:28:29 PM
	 */

	public String updatePassword() throws UnsupportedEncodingException {

		// 查找密码
		Map pageMap = new HashMap();
		String opwd = newpasswd;
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("passwd", oldpasswd);
		pageMap.put("user_id", this.session_user_id);
		memberuserList = this.memberuserService.getList(pageMap);
		if (memberuserList == null || memberuserList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认新密码不能为空");
			return goUrl("updatePw");
		}
		if (ValidateUtil.isAlphasLimtLength(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成6-20位");
			return goUrl("updatePw");
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致");
			return goUrl("updatePw");
		}

		// //获取密码强度
		String pswstrong = memberuser.getPass_strength();
		memberuser = this.memberuserService.get(this.session_user_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}

		memberuser.setPasswd(newpasswd);
		memberuser.setPass_strength(pswstrong);

		this.memberuserService.update(memberuser);
		// 信息提醒
		MessageAltFuc mesalt = new MessageAltFuc();
		mesalt.messageAutoSend("9", memberuser.getCust_id().toString());
		this.addActionMessage("设置密码成功");
		this.setMessage(MESSAGE_VALUE);
		return goUrl("updatePwSuccess");

	}

	/**
	 * @author HZX
	 * @Method Description : 跳转到修改邮箱页面
	 * @date : Feb 4, 2014 2:42:58 PM
	 */

	public String updateEmail() {
		// TODO Auto-generated method stub
		memberuser = this.memberuserService.get(this.session_user_id);
		return goUrl("updateEmail");

	}
	
	/**
	 * @author HZX
	 * @Method Description : 跳转到修改手机页面
	 * @date : Feb 4, 2014 2:42:58 PM
	 */

	public String updatePhone() {
		// TODO Auto-generated method stub
		memberuser = this.memberuserService.get(this.session_user_id);
		return goUrl("updatePhone");

	}
	
	
	/**
	 * @author HZX
	 * @Method Description : 修改手机
	 * @date : Feb 4, 2014 2:39:27 PM
	 */

	public String updateP() {
		boolean ckfage=msgcheckService.checkMsgListToUse(newphone, cp_check);
		getResponse().setCharacterEncoding("UTF-8");
		if(ckfage==true){
			memberuser = this.memberuserService.get(this.session_user_id);
			memberuser.setCellphone(newphone);
			// 字段验证
			super.commonValidateField(memberuser);
			if (super.ifvalidatepass) {
				updatePhone();
			}
			this.memberuserService.update(memberuser);
			this.addActionMessage("手机修改成功");
			this.setMessage(MESSAGE_VALUE);
			return goUrl("updatePhoneSuccess");
		}
		else{
			this.addActionMessage("验证码过时或者错误，请重新输入手机号码并获取验证码！");
			return updatePhone();
		}
	}

	/**
	 * @author HZX
	 * @Method Description : 修改邮箱
	 * @date : Feb 4, 2014 2:39:27 PM
	 */

	public String updateE() {
        
		if (ValidateUtil.isRequired(this.getNewemail())) {
			this.addFieldError("newemail", "新邮箱不能为空");
			updateEmail();
		}
		if (ValidateUtil.isEmail(this.getNewemail())) {
			this.addFieldError("newemail", "请输入正确新邮箱");
			updateEmail();
		}

		if (!ValidateUtil.isEmail(this.getNewemail())
				&& existsTitle(newemail, memberuser.getEmail(), "memberuser",
						"email")) {
			this.addFieldError("newemail", "该邮箱已经存在");
			updateEmail();
		}
		if (newemail != null && (memberuser.getEmail().equals(newemail.trim()))) {
			this.addFieldError("newemail", "该邮箱已在使用");
			updateEmail();
		}
		Map checkMap = new HashMap();
		checkMap.put("cp_phone", newemail);
		checkMap.put("cp_check", cp_check);
		checkMap.put("minute", "1");
		List list = msgcheckService.getList(checkMap);
		getResponse().setCharacterEncoding("UTF-8");
		if(list!=null && list.size() > 0){
			memberuser = this.memberuserService.get(this.session_user_id);
			memberuser.setEmail(newemail);
			// 字段验证
			super.commonValidateField(memberuser);
			if (super.ifvalidatepass) {
				updateEmail();
			}
			this.memberuserService.update(memberuser);
			// 验证发送新邮箱
			String title = "修改邮箱确认信";
			String content = "恭喜您修改邮箱成功！";
			MailInter mailUtil = new MailInter();
			mailUtil.sendBatchMail(title, content, this.getNewemail());
			this.addActionMessage("邮箱修改成功");
			this.setMessage(MESSAGE_VALUE);
			return goUrl("updateEmailSuccess");
		}
		else{
			this.addActionMessage("验证码过时或者错误，请重新输入手机号码并获取验证码！");
			return updateEmail();
		}
	}

	/**
	 * 方法描述：删除会员信息表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memberuserService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除会员信息表成功");
		}else{
			this.addActionMessage("删除会员信息表失败");
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
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.memberuserService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		memberuserList = this.memberuserService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public String view() throws Exception {
		this.memberuser.setUser_id(this.session_user_id);
		String  id = this.memberuser.getUser_id();
		if(id==null || id.equals("")){
			memberuser = new Memberuser();
		}else{
			if(!is_update){
				memberuser = this.memberuserService.get(id);
			}
			String cust_id=this.memberuser.getCust_id();
			member = memberService.get(cust_id);
		}
		return goUrl(VIEW);
	}
	
	
	/**
	 * 方法描述：商城用户退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public void exit() throws Exception {
		getSession().invalidate();
		getResponse().sendRedirect(Constants.MEMBER_SIGNIN);
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the MemberuserList
	 */
	public List getMemberuserList() {
		return memberuserList;
	}
	/**
	 * @param memberuserList
	 *  the MemberuserList to set
	 */
	public void setMemberuserList(List memberuserList) {
		this.memberuserList = memberuserList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(memberuser == null){
			memberuser = new Memberuser();
		}
		String id = this.memberuser.getUser_id();
		if(!this.validateFactory.isDigital(id)){
			memberuser = this.memberuserService.get(id);
		}
	}

	/**
	 * @return the memberuser
	 */
	public Memberuser getMemberuser() {
		return memberuser;
	}
	/**
	 * @param Memberuser
	 *            the memberuser to set
	 */
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}
	public List getCommparaList() {
		return commparaList;
	}
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	public String getOldpasswd() {
		return oldpasswd;
	}
	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	public String getConfirmpasswd() {
		return confirmpasswd;
	}
	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}
	public String getMessage() {
		return message;
	}
	public String getNewemail() {
		return newemail;
	}
	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	public String getPass_strength() {
		return pass_strength;
	}
	public void setPass_strength(String pass_strength) {
		this.pass_strength = pass_strength;
	}
	public Memprotect getMemprotect() {
		return memprotect;
	}
	public void setMemprotect(Memprotect memprotect) {
		this.memprotect = memprotect;
	}
	public List getMemprotectList() {
		return memprotectList;
	}
	public void setMemprotectList(List memprotectList) {
		this.memprotectList = memprotectList;
	}
	public String getPasswd_ques() {
		return passwd_ques;
	}
	public void setPasswd_ques(String passwd_ques) {
		this.passwd_ques = passwd_ques;
	}
	public String getPasswd_selfques() {
		return passwd_selfques;
	}
	public void setPasswd_selfques(String passwd_selfques) {
		this.passwd_selfques = passwd_selfques;
	}
	public String getApasswd_answer_s() {
		return apasswd_answer_s;
	}
	public void setApasswd_answer_s(String apasswd_answer_s) {
		this.apasswd_answer_s = apasswd_answer_s;
	}

	public String getMemprotect_id() {
		return memprotect_id;
	}

	public void setMemprotect_id(String memprotect_id) {
		this.memprotect_id = memprotect_id;
	}

}

