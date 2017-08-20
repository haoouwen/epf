package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.service.IRateService;

/**	
 * @author : WXP
 * @param :
 * @date Feb 28, 2014 1:20:16 PM
 * @Method Description :统一加载汇率进入内存
 */
public class RateFuc extends CreateSpringContext{
	
	//找出默认货币符号
	public static String getDefaultRateMark(){
		List list = getRateList();
		String rate_mark = "",endefault = "";
        if(list != null && list.size()>0){
        	HashMap rMap = new HashMap();
        	for(int i=0;i<list.size();i++){
        		 rMap=(HashMap)list.get(i);
                 if(rMap.get("rate_mark") != null){
                	 rate_mark = rMap.get("rate_mark").toString();
                 }
                 if(rMap.get("endefault") != null){
                	 endefault = rMap.get("endefault").toString();
                 }
                 if(endefault.equals("0")){
                	 break;
                 }
        	}
        }
		return rate_mark;
	}
	
	//找出默认货币单位
	public static String getDefaultUnit(){
		List list = getRateList();
		String rate_unit = "",endefault = "";
        if(list != null && list.size() > 0){
        	HashMap rMap = new HashMap();
        	for(int i = 0;i < list.size();i++){
        		 rMap=(HashMap)list.get(i);
                 if(rMap.get("rate_unit") != null){
                	 rate_unit = rMap.get("rate_unit").toString();
                 }
                 if(rMap.get("endefault") != null){
                	 endefault = rMap.get("endefault").toString();
                 }
                 if(endefault.equals("0")){
                	 break;
                 }
        	}
        }
		return rate_unit;
	}
	
	//找出默认汇率
	public static String getDefaultExchangerate(){
		List list = getRateList();
		String exchangerate = "",endefault = "";
        if(list != null && list.size() > 0){
        	HashMap rMap = new HashMap();
        	for(int i=0;i<list.size();i++){
        		 rMap=(HashMap)list.get(i);
                 if(rMap.get("exchangerate") != null){
                	 exchangerate=rMap.get("exchangerate").toString();
                 }
                 if(rMap.get("endefault") != null){
                	 endefault = rMap.get("endefault").toString();
                 }
                 if(endefault.equals("0")){
                	 break;
                 }
        	}
        }
		return exchangerate;
	}
	
	//获取全部列表
	public static List getRateList(){
		IRateService rateService = (IRateService)getContext().getBean("rateService");
		return rateService.getAll();
	}
	
}