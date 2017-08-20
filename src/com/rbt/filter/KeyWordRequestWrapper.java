/*
  
 
 * Package:com.rbt.filter
 * FileName: KeyWordRequestWrapper.java
 */
package com.rbt.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.rbt.function.FilterWordFuc;

/**
 * @function 功能  敏感字过滤-过滤器
 * @author  创建人 HXK
 * @date  创建日期  2014-09-21
 */
public final class KeyWordRequestWrapper extends HttpServletRequestWrapper{   
    
    public Map keyMap;   
       
    public KeyWordRequestWrapper(HttpServletRequest servletRequest,Map keyMap){   
        super(servletRequest);   
        this.keyMap = keyMap;   
    }   
       
    @Override  
    public Map getParameterMap() {   
        super.getContextPath();   
        Map<String,String[]> map = super.getParameterMap();   
        if(!map.isEmpty()){
            Set<String> keySet = map.keySet();   
            Iterator<String> keyIt = keySet.iterator();   
            while(keyIt.hasNext()){   
                String key = keyIt.next();   
                String[] values=map.get(key);   
                for (int i = 0; i < values.length; i++) {   
                    map.get(key)[i]=this.replaceParam(values[i]);   
                }   
            }   
        }   
        return map;   
    }   
  
    /**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:52:11 PM
	 * @Method Description :敏感字过滤器
	 */
    public String replaceParam(String name){   
        return FilterWordFuc.replaceCheck(keyMap,name);   
    }   
}  
