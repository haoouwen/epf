/*
  
 
 * Package:com.rbt.function
 * FileName: FilterWordFuc.java 
 */
package com.rbt.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.util.DateUtil;
import com.rbt.common.util.IpSeekerInit;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Illegalsearch;
import com.rbt.service.IFilterwordService;
import com.rbt.service.IIllegalsearchService;
import com.rbt.service.ISearchfilterService;

/**
 * @function 功能 敏感字过滤
 * @author 创建人 HXK
 * @date 创建日期 2014-09-21
 */
public class FilterWordFuc extends CreateSpringContext {

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:50:25 PM
	 * @Method Description :获取数据库中的敏感字数据，放入map
	 */
	public static Map getFilterMap() {
		Map map = new HashMap();
		IFilterwordService filterword_service = getobj();
		List filterList = filterword_service.getList(new HashMap());
		if (filterList != null && filterList.size() > 0) {
			for (Iterator it = filterList.iterator(); it.hasNext();) {
				HashMap filterMap = (HashMap) it.next();
				String name = "", rep_name = "";
				if (filterMap.get("name") != null)
					name = filterMap.get("name").toString();
				if (filterMap.get("rep_name") != null)
					rep_name = filterMap.get("rep_name").toString();
				map.put(name, rep_name);
			}
		}
		return map;
	}

	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:50:38 PM
	 * @Method Description :根据传入的敏感字替换，返回替换后的关键字
	 */
	public static String replaceCheck(Map map, String name) {
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String value = (String) map.get(key);
			if (name.contains(key)) {
				name = name.replace(key, value);// 对于符合map中的key值实现替换功能
			}
		}
		return name;
	}

	/**
	 * @author : XBY
	 * @date : Dec 4, 2014 4:40:23 PM
	 * @Method Description :是否存在敏感字
	 */
	public static boolean isFilterWord(String content) throws IOException {
		boolean flag = false;
		String is_filter = SysconfigFuc.getSysValue("cfg_filterword");
		if (is_filter.equals("0")) {
			// 获取所有关键字
			List filterwordList = getFilterWordList();
			// 判断内容是否存在敏感字
			if (filterwordList != null && filterwordList.size() > 0
					&& !ValidateUtil.isRequired(content)) {
				for (int i = 0; i < filterwordList.size(); i++) {
					Map filterwordMap = (HashMap) filterwordList.get(i);
					if (content.contains(filterwordMap.get("name").toString())) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * @author : XBY
	 * @date : Dec 4, 2014 4:40:23 PM
	 * @Method Description :得到存在敏感字
	 */
	public static String getFilterWord(String content) throws IOException {
		// 敏感字字符串
		String filterwordstr = "";
		String is_filter = SysconfigFuc.getSysValue("cfg_filterword");
		if (is_filter.equals("0")) {
			// 获取敏感字集合
			List filterwordList = getFilterWordList();
			// 判断内容是否存在敏感字
			if (filterwordList != null && filterwordList.size() > 0
					&& !ValidateUtil.isRequired(content)) {
				for (int i = 0; i < filterwordList.size(); i++) {
					Map filterwordMap = (HashMap) filterwordList.get(i);
					if (content.contains(filterwordMap.get("name").toString())) {
						filterwordstr += filterwordMap.get("name").toString()
								+ ",";
					}
				}
			}
		}
		if (!ValidateUtil.isRequired(filterwordstr)) {
			filterwordstr = filterwordstr.substring(0,
					filterwordstr.length() - 1);
		}
		return filterwordstr;
	}

	/**
	 * 获取敏感字集合
	 * 
	 * @return
	 */
	public static List getFilterWordList() {
		IFilterwordService filterword_service = getobj();
		Map map = new HashMap();
		return filterword_service.getList(map);
	}

	/**
	 * 获取敏感字service
	 * 
	 * @return
	 */
	public static IFilterwordService getobj() {
		IFilterwordService filterword_service = (IFilterwordService) CreateSpringContext
				.getContext().getBean("filterwordService");
		return filterword_service;
	}
	
	 /**
	 * @Method Description :判断搜索关键字中是否含有非法字符
	 * @author: HXK
	 * @date : Apr 3, 2015 12:18:41 PM
	 * @param 
	 * @return return_type
	 */
    public static boolean ifExistsIllegalWord(String key_word){
    	boolean flage=true;
    	if(key_word!=null&&!"".equals(key_word)){
    		ISearchfilterService searchfilterService = (ISearchfilterService)getContext().getBean("searchfilterService");
        	List searchList =new ArrayList();
        	searchList=searchfilterService.getList(new HashMap());
        	if(searchList!=null&&searchList.size()>0){
        		for(int i=0;i<searchList.size();i++){
        			Map sMap=new HashMap ();
        			sMap=(HashMap)searchList.get(i);
        			if(sMap!=null&&sMap.get("search_in_word")!=null){
        				if(key_word.contains(" ")||key_word.equals("  ")){
        					if(sMap.get("search_in_word").toString().length()>=6){
        						if(key_word.contains(sMap.get("search_in_word").toString())){
                					//存在非法关键字
                					flage=false;
                					break;
                				}
        					}
        				}else {
        					if(key_word.equals(sMap.get("search_in_word").toString())){
            					//存在非法关键字
            					flage=false;
            					break;
            				}
						}
        				
        			}
        		}
        	}
    	}
    	return flage;
    }
    /**
	 * @Method Description : 判断搜索的关键字是否存在非法字符
	 * @author: HXK
	 * @date : Apr 3, 2015 12:58:45 PM
	 * @param 
	 * @return flag：true 表示不存在，false表示存在非法字符
	 */
	public static Boolean reqFilterKeyword() throws UnsupportedEncodingException{
		boolean flag=true;
		HttpServletRequest request =ServletActionContext.getRequest();
		if (request.getParameter("wd") != null && !request.getParameter("wd").equals("")) {
			String keyword = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
			keyword=keyword.toLowerCase();
			if(ifExistsIllegalWord(keyword)==false){
				//插入illegalsearch
				IIllegalsearchService illegalsearchService = (IIllegalsearchService)getContext().getBean("illegalsearchService");
				Illegalsearch illegalsearch=new Illegalsearch();
				String s_in_date="",s_m_ip="";
				try {
					s_in_date=DateUtil.getCurrentTime();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//获得当前时间
				s_m_ip=IpSeekerInit.getIpAddr(request);//活动访问者IP
				illegalsearch.setIllegal_in_date(s_in_date);
				illegalsearch.setIllegal_ip(s_m_ip);
				illegalsearch.setIllegal_search_word(keyword);
				illegalsearchService.insert(illegalsearch);
				//上传记录非法请求信息，
				flag=false;
			}
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
