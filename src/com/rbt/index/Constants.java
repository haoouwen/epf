package com.rbt.index;

import org.apache.lucene.util.Version;

import com.rbt.common.util.PropertiesUtil;

public class Constants {
	//项目的根目录
	private static final String ROOT_PATH = PropertiesUtil.getRootpath();
	//lucene存储的根目录
	public static final String BASEPATH = ROOT_PATH+"/a/index/";
	//定义lucene使用的版本
	public static final Version VERSION = Version.LUCENE_34;
	//普通搜索
	public static final String NORMAL = "normal";
	//多条件搜索
	public static final String MULTI = "multi";
	//单字段搜索
	public static final String ONE = "one";
	//范围搜索
	public static final String RANGE = "range";
	//判断是否有图片
	public static final String IFIMG="090909090909";
}
