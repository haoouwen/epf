/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AssociationkeywordsService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IAssociationkeywordsDao;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Associationkeywords;
import com.rbt.service.IAssociationkeywordsService;


/**
 * @function 功能 联想关键词Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Wed Jun 24 11:18:16 CST 2015
 */
@Service
public class AssociationkeywordsService extends GenericService<Associationkeywords,String> implements IAssociationkeywordsService {
	
	IAssociationkeywordsDao associationkeywordsDao;

	@Autowired
	public AssociationkeywordsService(IAssociationkeywordsDao associationkeywordsDao) {
		super(associationkeywordsDao);
		this.associationkeywordsDao = associationkeywordsDao;
	}
	public List getExAttrList(Map map){
		return this.associationkeywordsDao.getExAttrList(map);
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids,List cat_attr_list) {
		String ids[] = cat_ids.split("\\|");
		cat_attr_list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			Map listMap = new HashMap();
			String id = ids[i].replace(" ", "");
			listMap.put("id", id);
			String catName = CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if (!id.equals("") && !catName.equals("")) {
				cat_attr_list.add(i, listMap);
			}
		}
		return cat_attr_list;
	}
	public String associationToCategory(String ass_title) throws ParseException, IOException{
		String ass_cat_attr="";
		if(!ValidateUtil.isRequired(ass_title)){
			List asList=new ArrayList();
			HashMap<String,String> aslistMap=new HashMap<String,String >();
			aslistMap.put("ass_key_words_show", "0");
			aslistMap.put("ass_key_words_title", ass_title);
			asList=this.associationkeywordsDao.getList(aslistMap);
			if(asList!=null&&asList.size()>0){
				Map asMap=new HashMap();
				asMap=(HashMap)asList.get(0);
				if(asMap.get("cat_attr")!=null){
					ass_cat_attr=asMap.get("cat_attr").toString();
				}
			}
		}
		return ass_cat_attr;
	}
}

