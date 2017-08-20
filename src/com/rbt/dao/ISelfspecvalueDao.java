/*
  
 
 * Package:com.rbt.dao
 * FileName: ISelfsepcvalueDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfspecvalue;

/**
 * @function 功能 自定义规格值dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Wed Jan 30 15:33:30 CST 2014
 */

public interface ISelfspecvalueDao extends IGenericDao<Selfspecvalue,String>{
	public void deleteByspecSerialId(String spec_serial_id);
}

