/*
 
 * Package:com.rbt.action
 * FileName: ConsultingAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Consulting;
import com.rbt.service.IConsultingService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品咨询l回复信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:48:34 CST 2014
 */
@Controller
public class ConsultingAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Consulting consulting;

	/*******************业务层接口****************/
	@Autowired
	private IConsultingService consultingService;

	/*********************集合******************/
	public List consultingList;//记录商品咨询l回复信息信息集合


	
	/**
	 * 方法描述：返回新增记录商品咨询l回复信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品咨询l回复信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		if(commonCheck())return add();
		this.consultingService.insert(consulting);
		this.addActionMessage("新增记录商品咨询l回复信息成功！");
		this.consulting = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品咨询l回复信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		if(commonCheck())return view();
		this.consultingService.update(consulting);
		this.addActionMessage("修改记录商品咨询l回复信息成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 11:02:15 AM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(consulting);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	/**
	 * 方法描述：删除记录商品咨询l回复信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.consultingService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商品咨询l回复信息成功");
		}else{
			this.addActionMessage("删除记录商品咨询l回复信息失败");
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
		int count=this.consultingService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		consultingList = this.consultingService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商品咨询l回复信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.consulting.getRe_id();
		if(id==null || id.equals("")){
			consulting = new Consulting();
		}else{
			consulting = this.consultingService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the consulting
	 */
	public Consulting getConsulting() {
		return consulting;
	}
	/**
	 * @param Consulting
	 *            the consulting to set
	 */
	public void setConsulting(Consulting consulting) {
		this.consulting = consulting;
	}
	
	/**
	 * @return the ConsultingList
	 */
	public List getConsultingList() {
		return consultingList;
	}
	/**
	 * @param consultingList
	 *  the ConsultingList to set
	 */
	public void setConsultingList(List consultingList) {
		this.consultingList = consultingList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(consulting == null){
			consulting = new Consulting();
		}
		String id = this.consulting.getRe_id();
		if(!this.validateFactory.isDigital(id)){
			consulting = this.consultingService.get(id);
		}
	}

}

