package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.service.IPagetipService;

/**
 * @author : WXP
 * @param :page_code
 * @date Feb 4, 2014 4:39:19 PM
 * @Method Description :统一加载异常页面内容进入内存
 */
public class PageTipFuc extends CreateSpringContext{
	
	public static HashMap<String,String> conMap;
	
	//初始化变量的值
	public static void initPageCon(){
		if(conMap == null) {
			conMap = new HashMap();
			//若不存在则查找数据库
			List list = getSysList();
			String page_code="",page_content="";
	        if(list!=null && list.size()>0){
	        	HashMap aMap = new HashMap();
	        	for(int i=0;i<list.size();i++){
	        		 aMap=(HashMap)list.get(i);
	                 if(aMap.get("page_code")!=null){
	                	 page_code=aMap.get("page_code").toString();
	                 }
	                 if(aMap.get("page_content")!=null){
	                	 page_content=aMap.get("page_content").toString();
	                 }
	                 conMap.put(page_code, page_content);
	        	}
	        }
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 12:42:01 PM
	 * @Method Description :通过网页编码
	 */
	public static String getPageCon(String pt){
		initPageCon();//判断加载
		String content="";
		if(conMap!=null && conMap.get(pt)!=null){
			content = conMap.get(pt);
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 12:42:01 PM
	 * @Method Description :通过提示标题
	 */
	public static String getPageTitle(String pt){
		//若不存在则查找数据库
		List list = getSysList();
		String page_title="";
        if(list!=null && list.size()>0){
        	HashMap aMap = new HashMap();
        	for(int i=0;i<list.size();i++){
        		 aMap=(HashMap)list.get(i);
        		 if(aMap.get("page_code").equals(pt)){
        			  if(aMap.get("remark")!=null){
                     	 page_title=aMap.get("remark").toString();
                     	 break;
                      }
        		 }
               
        	}
        }
		return page_title;
	}
	
	
	//通过变量名得到变量值
	public static List getSysList(){
		IPagetipService pagetipService = (IPagetipService)getContext().getBean("pagetipService");
		return pagetipService.getAll();
	}
	
}