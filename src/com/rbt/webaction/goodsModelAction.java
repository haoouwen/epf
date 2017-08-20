/*
  
 
 * Package:com.rbt.action
 * FileName: WebBaseAction.java 
 */
package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Area;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Salegoods;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.IParagroupService;
import com.rbt.service.IParavalueService;
import com.rbt.service.ISalegoodsService;
import com.rbt.service.ISelfextendattrService;
import com.rbt.service.ISelfparagroupService;
import com.rbt.service.ISelfparavalueService;
import com.rbt.service.ISelfspecnameService;
import com.rbt.service.ISelfspecvalueService;


/**
 * @author : LJQ
 * @date : May 14, 2014 3:00:18 PM
 * @Method Description : 商品通用模块类
 */
@SuppressWarnings("unchecked")
public class goodsModelAction extends WebbaseAction  {
	
	/*******************实体层****************/
	public Goodsattr goodsattr;

	/*******************业务层接口****************/
	@Autowired
	public IGoodsattrService goodsattrService;
	@Autowired
	public IParagroupService paragroupService;
	@Autowired
	public IParavalueService paravalueService;
	@Autowired
	public ISelfparagroupService selfparagroupService;
	@Autowired
	public ISelfparavalueService selfparavalueService;
	@Autowired
	public ISelfextendattrService selfextendattrService;
	@Autowired
	public ISelfspecnameService selfspecnameService;
	@Autowired
	public ISelfspecvalueService selfsepcvalueService;
	@Autowired
	private IBuyeraddrService buyeraddrService;
	@Autowired
	private ISalegoodsService salegoodsService;

	/*********************集合******************/
	public List paragroupList;//参数组
	public List paravalueList;//参数值
	public List selfparagroupList;//自定义参数组
	public List selfparavalueList;//自定义参数值
	public List selfspecnameList;//自定义规格名称相关
	public List goodsattrList;//自定义商品属性相关
	public List selfsepcvalueList;//自定义规格值相关
	public List selfextendattrList;//自定义扩展属性相关
	public List careaList;//定义省、直辖市地区列表
	public List sareaList;//定义省、直辖市子集地区列表

	/*********************字段******************/
	public String postName;//导航
	public String specstr_str;//商品所有规格值串
	public String sale_price;//销售价
	public String market_price;//市场价
	public String max_sale_price;//最高销售价
	public String min_sale_price;//最低销售价
	public String min_market_price;//最低市场价
	public String max_market_price;//最高市场价
	public String goods_cat;//商品分类
	public String indexName;//系统参数
	public String specstr_attr;//规格、市场价、销售价、库存json串
	public String areaipName;//初始地区
	public String sareaid;//地区id
	public String shipareaid;//初始地区id上级
	public String areaid;//定义初始地区id
	public String carea_name;//存放省份地区串
	public StringBuilder postsb;
	private String top_area_id = SysconfigFuc.getSysValue("cfg_topareaid");//变量
	public int total_stock;//库存
	public String give_inter_str;
	public String is_more_attr;
	public String volume_attr;//物流体积
	public String logsweight_attr;//物流重量
	
	/**	
	 * @author : WXP
	 * @param :goods_id
	 * @date Mar 13, 2014 11:25:19 AM
	 * @Method Description :获取相应商品的所有规格值以","隔开,用于判断规格值是否不存在或被删
	 */
	public String getSpecstr(String goods_id){
		//获取商品属性
		Map attrMap = new HashMap();
		attrMap.put("goods_id", goods_id);
		List goodsattrList = this.goodsattrService.getList(attrMap);
		if (goodsattrList != null && goodsattrList.size() > 0) {
			//循环获取规格值
			for(int i = 0; i < goodsattrList.size(); i++){
				HashMap map = new HashMap();
				map = (HashMap)goodsattrList.get(i);
				if (map.get("specstr") != null) {
					if(specstr_str != null && !specstr_str.equals("")){
						specstr_str = specstr_str +","+ map.get("specstr").toString();
					}else{
						specstr_str = map.get("specstr").toString();
					}
				}
				
			}
		}
		return specstr_str;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:31:32 PM
	 * @Method Description :获取商品自定义规格名称跟规格值
	 */
	public void getSepcValueList(String goods_id){
		/*//获取商品自定义规格名称列表
		Map ssnMap = new HashMap();
		ssnMap.put("goods_id", goods_id);
		ssnMap.put("id_asc", "id_asc");
		selfspecnameList = this.selfspecnameService.getList(ssnMap);
		//获取全部自定义规格值列表
		Map ssvMap = new HashMap();
		ssnMap.put("id_asc", "id_asc");
		selfsepcvalueList = this.selfsepcvalueService.getList(ssvMap);*/
		Map attrMap = new HashMap();
		attrMap.put("goods_id", goods_id);
		goodsattrList = this.goodsattrService.getList(attrMap);
		if(goodsattrList!=null&&goodsattrList.size()>0){
			Map mspecstr=(HashMap)goodsattrList.get(0);
			//spestr是为了判断是多属性还是单属性
			if (mspecstr.get("specstr")!=null && !"".equals(mspecstr.get("specstr").toString())) {
				//获取商品自定义规格名称列表
				Map ssnMap = new HashMap();
				ssnMap.put("goods_id", goods_id);
				ssnMap.put("id_asc", "id_asc");
				selfspecnameList = this.selfspecnameService.getList(ssnMap);
				if (selfspecnameList != null && selfspecnameList.size() > 0) {
					is_more_attr = "0";
					String flagId="";
					for(int i=0;i<selfspecnameList.size();i++){
						Map map=(HashMap)selfspecnameList.get(i);
						if (map.get("spec_serial_id") != null) {
							if(!flagId.equals("")){
								flagId = flagId +","+ map.get("spec_serial_id").toString();
							}else{
								flagId = map.get("spec_serial_id").toString();
							}
						}
					}
					String attrId="";
					for(int i=0;i<goodsattrList.size();i++){
						Map map=(HashMap)goodsattrList.get(i);
						if (map.get("specstr") != null) {
							if(!attrId.equals("")){
								attrId = attrId +","+ map.get("specstr").toString();
							}else{
								attrId = map.get("specstr").toString();
							}
						}
					}
					
					
					
					if(!ValidateUtil.isRequired(flagId)&&!ValidateUtil.isRequired(attrId)){
						//获取自定义规格值列表
						Map ssvMap = new HashMap();
						ssvMap.put("id_asc", "id_asc");
						ssvMap.put("spec_serial_id", flagId);
						if(!ValidateUtil.isRequired(attrId)){
							if(attrId.indexOf(":")>0){
								attrId=attrId.replace(":",",");
							}
						}
						ssvMap.put("self_size_id", attrId);
						selfsepcvalueList = this.selfsepcvalueService.getList(ssvMap);
					}
				}
			}}
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:37:56 PM
	 * @Method Description :获取自定义扩展属性列表
	 */
	public void getSelfAttr(String goods_id){
		Map attrMap = new HashMap();
		attrMap.put("goods_id", goods_id);
		selfextendattrList = this.selfextendattrService.getList(attrMap);
	}			
	
	/**
	 * @author : LJQ
	 * @date : Feb 5, 2014 1:42:36 PM
	 * @Method Description : 获取参数组与参数值表数据
	 */
	public void getParagroup(String goods_id,String goods_cat) {
		if(goods_id==null || goods_cat==null || goods_id.equals("") || goods_cat.equals("")){
			return;
		}
		// 参数组list
		Map pgMap = new HashMap();
		pgMap.put("cat_attr", goods_cat);
		paragroupList = this.paragroupService.getList(pgMap);
		// 参数组值表List
		Map pgvMap = new HashMap();
		pgvMap.put("cat_attr", goods_cat);
		if (goods_id != null && !goods_id.equals("")) {
			pgvMap.put("goods_id", goods_id);
			paravalueList = this.paravalueService.getParaValList(pgvMap);
		} else {
			paravalueList = this.paravalueService.getList(pgvMap);
		}
		//获取自定义参数组
		Map spgMap = new HashMap();
		spgMap.put("goods_id", goods_id);
		selfparagroupList = this.selfparagroupService.getList(spgMap);
		//获取全部自定义参数值
		selfparavalueList = this.selfparavalueService.getList(spgMap);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:40:38 PM
	 * @Method Description :
	 */
	public void getLowerOrHeightPrice(String goods_id, String sale_id) throws Exception{
		
		//获取商品属性列表
		Map goodsattrMap = new HashMap();
		goodsattrMap.put("goods_id", goods_id);
		goodsattrMap.put("sale_price_asc", "price_asc");
		goodsattrList = goodsattrService.getWebList(goodsattrMap);
		//总库存
		total_stock = goodsattrService.getTotalStock(goodsattrMap);
		if (goodsattrList != null && goodsattrList.size() > 0) {
			//获取第一条商品属性
			HashMap firstAttrmap = new HashMap();
			firstAttrmap = (HashMap) goodsattrList.get(0);
			if (firstAttrmap.get("sale_price") != null) {
				min_sale_price = firstAttrmap.get("sale_price").toString();
				//if(saleFlag(sale_id)){
					//min_sale_price = SalegoodsFuc.getSalePrice(sale_id, min_sale_price);
				//}
			}
			if (firstAttrmap.get("market_price") != null) {
				min_market_price = firstAttrmap.get("market_price").toString();
			}
			
			if (firstAttrmap.get("volume") != null) {
				volume_attr = firstAttrmap.get("volume").toString();
			}
			if (firstAttrmap.get("logsweight") != null) {
				logsweight_attr = firstAttrmap.get("logsweight").toString();
			}
			//获取最后一条商品属性
			HashMap lastAttrmap = new HashMap();
			lastAttrmap = (HashMap) goodsattrList.get(goodsattrList.size()-1);
			if (lastAttrmap.get("sale_price") != null) {
				max_sale_price = lastAttrmap.get("sale_price").toString();
				//if(saleFlag(sale_id)){
					//max_sale_price = SalegoodsFuc.getSalePrice(sale_id, max_sale_price);
				//}
			}
			if (lastAttrmap.get("market_price") != null) {
				max_market_price = lastAttrmap.get("market_price").toString();
			}
		}
		//将规格值、库存以键值形式存入list
		List list = new ArrayList();
		for(int i = 0; i < goodsattrList.size(); i++){
			HashMap attrmap = new HashMap();
			attrmap = (HashMap) goodsattrList.get(i);
			String specstr = "", market_price = "", sale_price = "" , stock = "",volume="",logsweight="";
			if (attrmap.get("specstr") != null) {
				specstr = attrmap.get("specstr").toString();
			}
			if (attrmap.get("stock") != null) {
				stock = attrmap.get("stock").toString();
			}
			if (attrmap.get("market_price") != null) {
				market_price = attrmap.get("market_price").toString();
			}
			if (attrmap.get("sale_price") != null) {
				sale_price = attrmap.get("sale_price").toString();
				//if(saleFlag(sale_id)){
					//sale_price = SalegoodsFuc.getSalePrice(sale_id, sale_price);
				//}
			}
			if (attrmap.get("volume") != null) {
				volume = attrmap.get("volume").toString();
			}
			if (attrmap.get("logsweight") != null) {
				logsweight = attrmap.get("logsweight").toString();
			}
			Map map = new HashMap();
			map.put("specstr", specstr);
			map.put("stock", stock);
			map.put("market_price", market_price);
			map.put("sale_price", sale_price);
			map.put("volume", volume);
			map.put("logsweight", logsweight);
			list.add(map);
		}
		//list to json
		specstr_attr = JsonUtil.list2json(list);
	}
	
	
	/**
	 * 返回折扣标识
	 * @param sale_id 商品促销ID
	 * @return
	 */
	private boolean saleFlag(String sale_id) {
		//定义标识符
		boolean flag = false;
		//获取促销商品
		if(!ValidateUtil.isRequired(sale_id)) {
			Salegoods salegoods = salegoodsService.get(sale_id);
		    if(salegoods != null && !salegoods.getTerm_state().equals("4")) {
		    	int coupon_state = Integer.valueOf(salegoods.getCoupon_state());
		    	//条件判断
			     switch (coupon_state) {
				   case 3:
					    //符合应用条件的商品以固定折扣出售
					    flag = true;
					    break;		
				   case 4:
					    //符合应用条件的商品固定价格购买
					    flag = true;
					    break;
				   case 5:
					    //符合应用条件的商品减去固定折扣出售
					    flag = true;
					    break;
				   case 6:
					    //符合应用条件的商品减固定价格购买
					    flag = true;
					    break;
				 }	
		    }
		}
		
		return flag;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 15, 2014 10:23:43 AM
	 * @Method Description :获取平台导航位置
	 */
	public void getPlatPosition(Goods goods){
		//获取平台导航位置
		goods_cat = goods.getCat_attr();
		if(goods_cat!=null && !"".equals(goods_cat)){
			String postStrID =goods_cat.substring(goods_cat.lastIndexOf(",")+1,goods_cat.length());
			postsb.setLength(0);
			getPathUrl(getpostID(postStrID));
		}
	}
	
	
	/**
	 * @author : LJQ
	 * @date : May 7, 2014 10:10:25 AM
	 * @Method Description :获取导航
	 */
	public void getPathUrl(String postStrID){
		indexName = SysconfigFuc.getSysValue("cfg_index");
		String s = "";
		StringBuilder strsb = new StringBuilder();
		if(postStrID != null && !postStrID.equals("")){
			postStrID = postStrID.substring(0,postStrID.length()-1);
			String str[] = postStrID.split(",");
			strsb.append("<a href=\""+ indexName +"\">首页</a>");
			strsb.append("&nbsp;>&nbsp;");
			for(int i = str.length-1;i >= 0; i--){
				strsb.append("<span><a href=\"/mall-goodsListNav-"+ str[i] +".html\">"+ CategoryFuc.getCateName(str[i]) +"</a></span>");
				if(i > 0){
					strsb.append("&nbsp;>&nbsp;");
				}
			}
		}
		postName=strsb.toString();
	}
	
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:57:05 PM
	 * @Method Description : 通过cust_id与user_type获取会员用户名称
	 */
	public String getUserNameByCustidAndUt(String cust_id){
		String user_name="";
		Map userMap = new HashMap();
		userMap.put("user_type","1");
		userMap.put("cust_id",cust_id);
		List list=this.memberuserService.getList(userMap);
		if(list!=null&&list.size()>0){
			 Map listMap=new HashMap();
			 listMap=(HashMap)list.get(0);
			 if(listMap.get("user_name")!=null){
				 user_name=listMap.get("user_name").toString();
			 }					 
		}
		return user_name;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:03:31 PM
	 * @Method Description :递归查找 指定分类ID向顶级ID查找拼成ID串 导航
	 */
	public String getpostID(String id){
		if(id == null || id.equals("")) return "";
		category=categoryService.get(id);	
		String up_id = "";
		postsb.append(id+",");
		if(category!=null&&!"1111111111".equals(category.getUp_cat_id())){ 
		   up_id = category.getUp_cat_id();
		   getpostID(up_id);
		}
      return postsb.toString();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 4:03:08 PM
	 * @Method Description : 获取商品品牌名称
	 */
	public String getGoodsBrand(String brand_id){
		if(brand_id==null || brand_id.equals("") || brand_id.equals("0")){
			return "";
		}
		//获取商品品牌
		String brand_name="";
		if(!ValidateUtil.isDigital(brand_id)){
			Goodsbrand gb = goodsbrandService.get(brand_id);
			if(gb != null){
				brand_name = gb.getBrand_name();
			}
		}
		
		return brand_name;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 4:17:39 PM
	 * @Method Description :获取详细页运费配送管理
	 */
	public void getDetialShip(){
		//获取中国省份和直辖市
		StringBuilder Csb = new StringBuilder();
		Map careamap = new HashMap();
		careamap.put("up_area_id", top_area_id);
		careaList = areaService.getList(careamap);
		if(careaList!=null && careaList.size()>0){
			Csb.append("<div id='careadiv'><ul>");
			for(int i=0;i<careaList.size();i++){
				Map cnamemap = new HashMap();
				cnamemap = (HashMap)careaList.get(i);
				String areaName = cnamemap.get("area_name").toString();
				String areaid = cnamemap.get("area_id").toString();
				Csb.append("<li id='"+ areaid +"'  class='firstarea' onclick=\"areafoc('" + areaid + "');\">"+ areaName +"</li>");
			}
			Csb.append("</ul></div>");
		}
		carea_name = Csb.toString();
		//页面初始化定位地区
	    areaid = AreaFuc.getAreaidByIpaddr(getRequest());
		Area area = areaService.get(areaid);
		if(area!=null){
			shipareaid = area.getUp_area_id();
			sareaid = shipareaid;
			areaipName = area.getArea_name();
		}
		if(this.session_cust_id!=null){
			
			Map hasdfoaddr = new HashMap();
			hasdfoaddr.put("cust_id", this.session_cust_id);
    		hasdfoaddr.put("sel_address", "0");
    		List buyeraddrList = this.buyeraddrService.getList(hasdfoaddr);
	    	if(buyeraddrList!=null&&buyeraddrList.size()>0){
	    		Map isdefo = (Map) buyeraddrList.get(0);
	    		String m_area_attr=isdefo.get("area_attr").toString();	
	    		if(!validateFactory.isRequired(m_area_attr)){
	    			if(m_area_attr.indexOf(",")>-1){
	    				String[] aids=m_area_attr.split(",");
	    				String m_area_id=aids[1];
	    				Area m_area = areaService.get(m_area_id);
	    				if(m_area!=null){
	    					shipareaid = m_area.getUp_area_id();
	    					sareaid = shipareaid;
	    					areaipName = m_area.getArea_name();
	    				}
	    			}
	    		}
	    		
	    	}
		}
	}
}
