/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: RecycleService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberDao;
import com.rbt.dao.IReceiveboxDao;
import com.rbt.dao.IRecycleDao;
import com.rbt.dao.ISendboxDao;
import com.rbt.model.Member;
import com.rbt.model.Receivebox;
import com.rbt.model.Recycle;
import com.rbt.model.Sendbox;
import com.rbt.service.IRecycleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录回收站信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 04 15:55:48 CST 2014
 */
@Service
public class RecycleService extends GenericService<Recycle,String> implements IRecycleService {
	
	IRecycleDao recycleDao;
	@Autowired
	IReceiveboxDao receiveboxDao;
	@Autowired
	ISendboxDao sendboxDao;
	@Autowired
	IMemberDao memberDao;
	@Autowired
	public RecycleService(IRecycleDao recycleDao) {
		super(recycleDao);
		this.recycleDao = recycleDao;
	}

	public void dealDelete(String chb_id) {
		String id = chb_id;
		id = id.replace(" ", "");
		this.recycleDao.delete(id);
		String[] ids = id.split(","); 
		for(int i=0;i<ids.length;i++){
			if(ids[i]==null || ids[i].equals("")){
				continue;
			}
			Recycle rc = this.recycleDao.get(ids[i]);
			if(rc!=null && !rc.getBox_type().equals("")){
				if(rc.getBox_type().equals("0")){//0：收件箱 1:发件箱
					this.receiveboxDao.delete(rc.getReceive_id());//若是收件箱则直接删除
				}else{
					Sendbox sb = this.sendboxDao.get(rc.getSend_id());
					if(sb!=null){
						sb.setIs_send_del("2");// 真删
						this.sendboxDao.update(sb);
					}
				}
			}
			this.recycleDao.delete(ids[i]);
		}
		
	}

	public void revert(String chb_id) {
		String id = chb_id;
		id = id.replace(" ", "");
		String[] ids = id.split(","); 
		for(int i=0;i<ids.length;i++){
			if(ids[i]==null || ids[i].equals("")){
				continue;
			}
			Recycle rc = this.recycleDao.get(ids[i]);
			if(rc!=null && !rc.getBox_type().equals("")){
				if(rc.getBox_type().equals("0")){//0：收件箱 1:发件箱
					Receivebox rb = this.receiveboxDao.get(rc.getReceive_id());
					if(rb!=null){
						rb.setIs_get_del("1");
						this.receiveboxDao.update(rb);
					}
				}else{
					Sendbox sb = this.sendboxDao.get(rc.getSend_id());
					if(sb!=null){
						sb.setIs_send_del("1");
						this.sendboxDao.update(sb);
					}
				}
			}
			this.recycleDao.delete(ids[i]);
		}
		
	}

	public Map dealView(Recycle recycle) {
		Map sendboxMap=new HashMap();
		//判断是否读取收件箱的数据,且是未读的数据
		if(recycle.getBox_type()!=null && recycle.getBox_type().equals("0") && recycle.getIs_read().equals("1")){
			 //更改收件箱表中的已读状态 
			 Receivebox rb = this.receiveboxDao.get(recycle.getReceive_id());
			 if(rb!=null){
				 rb.setIs_read("0");
				 this.receiveboxDao.update(rb);
			 }
			 //更改回收站中信息的已读状态
			 recycle.setIs_read("0");
			 this.recycleDao.update(recycle);
		}
		
		// 获取发件箱的ID
		String send_id = recycle.getSend_id();
		Sendbox sendbox = this.sendboxDao.get(send_id);
		String  cust_name="",logo_path="";
		// 根据cust_id 找出相应的名称
		Member member = this.memberDao.get(recycle.getSend_cust_id());
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			}
			if (member.getLogo_path() != null) {
				logo_path = member.getLogo_path();
			}
		}
		// 实例化对象
		if(sendbox==null){
			sendbox = new Sendbox();
		}
		sendboxMap.put("sendbox", sendbox);
		sendboxMap.put("cust_name", cust_name);
		sendboxMap.put("logo_path", logo_path);
		return sendboxMap;
	}

	


	
}

