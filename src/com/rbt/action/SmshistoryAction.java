/*
 
 * Package:com.rbt.action
 * FileName: SmshistoryAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Smshistory;
import com.rbt.service.ISmshistoryService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录短信发送历史记录action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 13:50:23 CST 2014
 */
@Controller
public class SmshistoryAction extends AdminBaseAction implements Preparable{
	/*
	 * 获取发件人手机号码业务层接口
	 */
	public SysconfigFuc sysconfigFuc;
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Smshistory smshistory;
	/*******************业务层接口****************/
	@Autowired
	private ISmshistoryService smshistoryService;
	
	/*********************集合********************/
	
	public List smshistoryList;//短信发送历史
	/*********************字段********************/
	private String cellphones_s;//接受方
	private String cellnum_s;//发送方
	private String user_name_s;//接收方用户名
	private String send_cellnum;
	private String celltitle_s;//短信标题
	
	/**
	 * 方法描述：返回新增记录短信发送历史记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		send_cellnum=sysconfigFuc.getSysValue("cfg_msg_phone");
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录短信发送历史记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.smshistory.setUser_id(this.session_user_id);
		send_cellnum=sysconfigFuc.getSysValue("cfg_msg_phone");
		super.commonValidateField(smshistory);
		if(super.ifvalidatepass){
			return add();
		}
	    this.smshistory.setCellnum(send_cellnum);
		this.smshistoryService.insert(smshistory);
		this.addActionMessage("新增记录短信发送历史记录成功！");
		this.smshistory = null;
		return list();
	}
     /**
	 * @author：XBY
	 * @date：Nov 14, 2014 11:04:01 AM
	 * @Method Description：发送短信
	 */
	public String sendMessage() throws Exception {
		super.commonValidateField(smshistory);
		if(super.ifvalidatepass){
			return add();
		}
		String cellphone=smshistory.getCellphones();
		String cellphones[]=cellphone.split(",");
		for(int i=0;i<cellphones.length;i++){
		if(ValidateUtil.isRequired(cellphones[i])|| ValidateUtil.isMobile(cellphones[i].trim())){
			this.addFieldError("smshistory.cellphones","请输入正确手机号码！");
			return add();
		}
		}
		String content=smshistory.getContent();
		SendMsg(cellphone,smshistory.getCelltitle(), content);
		this.smshistory.setUser_id(this.session_user_id);
		this.addActionMessage("发送短信成功！");
		this.smshistory = null;
		return list();
	}
	
	/**
	 * 方法描述：修改记录短信发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(smshistory);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.smshistoryService.update(smshistory);
		this.addActionMessage("修改记录短信发送历史记录成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录短信发送历史记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.smshistoryService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录短信发送历史记录成功");
		}else{
			this.addActionMessage("删除记录短信发送历史记录失败");
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
	
		if (celltitle_s != null && !celltitle_s.equals("")) {
			pageMap.put("celltitle_s", celltitle_s);
		}
		if (cellphones_s != null && !cellphones_s.equals("")) {
			pageMap.put("cellphones", cellphones_s);
		}
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.smshistoryService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		smshistoryList = this.smshistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录短信发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.smshistory.getTrade_id();
		if(id==null || id.equals("")){
			smshistory = new Smshistory();
		}else{
			smshistory = this.smshistoryService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SmshistoryList
	 */
	public List getSmshistoryList() {
		return smshistoryList;
	}
	/**
	 * @param smshistoryList
	 *  the SmshistoryList to set
	 */
	public void setSmshistoryList(List smshistoryList) {
		this.smshistoryList = smshistoryList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(smshistory == null){
			smshistory = new Smshistory();
		}
		String id = this.smshistory.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			smshistory = this.smshistoryService.get(id);
		}
	}
	public String getCellphones_s() {
		return cellphones_s;
	}
	public void setCellphones_s(String cellphones_s) {
		this.cellphones_s = cellphones_s;
	}
	public String getCellnum_s() {
		return cellnum_s;
	}
	public void setCellnum_s(String cellnum_s) {
		this.cellnum_s = cellnum_s;
	}
	public String getUser_name_s() {
		return user_name_s;
	}
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}
	public String getSend_cellnum() {
		return send_cellnum;
	}
	public void setSend_cellnum(String send_cellnum) {
		this.send_cellnum = send_cellnum;
	}
	public String getCelltitle_s() {
		return celltitle_s;
	}
	public void setCelltitle_s(String celltitle_s) {
		this.celltitle_s = celltitle_s;
	}

	/**
	 * @return the smshistory
	 */
	public Smshistory getSmshistory() {
		return smshistory;
	}
	/**
	 * @param Smshistory
	 *            the smshistory to set
	 */
	public void setSmshistory(Smshistory smshistory) {
		this.smshistory = smshistory;
	}
}

