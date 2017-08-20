/*
 
 * Package:com.rbt.action
 * FileName: CategoryAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Category;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICategoryService;
import com.rbt.service.ISysmoduleService;
import com.rbt.service.ITaxrateService;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CnToPinyinFuc;
import com.rbt.function.ToolsFuc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 分类信息表action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2014
 */
@Controller
public class CategoryAction extends AdminBaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2244333279321571040L;
	
	/*******************实体层****************/
	public Category category;
	public Sysmodule sysmodule;

	/*******************业务层接口****************/
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	private ITaxrateService taxrateService;

	/*********************集合******************/
	public List categoryList;//系统模块信息表信息集合
	public List moduleLists;//系统模块信息表信息集合

	/*********************字段******************/
	public String cat_id;//分类ID
	public String up_cat_name;//分类上一级名称
	public String cat_level;//分类级别
	public String back_sel_cat;//分类
	public String back_sel_cat_name;//分类名称
	public String module_type;//模块类型
	public String is_del;//判断是否删除分类
	/*
	 * 获取系统模块列表
	 */
	public void getModuleList() {
		Map pageMap = new HashMap();
		pageMap.put("state_code", "0");
		moduleLists = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * 方法描述：返回新增分类信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 设置分类等级
		String up_cat_id = "";
		if (ValidateUtil.isRequired(category.getUp_cat_id())
				|| catIdStr.equals(category.getUp_cat_id())) {
			category.setCat_level("1");
			category.setUp_cat_id(catIdStr);
		} else {
			up_cat_id = category.getUp_cat_id();
			Category catobj = this.categoryService.get(up_cat_id);
			int level=0;
			if(catobj!=null){
				level = Integer.parseInt(catobj.getCat_level());
			}else{
				is_del="1";
				return list();
			}
			String levelStr = String.valueOf((level + 1));
			category.setCat_level(levelStr);
		}
		// 设置上一级ID转换成名称
		if (!ValidateUtil.isRequired(category.getUp_cat_id())) {
			String cat_id = this.category.getUp_cat_id().toString();
			if (cat_id.equals(catIdStr)) {
				// 设置每个选框title的名称
				//String module_type = this.category.getModule_type();//11-22
				sysmodule = this.sysmoduleService.get(module_type);
				up_cat_name = sysmodule.getModule_name() + "分类";
			} else {
				// 获取所属分类的对象
				Category cat_model = this.categoryService.get(cat_id);
				if (cat_model != null) {
					up_cat_name = cat_model.getCat_name();
				}
			}
		}
		return goUrl(ADD);
	}
	
	
	//将分类的中文名称替换成英文名称并且获取索引字母
	public void replacEnNameIndex(){
		List oldList=new ArrayList();
		oldList=categoryService.getAll();
		List newList=new ArrayList();
		for(int i=0;i<oldList.size();i++){
			HashMap Opy=new HashMap();
			Opy=(HashMap)oldList.get(i);
			String newpy="",newindex="",cnname="";
			cnname=Opy.get("cat_name").toString();
			newpy=CnToPinyinFuc.getPingYin(cnname);
			newindex=newpy.substring(0,1).toUpperCase();
			Opy.put("en_name", newpy);
			Opy.put("word_index", newindex);
			newList.add(Opy);
		}
		//categoryService.updateDisplay(newList);
	}
	
	/**
	 * @Method Description :
	 * @author: HXK
	 * @date : May 29, 2014 7:07:18 PM
	 * @param 
	 * @return return_type
	 */
	public void ajaxCatGoods() throws Exception {
		String outString="";//返回前台页面提示的字符串；
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cat_id=request.getParameter("cat_id");
		String module_type=request.getParameter("module_type");
		//处理信息
		if(categoryService.categoryGoods(cat_id, module_type)==true){
			outString="1";//能删除
		}else {
			outString="2";//不能删除
		}
		out.write(outString);
	}
	/**
	 * 方法描述：新增分类信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 生成10位的随机数ID
		String cat_id = RandomStrUtil.getNumberRand();
		this.category.setCat_id(cat_id);
		// 查看分类拼音是否存在
		//Map<String, String> map = new HashMap<String, String>();
		//String en_name = category.getEn_name();
		//map.put("en_name", en_name);
		//map.put("up_cat_id",category.getUp_cat_id());
		//map.put("module_type", this.category.getModule_type());
		//List list = this.categoryService.getList(map);
		//if (list != null && list.size() > 0) {
		//	this.addFieldError("category.en_name", "分类拼音名已存在，请重新选择输入");
		//}
		if(module_type!=null&&!module_type.equals("")){
			category.setModule_type(module_type);
		}else{
			return add();
		}
		// 字段验证
		super.commonValidateField(category);
		if (super.ifvalidatepass) {
			return add();
		}
		//设置seo
		category.setSeodesc(category.getCat_name());
		category.setSeokeyword(category.getCat_name());
		category.setSeotitle(category.getCat_name());
		// 插入数据库
		this.categoryService.insert(category);
		this.addActionMessage("新增分类信息成功,请点击右上角更新缓存!","新增分类信息成功");
		this.category=null;
		return list();
	}

	/**
	 * 方法描述：修改分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = category.getCat_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		//Map map = new HashMap();
		//String en_name = category.getEn_name();
		//map.put("en_name", en_name);
		//List list = this.categoryService.getList(map);
		//if (list != null && list.size() > 0) {
			//Map listMap = (HashMap) list.get(0);
			//if (listMap != null && listMap.get("cat_id") != null) {
				//if (!id.equals(listMap.get("cat_id").toString())) {
					//this.addFieldError("category.en_name", "分类拼音名已存在，请重新选择输入");
				//}
			//}
		//}
		// 字段验证
		super.commonValidateField(category);
		if (super.ifvalidatepass) {
			return view();
		}
		//设置seo
		category.setSeodesc(category.getCat_name());
		category.setSeokeyword(category.getCat_name());
		category.setSeotitle(category.getCat_name());
		// 更新数据库
		this.categoryService.update(category);
		this.addActionMessage("修改分类信息成功,请点击右上角更新缓存!","修改分类信息成功");
		return list();
	}
    
	
	/**
	 * 方法描述：删除系统分类信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.cat_id;
		id = id.replace(" ", "");
		this.categoryService.recuDelete(id);
		if (back_sel_cat != null) {
			int i = back_sel_cat.indexOf(cat_id);
			if (i > 0) {
				back_sel_cat = back_sel_cat.substring(0, i - 1);
			}
		}
		this.addActionMessage("删除分类信息成功,请点击右上角更新缓存!","删除分类信息成功");
		return list();
	}
	
	/**
	 * 方法描述：删除分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String catdelete() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 获取前台传过来的ID值
		String ids = request.getParameter("ids");
		boolean flag=this.categoryService.catdelete(ids);
		if(flag){
			this.addActionMessage("删除分类信息成功,请点击右上角更新缓存!","删除分类信息成功");
		}
		else{
			this.addActionMessage("删除分类信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：通过父ID获取子分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String childList() throws Exception {
		Map map = new HashMap();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String parentId = request.getParameter("pId");
		String mod_type = request.getParameter("mod_type");
		List list = new ArrayList();
		map.put("up_cat_id", parentId);
		map.put("module_type", mod_type);
		try {
			list = this.categoryService.getList(map);
			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list);
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("懒加载子串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

	/**
	 * 方法描述：根据主键找出分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.cat_id;
		if (id == null || id.equals("")) {
			category = new Category();
		} else {
			category = this.categoryService.get(id);
		}
		// 获取上一级分类ID
		String cat_id = category.getCat_id();
		if (cat_id.equals(catIdStr)) {
			up_cat_name = "商品分类";
		} else {
			Category cat_model = this.categoryService.get(cat_id);
			if (cat_model != null) {
				String up_cat_id = cat_model.getUp_cat_id();
				this.category.setUp_cat_id(up_cat_id);// 上一级ID
				this.category.setCat_level(cat_model.getCat_level());// 本级的等级
				Category up_cat_model = this.categoryService.get(up_cat_id);
				if (up_cat_model != null) {
					up_cat_name = up_cat_model.getCat_name();// 取出上一级分类ID的名称
				} else {
					up_cat_name = "商品分类"; // 否则是话为顶级ID
				}
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * @Method Description : 分类批量排序
	 * @author : LJQ
	 * @date : Nov 16, 2014 4:11:10 PM
	 */
	public String updateSort() throws Exception {
		boolean flag = this.categoryService.updateSort("cat_id", "sort_no",this.category.getCat_id(), this.category.getSort_no());
		if(flag){
			this.addActionMessage("分类排序成功");
		}else{
			this.addActionMessage("分类排序失败");
		}
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Jan 8, 2014 4:00:24 PM
	 * @Method Description : 分类列表页
	 */
	public String list() {
		Map pageMap = new HashMap();
		pageMap.put("state_code", "0");
		moduleLists = this.sysmoduleService.getList(pageMap);
        //转换字符串
		moduleLists = ToolsFuc.replaceList(moduleLists, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * @author : LJQ
	 * @date : Jul 11, 2014 3:56:26 PM
	 * @Method Description : 获取对应分类的提示信息
	 */
	public String getTip() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("cid");
		Category cg = this.categoryService.get(id);
		String outStr = "";
		if (cg != null && cg.getCat_simple() != null) {
			outStr = cg.getCat_simple().toString();
		}
		PrintWriter out = response.getWriter();
		out.write(outStr);
		return Action.NONE;
	}
	/**
	 * @Method Description : 导出统计信息
	 * @author: HXK
	 * @date : Apr 25, 2014 2:47:20 PM
	 * @param 
	 * @return return_type
	 */
	public String exportCatInfo() throws Exception{
		this.categoryService.exportCatExcel();
	    this.addActionMessage("数据导出分类成功!");
	    return list();
	}
	/**
	 * @return the CategoryList
	 */
	public List getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            the CategoryList to set
	 */
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param Category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param categoryService
	 *            the categoryService to set
	 */
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		super.saveRequestParameter();
		if (category == null) {
			category = new Category();
		}
	}

	public Sysmodule getSysmodule() {
		return sysmodule;
	}

	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}
}
