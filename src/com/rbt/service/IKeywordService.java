/*
  
 
 * Package:com.rbt.service
 * FileName: IKeywordService.java 
 */

package com.rbt.service;

import java.util.List;

import com.rbt.model.Keyword;

/**
 * @function 功能  关键字业务层接口
 * @author  创建人 CYC
 * @date  创建日期  July 6, 2014
 */
public interface IKeywordService extends IGenericService<Keyword,String>{
	
	/**
	 * @MethodDescribe 方法描述    更新关键字次数
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 22, 2014 3:45:13 PM
	 */
	public void updateKeyNums(Keyword keyword);
	
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 11:06:16 AM
	 * @Method Description：集合判断处理
	 */
	public void keywordList(List keywordList,Keyword keyword,String num);
}
