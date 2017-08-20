package com.rbt.webappaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Goods;
import com.rbt.service.IGoodsbrandService;

public class WebAppbrandAction extends WebAppgoodsModelAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4286534110288145209L;

	/*******************实体层****************/
	public Goods goods;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsbrandService goodsBrandService;
	/*********************集合******************/
	
	public List goodsbrandList;//品牌列表
	public Map goodsBrandMap;//商品列表
	/*********************字段******************/
	public String en_index;//品牌字母索引
	public String brand_name;
	/**	
	 * @author : HXK
	 * @param :en_index 品牌索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws java.text.ParseException 
	 * @Method Description :品牌列表
	 */
	public String list() throws ParseException, IOException, java.text.ParseException{
		reqKeyword("brand_name", "goodsbrand", null);
		if(getRequest().getParameter("en_wd")!=null&&!"".equals(getRequest().getParameter("en_wd"))){
			en_index=getRequest().getParameter("en_wd").substring(0, 1);
		}
		if(searchText!=null&&searchText.endsWith(" ")){
			searchText=searchText.trim();
		}
		//搜索查询
		goodsBrandMap=this.goodsBrandService.goodsBrandList(en_index, cat_id,searchText);
		//SEO设置
		Map seoMap = new HashMap();
		setSeoPage("brandlist",seoMap);
		return goUrl("mbBrandList");
	}
	public void prepare() throws Exception {
	}

}
