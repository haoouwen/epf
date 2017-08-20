/*
 
 * Package:com.rbt.action
 * FileName: OrderdetailAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Orderdetail;
import com.rbt.service.IOrderdetailService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录订单商品详细信息action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 11 16:38:19 CST 2014
 */
@Controller
public class OrderdetailAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Orderdetail orderdetail;
	
	/*******************业务层接口****************/

	@Autowired
	private IOrderdetailService orderdetailService;
	/*********************集合********************/
	public List orderdetailList;//订单商品详细信息
	
	/*********************字段********************/
	public String order_id_s;//订单编号
	/**
	 * 方法描述：返回新增记录订单商品详细信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录订单商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(orderdetail);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.orderdetailService.insert(orderdetail);
		this.addActionMessage("新增记录订单商品详细信息成功！");
		this.orderdetail = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录订单商品详细信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(orderdetail);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.orderdetailService.update(orderdetail);
		this.addActionMessage("修改记录订单商品详细信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录订单商品详细信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.orderdetailService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录订单商品详细信息成功");
		}else{
			this.addActionMessage("删除记录订单商品详细信息失败");
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
	    if(order_id_s!=null &&!"".equals(order_id_s)){
	    	pageMap.put("order_id_s", order_id_s);
	    }

		//根据页面提交的条件找出信息总数
		int count=this.orderdetailService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		orderdetailList = this.orderdetailService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录订单商品详细信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.orderdetail.getTrade_id();
		if(id==null || id.equals("")){
			orderdetail = new Orderdetail();
		}else{
			orderdetail = this.orderdetailService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the OrderdetailList
	 */
	public List getOrderdetailList() {
		return orderdetailList;
	}
	/**
	 * @param orderdetailList
	 *  the OrderdetailList to set
	 */
	public void setOrderdetailList(List orderdetailList) {
		this.orderdetailList = orderdetailList;
	}
	/**
	 * @return the orderdetail
	 */
	public Orderdetail getOrderdetail() {
		return orderdetail;
	}
	/**
	 * @param Orderdetail
	 *            the orderdetail to set
	 */
	public void setOrderdetail(Orderdetail orderdetail) {
		this.orderdetail = orderdetail;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(orderdetail == null){
			orderdetail = new Orderdetail();
		}
		String id = this.orderdetail.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			orderdetail = this.orderdetailService.get(id);
		}
	}

}

