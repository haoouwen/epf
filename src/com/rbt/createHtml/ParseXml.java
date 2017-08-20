/*
 
 * Package:com.rbt.createHtml
 * FileName: ParseXml.java
 */
package com.rbt.createHtml;

import java.io.File;
import java.util.*;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能  转化标签配置文件tagconfig.xml文件到HashMap
 * @author  创建人 HXK
 * @date  创建日期  2014-08-12
*/
public class ParseXml {
	
	//除详细页列表页标签的其他标签对象
	private static HashMap tagMap;
	
	//详细页列表页标签对象
	private static HashMap articleTagMap;
	
	//获取系统根目录
	static final String ROOT_PATH = PropertiesUtil.getClassPath();
	
	//获取标签sql配置文件
	private static String file_path = ROOT_PATH+"tagconfig.xml";
	
	//详细页列表标签类型名称
	private static final String ARTICLE_TYPE_NAME = "articleList";
	
	//tagconfig.xml缓存开关，第一次读取解析此文件，后面直接从内存中取
	//0:缓存 1：不缓存
	private static final String CACHE_SWITCH = SysconfigFuc.getSysValue("cfg_tagcache");
	
	public static HashMap getTagMap(){
		if(CACHE_SWITCH.equals("0")){
			if(tagMap == null){
				initMap();
				readXML();
			}
		}else{
			initMap();
			readXML();
		}
		return tagMap;
	}
	
	public static HashMap getArticleTagMap(){
		if(CACHE_SWITCH.equals("0")){
			if(articleTagMap == null){
				initMap();
				readXML();
			}
		}else{
			initMap();
			readXML();
		}
		return articleTagMap;
	}
	
	
	//初始化两个Map对象
	public static void initMap(){
		tagMap = new LinkedHashMap();
		articleTagMap = new LinkedHashMap();
	}
	
	
	public static void main(String[] args) {
		System.out.println(getArticleTagMap());
	}

	public static void readXML() {
		try {
			File file = new File(file_path);// 创建文件对象
			SAXReader reader = new SAXReader();// 创建SAX阅读器
			Document doc = reader.read(file);// 读取内容生成Document对象
			Element root = doc.getRootElement();// 取得根节点
			search(root);// 开始遍历
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 递归遍历
	private static void search(Element root) {
		Iterator it_element = root.elementIterator();// 将根节点下包含的元素组织成一个迭代器
		// 迭代
		
		while (it_element.hasNext()) {			
			String tagId = "",tagSql = "",tagType="";			
			Element element = (Element) it_element.next();
			if (!element.getText().equals("")) {
				//System.out.println(element.getName() + "节点的值是" + element.getText());
				tagSql = element.getText();
				tagSql = tagSql.trim();
				tagSql = tagSql.replace("\n", "");
			} else {
				//System.out.println(element.getName() + "节点");
			}
			Iterator it_attr = element.attributeIterator();
			while (it_attr.hasNext()) {
				Attribute attr = (Attribute) it_attr.next();
				if (attr != null) {
					//System.out.println(element.getName() + "节点的属性" + attr.getName() + "的值是" + attr.getValue());
					if(attr.getName().equals("id")){
						tagId = attr.getValue();
					}
					if(attr.getName().equals("type")){
						tagType = attr.getValue();
					}
				}
			}
			
			if(tagType.equals(ARTICLE_TYPE_NAME)){
				articleTagMap.put(tagId,tagSql);
			}else{
				tagMap.put(tagId,tagSql);
			}
			search(element);// 递归调用
		}
	}

}
