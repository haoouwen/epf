/*
 
 * Package:com.rbt.action
 * FileName: CommutemplateAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Commutemplate;
import com.rbt.service.ICommutemplateService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录会员发送模板信息action类
 * @author 创建人 HZX
 * @date 创建日期 Sat Dec 29 13:20:17 CST 2014
 */
@Controller
public class CommutemplateAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	private Commutemplate commutemplate;

	/** *****************业务层接口*************** */
	@Autowired
	private ICommutemplateService commutemplateService;

	/** *******************集合***************** */
	public List commutemplateList;// 记录会员发送模板信息信息集合

	/** *******************字段***************** */
	public String temp_name_s;// 搜索模板名称
	private String temp_code;// 搜索模板代码
	private String temp_type;// 搜索模板类型
	public String oldtempcode;// 搜索模板旧代码

	/**
	 * 方法描述：返回新增邮件模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增邮件模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck())
			return add();
		this.commutemplateService.insert(commutemplate);
		this.addActionMessage("新增邮件模板信息成功！");
		this.commutemplate = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改邮件模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck())
			return view();
		this.commutemplateService.update(commutemplate);
		this.addActionMessage("修改邮件模板信息成功！");
		return list();
	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:43:58 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		// 验证模板代码不能为空
		if (ValidateUtil.isRequired(commutemplate.getTemp_code())) {
			this.addFieldError("commutemplate.temp_code", "模板代码不能为空");
		}
		if (!ValidateUtil.isRequired(commutemplate.getTemp_code())
				&& existsTitle(commutemplate.getTemp_code(), oldtempcode,
						"commutemplate", "temp_code")) {
			this.addFieldError("commutemplate.temp_code", "模板代码已存在,请重新输入");
		}
		if (ValidateUtil.isRequired(commutemplate.getSend_who())) {
			this.addFieldError("commutemplate.send_who", "请选择发送方");
		}
		if (commutemplate.getSend_who() != null
				&& "3".equals(commutemplate.getSend_who())) {
			if(ValidateUtil.isRequired(commutemplate.getTemp_conTwo()))
				this.addFieldError("commutemplate.temp_conTwo", "卖方模板内容不能为空");
			if(ValidateUtil.isRequired(commutemplate.getTemp_con()))
				this.addFieldError("commutemplate.temp_con", "买方模板内容不能为空");
		}
		
		if (commutemplate.getSend_who() != null
				&& "2".equals(commutemplate.getSend_who())) {
			if(ValidateUtil.isRequired(commutemplate.getTemp_conTwo()))
			this.addFieldError("commutemplate.temp_conTwo", "卖方模板内容不能为空");
		}
		
		if (commutemplate.getSend_who() != null
				&& "1".equals(commutemplate.getSend_who())) {
			if(ValidateUtil.isRequired(commutemplate.getTemp_con()))
				this.addFieldError("commutemplate.temp_con", "买方模板内容不能为空");
		}
		
		super.commonValidateField(commutemplate);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:50:44 PM
	 * @Method Description：公共修改跳转方法
	 */
	public void commonView() {
		String id = this.commutemplate.getTemp_id();
		if (id == null || id.equals("")) {
			commutemplate = new Commutemplate();
		} else {
			commutemplate = this.commutemplateService.get(id);
		}
	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:53:42 PM
	 * @Method Description：公共查询方法
	 */
	public void commonList(Map pageMap) {
		// 搜索模板名称
		if (temp_name_s != null && !temp_name_s.equals("")) {
			pageMap.put("temp_name", temp_name_s);
		}
		temp_code = commutemplate.getTemp_code();
		// 搜索模板代码
		if (temp_code != null && !temp_code.equals("")) {
			pageMap.put("temp_code", temp_code);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.commutemplateService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		commutemplateList = this.commutemplateService.getList(pageMap);
	}

	/**
	 * @Method Description : 公共删除
	 * @author : HZX
	 * @date : May 3, 2014 3:37:30 PM
	 */
	private boolean commonDelete() {
		boolean flag = this.commutemplateService.delete(chb_id);
		return flag;
	}

	/**
	 * 方法描述：删除邮件模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = commonDelete();
		if (flag) {
			this.addActionMessage("删除邮件模板信息成功");
		} else {
			this.addActionMessage("删除邮件模板信息失败");
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
		pageMap.put("temp_type", "0");
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出邮件模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		commonView();
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出邮件模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String msgalertview() throws Exception {
		String temp_code = this.commutemplate.getTemp_code();
		if (temp_code == null || temp_code.equals("")) {
			commutemplate = new Commutemplate();
		} else {
			commutemplate = this.commutemplateService.getByTempcode(temp_code);
		}

		String temp_type = commutemplate.getTemp_type();
		if (temp_type.equals("0")) {
			return goUrl(VIEW);
		} else if (temp_type.equals("1")) {
			return goUrl("dxupdate");
		} else {
			return goUrl("znxupdate");
		}
	}

	// 短信模块

	/**
	 * 方法描述：返回新增短信模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dxadd() throws Exception {
		return goUrl("dxinsert");
	}

	/**
	 * 方法描述：新增短信模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dxinsert() throws Exception {
		this.commutemplate.setTemp_id(this.session_cust_id);
		// 字段验证
		if (commonCheck())
			return dxadd();
		this.commutemplateService.insert(commutemplate);
		this.addActionMessage("新增短信模板信息成功！");
		this.commutemplate = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改短信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dxupdate() throws Exception {
		// 字段验证
		if (commonCheck())
			return dxview();
		this.commutemplateService.update(commutemplate);
		this.addActionMessage("修改短信模板信息成功！");
		return dxlist();
	}

	/**
	 * 方法描述：删除短信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dxdelete() throws Exception {
		boolean flag = commonDelete();
		if (flag) {
			this.addActionMessage("删除短信模板信息成功");
		} else {
			this.addActionMessage("删除短信模板信息失败");
		}
		return dxlist();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String dxlist() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("temp_type", "1");
		commonList(pageMap);
		return goUrl("dxlist");
	}

	/**
	 * 方法描述：根据主键找出短信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String dxview() throws Exception {
		commonView();
		return goUrl("dxupdate");
	}

	// 站内信模块

	/**
	 * 方法描述：返回新增站内信模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String znxadd() throws Exception {
		return goUrl("znxinsert");
	}

	/**
	 * 方法描述：新增站内信模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String znxinsert() throws Exception {
		// 字段验证
		this.commutemplate.setTemp_id(this.session_cust_id);
		if (commonCheck())
			return znxadd();
		this.commutemplateService.insert(commutemplate);
		this.addActionMessage("新增站内信模板信息成功！");
		this.commutemplate = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改站内信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String znxupdate() throws Exception {
		// 字段验证
		if (commonCheck())
			return znxview();
		this.commutemplateService.update(commutemplate);
		this.addActionMessage("修改站内信模板信息成功！");
		return znxlist();
	}

	/**
	 * 方法描述：删除站内信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String znxdelete() throws Exception {
		boolean flag = commonDelete();
		if (flag) {
			this.addActionMessage("删除站内信模板信息成功");
		} else {
			this.addActionMessage("删除站内信模板信息失败");
		}
		return znxlist();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String znxlist() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("temp_type", "2");
		commonList(pageMap);
		return goUrl("znxlist");
	}

	/**
	 * 方法描述：根据主键找出站内信模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String znxview() throws Exception {
		commonView();
		return goUrl("znxupdate");
	}

	/**
	 * @return the CommutemplateList
	 */
	public List getCommutemplateList() {
		return commutemplateList;
	}

	/**
	 * @param commutemplateList
	 *            the CommutemplateList to set
	 */
	public void setCommutemplateList(List commutemplateList) {
		this.commutemplateList = commutemplateList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (commutemplate == null) {
			commutemplate = new Commutemplate();
		}
		String id = this.commutemplate.getTemp_id();
		if (!this.validateFactory.isDigital(id)) {
			commutemplate = this.commutemplateService.get(id);
		}
	}

	public String getTemp_name_s() {
		return temp_name_s;
	}

	public void setTemp_name_s(String temp_name_s) {
		this.temp_name_s = temp_name_s;
	}

	public String getTemp_code() {
		return temp_code;
	}

	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}

	public String getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}

	/**
	 * @return the commutemplate
	 */
	public Commutemplate getCommutemplate() {
		return commutemplate;
	}

	/**
	 * @param Commutemplate
	 *            the commutemplate to set
	 */
	public void setCommutemplate(Commutemplate commutemplate) {
		this.commutemplate = commutemplate;
	}

}
