/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ArticleService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IArticleDao;
import com.rbt.dao.ICategoryDao;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Article;
import com.rbt.model.Category;
import com.rbt.service.IArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录文章管理表信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 20 10:44:52 CST 2014
 */
@Service
public class ArticleService extends GenericService<Article,String> implements IArticleService {
	@Autowired
	private ICategoryDao categoryDao;
	IArticleDao articleDao;
	@Autowired
	public ArticleService(IArticleDao articleDao) {
		super(articleDao);
		this.articleDao = articleDao;
	}
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 3:29:44 PM
	 * @Method Description：获取详细页面信息
	 */
   public Map getDetail(String id){
	   String article_nav="";
	   Article	article = new Article();
	   String cat_attr_name="";
	   Map map=new HashMap();
	   if(id==null || id.equals("")){
		  return map;
		  }else{
		   article = this.articleDao.get(id);
			if(article.getCat_attr()==null){
				return null;
			}
		    String[] cat_ids = article.getCat_attr().split(",");
		    
		    for(int i=0;i<cat_ids.length;i++){
		    	if(cat_ids[i]!=null && !cat_ids[i].equals("")){
		    		if(i==cat_ids.length-1){
		    			article_nav+="<a href='/mall-articleattrdetail-"+cat_ids[i]+".html'>"+CategoryFuc.getCateNameByMap(cat_ids[i])+"</a>";
		    		}else{
		    			article_nav+="<a href='/mall-articleattrdetail-"+cat_ids[i]+".html'>"+CategoryFuc.getCateNameByMap(cat_ids[i])+"</a>&nbsp<b>></b>&nbsp";
		    		}
		    		
		    	}
		    }
		}
		//获取文章列表
		Map articleMap =new HashMap();
	  	List articleList = this.articleDao.getList(articleMap);
		//获取文章分类
		List categoryList=getArticeCatList();
       Map atMap=new HashMap();
	   atMap.put("article", article);
	   atMap.put("article_nav", article_nav);
	   atMap.put("articleList", articleList);
	   atMap.put("categoryList", categoryList);
       return atMap;
   }
	//获取文章分类列
	public List getArticeCatList(){
	    Map artcleMap =new HashMap();
	    artcleMap.put("module_type", "article");
		return  this.categoryDao.getList(artcleMap);
	
	}
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 3:59:15 PM
	 * @Method Description：分类列文章
	 */
	public Map getAttrDetail(String cat_attr,String page_cat_attr){
		String article_frist_cat="";
		String article_nav="";
		if(cat_attr==null||cat_attr.equals("")){
			cat_attr=page_cat_attr;
		}
		if(cat_attr!=null&&!cat_attr.equals("")&&this.categoryDao.get(cat_attr)!=null){
			Category category = this.categoryDao.get(cat_attr);
			if(category.getUp_cat_id()!=null&&!category.getCat_level().equals("1")){
				article_frist_cat=category.getUp_cat_id();
				article_nav="<a href='/mall-articleattrdetail-"+article_frist_cat+".html'>"+CategoryFuc.getCateNameByMap(article_frist_cat)+"</a>";
				article_nav+="&nbsp<b>></b>&nbsp";
				article_nav+="<a href='/mall-articleattrdetail-"+category.getCat_id()+".html'>"+category.getCat_name()+"</a>";
			}else{
				article_frist_cat=cat_attr;
				article_nav+="<a href='/mall-articleattrdetail-"+category.getCat_id()+".html'>"+category.getCat_name()+"</a>";
			}
		}
		Map map=new HashMap();
		map.put("article_frist_cat", article_frist_cat);
		map.put("article_nav", article_nav);
		map.put("cat_attr", cat_attr);
		return map;
	}
}

