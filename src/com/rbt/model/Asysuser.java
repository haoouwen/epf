/*
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Asysuser.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 代理商实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Aug 05 14:52:52 CST 2015
 */
public class Asysuser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String nike_name;
	public String getNike_name() {
		return nike_name;
	}
	public void setNike_name(String nike_name) {
		this.nike_name = nike_name;
	}
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	private String user_type;
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	private String role_id;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	private String real_name;
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String loginnum;
	public String getLoginnum() {
		return loginnum;
	}
	public void setLoginnum(String loginnum) {
		this.loginnum = loginnum;
	}
	
	private String logintime;
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	
	private String loginip;
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	private String org_id;
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	private String grade;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	private String subjection;
	public String getSubjection() {
		return subjection;
	}
	public void setSubjection(String subjection) {
		this.subjection = subjection;
	}
	
	private String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	private String com_name;
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String level;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	private String cost;
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	private String spread;
	public String getSpread() {
		return spread;
	}
	public void setSpread(String spread) {
		this.spread = spread;
	}
	
	private String cust_num;
	public String getCust_num() {
		return cust_num;
	}
	public void setCust_num(String cust_num) {
		this.cust_num = cust_num;
	}
	
	private String cust_amount;
	public String getCust_amount() {
		return cust_amount;
	}
	public void setCust_amount(String cust_amount) {
		this.cust_amount = cust_amount;
	}
	
	private String agent_type;
	public String getAgent_type() {
		return agent_type;
	}
	public void setAgent_type(String agent_type) {
		this.agent_type = agent_type;
	}
	private String address;	
	private String cellphone;	
	private String phone;	
	//门店名称
	private String store_name;
	//门店照片
	private String store_img;
	//门店经营时间
	private String store_opentime;
	//公交路线
	private String bus_line;
	//地铁路线
	private String railway_line;
	//停车服务
	private String parking_service;
	private String detai_area_attr;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_opentime() {
		return store_opentime;
	}
	public void setStore_opentime(String store_opentime) {
		this.store_opentime = store_opentime;
	}
	public String getBus_line() {
		return bus_line;
	}
	public void setBus_line(String bus_line) {
		this.bus_line = bus_line;
	}
	public String getRailway_line() {
		return railway_line;
	}
	public void setRailway_line(String railway_line) {
		this.railway_line = railway_line;
	}
	public String getParking_service() {
		return parking_service;
	}
	public void setParking_service(String parking_service) {
		this.parking_service = parking_service;
	}
	public String getStore_img() {
		return store_img;
	}
	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}
	
	private String store_servce;//门店服务
	public String getStore_servce(){
		return store_servce;
	}
	public void setStore_servce(String store_servce){
		this.store_servce=store_servce;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Asysuser[");
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", nike_name=");
		builder.append(this.nike_name);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", user_type=");
		builder.append(this.user_type);
		builder.append(", role_id=");
		builder.append(this.role_id);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", loginnum=");
		builder.append(this.loginnum);
		builder.append(", logintime=");
		builder.append(this.logintime);
		builder.append(", loginip=");
		builder.append(this.loginip);
		builder.append(", state=");
		builder.append(this.state);
		builder.append(", org_id=");
		builder.append(this.org_id);
		builder.append(", grade=");
		builder.append(this.grade);
		builder.append(", subjection=");
		builder.append(this.subjection);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append(", com_name=");
		builder.append(this.com_name);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", level=");
		builder.append(this.level);
		builder.append(", cost=");
		builder.append(this.cost);
		builder.append(", spread=");
		builder.append(this.spread);
		builder.append(", cust_num=");
		builder.append(this.cust_num);
		builder.append(", cust_amount=");
		builder.append(this.cust_amount);
		builder.append(", agent_type=");
		builder.append(this.agent_type);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", store_name=");
		builder.append(this.store_name);
		builder.append(", store_img=");
		builder.append(this.store_img);
		builder.append(", store_opentime=");
		builder.append(this.store_opentime);
		builder.append(", bus_line=");
		builder.append(this.bus_line);
		builder.append(", railway_line=");
		builder.append(this.railway_line);
		builder.append(", parking_service=");
		builder.append(this.parking_service);
		builder.append(", detai_area_attr=");
		builder.append(this.detai_area_attr);
		builder.append(", store_servce=");
		builder.append(this.store_servce);
		builder.append("]");
		return builder.toString();
	}
	public String getDetai_area_attr() {
		return detai_area_attr;
	}
	public void setDetai_area_attr(String detai_area_attr) {
		this.detai_area_attr = detai_area_attr;
	}


}

