/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsbrandService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;

import com.rbt.model.Goodsbrand;

/**
 * @function 功能 商品品牌表Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Mon Feb 27 12:42:32 CST 2014
 */

public interface IGoodsbrandService extends IGenericService<Goodsbrand,String>{
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids);
	
	/**	
	 * @author : WXP
	 * @param :en_index 品牌索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @date Mar 26, 2014 10:04:22 AM
	 * @Method Description :品牌列表
	 */
	public Map goodsBrandList(String en_index,String cat_id,String brand_name) throws ParseException, IOException;
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:45:23 PM
	 * @Method Description：获取catName
	 */
	public String getCatName(String goodsAttr,String cat_name_str);
	
	/**	
	 * @author : WXP
	 * @param :goods_attr 商品分类串关联 is_recom 是否推荐
	 * @throws IOException 
	 * @throws ParseException 
	 * @date Apr 10, 2014 8:45:04 PM
	 * @Method Description :相关品牌
	 */
	public List getBrandList(String goods_attr, String is_recom) throws ParseException, IOException;
	
	
	/**	
	 * @author : HXK
	 * @param :随机读取品牌20条
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :相关品牌
	 */
	public List getBrandRandList(Map map) throws ParseException, IOException;
	/**
	 * 导出品牌
	 * @throws Exception
	 */
	public boolean exportbrandExcel(List goodsbrandList, HttpServletResponse response) throws Exception;
	
}

