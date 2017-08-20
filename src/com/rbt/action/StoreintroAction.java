/*
 * Package:com.rbt.action
 * FileName: StoreintroAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Storeintro;
import com.rbt.service.IStoreintroService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 门店服务介绍action类
 * @author 创建人 HXK
 * @date 创建日期 Wed Sep 23 13:59:28 CST 2015
 */
@Controller
public class StoreintroAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 门店服务介绍对象
	 */
	private Storeintro storeintro;
	/*******************业务层接口****************/
	/*
	 * 门店服务介绍业务层接口
	 */
	@Autowired
	private IStoreintroService storeintroService;
	
	/*********************集合*******************/
	/*
	 * 门店服务介绍信息集合
	 */
	public List storeintroList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;
	
		
	/**
	 * 方法描述：返回新增门店服务介绍页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增门店服务介绍
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(ValidateUtil.isRequired(storeintro.getSto_name())){
			this.addFieldError("storeintro.sto_name", "门店服务介绍名称不能为空！");
		}
		if(ValidateUtil.isRequired(storeintro.getImg_path())){
			this.addFieldError("storeintro.img_path", "门店服务介绍图片不能为空！");
		}
		super.commonValidateField(storeintro);
		if(super.ifvalidatepass){
			return add();
		}
		this.storeintroService.insert(storeintro);
		this.addActionMessage("新增门店服务介绍成功！");
		this.storeintro = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改门店服务介绍信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(ValidateUtil.isRequired(storeintro.getSto_name())){
			this.addFieldError("storeintro.sto_name", "门店服务介绍名称不能为空！");
		}
		if(ValidateUtil.isRequired(storeintro.getImg_path())){
			this.addFieldError("storeintro.img_path", "门店服务介绍图片不能为空！");
		}
		super.commonValidateField(storeintro);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.storeintroService.update(storeintro);
		this.addActionMessage("修改门店服务介绍成功！");
		return list();
	}
	/**
	 * 方法描述：删除门店服务介绍信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除门店服务介绍信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.storeintroService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除门店服务介绍信息成功!");
		}else{
			this.addActionMessage("删除门店服务介绍信息失败!");
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
		
		if(title_s!=null&&!title_s.equals("")){
			pageMap.put("sto_name", title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.storeintroService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		storeintroList = this.storeintroService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 *
	 * @date : May 3, 2014 4:48:33 PM
	 * @Method Description :显示
	 */
	public String updateState() throws Exception {
		updatestate();
		return list();
	}

	/**
	 *
	 * @date : May 3, 2014 4:48:43 PM
	 * @Method Description :不显示
	 */
	public String updateNoState() throws Exception {
		updatestate();
		return list();
	}
	
	/**
	 * @author : 
	 * @date : May 3, 2014 4:48:54 PM
	 * @Method Description :启用/禁用公共方法
	 */
	public void updatestate(){
		boolean flag = this.storeintroService.updateBatchState(chb_id, state_val, "sto_id", "is_show");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("不显示成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	
	}
	
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.storeintroService.updateSort("sto_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("门店服务介绍排序成功");
		}else{
			this.addActionMessage("门店服务介绍排序失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据主键找出门店服务介绍信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.storeintro.getSto_id();
		if(id==null || id.equals("")){
			storeintro = new Storeintro();
		}else{
			storeintro = this.storeintroService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the StoreintroList
	 */
	public List getStoreintroList() {
		return storeintroList;
	}
	/**
	 * @param storeintroList
	 *  the StoreintroList to set
	 */
	public void setStoreintroList(List storeintroList) {
		this.storeintroList = storeintroList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(storeintro == null){
			storeintro = new Storeintro();
		}
		String id = this.storeintro.getSto_id();
		if(!this.validateFactory.isDigital(id)){
			storeintro = this.storeintroService.get(id);
		}
	}
	/**
	 * @return the storeintro
	 */
	public Storeintro getStoreintro() {
		return storeintro;
	}
	/**
	 * @param Storeintro
	 *            the storeintro to set
	 */
	public void setStoreintro(Storeintro storeintro) {
		this.storeintro = storeintro;
	}
}

