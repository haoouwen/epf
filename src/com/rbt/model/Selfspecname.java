/*
  
 
 * Package:com.rbt.model
 * FileName: Selfsepcname.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 自定义规格名称实体
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:32:16 CST 2014
 */
public class Selfspecname implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String spec_serial_id;
	public String getSpec_serial_id() {
		return spec_serial_id;
	}
	public void setSpec_serial_id(String spec_serial_id) {
		this.spec_serial_id = spec_serial_id;
	}
	
	private String spec_code;
	public String getSpec_code() {
		return spec_code;
	}
	public void setSpec_code(String spec_code) {
		this.spec_code = spec_code;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Selfsepcname[");
		builder.append(", spec_serial_id=");
		builder.append(this.spec_serial_id);
		builder.append(", spec_code=");
		builder.append(this.spec_code);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append("]");
		return builder.toString();
	}

}

