/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: BusimesService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IBusimesDao;
import com.rbt.dao.IMemberDao;
import com.rbt.model.Busimes;
import com.rbt.model.Member;
import com.rbt.service.IBusimesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商家留言信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Fri Mar 30 12:29:33 CST 2014
 */
@Service
public class BusimesService extends GenericService<Busimes,String> implements IBusimesService {
	
	IBusimesDao busimesDao;
    @Autowired
	IMemberDao memberDao;
	@Autowired
	public BusimesService(IBusimesDao busimesDao) {
		super(busimesDao);
		this.busimesDao = busimesDao;
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 10:06:49 AM
	 * @Method Description：把回复者ID替换回复者商铺名称
	 */
	public List replaceList(List busimesList,Member member){
		if(busimesList!=null&&busimesList.size()>0){
			for(int i=0;i<busimesList.size();i++){
				Map valueMap=(Map) busimesList.get(i);
				String re_name = "";
				if(valueMap.get("get_user_id")!=null){
					re_name = valueMap.get("get_user_id").toString();
					member=memberDao.get(re_name);
				}
				if(member!=null){
					valueMap.put("get_user_id", member.getCust_name());
				}
			}
		}
		return busimesList;
	}
}

