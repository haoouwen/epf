/*
 
 * Package:com.rbt.action
 * FileName: ExpressfundAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Expressfund;
import com.rbt.service.IExpressfundService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录直通车资金信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 10:11:33 CST 2014
 */
@Controller
public class ExpressfundAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Expressfund expressfund;

	/*******************业务层接口****************/
	@Autowired
	private IExpressfundService expressfundService;

	/*********************集合******************/
	public List expressfundList;//记录直通车资金信息信息集合


	
	/**
	 * 方法描述：返回新增记录直通车资金信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录直通车资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        //字段验证
		if(commonCheck())return add();	
		this.expressfundService.insert(expressfund);
		this.addActionMessage("新增记录直通车资金信息成功！");
		this.expressfund = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录直通车资金信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        //字段验证
		if(commonCheck())return view();	
		this.expressfundService.update(expressfund);
		this.addActionMessage("修改记录直通车资金信息成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 10:03:11 AM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(expressfund);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	/**
	 * 方法描述：删除记录直通车资金信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.expressfundService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录直通车资金信息成功");
		}else{
			this.addActionMessage("删除记录直通车资金信息失败");
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
		int count=this.expressfundService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		expressfundList = this.expressfundService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录直通车资金信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.expressfund.getCust_id();
		if(id==null || id.equals("")){
			expressfund = new Expressfund();
		}else{
			expressfund = this.expressfundService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the expressfund
	 */
	public Expressfund getExpressfund() {
		return expressfund;
	}
	/**
	 * @param Expressfund
	 *            the expressfund to set
	 */
	public void setExpressfund(Expressfund expressfund) {
		this.expressfund = expressfund;
	}
	/**
	 * @return the ExpressfundList
	 */
	public List getExpressfundList() {
		return expressfundList;
	}
	/**
	 * @param expressfundList
	 *  the ExpressfundList to set
	 */
	public void setExpressfundList(List expressfundList) {
		this.expressfundList = expressfundList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(expressfund == null){
			expressfund = new Expressfund();
		}
		String id = this.expressfund.getCust_id();
		if(!this.validateFactory.isDigital(id)){
			expressfund = this.expressfundService.get(id);
		}
	}

}

