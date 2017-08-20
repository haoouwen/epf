/*
 
 * Package:com.rbt.action
 * FileName: AuditmodelAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Auditmodel;
import com.rbt.service.IAuditmodelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 审核模型信息action类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2014
 */
@Controller
public class AuditmodelAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Auditmodel auditmodel;

	/*******************业务层接口****************/
	@Autowired
	private IAuditmodelService auditmodelService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	private ISysuserService sysuserService;
	@Autowired
	public ICommparaService commparaService;

	/*********************集合******************/
	public List auditmodelList;//审核模型信息
	public List auditmodeltypeList;//审核系统模块信息
	public List sysuserList;//系统用户信息
	public List sel_member_list;//用户信息
	public List auditList;//审核信息
	public List audittypeList;//审核模型信息
	
	/*********************字段******************/
	public String sel_mem_str;//择审核人员
	public String mem_count;//核人员数量
	public String audti_model_type;//模型类型

	/**
	 * 方法描述：返回新增审核模型信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：获取审核模型类型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void auditmodeltype() {
		Map map_list = new HashMap();
		map_list.put("para_code", "audit_model_commpara");
		auditmodeltypeList = commparaService.getList(map_list);
	}

	/**
	 * 方法描述：新增审核模型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck())
			return add();
		this.auditmodelService.inserinfo(sel_mem_str, sel_member_list,
				auditmodel);
		this.addActionMessage("新增审核模型信息成功！");
		this.auditmodel = null;
		return INPUT;
	}

	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 12:56:24 PM
	 * @Method Description：公告数据验证
	 */
	public boolean commonCheck() {
		if (sel_mem_str == null || sel_mem_str.equals("")) {
			this.addFieldError("mem_count", "请选择审核人员!");
			return true;
		}
		this.auditmodelService.sel_member_list(sel_mem_str, sel_member_list);
		if (auditmodel.getModel_type() != null
				&& auditmodel.getModel_type().equals("0")) {
			this.addFieldError("auditmodel.model_type", "请选择模型类型!");
			return true;
		}
		if (this.auditmodelService.existsAuditModelType(auditmodel
				.getModel_type()) == true) {
			this.addFieldError("auditmodel.model_type",
					"该模型类型已经存在,请选择其它或者修改该模型!");
			return true;
		}
		if (Integer.parseInt(mem_count) < 2) {
			this.addFieldError("mem_count", "请选择2个以上的审核人员!");
			return true;
		}
		super.commonValidateField(auditmodel);
		if (super.ifvalidatepass) {
			return true;
		}

		return false;
	}

	/**
	 * 方法描述：获取系统用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void systemUserList() {
		Map pageMap = new HashMap();
		pageMap.put("state", "0");
		sysuserList = this.sysuserService.getList(pageMap);
	}

	/**
	 * 方法描述：修改审核模型信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck()) {
			return view();
		}
		// 先删除信息，后新增
		if (audti_model_type != null && !audti_model_type.equals("")) {
			this.auditmodelService.delete(audti_model_type);
			this.auditmodelService.inserinfo(sel_mem_str, sel_member_list,
					auditmodel);
		}
		this.addActionMessage("修改审核模型信息成功！");
		return list();
	}

	/**
	 * 方法描述：删除审核模型信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.auditmodelService.deleteinfo(auditmodel);
		if (flag)
			this.addActionMessage("删除审核模型信息成功！");
		else {
			this.addActionMessage("删除审核模型信息失败！");
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
		audittypeList = this.auditmodelService.getList(pageMap);
		int count = 0;
		List countList = new ArrayList();
		countList = this.auditmodelService.getModelList(pageMap);
		if (countList != null && countList.size() > 0) {
			// 根据页面提交的条件找出信息总数
			count = countList.size();

		}
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		auditmodelList = this.auditmodelService.getModelList(pageMap);
		//构造一个新的List
        auditList=this.auditmodelService.auditList(auditmodelList, auditList, audittypeList);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出审核模型信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {

		Map aMap = new HashMap();
		if (audti_model_type != null && !audti_model_type.equals("")) {
			aMap.put("model_type", audti_model_type);
			auditmodel.setModel_type(audti_model_type);
		} else {
			return list();
		}

		List aList = new ArrayList();
		aList = auditmodelService.getList(aMap);
		//修改跳转判断
		this.auditmodelService.checkView(aList, mem_count, sel_mem_str, sel_member_list);
		return goUrl(VIEW);
	}

	/**
	 * @return the AuditmodelList
	 */
	public List getAuditmodelList() {
		return auditmodelList;
	}

	/**
	 * @param auditmodelList
	 *            the AuditmodelList to set
	 */
	public void setAuditmodelList(List auditmodelList) {
		this.auditmodelList = auditmodelList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (auditmodel == null) {
			auditmodel = new Auditmodel();
		}
		String id = this.auditmodel.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			auditmodel = this.auditmodelService.get(id);
		}
		// 获取参数模型类型
		auditmodeltype();
		// 获取系统用户
		systemUserList();
	}

	public List getAuditList() {
		return auditList;
	}

	public void setAuditList(List auditList) {
		this.auditList = auditList;
	}

	public String getSel_mem_str() {
		return sel_mem_str;
	}

	public void setSel_mem_str(String sel_mem_str) {
		this.sel_mem_str = sel_mem_str;
	}

	/**
	 * @return the auditmodel
	 */
	public Auditmodel getAuditmodel() {
		return auditmodel;
	}

	/**
	 * @param Auditmodel
	 *            the auditmodel to set
	 */
	public void setAuditmodel(Auditmodel auditmodel) {
		this.auditmodel = auditmodel;
	}

	public String getAudti_model_type() {
		return audti_model_type;
	}

	public void setAudti_model_type(String audti_model_type) {
		this.audti_model_type = audti_model_type;
	}

}
