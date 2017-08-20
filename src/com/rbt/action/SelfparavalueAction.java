/*
 
 * Package:com.rbt.action
 * FileName: SelfparavalueAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Selfparavalue;
import com.rbt.service.ISelfparavalueService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录自定义参数值信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 14:34:05 CST 2014
 */
@Controller
public class SelfparavalueAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Selfparavalue selfparavalue;
	
	/*******************业务层接口****************/
	@Autowired
	private ISelfparavalueService selfparavalueService;
	
	/*********************集合********************/
	public List selfparavalueList;//自定义参数值信息
	
	/*********************字段********************/

	/**
	 * 方法描述：返回新增记录自定义参数值信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录自定义参数值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(selfparavalue);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.selfparavalueService.insert(selfparavalue);
		this.addActionMessage("新增记录自定义参数值信息成功！");
		this.selfparavalue = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录自定义参数值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(selfparavalue);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.selfparavalueService.update(selfparavalue);
		this.addActionMessage("修改记录自定义参数值信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录自定义参数值信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.selfparavalueService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录自定义参数值信息成功");
		}else{
			this.addActionMessage("删除记录自定义参数值信息失败");
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
		int count=this.selfparavalueService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		selfparavalueList = this.selfparavalueService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录自定义参数值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.selfparavalue.getSlef_para_value_id();
		if(id==null || id.equals("")){
			selfparavalue = new Selfparavalue();
		}else{
			selfparavalue = this.selfparavalueService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SelfparavalueList
	 */
	public List getSelfparavalueList() {
		return selfparavalueList;
	}
	/**
	 * @param selfparavalueList
	 *  the SelfparavalueList to set
	 */
	public void setSelfparavalueList(List selfparavalueList) {
		this.selfparavalueList = selfparavalueList;
	}
	/**
	 * @return the selfparavalue
	 */
	public Selfparavalue getSelfparavalue() {
		return selfparavalue;
	}
	/**
	 * @param Selfparavalue
	 *            the selfparavalue to set
	 */
	public void setSelfparavalue(Selfparavalue selfparavalue) {
		this.selfparavalue = selfparavalue;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(selfparavalue == null){
			selfparavalue = new Selfparavalue();
		}
		String id = this.selfparavalue.getSlef_para_value_id();
		if(!this.validateFactory.isDigital(id)){
			selfparavalue = this.selfparavalueService.get(id);
		}
	}

}

