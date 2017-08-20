/*
 
 * Package:com.rbt.action
 * FileName: SelfsepcvalueAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Selfspecvalue;
import com.rbt.service.ISelfspecvalueService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 自定义规格值action类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:33:30 CST 2014
 */
@Controller
public class SelfspecvalueAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Selfspecvalue selfsepcvalue;
	/*******************业务层接口****************/

	@Autowired
	private ISelfspecvalueService selfsepcvalueService;
	/*********************集合********************/
	
	public List selfsepcvalueList;//自定义规格值
	/*********************字段********************/

	/**
	 * 方法描述：返回新增自定义规格值页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增自定义规格值
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(selfsepcvalue);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.selfsepcvalueService.insert(selfsepcvalue);
		this.addActionMessage("新增自定义规格值成功！");
		this.selfsepcvalue = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改自定义规格值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(selfsepcvalue);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.selfsepcvalueService.update(selfsepcvalue);
		this.addActionMessage("修改自定义规格值成功！");
		return list();
	}
	/**
	 * 方法描述：删除自定义规格值信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.selfsepcvalueService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除自定义规格值成功");
		}else{
			this.addActionMessage("删除自定义规格值失败");
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
		int count=this.selfsepcvalueService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		selfsepcvalueList = this.selfsepcvalueService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出自定义规格值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.selfsepcvalue.getSerial_id();
		if(id==null || id.equals("")){
			selfsepcvalue = new Selfspecvalue();
		}else{
			selfsepcvalue = this.selfsepcvalueService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SelfsepcvalueList
	 */
	public List getSelfsepcvalueList() {
		return selfsepcvalueList;
	}
	/**
	 * @param selfsepcvalueList
	 *  the SelfsepcvalueList to set
	 */
	public void setSelfsepcvalueList(List selfsepcvalueList) {
		this.selfsepcvalueList = selfsepcvalueList;
	}
	/**
	 * @return the selfsepcvalue
	 */
	public Selfspecvalue getSelfsepcvalue() {
		return selfsepcvalue;
	}
	/**
	 * @param Selfspecvalue
	 *            the selfsepcvalue to set
	 */
	public void setSelfsepcvalue(Selfspecvalue selfsepcvalue) {
		this.selfsepcvalue = selfsepcvalue;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(selfsepcvalue == null){
			selfsepcvalue = new Selfspecvalue();
		}
		String id = this.selfsepcvalue.getSerial_id();
		if(!this.validateFactory.isDigital(id)){
			selfsepcvalue = this.selfsepcvalueService.get(id);
		}
	}

}

