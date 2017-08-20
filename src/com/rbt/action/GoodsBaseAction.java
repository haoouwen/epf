package com.rbt.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Salegoods;
import com.rbt.service.IAreaService;
import com.rbt.service.ICouponService;
import com.rbt.service.IDepotService;
import com.rbt.service.IExtendattrService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.ISalegoodsService;

@SuppressWarnings("unchecked")
public class GoodsBaseAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************业务层接口****************/
	@Autowired
	private IGoodsbrandService goodsbrandService;
	@Autowired
	private IExtendattrService extendattrService;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private ISalegoodsService salegoodsService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private ICouponService couponService;

	/*********************集合******************/
	public List goodsLabelList;//商品标签
	public List goodsbrandList;//商品品牌
	public List extendattrList = new ArrayList();//商品扩展属性
	public List salegoodsList;//折扣商品
	public List areaList;//产地
	public List couponList;

	/*********************字段******************/
	public String goods_cat;//商品分类
	public String mbStr = "1111111111";// 会员自定义分类顶级id
	public String  g_goods_en_name_s;//英文名称
	public String  g_goods_tax_s;//税率tax_attr
	public String  g_goods_marketstart_s;//原价开始
	public String  g_goods_marketend_s;//原价结束
	public String  g_goods_salestart_s;//销售价开始
	public String  g_goods_saleend_s;//销售价结束
	public String  g_goods_stockstart_s;//库存开始
	public String  g_goods_stockend_s;//库存结束
	public String  g_goods_source_s;//商品来源
	public String  g_goods_depot_s;//贸易方式
	public String  g_goods_useintegral_s;//是否使用积分消费
	public String  g_goods_warehouse_s;//仓库编号
	public String  g_goods_kjtid_s;//跨境通商品ID
	public String  g_goods_place_s;//产地
	public String  g_goods_barcode_s;//条形码
	public String  g_goods_producer_s;//生产商
	public String  g_goods_brandid_s;//品牌ID
	public String  goods_no_s;//商品编号
	public String  goods_name_s;//商品名称
	public String  brand_name_s;//品牌名称
	public String  tab_s;//标签
	public String  g_starttime_s;//发布时间开始
	public String  g_endtime_s;//发布时间结束
	public String  g_goods_sale_id_s;//商品促销ID
	
      /**
	 * @Method Description :高级搜索组合条件map
	 * @author: HXK
	 * @date : Sep 16, 2015 11:17:05 AM
	 * @param 
	 * @return return_type
	 */
	   public void commonMapSearchGoodsList(Map commap){
			
			//搜索条件
			if(goods_no_s!=null&&!"".equals(goods_no_s)){
				goods_no_s=goods_no_s.trim();
				commap.put("goods_no", goods_no_s);
			}
			if(goods_name_s!=null&&!"".equals(goods_name_s)){
				goods_name_s=goods_name_s.trim();
				commap.put("goods_name", goods_name_s);
			}
			if(g_goods_en_name_s!=null&&!"".equals(g_goods_en_name_s)){
				g_goods_en_name_s=g_goods_en_name_s.trim();
				commap.put("g_goods_en_name_s", g_goods_en_name_s);
			}
			if(cat_attr_s!=null&&!"".equals(cat_attr_s)&&!"0".equals(cat_attr_s)){
				commap.put("cat_attr", cat_attr_s);
			}
			if(brand_name_s!=null&&!"".equals(brand_name_s)){
				commap.put("brand_name", brand_name_s);
			}
			if(tab_s!=null&&!"".equals(tab_s)){
				commap.put("labs", tab_s);
			}
			if(!ValidateUtil.isRequired(good_cat_attr)&&!"0".equals(good_cat_attr)){
				commap.put("cat_attr", good_cat_attr);
			}
			if(!ValidateUtil.isRequired(g_goods_brandid_s)){
				commap.put("g_goods_brandid_s", g_goods_brandid_s);
			}
			if(!ValidateUtil.isRequired(tax_attr)&&!"0".equals(tax_attr)){
				tax_attr=tax_attr.replace(" ","");
				if(tax_attr.endsWith(",0")){
					tax_attr=tax_attr.replace(",0","");
				}
				commap.put("tax_attr", tax_attr);
				viewTax(tax_attr);
			}
			if(!ValidateUtil.isRequired(g_goods_marketstart_s)){
				g_goods_marketstart_s=g_goods_marketstart_s.trim();
				commap.put("g_goods_marketstart_s", g_goods_marketstart_s);
			}
			if(!ValidateUtil.isRequired(g_goods_marketend_s)){
				g_goods_marketend_s=g_goods_marketend_s.trim();
				commap.put("g_goods_marketend_s", g_goods_marketend_s);
			}
			if(!ValidateUtil.isRequired(g_goods_salestart_s)){
				g_goods_salestart_s=g_goods_salestart_s.trim();
				commap.put("g_goods_salestart_s", g_goods_salestart_s);
			}
			if(!ValidateUtil.isRequired(g_goods_saleend_s)){
				g_goods_saleend_s=g_goods_saleend_s.trim();
				commap.put("g_goods_saleend_s", g_goods_saleend_s);
			}
			if(!ValidateUtil.isRequired(g_goods_stockstart_s)){
				g_goods_stockstart_s=g_goods_stockstart_s.trim();
				commap.put("g_goods_stockstart_s", g_goods_stockstart_s);
			}
			if(!ValidateUtil.isRequired(g_goods_stockend_s)){
				g_goods_stockend_s=g_goods_stockend_s.trim();
				commap.put("g_goods_stockend_s", g_goods_stockend_s);
			}
			if(!ValidateUtil.isRequired(g_goods_source_s)){
				commap.put("g_goods_source_s", g_goods_source_s);
			}
			if(!ValidateUtil.isRequired(g_goods_depot_s)){
				commap.put("g_goods_depot_s", g_goods_depot_s);
			}
			if(!ValidateUtil.isRequired(g_goods_useintegral_s)){
				commap.put("g_goods_useintegral_s", g_goods_useintegral_s);
			}
			if(!ValidateUtil.isRequired(g_goods_warehouse_s)){
				commap.put("g_goods_warehouse_s", g_goods_warehouse_s);
			}
			if(!ValidateUtil.isRequired(g_goods_kjtid_s)){
				g_goods_kjtid_s=g_goods_kjtid_s.trim();
				commap.put("g_goods_kjtid_s", g_goods_kjtid_s);
			}
			if(!ValidateUtil.isRequired(g_goods_place_s)){
				commap.put("g_goods_place_s", g_goods_place_s);
			}
			if(!ValidateUtil.isRequired(g_goods_barcode_s)){
				g_goods_barcode_s=g_goods_barcode_s.trim();
				commap.put("g_goods_barcode_s", g_goods_barcode_s);
			}
			if(!ValidateUtil.isRequired(g_goods_producer_s)){
				commap.put("g_goods_producer_s", g_goods_producer_s);
			}
			if(!ValidateUtil.isRequired(g_starttime_s)){
				commap.put("g_starttime_s", g_starttime_s);
			}
			if(!ValidateUtil.isRequired(g_endtime_s)){
				commap.put("g_endtime_s", g_endtime_s);
			}
			if(!ValidateUtil.isRequired(g_goods_sale_id_s)) {
				Salegoods salegoods = this.salegoodsService.get(g_goods_sale_id_s);
				//指定商品
				if(salegoods.getTerm_state().equals("1")){
					commap.put("sgis", salegoods.getTerm());
				}else if(salegoods.getTerm_state().equals("2")){//商品分类
					commap.put("cat_attr", salegoods.getTerm());
				}else if(salegoods.getTerm_state().equals("4")){//指定的商品总价满X,对商品优惠
					commap.put("sgis", salegoods.getTerm());
				}				
			}
	   }
	 /**
	 * @Method Description :高级搜索组合条件 转码
	 * @author: HXK
	 * @date : Sep 16, 2015 11:17:20 AM
	 * @param 
	 * @return return_type
	 * @throws UnsupportedEncodingException 
	 */
	 public void  commonUTFSearchGoodsList() throws UnsupportedEncodingException{
	    	//搜索条件数据 解码UTF-8
			if(goods_no_s!=null&&!"".equals(goods_no_s)){
				goods_no_s = URLDecoder.decode(goods_no_s, "UTF-8");
			}
			if(goods_name_s!=null&&!"".equals(goods_name_s)){
				goods_name_s = URLDecoder.decode(goods_name_s, "UTF-8");
				goods_name_s=goods_name_s.trim();
			}
			if(g_goods_en_name_s!=null&&!"".equals(g_goods_en_name_s)){
				g_goods_en_name_s = URLDecoder.decode(g_goods_en_name_s, "UTF-8");
			}
			if(cat_attr_s!=null&&!"".equals(cat_attr_s)&&!"0".equals(cat_attr_s)){
				cat_attr_s = URLDecoder.decode(cat_attr_s, "UTF-8");
			}
			if(brand_name_s!=null&&!"".equals(brand_name_s)){
				brand_name_s = URLDecoder.decode(brand_name_s, "UTF-8");
			}
			if(tab_s!=null&&!"".equals(tab_s)){
				tab_s = URLDecoder.decode(tab_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(good_cat_attr)&&!"0".equals(good_cat_attr)){
				good_cat_attr = URLDecoder.decode(good_cat_attr, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_brandid_s)){
				g_goods_brandid_s = URLDecoder.decode(g_goods_brandid_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(tax_attr)&&!"0".equals(tax_attr)){
				tax_attr = URLDecoder.decode(tax_attr, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_marketstart_s)){
				g_goods_marketstart_s = URLDecoder.decode(g_goods_marketstart_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_marketend_s)){
				g_goods_marketend_s = URLDecoder.decode(g_goods_marketend_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_salestart_s)){
				g_goods_salestart_s = URLDecoder.decode(g_goods_salestart_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_saleend_s)){
				g_goods_saleend_s = URLDecoder.decode(g_goods_saleend_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_stockstart_s)){
				g_goods_stockstart_s = URLDecoder.decode(g_goods_stockstart_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_stockend_s)){
				g_goods_stockend_s = URLDecoder.decode(g_goods_stockend_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_source_s)){
				g_goods_source_s = URLDecoder.decode(g_goods_source_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_depot_s)){
				g_goods_depot_s = URLDecoder.decode(g_goods_depot_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_useintegral_s)){
				g_goods_useintegral_s = URLDecoder.decode(g_goods_useintegral_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_warehouse_s)){
				g_goods_warehouse_s = URLDecoder.decode(g_goods_warehouse_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_kjtid_s)){
				g_goods_kjtid_s = URLDecoder.decode(g_goods_kjtid_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_place_s)&&!"0".equals(g_goods_place_s)){
				g_goods_place_s = URLDecoder.decode(g_goods_place_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_barcode_s)){
				g_goods_barcode_s = URLDecoder.decode(g_goods_barcode_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_goods_producer_s)){
				g_goods_producer_s = URLDecoder.decode(g_goods_producer_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_starttime_s)){
				g_starttime_s = URLDecoder.decode(g_starttime_s, "UTF-8");
			}
			if(!ValidateUtil.isRequired(g_endtime_s)){
				g_endtime_s = URLDecoder.decode(g_endtime_s, "UTF-8");
			}
	 }
	   
	   
	/**
	 * @author : LJQ
	 * @date : Feb 4, 2014 2:16:49 PM
	 * @Method Description :获取商品标签
	 */
	public void getGoodsLabel() {
		Map labMap = new HashMap();
		labMap.put("para_code", "goods_lable");
		labMap.put("enabled", "0");
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			labMap.put("no_para_value", "4");
		}
		goodsLabelList = this.commparaService.getList(labMap);
	}

	/**
	 * @author : LJQ
	 * @date : Feb 18, 2014 10:17:09 AM
	 * @Method Description :获取品牌列表
	 */
	public void getBrandList(String goods_cat) {
		Map brandMap = new HashMap();
		brandMap.put("goods_attr", goods_cat);
		goodsbrandList = this.goodsbrandService.getList(brandMap);
	}
	/**
	 * @author : LJQ
	 * @date : Feb 18, 2014 10:17:09 AM
	 * @Method Description :获取品牌列表
	 */
	public void getALLBrandList() {
		Map brandMap = new HashMap();
		goodsbrandList = this.goodsbrandService.getList(brandMap);
	}
	public List depotList;//贸易方式
	/**
	 * 获取贸易方式
	 */
	public void getTradeWay() {
		depotList = this.depotService.getList(new HashMap());
	}
	/**
	 * 获取折扣商品
	 */
	public void getSaleGoods() {
		Map map =new HashMap();
		//启用
		map.put("info_state", "1");
		//不过期
		map.put("time", "1");
		
		salegoodsList = this.salegoodsService.getList(map);
	}
	/**
	 * 获取贸易方式
	 */
	public void getTopArea() {
		//获取产地地区
		Map areaMap=new HashMap();
		areaMap.put("area_level", "1");
		areaList = areaService.getList(areaMap);
	}
	/**
	 * 获取折扣商品
	 */
	public void getCouponGoods() {
		Map couponMap =new HashMap();
		couponMap.put("time", "1");
		couponMap.put("info_state", "1");
		couponList = this.couponService.getList(couponMap);
	}
	/**
	 * @author : LJQ
	 * @date : Feb 5, 2014 10:03:07 AM
	 * @Method Description :获取扩展属性的列表
	 */
	public void getExtendAttr(String goods_id) {
		String[] cat_attrs = goods_cat.split(",");
		if (goods_id != null && !goods_id.equals("")) {
			extendattrList = commonExtendAttr(cat_attrs, goods_id);
		} else {
			extendattrList = commonExtendAttr(cat_attrs, "");
		}
	}
	
    
	/**
	 * 公共查询商品属性
	 * @param cat_attr_s 属性数组
	 * @param goods_id 商品ID
	 */
	private List commonExtendAttr(String[] cat_attr_s, String goods_id) {
		List commonList = new ArrayList();
		for(int i =0; i < cat_attr_s.length; i++) {
			List list = new ArrayList();
			Map map = new HashMap();
			map.put("cat_attr", cat_attr_s[i]+"|");
			if(!ValidateUtil.isRequired(goods_id)) {
				map.put("goods_id", goods_id);
				list = this.extendattrService.getExAttrList(map);
			}else {
			    list = this.extendattrService.getList(map);
			}
			
			if(list != null && list.size() > 0) {
				for(int j = 0; j < list.size(); j++) {
					Map attrMap = (HashMap) list.get(j);
					commonList.add(attrMap);
				}
			}
		}
		return commonList;
	}
	
	/**
	 * @author LHY
	 * @date : May 10, 2014 10:06:46 PM
	 * @Method Description : 进入更新页面返回串
	 */
	public void viewSelfCat(String mb_cat_attr) {
		if (mbStr != null && mbStr.equals("") || mbStr.indexOf(mb_cat_attr) == -1) {
			mbStr += "," + mb_cat_attr.replace(" ", "");
		}
	}

	public String getGoods_cat() {
		return goods_cat;
	}

	public void setGoods_cat(String goods_cat) {
		this.goods_cat = goods_cat;
	}

	public String getMbStr() {
		return mbStr;
	}

	public void setMbStr(String mbStr) {
		this.mbStr = mbStr;
	}

	public String getG_goods_en_name_s() {
		return g_goods_en_name_s;
	}

	public void setG_goods_en_name_s(String g_goods_en_name_s) {
		this.g_goods_en_name_s = g_goods_en_name_s;
	}

	public String getG_goods_tax_s() {
		return g_goods_tax_s;
	}

	public void setG_goods_tax_s(String g_goods_tax_s) {
		this.g_goods_tax_s = g_goods_tax_s;
	}

	public String getG_goods_marketstart_s() {
		return g_goods_marketstart_s;
	}

	public void setG_goods_marketstart_s(String g_goods_marketstart_s) {
		this.g_goods_marketstart_s = g_goods_marketstart_s;
	}

	public String getG_goods_marketend_s() {
		return g_goods_marketend_s;
	}

	public void setG_goods_marketend_s(String g_goods_marketend_s) {
		this.g_goods_marketend_s = g_goods_marketend_s;
	}

	public String getG_goods_salestart_s() {
		return g_goods_salestart_s;
	}

	public void setG_goods_salestart_s(String g_goods_salestart_s) {
		this.g_goods_salestart_s = g_goods_salestart_s;
	}

	public String getG_goods_saleend_s() {
		return g_goods_saleend_s;
	}

	public void setG_goods_saleend_s(String g_goods_saleend_s) {
		this.g_goods_saleend_s = g_goods_saleend_s;
	}

	public String getG_goods_stockstart_s() {
		return g_goods_stockstart_s;
	}

	public void setG_goods_stockstart_s(String g_goods_stockstart_s) {
		this.g_goods_stockstart_s = g_goods_stockstart_s;
	}

	public String getG_goods_stockend_s() {
		return g_goods_stockend_s;
	}

	public void setG_goods_stockend_s(String g_goods_stockend_s) {
		this.g_goods_stockend_s = g_goods_stockend_s;
	}

	public String getG_goods_source_s() {
		return g_goods_source_s;
	}

	public void setG_goods_source_s(String g_goods_source_s) {
		this.g_goods_source_s = g_goods_source_s;
	}

	public String getG_goods_depot_s() {
		return g_goods_depot_s;
	}

	public void setG_goods_depot_s(String g_goods_depot_s) {
		this.g_goods_depot_s = g_goods_depot_s;
	}

	public String getG_goods_useintegral_s() {
		return g_goods_useintegral_s;
	}

	public void setG_goods_useintegral_s(String g_goods_useintegral_s) {
		this.g_goods_useintegral_s = g_goods_useintegral_s;
	}

	public String getG_goods_warehouse_s() {
		return g_goods_warehouse_s;
	}

	public void setG_goods_warehouse_s(String g_goods_warehouse_s) {
		this.g_goods_warehouse_s = g_goods_warehouse_s;
	}

	public String getG_goods_kjtid_s() {
		return g_goods_kjtid_s;
	}

	public void setG_goods_kjtid_s(String g_goods_kjtid_s) {
		this.g_goods_kjtid_s = g_goods_kjtid_s;
	}

	public String getG_goods_place_s() {
		return g_goods_place_s;
	}

	public void setG_goods_place_s(String g_goods_place_s) {
		this.g_goods_place_s = g_goods_place_s;
	}

	public String getG_goods_barcode_s() {
		return g_goods_barcode_s;
	}

	public void setG_goods_barcode_s(String g_goods_barcode_s) {
		this.g_goods_barcode_s = g_goods_barcode_s;
	}

	public String getG_goods_producer_s() {
		return g_goods_producer_s;
	}

	public void setG_goods_producer_s(String g_goods_producer_s) {
		this.g_goods_producer_s = g_goods_producer_s;
	}

	public String getG_goods_brandid_s() {
		return g_goods_brandid_s;
	}

	public void setG_goods_brandid_s(String g_goods_brandid_s) {
		this.g_goods_brandid_s = g_goods_brandid_s;
	}

	public String getGoods_no_s() {
		return goods_no_s;
	}

	public void setGoods_no_s(String goods_no_s) {
		this.goods_no_s = goods_no_s;
	}

	public String getGoods_name_s() {
		return goods_name_s;
	}

	public void setGoods_name_s(String goods_name_s) {
		this.goods_name_s = goods_name_s;
	}

	public String getBrand_name_s() {
		return brand_name_s;
	}

	public void setBrand_name_s(String brand_name_s) {
		this.brand_name_s = brand_name_s;
	}

	public String getTab_s() {
		return tab_s;
	}

	public void setTab_s(String tab_s) {
		this.tab_s = tab_s;
	}
	public String getG_starttime_s() {
		return g_starttime_s;
	}
	public void setG_starttime_s(String g_starttime_s) {
		this.g_starttime_s = g_starttime_s;
	}
	public String getG_endtime_s() {
		return g_endtime_s;
	}
	public void setG_endtime_s(String g_endtime_s) {
		this.g_endtime_s = g_endtime_s;
	}

}
