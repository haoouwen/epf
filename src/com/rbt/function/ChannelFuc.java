package com.rbt.function;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import com.rbt.common.util.ControlImgSize;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.service.IChannelService;

/**
 * @function 功能  前台列表页详细页Url处理
 * @author  创建人  LJQ
 * @date  创建日期  2014-09-15
 */

public class ChannelFuc extends CreateSpringContext{
	
	
	static String cfg_templateroute = SysconfigFuc.getSysValue("cfg_templateroute");
	
	static HashMap chMap;
	
	public static String getArticleUrl(String module_type,String info_id,String in_date){
		//名称规则
		String article_rule="";
		//保存路径
		String save_dir="";
		
		if(chMap == null){
			chMap = new HashMap();
		}
		
		HashMap channelMap = new HashMap();
		
		if(chMap.get(module_type)==null){
			channelMap = getChannelMap(module_type);
			chMap.put(module_type, channelMap);
		}else{
			channelMap = (HashMap)chMap.get(module_type);
		}
		
		if(channelMap!=null && channelMap.get("save_dir")!=null){
			save_dir=channelMap.get("save_dir").toString();
		}
		if(channelMap!=null && channelMap.get("article_rule")!=null){
			article_rule=channelMap.get("article_rule").toString();
		}

		//解析信息的发布日期
		String s_year="",s_month="",s_day="";
		if(!in_date.equals("")){
			if(in_date.length() > 4){
				s_year = in_date.substring(0,4);
			}
			if(in_date.length() >= 7){
				s_month = in_date.substring(5,7);
			}
			if(in_date.length() >= 10){
				s_day = in_date.substring(8,10);
			}
		}
		if (cfg_templateroute!=null&&!cfg_templateroute.equals("")) {
			save_dir = cfg_templateroute + "/" + save_dir;
		}
		String value = article_rule.replace("{typedir}", "/"+save_dir).replace("{Y}", s_year).replace("{M}", s_month).replace("{D}", s_day).replace("{aid}", info_id);
		value=value.replace(" ","");
		return value;
	}
	
	public static HashMap getChannelMap(String module_type){
		HashMap channelHashMap=new HashMap();
		channelHashMap.put("moduletype", module_type);
		List channelsList = getChannelList(channelHashMap);
		HashMap channelMap = new HashMap();
		if(channelsList!=null&&channelsList.size()!=0) {
			channelMap =(HashMap)channelsList.get(0);
		}
		return channelMap;
	}
	
	/**
	 * @Method Description : 返回图片的大小
	 * @author : LJQ
	 * @date : Nov 23, 2014 9:33:46 AM
	 */
	public static String getSetImgSize(String imgPath,int setWidth,int setHeight){		
		String rootPath = PropertiesUtil.getRootpath();//获取根目录
		String img_filepath = rootPath+File.separator+imgPath;
		ControlImgSize ret = new ControlImgSize();
		return ret.returnDisImgWH(img_filepath, setWidth, setHeight);		

	}
	
	//获取栏目信息List
	public static List getChannelList(HashMap chMap){
		IChannelService channel_Service = (IChannelService)CreateSpringContext.getContext().getBean("channelService");
		return channel_Service.getList(chMap);
	}
	
	//通过模型类型module_type找出栏目标识ch_id
	public static String getChidByModuletype(String module_type){
		HashMap map = new HashMap();
		map.put("moduletype", module_type);
		List list = getChannelList(map);
		String ch_id = "";
		if(list!=null && list.size()>0){
			HashMap cMap = (HashMap)list.get(0);
			if(cMap.get("ch_id")!=null) ch_id = cMap.get("ch_id").toString();
		}
		return ch_id;
	}

}
