/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsaskService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.ICommparaDao;
import com.rbt.dao.IGoodsaskDao;
import com.rbt.dao.IMemberDao;
import com.rbt.model.Goodsask;
import com.rbt.model.Member;
import com.rbt.service.IGoodsaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品咨询信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Fri Mar 23 11:24:44 CST 2014
 */
@Service
public class GoodsaskService extends GenericService<Goodsask,String> implements IGoodsaskService {
	
	IGoodsaskDao goodsaskDao;
    @Autowired
	ICommparaDao commparaDao;
	@Autowired
    IMemberDao memberDao;
	@Autowired
	public GoodsaskService(IGoodsaskDao goodsaskDao) {
		super(goodsaskDao);
		this.goodsaskDao = goodsaskDao;
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 4:17:40 PM
	 * @Method Description：替换
	 */
	public void replaceList(List goodsaskList,Member member){
		if(goodsaskList!=null&&goodsaskList.size()>0){
			for(int i=0;i<goodsaskList.size();i++){
				Map valueMap=(Map) goodsaskList.get(i);
				String c_type = "",re_cust_id="";
				//替换咨询类型ID为咨询类型名称
				if(valueMap.get("c_type")!=null){
					c_type = valueMap.get("c_type").toString();
				}
				//替换回复者ID为回复者名称或商铺名称
				if(valueMap.get("re_cust_id")!=null){
					re_cust_id = valueMap.get("re_cust_id").toString();
				}
				Map postMap = new HashMap();
				postMap.put("para_value", c_type);
				postMap.put("para_code", "c_type");
				List commparaList=this.commparaDao.getList(postMap);
				String value="",namevalue="";
				if(commparaList!=null&&commparaList.size()>0){
				   Map paramap=(Map)commparaList.get(0);
				   value=paramap.get("para_key").toString();
				}
				if(!"".equals(re_cust_id)){
				    member=memberDao.get(re_cust_id);
				}
				if(member!=null){
					namevalue=member.getCust_name();
				}
				valueMap.put("re_cust_id",namevalue);
				valueMap.put("c_type", value);
			}
		}
	}
}

