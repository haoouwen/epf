/*
 
 * Package:com.rbt.action
 * FileName: SelfparagroupAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Selfparagroup;
import com.rbt.service.ISelfparagroupService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录自定义参数组信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 13:36:38 CST 2014
 */
@Controller
public class SelfparagroupAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Selfparagroup selfparagroup;
	
	/*******************业务层接口****************/

	@Autowired
	private ISelfparagroupService selfparagroupService;
	/*********************集合********************/
	public List selfparagroupList;//自定义参数组信息
	
	/*********************字段********************/
	
	/**
	 * 方法描述：返回新增记录自定义参数组信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录自定义参数组信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(selfparagroup);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.selfparagroupService.insert(selfparagroup);
		this.addActionMessage("新增记录自定义参数组信息成功！");
		this.selfparagroup = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录自定义参数组信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(selfparagroup);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.selfparagroupService.update(selfparagroup);
		this.addActionMessage("修改记录自定义参数组信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录自定义参数组信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.selfparagroupService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录自定义参数组信息成功");
		}else{
			this.addActionMessage("删除记录自定义参数组信息失败");
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
		int count=this.selfparagroupService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		selfparagroupList = this.selfparagroupService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录自定义参数组信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.selfparagroup.getSlef_para_group_id();
		if(id==null || id.equals("")){
			selfparagroup = new Selfparagroup();
		}else{
			selfparagroup = this.selfparagroupService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SelfparagroupList
	 */
	public List getSelfparagroupList() {
		return selfparagroupList;
	}
	/**
	 * @param selfparagroupList
	 *  the SelfparagroupList to set
	 */
	public void setSelfparagroupList(List selfparagroupList) {
		this.selfparagroupList = selfparagroupList;
	}
	/**
	 * @return the selfparagroup
	 */
	public Selfparagroup getSelfparagroup() {
		return selfparagroup;
	}
	/**
	 * @param Selfparagroup
	 *            the selfparagroup to set
	 */
	public void setSelfparagroup(Selfparagroup selfparagroup) {
		this.selfparagroup = selfparagroup;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(selfparagroup == null){
			selfparagroup = new Selfparagroup();
		}
		String id = this.selfparagroup.getSlef_para_group_id();
		if(!this.validateFactory.isDigital(id)){
			selfparagroup = this.selfparagroupService.get(id);
		}
	}

}

