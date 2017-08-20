/*
 
 * Package:com.rbt.action
 * FileName: ReceiveboxAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
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
 * @function 功能 记录收件箱信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:37:25 CST 2014
 */
@Controller
public class ReceiveboxAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Receivebox receivebox;
	private Member member;
	private Sendbox sendbox;
	
	/*******************业务层接口****************/
	@Autowired
	private IReceiveboxService receiveboxService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private ISendboxService sendboxService;
	/*********************集合********************/
	public List receiveboxList;//收件箱
	
	/*********************字段********************/
	
	public String cust_name;//发件人
	public String re_cust_name;
	public String re_in_date;
	public String re_content;
	public String re_read;
	public String logo_path;//会员头像
	public String title;//标题
	public String re_cust_id;
	public String sendman_content;//回复内容
	public int totalRow; // 共多少行
	public String rate_name_s;
	
	/**
	 * 方法描述：返回新增记录收件箱信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录收件箱信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(receivebox);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.receiveboxService.insert(receivebox);
		this.addActionMessage("新增记录收件箱信息成功！");
		this.receivebox = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录收件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(receivebox);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.receiveboxService.update(receivebox);
		this.addActionMessage("修改记录收件箱信息成功！");
		return list();
	}
	/**
	 * 方法描述：逻辑删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		//处理删除
		this.receiveboxService.dealDelete(chb_id);
		this.addActionMessage("删除成功！已在回收站");
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 2, 2014 5:28:26 PM
	 * @Method Description :实际删除
	 */
	public String  realDelete()throws Exception{
		this.receiveboxService.delete(chb_id);
		this.addActionMessage("彻底删除成功");
		return list();
		
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		// 根据cust_id 找出相应的发件人名称
		Member member = this.memberService.get(this.session_cust_id);
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			}
		}
		Map pageMap = new HashMap();
		//0：表示逻辑删  1：没有删除  2：物理删除
		pageMap.put("is_get_del", "1");
		//会员显示自己的
		pageMap.put("cust_id", this.session_cust_id);
        if(rate_name_s!=null &&!"".equals(rate_name_s)){
        	pageMap .put("cust_name", rate_name_s);
        }
		//根据页面提交的条件找出信息总数
		int count=this.receiveboxService.getCount(pageMap);
		totalRow=count;
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		receiveboxList = this.receiveboxService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据主键找出记录收件箱信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.receivebox.getReceive_id();
		if(id==null || id.equals("")){
			return list();
		}else{
			receivebox = this.receiveboxService.get(id);
		}
		//获取发件人名字
		String send_id =this.receivebox.getSend_id();
		sendbox=this.sendboxService.get(send_id);
		Member member = null;
		if(sendbox==null){
			sendbox=new Sendbox();
		}else{
			member= this.memberService.get(this.sendbox.getSend_cust_id());
		}
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			} 
			if(member.getLogo_path()!=null){
				logo_path=member.getLogo_path();
			}
			if(receivebox.getIs_read()!=null && receivebox.getIs_read().equals("1")){
				//设为已读
				this.receivebox.setIs_read("0");
				this.receiveboxService.update(receivebox);
			}
			
		}
		return goUrl(VIEW);
	}
	
/**
 * @author :LSQ
 * @date : Feb 5, 2014 9:37:00 AM
 * @Method Description :收件箱回复方法
 */
	public String replyadd(){
	    String send_id =this.receivebox.getSend_id();
		sendbox=this.sendboxService.get(send_id);
		//验证收件人不能为空
		if (ValidateUtil.isRequired(this.cust_name)) {
			this.addFieldError("cust_name", "发件人不能为空!");
		} 
		//不通过时返回
		super.commonValidateField(receivebox);
		if (super.ifvalidatepass) {
			return replyview();
		}			
        //将数据插入到发件箱
		this.sendbox.setSend_cust_id(this.session_cust_id);
		this.sendbox.setTitle(title);
		this.sendbox.setContent("<div id='newcontent1'>"+re_content+"</div>"+sendman_content);
		this.sendbox.setIs_send_del("1");
		this.sendbox.setRecevie_name(cust_name);
		String Send_id=this.sendboxService.insertGetPk(sendbox);
		this.receivebox.setSend_id(Send_id);
		//通过member表中的会员名获取其对应的id
		member = this.memberService.getByCustName(cust_name);
		String get_cuts_id=this.member.getCust_id();
		this.receivebox.setGet_cust_id(get_cuts_id);
		this.receivebox.setIs_read("1");
		this.receivebox.setIs_get_del("1");
		this.receiveboxService.insert(receivebox);
		this.addActionMessage("回复信息成功！");
		   
		return INPUT;
	}
	
	/**
	 * @author :LSQ
	 * @date : Feb 6, 2014 9:55:29 AM
	 * @Method Description :进入回复的页面
	 */
	public String replyview(){
		String id = this.receivebox.getReceive_id();
		if(id==null||id.equals("")){
			 receivebox=new Receivebox();
		}else{
			receivebox = this.receiveboxService.get(id);
		}
		//获取收件人名字
		String send_id =this.receivebox.getSend_id();
		sendbox=this.sendboxService.get(send_id);
		Member member = this.memberService.get(this.sendbox.getSend_cust_id());
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			} 
		}
		if(this.sendbox.getTitle()!=null&&!"".equals(this.sendbox.getTitle())){
			if(!this.sendbox.getTitle().contains("回复")){
				title="回复:"+this.sendbox.getTitle();
			}else {
				title=this.sendbox.getTitle();
			}
			
		}
		
		re_content=this.sendbox.getContent();
		return goUrl("replyadd");
	}
	
	
	/**
	 * @return the ReceiveboxList
	 */
	public List getReceiveboxList() {
		return receiveboxList;
	}
	/**
	 * @param receiveboxList
	 *  the ReceiveboxList to set
	 */
	public void setReceiveboxList(List receiveboxList) {
		this.receiveboxList = receiveboxList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(receivebox == null){
			receivebox = new Receivebox();
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
	public String getRe_cust_name() {
		return re_cust_name;
	}
	public void setRe_cust_name(String re_cust_name) {
		this.re_cust_name = re_cust_name;
	}
	public Sendbox getSendbox() {
		return sendbox;
	}
	public void setSendbox(Sendbox sendbox) {
		this.sendbox = sendbox;
	}
	public String getRe_in_date() {
		return re_in_date;
	}
	public void setRe_in_date(String re_in_date) {
		this.re_in_date = re_in_date;
	}
	public String getRe_read() {
		return re_read;
	}
	public void setRe_read(String re_read) {
		this.re_read = re_read;
	}
	/**
	 * @return the receivebox
	 */
	public Receivebox getReceivebox() {
		return receivebox;
	}
	/**
	 * @param Receivebox
	 *            the receivebox to set
	 */
	public void setReceivebox(Receivebox receivebox) {
		this.receivebox = receivebox;
	}
}

