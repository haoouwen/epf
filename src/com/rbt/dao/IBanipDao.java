/*
  
 
 * Package:com.rbt.dao
 * FileName: IBan_IpDao.java 
 */
package com.rbt.dao;
import java.util.List;
import com.rbt.model.Banip;
/**
 * @function 功能 禁止IP管理dao层接口
 * @author  创建人 HXK
 * @date  创建日期  July 5, 2014
 */
public interface IBanipDao extends IGenericDao<Banip,String>
{
	/**
	 * 方法描述：批量IP
	 * @param pk
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public void updateAllIp(List lists);

}
