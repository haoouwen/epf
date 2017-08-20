package com.rbt.webaction;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Advinfo;
import com.rbt.model.Goods;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IGoodsspreadService;
import com.rbt.common.util.PageUtil;
import com.rbt.function.AreaFuc;

/**
 * @author : HZX
 * @date : Jul 3, 2014 5:22:46 PM
 * @Method Description :品牌特卖
 */
public class WebhotSaleAction extends goodsModelAction implements Preparable{

	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	
	/*******************业务层接口****************/
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	private IGoodsspreadService goodsspreadService;
	/*********************集合********************/
	
	
	/*********************字段********************/
	public long difftime;//倒计时
	public String timeout="0";
	//定义分类列表
	public List advinfoList;//广告
	public List goodsspreadList;//热卖品牌商品推广
	//public List difftimeList=new ArrayList();//重组加倒计时列表
	
	/**
	 * @author :HZX
	 * @throws IOException 
	 * @Method Description :品牌特卖首页绑定
	 */
	public String index() throws Exception{
		Map advMap=new HashMap();
		advMap.put("adv_pos", "21");
		//取出正在播放的广告信息
		advMap.put("isshow", "0");
		advinfoList=this.advinfoService.getList(advMap);
		Map gsMap=new HashMap();
		gsMap.put("spread_position","4");
		gsMap.put("in_time","1");//推广中条件
		goodsspreadList=this.goodsspreadService.getList(gsMap);
//		if(goodsspreadList!=null&&goodsspreadList.size()>0){
//			for(int k=0;k<goodsspreadList.size();k++){//获取倒计时时间秒数
//				Map gspMap=(Map)goodsspreadList.get(k);
//				if(gspMap.get("spread_endtime")!=null){
//					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date date = sd.parse(gspMap.get("spread_endtime").toString());
//						difftime =date.getTime() - new Date().getTime();
//						if(0 > difftime){
//							timeout = "1";
//						}else{
//							Map difftimeMap=new HashMap();
//							difftimeMap.put("difftime", difftime);
//							difftimeMap.put("goodsList", gspMap);
//							difftimeList.add(k, difftimeMap);
//						}
//				}
//				
//			}
//			
//			
//		}
		
		return goUrl("sale");
	}
	
	public void prepare() throws Exception {
		
	}
}
