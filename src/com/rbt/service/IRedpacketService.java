/*
 
 * Package:com.rbt.servie
 * FileName: IRedpacketService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Redpacket;

/**
 * @function 功能 红包Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Tue Aug 11 20:50:54 CST 2015
 */

public interface IRedpacketService extends IGenericService<Redpacket,String>{
	/**
	 * 下载红包
	 * @param num 红包数量
	 * @param red_id 红包ID
	 * @return
	 * @throws Exception
	 */
	public boolean downRedBag(String num, String red_id) throws Exception;
	
}

