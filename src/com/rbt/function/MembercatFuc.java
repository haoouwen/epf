package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;

import com.rbt.service.ICategoryService;
import com.rbt.service.IMembercatService;

/**
 * @function 功能 获取会员自定义分类
 * @author 创建人 LHY
 * @date 创建日期 Aug 10, 2014 2:05:57 PM
 */
public class MembercatFuc extends CreateSpringContext {

	public static HashMap catMap  = null;
	
	// 从这个方法getAllCategory()里的list转化为HashMap，此处的list启用ibatis的缓存处理
	// cat_id和cat_name成键值对存放在HashMap中
	// 修改日期：2014-08-31
	@SuppressWarnings("unchecked")
	public static HashMap getSelfCatMap() {
		if(catMap==null){
			List catList = getAllSelfCat();
			catMap = new HashMap();
			if (catList != null && catList.size() > 0) {
				HashMap aMap = new HashMap();
				for (int i = 0; i < catList.size(); i++) {
					String cat_id = "", cat_name = "";
					aMap = (HashMap) catList.get(i);
					if (aMap.get("cat_id") != null) {
						cat_id = aMap.get("cat_id").toString();
					}
					if (aMap.get("cat_name") != null) {
						cat_name = aMap.get("cat_name").toString();
					}
					catMap.put(cat_id, cat_name);
				}
			}
		}
		return catMap;
	}
	
	/**
	 * @author : LHY
	 * @date : Feb 25, 2014 3:32:44 PM
	 * @Method Description :获取bean
	 */
	public static IMembercatService getCatObj() {
		return (IMembercatService) getContext().getBean("membercatService");
	}

	/**
	 * @author :LHY
	 * @date : Feb 25, 2014 3:33:19 PM
	 * @Method Description :获取所有自定义分类信息
	 */
	@SuppressWarnings("unchecked")
	public static List getAllSelfCat() {
		IMembercatService membercatService = getCatObj();
		return membercatService.getAll();
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:33:56 PM
	 * @Method Description :在全局categoryMap中根据所属分类ID找出对应的所属分类名称
	 */
	@SuppressWarnings("unchecked")
	public static String getCateNameByMap(String cat_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap categoryMap = getSelfCatMap();
		String cat_name = "";
		String column="0";
		// 定义String分隔串
		if(cat_id_string!=null&&!cat_id_string.equals("")){
			cat_id_string = cat_id_string.replace(" ","");
			/*****会员所属分类换行*******/
			cat_id_string=cat_id_string.replace("|", "|,");
			/************/
			String[] cat_id = cat_id_string.split(",");
			for (int j = 0; j < cat_id.length; j++) {
				if (!cat_id[j].equals("")) {
					/******会员所属分类换行******/
					if(cat_id[j].indexOf("|")!=-1){
						cat_id[j] = cat_id[j].replace("|", "");
					     column="1";
					}
					/************/
					if (categoryMap != null && categoryMap.get(cat_id[j]) != null) {
						cat_name = categoryMap.get(cat_id[j]).toString();
						sb.append(cat_name);
						if("1".equals(column)){
							sb.append("</br>");
						}else{
							if (j != cat_id.length - 1) {
								sb.append(",");
							}
						}
					}
					column="0";
				}
			}
		}
		return sb.toString();
	}


}