/*
  
 
 * Package:com.rbt.model
 * FileName: Goodseval.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Goodseval implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String g_eval;
	public String getG_eval() {
		return g_eval;
	}
	public void setG_eval(String g_eval) {
		this.g_eval = g_eval;
	}
	
	private String g_comment;
	public String getG_comment() {
		return g_comment;
	}
	public void setG_comment(String g_comment) {
		this.g_comment = g_comment;
	}
	
	private String is_two;
	public String getIs_two() {
		return is_two;
	}
	public void setIs_two(String is_two) {
		this.is_two = is_two;
	}
	
	private String eval_date;
	public String getEval_date() {
		return eval_date;
	}
	public void setEval_date(String eval_date) {
		this.eval_date = eval_date;
	}
	
	private String explan_cust_id;
	public String getExplan_cust_id() {
		return explan_cust_id;
	}
	public void setExplan_cust_id(String explan_cust_id) {
		this.explan_cust_id = explan_cust_id;
	}
	
	private String explan_man;
	public String getExplan_man() {
		return explan_man;
	}
	public void setExplan_man(String explan_man) {
		this.explan_man = explan_man;
	}
	
	private String explan_content;
	public String getExplan_content() {
		return explan_content;
	}
	public void setExplan_content(String explan_content) {
		this.explan_content = explan_content;
	}
	
	private String explan_date;
	public String getExplan_date() {
		return explan_date;
	}
	public void setExplan_date(String explan_date) {
		this.explan_date = explan_date;
	}
	
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	 private String e_type;
	
	public String getE_type() {
		return e_type;
	}
	public void setE_type(String e_type) {
		this.e_type = e_type;
	}
	private String  order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String is_img;
	public String getIs_img() {
		return is_img;
	}
	public void setIs_img(String is_img) {
		this.is_img = is_img;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodseval[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", g_eval=");
		builder.append(this.g_eval);
		builder.append(", g_comment=");
		builder.append(this.g_comment);
		builder.append(", is_two=");
		builder.append(this.is_two);
		builder.append(", eval_date=");
		builder.append(this.eval_date);
		builder.append(", explan_cust_id=");
		builder.append(this.explan_cust_id);
		builder.append(", explan_man=");
		builder.append(this.explan_man);
		builder.append(", explan_content=");
		builder.append(this.explan_content);
		builder.append(", explan_date=");
		builder.append(this.explan_date);
		builder.append(", is_enable=");
		builder.append(this.is_enable);
		builder.append(", e_type=");
		builder.append(this.e_type);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", is_img=");
		builder.append(this.is_img);
		builder.append("]");
		return builder.toString();
	}

}

