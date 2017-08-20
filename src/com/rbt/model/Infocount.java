/*
  
 
 * Package:com.rbt.model
 * FileName: Infocount.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 数据统计实体
 * @author 创建人 HXK
 * @date 创建日期 Wed Jan 30 10:28:21 CST 2014
 */
public class Infocount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String infocount_id;
	public String getInfocount_id() {
		return infocount_id;
	}
	public void setInfocount_id(String infocount_id) {
		this.infocount_id = infocount_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Infocount[");
		builder.append(", infocount_id=");
		builder.append(this.infocount_id);
		builder.append("]");
		return builder.toString();
	}

}

