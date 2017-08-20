/*
 
 * Package:com.rbt.common.util
 * FileName: FieldValidateFromXmlUtil.java
 */
package com.rbt.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @function 功能 转化标签配置文件fieldValidate.xml文件到ArrayList
 * @author 创建人 HXK
 * @date 创建日期 2014-12-20
 */
public class FieldValidateFromXmlUtil {

	// 获取验证配置文件
	private static String file_path = PropertiesUtil.getClassPath()
			+ "fieldValidate.xml";
	// 获取数据库表验证列表ArrayList
	public static ArrayList vtableList;

	/*
	 * 初始化加载验证规则，将xml文件转换为对象类型
	 */
	public static void initTablesInstance() {
		if (vtableList == null) {
			vtableList = new ArrayList();
			readValidateXML();
		}
	}
    /*
     * 获取表的验证字段List
     * className：类名
     * methodName：方法名称
     */
	public static List getObjValidateList(String className, String methodName) {
		//初始化加载xml
		initTablesInstance();
		
		if (vtableList == null) {
			return null;
		}
		//获取对应的list
		List thisList = new ArrayList();
		for (int i = 0; i < vtableList.size(); i++) {
			Map map = (HashMap)vtableList.get(i);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				List value = (List)entry.getValue();
				
				if(key.equals(className)){
					thisList = value;
					break;
				}
				
			}
		}
		
		if(thisList == null){
			return null;
		}
		//移除没用list
		for(int i=0;i<thisList.size();i++){
			Map thisMap = (HashMap)thisList.get(i);
			if(thisMap.get("method")!=null && !thisMap.get("method").toString().equals(methodName)){
				thisList.remove(i);
				i--;
			}
		}
		
		return thisList;
		
	}

	public static void main(String[] args) {
		
		//List list = FieldValidateFromXmlUtil.getObjValidateList("ask","public");
		//System.out.println(list);
	}

	// 读取xml验证规则
	public static void readValidateXML() {
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
		// 迭代获取所有表的验证集合
		while (it_element.hasNext()) {

			// 每一个表验证HashMap
			HashMap vtableHashMap = new HashMap();
			String vid = ""; // 表的唯一标识id
			Element element = (Element) it_element.next();
			Iterator it_attr = element.attributeIterator();
			// 迭代获取vid的值
			while (it_attr.hasNext()) {
				Attribute attr = (Attribute) it_attr.next();
				if (attr != null) {
					if (attr.getName().equals("id")) vid = attr.getValue();
				}
			}
			List vfieldList = searchSecond(element);
			// 将获取表的对象HashMap：键:vid 值:vfieldList
			vtableHashMap.put(vid, vfieldList);
			// 将表对象存在在表集合列表中
			vtableList.add(vtableHashMap);
		}
	}

	// 递归遍历 获取验证表字段的属性
	private static List searchSecond(Element root) {

		List vfieldList = new ArrayList();
		Iterator it_element = root.elementIterator();// 将根节点下包含的元素组织成一个迭代器
		while (it_element.hasNext()) {
			// 字段验证属性HashMap
			Map vfieldHashMap = new HashMap();
			String vname = "", vtype = "", vlength = "", vrequired = "", cnname = "", method = "";
			Element element = (Element) it_element.next();
			Iterator it_attr = element.attributeIterator();
			while (it_attr.hasNext()) {
				Attribute attr = (Attribute) it_attr.next();
				if (attr != null) {
					// 获取表的字段验证名称
					if (attr.getName().equals("name")) vname = attr.getValue();
					// 获取表的字段验证类型 字段类型，string为字符串，int为整数
					if (attr.getName().equals("type")) vtype = attr.getValue();
					// 获取表的字段验证长度 字段限制长度
					if (attr.getName().equals("length")) vlength = attr.getValue();
					// 获取表的字段验证是否必填 是否必填，true为必填
					if (attr.getName().equals("required")) vrequired = attr.getValue();
					// 获取表的字段验证中文名称 中文名，方便提示所用
					if (attr.getName().equals("cnname")) cnname = attr.getValue();
					// 获取表的字段验证特殊验证方法
					if (attr.getName().equals("method")) method = attr.getValue();
				}
			}
			// 获取验证属性值存放在HasHmap里面
			vfieldHashMap.put("name", vname);
			vfieldHashMap.put("type", vtype);
			vfieldHashMap.put("length", vlength);
			vfieldHashMap.put("required", vrequired);
			vfieldHashMap.put("cnname", cnname);
			vfieldHashMap.put("method", method.equals("")?"public":method);
			// 将属性字段验证的HashMap 存放在属性字段验证ArralyList里面
			vfieldList.add(vfieldHashMap);
		}
		return vfieldList;
	}
}
