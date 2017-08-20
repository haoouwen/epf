/*
 * Package:com.rbt.model
 * FileName: Goodsshare.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 晒单实体
 * @author 创建人 QJY
 * @date 创建日期 Wed Oct 29 14:36:43 CST 2014
 */
public class Goodsshare implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String eval_id;
	public String getEval_id(){
		return eval_id;
	}
	public void setEval_id(String eval_id){
		this.eval_id = eval_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String share_content;
	public String getShare_content() {
		return share_content;
	}
	public void setShare_content(String share_content) {
		this.share_content = share_content;
	}
	
	private String share_pic;
	public String getShare_pic() {
		return share_pic;
	}
	public void setShare_pic(String share_pic) {
		this.share_pic = share_pic;
	}
	
	private String share_date;
	public String getShare_date(){
		return share_date;
	}
	public void setShare_date(String share_date){
		this.share_date = share_date;
	}
	
	private String is_share;
	public String getIs_share(){
		return is_share;
	}
	public void setIs_share(String is_share){
		this.is_share=is_share;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsshare[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", share_content=");
		builder.append(this.share_content);
		builder.append(", share_pic=");
		builder.append(this.share_pic);
		builder.append(",share_date=");
		builder.append(this.share_date);
		builder.append(",is_share=");
		builder.append(this.is_share);
		builder.append("]");
		return builder.toString();
	}

}

