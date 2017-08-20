/*
 
 * Package:com.rbt.action
 * FileName: OnlinepaytradeAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Onlinepaytrade;
import com.rbt.service.IOnlinepaytradeService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录网银支付流水信息action类
 * @author 创建人 WXP
 * @date 创建日期 Fri Dec 07 14:55:14 CST 2014
 */
@Controller
public class OnlinepaytradeAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Onlinepaytrade onlinepaytrade;
	
	/*******************业务层接口****************/
	@Autowired
	private IOnlinepaytradeService onlinepaytradeService;
	
	/*********************集合********************/
	public List onlinepaytradeList;//网银支付流水信息
	
	/*********************字段********************/
	

	/**
	 * 方法描述：返回新增记录网银支付流水信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录网银支付流水信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(onlinepaytrade);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.onlinepaytradeService.insert(onlinepaytrade);
		this.addActionMessage("新增记录网银支付流水信息成功！");
		this.onlinepaytrade = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录网银支付流水信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(onlinepaytrade);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.onlinepaytradeService.update(onlinepaytrade);
		this.addActionMessage("修改记录网银支付流水信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录网银支付流水信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.onlinepaytradeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录网银支付流水信息成功");
		}else{
			this.addActionMessage("删除记录网银支付流水信息失败");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.onlinepaytradeService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		onlinepaytradeList = this.onlinepaytradeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录网银支付流水信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.onlinepaytrade.getTrade_id();
		if(id==null || id.equals("")){
			onlinepaytrade = new Onlinepaytrade();
		}else{
			onlinepaytrade = this.onlinepaytradeService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the OnlinepaytradeList
	 */
	public List getOnlinepaytradeList() {
		return onlinepaytradeList;
	}
	/**
	 * @param onlinepaytradeList
	 *  the OnlinepaytradeList to set
	 */
	public void setOnlinepaytradeList(List onlinepaytradeList) {
		this.onlinepaytradeList = onlinepaytradeList;
	}
	/**
	 * @return the onlinepaytrade
	 */
	public Onlinepaytrade getOnlinepaytrade() {
		return onlinepaytrade;
	}
	/**
	 * @param Onlinepaytrade
	 *            the onlinepaytrade to set
	 */
	public void setOnlinepaytrade(Onlinepaytrade onlinepaytrade) {
		this.onlinepaytrade = onlinepaytrade;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(onlinepaytrade == null){
			onlinepaytrade = new Onlinepaytrade();
		}
		String id = this.onlinepaytrade.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			onlinepaytrade = this.onlinepaytradeService.get(id);
		}
	}

}

