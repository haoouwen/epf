/*
  
 
 * Package:com.rbt.model
 * FileName: Cancelorder.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 取消订单理由实体
 * @author 创建人 XBY
 * @date 创建日期 Sat Jan 10 13:47:37 CST 2015
 */
public class Cancelorder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String buy_cust_id;
	public String getBuy_cust_id() {
		return buy_cust_id;
	}
	public void setBuy_cust_id(String buy_cust_id) {
		this.buy_cust_id = buy_cust_id;
	}
	
	private String buy_user_id;
	public String getBuy_user_id() {
		return buy_user_id;
	}
	public void setBuy_user_id(String buy_user_id) {
		this.buy_user_id = buy_user_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String buy_reason;
	public String getBuy_reason() {
		return buy_reason;
	}
	public void setBuy_reason(String buy_reason) {
		this.buy_reason = buy_reason;
	}
	
	private String buy_date;
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	
	private String sell_cust_id;
	public String getSell_cust_id() {
		return sell_cust_id;
	}
	public void setSell_cust_id(String sell_cust_id) {
		this.sell_cust_id = sell_cust_id;
	}
	
	private String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	private String reject_reason;
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	
	//在线退款 返回的标识
	private String refund_code;
	public String getRefund_code() {
		return refund_code;
	}
	public void setRefund_code(String refund_code) {
		this.refund_code = refund_code;
	}
	
	//在线退款 返回的标识信息
	private String refund_message;
	public String getRefund_message() {
		return refund_message;
	}
	public void setRefund_message(String refund_message) {
		this.refund_message = refund_message;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cancelorder[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", buy_cust_id=");
		builder.append(this.buy_cust_id);
		builder.append(", buy_user_id=");
		builder.append(this.buy_user_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", buy_reason=");
		builder.append(this.buy_reason);
		builder.append(", buy_date=");
		builder.append(this.buy_date);
		builder.append(", sell_cust_id=");
		builder.append(this.sell_cust_id);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", reject_reason=");
		builder.append(this.reject_reason);
		builder.append(", refund_code=");
		builder.append(this.refund_code);
		builder.append(", refund_message=");
		builder.append(this.refund_message);
		builder.append("]");
		return builder.toString();
	}
	

}

