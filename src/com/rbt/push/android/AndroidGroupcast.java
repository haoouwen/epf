package com.rbt.push.android;

import org.json.JSONObject;

import com.rbt.push.AndroidNotification;

public class AndroidGroupcast extends AndroidNotification {
	
	//组播(groupcast): 向满足特定条件的设备集合发送消息，例如: "特定版本"、"特定地域"等。
	//友盟消息推送所支持的维度筛选和友盟统计分析所提供的数据展示维度是一致的，后台数据也是打通的
	public AndroidGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
