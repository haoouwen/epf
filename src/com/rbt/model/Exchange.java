/*
  
 
 * Package:com.rbt.model
 * FileName: Exchange.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 换货实体
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 12 13:50:08 CST 2015
 */
public class Exchange implements Serializable {

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
	
	private String seller_state;
	public String getSeller_state() {
		return seller_state;
	}
	public void setSeller_state(String seller_state) {
		this.seller_state = seller_state;
	}
	
	private String seller_date;
	public String getSeller_date() {
		return seller_date;
	}
	public void setSeller_date(String seller_date) {
		this.seller_date = seller_date;
	}
	
	private String seller_reason;
	public String getSeller_reason() {
		return seller_reason;
	}
	public void setSeller_reason(String seller_reason) {
		this.seller_reason = seller_reason;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String info_date;
	public String getInfo_date() {
		return info_date;
	}
	public void setInfo_date(String info_date) {
		this.info_date = info_date;
	}
	
	private String consignee;
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	private String mconsignee;
	public String getMconsignee() {
		return mconsignee;
	}
	public void setMconsignee(String mconsignee) {
		this.mconsignee = mconsignee;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String marea_attr;
	public String getMarea_attr() {
		return marea_attr;
	}
	public void setMarea_attr(String marea_attr) {
		this.marea_attr = marea_attr;
	}
	
	private String sell_address;
	public String getSell_address() {
		return sell_address;
	}
	public void setSell_address(String sell_address) {
		this.sell_address = sell_address;
	}
	
	private String buy_address;
	public String getBuy_address() {
		return buy_address;
	}
	public void setBuy_address(String buy_address) {
		this.buy_address = buy_address;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private String mmobile;
	public String getMmobile() {
		return mmobile;
	}
	public void setMmobile(String mmobile) {
		this.mmobile = mmobile;
	}
	
	private String send_mode;
	public String getSend_mode() {
		return send_mode;
	}
	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}
	
	private String msend_mode;
	public String getMsend_mode() {
		return msend_mode;
	}
	public void setMsend_mode(String msend_mode) {
		this.msend_mode = msend_mode;
	}
	
	private String send_time;
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	private String msend_time;
	public String getMsend_time() {
		return msend_time;
	}
	public void setMsend_time(String msend_time) {
		this.msend_time = msend_time;
	}
	
	private String sure_time;
	public String getSure_time() {
		return sure_time;
	}
	public void setSure_time(String sure_time) {
		this.sure_time = sure_time;
	}
	
	private String msure_time;
	public String getMsure_time() {
		return msure_time;
	}
	public void setMsure_time(String msure_time) {
		this.msure_time = msure_time;
	}
	
	private String sell_remark;
	public String getSell_remark() {
		return sell_remark;
	}
	public void setSell_remark(String sell_remark) {
		this.sell_remark = sell_remark;
	}
	
	private String buy_remark;
	public String getBuy_remark() {
		return buy_remark;
	}
	public void setBuy_remark(String buy_remark) {
		this.buy_remark = buy_remark;
	}
	
	private String send_num;
	public String getSend_num() {
		return send_num;
	}
	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}
	
	private String msend_num;
	public String getMsend_num() {
		return msend_num;
	}
	public void setMsend_num(String msend_num) {
		this.msend_num = msend_num;
	}
	
	private String deny_num;
	public String getDeny_num() {
		return deny_num;
	}
	public void setDeny_num(String deny_num) {
		this.deny_num = deny_num;
	}
	
	private String return_no;
	public String getReturn_no() {
		return return_no;
	}
	public void setReturn_no(String return_no) {
		this.return_no = return_no;
	}
	
	private String detail_id_str;
	public String getDetail_id_str() {
		return detail_id_str;
	}
	public void setDetail_id_str(String detail_id_str) {
		this.detail_id_str = detail_id_str;
	}
	
	private String refund_state;
	public String getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(String refund_state) {
		this.refund_state = refund_state;
	}
	
	private String refund_type;
	public String getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(String refund_type) {
		this.refund_type = refund_type;
	}
	
	private String ex_goods_num;
	public String getEx_goods_num() {
		return ex_goods_num;
	}
	public void setEx_goods_num(String ex_goods_num) {
		this.ex_goods_num = ex_goods_num;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exchange[");
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
		builder.append(", seller_state=");
		builder.append(this.seller_state);
		builder.append(", seller_date=");
		builder.append(this.seller_date);
		builder.append(", seller_reason=");
		builder.append(this.seller_reason);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", info_date=");
		builder.append(this.info_date);
		builder.append(", consignee=");
		builder.append(this.consignee);
		builder.append(", mconsignee=");
		builder.append(this.mconsignee);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", marea_attr=");
		builder.append(this.marea_attr);
		builder.append(", sell_address=");
		builder.append(this.sell_address);
		builder.append(", buy_address=");
		builder.append(this.buy_address);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", mmobile=");
		builder.append(this.mmobile);
		builder.append(", send_mode=");
		builder.append(this.send_mode);
		builder.append(", msend_mode=");
		builder.append(this.msend_mode);
		builder.append(", send_time=");
		builder.append(this.send_time);
		builder.append(", msend_time=");
		builder.append(this.msend_time);
		builder.append(", sure_time=");
		builder.append(this.sure_time);
		builder.append(", msure_time=");
		builder.append(this.msure_time);
		builder.append(", sell_remark=");
		builder.append(this.sell_remark);
		builder.append(", buy_remark=");
		builder.append(this.buy_remark);
		builder.append(", send_num=");
		builder.append(this.send_num);
		builder.append(", msend_num=");
		builder.append(this.msend_num);
		builder.append(", deny_num=");
		builder.append(this.deny_num);
		builder.append(", return_no=");
		builder.append(this.return_no);
		builder.append(", detail_id_str=");
		builder.append(this.detail_id_str);
		builder.append(", refund_state=");
		builder.append(this.refund_state);
		builder.append(", refund_type=");
		builder.append(this.refund_type);
		builder.append(", ex_goods_num=");
		builder.append(this.ex_goods_num);
		builder.append("]");
		return builder.toString();
	}

}

