package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.service.ISysconfigService;

/**
 * @function 功能  统一加载系统配置值进入内存
 * @author  创建人  HXK
 * @date  创建日期  2014-08-30
 */
public class SysconfigFuc extends CreateSpringContext{
	
	public static HashMap<String,String> sysMap ;
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:21:13 PM
	 * @Method Description :根据变量名找出变量值
	 */
	public static String getSysValue(String var_name){
		//初始化参数
		initSysValue();
		String  var_value="";
		if(sysMap!=null && sysMap.get(var_name) != null){
			var_value = sysMap.get(var_name).toString();
		}
		return var_value;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 1:15:31 PM
	 * @Method Description :初始化系统参数表
	 */
	public static void initSysValue(){
		if(sysMap == null) {
			sysMap =  new HashMap();
			List list = getSysList();
			String var_name="",var_value="";
	        if(list!=null && list.size()>0){
	        	HashMap aMap = new HashMap();
	        	for(int i=0;i<list.size();i++){
	        		 aMap=(HashMap)list.get(i);
	                 if(aMap.get("var_name")!=null){
	                	 var_name=aMap.get("var_name").toString();
	                 }
	                 if(aMap.get("var_value")!=null){
	                	 var_value=aMap.get("var_value").toString();
	                 }
	                 sysMap.put(var_name, var_value);
	        	}
	        }
		}
	}
	
	
	//通过变量名得到变量值
	public static List getSysList(){
		ISysconfigService sysconfigService = (ISysconfigService)getContext().getBean("sysconfigService");
		return sysconfigService.getAll();
	}
	
}