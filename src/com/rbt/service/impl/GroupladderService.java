/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GroupladderService.java 
 */
package com.rbt.service.impl;
import com.rbt.dao.IGroupladderDao;
import com.rbt.model.Groupladder;
import com.rbt.service.IGroupladderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 团购阶梯价格Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Mon Apr 15 17:26:40 CST 2014
 */
@Service
public class GroupladderService extends GenericService<Groupladder,String> implements IGroupladderService {
	
	IGroupladderDao groupladderDao;

	@Autowired
	public GroupladderService(IGroupladderDao groupladderDao) {
		super(groupladderDao);
		this.groupladderDao = groupladderDao;
	}
	public Groupladder getByGroupID(String group_id){
		return  (Groupladder) this.groupladderDao.getByGroupID(group_id);
	}
	public void deleteGroupID(String id) {
		this.groupladderDao.deleteGroupID(id);
		
	}
}

