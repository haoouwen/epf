/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SendboxService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberDao;
import com.rbt.dao.IReceiveboxDao;
import com.rbt.dao.ISendboxDao;
import com.rbt.model.Receivebox;
import com.rbt.model.Sendbox;
import com.rbt.service.ISendboxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录发件箱信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:36:28 CST 2014
 */
@Service
public class SendboxService extends GenericService<Sendbox,String> implements ISendboxService {
	
	ISendboxDao sendboxDao;
	@Autowired
	IMemberDao memberDao;
	@Autowired
	IReceiveboxDao receiveboxDao;
	@Autowired
	public SendboxService(ISendboxDao sendboxDao) {
		super(sendboxDao);
		this.sendboxDao = sendboxDao;
	}
	
	public void deletelogic(HashMap map){
		this.sendboxDao.deletelogic(map);
	}

	public List getDelSend() {
		return this.sendboxDao.getDelSend();
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:00:20 AM
	 * @Method Description :获取可发送的会员id
	 */
	public Map getCustStr(String[] cust_name_str,String send_cust_id) {
		String get_cust_id_str="";
		Map idMap=new HashMap();
		idMap.put("send_cust_name", "");
		idMap.put("get_cust_id_str", "");
		for (int i = 0; i < cust_name_str.length; i++) {
			if (cust_name_str[i].trim() != null&& !cust_name_str[i].trim().equals("")) {
				Map listMap = new HashMap();
				listMap.put("cust_id_name", cust_name_str[i].trim());
				List list = memberDao.getList(listMap);
				if (list != null && list.size() == 0) {
					idMap.put("send_cust_name", "该会员"+ cust_name_str[i] + "不存在，请重新输入!");
					return idMap;
				} else {
					HashMap map = (HashMap) list.get(0);
					if (map.get("cust_id") != null) {
						// 判断会员是否给自己发送站内信，如果不是加入需要发送的cust_id 串中
						if (map.get("cust_id").toString().equals(send_cust_id)) {
							idMap.put("send_cust_name", "您不能给自己发站内信!");
							return idMap;
						}
						get_cust_id_str += map.get("cust_id").toString() + ",";
					}
				}
			}
		}
		idMap.put("get_cust_id_str", get_cust_id_str);
		return idMap;
	}

	public void setReceivebox(String get_cust_id_str, String send_id) {
		String[] get_cust_ids = get_cust_id_str.split(",");
		for (int i = 0; i < get_cust_ids.length; i++) {
			if (get_cust_ids[i].trim() != null&& !get_cust_ids[i].trim().equals("")) {
				Receivebox receivebox = new Receivebox();
				receivebox.setSend_id(send_id);
				receivebox.setGet_cust_id(get_cust_ids[i].trim());
				receivebox.setIs_get_del("1");// 1为未删除状态
				receivebox.setIs_read("1");// 1为未读状态
				receiveboxDao.insert(receivebox);
			}
		}
		
	}


	
}

