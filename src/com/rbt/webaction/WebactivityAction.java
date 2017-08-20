package com.rbt.webaction;

import com.opensymphony.xwork2.Preparable;

public class WebactivityAction extends goodsModelAction implements Preparable {

	/**
	 * 商城活动
	 */
	private static final long serialVersionUID = -1903697972305654818L;
	/*******************实体层****************/
	/*********************集合******************/
	/*********************字段******************/
    public String tab_number;
	/**
	 * 活动页面：限时抢购,秒杀
	 */
	public String activity_snap() throws Exception {
			return goUrl("activity_"+tab_number);
	}
	public void prepare() throws Exception {
		
	}
	
	
	}
