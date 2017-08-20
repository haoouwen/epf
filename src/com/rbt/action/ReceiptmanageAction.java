/*
 
 * Package:com.rbt.action
 * FileName: ReceiptmanageAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Receiptmanage;
import com.rbt.service.IReceiptmanageService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录单据管理信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:13:55 CST 2014
 */
@Controller
public class ReceiptmanageAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Receiptmanage receiptmanage;
	
	/*******************业务层接口****************/
	@Autowired
	private IReceiptmanageService receiptmanageService;
	
	/*********************集合********************/
	public List receiptmanageList;//单据管理信息
	
	/*********************字段********************/
	
	
	/**
	 * 方法描述：返回新增记录单据管理信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录单据管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(receiptmanage);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.receiptmanageService.insert(receiptmanage);
		this.addActionMessage("新增记录单据管理信息成功！");
		this.receiptmanage = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录单据管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String tip="成功";
		//处理更新
		if(!this.receiptmanageService.dealUpdate(receiptmanage,this.session_cust_id)){
			tip="失败";
		}
		this.addActionMessage("修改单据管理信息"+tip+"！");
		return list();
	}
	/**
	 * 方法描述：删除记录单据管理信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.receiptmanageService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录单据管理信息成功");
		}else{
			this.addActionMessage("删除记录单据管理信息成功失败");
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
			pageMap.put("cust_id", this.session_cust_id);
		//根据页面提交的条件找出信息总数
		int count=this.receiptmanageService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		receiptmanageList = this.receiptmanageService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录单据管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.receiptmanage.getTrade_id();
		if(id==null || id.equals("")){
			receiptmanage = new Receiptmanage();
		}else{
			receiptmanage = this.receiptmanageService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the ReceiptmanageList
	 */
	public List getReceiptmanageList() {
		return receiptmanageList;
	}
	/**
	 * @param receiptmanageList
	 *  the ReceiptmanageList to set
	 */
	public void setReceiptmanageList(List receiptmanageList) {
		this.receiptmanageList = receiptmanageList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(receiptmanage == null){
			receiptmanage = new Receiptmanage();
		}
		String id = this.receiptmanage.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			receiptmanage = this.receiptmanageService.get(id);
		}
	}

	/**
	 * @return the receiptmanage
	 */
	public Receiptmanage getReceiptmanage() {
		return receiptmanage;
	}
	/**
	 * @param Receiptmanage
	 *            the receiptmanage to set
	 */
	public void setReceiptmanage(Receiptmanage receiptmanage) {
		this.receiptmanage = receiptmanage;
	}

}

