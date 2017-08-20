/*
 
 * Package:com.rbt.action
 * FileName: TemplateAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Template;
import com.rbt.service.ITemplateService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 瀑布流模板action类
 * @author 创建人 XBY
 * @date 创建日期 Wed Feb 19 17:12:59 CST 2014
 */
@Controller
public class TemplateAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层****************/
	private Template template;
	/*******************业务层接口*************/
	@Autowired
	private ITemplateService templateService;
	/*********************集合*****************/
	public List templateList;//瀑布流模板
	/*********************字段*****************/
	public String template_id;//模板标识

	
	/**
	 * 方法描述：返回新增瀑布流模板页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增瀑布流模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		template.setUser_id(this.session_user_name);
		super.commonValidateField(template);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.templateService.insert(template);
		this.addActionMessage("新增瀑布流模板成功！");
		this.template = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改瀑布流模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		template.setUser_id(this.session_user_name);
		super.commonValidateField(template);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.templateService.update(template);
		this.addActionMessage("修改瀑布流模板成功！");
		return list();
	}
	/**
	 * 方法描述：删除瀑布流模板信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = chb_id;
		id = id.replace(" ", "");
		String[] ids = id.split(",");
		for(int i=0;i<ids.length;i++){
			this.templateService.delete(ids[i]);
		}
		
		this.addActionMessage("删除瀑布流模板成功！");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.templateService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		templateList = this.templateService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出瀑布流模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.template.getTemplate_id();
		if(id==null || id.equals("")){
			template = new Template();
		}else{
			template = this.templateService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @author：XBY
	 * @date：Jan 7, 2014 10:19:44 AM
	 * @Method Description：Ajax获取信息
	 */
	public void getTemplateInfo() throws Exception{
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String outputString="";
		if(!ValidateUtil.isRequired(template_id)){
			outputString=this.templateService.get(template_id).getTemplate_content();
		}
		out.write(outputString);
	}
	/**
	 * @return the TemplateList
	 */
	public List getTemplateList() {
		return templateList;
	}
	/**
	 * @param templateList
	 *  the TemplateList to set
	 */
	public void setTemplateList(List templateList) {
		this.templateList = templateList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(template == null){
			template = new Template();
		}
		String id = this.template.getTemplate_id();
		if(!this.validateFactory.isDigital(id)){
			template = this.templateService.get(id);
		}
	}

	/**
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}
	/**
	 * @param Template
	 *            the template to set
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

}

