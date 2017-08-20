/*
 
 * Package:com.rbt.createHtml
 * FileName: ParseHtml.java
 */
package com.rbt.createHtml;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rbt.common.Constants;
import com.rbt.common.util.PropertiesUtil;

import com.rbt.common.util.FileUtil;
import com.rbt.function.SeosetFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Seoset;

import java.io.File;

/**
 * @function 功能 标签替换类
 * @author  创建人 HXK
 * @date  创建日期  2014-08-12
*/

public class ParseHtml {

	InfoVo infoVo;
	SpecialField sField;
	FileUtil fileUtil;
	HashMap tagMap;
	HashMap articleTagMap;
	static final String ROOT_PATH = PropertiesUtil.getRootpath();
	
	private String fieldPreStr = "[field:";
	
	//前台模板存放的位置
	private String templateSavePath =Constants.TEMPLATE_PORTAL_FOLDER;
	//PC端网站模板的名称，可在系统后台设置
	private String templateFolder =SysconfigFuc.getSysValue("cfg_templatefolder");
	//触屏版网站模板
	private String templateWebAppFolder = SysconfigFuc.getSysValue("cfg_webapptemplatefolder");
	//PC端网站模板的完全路径
	private String templatePath = templateSavePath + templateFolder + File.separator;
	//触屏版网站模板
	private String templateWebAppPath = templateSavePath + templateWebAppFolder + File.separator;
	
	//前台模板生成静态页面保存路径
	private String templateSaveRoute =SysconfigFuc.getSysValue("cfg_templateroute");
	
	public ParseHtml(){
		infoVo = new InfoVo();
		sField = new SpecialField();
		fileUtil = new FileUtil();
		//取出tagconfig.xml里的所有标签配置信息
		tagMap = ParseXml.getTagMap();
		//取出tagconfig.xml里类型等于articleList的所有标签配置信息
		articleTagMap = ParseXml.getArticleTagMap();
	}
	
	// 标签名
	static String cmsName = "tag";

	public static void main(String[] args) {
		
		HashMap fieldMap = new HashMap();
		
		String tagCon = "[field:desc?len(2,322)?format(ch)/]666666[field:num/]6666666[field:numd?len(5)/]";
		
		Pattern p = Pattern.compile("\\[field:[a-zA-Z0-9_]+(\\?len\\([0-9]+(,[0-9]+)?\\))?(\\?format\\([a-z]+\\))?[/]]");
		Matcher m = p.matcher(tagCon);
		while(m.find()){
			String filedBody = m.group();
			String fieldName = filedBody.replace("[field:", "");
			fieldName = fieldName.replace("/]", "");
			Map attrMap = new HashMap();
			if(fieldName.indexOf("?") > -1){
				int j = fieldName.indexOf("?");
				String attrStr = fieldName.substring(j,fieldName.length());
				String fattr[] = attrStr.split("\\?");
				for(int i=0;i<fattr.length;i++){
					if(!fattr[i].equals("")){
						String attrone = fattr[i];
						String key = attrone.substring(0,attrone.indexOf("("));
						String val = attrone.substring(attrone.indexOf("(")+1,attrone.indexOf(")"));
						attrMap.put(key, val);
					}
				}
				fieldName = fieldName.substring(0,j);
			}
			fieldMap.put(fieldName, attrMap);
		}
		
		System.out.print(fieldMap);
		
	}
	
	/*
	 * 根据管理员后台更新详细页菜单
	 * 参数为栏目对象，栏目对象包括模块类型，详细页模板，详细页生成文件规则，详细页保存位置
	 * 执行流程如下：
	 * 1，根据详细页模板读取模板文件内容，根据模块类型去tagMap（解析tagconfig.xml所得）中找到对应的sql语句
	 * 2，根据sql语句由数据库找出对应的数据infoList
	 * 3，循环infoList生成详细页文件，读取模板中的详细页标签{tag:field name="字段名" /}
	 * 4，把标签中的字段名和infoList中的infoMap>字段匹配，如果匹配上，替换整个标签
	 * 5，如果匹配不上，在去SpecialField中的getArticleVlaue找是否有对应的自定义字段解析，如果没有，直接替换成“字段域未获取到值”
	 * 6，详细页得域字段标签替换之后，在替换详细列表标签（tagconfig.xml中type为articleList的）
	 * 7，保存文件，根据详细页生成文件规则和详细页保存位置
	 */
	@SuppressWarnings("unchecked")
	public  void doArticleHtml(Map outMap){
		//module_type:所属模块，通过这个找出对应的sql
		//article_temp：详细页模板路径
		//article_rule：详细页Url规则
		//save_dir:详细保存的地址
		String module_type = "",article_temp="",article_rule="",save_dir="";
		if(outMap.get("module_type")!=null){
			module_type = outMap.get("module_type").toString();
		}
		if(outMap.get("article_temp")!=null){
			article_temp = outMap.get("article_temp").toString();
		}
		if(outMap.get("article_rule")!=null){
			article_rule = outMap.get("article_rule").toString();
		}
		if(outMap.get("save_dir")!=null){
			save_dir =templateSaveRoute+File.separator+outMap.get("save_dir").toString();
		}
		//都不为空才执行解析
		if(!module_type.equals("") && !article_temp.equals("") && !article_rule.equals("") && !save_dir.equals("")){
			
			article_temp = ROOT_PATH + templatePath + article_temp;
			save_dir = ROOT_PATH + save_dir;
			
			//读取模板内容
			String inStr = fileUtil.readTxt(article_temp);
			
			//更新全局标签替换
			//outMap：当前栏目信息
			inStr = createHtml(inStr,outMap);
			
			String tagSql = "";
			//根据模块名称从tagconfig.xml文件里找出对应的sql语句
			
			String model_key = module_type+"-model";
			
			if(tagMap!=null && tagMap.get(model_key)!=null) tagSql = tagMap.get(model_key).toString();
			
			if(tagSql.equals("")){
				System.out.println("["+module_type+"]此模块在tagcongfig.xml中未找到对应的sql语句");
				return;
			}
			
			//如果outMap中有info_id,即更新单条信息详细页面
			tagSql = getReplaceSqlByMap(tagSql,outMap);
			System.out.println("==================================================article sql==================================================");
			System.out.println(tagSql);
			
			//获取信息总数
			int infoCt = getInfoCount(tagSql);
			
			if(infoCt == 0){
				System.out.println("详细信息数量为0");
				return;
			}
			
			if(infoCt == 1){
				//只更新一条信息，在发布和修改信息时更新
				parseArticleTag(tagSql,outMap,inStr,article_rule,save_dir);
			}else{
				//一次更新信息的条数
				int infoPage = 100;
				//分布更新的次数
				int totalPage = infoCt / infoPage;
				if(totalPage == 0){
					totalPage = 1;
				}
				for(int i=0;i<totalPage;i++){
					outMap.put("start", i*100);
					outMap.put("row", infoPage);
					parseArticleTag(tagSql,outMap,inStr,article_rule,save_dir);
				}
			}
			
			try {
	            Thread.sleep(300);
	        } catch (InterruptedException e) {
	            // 执行到这里的机率非常低
	        }	
			
		}
	}
	
	
	public int getInfoCount(String tagSql){
		int fromIndex = tagSql.toLowerCase().lastIndexOf("from");
		String ctTagSql = "SELECT COUNT(*) ct "+tagSql.substring(fromIndex,tagSql.length());
		
		HashMap ctMap = infoVo.getInfoMap(ctTagSql);
		int infoCt = 0;
		if(ctMap.get("ct")!=null && !ctMap.get("ct").toString().equals("")){
			infoCt = Integer.parseInt(ctMap.get("ct").toString());
		}
		return infoCt;
	}
	
	
	public void parseArticleTag(String tagSql,Map outMap,String inStr,String article_rule,String save_dir){
		//根据sql找出信息list
		List infoList = infoVo.getInfoList(tagSql, outMap);
		//开始循环生成详细页
		if(infoList!=null && infoList.size()>0){
			HashMap infoMap = new HashMap();

			for(int i=0;i<infoList.size();i++){
				
				infoMap = (HashMap)infoList.get(i);
				
				//把栏目里面的信息并到信息Map来
				//供标签使用
				if(outMap.get("ch_id")!=null){
					infoMap.put("ch_id",outMap.get("ch_id").toString());
				}
				
				//============================获取通用字段开始============================
				//为文件保存做准备
				//info_id：信息主键
				//in_date：信息发布日期
				String info_id="",in_date="";
				if(infoMap.get("info_id")!=null){
					info_id = infoMap.get("info_id").toString();
				}
				if(infoMap.get("in_date")!=null){
					in_date = infoMap.get("in_date").toString();
				}
				//============================获取通用字段结束============================
				
				//临时变量保存文件内容
				String tempStr = inStr;
				
				//====================================================解析详细页字段域标签开始====================================================
				String tagName = "field";
				while (checkTag(cmsName, tagName, tempStr)) {
					//获取标签体
					String tagBody = getNoonLoopTagName(cmsName,tagName,tempStr);
					//获取标签体参数
					HashMap paraMap = getParaMap(tagBody,tagName); 
					String name = "",len="",htm="";
					int limit_len = 0;
					//获取字段域名称
					if(paraMap.get("name")!=null){
						name = paraMap.get("name").toString();
					}
					//获取字段域长度
					if(paraMap.get("len")!=null){
						len = paraMap.get("len").toString();
					}
					//获取是否格式化网页内容
					if(paraMap.get("htm")!=null){
						htm = paraMap.get("htm").toString();
					}
					//判断是否是数字，以防转化异常
					if(len.matches("[0-9]+")){
						limit_len = Integer.parseInt(len);
					}
					//替换标签体
					String fieldValue = "";
					
					if(infoMap.get(name)!=null && !infoMap.get(name).toString().equals("")){
						fieldValue = infoMap.get(name).toString();
					}else{
						String sfValue = sField.getArticleVlaue(name, infoMap);
						if(!sfValue.equals("")){
							fieldValue = sfValue;
						}
					}
					
					//是否去掉html标签
					if(htm.equals("false")){
						fieldValue = ToolsFuc.getChinese(fieldValue);
					}
					
					//控制详细页字段长度
					if(!fieldValue.equals("") && limit_len!=0 && fieldValue.length() > limit_len){
						fieldValue = fieldValue.substring(0,limit_len).trim();
					}
					
					//if(fieldValue.equals("")){
					//	fieldValue = "字段域未获取到值";
					//}
					tempStr = tempStr.replace(tagBody, fieldValue);
				}
				//====================================================解析详细页字段域标签结束====================================================
				
				//更新详细页列表标签
				//outMap：当前栏目信息
				//当前的Map换成详细列表标签的Map
				tagMap = articleTagMap;
				tempStr = createHtml(tempStr,outMap);
				
				//解析信息的发布日期
				String s_year="",s_month="",s_day="";
				if(!in_date.equals("")){
					if(in_date.length() > 4){
						s_year = in_date.substring(0,4);
					}
					if(in_date.length() > 5){
						s_month = in_date.substring(5,7);
					}
					if(in_date.length() > 8){
						s_day = in_date.substring(8,10);
					}
				}
				
				String article_rule_dir = article_rule.replace("{typedir}", save_dir).replace("{Y}", s_year).replace("{M}", s_month).replace("{D}", s_day).replace("{aid}", info_id);
				
				//从文件保存规则里得到文件名
				//得到最后一个/的位置
				int symbol_pos = article_rule_dir.lastIndexOf("/");
				//得到文件名
				String file_name = article_rule_dir.substring(symbol_pos+1,article_rule_dir.length());
				//得到目录地址
				String date_dir = article_rule_dir.substring(0,symbol_pos+1);
				//写入文件
				fileUtil.writeTxt(date_dir, file_name.trim(), tempStr);
				System.out.println(date_dir+file_name+" 生成成功");
				
			}
		}
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Jun 14, 2014 2:25:39 PM
	 * @Method Description : 删除模块的信息时同时也要删除
	 */
	public void deleteModelArticeHtml(Map outMap){
		String module_type = "",article_rule="",save_dir="",in_date="",info_id="";
		if(outMap.get("module_type")!=null){
			module_type = outMap.get("module_type").toString();
		}
		if(outMap.get("article_rule")!=null){
			article_rule = outMap.get("article_rule").toString();
		}
		if(outMap.get("save_dir")!=null){
			save_dir =templateSaveRoute+File.separator+outMap.get("save_dir").toString();
		}
		if(outMap.get("in_date")!=null){
			in_date = outMap.get("in_date").toString();
		}
		if(outMap.get("info_id")!=null){
			info_id = outMap.get("info_id").toString();
		}
		save_dir = ROOT_PATH + save_dir;
		//解析信息的发布日期
		String s_year="",s_month="",s_day="";
		if(!in_date.equals("")){
			if(in_date.length() > 4){
				s_year = in_date.substring(0,4);
			}
			if(in_date.length() > 5){
				s_month = in_date.substring(5,7);
			}
			if(in_date.length() > 8){
				s_day = in_date.substring(8,10);
			}
		}
		String article_rule_dir = article_rule.replace("{typedir}", save_dir).replace("{Y}", s_year).replace("{M}", s_month).replace("{D}", s_day).replace("{aid}", info_id);
		article_rule_dir=article_rule_dir.trim();
		fileUtil.delFile(article_rule_dir);
	}
	
	
	//temp_path：模板文件路径
	//file_name：生成后的文件名
	//save_path：生成后的文件保存路径
	//outMap：外部传来的参数,譬如栏目信息
	@SuppressWarnings("unchecked")
	public  String doIndexHtml(Map outMap){
		
		String temp_path="",file_name="",save_dir="",is_pc_webapp="";
		if(outMap.get("temp_path")!=null) temp_path = outMap.get("temp_path").toString();
		if(outMap.get("file_name")!=null) file_name = outMap.get("file_name").toString();
		if(outMap.get("save_dir")!=null)
		{
			if(!outMap.get("save_dir").equals("/"))//更新首页保存的路劲，因为首页保存在项目的根目录地下
			{
				save_dir =templateSaveRoute+File.separator+outMap.get("save_dir").toString()+File.separator;
			}
		}
		
		if(outMap.get("is_webapp")!=null)is_pc_webapp = outMap.get("is_webapp").toString();
		
		if(temp_path.equals("")){
			return "";
		}
		if(is_pc_webapp.equals("0")){//PC端
			temp_path = ROOT_PATH + templatePath + temp_path;
		}else if(is_pc_webapp.equals("1")){//触屏版
			temp_path = ROOT_PATH + templateWebAppPath + temp_path;
		}
		
		save_dir = ROOT_PATH + save_dir;
		
		//读取模板内容
		String inStr = fileUtil.readTxt(temp_path);
		
		//替换标签
		inStr = createHtml(inStr,outMap);
		
		//写入文件
		fileUtil.writeTxt(save_dir, file_name, inStr);
		System.out.println(save_dir+file_name+"生成成功");
		
		return "";
	}
	
	/*
	 * 根据管理员后台更新栏目页菜单
	 * 参数为模板字符和栏目信息
	 * 执行流程如下：
	 * 1，先解析固定的四个标签，文件包含标签（include）、SEO字段处理标签（dealfield）、栏目信息标签（chinfo）、系统设置标签（sysbase）
	 * 2，循环tagMap（解析tagconfig.xml所得），查看当前模板文件里是否有tagMap里的标签，如果存在，开始解析
	 * 3，获取整个循环标签体
	 * 4，获取标签体里的参数，放入一个paraMap中
	 * 5，根据标签名从tagMap中获取对应的sql语句
	 * 6，在用paraMap中的参数替换sql语句中的参数域
	 * 7，用替换后的sql找出数据库数据infoList，循环infoList替换循环体的标签
	 * 8，把循环体里的字段域名称与infoList里的Map域匹配，匹配上则替换
	 * 9，如果未匹配上，在去SpecialField中的getListVlaue找是否有对应的自定义字段解析，如果没有，不替换，直接以标签输出（嵌套标签原理在此）
	 */
	@SuppressWarnings("unchecked")
	public  String createHtml(String inStr,Map outMap){
		
		String tagName = "";
		
		//====================================================解析包含文件标签开始====================================================
		tagName = "include";
		while (checkTag(cmsName, tagName, inStr)) {
			//获取标签体
			String tagBody = getNoonLoopTagName(cmsName,tagName,inStr);
			//获取标签体参数
			HashMap paraMap = getParaMap(tagBody,tagName); 
			String file = "";
			//被包含文件目录地址
			if(paraMap.get("file")!=null){
				file = paraMap.get("file").toString();
			}
			if(!file.equals("")){
				//读取文件内容
				if(file.indexOf("/") == -1){
					file = templatePath + file;
				}
				String fileCon = fileUtil.readTxt(ROOT_PATH + file);
				inStr = inStr.replace(tagBody, fileCon);
			}else{
				break;
			}
		}
		//====================================================解析包含文件标签结束====================================================
		
		
		//====================================================SEO字段处理标签====================================================
		tagName = "dealfield";
		while (checkTag(cmsName, tagName, inStr)) {
			//获取标签体
			String tagBody = getNoonLoopTagName(cmsName,tagName,inStr);
			//获取标签体参数
			HashMap paraMap = getParaMap(tagBody,tagName); 
			String name = "",fieldValue = "";
			//被包含文件目录地址
			if(paraMap.get("name")!=null){
				name = paraMap.get("name").toString();
			}
			//替换标签体
			Seoset sset = SeosetFuc.getSeosetModel("index",null);
			if(name.equals("webtitle")){
				fieldValue = sset.getSeo_title();
			}
			if(name.equals("keywords")){
				fieldValue = sset.getSeo_keyword();
			}
			if(name.equals("description")){
				fieldValue = sset.getSeo_decri();
			}
			inStr = inStr.replace(tagBody, fieldValue);
		}
		//====================================================解析包含文件标签结束====================================================
		

		//====================================================解析栏目信息标签开始====================================================
		tagName = "chinfo";
		while (checkTag(cmsName, tagName, inStr)) {
			//获取标签体
			String tagBody = getNoonLoopTagName(cmsName,tagName,inStr);
			//获取标签体参数
			HashMap paraMap = getParaMap(tagBody,tagName); 
			String name = "";
			//被包含文件目录地址
			if(paraMap.get("name")!=null){
				name = paraMap.get("name").toString();
			}
			//替换标签体
			String fieldValue = "";
			if(outMap.get(name)!=null){
				fieldValue = outMap.get(name).toString();
			}
			inStr = inStr.replace(tagBody, fieldValue);
		}
		//====================================================解析栏目信息标签结束====================================================
		
		
		//====================================================解析系统设置标签开始====================================================
		//关联sysconfig表
		tagName = "sysbase";
		
		while (checkTag(cmsName, tagName, inStr)) {
			//获取标签体
			String tagBody = getNoonLoopTagName(cmsName,tagName,inStr);
			//获取标签体参数
			HashMap paraMap = getParaMap(tagBody,tagName); 
			
			//得到标签name属性值,即系统参数变量名
			String var_name = "";
			if(paraMap.get("name")!=null){
				var_name = paraMap.get("name").toString();
			}
			if(var_name.equals("")){
				//标签里为找到变量名
				break;
			}
			Map paramMap = new HashMap();
			paramMap.put("var_name", var_name);
			
			String sysbaseSql = "";
			//根据标签名从tagconfig.xml文件里找出对应的sql语句
			if(tagMap!=null && tagMap.get(tagName)!=null) sysbaseSql = tagMap.get(tagName).toString();
			
			if(sysbaseSql.equals("")){
				//tagconfig.xml文件里未找到sql语句
				break;
			}
			
			sysbaseSql = getReplaceSqlByMap(sysbaseSql,paramMap);
			//根据sql找出变量对应的值
			HashMap sysbaseMap = infoVo.getInfoMap(sysbaseSql);
			String fieldValue = "";
			if(sysbaseMap!=null && sysbaseMap.get("var_value")!=null){
				fieldValue = sysbaseMap.get("var_value").toString();
			}
//			if(fieldValue.equals("")){
//				//变量不存在于数据库，未找到对应的值
//				break;
//			}
			inStr = inStr.replace(tagBody, fieldValue);
		}
		//====================================================解析系统设置标签结束====================================================
		
		
		if(tagMap!=null){
			//遍历标签配置信息，解析文档
			for (Iterator iter = tagMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				//标签名
				tagName = entry.getKey().toString();
				//标签名所对应的sql语句
				String tagSql = entry.getValue().toString();
				
				//循环判断当前的标签是否存在于当前的模板里
				//如果存在开始循环解析
				while (checkTag(cmsName, tagName, inStr)) {
					String start = "{"+cmsName+":"+tagName;
					String end = "{/"+cmsName+":"+tagName+"}";
					
					int i = inStr.indexOf(start);
					int j = inStr.indexOf(end)+end.length();
					
					
					if(i==-1 || i >= j){
						//标签规则出问题了
						break;
					}
					
					//取出当前标签的标签体
					String tagBody = inStr.substring(i,j);
					
					//System.out.println(tagBody);
					
					int k = tagBody.indexOf("}")+1;
					
					//取出此标签的开始标签部分，根据这个取出对应的参数
					if(k <= 0){
						//标签规则出问题了
						break;
					}
					String startPos = tagBody.substring(0, k);
					
					//根据标签的开始部分取出对应的参数，放进HashMap
					HashMap paraMap = getParaMap(startPos,tagName);
					
					//取出需要循环生成的标签内容
					String tagCon = tagBody.replace(startPos, "").replace(end, "").trim();
					
					HashMap fieldMap = getfieldMap(tagCon);
					
					//根据标签体里面的参数替换sql里面的配置的值
					//如果paraMap里的值匹配不上的话，不替换sql语句里的标签
					String tempSql = getReplaceSqlByMap(tagSql,paraMap,false);
					//如果paraMap里的值匹配不上的话，替换sql语句里的标签，直接替换为空
					tempSql = getReplaceSqlByMap(tagSql,paraMap);
					
					//System.out.println("sql======================================"+tempSql);
					
					//根据替换后的sql取数据找数据
					List infoList = infoVo.getInfoList(tempSql, paraMap);
					
					StringBuffer newCon = new StringBuffer();
					
					//替换标签体内容的具体值
					if(infoList!=null && infoList.size()>0){
						HashMap infoMap = new HashMap();
						for(int c=0;c<infoList.size();c++){
							infoMap = (HashMap)infoList.get(c);
							//把参数Map都放到infoMap一起去
							infoMap.putAll(paraMap);
							infoMap.put("tagName", tagName);
							String tempCon = tagCon;
							for (Iterator iters = fieldMap.entrySet().iterator(); iters.hasNext();) {
								Map.Entry entrys = (Map.Entry) iters.next();
								
								//字段名
								String fkey = "";
								//字段体
								String fieldBody="";
								
								//获取字段名称
								if(entrys.getKey()!=null){
									fieldBody = entrys.getKey().toString();
									fkey = fieldBody.replace(fieldPreStr, "");
									fkey = fkey.replace("/]", "");
									int wenhao = fkey.indexOf("?");
									if(wenhao > -1){
										fkey = fkey.substring(0,wenhao);
									}
								}
								
								//len_limt：限制循环体类字段的长度
								String len_limit = "";
								int lenstart = 0,lenend = 0;
								
								//formatStr：格式化属性值
								String formatStr = "";
								
								//获取字段属性
								if(entrys.getValue() != null){
									Map attrMap = (Map)entrys.getValue();
									
									//获取字段长度控制属性
									if(attrMap!=null && attrMap.get("len")!=null){
										len_limit = attrMap.get("len").toString();
										if(len_limit.indexOf(",") > -1){
											String lenStr[] = len_limit.split(",");
											if(lenStr.length > 1){
												if(lenStr[0].matches("[0-9]+"))	lenstart = Integer.parseInt(lenStr[0]);
												if(lenStr[1].matches("[0-9]+"))	lenend = Integer.parseInt(lenStr[1]);
											}
										}else{
											lenend = Integer.parseInt(len_limit);
										}
									}
									
									//获取字段格式化属性
									if(attrMap!=null && attrMap.get("format")!=null){
										formatStr = attrMap.get("format").toString();
									}
									
								}
								
								String sfValue = sField.getListVlaue(fkey, infoMap);
								
								String finalValue = "";
								
								//从数据库的Map里找出对应的字段属性值
								if (infoMap.get(fkey) != null){
									String fieldValue = infoMap.get(fkey).toString();
									if(sfValue.equals("")){
										finalValue = fieldValue;
									}else{
										finalValue = sfValue;
									}
								//全局序号替换
								}else if (fkey.equals(tagName+"num")){
									finalValue = String.valueOf(c+1);
								//自定义字段替换
								}else if(infoMap.get(fkey) == null && !sfValue.equals("")){
									finalValue = sfValue;
								}else{
								//都找不到，只有不替换了
								//当然此处也应用于循环嵌套标签
									finalValue = fieldBody;
								}
								
								//过滤HTML代码,只显示中文
								if(formatStr.equals("ch") && !finalValue.startsWith(fieldPreStr)){
									finalValue = ToolsFuc.getChinese(finalValue);
								}
								
								//根据标签域的长度限制截字符串
								if(lenend != 0 && finalValue.length() > lenend && !finalValue.startsWith(fieldPreStr)){
									finalValue = finalValue.substring(lenstart,lenend);
								}
								
								//处理信息标题颜色 title_color
								if(fkey.equals("title")){
									String title_color="";
									if(infoMap.get("title_color")!=null){
										title_color = infoMap.get("title_color").toString();
									}
									if(!finalValue.equals("") && !title_color.equals("")){
										finalValue = "<font color=\""+title_color+"\">"+finalValue+"</font>";
									}
								}
								
								tempCon = tempCon.replace(fieldBody, finalValue);
							}
							newCon = newCon.append(tempCon);
						}
					}
					
					inStr = inStr.replace(tagBody, newCon);
					
				}

			}
		}
		return inStr;
	}
	
	//获取非循环标签体 如： {tag:include file=webtitle/}
	public  String getNoonLoopTagName(String cmsName,String tagName,String inStr){
		if(inStr.indexOf("{"+cmsName+":"+tagName)<0) return "";
		int i = inStr.indexOf("{"+cmsName+":"+tagName);
		inStr = inStr.substring(i,inStr.length());
		int j = inStr.indexOf("/}");
		String tagCon = inStr.substring(0,j+2);
		return tagCon;
	}
	
	
	@SuppressWarnings("unchecked")
	public  HashMap getfieldMap(String tagCon){
		HashMap fieldMap = new HashMap();
		
		Pattern p = Pattern.compile("\\[field:[a-zA-Z0-9_]+(\\?len\\([0-9]+(,[0-9]+)?\\))?(\\?format\\([a-z]+\\))?[/]]");
		Matcher m = p.matcher(tagCon);
		while(m.find()){
			String filedBody = m.group();
			String fieldName = filedBody.replace(fieldPreStr, "");
			fieldName = fieldName.replace("/]", "");
			Map attrMap = null;
			if(fieldName.indexOf("?") > -1){
				attrMap = new HashMap();
				int j = fieldName.indexOf("?");
				String attrStr = fieldName.substring(j,fieldName.length());
				String fattr[] = attrStr.split("\\?");
				for(int i=0;i<fattr.length;i++){
					if(!fattr[i].equals("")){
						String attrone = fattr[i];
						String key = attrone.substring(0,attrone.indexOf("("));
						String val = attrone.substring(attrone.indexOf("(")+1,attrone.indexOf(")"));
						attrMap.put(key, val);
					}
				}
			}
			fieldMap.put(filedBody, attrMap);
		}
		return fieldMap;
	}
	
	//根据传入的map替换sql里面的值
	@SuppressWarnings("unchecked")
	public  String getReplaceSqlByMap(String sql,Map map,boolean isRep){
		if(sql.equals("")) return "";
		Pattern p = Pattern.compile("[\\[]{1}[a-zA-Z_]+[\\]]{1}"); 
		Matcher m = p.matcher(sql);
		String tagStartBody = "";

		String start = "[",end = "]";
		
		while(m.find()){
			tagStartBody = m.group();
			String tagField = tagStartBody.replace(start, "").replace(end, "");
			if(map!=null && map.get(tagField)!=null){
				String tagValue = map.get(tagField).toString();
				sql = sql.replace(start+tagField+end, "").replace(start+"/"+tagField+end, "").replace(start+tagField+"_value/"+end, tagValue).trim();
			}
			else{
				if(isRep){
					int tagStart = sql.indexOf(start+tagField+end);
					int tagEnd = sql.indexOf(start+"/"+tagField+end)+(start+"/"+tagField+end).length();
					if(tagStart > tagEnd){
						return "";
					}
					String tagBody = sql.substring(tagStart,tagEnd);
					sql = sql.replace(tagBody, "");
				}
			}
		}
		return sql;
	}
	
	
	public  String getReplaceSqlByMap(String sql,Map map){
		return getReplaceSqlByMap(sql,map,true);
	}

	public  HashMap getParaMap(String startPos,String tagName) {
		HashMap paraMap = new HashMap();

		String tempStr = startPos;

		tempStr = tempStr.replace("{" + cmsName + ":" + tagName, "").replace("}", "").replace(
				"\"", "").replace("'", "");
		
		//无参数直接返回
		if(tempStr.trim().equals("")) return paraMap;
		
		//如果是非循环体，此处需要替换最后一个字符 “/” 符号
		
		int strLen = tempStr.length();
		if(strLen < 1){
			return paraMap;
		}
		
		if(tempStr.substring(strLen-1,strLen).equals("/")){
			tempStr = tempStr.substring(0,tempStr.length()-1);
		}

		String tempS[] = tempStr.split(" ");
		if (tempS != null && tempS.length > 0) {
			for (int i = 0; i < tempS.length; i++) {
				if (!tempS[i].equals("")) {
					String para[] = tempS[i].split("=");
					if (para.length > 1) {
						paraMap.put(para[0], para[1]);
					}
				}
			}
		}

		return paraMap;
	}
	
	
	public  boolean checkTag(String cmsName,String tagname,String inStr)
    {
    	if (inStr.indexOf(cmsName+":"+tagname)>=0) return true;
    	return false;
    }

}
