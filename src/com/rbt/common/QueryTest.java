package com.rbt.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spans.SpanFirstQuery;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanNotQuery;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class QueryTest extends TestCase{

static String sIndex_Path="E:/index";
static String sText_path="E:/textbook";
static protected String[] keywords = {"001","002","003","004","005"};
static protected String[] textdetail = {"记录 一","记录 二","记录 三","一 2345 记录","记录 新 一"};
static File fIndex_Path=new File(sIndex_Path);
/**===========================================================
* 名称：IndexBuilder
* 功能：构造磁盘索引，添加内容到指定目录，为后继检索查询做好准备
=============================================================**/
public static void IndexBuilder(){
   try{
    Date start = new Date();
    File f=new File(sText_path);
    File[] list=f.listFiles(); 
    File file2 = new File(sIndex_Path);
    //创建磁盘索引目录
    Directory dir = FSDirectory.open(file2);
    Directory ramdir = new RAMDirectory();
    Analyzer TextAnalyzer = new SimpleAnalyzer();
    //创建磁盘索引
    IndexWriter TextIndex = new IndexWriter(dir, TextAnalyzer, true, IndexWriter.MaxFieldLength.LIMITED);
    //创建内存索引
    IndexWriter RAMTextIndex = new IndexWriter(ramdir,TextAnalyzer,true, IndexWriter.MaxFieldLength.LIMITED);
    for(int i=0;i<list.length;i++){
     Document document = new Document();
     Field field_name = new Field("name", list[1].getName(),
       Field.Store.YES, Field.Index.NOT_ANALYZED);
     document.add(field_name);
     FileInputStream inputfile = new FileInputStream(list[i]);
     int len = inputfile.available();
     byte[] buffer = new byte[len];
     inputfile.read(buffer);
     inputfile.close();

     String contenttext = new String(buffer);
     Field field_content = new Field("content", contenttext,
       Field.Store.YES, Field.Index.ANALYZED);
     document.add(field_content);
    
     Field field_size = new Field("size",String.valueOf(len),Field.Store.YES,Field.Index.NOT_ANALYZED);
     document.add(field_size);
     TextIndex.addDocument(document);
     TextIndex.optimize();
    }
      //关闭磁盘索引
      TextIndex.close();
      Date end = new Date();
      long tm_index = end.getTime()-start.getTime();
      System.out.print("Total Time:(ms)");
      System.out.println(tm_index);
   }catch(IOException e){
    e.printStackTrace();
   }
   System.out.println("index Sccess");
}
/**===================================================================
*名称：LuceneTermQuery
*功能：构造检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
===================================================================**/
public static void LuceneTermQuery(String word){
   try{
    Directory Index_Dir=FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term t = new Term("id", "002");
    TermQuery query = new TermQuery(t);
    System.out.print(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   }catch(IOException e){
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**===================================================================
*名称：LuceneRangeQuery
*功能：构造范围检索查询器，对指定的索引进行查询，找到指定的文档，并输
===================================================================**/
public static void LuceneRangeQuery(String lowerTerm, String upperTerm){
   try{
    Directory Index_Dir=FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    TermRangeQuery query = new TermRangeQuery("numval",lowerTerm,upperTerm,true,true);
    System.out.print(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   }catch(IOException e){
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
*名称：LuceneBooleanQuery
*功能：构造布尔检索查询器，对指定的索引进行查询，找到指定的值，并输出相应的结果
=========================================================================**/
public static void LuceneBooleanQuery(){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term1 = new Term("info_state","1");
    Term term2 = new Term("goods_name","门");
    Term term3 = new Term("is_del","1");
    Term term4 = new Term("is_up","0");
    TermQuery query1 = new TermQuery(term1);
    TermQuery query2 = new TermQuery(term2);
    TermQuery query3 = new TermQuery(term3);
    TermQuery query4 = new TermQuery(term4);
    BooleanQuery query = new BooleanQuery();
    query.add(query1,BooleanClause.Occur.MUST);
    query.add(query2,BooleanClause.Occur.MUST);
    query.add(query3,BooleanClause.Occur.MUST);
    query.add(query4,BooleanClause.Occur.MUST);
    System.out.println(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
    	Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("goods_name"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
public static void main(String[] args) {
	QueryTest.LuceneBooleanQuery();
//	QueryTest.LucenePrefixQuery("门");
//	QueryTest.LucenePhraseQuery("门","门");
//	QueryTest.LuceneSpanFirstQuery("门");
}
/**=========================================================================
* 名称：LucenePrefixQuery
* 功能：构造前缀检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LucenePrefixQuery(String word){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term = new Term("goods_name",word);
    PrefixQuery query = new PrefixQuery(term);
    System.out.println(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LucenePhraseQuery
* 功能：构造短语检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LucenePhraseQuery(String word1, String word2){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term1 = new Term("goods_name",word1);
    PhraseQuery query = new PhraseQuery();
    query.add(term1);
    System.out.println(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LuceneFuzzyQuery
* 功能：构造模糊检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneFuzzyQuery(String word){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term = new Term("content",word);
    FuzzyQuery query = new FuzzyQuery(term);
    System.out.println(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LuceneWildcardQuery
* 功能：构造通配符检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneWildcardQuery(String word){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term = new Term("content",word);
    WildcardQuery query = new WildcardQuery(term);
    System.out.println(query.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}

/**=========================================================================
* 名称：LuceneSpanFirstQuery
* 功能：构造SpanQuery检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneSpanFirstQuery(String word){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term = new Term("goods_name",word);
    SpanTermQuery query = new SpanTermQuery(term);
    SpanFirstQuery firstquery = new SpanFirstQuery(query,2);
    System.out.println(firstquery.toString());
    ScoreDoc[] hits = searcher.search(query, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LuceneSpanNearQuery
* 功能：构造SpanQuery检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneSpanNearQuery(String word1,String word2,String word3){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term1 = new Term("content",word1);
    Term term2 = new Term("content",word2);
    Term term3 = new Term("content",word3);
    SpanTermQuery query1 = new SpanTermQuery(term1);
    SpanTermQuery query2 = new SpanTermQuery(term2);
    SpanTermQuery query3 = new SpanTermQuery(term3);
    SpanQuery[] queryarray = new SpanQuery[]{query1,query2,query3};
    SpanNearQuery nearquery = new SpanNearQuery(queryarray,1,true);
    System.out.println(nearquery.toString());
    ScoreDoc[] hits = searcher.search(nearquery, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LuceneSpanNotQuery
* 功能：构造SpanQuery检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneSpanNotQuery(String word1,String word2,String word3){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term1 = new Term("content",word1);
    Term term2 = new Term("content",word2);
    Term term3 = new Term("content",word3);
    SpanTermQuery query1 = new SpanTermQuery(term1);
    SpanTermQuery query2 = new SpanTermQuery(term2);
    SpanTermQuery query3 = new SpanTermQuery(term3);
    SpanQuery[] queryarray = new SpanQuery[]{query1,query2};
    SpanNearQuery nearquery = new SpanNearQuery(queryarray,1,true);
    SpanNotQuery notquery = new SpanNotQuery(nearquery,query3);
    System.out.println(notquery.toString());
    ScoreDoc[] hits = searcher.search(notquery, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}
/**=========================================================================
* 名称：LuceneSpanOrQuery
* 功能：构造SpanQuery检索查询器，对指定的目录进行查询，找到指定的值，并输出相应结果
==========================================================================*/
public static void LuceneSpanOrQuery(String word1,String word2,String word3){
   try {
    Directory Index_Dir = FSDirectory.open(fIndex_Path);
    IndexSearcher searcher = new IndexSearcher(Index_Dir);
    Term term1 = new Term("content",word1);
    Term term2 = new Term("content",word2);
    Term term3 = new Term("content",word3);
    SpanTermQuery query1 = new SpanTermQuery(term1);
    SpanTermQuery query2 = new SpanTermQuery(term2);
    SpanTermQuery query3 = new SpanTermQuery(term3);
    SpanQuery[] queryarray1 = new SpanQuery[]{query1,query2};
    SpanQuery[] queryarray2 = new SpanQuery[]{query2,query3};
    SpanNearQuery nearquery1 = new SpanNearQuery(queryarray1,1,true);
    SpanNearQuery nearquery2 = new SpanNearQuery(queryarray2,1,true);
    SpanOrQuery orquery = new SpanOrQuery(new SpanNearQuery[]{nearquery1,nearquery2});
    System.out.println(orquery.toString());
    ScoreDoc[] hits = searcher.search(orquery, null, 1000).scoreDocs;
    System.out.println("Search result:");
    for (int i = 0; i < hits.length; i++) {
     Document hitDoc = searcher.doc(hits[i].doc);
        System.out.println(hitDoc.get("fieldname"));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println("Search Success");
}

}