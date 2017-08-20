package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;
import com.rbt.service.ITaxrateService;
import com.rbt.service.IAutofckService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IGoodsorderService;
import com.rbt.timerTask.AutofckJob;
import com.rbt.timerTask.DirectSellJob;

/**
 * @function 功能 Tomcat服务器启动时加载分类列表
 * @author 创建人 LJQ
 * @date 创建日期 Aug 10, 2014 2:05:57 PM
 */
public class CategoryFuc extends CreateSpringContext {

	
	public static HashMap catMap  = null;
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:31:31 PM
	 * @Method Description :通过上级分类标识和分类类型找出分类信息
	 */
	@SuppressWarnings("unchecked")
	public static List getCategoryList(Map map) {
		return getCatObj().getList(map);
	}
   

	// 从这个方法getAllCategory()里的list转化为HashMap，此处的list启用ibatis的缓存处理
	// cat_id和cat_name成键值对存放在HashMap中
	// 作者：HXK
	// 修改日期：2014-08-31
	@SuppressWarnings("unchecked")
	public static HashMap getCategoryMap() {
		if(catMap==null){
			List catList = getAllCategory();
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
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:32:44 PM
	 * @Method Description :初始化分类BEAN
	 */
	public static ICategoryService getCatObj() {
		return (ICategoryService) getContext().getBean("categoryService");
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:33:19 PM
	 * @Method Description :获取所有分类信息
	 */
	@SuppressWarnings("unchecked")
	public static List getAllCategory() {
		ICategoryService categoryService = getCatObj();
		return categoryService.getAll();
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:33:56 PM
	 * @Method Description :在全局categoryMap中根据所属分类ID找出对应的所属分类名称
	 */
	@SuppressWarnings("unchecked")
	public static String getCateNameByMap(String cat_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap categoryMap = getCategoryMap();
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

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:36:21 PM
	 * @Method Description :在全局areaMap中找出第一个ID的名称
	 */
	public static String getFiresCateName(String cate_id_string) {
		String first_cate_name = " ";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		if(cate_id_string!=null && cate_id_string.contains("/")){
			cate_id_string=cate_id_string.replace("/", ",");
		}
		String[] cate_id = cate_id_string.split(",");
		String first_cat_id="";
		if (cate_id.length > 1 && cate_id[0] != null) {
			first_cat_id=cate_id[0].toString();
		}else{
			first_cat_id=cate_id_string;
		}
		if (categoryMap != null && categoryMap.get(first_cat_id) != null) {
			first_cate_name = categoryMap.get(first_cat_id).toString();
		}
		return first_cate_name;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:36:44 PM
	 * @Method Description :在全局cateMap中找出最后一个ID的名称
	 */
	public static String getLastCateName(String cate_id_string) {
		String Last_cate_name = "";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		String[] area_id = cate_id_string.split(",");
		int len = 0;
		if (area_id != null) {
			len = area_id.length;
		}
		if (categoryMap != null && len != 0) {
			if (categoryMap.get(area_id[len - 1]) != null) {
				Last_cate_name = categoryMap.get(area_id[len - 1]).toString();
			}
		}
		return Last_cate_name;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:36:56 PM
	 * @Method Description :在全局cateMap中找出对应的ID的名称
	 */
	@SuppressWarnings("unchecked")
	public static String getCateName(String cate_id) {
		String cart_name = "";
		HashMap categoryMap = getCategoryMap();
		if (categoryMap != null) {
			if (categoryMap.get(cate_id) != null) {
				cart_name = categoryMap.get(cate_id).toString();
			}
		}
		return cart_name;
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:37:07 PM
	 * @Method Description :全局cateMap中找出对应的英文名称的名称
	 */
	@SuppressWarnings("unchecked")
	public static String getenName(String cate_id) {
		String en_name = "";
		if(getCatObj().get(cate_id)!=null){
			en_name=getCatObj().get(cate_id).getEn_name();
		}
		return en_name;
	}
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:36:44 PM
	 * @Method Description :在全局cateMap中找出最后一个ID的英文名称
	 */
	public static String getLastCateEnName(String cate_id_string) {
		String Last_cate_ENname = "";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		String[] area_id = cate_id_string.split(",");
		int len = 0;
		if (area_id != null) {
			len = area_id.length;
		}
		if (categoryMap != null && len != 0) {
			if (categoryMap.get(area_id[len - 1]) != null) {
				Last_cate_ENname = getenName(area_id[len - 1]).toString();
			}
		}
		return Last_cate_ENname;
	}
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:36:44 PM
	 * @Method Description :在全局cateMap中找出最后一个ID的
	 */
	public static String getLastCateID(String cate_id_string) {
		String Last_cate_ID = "";
		HashMap categoryMap = getCategoryMap();
		// 定义String分隔串
		String[] area_id = cate_id_string.split(",");
		int len = 0;
		if (area_id != null) {
			len = area_id.length;
		}
		if (categoryMap != null && len != 0) {
			if (categoryMap.get(area_id[len - 1]) != null) {
				Last_cate_ID = area_id[len - 1];
			}
		}
		return Last_cate_ID;
	}
	/**
	 * 方法：根据级别输出相对应的分类
	 */
	public void printAllcategory(){
		List<Map<String, String>> onecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> twocatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> threecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> fourcatList=new ArrayList<Map<String,String>>();
		Map<String, String> mapc=new HashMap<String, String>();
		mapc.put("cat_level", "1");
		onecatList=getCatObj().getList(mapc);
		for(Map<String, String> onemap:onecatList){
			String onecat_name="",onecat_id="";
			onecat_name=onemap.get("cat_name");
			onecat_id=onemap.get("cat_id");
			mapc.put("cat_level", "2");
			mapc.put("up_cat_id", onecat_id);
			twocatList=getCatObj().getList(mapc);
			for(Map<String, String> twmap:twocatList){
				//二级
				String twcat_name="",twcat_id="";
				twcat_name=twmap.get("cat_name");
				twcat_id=twmap.get("cat_id");
				mapc.put("cat_level", "3");
				mapc.put("up_cat_id", twcat_id);
				threecatList=getCatObj().getList(mapc);
				   for(Map<String, String> thrmap:threecatList){
					   
					    //三级
						String thrcat_name="",thrcat_id="";
						thrcat_name=thrmap.get("cat_name");
						thrcat_id=thrmap.get("cat_id");
						mapc.put("cat_level", "4");
						mapc.put("up_cat_id", thrcat_id);
						fourcatList=getCatObj().getList(mapc);
						if(fourcatList.size()>0){
							 for(Map<String, String> fmap:fourcatList){
									//继续4级
									String fcat_name="",fcat_id="";
									fcat_name=fmap.get("cat_name");
									fcat_id=fmap.get("cat_id");
									//输入三级
									System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+","+fcat_id);
									continue;
							 }
						}else{
							//输入三级
							System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id);
							continue;
						}
						
					   
				   }
			}
			
			
		}
	}
	
	
	 /**
     * 
     * 通过上级税率标识和税率类型找出税率信息
     */
	@SuppressWarnings("unchecked")
	public static List getTaxrateList(Map map){
		return getTaxObj().getList(map);
	}
	/**
	 * @date : Feb 25, 2014 3:32:44 PM
	 * @Method Description :初始化税率BEAN
	 */
	public static ITaxrateService getTaxObj() {
		return (ITaxrateService) getContext().getBean("taxrateService");
	}
	/**
	 *   方法：根据级别输出相对应的税率
	 */
	public void printAlltax(){
		List<Map<String, String>> onecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> twocatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> threecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> fourcatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> fivecatList=new ArrayList<Map<String,String>>();
		Map<String, String> mapc=new HashMap<String, String>();
		mapc.put("tax_level", "1");
		onecatList=getTaxObj().getList(mapc);
		for(Map<String, String> onemap:onecatList){
			String onecat_name="",onecat_id="",one_tax_rate="";
			onecat_name=onemap.get("tax_name");
			onecat_id=onemap.get("tax_id");
			one_tax_rate=onemap.get("tax_rate");
			mapc.put("tax_level", "2");
			mapc.put("up_tax_id", onecat_id);
			twocatList=getTaxObj().getList(mapc);
			if(twocatList.size()>0){
				for(Map<String, String> twmap:twocatList){
					//二级
					String twcat_name="",twcat_id="",tw_tax_rate="";
					twcat_name=twmap.get("tax_name");
					twcat_id=twmap.get("tax_id");
					tw_tax_rate=twmap.get("tax_rate");
					mapc.put("tax_level", "3");
					mapc.put("up_tax_id", twcat_id);
					threecatList=getTaxObj().getList(mapc);
					   if(threecatList.size()>0){
						   for(Map<String, String> thrmap:threecatList){
							   
							    //三级
								String thrcat_name="",thrcat_id="",th_tax_rate="";
								thrcat_name=thrmap.get("tax_name");
								thrcat_id=thrmap.get("tax_id");
								th_tax_rate=thrmap.get("tax_rate");
								mapc.put("tax_level", "4");
								mapc.put("up_tax_id", thrcat_id);
								fourcatList=getTaxObj().getList(mapc);
								if(fourcatList.size()>0){
									 for(Map<String, String> fmap:fourcatList){
											//继续4级
											String fcat_name="",fcat_id="",f_tax_rate="";
											fcat_name=fmap.get("tax_name");
											fcat_id=fmap.get("tax_id");
											f_tax_rate=fmap.get("tax_rate");
											mapc.put("tax_level", "5");
											mapc.put("up_tax_id", fcat_id);
											fivecatList=getTaxObj().getList(mapc);
											if(fivecatList.size()>0){
												for(Map<String, String> fvmap:fivecatList){
													//继续5级
													String fvcat_name="",fvcat_id="",fv_tax_rate="";
													fvcat_name=fvmap.get("tax_name");
													fvcat_id=fvmap.get("tax_id");
													fv_tax_rate=fvmap.get("tax_rate"); 
										       		//输入5级
													System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+">"+fvcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+","+fcat_id+","+fvcat_id+"=========="+fv_tax_rate+"%");
													continue;
												}
												
												
											}else{
												//输入4级
												System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+","+fcat_id+"=========="+f_tax_rate+"%");
												continue;
											}
											
									 }
								}else{
									//输入三级
									System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+"=========="+th_tax_rate+"%");
									continue;
								}
							   
						   }
					   }else{
						 //输入2级
						   System.out.println(onecat_name+">"+twcat_name+"======"+onecat_id+","+twcat_id+"=========="+tw_tax_rate+"%");
						   continue;
					   }
				}
			}else{
				//输入1级
				System.out.println(onecat_name+"======"+onecat_id+"=========="+one_tax_rate+"%");
				continue;
			}
			
		}
	}
	

}