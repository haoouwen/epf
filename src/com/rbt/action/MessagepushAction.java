/*
 * Package:com.rbt.action
 * FileName: MessagepushAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.function.MessagePushFuc;
import com.rbt.model.Messagepush;
import com.rbt.service.IMessagepushService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 消息推送action类
 * @author 创建人 HXK
 * @date 创建日期 Thu Jul 07 14:40:46 CST 2016
 */
@Controller
public class MessagepushAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 消息推送对象
	 */
	private Messagepush messagepush;
	/*******************业务层接口****************/
	/*
	 * 消息推送业务层接口
	 */
	@Autowired
	private IMessagepushService messagepushService;
	/*********************集合*******************/
	/*
	 * 消息推送信息集合
	 */
	public List messagepushList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;
		
	/**
	 * 方法描述：返回新增消息推送页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增消息推送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(messagepush);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.messagepushService.insert(messagepush);
		this.addActionMessage("新增消息成功！");
		this.messagepush = null;
		return list();
	}

	/**
	 * 方法描述：修改消息推送信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(messagepush);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.messagepushService.update(messagepush);
		this.addActionMessage("修改消息成功！");
		return list();
	}
	/**
	 * 方法描述：删除消息推送信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除消息推送信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.messagepushService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除消息成功!");
		}else{
			this.addActionMessage("删除消息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		if(title_s!=null&&!"".equals(title_s)){
			
			pageMap.put("msgpush_name", title_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.messagepushService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		messagepushList = this.messagepushService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出消息推送信息
	 */
	public String sendMsgView() throws Exception {
		String id = this.messagepush.getMsgpush_id();
		if(id==null || id.equals("")){
			messagepush = new Messagepush();
		}else{
			messagepush = this.messagepushService.get(id);
		}
		return goUrl("sendmsg");
	}
	/**
	 * 方法描述：修改消息推送信息
	 */
	public String sendupdate() throws Exception {
		
		if(messagepush!=null&&messagepush.getContent()!=null){
			MessagePushFuc messagePushFuc=new MessagePushFuc();
			boolean fage=messagePushFuc.sendpush(messagepush,ym_android_appkey,ym_android_appMasterSecret,ym_ios_appkey,ym_ios_appMasterSecret);
			if(fage==true){
				messagepush.setInfo_state("1");//标志已经推送
				this.messagepushService.update(messagepush);
				this.addActionMessage("消息推送成功！");
			}else {
				this.addActionMessage("消息推送失败！");
			}
		}else {
			this.addActionMessage("消息推送失败！");
		}
		return list();
	}
	public String view() throws Exception {
		String id = this.messagepush.getMsgpush_id();
		if(id==null || id.equals("")){
			messagepush = new Messagepush();
		}else{
			messagepush = this.messagepushService.get(id);
		}
		return goUrl(VIEW);
	}
	public List getMessagepushList() {
		return messagepushList;
	}
	public void setMessagepushList(List messagepushList) {
		this.messagepushList = messagepushList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(messagepush == null){
			messagepush = new Messagepush();
		}
		String id = this.messagepush.getMsgpush_id();
		if(!this.validateFactory.isDigital(id)){
			messagepush = this.messagepushService.get(id);
		}
	}
	public Messagepush getMessagepush() {
		return messagepush;
	}
	public void setMessagepush(Messagepush messagepush) {
		this.messagepush = messagepush;
	}
	
	
}

