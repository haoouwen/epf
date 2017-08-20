/*
 
 * Package:com.rbt.action
 * FileName: AboutusAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.service.IMalllevelsetService;

/**
 * @function 功能 关于我们action类
 * @author 创建人 CYC
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2014
 */

public class AdminBaseAction extends BaseAction implements Preparable {
	
	/*******************业务层接口****************/
	@Autowired
	private IMalllevelsetService malllevelsetService;//卖家级别
	
	/*********************集合******************/
	public List malllevelsetList;//卖家级别
	
	/*********************字段******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String stock_val;//库存值
	
	public void prepare() throws Exception {
		if(chb_id!=null){
			chb_id=chb_id.replace(" ","");
		}
	}
	public String judgeList_img(String img_paths){
		String list_img;
		if(img_paths.indexOf(",") >-1){
			String [] img_path=img_paths.split(",");
			list_img=img_path[0];
		}else{
			list_img=img_paths;
		}
		if(list_img.indexOf("big")>-1){
			list_img=list_img.replace("big","mid");
		}
		return list_img;
	}
	/**
	 * @Method Description :加载卖家等级信息
	 * @author : HZX
	 * @date : May 10, 2014 11:10:45 AM
	 */
	public void getSellLevel(String mem_type){
		Map levelMap=new HashMap();
		levelMap.put("mem_type", mem_type);
		malllevelsetList = this.malllevelsetService.getList(levelMap);
	}
}
