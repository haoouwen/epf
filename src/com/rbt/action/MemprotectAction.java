/*
 
 * Package:com.rbt.action
 * FileName: MemprotectAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Memprotect;
import com.rbt.service.IMemprotectService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录会员密保信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 21 09:21:06 CST 2014
 */
@Controller
public class MemprotectAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Memprotect memprotect;
	/*******************业务层接口****************/
	@Autowired
	private IMemprotectService memprotectService;
	
	/*********************集合********************/
	public List memprotectList;//会员密保信息
	
	/*********************字段********************/

	/**
	 * 方法描述：返回新增记录会员密保信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员密保信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(memprotect);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.memprotectService.insert(memprotect);
		this.addActionMessage("新增记录会员密保信息成功！");
		this.memprotect = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员密保信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(memprotect);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.memprotectService.update(memprotect);
		this.addActionMessage("修改记录会员密保信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录会员密保信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memprotectService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录会员密保信息成功");
		}else{
			this.addActionMessage("删除记录会员密保信息失败");
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
		int count=this.memprotectService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		memprotectList = this.memprotectService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员密保信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.memprotect.getId();
		if(id==null || id.equals("")){
			memprotect = new Memprotect();
		}else{
			memprotect = this.memprotectService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MemprotectList
	 */
	public List getMemprotectList() {
		return memprotectList;
	}
	/**
	 * @param memprotectList
	 *  the MemprotectList to set
	 */
	public void setMemprotectList(List memprotectList) {
		this.memprotectList = memprotectList;
	}
	/**
	 * @return the memprotect
	 */
	public Memprotect getMemprotect() {
		return memprotect;
	}
	/**
	 * @param Memprotect
	 *            the memprotect to set
	 */
	public void setMemprotect(Memprotect memprotect) {
		this.memprotect = memprotect;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(memprotect == null){
			memprotect = new Memprotect();
		}
		String id = this.memprotect.getId();
		if(!this.validateFactory.isDigital(id)){
			memprotect = this.memprotectService.get(id);
		}
	}

}

