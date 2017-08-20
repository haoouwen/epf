/*
 * Package:com.rbt.model
 * FileName: Rechargeablecard.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 充值卡实体
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 11:07:29 CST 2015
 */
public class Rechargeablecard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cardid;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
	private String cardname;
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	private String cardmoney;
	public String getCardmoney() {
		return cardmoney;
	}
	public void setCardmoney(String cardmoney) {
		this.cardmoney = cardmoney;
	}
	
	private String cardnum;
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	
	private String cardused;
	public String getCardused() {
		return cardused;
	}
	public void setCardused(String cardused) {
		this.cardused = cardused;
	}
	
	private String carddate;
	public String getCarddate() {
		return carddate;
	}
	public void setCarddate(String carddate) {
		this.carddate = carddate;
	}
	
	private String cardstate;
	public String getCardstate() {
		return cardstate;
	}
	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rechargeablecard[");
		builder.append(", cardid=");
		builder.append(this.cardid);
		builder.append(", cardname=");
		builder.append(this.cardname);
		builder.append(", cardmoney=");
		builder.append(this.cardmoney);
		builder.append(", cardnum=");
		builder.append(this.cardnum);
		builder.append(", cardused=");
		builder.append(this.cardused);
		builder.append(", carddate=");
		builder.append(this.carddate);
		builder.append(", cardstate=");
		builder.append(this.cardstate);
		builder.append("]");
		return builder.toString();
	}

}

