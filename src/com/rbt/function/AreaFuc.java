package com.rbt.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.rbt.common.util.IpSeekerInit;
import com.rbt.model.Area;
import com.rbt.service.IAreaService;

/**
 * @function 功能 加载地区列表
 * @author 创建人 LJQ
 * @date 创建日期 Jul 28, 2014 11:11:59 AM
 */
public class AreaFuc extends CreateSpringContext {
	
	public static HashMap areaMap = null;
	public static List three_areaList;
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 2:54:51 PM
	 * @Method Description :从这个方法getAllArea()里的list转化为HashMap，此处的list启用ibatis的缓存处理,
	 * area_id和area_name成键值对存放在HashMap中
	 */
	public static HashMap getAreaMap() {
		if(areaMap==null){
			areaMap = new HashMap();
			List areaList = getAllArea();
			if (areaList != null && areaList.size() > 0) {
				HashMap aMap = new HashMap();
				for (int i = 0; i < areaList.size(); i++) {
					String area_id = "", area_name = "";
					aMap = (HashMap) areaList.get(i);
					if (aMap.get("area_id") != null) {
						area_id = aMap.get("area_id").toString();
					}
					if (aMap.get("area_name") != null) {
						area_name = aMap.get("area_name").toString();
					}
					areaMap.put(area_id, area_name);
				}
			}
		}
		return areaMap;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 2:54:38 PM
	 * @Method Description :从Spring容器中获取地区业务Bean
	 */
	public static IAreaService getAreaObj() {
		return (IAreaService) getContext().getBean("areaService");
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 2:54:08 PM
	 * @Method Description :获取系统所有的地区
	 */
	public static List getAllArea() {
		HashMap map = new HashMap();
		return getAreaList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 2:53:49 PM
	 * @Method Description :根据条件获取地区信息
	 */
	public static List getAreaList(HashMap map) {
		IAreaService areaService = getAreaObj();
		return areaService.getList(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:36:50 PM
	 * @Method Description :根据地区名称找出地区的ID串
	 */
	public static String getAreaIdByAreaName(String area_name) {
		// 地区id
		String area_id = "";
		HashMap map = new HashMap();
		map.put("area_name", area_name);
		List areaList = getAreaList(map);
		if (areaList != null && areaList.size() > 0) {
			HashMap idmap = new HashMap();
			idmap = (HashMap) areaList.get(0);
			if (idmap.get("area_id") != null) {
				area_id = idmap.get("area_id").toString();
			}
		}
		return area_id;
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 12:36:37 PM
	 * @Method Description :根据地区拼音找出地区的ID串
	 */
	public static String getAreaIdByEnName(String en_name) {
		// 地区id
		String area_id = "";
		HashMap map = new HashMap();
		map.put("en_name", en_name);
		List areaList = getAreaList(map);
		if (areaList != null && areaList.size() > 0) {
			HashMap idmap = new HashMap();
			idmap = (HashMap) areaList.get(0);
			if (idmap.get("area_id") != null) {
				area_id = idmap.get("area_id").toString();
			}
		}
		return area_id;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 2:56:03 PM
	 * @Method Description : 在全局areaMap中根据地区标识串,以逗号,隔开,找出地区名称
	 */
	public static String getAreaNameByMap(String area_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap areaMap = getAreaMap();
		String area_name = "";
		// 定义String分隔串
		if (area_id_string != null && !"".equals(area_id_string)) {
			area_id_string = area_id_string.replace(" ","");
			String[] area_id = area_id_string.split(",");
			for (int j = 0; j < area_id.length; j++) {
				if (area_id[j] != null && !area_id[j].equals("")) {
					if (areaMap != null && areaMap.get(area_id[j]) != null) {
						area_name = areaMap.get(area_id[j]).toString();
						sb.append(area_name);
						if (j != area_id.length - 1) {
							sb.append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:15:48 PM
	 * @Method Description :在全局areaMap中根据地区标识串,以|线隔开,找出地区名称
	 */
	public static String getAreaNameByListMap(String area_id_string) {
		StringBuffer sb = new StringBuffer();
		HashMap areaMap = getAreaMap();
		String area_name = "";
		// 定义String分隔串
		if (area_id_string != null && !"".equals(area_id_string)) {
			area_id_string = area_id_string.replace(" ","");
			String[] area_id = area_id_string.split("\\|");
			for (int j = 0; j < area_id.length; j++) {
				if (area_id[j] != null && !area_id[j].equals("")) {
					if (areaMap != null && areaMap.get(area_id[j]) != null) {
						area_name = areaMap.get(area_id[j]).toString();
						sb.append(area_name);
						if (j != area_id.length - 1) {
							sb.append(",");
						}
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:16:13 PM
	 * @Method Description :通过地区ID找出英文名称
	 */
	public static String getAreaEnglishName(String area_id) {
		String en_name= "";
		if (area_id != null && !area_id.equals("")) {
			en_name = getAreaObj().get(area_id).getEn_name();
		}
		return en_name;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:17:43 PM
	 * @Method Description :在全局areaMap中找出地区串第一个ID的名称
	 */
	public static String getFiresAreaName(String area_id_string) {
		String first_area_name = "";
		HashMap areaMap = getAreaMap();
		// 定义String分隔串
		String[] area_id = area_id_string.split(",");
		if (area_id.length > 0 && area_id[0] != null) {
			if (areaMap != null && areaMap.get(area_id[0]) != null
					&& !areaMap.get(area_id[0]).equals("")) {
				first_area_name = areaMap.get(area_id[0]).toString();
			}
		}
		return first_area_name;
	}
	
	/**
	 * @author : QJY
	 * @date : Feb 31, 2014 11:17:43 PM
	 * @Method Description :在全局areaMap中找出地区串最后一个ID的名称
	 */
	public static String getLastAreaName(String area_id_string) {
		String first_area_name = "";
		HashMap areaMap = getAreaMap();
		// 定义String分隔串
		String[] area_id = area_id_string.split(",");
		int total_length = area_id.length-1;
		if (area_id.length > 0 && area_id[total_length] != null) {
			if (areaMap != null && areaMap.get(area_id[total_length]) != null
					&& !areaMap.get(area_id[total_length]).equals("")) {
				first_area_name = areaMap.get(area_id[total_length]).toString();
			}
		}
		return first_area_name;
	}
	/**
	 * @author : QJY
	 * @date : Feb 31, 2015 11:17:43 PM
	 * @Method Description :在全局areaMap中找出地区串最后一个ID对于的区域编号
	 */
	public static String getLastAreaNumber(String area_id_string) {
		String last_area_number = "";
		String last_id="";
		// 定义String分隔串
		String[] area_id = area_id_string.split(",");
		int total_length = area_id.length-1;
		if (area_id.length > 0 && area_id[total_length] != null) {
			last_id=area_id[total_length];
			Area area=getAreaObj().get(last_id);
		    if(area!=null&&area.getArea_number()!=null){
		    	last_area_number=area.getArea_number();
		    }
		}
		return last_area_number;
	}
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:19:41 PM
	 * @Method Description :任意一个地区id号找到顶级地区名称
	 */
	public static String getTopAreaName(String area_id){
		String area_name="";
		Area area=getAreaObj().get(area_id);
		if(area!=null && area.getArea_level()!=null){
			area_name=area.getArea_name();
			if(!"1".equals(area.getArea_level().toString())){
				return getTopAreaName(area.getUp_area_id());
			}
		}
		return area_name;
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:19:56 PM
	 * @Method Description :任意一个地区id好找到顶级地区id号
	 */
	public static String getTopAreaId(String area_id){
		String top_area_id="";
		Area area=getAreaObj().get(area_id);
		if(area!=null && area.getArea_level()!=null){
			top_area_id=area.getArea_id();
			if(!"1".equals(area.getArea_level().toString())){
				return getTopAreaId(area.getUp_area_id());
			}
		}
		return top_area_id;
	}
	
	
	/**
	 * @author : LJQ
	 * @date : May 23, 2014 1:23:32 PM
	 * @Method Description : 根据IP找出应的数据
	 */
	public static List getAreaListByIpaddr(HttpServletRequest request){
		IAreaService areaService = (IAreaService) getAreaObj();		
		// 获取IP地址
		String ipaddr = IpSeekerInit.getIpAddr(request);
		if (ipaddr.equals("127.0.0.1")) {
		}
		String addrName = IpSeekerInit.getAreaName(ipaddr);
		HashMap areamap = new HashMap();
		areamap.put("address", addrName);
		List areaList = areaService.getList(areamap);
		return areaList;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:26:30 PM
	 * @Method Description :根据IP地址获取本系统地区id
	 */
	public static String getAreaidByIpaddr(HttpServletRequest request) {
		List list = getAreaListByIpaddr(request);
		String idvlaue = "";
		// 获取与IP匹配的地区列表 筛选级别最低的地区ID
		if (list != null && list.size() > 0) {
			HashMap valuemap = (HashMap) list.get(0);
			if (valuemap.get("area_id") != null){
				idvlaue = valuemap.get("area_id").toString();
			}
		}
		return idvlaue;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 23, 2014 1:32:33 PM
	 * @Method Description :根据ip地址获取本系统地区名称
	 */
	public static String getAreanameByIpaddr(HttpServletRequest request){
		List list = getAreaListByIpaddr(request);
		String area_name = "";
		// 获取与IP匹配的地区列表 筛选级别最低的地区ID
		if (list != null && list.size() > 0) {
			HashMap valuemap = (HashMap) list.get(0);
			if (valuemap.get("area_name") != null){
				area_name = valuemap.get("area_name").toString();
			}
		}
		return area_name;
	}
	
}