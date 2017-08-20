/*
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: DepotAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Depot;
import com.rbt.service.IDepotService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 仓库信息action类
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 05 20:27:34 CST 2015
 */
@Controller
public class DepotAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 仓库信息对象
	 */
	private Depot depot;
	/*******************业务层接口****************/
	/*
	 * 仓库信息业务层接口
	 */
	@Autowired
	private IDepotService depotService;
	
	/*********************集合*******************/
	/*
	 * 仓库信息信息集合
	 */
	public List depotList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String is_sys;//判断是否系统邮件
	public String title_s;//搜索仓库名称字段
	public String contact;//搜索联系人字段
	
	/**
	 * 方法描述：返回新增仓库信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增仓库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(common()){
			return add();
		}
		this.depotService.insert(depot);
		this.addActionMessage("新增仓库信息成功！");
		this.depot = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改仓库信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(common()){
			return view();
		}
	
		this.depotService.update(depot);
		this.addActionMessage("修改仓库信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除仓库信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
	
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除仓库信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.depotService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除仓库信息信息成功!");
		}else{
			this.addActionMessage("删除仓库信息信息失败!");
		}
	
	}
	/**
	 * @author：ZMS
	 * @Method Description：公共数据验证
	 */
	public boolean common(){
		
		// 验证仓库名称不能为空
		if (ValidateUtil.isRequired(depot.getDepot_name())) {
			this.addFieldError("depot.depot_name", "仓库名字不能为空");
		}
		//验证联系电话不能为空
		if (ValidateUtil.isRequired(depot.getPhone())) {
			this.addFieldError("depot.phone", "联系电话不能为空");
		}
		//验证联系人不能为空
		if (ValidateUtil.isRequired(depot.getContact())) {
			this.addFieldError("depot.contact", "联系人不能为空");
		}
		//验证联系邮件不能为空
		if (ValidateUtil.isRequired(depot.getDepot_mail())) {
			this.addFieldError("depot.depot_mail", "联系邮件不能为空");
		}
		//验证联系邮件格式是否正确
		if(ValidateUtil.isEmail(depot.getDepot_mail())){
			this.addFieldError("depot.depot_mail", "联系邮件格式不正确");
		}
		//验证联系电话格式是否正确
		if(ValidateUtil.isphone(depot.getPhone())){
			this.addFieldError("depot.phone", "联系电话格式不正确");
		}
		super.commonValidateField(depot);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		
		Map pageMap = new HashMap();
		// 搜索仓库名称
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("depot_name", title_s);
		}
		//搜索联系人名称
		if(contact!=null&& !contact.equals("")){
			pageMap.put("contact", contact);
		}
		//根据页面提交的条件找出信息总数
		int count=this.depotService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		depotList = this.depotService.getList(pageMap);
		
		return goUrl(INDEXLIST);
		
	}
	/**
	 * 方法描述：根据主键找出仓库信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
	   
		String id = this.depot.getDepot_id();
		if(id==null || id.equals("")){
			depot = new Depot();
		}else{
			depot = this.depotService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the DepotList
	 */
	public List getDepotList() {
		return depotList;
	}
	/**
	 * @param depotList
	 *  the DepotList to set
	 */
	public void setDepotList(List depotList) {
		this.depotList = depotList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(depot == null){
			depot = new Depot();
		}
		String id = this.depot.getDepot_id();
		if(!this.validateFactory.isDigital(id)){
			depot = this.depotService.get(id);
		}
	}
	/**
	 * @return the depot
	 */
	public Depot getDepot() {
		return depot;
	}
	/**
	 * @param Depot
	 *            the depot to set
	 */
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
}

