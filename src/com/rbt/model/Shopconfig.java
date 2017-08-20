/*
  
 
 * Package:com.rbt.model
 * FileName: Shopconfig.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Shopconfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String shop_id;
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	private String shop_logo;
	public String getShop_logo() {
		return shop_logo;
	}
	public void setShop_logo(String shop_logo) {
		this.shop_logo = shop_logo;
	}
	
	private String header_img;
	public String getHeader_img() {
		return header_img;
	}
	public void setHeader_img(String header_img) {
		this.header_img = header_img;
	}
	
	private String back_img;
	public String getBack_img() {
		return back_img;
	}
	public void setBack_img(String back_img) {
		this.back_img = back_img;
	}
	
	private String back_color;
	public String getBack_color() {
		return back_color;
	}
	public void setBack_color(String back_color) {
		this.back_color = back_color;
	}
	
	private String shop_site;
	public String getShop_site() {
		return shop_site;
	}
	public void setShop_site(String shop_site) {
		this.shop_site = shop_site;
	}
	
	private String shop_intro;
	public String getShop_intro() {
		return shop_intro;
	}
	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}
	
	private String busi_range;
	public String getBusi_range() {
		return busi_range;
	}
	public void setBusi_range(String busi_range) {
		this.busi_range = busi_range;
	}
	
	private String busi_mode;
	public String getBusi_mode() {
		return busi_mode;
	}
	public void setBusi_mode(String busi_mode) {
		this.busi_mode = busi_mode;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String contant_man;
	public String getContant_man() {
		return contant_man;
	}
	public void setContant_man(String contant_man) {
		this.contant_man = contant_man;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	private String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	private String alliwang;
	public String getAlliwang() {
		return alliwang;
	}
	public void setAlliwang(String alliwang) {
		this.alliwang = alliwang;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_colse;
	public String getIs_colse() {
		return is_colse;
	}
	public void setIs_colse(String is_colse) {
		this.is_colse = is_colse;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String template_code;
	public String getTemplate_code() {
		return template_code;
	}
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}
	public String sale_time;
	public String getSale_time() {
		return sale_time;
	}
	public void setSale_time(String sale_time) {
		this.sale_time = sale_time;
	}
	public String shop_code;
	public String getShop_code() {
		return shop_code;
	}
	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}
	
	public String radom_no;
	public String getRadom_no() {
		return radom_no;
	}
	public void setRadom_no(String radom_no) {
		this.radom_no = radom_no;
	}
	private String cfg_imagemarktype;
	private String cfg_imagemark;
	private String cfg_imagemarktext;
	private String cfg_imagemaricon;
	
	private String postcode;
	
	public String getCfg_imagemarktype() {
		return cfg_imagemarktype;
	}
	public void setCfg_imagemarktype(String cfg_imagemarktype) {
		this.cfg_imagemarktype = cfg_imagemarktype;
	}
	public String getCfg_imagemark() {
		return cfg_imagemark;
	}
	public void setCfg_imagemark(String cfg_imagemark) {
		this.cfg_imagemark = cfg_imagemark;
	}
	public String getCfg_imagemarktext() {
		return cfg_imagemarktext;
	}
	public void setCfg_imagemarktext(String cfg_imagemarktext) {
		this.cfg_imagemarktext = cfg_imagemarktext;
	}
	public String getCfg_imagemaricon() {
		return cfg_imagemaricon;
	}
	public void setCfg_imagemaricon(String cfg_imagemaricon) {
		this.cfg_imagemaricon = cfg_imagemaricon;
	}
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shopconfig[");
		builder.append(", shop_id=");
		builder.append(this.shop_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", shop_name=");
		builder.append(this.shop_name);
		builder.append(", shop_logo=");
		builder.append(this.shop_logo);
		builder.append(", header_img=");
		builder.append(this.header_img);
		builder.append(", back_img=");
		builder.append(this.back_img);
		builder.append(", back_color=");
		builder.append(this.back_color);
		builder.append(", shop_site=");
		builder.append(this.shop_site);
		builder.append(", shop_intro=");
		builder.append(this.shop_intro);
		builder.append(", busi_range=");
		builder.append(this.busi_range);
		builder.append(", busi_mode=");
		builder.append(this.busi_mode);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", contant_man=");
		builder.append(this.contant_man);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", alliwang=");
		builder.append(this.alliwang);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", is_colse=");
		builder.append(this.is_colse);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", template_code=");
		builder.append(this.template_code);
		builder.append(", sale_time=");
		builder.append(this.sale_time);
		builder.append(", shop_code=");
		builder.append(this.shop_code);
		builder.append(", radom_no=");
		builder.append(this.radom_no);
		builder.append(", cfg_imagemarktype=");
		builder.append(this.cfg_imagemarktype);
		builder.append(", cfg_imagemark=");
		builder.append(this.cfg_imagemark);
		builder.append(", cfg_imagemarktext=");
		builder.append(this.cfg_imagemarktext);
		builder.append(", cfg_imagemaricon=");
		builder.append(this.cfg_imagemaricon);
		builder.append(", postcode=");
		builder.append(this.postcode);
		builder.append("]");
		return builder.toString();
	}

}

