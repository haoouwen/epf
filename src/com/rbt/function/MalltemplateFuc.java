package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.service.IMalltemplateService;

public class MalltemplateFuc extends CreateSpringContext {
	
	public static String default_mall_template=null;
	
	public static String getMallTemplate(){
		IMalltemplateService malltemplateService = (IMalltemplateService)getContext().getBean("malltemplateService");
		if(default_mall_template==null){
			Map map = new HashMap();
			map.put("is_enable","0");
			List list = malltemplateService.getList(map);
			if(list!=null && list.size()>0){
				Map listMap = (HashMap) list.get(0);
				if(listMap.get("template_code")!=null){
					default_mall_template= listMap.get("template_code").toString();
				}
			}
		}
		return default_mall_template;
	}
}
