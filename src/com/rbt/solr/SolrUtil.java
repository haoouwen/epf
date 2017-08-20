package com.rbt.solr;

import java.io.IOException;  
import java.io.Serializable;  
import java.net.MalformedURLException;  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer; 
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.HighlightParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.rbt.createHtml.InfoVo;
import com.rbt.function.SysconfigFuc;
import com.rbt.index.ParaModel;
import com.rbt.index.SpecialDeal;

public class SolrUtil{
	
	//商品信息索引SQL 测试使用
	private static String goods_sql="SELECT distinct(g.goods_id),g.goods_id,g.goods_id AS index_id,g.info_state as info_state,g.is_del as is_del,g.cust_id as cust_id,g.is_up as is_up,LPAD(ga.sale_price,20,'0') AS lpad_price, g.cat_attr,sc.area_attr,g.goods_name as goods_name, brand_id, list_img,"+
					"up_date, down_date, lab,  g.in_date, g.user_id,specstr,market_price,sale_price,cost_price,stock,sale_num,"+
					"IF(SUM(od.order_num) IS NULL,0,SUM(od.order_num)) AS order_num,g.in_date as in_date,COUNT(r.trade_id) AS eval_num,click_num	,COUNT(c.goods_id) as collNum"+
					" FROM goods g LEFT JOIN goodsattr ga ON g.goods_id=ga.goods_id LEFT JOIN orderdetail od ON od.goods_id=g.goods_id "+        
					" LEFT JOIN goodsorder go ON go.order_id=od.order_id LEFT JOIN reviews r ON r.goods_id=g.goods_id LEFT JOIN shopconfig sc ON g.cust_id=sc.cust_id"+
					" LEFT JOIN collect c ON g.goods_id=c.goods_id GROUP BY g.goods_id  ";
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 10:10:17 AM
	 * @Method Description :初始化solr服务器
	 */ 
    public  static HttpSolrServer getHttpSolrServer() {  
    	//solr web访问地址
    	String solr_host = SysconfigFuc.getSysValue("cfg_solr_host");
    	//solr 应用名称
    	String solr_name = SysconfigFuc.getSysValue("cfg_solr_name");
    	//solr 核心名称
    	String solr_core_name = SysconfigFuc.getSysValue("cfg_solr_core_name");
        String url = solr_host+"/"+solr_name+"/"+solr_core_name;  
    	//String url = "http://solr.xxxx.net/b2c2c_dev";
        HttpSolrServer server = new HttpSolrServer(url);  
        server.setSoTimeout(3000*60); // socket read timeout  
        server.setConnectionTimeout(1000*60);  
        server.setDefaultMaxConnectionsPerHost(1000);  
        server.setMaxTotalConnections(10);  
        server.setFollowRedirects(false); // defaults to false  
        server.setAllowCompression(true);  
        server.setMaxRetries(1);  
          
        return server;  
    } 
    
    /**	
     * @author : WXP
     * @param : sql
     * @date Apr 24, 2014 10:13:09 AM
     * @Method Description :获取需要索引的数据列表
     */
	public static List getSourceList(String sql){
		InfoVo iv = new InfoVo();
		List infoList = iv.getInfoList(sql, null);
		return infoList;
	}
	    
    /**	
     * @author : WXP
     * @param :HttpSolrServer,List
     * @date Apr 24, 2014 10:10:18 AM
     * @Method Description :创建索引
     */
    public void createIndex(HttpSolrServer server,List list){
    	 Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    	 if(list== null || list.size()==0){
  		   return;
  		}
         try {
	        	//循环建立索引
	       		Map listMap = new HashMap();
	       		for(int i = 0;i < list.size(); i++){
	       			listMap = (HashMap) list.get(i);
	       			SolrInputDocument doc = new SolrInputDocument();
	       			//System.out.println(listMap.toString());
	       			addDoc(doc,listMap);
	       			docs.add(doc);
	       		}
                server.add(docs);
                //对索引进行优化
                server.optimize();
                server.commit();
         } catch (Exception e) {
                e.printStackTrace();
         }
    }
    /**	
     * @author : WXP
     * @param :Map
     * @date Apr 24, 2014 10:36:25 AM
     * @Method Description :迭代将集合中的索引加入文档
     */
    public void addDoc(SolrInputDocument doc, Map map) throws CorruptIndexException, IOException{
		if(map == null) return;
		Iterator iter = map.entrySet().iterator();
		//建立索引文档
		//迭代加入索引文档
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = "",value = "";
			if(entry.getKey() != null){
				key = entry.getKey().toString();
			}
			if(entry.getValue() != null){
				value = entry.getValue().toString();
			}
			//加入文档中
			if(!value.equals("")){
				doc.addField(key,value);  
			}
		}
	}
    
    /**	
     * @author : WXP
     * @param :params
     * @date Apr 25, 2014 1:26:29 PM
     * @Method Description :返回文档总数
     */
    public int getCount(ModifiableSolrParams params){
    	try {  
    		HttpSolrServer httpSolrServer = getHttpSolrServer();    
            SolrParams solrParams = new ModifiableSolrParams(params); 
            QueryResponse response = httpSolrServer.query(solrParams);  
            SolrDocumentList docs = response.getResults();  
            int count = (int) docs.getNumFound();
            System.out.println("文档个数：" + docs.getNumFound());  
            return count;
        } catch (SolrServerException e) {  
            e.printStackTrace();  
            return 0;
        } catch(Exception e) {  
            System.out.println("Unknowned Exception!!!!");  
            e.printStackTrace(); 
            return 0;
        }  
    }
    
    /**	
     * @author : WXP
     * @param :params:查询条件,facet_field:分组统计字段,facet_query:统计条件
     * @date Apr 26, 2014 10:44:43 AM
     * @Method Description :facet查询
     */
    public List facetSearch(ModifiableSolrParams params, String facet_field, String facet_query) {        
    	//构建存放文档的list
    	List facetList = new ArrayList();
        try {  
        	HttpSolrServer httpSolrServer = getHttpSolrServer();     
        	//开启facet分组统计
        	params.remove(FacetParams.FACET);
        	params.add(FacetParams.FACET,"on"); 
        	params.remove(FacetParams.FACET_MINCOUNT);
        	params.add(FacetParams.FACET_MINCOUNT,"1"); 
        	if(facet_field != null && !facet_field.equals("")){
        		params.remove(FacetParams.FACET_FIELD);
        		params.add(FacetParams.FACET_FIELD,facet_field); 
        	}
        	if(facet_query != null && !facet_query.equals("")){
        		params.remove(FacetParams.FACET_QUERY);
        		params.add(FacetParams.FACET_QUERY,facet_query);
        	}
        	
            SolrParams solrParams = new ModifiableSolrParams(params); 
            QueryResponse response = httpSolrServer.query(solrParams);  
            
            //封装FQ参数到map中
            String[] fqs  = solrParams.getParams(CommonParams.FQ);
            Map fq_map = new HashMap();
            for(int i = 0; i < fqs.length; i++){
            	String[] fq = fqs[i].split(":");
            	fq_map.put(fq[0], fq[1]);
            }
            System.out.println("---------------getParam:"+fq_map);
            
            //获取facet列表
            List<FacetField> facets = response.getFacetFields(); 
            for(FacetField facet : facets)
            {
            	List<FacetField.Count> facetEntries = facet.getValues();
            	String facet_name = facet.getName();
                for(FacetField.Count fcount : facetEntries)
                {
                	Map map = new HashMap();
                	if(facet_name != null && facet_name.equals("cat_attr")){
                		//获取分类等级
                		String cat_level = "1";
                		if(fq_map.get("cat_attr") != null){
                			cat_level = getCatLevel(fq_map.get("cat_attr").toString());
                		}
                		cat_level = String.valueOf(Integer.parseInt(cat_level)+1);
                		String cat_name = getCatName(fcount.getName(), cat_level);
                		String level_cat_id = getCatId(fcount.getName(), cat_level);
                		if(!cat_name.equals("")){
                			map.put("id", level_cat_id);
                        	map.put("count", fcount.getCount());
                			map.put("name", cat_name);//分类名称
                		}
                	}else if(facet_name != null && facet_name.equals("brand_id")){
                		String brand_name = getBrandName(fcount.getName());
                		if(!brand_name.equals("")){
                			map.put("id", fcount.getName());
                        	map.put("count", fcount.getCount());
                			map.put("name", brand_name);//品牌名称
                		}
                	}
                	if(!map.isEmpty()){
                		facetList.add(map);
                	}
                }
            }
            System.out.println("---------------------------"+facetList);
            return facetList;
        } catch (SolrServerException e) {  
            e.printStackTrace();  
            return null;
        } catch(Exception e) {  
            System.out.println("Unknowned Exception!!!!");  
            e.printStackTrace();  
            return null;
        }  
    }
    /**
     * @author : WXP
     * @param cat_id
     * @return cat_level
     * @throws ParseException
     * @throws IOException
     * @Method Description :获取分类等级
     */
    private String getCatLevel(String cat_id) throws ParseException, IOException{
    	String cat_level = "1";
    	ModifiableSolrParams map = new ModifiableSolrParams();
		map.add(CommonParams.Q, "cat_id:"+cat_id);
		List list = search(map);
		if(list != null && list.size() > 0){
			HashMap listMap = (HashMap)list.get(0);
			if(listMap.get("cat_level") != null){
				cat_level = listMap.get("cat_level").toString();
			}
		}
        return cat_level;
	}
    /**	
     * @author : WXP
     * @param :cat_id
     * @date Apr 27, 2014 3:58:45 PM
     * @Method Description :获取分类名称
     */
    private String getCatName(String cat_id, String cat_level) throws Exception, Exception{
    	//返回的分类名称
		String cat_name = "";
		String[] _cat_id = cat_id.split(",");
		//最后一级分类等级
		String last_level = getCatLevel(_cat_id[_cat_id.length-1]);
		if(Integer.parseInt(last_level) < Integer.parseInt(cat_level)){
			cat_level = last_level;
		}
		for(int i = 0; i < _cat_id.length; i++){
			String level = "1";
			level = getCatLevel(_cat_id[i]);
			if(cat_level.equals(level)){
				ModifiableSolrParams map = new ModifiableSolrParams();
				map.add(CommonParams.Q, "cat_id:"+_cat_id[i]);
				System.out.println("----------------------cat_id:"+_cat_id[i]);
				//map.put(CommonParams.FL, "brand_name");
				List list = search(map);
				if(list != null && list.size() > 0){
					HashMap listMap = (HashMap)list.get(0);
					if(listMap.get("cat_name") != null){
						if(!cat_name.equals("")){
							cat_name = cat_name +","+ listMap.get("cat_name").toString();
						}else{
							cat_name = listMap.get("cat_name").toString();
						}
					}
				}
			}
		}
		return cat_name;
	}
    /**	
     * @author : WXP
     * @param :cat_id
     * @date Apr 27, 2014 3:58:45 PM
     * @Method Description :获取对应等级的cat_id
     */
    private String getCatId(String cat_id, String cat_level) throws Exception, Exception{
    	//返回的分类ID
		String level_cat_id = "";
		String[] _cat_id = cat_id.split(",");
		for(int i = 0; i < _cat_id.length; i++){
			String level = "1";
			level = getCatLevel(_cat_id[i]);
			if(cat_level.equals(level)){
				level_cat_id = _cat_id[i];
			}
		}
		return level_cat_id;
	}
    /**	
     * @author : WXP
     * @param :brand_id
     * @date Apr 27, 2014 3:58:45 PM
     * @Method Description :获取品牌名称
     */
    private String getBrandName(String brand_id) throws Exception, Exception{
		String brand_name = "";
		ModifiableSolrParams map = new ModifiableSolrParams();
		map.add(CommonParams.Q, "brand_id:"+brand_id);
		//map.put(CommonParams.FL, "brand_name");
		List list = search(map);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				HashMap listMap = (HashMap)list.get(i);
				if(listMap.get("brand_name") != null){
					brand_name = listMap.get("brand_name").toString();
				}
			}
		}
		return brand_name;
	}
    
    /**	
     * @author : WXP
     * @param :fields,map
     * @date Apr 24, 2014 10:10:17 AM
     * @Method Description :开启高亮
     */
    public void highlight(String fields, ModifiableSolrParams map){
    	//开启高亮
        map.add(HighlightParams.HIGHLIGHT, "true");
        //设置高亮字段
        map.add(HighlightParams.FIELDS, fields);
        map.add(HighlightParams.SIMPLE_PRE, "<font color=\"red\">");
        map.add(HighlightParams.SIMPLE_POST, "</font>");
    }
    
    /**	
     * @author : WXP
     * @param :
     * @date Apr 24, 2014 10:04:43 AM
     * @Method Description :查询
     */
    public List search(ModifiableSolrParams params) {        
//        SolrQuery query = new SolrQuery();  
//        query.setQuery("goods_name:sdfsd");
    	//构建存放文档的list
        List list = new ArrayList();
        
        try {  
        	HttpSolrServer httpSolrServer = getHttpSolrServer();  
            SolrParams solrParams = new ModifiableSolrParams(params); 
            QueryResponse response = httpSolrServer.query(solrParams);  
            SolrDocumentList docs = response.getResults();  
            //第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
            Map<String, Map<String, List<String>>> hl = response.getHighlighting();
            System.out.println("文档个数：" + docs.getNumFound());  
            System.out.println("查询时间：" + response.getQTime());  
  
            for (SolrDocument doc : docs) {  
            	//文档主键
                String index_id = (String) doc.getFieldValue("index_id");  
                if(index_id != null){
                	index_id = index_id.trim();
                }
                if(hl != null && hl.get(index_id) != null){
                	//高亮字段名称
                	String hf = "";
                	if(hl.get(index_id).get("goods_name") != null){
                		hf = hl.get(index_id).get("goods_name").toString();
                	}
                	//替换高亮商品名称
                	if(hf != null && !hf.equals("")){
                		doc.removeFields("goods_name");
    					doc.addField("goods_name", hl.get(index_id).get("goods_name"));
                	}
                }
                System.out.println();  
                System.out.println(doc.getFieldNames());
                Map map = new HashMap();
                //字段名词集合
                Iterator<String> it = doc.getFieldNames().iterator(); 
                while (it.hasNext()) {  
            	  String field = it.next();  
            	  map.put(field, doc.getFieldValue(field));
            	}  
                list.add(map);
            }  
            return list;
        } catch (SolrServerException e) {  
            e.printStackTrace();  
            return null;
        } catch(Exception e) {  
            System.out.println("Unknowned Exception!!!!");  
            e.printStackTrace();  
            return null;
        }  
    }

    /**	
     * @author : WXP
     * @param :Query (*:*所有)
     * @date Apr 24, 2014 10:05:00 AM
     * @Method Description :删除索引
     */
    public void deleteIndex(String query){
    	try {
    		HttpSolrServer httpSolrServer = getHttpSolrServer();   
    		httpSolrServer.deleteByQuery(query);
    		httpSolrServer.commit();

	     } catch (Exception e) {
	            e.printStackTrace();
	     }
    }
    
    /**	
     * @author : WXP
     * @param :list(文档主键集合)
     * @date Apr 24, 2014 10:05:00 AM
     * @Method Description :删除索引
     */
    public void deleteIndex(List list){
    	try {
    		HttpSolrServer httpSolrServer = getHttpSolrServer();   
            //删除所有的索引
    		httpSolrServer.deleteById(list);
    		httpSolrServer.commit();

	     } catch (Exception e) {
	            e.printStackTrace();
	     }
    }
    
    /**	
     * @author : WXP
     * @param :sql
     * @date Apr 27, 2014 12:55:00 PM
     * @Method Description :删除索引记录
     */
    public void deleteInfo(String sql){
		InfoVo iv=new InfoVo();
		iv.excuteSql(sql);
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpSolrServer httpSolrServer = getHttpSolrServer();         
		//测试实例！  
		SolrUtil  test = new SolrUtil(); 
		
		//查询
		ModifiableSolrParams map = new ModifiableSolrParams();  
		SolrQuery filterQuery = new SolrQuery();  
 	    map.add(CommonParams.Q, "*:*");  
	    map.add(CommonParams.START, "0");  
	    map.add(CommonParams.ROWS, "10");  
	    map.add(CommonParams.SORT, "sale_price asc"); 
	    filterQuery.addFilterQuery("cust_id:56");
	    filterQuery.addFilterQuery("cat_attr:33333");
	   
	    map.add(filterQuery);
	    
	    test.search(map);
//	    //开启高亮
//	    map.put(HighlightParams.HIGHLIGHT, "true");
//	    //设置高亮字段
//	    map.put(HighlightParams.FIELDS, "goods_name");
//	    map.put(HighlightParams.SIMPLE_PRE, "<font color=\"red\">");
//	    map.put(HighlightParams.SIMPLE_POST, "</font>");
//	    //facet查询
//	    map.put(FacetParams.FACET,"on"); 
//	    map.put(FacetParams.FACET_FIELD,"cat_attr"); 
//	    map.put(FacetParams.FACET_MINCOUNT,"1"); 
//		test.facetSearch(map);
		
		//删除全部索引
		//test.deleteIndex("*:*");
		
		//创建索引
//		List list = getSourceList(goods_sql);
//		test.createIndex(httpSolrServer, list);
	}

}
