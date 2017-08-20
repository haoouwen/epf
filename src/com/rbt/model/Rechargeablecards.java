/*
 * Package:com.rbt.model
 * FileName: Rechargeablecards.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 充值卡实体
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 14:01:50 CST 2015
 */
public class Rechargeablecards implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cardid;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
	private String cardskey;
	public String getCardskey() {
		return cardskey;
	}
	public void setCardskey(String cardskey) {
		this.cardskey = cardskey;
	}
	
	private String cardsmoney;
	public String getCardsmoney() {
		return cardsmoney;
	}
	public void setCardsmoney(String cardsmoney) {
		this.cardsmoney = cardsmoney;
	}
	
	private String cardsdate;
	public String getCardsdate() {
		return cardsdate;
	}
	public void setCardsdate(String cardsdate) {
		this.cardsdate = cardsdate;
	}
	
	private String cardsstate;
	public String getCardsstate() {
		return cardsstate;
	}
	public void setCardsstate(String cardsstate) {
		this.cardsstate = cardsstate;
	}
	
	private String cardsuseddate;
	public String getCardsuseddate() {
		return cardsuseddate;
	}
	public void setCardsuseddate(String cardsuseddate) {
		this.cardsuseddate = cardsuseddate;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rechargeablecards[");
		builder.append(", cardid=");
		builder.append(this.cardid);
		builder.append(", cardskey=");
		builder.append(this.cardskey);
		builder.append(", cardsmoney=");
		builder.append(this.cardsmoney);
		builder.append(", cardsdate=");
		builder.append(this.cardsdate);
		builder.append(", cardsstate=");
		builder.append(this.cardsstate);
		builder.append(", cardsuseddate=");
		builder.append(this.cardsuseddate);
		builder.append("]");
		return builder.toString();
	}

}

