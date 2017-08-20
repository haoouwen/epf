/*
  
 
 * Package:com.rbt.model
 * FileName: Goodsattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商品属性信息实体
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 29 14:31:37 CST 2014
 */
public class Goodsattr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String goods_item;
	public String getGoods_item() {
		return goods_item;
	}
	public void setGoods_item(String goods_item) {
		this.goods_item = goods_item;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String specstr;
	public String getSpecstr() {
		return specstr;
	}
	public void setSpecstr(String specstr) {
		this.specstr = specstr;
	}
	
	private Double market_price;
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	
	private Double sale_price;
	public Double getSale_price() {
		return sale_price;
	}
	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}
	
	private Double cost_price;
	public Double getCost_price() {
		return cost_price;
	}
	public void setCost_price(Double cost_price) {
		this.cost_price = cost_price;
	}
	
	private String stock;
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	private String up_goods;
	public String getUp_goods() {
		return up_goods;
	}
	public void setUp_goods(String up_goods) {
		this.up_goods = up_goods;
	}
	
	private String sale_num;
	public String getSale_num() {
		return sale_num;
	}
	public void setSale_num(String sale_num) {
		this.sale_num = sale_num;
	}
	
	//物流体积
	private String volume;
	//物流重量
	private String logsweight;
	//税费
	private String tax_price;
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getLogsweight() {
		return logsweight;
	}
	public void setLogsweight(String logsweight) {
		this.logsweight = logsweight;
	}
	public String getTax_price() {
		return tax_price;
	}
	public void setTax_price(String tax_price) {
		this.tax_price = tax_price;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsattr[");
		builder.append(", goods_item=");
		builder.append(this.goods_item);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", specstr=");
		builder.append(this.specstr);
		builder.append(", market_price=");
		builder.append(this.market_price);
		builder.append(", sale_price=");
		builder.append(this.sale_price);
		builder.append(", cost_price=");
		builder.append(this.cost_price);
		builder.append(", stock=");
		builder.append(this.stock);
		builder.append(", up_goods=");
		builder.append(this.up_goods);
		builder.append(", sale_num=");
		builder.append(this.sale_num);
		builder.append(", volume=");
		builder.append(this.volume);
		builder.append(", logsweight=");
		builder.append(this.logsweight);
		builder.append(", tax_price=");
		builder.append(this.tax_price);		
		builder.append("]");
		return builder.toString();
	}

}

