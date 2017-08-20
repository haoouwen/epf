package com.rbt.push.android;

import com.rbt.push.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	//广播(broadcast): 向安装该App的所有设备发送消息。
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
