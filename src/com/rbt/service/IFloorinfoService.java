/*
 
 * Package:com.rbt.servie
 * FileName: IFloorinfoService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Floorinfo;
import com.rbt.model.Floormodel;

/**
 * @function 功能 楼层管理Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Sat Aug 08 16:33:52 CST 2015
 */

public interface IFloorinfoService extends IGenericService<Floorinfo,String>{
	
	/**
	 * @Method Description : 修改楼层信息
	 * @author: HXK
	 * @date : Aug 13, 2015 10:34:50 AM
	 * @param 
	 * @return return_type
	 */
	public void updateFloorInfo(Floorinfo floorinfo,Floormodel floormodel,String goods_relate_str,String floormark_str);

	public boolean deltetallinfo(String f_id);
	
}

