/*
  
 
 * Package:com.rbt.servie
 * FileName: IChannelService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.createHtml.DoHtml;
import com.rbt.model.Aboutus;
import com.rbt.model.Channel;

/**
 * @function 功能 记录网站栏目信息Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Mon Aug 15 10:57:10 CST 2014
 */

public interface IChannelService extends IGenericService<Channel,String> {
	/**
	 * @MethodDescribe 方法描述   根据模块类型返回相应的对象
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 17, 2014 3:49:12 PM
	 */
	@SuppressWarnings("unchecked")
	public Channel getChannelByType(String type);
	
	public List getDeleteList(Map map);
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void recuDelete(String id);
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 2:24:45 PM
	 * @Method Description： 用于判断是否出现相同的栏目名称
	 */
	public int getChanneName(String name, String id) throws Exception;
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 2:50:48 PM
	 * @Method Description：将List的值，转换为对象的值
	 */
	public Channel switchChannel(HashMap myMap);
	
	//更新详细页
	//ch_id all：更新所有
	//type 1：递归更新下级栏目详细页
	public  String updateArticleHtml(String type,String ch_id,DoHtml doHtml)throws IOException;
	
	//更新前台栏目页面
	//ch_id：栏目标识 
	//1：表示更新成功！0：更新失败
	public String updateHtmlPage(String ch_id,String is_pc_webapp) throws IOException;
	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	public String updateChannelPage(String update_state,String strall,String strtwo,String update_type,Channel	channel,String id,String strone) throws Exception;	
}

