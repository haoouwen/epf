/*
 
 * Package:com.rbt.createHtml
 * FileName: DoHtml.java
 */
package com.rbt.createHtml;

import java.util.HashMap;
import java.util.Map;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Channel;
import com.rbt.service.IChannelService;

/**
 * @function 功能 外部与生成静态文件代码的借口
 * @author  创建人 HXK
 * @date  创建日期  2014-08-18
*/
public class DoHtml extends CreateSpringContext {
	
	//解析栏目首页 
	public  void doIndexHtml(Map chMap){
		ParseHtml parseHtml = new ParseHtml();
		//开始解析模板文件
		parseHtml.doIndexHtml(chMap);
	}
	
	//解析详细页
	public  void doArticleHtml(String  modType){
		doArticleHtml(modType,"");
	}
	
	//解析详细页
	@SuppressWarnings("unchecked")
	public void doArticleHtml(String modType,String info_id){
		//module_type:所属模块
		//article_temp：详细页模板路径
		//article_rule：详细页Url规则
		// 找出关于供应的的模板信息	
		IChannelService channelService = (IChannelService) getContext().getBean("channelService");
		Channel channel = channelService.getChannelByType(modType);//取出对象
		String module_type = "",article_temp="",article_rule="",save_dir="";
		if(channel.getModule_type()!=null) module_type = channel.getModule_type();
		if(channel.getArticle_temp()!=null) article_temp = channel.getArticle_temp();
		if(channel.getArticle_rule()!=null) article_rule = channel.getArticle_rule();
		if(channel.getSave_dir()!=null) save_dir = channel.getSave_dir();
		Map chMap = new HashMap();
		chMap.put("module_type", module_type);
		chMap.put("article_temp", article_temp);
		chMap.put("article_rule", article_rule);
		chMap.put("save_dir", save_dir);
		
		String ch_title="",ch_id="";
		if(channel.getCh_title()!=null) ch_title = channel.getCh_title();
		if(channel.getCh_id()!=null) ch_id = channel.getCh_id();
		chMap.put("ch_title", ch_title);
		chMap.put("ch_id", ch_id);
		
		if(!info_id.equals("")){
			chMap.put("info_id", info_id);
		}
		
		ParseHtml parseHtml = new ParseHtml();
		//开始解析模板文件
		parseHtml.doArticleHtml(chMap);
		
	}
	
	
	
	/**
	 * @author : LJQ
	 * @date : Jun 14, 2014 2:52:10 PM
	 * @Method Description :删除当条模块信息静态页面
	 */
	public void delArticeHtml(String modType,String info_id,String datetime){
		//module_type:所属模块
		//article_temp：详细页模板路径
		//article_rule：详细页Url规则
		// 找出关于供应的的模板信息	
		IChannelService channelService = (IChannelService) getContext().getBean("channelService");
		Channel channel = channelService.getChannelByType(modType);//取出对象
		String module_type = "",article_rule="",save_dir="";
		if(channel.getModule_type()!=null) module_type = channel.getModule_type();
		if(channel.getArticle_rule()!=null) article_rule = channel.getArticle_rule();
		if(channel.getSave_dir()!=null) save_dir = channel.getSave_dir();
		Map chMap = new HashMap();
		chMap.put("module_type", module_type);
		chMap.put("article_rule", article_rule);
		chMap.put("save_dir", save_dir);
		chMap.put("in_date",datetime);
		if(!info_id.equals("")){
			chMap.put("info_id", info_id);
		}		
		ParseHtml parseHtml = new ParseHtml();
		parseHtml.deleteModelArticeHtml(chMap);		
	}
	
	
	//删除详细页
	public void deleteArticleHtml(HashMap chMap){
		ParseHtml parseHtml = new ParseHtml();
		//开始解析模板文件
		parseHtml.deleteModelArticeHtml(chMap);	
		
	}
	
	//解析详细页
	public  void doArticleHtml(HashMap chMap){
		
		ParseHtml parseHtml = new ParseHtml();
		//开始解析模板文件
		parseHtml.doArticleHtml(chMap);
	}
	
}
