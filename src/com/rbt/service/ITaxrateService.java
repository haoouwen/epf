/*
 
 * Package:com.rbt.servie
 * FileName: ITaxrateService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rbt.model.Taxrate;

/**
 * @function 功能 税率信息Service层业务接口实现类
 * @author  创建人 ZMS
 * @date  创建日期 Tue Aug 18 16:12:24 CST 2015
 */

public interface ITaxrateService extends IGenericService<Taxrate,String>{
	
	/**
	 * @Method Description : 递归调用删除数据
	 */
	public void Recursive(String id);
	/**
	 * @MethodDescribe 方法描述 取出所有地区
	 * @date  创建日期  2015
	 */
    public List getAll();
    /**
	 * @Method Description :导出税率表格
	 * @author: HXK
	 * @date : Nov 6, 2015 3:05:28 PM
	 * @param 
	 * @return return_type
	 */
    public boolean exportTaxExcel() throws Exception ;
    /**
	 * 导入税率
	 */
	public  boolean importTax(String file ) throws IOException;
}

