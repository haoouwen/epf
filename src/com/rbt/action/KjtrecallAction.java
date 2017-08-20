/*
 * Package:com.rbt.action
 * FileName: KjtrecallAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Kjtrecall;
import com.rbt.service.IKjtrecallService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 跨境通回调表action类
 * @author 创建人 CYC
 * @date 创建日期 Fri Sep 18 16:21:49 CST 2015
 */
@Controller
public class KjtrecallAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 跨境通回调表对象
	 */
	private Kjtrecall kjtrecall;
	/*******************业务层接口****************/
	/*
	 * 跨境通回调表业务层接口
	 */
	@Autowired
	private IKjtrecallService kjtrecallService;
	
	/*********************集合*******************/
	/*
	 * 跨境通回调表信息集合
	 */
	public List kjtrecallList;
	public List commparaList;
	public List kjtcommparaList;
	public List exkjtrecallList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String sosysno;//跨境通订单
	public String sostatus;//跨境通订单状态
	public String excid;//导出id
	
		
	/**
	 * 方法描述：返回新增跨境通回调表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	

	/**
	 * 方法描述：新增跨境通回调表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(kjtrecall);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.kjtrecallService.insert(kjtrecall);
		this.addActionMessage("新增跨境通回调表成功！");
		this.kjtrecall = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改跨境通回调表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(kjtrecall);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.kjtrecallService.update(kjtrecall);
		this.addActionMessage("修改跨境通回调表成功！");
		return list();
	}
	/**
	 * 方法描述：删除跨境通回调表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除跨境通回调表信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.kjtrecallService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除跨境通回调表信息成功!");
		}else{
			this.addActionMessage("删除跨境通回调表信息失败!");
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
		exkjtrecallList = this.kjtrecallService.getList(pageMap);
		exkjtrecallList = ToolsFuc.replaceList(exkjtrecallList, "");
		if(this.kjtrecallService.exprotExcel(exkjtrecallList, getResponse())) {
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
		//获取跨境通订单状态
		//获取跨境通订单状态
		kjtOrderCommparaState("kjtorder_state");
		
		Map pageMap = new HashMap();
		if(sosysno!=null&&!"".equals(sosysno)){
			pageMap.put("sosysno", sosysno);
		}
		
		if(sostatus!=null&&!"".equals(sostatus)){
			pageMap.put("sostatus", sostatus);
		}
		
		
		
		//根据页面提交的条件找出信息总数
		int count=this.kjtrecallService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		kjtrecallList = this.kjtrecallService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出跨境通回调表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.kjtrecall.getSosysno();
		if(id==null || id.equals("")){
			kjtrecall = new Kjtrecall();
		}else{
			kjtrecall = this.kjtrecallService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the KjtrecallList
	 */
	public List getKjtrecallList() {
		return kjtrecallList;
	}
	/**
	 * @param kjtrecallList
	 *  the KjtrecallList to set
	 */
	public void setKjtrecallList(List kjtrecallList) {
		this.kjtrecallList = kjtrecallList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(kjtrecall == null){
			kjtrecall = new Kjtrecall();
		}
		String id = this.kjtrecall.getSosysno();
		if(!this.validateFactory.isDigital(id)){
			kjtrecall = this.kjtrecallService.get(id);
		}
	}
	/**
	 * @return the kjtrecall
	 */
	public Kjtrecall getKjtrecall() {
		return kjtrecall;
	}
	/**
	 * @param Kjtrecall
	 *            the kjtrecall to set
	 */
	public void setKjtrecall(Kjtrecall kjtrecall) {
		this.kjtrecall = kjtrecall;
	}
	
	/**
	 * 方法描述：获取跨境通订单状态参数
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
		Map kjtorderMap = new HashMap();
	public void kjtOrderCommparaState(String com_value) {
		kjtorderMap.put("enabled", "0");
		kjtorderMap.put("para_code", com_value);
		kjtorderMap.put("start", "0");
		kjtorderMap.put("limit", "15");
		kjtcommparaList = commparaService.getList(kjtorderMap);
	}
}

