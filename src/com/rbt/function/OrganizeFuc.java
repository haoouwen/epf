package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.service.IAreaService;
import com.rbt.service.IOrganizeService;

/**
 * @author : LJQ
 * @date : Mar 12, 2014 11:05:17 AM
 * @Method Description :部门的数据管理
 */
public class OrganizeFuc extends CreateSpringContext{
	
	public static HashMap orgMap = null;
	// 从Spring容器中获取所属地区业务Bean
	@SuppressWarnings("unchecked")
	public static IOrganizeService getOrganizeObj() {
		return (IOrganizeService) getContext().getBean("organizeService");
	}
	
	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 2:29:15 PM
	 * @Method Description :获取所属部门的全部名称
	 */
	public static List getAllOrganize() {
		IOrganizeService organizeService = getOrganizeObj();
		return organizeService.getAll();
	}
	
	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 3:24:34 PM
	 * @Method Description :org_id和org_name成键值对存放在HashMap中
	 */
	public static HashMap getOrganizeMap() {
		if(orgMap!=null){
			return orgMap;
		}
		orgMap= new HashMap();
		List orgList = getAllOrganize();
		if (orgList != null && orgList.size() > 0) {
			HashMap aMap = new HashMap();
			for (int i = 0; i < orgList.size(); i++) {
				String org_id = "", org_name = "";
				aMap = (HashMap) orgList.get(i);
				if (aMap.get("org_id") != null) {
					org_id = aMap.get("org_id").toString();
				}
				if (aMap.get("org_name") != null) {
					org_name = aMap.get("org_name").toString();
				}
				orgMap.put(org_id, org_name);
			}
		}
		return orgMap;
	}
	
	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 3:27:51 PM
	 * @Method Description :根据所属部门ID找出所属部门名称
	 */
	public static String getOrganizeNameByMap(String org_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap orgMap = getOrganizeMap();
		String org_name = "";
		// 定义String分隔串
		if (org_id_string != null && !"".equals(org_id_string)) {
			org_id_string = org_id_string.replace(" ","");
			String[] org_id = org_id_string.split(",");
			for (int j = 0; j < org_id.length; j++) {
				if (org_id[j] != null && !org_id[j].equals("")) {
					if (orgMap != null && orgMap.get(org_id[j]) != null) {
						org_name = orgMap.get(org_id[j]).toString();
						sb.append(org_name);
						if (j != org_id.length - 1) {
							sb.append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
}
