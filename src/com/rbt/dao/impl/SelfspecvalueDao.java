/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SelfsepcvalueDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISelfspecvalueDao;
import com.rbt.model.Selfspecvalue;

/**
 * @function 功能  自定义规格值dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:33:30 CST 2014
 */
@Repository
public class SelfspecvalueDao extends GenericDao<Selfspecvalue,String> implements ISelfspecvalueDao {
	
	public SelfspecvalueDao() {
		super(Selfspecvalue.class);
	}
	public void deleteByspecSerialId(String spec_serial_id) {
		this.getSqlMapClientTemplate().delete("selfspecvalue.deleteByspecSerialId", spec_serial_id);
	}
}

