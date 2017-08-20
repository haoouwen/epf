/*
  
 
 * Package:com.rbt.service.impl
 * FileName: KeywordService.java 
 */
package com.rbt.service.impl;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Keyword;
import com.rbt.service.IKeywordService;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.IpSeekerInit;
import com.rbt.dao.IKeywordDao;

@Service
public class KeywordService extends GenericService<Keyword,String> implements IKeywordService {
	
	IKeywordDao keywordDao;

	@Autowired
	public KeywordService(IKeywordDao keywordDao) {
		super(keywordDao);
		this.keywordDao = keywordDao;
	}

	public void updateKeyNums(Keyword keyword) {
		this.keywordDao.updateKeyNums(keyword);
	}
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 11:06:16 AM
	 * @Method Description：集合判断处理
	 */
	public void keywordList(List keywordList,Keyword keyword,String num){
		
		if (keywordList != null && keywordList.size() > 0) {
			Map map = (HashMap) keywordList.get(0);
			String keyid = "";
			if (map.get("key_id") != null){
				keyid = map.get("key_id").toString();
			}
			 keyword = this.keywordDao.get(keyid);
			if(keyword!=null){
				int nums = Integer.parseInt(keyword.getNum()) + 1;
				keyword.setNum(String.valueOf(nums));
				this.keywordDao.update(keyword);
			}
		}else{
			try {
				keyword.setIn_date(DateUtil.getCurrentTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpServletRequest request =ServletActionContext.getRequest();
			String ipaddr =IpSeekerInit.getIpAddr(request);
			System.out.println(ipaddr);
			keyword.setM_ip(ipaddr);
			keyword.setNum(num);
			this.keywordDao.insert(keyword);
		}
	}

}
