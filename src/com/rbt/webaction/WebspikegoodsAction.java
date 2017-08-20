package com.rbt.webaction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.GoodsSpreadFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Shopconfig;
import com.rbt.model.Member;
import com.rbt.model.Spikegoods;
import com.rbt.service.IAftersalesService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISpikegoodsService;

public class WebspikegoodsAction  extends goodsModelAction implements Preparable  {
	/*******************实体层********************/
	public Goods goods;
	public Spikegoods spikegoods;
	public Goodsbrand goodsbrand;
	public Member member;
	public Shopconfig shopconfig;
	
	/*******************业务层接口****************/
	@Autowired
	private ISpikegoodsService spikegoodsService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IShopconfigService shopconfigService;
	
	/*********************集合********************/
	public List dateList = new ArrayList();//秒杀日期排程列表
	public List spikegoodsList;//秒杀列表
	public List goodsspreadList;//推广商品列表
	public List spikeRalateCatList;
	
	/*********************字段********************/
	public String current_hour;//当前系统小时
	private int cfg_spike_day = Integer.parseInt(SysconfigFuc.getSysValue("cfg_spike_day"));//秒杀系统配置参数，显示几天内的秒杀
	public String trade_id;
	public String spikeday="0";
	public String current_day;
	public String spikeState;
	public String indexName;
	public String gid;//商品标识
	public String goods_cat;//商品分类
	public String imgGroup;
	public String ship_addr;
	public String brand_name;
	public String cust_name;
	public String user_name;
	public long starttime;
	public long endtime;
	public String timeout="0";
	public Map indexMap=new HashMap();
	public String isBuySelf;//是否购买自己的商品
	public String time;//搜索时间点
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 17, 2014 10:06:19 AM
	 * @Method Description :秒杀首页
	 */
	public String index()throws Exception{
		indexMap=this.spikegoodsService.getIndexMap(cfg_spike_day,spikeday,time,pages_s,pageSize_s);
		return goUrl("spikeIndex");
	}
	
	
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 17, 2014 10:06:19 AM
	 * @Method Description :秒杀首页
	 */
	public String list()throws Exception{
		indexMap=this.spikegoodsService.getIndexMap(cfg_spike_day,"0","",pages_s,pageSize_s);
		return goUrl("spikeList");
	}
	
	
	/**
	 * @author : WXP
	 * @throws IOException 
	 * @date Feb 20, 2014 9:39:25 AM
	 * @Method Description :秒杀详细页
	 */
	@SuppressWarnings("unchecked")
	public String detail() throws Exception{
		String cust_id="";
		if(trade_id != null && !trade_id.equals("")){
			spikegoods = this.spikegoodsService.get(trade_id);//秒杀对象
			gid = spikegoods.getGoods_id();
			if(gid != null && !gid.equals("")){
				//获取商品对象
				goods = this.goodsService.get(gid);
				//校验商品
				if(verifyGoods(goods)){
					return null;
				}
				cust_id = goods.getCust_id();
			}
		}
		//获取平台导航位置
		goods_cat = goods.getCat_attr();
		if(goods_cat!=null && !"".equals(goods_cat)){
			String postStrID =goods_cat.substring(goods_cat.lastIndexOf(",")+1,goods_cat.length());
			postsb.setLength(0);
			getPathUrl(getpostID(postStrID));
		}
		//企业名称
		cust_name = turnCustidChangeName(cust_id);
		//判断商品是否是运营商发布的，如果是详细页左边的数据为运营商
		if(cust_id.equals("0")){
			//加载相关秒杀商品
			getSpikeCatGoods(spikegoods.getCat_attr());
		}else{
			if(cust_id != null && !cust_id.equals("")){
				shopconfig = this.shopconfigService.getByCustID(cust_id);//查看店铺是否关闭
				//校验店铺
				if(verifyShop(shopconfig)){
					return null;
				}
			}
			//获取会员用户名称
			user_name = getUserNameByCustidAndUt(cust_id);
			//发货地址
			ship_addr = shopconfig.getArea_attr();
			//地区ID转换为中文名称
			ship_addr = AreaFuc.getAreaNameByMap(ship_addr);
			ship_addr = ship_addr.replace(",", "");
			//判断商品是否是运营商发布，如果不是就要加载店铺的头部信息
			getIndexTop(cust_id);
		}
		//获取商品品牌
		brand_name = getGoodsBrand(goods.getBrand_id());
		//获取最低价、最高价
		getLowerOrHeightPrice(gid, "");
		//自定义规格和值
		getSepcValueList(gid);
		//获取规格值与商品规格比对是否属于规格库中
		getSpecstr(gid);
		//自定义属性
		getSelfAttr(gid);
		//获取系统参数组与参数值与自定义的参数组跟值
		getParagroup(gid,goods.getCat_attr());
		//处理商品图片
		if(spikegoods.getImg_path()!=null){
			imgGroup = spikegoods.getImg_path();
		}
		//获取秒杀详细页的运费管理
		getDetialShip();
		//秒杀详细页SEO
		spikeDetailSeo(goods_cat);
		//秒杀进行的状态
		spikeState=ToolsFuc.comparaDate(spikegoods.getStart_date(),spikegoods.getEnd_date());
		//获取倒计时时间秒数
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(spikegoods.getEnd_date()!=null){
			Date date = sd.parse(spikegoods.getEnd_date());
			endtime =date.getTime() - new Date().getTime();
//			if(0 > endtime){
//				timeout = "1";
//			}
		}
		if(spikegoods.getStart_date()!=null){
			Date date = sd.parse(spikegoods.getStart_date());
			starttime =date.getTime() - new Date().getTime();
//			if(0 > endtime){
//				timeout = "1";
//			}
		}
		return goUrl("spikeDetail");
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 3:46:10 PM
	 * @Method Description :秒杀详细页的SEO设置
	 */
	@SuppressWarnings("unchecked")
	private void spikeDetailSeo(String goods_cat){
		//seo详细页的设置
		goods_cat = CategoryFuc.getCateNameByMap(goods_cat);//商品所属分类
		Map seoMap = new HashMap();
		seoMap.put("goods_name", spikegoods.getSpike_title());
		seoMap.put("goods_no", goods.getGoods_no());
		seoMap.put("brand", brand_name);
		seoMap.put("goods_wd", goods.getGoods_wd());
		if(shopconfig != null){
			seoMap.put("shopname", shopconfig.getShop_name());
		}
		seoMap.put("goods_cat", goods_cat);
		seoMap.put("goods_seo_title", goods.getSeo_title());
		seoMap.put("goods_seo_keyword", goods.getSeo_keyword());
		seoMap.put("goods_seo_desc", goods.getSeo_desc());
		setSeoPage("goodsdetail",seoMap);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 1:25:59 PM
	 * @Method Description :获取秒杀的相关分类秒杀商品
	 */
	private void getSpikeCatGoods(String spikeCat){
		if(spikeCat==null || spikeCat.equals("")){
			return;
		}
		Map scMap = new HashMap();
		scMap.put("cat_attr", spikeCat);
		scMap.put("start", 0);
		scMap.put("limit", 8);
		spikeRalateCatList = this.spikegoodsService.getList(scMap);
	}
	
	public void prepare() throws Exception {
		//初始化StringBuilder
		postsb = new StringBuilder();
	}

}
