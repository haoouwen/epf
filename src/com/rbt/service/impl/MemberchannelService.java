/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemberchannelService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberchannelDao;
import com.rbt.model.Memberchannel;
import com.rbt.service.IMemberchannelService;

/**
 * @function 功能 记录会员企业站栏目信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Fri Aug 26 16:21:41 CST 2014
 */
@Service
public class MemberchannelService extends GenericService<Memberchannel,String> implements IMemberchannelService {


	IMemberchannelDao memberchannelDao;

	@Autowired
	public MemberchannelService(IMemberchannelDao memberchannelDao) {
		super(memberchannelDao);
		this.memberchannelDao = memberchannelDao;
	}
	
	
	
	
	/**
	 * 批量更新是否显示
	 */
	public void updateisdis(List list) {
		this.memberchannelDao.updateState(list);

	}
	
	/**
	 * 批量更新sort_no排序字段
	 */
	public void updatechannel(String member_memberchannel_id,String member_sort,String member_name,String member_num) {
		String idStr[] = member_memberchannel_id.split(",");
		String sortStr[] = member_sort.split(",");
		String nameStr[] = member_name.split(",");
		String numStr[] = member_num.split(",");
		List channelList = new ArrayList();
		if (sortStr != null && sortStr.length > 0) {
			for (int i = 0; i < sortStr.length; i++) {
				Map channelMap = new HashMap();
				channelMap.put("ch_id", idStr[i].trim());
				channelMap.put("sort_no", sortStr[i].trim());
				channelMap.put("ch_name", nameStr[i].trim());
				channelMap.put("ch_num", numStr[i].trim());
				channelList.add(channelMap);
			}
		}
		this.memberchannelDao.updatechannel(channelList);
	}

}

