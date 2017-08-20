/*
 
 * Package:com.rbt.common.codeBuild
 * FileName: CodeEngine.java
 */
package com.rbt.common.codeBuild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rbt.common.util.DbUtil;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.PropertiesUtil;

/**
 * 功能：生成本框架的基础代码，包括java类，xml文件，前台文件
 * date:2014-07-10
 * @author HXK
 *
 */
public class CodeEngine {
	
	//数据库表名

	private static String TABLENAME = "storeintro";

	
	//表主键

	private static final String TABLEKEY = "sto_id";

	
	//功能名称

	private static final String FUNNAME = "门店服务介绍";

	//功能名称
	private static final String AUTHOR = "HXK";
	
	//表名第一个字母大写
	private final String CLASSNAME;
	
	//主键第一个字母大写
	private final String TABLEKEYUPPER;
	
	//当前日期
	private final String DATE;
	//数据库名称
	private static final String DBNAME = "www_jiutong_com";
	//项目的根目录
	private static String rootpath = PropertiesUtil.getRootpath().replace("WebRoot/", "");
	
	//模板路径
	private static String templatePath = Constants.TEMPLATEPATH;
	
	//文件工具类
	private FileUtil fileUtil;
	
	//数据库工具类
	private DbUtil dbOperate;
	
	//替换内容
	private String fileContent;
	
	public CodeEngine(){
		CLASSNAME = oneWordUpper(TABLENAME);
		TABLEKEYUPPER = oneWordUpper(TABLEKEY);
		DATE = getDate();
		fileUtil = new FileUtil();
		dbOperate = new DbUtil();
	}
	
	/*
	 * 主入口方法
	 */
	public static void main(String args[]){
		
		CodeEngine codeEngine = new CodeEngine();
		String className=codeEngine.oneWordUpper(TABLENAME);
		//java类、ibatis sql文件自动生成
		codeEngine.createFile("actionClass.txt",className+"Action.java",Constants.ACTION_SAVEPATH);
		codeEngine.createFile("daoInterface.txt","I"+className+"Dao.java",Constants.DAO_SAVEPATH);
		codeEngine.createFile("daoImpl.txt",className+"Dao.java",Constants.DAOIMPL_SAVEPATH);
		codeEngine.createFile("serviceInterface.txt","I"+className+"Service.java",Constants.SERVICE_SAVEPATH);
		codeEngine.createFile("serviceImpl.txt",className+"Service.java",Constants.SERVICEIMPL_SAVEPATH);
		codeEngine.createFile("pojoClass.txt",className+".java",Constants.POJO_SAVEPATH);
		codeEngine.createFile("sqlmap.txt",TABLENAME+".xml",Constants.SQLMAP_SAVEPATH);			
		codeEngine.createSqlMapFile("sqlmapxml.txt",Constants.SQL_PATH,"sqlmap.xml",Constants.SQLMAP_SAVEPATH);
		codeEngine.createTableValidateXml("fieldValidate.xml",TABLENAME);	
//		//运营商后台页面
		codeEngine.createFoldFile(Constants.PAGE_PATH,"index.txt","index.ftl");	
		codeEngine.createFoldFile(Constants.PAGE_PATH,"insert.txt","insert.ftl");	
		codeEngine.createFoldFile(Constants.PAGE_PATH,"update.txt","update.ftl");	
		
	
		
		
	}
	
	
	/**
	 * @author Administrator 
	 * @date : Jan 27, 2014 5:07:02 PM
	 * @Method Description : 查询出数据库所有表
	 */
	public void showAllTable(){
		//生成数据库对应的底层实体
		//codeEngine.showAllTable();
		List tableList = dbOperate.queryList("SHOW tables");
		for(int i=0;i<tableList.size();i++){
			HashMap map = (HashMap)tableList.get(i);
			if(map.get("Tables_in_"+DBNAME)!=null){
				System.out.println(map.get("Tables_in_"+DBNAME));
				String tb = map.get("Tables_in_"+DBNAME).toString();
				TABLENAME=tb;
				CodeEngine codeEngine = new CodeEngine();
				String className=codeEngine.oneWordUpper(tb);
				codeEngine.createFile("pojoClass.txt",className+".java",Constants.POJO_SAVEPATH);
			}
		}
	}
	
	
	
	
	public void createFoldFile(String creathPath,String templateName,String fileName){
		//模板的详细地址
		String file_path = creathPath+CLASSNAME;
		//创建文件夹
		String path=fileUtil.createFolder(file_path);
		//模板的详细地址
		String template_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(template_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(file_path,fileName,fileCon);
		System.out.println(file_path+"/"+fileName+"文件生成成功");
	}
	
	
	
	public void createSqlMapFile(String templateName,String sqlMapFile,String fileName,String filePath){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileContent = replaceTemplate(fileCon);				
		String oldCon= fileUtil.readTxt(sqlMapFile);
		if(oldCon.indexOf(fileContent)==-1){
			oldCon = replaceTemplate(oldCon);
		}		
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(sqlMapFile,"", oldCon);
		System.out.println(sqlMapFile+"文件更新成功");
	}
	
	
	
	//生成所有表的验证规则
	public void createAllTableValidateXml(){
		
		DbUtil dbUtil = new DbUtil();
		String dbName = dbUtil.getDbName();
		List tList = dbUtil.queryList("SHOW TABLES;");
		StringBuffer sb = new StringBuffer();
		if(tList!=null && tList.size()>0){
			for(int j=0;j<tList.size();j++){
				HashMap tMap = (HashMap)tList.get(j);
				String tableName = "";
				if(tMap.get("Tables_in_"+dbName)!=null){
					tableName = tMap.get("Tables_in_"+dbName).toString();
				}
				sb.append( createTableValidateXml("fieldValidate.xml",tableName) );
			}
		}	
	}
	
	//生成单独表的验证规则
	public String createTableValidateXml(String fileName,String tableName){
		
		StringBuffer sb = new StringBuffer();
				
		ArrayList fieldList = dbOperate.getTableDescList(tableName);
		
		sb.append("<table id=\""+tableName+"\">\n");
		for(int i=0;i<fieldList.size();i++){
			HashMap map = (HashMap)fieldList.get(i);
			String Field = "",Type = "",Null = "",Key = "";
			if(map.get("Field")!=null) Field = map.get("Field").toString();
			if(map.get("Type")!=null) Type = map.get("Type").toString();
			if(map.get("Null")!=null) Null = map.get("Null").toString();
			if(map.get("Key")!=null) Key = map.get("Key").toString();
			
			String type = "",length = "";
			
			int pos = Type.indexOf("(");
			
			if(pos == -1){
				type = Type;
				length = "10000";
			}else{
				type = Type.substring(0,pos);
				length = Type.replace(type, "").replace("(", "").replace(")", "");
			}
			
			if(type.equals("mediumint")){
				type = "int";
			}
			
			if(type.equals("char") || type.equals("varchar") || type.equals("text")){
				type = "string";
			}
			
			sb.append("<field name=\""+Field+"\" type=\""+type+"\" length=\""+length+"\" required=\""+( Null.equals("NO")?"true":"false" )+"\" cnname=\""+Field+"\"  />\n");
			
		}
		sb.append("</table>\n");
		fileContent=sb.toString();
		//模板的详细地址
		String file_path = "src/"+fileName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码				
		String oldCon= fileUtil.readTxt(file_path);
		if(oldCon.indexOf(fileContent)==-1){
			oldCon = replaceTemplate(oldCon);
		}		
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(file_path,"", oldCon);
		System.out.println(file_path+"文件更新成功");		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	//
	public void addXmlCode(String templateName,String xmlName){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		
		//得到配置文件的绝对路径
		String xml_path = rootpath+Constants.SRC_PATH+xmlName;
		//得到配置文件的内容
		String xmlFileCon = fileUtil.readTxt(xml_path);
		//替换模板中的标签为具体代码
		xmlFileCon = xmlFileCon.replace(Constants.XML_TAGBODY,fileCon+Constants.XML_TAGBODY);
		
		fileUtil.writeTxt(rootpath+Constants.SRC_PATH,xmlName, xmlFileCon);
		System.out.println(xml_path+"文件更新成功");
	}
	
	
	/*
	 * templateName:模板名称
	 * fileName：生成后的文件名
	 * fileCon：文件内容
	 * filePath：生成后的文件存放地址
	 */
	public void createFile(String templateName,String fileName,String filePath){
		//模板的详细地址
		String file_path = rootpath+templatePath+templateName;
		//得到模板的内容
		String fileCon = fileUtil.readTxt(file_path);
		//替换模板中的标签为具体代码
		fileCon = replaceTemplate(fileCon);
		//创建文件 和 写入文件内容
		fileUtil.writeTxt(rootpath+filePath,fileName, fileCon);
		System.out.println(rootpath+filePath+"/"+fileName+"文件生成成功");
	}
	
	public String replaceTemplate(String fileCon){
		fileCon = fileCon.replace("{TABLENAME}", TABLENAME);
		fileCon = fileCon.replace("{CLASSNAME}", CLASSNAME);
		fileCon = fileCon.replace("{FUNNAME}", FUNNAME);
		fileCon = fileCon.replace("{AUTHOR}", AUTHOR);
		fileCon = fileCon.replace("{DATE}", DATE);
		fileCon = fileCon.replace("{TABLEKEYUPPER}", TABLEKEYUPPER);
		fileCon = fileCon.replace("{TABLEKEY}", TABLEKEY);
		fileCon = fileCon.replace("<!--tagbody-->", fileContent+"<!--tagbody-->");		
		
		while(fileCon.indexOf("fieldlist") > -1){
			fileCon = replaceLoopField(fileCon);
		}
		return fileCon;
	}
	
	//循环替换表字段
	public String replaceLoopField(String fileCon){
		String tagname = "fieldlist";
		String startTag = "{"+tagname+"}";
		String enTag = "{/"+tagname+"}";
		if(fileCon.indexOf(startTag) == -1) return fileCon;
		int i = fileCon.indexOf(startTag);
		int j = fileCon.indexOf(enTag)+enTag.length();
		//得到标签体
		String tagBody = fileCon.substring(i,j);
		String bodyCon = tagBody.replace(startTag, "").replace(enTag, "");
		
		//取出此表数据库描述
		ArrayList fieldList = dbOperate.getTableDescList(TABLENAME);
		
		StringBuffer sb = new StringBuffer();
		
		if(fieldList!=null && fieldList.size()>0){
			HashMap fieldMap = new HashMap();
			for(int k=0;k<fieldList.size();k++){
				fieldMap = (HashMap)fieldList.get(k);
				String field = "",Type="";
				if(fieldMap.get("field")!=null){
					field = fieldMap.get("field").toString();
				}
				if(fieldMap.get("Type")!=null) {
					Type = fieldMap.get("Type").toString();
				}
				System.out.println(Type);
				String type = "",length = "";
				int pos = Type.indexOf("(");
				if(pos == -1){
					type = Type;
					length = "25000";//类型长度
				}else{
					type = Type.substring(0,pos);
					length = Type.replace(type, "").replace("(", "").replace(")", "");//类型长度
				}
				if(field.equals("")) continue;
				String temp = bodyCon;
				//直接替换字段
				temp = temp.replace("[field_name]", field);
				//替换第一个字母大写的字段
				temp = temp.replace("[fieldname]", oneWordUpper(field));
				//替换SQLMap.txt
				temp = temp.replace("[field_node_u]", field);	
				if(k==fieldList.size()-1){
					temp = temp.replace("[field_node],", field);	
				}else{
					temp = temp.replace("[field_node],", field+",");	
				}			
				if(k==fieldList.size()-1){
					temp = temp.replace("#[field_u_node]#,", "#"+field+"#");	
				}else{
					temp = temp.replace("#[field_u_node]#,", "#"+field+"#,");	
				}		
				//根据类型控制字段的长度
				if(type.equals("mediumint")){
					temp = temp.replace("{checkNode}", "onkeyup="+"\"checkDigital(this);\"");	
				}else{					
					temp = temp.replace("{checkNode}", "onkeyup="+"\"checkLength(this,"+length+");\""+" maxlength=\""+length+"\"");	
				}
				
				//根据数据库不同的类型定义不同的实体类型
				if(type.indexOf("float")>-1){
					temp = temp.replace("[field_type]", "Double");
					
				}else if(type.indexOf("double")>-1){
					temp = temp.replace("[field_type]", "Double");
					
				}else if(type.indexOf("mediumint")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("int")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("datetime")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("varchar")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("text")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("char")>-1){
					temp = temp.replace("[field_type]", "String");
					
				}else if(type.indexOf("decimal")>-1){
					temp = temp.replace("[field_type]", "Double");
					
				}else{
					temp = temp.replace("[field_type]", "String");
				}
				
				sb.append(temp);
			}
		}
		fileCon = fileCon.replace(tagBody, sb.toString());
		
		return fileCon;
	}
	
	//让传进来的字符串第一个字母大写
	public String oneWordUpper(String str){
		if(str.length()==0) return "";
		String oneWord = str.substring(0,1);
		return oneWord.toUpperCase()+str.substring(1,str.length());
	}
	
	//得到当前的日期
	public String getDate(){
		return new java.util.Date().toString();
	}
	
}
