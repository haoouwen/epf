/*
 
 * Package:com.rbt.action
 * FileName: ParagroupAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Paragroup;
import com.rbt.service.IParagroupService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品扩展属性信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 10:14:11 CST 2014
 */
@Controller
public class ParagroupAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Paragroup paragroup;
	
	/*******************业务层接口****************/
	@Autowired
	private IParagroupService paragroupService;
	
	/*********************集合********************/
	public List cat_attr_list;//分类信息
	public List paragroupList;
	/*********************字段********************/
	public String cat_attr_str;
	public String isort_no;//排序
	public String paragroup_sortno_id;
	public String para_name_s;//参数组名


	/**
	 * 方法描述：返回新增商品扩展属性信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 判断回选是否为空
		if (cat_attr_str != null && !"".equals(cat_attr_str)) {
			cat_attr_list(cat_attr_str);
		}
		return goUrl(ADD);
	}

	/**
	 * @author LSQ
	 * @date : Jan 16, 2014 10:25:08 AM
	 * @Method Description :添加参数
	 */
	public String insert() throws Exception {
		// 设置参数ID为随机数
		String paragroup_id = RandomStrUtil.getNumberRand();
		paragroup.setPara_group_id(paragroup_id);
		// 验证参数组名不能为空
		if (ValidateUtil.isRequired(paragroup.getPara_name())) {
			this.addFieldError("paragroup.para_name", "参数组名不能为空");
		}
		// 判断回选是否为空
		if (cat_attr_str != null && !"".equals(cat_attr_str)) {
			paragroup.setCat_attr(cat_attr_str);
		} else {
			paragroup.setCat_attr("");
		}
		// 检查该参数组是否已经存在
		if (existsTitle(paragroup.getPara_name(), "", "paragroup", "para_name")) {
			this.addFieldError("paragroup.para_name", "该参数组已经存在,请重新输入");
		}
		super.commonValidateField(paragroup);
		if (super.ifvalidatepass) {
			return add();
		}

		this.paragroupService.insert(paragroup);
		this.addActionMessage("添加参数成功！");
		this.paragroup = null;
		return INPUT;
	}

	/**
	 * @author LSQ
	 * @date : Jan 16, 2014 10:34:38 AM
	 * @Method Description :修改参数
	 */
	public String update() throws Exception {
		String id = paragroup.getPara_group_id();
		if (id == null || id.equals("")) {
			paragroup = new Paragroup();
		}
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}

		// 判断回选是否为空
		if (cat_attr_str != null && !cat_attr_str.equals("")) {
			if(cat_attr_str.length()>950){
				this.addFieldError("paragroup.cat_attr", "同一个参数组下不能添加太多分类!");
			}else{
				paragroup.setCat_attr(cat_attr_str);
			}
		} else {
			paragroup.setCat_attr("");
		}
		// 判读参数组名不能重复
		if (existsTitle(paragroup.getPara_name(), oldinfotitle, "paragroup",
				"para_name")) {
			this.addFieldError("paragroup.para_name", "参数组名不能重复");
		}
		super.commonValidateField(paragroup);
		if (super.ifvalidatepass) {
			return view();
		}

		this.paragroupService.update(paragroup);
		this.addActionMessage("修改参数成功！");
		return list();
	}

	/**
	 * 方法描述：删除商品扩展属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.paragroupService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除参数成功");
		}else{
			this.addActionMessage("删除参数失败");
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
		// 通过参数组名筛选
		if (!ValidateUtil.isRequired(para_name_s)) {
			pageMap.put("para_name", para_name_s);
		}
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.paragroupService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		paragroupList = this.paragroupService.getList(pageMap);
		paragroupList=ToolsFuc.replaceList(paragroupList,"");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出商品扩展属性信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.paragroup.getPara_group_id();
		if (id == null || id.equals("")) {
			paragroup = new Paragroup();
		} else {
			paragroup = this.paragroupService.get(id);
		}
		if (cat_attr_str == null) {
			cat_attr_str = paragroup.getCat_attr();
		}
		cat_attr_list(cat_attr_str);
		return goUrl(VIEW);
	}

	/**
	 * @return the ParagroupList
	 */
	public List getParagroupList() {
		return paragroupList;
	}

	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	private void cat_attr_list(String cat_ids) {
		String ids[] = cat_ids.split("\\|");
		cat_attr_list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			Map listMap = new HashMap();
			String id = ids[i].replace(" ", "");
			listMap.put("id", id);
			String catName = CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if (!id.equals("") && !catName.equals("")) {
				cat_attr_list.add(i, listMap);
			}

		}
	}

	/**
	 * @author LSQ
	 * @date : Jan 16, 2014 1:08:24 PM
	 * @Method Description :批量排序
	 */

	public String updateSort() throws Exception {
		boolean flag = this.paragroupService.updateSort("para_group_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("参数批量排序成功");
		}else{
			this.addActionMessage("参数批量排序失败");
		}
		return list();
	}

	/**
	 * @author LSQ
	 * @date : Jan 16, 2014 4:24:25 PM
	 * @Method Description :查看参数值
	 */
	public String viewgroupvalue() throws Exception {
		String id = this.paragroup.getPara_group_id();

		// String memberuser_id=this.memberuser.getUser_id();

		if (id == null || id.equals("")) {
			paragroup = new Paragroup();
		} else {
			paragroup = this.paragroupService.get(id);
		}

		return goUrl(AUDIT);
	}

	/**
	 * @param paragroupList
	 *            the ParagroupList to set
	 */
	public void setParagroupList(List paragroupList) {
		this.paragroupList = paragroupList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (paragroup == null) {
			paragroup = new Paragroup();
		}
		String id = this.paragroup.getPara_group_id();
		if (!this.validateFactory.isDigital(id)) {
			paragroup = this.paragroupService.get(id);
		}
	}

	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
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

	public String getParagroup_sortno_id() {
		return paragroup_sortno_id;
	}

	public void setParagroup_sortno_id(String paragroup_sortno_id) {
		this.paragroup_sortno_id = paragroup_sortno_id;
	}
	/**
	 * @return the paragroup
	 */
	public Paragroup getParagroup() {
		return paragroup;
	}

	/**
	 * @param Paragroup
	 *            the paragroup to set
	 */
	public void setParagroup(Paragroup paragroup) {
		this.paragroup = paragroup;
	}
}
