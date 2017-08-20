/*
  
 
 * Package:com.rbt.model
 * FileName: Advinfo.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Advinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String adv_id;
	public String getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}
	
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	
	private String adv_name;
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	
	private String adv_desc;
	public String getAdv_desc() {
		return adv_desc;
	}
	public void setAdv_desc(String adv_desc) {
		this.adv_desc = adv_desc;
	}
	
	private String keyword;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	private String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String adv_code;
	public String getAdv_code() {
		return adv_code;
	}
	public void setAdv_code(String adv_code) {
		this.adv_code = adv_code;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String flash_url;
	public String getFlash_url() {
		return flash_url;
	}
	public void setFlash_url(String flash_url) {
		this.flash_url = flash_url;
	}
	
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String start_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	private String end_date;
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String adv_state;
	public String getAdv_state() {
		return adv_state;
	}
	public void setAdv_state(String adv_state) {
		this.adv_state = adv_state;
	}
	
	private String iscount;
	public String getIscount() {
		return iscount;
	}
	public void setIscount(String iscount) {
		this.iscount = iscount;
	}
	
	private String addnum;
	public String getAddnum() {
		return addnum;
	}
	public void setAddnum(String addnum) {
		this.addnum = addnum;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String bg_color;
	public String getBg_color() {
		return bg_color;
	}
	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Advinfo[");
		builder.append(", adv_id=");
		builder.append(this.adv_id);
		builder.append(", pos_id=");
		builder.append(this.pos_id);
		builder.append(", adv_name=");
		builder.append(this.adv_name);
		builder.append(", adv_desc=");
		builder.append(this.adv_desc);
		builder.append(", keyword=");
		builder.append(this.keyword);
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", adv_code=");
		builder.append(this.adv_code);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", flash_url=");
		builder.append(this.flash_url);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", adv_state=");
		builder.append(this.adv_state);
		builder.append(", iscount=");
		builder.append(this.iscount);
		builder.append(", addnum=");
		builder.append(this.addnum);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", bg_color=");
		builder.append(this.bg_color);
		builder.append("]");
		return builder.toString();
	}

}

