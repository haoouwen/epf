package com.rbt.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class UserLoginListener implements HttpSessionAttributeListener{
	public static Map<String, String> loginMap=new HashMap<String, String>();
	 //定义
    Map<String, HttpSession> map = new HashMap<String, HttpSession>();
    //session属性的添加
    public void attributeAdded(HttpSessionBindingEvent event) {
       //获取session的属性名称
       String name = event.getName();
       //如果属性名称是usa
       if(name.equals("usa")){
    	   //获取sessionr的属性值
           UserSessionAttr usa = (UserSessionAttr)event.getValue();
           if(map.get(usa.getUsername())==null){
        	   loginMap.put(usa.getUsername(), "1");
           }
       }
    }
 
    public void attributeRemoved(HttpSessionBindingEvent event) {
       String name = event.getName();
       if(name.equals("usa")){
           UserSessionAttr usa = (UserSessionAttr)event.getValue();
           map.remove(usa.getUsername());
           loginMap.remove(usa.getUsername());
       }
    }
 
    public void attributeReplaced(HttpSessionBindingEvent event) {
       // TODO Auto-generated method stub
       
    }
}
