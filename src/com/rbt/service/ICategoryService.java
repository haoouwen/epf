/*
  
 
 * Package:com.rbt.servie
 * FileName: ICategoryService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Category;

/**
 * @function 功能 分类信息表Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Tue Jul 12 13:04:58 CST 2014
 */

public interface ICategoryService extends IGenericService<Category,String>{
	
	/**
	 * @MethodDescribe 方法描述   根据地区查找数据 
	 * @author  创建人  CYC
	 * @date  创建日期  Nov 21, 2014 2:52:04 PM
	 */
	public List getAreaCategoryList(Map map);
	/**
	 * @MethodDescribe 方法描述   根据地区查找数据 
	 * @author  创建人  CYC
	 * @date  创建日期  Nov 21, 2014 2:52:04 PM
	 */
	public List getTwoAreaCategoryList(Map map);
	/**
	 * @MethodDescribe 方法描述   建立分类表索引数据 
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 26, 2014 2:52:04 PM
	 */
	public List getCategoryIndexList(Map map);
	
	/**
	 * @MethodDescribe 方法描述   前台查找相应的分类
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 29, 2014 5:46:42 PM
	 */
    public List getWebCategroyList(Map map);
    
    /**
	 * @MethodDescribe 方法描述  获取所有地区信息
	 * @author  创建人  HXK
	 * @date  创建日期  2014-08-31
	 */
    public List getAll();
    
	/**
	 * 方法描述：分类是否显示批量修改
	 * @param interrule
	 */
	public void updateDisplay(final List list);
	/**
	 * 方法描述：获取特殊的cat_id集合
	 * @param interrule
	 */
	public List getDeleteList(Map map);
	/**
	 * @MethodDescribe 方法描述 创建一个递归方法用于批量删除
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 15, 2014 4:33:29 PM
	 */
	public boolean Recursive(String id);
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void recuDelete(String id);
	
	/**
	 * 方法描述：删除分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean catdelete(String ids);
	/**
	 * @Method Description :删除分类前，先判断是否已经有商品绑定了，如果没有可以删除，否则不让删除
	 * @author: HXK
	 * @date : May 29, 2014 6:16:07 PM
	 * @param 
	 * @return bool  true可以删除，false不能删除
	 */
    public boolean categoryGoods(String cat_id,String model_type);
    /**
	 * @Method Description :导出分类
	 * @author: HXK
	 * @date : Sep 1, 2015 6:18:31 PM
	 * @param 
	 * @return return_type
	 */
    public void exportCatExcel() throws Exception ;
}

