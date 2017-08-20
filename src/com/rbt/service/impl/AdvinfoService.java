/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AdvinfoService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAdvinfoDao;
import com.rbt.dao.ICommparaDao;
import com.rbt.model.Advinfo;
import com.rbt.service.IAdvinfoService;

/**
 * @function 功能 广告信息Service层业务接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 5:40:26 PM
 */
@Service
public class AdvinfoService extends GenericService<Advinfo,String> implements IAdvinfoService {

	IAdvinfoDao advinfoDao;
    @Autowired
	ICommparaDao commparaDao;
	@Autowired
	public AdvinfoService(IAdvinfoDao advinfoDao) {
		super(advinfoDao);
		this.advinfoDao = advinfoDao;
	}


	public List getKeywordAdList(Map map) {
		return advinfoDao.getKeywordAdList(map);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IAdvinfoService#getAdvinfoIntr(java.util.Map)
	 */
	public List getAdvinfoIntr(Map map) {
		return this.advinfoDao.getAdvinfoIntr(map);
	}
	public Advinfo getImg(String id) {
		return this.advinfoDao.getImg(id);
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:35:24 AM
	 * @Method Description：List处理
	 */
	public List replaceList(List advinfoList){
		if(advinfoList!=null&&advinfoList.size()>0){
			Map postMap=new HashMap();
			for(int i=0;i<advinfoList.size();i++){
				Map valueMap=(Map) advinfoList.get(i);
				String post_type = "";
				if(valueMap.get("pos_type")!=null){
					post_type = valueMap.get("pos_type").toString();
				}
				postMap.put("para_value", post_type);
				List commparaList=this.commparaDao.getList(postMap);
				String value="";
				if(commparaList!=null&&commparaList.size()>0){
				   Map paramap=(Map)commparaList.get(0);
				   value=paramap.get("para_key").toString();
				}
				valueMap.put("pos_type", value);
			}
		}
		return advinfoList;
	}
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 2:16:59 PM
	 * @Method Description：获取信息
	 */
	public Advinfo getAdvinfo(String pos_id,Advinfo	advinfo){
		if(pos_id!=null){
		    Map posmap=new HashMap();
		    posmap.put("pos_id", pos_id);
		   List advinfoList=advinfoDao.getList(posmap);
		    String adv_id="";
		    if(advinfoList!=null&&advinfoList.size()>0){
		    	posmap=(HashMap)advinfoList.get(0);
		    	if(posmap.get("adv_id")!=null){
		    		adv_id=posmap.get("adv_id").toString();
		    	}
		    }
		    advinfo=advinfoDao.get(adv_id); 
		}
		return advinfo;
	}
}
