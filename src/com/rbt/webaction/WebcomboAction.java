package com.rbt.webaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.function.AreaFuc;
import com.rbt.model.Combo;
import com.rbt.model.Shopconfig;
import com.rbt.service.IGoodsService;
import com.rbt.service.IComboService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISelfspecnameService;
import com.rbt.service.ISelfspecvalueService;

public class WebcomboAction  extends WebbaseAction implements Preparable  {
	
	/*******************实体层****************/
	public Combo combo;
	public Shopconfig shopconfig;
	/*******************业务层接口****************/
	@Autowired
	private IComboService comboService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private ISelfspecnameService selfspecnameService;
	@Autowired
	private ISelfspecvalueService selfsepcvalueService;

	/*********************集合******************/
	public List goodsList;//商品列表
	public List selfspecnameList;//自定义规格名称相关
	public List selfsepcvalueList;//自定义规格值相关
	public List comboRalateCatList;//组合相关商品

	/*********************字段******************/
	public String trade_id;//套餐标识
	public String user_name;//卖家名称
	public String ship_addr;//店铺地址
	public String isBuySelf;//是否购买自己的商品
	public Double all_goods_price = 0.0;//商品总价
	
	/**	
	 * @author : WXP
	 * @param :trade_id
	 * @date Apr 22, 2014 3:32:09 PM
	 * @Method Description :套餐详情
	 */
	@SuppressWarnings("unchecked")
	public String detail() throws Exception{
		if(trade_id !=null && !trade_id.equals("")){
			//实例化套餐信息对象
			combo = this.comboService.get(trade_id);
			//套餐商品标识串
			if(combo != null){
				//------------获取左边店铺相关信息开始
				String cust_id = combo.getCust_id();//会员标识
				//判断商品是否是运营商发布的，如果是详细页左边的数据为运营商
				if(cust_id.equals("0")){
					//取出当前商品的所属分类
					String cat_attr = combo.getCat_attr();
					//查找出相关分类
					getComboRalateCat(cat_attr);
				}else{//获取店铺配置
					shopconfig = this.shopconfigService.getByCustID(cust_id);
					//查看店铺是否关闭
					if(shopconfig == null){
						shopconfig = new Shopconfig();
					}
					//校验店铺
					if(verifyShop(shopconfig)){
						return null;
					}
					//发货地址
					ship_addr = shopconfig.getArea_attr();
					//地区ID转换为中文名称
					ship_addr = AreaFuc.getAreaNameByMap(ship_addr);
					ship_addr = ship_addr.replace(",", "");
					//加载店铺的头部信息
					getIndexTop(shopconfig.getCust_id());
					//热门收藏排行
					collectList=this.goodsService.getHotCollectList(shopconfig.getCust_id());
					//热门销售
					hotsaleList=this.goodsService.getHotSaleList(shopconfig.getCust_id(),"");
				    //获取自定义分类
				    getMembercat(cust_id);
					Map userMap = new HashMap();
					userMap.put("user_type","1");
					userMap.put("cust_id",cust_id);
					List list=this.memberuserService.getList(userMap);
					user_name=this.comboService.getUserName(list);
				}
				
				//------------获取左边店铺相关信息结束
				
				//------------获取商品相关信息开始
				Map goodsMap = new HashMap();
				goodsMap.put("goods_id_in", combo.getGoods_str());
				goodsList = this.goodsService.getWebList(goodsMap);
				//所有商品价格
				all_goods_price=this.comboService.allPrice(goodsList, all_goods_price);
				
				//获取商品自定义规格名称列表
				Map ssnMap = new HashMap();
				ssnMap.put("id_asc", "id_asc");
				selfspecnameList = this.selfspecnameService.getList(ssnMap);
				
				//获取全部自定义规格值列表
				Map ssvMap = new HashMap();
				ssnMap.put("id_asc", "id_asc");
				selfsepcvalueList = this.selfsepcvalueService.getList(ssvMap);
				
				//------------获取商品相关信息结束
			}
			
		}
		return goUrl("comboDetail");
	}
	
	/**
	 * 取出套餐相关的分类商品
	 * @author LHY
	 */
	public void getComboRalateCat(String cat_id){
		if(cat_id==null || cat_id.length()<=0){
			return;
		}
		Map comboMap = new HashMap();
		comboMap.put("cat_attr", cat_id);
		comboMap.put("start", 0);
		comboMap.put("limit", 8);
		comboRalateCatList = this.comboService.getList(comboMap);
	}
	
	public void prepare() throws Exception {
		
	}
}
