/*
 
 * Package:com.rbt.common.util
 * FileName: PropertiesUtil.java
 */
package com.rbt.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取Properties综合类
 * 
 * @author HXK
 */
public class PropertiesUtil {

	/**
	 * 配置文件对象
	 */
	private Properties props = null;

	/**
	 * 项目根路径
	 */
	private static String rootpath;
	
	private String filePath;
	
	private String thisFileName;

	/**
	 * 构造函数
	 * 
	 * @param fileName
	 *            配置文件名称
	 */
	public PropertiesUtil(String fileName) {
		this.thisFileName = fileName;
		this.filePath = getPath(PropertiesUtil.class) + fileName;
		props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					this.filePath));
			props.load(in);
			// 关闭资源
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key值读取配置的值 2014-05-31
	 * 
	 * @author HXK
	 * @param key
	 *            key值
	 * @return key 键对应的值
	 * @throws IOException
	 */
	public String readValue(String key) throws IOException {
		return props.getProperty(key);
	}

	/**
	 * 修改或添加键值对 如果key存在，修改, 反之，添加。
	 * 
	 * @param filePath
	 *            文件路径，即文件所在包的路径，例如：java/util/config.properties
	 * @param key
	 *            键
	 * @param value
	 *            键对应的值
	 */
	public void writeData(String key, String value) {

		Properties prop = new Properties();
		
		try {
			File file = new File(this.filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			InputStream fis = new FileInputStream(file);
			prop.load(fis);
			// 一定要在修改值之前关闭fis
			fis.close();
			OutputStream fos = new FileOutputStream(this.filePath);
			prop.setProperty(key, value);
			// 保存，并加入注释
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
			
			FileUtil fileUtil = new FileUtil();
			String fileCon = fileUtil.readTxt(this.filePath);
			fileCon = fileCon.replace("\\", "");
			fileUtil.writeTxt(getPath(PropertiesUtil.class), this.thisFileName, fileCon);
			
		} catch (IOException e) {
			System.err.println("Visit " + this.filePath + " for updating " + value+ " value error");
		}
	}

	/**
	 * 读取properties的全部信息 2014-05-31
	 * 
	 * @author HXK
	 * @throws FileNotFoundException
	 *             配置文件没有找到
	 * @throws IOException
	 *             关闭资源文件，或者加载配置文件错误
	 * 
	 */
	public Map readAllProperties()
			throws FileNotFoundException, IOException {
		// 保存所有的键值
		Map map = new HashMap();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

	/**
	 * 得到某一个类的路径
	 * 
	 * @param name
	 * @return
	 */
	private static String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "")
					.replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "")
					.replace("%20", " ");
		}
		return strResult;
	}

	public static String getRootpath() {
		if (rootpath != null) {
			return rootpath;
		} else {
			String root_path = getPath(PropertiesUtil.class).replace(
					"WEB-INF/classes/", "");
			setRootpath(root_path);
			return root_path;
		}
	}

	public static String getClassPath() {
		return getPath(PropertiesUtil.class);
	}

	public static void setRootpath(String rootpath) {
		PropertiesUtil.rootpath = rootpath;
	}

}
