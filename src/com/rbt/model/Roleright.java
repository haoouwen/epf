/*
  
 
 * Package:com.rbt.model
 * FileName: Roleright.java 
 */
package com.rbt.model;

/**
 * @function 功能 权限表pojo类
 * @author 创建人 QJY
 * @date 创建日期 Jun 28, 2014
 */

public class Roleright {
	/*
	 * 权限唯一标识
	 */
	String right_id;
	/*
	 * 权限名称
	 */
	String right_name;
	/*
	 * 所属后台
	 */
	String syscode;
	/*
	 * 所属菜单
	 */
	String menu_attr;
	/*
	 * 权限url
	 */
	String url;
	/*
	 * 备注
	 */
	String remark;
	/*
	 * 操作提示信息
	 */
	String prompt;
	/*
	 * 操作时间
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * @return the right_id
	 */
	public String getRight_id() {
		return right_id;
	}

	/**
	 * @param right_id
	 *            the right_id to set
	 */
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}

	/**
	 * @return the right_name
	 */
	public String getRight_name() {
		return right_name;
	}

	/**
	 * @param right_name
	 *            the right_name to set
	 */
	public void setRight_name(String right_name) {
		this.right_name = right_name;
	}

	/**
	 * @return the syscode
	 */
	public String getSyscode() {
		return syscode;
	}

	/**
	 * @param syscode
	 *            the syscode to set
	 */
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	/**
	 * @return the menu_attr
	 */
	public String getMenu_attr() {
		return menu_attr;
	}

	/**
	 * @param menu_attr
	 *            the menu_attr to set
	 */
	public void setMenu_attr(String menu_attr) {
		this.menu_attr = menu_attr;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Roleright [right_id=");
		builder.append(this.right_id);
		builder.append(", right_name=");
		builder.append(this.right_name);
		builder.append(", syscode=");
		builder.append(this.syscode);
		builder.append(", menu_attr=");
		builder.append(this.menu_attr);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}
