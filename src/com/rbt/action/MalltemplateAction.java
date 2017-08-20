/*
 
 * Package:com.rbt.action
 * FileName: MalltemplateAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Malltemplate;
import com.rbt.service.IMalltemplateService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商城模板信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Dec 28 10:31:27 CST 2014
 */
@Controller
public class MalltemplateAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Malltemplate malltemplate;
	
	/*******************业务层接口****************/
	@Autowired
	private IMalltemplateService malltemplateService;
	/*********************集合********************/
	public List malltemplateList;//商城模板信息
	public List mallEnableTemplateList;//商城模板当前使用模板
	/*********************字段********************/
	
	public String template_code_s;
	
	

	/**
	 * 方法描述：返回新增商城模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增商城模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(!commonCheck()){
			return add();
		}
		this.malltemplateService.insert(malltemplate);
		this.addActionMessage("新增商城模板信息成功！");
		this.malltemplate = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商城模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!commonCheck()){
			return view();
		}
		this.malltemplateService.update(malltemplate);
		this.addActionMessage("修改商城模板信息成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 4:52:39 PM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
		this.malltemplate.setUser_id(this.session_user_id);
		super.commonValidateField(malltemplate);
		if (super.ifvalidatepass) {
			return false;
		}
		if (malltemplate.getIs_enable().equals("0")) {
			this.malltemplateService.updateisenable();
		}
		return true;
	}
	/**
	 * 方法描述：删除商城模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String trade_id = "";
		if (malltemplate.getTrade_id() != null
				&& !"".equals(malltemplate.getTrade_id())) {
			trade_id=malltemplate.getTrade_id();
			boolean flag = this.malltemplateService.delete(trade_id);
			if (flag) {
				this.addActionMessage("删除商城模板信息成功");
			} else {
				this.addActionMessage("删除商城模板信息失败");
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
		if (!ValidateUtil.isRequired(template_code_s)) {
			pageMap.put("template_code", template_code_s);
		}
		pageMap.put("is_enable", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.malltemplateService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		malltemplateList = this.malltemplateService.getList(pageMap);

		// 找出当前使用的模板
		Map enableMap = new HashMap();
		enableMap.put("is_enable", "0");
		mallEnableTemplateList = this.malltemplateService.getList(enableMap);

		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出商城模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.malltemplate.getTrade_id();
		if (id == null || id.equals("")) {
			malltemplate = new Malltemplate();
		} else {
			malltemplate = this.malltemplateService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the MalltemplateList
	 */
	public List getMalltemplateList() {
		return malltemplateList;
	}

	/**
	 * @author LSQ
	 * @date : Jan 14, 2014 12:51:00 PM
	 * @Method Description :启用需要使用的模板
	 */

	public String selTemplete() throws Exception {
		String id = this.malltemplate.getTrade_id();
		if (id == null || id.equals("")) {
			return list();
		}
		// 设置所有的模板都为不默认
		this.malltemplateService.updateisenable();
		// 设置当前的默认模板
		Malltemplate mtl = this.malltemplateService.get(id);
		mtl.setIs_enable("0");
		this.malltemplateService.update(mtl);

		this.addActionMessage("使用模板成功！");
		return list();
	}

	/**
	 * @param malltemplateList
	 *            the MalltemplateList to set
	 */
	public void setMalltemplateList(List malltemplateList) {
		this.malltemplateList = malltemplateList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (malltemplate == null) {
			malltemplate = new Malltemplate();
		}
		String id = this.malltemplate.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			malltemplate = this.malltemplateService.get(id);
		}
	}

	public List getMallEnableTemplateList() {
		return mallEnableTemplateList;
	}

	public void setMallEnableTemplateList(List mallEnableTemplateList) {
		this.mallEnableTemplateList = mallEnableTemplateList;
	}

	/**
	 * @return the malltemplate
	 */
	public Malltemplate getMalltemplate() {
		return malltemplate;
	}

	/**
	 * @param Malltemplate
	 *            the malltemplate to set
	 */
	public void setMalltemplate(Malltemplate malltemplate) {
		this.malltemplate = malltemplate;
	}


}
