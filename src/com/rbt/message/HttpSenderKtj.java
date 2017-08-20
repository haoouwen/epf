package com.rbt.message;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.rbt.common.BASE64;
import com.rbt.common.Md5;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.function.SysconfigFuc;

public class HttpSenderKtj {
	
	public static void main(String[] args) throws Exception {
		String dateString ="{\"SalesChannelSysNo\":1087,\"OrderIds\":[\"10011003\"]}";
		Map<String,Object> map = new HashMap<String,Object>();
		ArrayList orderList = new ArrayList(); 
		orderList.add("10011114");
		map.put("OrderIds", orderList);
		
		map.put("SalesChannelSysNo", "1087");
		
		JSONObject jsObj = JSONObject.fromObject(map);  
		String datajson = jsObj.toString();

		readContentFromPost(datajson,"order.SOTrace");
		
	}  
	
	
	public static String  readContentFromPost(String dates,String method) throws Exception{  
        URL postUrl = new URL("http://api.kjt.com/open.api");  
        HttpURLConnection connection = (HttpURLConnection) postUrl  
                .openConnection();  
        connection.setDoOutput(true);  
        connection.setDoInput(true);  
        //采用post 方式
        connection.setRequestMethod("POST");  
        connection.setUseCaches(false);  
        connection.setInstanceFollowRedirects(true);  
        connection.setRequestProperty("Content-Type",  
                "application/x-www-form-urlencoded");  
        postUrl.openConnection();
        connection.connect();  
        DataOutputStream out = new DataOutputStream(connection  
                .getOutputStream()); 
       //可以方便地修改日期格式
        Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHmmss");
		String timestamp = dateFormat.format( now );
		//随机数
		String nonce =RandomStrUtil.getRand("4");
		System.out.println(dates);
		dates = URLEncoder.encode(dates, "UTF-8");
		//要发送的数据
        String kjtString = "appid="+SysconfigFuc.getSysValue("cfg_kjtappid")+"&data="+dates+"&format=json&method="+method+"&nonce="+nonce+"&timestamp="+timestamp+"&version=1.0&"+SysconfigFuc.getSysValue("cfg_kjtsecretkey")+"";
        //md5加密发送签名
        String  passwd = Md5.getMD5(kjtString.getBytes());
        kjtString=kjtString+"&sign="+passwd;
		out.writeBytes(kjtString);
        out.flush();  
        out.close(); // flush and close  
        BufferedReader reader = new BufferedReader(new InputStreamReader(  
                connection.getInputStream(),"UTF-8"));  
        String line;  
        String billnoSB = "";
        while ((line = reader.readLine()) != null){  
        	billnoSB=billnoSB+line;
        }  
        reader.close();  
        connection.disconnect();
        System.out.println(billnoSB); 
        return billnoSB;
    }  
		
}
