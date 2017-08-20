/*
  
 
 * Package:com.rbt.model
 * FileName: Goods.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = 1L;
	private String volume;
	private String logsweight;
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	private String goods_no;
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	
	private String goods_wd;
	public String getGoods_wd() {
		return goods_wd;
	}
	public void setGoods_wd(String goods_wd) {
		this.goods_wd = goods_wd;
	}
	
	private String brand_id;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	
	private String list_img;
	public String getList_img() {
		return list_img;
	}
	public void setList_img(String list_img) {
		this.list_img = list_img;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String goods_video;
	public String getGoods_video() {
		return goods_video;
	}
	public void setGoods_video(String goods_video) {
		this.goods_video = goods_video;
	}
	
	private String goods_detail;
	public String getGoods_detail() {
		return goods_detail;
	}
	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}
	
	private String self_cat;
	public String getSelf_cat() {
		return self_cat;
	}
	public void setSelf_cat(String self_cat) {
		this.self_cat = self_cat;
	}
	
	private String up_date;
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	
	private String down_date;
	public String getDown_date() {
		return down_date;
	}
	public void setDown_date(String down_date) {
		this.down_date = down_date;
	}
	
	private String weight;
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	private String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	private String is_ship;
	public String getIs_ship() {
		return is_ship;
	}
	public void setIs_ship(String is_ship) {
		this.is_ship = is_ship;
	}
	
	private String seo_title;

	
	private String seo_keyword;
	public String getSeo_keyword() {
		return seo_keyword;
	}
	public void setSeo_keyword(String seo_keyword) {
		this.seo_keyword = seo_keyword;
	}
	
	private String seo_desc;
	public String getSeo_desc() {
		return seo_desc;
	}
	public void setSeo_desc(String seo_desc) {
		this.seo_desc = seo_desc;
	}
	
	private String relate_goods;
	public String getRelate_goods() {
		return relate_goods;
	}
	public void setRelate_goods(String relate_goods) {
		this.relate_goods = relate_goods;
	}
	
	private String give;
	public String getGive() {
		return give;
	}
	public void setGive(String give) {
		this.give = give;
	}
	
	private String lab;
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	
	private String busi_remark;
	public String getBusi_remark() {
		return busi_remark;
	}
	public void setBusi_remark(String busi_remark) {
		this.busi_remark = busi_remark;
	}
	
	private String is_up;
	public String getIs_up() {
		return is_up;
	}
	public void setIs_up(String is_up) {
		this.is_up = is_up;
	}
	
	private String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	
	private String ship_id;
	public String getShip_id() {
		return ship_id;
	}
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int click_num;
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public String  sort_order;
	public String  getSort_order() {
		return sort_order;
	}
	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	
	private String active_state;
	public String getActive_state() {
		return active_state;
	}
	public void setActive_state(String active_state) {
		this.active_state = active_state;
	}
	
	private String total_stock;
	public String getTotal_stock() {
		return total_stock;
	}
	public void setTotal_stock(String total_stock) {
		this.total_stock = total_stock;
	}
	
	private String min_sale_price;
	public String getMin_sale_price() {
		return min_sale_price;
	}
	public void setMin_sale_price(String min_sale_price) {
		this.min_sale_price = min_sale_price;
	}
	
	private String goods_market_price;
	public String getGoods_market_price() {
		return goods_market_price;
	}
	public void setGoods_market_price(String goods_market_price) {
		this.goods_market_price = goods_market_price;
	}
	
	private String pretime;
	public String getPretime() {
		return pretime;
	}
	public void setPretime(String pretime) {
		this.pretime = pretime;
	}
	private String is_limit; 
	private String limit_num;
	private String phone_goods_detail;
	public String getPhone_goods_detail() {
		return phone_goods_detail;
	}
	public void setPhone_goods_detail(String phone_goods_detail) {
		this.phone_goods_detail = phone_goods_detail;
	}
	//产地
	private String goods_place; 
	//商品来源:0自主运营,1供货商供货
	private String goods_source;
	//是否使用积分消费，0不使用。1可以使用积分消费
	private String use_integral;
	//仓库ID。对于仓库表depot
	private String depot_id; 
    //税率
	private String tax_rate;
	//税费
	private String tax_price;
    //税率类别
	private String tax_attr; 
	
	//国际运价
	private String ief_id;
	private  String is_international;
	
	public String getIef_id() {
		return ief_id;
	}
	public void setIef_id(String ief_id) {
		this.ief_id = ief_id;
	}
	public String getGoods_place() {
		return goods_place;
	}
	public void setGoods_place(String goods_place) {
		this.goods_place = goods_place;
	}
	public String getGoods_source() {
		return goods_source;
	}
	public void setGoods_source(String goods_source) {
		this.goods_source = goods_source;
	}
	public String getUse_integral() {
		return use_integral;
	}
	public void setUse_integral(String use_integral) {
		this.use_integral = use_integral;
	}
	public String getDepot_id() {
		return depot_id;
	}
	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
	}
	public String getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}
	public String getTax_price() {
		return tax_price;
	}
	public void setTax_price(String tax_price) {
		this.tax_price = tax_price;
	}
	public String getTax_attr() {
		return tax_attr;
	}
	public void setTax_attr(String tax_attr) {
		this.tax_attr = tax_attr;
	}
	private String sale_name;
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	//英文名称
	private String goods_en_name;
	//条形码
	private String bar_code;
	//生产商
	private String producer;
	//生产地址
	private String product_address;
	//出口经销商
	private String export;
	//海关已备案
	private String is_customs;
	//商检经备案
	private String inspection;
	//贸易方式
	private String trade_way;
	//保质期
	private String quality;
	//品牌故事
	private String brand_store;
	//生产流程
	private String product_process;
	//进口证明
	private String import_cert;
	public String getGoods_en_name() {
		return goods_en_name;
	}
	public void setGoods_en_name(String goods_en_name) {
		this.goods_en_name = goods_en_name;
	}
	public String getBar_code() {
		return bar_code;
	}
	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getProduct_address() {
		return product_address;
	}
	public void setProduct_address(String product_address) {
		this.product_address = product_address;
	}
	public String getExport() {
		return export;
	}
	public void setExport(String export) {
		this.export = export;
	}
	public String getIs_customs() {
		return is_customs;
	}
	public void setIs_customs(String is_customs) {
		this.is_customs = is_customs;
	}
	public String getInspection() {
		return inspection;
	}
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	public String getTrade_way() {
		return trade_way;
	}
	public void setTrade_way(String trade_way) {
		this.trade_way = trade_way;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getBrand_store() {
		return brand_store;
	}
	public void setBrand_store(String brand_store) {
		this.brand_store = brand_store;
	}
	public String getProduct_process() {
		return product_process;
	}
	public void setProduct_process(String product_process) {
		this.product_process = product_process;
	}
	public String getImport_cert() {
		return import_cert;
	}
	public void setImport_cert(String import_cert) {
		this.import_cert = import_cert;
	}
	
	private String sale_num;
	public String getSale_num() {
		return sale_num;
	}
	public void setSale_num(String sale_num) {
		this.sale_num = sale_num;
	}
	
	private String warehouse_number;
	public String getWarehouse_number() {
		return warehouse_number;
	}
	public void setWarehouse_number(String warehouse_number) {
		this.warehouse_number = warehouse_number;
	}
	
	
	private String goods_width;
	private String goods_height;
	private String goods_long;
	public String getGoods_width() {
		return goods_width;
	}
	public void setGoods_width(String goods_width) {
		this.goods_width = goods_width;
	}
	public String getGoods_height() {
		return goods_height;
	}
	public void setGoods_height(String goods_height) {
		this.goods_height = goods_height;
	}
	public String getGoods_long() {
		return goods_long;
	}
	public void setGoods_long(String goods_long) {
		this.goods_long = goods_long;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String kjt_id;
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goods[");
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", goods_name=");
		builder.append(this.goods_name);
		builder.append(", goods_no=");
		builder.append(this.goods_no);
		builder.append(", goods_wd=");
		builder.append(this.goods_wd);
		builder.append(", brand_id=");
		builder.append(this.brand_id);
		builder.append(", list_img=");
		builder.append(this.list_img);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", goods_video=");
		builder.append(this.goods_video);
		builder.append(", goods_detail=");
		builder.append(this.goods_detail);
		builder.append(", self_cat=");
		builder.append(this.self_cat);
		builder.append(", up_date=");
		builder.append(this.up_date);
		builder.append(", down_date=");
		builder.append(this.down_date);
		builder.append(", weight=");
		builder.append(this.weight);
		builder.append(", unit=");
		builder.append(this.unit);
		builder.append(", is_ship=");
		builder.append(this.is_ship);
		builder.append(", seo_keyword=");
		builder.append(this.seo_keyword);
		builder.append(", seo_desc=");
		builder.append(this.seo_desc);
		builder.append(", relate_goods=");
		builder.append(this.relate_goods);
		builder.append(", give=");
		builder.append(this.give);
		builder.append(", lab=");
		builder.append(this.lab);
		builder.append(", busi_remark=");
		builder.append(this.busi_remark);
		builder.append(", is_up=");
		builder.append(this.is_up);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", ship_id=");
		builder.append(this.ship_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", click_num=");
		builder.append(this.click_num);
		builder.append(", sort_order=");
		builder.append(this.sort_order);
		builder.append(", active_state=");
		builder.append(this.active_state);
		builder.append(", total_stock=");
		builder.append(this.total_stock);
		builder.append(", min_sale_price=");
		builder.append(this.min_sale_price);
		builder.append(", goods_market_price=");
		builder.append(this.goods_market_price);
		builder.append(", pretime=");
		builder.append(this.pretime);
		builder.append(", is_limit=");
		builder.append(this.is_limit);
		builder.append(", limit_num=");
		builder.append(this.limit_num);
		builder.append(", phone_goods_detail=");
		builder.append(this.phone_goods_detail);
		builder.append(", goods_place=");
		builder.append(this.goods_place);
		builder.append(", goods_source=");
		builder.append(this.goods_source);
		builder.append(", use_integral=");
		builder.append(this.use_integral);
		builder.append(", depot_id=");
		builder.append(this.depot_id);
		builder.append(", tax_rate=");
		builder.append(this.tax_rate);
		builder.append(", tax_price=");
		builder.append(this.tax_price);
		builder.append(", tax_attr=");
		builder.append(this.tax_attr);
		builder.append(", ief_id=");
		builder.append(this.ief_id);
		builder.append(", sale_name=");
		builder.append(this.sale_name);	
		builder.append(", goods_en_name=");
		builder.append(this.goods_en_name);
		builder.append(", bar_code=");
		builder.append(this.bar_code);
		builder.append(", producer=");
		builder.append(this.producer);
		builder.append(", product_address=");
		builder.append(this.product_address);
		builder.append(", export=");
		builder.append(this.export);
		builder.append(", is_customs=");
		builder.append(this.is_customs);
		builder.append(", inspection=");
		builder.append(this.inspection);
		builder.append(", trade_way=");
		builder.append(this.trade_way);
		builder.append(", quality=");
		builder.append(this.quality);
		builder.append(", brand_store=");
		builder.append(this.brand_store);
		builder.append(", product_process=");
		builder.append(this.product_process);
		builder.append(", import_cert=");
		builder.append(this.import_cert);	
		builder.append(", sale_num=");
		builder.append(this.sale_num);
		builder.append(", is_international=");
		builder.append(this.is_international);
		builder.append(", warehouse_number=");
		builder.append(this.warehouse_number);
		builder.append(", goods_width=");
		builder.append(this.goods_width);
		builder.append(", goods_height=");
		builder.append(this.goods_height);
		builder.append(", goods_long=");
		builder.append(this.goods_long);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", kjt_id=");
		builder.append(this.kjt_id);
		builder.append("]");
		return builder.toString();
	}
	public String getSeo_title() {
		return seo_title;
	}
	public void setSeo_title(String seo_title) {
		this.seo_title = seo_title;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getLogsweight() {
		return logsweight;
	}
	public void setLogsweight(String logsweight) {
		this.logsweight = logsweight;
	}
	private String is_virtual;
	public String getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(String is_virtual) {
		this.is_virtual = is_virtual;
	}
	public String getIs_limit() {
		return is_limit;
	}
	public void setIs_limit(String is_limit) {
		this.is_limit = is_limit;
	}
	public String getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(String limit_num) {
		this.limit_num = limit_num;
	}
	public String getIs_international() {
		return is_international;
	}
	public void setIs_international(String is_international) {
		this.is_international = is_international;
	}
	public String getKjt_id() {
		return kjt_id;
	}
	public void setKjt_id(String kjt_id) {
		this.kjt_id = kjt_id;
	}

}

