/*
 
 * Package:com.rbt.action
 * FileName: DirectorderdetailAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Directorderdetail;
import com.rbt.service.IDirectorderdetailService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 预售订单商品详细信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:26:38 CST 2014
 */
@Controller
public class DirectorderdetailAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Directorderdetail directorderdetail;

	/*******************业务层接口****************/
	@Autowired
	private IDirectorderdetailService directorderdetailService;

	/*********************集合******************/
	public List directorderdetailList;//预售订单商品详细信息信息集合


	
	/**
	 * 方法描述：返回新增预售订单商品详细信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增预售订单商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
	    //字段验证
		if(commonCheck())return add();
		this.directorderdetailService.insert(directorderdetail);
		this.addActionMessage("新增预售订单商品详细信息成功！");
		this.directorderdetail = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改预售订单商品详细信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	    //字段验证
		if(commonCheck())return view();
		this.directorderdetailService.update(directorderdetail);
		this.addActionMessage("修改预售订单商品详细信息成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 10:34:12 AM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(directorderdetail);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：删除预售订单商品详细信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.directorderdetail.getTrade_id();
		id = id.replace(" ", "");
		this.directorderdetailService.delete(id);
		this.addActionMessage("删除预售订单商品详细信息成功！");
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
		int count=this.directorderdetailService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		directorderdetailList = this.directorderdetailService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出预售订单商品详细信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.directorderdetail.getTrade_id();
		if(id==null || id.equals("")){
			directorderdetail = new Directorderdetail();
		}else{
			directorderdetail = this.directorderdetailService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the directorderdetail
	 */
	public Directorderdetail getDirectorderdetail() {
		return directorderdetail;
	}
	/**
	 * @param Directorderdetail
	 *            the directorderdetail to set
	 */
	public void setDirectorderdetail(Directorderdetail directorderdetail) {
		this.directorderdetail = directorderdetail;
	}
	/**
	 * @return the DirectorderdetailList
	 */
	public List getDirectorderdetailList() {
		return directorderdetailList;
	}
	/**
	 * @param directorderdetailList
	 *  the DirectorderdetailList to set
	 */
	public void setDirectorderdetailList(List directorderdetailList) {
		this.directorderdetailList = directorderdetailList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(directorderdetail == null){
			directorderdetail = new Directorderdetail();
		}
		String id = this.directorderdetail.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			directorderdetail = this.directorderdetailService.get(id);
		}
	}

}

