package com.rbt.push.ios;

import com.rbt.push.IOSNotification;

public class IOSUnicast extends IOSNotification {
	//单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	public IOSUnicast(String appkey,String appMasterSecret) throws Exception{
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
