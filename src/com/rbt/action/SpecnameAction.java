/*
 
 * Package:com.rbt.action
 * FileName: SpecnameAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Specname;
import com.rbt.service.ISpecnameService;
import com.rbt.service.ISpecvalueService;
import com.rbt.model.Specvalue;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录规格名称信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:40:54 CST 2014
 */
@Controller
public class SpecnameAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Specname specname;
	
	/*******************业务层接口****************/
	@Autowired
	private ISpecnameService specnameService;
	@Autowired
	private ISpecvalueService specvalueService;
	
	/*********************集合********************/
	public List cat_attr_list;//分类信息
	public List specnameList;//规格名称信息
	public List specsizevalueList;
	
	/*********************字段********************/
	public String cat_attr_str;//关系信息串
	public String spe_name;//规格名称
	public String spec_name_s;//规格名称
	public String all_spec_sort_no_attr;
	public String all_spec_value_attr;//值串
	public String all_spec_img_attr;//图片传 
	public String all_spec_id;
	


	
	/**
	 * 方法描述：返回新增记录规格名称信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if(cat_attr_str!=null&&!"".equals(cat_attr_str)){
			cat_attr_list(cat_attr_str);
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录规格名称信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String spec_code = RandomStrUtil.getNumberRand();
		this.specname.setSpec_code(spec_code);
		if(cat_attr_str==null||cat_attr_str.equals("")){
			this.addFieldError("specname.cat_attr","请选择分类！");
		}
		if(ValidateUtil.isRequired(all_spec_value_attr)){
			this.addFieldError("all_spec_value_attr","请添加规格值！");
		}
		//分类
		if(cat_attr_str!=null &&!"".equals(cat_attr_str)){
			specname.setCat_attr(cat_attr_str);
		}else {
			specname.setCat_attr("");
		}
		
		
		if(super.ifvalidatepass){
			return add();
		}
		//插入规格表
		this.specnameService.insert(specname);
		//插入规格值表
		insertSpecValue(spec_code);
		this.addActionMessage("新增记录规格名称信息成功！");
		this.specname = null;
		return add();
	}

	/**
	 * @author lin 
	 * @date : Sep 27, 2014 11:02:32 PM
	 * @Method Description : 插入规格值表
	 */
    public void insertSpecValue(String spec_id)throws Exception{
    	String[] str_all_spec_sort_no_attr=null;
    	String[] str_all_spec_value_attr=null;
    	String[] str_all_spec_img_attr=null;
		//获取规格值名称
		if(all_spec_value_attr!=null&&!"".equals(all_spec_value_attr)){
			all_spec_value_attr=all_spec_value_attr.replace(" ", "");
			str_all_spec_value_attr=all_spec_value_attr.split(",");
		}
		//获取规格排序值
		if(all_spec_sort_no_attr!=null&&!"".equals(all_spec_sort_no_attr)){
			all_spec_sort_no_attr=all_spec_sort_no_attr.replace(" ", "");
			str_all_spec_sort_no_attr=all_spec_sort_no_attr.split(",");
		}
		//获取规格的图片
		if(all_spec_img_attr!=null&&!"".equals(all_spec_img_attr)){
			all_spec_img_attr=all_spec_img_attr.replace(" ", "");
			str_all_spec_img_attr=all_spec_img_attr.split(",");
		}
		if(str_all_spec_value_attr!=null&&str_all_spec_value_attr.length>0)	{
			for(int i=0;i<str_all_spec_value_attr.length;i++){
				Specvalue specValueModel=new Specvalue();
				String value_id = RandomStrUtil.getNumberRand();
				specValueModel.setSv_code(value_id);
				specValueModel.setSv_img_path(str_all_spec_img_attr[i]);
				specValueModel.setSort_no(str_all_spec_sort_no_attr[i]);
				specValueModel.setSv_name(str_all_spec_value_attr[i]);
				specValueModel.setSpec_code(spec_id);
				specvalueService.insert(specValueModel);
			}
		}
    }
	
	
	
	/**
	 * 方法描述：修改记录规格名称信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(cat_attr_str==null||cat_attr_str.equals("")){
			this.addFieldError("specname.cat_attr","请选择分类！");
		}
		if(ValidateUtil.isRequired(all_spec_value_attr)){
			this.addFieldError("all_spec_value_attr","请添加规格值！");
		}
		//分类
		if(cat_attr_str!=null &&!"".equals(cat_attr_str)){
			specname.setCat_attr(cat_attr_str);
		}else {
			specname.setCat_attr("");
		}
		super.commonValidateField(specname);
		if(super.ifvalidatepass){
			return view();
		}
		String id=specname.getSpec_code();
		if(id!=null && !id.equals("")){
			this.specnameService.update(specname);
			//deleteAttr();//删除规格值
			specvalueService.delete(id);//删除规格值
			insertSpecValue(id);
			this.addActionMessage("修改记录规格名称信息成功！");
		}else{
			this.addActionMessage("修改记录规格名称信息失败！");
		}
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Jan 14, 2014 3:01:53 PM
	 * @Method Description :删除规格值
	 */
	public void deleteAttr()throws Exception{
		this.specnameService.delete(chb_id);
	}
	
	/**
	 * 方法描述：删除记录规格名称信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.specnameService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录规格名称信息成功");
		}else{
			this.addActionMessage("删除记录规格名称信息成功失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (!ValidateUtil.isRequired(cat_attr_s)&&!cat_attr_s.equals("0")) {
			String cat_attrs=cat_attr_s.replace(" ","");
			pageMap.put("cat_attr", cat_attrs);
			//回选分类
			viewCat(cat_attr_s);
		}
		if(!ValidateUtil.isRequired(spe_name)){
			pageMap.put("spe_name", spe_name);
		}
		//根据页面提交的条件找出信息总数
		int count=this.specnameService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		specnameList = this.specnameService.getList(pageMap);
		//获取规格值列表
		Map specvalueMap = new HashMap();
		specsizevalueList=specvalueService.getList(specvalueMap);
		specnameList=ToolsFuc.replaceList(specnameList,"");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录规格名称信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		String id = this.specname.getSpec_code();
		if(id==null || id.equals("")){
			specname = new Specname();
		}else{
			specname = this.specnameService.get(id);
		}
		if(cat_attr_str==null){
			cat_attr_str=specname.getCat_attr();
		}
		cat_attr_list(cat_attr_str);
		if(id==null || id.equals("")){
			specname = new Specname();
		}else{
			specname = this.specnameService.get(id);
			all_spec_id=specname.getSpec_code();
		}
		//根据规格ID获取规格值列表
		Map valMap =new HashMap();
		valMap.put("spec_code", all_spec_id);
		specsizevalueList=this.specvalueService.getList(valMap);
		return goUrl(VIEW);
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	private void cat_attr_list(String cat_ids){
		String ids[]=cat_ids.split("\\|");
		cat_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			listMap.put("id",id);
			String catName=CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if(!id.equals("")&&!catName.equals("")){
				cat_attr_list.add(i,listMap);
			}
		}
	}
	
	/**
	 * @return the SpecnameList
	 */
	public List getSpecnameList() {
		return specnameList;
	}
	/**
	 * @param specnameList
	 *  the SpecnameList to set
	 */
	public void setSpecnameList(List specnameList) {
		this.specnameList = specnameList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(specname == null){
			specname = new Specname();
		}
		String id = this.specname.getSpec_code();
		if(!this.validateFactory.isDigital(id)){
			specname = this.specnameService.get(id);
		}
	}
	/**
	 * @return the specname
	 */
	public Specname getSpecname() {
		return specname;
	}
	/**
	 * @param Specname
	 *            the specname to set
	 */
	public void setSpecname(Specname specname) {
		this.specname = specname;
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
}

