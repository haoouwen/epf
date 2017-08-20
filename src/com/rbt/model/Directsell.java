/*
  
 
 * Package:com.rbt.model
 * FileName: Directsell.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 预售商品实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:27:41 CST 2014
 */
public class Directsell implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private Double earnest;
	public Double getEarnest() {
		return earnest;
	}
	public void setEarnest(Double earnest) {
		this.earnest = earnest;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private Double saleprice;
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double ladprices) {
		this.saleprice = ladprices;
	}
	
	private String saledesc;
	public String getSaledesc() {
		return saledesc;
	}
	public void setSaledesc(String saledesc) {
		this.saledesc = saledesc;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
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
	
	private String sort;
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	private String stock;
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String sale_title;
	public String getSale_title() {
		return sale_title;
	}
	public void setSale_title(String sale_title) {
		this.sale_title = sale_title;
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
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	
	private String tail_time;
	public String getTail_time() {
		return tail_time;
	}
	public void setTail_time(String tail_time) {
		this.tail_time = tail_time;
	}
	
	private String deliver_time;
	public String getDeliver_time() {
		return deliver_time;
	}
	public void setDeliver_time(String deliver_time) {
		this.deliver_time = deliver_time;
	}
	
	private String max_buy;
	
	public String getMax_buy() {
		return max_buy;
	}
	public void setMax_buy(String max_buy) {
		this.max_buy = max_buy;
	}
	
	private String stock_maxbuy;
	
	public String getStock_maxbuy() {
		return stock_maxbuy;
	}
	public void setStock_maxbuy(String stock_maxbuy) {
		this.stock_maxbuy = stock_maxbuy;
	}

	private String deposit_num;
	public String getDeposit_num() {
		return deposit_num;
	}
	public void setDeposit_num(String deposit_num) {
		this.deposit_num = deposit_num;
	}

	private String is_limit; 
	private String limit_num; 
	
	private String pretime;
	public String getPretime() {
		return pretime;
	}
	public void setPretime(String pretime) {
		this.pretime = pretime;
	}
	
	private String final_time;
	public String getFinal_time() {
		return final_time;
	}
	public void setFinal_time(String final_time) {
		this.final_time = final_time;
	}
	
	private String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Directsell[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", earnest=");
		builder.append(this.earnest);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", saleprice=");
		builder.append(this.saleprice);
		builder.append(", saledesc=");
		builder.append(this.saledesc);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", start_time=");
		builder.append(this.start_time);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", sort=");
		builder.append(this.sort);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", sale_title=");
		builder.append(this.sale_title);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append(", tail_time=");
		builder.append(this.tail_time);
		builder.append(", deliver_time=");
		builder.append(this.deliver_time);
		builder.append(", max_buy=");
		builder.append(this.max_buy);
		builder.append(", stock_maxbuy=");
		builder.append(this.stock_maxbuy);

		builder.append(", is_limit=");
		builder.append(this.is_limit);
		builder.append(", limit_num=");
		builder.append(this.limit_num);

		builder.append(", pretime=");
		builder.append(this.pretime);

		builder.append(", final_time=");
		builder.append(this.final_time);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append("]");
		return builder.toString();
	}
	public String getIs_limit() {
		return is_limit;
	}
	public void setIs_limit(String is_limit) {
		this.is_limit = is_limit;
	}
	public String getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(String limit_num) {
		this.limit_num = limit_num;
	}

}

