/*
  
 
 * Package:com.rbt.model
 * FileName: Rate.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Rate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String rate_id;
	public String getRate_id() {
		return rate_id;
	}
	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}
	
	private String rate_name;
	public String getRate_name() {
		return rate_name;
	}
	public void setRate_name(String rate_name) {
		this.rate_name = rate_name;
	}
	private String rate_unit;
	public String getRate_unit() {
		return rate_unit;
	}
	public void setRate_unit(String rate_unit) {
		this.rate_unit = rate_unit;
	}
	
	private String rate_mark;
	public String getRate_mark() {
		return rate_mark;
	}
	public void setRate_mark(String rate_mark) {
		this.rate_mark = rate_mark;
	}
	
	private String rate_img;
	public String getRate_img() {
		return rate_img;
	}
	public void setRate_img(String rate_img) {
		this.rate_img = rate_img;
	}
	
	private String enables;
	public String getEnables() {
		return enables;
	}
	public void setEnables(String enables) {
		this.enables = enables;
	}
	
	private Double exchangerate;
	public Double getExchangerate() {
		return exchangerate;
	}
	public void setExchangerate(Double exchangerate) {
		this.exchangerate = exchangerate;
	}
	
	private String endefault;
	public String getEndefault() {
		return endefault;
	}
	public void setEndefault(String endefault) {
		this.endefault = endefault;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rate[");
		builder.append(", rate_id=");
		builder.append(this.rate_id);
		builder.append(", rate_name=");
		builder.append(this.rate_name);
		builder.append(", rate_mark=");
		builder.append(this.rate_mark);
		builder.append(", rate_img=");
		builder.append(this.rate_img);
		builder.append(", rate_unit=");
		builder.append(this.rate_unit);
		builder.append(", enables=");
		builder.append(this.enables);
		builder.append(", exchangerate=");
		builder.append(this.exchangerate);
		builder.append(", endefault=");
		builder.append(this.endefault);
		builder.append("]");
		return builder.toString();
	}
	

}

