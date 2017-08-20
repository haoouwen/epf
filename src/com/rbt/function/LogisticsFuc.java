package com.rbt.function;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.qq.connect.utils.json.JSONException;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;

public class LogisticsFuc  extends CreateSpringContext{
	
	// 快递KEY 身份授权Key
	private static String kuai_key = SysconfigFuc.getSysValue("cfg_smode_id");
	// 快递信息展示形式
	// 返回类型。0：返回json字符串，1：返回xml对象，2：返回html对象，3：返回text文本。如果不填，默认返回json字符串
	private static String kuai_show = SysconfigFuc.getSysValue("cfg_smode_show");
	// 查询快递的电话号码，目前只有佳吉物流需要这个参数，其他公司请忽略
	private static String kuai_valicode = SysconfigFuc.getSysValue("cfg_smode_valicode");
	// 返回信息数量，1:返回多行完整的信息，0:只返回一行信息。不填默认返回多行
	private static String kuai_muti = SysconfigFuc.getSysValue("cfg_smode_muti");
	// 排序。desc：按时间由新到旧排列，asc：按时间由旧到新排列。不填默认返回倒序（大小不敏感）
	private static String kuai_order = SysconfigFuc.getSysValue("cfg_smode_order");
	//接入快递查询易源api的id https://www.showapi.com/api/lookPoint/64
	private static String showapi_appid="3940";
	//接入快递查询易源api的签名 https://www.showapi.com/api/lookPoint/64
	private static String showapi_sign="92f6ac3a440748f59b80f7ea8a34d2bc";
	
	/**
	 * @author : LJQ
	 * @date : May 21, 2014 3:37:53 PM
	 * @Method Description :快递100查询物流的接口HTMLAPI
	 */
	public static String hundredTrace(String kuai_company, String kuai_number){
			String content = "";
			 if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
		        try
		        {
		        		 URL url = new URL("http://www.kuaidi100.com/applyurl?key=" + kuai_key + "&com=" + kuai_company
	                             + "&nu=" + kuai_number);
				           URLConnection con = url.openConnection();
				           con.setAllowUserInteraction(false);
				           InputStream urlStream = url.openStream();
				           byte b[] = new byte[10000];
				           int numRead = urlStream.read(b);
				           content = new String(b, 0, numRead);
				           while (numRead != -1)
				           {
				               numRead = urlStream.read(b);
				               if (numRead != -1)
				               {
				                   String newContent = new String(b, 0, numRead, "UTF-8");
				                   content += newContent;
				               }
				           }
				           urlStream.close();
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
			 }
	        return content;
	}
	 /**
		 * @author : LJQ
		 * @date : May 21, 2014 3:37:53 PM
		 * @Method Description :快递100查询物流的接口
		 */
		public static String hundredTraceAPI(String kuai_company, String kuai_number){
			
			String result = null;
		    if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
			
				String url = "http://api.kuaidi100.com/api?" + "id=" + kuai_key + "&com=" + kuai_company + "&nu=" + kuai_number + "&show=" + kuai_show + "&muti=" + kuai_muti + "&order=" + kuai_order;
				
				try {
					HttpClient httpClient = new HttpClient();
					GetMethod getMethod = new GetMethod(url);
					httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
					getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
					int statusCode = httpClient.executeMethod(getMethod);
					if (statusCode == 200) {
						StringBuffer temp = new StringBuffer();
						InputStream in = getMethod.getResponseBodyAsStream();
						BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "UTF-8"));
						for (String tempstr = ""; (tempstr = buffer.readLine()) != null;)
							temp = temp.append(tempstr);
						buffer.close();
						in.close();
						result = temp.toString().trim();
					} else {
						System.err.println((new StringBuilder("Can't get page:")).append(url).append("#").append(getMethod.getStatusLine()).toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
			return result;
		}
		 /**
		 * @author : LJQ
		 * @date : May 21, 2014 3:37:53 PM
		 * @Method Description :快递100查询物流的接口,带返回数据类型的	
		 * 返回类型： 
		 * 	0：返回json字符串， 
		 * 	1：返回xml对象， 
		 * 	2：返回html对象， 
		 * 	3：返回text文本。 
		 * 	如果不填，默认返回json字符串。
		 */
		public static String hundredTraceAPI(String kuai_company, String kuai_number,String show){
			String result = null;
			if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
				String url = "http://api.kuaidi100.com/api?id=" + kuai_key + "&com=" + kuai_company + "&nu=" + kuai_number + "&show=" + show + "&muti=" + kuai_muti + "&order=" + kuai_order;
				try {
					HttpClient httpClient = new HttpClient();
					GetMethod getMethod = new GetMethod(url);
					httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
					getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
					int statusCode = httpClient.executeMethod(getMethod);
					if (statusCode == 200) {
						StringBuffer temp = new StringBuffer();
						InputStream in = getMethod.getResponseBodyAsStream();
						BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "UTF-8"));
						for (String tempstr = ""; (tempstr = buffer.readLine()) != null;)
							temp = temp.append(tempstr);
						buffer.close();
						in.close();
						result = temp.toString().trim();
					} else {
						System.err.println((new StringBuilder("Can't get page:")).append(url).append("#").append(getMethod.getStatusLine()).toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	/**
	 * @author : LJQ
	 * @date : May 22, 2014 1:13:28 PM
	 * @Method Description : 返回
	 */
	public static String hundredTraceReturnList(String kuai_company, String kuai_number){
		String jsonString = hundredTrace(kuai_company,kuai_number);
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsons = jsonObject.getJSONArray("rateList");
		return null;
	}
	/**
	 * @Method Description :调用https://www.showapi.com/api/lookPoint/64 接口查询快递信息
	 * showapi_appid 应用ID 为3940  
	 * showapi_sign 应用签名 92f6ac3a440748f59b80f7ea8a34d2bc
	 * @author: HXK
	 * @date : Jul 22, 2015 11:49:15 AM
	 * @param 
	 * @return return_type
	 */
	public static String showAPITraceReturnList(String kuai_company, String kuai_number) throws IOException{
		
		if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
		    URL u=new URL("http://route.showapi.com/64-19?showapi_appid="+showapi_appid+"&showapi_timestamp="+DateUtil.getOrderNum()+"&com=ems&nu=5161488419703&showapi_sign="+showapi_sign);
	        InputStream in=u.openStream();
	        ByteArrayOutputStream out=new ByteArrayOutputStream();
	        try {
	            byte buf[]=new byte[1024];
	            int read = 0;
	            while ((read = in.read(buf)) > 0) {
	                out.write(buf, 0, read);
	            }
	        }  finally {
	            if (in != null) {
	                in.close();
	            }
	        }
	        byte b[]=out.toByteArray( );
	        String jsonString= new String(b,"utf-8");
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			return jsonObject.toString();
		}else {
			return "";
		}
	}
	//获取物流
	public static String expressState(String kuai_number,String kuai_company) throws IOException, JSONException{
		
		String eprState="";
		String logisticsStr="";
		if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
			if(kuai_number!=null&&!"".equals(kuai_number)&&kuai_company!=null&&!"".equals(kuai_company)){
				logisticsStr=showAPITraceReturnList(kuai_company, kuai_number);
			}
			if(logisticsStr!=null&&!"".equals(logisticsStr)){
				Map jMap=JsonUtil.toMap(logisticsStr);
				if(jMap!=null&&jMap.get("showapi_res_body")!=null){
					Map jMap2=JsonUtil.toMap(jMap.get("showapi_res_body").toString());
					if(jMap2!=null&&jMap2.get("status")!=null){
						eprState=jMap2.get("status").toString();
						System.out.println("===get_express_state="+eprState+"=====");
					}
				}
			}
		}
		return eprState;
	}
	
	//获取物流详细信息
	@SuppressWarnings("unchecked")
	public static String expressInfo(String kuai_number,String kuai_company) throws IOException, JSONException{
		String eprState="";
		String reteprState="";
		String logisticsStr="";
		if(!ValidateUtil.isRequired(kuai_company)&&!ValidateUtil.isRequired(kuai_number)){
			if(kuai_number!=null&&!"".equals(kuai_number)&&kuai_company!=null&&!"".equals(kuai_company)){
				logisticsStr=showAPITraceReturnList(kuai_company, kuai_number);
			}
			if(logisticsStr!=null&&!"".equals(logisticsStr)){
				Map jMap=JsonUtil.toMap(logisticsStr);
				if(jMap!=null&&jMap.get("showapi_res_body")!=null){
					Map jMap2=JsonUtil.toMap(jMap.get("showapi_res_body").toString());
					if(jMap2!=null&&jMap2.get("data")!=null){
						eprState=jMap2.get("data").toString();
						JSONArray array = JSONArray.fromObject(eprState); 
						for(int i = 0; i < array.size(); i++){
							JSONObject jsonObject = array.getJSONObject(i);
							if(jsonObject!=null&&jsonObject.get("context")!=null){
								reteprState+="<tr><td>"+jsonObject.get("context")+"</td><td>"+jsonObject.get("time")+"</td></tr>";
							}
						}
					}
				}
			}
		}
		
		System.out.println(reteprState);
		return reteprState;
	}
	
	
	
	public static void main(String path[]) throws Exception {
		
		//System.out.print(showAPITraceReturnList("ems","5161488419703"));
		expressInfo("ems","5161488419703");
		//http://api.kuaidi100.com/api?id=3d025e139a544ed4&com=ems&nu=&show=1&muti=1&order=desc
		//System.out.println(hundredTraceAPI("ems","5161488419703","1"));
       
   }
	
	
	
	
}
