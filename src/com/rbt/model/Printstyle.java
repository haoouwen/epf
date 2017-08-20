/*
  
 
 * Package:com.rbt.model
 * FileName: Printstyle.java 
 */
package com.rbt.model;

import java.io.Serializable;
import com.rbt.common.util.ValidateUtil;
/**
 * @function 功能 记录打印样式信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 27 15:56:03 CST 2014
 */
public class Printstyle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String trade_id;

	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

	private String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	private String template_code;

	public String getTemplate_code() {
		return template_code;
	}

	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

	private String template_name;

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	private String label_explan;

	public String getLabel_explan() {
		return label_explan;
	}

	public void setLabel_explan(String label_explan) {
		this.label_explan = label_explan;
	}

	private String print_content;

	public String getPrint_content() {
		return print_content;
	}

	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}

	private String print_style;

	public String getPrint_style() {
		return print_style;
	}

	public void setPrint_style(String print_style) {
		this.print_style = print_style;
	}

	private String show_content;

	public String getShow_content() {
		return show_content;
	}

	public void setShow_content(String show_content) {
		this.show_content = show_content;
	}

	private String print_param;

	public String getPrint_param() {
		return print_param;
	}

	public void setPrint_param(String print_param) {
		this.print_param = print_param;
	}
	/**
	 * @author:HXM
	 * @date:May 26, 20149:20:34 AM
	 * @param:
	 * @Description:将显示代码替换成打印代码
	 */
	public void replacePrintCode() {
		String str = this.show_content;
		if (!ValidateUtil.isRequired(this.show_content)) {
			//商品订单替换
			str=str.replace("订单编号", "${order_id}");
			str=str.replace("发件人姓名", "${send_name}");
			str=str.replace("发件人电话", "${send_phone}");
			str=str.replace("发件人公司", "${send_compiny}");
			str=str.replace("发件人地址", "${send_address}");
			str=str.replace("发件人邮编", "${send_postcode}");
			str=str.replace("备注", "${send_remark}");
			str=str.replace("收件人姓名", "${receive_name}");
			str=str.replace("收件人电话", "${receive_phone}");
			str=str.replace("收件人地址", "${receive_address}");
			str=str.replace("收件人邮编", "${receive_postcode}");
			str=str.replace("寄件人签名时间", "${signature_date}");
			str=str.replace("寄件人签名", "${send_signature}");
			str=str.replace("寄件人地区", "${send_area}");
			str=str.replace("收件人地区", "${receive_area}");
			//门店商品二维码替换
			str = str.replace("商品编号","${goods_no}");//选择的商品货号
			str = str.replace("英文名称","${goods_en_name}");//英文名称
			str = str.replace("产地","${goods_place}");//产地
			str = str.replace("商品品牌","${goods_brand}");//商品品牌
			str = str.replace("商品名称","${goods_name}");//商品品牌
			str = str.replace("条形码","${goods_bar_code}");//商品条形码
			str = str.replace("贸易方式","${goods_depot}");//贸易方式
			str = str.replace("生产厂商","${goods_producer}");//生产厂商
			str = str.replace("计量单位","${goods_unit}");//计量单位
			
			str = str.replace("订单号2","${orderid2}");//订单编号2
			str = str.replace("收件地区2","${receivearea2}");//收件人地区2
			str = str.replace("收件邮编2","${receivepostcode2}");//收件人邮编2
			str = str.replace("收件地址2","${receiveaddress2}");//收件人地址2
			str = str.replace("收件电话2","${receivephone2}");//收件人电话2
			str = str.replace("收件姓名2","${receivename2}");//收件人姓名2
			str = str.replace("运单号","${send_number}");//运单号
			str = str.replace("内容品名","${content_name}");//内容品名
			str = str.replace("收件区域编码","${receive_codenumber}");//收件区域编码
			str = str.replace("海关_单号","${customs_id}");//海关订单编号
			str = str.replace("海关单号2","${customsid2}");//海关订单编号2
			
			this.print_content=str;
		} else {
			this.print_content = "";
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Printstyle[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", template_code=");
		builder.append(this.template_code);
		builder.append(", template_name=");
		builder.append(this.template_name);
		builder.append(", label_explan=");
		builder.append(this.label_explan);
		builder.append(", print_content=");
		builder.append(this.print_content);
		builder.append(", print_style=");
		builder.append(this.print_style);
		builder.append(", show_content=");
		builder.append(this.show_content);
		builder.append(", print_param=");
		builder.append(this.print_param);
		builder.append("]");
		return builder.toString();
	}

}
