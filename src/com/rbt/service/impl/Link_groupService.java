/*
  
 
 * Package:com.rbt.service.impl
 * FileName: Link_groupService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ILink_groupDao;
import com.rbt.model.Link_group;
import com.rbt.service.ILink_groupService;

@Service
public class Link_groupService extends GenericService<Link_group,String> implements ILink_groupService {

	
	ILink_groupDao link_groupDao;

	@Autowired
	public Link_groupService(ILink_groupDao link_groupDao) {
		super(link_groupDao);
		this.link_groupDao = link_groupDao;
	}

	/**
	 * 批量更新List_group name字段
	 */
	public void updateLinkgroup_name(List list) {
		this.link_groupDao.updateLinkgroup_name(list);
	}
	
	/**
	 * 方法描述：批量修改友情链接分组
	 * @return
	 * @throws Exception
	 */
	public List updateGroupname(String id,String name){
		id = id.replace(" ", "");
		name=name.replace(" ", "");
		String linkidStr[] = id.split(",");
		String linkNameStr[] = name.split(",");
		List linkgroupList = new ArrayList();
		if(linkidStr.length>0){
			for(int i=0;i<linkidStr.length;i++){
				Map linkMap = new HashMap();
				linkMap.put("name", linkNameStr[i]);
				linkMap.put("id", linkidStr[i]);
				linkgroupList.add(linkMap);
			}
		}
		return linkgroupList;
	}

}
