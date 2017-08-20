/*
 * Package:com.rbt.action
 * FileName: FepbillAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Fepbill;
import com.rbt.service.IFepbillService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 待购汇账单action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Sep 23 13:22:25 CST 2015
 */
@Controller
public class FepbillAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 待购汇账单对象
	 */
	private Fepbill fepbill;
	/*******************业务层接口****************/
	/*
	 * 待购汇账单业务层接口
	 */
	@Autowired
	private IFepbillService fepbillService;
	
	/*********************集合*******************/
	/*
	 * 待购汇账单信息集合
	 */
	public List fepbillList;
	public List exfepbillList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String fepbill_id_s;
	public String excid;
		
	/**
	 * 方法描述：返回新增待购汇账单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增待购汇账单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(fepbill);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.fepbillService.insert(fepbill);
		this.addActionMessage("新增待购汇账单成功！");
		this.fepbill = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改待购汇账单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(fepbill);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.fepbillService.update(fepbill);
		this.addActionMessage("修改待购汇账单成功！");
		return list();
	}
	/**
	 * 方法描述：删除待购汇账单信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除待购汇账单信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.fepbillService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除待购汇账单信息成功!");
		}else{
			this.addActionMessage("删除待购汇账单信息失败!");
		}
	}
	
	/**
	 * @Method Description : 导出统计信息
	 * @param 
	 * @return return_type
	 */
	public String exportInfo() throws Exception{
		Map pageMap = new HashMap();
		if(excid!=null&&!"".equals(excid)){
			pageMap.put("excid", excid);
		}
		exfepbillList = this.fepbillService.getList(pageMap);
		exfepbillList = ToolsFuc.replaceList(exfepbillList, "");
		if(this.fepbillService.exprotExcel(exfepbillList, getResponse())) {
			   this.addActionMessage("数据导出成功！");	
			}else {
			   this.addActionMessage("数据导出成功！");
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
		if(!validateFactory.isRequired(fepbill_id_s)){
			pageMap.put("fepbill_id", fepbill_id_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.fepbillService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		fepbillList = this.fepbillService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出待购汇账单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.fepbill.getFepbill_id();
		if(id==null || id.equals("")){
			fepbill = new Fepbill();
		}else{
			fepbill = this.fepbillService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the FepbillList
	 */
	public List getFepbillList() {
		return fepbillList;
	}
	/**
	 * @param fepbillList
	 *  the FepbillList to set
	 */
	public void setFepbillList(List fepbillList) {
		this.fepbillList = fepbillList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(fepbill == null){
			fepbill = new Fepbill();
		}
		String id = this.fepbill.getFepbill_id();
		if(!this.validateFactory.isDigital(id)){
			fepbill = this.fepbillService.get(id);
		}
	}
	/**
	 * @return the fepbill
	 */
	public Fepbill getFepbill() {
		return fepbill;
	}
	/**
	 * @param Fepbill
	 *            the fepbill to set
	 */
	public void setFepbill(Fepbill fepbill) {
		this.fepbill = fepbill;
	}
}

