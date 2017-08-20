/*
 
 * Package:com.rbt.action
 * FileName: ParavalueAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Paragroup;
import com.rbt.model.Paravalue;
import com.rbt.service.IParavalueService;
import com.rbt.service.IParagroupService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 参数值信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 16:29:11 CST 2014
 */
@Controller
public class ParavalueAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Paravalue paravalue;
	private Paragroup paragroup;
	
	/*******************业务层接口****************/

	@Autowired
	private IParavalueService paravalueService;
	@Autowired
	private IParagroupService paragroupService;
	/*********************集合********************/
	public List paravalueList;//参数值信息
	
	/*********************字段********************/
	public String paravalue_sortno_id;//排序值
	public String para_name_s;//参数组名
	public String pgi_s;//参数组表的组ID
	public String pgi;//参数组表的组ID重复变量名会多值故。。
	public String isort_no;//排序
	
	/**
	 * 方法描述：返回新增参数值信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		pgi=paravalue.getPara_group_id();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增参数值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String para_id = RandomStrUtil.getNumberRand();
		paravalue.setPara_id(para_id);

		// 检查该参数组是否已经存在
		if (existsTitle(paravalue.getPara_name(), "", "paravalue", "para_name")) {
			this.addFieldError("paravalue.para_name", "该参数名称已经存在,请重新输入");
		}
		if(!ValidateUtil.isRequired(pgi)){
			paravalue.setPara_group_id(pgi);
		}
		super.commonValidateField(paravalue);
		if (super.ifvalidatepass) {
			return add();
		}

		this.paravalueService.insert(paravalue);
		this.addActionMessage("新增参数值信息成功！");
		return add();
	}

	/**
	 * 方法描述：修改参数值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = paravalue.getPara_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		// 判读参数组名不能重复
		if (existsTitle(paravalue.getPara_name(), oldinfotitle, "paravalue",
				"para_name")) {
			this.addFieldError("paravalue.para_name", "参数组名不能重复");
		}
		super.commonValidateField(paravalue);
		if (super.ifvalidatepass) {
			return view();
		}

		this.paravalueService.update(paravalue);
		this.addActionMessage("修改参数值信息成功！");
		return list();
	}

	/**
	 * 方法描述：删除参数值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.paravalueService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除参数值信息成功");
		}else{
			this.addActionMessage("删除参数值信息失败");
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
		// 通过参数名筛选
		if (!ValidateUtil.isRequired(para_name_s)) {
			pageMap.put("para_name", para_name_s);
		}
		// 将参数组表的组ID赋于参数对象中
		if (pgi_s != null && !"".equals(pgi_s)) {
			pageMap.put("para_group_id", pgi_s);
		}

		// 根据页面提交的条件找出信息总数
		int count = this.paravalueService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		paravalueList = this.paravalueService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出参数值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.paravalue.getPara_id();
		if (id == null || id.equals("")) {
			paravalue = new Paravalue();
		} else {
			paravalue = this.paravalueService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * @author LSQ
	 * @date : Jan 17, 2014 2:39:09 PM
	 * @Method Description :参数值排序
	 */
	public String updateSort() throws Exception {
		boolean flag = this.paravalueService.updateSort("para_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("参数排序成功");
		}else{
			this.addActionMessage("参数排序失败");
		}
		return list();
	}

	/**
	 * @return the ParavalueList
	 */
	public List getParavalueList() {
		return paravalueList;
	}

	/**
	 * @param paravalueList
	 *            the ParavalueList to set
	 */
	public void setParavalueList(List paravalueList) {
		this.paravalueList = paravalueList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (paravalue == null) {
			paravalue = new Paravalue();
		}
		String id = this.paravalue.getPara_id();
		if (!this.validateFactory.isDigital(id)) {
			paravalue = this.paravalueService.get(id);
		}
	}

	public Paragroup getParagroup() {
		return paragroup;
	}

	public void setParagroup(Paragroup paragroup) {
		this.paragroup = paragroup;
	}

	public IParagroupService getParagroupService() {
		return paragroupService;
	}

	public void setParagroupService(IParagroupService paragroupService) {
		this.paragroupService = paragroupService;
	}

	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}

	public String getParavalue_sortno_id() {
		return paravalue_sortno_id;
	}

	public void setParavalue_sortno_id(String paravalue_sortno_id) {
		this.paravalue_sortno_id = paravalue_sortno_id;
	}

	/**
	 * @return the paravalue
	 */
	public Paravalue getParavalue() {
		return paravalue;
	}

	/**
	 * @param Paravalue
	 *            the paravalue to set
	 */
	public void setParavalue(Paravalue paravalue) {
		this.paravalue = paravalue;
	}
}
