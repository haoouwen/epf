package com.rbt.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlUtil {
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(), e.getText());
			System.out.println(e.getName()+":"+ e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	 /**@author Administrator QJY
	 * @Method Description :通过获取返回的单号xml文档解析出来单号
	 *  xml解析常用的三种解析方式：DOM，SAX，PULL，PULL解析类似SAX，但是其性能和效率方面，优化较好，在Android系统中开发APP，常用到PULL解析                  
	 ** 获取数据
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String dealXMLForResult(InputStream xml) throws Exception {
		StringBuffer sb = null;
		//利用PULL解析器工厂获取pull解析器实例
		XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
		pullParser.setInput(xml, "UTF-8");// 为Pull解析器设置要解析的XML数据
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {//开始文档事件
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				sb = new StringBuffer();
				break;

			case XmlPullParser.START_TAG:
				if("is_success".equals(pullParser.getName())){
					String result = pullParser.nextText();
					sb.append(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = pullParser.next();
		}
		//System.out.println("result======================="+sb.toString());
		return sb.toString();
	} 
	
}
