package com.rbt.function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.model.Messagepush;
import com.rbt.push.PushClient;
import com.rbt.push.android.AndroidBroadcast;
import com.rbt.push.ios.IOSBroadcast;
import com.rbt.push.ios.IOSUnicast;
import com.rbt.service.IMessagepushService;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
/**
 * @function 功能  IOS 安卓 推送消息
 * @author  创建人  HXK	
 */

public class MessagePushFuc extends CreateSpringContext {
	
	private PushClient client = new PushClient();
	@SuppressWarnings("unchecked")
	public boolean sendpush(Messagepush messagepush,String android_appkey,String android_appMasterSecret,String ios_appkey,String ios_appMasterSecret){
		
		boolean fage=true;
		try {
			//消息详细页连接地址
			String info_url="http://m.epff.cc/webapp/MessagePushDetail_"+messagepush.getMsgpush_id()+".html";
			
			//IOS友盟
			IOSAllPushYm(messagepush,info_url,ios_appkey,ios_appMasterSecret);
			
			//安卓
			androidAllSendPush(messagepush,info_url,android_appkey,android_appMasterSecret);
			//IOSPushYm("4d7787aa61284c8703701d178eea4ca0a88ef6bb6fdfcf744957f6258c06c081",messagepush.getMp_abstract());
			fage=true;
		} catch (Exception e) {
			// TODO: handle exception
			fage=false;
		}
	  return fage;
		
	}
	
	/**
	 * @Method Description :友盟android广播推送服务器
	 * @author: HXK
	 * @date : Jul 8, 2016 10:10:15 AM
	 */
	@SuppressWarnings("unchecked")
	public void androidAllSendPush(Messagepush messagepush,String info_url,String appkey,String appMasterSecret) throws Exception {  
		
			//String appkey="578dd73f67e58e2a2a000a44";
			//String appMasterSecret="gokn5ci8qpijnccklyy8wcbknuexipox";
			IMessagepushService messagepushService = (IMessagepushService) getContext().getBean("messagepushService");
			AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
			broadcast.setTicker( "Android broadcast ticker");
			broadcast.setTitle(messagepush.getMsgpush_name());
			broadcast.setText(messagepush.getMp_abstract());
			broadcast.goAppAfterOpen();
			//broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
			broadcast.setDisplayType("notification");
			broadcast.setProductionMode();
			// Set customized fields
			broadcast.setExtraField("cus_title", messagepush.getMsgpush_name());
			broadcast.setExtraField("cus_content",messagepush.getMp_abstract());
			broadcast.setExtraField("cus_date",messagepush.getIn_date());
			broadcast.setExtraField("info_url",info_url);
			broadcast.setExtraField("apns_type",messagepush.getApns_type());
			client.send(broadcast);
			//更新数据库 推送状态为成功1 
			messagepush.setAndroid_state("1");
			messagepushService.update(messagepush);
	}
	
	/**
	 * @Method Description :友盟IOS广播模式推送服务器
	 * @author: HXK
	 * @date : Jul 8, 2016 10:10:15 AM
	 * @param 
	 * @return return_type
	 * @throws Exception 
	 */
	public void IOSAllPushYm(Messagepush messagepush,String info_url,String appkey,String appMasterSecret) throws Exception{
		//String appkey="578dd689e0f55aa886003f23";
		//String appMasterSecret="f05foiqtexdaguav0cg67so7hlmhaxqg";
		
		try {
			IMessagepushService messagepushService = (IMessagepushService) getContext().getBean("messagepushService");
			IOSBroadcast broadcast = new IOSBroadcast(appkey,appMasterSecret);
			broadcast.setAlert(messagepush.getMp_abstract());
			broadcast.setBadge( 1);
			broadcast.setSound( "default");
			// TODO set 'production_mode' to 'true' if your app is under production mode
			broadcast.setProductionMode();
			// Set customized fields
			broadcast.setCustomizedField("cus_title", messagepush.getMsgpush_name());
			broadcast.setCustomizedField("cus_content", messagepush.getMp_abstract());
			broadcast.setCustomizedField("cus_date", messagepush.getIn_date());
			broadcast.setCustomizedField("info_url",info_url);
			broadcast.setCustomizedField("apns_type",messagepush.getApns_type());
			broadcast.setCustomizedField("msgID",messagepush.getMsgpush_id());
			client.send(broadcast);
			//更新数据库 推送状态为成功1 
			messagepush.setIos_state("1");
			messagepushService.update(messagepush);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @Method Description :友盟IOS单播模式
	 * @author: HXK
	 * @date : Jul 8, 2016 10:10:15 AM
	 * @param 
	 * @return return_type
	 * @throws Exception 
	 */
	public void IOSPushYm(String tokensss,String content) throws Exception{
		String appkey="578dd689e0f55aa886003f23";
		String appMasterSecret="f05foiqtexdaguav0cg67so7hlmhaxqg";
		IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken(tokensss);
		unicast.setAlert(content);
		unicast.setBadge( 1);
		unicast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		unicast.setTestMode();
		// Set customized fields
		//unicast.setCustomizedField("test", "helloworld");
		client.send(unicast);
	}
	/**
	 * @Method Description :IOS推送服务器
	 * @author: HXK
	 * @date : Jul 8, 2016 10:10:15 AM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public void iosSendPush(List tokens,String content) {  
		String path="E:\\iphone\\WPNPushService.p12";  
        String password="wappin2009";  
        String message="{'aps':{'alert':'"+content+"'}}";  
        Integer count=1;  
        boolean sendCount=false; 
		iosSendPushOrg(tokens, path,  password,  message, count, sendCount);
	}

    /************************************************ 
    测试推送服务器地址：gateway.sandbox.push.apple.com /2195  
    产品推送服务器地址：gateway.push.apple.com / 2195  
    需要javaPNS_2.2.jar包 

    ***************************************************/  
   /** 

    * apple的推送方法 

    * @param tokens   iphone手机获取的token 

    * @param path 这里是一个.p12格式的文件路径，需要去apple官网申请一个  

    * @param password  p12的密码 此处注意导出的证书密码不能为空因为空密码会报错 

    * @param message 推送消息的内容 

    * @param count 应用图标上小红圈上的数值 

    * @param sendCount 单发还是群发  true：单发 false：群发 

    */  
	@SuppressWarnings("unchecked")
	public void iosSendPushOrg(List tokens,String path, String password, String message,Integer count,boolean sendCount) {  
		
		
		if(tokens!=null&&tokens.size()>0){
			 try {  
	             //message是一个json的字符串{“aps”:{“alert”:”iphone推送测试”}}  
	  
	            PushNotificationPayload payLoad =  PushNotificationPayload.fromJSON(message);  
	              
	            payLoad.addAlert(message); // 消息内容  
	              
	            payLoad.addBadge(count); // iphone应用图标上小红圈上的数值  
	              
	            payLoad.addSound("default"); // 铃音 默认  
	  
	            PushNotificationManager pushManager = new PushNotificationManager();  
	              
	            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务  
	              
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(path, password, false));  
	              
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();   
	              
	            // 发送push消息  
	              
	            if (sendCount) {  
	              
	            System.out.println("--------------------------apple 推送 单-------");
	              
	            Device device = new BasicDevice();  
	            
	            Map tokensMap=new HashMap();  
	            tokensMap=(HashMap)tokens.get(0);
	            if(tokensMap!=null&&tokensMap.get("token_name")!=null){
		            device.setToken(tokensMap.get("token_name").toString());  
		            PushedNotification notification = pushManager.sendNotification(device, payLoad, true);  
		            notifications.add(notification);  
	            }
	              
	            } else {  
	              
	            System.out.println("--------------------------apple 推送 群-------");
	              
	            List<Device> device = new ArrayList<Device>();  
	              
	            for(int i=0;i<tokens.size();i++){
	            	Map tokensMap=new HashMap();  
		            tokensMap=(HashMap)tokens.get(i);
		            if(tokensMap!=null&&tokensMap.get("token_name")!=null){
		            	device.add(new BasicDevice(tokensMap.get("token_name").toString())); 
		            }
	            }
	            notifications = pushManager.sendNotifications(payLoad, device);  
	              
	            }  
	              
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);  
	              
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);  
	              
	            int failed = failedNotifications.size();  
	              
	            int successful = successfulNotifications.size();  
	              
	               
	              
	            if (successful > 0 && failed == 0) {  
	              
	            	System.out.println("-----All notifications pushed 成功 (" + successfulNotifications.size() + "):");  
	              
	            } else if (successful == 0 && failed > 0) {  
	              
	            	System.out.println("-----All notifications 失败 (" + failedNotifications.size() + "):");  
	              
	            } else if (successful == 0 && failed == 0) {  
	              
	            System.out.println("No notifications could be sent, probably because of a critical error");  
	              
	            } else {  
	              
	            	System.out.println("------Some notifications 失败 (" + failedNotifications.size() + "):");  
	              
	            	System.out.println("------Others 成功 (" + successfulNotifications.size() + "):");  
	              
	            }  
	      
	    // pushManager.stopConnection();  
	  
	    } catch (Exception e) {  
	      
	    e.printStackTrace();  
	      
	    }  
		}
	}  
	
	
	 public static void main(String[] args) {  
	        //sendpush(tokens, path, password, message, count, sendCount);  
	 }  
	
}
