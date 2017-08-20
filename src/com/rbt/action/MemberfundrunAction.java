/*
 
 * Package:com.rbt.action
 * FileName: MemberfundrunAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberfundrun;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberfundrunService;
import com.rbt.service.ISysfundService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 审核余额调整action类
 * @author 创建人 XBY
 * @date 创建日期 Mon Jun 09 15:36:58 CST 2014
 */
@Controller
public class MemberfundrunAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 审核余额调整对象
	 */
	private Memberfundrun memberfundrun;
	private Memberfund memberfund;
	private Fundhistory fundhistory;
	/*******************业务层接口****************/
	/*
	 * 审核余额调整业务层接口
	 */
	@Autowired
	private IMemberfundrunService memberfundrunService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IFundhistoryService fundhistoryService;
	@Autowired
	private ISysfundService sysfundService;
	
	/*********************集合*******************/
	/*
	 * 审核余额调整信息集合
	 */
	public List memberfundrunList;
	/*********************字段*******************/
	public String user_name_s;//会员名称
	public String reason;//审核理由
	public String info_state_s;//审核状态
	public String info_state;//审核状态
	/**
	 * 方法描述：返回新增审核余额调整页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增审核余额调整
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(memberfundrun);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.memberfundrunService.insert(memberfundrun);
		this.addActionMessage("新增审核余额调整成功！");
		this.memberfundrun = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改审核余额调整信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=memberfundrun.getFund_id();
		if(id==null){
			return list();
		}else{
		memberfundrun=this.memberfundrunService.get(id);
		if(ValidateUtil.isRequired(info_state)){
			this.addFieldError("memberfundrun.info_state", "请选择审核状态！");
			return view();
		}
		if(info_state.equals("2")&&ValidateUtil.isRequired(reason)){
			this.addFieldError("reason", "请输入审核不通过的理由！");
			return view();
		}
		//审核状态
		memberfundrun.setInfo_state(info_state);
		//审核理由
		memberfundrun.setAuditreason(reason);
		memberfundrun.setAudit_user_name(session_user_name);
		fundhistory=new Fundhistory();
		String cust_id = memberfundrun.getCust_id();
		double amount = memberfundrun.getFund_num();
		fundhistory.setCust_id(this.memberfundrun.getCust_id());
		// 判断收入 或者 支出    "0"表示收入  "1"表示支出
		if (memberfundrun.getFund_type().equals("0")) {
			//会员入金操作
			double value_usefund = memberfundService.outAndInNum(cust_id,amount, 1);
			//平台入金操作
			sysfundService.outAndInNum(amount, 1);
			//流水记录
			fundhistory.setFund_in(amount);
			fundhistory.setFund_out(0.00);
			fundhistory.setBalance(value_usefund);
		} else {
			//会员出金操作
			double value_usefund = memberfundService.outAndInNum(cust_id,amount, 0);
			//平台出金操作
			sysfundService.outAndInNum(amount, 0);
			//流水记录
			fundhistory.setFund_in(0.00);
			fundhistory.setFund_out(amount);
			fundhistory.setBalance(value_usefund);
		}
		fundhistory.setCust_id(cust_id);
		fundhistory.setUser_id(this.session_user_id);
		fundhistory.setReason(memberfundrun.getReason());
		// 更新余额表
		this.fundhistoryService.insert(fundhistory);
		this.memberfundrunService.update(memberfundrun);
		this.addActionMessage("审核余额调整成功！");
		}
		return list();
	}
	/**
	 * 方法描述：删除审核余额调整信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除审核余额调整信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.memberfundrunService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除审核余额调整信息成功!");
		}else{
			this.addActionMessage("删除审核余额调整信息失败!");
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
		//审核状态
		if(!ValidateUtil.isRequired(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		if(!ValidateUtil.isRequired(user_name_s)){
		 pageMap.put("user_name", user_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.memberfundrunService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		memberfundrunList = this.memberfundrunService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出审核余额调整信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.memberfundrun.getFund_id();
		if(id==null || id.equals("")){
			memberfundrun = new Memberfundrun();
		}else{
			memberfundrun = this.memberfundrunService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MemberfundrunList
	 */
	public List getMemberfundrunList() {
		return memberfundrunList;
	}
	/**
	 * @param memberfundrunList
	 *  the MemberfundrunList to set
	 */
	public void setMemberfundrunList(List memberfundrunList) {
		this.memberfundrunList = memberfundrunList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(memberfundrun == null){
			memberfundrun = new Memberfundrun();
		}
		String id = this.memberfundrun.getFund_id();
		if(!this.validateFactory.isDigital(id)){
			memberfundrun = this.memberfundrunService.get(id);
		}
	}
	/**
	 * @return the memberfundrun
	 */
	public Memberfundrun getMemberfundrun() {
		return memberfundrun;
	}
	/**
	 * @param Memberfundrun
	 *            the memberfundrun to set
	 */
	public void setMemberfundrun(Memberfundrun memberfundrun) {
		this.memberfundrun = memberfundrun;
	}
}

