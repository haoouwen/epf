package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.JsonUtil;
import com.rbt.model.Goodsbrand;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.ICategoryService;

public class WebgoodsbrandAction  extends WebbaseAction implements Preparable  {
	
	/*******************实体层****************/
	public Goodsbrand goodsbrand;

	/*******************业务层接口****************/
	@Autowired
	private IGoodsbrandService goodsBrandService;
	@Autowired
	private ICategoryService categoryService;

	/*********************集合******************/
	public List goodsbrandList;//品牌列表
	public List recomBrandList;//推荐品牌列表
	public List relatedBrandList;//相关品牌列表
	public Map goodsBrandMap;//商品列表

	/*********************字段******************/
	public String brand_id;//品牌标识
	public String en_index;//品牌字母索引
	public String cat_name_str;//商品分类串
	public String brand_name;
	public String jsontotal;
	
	/**	
	 * @author : WXP
	 * @param :en_index 品牌索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @date Mar 26, 2014 10:04:22 AM
	 * @Method Description :品牌列表
	 */
	public String list() throws ParseException, IOException{
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
		return goUrl("brandStreet");
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 10, 2014 7:29:46 PM
	 * @Method Description :品牌详情
	 */
	public String detail() throws Exception{
		if(brand_id != null && !brand_id.equals("")){
			goodsbrand = this.goodsBrandService.get(brand_id);
			if(goodsbrand != null){
				cat_name_str=this.goodsBrandService.getCatName(goodsbrand.getGoods_attr(), cat_name_str);
				//获取推荐品牌
				recomBrandList = this.goodsBrandService.getBrandList(goodsbrand.getGoods_attr(), "1");
				//获取相关品牌
				relatedBrandList =this.goodsBrandService.getBrandList(goodsbrand.getGoods_attr(), "0");
				//SEO设置
				Map seoMap = new HashMap();
				seoMap.put("brand_name", goodsbrand.getBrand_name());
				seoMap.put("brand_cat", cat_name_str);
				setSeoPage("branddetail",seoMap);
			}
		}
		return goUrl("brandDetail");
	}
	
	/**
	 * @Method Description :随机获取商品品牌
	 * @author: HXK
	 * @date : Aug 20, 2015 5:33:55 PM
	 * @param 
	 * @return return_type
	 * @throws ParseException 
	 */
	public void getRandBrandList() throws IOException, ParseException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String band_number = request.getParameter("band_number");
		Map map = new HashMap();
		map.put("limit", band_number);
		map.put("start", "0");
		List list = goodsBrandService.getBrandRandList(map);
		jsontotal=JsonUtil.list2json(list);
		out.print(jsontotal);
	}

	
	
}
