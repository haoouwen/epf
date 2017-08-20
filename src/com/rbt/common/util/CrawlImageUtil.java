package com.rbt.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : LJQ
 * @date : Mar 25, 2014 12:21:56 PM
 * @Method Description :抓取网络图片
 */
public class CrawlImageUtil {
	public static void main(String[] args) {
	}
	
	
	public static void getCrawlImage(String img_url,String save_img_url){
		  String path = save_img_url;// 图片保存路径
		  File file = new File(path);
		  OutputStream os = null;
		  String str = null;
		  InputStream is = null;
		  HttpURLConnection connection = null;
		  URL userver = null;
		  str = img_url;
		  try {
			   userver = new URL(str);
			   connection = (HttpURLConnection) userver.openConnection();
			   connection.connect();
			   is = connection.getInputStream();
			   os = new FileOutputStream(file);
			   int b = is.read();
			   while (b != -1) {
				    os.write(b);
				    b = is.read();
			   }
			   is.close();
			   os.close();
			   //System.out.println("图片抓取保存成功");
		  } catch (Exception e) {
			  e.printStackTrace();
			  System.out.println(e);
			  System.out.println("======================");
		  }
		
	}
	
}
