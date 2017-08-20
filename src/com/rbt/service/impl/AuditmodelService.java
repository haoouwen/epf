/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AuditmodelService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IAuditmodelDao;
import com.rbt.model.Auditmodel;
import com.rbt.service.IAuditmodelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 审核模型信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2014
 */
@Service
public class AuditmodelService extends GenericService<Auditmodel, String>
		implements IAuditmodelService {

	IAuditmodelDao auditmodelDao;

	@Autowired
	public AuditmodelService(IAuditmodelDao auditmodelDao) {
		super(auditmodelDao);
		this.auditmodelDao = auditmodelDao;
	}

	public List getModelList(Map map) {
		return this.auditmodelDao.getModelList(map);
	}

	public int getModelCount(Map map) {
		return this.auditmodelDao.getModelCount(map);
	}

	/**
	 * 获取某一个用户需要审核的模块信息
	 * 
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map) {
		return this.auditmodelDao.getAuditList(map);
	}

	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:01:38 PM
	 * @Method Description：新增方法
	 */
	public void inserinfo(String sel_mem_str, List sel_member_list,
			Auditmodel auditmodel) {
		if (sel_mem_str != null) {
			String ids[] = null;
			if (sel_mem_str.contains("|")) {
				ids = sel_mem_str.split("\\|");
			} else {
				ids = new String[1];
				ids[0] = sel_mem_str;
			}
			sel_member_list = new ArrayList();
			for (int i = 0; i < ids.length; i++) {
				Map listMap = new HashMap();
				String id = ids[i].replace(" ", "");
				String values[] = id.split(",");
				if (values != null) {
					if (values[0] != null) {
						auditmodel.setUserid(values[0].toString());
					}
					if (values[1] != null) {
						auditmodel.setUsername(values[1].toString());
					}
					auditmodel.setSort_no(String.valueOf(i + 1));
					if (!values[0].equals("") && !values[1].equals("")) {
						this.auditmodelDao.insert(auditmodel);
					}
				}
			}
		}
	}

	/**
	 * 方法描述：插入模型的时候，判断该审核模型是否已经存在数据中！，如果存在只能修改，不能新增重复的审核模块
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean existsAuditModelType(String modeltype) {
		boolean flage = false;// flage的值为：ture表示找到该模型信息，如果为false表示没有找到模型信息
		List modelList = new ArrayList();
		Map pageMap = new HashMap();
		pageMap.put("model_type", modeltype);
		modelList = this.auditmodelDao.getList(pageMap);
		if (modelList != null && modelList.size() > 0) {
			flage = true;
		}
		return flage;
	}

	/**
	 * 方法描述：构造LIST用于信息的回选
	 * 
	 * @return
	 * @throws Exception
	 */
	public void sel_member_list(String user_ids, List sel_member_list) {
		if (user_ids != null && !user_ids.equals("")) {
			String ids[] = null;
			if (user_ids.contains("|")) {
				ids = user_ids.split("\\|");
			} else {
				ids = new String[1];
				ids[0] = user_ids;
			}
			sel_member_list = new ArrayList();
			for (int i = 0; i < ids.length; i++) {
				Map listMap = new HashMap();
				String id = ids[i].replace(" ", "");
				String values[] = id.split(",");
				if (values != null) {
					if (values[0] != null) {
						listMap.put("id", values[0].toString());
					}
					if (values[1] != null) {
						listMap.put("val", values[1].toString());
					}
					if (!values[0].equals("") && !values[1].equals("")) {
						sel_member_list.add(i, listMap);
					}
				}
			}
		}
	}

	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:17:12 PM
	 * @Method Description：删除方法
	 */
	public boolean deleteinfo(Auditmodel auditmodel) {
		if (auditmodel != null && auditmodel.getModel_type() != null) {
			String modelattr = auditmodel.getModel_type().toString();
			modelattr = modelattr.replace(" ", "");
			if (modelattr.contains(",")) {
				String strmodelattr[] = modelattr.split(",");
				for (int i = 0; i < strmodelattr.length; i++) {
					if (strmodelattr[i] != null) {
						this.auditmodelDao.delete(strmodelattr[i].toString());
					}
				}
			} else {
				this.auditmodelDao.delete(modelattr);
			}
			return true;
		}
		return false;
	}

	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:27:47 PM
	 * @Method Description：构造一个List
	 */
	public List auditList(List auditmodelList, List auditList,
			List audittypeList) {

		if (auditmodelList != null && auditmodelList.size() > 0) {

			auditList = new ArrayList();
			for (int i = 0; i < auditmodelList.size(); i++) {
				String model_type = "";
				Map auditmodelMap = new HashMap();
				auditmodelMap = (HashMap) auditmodelList.get(i);
				if (auditmodelMap != null
						&& auditmodelMap.get("model_type") != null) {
					String trade_id = "", module_name = "", str_username = "";
					model_type = auditmodelMap.get("model_type").toString();
					trade_id = auditmodelMap.get("trade_id").toString();
					module_name = auditmodelMap.get("para_key").toString();
					for (int j = 0; j < audittypeList.size(); j++) {
						Map audittypeMap = new HashMap();
						audittypeMap = (HashMap) audittypeList.get(j);
						if (audittypeMap != null
								&& audittypeMap.get("model_type") != null) {
							String str_model_type = "", sort_no = "", username = "";
							str_model_type = audittypeMap.get("model_type")
									.toString();
							if (model_type.equals(str_model_type)) {

								if (audittypeMap.get("sort_no") != null) {
									sort_no = audittypeMap.get("sort_no")
											.toString();
								}
								if (audittypeMap.get("username") != null) {
									username = audittypeMap.get("username")
											.toString();
								}
								str_username += " ["
										+ sort_no
										+ "]."
										+ username
										+ " <img src='/include/images/admin/litjian.png' />";
							}

						}
					}
					// 构造一个新的List
					Map auditMap = new HashMap();
					auditMap.put("trade_id", trade_id);
					auditMap.put("para_key", module_name);
					auditMap.put("model_type", model_type);
					if (str_username != null && !str_username.equals("")) {
						str_username = str_username.substring(0, str_username
								.lastIndexOf("<img"));
						auditMap.put("audit_sortno_name", str_username);
					}
					auditList.add(auditMap);
				}
			}
		}
		return auditList;
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:39:29 PM
	 * @Method Description：修改判断
	 */
	public void checkView(List aList,String mem_count,String sel_mem_str,List sel_member_list){
		if (aList != null && aList.size() > 0) {
			mem_count = String.valueOf(aList.size());
			sel_mem_str = "";
			for (int i = 0; i < aList.size(); i++) {
				Map mMap = new HashMap();
				mMap = (HashMap) aList.get(i);
				String user_id = "", user_name = "";
				if (mMap != null && mMap.get("userid") != null) {
					user_id = mMap.get("userid").toString();
				}
				if (mMap != null && mMap.get("username") != null) {
					user_name = mMap.get("username").toString();
				}
				if (mMap != null && mMap.get("userid") != null
						&& mMap.get("username") != null) {
					sel_mem_str += user_id + "," + user_name + "|";
				}
			}
		}
		if (sel_mem_str != null) {
			sel_mem_str = sel_mem_str
					.substring(0, sel_mem_str.lastIndexOf("|"));
			sel_member_list(sel_mem_str, sel_member_list);
		}
	}
}
