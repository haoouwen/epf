/*
 
 * Package:com.rbt.action
 * FileName: CatnavAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Catnav;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICatnavService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 分类信息action类
 * @author 创建人 ZMS
 * @date 创建日期 Fri Aug 14 20:22:09 CST 2015
 */
@Controller
public class CatnavAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 分类信息对象
	 */
	private Catnav catnav;
	/*******************业务层接口****************/
	/*
	 * 分类信息业务层接口
	 */
	@Autowired
	private ICatnavService catnavService;
	@Autowired
	private ICategoryService categoryService;
	/*********************集合*******************/
	/*
	 * 分类信息信息集合
	 */
	public List catnavList;
	public List cat_attr_list;//分类信息集合
	/*********************字段*******************/
	public String cat_attr_str;//商品品牌商品关系信息串
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
		
	/**
	 * 方法描述：返回新增分类信息页面
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
	 * 方法描述：新增分类信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(cat_attr_str==null||cat_attr_str.equals("")){
			this.addFieldError("catnav.cat_attr","请选择分类！");
		}
		//分类
		if(cat_attr_str!=null &&!"".equals(cat_attr_str)){
			catnav.setCat_attr(cat_attr_str);
		}else {
			catnav.setCat_attr("");
		}
		super.commonValidateField(catnav);
		if(super.ifvalidatepass){
			return add();
		}
		
		this.catnavService.insert(catnav);
		this.addActionMessage("新增分类信息成功！");
		this.catnav = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(cat_attr_str==null||cat_attr_str.equals("")){
			this.addFieldError("catnav.cat_attr","请选择分类！");
		}
		
		// 判断回选是否为空
		if (cat_attr_str != null && !cat_attr_str.equals("")) {
			if(cat_attr_str.length()>950){
				this.addFieldError("catnav.cat_attr", "同一个分类导航下不能添加太多分类!");
			}else{
				catnav.setCat_attr(cat_attr_str);
			}
		} else {
			catnav.setCat_attr("");
		}
		super.commonValidateField(catnav);
		if(super.ifvalidatepass){
			return view();
		}
	   this.catnavService.update(catnav);
		this.addActionMessage("修改分类信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除分类信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除分类信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.catnavService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除分类信息信息成功!");
		}else{
			this.addActionMessage("删除分类信息信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		//根据页面提交的条件找出信息总数
		int count=this.catnavService.getCount(pageMap); 
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		catnavList = this.catnavService.getList(pageMap);
		//截取二级分类字符串
		if (catnavList != null && catnavList.size() > 0) {
			for (int i = 0; i < catnavList.size(); i++) {
				String cat_attrstr="";
				Map repListMap = new HashMap();
				repListMap = (HashMap) catnavList.get(i);
				if (repListMap.get("cat_attr") != null&& !repListMap.get("cat_attr").equals("")) {
					String cat_attrs[]=repListMap.get("cat_attr").toString().split("\\|");
					for(String c_attr:cat_attrs){
						cat_attrstr += CategoryFuc.getLastCateName(c_attr.trim())+"/";
					}
					if(!ValidateUtil.isRequired(cat_attrstr)){
						cat_attrstr=cat_attrstr.substring(0,cat_attrstr.length()-1);
					}
					repListMap.put("cat_attr", cat_attrstr);
				}
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.catnav.getCn_id();
		if(id==null || id.equals("")){
			catnav = new Catnav();
		}else{
			catnav = this.catnavService.get(id);
		}
		if(cat_attr_str==null){
			cat_attr_str=catnav.getCat_attr();
		}
		cat_attr_list(cat_attr_str);
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
	 * @author ZMS
	 * @date : Jan 16, 2014 1:08:24 PM
	 * @Method Description :批量排序
	 */

	public String updateSort() throws Exception {
		boolean flag = this.catnavService.updateSort("cn_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("分类排序成功");
		}else{
			this.addActionMessage("分类排序失败");
		}
		return list();
	}
	
	
	/**
	 * @return the CatnavList
	 */
	public List getCatnavList() {
		return catnavList;
	}
	/**
	 * @param catnavList
	 *  the CatnavList to set
	 */
	public void setCatnavList(List catnavList) {
		this.catnavList = catnavList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(catnav == null){
			catnav = new Catnav();
		}
		String id = this.catnav.getCn_id();
		if(!this.validateFactory.isDigital(id)){
			catnav = this.catnavService.get(id);
		}
	}
	/**
	 * @return the catnav
	 */
	public Catnav getCatnav() {
		return catnav;
	}
	/**
	 * @param Catnav
	 *            the catnav to set
	 */
	public void setCatnav(Catnav catnav) {
		this.catnav = catnav;
	}

	public List getCat_attr_list() {
		return cat_attr_list;
	}

	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}

	public String getCat_attr_str() {
		return cat_attr_str;
	}

	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}
}

