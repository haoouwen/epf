/*
 
 * Package:com.rbt.action
 * FileName: MalllevelsetAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Malllevelset;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.ISysmenuService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商城会员等级信息action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Dec 28 14:46:47 CST 2014
 */
@Controller
public class MalllevelsetAction extends AdminBaseAction implements Preparable {
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Malllevelset malllevelset;
	
	/*******************业务层接口****************/
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private ISysmenuService sysmenuService;
	/*********************集合********************/
	public List malllevelsetList;//商城会员等级信息
	public List menuList;///菜单信息集合
	public List b2cmenuList;
	/*********************字段********************/
	
	public String mem_type_s;//1代表买家列表 0卖家
	public String jsonMenu;// 获取商城B2C菜单
	public String level_name_s;

	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 4:56:11 PM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
		if (ValidateUtil.isRequired(malllevelset.getMenu_right())) {
			this.addFieldError("malllevelset.menu_right", "菜单权限不能为空！");
		}
		/*if(!ValidateUtil.isRequired(malllevelset.getInter_height())&&!ValidateUtil.isRequired(malllevelset.getInter_lower()) &&Integer.valueOf(malllevelset.getInter_height()) < Integer.valueOf(malllevelset.getInter_lower()) ){
			this.addFieldError("malllevelset.inter_lower", "下限积分不能大于上限积分");
			return false;
		}*/
		super.commonValidateField(malllevelset);
		if (super.ifvalidatepass) {
			return false;
		}
		// 去掉菜单串中的空格
		String menu_right_str = malllevelset.getMenu_right();
		menu_right_str = menu_right_str.replace(" ", "");
		malllevelset.setMenu_right(menu_right_str);
		return true;
	}
	/**
	 * 方法描述：返回新增记录商城会员等级信息页面买家
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyadd() throws Exception {
		// 加载树
		menuList=this.malllevelsetService.roleTree(getSession());
		jsonMenu=GridTreeUtil.getJsonStr(menuList);
		return goUrl("buyinsert");
	}

	/**
	 * 方法描述：新增记录商城会员等级信息买家
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyinsert() throws Exception {
		// 判断是否已存在级别代码
		if (!ValidateUtil.isRequired(malllevelset.getLevel_code())
				&& existsTitle(malllevelset.getLevel_code(), "",
						"malllevelset", "level_code")) {
			this.addFieldError("malllevelset.level_code", "级别代码已存在,请重新输入");
		}
		if(!commonCheck()){
			return buyadd();
		}

		this.malllevelsetService.insert(malllevelset);
		this.addActionMessage("新增会员级别信息成功！");
		this.malllevelset = null;
		return buylist();
	}

	/**
	 * 方法描述：修改记录商城会员等级信息信息买家
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyupdate() throws Exception {
		if(!commonCheck()){
			return buyview();
		}
		this.malllevelsetService.update(malllevelset);
		this.addActionMessage("修改会员级别信息成功！");
		return buylist();
	}

	/**
	 * @author : LJQ
	 * @date : May 3, 2014 11:03:38 AM
	 * @Method Description :公用删除
	 */
	private void commonDel(){
		String idStr = this.chb_id;
		idStr = idStr.replace(" ", "");
		String[] ids = idStr.split(",");
		for (int i = 0; i < ids.length; i++) {
			this.malllevelsetService.delete(ids[i]);
		}
		this.addActionMessage("删除会员级别信息成功！");
	}

	/**
	 * 方法描述：删除记录商城会员等级信息信息买家
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buydelete() throws Exception {
		commonDel();
		return buylist();
	}

	/**
	 * 方法描述：获取买家的信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String buylist() throws Exception {
		commonList("1");
		return goUrl("buylist");
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 5:00:31 PM
	 * @Method Description : 通用列表
	 * @param mem_type:1代表买家列表 0卖家
	 */ 
	public void commonList(String mem_type){
		Map pageMap = new HashMap();
		pageMap.put("mem_type", mem_type);
		// 筛选
		if (level_name_s != null && !level_name_s.equals("")) {
			pageMap.put("level_name", level_name_s);
		}
		if (mem_type_s != null && !"".equals(mem_type_s)&& !mem_type_s.equals("4")) {
			pageMap.put("mem_type", mem_type_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.malllevelsetService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		malllevelsetList = this.malllevelsetService.getList(pageMap);
	}

	/**
	 * 方法描述：根据主键找出记录商城会员等级信息信息买家
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buyview() throws Exception {
		if(!commonview()){
			return buylist();
		}
		return goUrl("buyupdate");
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 5:06:06 PM
	 * @Method Description :通用查看页面
	 */
	public boolean commonview(){
		String id = this.malllevelset.getLevel_code();
		if (id == null || id.equals("")) {
			malllevelset = new Malllevelset();
		} else {
			malllevelset = this.malllevelsetService.get(id);
		}
		if(ValidateUtil.isRequired(malllevelset.getLevel_code())){
			return false;
		}
		malllevelset=this.malllevelsetService.get(malllevelset.getLevel_code());
		// 获取商城B2C菜单
		menuList=this.malllevelsetService.roleTree(getSession());
		jsonMenu=GridTreeUtil.getJsonStr(menuList);
		return true;
	}

	/**
	 * @return the MalllevelsetList
	 */
	public List getMalllevelsetList() {
		return malllevelsetList;
	}

	/**
	 * @param malllevelsetList
	 *            the MalllevelsetList to set
	 */
	public void setMalllevelsetList(List malllevelsetList) {
		this.malllevelsetList = malllevelsetList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (malllevelset == null) {
			malllevelset = new Malllevelset();
		}
	}

	public String getLevel_name_s() {
		return level_name_s;
	}

	public void setLevel_name_s(String level_name_s) {
		this.level_name_s = level_name_s;
	}

	public String getMem_type_s() {
		return mem_type_s;
	}

	public void setMem_type_s(String mem_type_s) {
		this.mem_type_s = mem_type_s;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public String getJsonMenu() {
		return jsonMenu;
	}

	public void setJsonMenu(String jsonMenu) {
		this.jsonMenu = jsonMenu;
	}

	public List getB2cmenuList() {
		return b2cmenuList;
	}

	public void setB2cmenuList(List list) {
		b2cmenuList = list;
	}
	/**
	 * @return the malllevelset
	 */
	public Malllevelset getMalllevelset() {
		return malllevelset;
	}

	/**
	 * @param Malllevelset
	 *            the malllevelset to set
	 */
	public void setMalllevelset(Malllevelset malllevelset) {
		this.malllevelset = malllevelset;
	}
}
