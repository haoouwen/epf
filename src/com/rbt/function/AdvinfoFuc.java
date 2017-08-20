/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 HXK
 * @date  创建日期  2014-09-22
 */
public class AdvinfoFuc extends CreateSpringContext{
	
	public static String cfg_mobiledomain=SysconfigFuc.getSysValue("cfg_mobiledomain");//手机端域名
	
	//根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static List getDisplayAdv(String pos_id,String id,String type){
		IAdvinfoService advinfoService = (IAdvinfoService)getContext().getBean("advinfoService");
		HashMap map = new HashMap();
		map.put("pos_id", pos_id);
		if(!id.equals("")&&"area".equals(type)){
			map.put("area_attr", id);
		}
		if(!id.equals("")&&"cate".equals(type)){
			map.put("cat_attr", id);
		}
		//广告状态：0 已审核 1：未审核
		map.put("adv_state", "0");
		//取出正在播放的广告信息
		map.put("isshow", "0");
		return advinfoService.getList(map);
	}
	
	//取广告位信息
	public static Advpos getAdvposByPk(String pos_id){
		IAdvposService advposService = (IAdvposService)getContext().getBean("advposService");
		return advposService.get(pos_id);
	}
	
	//根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static List getDisplayAdvList(String pos_id,String pos_number){
		IAdvinfoService advinfoService = (IAdvinfoService)getContext().getBean("advinfoService");
		HashMap map = new HashMap();
		map.put("pos_id", pos_id);
		//广告状态：0 已审核 1：未审核
		map.put("adv_state", "0");
		//取出正在播放的广告信息
		map.put("isshow", "0");
		map.put("start", "0");
		map.put("limit", pos_number);
		List gList=new ArrayList();
		List advList=new ArrayList();
		gList=advinfoService.getList(map);
		if(gList!=null&&gList.size()>0){
			for(int i=0;i<gList.size();i++){
				HashMap gmap = new HashMap();
				//构造一个新的对象
				HashMap advmap = new HashMap();
				gmap=(HashMap)gList.get(i);
				if(gmap!=null){
					//广告名称
					advmap.put("advName", gmap.get("adv_name"));
					//广告链接地址
					advmap.put("advLinkUrl", cfg_mobiledomain+gmap.get("link_url"));
					//广告图片URL
					advmap.put("advImgUrl", cfg_mobiledomain+gmap.get("img_path"));
					advList.add(advmap);
				}
			}
		}
		return advList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}