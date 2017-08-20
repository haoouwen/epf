/*
 
 * Package:com.rbt.action
 * FileName: RoleAction.java
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Role;
import com.rbt.model.Sysuser;
import com.rbt.service.IRoleService;
import com.rbt.service.IRolerightService;
import com.rbt.service.ISysmenuService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 角色的管理action类
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 1:47:20 PM
 */
@Controller
public class RoleAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = -5492437578684030364L;
	private static final String SYSCODE_SYS_VALUE = "sys";
	/*******************实体层********************/
	public Role role;
	/*******************业务层接口****************/
	@Autowired
	public ISysmenuService sysmenuService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ISysmenuService menuService;
	@Autowired
	public IRolerightService rolerightService;
	@Autowired
	public ISysuserService sysuserService;
	/*********************集合********************/
	
	public List menuList;//菜单信息
	public List menutowList;
	public List menuthreeList;
	public List menuoneList;
	public List rolerightList;//角色信息
	public List rolelist;
	/*********************字段********************/
	public String rolemenuright;
	public String jsonMenu;
	public String men_index = "";
	public String ro_op_right;//操作权限
	public String ro_menu_right;//菜单权限
	public String role_name_s;//角色名
	public String oldRoleName;//用于修改是判断角色名是否一样
	public String grade;
	public String userStr="";

	/**
	 * @MethodDescribe 方法描述 跳转到新增角色页面
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 28, 2014 1:50:41 PM
	 */
	@SuppressWarnings("unchecked")
	public String add() throws Exception {
		initMsg();
		return goUrl("insert");
	}

	/**
	 * @MethodDescribe 方法描述 添加角色的方法
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 28, 2014 5:27:43 PM
	 */
	public String insert() throws Exception {
		if (ValidateUtil.isRequired(ro_menu_right)) {
			this.addFieldError("ro_menu_right", "菜单权限不能为空！");
		}
		if (!ValidateUtil.isRequired(ro_menu_right)) {
			role.setMenu_right(ro_menu_right.replace(" ",""));
		}
		if (!ValidateUtil.isRequired(role.getOper_right())) {
			role.setOper_right(role.getOper_right().replace(" ", ""));
		}
		if (!ValidateUtil.isRequired(this.ro_op_right)) {
			role.setOper_right(ro_op_right.replace(" ",""));
		}
		if(existsTitle(role.getRole_name(),"","role","role_name")){
			this.addFieldError("role.role_name","角色名称已存在");
			return add();
		}
		role.setCust_id(this.session_cust_id);
		role.setuser_id(this.session_user_id);
		// 字段验证
		super.commonValidateField(role);
		if (super.ifvalidatepass) {
			return add();
		}
		this.roleService.insert(role);
		this.role = null;
		this.addActionMessage("添加角色信息成功");
		return add();
	}

	/**
	 * @MethodDescribe 方法描述 根据条件返回数据库条件的列表
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 29, 2014 3:36:01 PM
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("cust_id", this.session_cust_id);
		if(role_name_s!=null && !"".equals(role_name_s)){
			pageMap.put("role_name_s", role_name_s);
		}
		pageMap.put("user_id",this.session_user_id);
		// 过滤地区
		pageMap = super.areafilter(pageMap);
		Sysuser sysuser = sysuserService.get(session_user_id);
		if("3".equals(sysuser.getUser_type())){
			pageMap.put("supper","0");
		}
		// 根据页面提交的条件找出信息总数
		int count = this.roleService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		rolelist = this.roleService.getList(pageMap);
		if (men_index.equals("")) {
			men_index = "0";
		}
		grade=this.sysuserService.get(this.session_user_id).getGrade();
		// 找出角色对应的管理员数量
		rolelist=this.roleService.getRoleList(rolelist);
		return goUrl(INDEXLIST);
	}

	
	/**
	 * @Method Description :删除角色
	 * @author : HZX
	 * @date : May 29, 2014 8:36:52 PM
	 */
	public String delete() throws Exception {
		String ids[];
		int cnum=0;
		if(chb_id.indexOf(",")>-1){
			ids=chb_id.split(",");
			for(int i=0;i<ids.length;i++){
				cnum=getSysCount(ids[i],cnum);
			}
				
		}else {
			cnum=getSysCount(chb_id,cnum);
		}
		if(cnum==0){
			this.addActionMessage("删除角色信息成功");
		}else{
			this.addActionMessage("部分角色下有管理员删除失败");
		}
		return list();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : May 27, 2014 7:51:26 PM
	 */
	
	public int getSysCount(String id,int cnum){
		int adminnum;
		HashMap aMap = new HashMap();
		aMap.put("role_id", id);
		adminnum = sysuserService.getCount(aMap);
		if(adminnum>0){
			cnum++;
		}else {
			this.roleService.delete(id);
		}
		return cnum;
	}
	/**
	 * @MethodDescribe 方法描述 通过主键ID返回角色的详细信息
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 30, 2014 2:21:57 PM
	 */
	public String view() throws Exception {
		if (role.getCust_id() != null) {
			if (accessControl(role.getCust_id())) {
				return list();
			}
		}
		if (ValidateUtil.isRequired(role.getRole_id())) {
			return list();
		}
		role = this.roleService.get(role.getRole_id());
		initMsg();
		return goUrl(VIEW);
	}

	/**
	 * @author : LJQ
	 * @date : May 3, 2014 9:47:19 AM
	 * @Method Description :初始化数据
	 */
	private void initMsg(){
		// 页面搜索提交的参数
		if (getSession().getAttribute("menu_right") != null) {
			String menu_right="";
			if("105".equals(getSession().getAttribute("user_id").toString())){
				menu_right="all";
			}else{
				menu_right = getSession().getAttribute("menu_right").toString();
			}
			menuoneList=this.roleService.queryCommon(SYSCODE_SYS_VALUE, "1", menu_right);
			menutowList=this.roleService.queryCommon(SYSCODE_SYS_VALUE, "2", menu_right);
			menuthreeList=this.roleService.queryCommon(SYSCODE_SYS_VALUE, "3", menu_right);
		}
		// 获取菜单权限
		if(getSession().getAttribute("oper_right") != null){
			String oper_right="";
			if("105".equals(getSession().getAttribute("user_id").toString())){
				oper_right="all";
			}else{
				oper_right=getSession().getAttribute("oper_right").toString();
			}
			rolerightList = this.rolerightService.getOperRightList(SYSCODE_SYS_VALUE, oper_right);
		}
	}
	
	/**
	 * @MethodDescribe 方法描述 根据ID对角色进行修改操作
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 30, 2014 3:22:58 PM
	 */
	public String update() throws Exception {
		if (ValidateUtil.isRequired(role.getRole_id())) {
			return view();
		}
		if (ValidateUtil.isRequired(role.getMenu_right())) {
			this.addFieldError("ro_menu_right", "菜单权限不能为空！");
		}
		if(existsTitle(role.getRole_name(),oldRoleName,"role","role_name")){
			this.addFieldError("role.role_name","角色名称已存在");
			return view();
		}
		//递归查找当前用户创建相关的角色
		String userStr = searchuser(role.getUser_id());
		if(userStr!=null&&!"".equals(userStr)){
			String str = userStr.substring(0,userStr.length()-1);
			//获取相关role列表
			HashMap map = new HashMap();
			map.put("user_id_in",str);
			List rolelist = roleService.getList(map);
			if(rolelist!=null && rolelist.size()>0){
				for(int i=0;i<rolelist.size();i++){
					HashMap mapvalue = (HashMap)rolelist.get(i);
					String role_id = mapvalue.get("role_id").toString();
					//获取数据库角色权限对象
					//获取菜单权限串(比较当前权限串和数据库权限串差异)
					String newmenu_right = role.getMenu_right();
					String oldmenu_right = mapvalue.get("menu_right").toString();
					String oldmenuStr[] =  oldmenu_right.replace(" ", "").split(",");
					//查找出去除的权限串
					String deletemenustr ="";
					for(int j=0;j<oldmenuStr.length;j++){
						if(newmenu_right.indexOf(oldmenuStr[j])==-1){
							deletemenustr +=oldmenuStr[j]+",";
						}
					}
					//替换禁用的权限串
					if(!"".equals(deletemenustr)){
					String menustr[] = deletemenustr.split(",");
						for(int s=0;s<menustr.length;s++){
							oldmenu_right=oldmenu_right.replace(menustr[s], "").replace(",,", ",");
						}
					}
					//获取操作权限串
					String newoper_right = role.getOper_right();
					String oldoprer_right = mapvalue.get("oper_right").toString();
					String oldoperStr[] =  oldoprer_right.replace(" ", "").split(",");
					//查找出去除的操作权限串
					String deleteoperstr ="";
					for(int k=0;k<oldoperStr.length;k++){
						if(newoper_right.indexOf(oldoperStr[k])==-1){
							deleteoperstr +=oldoperStr[k]+",";
						}
					}
					//替换禁用的权限串
					if(!"".equals(deleteoperstr)){
					String operstr[] = deleteoperstr.split(",");
						for(int s=0;s<operstr.length;s++){
							oldoprer_right=oldoprer_right.replace(operstr[s], "").replace(",,", ",");
						}
					}
					//
				   Role	newrole = roleService.get(role_id);
				   newrole.setMenu_right(oldmenu_right);
				   newrole.setOper_right(oldoprer_right);
				   this.roleService.update(newrole);
				}
			}
		}
		
		role.setCust_id(this.session_cust_id);
		// 字段验证
		super.commonValidateField(role);
		if (super.ifvalidatepass) {
			return view();
		}
		this.roleService.update(role);
		this.addActionMessage("修改角色信息成功");
		return list();
	}
	//递归查找当前用户创建相关的角色
	public String searchuser(String user_id){
		HashMap map = new HashMap();
		map.put("subjection", user_id);
		List sysuserList = sysuserService.getList(map);
		if(sysuserList!=null && sysuserList.size()>0){
			for(int i=0;i<sysuserList.size();i++){
			HashMap mapvalue=(HashMap)sysuserList.get(i);
			String userid = mapvalue.get("user_id").toString();
			userStr+=userid+",";
			searchuser(userid);
			}
		}
		return userStr;
	}


	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (role == null) {
			role = new Role();
		}
		String id = role.getRole_id();
		if (!ValidateUtil.isDigital(id)) {
			role = this.roleService.get(id);
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



}
