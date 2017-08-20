/*
  
 
 * Package:com.rbt.dao
 * FileName: ILink_groupDao.java 
 */
package com.rbt.dao;
import java.util.List;
import com.rbt.model.Link_group;;

/**
 * @function 功能 友情链接dao层接口
 * @author  创建人 CYC
 * @date  创建日期  Jun 29, 2014
 */
public interface ILink_groupDao extends IGenericDao<Link_group,String>{

	/**
	 * 方法描述：批量修改友情链接分组值
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateLinkgroup_name(List lists);
}
