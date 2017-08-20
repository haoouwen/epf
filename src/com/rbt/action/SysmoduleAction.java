/*
 
 * Package:com.rbt.action
 * FileName: SysmoduleAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Sysmodule;
import com.rbt.service.INavService;
import com.rbt.service.ISysmenuService;
import com.rbt.service.ISysmoduleService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 系统模块表action类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2014
 */
@Controller
public class SysmoduleAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Sysmodule sysmodule;
	/*******************业务层接口****************/
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	private INavService navService;
	@Autowired
	private ISysmenuService sysmenuService;
	/*********************集合********************/
	public List sysmoduleList;//系统模块表
	
	/*********************字段********************/
	public String checkonly;
	public String old_link_menu;
	public String admin_sysmodule_id;
	public String mod_sort_no;

	/**
	 * 方法描述：返回新增系统模块表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增系统模块表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.sysmoduleService.insert(sysmodule);
		this.addActionMessage("新增系统模块表成功");
		this.sysmodule = null;
		return INPUT;
	}
	
	/**
	 * 方法描述：批量排序方法
	 * 
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String updateSort() throws Exception{
		boolean flag = this.sysmoduleService.updateSort("module_type", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("模块排序成功");
		}else{
			this.addActionMessage("模块排序失败");
		}
		return list();
	}
	
	/**
	 * 方法描述：修改系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		Map map=new HashMap();		
		String module="";
		// 验证模块类型是否存在
	    if(!this.validateFactory.isRequired(sysmodule.getModule_type())){
	    	module=sysmodule.getModule_type();
	    	map.put("module_type",module);	    	
	    }
	    List list =this.sysmoduleService.getList(map);
	    if(list!=null && list.size()>0){	    	
	    	Map modMap=new HashMap();
	    	modMap=(HashMap)list.get(0);
	    	// 验证是不是本条的记录
	    	if(modMap!=null&&modMap.get("module_type")!=null){
	    		if(!checkonly.equals(modMap.get("module_type").toString())){
	    			this.addFieldError("sysmodule.module_type", "模块类型已存在");
	    		}
	    	}
	    } 
	    // 字段验证
		super.commonValidateField(sysmodule);
		if(super.ifvalidatepass){
			return view();
		}
	    // 当模块功能关闭时，导航对应的模块也要关闭
	    Map navMap=new HashMap();
	    navMap.put("nav_code", module);
	    List navList=this.navService.getList(navMap);
	    String id="",isshow="",state_code="";
	    if(navList!=null&&navList.size()>0){
	         Map listMap=new HashMap();
	         listMap=(HashMap)navList.get(0);
	         
	         if(listMap.get("nav_id")!=null){
	        	  id=listMap.get("nav_id").toString();
	         }
	         if(listMap.get("isshow")!=null){
	        	  isshow=listMap.get("isshow").toString();
	         }
	 	     if(!this.validateFactory.isRequired(sysmodule.getState_code())){
	 	    	  state_code=sysmodule.getState_code();
		     }
	    }
		// 当导航与模块的显示的值不一致时执行操作,控制显示与隐藏
        if(!isshow.equals(state_code)){
       	 List aList = new ArrayList();
			 Map linkMap = new HashMap();
			 linkMap.put("isshow", state_code);
			 linkMap.put("nav_id", id);
			 aList.add(linkMap);
			 this.navService.updateBatchState(id, state_code, "nav_id", "isshow");
        }
	    // 当模块功能关闭时，对应的菜单功能也要关闭
        if(old_link_menu!=null){
        	String old_menu[]=old_link_menu.split(",");
        	List oldList=new ArrayList();
        	for(int i=0;i<old_menu.length;i++){
                // 当菜单与模块的state_code为禁用时，则把对应的菜单ID中值变为隐藏:0,显示，1,隐藏
        		Map cateMap = new HashMap();
        		cateMap.put("menu_id", old_menu[i]);
               	cateMap.put("enabled", "0");//设置全部为启用
               	oldList.add(cateMap);    		
        	}	
        	//更新菜单
        	this.sysmenuService.updateEnable(oldList);
        }        
        //再实行关闭或启动
    	String link_menu=sysmodule.getLink_menu();
    	String menu[]=link_menu.split(",");    	
    	List menuList=new ArrayList();
    	for(int i=0;i<menu.length;i++){
            // 当菜单与模块的state_code为禁用时，则把对应的菜单ID中值变为隐藏:0,显示，1,隐藏
    		Map cateMap = new HashMap();
    		cateMap.put("menu_id", menu[i]);
           	cateMap.put("enabled", state_code);
           	menuList.add(cateMap);    		
    	}	
    	//更新菜单
    	this.sysmenuService.updateEnable(menuList);
        // 更新系统模块
		this.sysmoduleService.update(sysmodule);
		this.addActionMessage("修改系统模块表成功");
		return list();
	}
	
	/**
	 * 方法描述：删除系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.sysmoduleService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除系统模块成功");
		}else{
			this.addActionMessage("删除系统模块成功失败");
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
		// 根据页面提交的条件找出信息总数
		int count=this.sysmoduleService.getCount(pageMap);		
		// 分页插件
		pageMap = super.pageTool(count,pageMap);		
		sysmoduleList = this.sysmoduleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:17:05 PM
	 * @Method Description :启用
	 */
	public String updateOn() throws Exception {
		updateOnDown();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:17:15 PM
	 * @Method Description :禁用
	 */
	public String updateDown() throws Exception {
		updateOnDown();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:17:30 PM
	 * @Method Description :启用禁用的公共方法
	 */
	public void updateOnDown(){
		boolean flag = this.sysmoduleService.updateBatchState(chb_id, state_val, "module_type", "state_code");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("启用模块成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("禁用模块成功");
			}
		}else{
			this.addActionMessage("操作模块失败");
		}
	}
	
	
	/**
	 * 方法描述：根据主键找出系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sysmodule == null){
			sysmodule = new Sysmodule();
		}
		String id = this.sysmodule.getModule_type();
		if(!this.validateFactory.isRequired(id)){
			sysmodule = this.sysmoduleService.get(id);
		}
	}

	/**
	 * @return the sysmodule
	 */
	public Sysmodule getSysmodule() {
		return sysmodule;
	}
	/**
	 * @param Sysmodule
	 *            the sysmodule to set
	 */
	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}
	
}

