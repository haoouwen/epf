package com.rbt.push.ios;

import com.rbt.push.IOSNotification;


public class IOSBroadcast extends IOSNotification {
	//广播(broadcast): 向安装该App的所有设备发送消息。
	public IOSBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
