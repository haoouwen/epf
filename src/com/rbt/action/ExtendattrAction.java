/*
 
 * Package:com.rbt.action
 * FileName: Extend_attrAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Extendattr;
import com.rbt.service.IExtendattrService;
import com.rbt.service.ISelfextendattrService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品扩展属性信息action类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 15 10:51:04 CST 2014
 */
@Controller
public class ExtendattrAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	
	/*******************实体层****************/
	public Extendattr extendattr;

	/*******************业务层接口****************/
	@Autowired
	private IExtendattrService extendattrService;
	@Autowired
	private ISelfextendattrService selfextendattrService;

	/*********************集合******************/
	public List cat_attr_list;//分类信息集合
	public List extendattrList;//商品扩展属性信息

	/*********************字段******************/
	public String cat_attr_str;//商品品牌商品关系信息串
	public String extendattr_sortno_id;//
	public String isort_no;//排序
	public String attr_name_s;//属性名称
	public String option_type_s;//选项类型
	public String is_display_s;//是否显示

	/**
	 * 方法描述：返回新增记录商品扩展属性信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (cat_attr_str != null && !"".equals(cat_attr_str)) {
			//构造List
			cat_attr_list=this.extendattrService.cat_attr_list(cat_attr_str,cat_attr_list);
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品扩展属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		// 验证属性名称不能为空
		if (ValidateUtil.isRequired(extendattr.getAttr_name())) {
			this.addFieldError("extendattr.attr_name", "属性名称不能为空!");
		}
		// 验证商品分类不能为空
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cat_attr_str", "商品分类不能为空！");
		}
		// 流水号为10位数随机数
		String ex_attr_id = RandomStrUtil.getNumberRand();
		extendattr.setEx_attr_id(ex_attr_id);
		// 商品分类
		if (cat_attr_str != null && !"".equals(cat_attr_str)) {
			extendattr.setCat_attr(cat_attr_str);
		} else {
			extendattr.setCat_attr("");
		}
		super.commonValidateField(extendattr);
		if (super.ifvalidatepass) {
			return add();
		}

		this.extendattrService.insert(extendattr);
		this.addActionMessage("新增商品属性成功！");
		this.extendattr = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品扩展属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		String id = extendattr.getEx_attr_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		// 验证商品分类不能为空
		if (ValidateUtil.isRequired(cat_attr_str)) {
			this.addFieldError("cat_attr_str", "商品分类不能为空！");
		}
		// 商品分类
		if (cat_attr_str != null && !"".equals(cat_attr_str)) {
			extendattr.setCat_attr(cat_attr_str);
		} else {
			extendattr.setCat_attr("");
		}
		if (extendattr.getSort_no() == null
				|| extendattr.getSort_no().equals("")) {
			extendattr.setSort_no("0");
		}
		super.commonValidateField(extendattr);
		if (super.ifvalidatepass) {
			return view();
		}

		this.extendattrService.update(extendattr);
		this.addActionMessage("修改商品属性成功！");
		return list();
	}

	/**
	 * 方法描述：删除记录商品扩展属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		Map map = new HashMap();
		map.put("ex_attr_id", chb_id);
		List list = this.selfextendattrService.getList(map);
		if(list != null && list.size() > 0) {
			this.addActionMessage("商品中含有该商品属性，不能删除商品属性");
		}else {
			boolean flag = this.extendattrService.delete(chb_id);
			if(flag){
				this.addActionMessage("删除商品属性成功");
			}else{
				this.addActionMessage("删除商品属性失败");
			}
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
		if (attr_name_s != null && !attr_name_s.equals("")) {
			pageMap.put("attr_name", attr_name_s);
		}
		if (option_type_s != null && !option_type_s.equals("")) {
			pageMap.put("option_type", option_type_s);
		}
		if (is_display_s != null && !is_display_s.equals("")) {
			pageMap.put("is_display", is_display_s);
		}
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.extendattrService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		extendattrList = this.extendattrService.getList(pageMap);
		extendattrList = ToolsFuc.replaceList(extendattrList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录商品扩展属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.extendattr.getEx_attr_id();
		if (id == null || id.equals("")) {
			extendattr = new Extendattr();
		} else {
			extendattr = this.extendattrService.get(id);
		}
		if (cat_attr_str == null) {
			cat_attr_str = extendattr.getCat_attr();
		}
		//构造List
		cat_attr_list=this.extendattrService.cat_attr_list(cat_attr_str,cat_attr_list);
		return goUrl(VIEW);
	}



	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		boolean flag = this.extendattrService.updateSort("ex_attr_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("扩展属性排序成功");
		}else{
			this.addActionMessage("扩展属性排序失败");
		}
		return list();
	}

	/**
	 * @return the extendattrList
	 */
	public List getextendattrList() {
		return extendattrList;
	}

	/**
	 * @param extendattrList
	 *            the extendattrList to set
	 */
	public void setextendattrList(List extendattrList) {
		this.extendattrList = extendattrList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (extendattr == null) {
			extendattr = new Extendattr();
		}
		String id = this.extendattr.getEx_attr_id();
		if (!this.validateFactory.isDigital(id)) {
			extendattr = this.extendattrService.get(id);
		}
	}

	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}

	public String getExtendattr_sortno_id() {
		return extendattr_sortno_id;
	}

	public void setExtendattr_sortno_id(String extendattr_sortno_id) {
		this.extendattr_sortno_id = extendattr_sortno_id;
	}

	public String getCat_attr_str() {
		return cat_attr_str;
	}

	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}

	public List getCat_attr_list() {
		return cat_attr_list;
	}

	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}

	/**
	 * @return the extendattr
	 */
	public Extendattr getextendattr() {
		return extendattr;
	}

	/**
	 * @param Extendattr
	 *            the extendattr to set
	 */
	public void setextendattr(Extendattr extendattr) {
		this.extendattr = extendattr;
	}

	public String getOption_type_s() {
		return option_type_s;
	}

	public void setOption_type_s(String option_type_s) {
		this.option_type_s = option_type_s;
	}

	public String getIs_display_s() {
		return is_display_s;
	}

	public void setIs_display_s(String is_display_s) {
		this.is_display_s = is_display_s;
	}

	public String getAttr_name_s() {
		return attr_name_s;
	}

	public void setAttr_name_s(String attr_name_s) {
		this.attr_name_s = attr_name_s;
	}
}
