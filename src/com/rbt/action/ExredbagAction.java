/*
 * Package:com.rbt.action
 * FileName: ExredbagAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Exredbag;
import com.rbt.service.IExredbagService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 红包兑换表action类
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:33:32 CST 2015
 */
@Controller
public class ExredbagAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 红包兑换表对象
	 */
	private Exredbag exredbag;
	/*******************业务层接口****************/
	/*
	 * 红包兑换表业务层接口
	 */
	@Autowired
	private IExredbagService exredbagService;
	
	/*********************集合*******************/
	/*
	 * 红包兑换表信息集合
	 */
	public List exredbagList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增红包兑换表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增红包兑换表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(exredbag);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.exredbagService.insert(exredbag);
		this.addActionMessage("新增红包兑换表成功！");
		this.exredbag = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改红包兑换表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(exredbag);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.exredbagService.update(exredbag);
		this.addActionMessage("修改红包兑换表成功！");
		return list();
	}
	/**
	 * 方法描述：删除红包兑换表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除红包兑换表信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.exredbagService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除红包兑换表信息成功!");
		}else{
			this.addActionMessage("删除红包兑换表信息失败!");
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
		int count=this.exredbagService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		exredbagList = this.exredbagService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出红包兑换表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.exredbag.getEx_id();
		if(id==null || id.equals("")){
			exredbag = new Exredbag();
		}else{
			exredbag = this.exredbagService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the ExredbagList
	 */
	public List getExredbagList() {
		return exredbagList;
	}
	/**
	 * @param exredbagList
	 *  the ExredbagList to set
	 */
	public void setExredbagList(List exredbagList) {
		this.exredbagList = exredbagList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(exredbag == null){
			exredbag = new Exredbag();
		}
		String id = this.exredbag.getEx_id();
		if(!this.validateFactory.isDigital(id)){
			exredbag = this.exredbagService.get(id);
		}
	}
	/**
	 * @return the exredbag
	 */
	public Exredbag getExredbag() {
		return exredbag;
	}
	/**
	 * @param Exredbag
	 *            the exredbag to set
	 */
	public void setExredbag(Exredbag exredbag) {
		this.exredbag = exredbag;
	}
}

