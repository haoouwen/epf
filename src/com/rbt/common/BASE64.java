/*
 
 * Package:com.rbt.common
 * FileName: BASE64.java
 */
package com.rbt.common;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *    BASE64加密解密
 *  @author YUANWEi
 */
public class BASE64 {
	
	private static BASE64Encoder encoder = new BASE64Encoder();  
    private static BASE64Decoder decoder = new BASE64Decoder(); 
	
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptBASE64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
       
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBASE64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }  
    /**
	 * @Method Description :base64加密 字符串
	 * @author: HXK
	 * @date : Dec 13, 2014 12:12:34 PM
	 * @param 
	 * @return String
     * @throws UnsupportedEncodingException 
	 */
    public static String encryptBBase64String(String key) throws UnsupportedEncodingException{
		String ret = null;
		ret = new BASE64Encoder().encode(key.getBytes());
		return ret;
    }
    /**
	 * @Method Description :base64解密 字符串
	 * @author: HXK
	 * @date : Dec 13, 2014 12:12:34 PM
	 * @param 
	 * @return String
	 */
    public static String decryptBase64String(String key){
		String ret = null;
		try {
			ret = new String(new BASE64Decoder().decodeBuffer(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
    }
    
    /**
     * ============================
     */
    /** 
     * BASE64 编码 
     *  
     * @param s 
     * @return 
     */  
    public static String encodeBufferBase64(byte[] buff)  
    {  
        return buff == null?null:encoder.encodeBuffer(buff).trim();  
    }  
    /** 
     * BASE64解码 
     *  
     * @param s 
     * @return 
     */  
    public static byte[] decodeBufferBase64(String s)  
    {  
        try  
        {  
            return s == null ? null : decoder.decodeBuffer(s);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }   
        return null;  
    }  
    
    /** 
     * base64编码 
     *  
     * @param bytes 
     *            字符数组 
     * @return 
     * @throws IOException 
     */  
    public static String encodeBytes(byte[] bytes) throws IOException  
    {  
        return new BASE64Encoder().encode(bytes).replace("\n", "").replace("\r", "");  
    }  
  
    /** 
     * base64解码 
     *  
     * @param bytes 
     *            字符数组 
     * @return 
     * @throws IOException 
     */  
    public static String decodeBytes(byte[] bytes) throws IOException  
    {  
        return new String(new BASE64Decoder().decodeBuffer(new String(bytes)));  
    }
    
    
    
    /**
	 * @param args
     * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		    String str = "java12345中午";
			String ret = null;
			ret = new BASE64Encoder().encode(str.getBytes());
			System.out.println("加密前:"+str+" 加密后:"+ret);
			System.out.println("加密前2:"+str+" 加密后2:"+encryptBBase64String(str));
			str = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48cmVzcG9uc2U+PHJlc3VsdD4wPC9yZXN1bHQ+PGVycm9yRGVzYz7osIPnlKjmjqXlj6PovpPlhaXlj4LmlbDkuI3og73kuLrnqbo8L2Vycm9yRGVzYz48L3Jlc3BvbnNlPg==";
			try {
				ret = new String(new BASE64Decoder().decodeBuffer(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
		    System.out.println("解密前:"+str+" 解密后:"+ret);
		    
		    String stpwd="123456";
		    String opwd=Md5.getMD5(stpwd.getBytes());
		    System.out.println("md5加密前:"+stpwd+" 加密后:"+opwd+"提供:e10adc3949ba59abbe56e057f20f883e");
		    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
}