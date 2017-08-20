/*
 * Package:com.rbt.model
 * FileName: Messagepush.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能 消息推送实体
 * @author 创建人 HXK
 * @date 创建日期 Thu Jul 07 14:40:46 CST 2016
 */
public class Messagepush implements Serializable {

	private static final long serialVersionUID = 1L;
	private String msgpush_id;
	public String getMsgpush_id() {
		return msgpush_id;
	}
	public void setMsgpush_id(String msgpush_id) {
		this.msgpush_id = msgpush_id;
	}
	
	private String msgpush_name;
	public String getMsgpush_name() {
		return msgpush_name;
	}
	public void setMsgpush_name(String msgpush_name) {
		this.msgpush_name = msgpush_name;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String mp_abstract;
	public String getMp_abstract() {
		return mp_abstract;
	}
	public void setMp_abstract(String mp_abstract) {
		this.mp_abstract = mp_abstract;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String ios_state;
	private String android_state;
	
	public String getIos_state() {
		return ios_state;
	}
	public void setIos_state(String ios_state) {
		this.ios_state = ios_state;
	}
	public String getAndroid_state() {
		return android_state;
	}
	public void setAndroid_state(String android_state) {
		this.android_state = android_state;
	}
	
	private String apns_type;
	
	
	
	public String getApns_type() {
		return apns_type;
	}
	public void setApns_type(String apns_type) {
		this.apns_type = apns_type;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Messagepush[");
		builder.append(", msgpush_id=");
		builder.append(this.msgpush_id);
		builder.append(", msgpush_name=");
		builder.append(this.msgpush_name);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", mp_abstract=");
		builder.append(this.mp_abstract);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", android_state=");
		builder.append(this.android_state);
		builder.append(", ios_state=");
		builder.append(this.ios_state);
		builder.append(", apns_type=");
		builder.append(this.apns_type);
		builder.append("]");
		return builder.toString();
	}

}

