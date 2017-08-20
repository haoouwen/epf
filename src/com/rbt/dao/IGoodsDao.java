/*
  
 
 * Package:com.rbt.dao
 * FileName: IGoodsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goods;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录商品表信息dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Tue Jan 15 10:28:08 CST 2014
 */

public interface IGoodsDao extends IGenericDao<Goods,String>{
	
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	/**
	 * 方法描述：按照map中的条件找出商品信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
	//获取热门收藏
	public List getsumList(Map map);
	//热门销售
	public List gethotsaleList(Map map);
	public void  updateIsup(List list);
	public void  updateIsdel(List list);
	public Goods getByPkNoDel(String goods_id);//通过主键查找未删除商品
	public List getAll(Map map);
	/**
	 * @author:HXM
	 * @date:May 6, 201410:30:31 AM
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
	/**
	 * 根据订单编号查询商品详情
	 * @author:yu
	 * @date:2016年1月20日
	 * */
	public List getInfoByOrder(String order_ids);
	
	/**
	 * @author : yu
	 * @date : 2016-5-9 11:14:25
	 * @Method Description :首页换一换
	 */
	public List getRandGoodsList(Map map);
}

