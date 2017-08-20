/*
 
 * Package:com.rbt.action
 * FileName: PrintstyletemAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Printstyletem;
import com.rbt.service.IPrintstyletemService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录打印样式模板信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 10:45:36 CST 2014
 */
@Controller
public class PrintstyletemAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Printstyletem printstyletem;
	
	/*******************业务层接口****************/
	@Autowired
	private IPrintstyletemService printstyletemService;
	
	/*********************集合********************/
	
	public List printstyletemList;//打印样式模板信息
	/*********************字段********************/

	
	/**
	 * 方法描述：返回新增记录打印样式模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录打印样式模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(printstyletem);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.printstyletemService.insert(printstyletem);
		this.addActionMessage("新增记录打印样式模板信息成功！");
		this.printstyletem = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录打印样式模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(printstyletem);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.printstyletemService.update(printstyletem);
		this.addActionMessage("修改记录打印样式模板信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录打印样式模板信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.printstyletemService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录打印样式模板信息成功");
		}else{
			this.addActionMessage("删除记录打印样式模板信息失败");
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
		int count=this.printstyletemService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		printstyletemList = this.printstyletemService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录打印样式模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.printstyletem.getTrade_id();
		if(id==null || id.equals("")){
			printstyletem = new Printstyletem();
		}else{
			printstyletem = this.printstyletemService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the PrintstyletemList
	 */
	public List getPrintstyletemList() {
		return printstyletemList;
	}
	/**
	 * @param printstyletemList
	 *  the PrintstyletemList to set
	 */
	public void setPrintstyletemList(List printstyletemList) {
		this.printstyletemList = printstyletemList;
	}

	/**
	 * @return the printstyletem
	 */
	public Printstyletem getPrintstyletem() {
		return printstyletem;
	}
	/**
	 * @param Printstyletem
	 *            the printstyletem to set
	 */
	public void setPrintstyletem(Printstyletem printstyletem) {
		this.printstyletem = printstyletem;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(printstyletem == null){
			printstyletem = new Printstyletem();
		}
		String id = this.printstyletem.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			printstyletem = this.printstyletemService.get(id);
		}
	}

}

