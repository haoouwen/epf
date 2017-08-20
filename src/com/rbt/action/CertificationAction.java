/*
 
 * Package:com.rbt.action
 * FileName: CertificationAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Certification;
import com.rbt.model.Member;
import com.rbt.model.Sysuser;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICertificationService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 记录企业身份认证信息action类
 * @author 创建人 LJQ */
@Controller
public class CertificationAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Certification certification;
	public Member member;
	public Sysuser sysuser ;

	/*******************业务层接口****************/
	@Autowired
	public ICertificationService certificationService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public ISysuserService sysuserService;

	/*********************集合******************/
	public List certificationList;//记录企业身份认证信息信息
	public List commparaList;//参数
	public List cust_type_List;//企业类型
	public List catList;//分类

	/*********************字段******************/
	public String hidden_area_value;//地区隐藏值
	private String para_code = "currency_type";//币种的参数
	private String cust_para_code="cust_type";//企业的参数
	private String module_type="company";//所属模块
	public String state_s;//搜索状态
	public String cust_name_s;//公司名称
	public String corporate_s;//法定代表人
	public String cust_type_s;//企业类型
	public String app_name_s;//申请人姓名
	public String areaName;//地区名称

	/**
	 * 方法描述：返回新增记录企业身份认证信息页面
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		//地区设置
		super.selectArea();
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", 1);
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);
		// 获取公司的信息
		member=this.memberService.get(this.session_cust_id);
		//找出企业认证表中存在该数据则取表数据，如果没有，则从会员表中取数据
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		if(list!=null&&list.size()>0){
			certification=this.certificationService.get(this.session_cust_id);
			String audit_user_id=certification.getAudit_user_id();//根据审核人的ID获取审核人的名称
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
		}
		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增记录企业身份认证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 存入地区
		certification.setArea_attr(area_attr);	
		certification.setCust_id(this.session_cust_id);
		certification.setUser_id(this.session_user_id);
		// 去掉年检记录的空格符号
		if(certification.getIns_record()!=null){
			String ins_reconrd=this.certification.getIns_record();
			this.certification.setIns_record(ins_reconrd.replace(" ",""));
		}		
		//找出企业认证表中是否已存在该公司的认证
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		//字段验证
		super.commonValidateField(certification);
		if(super.ifvalidatepass){
			return add();
		}		
		if(list!=null&&list.size()>0){
			Certification cfc=new Certification();
			if(!ValidateUtil.isRequired(certification.getCust_id())){
				cfc=this.certificationService.get(certification.getCust_id().toString());
			}		
			String state=cfc.getInfo_state();
			//对审核通过的实名认证更新后减分
			if(state!=null&&state.equals("3")){
				this.certificationService.creditChangeNum(certification.getCust_id(), -1, "cfg_identity","a","修改成功","");
			}
			this.certification.setInfo_state("1");//认证中
			this.certificationService.update(certification);
			this.addActionMessage("修改认证成功");
		}else{
			this.certification.setInfo_state("0");//新加入
			this.certificationService.insert(certification);
			this.addActionMessage("添加认证成功");
		}
		return add();
	}

	

	/**
	 * 方法描述：修改记录企业身份认证信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.certificationService.update(certification);
		this.addActionMessage("修改企业身份认证成功");
		return list();
	}
	/**
	 * 方法描述：删除记录企业身份认证信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.certification.getCust_id();
		//对审核通过的实名认证删除操作减分
		this.certificationService.del(id);
		//删除记录企业身份认证
		this.certificationService.delete(id);
		this.addActionMessage("删除企业身份认证成功");
		return auditList();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @Method Description : 运营商审核实名认证列表
	 * @author : LJQ
	 * @date : Dec 2, 2014 11:20:35 AM
	 */
	public String auditList() throws Exception {		
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		//搜索内容
        Map pageMap = new HashMap();		
        if(!ValidateUtil.isRequired(state_s)){
        	pageMap.put("info_state", state_s);
        }
        if(!ValidateUtil.isRequired(cust_name_s)){
        	pageMap.put("cust_name", cust_name_s);
        }	
        if(!ValidateUtil.isRequired(corporate_s)){
        	pageMap.put("corporate", corporate_s);
        }	
        if(!ValidateUtil.isRequired(cust_type_s)){
        	pageMap.put("cust_type", cust_type_s);
        }
        if(!ValidateUtil.isRequired(app_name_s)){
        	pageMap.put("app_name", app_name_s);
        }
		//根据页面提交的条件找出信息总数
		int count=this.certificationService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		certificationList = this.certificationService.getList(pageMap);
		return goUrl(AUDITLIST);
	}
	
	/**
	 * 方法描述：根据主键找出记录企业身份认证信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(certification.getCust_id()!=null){
			if(accessControl(certification.getCust_id())){
				return list();
			}
		}
		//找出企业认证表中存在该数据则取表数据，如果没有，则从会员表中取数据
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		List list=this.certificationService.getList(map);
		if(list!=null&&list.size()<=0){
			return add();
		}
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", "1");
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);		
        //实例化对象
		if(this.session_cust_id==null || "".equals(this.session_cust_id)){
			certification = new Certification();
		}else{
			certification = this.certificationService.get(this.session_cust_id);
			if(certification.getAudit_date()!=null){
				certification.setAudit_date(certification.getAudit_date().substring(0,19));
			}
			super.viewArea(certification.getArea_attr().replace(" ",""));
			String audit_user_id=certification.getAudit_user_id();
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
			// 找出地区字段的存入隐藏值中
			areaName=AreaFuc.getAreaNameByMap(certification.getArea_attr()); 
		}
		if(certification.getInfo_state().equals("3")){
			return goUrl("auditok");
		}else{
			return goUrl(VIEW);
		}
	}
	
	/**
	 * @Method Description :运营商审核会员实名认证
	 * @author : LJQ
	 * @date : Dec 2, 2014 12:59:16 PM
	 */
	public String audit() throws Exception {
		String id=certification.getCust_id();
		if(id==null || id.equals("")){
			certification = new Certification();
		}else{
			certification = this.certificationService.get(id);	
			String audit_user_id=certification.getAudit_user_id();
			if(!ValidateUtil.isRequired(audit_user_id)){
				sysuser=this.sysuserService.get(audit_user_id);
			}
		}
		//获取地区中文字符
		String area_name = "";
		if(certification.getArea_attr() != null){
			area_name = AreaFuc.getAreaNameByMap(certification.getArea_attr());
		}
		certification.setArea_attr(area_name);
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		// 获取公司的经营范围
		Map catMap=new HashMap();
		catMap.put("cat_level", "1");
		catMap.put("module_type", module_type);
		catList=this.categoryService.getList(catMap);		
		return goUrl(AUDIT);
	}
	
	/**
	 * @Method Description : 运营商审核实名认证的状态
	 * @author : LJQ
	 * @date : Dec 2, 2014 1:29:20 PM
	 */
	public String auditState()throws Exception {
		String info_state=this.certification.getInfo_state();
		if("2".equals(info_state)){
			if(ValidateUtil.isRequired(certification.getReason())){
				this.addFieldError("certification.reason", "拒绝理由不能为空");
				return audit();
			}
		}
		Certification cfc=new Certification();
		String cust_id="";
		if(!ValidateUtil.isRequired(certification.getCust_id())){
			cust_id=certification.getCust_id().toString();
			cfc=this.certificationService.get(cust_id);
		}		
		String state=cfc.getInfo_state();
		//对审核通过的实名认证加分,3代表审核通过
		if(state!=null&&!"3".equals(state)&&"3".equals(info_state)){
			this.certificationService.creditChangeNum(cust_id, 1, "cfg_identity","a","审核通过","");
		}
		//修改审核状态
		this.certification.setAudit_user_id(this.session_user_id);//设置审核人ID	
		this.certificationService.auditState(certification);
		this.addActionMessage("审核企业身份认证信息成功");		
		return auditList();
	}
	
	
	/**
	 * @return the CertificationList
	 */
	public List getCertificationList() {
		return certificationList;
	}
	/**
	 * @param certificationList
	 *  the CertificationList to set
	 */
	public void setCertificationList(List certificationList) {
		this.certificationList = certificationList;
	}
	
	public void prepare() throws Exception { 
		super.saveRequestParameter();
		if(certification == null){
			certification = new Certification();
		}
		String id = this.certification.getCust_id();
		if(!ValidateUtil.isDigital(id)){
			certification = this.certificationService.get(id);
		}
	}
	/**
	 * @return the certification
	 */
	public Certification getCertification() {
		return certification;
	}
	/**
	 * @param Certification
	 *            the certification to set
	 */
	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}

