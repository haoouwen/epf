/*
 
 * Package:com.rbt.action
 * FileName: AutofckAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.model.Autofck;
import com.rbt.service.IAutofckService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 在线编辑器action类
 * @author 创建人 LHY
 * @date 创建日期 Mon Jan 28 09:51:03 CST 2014
 */
@Controller
public class AutofckAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Autofck autofck;

	/*******************业务层接口****************/
	@Autowired
	private IAutofckService autofckService;

	/*********************集合******************/
	public List autofckList;//在线编辑器

	/*********************字段******************/
	public String random_num;//随机数

	/**
	 * 方法描述：返回新增在线编辑器页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增在线编辑器
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.autofckService.insert(autofck);
		this.addActionMessage("新增在线编辑器成功！");
		this.autofck = null;
		return INPUT;
	}
	//ajax保存在线编辑器的文本
	public String save() throws Exception{
		String num=this.getRequest().getParameter("random_num");
		String content=this.getRequest().getParameter("content");
		if(num!=null && !"".equals(num)){
			autofck.setRandom_num(num);
		}
		if(content!=null && !"".equals(content)){
			autofck.setContent(content);
		}else{
			this.addFieldError("autofck.content", "请输入文本！");
			return add();
		}
		autofck.setCust_id(this.session_cust_id);
		this.autofckService.insert(autofck);
		this.addActionMessage("新增在线编辑器成功！");
		return null;
	}
	//获取数据
	public List getList() throws IOException{
		String random=this.getRequest().getParameter("random_num");
		Map pageMap = new HashMap();
		if(random!=null && !"".equals(random)){
			pageMap.put("random",random);
		}
		//根据页面提交的条件找出信息总数
		int count=this.autofckService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		autofckList = this.autofckService.getList(pageMap);
		String jsonStr=GridTreeUtil.getJsonStr(autofckList);
		HttpServletResponse response=this.getResponse();
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out=response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:48:44 PM
	 * @Method Description：ajax 获取在线编辑器的内容
	 */
	public String getContent() throws IOException{
		String id=this.getRequest().getParameter("id");
		HttpServletResponse response=this.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter();
		if(id!=null && !"".equals(id)){
			autofck=this.autofckService.get(id);
			out.write(autofck.getContent());
			out.flush();
			out.close();
		}
		return null;
	}
	/**
	 * 方法描述：修改在线编辑器信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(autofck);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.autofckService.update(autofck);
		this.addActionMessage("修改在线编辑器成功！");
		return list();
	}
	/**
	 * 方法描述：删除在线编辑器信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.autofckService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除在线编辑器成功");
		}else{
			this.addActionMessage("删除在线编辑器失败");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.autofckService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		autofckList = this.autofckService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出在线编辑器信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.autofck.getId();
		if(id==null || id.equals("")){
			autofck = new Autofck();
		}else{
			autofck = this.autofckService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the autofck
	 */
	public Autofck getAutofck() {
		return autofck;
	}
	/**
	 * @param Autofck
	 *            the autofck to set
	 */
	public void setAutofck(Autofck autofck) {
		this.autofck = autofck;
	}
	
	/**
	 * @return the AutofckList
	 */
	public List getAutofckList() {
		return autofckList;
	}
	/**
	 * @param autofckList
	 *  the AutofckList to set
	 */
	public void setAutofckList(List autofckList) {
		this.autofckList = autofckList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(autofck == null){
			autofck = new Autofck();
		}
		String id = this.autofck.getId();
		if(!this.validateFactory.isDigital(id)){
			autofck = this.autofckService.get(id);
		}
	}

}

