/*
  
 
 * Package:com.rbt.model
 * FileName: Filterword.java 
 */
package com.rbt.model;

/**
 * @function 功能 敏感字表pojo类
 * @author  创建人 QJY
 * @date  创建日期 Jul 5, 2014 9:07:04 AM
 */

public class Filterword {
	/*
	 * 敏感字唯一标识
	 */
	String word_id;
	/*
	 * 关键字名称
	 */
	String name;
	/*
	 * 替换子名称
	 */
	String rep_name;

	/**
	 * @return the word_id
	 */
	public String getWord_id() {
		return word_id;
	}

	/**
	 * @param word_id
	 *            the word_id to set
	 */
	public void setWord_id(String word_id) {
		this.word_id = word_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rep_name
	 */
	public String getRep_name() {
		return rep_name;
	}

	/**
	 * @param rep_name
	 *            the rep_name to set
	 */
	public void setRep_name(String rep_name) {
		this.rep_name = rep_name;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Filterword [word_id=");
		builder.append(this.word_id);
		builder.append(", name=");
		builder.append(this.name);
		builder.append(", rep_name=");
		builder.append(this.rep_name);
		builder.append("]");
		return builder.toString();
	}
}
