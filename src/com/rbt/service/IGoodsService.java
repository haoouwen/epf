/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.solr.common.params.ModifiableSolrParams;
import com.rbt.model.Goods;
import com.rbt.model.Selfextendattr;
import com.rbt.model.Selfparagroup;
import com.rbt.model.Selfparavalue;
/**
 * @function 功能 记录商品表信息Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Tue Jan 15 10:28:08 CST 2014
 */

public interface IGoodsService extends IGenericService<Goods,String>{
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:05 PM
	 * @Method Description :查询前台条数
	 */
	public int getWebCount(Map map);
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:21 PM
	 * @Method Description :查询前台列表
	 */
	public List getWebList(Map map);
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:03:52 PM
	 * @Method Description :获取热门收藏
	 */
	public List getsumList(Map map);
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:03:52 PM
	 * @Method Description :热销商品数据
	 */
	
	public List getHotSaleList(Map map);
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:36 PM
	 * @Method Description :上下架管理
	 */
	public boolean updateIsup(Goods goods,String state); 
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:04:36 PM
	 * @Method Description :商品逻辑删除
	 */
	public boolean updateIsdel(String  chb_id,String state);
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 9:57:34 AM
	 * @Method Description :添加修改商品数据
	 */
	public void insertGoods(String goods_id, Goods goods, String random_num, List goodsattrList, String goods_item_str, 
			String specstr_str, String market_price_str, String sale_price_str, String cost_price_str, String stock_str, String volume_str, String logsweight_str, String up_goods_str, String self_goods_size_value,
			String self_goods_img_value, String self_goods_relate_img_value, String self_goods_sort_value, String self_size_id, String sel_spec_name,
			String is_must_delete_spec, String sel_spec_count, String goods_up_str, String goods_down_str, Selfextendattr selfextendattr,
			String ex_attr_value, Selfparagroup selfparagroup, String para_num, Selfparavalue selfparavalue, String slef_para_value,String reason,String session_cust_id,String session_user_id,String session_user_name,Map<String,String> commonMap); 

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:15:41 PM
	 * @Method Description :更新方法
	 */
	public void updateGoods(String goods_id, Goods goods, String random_num, List goodsattrList, String goods_item_str, 
			String specstr_str, String market_price_str, String sale_price_str, String cost_price_str, String stock_str, String volume_str, String logsweight_str, String up_goods_str, String self_goods_size_value,
			String self_goods_img_value, String self_goods_relate_img_value, String self_goods_sort_value, String self_size_id, String sel_spec_name,
			String is_must_delete_spec, String sel_spec_count, String goods_up_str, String goods_down_str, Selfextendattr selfextendattr,
			String ex_attr_value, Selfparagroup selfparagroup, String para_num, Selfparavalue selfparavalue, String slef_para_value,String reason,String session_user_id,String session_user_name,Map<String,String> commonMap); 
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 2:00:00 PM
	 * @Method Description :更新审核方法
	 */
	public void updateAuditState(Goods goods,String reason,String session_cust_id,String session_user_id,String session_user_name);
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 5:27:43 PM
	 * @Method Description : 新增虚拟商品
	 */
	public void insertVirtualGoods(Goods goods,String goods_up_str,String goods_down_str,String session_cust_id,String session_user_id,
			String session_user_name,String random_num,String reason,Double market_price_str,Double cost_price_str,Double sale_price_str);
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:10:27 PM
	 * @Method Description :更新虚拟商品
	 */
	public void updatetVirtualGoods(Goods goods,String goods_up_str,String goods_down_str,String session_cust_id,String session_user_id,
			String session_user_name,String random_num,String reason);
	/**
	 * @Method Description :通过主键查找未删除商品
	 * @author : HZX
	 * @date : Apr 19, 2014 10:57:55 AM
	 */
	public Goods getByPkNoDel(String goods_id);
	
	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:22:44 PM
	 * @Method Description :获取所有商品
	 */
	public List getAll(Map map);
	
	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:22:53 PM
	 * @Method Description : 获取热门收藏排行
	 */
	public List getHotCollectList(String cust_id);
	
	/**
	 * @author : LJQ
	 * @date : May 9, 2014 5:26:15 PM
	 * @Method Description :热门销售
	 */
	public List getHotSaleList(String cust_id,String cat_attr);
	
	/**
	 * @author : LHY
	 * @date : Feb 26, 2014 10:53:52 AM
	 * @Method Description : 根据不同的商品类型，获取不同的数据
	 */
	public List getGoodsLabList(String cust_id,String label,int limit);
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 2:59:30 PM
	 * @Method Description：获取商品信息列表
	 */
	public Map getGoodsList(String catEn_name,String catId,StringBuilder postsb);
	
	/**
	 * @author : HZX
	 * @throws UnsupportedEncodingException
	 * @date : Jun 24, 2014 10:47:45 AM
	 * @Method Description :solr索引
	 */
	public ModifiableSolrParams toSolrIndex(Map map) throws UnsupportedEncodingException;
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 4:30:38 PM
	 * @Method Description：运费处理
	 */
	public List hualFeer(String goods_id_str,String buy_num_str,String end_area_attr,String spec_id_str);
	/**
	 * @author : WXP
	 * @param :ship_id,buy_num,volume,logsweight,end_area
	 * @date Apr 11, 2014 3:50:14 PM
	 * @Method Description :处理运费
	 */
	public  List dealShipPrice(String is_ship_str, String ship_id_str,
			String buy_num_str, String volume_str, String logsweight_str,
			String end_area);
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @author：XBY
	 * @date：Feb 17, 2014 5:01:04 PM
	 * @Method Description：购物车处理
	 */
	public void dealCart(Cookie[] cookies,String session_cust_id) throws UnsupportedEncodingException;
	
	/**
	 * @author : WXP
	 * @param :ship_id,smode_id
	 * @date Apr 7, 2014 3:28:28 PM
	 * @Method Description :获取某个配送方式的默认运费计算方式
	 */
	public String getshiparea(String uparea);
	
	/**
	 * @author : WXP
	 * @param :specstr
	 *            商品规格串
	 * @date Feb 26, 2014 10:35:17 AM
	 * @Method Description :获取商品属性
	 */
	public List getGoodsAttr(String specstr,String goods_id);
	
	/**
	 * @author:HXM
	 * @date:May 6, 201410:52:42 AM
	 * @param:
	 * @Description:修改商品的活动状态
	 */
	public void updateActiveState(Map map);

	/**
	 * @author:QJY
	 * @date:2015-08-24
	 * @param:map
	 * @Description:更新商品的销售数量
	 */
	public void updateSalesVolume(Map map) throws Exception;
	
	public String dealCartApp(List josnlist, String session_cust_id) throws UnsupportedEncodingException;
	
	/**
	 * 导入商品
	 * @param iname
	 * @return
	 * @throws IOException
	 */
	public boolean importGoods(String iname) throws IOException;
	
	/**
	 * 导入跨境通商品
	 * @param iname
	 * @return
	 * @throws IOException
	 */
	public boolean importKtjGoods(String iname) throws IOException;
	
	/**
	 * KJT导出商品
	 * @param goodsList
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean exportGoods(List goodsList, HttpServletResponse response) throws Exception;
	
	/**
	 * 导出商品
	 * @param goodsList
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean exprotExcel(List exList, HttpServletResponse response) throws IOException, WriteException;
	
	/**
	 * @Method Description :物理删除商品
	 * @author: HXK
	 * @date : Oct 12, 2015 2:24:10 PM
	 * @param 
	 * @return return_type
	 */
	public boolean physicalDeleteGoods(String goodsIdStr) throws IOException;
	/**
	 * 导入商品,检查商品数据是否合格
	 * @throws BiffException 
	 */
	public  String importCheckGoods(String file) throws IOException, BiffException;
	/**
	 * @author : yu
	 * @date : 2016-1-20
	 * @Method Description : 根据订单获取商品的详细信息
	 */
	public List getInfoByOrder(String order_ids);
	/**
	 * @Method Description :根据商品ID 查找商品楼层标签是否存在该商品
	 * @author: 胡惜坤
	 * @date : Mar 15, 2016 9:00:58 AM
	 * @param goods_id：商品ID串
	 * @return 商品编号
	 */
	public String rgGoodsInFloor(String goods_id);
	/**
	 * 导出商品
	 * @param goodsList 商品集合
	 * @param response 
	 * @return
	 * @throws IOException
	 */
	public boolean exportGoodsPic(List goodsList, HttpServletResponse response) throws Exception;
	/**
	 * @author : yu
	 * @date : 2016-5-9 11:14:25
	 * @Method Description :首页换一换
	 */
	public List getRandGoodsList(Map map);
	
}


