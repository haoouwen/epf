/*
 
 * Package:com.rbt.action
 * FileName: AttrvalueAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Attrvalue;
import com.rbt.service.IAttrvalueService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录分类属性的值action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Aug 21 15:25:18 CST 2014
 */
@Controller
public class AttrvalueAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Attrvalue attrvalue;

	/*******************业务层接口****************/
	@Autowired
	private IAttrvalueService attrvalueService;

	/*********************集合******************/
	public List attrvalueList;//记录分类属性的值

	/**
	 * 方法描述：返回新增记录分类属性的值页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录分类属性的值
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck()) {
			return add();
		}
		this.attrvalueService.insert(attrvalue);
		this.addActionMessage("新增记录分类属性的值成功！");
		this.attrvalue = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录分类属性的值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck())
			return view();
		this.attrvalueService.update(attrvalue);
		this.addActionMessage("修改记录分类属性的值成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 25, 2014 3:57:19 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		super.commonValidateField(attrvalue);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：删除记录分类属性的值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.attrvalueService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除记录分类属性的值成功");
		} else {
			this.addActionMessage("删除记录分类属性的值失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();

		// 根据页面提交的条件找出信息总数
		int count = this.attrvalueService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		attrvalueList = this.attrvalueService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录分类属性的值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.attrvalue.getTrade_id();
		if (id == null || id.equals("")) {
			attrvalue = new Attrvalue();
		} else {
			attrvalue = this.attrvalueService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the attrvalue
	 */
	public Attrvalue getAttrvalue() {
		return attrvalue;
	}

	/**
	 * @param Attrvalue
	 *            the attrvalue to set
	 */
	public void setAttrvalue(Attrvalue attrvalue) {
		this.attrvalue = attrvalue;
	}
	
	/**
	 * @return the AttrvalueList
	 */
	public List getAttrvalueList() {
		return attrvalueList;
	}

	/**
	 * @param attrvalueList
	 *            the AttrvalueList to set
	 */
	public void setAttrvalueList(List attrvalueList) {
		this.attrvalueList = attrvalueList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (attrvalue == null) {
			attrvalue = new Attrvalue();
		}
		String id = this.attrvalue.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			attrvalue = this.attrvalueService.get(id);
		}
	}

}
