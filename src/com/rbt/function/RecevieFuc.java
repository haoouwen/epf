package com.rbt.function;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.service.IReceiveboxService;

public class RecevieFuc extends CreateSpringContext{
	//获取收件箱条数
	public static int getReceCount(){
		IReceiveboxService receiveboxService=(IReceiveboxService) getContext().getBean("receiveboxService");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Map map=new HashMap();
		map.put("cust_id",session.getAttribute("cust_id"));
		map.put("is_read","1");
		map.put("is_get_del","1");
		int i=receiveboxService.getCount(map);
		return i;
	}

}
