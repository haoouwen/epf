/*
 
 * Package:com.rbt.action
 * FileName: OrderhistoryAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Orderhistory;
import com.rbt.service.IOrderhistoryService;

/**
 * @function 功能 订单状态历史记录action类
 * @author 创建人 CYC 订单历史
 * @date 创建日期 Tue Nov 01 13:15:49 CST 2014
 */
@Controller
public class OrderhistoryAction extends AdminBaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	public Orderhistory orderhistory;
	/*******************业务层接口****************/

	@Autowired
	public IOrderhistoryService orderhistoryService;
	/*********************集合********************/
	public List orderhistoryList;//订单状态历史记录信息
	
	/*********************字段********************/
	public String order_id_s;//订单编号
	public String cust_name_s;//会员名 
	public String starttime_s;//时间搜索
	public String endtime_s;

	/**
	 * 方法描述：返回新增订单状态历史记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增订单状态历史记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.orderhistoryService.insert(orderhistory);
		this.addActionMessage("新增订单状态历史成功");
		this.orderhistory = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改订单状态历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.orderhistoryService.update(orderhistory);
		this.addActionMessage("修改订单状态历史成功");
		return list();
	}
	/**
	 * 方法描述：删除订单状态历史记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.orderhistoryService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除订单状态历史记录信息成功");
		}else{
			this.addActionMessage("删除订单状态历史记录信息失败");
		}

		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//搜索订单id字段
		if (order_id_s != null && !order_id_s.equals("")) {
			pageMap.put("order_id", order_id_s);
		}
		//搜索操作人字段
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		//开始时间结束时间段
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.orderhistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		orderhistoryList = this.orderhistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出订单状态历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	/**
	 * @return the OrderhistoryList
	 */
	public List getOrderhistoryList() {
		return orderhistoryList;
	}
	/**
	 * @param orderhistoryList
	 *  the OrderhistoryList to set
	 */
	public void setOrderhistoryList(List orderhistoryList) {
		this.orderhistoryList = orderhistoryList;
	}
	public String getOrder_id_s() {
		return order_id_s;
	}
	public void setOrder_id_s(String order_id_s) {
		this.order_id_s = order_id_s;
	}

	public String getStarttime_s() {
		return starttime_s;
	}
	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}
	public String getEndtime_s() {
		return endtime_s;
	}
	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	/**
	 * @return the orderhistory
	 */
	public Orderhistory getOrderhistory() {
		return orderhistory;
	}
	/**
	 * @param Orderhistory
	 * the orderhistory to set
	 */
	public void setOrderhistory(Orderhistory orderhistory) {
		this.orderhistory = orderhistory;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
	if(orderhistory == null){
		orderhistory = new Orderhistory();
	}
	String id = orderhistory.getOrder_id();
	if(!ValidateUtil.isDigital(id)){
		orderhistory = this.orderhistoryService.get(id);
	}
}
}

