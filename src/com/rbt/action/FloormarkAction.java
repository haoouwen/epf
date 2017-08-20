/*
 
 * Package:com.rbt.action
 * FileName: FloormarkAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Floormark;
import com.rbt.service.IFloormarkService;
import com.thoughtworks.xstream.mapper.Mapper.Null;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 楼层标签action类
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:35:30 CST 2015
 */
@Controller
public class FloormarkAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 楼层标签对象
	 */
	private Floormark floormark;
	/*******************业务层接口****************/
	/*
	 * 楼层标签业务层接口
	 */
	@Autowired
	private IFloormarkService floormarkService;
	
	/*********************集合*******************/
	/*
	 * 楼层标签信息集合
	 */
	public List floormarkList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String fm_name;//搜索楼层标签名称字段
	/**
	 * 方法描述：返回新增楼层标签页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增楼层标签
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 验证楼层标签名称不能为空
		if (ValidateUtil.isRequired(floormark.getFm_name())) {
			this.addFieldError("floormark.fm_name", "楼层标签名字不能为空");
		}
		super.commonValidateField(floormark);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.floormarkService.insert(floormark);
		
		this.addActionMessage("新增楼层标签成功！");
		this.floormark = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改楼层标签信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 验证楼层标签名称不能为空
		if (ValidateUtil.isRequired(floormark.getFm_name())) {
			this.addFieldError("floormark.fm_name", "楼层标签名字不能为空");
		}
			super.commonValidateField(floormark);
		if(super.ifvalidatepass){
			return view();
		}
		this.floormarkService.update(floormark);
		this.addActionMessage("修改楼层标签成功！");
		return list();
	}
	/**
	 * 方法描述：删除楼层标签信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除楼层标签信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.floormarkService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除楼层标签信息成功!");
		}else{
			this.addActionMessage("删除楼层标签信息失败!");
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
		//验证楼层标签名称的字段
		if(fm_name!=null &&!fm_name.equals("")){
			pageMap.put("fm_name", fm_name);
		}
		//根据页面提交的条件找出信息总数
		int count=this.floormarkService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		floormarkList = this.floormarkService.getList(pageMap);
		
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出楼层标签信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.floormark.getFm_id();
		if(id==null || id.equals("")){
			floormark = new Floormark();
		}else{
			floormark = this.floormarkService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @author ZMS
	 * @date : Jan 16, 2014 1:08:24 PM
	 * @Method Description :批量排序
	 */

	public String updateSort() throws Exception {
		boolean flag = this.floormarkService.updateSort("fm_id", "fm_sort",chb_id, sort_val);
		if(flag){
			this.addActionMessage("楼层标签排序成功");
		}else{
			this.addActionMessage("楼层标签排序失败");
		}
		return list();
	}
	
	/**
	 * @return the FloormarkList
	 */
	public List getFloormarkList() {
		return floormarkList;
	}
	/**
	 * @param floormarkList
	 *  the FloormarkList to set
	 */
	public void setFloormarkList(List floormarkList) {
		this.floormarkList = floormarkList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(floormark == null){
			floormark = new Floormark();
		}
		String id = this.floormark.getFm_id();
		if(!this.validateFactory.isDigital(id)){
			floormark = this.floormarkService.get(id);
		}
	}
	/**
	 * @return the floormark
	 */
	public Floormark getFloormark() {
		return floormark;
	}
	/**
	 * @param Floormark
	 *            the floormark to set
	 */
	public void setFloormark(Floormark floormark) {
		this.floormark = floormark;
	}
}

