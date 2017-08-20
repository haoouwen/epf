/*
 
 * Package:com.rbt.action
 * FileName: ArticleAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Article;
import com.rbt.service.IArticleService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录文章管理表信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 20 10:44:52 CST 2014
 */
@Controller
public class ArticleAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Article article;

	/*******************业务层接口****************/
	@Autowired
	private IArticleService articleService;

	/*********************集合******************/
	public List articleList;//文章

	/*********************字段******************/
	public String art_author_s;//搜索作者
	public String title_s;//搜索标题
	public String is_display_s;//搜索是否显示
	public String is_sticky_s;//搜索是否置顶
	public String cat_attr_s;//搜索分类

	/**
	 * 方法描述：返回新增记录文章管理表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录文章管理表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 字段验证
		if (commonCheck())
			return add();
		this.articleService.insert(article);
		this.addActionMessage("新增文章成功！");
		this.article = null;
		return list();
	}

	/**
	 * 方法描述：修改记录文章管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 字段验证
		if (commonCheck()) {
			return view();
		}
		this.articleService.update(article);
		this.addActionMessage("修改文章成功！");
		return list();
	}

	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 12:42:39 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		if(ValidateUtil.isRequired(cat_attr)||"0".equals(cat_attr)){
			this.addFieldError("article.cat_attr", "请选择分类");
			return true;
		}
		cat_attr = cat_attr.replace(" ", "");
		this.article.setCat_attr(cat_attr);
		this.article.setCust_id(this.session_cust_id);
		this.article.setUser_id(this.session_user_id);
		super.commonValidateField(article);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：删除记录文章管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.articleService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除文章信息成功");
		} else {
			this.addActionMessage("删除文章信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (art_author_s != null && !art_author_s.equals("")) {
			pageMap.put("art_author_", art_author_s);
		}
		if (title_s != null && !title_s.equals("")) {
			title_s=title_s.replace(" ", "");
			pageMap.put("title", title_s);
		}
		if (is_sticky_s != null && !is_sticky_s.equals("")) {
			pageMap.put("is_sticky", is_sticky_s);
		}
		if (is_display_s != null && !is_display_s.equals("")) {
			pageMap.put("is_display", is_display_s);
		}
		// 获取搜索的分类
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
			//回选分类
			viewCat(cat_attr_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.articleService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		articleList = this.articleService.getList(pageMap);
		articleList = ToolsFuc.replaceList(articleList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录文章管理表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.article.getArticle_id();
		if (id == null || id.equals("")) {
			article = new Article();
		} else {
			article = this.articleService.get(id);
		}
		viewCat(article.getCat_attr());
		return goUrl(VIEW);
	}
	/**
	 * @author Administrator 异步判断文章名称是否重复
	 * @throws Exception
	 */
	public void ajaxArticleNameCopy() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String b_id = request.getParameter("b_id");//文章ID
		String b_name = request.getParameter("b_name");//文章名称
		String b_type = request.getParameter("b_type");//类型 0新增 1修改
		PrintWriter out = response.getWriter();
		String rString="0";//表示不重复,1表示重复
		if("0".equals(b_type)){
			//新增
			if(!checkArticleName("",b_name)){
				rString="1";
			}
		}else {
			//修改
			if(!checkArticleName(b_id,b_name)){
				rString="1";
			}
		}
		out.write(rString);
	}
	public boolean checkArticleName(String b_id,String b_name){
		boolean  fage=true;
		if(!ValidateUtil.isRequired(b_name)){
			Map map=new HashMap();
			map.put("article_name_all",b_name);
	        List blist=this.articleService.getList(map);
	        if(blist!=null&&blist.size()>0){
	           //存在品牌名称
	           if(!ValidateUtil.isRequired(b_id)){
	        	   //继续验证 是更新的情况，因为更新的本身就存在文章标题 需要过滤，通过文章id来判别
	        	   for(int i=0;i<blist.size();i++){
	        		   Map mapbrand=new HashMap();
	        		   mapbrand=(HashMap)blist.get(i);
	        		   if(mapbrand!=null&&mapbrand.get("article_id")!=null&&!b_id.equals(mapbrand.get("article_id").toString())){
	        			   //判断获取到列表中 是否有不等于自己的文章ID的数据
	        			   fage=false;
	        			   break;
	        		   }
	        	   }
	           }else {
	        	   //新增的模式，存在直接判断为重复
	        	   fage=false;
			   }
	        }
		}
		return fage;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (article == null) {
			article = new Article();
		}
		String id = this.article.getArticle_id();
		if (!ValidateUtil.isDigital(id)) {
			article = this.articleService.get(id);
		}
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
