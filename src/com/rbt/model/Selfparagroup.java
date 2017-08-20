/*
  
 
 * Package:com.rbt.model
 * FileName: Selfparagroup.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录自定义参数组信息实体
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 13:36:38 CST 2014
 */
public class Selfparagroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String slef_para_group_id;
	public String getSlef_para_group_id() {
		return slef_para_group_id;
	}
	public void setSlef_para_group_id(String slef_para_group_id) {
		this.slef_para_group_id = slef_para_group_id;
	}
	
	private String para_group_id;
	public String getPara_group_id() {
		return para_group_id;
	}
	public void setPara_group_id(String para_group_id) {
		this.para_group_id = para_group_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Selfparagroup[");
		builder.append(", slef_para_group_id=");
		builder.append(this.slef_para_group_id);
		builder.append(", para_group_id=");
		builder.append(this.para_group_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}

