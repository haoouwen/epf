package com.rbt.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.function.CreateSpringContext;
import com.rbt.service.ICommparaService;

public class SpecialDeal  extends CreateSpringContext{
	
	public String getSpecialValue(String key,String value){
		String ret = value;
		//经营模式的处理
		if(key.equals("client_status")){
			ICommparaService commparaService = (ICommparaService) getContext().getBean("commparaService");
			Map clientMap = new HashMap();
			clientMap.put("para_code", "client_status");
			List list = commparaService.getWebCommparaList(clientMap);
			ret="";//清空数据
			for(int i=0;i<list.size();i++){
				HashMap listMap=new HashMap();
				listMap=(HashMap)list.get(i);
				String para_name="",para_value="";
				if(listMap.get("para_key")!=null){
					para_name=listMap.get("para_key").toString();
				}
				if(listMap.get("para_value")!=null){
					para_value=listMap.get("para_value").toString();
				}
				if(value.indexOf(para_value)>-1){
					ret+=(para_name+",");
				}
			}
		}		
		//图片的处理
		if(key.equals("lu_img_path")){
			if(value!=null && !value.equals("")){
				ret=Constants.IFIMG+value;	
			}
		}
		
		return ret;
	}
	
}
