/*
 
 * Package:com.rbt.model
 * FileName: Internationaltariff.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 国际物流实体
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 20 18:55:20 CST 2015
 */
public class Internationaltariff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ief_id;
	public String getIef_id() {
		return ief_id;
	}
	public void setIef_id(String ief_id) {
		this.ief_id = ief_id;
	}
	
	private String ief_price;
	public String getIef_price() {
		return ief_price;
	}
	public void setIef_price(String ief_price) {
		this.ief_price = ief_price;
	}
	
	private String ief_cubic;
	public String getIef_cubic() {
		return ief_cubic;
	}
	public void setIef_cubic(String ief_cubic) {
		this.ief_cubic = ief_cubic;
	}
	
	private String ief_overweight;
	public String getIef_overweight() {
		return ief_overweight;
	}
	public void setIef_overweight(String ief_overweight) {
		this.ief_overweight = ief_overweight;
	}
	
	private String ief_overweight_price;
	public String getIef_overweight_price() {
		return ief_overweight_price;
	}
	public void setIef_overweight_price(String ief_overweight_price) {
		this.ief_overweight_price = ief_overweight_price;
	}
	
	private String ief_name;
	public String getIef_name() {
		return ief_name;
	}
	public void setIef_name(String ief_name) {
		this.ief_name = ief_name;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Internationaltariff[");
		builder.append(", ief_id=");
		builder.append(this.ief_id);
		builder.append(", ief_price=");
		builder.append(this.ief_price);
		builder.append(", ief_cubic=");
		builder.append(this.ief_cubic);
		builder.append(", ief_overweight=");
		builder.append(this.ief_overweight);
		builder.append(", ief_overweight_price=");
		builder.append(this.ief_overweight_price);
		builder.append(", ief_name=");
		builder.append(this.ief_name);
		builder.append("]");
		return builder.toString();
	}

}

