/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ReceiveboxService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IReceiveboxDao;
import com.rbt.dao.IRecycleDao;
import com.rbt.dao.ISendboxDao;
import com.rbt.model.Receivebox;
import com.rbt.model.Recycle;
import com.rbt.model.Sendbox;
import com.rbt.service.IReceiveboxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录收件箱信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:37:25 CST 2014
 */
@Service
public class ReceiveboxService extends GenericService<Receivebox,String> implements IReceiveboxService {
	
	IReceiveboxDao receiveboxDao;
	@Autowired
	ISendboxDao sendboxDao;
	@Autowired
	IRecycleDao recycleDao;
	@Autowired
	public ReceiveboxService(IReceiveboxDao receiveboxDao) {
		super(receiveboxDao);
		this.receiveboxDao = receiveboxDao;
	}
	
	public void deletelogic(HashMap map){
		this.receiveboxDao.deletelogic(map);
	}

	public void dealDelete(String chb_id) {
		String id = chb_id;
		id = id.replace(" ", "");
		String[] ids = id.split(",");
		for(int i =0;i<ids.length;i++){
			if(ids[i]==null ||ids[i].equals("")){
				continue;
			}
			HashMap map = new HashMap();
			map.put("is_get_del", "0");
			map.put("receive_id", ids[i]);
			this.deletelogic(map);
			//加入回收站列表
			Receivebox rb = this.receiveboxDao.get(ids[i]);
			//获取发件箱人的send_cust_id
			Sendbox  sb = this.sendboxDao.get(rb.getSend_id());
			Recycle rc = new Recycle();
			rc.setSend_cust_id(sb.getSend_cust_id());
			rc.setGet_cust_id(rb.getGet_cust_id());
			rc.setSend_id(rb.getSend_id());
			rc.setBox_type("0");//收件箱类型
			rc.setIs_read(rb.getIs_read());
			rc.setReceive_id(ids[i]);
			this.recycleDao.insert(rc);
		}
		
	}
	
}

