/*
 * Package:com.rbt.action
 * FileName: ExcouponsAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Excoupons;
import com.rbt.service.IExcouponsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 优惠券兑换表action类
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:10:41 CST 2015
 */
@Controller
public class ExcouponsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 优惠券兑换表对象
	 */
	private Excoupons excoupons;
	/*******************业务层接口****************/
	/*
	 * 优惠券兑换表业务层接口
	 */
	@Autowired
	private IExcouponsService excouponsService;
	
	/*********************集合*******************/
	/*
	 * 优惠券兑换表信息集合
	 */
	public List excouponsList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增优惠券兑换表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增优惠券兑换表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(excoupons);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.excouponsService.insert(excoupons);
		this.addActionMessage("新增优惠券兑换表成功！");
		this.excoupons = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改优惠券兑换表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(excoupons);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.excouponsService.update(excoupons);
		this.addActionMessage("修改优惠券兑换表成功！");
		return list();
	}
	/**
	 * 方法描述：删除优惠券兑换表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除优惠券兑换表信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.excouponsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除优惠券兑换表信息成功!");
		}else{
			this.addActionMessage("删除优惠券兑换表信息失败!");
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
		int count=this.excouponsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		excouponsList = this.excouponsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出优惠券兑换表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.excoupons.getEx_id();
		if(id==null || id.equals("")){
			excoupons = new Excoupons();
		}else{
			excoupons = this.excouponsService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the ExcouponsList
	 */
	public List getExcouponsList() {
		return excouponsList;
	}
	/**
	 * @param excouponsList
	 *  the ExcouponsList to set
	 */
	public void setExcouponsList(List excouponsList) {
		this.excouponsList = excouponsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(excoupons == null){
			excoupons = new Excoupons();
		}
		String id = this.excoupons.getEx_id();
		if(!this.validateFactory.isDigital(id)){
			excoupons = this.excouponsService.get(id);
		}
	}
	/**
	 * @return the excoupons
	 */
	public Excoupons getExcoupons() {
		return excoupons;
	}
	/**
	 * @param Excoupons
	 *            the excoupons to set
	 */
	public void setExcoupons(Excoupons excoupons) {
		this.excoupons = excoupons;
	}
}

