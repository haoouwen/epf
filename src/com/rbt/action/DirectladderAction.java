/*
 
 * Package:com.rbt.action
 * FileName: DirectladderAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Directladder;
import com.rbt.service.IDirectladderService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 预售商品阶梯价格action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:28:18 CST 2014
 */
@Controller
public class DirectladderAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Directladder directladder;

	/*******************业务层接口****************/
	@Autowired
	private IDirectladderService directladderService;

	/*********************集合******************/
	public List directladderList;//预售商品阶梯价格信息集合


	
	/**
	 * 方法描述：返回新增预售商品阶梯价格页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增预售商品阶梯价格
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        //字段验证
		if(commonCheck())return add();
		this.directladderService.insert(directladder);
		this.addActionMessage("新增预售商品阶梯价格成功！");
		this.directladder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改预售商品阶梯价格信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        //字段验证
		if(commonCheck())return view();
		this.directladderService.update(directladder);
		this.addActionMessage("修改预售商品阶梯价格成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:32:09 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(directladder);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	/**
	 * 方法描述：删除预售商品阶梯价格信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.directladder.getTrade_id();
		id = id.replace(" ", "");
		this.directladderService.delete(id);
		this.addActionMessage("删除预售商品阶梯价格成功！");
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
		int count=this.directladderService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		directladderList = this.directladderService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出预售商品阶梯价格信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.directladder.getTrade_id();
		if(id==null || id.equals("")){
			directladder = new Directladder();
		}else{
			directladder = this.directladderService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the DirectladderList
	 */
	public List getDirectladderList() {
		return directladderList;
	}
	
	/**
	 * @return the directladder
	 */
	public Directladder getDirectladder() {
		return directladder;
	}
	/**
	 * @param Directladder
	 *            the directladder to set
	 */
	public void setDirectladder(Directladder directladder) {
		this.directladder = directladder;
	}
	/**
	 * @param directladderList
	 *  the DirectladderList to set
	 */
	public void setDirectladderList(List directladderList) {
		this.directladderList = directladderList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(directladder == null){
			directladder = new Directladder();
		}
		String id = this.directladder.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			directladder = this.directladderService.get(id);
		}
	}

}

