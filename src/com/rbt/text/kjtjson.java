package com.rbt.text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.rbt.common.Md5;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Kjtrecall;
import com.rbt.service.IKjtrecallService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class kjtjson  extends CreateSpringContext{
        
		public static void main(String args[]) throws Exception { 
		
			//kjtjson ks = new kjtjson();
			//ks.orderEndpoint();\
			//String sss = ks.JsonDate("http://192.168.10.108/serviceTest.html","a=99999");
			/**
			 Map<String,Object> prumap = new HashMap<String,Object>();
		     ArrayList orderList = new ArrayList(); //组装id 列表
			 String SOSysNo="10011313";
			 orderList.add(SOSysNo);
			 prumap.put("OrderIds", orderList);
			 prumap.put("SaleChannelSysNo", "1087");
			 prumap.put("OrderIds", orderList);
				
				
			JSONObject jsObj = JSONObject.fromObject(prumap);  
			HttpSenderKtj client=new HttpSenderKtj();
			String datajson = jsObj.toString();
			JsonUtil jsonUtil=new JsonUtil();
			String jsonString=client.readContentFromPost(jsObj+"","Invoice.FEPBillPost");//获取返回数据
			String dateString=(String) jsonUtil.toMap(jsonString).get("Data");
			String PurchasingTotalAmount=(String) jsonUtil.toMap(dateString).get("PurchasingTotalAmount");
			
			System.out.println(PurchasingTotalAmount); 
			**/
			IKjtrecallService kjtrecallService = (IKjtrecallService) getContext().getBean("kjtrecallService");
			Kjtrecall kjtrecall = new Kjtrecall();
			HashMap  kjtrecallmap = new HashMap();
			kjtrecallmap.put("purchasing", "ceshi");
			kjtrecallmap.put("sosysno", "10011313");
			kjtrecallService.updatekjtpur(kjtrecallmap);
			
		}
		
		public String orderrecall(){
			//返回的json数据
			String recallstr = "{\"Data\":{\"TraceOrderList\":[{\"SOID\":10011110,\"MerchantOrderId\":\"2015090818112527856218813\",\"SOStatus\":1,\"Logs\":[{\"OptTime\":\"2015/9/18 16:01:51\",\"OptType\":-90,\"OptNote\":\"后台订单锁定\"}]}," +
					"{\"SOID\":10011111,\"MerchantOrderId\":\"2015090818112527856218814\",\"SOStatus\":1,\"Logs\":[]}," +
							"{\"SOID\":10011114,\"MerchantOrderId\":\"2015090818112527856218815\",\"SOStatus\":1,\"Logs\":[]}," +
									"{\"SOID\":10011123,\"MerchantOrderId\":\"2015090809492587334663436\",\"SOStatus\":1,\"Logs\":[]}]},\"Code\":\"0\",\"Desc\":\"SUCCESS\"}";
			
			//转发为json对象
			JSONObject jsondata = JSONObject.fromObject(recallstr);  
			//获取Data对象
			JSONObject  response=jsondata.getJSONObject("Data");
			//获取Data对象中的数组
			String   TraceOrderList=response.getString("TraceOrderList");
			//字符转换为json
			JSONArray jsonArr = JSONArray.fromObject(TraceOrderList);
			if(jsonArr!=null&&jsonArr.size()>0){
					for(int i=0;i<jsonArr.size();i++){
					//挨个获取数组对象
					JSONObject info=jsonArr.getJSONObject(i);
					String SOID=info.getString("SOID");
					String MerchantOrderId=info.getString("MerchantOrderId");
					String SOStatus=info.getString("SOStatus");
					System.out.println("SOID="+SOID+" MerchantOrderId="+MerchantOrderId+" SOStatus=" +SOStatus );
					}
			}
			System.out.println(jsonArr.size());
			String Code = jsondata.getString("Code");
			String Desc = jsondata.getString("Desc");
			System.out.println("Code="+Code+" Desc="+Desc);
		return "";		
		}
		
		
		public String orderEndpoint() throws Exception{
			//{"OrderIds":["10011114"],"SalesChannelSysNo":1087}
			
			Map<String,Object> map = new HashMap<String,Object>();
			ArrayList orderList = new ArrayList(); 
			orderList.add("10011114");
			map.put("OrderIds", orderList);
			
			map.put("SalesChannelSysNo", "1087");
			
			JSONObject jsObj = JSONObject.fromObject(map);  
			String datajson = jsObj.toString();
			String   mydata   =   java.net.URLEncoder.encode(datajson,"utf-8"); 
			
			
			String signdata =	"appid=seller313&data="+mydata +"&format=json&method=order.sotrace&nonce=2743&timestamp=20150917102141&version=1.0&kjt@313";
			//MD5加密生成签名
			String sign = md5code(signdata);
			String param =	signdata + "&sign="+sign;
			String url = "http://api.kjt.com/open.api"; 
			String recallstr = JsonDate(url,param);
			System.out.println(recallstr);
			return "";
		}		
		
		public String JsonDate(String url,String param) throws Exception {
			StringBuilder json = new StringBuilder();  
	        try {  
	            URL urlObject = new URL(url);  
	            URLConnection uc = urlObject.openConnection(); 
	            uc.setDoOutput(true);  
	            uc.setDoInput(true);  
	            PrintWriter out = null;  
	            // 获取URLConnection对象对应的输出流  
	            out = new PrintWriter(uc.getOutputStream());  
	            // 发送请求参数  
	            out.print(param);  
	            // flush输出流的缓冲  
	            out.flush();  
	            
	            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  
	            String inputLine = null;  
	            while ( (inputLine = in.readLine()) != null) {  
	                json.append(inputLine);  
	            }  
	            in.close();  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
	        return json.toString();
		}
		
		public String md5code(String mydata){
			return Md5.getMD5(mydata.getBytes());
		}
		//提交跨境通解析json
		public void submitkjt(){
			
           //JSONArray json = JSONArray.fromObject(map);
			
			//appadd("http://preapi.kjt.com/open.api"); 
             //System.out.println(x); 
			//System.out.println(json);
			String recallstr ="{\"Data\":{\"SOSysNo\":10011107,\"MerchantOrderID\":\"2015090818112527856218810\",\"ProductAmount\":0.01,\"ShippingAmount\":15.00,\"TaxAmount\":0},\"Code\":\"0\",\"Desc\":\"SUCCESS\"}";
			//返回值转化为json
			JSONObject jsondata = JSONObject.fromObject(recallstr);  
			JSONObject  Data=jsondata.getJSONObject("Data");
			//获取状态值
			String Code = jsondata.getString("Code");
			String Desc = jsondata.getString("Desc");
			//获取kjt系统订单号
			String SOSysNo="";
			//获取商家订单号
			String MerchantOrderID="";
			//获取商品总金额
			String ProductAmount="";
			//kjt计算的运费总金额
			String TaxAmount="";
			if("0".equals(Code)&&"SUCCESS".equals(Desc)){
				SOSysNo=Data.getString("SOSysNo");
				MerchantOrderID=Data.getString("MerchantOrderID");
				ProductAmount = Data.getString("ProductAmount");
				TaxAmount = Data.getString("TaxAmount");
			}
			System.out.println(SOSysNo);
				System.out.println(MerchantOrderID);
				System.out.println(ProductAmount);
				System.out.println(TaxAmount);
		}
		
		
		public Map jsonmap(){
			//分销渠道订单创建接口--data转换为json
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("SaleChannelSysNo", 1087);                               //渠道编号
			map.put("MerchantOrderID", 4428);                                //订单编号kjt平台创建一个订单
			map.put("WarehouseID", 51);                                           //订单出库编号,仓库编号
			
			//订单支付信息
			Map PayInfo = new HashMap();
			PayInfo.put("ProductAmount", 300.00);         //商品总金额（保留2位小数）
			PayInfo.put("ShippingAmount", 10.10);        //运费总金额（保留2位小数）
			PayInfo.put("TaxAmount", 5.10);                 //商品行邮税总金额（保留2位小数）
			PayInfo.put("CommissionAmount", 10.10);   //下单支付产生的手续费（保留2位小数）
			PayInfo.put("PayTypeSysNo", "112");            //支付方式111: 东方 支付 112: 支付宝 11 4: :财付通 117:银联支付 118 : 微信支付
			PayInfo.put("PaySerialNumber", "545877458");        //支付流水号
			map.put("PayInfo", PayInfo);
			//订单配送信息
			Map ShippingInfo = new HashMap();
			ShippingInfo.put("ReceiveName", "小蔡");               //收件人姓名
			ShippingInfo.put("ReceivePhone", "010-51388148");               //收件人电话
			ShippingInfo.put("ReceiveAddress", "青年家园156幢15室");             //收件人收货地址
			ShippingInfo.put("ReceiveAreaCode", "101100");          //收货地区编号
			ShippingInfo.put("ShipTypeID", 1);                        //物流公司编号 kjt提供
			ShippingInfo.put("ReceiveAreaName", "北京，北京市，通州区");          //收件省市区名称
			map.put("ShippingInfo", ShippingInfo);
			//下单用户实名认证信息
			Map AuthenticationInfo = new HashMap();
			AuthenticationInfo.put("Name", "CYC");                    //下单用户真实姓名
			AuthenticationInfo.put("IDCardType", 0);           //下单用户证件类型
			AuthenticationInfo.put("IDCardNumber", "350626198512240511");     //下单用户名证件编码
			AuthenticationInfo.put("PhoneNumber", "18859211953");      //下单用户联系电话
			AuthenticationInfo.put("Email", "812606068@qq.com");                     //下单用户电子邮件
			AuthenticationInfo.put("Address", "北京通州青年家园156幢15室");                  //下单用户联系地址
			map.put("AuthenticationInfo", AuthenticationInfo);
			//订单中购买商品列表
			Map[] ItemList = new Map[1]; 
			ItemList[0] = new HashMap(); 
			ItemList[0] .put("ProductID", "313BEA01ew30001");        //商品ID
			ItemList[0] .put("Quantity", 1);                           //购买数量
			ItemList[0] .put("SalePrice", 300.00);                    //单品金额（保留2位小数）
			ItemList[0] .put("TaxPrice", 200.00);                     //商品在分销渠道下单的单品税费（保留2位小数）
			map.put("ItemList", ItemList);
			
			return map;
			
		}
	}
	

