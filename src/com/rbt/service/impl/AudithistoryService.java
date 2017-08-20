/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AudithistoryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import com.rbt.dao.IAudithistoryDao;
import com.rbt.model.Audithistory;
import com.rbt.service.IAudithistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录模块审核历史Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Tue Nov 15 10:35:16 CST 2014
 */
@Service
public class AudithistoryService  extends GenericService<Audithistory,String>  implements IAudithistoryService {

	/*
	 * 记录模块审核历史Dao层接口
	 */
	IAudithistoryDao audithistoryDao;

	@Autowired
	public AudithistoryService(IAudithistoryDao audithistoryDao) {
		super(audithistoryDao);
		this.audithistoryDao = audithistoryDao;
	}
	public List getAuditList(Map map){
		return this.audithistoryDao.getAuditList(map);
	}

}

