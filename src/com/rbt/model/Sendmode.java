/*
  
 
 * Package:com.rbt.model
 * FileName: Sendmode.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sendmode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String smode_name;
	public String getSmode_name() {
		return smode_name;
	}
	public void setSmode_name(String smode_name) {
		this.smode_name = smode_name;
	}
	
	private String smode_desc;
	public String getSmode_desc() {
		return smode_desc;
	}
	public void setSmode_desc(String smode_desc) {
		this.smode_desc = smode_desc;
	}
	
	private String is_insured;
	public String getIs_insured() {
		return is_insured;
	}
	public void setIs_insured(String is_insured) {
		this.is_insured = is_insured;
	}
	
	private String rate;
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	private Double mix_insured;
	public Double getMix_insured() {
		return mix_insured;
	}
	public void setMix_insured(Double mix_insured) {
		this.mix_insured = mix_insured;
	}
	
	private Double max_insured;
	public Double getMax_insured() {
		return max_insured;
	}
	public void setMax_insured(Double max_insured) {
		this.max_insured = max_insured;
	}
	
	private String reach_pay;
	public String getReach_pay() {
		return reach_pay;
	}
	public void setReach_pay(String reach_pay) {
		this.reach_pay = reach_pay;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String smode_id;
	public String getSmode_id() {
		return smode_id;
	}
	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	private String print_mode;
	public String getPrint_mode() {
		return print_mode;
	}
	public void setPrint_mode(String print_mode) {
		this.print_mode = print_mode;
	}
	private String img_path;
	
	
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	private String sendlength;
	
	public String getSendlength() {
		return sendlength;
	}
	public void setSendlength(String sendlength) {
		this.sendlength = sendlength;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sendmode[");
		builder.append(", smode_name=");
		builder.append(this.smode_name);
		builder.append(", smode_desc=");
		builder.append(this.smode_desc);
		builder.append(", is_insured=");
		builder.append(this.is_insured);
		builder.append(", rate=");
		builder.append(this.rate);
		builder.append(", mix_insured=");
		builder.append(this.mix_insured);
		builder.append(", max_insured=");
		builder.append(this.max_insured);
		builder.append(", reach_pay=");
		builder.append(this.reach_pay);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", smode_id=");
		builder.append(this.smode_id);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", print_mode=");
		builder.append(this.print_mode);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", sendlength=");
		builder.append(this.sendlength);
		builder.append("]");
		return builder.toString();
	}

}

