package com.rbt.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
public class indexesAction  {
	
	/*********************集合******************/
	public List showList;// 显示

	/*********************字段******************/
	private static String module_type = "supply";// 模块索引文件夹
	
	


	
	
	/**
	 * @author : LJQ
	 * @date : Jul 23, 2014 4:16:42 PM
	 * @Method Description : lucene搜索查询
	 */
	public String search() throws Exception{
	    String[] fields ={"supply_id","title"};//需要搜索字段的数组
	    searchIndex(fields,"184");//调用搜索索引方法
		return goUrl("INDEXLIST");
	}
	
	private String goUrl(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author : LJQ
	 * @date : Jul 20, 2014 2:19:27 PM
	 * @Method Description : 根据关键字搜索索引找出相应的数据
	 */
	public  void searchIndex(String[] fields,String keyword) throws Exception{
		    if(fields==null){
		    	return;
		    }
	        // 获取IndexSearcher
	        IndexReader reader = IndexReader.open(FSDirectory.open(new File("D:/index")));
	        IndexSearcher searcher = new IndexSearcher(reader);
	        //输出最大的索引记录数
	        System.out.println(searcher.maxDoc());
	        // 创建一个语法分析器
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);    
	        //搜索条件
	        QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_34, fields, analyzer);
	        Query query = parser.parse(keyword);
	        
	        System.out.println(query.toString()+"===============================");
	        TopDocs results = searcher.search(query, 100);
	        ScoreDoc[] hits = results.scoreDocs;
	        
			//定义一个列表
	        showList = new ArrayList();
			String supply_id = "", title = "";
			for(ScoreDoc scoreDoc:hits){
				Map listMap = new HashMap();
				// 取得该条索引文档
				Document  hit= searcher.doc(scoreDoc.doc);
				//DocBean db=new DocBean();
				supply_id = hit.get("supply_id");
				title = hit.get("title");
				//存入listMap中
				listMap.put("supply_id", supply_id);
				listMap.put("title",title);
				showList.add(listMap);
			}
			System.out.println("我的查询结果列表是:" + showList);
	    }
}
