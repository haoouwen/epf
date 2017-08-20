/*
 
 * Package:com.rbt.action
 * FileName: SysmenuAction.java 
 */

package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Sysmenu;
import com.rbt.service.ISysmenuService;
import com.rbt.common.Constants;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;

/**
 * @function 功能 系统菜单action类
 * @author 创建人 HXK
 * @date 创建日期 Jun 13, 2014
 */
@Controller
public class SysmenuAction extends AdminBaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6657573364095211387L;
	/*******************实体层********************/
	public Sysmenu sysmenu;
	
	/*******************业务层接口****************/
	@Autowired
	public ISysmenuService sysmenuService;
	/*********************集合********************/
	public List adminmenuList;//管理员后台
	public List membermenuList;//会员后台
	public List b2cmenuList;
	public List menuList;
	public List menusList;
	/*********************字段********************/
	
	private static final String DEFAULT_SYSCODE_VALUE = "sys";//后台类型默认值
	private static final String SYSCODE_COM_VALUE = "com";
	private static final String SYSCODE_SYS_VALUE = "sys";
	public String syscode_s;//管理员后台类型
	public String menu_code;
	public String admin_menu = "sys";
	public String b2c_menu = "b2c";
	public String upenabled = "0";
	public String downenabled = "1";
	public String sysmenu_sortno_id;
	public String isort_no;//排序
	public String jsonMenu;//管理员后台树
	public String jsonMall;//会员后台树
	public String menu_level;//菜单级别
	

	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		menu_code = this.getRequest().getParameter("menu_code");
		menu_level = this.sysmenu.getMenu_level();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lev_s = "";

	public String insert() throws Exception {
		String menu_id = RandomStrUtil.getNumberRand();
		sysmenu.setMenu_id(menu_id);
		menu_code = this.getRequest().getParameter("menu_code");
		sysmenu.setSyscode(menu_code);
		// 字段验证
		super.commonValidateField(sysmenu);
		if (super.ifvalidatepass) {
			lev_s = sysmenu.getMenu_level();
			return add();
		}
		String level = String
				.valueOf(Integer.parseInt(sysmenu.getMenu_level()) - 1);
		this.sysmenuService.insert(sysmenu);
		this.addActionMessage("新增菜单信息成功");
		sysmenu.setMenu_name(null);
		sysmenu.setUrl(null);
		sysmenu.setMenu_level(level);
		return list();
	}

	/**
	 * 方法描述：修改菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String enabled = "", up_menu_id;
		String id = this.sysmenu.getMenu_id();
		List slist = new ArrayList();
		String type=this.getRequest().getParameter("type");
		// 字段验证
		sysmenu.setSyscode(menu_code);
		super.commonValidateField(sysmenu);
		if (super.ifvalidatepass) {
			return view();
		}
		if (this.sysmenu.getEnabled().equals("1")) {
			this.sysmenuService.updateValid(id,"1");
		}else{
			if(type.equals("1")){
				this.sysmenuService.updateValid(id,"0");
			}
		}
		this.sysmenuService.update(sysmenu);
		this.addActionMessage("修改菜单信息成功");
		return list();
	}

	/**
	 * 删除子菜单
	 * @return
	 * @throws Exception
	 */
	public String deletemenu() throws Exception {
		String mid = this.getMenuid();
		menu_code = this.getRequest().getParameter("menu_code");
		if (mid != null && !mid.equals("")) {
			mid = mid.replace(" ", "");
			this.sysmenuService.recuDelete(mid);
			this.addActionMessage("删除菜单信息成功");
		}
			return list();
		
	}
	
	
	public static String getListByUpmenuid() throws Exception {
		return "sssssssssss";
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @author LHY
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 加载树
		roleTree();
		jsonMenu = GridTreeUtil.getJsonStr(menuList);
		if (menu_code == null || menu_code.equals("")) {
			menu_code = "sys";
		}

		sysmenu.setSyscode(menu_code);

		Map b2cMap = new HashMap();
		b2cMap.put("syscode", "b2c");
		b2cmenuList = this.sysmenuService.getList(b2cMap);
		jsonMall = GridTreeUtil.getJsonStr(b2cmenuList);
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 得到角色菜单树
	 * @author 创建人 LJQ
	 * @date 创建日期 Jun 30, 2014 5:22:08 PM
	 */
	public void roleTree() {
		HttpSession session = getSession();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("syscode", SYSCODE_COM_VALUE);
		} else {
			pageMap.put("syscode", SYSCODE_SYS_VALUE);
		}
		// 找出信息列表，放入list
		menuList = this.sysmenuService.getList(pageMap);
		// 从数据库获取权限串,取出session中的菜单串,用于匹配菜单
		String menu_string = "";
		if (session.getAttribute("menu_right") != null) {
			menu_string = session.getAttribute("menu_right").toString();
		}
		// 操作者为运营商时运行方法
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 匹配菜单串，若不存在删除节点
			menuList=this.sysmenuService.removeMenuList(menuList,menu_string);
		}

	}

	/**
	 * 方法描述：根据主键找出菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * @function 功能 系统后台菜单
	 * @author 创建人 QJY
	 * @date 创建日期 Aug 31, 2014 5:46:47 PM
	 */
	@Action(value = "sysmenu", results = { @Result(name = "success", location = "/WEB-INF/template/manager/admin/Sysmenu/menulist.ftl") })
	public String execute() throws Exception {
		// 所有后台菜单
		Map menuMap = new HashMap();
		menuMap.put("syscode", DEFAULT_SYSCODE_VALUE);
		adminmenuList = this.sysmenuService.getList(menuMap);
		return SUCCESS;
	}

	/**
	 * @author : LJQ
	 * @date : Jan 4, 2014 3:57:36 PM
	 * @Method Description :菜单功能介绍
	 */
	public String menuview() {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		redirectUrl = request.getParameter("redurl");
		if (redirectUrl != null && !redirectUrl.equals("")) {
			return "redirectUrl";
		} else {
			response.setStatus(301);
			response.setHeader("Location", "/adminindex.action");
			response.setHeader("Connection", "close");
			return null;
		}
	}

	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		boolean flag = this.sysmenuService.updateSort("menu_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("菜单排序成功");
		}else{
			this.addActionMessage("菜单排序失败");
		}
		return list();
	}

	/**
	 * @return the adminmenuList
	 */
	public List getAdminmenuList() {
		return adminmenuList;
	}

	/**
	 * @param adminmenuList
	 *            the adminmenuList to set
	 */
	public void setAdminmenuList(List adminmenuList) {
		this.adminmenuList = adminmenuList;
	}

	/**
	 * @return the membermenuList
	 */
	public List getMembermenuList() {
		return membermenuList;
	}

	/**
	 * @param membermenuList
	 *            the membermenuList to set
	 */
	public void setMembermenuList(List membermenuList) {
		this.membermenuList = membermenuList;
	}

	/**
	 * @return the syscode_s
	 */
	public String getSyscode_s() {
		return syscode_s;
	}

	/**
	 * @param syscode_s
	 *            the syscode_s to set
	 */
	public void setSyscode_s(String syscode_s) {
		this.syscode_s = syscode_s;
	}

	/**
	 * @return the sysmenu
	 */
	public Sysmenu getSysmenu() {
		return sysmenu;
	}

	public List getB2cmenuList() {
		return b2cmenuList;
	}

	public void setB2cmenuList(List list) {
		b2cmenuList = list;
	}

	/**
	 * @param sysmenu
	 *            the sysmenu to set
	 */
	public void setSysmenu(Sysmenu sysmenu) {
		this.sysmenu = sysmenu;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (sysmenu == null) {
			sysmenu = new Sysmenu();
		}
		String id = sysmenu.getMenu_id();
		if (!ValidateUtil.isRequired(id)) {
			sysmenu = this.sysmenuService.get(id);
		}
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public String getAdmin_menu() {
		return admin_menu;
	}

	public void setAdmin_menu(String admin_menu) {
		this.admin_menu = admin_menu;
	}

	public String getB2c_menu() {
		return b2c_menu;
	}

	public void setB2c_menu(String b2c_menu) {
		this.b2c_menu = b2c_menu;
	}

	public String getSysmenu_sortno_id() {
		return sysmenu_sortno_id;
	}

	public void setSysmenu_sortno_id(String sysmenu_sortno_id) {
		this.sysmenu_sortno_id = sysmenu_sortno_id;
	}

	public String getLev_s() {
		return lev_s;
	}

	public void setLev_s(String lev_s) {
		this.lev_s = lev_s;
	}

	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	public String menuid;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
