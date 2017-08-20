/*
 
 * Package:com.rbt.action
 * FileName: SmodetempleteAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.model.Smodetemplete;
import com.rbt.service.ISmodetempleteService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录区域设置信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Jan 10 10:52:23 CST 2014
 */
@Controller
public class SmodetempleteAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Smodetemplete smodetemplete;
	
	/*******************业务层接口****************/
	@Autowired
	private ISmodetempleteService smodetempleteService;
	
	/*********************集合********************/
	
	public List smodetempleteList;//区域设置
	/*********************字段********************/
	private String smode_templete_s;//区域设置模板
	private String smode_name_s;//
	public String smode_id;
	

	
	/**
	 * 方法描述：返回新增记录区域设置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录区域设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		smodetemplete.setUser_id(this.session_user_id);
		super.commonValidateField(smodetemplete);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.smodetempleteService.insert(smodetemplete);
		this.addActionMessage("新增记录区域设置信息成功！");
		this.smodetemplete = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录区域设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(smodetemplete);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.smodetempleteService.update(smodetemplete);
		this.addActionMessage("修改记录区域设置信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录区域设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.smodetempleteService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录区域设置信息成功");
		}else{
			this.addActionMessage("删除记录区域设置信息失败");
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
		if (smode_templete_s != null && !smode_templete_s.equals("")) {
			pageMap.put("smode_templete", smode_templete_s);
		}
		if (smode_name_s != null && !smode_name_s.equals("")) {
			pageMap.put("smode_name", smode_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.smodetempleteService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		smodetempleteList = this.smodetempleteService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录区域设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.smodetemplete.getTrade_id();
		if(id==null || id.equals("")){
			smodetemplete = new Smodetemplete();
		}else{
			smodetemplete = this.smodetempleteService.get(id);
		}
		return goUrl(VIEW);
	}
	
/**
 * @author HZX
 * @Method Description : 根据配送方式的ID的是否已设置模板记录
 * @date : Jan 10, 2014 1:25:19 PM
 */

	public String goTemplete() throws Exception {
		String smode_id=this.smode_id;
		//根据配送方式的ID的是否已设置模板记录，如果是则进入更新页面，否则进入新增页面
		HashMap map =  new HashMap();
		map.put("smode_id", smode_id);
		List list = this.smodetempleteService.getList(map);
		if(list!=null && list.size()>0){
			smodetemplete=this.smodetempleteService.getSmodetempleteBySmodeId(smode_id);
		return goUrl(VIEW);
		}else{
			return goUrl(ADD);
		}
		
	}
	/**
	 * @return the SmodetempleteList
	 */
	public List getSmodetempleteList() {
		return smodetempleteList;
	}
	/**
	 * @param smodetempleteList
	 *  the SmodetempleteList to set
	 */
	public void setSmodetempleteList(List smodetempleteList) {
		this.smodetempleteList = smodetempleteList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(smodetemplete == null){
			smodetemplete = new Smodetemplete();
		}
		String id = this.smodetemplete.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			smodetemplete = this.smodetempleteService.get(id);
		}
	}
	public String getSmode_templete_s() {
		return smode_templete_s;
	}
	public void setSmode_templete_s(String smode_templete_s) {
		this.smode_templete_s = smode_templete_s;
	}
	public String getSmode_name_s() {
		return smode_name_s;
	}
	public void setSmode_name_s(String smode_name_s) {
		this.smode_name_s = smode_name_s;
	}
	/**
	 * @return the smodetemplete
	 */
	public Smodetemplete getSmodetemplete() {
		return smodetemplete;
	}
	/**
	 * @param Smodetemplete
	 *            the smodetemplete to set
	 */
	public void setSmodetemplete(Smodetemplete smodetemplete) {
		this.smodetemplete = smodetemplete;
	}
	
}

