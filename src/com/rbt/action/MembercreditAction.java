/*
 
 * Package:com.rbt.action
 * FileName: MembercreditAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员信誉action类
 * @author 创建人 XBY
 * @date 创建日期 Tue Apr 22 19:59:28 CST 2014
 */
@Controller
public class MembercreditAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 会员信誉对象
	 */
	private Membercredit membercredit;
	/*******************业务层接口****************/
	/*
	 * 会员信誉业务层接口
	 */
	@Autowired
	private IMembercreditService membercreditService;
	
	/*********************集合*******************/
	/*
	 * 会员信誉信息集合
	 */
	public List membercreditList;
	/*********************字段*******************/
	
	
		
	/**
	 * 方法描述：返回新增会员信誉页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员信誉
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(membercredit);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.membercreditService.insert(membercredit);
		this.addActionMessage("新增会员信誉成功！");
		this.membercredit = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改会员信誉信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(membercredit);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.membercreditService.update(membercredit);
		this.addActionMessage("修改会员信誉成功！");
		return list();
	}
	/**
	 * 方法描述：删除会员信誉信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除会员信誉信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.membercreditService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除会员信誉信息成功!");
		}else{
			this.addActionMessage("删除会员信誉信息失败!");
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
		int count=this.membercreditService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		membercreditList = this.membercreditService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员信誉信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.membercredit.getCredit_id();
		if(id==null || id.equals("")){
			membercredit = new Membercredit();
		}else{
			membercredit = this.membercreditService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MembercreditList
	 */
	public List getMembercreditList() {
		return membercreditList;
	}
	/**
	 * @param membercreditList
	 *  the MembercreditList to set
	 */
	public void setMembercreditList(List membercreditList) {
		this.membercreditList = membercreditList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(membercredit == null){
			membercredit = new Membercredit();
		}
		String id = this.membercredit.getCredit_id();
		if(!this.validateFactory.isDigital(id)){
			membercredit = this.membercreditService.get(id);
		}
	}
	/**
	 * @return the membercredit
	 */
	public Membercredit getMembercredit() {
		return membercredit;
	}
	/**
	 * @param Membercredit
	 *            the membercredit to set
	 */
	public void setMembercredit(Membercredit membercredit) {
		this.membercredit = membercredit;
	}
}

