/*
  
 
 * Package:com.rbt.function
 * FileName: ToolsFuc.java 
 */
package com.rbt.function;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.index.ParaModel;
import com.rbt.index.SearchIndex;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Selfextendattr;
import com.rbt.model.Sendshipmode;
import com.rbt.model.Taxrate;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.ISendshipmodeService;
import com.rbt.service.ITaxrateService;
import com.rbt.timerTask.DelCancelOrderBox;

/**
 * @function 功能  前台freemarker工具类
 * @author  创建人 HXK
 * @date  创建日期  2014-09-30
 */

public class ToolsFuc extends CreateSpringContext{
	@Autowired
	private ITaxrateService taxrateService;
	
	/**
	 * @Method Description :传入字符串，直接返回字符串中的中文汉字
	 * @author : LJQ
	 * @date : Dec 13, 2014 1:24:40 PM
	 */
	public static String getChinese(String str){
		
		  String htmlStr = str; //含html标签的字符串 
		  
	      java.util.regex.Pattern p_script; 
	      java.util.regex.Matcher m_script; 
	      java.util.regex.Pattern p_style; 
	      java.util.regex.Matcher m_style; 
	      java.util.regex.Pattern p_html; 
	      java.util.regex.Matcher m_html; 
	  
	      //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
	      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
	      //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
	      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	      //定义HTML标签的正则表达式 
	      String regEx_html = "<[^>]+>"; 
	      
	      //过滤script标签 
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
          m_script = p_script.matcher(htmlStr); 
          htmlStr = m_script.replaceAll(""); 
          //过滤style标签 
          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
          m_style = p_style.matcher(htmlStr); 
          htmlStr = m_style.replaceAll(""); 
          //过滤html标签 
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
          m_html = p_html.matcher(htmlStr); 
          htmlStr = m_html.replaceAll("");           
          //去除字符串中的空格、回车、换行符、制表符
          Pattern pat = Pattern.compile("\\s*|\t|\r|\n|");
  		  Matcher mat = pat.matcher(htmlStr);
  		  //htmlStr = mat.replaceAll(""); 
  		  
  		  return htmlStr;
	}
	
	/**
	 * @Method Description : 替换list表中字段的处理,网站前台的替换数据
	 * @author : LJQ
	 * @date : Oct 28, 2014 9:56:06 AM
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public static List replaceList(List list) {
		if(list==null || list.size()<=0){
			return null;
		}
		Map map = new HashMap();
		String area_name = "", cat_name = "", img_path = "",
		content = "",state_in="",state_before="",state_after="";
		for (int i = 0; i < list.size(); i++) {
			map = (HashMap) list.get(i);
			if (map.get("area_attr") != null) {
				// 转换名称，取第二级的ID
				String area_attr_id = map.get("area_attr").toString();
				if(area_attr_id!=null&&area_attr_id.contains("/")){
					area_attr_id=area_attr_id.replace("/", ",");
				}
				if (area_attr_id.indexOf(",") > 0) {
					String[] attr_id = area_attr_id.split(",");
					if (attr_id.length > 2 && attr_id[1] != null) {
						map.put("attr_id", attr_id[1]);
					}
				}
				// 转换名称，取第一级的名称
				area_name = AreaFuc.getFiresAreaName(area_attr_id);
				map.put("area_name", area_name);
			}
			if (map.get("cat_attr") != null) {
				// 转换名称，取第一级的名称
				cat_name = CategoryFuc.getFiresCateName(map.get("cat_attr").toString());
				map.put("cat_name", cat_name);
			}
			if (map.get("img_path") != null) {
				img_path = map.get("img_path").toString();
				img_path = img_path.replace("090909090909","");
				if (img_path.indexOf(",") > 0) {
					String[] img_src = img_path.split(",");
					if (img_src[0] != null) {
						map.put("img_path", img_src[0].toString());
					}
				}
				// 若无图，则取系统默认无图图片
				if (img_path.equals("")) {
					img_path = SysconfigFuc.getSysValue("cfg_nopic");
					map.put("img_path", img_path);
				}
			}
			if (map.get("content") != null) {
				content = map.get("content").toString();
				// 把html代码去掉
				content = getChinese(content);
				map.put("content", content);
			}
			if(map.get("sub_desc")!=null){
				content = map.get("sub_desc").toString();
				// 把html代码去掉
				content = getChinese(content);
				map.put("sub_desc", content);
			}
			// is_progress:状态 0，未开始,1，进行中,2，已结束 展会状态的判断
			if (map.get("state_in") != null) {
				state_in = map.get("state_in").toString();
				if (state_in.equals("1")) {
					map.put("is_progress", "1");
				} else {
					if (map.get("state_before") != null) {
						state_before = map.get("state_before").toString();
						if (state_before.equals("1")) {
							map.put("is_progress", "0");
						} else {
							if (map.get("state_after") != null) {
								state_after = map.get("state_after").toString();
								if (state_after.equals("1")) {
									map.put("is_progress", "2");
								}
							}
						}
					}
				}
			}
			list.set(i, map);
		}
		return list;
	}
	
	
	/**
	 * @Method Description : 替换特殊符号过滤
	 * @author : HXK
	 * @date : 2014-01-11
	 */
    public static String xssFilter(String inputContent){
		if(inputContent!=null&&!"".equals(inputContent))
		{
			inputContent=inputContent.replace(">", "");
			inputContent=inputContent.replace(">", "");
			//可以扩充过滤符号;		
		}
		return inputContent;
    }
    
    /**
	 * @Method Description : 替换特殊符号过滤
	 * @author : CYC
	 * @date : 2014-01-13
	 */
    public static List getlastpic(List objectlist){
		if(objectlist!=null&&objectlist.size()>0){
			String img="";
		    for(int i=0;i<objectlist.size();i++){
		    	HashMap mapimg = (HashMap)objectlist.get(i);
				if(mapimg.get("img_path")!=null){
				    String imgpath=mapimg.get("img_path").toString();
				    if(imgpath.indexOf(",")!=-1){
				    	//获取字符串最后一张图片名称（最新上传图片）
				    	img=imgpath.substring(imgpath.lastIndexOf(",")+1,imgpath.length());
				    	mapimg.put("img_path", img);
				    }
				    
				}
				
		    }  
	    }
		return objectlist;
	}
    
    /**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:13:30 PM
	 * @Method Description :在全局替换配送公司
	 */
	public static String getSmodeByMap(String smode_attr) {
		StringBuffer sb = new StringBuffer();
		ISendshipmodeService  sendshipmodeService  =	(ISendshipmodeService) getContext().getBean("sendshipmodeService");
		Sendshipmode sendshipmode = new Sendshipmode();
		// 定义String分隔串
		if (smode_attr != null && !"".equals(smode_attr)) {
			smode_attr = smode_attr.replace(" ","");
			String[] smode_id = smode_attr.split(",");
			for (int j = 0; j < smode_id.length; j++) {
				if (smode_id[j] != null && !smode_id[j].equals("")) {
					sendshipmode  = sendshipmodeService.get(smode_id[j]);
					if(sendshipmode!=null){
						sb.append(sendshipmode.getSmode_name());
						if (j != smode_id.length - 1) {
							sb.append(",");
						}
					}
						
				}
			}
		}
		return sb.toString();
	}
    
	/**
	 * @author : LJQ
	 * @throws org.apache.lucene.queryParser.ParseException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @date : Feb 25, 2014 3:41:02 PM
	 * @Method Description :地区和分类的ID替换成名称,第一个参数要替换的列表，第二个参数属于模块类型
	 */
	@SuppressWarnings("unchecked")
	public static List replaceList(List replist, String type)  {
		Map repListMap = new HashMap();
		String area_attr = "", cat_type = "", mod_value = "",org_name_str="",coupon_type="";
		if (replist != null && replist.size() > 0) {
			for (int i = 0; i < replist.size(); i++) {
				repListMap = (HashMap) replist.get(i);
				
				if (repListMap.get("area_attr") != null
						&& !repListMap.get("area_attr").equals("")) {
					area_attr = repListMap.get("area_attr").toString();
					// 将ID全部用名称代替
					area_attr = AreaFuc.getAreaNameByMap(area_attr);
					repListMap.put("area_attr", area_attr);
				}
				
				if (repListMap.get("c_area_attr") != null
						&& !repListMap.get("c_area_attr").equals("")) {
					area_attr = repListMap.get("c_area_attr").toString();
					// 将ID全部用名称代替
					area_attr = AreaFuc.getAreaNameByListMap(area_attr);
					repListMap.put("c_area_attr", area_attr);
				}	
				
				if (repListMap.get("cat_attr") != null
						&& !repListMap.get("cat_attr").equals("")) {
					repListMap.put("cat_attr_id", repListMap.get("cat_attr").toString());
				}
				
				if (repListMap.get("cat_attr") != null
						&& !repListMap.get("cat_attr").equals("")) {
					cat_type = repListMap.get("cat_attr").toString();
					// 将ID全部用分类代替
					cat_type = CategoryFuc.getCateNameByMap(cat_type);
					repListMap.put("cat_attr", cat_type);
				}
				if (repListMap.get("goods_attr") != null
						&& !repListMap.get("goods_attr").equals("")) {
					cat_type = repListMap.get("goods_attr").toString();
					// 将ID全部用分类代替
					cat_type = CategoryFuc.getCateNameByMap(cat_type);
					repListMap.put("goods_attr", cat_type);
				}
				
				if (repListMap.get("start_area") != null
						&& !repListMap.get("start_area").equals("")) {
					cat_type = repListMap.get("start_area").toString();
					// 将ID全部用分类代替
					cat_type = AreaFuc.getAreaNameByMap(cat_type);
					repListMap.put("start_area", cat_type);
				}
				
				if (repListMap.get("smode_attr") != null
						&& !repListMap.get("smode_attr").equals("")) {
					cat_type = repListMap.get("smode_attr").toString();
					// 将ID全部用分类代替
					cat_type = ToolsFuc.getSmodeByMap(cat_type);
					repListMap.put("smode_attr", cat_type);
				}
				
				if (repListMap.get(type) != null
						&& !repListMap.get(type).equals("")) {
					mod_value = repListMap.get(type).toString();
					// 将模块类型的ID转换成对应的名称
					mod_value = CommparaFuc.get_commparakey_By_value(mod_value,
							type);
					repListMap.put("model_value", mod_value);
				}
				
				if (repListMap.get("at_content") != null
						&& !repListMap.get("at_content").equals("")) {
					mod_value = repListMap.get("at_content").toString();
					// 把html代码去掉
					mod_value = ToolsFuc.getChinese(mod_value);
					repListMap.put("at_content", mod_value);
				}
				
				if (repListMap.get("org_id") != null
						&& !repListMap.get("org_id").equals("")) {
					org_name_str = repListMap.get("org_id").toString();
					// 将所属部门的ID转换成对应的名称
					org_name_str = OrganizeFuc.getOrganizeNameByMap(org_name_str);
					repListMap.put("org_name", org_name_str);
				}
				
				//替换优惠券类型
				if(repListMap.get("coupon_type") != null
						&& !repListMap.get("coupon_type").equals("")) {
					coupon_type = repListMap.get("coupon_type").toString();
					if(coupon_type.equals("0")){
						coupon_type="A类优惠券";
					}else {
						coupon_type="B类优惠券";
					}
					repListMap.put("coupon_type", coupon_type);
				}
				
				//替换会员级别
				if(repListMap.get("member_level") != null
						&& !repListMap.get("member_level").equals("")) {
					
                    String member_level = "";					
					String[] member_level_s =repListMap.get("member_level").toString().split(",");
					IMalllevelsetService malllevelsetService = (IMalllevelsetService) getContext().getBean("malllevelsetService");
					for(int j = 0; j < member_level_s.length; j ++) {
						
						member_level += malllevelsetService.get(member_level_s[j].trim()).getLevel_name() +" ";
					}
					
					repListMap.put("member_level", member_level);
				}			
				if (repListMap.get("goods_cat_last_name") != null
						&& !repListMap.get("goods_cat_last_name").equals("")) {
					cat_type = repListMap.get("goods_cat_last_name").toString();
					if(cat_type.contains(",0")){
						cat_type=cat_type.replace(",0", "");
					}
					// 获取最后一级分类名称
					cat_type = CategoryFuc.getLastCateName(cat_type);
					repListMap.put("goods_cat_last_name", cat_type);
				}
				if(repListMap.get("gcount") != null&& !repListMap.get("gcount").equals("")) {
				   String ass_key_words_title="",search_cat_attr="";
				   if(repListMap.get("ass_key_words_title")!=null){
					   ass_key_words_title=repListMap.get("ass_key_words_title").toString();
				   }
				   if(repListMap.get("search_cat_attr")!=null){
					   search_cat_attr=repListMap.get("search_cat_attr").toString();
				   }
					try {
						repListMap.put("gcount", associationkeywordsGoodsnumber(ass_key_words_title,search_cat_attr));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (org.apache.lucene.queryParser.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		return replist;
	}
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 10:20:30 AM
	 * @Method Description : lucene普通搜索条件的赋值------------------------更换sorl索引时删掉
	 */
	public static List normalSearch(String fieldName,String fieldValue,List list){
		if(fieldName==null || fieldName.equals("")) return list;
		if(fieldValue!=null&&!fieldValue.equals("")){
			ParaModel pm = new ParaModel(fieldName,fieldValue);
			list.add(pm);
		}
		return list;
	}
	 /**
     * @Method Description : 通过lucece获取联想关键字商品的数量
     * @author : HXK
     * @param :
	 * @throws org.apache.lucene.queryParser.ParseException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws org.apache.lucene.queryParser.ParseException 
     */
	public static String  associationkeywordsGoodsnumber(String key_words_title ,String key_words_cat_attr) throws IOException, org.apache.lucene.queryParser.ParseException {
		
		int count =0;
		// 构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state", "1", shList);
		// 搜索商品名称
		shList = normalSearch("goods_name", key_words_title, shList);
		
		if(key_words_cat_attr.contains(",0")){
			key_words_cat_attr=key_words_cat_attr.replace(",0", "");
		}
		key_words_cat_attr=CategoryFuc.getLastCateID(key_words_cat_attr);
		
		shList = normalSearch("cat_attr", key_words_cat_attr, shList);
		// 未被删除商品
		shList = normalSearch("is_del", "1", shList);
		// 上架商品
		shList = normalSearch("is_up", "0", shList);
		// 根据页面提交的条件找出信息总数
		SearchIndex si = new SearchIndex("goods");
		// 搜索到的数据
		count = si.getCount(shList);
		return String.valueOf(count);
	}
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:42:07 AM
	 * @Method Description : 补足17位方法
	 */
	public static String fullBit(String n){
		  return fillBitChar(n,17);
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:42:07 AM
	 * @Method Description : 补位的方法
	 */
	public static String fillBitChar(String n,int clen){
		if(n==null || n.equals("")){
			  return null;
		  }
		  int in = Integer.parseInt(n);
		  NumberFormat formatter = NumberFormat.getNumberInstance();   
		  formatter.setMinimumIntegerDigits(clen);   
		  formatter.setGroupingUsed(false);   
		  String s = formatter.format(in);   
		  System.out.println(s+"=============");
		  return s;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 9:59:16 AM
	 * @Method Description : 过滤不符合RMB格式的字符串
	 */
	public static String isRmb(String s){
		if(s==null || s.equals("")){
			return null;
		}else{
			if(s.indexOf(".")==-1){
				s=s+".00";
			}
		}
		return s;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Jul 27, 2014 2:40:30 PM
	 * @Method Description : 获取系统昨天的日期
	 */
	public static String getYestedayDate(int d){
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, d); //得到前一天
		String yestedayDate = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		return yestedayDate;
	}
	
	/**
	 * @author : HXK
	 * @date : Jul 30, 2014 9:40:30 PM
	 * @Method Description : 转换日期格式
	 */
	public static String formatLuDate(String d){
		if(d==null || d.equals("")){
			return null;
		}else{
			d= new SimpleDateFormat("yyyyMMdd").format(d);
		}
		return d;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jan 10, 2014 12:57:24 PM
	 * @Method Description : 保留两位小数的方法
	 */
	public static String formatPrice(Double price) {
		DecimalFormat df = new DecimalFormat("#.00");
		String priceStr = df.format(price);
		return priceStr;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jan 10, 2014 12:50:07 PM
	 * @Method Description : 格式化当前的时间
	 */
	public static String getNowDateTime() {
		Date now = new Date();
		DateFormat d1 = DateFormat.getDateTimeInstance(); // 默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16
		// 20:54:53）
		String nowStr = d1.format(now);
		return nowStr;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jan 15, 2014 4:13:53 PM
	 * @Method Description :去掉最后的逗号
	 */
	public static String backLastPosStr(String isStr) {
		if (isStr.indexOf(",") > -1) {
			int len = isStr.lastIndexOf(",");
			String singleStr = isStr.substring(len + 1, isStr.length());
			return singleStr;
		}
		return isStr;
	}
	
	/**
	 * @author : WXP
	 * @date : Nov 12, 2014 13:40:33 PM
	 * @Method Description : 过滤关键字中的非法字符
	 */
	public static String filterValue(String p){
		if(p==null || p.equals("")){
			return null;
		}else{
			p = p.replace("-", "").replace("+", "").replace("�?", "").replace("*", "").replace("*", "").replace("^", "").replace("(", "").replace(")", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("\\", "").replace("\"", "");
		}
		return p;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 14, 2014 10:17:15 AM
	 * @Method Description :比较时间前后
	 */
	public static String comparaDate(String start_date,String end_date){
		if(start_date==null || end_date==null || start_date.equals("") || end_date.equals("")){
			return null;
		}
		//获取当前时间
		String nowDate = getNowDateTime();
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Calendar start=java.util.Calendar.getInstance();
		java.util.Calendar end=java.util.Calendar.getInstance();
		java.util.Calendar now=java.util.Calendar.getInstance();
		try{
		start.setTime(df.parse(start_date));
		end.setTime(df.parse(end_date));
		now.setTime(df.parse(nowDate));
		}catch(java.text.ParseException e){
		System.err.println("格式不正确");
		return null;
		}
		int isStart=start.compareTo(now);
		int isEnd=now.compareTo(end);
		if(isEnd>0)
			return "3";//已结束
	    if(isStart<=0)
	    	return "2";//进行中
		else 
			return "1";//未开始
	}
	/**
	 * @author : LJQ
	 * @date : May 15, 2014 4:38:24 PM
	 * @Method Description :根据字段名，拼接list中的字符串值
	 */
	public static String fieldListTurnString(List list,String field_name){
		String fieldStr="";
		for(int i=0;i<list.size();i++){
			HashMap fieldMap = (HashMap)list.get(i);
			if(fieldMap.get(field_name)!=null){
				fieldStr+=fieldMap.get(field_name).toString()+",";
			}
		}
		if(fieldStr.indexOf(",")>-1){
			fieldStr = fieldStr.substring(0, fieldStr.length()-1);
		}
		return fieldStr;
	}
	
	
	
	
	//测试方法
	public static void main(String args[]) throws ParseException, IOException{
	}
	
	
	/**
	 * @author : CYC
	 * @throws ParseException 
	 * @date : May 4, 2014 4:38:24 PM
	 * @Method Description :根据字段名，拼接list中的字符串值
	 */
	public static String dayhourminute(String datetime) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = datetime;
		long timeStart=sdf.parse(start).getTime();
		long date_now = new Date().getTime();
		long day = 24*3600*1000;
		long hour = 3600*1000;
		long minute = 60*1000;
		long chaCount =timeStart - date_now;
		if(chaCount<0){
			return "0";
		}else{
		long dayCount= chaCount/day;
		long hourCount = (chaCount-dayCount*day)/hour;
		long minuteCount = (chaCount-dayCount*day - hourCount*hour)/minute;
	        return dayCount + "天" + hourCount + "小时" + minuteCount + "分钟";
		}
	}
	// 将汉字转换为全拼  
    public static String getPingYin(String src) {  
  
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
          
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches(  
                        "[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                    t4 += t2[0];  
                } else  
                    t4 += java.lang.Character.toString(t1[i]);  
            }  
            // System.out.println(t4);  
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
            e1.printStackTrace();  
        }  
        return t4;  
    }  
	/**
	 * 返回商品ID串
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getGoodsIds(List list) {
		String goods_id ="";
		if(list != null && list.size() > 0 ) {
			for(int i = 0; i < list.size(); i ++) {
				Map map = (HashMap) list.get(i);
				if(!ValidateUtil.isRequired(map.get("goods_id").toString())) {
					goods_id += map.get("goods_id").toString()+ ",";
				}
			}
			//去除最后一个逗号
			goods_id = goods_id.substring(0, goods_id.length()-1);
		}
		return goods_id;
	}
	
}
