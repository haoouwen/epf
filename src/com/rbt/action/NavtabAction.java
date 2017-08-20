/*
 * Package:com.rbt.action
 * FileName: NavtabAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Navtab;
import com.rbt.service.INavigationService;
import com.rbt.service.INavtabService;

/**
 * @function 功能 导航标签信息action类
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 12 20:56:05 CST 2015
 */
@Controller
public class NavtabAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 导航标签信息对象
	 */
	private Navtab navtab;
	/*******************业务层接口****************/
	/*
	 * 导航标签信息业务层接口
	 */
	@Autowired
	private INavtabService navtabService;
	@Autowired
	private INavigationService navigationService;
	
	/*********************集合*******************/
	/*
	 * 导航标签信息信息集合
	 */
	public List navtabList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;//搜索导航标签名字段
	public String touch_s;//0手机端 1 PC端
	
		
	/**
	 * 方法描述：返回新增导航标签信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增导航标签信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(comm()){
			return add();
		}
		// 生成十位随机数的字符串
		String charNum = RandomStrUtil.getNumberRand();
		navtab.setTab_number(charNum);
		//显示端 1：PC端 0触屏端口
		if("0".equals(navtab.getTouch())){
			navtab.setTab_url("/webapp/marksgoodslist/"+charNum+".html");
		}else{
			navtab.setTab_url("/mallmarkslist_"+charNum+".html");
		}
		this.navtabService.insert(navtab);
		this.addActionMessage("新增商城标签信息成功！");
		this.navtab = null;
		return INPUT;
	}

	/**
	 * 方法描述:公共数据的验证
	 */
	public boolean comm(){
		//验证导航标签名是否为空
		if(ValidateUtil.isRequired(navtab.getTab_name())){
			this.addFieldError("navtab.tab_name", "标签名不能为空！");
		}
		
		//验证导航备注是否为空
		if(ValidateUtil.isRequired(navtab.getTab_remark())){
			this.addFieldError("navtab.tab_remark", "标签备注不能为空!");
		}
		super.commonValidateField(navtab);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 方法描述：修改导航标签信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			
		if(comm()){
			return view();
		}
		this.navtabService.update(navtab);
		this.addActionMessage("修改商城标签信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除导航标签信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除导航标签信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.navtabService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商城标签信息成功!");
		}else{
			this.addActionMessage("删除商城标签信息失败!");
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
	    //搜索导航标签名 
	     if(!ValidateUtil.isRequired(title_s)){
	    	 pageMap.put("tab_name", title_s);
	     }
		//搜索导航标签名 
		if(!ValidateUtil.isRequired(touch_s)){
	    	 pageMap.put("touch", touch_s);
	     }
		//根据页面提交的条件找出信息总数
		int count=this.navtabService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		navtabList = this.navtabService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出导航标签信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.navtab.getTab_id();
		if(id==null || id.equals("")){
			navtab = new Navtab();
		}else{
			navtab = this.navtabService.get(id);
		}
		return goUrl(VIEW);
	}
	
	
	/**
	 * @Method Description :批量导航标签排序
	 */

	public String updateSort() throws Exception {
		boolean flag = this.navtabService.updateSort("tab_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("商城标签排序成功");
		}else{
			this.addActionMessage("商城标签排序失败");
		}
		return list();
	}
	
	/**
	 * @Method Description :启用为【1】
	 */
	public String updateOnState() throws Exception {
		updatestart();
		return list();
	}

	/**
	 * @Method Description :禁用为【0】
	 */
	public String updateDownState() throws Exception {
		updatestart();
		return list();
	}
	
	/**
	 * @Method Description :启用\禁用公共方法
	 */
	public void updatestart(){
		boolean flag = this.navtabService.updateBatchState(chb_id, state_val, "tab_id", "start");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("禁用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("启用成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	}
	
	
	/**
	 * @return the NavtabList
	 */
	public List getNavtabList() {
		return navtabList;
	}
	/**
	 * @param navtabList
	 *  the NavtabList to set
	 */
	public void setNavtabList(List navtabList) {
		this.navtabList = navtabList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(navtab == null){
			navtab = new Navtab();
		}
		String id = this.navtab.getTab_id();
		if(!this.validateFactory.isDigital(id)){
			navtab = this.navtabService.get(id);
		}
	}
	/**
	 * @return the navtab
	 */
	public Navtab getNavtab() {
		return navtab;
	}
	/**
	 * @param Navtab
	 *            the navtab to set
	 */
	public void setNavtab(Navtab navtab) {
		this.navtab = navtab;
	}
}

