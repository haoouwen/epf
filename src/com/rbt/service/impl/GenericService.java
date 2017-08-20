/*
  
 
 * Package:com.rbt.service.impl
 * FileName: GenericService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IAudithistoryDao;
import com.rbt.dao.IAutofckDao;
import com.rbt.dao.IGenericDao;
import com.rbt.model.Audithistory;
import com.rbt.service.IGenericService;

/**
 * @function 功能 泛型业务通用接口实现类
 * @author  创建人 HXK
 * @date  创建日期 2014-11-25
 */

@Service
public class GenericService<T,PK> implements IGenericService<T,PK>{
	
	IGenericDao<T,PK> genericDao;
	
	@Autowired
	private IAutofckDao autofckDao;
	@Autowired
	public IAudithistoryDao audithistoryDao;
	
	public GenericService() {}

    public GenericService(IGenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:16:30 AM
	 * @Method Description :删除
	 */
	public boolean delete(PK id) {
		if(id==null || id.equals("")){
			return false;
		}
		int d=genericDao.delete(id);
		if(d==0)
			return false;
		else{
			return true;
		}
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:16:43 AM
	 * @Method Description :获取对象
	 */
	public T get(PK id) {
		return (T)genericDao.get(id);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:16:52 AM
	 * @Method Description :获取条数
	 */
	public int getCount(Map map) {
		return genericDao.getCount(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:17:04 AM
	 * @Method Description :获取数据列表
	 */
	public List getList(Map map) {
		return genericDao.getList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:17:14 AM
	 * @Method Description :新增对象
	 */
	public void insert(T t) {
		genericDao.insert(t);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2016 10:17:14 AM
	 * @Method Description :新增券对象
	 */
	public void insertcust(T t) {
		genericDao.insertcust(t);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:17:22 AM
	 * @Method Description :更新对象
	 */
	public void update(T t) {
		genericDao.update(t);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:17:35 AM
	 * @Method Description :新增对象成功后返回指定内容
	 */
	public String insertGetPk(T t){		
		return genericDao.insertGetPk(t);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:18:05 AM
	 * @Method Description :删除模块
	 */
    public void deleteByModel(String id){
    	//genericDao.deleteByModel(id);
    }
    
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 11:05:33 AM
	 * @Method Description :批量排序
	 */
    public boolean updateSort(String field_id,String field_sort,String ids,String vals){
		if(ids==null || vals==null){
			return false;
		}
		String[] sort_id= ids.split(",");
		String[] sort_val= vals.split(",");
		List sortList=new ArrayList();
		if(sort_id.length>0){
			for(int i=0;i<sort_id.length;i++){
				Map sortMap = new HashMap();
				if(sort_id[i].trim().equals("")){
					continue;
				}
				sortMap.put(field_id,sort_id[i].trim());
				//如果文本框为空则返回field_id
				if(ValidateUtil.isRequired(sort_val[i])){
					sortMap.put(field_sort,"0");
				}else{
					sortMap.put(field_sort,sort_val[i]);
				}
				sortList.add(sortMap);
			}
		}
    	genericDao.updateSort(sortList);
    	return true;
    }
    
    
    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:18:30 AM
	 * @Method Description : 增加FCK内容
	 */
    public void insertFckContent(String cust_id,String table_name,String table_id,String random_num){
    	Map fckMap = new HashMap();
		if (cust_id!= null && !"".equals(cust_id)) {
			fckMap.put("cust_id", cust_id);
		}
		if (table_id != null && !"".equals(table_id)) {
			fckMap.put("table_id", table_id);
		}
		fckMap.put("table_name", table_name);
		if (random_num != null && !"".equals(random_num)) {
			fckMap.put("random_num", random_num);
		}
		autofckDao.updaterandom(fckMap);
    }
    
    /**
	 * @author : LJQ
	 * @date : May 3, 2014 2:29:26 PM
	 * @Method Description :批量更新状态
	 */
	public boolean updateBatchState(String ids,String state,String field_id,String field_state){
		if(ids==null || ids.equals("")){
			return false;
		}
		String[] id = ids.split(",");
		List stateList = new ArrayList();
		if (id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				Map userMap = new HashMap();
				userMap.put(field_state, state);
				userMap.put(field_id, id[i].trim());
				stateList.add(userMap);
			}
		}
		genericDao.updateBatchState(stateList);
		return true;
	}
    
    
    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:26:59 PM
	 * @Method Description :审核数据流
	 */
    public void insertAudit(String table_name,String info_id,String info_state,String no_reason,String session_cust_id,
    		String session_user_id,String session_user_name){
    	Audithistory ah = new Audithistory();
    	ah.setCust_id(session_cust_id);
		ah.setUser_id(session_user_id);
		ah.setUser_name(session_user_name);
		ah.setInfo_state(info_state);
		ah.setNo_reason(no_reason);
		ah.setModule_type(table_name);
		ah.setInfo_id(info_id);
		this.audithistoryDao.insert(ah);
    }
    
    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:16:30 AM
	 * @Method Description :删除通过  订单ID
	 */
	public void deleteByOrderIds(String id) {
		genericDao.deleteByOrderIds(id);
	}
    
}
