/*
 
 * Package:com.rbt.common.util
 * FileName: ValidateUtil.java 
 */

package com.rbt.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

/**
 * @function 功能 表单验证工具
 * @author 创建人 HXK
 * @date 创建日期 Jun 30, 2014
 */
public class ValidateUtil {

	public ValidateUtil() {
	}

	private static Pattern PATTERN_EMAIL = Pattern
			.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");// 邮件地址
	private static Pattern PATTERN_TEL = Pattern
			.compile("^([0-9]{3,4}-)?[0-9]{7,8}$");// 固定电话
	private static Pattern PATTERN_MOBILE = Pattern
			.compile("^(\\+86)?0?1\\d{10}$");// 移动电话	  
    
	private static Pattern PATTERN_MOBIL = Pattern
	.compile("^1(?:[38]\\d|(4[57])|(5[012356789]))\\d{8}$");// 电话	  

	private static Pattern PATTERN_ALPHA = Pattern.compile("^[A-Za-z]+$");// 字母

	private static Pattern PATTERN_ALPHAS = Pattern.compile("^[A-Za-z0-9_]+$");// 数字、字母、下划线
	
	private static Pattern PATTERN_EXPRESS = Pattern.compile("^[A-Za-z0-9]+$");// 数字、字母(用于快递单)

	private static Pattern PATTERN_DIGITAL = Pattern.compile("^\\d+$");// 数字

	private static Pattern PATTERN_DOUBLE = Pattern.compile("^\\d+(\\.\\d+)?$");// 浮点型正则表达式

	private static Pattern PATTERN_CHINESE = Pattern
			.compile("^[\\u4E00-\\u9FA5]+$");// 中文
	private static Pattern PATTERN_IDCARD_15 = Pattern
			.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");// 15位身份证格式
	private static Pattern PATTERN_IDCARD_18 = Pattern
			.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[\\d|x|X]$");// 18位身份证格式

	private static Pattern PATTERN_IP = Pattern
			.compile("^((00\\d|1?\\d?\\d|(2([0-4]\\d|5[0-5])))\\.){3}(00\\d|1?\\d?\\d|(2([0-4]\\d|5[0-5])))$");// IP格式

	private static Pattern PATTERN_TIME = Pattern.compile("^([0-9]{4}-[0-9]{2}-[0-9]{2})$");// 日期格式如 如：2014-11-11

	private static Pattern PATTERN_REPEAT = Pattern.compile(".*(.).*\\1.*");// 重复字符格式
	private static Pattern PATTERN_RMB=Pattern .compile("^[0-9]{1,10}(\\.[0-9]{0,3})?$");//人民币格式	
	private static Pattern PATTERN_ALPHASFIRST = Pattern.compile("^[a-zA-Z]{1}[A-Za-z0-9_]+$");// 由字母开头和数字、字母、下划线组成
	private static Pattern PATTERN_ALPHASLIMTLENGTH = Pattern.compile("^[0-9a-zA-Z_]{6,32}");// 数字、字母、下划线组成6-20位
	private static Pattern PATTERN_URL = Pattern.compile("^(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
     + "|"
     + "([0-9a-z_!~*'()-]+\\.)*"
     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
     + "[a-z]{2,6})"
     + "(:[0-9]{1,4})?"
     + "((/?)|"
     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");// URL格式
	private static Pattern PATTERN_HTTPURL = Pattern.compile("^((https|http|ftp|rtsp|mms)?://)"
		     + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
		     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
		     + "|"
		     + "([0-9a-z_!~*'()-]+\\.)*"
		     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
		     + "[a-z]{2,6})"
		     + "(:[0-9]{1,4})?"
		     + "((/?)|"
		     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");// URL格式
	//输入验证是否通过true:通过，false:不通过
	private static Pattern PATTERN_POST=Pattern.compile("^[1-9]{1}[0-9]{5}$");//邮政编码格式
    public boolean ifvalidatepass=true;
    
    
    /**
	 * 校验人民币格式
	 * 
	 * @param rmb
	 *            rmb
	 * @return
	 */
    public synchronized static boolean isRmb(String rmb) {
		if (rmb == null)
			return true;
		else
			return !PATTERN_RMB.matcher(rmb).matches();
	}
    /**
	 * 校验由字母开头和数字、字母、下划线组成格式，主要是用于注册用户名
	 * 
	 * @param username
	 *            username
	 * @return
	 */
    public synchronized static boolean isAlphasFirst(String username) {
		if (username == null)
			return true;
		else
			return !PATTERN_ALPHASFIRST.matcher(username).matches();
	}
    /**
	 * 校验 数字、字母、下划线组成6-20位 格式 ,主要是用于验证密码长度
	 * 
	 * @param email
	 *            email
	 * @return
	 */
    public synchronized static boolean isAlphasLimtLength(String pwd) {
		if (pwd == null)
			return true;
		else
			return !PATTERN_ALPHASLIMTLENGTH.matcher(pwd).matches();
	}
    
    
    
    /**
	 *  验证空
	 * 
	 * @param string
	 *            
	 * @return
	 */
	public synchronized static boolean isRequired(String name) {
		if (name == null)
			return true;
		name = name.trim();
		if (name.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验email格式
	 * 
	 * @param email
	 *            email
	 * @return
	 */
	public synchronized static boolean isEmail(String email) {
		if (email == null)
			return true;
		else
			return !PATTERN_EMAIL.matcher(email).matches();
	}

	public synchronized static boolean isTelephone(String telephone) {
		if (telephone == null)
			return true;
		else
			return !PATTERN_TEL.matcher(telephone).matches();
	}

	public synchronized static boolean isMobile(String mobile) {
		if (mobile == null)
			return true;
		else
			return !PATTERN_MOBILE.matcher(mobile).matches();
	}
	
	public synchronized static boolean isphone(String mobile) {
		if (mobile == null)
			return true;
		else
			return !PATTERN_MOBIL.matcher(mobile).matches();
	}
	public synchronized static boolean isAlpha(String alpha) {
		if (alpha == null)
			return true;
		else
			return !PATTERN_ALPHA.matcher(alpha).matches();
	}
	public synchronized static boolean isIdcard_18(String idcard) {
		if (idcard == null)
			return true;
		else
			return !PATTERN_IDCARD_18.matcher(idcard).matches();
	}
	public synchronized static boolean isAlphas(String alpha) {
		if (alpha == null)
			return true;
		else
			return !PATTERN_ALPHAS.matcher(alpha).matches();
	}

	public synchronized static boolean isDigital(String digital) {
		if (digital == null)
			return true;
		else
			return !PATTERN_DIGITAL.matcher(digital).matches();
	}

	public synchronized static boolean isDouble(String Double) {
		if (Double == null)
			return true;
		else
			return !PATTERN_DOUBLE.matcher(Double).matches();
	}

	public synchronized static boolean isChinese(String chinese) {
		if (chinese == null)
			return true;
		else
			return !PATTERN_CHINESE.matcher(chinese).matches();
	}

	/**
	 * 校验时间 时间可以不写前面的0 如 9:3:1
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public synchronized static boolean isTime(String time) {
		if (time == null)
			return true;
		else
			return !PATTERN_TIME.matcher(time).matches();
	}

	/**
	 * 校验IP格式
	 * 
	 * @param ip
	 * @return
	 */
	public synchronized static boolean isIP(String ip) {
		if (ip == null)
			return true;
		else
			return !PATTERN_IP.matcher(ip).matches();
	}

	/**
	 * 校验是否有重复字符
	 * 
	 * @param repeat
	 * @return
	 */
	public synchronized static boolean hasRepeat(String repeat) {
		if (repeat == null)
			return true;
		else
			return !PATTERN_REPEAT.matcher(repeat).matches();
	}
	
	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return
	 */
	public synchronized static boolean isURL(String url) {
		if (url == null)
			return true;
		else
			return !PATTERN_URL.matcher(url).matches();
	}
	
	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return
	 */
	public synchronized static boolean isHTTPURL(String url) {
		if (url == null)
			return true;
		else
			return !PATTERN_HTTPURL.matcher(url).matches();
	}

    
	/**
	 * 校验POST
	 * 
	 * @param post
	 * @return
	 */
	public synchronized static boolean isPOST(String post){
		if (post == null)
			return true;
		else
			return !PATTERN_POST.matcher(post).matches();
	}
	
	/*
	 * 对象转Map
	 */
	public static Map convertObjToMap(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(
							fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}
	
		//去除script、style、HTML标签
	    public static String delHTMLTag(String htmlStr){ 
	         String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	         String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	         String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	         Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	         Matcher m_script=p_script.matcher(htmlStr); 
	         htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	         Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	         Matcher m_style=p_style.matcher(htmlStr); 
	         htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	         Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	         Matcher m_html=p_html.matcher(htmlStr); 
	         htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr; //返回文本字符串 
	     } 
	    
	    /**
		 * 校验快递单编号
		 * 
		 * @param post
		 * @return
		 */
		public synchronized static boolean isEXPRESS(String express){
			if (express == null)
				return true;
			else
				return !PATTERN_EXPRESS.matcher(express).matches();
		}

		public static void main(String[] args) {
			
		}

}
