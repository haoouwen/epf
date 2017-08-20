/*
 
 * Package:com.rbt.action
 * FileName: SysfundAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.model.Sysfund;
import com.rbt.service.ISysfundService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 平台总资金action类
 * @author 创建人 HXM
 * @date 创建日期 Sun Jul 13 17:17:59 CST 2014
 */
@Controller
public class SysfundAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 平台总资金对象
	 */
	private Sysfund sysfund;
	/*******************业务层接口****************/
	/*
	 * 平台总资金业务层接口
	 */
	@Autowired
	private ISysfundService sysfundService;
	
	/*********************集合*******************/
	/*
	 * 平台总资金信息集合
	 */
	public List sysfundList;
	/*********************字段*******************/
	
	
		
	/**
	 * 方法描述：返回新增平台总资金页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增平台总资金
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(sysfund);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.sysfundService.insert(sysfund);
		this.addActionMessage("新增平台总资金成功！");
		this.sysfund = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改平台总资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(sysfund);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.sysfundService.update(sysfund);
		this.addActionMessage("修改平台总资金成功！");
		return list();
	}
	/**
	 * 方法描述：删除平台总资金信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除平台总资金信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.sysfundService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除平台总资金信息成功!");
		}else{
			this.addActionMessage("删除平台总资金信息失败!");
		}
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
		int count=this.sysfundService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sysfundList = this.sysfundService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出平台总资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sysfund.getSysfund_id();
		if(id==null || id.equals("")){
			sysfund = new Sysfund();
		}else{
			sysfund = this.sysfundService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * 平台统计
	 * @return
	 * @throws Exception
	 */
	public String count()throws Exception{
	    //运营商的余额信息
		sysfund = this.sysfundService.get("0");
		return goUrl("count");
	}
	
	/**
	 * @return the SysfundList
	 */
	public List getSysfundList() {
		return sysfundList;
	}
	/**
	 * @param sysfundList
	 *  the SysfundList to set
	 */
	public void setSysfundList(List sysfundList) {
		this.sysfundList = sysfundList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sysfund == null){
			sysfund = new Sysfund();
		}
		String id = this.sysfund.getSysfund_id();
		if(!this.validateFactory.isDigital(id)){
			sysfund = this.sysfundService.get(id);
		}
	}
	/**
	 * @return the sysfund
	 */
	public Sysfund getSysfund() {
		return sysfund;
	}
	/**
	 * @param Sysfund
	 *            the sysfund to set
	 */
	public void setSysfund(Sysfund sysfund) {
		this.sysfund = sysfund;
	}
}

