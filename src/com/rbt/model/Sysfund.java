/*
  
 
 * Package:com.rbt.model
 * FileName: Sysfund.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 平台总资金实体
 * @author 创建人 HXM
 * @date 创建日期 Sun Jul 13 17:17:59 CST 2014
 */
public class Sysfund implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String sysfund_id;
	public String getSysfund_id() {
		return sysfund_id;
	}
	public void setSysfund_id(String sysfund_id) {
		this.sysfund_id = sysfund_id;
	}
	
	private Double fund_num;
	public Double getFund_num() {
		return fund_num;
	}
	public void setFund_num(Double fund_num) {
		this.fund_num = fund_num;
	}
	
	private Double use_num;
	public Double getUse_num() {
		return use_num;
	}
	public void setUse_num(Double use_num) {
		this.use_num = use_num;
	}
	
	private Double freeze_num;
	public Double getFreeze_num() {
		return freeze_num;
	}
	public void setFreeze_num(Double freeze_num) {
		this.freeze_num = freeze_num;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysfund[");
		builder.append(", sysfund_id=");
		builder.append(this.sysfund_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", use_num=");
		builder.append(this.use_num);
		builder.append(", freeze_num=");
		builder.append(this.freeze_num);
		builder.append("]");
		return builder.toString();
	}

}

