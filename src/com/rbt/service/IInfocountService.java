/*
  
 
 * Package:com.rbt.servie
 * FileName: IInfocountService.java 
 */
package com.rbt.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Infocount;

/**
 * @function 功能 数据统计Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Wed Jan 30 10:28:21 CST 2014
 */

public interface IInfocountService extends IGenericService<Infocount,String>{
	/**
	 * @MethodDescribe 方法描述   获取标题数
	 * @author  创建人  HXK
	 * @date  创建日期  Sep 2, 2014 9:06:31 AM
	 */
	@SuppressWarnings("unchecked")
	public int getRepeatTitle(Map map);

	 /**
	 * @MethodDescribe 方法描述    统计最近7天的数据
	 * @author  创建人  HXK
	 * @date  创建日期  Aug 26, 2014 2:34:18 PM
	 */
	public List getSevenDaysList(Map map);
	public List getTotalList(Map map);
	public List getRankingList(Map map);
	public List getBuycountList(Map map);
	public List getOrdernumList(Map map);
	public List getMembernumList(Map map);
	public List getMoneyList(Map map);
	/**
	 * 查询记录数量
	 */
	public int getRankingCount(Map<String,String> map);
	/**
	 * 查询记录数量
	 */
	public int getMoneyCount(Map<String,String> map);
	/**
	 * 查询记录数量
	 */
	public int getBuyCount(Map<String,String> map);
	
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 12:53:43 PM
	 * @Method Description：
	 */
	public Map sevendays(List infocountList,String sevendatatime,String sevengoodsorder);
	
	/**
	 * 方法描述：获取日期数据字符串
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map getinfo(List infototalList,double[] elements,String x_axis,double max,double steps);
	
	/**
	 * 导出数据
	 * @param list
	 * @throws Exception
	 */
	public void exportExcel(List list,String count_type)throws Exception;
	
	/**
	 * 会员总数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalMember(Map map)throws Exception;
	
	/**
	 * 新增会员数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getGrowMember(Map map) throws Exception;
	
	/**
	 * 区域会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAreaAmount(Map map) throws Exception;
	
	/**
	 * 会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAmount(Map map) throws Exception;
	/**
	 * 被会员消费的商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalGoods(Map map) throws Exception;
	/**
	 * 被会员消费的同类商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalCatGoods(Map map) throws Exception;
	
	/**
	 * 销售统计的数量
	 * @param map
	 * @return
	 */
	public int getOperationCount(Map map) throws Exception;
	
	/**
	 * 销售统计
	 * @param map
	 * @return
	 */
	public List getSalesAmountList(Map map) throws Exception;
	
	/**
	 * 按商品种类
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByCat(Map map)throws Exception;
	
    public List getSalesByCatList(Map map)throws Exception;
	
	public int getSaleByCatCount(Map map)throws Exception;
	
	/**
	 * 按地区统计
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByArea(Map map)throws Exception;
	
	/**
	 * 按购买量排序统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByPurchases(Map map)throws Exception;
	public int getCountByPurchases(Map map) throws Exception;
}

