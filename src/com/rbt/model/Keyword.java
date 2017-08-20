/*
  
 
 * Package:com.rbt.model
 * FileName: Keyword.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  关键字pojo类		
 * @author  创建人 CYC
 * @date  创建日期  July 6, 2014
*/
public class Keyword  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 6774355621991793528L;
	/*
	 * 关键字唯一标识
	 */
	String key_id;
	/*
	 * 关键字名称
	 */
	String key_name;
	/*
	 * 拼音
	 */
	String en_name;
	/*
	 * 关键字参照类型
	 */
	String module_type;
	/*
	 * 关键字搜索频率
	 */
	String num;
	

	public String getKey_id() {
		return key_id;
	}

	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	private String is_show;
	private String in_date;
	private String m_ip;

	public String getIs_show() {
		return is_show;
	}

	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getM_ip() {
		return m_ip;
	}

	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [key_id=");
		builder.append(this.key_id);
		builder.append(", key_name=");
		builder.append(this.key_name);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", num=");
		builder.append(this.num);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", is_show=");
		builder.append(this.is_show);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", m_ip=");
		builder.append(this.m_ip);
		builder.append("]");
		return builder.toString();
	}
	
}
