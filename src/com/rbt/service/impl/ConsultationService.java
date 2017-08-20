/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ConsultationService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.ICommparaDao;
import com.rbt.dao.IConsultationDao;
import com.rbt.dao.IMemberDao;
import com.rbt.model.Consultation;
import com.rbt.model.Member;
import com.rbt.service.IConsultationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品咨询信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:47:46 CST 2014
 */
@Service
public class ConsultationService extends GenericService<Consultation,String> implements IConsultationService {
	
	IConsultationDao consultationDao;
    @Autowired
	ICommparaDao commparaDao;
    @Autowired
    IMemberDao memberDao;
	@Autowired
	public ConsultationService(IConsultationDao consultationDao) {
		super(consultationDao);
		this.consultationDao = consultationDao;
	}
	
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map) {
		return this.consultationDao.getWebCount(map);
	}
	
	@SuppressWarnings("unchecked")
	public List getWebList(Map map){
		return this.consultationDao.getWebList(map);
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:37:18 PM
	 * @Method Description：List替换数据处理
	 */
	public List replaceList(List consultationList,Member member){
		if(consultationList!=null&&consultationList.size()>0){
			for(int i=0;i<consultationList.size();i++){
				Map valueMap=(Map) consultationList.get(i);
				String c_type = "",re_men_id="";
				//替换咨询类型ID为咨询类型名称
				if(valueMap.get("c_type")!=null){
					c_type = valueMap.get("c_type").toString();
				}
				//替换回复者ID为回复者名称或商铺名称
				if(valueMap.get("re_men_id")!=null){
					re_men_id = valueMap.get("re_men_id").toString();
				}
				Map postMap = new HashMap();
				postMap.put("para_value", c_type);
				postMap.put("para_code", "c_type");
				List commparaList=this.commparaDao.getList(postMap);
				String value="",namevalue="";
				if(commparaList!=null&&commparaList.size()>0){
				   Map paramap=(Map)commparaList.get(0);
				   value=paramap.get("para_key").toString();
				}
				if(!"".equals(re_men_id)){
				    member=memberDao.get(re_men_id);
				}
				if(member!=null){
					namevalue=member.getCust_name();
				}
				valueMap.put("re_men_id",namevalue);
				valueMap.put("c_type", value);
			}
		}
		return consultationList;
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map){
		return this.consultationDao.getListByGroup(map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map){
		return this.consultationDao.getGroupCount(map);
	}
}

