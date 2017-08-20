package com.rbt.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.rbt.common.util.FileUtil;
import com.rbt.common.util.PropertiesUtil;

public class CopyPageTextFuc {
	
	
	public String getPageText(String path) throws Exception{
		StringBuffer sf=new StringBuffer();
		//创建一个url流
		URL url=new URL(path);
		//将url留放在缓存里
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		String line;
		//获取数据并判断是否等于null
		while((line=br.readLine())!=null){
			sf.append(line+"<br/>");
		}
		/*FileUtil fu=new FileUtil();
		fu.writeTxt(path,"aa.html",sf.toString());*/
		br.close();
		return sf.toString();
	}
	 public void DOWriteTxt(String file, String txt) {
		  try {
		   FileOutputStream os = new FileOutputStream(new File(file), true);
		   os.write((txt + "\n").getBytes());
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }

	public static void main(String[]args) throws Exception{
		String path=PropertiesUtil.getRootpath();
		CopyPageTextFuc ct=new CopyPageTextFuc();
		String str=ct.getPageText("http://detail.tmall.com/item.htm?spm=a2106.m896.1000384.d13.ym6Fgy&id=15690894056&source=dou&scm=1029.newlist-0.bts3.50016860&ppath=");
		FileUtil fu=new FileUtil();
		fu.writeTxt(path,"aa.xml",str,"GBK");
		//ct.DOWriteTxt(path,str);
	}

}
