/*
 
 * Package:com.rbt.createHtml
 * FileName: SpecialField.java
 */
package com.rbt.createHtml;

import java.io.File;
import java.util.*;

import com.rbt.common.util.ControlImgSize;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.*;
import com.rbt.model.Area;
import com.rbt.model.Category;
import com.rbt.service.*;

/**
 * @function 功能 自定义标签实现，标签体内的标签无法直接从数据库获取字段，则需要自定义方法实现
 * @author 创建人 HXK
 * @date 创建日期 2014-08-12
 */
@SuppressWarnings("unchecked")
public class SpecialField {
	/*
	 * 实体分类列表页URL规则 如资讯分类列表页url：/news/cat-1212121212.html [model_name]:实体名称
	 * [cat_id]：分类标识
	 */
	private static final String CAT_URL_REGEX = "/[model_name]/cat-[cat_id].html";
	private static final String AREA_URL_REGEX = "/[model_name]/area-[area_id].html";
	private static final String AREAS_URL_REGEX = "/[model_name]/areas-[area_id].html";

	private static final String COMPANY_URL_REGEX = "/showroom/[user_name]";

	public AreaFuc areaFuc;
	public CategoryFuc categoryFuc;
	public IMemberuserService memberuserService;
	public ICategoryService categoryService;
	// 模板的样式，可在系统后台设置
	public String templateStyle = SysconfigFuc.getSysValue("cfg_templatestyle");
	// 模板生成文件路径，可在系统后台设置
	public String templateRoute = SysconfigFuc.getSysValue("cfg_templateroute");
	// 模板文件路径，可在系统后台设置
	public String templateFiles = SysconfigFuc.getSysValue("cfg_templatefolder");

	public String getArticleVlaue(String name, Map map) {
		String value = "";
		

		
		// 处理无图图片
		if (name.equals("img_path")) {
			String img_path = "";
			if (map.get("img_path") != null) {
				img_path = map.get("img_path").toString();
				if(img_path.indexOf(",") > -1){
					int len = img_path.indexOf(",");
					img_path = img_path.substring(0,len);
				}
			}
			if (img_path.equals("")) {
				img_path = SysconfigFuc.getSysValue("cfg_nopic");
			}
			return img_path;
		}

		// 网页位置标签
		if (name.equals("page_position")) {
			return getPosition(map, "","article");
		}
		// 获取地区
		if (name.equals("get_cat_attr")) {
			value = getCustAreaName(map);
		}
		// 获取一级地区
		if (name.equals("get_area_attr")) {
			value = getCustAreaName(map);
		}
		// 获取所有级别地区
		if (name.equals("get_all_area_attr")) {
			value = getAllAreaName(map);
		}
		if (name.equals("get_salary")) {
			String salaryName = "";
			if (map.get("salary") != null) {
				salaryName = map.get("salary").toString();
				salaryName = salaryName.replace(",", "--");
			}
			map.put("get_salary", salaryName);
			value = salaryName;
		}
		// 通过分类串获取分类的名称
		if (name.equals("get_cats_attr")) {
			String cateName = "";
			if (map.get("cat_attr") != null) {
				cateName = map.get("cat_attr").toString();
				cateName = com.rbt.function.CategoryFuc.getCateNameByMap(cateName);
			}
			map.put("get_cats_attr", cateName);
			value = cateName;
		}
		// 通过分类串获取分类的最后一级名称
		if (name.equals("get_last_cat")) {
			String cateName = "";
			if (map.get("cat_attr") != null) {
				cateName = map.get("cat_attr").toString();
				cateName = com.rbt.function.CategoryFuc.getLastCateName(cateName);
			}
			map.put("get_last_cat", cateName);
			value = cateName;
		}
		// 通过分类串获取分类的最后一级分类的ID
		if (name.equals("same_cat_attr")) {
			String last_cat_id = "";
			if (map.get("cat_attr") != null) {
				last_cat_id = map.get("cat_attr").toString();
				if (last_cat_id.indexOf(",") > -1) {
					int len = last_cat_id.lastIndexOf(",");
					last_cat_id = last_cat_id.substring((len + 1), last_cat_id.length());
				}
			}
			map.put("same_cat_attr", last_cat_id);
			value = last_cat_id;
		}
		// 通过分类串获取分类的第一级名称
		if (name.equals("get_first_cat")) {
			String cateName = "";
			if (map.get("cat_attr") != null) {
				cateName = map.get("cat_attr").toString();
				cateName = com.rbt.function.CategoryFuc.getFiresCateName(cateName);
			}
			map.put("get_first_cat", cateName);
			value = cateName;
		}
		// 通过客户cust_id获取管理员的账号
		if (name.equals("cust_user_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("cust_id") != null) {
				mymap.put("cust_id", map.get("cust_id").toString());
			}
			mymap.put("user_type", "1");
			value = getCustUserName(mymap);
		}
		// 获取评论数量
		if (name.equals("get_count_info")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("info_id") != null) {
				mymap.put("info_id", map.get("info_id").toString());
			}
			value = "";
		}
		// 通过客户cust_id获取公司名称
		if (name.equals("get_cust_company_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("cust_id") != null) {
				mymap.put("cust_id", map.get("cust_id").toString());
			}
			value = getCompanyName(mymap);
		}
		// 获取资讯是否支持评论
		if (name.equals("getcomment")) {
			String commentvalue = "";
			if (map.get("COMMENT") != null) {
				commentvalue = map.get("COMMENT").toString();
				if(commentvalue.equals("1"))
				{
					value="none";
				}
				else {
					value="block";
				}
			}
		}
		// 通过参数表获取企业的类型
		if (name.equals("get_cust_type_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("para_value") != null) {
				mymap.put("para_value", map.get("para_value").toString());
			}
			value = getCompanyTypeName(mymap);
		}
		// 通过客户user_id获取用户名
		if (name.equals("get_user_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("user_id") != null) {
				mymap.put("user_id", map.get("user_id").toString());
			}
			value = getCustUserName(mymap);
			if (value == null || value.equals("")) {
				value = "匿名";
			}
		}
		if (name.equals("price")) {
			if (map.get("price") != null&&!"".equals(map.get("price").toString())&&!"0.00".equals(map.get("price").toString())) {
				value = map.get("main_product").toString();
			}
			else {
				value="面议";
			}
		}
		return value;
	}

	public String getListVlaue(String name, Map map) {

		String tagName = "";
		if (map.get("tagName") != null) {
			tagName = map.get("tagName").toString();
		}

		String value = "";

		if (name.equals("cust_area")) {
			value = getCustAreaName(map);
			if (value.equals(""))
				value = " ";
			return value;
		}
		if (name.equals("main_product")) {
			if (map.get("main_product") != null&&!"".equals(map.get("main_product").toString())) {
				value = map.get("main_product").toString();
			}
			else {
				value="暂无简介";
			}
			return value;
		}
		if (name.equals("company_detail_url")) {
			String cust_id = "";
			if (map.get("info_id") != null) {
				cust_id = map.get("info_id").toString();
			}
			String user_name = "";
			// 根据cust_id取user_name
			HashMap custidMap = new HashMap();
			custidMap.put("cust_id", cust_id);
			IMemberuserService comment_Service = (IMemberuserService) CreateSpringContext.getContext().getBean("memberuserService");
			List memberuserList = comment_Service.getList(custidMap);
			if (memberuserList != null && memberuserList.size() > 0) {
				HashMap userMap = new HashMap();
				userMap = (HashMap) memberuserList.get(0);
				user_name = userMap.get("user_name").toString();
				value = COMPANY_URL_REGEX.replace("[user_name]", user_name);
			}
			if (value.equals(""))
				value = " ";
			return value;
		}

		// 获取一级地区
		if (name.equals("get_area_attr")) {
			value = getCustAreaName(map);
		}
		
		

		// 实体详细页url自定义标签
		if (name.matches("[a-zA-Z]{0,10}_detail_url")) {
			// 获取信息的唯一标识和发布日期
			String info_id = "", in_date = "";
			if (map.get("info_id") != null) {
				info_id = map.get("info_id").toString();
			}
			if (map.get("in_date") != null) {
				in_date = map.get("in_date").toString();
				if (in_date.length() > 10) {
					in_date = in_date.substring(0, 10);
				}
			}
			// 此处考虑到嵌套的问题，info_id和in_date可能为空
			if (info_id.equals("") || in_date.equals("")) {
				value = "";
				return value;
			}
			// 根据模型名称找出对应的保存地址和详细页url规则
			String model_name = name.substring(0, name.indexOf("_"));
			value = ChannelFuc.getArticleUrl(model_name, info_id, in_date);
			return value;
		}
		// 拼成连接到栏目页的地址
		if (name.equals("ch_name_url")) {
			String save_dir = "", file_name = "";
			if (map.get("save_dir") != null) {
				save_dir = map.get("save_dir").toString();
			}
			if (map.get("file_name") != null) {
				file_name = map.get("file_name").toString();
			}
			if (!templateRoute.equals("")) {
				save_dir = "/" + templateRoute + "/" + save_dir;
			}
			value = save_dir + "/" + file_name;
			return value;
		}
		//通过参数的值，拼成链接到栏目的地址
		if (name.equals("ch_module_url")) {
			String save_dir = "", file_name = "", module_code = "";
			//获取参数代码 如"news"
			if (map.get("module_code") != null) {
				module_code = map.get("module_code").toString();
			}
			//用于给栏目传参数
			HashMap channelputMap = new HashMap();
			//获取栏目信息
			HashMap channeloutMap = new HashMap();
			channelputMap.put("moduletype", module_code);
			//栏目列表
			List channelsList = ChannelFuc.getChannelList(channelputMap);
			if (channelsList != null && channelsList.size() > 0) {
				channeloutMap = (HashMap) channelsList.get(0);
			}
			//获取文件存储路劲
			if (channeloutMap.get("save_dir") != null) {
				save_dir = channeloutMap.get("save_dir").toString();
			}
			//获取文件名称
			if (channeloutMap.get("file_name") != null) {
				file_name = channeloutMap.get("file_name").toString();
			}
			//获取生成文件的存储路径
			if (!templateRoute.equals("")) {
				save_dir = "/" + templateRoute + "/" + save_dir;
			}
			value = save_dir + "/" + file_name;
			return value;
		}
		// 实体分类列表页自定义标签
		// 标签嵌套时代表实体一级分类标签
		if (name.matches("[a-zA-Z_]{0,10}_cat_url")) {
			String cat_id = "";
			if (map.get("cat_id") != null) {
				cat_id = map.get("cat_id").toString();
			}
			String model_name = name.substring(0, name.indexOf("_"));
			value = CAT_URL_REGEX.replace("[model_name]", model_name).replace("[cat_id]", cat_id);
			return value;
		}

		// 标签嵌套时代表实体二级分类标签
		if (name.matches("[a-zA-Z_]{0,10}_twocat_url")) {
			String two_cat_id = "";
			if (map.get("two_cat_id") != null) {
				two_cat_id = map.get("two_cat_id").toString();
			}
			if (two_cat_id.equals("")) {
				value = "";
				return value;
			}
			String model_name = name.substring(0, name.indexOf("_"));
			value = "/" + model_name + "/cat-" + two_cat_id + ".html";
			return value;
		}
		// 标签嵌套时代表实体三级分类标签
		if (name.matches("[a-zA-Z_]{0,10}_threecat_url")) {
			String three_cat_id = "";
			if (map.get("three_cat_id") != null) {
				three_cat_id = map.get("three_cat_id").toString();
			}
			if (three_cat_id.equals("")) {
				value = "";
				return value;
			}
			String model_name = name.substring(0, name.indexOf("_"));
			value = "/" + model_name + "/cat-" + three_cat_id + ".html";
			return value;
		}

		// 实体地区列表页自定义标签
		if (name.matches("[a-zA-Z_]{0,10}_area_url")) {
			String area_id = "";
			if (map.get("area_id") != null) {
				area_id = map.get("area_id").toString();
			}
			String model_name = name.substring(0, name.indexOf("_"));
			value = AREA_URL_REGEX.replace("[model_name]", model_name).replace("[area_id]", area_id);
			return value;
		}

		// 实体地区列表页自定义标签
		if (name.matches("[a-zA-Z_]{0,10}_areas_url")) {
			String area_id = "";
			if (map.get("area_id") != null) {
				area_id = map.get("area_id").toString();
			}
			String model_name = name.substring(0, name.indexOf("_"));
			value = AREAS_URL_REGEX.replace("[model_name]", model_name).replace("[area_id]", area_id);
			return value;
		}
		// 获取分类的第一个分类名称	
		if (name.equals("category")) {
			if (map != null && map.get("category") != null) {
				String cateName = map.get("category").toString();
				// 把ID转换成名称
				value = CategoryFuc.getFiresCateName(cateName);
			}
			return value;
		}
		// 获取分类的最后一个分类名称
		if (name.equals("cat_lastname")) {
			String cateName = "";
			if (map.get("cate_attr") != null) {
				cateName = map.get("cate_attr").toString();
				cateName = CategoryFuc.getLastCateName(cateName);
			}
			return cateName;
		}
		// 获取分类的分类名称
		if (name.equals("cats_name")) {
			String cateName = "";
			if (map.get("cate_attr") != null) {
				cateName = map.get("cate_attr").toString();
				cateName = CategoryFuc.getCateNameByMap(cateName);
			}
			return cateName;
		}

		// 通过客户cust_id获取公司名称
		if (name.equals("get_company_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("cust_id") != null) {
				mymap.put("cust_id", map.get("cust_id").toString());
			}
			return getCompanyName(mymap);
		}
		// 通过客户user_id获取用户名
		if (name.equals("get_list_user_name")) {
			HashMap mymap = new HashMap();
			if (map != null && map.get("user_id") != null) {
				mymap.put("user_id", map.get("user_id").toString());
			}
			return getCustUserName(mymap);
		}

		// 各栏目页中img图片的处理
		if (name.equals("img_path") || name.equals(tagName + "img_path")) {
			String path = "";
			if (map.get("img_path") != null) {
				path = map.get("img_path").toString();
				if (path.indexOf(",") > 0) {
					path = path.substring(0, path.indexOf(","));
				}
			}
			// 若无图，则取系统默认无图图片
			if (path.equals("")) {
				path = SysconfigFuc.getSysValue("cfg_nopic");
			}
			return path;
		}

		// 各栏目页中img图片宽高的处理
		if (name.equals("imgsize") || name.equals(tagName + "imgsize")) {
			return getDealImg(map);
		}

		if (name.equals("give_inter")) {
			String inter = map.get("give_inter").toString();
			if(inter==null || inter.equals("") ||inter.equals("0")){
				inter="";
			}else{
				inter="积分："+inter;
			}
			return inter;
		}
		
		//以下获取前台分类的
		
		if (name.equals("cat_name_first")) {
			//根据分类导航标签获取的分类名称
			String strcatnam = map.get("cat_attr").toString();
			String cat_attrstr="";
			if (!ValidateUtil.isRequired(strcatnam)) {
				String cat_attrs[]=strcatnam.split("\\|");
				for(String c_attr:cat_attrs){
					cat_attrstr +="<a href=\"/mall-goodslist-"+CategoryFuc.getLastCateEnName(c_attr.trim())+".html\">"+CategoryFuc.getLastCateName(c_attr.trim())+"</a>"+"、";
				}
				if(!ValidateUtil.isRequired(cat_attrstr)){
					cat_attrstr=cat_attrstr.substring(0,cat_attrstr.length()-1);
				}
			}
			return cat_attrstr;
		}
		if (name.equals("cat_id_first")) {
			//根据分类导航标签获取的分类名称
			String strcatnam = map.get("cat_attr").toString();
			String cat_attrstr_id="";
			if (!ValidateUtil.isRequired(strcatnam)) {
				String cat_attrs[]=strcatnam.split("\\|");
				for(String c_attr:cat_attrs){
					cat_attrstr_id +=CategoryFuc.getLastCateID(c_attr.trim())+",";
				}
				if(!ValidateUtil.isRequired(cat_attrstr_id)){
					cat_attrstr_id=cat_attrstr_id.substring(0,cat_attrstr_id.length()-1);
				}
			}
			return cat_attrstr_id;
		}
		if (name.equals("gf_cat_attr")) {
			String strcatnam="";
			//根据分类 将| 替换为,
			if(map.get("gf_cat_attr")!=null){
			 strcatnam = map.get("gf_cat_attr").toString();
			 strcatnam=strcatnam.replaceAll("\\|", ",");
			}
			return strcatnam;
		}
		//动态新增楼层长幅广告位左边
		if (name.equals("pos_id_left")) {
			String strcatnam="";
			//根据分类 将| 替换为,
			if(map.get("f_sort")!=null){
			    strcatnam = map.get("f_sort").toString();
			 	 //58，61，64，67，70，73，76 对于广告位
				 if(strcatnam.equals("1")){
					 //表示1楼层
					 strcatnam="58";
				 }else if(strcatnam.equals("2")) {
					 strcatnam="61";
				 }else if(strcatnam.equals("3")) {
					 strcatnam="64";
				 }else if(strcatnam.equals("4")) {
					 strcatnam="67";
				 }else if(strcatnam.equals("5")) {
					 strcatnam="70";
				 }else if(strcatnam.equals("6")) {
					 strcatnam="73";
				 }else if(strcatnam.equals("7")) {
					 strcatnam="76";
				 }
			}
			return strcatnam;
		}
		//动态新增楼层长幅广告位中间
		if (name.equals("pos_id_mid")) {
			String strcatnam="";
			//根据分类 将| 替换为,
			if(map.get("f_sort")!=null){
			 strcatnam = map.get("f_sort").toString();
			//59，62，65，68，71，74，77 对于广告位
			 if(strcatnam.equals("1")){
				 //表示1楼层
				 strcatnam="59";
			 }else if(strcatnam.equals("2")) {
				 strcatnam="62";
			 }else if(strcatnam.equals("3")) {
				 strcatnam="65";
			 }else if(strcatnam.equals("4")) {
				 strcatnam="68";
			 }else if(strcatnam.equals("5")) {
				 strcatnam="71";
			 }else if(strcatnam.equals("6")) {
				 strcatnam="74";
			 }else if(strcatnam.equals("7")) {
				 strcatnam="77";
			 }
			}
			return strcatnam;
		}
		//动态新增楼层长幅广告位右边
		if (name.equals("pos_id_right")) {
			String strcatnam="";
			//根据分类 将| 替换为,
			if(map.get("f_sort")!=null){
			 strcatnam = map.get("f_sort").toString();
			//60，63，66，69，72，75，78 对于广告位
			 if(strcatnam.equals("1")){
				 //表示1楼层
				 strcatnam="60";
			 }else if(strcatnam.equals("2")) {
				 strcatnam="63";
			 }else if(strcatnam.equals("3")) {
				 strcatnam="66";
			 }else if(strcatnam.equals("4")) {
				 strcatnam="69";
			 }else if(strcatnam.equals("5")) {
				 strcatnam="72";
			 }else if(strcatnam.equals("6")) {
				 strcatnam="75";
			 }else if(strcatnam.equals("7")) {
				 strcatnam="78";
			 }
			}
			return strcatnam;
		}
		
		if (name.equals("img_ico_cat")) {
			//替换全部分类小图标 如果空 不显示
			String strcatnam=" ";
			if(map.get("first_img_ico")!=null&&!"".equals(map.get("first_img_ico").toString())){
			 strcatnam = map.get("first_img_ico").toString();
			 strcatnam="<img src="+strcatnam+" width=\"25px\" height=\"25px\" />";
			}
			return strcatnam;
		}
		if (name.equals("first_img_ico_url")) {
			//替换首页楼层分类小图标 如果空 不显示
			String strcatnam=" ";
			if(map.get("first_img_ico")!=null&&!"".equals(map.get("first_img_ico").toString())){
				 strcatnam = map.get("first_img_ico").toString();
				 strcatnam="<img src="+strcatnam+" width=\"20px\" height=\"20px\" />";
			}
			if(map.get("img_ico")!=null&&!"".equals(map.get("img_ico").toString())){
				 strcatnam = map.get("img_ico").toString();
				 strcatnam="<img src="+strcatnam+" width=\"20px\" height=\"20px\" />";
			}
			return strcatnam;
		}
		if (name.equals("gl_img_path")) {
			String strcatnam=" ";
			if(map.get("gl_img_path")==null||"".equals(map.get("gl_img_path").toString())){
				strcatnam = SysconfigFuc.getSysValue("cfg_nopic");
			}
			return strcatnam;
		}
		if (name.equals("two_cat_attr")){
			String class_str="";
			if(map.get("two_cat_attr")==null){
				return class_str;
			}if("1".equals(map.get("two_cat_attr").toString())){
				class_str = " <img src=\"/include/common/images/hot.gif\" width=\"22px\" height=\"11px\" />";
			}else{
				class_str=" ";
			}
			return class_str;
		}
		
		return value;
	}

	/**
	 * @Method Description : 获取处理后的照片大小
	 * @author : LJQ
	 * @date : Nov 21, 2014 12:31:33 PM
	 */
	public String getDealImg(Map map) {
		int width = 1, height = 1;
		String imgPath = "";

		if (map != null && map.get("imgwidth") != null) {
			width = Integer.valueOf(map.get("imgwidth").toString());
		}
		if (map != null && map.get("imgheight") != null) {
			height = Integer.valueOf(map.get("imgheight").toString());
		}
		if (map != null && map.get("img_path") != null) {
			imgPath = map.get("img_path").toString();
			if(imgPath.indexOf(",") > -1){
				int len = imgPath.indexOf(",");
				imgPath = imgPath.substring(0,len);
			}
			System.out.print(imgPath+"======================"+width+"======="+height);
		}
		if (imgPath.equals("")) {
			imgPath = SysconfigFuc.getSysValue("cfg_nopic");
		}
		String rootPath = PropertiesUtil.getRootpath();// 获取根目录
		String img_filepath = rootPath + File.separator + imgPath;
		ControlImgSize ret = new ControlImgSize();
		return ret.returnDisImgWH(img_filepath, width, height);
	}

	@SuppressWarnings("static-access")
	public String getCustAreaName(Map map) {
		String area_attr = "";
		if (map.get("area_attr") != null) {
			area_attr = map.get("area_attr").toString();
		}
		area_attr = areaFuc.getFiresAreaName(area_attr);
		return area_attr;
	}

	// 获取所有地区的名称
	public String getAllAreaName(Map map) {
		String area_attr = "";
		if (map.get("area_attr") != null) {
			area_attr = map.get("area_attr").toString();
		}
		return areaFuc.getAreaNameByMap(area_attr);
	}

	// 通过客户ID获取公司名称
	public String getCompanyName(HashMap map) {
		String companyname = "";
		IMemberService member_Service = (IMemberService) CreateSpringContext.getContext().getBean("memberService");
		List memberList = member_Service.getList(map);
		if (memberList != null && memberList.size() != 0) {
			HashMap mapusername = (HashMap) memberList.get(0);
			if (mapusername.get("cust_name") != null) {
				companyname = mapusername.get("cust_name").toString();
				System.out.print(companyname+"===================");
			}
		}
		if(companyname.equals("")){
			companyname="-";
		}
		return companyname;
	}

	// 通过通过参数获取企业的类型
	public String getCompanyTypeName(HashMap map) {
		String typename = "";
		ICommparaService commpara_Service = (ICommparaService) CreateSpringContext.getContext().getBean("commparaService");
		List commparaList = commpara_Service.getList(map);
		if (commparaList != null && commparaList.size() != 0) {
			HashMap mapusername = (HashMap) commparaList.get(0);
			if (mapusername.get("para_key") != null) {
				typename = mapusername.get("para_key").toString();
			}
		}
		return typename;
	}

	// 通过客户ID找到客户会员名称
	public String getCustUserName(HashMap map) {
		String usernameString = "";
		IMemberuserService news_Service = (IMemberuserService) CreateSpringContext.getContext().getBean("memberuserService");
		List memberusernameList = news_Service.getList(map);
		if (memberusernameList != null && memberusernameList.size() != 0) {
			HashMap mapusername = (HashMap) memberusernameList.get(0);
			if (mapusername.get("user_name") != null) {
				usernameString = mapusername.get("user_name").toString();
			}
		}
		return usernameString;

	}



	public IMemberuserService getMemberuserService() {
		return memberuserService;
	}

	public void setMemberuserService(IMemberuserService memberuserService) {
		this.memberuserService = memberuserService;
	}

	public String getListPosition(Map map) {
		return getPosition(map, "list","");
	}

	// 根据module_type获取moduleinfo_url
	public String getUrlByMouleType(String module_type) {
		Map map=new HashMap();
		map.put("module_type", module_type);
		String module_url ="";
		ISysmoduleService sysmodule_Service = (ISysmoduleService) CreateSpringContext.getContext().getBean("sysmoduleService");
		List modulelist=sysmodule_Service.getList(map);
		if (modulelist != null && modulelist.size() != 0) {
			for(int i=0;i<modulelist.size();i++){
				HashMap mapurl = (HashMap) modulelist.get(i);
				if (mapurl.get("moudleinfo_url") != null) {
					module_url = mapurl.get("moudleinfo_url").toString();
				}
			}
		}
		return module_url;
	}
	
	// 取详细页的网页位置
	// 列表页的网页位置
	public String getPosition(Map map, String type,String flag) {
		
		// 模型类型，用于生成分类路径
		String module_type = "";
		// 详细页页面位置
		String articlePosRule = "";
		//详细页更新取模块类型
		if(flag.equals("article"))
		{
			if (map.get("ch_id") != null && !map.get("ch_id").toString().equals("")) {
				String ch_id = map.get("ch_id").toString();
				HashMap chMap = new HashMap();
				chMap.put("ch_id", ch_id);
				List chList = ChannelFuc.getChannelList(chMap);
				if (chList != null && chList.size() > 0) {
					HashMap cMap = (HashMap) chList.get(0);
					if (cMap.get("module_type") != null)
						module_type = cMap.get("module_type").toString();
				}
			}
		}
		else//列表页取页面位置模块类型
		{
			if (map.get("module_type") != null) {
				module_type = map.get("module_type").toString();
			}
		}

		String module_url = getUrlByMouleType(module_type);
		if(!module_url.equals("")){
			articlePosRule = module_url;
		}else{
			articlePosRule = SysconfigFuc.getSysValue("cfg_articlePosRule");
		}

		
		
		
		// 首页链接名称
		String indexName = SysconfigFuc.getSysValue("cfg_indexname");
		// 网页首页链接
		String indexUrl = SysconfigFuc.getSysValue("cfg_indexurl");
		// 网页位置分隔符
		String posSeparator = SysconfigFuc.getSysValue("cfg_posSeparator");

		String indexStr = "<a href=\"" + indexUrl + "\">" + indexName + "</a>";

		// ---------------------------------------------替换首页标签---------------------------------------------
		articlePosRule = articlePosRule.replace("[indexname]", indexStr + posSeparator);

		// ---------------------------------------------替换栏目标签---------------------------------------------
		// 找出栏目信息

		// 以下代码是处理列表页的情况
		if (map.get("ch_id") == null && map.get("module_type") != null) {
			String ch_id = ChannelFuc.getChidByModuletype(map.get("module_type").toString());
			map.put("ch_id", ch_id);
		}

		// 开始替换
		if (map.get("ch_id") != null && !map.get("ch_id").toString().equals("")) {
			String ch_id = map.get("ch_id").toString();
			HashMap chMap = new HashMap();
			chMap.put("ch_id", ch_id);
			List chList = ChannelFuc.getChannelList(chMap);
			if (chList != null && chList.size() > 0) {
				HashMap cMap = (HashMap) chList.get(0);
				String ch_name = "", save_dir = "", file_name = "";
				if (cMap.get("ch_name") != null)
					ch_name = cMap.get("ch_name").toString();
				if (cMap.get("save_dir") != null)
					save_dir = cMap.get("save_dir").toString();
				if (cMap.get("file_name") != null)
					file_name = cMap.get("file_name").toString();
				if (cMap.get("module_type") != null)
					module_type = cMap.get("module_type").toString();
				String chNameStr = "";
				String url = "";

				// 如果保存地址前面没有/符号的话，就变成相对路径了，不对
				if (!save_dir.startsWith("/")) {
					save_dir = "/" + save_dir;
				}

				if (!templateRoute.equals("")) {
					save_dir = "/" + templateRoute + save_dir;
				}

				// 如果保存地址后面没有/符号的话，就变成保存地址直接+文件名，不对
				if (!save_dir.endsWith("/")) {
					save_dir = save_dir + "/";
				}
				url = save_dir + file_name;
				chNameStr = "<a href=\"" + url + "\">" + ch_name + "</a>";

				// 替换栏目标签
				articlePosRule = articlePosRule.replace("[chname]", chNameStr + posSeparator);

			}
		} else {
			return articlePosRule;
		}

		// ---------------------------------------------替换分类标签---------------------------------------------
		// 存放分类路径
		StringBuffer catStr = new StringBuffer();
		// 取出分类信息
		if (map.get("cat_attr") != null && !map.get("cat_attr").toString().equals("")) {

			String cat_attr = map.get("cat_attr").toString();

			if (type.equals("list")) {
				// 此处处理列表页中的分类网页路径
				catStr.append(getUpcatName(cat_attr, module_type, posSeparator));

			} else {
				// 取内存中所有的分类信息
				HashMap allCatMap = CategoryFuc.getCategoryMap();
				String[] cat_attr_str = cat_attr.split(",");
				if (cat_attr_str.length > 0) {
					for (int i = 0; i < cat_attr_str.length; i++) {
						if (!cat_attr_str[i].equals("") && allCatMap.get(cat_attr_str[i]) != null) {
							String cat_name = allCatMap.get(cat_attr_str[i]).toString();
							String cat_url = CAT_URL_REGEX.replace("[model_name]", module_type).replace("[cat_id]", cat_attr_str[i]);
							catStr.append("<a href=\"" + cat_url + "\">" + cat_name + "</a>" + posSeparator);
						}
					}
				}

			}

		}
		// 替换分类标签
		articlePosRule = articlePosRule.replace("[catpath]", catStr.toString());

		// ---------------------------------------------替换地区标签---------------------------------------------
		// 存放地区路径
		StringBuffer areaStr = new StringBuffer();
		// 取出分类信息
		if (map.get("area_attr") != null && !map.get("area_attr").toString().equals("")) {

			String area_attr = map.get("area_attr").toString();

			if (type.equals("list")) {
				// 此处处理列表页中的地区网页路径
				areaStr.append(getUpareaName(area_attr, module_type, posSeparator));
			} else {

				// 此处处理详细页中的地区网页路径
				// 取内存中所有的地区信息
				HashMap allAreaMap = AreaFuc.getAreaMap();
				String[] area_attr_str = area_attr.split(",");
				if (area_attr_str.length > 0) {
					for (int i = 0; i < area_attr_str.length; i++) {
						if (!area_attr_str[i].equals("") && allAreaMap.get(area_attr_str[i]) != null) {
							String area_name = allAreaMap.get(area_attr_str[i]).toString();
							String area_url = AREA_URL_REGEX.replace("[model_name]", module_type).replace("[area_id]", area_attr_str[i]);
							areaStr.append("<a href=\"" + area_url + "\">" + area_name + "</a>" + posSeparator);
						}
					}
				}

			}

		}
		// 替换地区标签
		articlePosRule = articlePosRule.replace("[areapath]", areaStr.toString());

		return articlePosRule;
	}
	

	/**
	 * @Method Description :递归的找出相应的分类ID的上一级ID,拼成分类串
	 * @author : LJQ
	 * @date : Oct 31, 2014 2:02:17 PM
	 */
	String catString = "";

	@SuppressWarnings("static-access")
	public String getUpcatName(String cat_id, String module_type, String posSeparator) {

		ICategoryService categoryService = (ICategoryService) CreateSpringContext.getContext().getBean("categoryService");
		String cat_url = CAT_URL_REGEX.replace("[model_name]", module_type).replace("[cat_id]", cat_id);
		Category category = categoryService.get(cat_id);
		if (category != null && category.getCat_name() != null) {
			// 使用+来组成字符串，为了实现先进后出
			catString = "<a href=\"" + cat_url + "\">" + category.getCat_name() + "</a>" + posSeparator + catString;
		}
		Map map = new HashMap();
		map.put("cat_id", cat_id);
		List list = categoryService.getCategoryIndexList(map);
		if (list != null && list.size() > 0) {
			String up_cat_id = "";
			HashMap listMap = new HashMap();
			listMap = (HashMap) list.get(0);
			if (listMap.get("cat_id") != null) {
				up_cat_id = listMap.get("cat_id").toString();
			}
			getUpcatName(up_cat_id, module_type, posSeparator);
		}
		return catString;
	}

	/**
	 * @Method Description :递归的找出相应的地区ID的上一级ID,拼成地区串
	 * @author : LJQ
	 * @date : Nov 1, 2014 9:40:42 AM
	 */
	String areaString = "";

	@SuppressWarnings("static-access")
	public String getUpareaName(String area_id, String module_type, String posSeparator) {

		IAreaService areaService = (IAreaService) CreateSpringContext.getContext().getBean("areaService");
		String area_url = AREA_URL_REGEX.replace("[model_name]", module_type).replace("[area_id]", area_id);
		Area area = areaService.get(area_id);
		if (area != null && area.getArea_name() != null) {
			// 使用+来组成字符串，为了实现先进后出
			areaString = "<a href=\"" + area_url + "\">" + area.getArea_name() + "</a>" + posSeparator + areaString;
		}
		Map map = new HashMap();
		map.put("area_id", area_id);
		List list = areaService.getWebAreaIndexList(map);
		if (list != null && list.size() > 0) {
			String up_area_id = "";
			HashMap listMap = new HashMap();
			listMap = (HashMap) list.get(0);
			if (listMap.get("area_id") != null) {
				up_area_id = listMap.get("area_id").toString();
			}
			getUpareaName(up_area_id, module_type, posSeparator);
		}
		return areaString;
	}
}
