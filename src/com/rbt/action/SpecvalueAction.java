/*
 
 * Package:com.rbt.action
 * FileName: SpecvalueAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.model.Specvalue;
import com.rbt.service.ISpecvalueService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录规格值信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:42:06 CST 2014
 */
@Controller
public class SpecvalueAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Specvalue specvalue;
	
	/*******************业务层接口****************/
	@Autowired
	private ISpecvalueService specvalueService;
	
	/*********************集合********************/
	public List specvalueList;//规格值信息
	
	/*********************字段********************/

	
	
	/**
	 * 方法描述：返回新增记录规格值信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录规格值信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(specvalue);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.specvalueService.insert(specvalue);
		this.addActionMessage("新增记录规格值信息成功！");
		this.specvalue = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录规格值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(specvalue);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.specvalueService.update(specvalue);
		this.addActionMessage("修改记录规格值信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录规格值信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.specvalueService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录规格值信息成功");
		}else{
			this.addActionMessage("删除记录规格值信息失败");
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
		int count=this.specvalueService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		specvalueList = this.specvalueService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @author : LJQ
	 * @date : Jan 17, 2014 4:00:47 PM
	 * @Method Description :找出list列表返回json
	 */
	public String childList() throws Exception {
		Map map = new HashMap();	
		HttpServletRequest request =getRequest();
		HttpServletResponse response = getResponse();
		String parentId = request.getParameter("pId");
		List list = new ArrayList();		
		map.put("spec_code", parentId);
		try {
			list =this.specvalueService.getList(map);
			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list);
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("懒加载子串:"+jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;		
	}
	
	
	
	/**
	 * 方法描述：根据主键找出记录规格值信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.specvalue.getSv_code();
		if(id==null || id.equals("")){
			specvalue = new Specvalue();
		}else{
			specvalue = this.specvalueService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SpecvalueList
	 */
	public List getSpecvalueList() {
		return specvalueList;
	}
	/**
	 * @param specvalueList
	 *  the SpecvalueList to set
	 */
	public void setSpecvalueList(List specvalueList) {
		this.specvalueList = specvalueList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(specvalue == null){
			specvalue = new Specvalue();
		}
		String id = this.specvalue.getSv_code();
		if(!this.validateFactory.isDigital(id)){
			specvalue = this.specvalueService.get(id);
		}
	}
	/**
	 * @return the specvalue
	 */
	public Specvalue getSpecvalue() {
		return specvalue;
	}
	/**
	 * @param Specvalue
	 *            the specvalue to set
	 */
	public void setSpecvalue(Specvalue specvalue) {
		this.specvalue = specvalue;
	}

}

