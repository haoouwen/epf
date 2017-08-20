/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MembercatService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IMembercatDao;
import com.rbt.model.Membercat;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMembercatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员自定义分类信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 11 09:28:06 CST 2014
 */
@Service
public class MembercatService extends GenericService<Membercat,String> implements IMembercatService {
	
	IMembercatDao membercatDao;

	@Autowired
	public MembercatService(IMembercatDao membercatDao) {
		super(membercatDao);
		this.membercatDao = membercatDao;
	}

	public List getDeleteList(Map map) {
		return this.membercatDao.getDeleteList(map);
	}

	public List getAll() {
		return this.membercatDao.getAll();
	}
	@Autowired
	IGoodsService goodsService;//商品
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 4:44:12 PM
	 * @Method Description :递归删除
	 */
	public boolean dealDelete(String id,String session_cust_id) {
		if(id==null || id.equals(""))
			return false;
		String chhid_id="";
		List list=new ArrayList();
		String[] str_id=id.split(",");
		for (int i = 0; i < str_id.length; i++) {
			if(str_id[i].trim().equals("")){
				return false;
			}
			Map chiMap=new HashMap();
			chiMap.put("parent_cat_id",str_id[i]);
			chiMap.put("cust_id",session_cust_id);
			//获取所有上级id为当前id的栏目
			List chi_list=this.membercatDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if(chi_list!=null && chi_list.size()>0){
				Map listMap=null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap=(HashMap) chi_list.get(j);
					if(listMap.get("cat_id_str")!=null){
						chhid_id+=listMap.get("cat_id_str")+",";
					}
				}
			}
		}
		//判断是否最后一个字符是否为逗号，是则删除
		if(chhid_id.lastIndexOf(",")>0){
			chhid_id=chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		//删除ID
		this.membercatDao.delete(id);
		dealDelete(chhid_id,session_cust_id);
		return true;
	}
	/**
	 * @Method Description :删除分类前，先判断是否已经有商品绑定了，如果没有可以删除，否则不让删除
	 * @author: HXK
	 * @date : May 29, 2014 6:16:07 PM
	 * @param 
	 * @return return_type
	 */
    public boolean memCatGoods(String cat_id,String cust_id){
    	boolean is_del=true;
    	if(cat_id!=null&&!cat_id.equals("")){
            	List infoList=new ArrayList ();
        		HashMap infoMap =new HashMap ();
        		infoMap.put("self_cat", cat_id);
        		infoMap.put("cust_id", cust_id);
        		infoMap.put("is_del", "1");
            		//商品
        		infoList=goodsService.getList(infoMap);
        		if(infoList!=null&&infoList.size()>0){
        			is_del=false;
        		}
            }
    	
    	return is_del;
    }
	

	
	
}

