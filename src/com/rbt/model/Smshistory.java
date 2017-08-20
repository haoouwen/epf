/*
  
 
 * Package:com.rbt.model
 * FileName: Smshistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Smshistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String cellphones;
	public String getCellphones() {
		return cellphones;
	}
	public void setCellphones(String cellphones) {
		this.cellphones = cellphones;
	}
	
	private String celltitle;
	public String getCelltitle() {
		return celltitle;
	}
	public void setCelltitle(String celltitle) {
		this.celltitle = celltitle;
	}
	
	private String cellnum;
	public String getCellnum() {
		return cellnum;
	}
	public void setCellnum(String cellnum) {
		this.cellnum = cellnum;
	}
	
	private String cellname;
	public String getCellname() {
		return cellname;
	}
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String cell_code;
	public String getCell_code() {
		return cell_code;
	}
	public void setCell_code(String cell_code) {
		this.cell_code = cell_code;
	}
	
	private String send_date;
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Smshistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cellphones=");
		builder.append(this.cellphones);
		builder.append(", celltitle=");
		builder.append(this.celltitle);
		builder.append(", cellnum=");
		builder.append(this.cellnum);
		builder.append(", cellname=");
		builder.append(this.cellname);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", cell_code=");
		builder.append(this.cell_code);
		builder.append(", send_date=");
		builder.append(this.send_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}

