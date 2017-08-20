/*
  
 
 * Package:com.rbt.model
 * FileName: Tryapply.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 申请试用实体
 * @author 创建人 CYC
 * @date 创建日期 Sat Jul 12 09:25:36 CST 2014
 */
public class Tryapply implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tryapply_id;
	public String getTryapply_id() {
		return tryapply_id;
	}
	public void setTryapply_id(String tryapply_id) {
		this.tryapply_id = tryapply_id;
	}
	
	private String try_id;
	public String getTry_id() {
		return try_id;
	}
	public void setTry_id(String try_id) {
		this.try_id = try_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String add_id;
	public String getAdd_id() {
		return add_id;
	}
	public void setAdd_id(String add_id) {
		this.add_id = add_id;
	}
	
	private String report;
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	private String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String report_date;
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String try_type;
	private String goods_name;
	
	public String getTry_type() {
		return try_type;
	}
	public void setTry_type(String try_type) {
		this.try_type = try_type;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	
	private String cust_name;
	
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String weiboid;
	public String getWeiboid() {
		return weiboid;
	}
	public void setWebboid(String weiboid) {
		this.weiboid = weiboid;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tryapply[");
		builder.append(", try_id=");
		builder.append(this.try_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", add_id=");
		builder.append(this.add_id);
		builder.append(", report=");
		builder.append(this.report);
		builder.append(", comment=");
		builder.append(this.comment);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", report_date=");
		builder.append(this.report_date);
		builder.append(", status=");
		builder.append(this.status);
		builder.append(", try_type=");
		builder.append(this.try_type);
		builder.append(", goods_name=");
		builder.append(this.goods_name);
		builder.append(", tryapply_id=");
		builder.append(this.tryapply_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", weiboid=");
		builder.append(this.weiboid);
		builder.append("]");
		return builder.toString();
	}


}

