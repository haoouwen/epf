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
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer; 
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.HighlightParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.rbt.createHtml.InfoVo;
import com.rbt.index.SpecialDeal;

/**	
 * @author : WXP
 * @param :
 * @date Apr 24, 2014 10:10:17 AM
 * @Method Description :
 */
public class TestSolrServer{
	//商品信息索引SQL
	private static String goods_sql="SELECT distinct(g.goods_id),g.goods_id,g.info_state as info_state,g.is_del as is_del,g.cust_id as cust_id,g.is_up as is_up,LPAD(ga.sale_price,20,'0') AS lpad_price, REPLACE(g.cat_attr,',','/') as cat_attr,REPLACE(sc.area_attr,',','/') AS area_attr,g.goods_name as goods_name, brand_id, list_img,"+
					"up_date, down_date, lab,  g.in_date, g.user_id,specstr,market_price,sale_price,cost_price,stock,sale_num,"+
					"IF(SUM(od.order_num) IS NULL,0,SUM(od.order_num)) AS order_num,g.in_date as in_date,COUNT(r.trade_id) AS eval_num,click_num	,COUNT(c.goods_id) as collNum"+
					" FROM goods g LEFT JOIN goodsattr ga ON g.goods_id=ga.goods_id LEFT JOIN orderdetail od ON od.goods_id=g.goods_id "+        
					" LEFT JOIN goodsorder go ON go.order_id=od.order_id LEFT JOIN reviews r ON r.goods_id=g.goods_id LEFT JOIN shopconfig sc ON g.cust_id=sc.cust_id"+
					" LEFT JOIN collect c ON g.goods_id=c.goods_id GROUP BY g.goods_id ";
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 24, 2014 10:10:17 AM
	 * @Method Description :初始化solr服务器
	 */ 
    private  static HttpSolrServer getHttpSolrServer() {  
        String url = "http://localhost:8080/solr/rbtb2c2c";  
        HttpSolrServer server = new HttpSolrServer(url);  
        server.setSoTimeout(3000); // socket read timeout  
        server.setConnectionTimeout(1000);  
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
	       			System.out.println(listMap.toString());
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
     * @param :
     * @date Apr 24, 2014 10:04:43 AM
     * @Method Description :查询
     */
    public void search(HttpSolrServer server, Map params) {        
//        SolrQuery query = new SolrQuery();  
//        query.setQuery(string);  
  
        try {  
            SolrParams solrParams = new MapSolrParams(params); 
        	
            QueryResponse response = server.query(solrParams);  
            SolrDocumentList docs = response.getResults();  
          //第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
            Map<String, Map<String, List<String>>> hl = response.getHighlighting();
  
            System.out.println("文档个数：" + docs.getNumFound());  
            System.out.println("查询时间：" + response.getQTime());  
  
            for (SolrDocument doc : docs) {  
                String goods_name = (String) doc.getFieldValue("goods_name");  
                int goods_id = (Integer) doc.getFieldValue("goods_id");  
                Double sale_price = (Double) doc.getFieldValue("sale_price");
                System.out.println("商品标识: " + goods_id); 
                System.out.println("商品名称: " + goods_name);  
                System.out.println("售价: " + sale_price);  
                String id = String.valueOf(goods_id).trim();
                if(hl.get(id) != null){
                    System.out.println("高亮："+hl.get(id).get("goods_name"));
                }
                System.out.println();  
            }  
        } catch (SolrServerException e) {  
            e.printStackTrace();  
        } catch(Exception e) {  
            System.out.println("Unknowned Exception!!!!");  
            e.printStackTrace();  
        }  
    }

    /**	
     * @author : WXP
     * @param :HttpSolrServer,Query (*:*所有)
     * @date Apr 24, 2014 10:05:00 AM
     * @Method Description :删除索引
     */
    public void deleteIndex(HttpSolrServer server, String query){
    	try {
            //删除所有的索引
    		server.deleteByQuery(query);
    		server.commit();

	     } catch (Exception e) {
	            e.printStackTrace();
	     }
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpSolrServer httpSolrServer = getHttpSolrServer();         
		//测试实例！  
		TestSolrServer  test = new TestSolrServer(); 
		
		//查询
		Map map = new HashMap();  
        map.put(CommonParams.Q, "goods_name:韩都");  
        map.put(CommonParams.START, "0");  
        map.put(CommonParams.ROWS, "10");  
        map.put(CommonParams.SORT, "sale_price asc"); 
        //map.put(CommonParams.FQ, "goods_name:南极人"); 
        //开启高亮
        map.put(HighlightParams.HIGHLIGHT, "true");
        //设置高亮字段
        map.put(HighlightParams.FIELDS, "goods_name");
        map.put(HighlightParams.SIMPLE_PRE, "<font color=\"red\">");
        map.put(HighlightParams.SIMPLE_POST, "</font>");
		test.search(httpSolrServer, map);
		
		//删除全部索引
		//test.deleteIndex(httpSolrServer, "*:*");
		
		//创建索引
//		List list = getSourceList(goods_sql);
//		test.createIndex(httpSolrServer, list);
	}

}
