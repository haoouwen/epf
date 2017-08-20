/*
  
 
 * Package:com.rbt.filter
 * FileName: KeyWordFilter.java
 */
package com.rbt.function;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.util.DateUtil;
import com.rbt.common.util.IpSeekerInit;
import com.rbt.model.Keyword;
import com.rbt.service.IKeywordService;


/**
 * @function 功能  将搜索的词插入数据库
 * @author  创建人  LJQ
 * @date  创建日期  Sep 22, 2014 3:40:04 PM
 */
public class KeyWordInsertFuc extends CreateSpringContext {
	/**
	 * @MethodDescribe 方法描述    将关键字插入数据库
	 * @author  创建人  LJQ
	 * @throws ParseException 
	 * @date  创建日期  Sep 22, 2014 3:39:58 PM
	 */
	public static void wdInsert(String keyword,String en_name,String modtype) throws ParseException{
		// 是否开启关键字插入功能 0:是 1：否
		String keyword_switch = SysconfigFuc.getSysValue("cfg_autokeyword");
		String s_in_date="",s_m_ip="";
		s_in_date=DateUtil.getCurrentTime();//获得当前时间
		HttpServletRequest request =ServletActionContext.getRequest();
		s_m_ip=IpSeekerInit.getIpAddr(request);//活动访问者IP
		// 0表示是
		if(keyword_switch.equals("0")){
			IKeywordService keywordService = (IKeywordService)getContext().getBean("keywordService");
			Map map=new HashMap();
			map.put("keyword", keyword);
			map.put("module_type", modtype);
			List<Map<String,String>> list=keywordService.getList(map);
			Keyword word=new Keyword();
			//如果关键字已存在表中，就加一，否则就建立新的关键字
			if(list!=null&&list.size()>0){
                  String key_id="";
                  Map listMap = (HashMap)list.get(0);
                  if(listMap.get("key_id")!=null){
                	  key_id=listMap.get("key_id").toString();
                  }
                  word.setKey_id(key_id);
                  word.setIn_date(s_in_date);
                  word.setM_ip(s_m_ip);
                  keywordService.updateKeyNums(word);
			}else{
				word.setKey_name(keyword);
				word.setEn_name(en_name);
				word.setModule_type(modtype);
				word.setNum("1");
				word.setIs_show("1");//是否前台显示，0是，1否
				word.setIn_date(s_in_date);
				word.setM_ip(s_m_ip);
				keywordService.insert(word);
			}
		}    	
    }
}
