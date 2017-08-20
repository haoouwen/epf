/*
 
 * Package:com.rbt.dao.impl
 * FileName: ConsultationDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IConsultationDao;
import com.rbt.model.Consultation;

/**
 * @function 功能  记录商品咨询信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:47:46 CST 2014
 */
@Repository
public class ConsultationDao extends GenericDao<Consultation,String> implements IConsultationDao {
	
	public ConsultationDao() {
		super(Consultation.class);
	}

	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("consultation.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("consultation.getWebList",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:37:14 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("consultation.getListByGroup",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map){
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("consultation.getGroupCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
}

