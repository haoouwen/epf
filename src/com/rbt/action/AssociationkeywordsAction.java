/*
 
 * Package:com.rbt.action
 * FileName: AssociationkeywordsAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.BaseAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.common.util.JsonUtil;
import com.rbt.model.Associationkeywords;
import com.rbt.service.IAssociationkeywordsService;

/**
 * @function 功能 联想关键词action类
 * @author 创建人 HXK
 * @date 创建日期 Wed Jun 24 11:18:16 CST 2015
 */
@Controller
public class AssociationkeywordsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 联想关键词对象
	 */
	private Associationkeywords associationkeywords;
	/*******************业务层接口****************/
	/*
	 * 联想关键词业务层接口
	 */
	@Autowired
	private IAssociationkeywordsService associationkeywordsService;
	
	/*********************集合*******************/
	public List cat_attr_list;//分类信息集合
	/*
	 * 联想关键词信息集合
	 */
	public List associationkeywordsList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;//搜索标题
	public String cat_attr_s;//搜索分类
	public String ass_key_words_show_s;//是否显示
	public String cat_attr_str;//商品品牌商品关系信息串
		
	/**
	 * 方法描述：返回新增联想关键词页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增联想关键词
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck()){
			return add();
		}
		this.associationkeywordsService.insert(associationkeywords);
		this.addActionMessage("添加联想关键词成功！");
		this.associationkeywords = null;
		return list();
	}
	/**
	 * 
	 * @date：Feb 10, 2014 12:42:39 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		
		if(!validateFactory.isRequired(cat_attr)){
			// 保存分类cat_attr
			cat_attr = cat_attr.replace(" ", "");
			if(cat_attr.contains(",0")){
				cat_attr=cat_attr.replace(",0", "");
			}
		}
		this.associationkeywords.setCat_attr(cat_attr);
		super.commonValidateField(associationkeywords);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：修改联想关键词信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck()) {
			return view();
		}
		this.associationkeywordsService.update(associationkeywords);
		this.addActionMessage("修改联想关键词成功！");
		return list();
	}
	/**
	 * 方法描述：删除联想关键词信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除联想关键词信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.associationkeywordsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除联想关键词信息成功!");
		}else{
			this.addActionMessage("删除联想关键词信息失败!");
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
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("words_title", title_s);
		}
		//获取搜索显示
		if(ass_key_words_show_s!=null&& !ass_key_words_show_s.equals(""))
		{
			pageMap.put("ass_key_words_show", ass_key_words_show_s);
		}
		// 获取搜索的分类
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.associationkeywordsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		associationkeywordsList = this.associationkeywordsService.getList(pageMap);
		associationkeywordsList = ToolsFuc.replaceList(associationkeywordsList, "");
		return goUrl(INDEXLIST);
	}
	/**
	 *
	 * @date : May 3, 2014 4:48:33 PM
	 * @Method Description :显示
	 */
	public String updateIsshow() throws Exception {
		updateshow();
		return list();
	}

	/**
	 *
	 * @date : May 3, 2014 4:48:43 PM
	 * @Method Description :隐藏
	 */
	public String updateUnshow() throws Exception {
		updateshow();
		return list();
	}
	
	/**
	 * @author : 
	 * @date : May 3, 2014 4:48:54 PM
	 * @Method Description :显示不显示公共方法
	 */
	public void updateshow(){
		boolean flag = this.associationkeywordsService.updateBatchState(chb_id, state_val, "ass_key_words_id", "ass_key_words_show");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("隐藏成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	}
	/**
	 * 方法描述：批量修改导航
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.associationkeywordsService.updateSort("ass_key_words_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("批量排序成功");
		}else{
			this.addActionMessage("批量排序失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据主键找出联想关键词信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.associationkeywords.getAss_key_words_id();
		if (id == null || id.equals("")) {
			associationkeywords = new Associationkeywords();
		} else {
			associationkeywords = this.associationkeywordsService.get(id);
		}
		viewCat(associationkeywords.getCat_attr());
	
		return goUrl(VIEW);
	}
	   /**
     * @Method Description : 获取关键字	
     * @author : WXP
     * @param :
     * @date Jan 25, 2014 4:56:53 PM
     */
	public void associationkeywordsList() throws IOException{
		//AJAX获取操作获取关键字
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map pageMap = new HashMap();	
		pageMap.put("ass_key_words_show", "0");
		associationkeywordsList = this.associationkeywordsService.getList(pageMap);
		JsonUtil json=new JsonUtil();
		String keywordStr = json.list2json(associationkeywordsList);
		out.print(keywordStr);	
	}
	
	
	/**
	 * @return the AssociationkeywordsList
	 */
	public List getAssociationkeywordsList() {
		return associationkeywordsList;
	}
	/**
	 * @param associationkeywordsList
	 *  the AssociationkeywordsList to set
	 */
	public void setAssociationkeywordsList(List associationkeywordsList) {
		this.associationkeywordsList = associationkeywordsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(associationkeywords == null){
			associationkeywords = new Associationkeywords();
		}
		String id = this.associationkeywords.getAss_key_words_id();
		if(!this.validateFactory.isDigital(id)){
			associationkeywords = this.associationkeywordsService.get(id);
		}
	}
	/**
	 * @return the associationkeywords
	 */
	public Associationkeywords getAssociationkeywords() {
		return associationkeywords;
	}
	/**
	 * @param Associationkeywords
	 *            the associationkeywords to set
	 */
	public void setAssociationkeywords(Associationkeywords associationkeywords) {
		this.associationkeywords = associationkeywords;
	}

	public String getAss_key_words_show_s() {
		return ass_key_words_show_s;
	}

	public void setAss_key_words_show_s(String ass_key_words_show_s) {
		this.ass_key_words_show_s = ass_key_words_show_s;
	}

	public List getCat_attr_list() {
		return cat_attr_list;
	}

	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getCat_attr_str() {
		return cat_attr_str;
	}

	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}
	
}

