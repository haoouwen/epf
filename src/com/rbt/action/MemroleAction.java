/*
 
 * Package:com.rbt.action
 * FileName: MemroleAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Memrole;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemroleService;
import com.rbt.service.IRolerightService;
import com.rbt.service.ISysmenuService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商城后台角色信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Thu Jan 24 16:33:37 CST 2014
 */
@Controller
public class MemroleAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	private static final String SYSCODE_b2c_VALUE = "b2c";
	/*******************实体层********************/
	private Memrole memrole;
	private Member member;
	private Malllevelset malllevelset;
	/*******************业务层接口****************/

	@Autowired
	private IMemroleService memroleService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private ISysmenuService sysmenuService;
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	public IRolerightService rolerightService;
	@Autowired
	private IMemberService memberService;
	/*********************集合********************/
	public List memroleList;//会员角色
	public List b2cmenuList;
	public List menuthreeList;
	public List menuoneList;
	public List menutwoList;
	public List rolerightList;
	public List malllevelList;
	
	/*********************字段********************/

	public String ro_menu_right;//菜单权限
	public String ro_op_right;//操作权限权限


	/**
	 * 方法描述：返回新增商城后台角色信息页面
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {

		// 菜单权限串，登陆的时候放进去的
		menuoneList = new ArrayList<Map<String, String>>();
		String menu_right = "";
		if (getSession().getAttribute("menu_right") != null) {
			menu_right = getSession().getAttribute("menu_right").toString();
			String[] menu_right_x = menu_right.split(",");
			menuoneList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"1", "0");
			menutwoList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"2", "0");
			menuthreeList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"3", "0");
		}

		// 获取菜单权限
		Map rolerightMap = new HashMap();
		rolerightMap.put("syscode", SYSCODE_b2c_VALUE);
		rolerightList = this.rolerightService.getList(rolerightMap);
		return goUrl("insert");
	}

	/**
	 * 方法描述：新增商城后台角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 设置参数ID为随机数
		this.memrole.setCust_id(this.session_cust_id);
		if (ValidateUtil.isRequired(memrole.getMemrole_name())) {
			this.addFieldError("memrole.memrole_name", "角色名称不能为空！");
		}
		if (ValidateUtil.isRequired(ro_menu_right)) {
			this.addFieldError("ro_menu_right", "菜单权限不能为空！");
		}
		if (!ValidateUtil.isRequired(ro_menu_right)) {
			memrole.setMemmenu_right(ro_menu_right.replace(" ", ""));
		}
		if (!ValidateUtil.isRequired(ro_op_right)) {
			memrole.setOper_right(ro_op_right.replace(" ", ""));
		}
		if (super.ifvalidatepass) {
			return add();
		}
		// 去掉menu_right中的空格

		this.memroleService.insert(memrole);
		this.addActionMessage("新增商城后台角色信息成功！");
		this.memrole = null;
		return list();
	}

	/**
	 * 方法描述：修改商城后台角色信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = memrole.getMemrole_id();
		if (id == null && "".equals(id)) {
			return list();
		}
		if (ValidateUtil.isRequired(memrole.getMemrole_name())) {
			this.addFieldError("memrole.memrole_name", "角色名称不能为空！");
		}
		if (ValidateUtil.isRequired(ro_menu_right)) {
			this.addFieldError("ro_menu_right", "菜单权限不能为空！");
		}
		// 验证会员是否重复
		if (existsTitle(memrole.getMemrole_name(), oldinfotitle, "memrole","memrole_name")) {
			this.addFieldError("memrole.memrole_name", "角色名称不能重复");
		}

		super.commonValidateField(memrole);
		if (super.ifvalidatepass) {
			return view();
		}
		memrole.setMemmenu_right(ro_menu_right);
		memrole.setOper_right(ro_op_right);
		this.memroleService.update(memrole);
		this.addActionMessage("修改商城后台角色信息成功！");
		return list();
	}

	/**
	 * 方法描述：删除商城后台角色信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memroleService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商城后台角色信息成功");
		}else{
			this.addActionMessage("删除商城后台角色信息成功失败");
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
		int count = this.memroleService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		pageMap.put("mem_cust_id", this.session_cust_id);
		memroleList = this.memroleService.getList(pageMap);
		// 找出角色对应的子账号数量
		memroleList=this.memroleService.getmemroleList(memroleList,this.session_cust_id);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出商城后台角色信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.memrole.getMemrole_id();
		if (id == null || id.equals("")) {
			memrole = new Memrole();
		} else {
			memrole = this.memroleService.get(id);
		}

		// 菜单权限串，登陆的时候放进去的
		menuoneList = new ArrayList<Map<String, String>>();
		String menu_right = "";
		if (getSession().getAttribute("menu_right") != null) {
			menu_right = getSession().getAttribute("menu_right").toString();
			String[] menu_right_x = menu_right.split(",");
			menuoneList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"1", "0");
			menutwoList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"2", "0");
			menuthreeList=this.memroleService.queryCommon(menu_right_x, SYSCODE_b2c_VALUE,
					"3", "0");
		}

		// 获取菜单权限
		Map rolerightMap = new HashMap();
		rolerightMap.put("syscode", SYSCODE_b2c_VALUE);
		rolerightList = this.rolerightService.getList(rolerightMap);
		return goUrl(VIEW);
	}

	/**
	 * @return the MemroleList
	 */
	public List getMemroleList() {
		return memroleList;
	}

	/**
	 * @param memroleList
	 *            the MemroleList to set
	 */
	public void setMemroleList(List memroleList) {
		this.memroleList = memroleList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memrole == null) {
			memrole = new Memrole();
		}
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getMenuthreeList() {
		return menuthreeList;
	}

	public void setMenuthreeList(List menuthreeList) {
		this.menuthreeList = menuthreeList;
	}

	public List getMenuoneList() {
		return menuoneList;
	}

	public void setMenuoneList(List menuoneList) {
		this.menuoneList = menuoneList;
	}

	public List getMenutwoList() {
		return menutwoList;
	}

	public void setMenutwoList(List menutwoList) {
		this.menutwoList = menutwoList;
	}

	public List getRolerightList() {
		return rolerightList;
	}

	public void setRolerightList(List rolerightList) {
		this.rolerightList = rolerightList;
	}

	public List getMalllevelList() {
		return malllevelList;
	}

	public void setMalllevelList(List malllevelList) {
		this.malllevelList = malllevelList;
	}

	public Malllevelset getMalllevelset() {
		return malllevelset;
	}

	public void setMalllevelset(Malllevelset malllevelset) {
		this.malllevelset = malllevelset;
	}
	/**
	 * @return the memrole
	 */
	public Memrole getMemrole() {
		return memrole;
	}

	/**
	 * @param Memrole
	 *            the memrole to set
	 */
	public void setMemrole(Memrole memrole) {
		this.memrole = memrole;
	}
}
