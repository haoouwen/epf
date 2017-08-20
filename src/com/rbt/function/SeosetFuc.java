package com.rbt.function;

import java.util.Map;

import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Seoset;
import com.rbt.service.ILinkService;
import com.rbt.service.ISeosetService;

public class SeosetFuc extends CreateSpringContext{

	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 4:30:02 PM
	 * @Method Description :返回商城首页的seoset对象
	 */
	public static Seoset getSeosetModel(String seo_code,Map map){
		Seoset seoModel = getSeosetObj().get(seo_code);
		if(seoModel!=null){
			String title="",keyword="",descript="";
			//SEO标题
			title=seoModel.getSeo_title();
			//SEO关键字
			keyword=seoModel.getSeo_keyword();
			//SEO描述
			descript=seoModel.getSeo_decri();
			//替换标签
			if(seo_code.equals("index")){
				title = replaceIndexTag(title);
				keyword = replaceIndexTag(keyword);
				descript = replaceIndexTag(descript);
			}else if(seo_code.equals("goodslist")){
				title = replaceGoodsListTag(title,map);
				keyword = replaceGoodsListTag(keyword,map);
				descript = replaceGoodsListTag(descript,map);
			}else if(seo_code.equals("goodsdetail")){
				title = replaceGoodsDetailTag(title,map);
				keyword = replaceGoodsDetailTag(keyword,map);
				descript = replaceGoodsDetailTag(descript,map);
			}else if(seo_code.equals("articlelist")){
				title = replaceArticleListTag(title,map);
				keyword = replaceArticleListTag(keyword,map);
				descript = replaceArticleListTag(descript,map);
			}else if(seo_code.equals("articledetail")){
				title = replaceArticleDetailTag(title,map);
				keyword = replaceArticleDetailTag(keyword,map);
				descript = replaceArticleDetailTag(descript,map);
			}else if(seo_code.equals("brandlist")){
				title = replaceBrandListTag(title,map);
				keyword = replaceBrandListTag(keyword,map);
				descript = replaceBrandListTag(descript,map);
			}else if(seo_code.equals("branddetail")){
				title = replaceBrandDetailTag(title,map);
				keyword = replaceBrandDetailTag(keyword,map);
				descript = replaceBrandDetailTag(descript,map);
			}
			//重新赋值seoModel
			seoModel.setSeo_title(title);
			seoModel.setSeo_keyword(keyword);
			seoModel.setSeo_decri(descript);
		}
		return seoModel;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 4:45:25 PM
	 * @Method Description : 商城首页标签的替换
	 */
	public static String replaceIndexTag(String content){
		if(content!=null && !content.equals("")){
			//分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			//网站SEO标题
			content=content.replace("${webtitle}", SysconfigFuc.getSysValue("cfg_webtitle"));
			//网站SEO关键字
			content=content.replace("${webkeyword}", SysconfigFuc.getSysValue("cfg_keywords"));
			//网站SEO描述 
			content=content.replace("${webdescription}", SysconfigFuc.getSysValue("cfg_description"));
			//网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
		}
		return content;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:45:43 PM
	 * @Method Description :替换商品列表页的标签
	 */
	public static String replaceGoodsListTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站搜索关键字
			if(map.get("search_wd")!=null&&!"".equals(map.get("search_wd"))){
				content=content.replace("${search_wd}",map.get("search_wd").toString());
			}else{
				content=content.replace("${search_wd}","");
			}
			// 商品分类
			if(map.get("goods_cat")!=null&&!"".equals(map.get("goods_cat"))){
				content=content.replace("${goods_cat}",map.get("goods_cat").toString());
			}else{
				content=content.replace("${goods_cat}","");
			}
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			if(!ValidateUtil.isRequired(content)){
				
				if(content.endsWith("-")){
					content=content.substring(0,content.length()-1);
				}
				if(content.contains("--")){
					content=content.replace("--", "-");
				}
			}
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 2:28:24 PM
	 * @Method Description :替换商品详细页标签
	 */
	public static String replaceGoodsDetailTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			// 商品名称
			if(map.get("goods_name")!=null){
				content=content.replace("${goods_name}",map.get("goods_name").toString());
			}else{
				content=content.replace("${goods_name}","");
			}
			if(map.get("goods_cat")!=null){
				content=content.replace("${goods_cat}",map.get("goods_cat").toString());
			}else{
				content=content.replace("${goods_cat}","");
			}
			if(map.get("goods_no")!=null){
				content=content.replace("${goods_no}",map.get("goods_no").toString());
			}else{
				content=content.replace("${goods_no}","");
			}
			if(map.get("brand")!=null){
				content=content.replace("${brand}",map.get("brand").toString());
			}else{
				content=content.replace("${brand}","");
			}
			if(map.get("goods_wd")!=null){
				content=content.replace("${goods_wd}",map.get("goods_wd").toString());
			}else{
				content=content.replace("${goods_wd}","");
			}
			if(map.get("shopname")!=null){
				content=content.replace("${shopname}",map.get("shopname").toString());
			}else{
				content=content.replace("${shopname}","");
			}
			if(map.get("goods_seo_title")!=null){
				content=content.replace("${goods_seo_title}",map.get("goods_seo_title").toString());
			}else{
				content=content.replace("${goods_seo_title}","");
			}
			if(map.get("goods_seo_keyword")!=null){
				content=content.replace("${goods_seo_keyword}",map.get("goods_seo_keyword").toString());
			}else{
				content=content.replace("${goods_seo_keyword}","");
			}
			if(map.get("goods_seo_desc")!=null){
				content=content.replace("${goods_seo_desc}",map.get("goods_seo_desc").toString());
			}else{
				content=content.replace("${goods_seo_desc}","");
			}
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:45:43 PM
	 * @Method Description :替换文章列表页的标签
	 */
	public static String replaceArticleListTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			// 文章标题
			if(map.get("article_name")!=null){
				content=content.replace("${article_name}",map.get("article_name").toString());
			}
			// 文章分类
			if(map.get("article_cat")!=null){
				content=content.replace("${article_cat}",map.get("article_cat").toString());
			}
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:45:43 PM
	 * @Method Description :替换文章详细页的标签
	 */
	public static String replaceArticleDetailTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			if(map.get("article_name")!=null){
				content=content.replace("${article_name}",map.get("article_name").toString());
			}
			// 文章分类
			if(map.get("article_cat")!=null){
				content=content.replace("${article_cat}",map.get("article_cat").toString());
			}
			if(map.get("article_seo_title")!=null){
				content=content.replace("${article_seo_title}",map.get("article_seo_title").toString());
			}
			if(map.get("article_seo_keyword")!=null){
				content=content.replace("${article_seo_keyword}",map.get("article_seo_keyword").toString());
			}
			if(map.get("article_seo_desc")!=null){
				content=content.replace("${article_seo_desc}",map.get("article_seo_desc").toString());
			}
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:45:43 PM
	 * @Method Description :替换品牌列表页的标签
	 */
	public static String replaceBrandListTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			// 品牌名称
			if(map.get("brand_name")!=null){
				content=content.replace("${brand_name}",map.get("brand_name").toString());
			}
			// 品牌分类
			if(map.get("brand_cat")!=null){
				content=content.replace("${brand_cat}",map.get("brand_cat").toString());
			}
		}
		return content;
	}
	
	/**
	 * @author : LJQ
	 * @date : Mar 13, 2014 1:45:43 PM
	 * @Method Description :替换品牌详细页的标签
	 */
	public static String replaceBrandDetailTag(String content,Map map){
		if(map!=null){
			// 分隔符
			content=content.replace("${separator}", SysconfigFuc.getSysValue("cfg_separator"));
			// 网站的名称
			content=content.replace("${webname}", SysconfigFuc.getSysValue("cfg_webname"));
			// 品牌名称
			if(map.get("brand_name")!=null){
				content=content.replace("${brand_name}",map.get("brand_name").toString());
			}
			// 品牌分类
			if(map.get("brand_cat")!=null){
				content=content.replace("${brand_cat}",map.get("brand_cat").toString());
			}
		}
		return content;
	}
	
	
	
	//从Spring容器中获取招聘业务Bean
	public static ISeosetService getSeosetObj(){
		return (ISeosetService)getContext().getBean("seosetService");
	}
	
}
