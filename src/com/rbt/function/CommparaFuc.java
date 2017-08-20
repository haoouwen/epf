package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

import com.rbt.common.util.ValidateUtil;
import com.rbt.service.ICommparaService;

/**
 * @function 功能  用于加载系统基本参数
 * @author  创建人  LJQ
 * @date  创建日期  Jul 29, 2014 1:10:56 PM
 */
public class CommparaFuc extends CreateSpringContext {

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:44:03 PM
	 * @Method Description :定义一个静态Map数据
	 */
	@SuppressWarnings("unchecked")
	public static Map commparaMap = null;

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:44:18 PM
	 * @Method Description :初始化commpara表
	 */
	@SuppressWarnings("unchecked")
	public static void initCommpara(String para_code) {
		commparaMap = new HashMap();
		try {			
			//定义一个list数组
			List commparaList = getCommparaList(para_code);
			if (commparaList != null && commparaList.size() > 0) {
				HashMap aMap = new HashMap();
				for (int i = 0; i < commparaList.size(); i++) {
					String para_value = "", para_key = "";
					aMap = (HashMap) commparaList.get(i);
					if (aMap.get("para_value") != null) {
						para_value = aMap.get("para_value").toString();
					}
					if (aMap.get("para_key") != null) {
						para_key = aMap.get("para_key").toString();
					}
					//将键值对存放在静态的MAP数据中
					commparaMap.put(para_value, para_key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:44:35 PM
	 * @Method Description :获取参数列表
	 */
	public static List getCommparaList(String para_code){
		//获取commparaService对象
		ICommparaService commparaService = (ICommparaService) getContext().getBean("commparaService");
		Map<String,String> pageMap = new HashMap<String,String>();
		if (para_code != null && !para_code.equals("")) {
			pageMap.put("para_code", para_code);
		}
		//找出相应的系统参数类型的列表
		return commparaService.getList(pageMap);
	}
	
	/**
	 * @author:HXM
	 * @date:Jun 5, 20143:02:03 PM
	 * @param:para_code 参数编码
	 * @Description:用于返回相同参数编码且信息状态为启用的(此处主要考虑的是可以看到的，所以禁用的就不显示)
	 */
	public static List getEnabledList(String para_code){
		//获取commparaService对象
		ICommparaService commparaService = (ICommparaService) getContext().getBean("commparaService");
		Map<String,String> pageMap = new HashMap<String,String>();
		if (!ValidateUtil.isRequired(para_code)) {
			pageMap.put("para_code", para_code);
		}
		//标记信息启用的信息
		pageMap.put("enabled", "0");
		//找出相应的系统参数类型的列表
		return commparaService.getList(pageMap);
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 3:45:23 PM
	 * @Method Description :在全局commparaMap中根据模块找出相应的键值对
	 */
	public static String get_commparakey_By_value(String value, String para_code) {
		String para_key = "";
		if (commparaMap == null) {
			initCommpara(para_code);
		}
		if (commparaMap != null && commparaMap.get(value) != null) {
			para_key = commparaMap.get(value).toString();
		}
		return para_key;
	}
	/**
	 * @author:HXM
	 * @date:Jun 7, 20145:46:23 PM
	 * @param:s_order_state订单状态
	 * @param:order_reason:订单修改原因
	 * @Description:返回订单修改说明
	 */
	public static String getReason(String s_order_state,String order_reason){
		String reason="";
		if(ValidateUtil.isRequired(order_reason)){
			reason="订单状态变为：";
		}else{
			return order_reason;
		}
		List list=getEnabledList("order_state");
		for (int i = 0; i < list.size(); i++) {
			Map m=(HashMap)list.get(i);
			if(s_order_state.equals(m.get("para_value").toString())){
				reason+=m.get("para_key").toString();					
				break;
			}
		}
		return reason;
	}
	
	
}