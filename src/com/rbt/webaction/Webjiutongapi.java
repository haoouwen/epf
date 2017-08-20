package com.rbt.webaction;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Goodsorder;
import com.rbt.service.IGoodsorderService;

/*
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: Webjiutongapi.java 
 */

/**
 * @function 功能 跨境通API信息action类
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 31 13:55:21 CST 2015
 */
@Controller
public class Webjiutongapi extends WebbaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3613548779622305289L;
	private Goodsorder goodsorder;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Action(value = "exitorderapi")
	public void exitorderapi() throws Exception {
		System.out.println("=================start api==============================");
		/**
		 * 获取参数信息
		 * */
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String method =request.getParameter("method"); //商家订单号
		String data =request.getParameter("data"); //获取跨境通传过来的data值
		JSONObject jsondata = JSONObject.fromObject(data);  //转换为json对象
		
		System.out.println("=================method="+method+"===========================");
		
		boolean sResult = false;//接口调用结果标志
		
		if(ValidateUtil.isRequired(method)){
			System.out.println("=================参数method不能为空===========================");
		}else if("order.sooutputwarehouse".equals(method.toLowerCase())){//Order.SOOutputWareHouse 订单出库
			sResult = SOOutputWareHouse(jsondata);
		}else if("order.sooutputcustoms".equals(method.toLowerCase())){//Order.SOOutputCustoms 订单出区方法 【预留】 
			//TODO 
		}else if("inventory.channelq4sadjustrequest".equals(method.toLowerCase())){//inventory.channelq4sadjustrequest 库存调整通知  【预留】
			//TODO 
		}else{
			System.out.println("=================参数method不正确===========================");
		}
			
		/**
		 * 根据接口调用情况回执信息
		 * */
		if(sResult){
			out.print("SUCCESS");
		}else {
			out.print("FAILURE");
		}
		System.out.println("=================end api==============================");
	}
	/**
	 * 跨境通返回物流信息
	 * */
	private boolean SOOutputWareHouse(JSONObject jsondata){
		String MerchantOrderID = jsondata.getString("MerchantOrderID"); //商家订单号
		String ShipTypeID = jsondata.getString("ShipTypeID"); //订单物流运输公司编号(可选)
		String TrackingNumber = jsondata.getString("TrackingNumber");  //订单物流编号（可选）
		String CommitTime = jsondata.getString("CommitTime");  //出库时间，一共 14 位格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2 位] 例 如：20071117020101
		//必选项不为空
		if(!"".equals(MerchantOrderID)&&!"".equals(ShipTypeID)&&!"".equals(TrackingNumber)&&!"".equals(CommitTime)){
			//更新运单
			goodsorder = goodsorderService.get(MerchantOrderID);
			if(goodsorder!=null){
				goodsorder.setSend_mode("5688834713");//该处指定圆通
				goodsorder.setSend_num(TrackingNumber);
				goodsorderService.update(goodsorder);
				System.out.println("=================接收物流信息成功！====1=<"+MerchantOrderID+">==2=<"+TrackingNumber+">====");
				return true;
			}else{
				System.out.println("=================没有找到订单！==============================");
				return false;
			}
		}else{
			System.out.println("=================请求参数不完整！==============================");
			return false;
		}
	}
	/**
	 * 跨境通订单出区通知
	 * */
	@SuppressWarnings("unused")
	private boolean SOOutputCustoms(JSONObject jsondata){
		String MerchantOrderID = jsondata.getString("MerchantOrderID"); //商家订单号
		String Status = jsondata.getString("Status");   //-1 表示出关失败 1  表示出关成功;
		String ShipTypeID = jsondata.getString("ShipTypeID"); //订单物流运输公司编号(可选)
		String TrackingNumber = jsondata.getString("TrackingNumber");  //订单物流编号（可选）
		String CommitTime = jsondata.getString("CommitTime");  //出库时间，一共 14 位格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2 位] 例 如：20071117020101
		//必选项不为空
		if(!"".equals(MerchantOrderID)&&!"".equals(Status)&&!"".equals(CommitTime)){
			//更新运单
			goodsorder = goodsorderService.get(MerchantOrderID);
			if(goodsorder!=null){
			goodsorder.setSend_mode(ShipTypeID);
			goodsorder.setSend_num(TrackingNumber);
			goodsorderService.update(goodsorder);
			System.out.println("=================start api SUCCESS==============================");
			return true;
			}else{
				System.out.println("=================start api FAILURE==============================");
				return false;
			}
		}else{
			System.out.println("=================start api FAILURE==============================");
			return false;
		}
	}
}