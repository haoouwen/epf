/*
 
 * Package:com.rbt.action
 * FileName: SelfsepcnameAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Selfspecname;
import com.rbt.service.ISelfspecnameService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 自定义规格名称action类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:32:16 CST 2014
 */
@Controller
public class SelfspecnameAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Selfspecname selfspecname;
	
	/*******************业务层接口****************/
	@Autowired
	private ISelfspecnameService selfsepcnameService;
	
	/*********************集合********************/
	public List selfspecnameList;//自定义规格名称
	
	/*********************字段********************/
	
	
	/**
	 * 方法描述：返回新增自定义规格名称页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增自定义规格名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(selfspecname);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.selfsepcnameService.insert(selfspecname);
		this.addActionMessage("新增自定义规格名称成功！");
		this.selfspecname = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改自定义规格名称信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(selfspecname);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.selfsepcnameService.update(selfspecname);
		this.addActionMessage("修改自定义规格名称成功！");
		return list();
	}
	/**
	 * 方法描述：删除自定义规格名称信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.selfsepcnameService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除自定义规格名称成功");
		}else{
			this.addActionMessage("删除自定义规格名称失败");
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
		int count=this.selfsepcnameService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		selfspecnameList = this.selfsepcnameService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出自定义规格名称信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.selfspecname.getSpec_serial_id();
		if(id==null || id.equals("")){
			selfspecname = new Selfspecname();
		}else{
			selfspecname = this.selfsepcnameService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SelfsepcnameList
	 */
	public List getSelfsepcnameList() {
		return selfspecnameList;
	}
	/**
	 * @param selfspecnameList
	 *  the SelfsepcnameList to set
	 */
	public void setSelfsepcnameList(List selfspecnameList) {
		this.selfspecnameList = selfspecnameList;
	}
	/**
	 * @return the selfsepcname
	 */
	public Selfspecname getSelfsepcname() {
		return selfspecname;
	}
	/**
	 * @param Selfspecname
	 *            the selfspecname to set
	 */
	public void setselfspecname(Selfspecname selfspecname) {
		this.selfspecname = selfspecname;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(selfspecname == null){
			selfspecname = new Selfspecname();
		}
		String id = this.selfspecname.getSpec_serial_id();
		if(!this.validateFactory.isDigital(id)){
			selfspecname = this.selfsepcnameService.get(id);
		}
	}

}

