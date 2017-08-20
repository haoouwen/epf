package com.rbt.filter;
import java.io.IOException;
import java.util.Enumeration;
 
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.function.SysconfigFuc;
 
public class AntiSqlInjectionfilter implements Filter {
	private final static String SQL_LIMIT_PAGE = "/include/antiSql.jsp"; 
    public void destroy() {
    }
     
    public void init(FilterConfig arg0) throws ServletException {
    }
     
    public void doFilter(ServletRequest args0, ServletResponse args1,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)args0;
        req.setCharacterEncoding("utf-8");
        HttpServletResponse response = (HttpServletResponse) args1;
         //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //有sql关键字，跳转到error.html
        if (sqlValidate(sql)) {
        	String ip = req.getRemoteAddr();
        	response.sendRedirect(SQL_LIMIT_PAGE);
        } else {
            chain.doFilter(args0,args1);
        }
    }
     
    //效验
    protected static boolean sqlValidate(String str) {
       str = str.toLowerCase();//统一转为小写
       String badStr = SysconfigFuc.getSysValue("cfg_antisql");
       if(badStr==null||"".equals(badStr)){
    	   return false;
       }
       // String badStr = "alert|execute|insert|select|delete|update|drop|master|truncate";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
 
 
