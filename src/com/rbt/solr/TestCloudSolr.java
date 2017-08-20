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
  
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer; 
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.cloud.ClusterState; 
import org.apache.solr.common.cloud.ZkStateReader;

public class TestCloudSolr{
	
	private static CloudSolrServer cloudSolrServer;  
     
    private  static synchronized CloudSolrServer getCloudSolrServer(final String zkHost) {  
        if(cloudSolrServer == null) {  
            try {  
                cloudSolrServer = new CloudSolrServer(zkHost);  
            }catch(MalformedURLException e) {  
                System.out.println("The URL of zkHost is not correct!! Its form must as below:\n zkHost:port");  
                e.printStackTrace();  
            }catch(Exception e) {  
                e.printStackTrace();                  
            }  
        }  
          
        return cloudSolrServer;  
    } 
	    
    public void search(CloudSolrServer cloudSolrServer, String String) {        
        SolrQuery query = new SolrQuery();  
        query.setQuery(String);  
  
        try {  
            QueryResponse response = cloudSolrServer.query(query);  
            SolrDocumentList docs = response.getResults();  
  
            System.out.println("文档个数：" + docs.getNumFound());  
            System.out.println("查询时间：" + response.getQTime());  
  
            for (SolrDocument doc : docs) {  
                String goods_name = (String) doc.getFieldValue("goods_name");  
                String goods_id = (String) doc.getFieldValue("goods_id");  
                System.out.println("goods_id: " + goods_id);  
                System.out.println("goods_name: " + goods_name);  
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
	 * @param args
	 */
	public static void main(String[] args) {
		final String zkHost = "localhost:8080";       
        final String  defaultCollection = "rbtb2c2c";  
        final int  zkClientTimeout = 20000;  
        final int zkConnectTimeout = 1000; 
        
		CloudSolrServer cloudSolrServer = getCloudSolrServer(zkHost);         
        System.out.println("The Cloud SolrServer Instance has benn created!");  
          
        cloudSolrServer.setDefaultCollection(defaultCollection);  
        cloudSolrServer.setZkClientTimeout(zkClientTimeout);  
        cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);    
          
        cloudSolrServer.connect();  
        System.out.println("The cloud Server has been connected !!!!");  
        
        ZkStateReader zkStateReader = cloudSolrServer.getZkStateReader();  
        ClusterState clusterState  = zkStateReader.getClusterState();  
        System.out.println(clusterState);  
        
        //测试实例！  
        TestCloudSolr  test = new TestCloudSolr();  

        System.out.println("测试查询query！！！！");  
        test.search(cloudSolrServer, "goods_name:*");  
        
        cloudSolrServer.shutdown();  
	}

}
