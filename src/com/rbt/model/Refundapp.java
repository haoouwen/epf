/*
  
 
 * Package:com.rbt.model
 * FileName: Refundapp.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 退款申请表实体
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 29 16:04:49 CST 2014
 */
public class Refundapp implements Serializable {

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
	
	private String buy_type;
	public String getBuy_type() {
		return buy_type;
	}
	public void setBuy_type(String buy_type) {
		this.buy_type = buy_type;
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
	
	private String seller_cust_id;
	public String getSeller_cust_id() {
		return seller_cust_id;
	}
	public void setSeller_cust_id(String seller_cust_id) {
		this.seller_cust_id = seller_cust_id;
	}
	
	private String seller_user_id;
	public String getSeller_user_id() {
		return seller_user_id;
	}
	public void setSeller_user_id(String seller_user_id) {
		this.seller_user_id = seller_user_id;
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
	
	private String refund_state;
	public String getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(String refund_state) {
		this.refund_state = refund_state;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	private String seller_reason;
	
	
	
	public String getSeller_reason() {
		return seller_reason;
	}
	public void setSeller_reason(String seller_reason) {
		this.seller_reason = seller_reason;
	}
	
	public String admin_reason;
	public String getAdmin_reason() {
		return admin_reason;
	}
	public void setAdmin_reason(String admin_reason) {
		this.admin_reason = admin_reason;
	}
	public String admin_date;
	public String getAdmin_date() {
		return admin_date;
	}
	public void setAdmin_date(String admin_date) {
		this.admin_date = admin_date;
	}
	public String is_return;
	public String getIs_return() {
		return is_return;
	}
	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}
	public String is_get;
	public String getIs_get() {
		return is_get;
	}
	public void setIs_get(String is_get) {
		this.is_get = is_get;
	}
	public String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String refund_amount;
	public String getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String info_date;
	public String getInfo_date() {
		return info_date;
	}
	public void setInfo_date(String info_date) {
		this.info_date = info_date;
	}
	public String consignee;
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	public String sell_address;
	public String getSell_address() {
		return sell_address;
	}
	public void setSell_address(String sell_address) {
		this.sell_address = sell_address;
	}
	public String zip_code;
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String telephone;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String send_mode;
	public String getSend_mode() {
		return send_mode;
	}
	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}
	public String sure_time;
	public String getSure_time() {
		return sure_time;
	}
	public void setSure_time(String sure_time) {
		this.sure_time = sure_time;
	}
	public String send_time;
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	public String sell_remark;
	public String getSell_remark() {
		return sell_remark;
	}
	public void setSell_remark(String sell_remark) {
		this.sell_remark = sell_remark;
	}
	
	public String send_num;
	public String getSend_num() {
		return send_num;
	}
	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}
	
	public String deny_num;
	public String getDeny_num() {
		return deny_num;
	}
	public void setDeny_num(String deny_num) {
		this.deny_num = deny_num;
	}
	
	public String is_treated;
	public String getIs_treated() {
		return is_treated;
	}
	public void setIs_treated(String is_treated) {
		this.is_treated = is_treated;
	}
	
	public String return_no;
	public String goods_id_str;
	public String getReturn_no() {
		return return_no;
	}
	public void setReturn_no(String return_no) {
		this.return_no = return_no;
	}
	public String getGoods_id_str() {
		return goods_id_str;
	}
	public void setGoods_id_str(String goods_id_str) {
		this.goods_id_str = goods_id_str;
	}
	
	private String batch_no;
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	
	private String back_goods_num;
	public String getBack_goods_num() {
		return back_goods_num;
	}
	public void setBack_goods_num(String back_goods_num) {
		this.back_goods_num = back_goods_num;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Refundapp[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", buy_cust_id=");
		builder.append(this.buy_cust_id);
		builder.append(", buy_user_id=");
		builder.append(this.buy_user_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", buy_type=");
		builder.append(this.buy_type);
		builder.append(", buy_reason=");
		builder.append(this.buy_reason);
		builder.append(", buy_date=");
		builder.append(this.buy_date);
		builder.append(", seller_cust_id=");
		builder.append(this.seller_cust_id);
		builder.append(", seller_user_id=");
		builder.append(this.seller_user_id);
		builder.append(", seller_state=");
		builder.append(this.seller_state);
		builder.append(", seller_date=");
		builder.append(this.seller_date);
		builder.append(", refund_state=");
		builder.append(this.refund_state);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", seller_reason=");
		builder.append(this.seller_reason);
		builder.append(", admin_reason=");
		builder.append(this.admin_reason);
		builder.append(", admin_date=");
		builder.append(this.admin_date);
		builder.append(", is_return=");
		builder.append(this.is_return);
		builder.append(", is_get=");
		builder.append(this.is_get);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", refund_amount=");
		builder.append(this.refund_amount);
		builder.append(", info_date=");
		builder.append(this.info_date);
		builder.append(", consignee=");
		builder.append(this.consignee);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", zip_code=");
		builder.append(this.zip_code);
		builder.append(", sell_address=");
		builder.append(this.sell_address);
		builder.append(", telephone=");
		builder.append(this.telephone);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", send_mode=");
		builder.append(this.send_mode);
		builder.append(", send_time=");
		builder.append(this.send_time);
		builder.append(", sure_time=");
		builder.append(this.sure_time);
		builder.append(", sell_remark=");
		builder.append(this.sell_remark);
		builder.append(", send_num=");
		builder.append(this.send_num);
		builder.append(", deny_num=");
		builder.append(this.deny_num);
		builder.append(", is_treated=");
		builder.append(this.is_treated);
		builder.append(", return_no=");
		builder.append(this.return_no);
		builder.append(", goods_id_str=");
		builder.append(this.goods_id_str);
		builder.append(", batch_no=");
		builder.append(this.batch_no);
		builder.append(", back_goods_num=");
		builder.append(this.back_goods_num);
		builder.append("]");
		return builder.toString();
	}

}

