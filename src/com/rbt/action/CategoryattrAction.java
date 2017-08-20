/*
 
 * Package:com.rbt.action
 * FileName: CategoryattrAction.java 
 */
package com.rbt.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Category;
import com.rbt.model.Attrvalue;
import com.rbt.model.Categoryattr;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICategoryService;
import com.rbt.service.IAttrvalueService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.ISysmoduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 产品属性列表action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 19 08:59:50 CST 2014
 */
@Controller
public class CategoryattrAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Category category;
	public Categoryattr categoryattr;
	public Attrvalue attrvalue;
	public CategoryFuc categoryFuc;
	
	/*******************业务层接口****************/
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public ISysmoduleService sysmoudleService;
	@Autowired
	private IAttrvalueService attrvalueService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public ISysmoduleService sysmoduleService;

	/*********************集合******************/
	public List categoryattrList;//产品属性列表信息集合
	public List moduleList;//模块

	/*********************字段******************/
	public String url_up_id="";//用来存放从URL上传过来的值
	public String default_val;//默认值
	public String attr_name_s;//属性值名称
	public String attr_type_s;//属性值类型
	public String cat_attr_s;//分类
	public String area_attr_s;//地区
	public String is_must_s;//是否必填
	public String code_value;//
	public String codehid;//
	public String modtype_s;//所属模块
	public String intr_type;//
	public String level;//级别
	public String modtype_name_id;//模块ID
	public String modtype_name;//模块名称
	public String up_level_id;//上一级ID
	public String up_level="";//上一级级别
	public String cat_value;//分类值
	public static final String UP_CAT_ID="1111111111";//上一级分类ID
	/**
	 * 方法描述：返回新增产品属性列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
       Map map=this.categoryattrService.getMap(url_up_id, level, up_level_id, up_level, modtype_name_id);
	   up_level=map.get("up_level").toString();
	   modtype_name=map.get("modtype_name").toString();
       Map modMap=new HashMap();
		moduleList=this.sysmoudleService.getList(modMap);	
		return goUrl(ADD);		
	}

	/**
	 * 方法描述：新增产品属性列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//实例化分法随机生成10位的数字
		String attr_id = RandomStrUtil.getNumberRand();
		this.categoryattr.setAttr_id(attr_id);
        // 用于所属分类的回选开始
		if(this.categoryattr.getCat_attr()==null || "".equals(this.categoryattr.getCat_attr())){
			//验证分类是选择
			validateCategoryIfSelect();
		}
		//字段验证
		super.commonValidateField(categoryattr);
		if(super.ifvalidatepass){
			return add();
		}	
		if(cat_attr!=null&&cat_attr.contains(",0"))
		{
			cat_attr=cat_attr.replace(",0", "");
		}
		//存入数据库
		if(this.cat_attr!=null && !"".equals(this.cat_attr)){
			this.categoryattr.setCat_attr(cat_attr);
		}else{
			this.categoryattr.setCat_attr(this.categoryattr.getCat_attr());
		}
		this.categoryattrService.insert(categoryattr);
		//所属分类属性值插入
		if(default_val!=null &&!default_val.equals("")){
			if(categoryattr.getAttr_type().equals("2") || categoryattr.getAttr_type().equals("3")){
				String[] val =default_val.split("\\|");
				for(int i=0;i<val.length;i++){
					String vid =  RandomStrUtil.getNumberRand();
					attrvalue =new Attrvalue();
					attrvalue.setTrade_id(vid);
					attrvalue.setAttr_id(attr_id);
					attrvalue.setAttr_value(val[i]);
					this.attrvalueService.insert(attrvalue);
				}
			}else{
				String vid =  RandomStrUtil.getNumberRand();
				attrvalue =new Attrvalue();
				attrvalue.setTrade_id(vid);
				attrvalue.setAttr_id(attr_id);
				attrvalue.setAttr_value(default_val);
				this.attrvalueService.insert(attrvalue);
			}
		}else{
			String vid =  RandomStrUtil.getNumberRand();
			attrvalue =new Attrvalue();
			attrvalue.setTrade_id(vid);
			attrvalue.setAttr_id(attr_id);
			attrvalue.setAttr_value(default_val);
			this.attrvalueService.insert(attrvalue);
		}
		this.addActionMessage("新增分类属性成功");
		this.attrvalue=null;
		this.default_val=null;
		this.categoryattr.setAttr_name("");//清空属性名称
		return add();
	}
	
	public void validateCategoryIfSelectattr()
    {
    	//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr)) {
			this.addFieldError("cate_attr", "请选择分类");
		}
    }
	
	/**
	 * 方法描述：修改产品属性列表信息 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String attr_id = categoryattr.getAttr_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isRequired(attr_id)){
			return list();
		}
        // 用于所属分类的回选开始
		if(this.categoryattr.getCat_attr()==null || "".equals(this.categoryattr.getCat_attr())){
			//验证分类是选择
			validateCategoryIfSelect();
		}
		//字段验证
		super.commonValidateField(categoryattr);
		if(super.ifvalidatepass){
			return update_view();
		}	
		//更新对象
		if(cat_attr!=null&&cat_attr.contains(",0"))
		{
			cat_attr=cat_attr.replace(",0", "");
		}
		//存入数据库
		if(this.cat_attr!=null && !"".equals(this.cat_attr)){
			this.categoryattr.setCat_attr(cat_attr);
		}else{
			this.categoryattr.setCat_attr(this.categoryattr.getCat_attr());
		}
		//先删除属性值id
		this.attrvalueService.deleteByattrid(attr_id);
		//所属分类属性值插入
		if(default_val!=null &&!default_val.equals("")){
			if(categoryattr.getAttr_type().equals("2") || categoryattr.getAttr_type().equals("3")){
				String[] val =default_val.split("\\|");
				for(int i=0;i<val.length;i++){
					String vid =  RandomStrUtil.getNumberRand();
					attrvalue =new Attrvalue();
					attrvalue.setTrade_id(vid);
					attrvalue.setAttr_id(attr_id);
					attrvalue.setAttr_value(val[i]);
					this.attrvalueService.insert(attrvalue);
				}
			}else{
				String vid =  RandomStrUtil.getNumberRand();
				attrvalue =new Attrvalue();
				attrvalue.setTrade_id(vid);
				attrvalue.setAttr_id(attr_id);
				attrvalue.setAttr_value(default_val);
				this.attrvalueService.insert(attrvalue);
			}
		}else{
			String vid =  RandomStrUtil.getNumberRand();
			attrvalue =new Attrvalue();
			attrvalue.setTrade_id(vid);
			attrvalue.setAttr_id(attr_id);
			attrvalue.setAttr_value(default_val);
			this.attrvalueService.insert(attrvalue);
		}
		this.categoryattrService.update(categoryattr);
		this.addActionMessage("修改分类属性成功");
		//清空掉select选择框中的值
		this.setCat_attr("");
		return list();
	}
	
	
	/**
	 * 方法描述：根据主键找出产品属性列表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		// 将从数据库中查询的所属分类存入分类隐藏域中
		//根据属性id获取属性值列表
		Map map=new HashMap();
		map.put("attr_id",categoryattr.getAttr_id());
		List valList=this.attrvalueService.getList(map);
		default_val="";
		if(valList!=null && valList.size()>0){
			for(int i=0;i<valList.size();i++){
				Map listMap=(HashMap)valList.get(i);
				String attr_value="";
				if(listMap.get("attr_value")!=null){
					attr_value = listMap.get("attr_value").toString();
				}
				if(i==(valList.size()-1)){
					default_val+=attr_value;
				}else{
					default_val+=attr_value+"|";
				}
			}
		}
		if(modtype_name_id!=null && !"".equals(modtype_name_id)){
			Sysmodule sysmodule=this.sysmoduleService.get(modtype_name_id);
			//模块名称
			modtype_name =sysmodule.getModule_name();
		}
		return goUrl(VIEW);
	}
	
    /**
	 * @MethodDescribe 方法描述  用于用户操作失败后select的回选
	 * @author  创建人  LJQ
	 * @date  创建日期  Jul 20, 2014 2:36:32 PM
	 */	
	public String update_view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：删除产品属性列表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.categoryattr.getAttr_id();
		id = id.replace(" ", "");
		this.categoryattrService.delete(id);
		String[] ids =id.split(",");
		for(int i=0;i<ids.length;i++){
			this.attrvalueService.deleteByattrid(id);
		}
		this.addActionMessage("删除分类属性成功");
		return list();
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if(modtype_s !=null && !modtype_s.equals("")){
			pageMap.put("module_type", modtype_s);
		}
		if (attr_name_s != null && !attr_name_s.equals("")){
			pageMap.put("attr_name",attr_name_s);		
		}
		//从搜索框中获取的分类字符串		
		String search_cat_attr =request.getParameter("cat_attr_s");		
		if (search_cat_attr==null){
			//从产品分类传过的值用来查找该分类的所有属性
			if (this.getUrl_up_id()!= null && !this.getUrl_up_id().equals("")){
				pageMap.put("cat_attr",this.getUrl_up_id().trim());
				//找出所对应的模块名称
				if(this.getUrl_up_id().trim().length()>9 ){
					//截取出cat_attr的前10个数
					String up_id=this.getUrl_up_id().trim().substring(0, 10);
					category = categoryService.get(up_id);
					if(category==null){
						category = new Category();
					}
					modtype_name_id=category.getModule_type();
					if(modtype_name_id!=null && !"".equals(modtype_name_id)){
						Sysmodule sysmodule=this.sysmoduleService.get(category.getModule_type());
						//模块名称
						modtype_name =sysmodule.getModule_name();
					}
				}
			}		
		}else{
			pageMap.put("cat_attr",search_cat_attr.trim());
		}
		if (attr_type_s != null && !attr_type_s.equals("")){
			pageMap.put("attr_type", attr_type_s);
		}
		if (is_must_s != null && !is_must_s.equals("")){
			pageMap.put("is_must", is_must_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.categoryattrService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		categoryattrList = this.categoryattrService.getList(pageMap);		
		categoryattrList = ToolsFuc.replaceList(categoryattrList, "");
		//系统模块列表
		Map modMap=new HashMap();
		moduleList=this.sysmoudleService.getList(modMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * @return the CategoryattrList
	 */
	public List getCategoryattrList() {
		return categoryattrList;
	}
	/**
	 * @param categoryattrList
	 *  the CategoryattrList to set
	 */
	public void setCategoryattrList(List categoryattrList) {
		this.categoryattrList = categoryattrList;
	}
	/**
	 * @return the categoryattr
	 */
	public Categoryattr getCategoryattr() {
		return categoryattr;
	}
	/**
	 * @param Categoryattr
	 *            the categoryattr to set
	 */
	public void setCategoryattr(Categoryattr categoryattr) {
		this.categoryattr = categoryattr;
	}


	/**
	 * @return the cat_attr
	 */
	public String getCat_attr() {
		return cat_attr;
	}

	/**
	 * @param cat_attr the cat_attr to set
	 */
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	/**
	 * @return the url_up_id
	 */
	public String getUrl_up_id() {
		return url_up_id;
	}

	/**
	 * @param url_up_id the url_up_id to set
	 */
	public void setUrl_up_id(String url_up_id) {
		this.url_up_id = url_up_id;
	}

	public String getCode_value() {
		return code_value;
	}

	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}

	public String getCodehid() {
		return codehid;
	}

	public void setCodehid(String codehid) {
		this.codehid = codehid;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(categoryattr == null){
			categoryattr = new Categoryattr();
		}
		String id = this.categoryattr.getAttr_id();
		if(!ValidateUtil.isRequired(id)){
			categoryattr = this.categoryattrService.get(id);
		}
	}

	public String getAttr_name_s() {
		return attr_name_s;
	}

	public void setAttr_name_s(String attr_name_s) {
		this.attr_name_s = attr_name_s;
	}

	public String getAttr_type_s() {
		return attr_type_s;
	}

	public void setAttr_type_s(String attr_type_s) {
		this.attr_type_s = attr_type_s;
	}

	public String getIs_must_s() {
		return is_must_s;
	}

	public void setIs_must_s(String is_must_s) {
		this.is_must_s = is_must_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

	public String getModtype_s() {
		return modtype_s;
	}

	public void setModtype_s(String modtype_s) {
		this.modtype_s = modtype_s;
	}

	
	public String getModtype_name_id() {
		return modtype_name_id;
	}

	public void setModtype_name_id(String modtype_name_id) {
		this.modtype_name_id = modtype_name_id;
	}

	public String getModtype_name() {
		return modtype_name;
	}

	public void setModtype_name(String modtype_name) {
		this.modtype_name = modtype_name;
	}

	
}

