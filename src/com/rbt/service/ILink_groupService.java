/*
  
 
 * Package:com.rbt.service
 * FileName: ILink_groupService.java 
 */

package com.rbt.service;

import java.util.List;
import com.rbt.model.Link_group;;

/**
 * @function 功能  友情链接业务层接口
 * @author  创建人 CYC
 * @date  创建日期  Jun 29, 2014
 */
public interface ILink_groupService extends IGenericService<Link_group,String>{	
	/**
	 * 方法描述：批量修改友情链接分组
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateLinkgroup_name(List list);
	
	/**
	 * 方法描述：批量修改友情链接分组
	 * @return
	 * @throws Exception
	 */
	public List updateGroupname(String id,String name);
}
