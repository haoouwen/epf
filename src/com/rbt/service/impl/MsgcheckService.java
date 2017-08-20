/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MsgcheckService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.dao.IMsgcheckDao;
import com.rbt.function.SysconfigFuc;
import com.rbt.message.MailInter;
import com.rbt.model.Msgcheck;
import com.rbt.service.IMsgcheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商城的活动管理Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Wed Oct 10 16:38:35 CST 2014
 */
@Service
public class MsgcheckService extends GenericService<Msgcheck,String> implements IMsgcheckService {


	IMsgcheckDao msgcheckDao;

	@Autowired
	public MsgcheckService(IMsgcheckDao msgcheckDao) {
		super(msgcheckDao);
		this.msgcheckDao = msgcheckDao;
	}
	
	
	public void deleteMsgcheck(Map msgCkeckMap) {
		this.msgcheckDao.deleteMsgcheck(msgCkeckMap);
		
	}
	public void deleteById(String id) {
		this.msgcheckDao.deleteById(id);
		
	}

	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:34:25 PM
	 * @Method Description :发送验证邮件
	 */
	public List sendCheckEmail(Msgcheck msgcheck, String session_cust_id) {
		String cp_phone = msgcheck.getCp_phone();
		String cp_check = RandomStrUtil.getRand("6");
        String cp_type = msgcheck.getCp_type();
		Msgcheck mc =new Msgcheck();
		String c_check= Md5.getMD5(cp_check.getBytes());
		String mark_id= session_cust_id+c_check;
		String c_mark_id=SysconfigFuc.getSysValue("cfg_basehost")+"/mall/msgcheck!onclickemail.action?cust_id="+session_cust_id+"&c_mark_id="+mark_id;
		mc.setCp_phone(cp_phone);
		mc.setCp_check(cp_check); 
		mc.setCp_type(cp_type);
		mc.setMark_id(mark_id);
		this.msgcheckDao.insert(mc);
        String title="邮箱验证";
        String content = "请点击<a href=\"http://"+c_mark_id+"\" target=\"_blank\"> "+c_mark_id +" </a>完成邮箱验证";
        System.out.print(content);
		new MailInter().sendBatchMail(title,content, cp_phone);
		Map checkMap = new HashMap();
		checkMap.put("cp_phone", cp_phone);
		checkMap.put("cp_check", cp_check);
		checkMap.put("minute", "1");
		return msgcheckDao.getList(checkMap);
		
	}
	/**
	 * @Method Description :获取验证是否有效
	 * @author: 胡惜坤
	 * @date : Mar 18, 2016 2:58:54 PM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean checkMsgListToUse(String cp_phone,String cp_check){
		boolean msgfage=true;
		Map checkMap = new HashMap();
		checkMap.put("cp_phone", cp_phone);
		checkMap.put("cp_check", cp_check);
		//超时限制
		checkMap.put("minute", "1");
		//cp_use 0表示未被使用，1表示已经被使用
		checkMap.put("cp_use", "0");
		List list = msgcheckDao.getList(checkMap);
		if(list!=null && list.size() > 0){
			updateUse(cp_check);
			msgfage=true;
		}else {
			msgfage=false;
		}
		return msgfage;
	}
	/**
	 * @Method Description :将验证码 修改已经验证
	 * @author: 胡惜坤
	 * @date : Mar 18, 2016 3:37:18 PM
	 * @param 
	 * @return void
	 */
	public void updateUse(String checkcode){
		//更新验证码为已经使用
		Map usemap = new HashMap();
		usemap.put("cp_check", checkcode);
		usemap.put("cp_use", "1");//0未使用，1已使用
		msgcheckDao.updateUse(usemap);
	}

}

