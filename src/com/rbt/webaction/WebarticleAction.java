/*
  
 
 * Package:com.rbt.action
 * FileName: ArticleAction.java 
 */
package com.rbt.webaction;

import java.util.*;

import com.rbt.function.CategoryFuc;
import com.rbt.model.Article;
import com.rbt.model.Category;
import com.rbt.service.IArticleService;
import com.rbt.service.ICategoryService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录文章管理表信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 20 10:44:52 CST 2014
 */
@Controller
public class WebarticleAction extends WebbaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Article article;
	private Category category;

	/*******************业务层接口****************/
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	/*********************集合******************/
	public List articleList;//记录文章管理表信息信息集合
	public List categoryList;//分类信息表信息集合
	public Map articleMap;//文章

	/*********************字段******************/
	private String cat_attr_name;//转化分类中文
	private String cat_name;//分类中文名
	private String cat_name_;//分类中文名
	public String article_frist_cat;//文章分类第一级串
	public String article_nav="";//文章标题
	public String content;//文章内容
	public String page_cat_attr;//分页用的
	

	/**
	 * @author : HZX
	 * @date : Jun 21, 2014 10:34:56 AM
	 * @Method Description :导航条跳转页面。。传参取文章内容
	 */
	public String getNavDetail(){
		String id = this.article.getArticle_id();
		if(id!=null &&! id.equals("")){
			article = this.articleService.get(id);
			content=article.getContent();
		}
		return goUrl("navDetail");
		
	}
	/**
	 * @Method Description :查找详细文章
	 * @author : HZX
	 * @date : Feb 26, 2014 1:24:23 PM
	 */
	@SuppressWarnings("unchecked")
	public String detail(){
		String id = this.article.getArticle_id();
		//获取信息
		articleMap=this.articleService.getDetail(id);
	    article=(Article)articleMap.get("article");
		//获取分类列表
		viewCat(article.getCat_attr());
		//获取文章列表
	    cat_attr_name = article.getCat_attr();
		cat_attr_name=CategoryFuc.getCateNameByMap(cat_attr_name);
		//seo列表页的设置
		Map seoMap = new HashMap();
		seoMap.put("article_name", article.getTitle());
		seoMap.put("article_seo_title", article.getSeo_title());
		seoMap.put("article_seo_keyword", article.getSeo_keyword());
		seoMap.put("article_seo_desc", article.getSeo_descri());
		seoMap.put("article_cat", cat_attr_name);
		setSeoPage("articlelist",seoMap);
		return goUrl("articleDetail");
	}
	
	/**
	 * @Method Description :查找详细文章
	 * @author : HZX
	 * @date : Feb 26, 2014 1:24:23 PM
	 */
	@SuppressWarnings("unchecked")
	public String noticeDetail(){
		String id = this.article.getArticle_id();
		//获取信息
		articleMap=this.articleService.getDetail(id);
	    article=(Article)articleMap.get("article");
		//获取分类列表
		viewCat(article.getCat_attr());
		//获取文章列表
	    cat_attr_name = article.getCat_attr();
		cat_attr_name=CategoryFuc.getCateNameByMap(cat_attr_name);
		//seo列表页的设置
		Map seoMap = new HashMap();
		seoMap.put("article_name", article.getTitle());
		seoMap.put("article_seo_title", article.getSeo_title());
		seoMap.put("article_seo_keyword", article.getSeo_keyword());
		seoMap.put("article_seo_desc", article.getSeo_descri());
		seoMap.put("article_cat", cat_attr_name);
		setSeoPage("articlelist",seoMap);
		return goUrl("noticeDetail");
	}
	
	
	//获取文章分类列
	public void getArticeCatList(){
	    Map artcleMap =new HashMap();
	    artcleMap.put("module_type", "article");
	    artcleMap.put("is_display", "1");
		categoryList = this.categoryService.getList(artcleMap);
		articleMap.put("categoryList", categoryList);
	}
	
    //分类列文章
	public String attrdetail(){
		//seo列表页的设置
		Map seoMap = new HashMap();
		String cat_attr=article.getCat_attr();
		//获取信息
		articleMap=this.articleService.getAttrDetail(cat_attr, page_cat_attr);
		//获取详细文章列表（公告和热点关注才有列表，其余一般是单条数据）
		Map artMap =new HashMap();
 		artMap.put("cat_attr", articleMap.get("cat_attr").toString());
		artMap.put("is_display", "0");
		//根据页面提交的条件找出信息总数
		int count=this.articleService.getCount(artMap);
		//分页插件
		artMap = super.pageTool(count,artMap);
		articleList = this.articleService.getList(artMap);
		if(articleList!=null&&articleList.size()>0){
			Map aMap=(Map)articleList.get(0);
			String art_id=aMap.get("article_id").toString();
			article = this.articleService.get(art_id);
		    cat_attr_name = article.getCat_attr();
			cat_attr_name=CategoryFuc.getCateNameByMap(cat_attr_name);
			 //获取分类列表
			viewCat(article.getCat_attr());

			seoMap.put("article_name", article.getTitle());
			seoMap.put("article_seo_title", article.getSeo_title());
			seoMap.put("article_seo_keyword", article.getSeo_keyword());
			seoMap.put("article_seo_desc", article.getSeo_descri());
			seoMap.put("article_cat", cat_attr_name);
			setSeoPage("articlelist",seoMap);
		}else{
			cat_attr_name=CategoryFuc.getCateNameByMap(cat_attr);
			article.setTitle(cat_attr_name);
			article.setContent("暂无内容");
			 //获取分类列表
			viewCat(cat_attr);
			seoMap.put("article_name", cat_attr_name);
			seoMap.put("article_seo_title", cat_attr_name);
			seoMap.put("article_seo_keyword", cat_attr_name);
			seoMap.put("article_seo_desc", cat_attr_name);
			seoMap.put("article_cat", cat_attr_name);
			setSeoPage("articlelist",seoMap);
		}
		//获取文章分类
		getArticeCatList();
		return goUrl("articleDetail");
}
	
	
	/***
	 * 网站公告
	 * @return
	 */
	public String sitenotice()throws Exception{
		String cat_attr = article.getCat_attr();
		//获取信息
		Map artMap =new HashMap();
		artMap.put("cat_attr", cat_attr);
		artMap.put("is_display", "0");
		//根据页面提交的条件找出信息总数
		int count=this.articleService.getCount(artMap);
		//分页插件
		artMap = super.pageTool(count,artMap);
		articleList = this.articleService.getList(artMap);
		//获取分类列表
		viewCat(cat_attr);
		cat_name_=CategoryFuc.getCateNameByMap(cat_attr);
		//此处cat_attr_name是数字串，用来传参（分页用到）
		cat_attr_name=cat_attr;
		return goUrl("noticeList");
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(article == null){
			article = new Article();
		}
		String id = this.article.getArticle_id();
		if(!this.validateFactory.isDigital(id)){
			article = this.articleService.get(id);
		}
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCat_attr_name() {
		return cat_attr_name;
	}
	public void setCat_attr_name(String cat_attr_name) {
		this.cat_attr_name = cat_attr_name;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_name_() {
		return cat_name_;
	}
	public void setCat_name_(String cat_name_) {
		this.cat_name_ = cat_name_;
	}

}

