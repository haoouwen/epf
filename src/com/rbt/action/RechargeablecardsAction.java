/*
 * Package:com.rbt.action
 * FileName: RechargeablecardsAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Rechargeablecards;
import com.rbt.service.IRechargeablecardsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 充值卡action类
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 14:01:50 CST 2015
 */
@Controller
public class RechargeablecardsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 充值卡对象
	 */
	private Rechargeablecards rechargeablecards;
	/*******************业务层接口****************/
	/*
	 * 充值卡业务层接口
	 */
	@Autowired
	private IRechargeablecardsService rechargeablecardsService;
	
	/*********************集合*******************/
	/*
	 * 充值卡信息集合
	 */
	public List rechargeablecardsList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String cardskey;//充值卡卡号
	
		
	/**
	 * 方法描述：返回新增充值卡页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增充值卡
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(rechargeablecards);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.rechargeablecardsService.insert(rechargeablecards);
		this.addActionMessage("新增充值卡成功！");
		this.rechargeablecards = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改充值卡信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(rechargeablecards);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.rechargeablecardsService.update(rechargeablecards);
		this.addActionMessage("修改充值卡成功！");
		return list();
	}
	/**
	 * 方法描述：删除充值卡信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除充值卡信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.rechargeablecardsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除充值卡信息成功!");
		}else{
			this.addActionMessage("删除充值卡信息失败!");
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
		if(rechargeablecards!=null){
			pageMap.put("cardid", rechargeablecards.getCardid());
		}
		if(cardskey!=null&&!"".equals(cardskey)){
			pageMap.put("cardskey", cardskey);
		}
		//根据页面提交的条件找出信息总数
		int count=this.rechargeablecardsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		rechargeablecardsList = this.rechargeablecardsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出充值卡信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.rechargeablecards.getCardid();
		if(id==null || id.equals("")){
			rechargeablecards = new Rechargeablecards();
		}else{
			rechargeablecards = this.rechargeablecardsService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the RechargeablecardsList
	 */
	public List getRechargeablecardsList() {
		return rechargeablecardsList;
	}
	/**
	 * @param rechargeablecardsList
	 *  the RechargeablecardsList to set
	 */
	public void setRechargeablecardsList(List rechargeablecardsList) {
		this.rechargeablecardsList = rechargeablecardsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(rechargeablecards == null){
			rechargeablecards = new Rechargeablecards();
		}
		String id = this.rechargeablecards.getCardid();
		if(!this.validateFactory.isDigital(id)){
			rechargeablecards = this.rechargeablecardsService.get(id);
		}
	}
	/**
	 * @return the rechargeablecards
	 */
	public Rechargeablecards getRechargeablecards() {
		return rechargeablecards;
	}
	/**
	 * @param Rechargeablecards
	 *            the rechargeablecards to set
	 */
	public void setRechargeablecards(Rechargeablecards rechargeablecards) {
		this.rechargeablecards = rechargeablecards;
	}
}

