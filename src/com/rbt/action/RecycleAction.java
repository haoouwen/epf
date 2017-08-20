/*
 
 * Package:com.rbt.action
 * FileName: RecycleAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Recycle;
import com.rbt.model.Sendbox;
import com.rbt.service.IRecycleService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录回收站信息action类
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 04 15:55:48 CST 2014
 */
@Controller
public class RecycleAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Recycle recycle;
	private Sendbox sendbox;
	
	/*******************业务层接口****************/
	@Autowired
	private IRecycleService recycleService;
	
	/*********************集合********************/
	public List recycleList;//回收站
	
	/*********************字段********************/
	
	public String cust_name;//会员名
	public String logo_path;//会员头像
	public String rate_name_s;
	
	/**
	 * 方法描述：返回新增记录回收站信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录回收站信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(recycle);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.recycleService.insert(recycle);
		this.addActionMessage("新增信息成功！");
		this.recycle = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录回收站信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(recycle);
		if(super.ifvalidatepass){
			return view();
		}
		this.recycleService.update(recycle);
		this.addActionMessage("修改信息成功！");
		return list();
	}
	
	/**
	 * 方法描述：删除记录回收站信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		//处理删除
		this.recycleService.dealDelete(chb_id);
		this.addActionMessage("删除信息成功！");
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @throws Exception 
	 * @date : Mar 5, 2014 12:29:17 PM
	 * @Method Description :还原信件
	 */
	public String revert() throws Exception{
		this.recycleService.revert(chb_id);
		this.addActionMessage("还原站内信成功！");
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
		if(rate_name_s!=null &&!"".equals(rate_name_s)){
			pageMap.put("cust_name", rate_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.recycleService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		recycleList = this.recycleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据主键找出记录回收站信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		//获取回收站的ID
		String id = this.recycle.getRecycle_id();
		if(id==null || id.equals("")){
			recycle = new Recycle();
			return list();
		}else{
			recycle = this.recycleService.get(id);
		}
		Map sendboxMap=this.recycleService.dealView(recycle);
		cust_name =sendboxMap.get("cust_name").toString();
		logo_path = sendboxMap.get("logo_path").toString();
		sendbox = (Sendbox) sendboxMap.get("sendbox");
		return goUrl(VIEW);
	}
	
	/**
	 * @return the RecycleList
	 */
	public List getRecycleList() {
		return recycleList;
	}
	/**
	 * @param recycleList
	 *  the RecycleList to set
	 */
	public void setRecycleList(List recycleList) {
		this.recycleList = recycleList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(recycle == null){
			recycle = new Recycle();
		}
		String id = this.recycle.getRecycle_id();
		if(!this.validateFactory.isDigital(id)){
			recycle = this.recycleService.get(id);
		}
	}
	public Sendbox getSendbox() {
		return sendbox;
	}
	public void setSendbox(Sendbox sendbox) {
		this.sendbox = sendbox;
	}
	/**
	 * @return the recycle
	 */
	public Recycle getRecycle() {
		return recycle;
	}
	/**
	 * @param Recycle
	 *            the recycle to set
	 */
	public void setRecycle(Recycle recycle) {
		this.recycle = recycle;
	}
}

