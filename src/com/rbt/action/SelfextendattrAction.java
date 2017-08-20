/*
 
 * Package:com.rbt.action
 * FileName: SelfextendattrAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Selfextendattr;
import com.rbt.service.ISelfextendattrService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品自定义属性action类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 10:59:08 CST 2014
 */
@Controller
public class SelfextendattrAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Selfextendattr selfextendattr;
	/*******************业务层接口****************/
	@Autowired
	private ISelfextendattrService selfextendattrService;
	
	/*********************集合********************/
	public List selfextendattrList;//商品自定义属性信息
	
	/*********************字段********************/

	/**
	 * 方法描述：返回新增商品自定义属性页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增商品自定义属性
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(selfextendattr);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.selfextendattrService.insert(selfextendattr);
		this.addActionMessage("新增商品自定义属性成功！");
		this.selfextendattr = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品自定义属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(selfextendattr);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.selfextendattrService.update(selfextendattr);
		this.addActionMessage("修改商品自定义属性成功！");
		return list();
	}
	/**
	 * 方法描述：删除商品自定义属性信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.selfextendattrService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商品自定义属性成功");
		}else{
			this.addActionMessage("删除商品自定义属性失败");
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
		int count=this.selfextendattrService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		selfextendattrList = this.selfextendattrService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出商品自定义属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.selfextendattr.getSelf_attr_id();
		if(id==null || id.equals("")){
			selfextendattr = new Selfextendattr();
		}else{
			selfextendattr = this.selfextendattrService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SelfextendattrList
	 */
	public List getSelfextendattrList() {
		return selfextendattrList;
	}
	/**
	 * @param selfextendattrList
	 *  the SelfextendattrList to set
	 */
	public void setSelfextendattrList(List selfextendattrList) {
		this.selfextendattrList = selfextendattrList;
	}
	/**
	 * @return the selfextendattr
	 */
	public Selfextendattr getSelfextendattr() {
		return selfextendattr;
	}
	/**
	 * @param Selfextendattr
	 *            the selfextendattr to set
	 */
	public void setSelfextendattr(Selfextendattr selfextendattr) {
		this.selfextendattr = selfextendattr;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(selfextendattr == null){
			selfextendattr = new Selfextendattr();
		}
		String id = this.selfextendattr.getSelf_attr_id();
		if(!this.validateFactory.isDigital(id)){
			selfextendattr = this.selfextendattrService.get(id);
		}
	}

}

