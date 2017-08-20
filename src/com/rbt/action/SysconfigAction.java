/*
 
 * Package:com.rbt.action
 * FileName: SysconfigAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Sysconfig;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysconfigService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 系统参数设置Action层实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 3:40:23 PM
 */
@Controller
public class SysconfigAction extends AdminBaseAction implements Preparable {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2345567567976466041L;
	/*******************实体层********************/
	public Sysconfig sysconfig;
	
	/*******************业务层接口****************/
	@Autowired
	public ISysconfigService sysconfigService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public ICommparaService commparaService;
	
	/*********************集合********************/
	public List sysconfigList;//系统变量
	public List commparaList;//参数
	/*********************字段********************/
	
	private static final String VAR_GROUP_DEFAULT_VALUE = "0";//给系统配置分组赋初始值 2：站点设置
	
	private static final String VAL_SYS_VALUE = "1";//定义默认的系统标量 1：自定义标量s
	
	public String para_key_s;//系统参数组别
	public String var_id;//标识
	public String var_value;//参数值
	public String sysdesc;//参数描述
	public String sysvalue;
	public String syssort;
	public String sysconfig_sortno_id;
	public String isort_no;//排序
	public String men_index="";
	public String admin_sysconfig_varid;//批量更新隐藏
	public String module_type_s;//模块类型
	public String module_name;//模块名称

	/**
	 * 方法描述：系统变量列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {		
		Map pageMap = new HashMap();
		if(module_type_s!=null&&!module_type_s.equals("")){
			Sysmodule sysmodule=this.sysmoduleService.get(module_type_s);
			if(sysmodule!=null){
				module_name=sysmodule.getModule_name();				
				pageMap.put("module_type", module_type_s);
				sysconfigList = this.sysconfigService.getList(pageMap);
			}
		}else{
			pageMap.put("para_code", "var_group");
			commparaList = this.commparaService.getList(pageMap);
			//给系统配置分组赋初始值2
			//2：站点设置
			if (para_key_s == null) {
				para_key_s = this.VAR_GROUP_DEFAULT_VALUE;
			}
			if (para_key_s != null && !para_key_s.equals(""))
				pageMap.put("para_key", para_key_s);
			sysconfigList = this.sysconfigService.getList(pageMap);
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * @author LHY
	 * 系统参数配置
	 * @return
	 * @throws Exception
	 */
	public String newlist() throws Exception {		
		Map pageMap = new HashMap();
		pageMap.put("para_code", "var_group");
		pageMap.put("enabled", "0");
		commparaList = this.commparaService.getList(pageMap);
		Map sysMap = new HashMap();
		sysconfigList = this.sysconfigService.getList(sysMap);
		if(men_index.equals("")){
			men_index="0";
		}
		return goUrl("newindex");
	}
	/**
	 * 更新系统配置参数
	 * @author LHY
	 * @throws Exception 
	 * 
	 */
	public String updateConfig() throws Exception{
		this.men_index=men_index;
		String mark="##########";
		String [] des = null;
		String [] val = null;
		String [] sort=null;
		//获取var_id
		if(sysdesc!=null && !"".equals(sysdesc)){
			des=sysdesc.split(mark);
		}
		//获取对应的值
		if(sysvalue!=null && !"".equals(sysvalue)){
			val=sysvalue.split(mark);
		}
		//获取排序号
		if(syssort!=null && !"".equals(syssort)){
			sort=syssort.split(mark);
		}
		List configList=new ArrayList();
		if (des.length > 0) {
			for (int i = 0; i < des.length; i++) {
				Map configMap =configMap=new HashMap();
				configMap.put("var_id", des[i].trim());
				configMap.put("var_value", val[i].trim());
				if(sort[i]==null || sort[i].trim().equals("")){
					configMap.put("sort_no", "0");
				}else{
					configMap.put("sort_no", sort[i].trim());
				}
				configList.add(configMap);
			}
		}
		this.sysconfigService.updateSysconfigBatch(configList);
		this.addActionMessage("系统参数修改信息成功,请点击右上角更新缓存!","系统参数修改信息成功");
		sysconfig.setVar_value(men_index);
		return newlist();
	}
	/**
	 * 设置商城协议
	 * @author LHY
	 * @return
	 */
	public String getMallTreaty(){
		Map pageMap = new HashMap();
		pageMap.put("var_id","216");
		sysconfigList = this.sysconfigService.getList(pageMap);
		return goUrl("treaty");
	}
	/**
	 * 更新商城协议
	 * @author LHY
	 * @return
	 */
	
	public String updateMallTreaty(){
		if(var_value==null||var_value.equals("")){
			this.addFieldError("var_value","注册协议不能为空！");
			return getMallTreaty();
		}
		this.sysconfig.setVar_id(var_id);
		this.sysconfig.setVar_value(var_value);
		this.sysconfigService.updateSys(sysconfig);
		this.addActionMessage("注册协议修改成功");
		return getMallTreaty();
	}
	/**
	 * 修改页面
	 * @author LHY
	 * @return
	 */
	public String view(){
		String var_id=this.getRequest().getParameter("var_id");
		if(var_id!=null&&!var_id.equals("")){
			sysconfig=this.sysconfigService.get(var_id);
		}
		commonView();
		return goUrl("update");
	}
	/**
	 * 更新单条数据
	 * @author LHY
	 * @return
	 * @throws Exception 
	 */
	public String update() throws Exception{
		if(!commonCheck()){
			return view();
		}
		this.sysconfigService.updateParma(sysconfig);
		this.addActionMessage("系统参数修改信息成功,请点击右上角更新缓存!","系统参数修改信息成功");
		return list();
	}
	/**
	 * 方法描述：跳转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		commonView();
		return goUrl(ADD);
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:34:29 PM
	 * @Method Description :通用跳转新增/修改
	 */
	public void commonView(){
		Map map = new HashMap();
		if(module_type_s!=null&&!module_type_s.equals("")){
			map.put("state_code", "0");
			commparaList = this.sysmoduleService.getList(map);
		}else{
			map.put("para_code", "var_group");
			commparaList = this.commparaService.getList(map);
		}
		if(sysconfig.getModule_type()!=null&&!sysconfig.getModule_type().equals("")){
			module_name=sysconfig.getModule_type();
		}
	}
	/**
	 * 方法描述：插入变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(!ValidateUtil.isRequired(sysconfig.getVar_name()) && existsTitle(sysconfig.getVar_name(),"","sysconfig","var_name")){
			this.addFieldError("sysconfig.var_name", "参数变量名称已存在,请重新输入");
		}
		if(!commonCheck()){
			return add();
		}
		this.sysconfigService.insert(sysconfig);
		this.addActionMessage("新增参数变量信息成功");
		return add();
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:37:52 PM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
	    if(!ValidateUtil.isRequired(sysconfig.getVar_value())){
			if (sysconfig.getVar_type().equals("1") && ValidateUtil.isDigital(sysconfig.getVar_value())) {
				this.addFieldError("sysconfig.var_value", "请填写数字");
			} 
		}
		if (sysconfig.getVar_group()!=null&&sysconfig.getVar_group().equals("0")) {
			this.addFieldError("sysconfig.var_group", "请选择变量类型所属组");
		}
		if (sysconfig.getModule_type()!=null&&sysconfig.getModule_type().equals("0")) {
			this.addFieldError("sysconfig.var_group", "请选择变量类型所属模块");
		}
		if(sysconfig.getSort_no()==null||sysconfig.getSort_no().equals("")){
			sysconfig.setSort_no("0");
		}
		sysconfig.setVal_sys(VAL_SYS_VALUE);
		//字段验证
		super.commonValidateField(sysconfig);
		if(super.ifvalidatepass){
			return false;
		}
		return true;
	}

	/**
	 * 方法描述：删除系统变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		boolean flag = this.sysconfigService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除系统参数信息成功");
		}else{
			this.addActionMessage("删除系统参数信息失败");
		}
		return list();
	}
	/**
	 * 方法描述：批量修改系统变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSysconfigBatch() throws Exception {
		if (sysconfig.getVar_value() != null && !sysconfig.getVar_value().equals("")) {
			String varid = this.admin_sysconfig_varid;
			String varvalue = this.sysconfig.getVar_value();
			String varidStr[] = varid.split(",");
			String varValueStr[] = varvalue.split(",");
			List sysconfigList = new ArrayList();
			if (varidStr.length > 0) {
				for (int i = 0; i < varidStr.length; i++) {
					Map configMap = new HashMap();
					configMap.put("var_id", varidStr[i]);
					configMap.put("var_value", varValueStr[i].trim());
					sysconfigList.add(configMap);
				}
			}
			this.sysconfigService.updateSysconfigBatch(sysconfigList);
			this.addActionMessage("系统参数修改信息成功,请点击右上角更新缓存!");
			return list();
		} else {
			return list();
		}

	}
	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		boolean flag = this.sysconfigService.updateSort("var_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("系统参数排序成功");
		}else{
			this.addActionMessage("系统参数排序失败");
		}
		return list();
	}
	
	/**
	 * @return the sysconfigList
	 */
	public List getSysconfigList() {
		return sysconfigList;
	}

	/**
	 * @param sysconfigList
	 *            the sysconfigList to set
	 */
	public void setSysconfigList(List sysconfigList) {
		this.sysconfigList = sysconfigList;
	}

	/**
	 * @return the commparaList
	 */
	public List getCommparaList() {
		return commparaList;
	}

	/**
	 * @param commparaList
	 *            the commparaList to set
	 */
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	/**
	 * @return the sysconfig
	 */
	public Sysconfig getSysconfig() {
		return sysconfig;
	}

	/**
	 * @param sysconfig
	 *            the sysconfig to set
	 */
	public void setSysconfig(Sysconfig sysconfig) {
		this.sysconfig = sysconfig;
	}

	/**
	 * @return the admin_sysconfig_varid
	 */
	public String getAdmin_sysconfig_varid() {
		return admin_sysconfig_varid;
	}

	/**
	 * @param admin_sysconfig_varid
	 *            the admin_sysconfig_varid to set
	 */
	public void setAdmin_sysconfig_varid(String admin_sysconfig_varid) {
		this.admin_sysconfig_varid = admin_sysconfig_varid;
	}

	public String getPara_key_s() {
		return para_key_s;
	}

	public void setPara_key_s(String para_key_s) {
		this.para_key_s = para_key_s;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if (sysconfig == null) {
		sysconfig = new Sysconfig();
	}
	String id = sysconfig.getVar_id();
	if (!ValidateUtil.isDigital(id)) {
		sysconfig = this.sysconfigService.get(id);
	}		   
}
	
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	public String getSysconfig_sortno_id() {
		return sysconfig_sortno_id;
	}
	public void setSysconfig_sortno_id(String sysconfig_sortno_id) {
		this.sysconfig_sortno_id = sysconfig_sortno_id;
	}

}
