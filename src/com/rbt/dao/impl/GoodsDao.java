/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IGoodsDao;
import com.rbt.model.Goods;
/**
 * @function 功能  记录商品表信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 15 10:28:08 CST 2014
 */
@Repository
public class GoodsDao extends GenericDao<Goods,String> implements IGoodsDao {
	
	
	public GoodsDao() {
		super(Goods.class);
	}
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goods.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goods.getWebList",map);
	}
	public List getsumList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goods.getsumList",map);
	}
	public List gethotsaleList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goods.getsaleList",map);
	}
	public void updateIsup(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("goods.updateisup", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	//批量逻辑删除
	public void updateIsdel(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("goods.updateisdel", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * @Method Description :通过主键查找未删除商品
	 * @author : HZX
	 * @date : Apr 19, 2014 11:01:04 AM
	 */
	public Goods getByPkNoDel(String goods_id) {
		return  (Goods)this.getSqlMapClientTemplate().queryForObject("goods.getByPkNoDel", goods_id);
	}
	public List getAll(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goods.getAll",map);
	}
	/**
	 * @author:HXM
	 * @date:May 6, 201410:32:26 AM
	 * @param:
	 * @Description:修改商品的活动状态
	 */
	public void updateActiveState(Map map){
		this.getSqlMapClientTemplate().update("goods.updateActiveState",map);
	}
	
	/**
	 * @author:QJY
	 * @date:2015-08-24
	 * @param:map
	 * @Description:更新商品的销售数量
	 */
	public void updateSalesVolume(Map map) throws Exception{
		this.getSqlMapClientTemplate().update("goods.updateSalesVolume",map);
	}
	/**
	 * 根据订单编号查询商品详情
	 * @author:yu
	 * @date:2016年1月20日
	 * */
	public List getInfoByOrder(String order_ids){
		return this.getSqlMapClientTemplate().queryForList("goods.getInfoByOrder",order_ids);
	}
	
	/**
	 * @author : yu
	 * @date : 2016-5-9 11:14:25
	 * @Method Description :首页换一换
	 */
	public List getRandGoodsList(Map map){
		return this.getSqlMapClientTemplate().queryForList("goods.getRandGoodsList",map);
	}
}


