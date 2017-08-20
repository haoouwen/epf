/*
 
 * Package:com.rbt.model
 * FileName: Redpacket.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 红包实体
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 20:50:54 CST 2015
 */
public class Redpacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String red_id;
	public String getRed_id() {
		return red_id;
	}
	public void setRed_id(String red_id) {
		this.red_id = red_id;
	}
	
	private String red_name;
	public String getRed_name() {
		return red_name;
	}
	public void setRed_name(String red_name) {
		this.red_name = red_name;
	}
	
	private String red_no;
	public String getRed_no() {
		return red_no;
	}
	public void setRed_no(String red_no) {
		this.red_no = red_no;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String money;
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	private String start_time;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	private String end_time;
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	private String member_level;
	public String getMember_level() {
		return member_level;
	}
	public void setMember_level(String member_level) {
		this.member_level = member_level;
	}
	
	private String red_num;
	public String getRed_num() {
		return red_num;
	}
	public void setRed_num(String red_num) {
		this.red_num = red_num;
	}
	private String is_show;
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	private String in_date;
	
	
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Redpacket[");
		builder.append(", red_id=");
		builder.append(this.red_id);
		builder.append(", red_name=");
		builder.append(this.red_name);
		builder.append(", red_no=");
		builder.append(this.red_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", money=");
		builder.append(this.money);
		builder.append(", start_time=");
		builder.append(this.start_time);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", member_level=");
		builder.append(this.member_level);
		builder.append(", red_num=");
		builder.append(this.red_num);
		builder.append(", is_show=");
		builder.append(this.is_show);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

