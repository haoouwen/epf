package com.rbt.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Area;
import com.rbt.model.Goodsorder;
import com.rbt.model.Kjtrecall;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IOrdertransService;
@Controller
public class OfflineAction extends AdminBaseAction implements Preparable {
	
	public Goodsorder goodsorder;
	public 	Memberuser memberuser;
	public Area area;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IOrdertransService ordertransService;
	public Orderdetail Orderdetail;
	public Kjtrecall kjtrecall;

	//订单ID
	public String recallstatus;

	public void start() throws Exception{
		
		//获取脚本传递的orderid
		String orderid=this.getRequest().getParameter("orderid");
		goodsorder = goodsorderService.get(orderid);
		//AJAX获取操作获取关键字
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//返回值
		if(goodsorder!=null){
			//更新提交跨境通状态
			goodsorder.setCustoms_type("001");//设置海关类型：001-久通宏达
			goodsorder.setIs_kjtsuccess("1");
			goodsorder.setKjtorder_state("0");
			goodsorderService.update(goodsorder);
			//插入订单异动
			ordertransService.inserOrderTran(orderid, session_cust_id, session_user_id, "提交海关", goodsorder.getOrder_state(), session_user_name);
	    }
		//更新是否提交跨境通
		out.print("success");	
	}
}   
