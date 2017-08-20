/*
 
 * Package:com.rbt.dao.impl
 * FileName: InfocountDao.java 
 */
package com.rbt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IInfocountDao;
import com.rbt.model.Infocount;

/**
 * @function 功能  数据统计dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Wed Jan 30 10:28:21 CST 2014
 */
@Repository
public class InfocountDao extends GenericDao<Infocount,String> implements IInfocountDao {
	
	public InfocountDao() {
		super(Infocount.class);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:55:09 AM
	 * @Method Description :标题唯一性的验证
	 */
	public int getRepeatTitle(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("infocount.getrepeattitle", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	

	
	/* (non-Javadoc)
	 * @see com.rbt.dao.InfocountDao#getSevenDaysList(java.util.Map)
	 */
	public List getSevenDaysList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getSevenDaysList",map);
	}
	
	public List getTotalList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getTotalList",map);
	}
	
	public List getRankingList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getRankingList",map);
	}
	
	public List getBuycountList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getBuycountList",map);
	}
	
	public List getOrdernumList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getOrdernumList",map);
	}
	
	public List getMembernumList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getMembernumList",map);
	}
	
	public List getMoneyList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("infocount.getMoneyList",map);
	}
	
	public int getRankingCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getRankingCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
	public int getMoneyCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getMoneyCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
	public int getBuyCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getBuyCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	/**
	 * 会员总数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalMember(Map map)throws Exception{
		HashMap totalMemberMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getTotalMember");
		return totalMemberMap;
	}
	/**
	 * 新增会员数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getGrowMember(Map map) throws Exception{
		HashMap growMemberMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getGrowMember",map);
		return growMemberMap;
	}
	
	/**
	 * 区域会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAreaAmount(Map map) throws Exception{
		HashMap totalAmountMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getTotalAreaAmount",map);
		return totalAmountMap;
	}
	
	/**
	 * 会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAmount(Map map) throws Exception{
		HashMap totalAmountMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getTotalAmount",map);
		return totalAmountMap;
	}
	
	/**
	 * 被会员消费的商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalGoods(Map map) throws Exception{
		HashMap totalGoodsMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getTotalGoods",map);
		return totalGoodsMap;
	}
	
	/**
	 * 被会员消费的同类商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalCatGoods(Map map) throws Exception{
		HashMap totalCatGoodsMap = (HashMap) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getTotalCatGoods",map);
		return totalCatGoodsMap;
	}
	
	/**
	 * 销售统计的数量
	 * @param map
	 * @return
	 */
	public int getOperationCount(Map map) throws Exception{
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getOperationCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
	/**
	 * 按时间和地区销售统计
	 * @param map
	 * @return
	 */
	public List getSalesAmountList(Map map) throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getSalesAmountList",map);
	}
	
	/**
	 * 按商品种类
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByCat(Map map)throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getCountSalesByCat",map);
	}
	
	
	/**
	 * 按地区统计
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByArea(Map map)throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getCountSalesByArea",map);
	}
	
	/**
	 * 按商品种类
	 * @return
	 * @throws Exception
	 */
	public List getSalesByCatList(Map map)throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getSalesByCatList",map);
	}
	public int getSaleByCatCount(Map map)throws Exception {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getSaleByCatCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
	
	/**
	 * 按购买量排序统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByPurchases(Map map)throws Exception{
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getCountSalesByPurchases",map);
	}
	public int getCountByPurchases(Map map) throws Exception{
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getCountByPurchases", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
}

