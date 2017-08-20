package com.rbt.index;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.rbt.createHtml.InfoVo;

public class LuceneUtil {

	// 创建一个语法分析器
	private static Analyzer analyzer = getAnalyzer(); 
	
	//定义全局变量
	private String Type;
	
	public void createIndex(List list,String filePath,String index_type) throws IOException{
		// 定义存放索引的位置
		Directory indexDictory = FSDirectory.open(new File(LuceneUtil.getBasePath()+"/"+filePath));
		// 使用分词器的配置
		IndexWriterConfig writerconfig   = new IndexWriterConfig(Constants.VERSION, analyzer);  
		Type=index_type;
		if(index_type.equals("4")){
			writerconfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE); //新增状态
		}else{
			writerconfig.setOpenMode(IndexWriterConfig.OpenMode.APPEND); //更新状态
		}
		// 实例化磁盘写入类
		IndexWriter writer = new IndexWriter(indexDictory,writerconfig);  
		// 生成索引
		indexDocs(writer,list);
		//自动合并索引文件
		writer.optimize();
		// 关闭写入流
		writer.close();
	}
	
	
	private void indexDocs(IndexWriter writer,List list) throws IOException{
		if(list== null || list.size()==0){
		   return;
		}
		//循环建立索引
		Map listMap=new HashMap();
		for(int i=0;i<list.size();i++){
			listMap = (HashMap) list.get(i);
			System.out.println(listMap.toString());
			addDoc(writer,listMap);
		}
	}
	
	public void addDoc(IndexWriter writer,Map map) throws CorruptIndexException, IOException{
		if(map == null) return;
		Iterator iter = map.entrySet().iterator();
		//建立索引文档
		Document doc = new Document();
		String index_id="",index_value="";
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = "",value = "";
			//寻找标识主键
			if(entry.getKey().equals("index_id")){
				index_value=entry.getValue().toString();
			}
			if(entry.getKey() != null){
				key = entry.getKey().toString();
			}
			if(entry.getValue() != null){
				value = entry.getValue().toString();
			}
			//特殊字段的处理
			SpecialDeal sd=new SpecialDeal();
			value=sd.getSpecialValue(key, value);
			//加入文档中
			doc.add(new Field(key,value,Field.Store.YES,Field.Index.ANALYZED));  
		}
		if(Type.equals("4")){
			writer.addDocument(doc);//全量新增
		}else if(Type.equals("1")){
			writer.addDocument(doc);//增量新增
		}else if(Type.equals("2")){
			writer.updateDocument(new Term("index_id",index_value), doc);//增量更新
		}else if(Type.equals("3")){
			writer.deleteDocuments(new Term("index_id",index_value));//增量删除
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 20, 2014 9:12:20 AM
	 * @Method Description : 找出需要索引数据的列表
	 */
	public List getSourceList(String sql){
		InfoVo iv=new InfoVo();
		List infoList = iv.getInfoList(sql, null);
		return infoList;
	}
	
	/**
	 * @author : LJQ
	 * @date : Aug 28, 2014 2:21:51 PM
	 * @Method Description : 删除记录
	 */
	public void deleteInfo(String sql){
		InfoVo iv=new InfoVo();
		iv.excuteSql(sql);
	}
	
	
	public static String getBasePath(){
		return Constants.BASEPATH;
	}
	
	public static Analyzer getAnalyzer(){
		if(analyzer == null){
			analyzer = new StandardAnalyzer(Constants.VERSION);
		    //analyzer=new IKAnalyzer();
			//analyzer=new PaodingAnalyzer();
		}
		return analyzer;
	}
	
}
