/*
 
 * Package:com.rbt.common.util
 * FileName: IpSeekerInit.java
 */
package com.rbt.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.kukugame.util.ipsearch.IPSeeker;

/**
 * IP获取
 * @author HXK
 */
public class IpSeekerInit {
	
	/*
	 * 单例
	 */
	private static IPSeeker seeker;
	
	public static IPSeeker getIpSeeker(){
		if(seeker == null){
			seeker = IPSeeker.getInstance();
		}
		return seeker;
	}
	
	public static String getAreaName(String ip){
		IPSeeker seeker = getIpSeeker();
        return seeker.getCountry(ip);
	}
	
	/**
	 * @function 功能 获取IP地址，在通过了Apache,Squid等反向代理软件也可以获取到客户端的真实IP地址
	 * @author 创建人 QJY
	 * @date 创建日期 Jul 26, 2014 4:27:37 PM
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	
	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		HttpServletRequest request =ServletActionContext.getRequest();
		String ipaddr =getIpAddr(request);
		System.out.println(ipaddr);
		 // 获取计算机名
        String name = InetAddress.getLocalHost().getHostName();
        // 获取IP地址
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("计算机名："+name);
        System.out.println("IP地址："+ip);
	}
	
}
