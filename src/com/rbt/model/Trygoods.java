/*
  
 
 * Package:com.rbt.model
 * FileName: Trygoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 试用商品实体
 * @author 创建人 CYC
 * @date 创建日期 Tue Jun 17 13:55:37 CST 2014
 */
public class Trygoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String try_id;
	public String getTry_id() {
		return try_id;
	}
	public void setTry_id(String try_id) {
		this.try_id = try_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String weibo_id;
	public String getWeibo_id() {
		return weibo_id;
	}
	public void setWeibo_id(String weibo_id) {
		this.weibo_id = weibo_id;
	}
	
	private String trynum;
	public String getTrynum() {
		return trynum;
	}
	public void setTrynum(String trynum) {
		this.trynum = trynum;
	}
	
	private String starttime;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	private String endtime;
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	private String wbcontent;
	public String getWbcontent() {
		return wbcontent;
	}
	public void setWbcontent(String wbcontent) {
		this.wbcontent = wbcontent;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String click_num;
	public String getClick_num() {
		return click_num;
	}
	public void setClick_num(String click_num) {
		this.click_num = click_num;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String try_type;
	public String getTry_type() {
		return try_type;
	}
	public void setTry_type(String try_type) {
		this.try_type = try_type;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String postage;
	public String getPostage() {
		return postage;
	}
	public void setPostage(String postage) {
		this.postage = postage;
	}
	
	private String surplus;
	public String getSurplus() {
		return surplus;
	}
	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	
	private String try_title;
	
	
	public String getTry_title() {
		return try_title;
	}
	public void setTry_title(String try_title) {
		this.try_title = try_title;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trygoods[");
		builder.append(", try_id=");
		builder.append(this.try_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", weibo_id=");
		builder.append(this.weibo_id);
		builder.append(", trynum=");
		builder.append(this.trynum);
		builder.append(", starttime=");
		builder.append(this.starttime);
		builder.append(", endtime=");
		builder.append(this.endtime);
		builder.append(", wbcontent=");
		builder.append(this.wbcontent);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", click_num=");
		builder.append(this.click_num);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", try_type=");
		builder.append(this.try_type);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", postage=");
		builder.append(this.postage);
		builder.append(", surplus=");
		builder.append(this.surplus);
		builder.append(", try_title=");
		builder.append(this.try_title);
		builder.append("]");
		return builder.toString();
	}

}

