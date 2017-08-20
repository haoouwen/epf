/*
 
 * Package:com.rbt.action
 * FileName: AreasetAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.function.AreaFuc;
import com.rbt.model.Areaset;
import com.rbt.service.IAreasetService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录区域设置信息action类
 * @author 创建人 HXK
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2014
 */
@Controller
public class AreasetAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Areaset areaset;
	
	/*******************业务层接口****************/
	@Autowired
	private IAreasetService areasetService;
	
	/*********************集合******************/
	public List areasetList;//记录区域设置信息
	public List area_attr_list;//地区

	/*********************字段******************/
	public String smode_id;//记录区域设置信息ID
	public String area_attr_str;//地区串
	public String area_attr_str_first;//地区第一级字符串
	public String area_attr_str_first_name;//地区第一级名称
	public String areaset_name_s;//区域名称

	/**
	 * 方法描述：返回新增记录区域设置信息页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (area_attr_str != null && !"".equals(area_attr_str)) {
			this.areasetService.area_attr_list(area_attr_str,area_attr_list);
		}
		if (area_attr_str_first != null && !"".equals(area_attr_str_first)) {
			area_attr_str_first_name = AreaFuc.getAreaNameByMap(area_attr_str_first);
		}
		return goUrl(ADD);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 9:57:20 PM
	 * @Method Description :验证数据
	 */
	private boolean commonCheck(){
		if (area_attr_str != null && !"".equals(area_attr_str)) {
			area_attr_str = area_attr_str.replace(" ", "");
			areaset.setEnd_area(area_attr_str);
		} else {
			this.addFieldError("end_arear", "请选择到达地区");
		}
		if (area_attr_str_first != null && !"".equals(area_attr_str_first)) {
			area_attr_str_first = area_attr_str_first.replace(" ", "");
		} else {
			this.addFieldError("start_area", "请选择开始地区");
		}
		areaset.setSmode_id(smode_id);
		super.commonValidateField(areaset);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
		
	}

	/**
	 * 方法描述：新增记录区域设置信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if (commonCheck()) {
			return add();
		}
		this.areasetService.insert(areaset);
		this.addActionMessage("新增记录区域设置信息成功！");
		this.areaset = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录区域设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		if(commonCheck())return view();
		this.areasetService.update(areaset);
		this.addActionMessage("修改记录区域设置信息成功！");
		return list();
	}

	/**
	 * 方法描述：删除记录区域设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.areasetService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录区域设置信息成功");
		}else{
			this.addActionMessage("删除记录区域设置信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("smode_id", smode_id);
		if (areaset_name_s != null && !"".equals(areaset_name_s)) {
			pageMap.put("areaset_name", areaset_name_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.areasetService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		areasetList = this.areasetService.getList(pageMap);
        //List判断处理
		areasetList=this.areasetService.replaceList(areasetList);
		return goUrl(INDEXLIST);
	}



	/**
	 * 方法描述：根据主键找出记录区域设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.areaset.getAreaset_id();
		if (id == null || id.equals("")) {
			areaset = new Areaset();
		} else {
			areaset = this.areasetService.get(id);
		}
		area_attr_str = areaset.getEnd_area();
		this.areasetService.area_attr_list(area_attr_str,area_attr_list);
		area_attr_str_first_name = AreaFuc.getAreaNameByMap(area_attr_str_first);
		return goUrl(VIEW);
	}


	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (areaset == null) {
			areaset = new Areaset();
		}
		String id = this.areaset.getAreaset_id();
		if (!this.validateFactory.isDigital(id)) {
			areaset = this.areasetService.get(id);
		}
	}

	public Areaset getAreaset() {
		return areaset;
	}

	public void setAreaset(Areaset areaset) {
		this.areaset = areaset;
	}

}
