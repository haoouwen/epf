/*
  
 
 * Package:com.rbt.model
 * FileName: Goodsorder.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商品订单实体
 * @author 创建人 LHY
 * @date 创建日期 Fri Feb 01 16:00:36 CST 2014
 */
public class Goodsorder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String buy_cust_id;
	public String getBuy_cust_id() {
		return buy_cust_id;
	}
	public void setBuy_cust_id(String buy_cust_id) {
		this.buy_cust_id = buy_cust_id;
	}
	
	private String sell_cust_id;
	public String getSell_cust_id() {
		return sell_cust_id;
	}
	public void setSell_cust_id(String sell_cust_id) {
		this.sell_cust_id = sell_cust_id;
	}
	
	private String consignee;
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String buy_address;
	public String getBuy_address() {
		return buy_address;
	}
	public void setBuy_address(String buy_address) {
		this.buy_address = buy_address;
	}
	
	private String zip_code;
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	private String telephone;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private Double goods_amount;
	public Double getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(Double goods_amount) {
		this.goods_amount = goods_amount;
	}
	
	private Double comm_free;
	public Double getComm_free() {
		return comm_free;
	}
	public void setComm_free(Double comm_free) {
		this.comm_free = comm_free;
	}
	
	private Double ship_free;
	public Double getShip_free() {
		return ship_free;
	}
	public void setShip_free(Double ship_free) {
		this.ship_free = ship_free;
	}
	
	private Double insured;
	public Double getInsured() {
		return insured;
	}
	public void setInsured(Double insured) {
		this.insured = insured;
	}
	
	private Double why_price;
	public Double getWhy_price() {
		return why_price;
	}
	public void setWhy_price(Double why_price) {
		this.why_price = why_price;
	}
	
	private Double tatal_amount;
	public Double getTatal_amount() {
		return tatal_amount;
	}
	public void setTatal_amount(Double tatal_amount) {
		this.tatal_amount = tatal_amount;
	}
	
	private String order_weight;
	public String getOrder_weight() {
		return order_weight;
	}
	public void setOrder_weight(String order_weight) {
		this.order_weight = order_weight;
	}
	
	private String pay_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	
	private String send_mode;
	public String getSend_mode() {
		return send_mode;
	}
	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}
	
	private String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	private String order_time;
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	
	private String pay_time;
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	
	private String send_time;
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	private String sure_time;
	public String getSure_time() {
		return sure_time;
	}
	public void setSure_time(String sure_time) {
		this.sure_time = sure_time;
	}
	
	private String mem_remark;
	public String getMem_remark() {
		return mem_remark;
	}
	public void setMem_remark(String mem_remark) {
		this.mem_remark = mem_remark;
	}
	
	private String send_num;
	public String getSend_num() {
		return send_num;
	}
	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}
	
	private String order_type;
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
	private String mark_id;
	public String getMark_id() {
		return mark_id;
	}
	public void setMark_id(String mark_id) {
		this.mark_id = mark_id;
	}
	
	private String is_virtual;
	public String getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(String is_virtual) {
		this.is_virtual = is_virtual;
	}
	
	private String info_status;
	public String getInfo_status() {
		return info_status;
	}
	public void setInfo_status(String info_status) {
		this.info_status = info_status;
	}
	
	private String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	private String is_eval;
	public String getIs_eval() {
		return is_eval;
	}
	public void setIs_eval(String is_eval) {
		this.is_eval = is_eval;
	}
	private String sell_remark;
	private String print_smode_no;
	
//	private List orderdetaiList;
//	public List getOrderdetaiList() {
//		return orderdetaiList;
//	}
//	public void setOrderdetaiList(List orderdetaiList) {
//		this.orderdetaiList = orderdetaiList;
//	}
	
	
	public String getPrint_smode_no() {
		return print_smode_no;
	}
	public void setPrint_smode_no(String print_smode_no) {
		this.print_smode_no = print_smode_no;
	}
	public String getSell_remark() {
		return sell_remark;
	}
	public void setSell_remark(String sell_remark) {
		this.sell_remark = sell_remark;
	}
    
	private String print_gouwu;
	private String print_fahuo;
	public String getPrint_gouwu() {
		return print_gouwu;
	}
	public void setPrint_gouwu(String print_gouwu) {
		this.print_gouwu = print_gouwu;
	}
	public String getPrint_fahuo() {
		return print_fahuo;
	}
	public void setPrint_fahuo(String print_fahuo) {
		this.print_fahuo = print_fahuo;
	}
	
	//快递业务类型
	private String business_type;
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	//采用EMS自助系统 上传数据标识
	private String upload_flag;
	public String getUpload_flag() {
		return upload_flag;
	}
	public void setUpload_flag(String upload_flag) {
		this.upload_flag = upload_flag;
	}
	
	private String print_kuaidi;
	public String getPrint_kuaidi() {
		return print_kuaidi;
	}
	public void setPrint_kuaidi(String print_kuaidi) {
		this.print_kuaidi = print_kuaidi;
	}
	public String is_vip;
	public String getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}
	
	private String is_sfexpress;
	public String getIs_sfexpress() {
		return is_sfexpress;
	}
	public void setIs_sfexpress(String is_sfexpress) {
		this.is_sfexpress = is_sfexpress;
	}
	
	//新增：第三方支付交易流水号
	private String pay_trxid;
	public String getPay_trxid() {
		return pay_trxid;
	}
	public void setPay_trxid(String pay_trxid) {
		this.pay_trxid = pay_trxid;
	}
	
	//新增：已付款订单未发货_退款批次号
	private String refund_batch_no; 
	public String getRefund_batch_no() {
		return refund_batch_no;
	}
	public void setRefund_batch_no(String refund_batch_no) {
		this.refund_batch_no = refund_batch_no;
	}
	
	//新增：系统订单类型  0：电脑订单  1：触屏版订单
	private String is_webapp_order;
	public String getIs_webapp_order() {
		return is_webapp_order;
	}
	public void setIs_webapp_order(String is_webapp_order) {
		this.is_webapp_order = is_webapp_order;
	}
	
	//订单发货状态
	private String deliver_state;	
	public String getDeliver_state() {
		return deliver_state;
	}
	public void setDeliver_state(String deliver_state) {
		this.deliver_state = deliver_state;
	}
	private String print_fahuo_number;
	private String  print_kuaidi_number;
	public String getPrint_fahuo_number() {
		return print_fahuo_number;
	}
	public void setPrint_fahuo_number(String print_fahuo_number) {
		this.print_fahuo_number = print_fahuo_number;
	}
	public String getPrint_kuaidi_number() {
		return print_kuaidi_number;
	}
	public void setPrint_kuaidi_number(String print_kuaidi_number) {
		this.print_kuaidi_number = print_kuaidi_number;
	}
	//通关状态
	private String is_clearance;
	public String getIs_clearance(){
		return is_clearance;
	}
	public void setIs_clearance(String is_clearance){
		this.is_clearance=is_clearance;
		
	}
	
	private String express_transport_state;
	private String send_city_msg;
	private String integral_use;
	private String invoice_id;
	private String is_sea;
	private String taxes;
	private String discount;
	
	
	
	public String getExpress_transport_state() {
		return express_transport_state;
	}
	public void setExpress_transport_state(String express_transport_state) {
		this.express_transport_state = express_transport_state;
	}
	public String getSend_city_msg() {
		return send_city_msg;
	}
	public void setSend_city_msg(String send_city_msg) {
		this.send_city_msg = send_city_msg;
	}
	
	

	private String balance_use;
	public String getBalance_use() {
		return balance_use;
	}
	public void setBalance_use(String balance_use) {
		this.balance_use = balance_use;
	}

	
    private String order_integral;
    public String getOrder_integral() {
		return order_integral;
	}
	public void setOrder_integral(String order_integral) {
		this.order_integral = order_integral;
	}
	
	private String identitycard;
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	
	private String present_integral;
	public String getPresent_integral() {
		return present_integral;
	}
	public void setPresent_integral(String present_integral) {
		this.present_integral = present_integral;
	}
	
	private String red_id;
	private String red_money;
	private String send_coupon_id;
	private String send_red_id;
	private String  last_pay;
	
	private String is_kjtsuccess;
	public String getIs_kjtsuccess() {
		return is_kjtsuccess;
	}
	public void setIs_kjtsuccess(String is_kjtsuccess) {
		this.is_kjtsuccess = is_kjtsuccess;
	}
	
	private String falsereason;
	public String getFalsereason() {
		return falsereason;
	}
	public void setFalsereason(String falsereason) {
		this.falsereason = falsereason;
	}
	
	private String kjtorder_state;
	public String getKjtorder_state() {
		return kjtorder_state;
	}
	public void setKjtorder_state(String kjtorder_state) {
		this.kjtorder_state = kjtorder_state;
	}
	
	private String recallstatus;
	public String getRecallstatus() {
		return recallstatus;
	}
	public void setRecallstatus(String recallstatus) {
		this.recallstatus = recallstatus;
	}
	
	
	private String fg_ids;
	public String getFg_ids() {
		return fg_ids;
	}
	public void setFg_ids(String fg_ids) {
		this.fg_ids = fg_ids;
	}
	
	private String memberdiscount;
	public String getMemberdiscount() {
		return memberdiscount;
	}
	public void setMemberdiscount(String memberdiscount) {
		this.memberdiscount = memberdiscount;
	}
	
	private String coupon_money;
	public String getCoupon_money() {
		return coupon_money;
	}
	public void setCoupon_money(String coupon_money) {
		this.coupon_money = coupon_money;
	}
	
	private String customs_type;
	public String getCustoms_type() {
		return customs_type;
	}
	public void setCustoms_type(String customs_type) {
		this.customs_type = customs_type;
	}
	
	private String final_pay_method;
	public String getFinal_pay_method() {
		return final_pay_method;
	}
	public void setFinal_pay_method(String final_pay_method) {
		this.final_pay_method = final_pay_method;
	}
	
	private String sosysno;
	public String getSosysno() {
		return sosysno;
	}
	public void setSosysno(String sosysno) {
		this.sosysno = sosysno;
	}
	
	private String order_sign;
	
	public String getOrder_sign() {
		return order_sign;
	}
	public void setOrder_sign(String order_sign) {
		this.order_sign = order_sign;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsorder[");
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", buy_cust_id=");
		builder.append(this.buy_cust_id);
		builder.append(", sell_cust_id=");
		builder.append(this.sell_cust_id);
		builder.append(", consignee=");
		builder.append(this.consignee);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", buy_address=");
		builder.append(this.buy_address);
		builder.append(", zip_code=");
		builder.append(this.zip_code);
		builder.append(", telephone=");
		builder.append(this.telephone);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", goods_amount=");
		builder.append(this.goods_amount);
		builder.append(", comm_free=");
		builder.append(this.comm_free);
		builder.append(", ship_free=");
		builder.append(this.ship_free);
		builder.append(", insured=");
		builder.append(this.insured);
		builder.append(", why_price=");
		builder.append(this.why_price);
		builder.append(", tatal_amount=");
		builder.append(this.tatal_amount);
		builder.append(", order_weight=");
		builder.append(this.order_weight);
		builder.append(", pay_id=");
		builder.append(this.pay_id);
		builder.append(", send_mode=");
		builder.append(this.send_mode);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", order_time=");
		builder.append(this.order_time);
		builder.append(", pay_time=");
		builder.append(this.pay_time);
		builder.append(", send_time=");
		builder.append(this.send_time);
		builder.append(", sure_time=");
		builder.append(this.sure_time);
		builder.append(", mem_remark=");
		builder.append(this.mem_remark);
		builder.append(", send_num=");
		builder.append(this.send_num);
		builder.append(", order_type=");
		builder.append(this.order_type);
		builder.append(", mark_id=");
		builder.append(this.mark_id);
		builder.append(", is_virtual=");
		builder.append(this.is_virtual);
		builder.append(", info_status=");
		builder.append(this.info_status);
		builder.append(", sell_remark=");
		builder.append(this.sell_remark);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append(", is_eval=");
		builder.append(this.is_eval);
		builder.append(", print_gouwu=");
		builder.append(this.print_gouwu);
		builder.append(", print_fahuo=");
		builder.append(this.print_fahuo);
		builder.append(", business_type=");
		builder.append(this.business_type);
		builder.append(", upload_flag=");
		builder.append(this.upload_flag);
		builder.append(", print_kuaidi=");
		builder.append(this.print_kuaidi);
		builder.append(", is_vip=");
		builder.append(this.is_vip);
		builder.append(", is_sfexpress=");
		builder.append(this.is_sfexpress);
		builder.append(",pay_trxid=");
		builder.append(this.pay_trxid);
		builder.append(",refund_batch_no=");
		builder.append(this.refund_batch_no);
		builder.append(",is_webapp_order=");
		builder.append(this.is_webapp_order);
		builder.append(",deliver_state=");
		builder.append(this.deliver_state);
		builder.append(",print_kuaidi_number=");
		builder.append(this.print_kuaidi_number);
		builder.append(",send_city_msg=");
		builder.append(this.send_city_msg);
		builder.append(",express_transport_state=");
		builder.append(this.express_transport_state);
		builder.append(",integral_use=");
		builder.append(this.integral_use);
		builder.append(",balance_use=");
		builder.append(this.balance_use);
		builder.append(",order_integral=");
		builder.append(this.order_integral);
		builder.append(",is_sea=");
		builder.append(this.is_sea);
		builder.append(",taxes=");
		builder.append(this.taxes);
		builder.append(",invoice_id=");
		builder.append(this.invoice_id);
		builder.append(",identitycard=");
		builder.append(this.identitycard);
		builder.append(",present_integral=");
		builder.append(this.present_integral);
		builder.append(",discount=");
		builder.append(this.discount);
		builder.append(",last_pay=");
		builder.append(this.last_pay);
		builder.append(",is_clearance=");
		builder.append(this.is_clearance);
		builder.append(",red_id=");
		builder.append(this.red_id);
		builder.append(",red_money=");
		builder.append(this.red_money);		
		builder.append(",send_coupon_id=");
		builder.append(this.send_coupon_id);	
		builder.append(",send_red_id=");
		builder.append(this.send_red_id);	
		builder.append(",is_kjtsuccess=");
		builder.append(this.is_kjtsuccess);	
		builder.append(",falsereason=");
		builder.append(this.falsereason);	
		builder.append(",kjtorder_state=");
		builder.append(this.kjtorder_state);	
		builder.append(",recallstatus=");
		builder.append(this.recallstatus);	
		builder.append(",fg_ids=");
		builder.append(this.fg_ids);
		builder.append(",print_smode_no=");
		builder.append(this.print_smode_no);
		builder.append(",memberdiscount=");
		builder.append(this.memberdiscount);
		builder.append(",coupon_money=");
		builder.append(this.coupon_money);
		builder.append(",customs_type=");
		builder.append(this.customs_type);
		builder.append(",final_pay_method=");
		builder.append(this.final_pay_method);
		builder.append(",sosysno=");
		builder.append(this.sosysno);
		builder.append(",order_sign=");
		builder.append(this.order_sign);
		builder.append("]");
		return builder.toString();
	}
	public String getIntegral_use() {
		return integral_use;
	}
	public void setIntegral_use(String integral_use) {
		this.integral_use = integral_use;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getIs_sea() {
		return is_sea;
	}
	public void setIs_sea(String is_sea) {
		this.is_sea = is_sea;
	}
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getLast_pay() {
		return last_pay;
	}
	public void setLast_pay(String last_pay) {
		this.last_pay = last_pay;
	}
	public String getRed_id() {
		return red_id;
	}
	public void setRed_id(String red_id) {
		this.red_id = red_id;
	}
	public String getRed_money() {
		return red_money;
	}
	public void setRed_money(String red_money) {
		this.red_money = red_money;
	}
	public String getSend_coupon_id() {
		return send_coupon_id;
	}
	public void setSend_coupon_id(String send_coupon_id) {
		this.send_coupon_id = send_coupon_id;
	}
	public String getSend_red_id() {
		return send_red_id;
	}
	public void setSend_red_id(String send_red_id) {
		this.send_red_id = send_red_id;
	}
	
}


