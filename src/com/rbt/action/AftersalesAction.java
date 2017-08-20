/*
 
 * Package:com.rbt.action
 * FileName: AftersalesAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Aftersales;
import com.rbt.service.IAftersalesService;
import com.rbt.service.IShopconfigService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录售后维护信息action类
 * @author 创建人 HZX
 * @date 创建日期 Tue Feb 26 16:32:24 CST 2014
 */
@Controller
public class AftersalesAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Aftersales aftersales;
	
	/*******************业务层接口****************/
	@Autowired
	private IAftersalesService aftersalesService;
	@Autowired
	private IShopconfigService shopconfigService;
	
	/*********************集合******************/
	public List shopconfigList;//商店配置
	public List aftersalesList;//售后服务
	
	/**
	 * 方法描述：返回新增记录售后维护信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录售后维护信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		if(commonCheck())return add();
		this.aftersalesService.insert(aftersales);
		this.addActionMessage("新增售后维护信息成功！");
		return add();
	}

	/**
	 * 方法描述：修改记录售后维护信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(commonCheck()){
			return view();
		}
		this.aftersalesService.update(aftersales);
		this.addActionMessage("修改售后维护信息成功！");
		return view();
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 11:11:22 AM
	 * @Method Description：公共验证数据
	 */
	public boolean commonCheck()throws Exception{
		// 会员标识不能为空
		if (ValidateUtil.isRequired(this.aftersales.getCust_id())) {
			this.addFieldError("aftersales.cust_id", "会员标识不能为空");
		}
		super.commonValidateField(aftersales);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：删除记录售后维护信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.aftersalesService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录售后维护信息成功");
		}else{
			this.addActionMessage("删除记录售后维护信息失败");
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
		int count=this.aftersalesService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		aftersalesList = this.aftersalesService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据主键找出记录售后维护信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.aftersales.getCust_id();
		if(id==null || id.equals("")){
			aftersales = new Aftersales();
		}else{
			aftersales = this.aftersalesService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @Method Description :根据当前主键找出记录售后维护信息信息
	 * @author : HZX
	 * @date : Feb 27, 2014 2:41:04 PM
	 */
	public String nowview() throws Exception {
		String id = this.session_cust_id;
		if(id==null || id.equals("")){
			aftersales = new Aftersales();
		}else{
			aftersales = this.aftersalesService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @Method Description :会员后台判断是否开通店铺，是则进行修改售后维护
	 * @author : HZX
	 * @date : Feb 27, 2014 3:17:49 PM
	 */
	public String isopenshop() throws Exception{
		String cust_id=this.session_cust_id;
		Map map=new HashMap();
		map.put("cust_id", cust_id);
		shopconfigList=shopconfigService.getList(map);
		if(shopconfigList==null || shopconfigList.size()==0){
			getResponse().sendRedirect("/bmall_Shopconfig_activation.action");
			return "";
		}else{
			return nowview();
		}
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(aftersales == null){
			aftersales = new Aftersales();
		}
		String id = this.aftersales.getCust_id();
		if(!this.validateFactory.isDigital(id)){
			aftersales = this.aftersalesService.get(id);
		}
	}

	public Aftersales getAftersales() {
		return aftersales;
	}

	public void setAftersales(Aftersales aftersales) {
		this.aftersales = aftersales;
	}

}

