package com.rbt.push.android;

import com.rbt.push.AndroidNotification;

public class AndroidUnicast extends AndroidNotification {
	
	//单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	public AndroidUnicast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }

}