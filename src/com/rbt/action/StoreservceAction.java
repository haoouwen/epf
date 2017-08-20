/*
 * Package:com.rbt.action
 * FileName: StoreservceAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Storeservce;
import com.rbt.service.IStoreservceService;

/**
 * @function 功能 门店服务action类
 * @author 创建人 ZMS
 * @date 创建日期 Sat Aug 29 16:01:36 CST 2015
 */
@Controller
public class StoreservceAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 门店服务对象
	 */
	private Storeservce storeservce;
	/*******************业务层接口****************/
	/*
	 * 门店服务业务层接口
	 */
	@Autowired
	private IStoreservceService storeservceService;
	
	/*********************集合*******************/
	/*
	 * 门店服务信息集合
	 */
	public List storeservceList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;
		
	/**
	 * 方法描述：返回新增门店服务页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增门店服务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(storeservce);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.storeservceService.insert(storeservce);
		this.addActionMessage("新增门店服务成功！");
		this.storeservce = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改门店服务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(storeservce);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.storeservceService.update(storeservce);
		this.addActionMessage("修改门店服务成功！");
		return list();
	}
	/**
	 * 方法描述：删除门店服务信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除门店服务信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.storeservceService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除门店服务信息成功!");
		}else{
			this.addActionMessage("删除门店服务信息失败!");
		}
	}
	/**
	 *
	 * @date : May 3, 2014 4:48:33 PM
	 * @Method Description :启用
	 */
	public String updateState() throws Exception {
		updatestate();
		return list();
	}

	/**
	 *
	 * @date : May 3, 2014 4:48:43 PM
	 * @Method Description :禁用
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
		boolean flag = this.storeservceService.updateBatchState(chb_id, state_val, "store_id", "state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("启用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("禁用成功");
			}
		}else{
			this.addActionMessage("操作失败");
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
		if(title_s!=null && !"title_s".equals("")){
			pageMap.put("store_name", title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.storeservceService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		storeservceList = this.storeservceService.getList(pageMap);
		storeservceList= ToolsFuc.replaceList(storeservceList, "");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出门店服务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.storeservce.getStore_id();
		if(id==null || id.equals("")){
			storeservce = new Storeservce();
		}else{
			storeservce = this.storeservceService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the StoreservceList
	 */
	public List getStoreservceList() {
		return storeservceList;
	}
	/**
	 * @param storeservceList
	 *  the StoreservceList to set
	 */
	public void setStoreservceList(List storeservceList) {
		this.storeservceList = storeservceList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(storeservce == null){
			storeservce = new Storeservce();
		}
		String id = this.storeservce.getStore_id();
		if(!this.validateFactory.isDigital(id)){
			storeservce = this.storeservceService.get(id);
		}
	}
	/**
	 * @return the storeservce
	 */
	public Storeservce getStoreservce() {
		return storeservce;
	}
	/**
	 * @param Storeservce
	 *            the storeservce to set
	 */
	public void setStoreservce(Storeservce storeservce) {
		this.storeservce = storeservce;
	}
}

