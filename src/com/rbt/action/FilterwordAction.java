/*
 
 * Package:com.rbt.action
 * FileName: FilterwordAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Filterword;
import com.rbt.service.IFilterwordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 敏感字Action层实现
 * @author  创建人 QJY
 * @date  创建日期 Jul 7, 2014 3:40:23 PM
 */
@Controller
public class FilterwordAction extends AdminBaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1048476386272720275L;
	
	/*******************实体层****************/
	public Filterword filterword;

	/*******************业务层接口****************/
	@Autowired
	private IFilterwordService filterwordService;

	/*********************集合******************/
	public List filterwordList;//敏感字信息集合


	/*********************字段******************/
	public String name_s;//敏感字
	public String rep_name_s;//替换字
	

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (name_s != null && !name_s.equals("")) {
			pageMap.put("name", name_s);
		}
		if (rep_name_s != null && !rep_name_s.equals("")) {
			pageMap.put("rep_name", rep_name_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.filterwordService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		filterwordList = this.filterwordService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		if(commonCheck())return add();
		this.filterwordService.insert(filterword);
		this.addActionMessage("新增敏感字成功");
		this.filterword = null;
		return INPUT;
	}

	/**
	 * 方法描述：根据主键找出敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = filterword.getWord_id();
		if(!ValidateUtil.isDigital(id)){
			filterword = this.filterwordService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证，并检验
        if(commonCheck())return view();
		this.filterwordService.update(filterword);
		this.addActionMessage("修改敏感字成功");
		return list();
	}
     /**
	 * @author：XBY
	 * @date：Feb 11, 2014 1:21:41 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		if (ValidateUtil.isRequired(filterword.getName())) {
			this.addFieldError("filterword.name", "敏感字不能为空");
		}
		//验证敏感字是否重复
		if (existsTitle(filterword.getName(),oldinfotitle, "filter_word","name")) {
			this.addFieldError("filterword.name", "敏感字不能重复!");
		}
		super.commonValidateField(filterword);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：删除敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.filterwordService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除敏感字成功");
		}else{
			this.addActionMessage("删除敏感字失败");
		}
		return list();
	}

	/**
	 * @return the filterwordList
	 */
	public List getFilterwordList() {
		return filterwordList;
	}

	/**
	 * @param filterwordList
	 *            the filterwordList to set
	 */
	public void setFilterwordList(List filterwordList) {
		this.filterwordList = filterwordList;
	}

	/**
	 * @return the filterword
	 */
	public Filterword getFilterword() {
		return filterword;
	}

	/**
	 * @param filterword
	 *            the filterword to set
	 */
	public void setFilterword(Filterword filterword) {
		this.filterword = filterword;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(filterword == null){
			filterword = new Filterword();
		}
		String id = filterword.getWord_id();
		if(!ValidateUtil.isDigital(id)){
			filterword = this.filterwordService.get(id);
		}
	}
}
