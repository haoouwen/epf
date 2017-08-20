/*
 * Package:com.rbt.model
 * FileName: Kjtrecall.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 跨境通回调表实体
 * @author 创建人 CYC
 * @date 创建日期 Fri Sep 18 16:21:49 CST 2015
 */
public class Kjtrecall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String sosysno;
	public String getSosysno() {
		return sosysno;
	}
	public void setSosysno(String sosysno) {
		this.sosysno = sosysno;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String tatal_amount;
	public String getTatal_amount() {
		return tatal_amount;
	}
	public void setTatal_amount(String tatal_amount) {
		this.tatal_amount = tatal_amount;
	}
	
	private String taxes;
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	
	private String ship_free;
	public String getShip_free() {
		return ship_free;
	}
	public void setShip_free(String ship_free) {
		this.ship_free = ship_free;
	}
	
	private String merchantorderid;
	public String getMerchantorderid() {
		return merchantorderid;
	}
	public void setMerchantorderid(String merchantorderid) {
		this.merchantorderid = merchantorderid;
	}
	
	private String productamount;
	public String getProductamount() {
		return productamount;
	}
	public void setProductamount(String productamount) {
		this.productamount = productamount;
	}
	
	private String taxamount;
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	
	private String shippingamount;
	public String getShippingamount() {
		return shippingamount;
	}
	public void setShippingamount(String shippingamount) {
		this.shippingamount = shippingamount;
	}
	
	private String sostatus;
	public String getSostatus() {
		return sostatus;
	}
	public void setSostatus(String sostatus) {
		this.sostatus = sostatus;
	}
	private String kjtdate;
	public String getKjtdate() {
		return kjtdate;
	}
	public void setKjtdate(String kjtdate) {
		this.kjtdate = kjtdate;
	}

    private String purchasing;
	public String getPurchasing() {
		return purchasing;
	}
	public void setPurchasing(String purchasing) {
		this.purchasing = purchasing;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kjtrecall[");
		builder.append(", sosysno=");
		builder.append(this.sosysno);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", tatal_amount=");
		builder.append(this.tatal_amount);
		builder.append(", taxes=");
		builder.append(this.taxes);
		builder.append(", ship_free=");
		builder.append(this.ship_free);
		builder.append(", merchantorderid=");
		builder.append(this.merchantorderid);
		builder.append(", productamount=");
		builder.append(this.productamount);
		builder.append(", taxamount=");
		builder.append(this.taxamount);
		builder.append(", shippingamount=");
		builder.append(this.shippingamount);
		builder.append(", sostatus=");
		builder.append(this.sostatus);
		builder.append(", kjtdate=");
		builder.append(this.kjtdate);
		builder.append(", purchasing=");
		builder.append(this.purchasing);
		builder.append("]");
		return builder.toString();
	}

}

