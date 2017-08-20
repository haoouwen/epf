/*
  
 
 * Package:com.rbt.model
 * FileName: Area.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String area_id;
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	
	private String area_name;
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	private String up_area_id;
	public String getUp_area_id() {
		return up_area_id;
	}
	public void setUp_area_id(String up_area_id) {
		this.up_area_id = up_area_id;
	}
	
	private String area_level;
	public String getArea_level() {
		return area_level;
	}
	public void setArea_level(String area_level) {
		this.area_level = area_level;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String is_city;
	public String getIs_city() {
		return is_city;
	}
	public void setIs_city(String is_city) {
		this.is_city = is_city;
	}
	
	private String seotitle;
	public String getSeotitle() {
		return seotitle;
	}
	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}
	
	private String seokeyword;
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	
	private String seodesc;
	public String getSeodesc() {
		return seodesc;
	}
	public void setSeodesc(String seodesc) {
		this.seodesc = seodesc;
	}
	
	private String area_have;
	public String getArea_have() {
		return area_have;
	}
	public void setArea_have(String area_have) {
		this.area_have = area_have;
	}
	private String area_number;
	
	
	public String getArea_number() {
		return area_number;
	}
	public void setArea_number(String area_number) {
		this.area_number = area_number;
	}
	private String kjtareaid;
	public String getKjtareaid() {
		return kjtareaid;
	}
	public void setKjtareaid(String kjtareaid) {
		this.kjtareaid = kjtareaid;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Area[");
		builder.append(", area_id=");
		builder.append(this.area_id);
		builder.append(", area_name=");
		builder.append(this.area_name);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", up_area_id=");
		builder.append(this.up_area_id);
		builder.append(", area_level=");
		builder.append(this.area_level);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", is_city=");
		builder.append(this.is_city);
		builder.append(", seotitle=");
		builder.append(this.seotitle);
		builder.append(", seokeyword=");
		builder.append(this.seokeyword);
		builder.append(", seodesc=");
		builder.append(this.seodesc);
		builder.append(", area_have=");
		builder.append(this.area_have);
		builder.append(", area_number=");
		builder.append(this.area_number);
		builder.append("]");
		return builder.toString();
	}

}

