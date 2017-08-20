/*
 
 * Package:com.rbt.action
 * FileName: RolerightAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.model.Roleright;
import com.rbt.service.IRolerightService;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能 权限管理Action层实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 3:40:23 PM
 */
@Controller
public class RolerightAction extends AdminBaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7921122555497270488L;
	private static final String SYSCODE_SYS_VALUE = "sys";
	/*******************实体层********************/
	public Roleright roleright;
	
	/*******************业务层接口****************/
	@Autowired
	public IRolerightService rolerightService;
	@Autowired
	public ISysmenuService sysmenuService;
	
	/*********************集合********************/
	public List rolerightList;//角色权限信息'
	public List menuList;//菜单
	public List membermenuList;
	public List adminmenuList;
	public List menutowList;
	public List menuthreeList;
	public List menuoneList;
	
	/*********************字段********************/
	
	public String right_name_s;//用户名
	public String menu_attr_s;//匿名
	public String syscode_s;//后台类型
	public String menu_name_s;//菜单名称


	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (right_name_s != null && !right_name_s.equals("")) {
			pageMap.put("right_name", right_name_s);
		}
		if (menu_attr_s != null && !menu_attr_s.equals("")) {
			pageMap.put("menu_attr", menu_attr_s);
		}
		if (syscode_s != null && !syscode_s.equals("")) {
			pageMap.put("syscode", syscode_s);
		}
		if (menu_name_s != null && !menu_name_s.equals("")) {
			pageMap.put("menu_name", menu_name_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.rolerightService.getCount(pageMap);
		//分页插件
		//pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		rolerightList = this.rolerightService.getList(pageMap);
		initMsg();
		return goUrl(INDEXLIST);
	}
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 9:47:19 AM
	 * @Method Description :初始化数据
	 */
	private void initMsg(){
		// 页面搜索提交的参数
		// 获取运营商一级菜单
		menuoneList = this.sysmenuService.getList(getListMap("1"));
		// 获取运营商二级菜单
		menutowList = this.sysmenuService.getList(getListMap("2"));
		// 获取运营商三级菜单
		menuthreeList = this.sysmenuService.getList(getListMap("3"));
		// 获取菜单权限
		//Map rolerightMap = new HashMap();
		//rolerightMap.put("syscode", SYSCODE_SYS_VALUE);
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:51:43 AM
	 * @Method Description :获取不同级别菜单Map
	 */
	public Map getListMap(String level){
		Map Map = new HashMap();
		Map.put("ro_menu_level", level);
		Map.put("enabled", "0");
		return Map;
	}
	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map adminMap = new HashMap();
		adminMap.put("syscode", "sys");
		adminmenuList = this.sysmenuService.getList(adminMap);
		Map memberMap = new HashMap();
		memberMap.put("syscode", "b2c");
		membermenuList = this.sysmenuService.getList(memberMap);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String right_id = RandomStrUtil.getNumberRand();
		roleright.setRight_id(right_id);
		//字段验证
		super.commonValidateField(roleright);
		if(super.ifvalidatepass){
			return add();
		}	
		this.rolerightService.insert(roleright);
		this.addActionMessage("新增操作权限信息成功");
		return add();
	}

	/**
	 * 方法描述：根据主键找出角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map adminMap = new HashMap();
		adminMap.put("syscode", "sys");
		adminmenuList = this.sysmenuService.getList(adminMap);
		Map memberMap = new HashMap();
		memberMap.put("syscode", "b2c");
		membermenuList = this.sysmenuService.getList(memberMap);
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(roleright);
		if(super.ifvalidatepass){
			return view();
		}		
		this.rolerightService.update(roleright);
		this.addActionMessage("修改操作权限信息成功");
		return list();
	}

	/**
	 * 方法描述：删除角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.rolerightService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除操作权限信息成功");
		}else{
			this.addActionMessage("删除操作权限信息失败");
		}
		return list();
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(roleright == null){
			roleright = new Roleright();
		}
		String id = roleright.getRight_id();
		if(!this.validateFactory.isDigital(id)){
			roleright = this.rolerightService.get(id);
		}
	}
	
	public void setSyscode_s(String syscode_s) {
		this.syscode_s = syscode_s;
	}

	public Roleright getRoleright() {
		return roleright;
	}

	public void setRoleright(Roleright roleright) {
		this.roleright = roleright;
	}

}
