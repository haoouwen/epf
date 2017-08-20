package com.rbt.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.vectorhighlight.BaseFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.FastVectorHighlighter;
import org.apache.lucene.search.vectorhighlight.FragListBuilder;
import org.apache.lucene.search.vectorhighlight.FragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.ScoreOrderFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.SimpleFragListBuilder;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.browseengine.bobo.api.BoboBrowser;
import com.browseengine.bobo.api.BoboIndexReader;
import com.browseengine.bobo.api.Browsable;
import com.browseengine.bobo.api.BrowseException;
import com.browseengine.bobo.api.BrowseFacet;
import com.browseengine.bobo.api.BrowseRequest;
import com.browseengine.bobo.api.BrowseResult;
import com.browseengine.bobo.api.FacetAccessible;
import com.browseengine.bobo.api.FacetSpec;
import com.browseengine.bobo.api.FacetSpec.FacetSortSpec;
import com.browseengine.bobo.facets.FacetHandler;
import com.browseengine.bobo.facets.impl.CompactMultiValueFacetHandler;
import com.browseengine.bobo.facets.impl.SimpleFacetHandler;

public class SearchIndex {

	IndexReader reader = null;
	static Analyzer analyzer = null;
	IndexSearcher searcher = null;
	TokenStream tokenStream = null;
	
	private boolean isNotExistIndex = true;

	/**
	 * @author : LJQ
	 * @date : Aug 30, 2014 3:25:41 PM
	 * @Method Description :初始化搜索的模块索引
	 */
	public SearchIndex(String filePath){
		// 获取IndexSearcher
		try {
			String fileIndexDir=LuceneUtil.getBasePath()+"/"+filePath;
			File fPath=new File(fileIndexDir);
			reader = IndexReader.open(FSDirectory.open(fPath));
			searcher = new IndexSearcher(reader);
			// 创建一个语法分析器
			analyzer = LuceneUtil.getAnalyzer();
		} catch (Exception e) {
			isNotExistIndex = false;
		}
		
	}

	

	/**
	 * @author : LJQ
	 * @throws InvalidTokenOffsetsException 
	 * @date : Jul 24, 2014 4:20:08 PM
	 * @Method Description : 查询搜索条件列表的数据
	 */
	public List search(List paraList, Sort sort, int start, int limit) throws ParseException, IOException{
		List list = new ArrayList();
		if(!isNotExistIndex) return list;
		BooleanQuery booleanQuery = getCommonQuery(paraList);
		//返回符合条件的列表数据
		TopDocs result = getCommonIndex(booleanQuery, sort);
		list = getDocList(result.scoreDocs,start,limit, booleanQuery);
		closeIO();
		return list;
	}
	
	
	
	/**
	 * @author : LJQ
	 * @date : Aug 29, 2014 2:55:08 PM
	 * @Method Description :关闭数据源
	 */
	public void closeIO(){
		try {
			reader.close();
			searcher.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Aug 30, 2014 3:27:06 PM
	 * @Method Description :公用的查询索引方法
	 */
	public BooleanQuery getCommonQuery(List paraList) throws ParseException{
		BooleanQuery booleanQuery=new BooleanQuery();
		if (paraList == null)
			return null;
		if (paraList.size() == 0)
			return null;

		Query query = null;

		for (Object obj : paraList) {
			ParaModel pm = (ParaModel) obj;
			String search_type = pm.getSearch_type();
			String[] fields = pm.getFields();
			String search_key = pm.getSearch_key();
			String search_value = pm.getSearch_value();
			String start_value = pm.getStart_value();
			String end_value = pm.getEnd_value();

			if (search_type.equals(Constants.NORMAL)) {
				query = normalQuery(search_key, search_value);
			}

			if (search_type.equals(Constants.MULTI)) {
				query = multiFieldQuery(fields, search_value);
			}

			if (search_type.equals(Constants.ONE)) {
				query = termQuery(search_key, search_value);
			}

			if (search_type.equals(Constants.RANGE)) {
				query = rangeQuery(search_key, start_value, end_value);
			}

			booleanQuery.add(query, BooleanClause.Occur.MUST);

		}
		return booleanQuery;
	}



	

	/**
	 * @author : LJQ
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Jul 24, 2014 4:39:17 PM
	 * @Method Description : 获取符合条件的搜索的记录条件
	 */
	public int getCount(List paraList) throws IOException, ParseException {
		int count=0;
		if(!isNotExistIndex) return count;
		BooleanQuery booleanQuery = getCommonQuery(paraList);
		//返回符合条件的列表数据
		TopDocs result = getCommonIndex(booleanQuery, null);
		//返回条数
		return result.totalHits;
	}
	/**
	 * @author LHY
	 * @methon 获取品牌
	 * @return
	 * @throws Exception 
	 */
	public List getGoodsBrand(List paraList) throws Exception{
		List list=new ArrayList();
		//判断不等于true就返回
		if(!isNotExistIndex)return list;
		//品牌名称ID
		String fieldName="brand_id";
		Map<String,FacetAccessible> facetMap=getCommonInfo(paraList,fieldName);
		if(facetMap.size()>0){
			System.out.println("--------------品牌列表--------------");
			//获取对应的品牌名称对象
			FacetAccessible vendorFacets=facetMap.get(fieldName);
			List<BrowseFacet> facetVals=vendorFacets.getFacets();
			for(BrowseFacet f:facetVals){
				//调用获取品牌名称，传入id
				String brand_id=f.getValue();
				if(brand_id.equals("0")){
					continue;
				}
				String brand_name=getBrandName(brand_id);
				String logo=getLoGo(brand_id);
				Map map=new HashMap();
				map.put("name",brand_name); 
				map.put("id",brand_id);
				map.put("logo",logo);
				//获取数量
				map.put("count",f.getHitCount());
				list.add(map);
				System.out.println("========="+f.getValue()+"======="+brand_name+"++========"+f.getHitCount());
			}
		}
		return list;
	}

	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws BrowseException 
	 * @date : Aug 1, 2014 11:27:54 AM
	 * @Method Description :获取分类信息数量的list方法
	 */
	public List catInfoNum(List paraList) throws IOException, ParseException, BrowseException{
		List list= new ArrayList();
		if(!isNotExistIndex) return list;
		String fieldName="cat_attr";
		String fieldLevel="cat_level";
		String level =getInfoLevel(paraList,fieldName,fieldLevel);
		Map<String,FacetAccessible> facetMap = getCommonInfo(paraList,fieldName);
        if(facetMap.size() > 0){
            System.out.println("----------------------分类列表----------------------");                  
            FacetAccessible vendorFacets = facetMap.get(fieldName);  
            List<BrowseFacet> facetVals = vendorFacets.getFacets();  
            for(BrowseFacet f:facetVals){  
            	//找出对应的等级后输出
            	String cat_level = getCatLevel(f.getValue(),fieldLevel);
            	String cat_name = getCatName(f.getValue());
            	//if(cat_level.equals(level)){
	        		Map listMap=new HashMap();
	            	listMap.put("id", f.getValue());
	            	listMap.put("name", cat_name);
	            	listMap.put("count", f.getHitCount());
	            	list.add(listMap);
                	//System.out.println(list);
                    //System.out.println(f.getValue() + "(" + f.getHitCount() + ")");  
            	//}           	
                System.out.println(level+"==========="+cat_level+"=========="+f.getHitCount()+"==========="+cat_name);
            }
        }
		return list;
	}
	
	
	/**
	 * @author : LJQ
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws BrowseException 
	 * @date : Aug 2, 2014 3:45:03 PM
	 * @Method Description : 通过公共的方法搜索
	 */
	private Map getCommonInfo(List paraList,String fieldName) throws ParseException, IOException, BrowseException{
		//搜索的条件
		BooleanQuery booleanQuery = getCommonQuery(paraList);
        //分组开始
        List<FacetHandler<?>> facetHandlers = new ArrayList<FacetHandler<?>>();
        //搜索的形式与搜索的字段,使用SimpleFacetHandler创建实例
        //facetHandlers.add(new CompactMultiValueFacetHandler(fieldName));
        facetHandlers.add(new SimpleFacetHandler(fieldName));
        BoboIndexReader boboIndexReader = BoboIndexReader.getInstance(reader,facetHandlers); 
        //搜索实例
        BrowseRequest browseRequest = new BrowseRequest();  
        browseRequest.setCount(10);  
        browseRequest.setOffset(0);  
        browseRequest.setQuery(booleanQuery); 
        //字段的设置
        FacetSpec facetSpec = new FacetSpec();  
        //搜索出来的标签数目  
        facetSpec.setMaxCount(10);
        //分类的排序  
        facetSpec.setOrderBy(FacetSortSpec.OrderHitsDesc);
        browseRequest.setFacetSpec(fieldName, facetSpec);  
        //浏览索引文件  
        Browsable browser = new BoboBrowser(boboIndexReader);  
        //搜索结果
        BrowseResult browseResult = browser.browse(browseRequest);  
        //返回搜索条数
        int totalHits = browseResult.getNumHits();  
        System.out.println("=====Total records: "+totalHits);  
        Map<String,FacetAccessible> facetMap = browseResult.getFacetMap();  
		return facetMap;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Aug 2, 2014 4:04:57 PM
	 * @Method Description :获取对应分类ID的等级
	 */
	private String getInfoLevel(List paraList,String fieldName,String fieldLevel) throws ParseException, IOException{
		String parent_id="";//点击该分类的id
        if(paraList!=null && paraList.size()>0){
        	for(int i=0;i<paraList.size();i++){
        		ParaModel paraModel =(ParaModel)paraList.get(i);
            	if(paraModel.getSearch_key()!=null && paraModel.getSearch_key().equals(fieldName)){
            		parent_id = paraModel.getSearch_value().toString();
            	}
        	}
        }
        // 设置下一级需要显示分类的等级
        String level="0";
        if(!parent_id.equals("")){
        	level=getCatLevel(parent_id,fieldLevel);
        }
        level=String.valueOf((Integer.parseInt(level)+1));
        return level;
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 30, 2014 11:22:55 AM
	 * @Method Description :获取对应地区的等级
	 */
	private String getAreaInfoLevel(List paraList,String fieldName,String fieldLevel) throws ParseException, IOException{
		String parent_id="";//点击该地区的id
        if(paraList!=null && paraList.size()>0){
        	for(int i=0;i<paraList.size();i++){
        		ParaModel paraModel =(ParaModel)paraList.get(i);
            	if(paraModel.getSearch_key()!=null && paraModel.getSearch_key().equals(fieldName)){
            		parent_id = paraModel.getSearch_value().toString();
            	}
            	
        	}
        }
        // 设置下一级需要显示地区的等级
        String level="0";
        if(!parent_id.equals("")){
        	level=getAreaLevel(parent_id,fieldLevel);
        }
        level=String.valueOf((Integer.parseInt(level)+1));
        return level;
	}
	
	
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 1, 2014 5:29:00 PM
	 * @Method Description : 搜索分类信息的等级
	 */
	private String getCatLevel(String cat_id,String fieldName) throws ParseException, IOException{
		String catLevel="1";
		List list =searchCatList(cat_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get(fieldName)!=null){
				catLevel=listMap.get(fieldName).toString();
			}
		}
		return catLevel;
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 30, 2014 11:29:06 AM
	 * @Method Description : 搜索地区信息的等级
	 */
	private String getAreaLevel(String area_id,String fieldName) throws ParseException, IOException{
		String areaLevel="1";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get(fieldName)!=null){
				areaLevel=listMap.get(fieldName).toString();
			}
		}
		return areaLevel;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Aug 2, 2014 1:17:44 PM
	 * @Method Description : 获取地区的信息数据
	 */
	public List areaInfoNum(List paraList) throws IOException, ParseException, BrowseException{
		List list= new ArrayList();
		if(!isNotExistIndex) return list;
		String fieldName="area_attr";
		String fieldLevel="area_level";
		//寻找area_attr中的对应的ID级别
		String level =getAreaInfoLevel(paraList,fieldName,fieldLevel);
		//寻找出上一级ID的等级+1,因为第一级是国家
		level=String.valueOf(Integer.parseInt(level)+1);
		Map<String,FacetAccessible> facetMap = getCommonInfo(paraList,fieldName);
        if(facetMap.size() > 0){
            System.out.println("----------------------地区列表----------------------");                  
            FacetAccessible vendorFacets = facetMap.get(fieldName);  
            List<BrowseFacet> facetVals = vendorFacets.getFacets();  
            for(BrowseFacet f:facetVals){  
            	//找出对应的等级后输出
            	String area_level = getAreaLevel(f.getValue(),fieldLevel);
            	String area_name = getAreaName(f.getValue());
            	System.out.println(level+"=============="+area_level+"=========="+area_name+"============"+"地区ID:"+f.getValue());
            	if(area_level.equals(level)){
            		Map listMap=new HashMap();
                	listMap.put("area_id", f.getValue());
                	listMap.put("area_name", area_name);
                	listMap.put("num", f.getHitCount());
                	list.add(listMap);
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");  
            	}           	
            }
        }
		return list;
	}
	
	

	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 1:06:49 PM
	 * @Method Description : 搜索地区的等级
	 */
	private String getAreaLevel(String area_id) throws ParseException, IOException{
		String areaLevel="1";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("area_level")!=null){
				areaLevel=listMap.get("area_level").toString();
			}
		}
		return areaLevel;
	}
	
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 9:55:15 AM
	 * @Method Description : 获取索引分类的名称
	 */
	private String getCatName(String cat_id) throws ParseException, IOException{
		String catName="";
		List list =searchCatList(cat_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("cat_name")!=null){
				catName=listMap.get("cat_name").toString();
			}
		}
		return catName;
	}
	/**
	 * @author LHY
	 * @param brand_id
	 * @return
	 * @throws Exception
	 * 获取品牌的名称
	 */
	private String getBrandName(String brand_id) throws Exception, Exception{
		String brand_name="";
		List list=searchBrandList(brand_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("brand_name")!=null){
				brand_name=listMap.get("brand_name").toString();
			}
		}
		return brand_name;
	}
	/**
	 * @author LHY
	 * @param brand_id
	 * @return
	 * @throws Exception
	 * 获取品牌的logo
	 */
	private String getLoGo(String brand_id) throws Exception, Exception{
		String logo="";
		List list=searchBrandList(brand_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("logo")!=null){
				logo=listMap.get("logo").toString();
			}
		}
		return logo;
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 1:15:16 PM
	 * @Method Description : 获取索引文件中对应的地区名称
	 */
	private String getAreaName(String area_id) throws ParseException, IOException{
		String areaName="";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("area_name")!=null){
				areaName=listMap.get("area_name").toString();
			}
		}
		return areaName;
	}
	
	
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 9:46:13 AM
	 * @Method Description : 获取搜索分类的列表
	 */
	private List searchCatList(String cat_id) throws ParseException, IOException{
		List catList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"cat_id"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		pm.setSearch_key("cat_id");
		if(cat_id!=null && cat_id.equals("")){
			cat_id=null;
		}
		pm.setSearch_value(cat_id);
		catList.add(pm);
		List list = new SearchIndex("category").search(catList,null, 0, 0);
		return list;
	}
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 9:46:13 AM
	 * @Method Description : 获取搜索分类的列表
	 */
	private List searchBrandList(String brand_id) throws ParseException, IOException{
		List catList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"brand_id"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(brand_id==null && brand_id.equals("")){
			brand_id=null;
		}
		pm.setSearch_value(brand_id);
		catList.add(pm);
		List list = new SearchIndex("goodsbrand").search(catList,null, 0, 0);
		return list;
	}

	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @date : Aug 2, 2014 1:08:08 PM
	 * @Method Description :搜索地区的列表
	 */
	private List searchAreaList(String area_id) throws ParseException, IOException{
		List areaList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"area_id"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(area_id!=null && area_id.equals("")){
			area_id=null;
		}
		pm.setSearch_value(area_id);
		areaList.add(pm);
		List list = new SearchIndex("area").search(areaList,null, 0, 0);
		return list;
	}
	/**
	 * @author : HXK 
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :搜索联想词列表
	 */
	public  List searchAssociationALLList(String ass_key_words_show) throws ParseException, IOException{
		List areaList = new ArrayList();
		ParaModel pm = new ParaModel();
		Sort so = new Sort();
		SortField sf = new SortField("sort_no", SortField.STRING,false);// 升序
		so.setSort(new SortField[] { sf });
		String[] fields = {"ass_key_words_show"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(ass_key_words_show!=null && ass_key_words_show.equals("")){
			ass_key_words_show=null;
		}
		pm.setSearch_value(ass_key_words_show);
		areaList.add(pm);
		List list = new SearchIndex("association").search(areaList,so, 0, 0);
		return list;
	}
	/**
	 * @author : HXK 
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :搜索联想词列表
	 */
	public  List searchAssociationList(String ass_key_words_show,String ass_key_words_title) throws ParseException, IOException{
		List areaList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"ass_key_words_show","ass_key_words_title"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(ass_key_words_show!=null && ass_key_words_show.equals("")){
			ass_key_words_show=null;
		}
		pm.setSearch_value(ass_key_words_show);
		if(ass_key_words_title!=null && ass_key_words_title.equals("")){
			ass_key_words_title=null;
		}
		pm.setSearch_value(ass_key_words_title);
		areaList.add(pm);
		List list = new SearchIndex("association").search(areaList,null, 0, 0);
		return list;
	}
	
	/**
	 * @author : HXK 
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :搜索联想词列表
	 */
	public  List searchGoodsCountList(String title,String cat_attr) throws ParseException, IOException{
		List areaList = new ArrayList();
		ParaModel pm = new ParaModel();
		
		String[] fields = {"goods_name","cat_attr","is_del","is_up","info_state"};
		
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(title!=null && title.equals("")){
			title=null;
		}
		pm.setSearch_value(title);
		if(cat_attr!=null && cat_attr.equals("")){
			cat_attr=null;
		}
		pm.setSearch_value(cat_attr);
		pm.setSearch_value("1");// 未被删除商品
		pm.setSearch_value("0");// 上架商品
		pm.setSearch_value("1");// 审核通过
		areaList.add(pm);
		List list = new SearchIndex("goods").search(areaList,null, 0, 0);
		return list;
		
	}
	
	
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws ParseException 
	 * @date : Jul 24, 2014 4:13:42 PM
	 * @Method Description : 获取公共的索引条件
	 */
	public TopDocs getCommonIndex(BooleanQuery booleanQuery,Sort sort) throws IOException, ParseException {
		// 上一页的最后一个document索引
		// 查询搜索引擎
		TopDocs result = null;
		if (sort != null) {
			result = searcher.search(booleanQuery, 1000, sort);
		} else {
			result = searcher.search(booleanQuery, 1000);
		}
		return result;
	}

	/**
	 * @author : LJQ
	 * @throws InvalidTokenOffsetsException 
	 * @throws IOException 
	 * @date : Jul 26, 2014 10:06:03 AM
	 * @Method Description : 文本高亮方法
	 */
	public String heightLight(String fieldName, String fieldValue,BooleanQuery booleanQuery) throws IOException, InvalidTokenOffsetsException {
		  //加亮处理格式
		  SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		  //实例化Highlighter组件  
          Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(booleanQuery)); 
          //设置处理高亮的长度
          int sss = fieldValue.length();
          highlighter.setTextFragmenter(new SimpleFragmenter(fieldValue.length()));
          String highLightText = null;
          String highLightreplase ="";
          if (highlighter != null) {  
        	  //获取text的TokenStream
              TokenStream tokenStream = analyzer.tokenStream(fieldName,new StringReader(fieldValue));  
              //获取高亮后的字符串
              highLightText = highlighter.getBestFragment(tokenStream, fieldValue); 
              if(highLightText!=null){
              String hgihtstr = highLightText.replace("<font color='red'>","").replace("</font>","");
              highLightreplase = fieldValue.replace(hgihtstr, highLightText);
              System.out.println(highLightText);    
              }
          }
          if(highLightText==null || highLightText.equals("")){
        	  return fieldValue;
          }
          return highLightreplase;
 }
	/**
	 * @author : LJQ
	 * @date : Aug 30, 2014 3:29:32 PM
	 * @Method Description : 对搜索的结果分页处理
	 */
	public List getDocList(ScoreDoc[] hits,int start,int limit,BooleanQuery booleanQuery) throws CorruptIndexException, IOException{
		List list = new ArrayList();
		if(start==0 && limit==0){
			for(ScoreDoc scoredoc: hits){
				Document doc = searcher.doc(scoredoc.doc);
				Map map = docToMap(doc, booleanQuery);
				list.add(map);
			}
		}else{
			//获取取搜索记录的索引
			int index=(start-1)*limit;
			int recordLen=index+limit;
			//如果需要显示的最大数大于总的条数时
			if(recordLen>hits.length){
				recordLen=hits.length;
			}
			//寻找分页数据
			if(index<=hits.length){
				for (int i = index; i <recordLen; i++) {
					 Document doc = searcher.doc(hits[i].doc);
					 Map map = docToMap(doc, booleanQuery);
					 list.add(map);
				}
			}
		}
		
		return list;
	}

	/**
	 * @author : LJQ
	 * @date : Aug 30, 2014 3:29:06 PM
	 * @Method Description :文档转换成map
	 */
	public Map docToMap(Document doc,BooleanQuery booleanQuery) throws IOException{
		Map map = new HashMap();
		List fieldList = doc.getFields();
		if (fieldList != null && fieldList.size() > 0) {
			for (int i = 0; i < fieldList.size(); i++) {
				Field fa = (Field) fieldList.get(i);
				String name = fa.name();
				String value = fa.stringValue();
				if (name != null) {
					//高亮设置
					try {
						if(name!=null&&name.equals("goods_name"))
						value=heightLight(name, value, booleanQuery);
					} catch (InvalidTokenOffsetsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					map.put(name, value);
				}
			}
		}
		return map;
	}

	/***************************************************************************
	 * 一个或多个字段搜索同一值 或查询 模糊查询
	 **************************************************************************/
	public Query multiFieldQuery(String[] fields, String value) throws ParseException {
		QueryParser parser = new MultiFieldQueryParser(Constants.VERSION, fields, analyzer);
		return parser.parse(value);
	}

	/** 单个字段精确查询 * */
	public Query termQuery(String field, String value) {
		return new TermQuery(new Term(field, value));
	}

	/** 范围查询 包括数字和日期* */
	/** 两个true表示是否包含搜索边界 * */
	public Query rangeQuery(String field, String start, String end) {
		return new TermRangeQuery(field, start, end, true, true);
	}

	/** 普通的字段查询 */
	public Query normalQuery(String field, String value) throws ParseException {
		QueryParser parser = new QueryParser(Constants.VERSION, field, analyzer);
		return parser.parse(value);
	}

}
