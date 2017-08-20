/*
  
 
 * Package:com.rbt.dao
 * FileName: IAreaDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Area;

/**
 * @function 功能 地区管理dao层接口
 * @author 创建人 HXK
 * @date 创建日期 Jun 28, 2014
 */
public interface IAreaDao extends IGenericDao<Area,String> {

	/**
	 * @MethodDescribe 方法描述   找出地区表中不在索引记录表中的数据
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 12, 2014 1:05:46 PM
	 */
	@SuppressWarnings("unchecked")
	public List getAreaIndexList(Map map);
	/**
	 * 方法描述：单个地区排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateOneAreaSortNo(Map map);
	/**
	 * @MethodDescribe 方法描述    前台的地区列表
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 29, 2014 4:41:02 PM
	 */
	public List getWebAreaList(Map map);
	
	/**
	 * @MethodDescribe 方法描述 取出所有地区
	 * @author  创建人  HXK
	 * @date  创建日期  2014-08-31
	 */
    public List getAll();
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : LJQ
	 * @date : Nov 1, 2014 10:51:08 AM
	 */
    public List getWebAreaIndexList(Map map);
    
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : CYC
	 * @date : Nov 1, 2014 10:51:08 AM
	 */
    public List getAreaCityList(Map map);
    
    /**
	 * @Method Description : 根据地区ID获取
	 * @author : CYC
	 * @date : Nov 1, 2014 10:51:08 AM
	 */
    public List getCharacterList(Map map);
    /**
	 * @Method Description : 根据国家
	 * @author : CYC
	 * @date : Nov 1, 2014 10:51:08 AM
	 */
    public List getCountryList(Map map);
    
    /**
	 * @MethodDescribe 方法描述   获取特殊area_id集合
	 * @author  创建人  LHY
	 * @date  创建日期  2014 
	 */
	public List getDeleteList(Map map);
}

