/*
  
 
 * Package:com.rbt.model
 * FileName: Topdomain.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Topdomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String domain_url;
	public String getDomain_url() {
		return domain_url;
	}
	public void setDomain_url(String domain_url) {
		this.domain_url = domain_url;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private String go_url;
	public String getGo_url() {
		return go_url;
	}
	public void setGo_url(String go_url) {
		this.go_url = go_url;
	}
	
	private String domain_type;
	public String getDomain_type() {
		return domain_type;
	}
	public void setDomain_type(String domain_type) {
		this.domain_type = domain_type;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topdomain[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", domain_url=");
		builder.append(this.domain_url);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", go_url=");
		builder.append(this.go_url);
		builder.append(", domain_type=");
		builder.append(this.domain_type);
		builder.append("]");
		return builder.toString();
	}

}

