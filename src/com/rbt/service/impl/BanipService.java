/*
  
 
 * Package:com.rbt.service.impl
 * FileName: Ban_IpService.java 
 */

package com.rbt.service.impl;

import com.rbt.service.IBanipService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IBanipDao;
import com.rbt.model.Banip;;
/**
 * @function 功能 禁止IP管理业务层接口实现
 * @author  创建人 HXK
 * @date  创建日期  July 5, 2014
 */
@Service
public class BanipService extends GenericService<Banip,String> implements IBanipService {
	
	/**
	 * 禁止IPdao实现层
	 */
	 IBanipDao ban_ipDao;

	@Autowired
	public BanipService(IBanipDao ban_ipDao) {
		super(ban_ipDao);
		this.ban_ipDao = ban_ipDao;
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:05:46 PM
	 * @Method Description：修改方法
	 */
	public void updateAllIp(List list) {
		this.ban_ipDao.updateAllIp(list);
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:06:51 PM
	 * @Method Description：构造ipList
	 */
	public List banipidList(String banipid,String banip,String user_id){
		banipid = banipid.replace(" ", "");
		banip = banip.replace(" ", "");
		String banipidStr[] = banipid.split(",");
		String banipStr[] = banip.split(",");
		List banipidList = new ArrayList();
		if (banipidStr.length > 0) {
			for (int i = 0; i < banipidStr.length; i++) {
				Map banipMap = new HashMap();
				banipMap.put("ip_id", banipidStr[i]);
				banipMap.put("ip", banipStr[i]);
				banipMap.put("user_id", user_id);
				banipidList.add(banipMap);
			}
		}
		return banipidList;
	}
}
