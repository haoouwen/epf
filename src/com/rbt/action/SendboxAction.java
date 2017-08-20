/*
 
 * Package:com.rbt.action
 * FileName: SendboxAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Member;
import com.rbt.model.Receivebox;
import com.rbt.model.Sendbox;
import com.rbt.service.IMemberService;
import com.rbt.service.IReceiveboxService;
import com.rbt.service.IRecycleService;
import com.rbt.service.ISendboxService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录发件箱信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:36:28 CST 2014
 */
@Controller
public class SendboxAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/** *****************实体层******************* */
	private Sendbox sendbox;
	private Member member;
	private Receivebox receivebox;

	/** *****************业务层接口*************** */
	@Autowired
	private ISendboxService sendboxService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IReceiveboxService receiveboxService;
	@Autowired
	private IRecycleService recycleService;

	/** *******************集合******************* */
	public List sendboxList;// 发件箱
	public List receiveboxList;// 收件箱

	/** *******************字段******************* */

	public String cust_name;
	public String send_name;
	public String send_cust_name;// 收件人
	public String logo_path; // 头像
	public String send_cust_name_s;
    public String send_cust_name_member;
    public String send_cust_id_member;
    public String rate_name_s;
	/**
	 * 方法描述：返回新增记录发件箱信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 根据cust_id 找出相应的名称
		Member member = this.memberService.get(this.session_cust_id);
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			}
		}
		//获取系统默认运营商会员账户
		getadmininfo();
		return goUrl(ADD);
	}
    
	/**
	 * 方法描述：新增记录发件箱信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String send_cust_id = this.session_cust_id;
		this.sendbox.setSend_cust_id(send_cust_id);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//获取默认的账户cust_id=0
			send_cust_name =send_cust_name_member;
		} else {
			if (ValidateUtil.isRequired(this.send_cust_name)) {
				this.addFieldError("send_cust_name", "收件人不能为空!");
			}
		}
		// 会员不能给自己发邮件
		String get_cust_id_str = "";
		String[] cust_name_str = send_cust_name.split(",");
		//获取可发送的会员id
		Map idMap=this.sendboxService.getCustStr(cust_name_str,send_cust_id);
		if(!idMap.get("send_cust_name").toString().equals("")){
			this.addFieldError("send_cust_name", idMap.get("send_cust_name").toString());
		}
		get_cust_id_str=idMap.get("get_cust_id_str").toString();
		// 不通过时返回
		super.commonValidateField(sendbox);
		if (super.ifvalidatepass) {
			return add();
		}
		this.sendbox.setIs_send_del("1");
		if(sendbox.getIs_draft()==null){
			String is_draft_s="0";
		    sendbox.setIs_draft(is_draft_s);
		}
		
		this.sendbox.setRecevie_name(send_cust_name);
		String send_id = this.sendboxService.insertGetPk(sendbox);
		
		if(sendbox.getIs_draft().equals("0")){
			// 保存到收件箱
			this.sendboxService.setReceivebox(get_cust_id_str, send_id);
		    this.addActionMessage("发送信件成功");
		}else
			this.addActionMessage("保存草稿成功");	
		this.sendbox = null;
		return add();
	}
	/**
	 * @Method Description :获取系统内置的运营商帐号信息
	 * @author: HXK
	 * @date : Aug 31, 2015 4:30:25 PM
	 * @param 
	 * @return return_type
	 */

    public void getadmininfo(){
    	//获取系统默认运营商会员账户
    	Member memberadmin= this.memberService.get("0");
		if(memberadmin!=null){
			send_cust_name_member=memberadmin.getCust_name();
			send_cust_id_member=memberadmin.getCust_id();
		}
    }
	/**
	 * 方法描述：修改记录发件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		super.commonValidateField(sendbox);
		if (super.ifvalidatepass) {
			return view();
		}
		//获取系统默认运营商会员账户
		Member memberadmin= this.memberService.get("0");
		if(memberadmin!=null){
			send_cust_name_member=memberadmin.getCust_name();
			send_cust_id_member=memberadmin.getCust_id();
		}
		this.sendboxService.update(sendbox);
		this.addActionMessage("修改记录发件箱信息成功！");
		return list();
	}

	/**
	 * 方法描述：逻辑删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = chb_id;
		id = id.replace(" ", "");
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null || ids[i].equals("")) {
				continue;
			}
			HashMap map = new HashMap();
			map.put("is_send_del", "0");
			map.put("send_id", ids[i]);
			this.sendboxService.deletelogic(map);
		}
		this.addActionMessage("信息删除成功！");
		return list();
	}
    
	
	/**
	 * 删除草稿信息
	 * @return
	 * @throws Exception
	 */
	public String deleteDraft()throws Exception{
		sendboxService.delete(chb_id);
		this.addActionMessage("删除草稿成功！");
		return draftList();
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Mar 2, 2014 5:28:26 PM
	 * @Method Description :实际删除
	 */
	public void realDelete() {
		String id = chb_id;
		id = id.replace(" ", "");
		this.sendboxService.delete(id);
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		commonList("0");
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 草稿箱
	 * @return
	 * @throws Exception
	 */
	public String draftList()throws Exception{
		commonList("1");
		return goUrl("draftindex");
	}
	
	public void commonList(String is_draft){
		Map pageMap = new HashMap();
		pageMap.put("cust_id", this.session_cust_id);
		// 0：表示逻辑删 1：没有删除 2：物理删除
		pageMap.put("is_send_del", "1");
		if (!ValidateUtil.isRequired(send_cust_name_s)) {
			pageMap.put("send_cust_name", send_cust_name_s);
		}
		if(!ValidateUtil.isRequired(is_draft)){
			pageMap.put("is_draft", is_draft);
		}
		if(rate_name_s!=null && !"".equals(rate_name_s)){
			pageMap.put("recevie_name", rate_name_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.sendboxService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		sendboxList = this.sendboxService.getList(pageMap);
	}

	/**
	 * 方法描述：根据主键找出记录发件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sendbox.getSend_id();
		if (id == null || id.equals("")) {
			return list();
		} else {
			sendbox = this.sendboxService.get(id);
		}
		// 根据cust_id 找出相应的名称
		Member member = this.memberService.get(this.session_cust_id);
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			}
			if (member.getLogo_path() != null) {
				logo_path = member.getLogo_path();
			}
		}
		if (id == null || id.equals("")) {
			sendbox = new Sendbox();
		} else {
			sendbox = this.sendboxService.get(id);
		}
		
		//获取系统默认运营商会员账户
		getadmininfo();
		
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：根据主键找出记录发件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String draftView()throws Exception{
		String id = this.sendbox.getSend_id();
		if (id == null || id.equals("")) {
			return draftList();
		} else {
			sendbox = this.sendboxService.get(id);
		}
		//获取系统默认运营商会员账户
		getadmininfo();
		return goUrl("draftupdate");
	}
   
	/**
	 * 修改草稿的信息
	 * @return
	 * @throws Exception
	 */
	public String updateDraft()throws Exception{
		String tip="保存草稿成功";
		super.commonValidateField(sendbox);
		if (super.ifvalidatepass) {
			return draftView();
		}
		if(sendbox.getIs_draft().equals("0")){
		// 保存到收件箱
		this.sendboxService.setReceivebox(sendbox.getSend_cust_id(), sendbox.getSend_id());
		 tip="发送信件成功";
		}
		this.addActionMessage(tip);	
		this.sendboxService.update(sendbox);
		return draftList();
	}
	
	/**
	 * @author :LSQ
	 * @date : Feb 16, 2014 9:42:23 AM
	 * @Method Description :发件箱再次编辑
	 */
	public String reedit() throws Exception {
		String id = this.sendbox.getSend_id();
		if (id == null || id.equals("")) {
			return list();
		} else {
			sendbox = this.sendboxService.get(id);
		}
		return goUrl(VIEW);
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (sendbox == null) {
			sendbox = new Sendbox();
		}
		String id = this.sendbox.getSend_id();
		if (!this.validateFactory.isDigital(id)) {
			sendbox = this.sendboxService.get(id);
		}
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Receivebox getReceivebox() {
		return receivebox;
	}

	public void setReceivebox(Receivebox receivebox) {
		this.receivebox = receivebox;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getSend_cust_name() {
		return send_cust_name;
	}

	public void setSend_cust_name(String send_cust_name) {
		this.send_cust_name = send_cust_name;
	}

	public List getSendboxList() {
		return sendboxList;
	}

	public void setSendboxList(List sendboxList) {
		this.sendboxList = sendboxList;
	}

	public Sendbox getSendbox() {
		return sendbox;
	}

	public void setSendbox(Sendbox sendbox) {
		this.sendbox = sendbox;
	}
}
