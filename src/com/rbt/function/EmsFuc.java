package com.rbt.function;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.rbt.common.BASE64;
import com.rbt.model.Goodsorder;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  EMS获取单号寄打印订单
 * @author  创建人  HXK	
 * @date  创建日期  2014-12-13
 */

public class EmsFuc{
	
	
	 /**
	 * @Method Description :http发送请求 测试
	 * @author: QJY
	 * @date : Dec 13, 2014 1:12:28 PM
	 * @param 
	 * @return return_type
	 */
	public static String getBillnos(String businessType,String billNoAmount) throws Exception {  
		StringBuffer billnoSB = new StringBuffer();//把String拼串换成StringBuffer 大部分情况下 在大部分情况下 StringBuffer > String，1，线程安全，二，速度快
		String soapAction = "http://os.ems.com.cn:8081/zkweb/bigaccount/getBigAccountDataAction.do";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml+="<XMLInfo>";
		xml+="<sysAccount>35012800006064</sysAccount>";//大客户号，必填，代码调试期间请务必传入大客户号：测试-A1234567890Z 正式-35012800006064
		xml+="<passWord>e10adc3949ba59abbe56e057f20f883e</passWord>";//大客户密码，必填，密码明文32位md5加密后转小写，e10adc3949ba59abbe56e057f20f883e（123456）
		xml+="<appKey></appKey>";//对接方平台id，作为接口调用方的身份凭据，由接口提供方提供
		xml+="<businessType>"+businessType+"</businessType>";//业务类型，必填，1为标准快递，4为经济快递（传数字）
		xml+="<billNoAmount>"+billNoAmount+"</billNoAmount>";//需要详情单数量，1-100之间最多输入100
		xml+="</XMLInfo>";
		//通过BASE64加密
		xml=BASE64.encryptBASE64(xml.getBytes());
		xml = URLEncoder.encode(xml, "UTF-8");//如果是直接把xml拼接到接口地址后面的情况，需要对加密字符串再进行URL地址转码
        URL url = new URL(soapAction);  
        URLConnection connection = url.openConnection();  
        connection.setDoOutput(true);  
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");        
        out.write("method=getBillNumBySys&xml="+xml); //向页面传递数据。post的关键所在！ 
        out.flush(); //刷新输出流缓冲区 
        out.close();  
        // 一旦发送成功，用以下方法就可以得到服务器的回应：  
        String sCurrentLine;  
        String sTotalString;  
        sCurrentLine = "";  
        sTotalString = "";  
        InputStream l_urlStream;  
        l_urlStream = connection.getInputStream();
        /**传说中的三层包装阿！ */ 
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream,"utf-8"));  
        while ((sCurrentLine = l_reader.readLine()) != null) {  
            sTotalString += sCurrentLine;  
        }
        System.out.println("解密前============="+sTotalString);
        sTotalString = BASE64.decryptBase64String(sTotalString);//解密返回来的xml数据
        System.out.println("解密后============="+sTotalString);
        //需判断返回的是成功的参数或者失败的参数:成功存在<assignIds>标签，失败则不存在
        if(sTotalString.indexOf("<assignIds>")>-1){
        	 //去掉中文字符，总是会报错
            sTotalString=sTotalString.replace(sTotalString.substring(sTotalString.indexOf("<errorDesc>"),sTotalString.indexOf("<assignIds>")),"");
        }else{
        	//去掉中文字符，总是会报错
            sTotalString=sTotalString.replace(sTotalString.substring(sTotalString.indexOf("<errorDesc>"),sTotalString.indexOf("/response")),"");
        }
       
        //包装成输入流InputStream
        InputStream   in_withcode   =   new   ByteArrayInputStream(sTotalString.getBytes("UTF-8"));  
        //通过PULL解析器取得EMS运单号：1：标准快递运单号  4：经济快递运单号
        String Billnos = dealXMLForBillnos(in_withcode);
        return Billnos;
    }  
	
	/**@author Administrator QJY
	 * @Method Description :通过获取返回的单号xml文档解析出来单号
	 *  xml解析常用的三种解析方式：DOM，SAX，PULL，PULL解析类似SAX，但是其性能和效率方面，优化较好，在Android系统中开发APP，常用到PULL解析                  
	 ** 获取数据
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String dealXMLForBillnos(InputStream xml) throws Exception {
		StringBuffer sb = null;
		//利用PULL解析器工厂获取pull解析器实例
		XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
		pullParser.setInput(xml, "UTF-8");// 为Pull解析器设置要解析的XML数据
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {//开始文档事件
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				sb = new StringBuffer();
				break;

			case XmlPullParser.START_TAG:
				if("billno".equals(pullParser.getName())){
					String billno = pullParser.nextText();
					sb.append(billno);
					sb.append(",");
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = pullParser.next();
		}
		return sb.toString();
	}	
    
    /**
	 * @Method Description :2.2 详情单打印信息更新到EMS自助服务系统接口
	 * @author: HXK
	 * @date : Dec 13, 2014 3:48:40 PM
	 * @param 
	 * @return    
     * @throws Exception 
	 */
    public static String uploadDataToEMS(Goodsorder prt_goodsorder,Shopconfig prt_Shopconfig,String businessType) throws Exception{
		String str_shopname = "", str_shopaddress="",str_contacts="", str_mobile = "", str_phone = "",str_postcode="",
		str_order_id="",str_order_num = "",str_send_consignee = "", str_send_telphone = "", str_send_mobile = "", str_send_address = "", 
		str_Province="",str_City="",str_County="",str_send_zipcode = "",str_total_amount = "",str_order_weight="";
        
		// 店铺信息
		if (prt_Shopconfig != null) {
			// 获取店铺名称
			if (prt_Shopconfig.getShop_name() != null) {
				str_shopname = prt_Shopconfig.getShop_name();
			}
			// 获取店铺经营地址
			if (prt_Shopconfig.getAddress() != null) {
				str_shopaddress = AreaFuc.getAreaNameByMap(prt_Shopconfig.getArea_attr());
				str_shopaddress = str_shopaddress + prt_Shopconfig.getAddress().toString();
			}
			//获取店铺联系人的姓名
			if(prt_Shopconfig.getContant_man() != null){
				str_contacts = prt_Shopconfig.getContant_man();
			}
			// 获取店铺联系人的手机号码
			if (prt_Shopconfig.getMobile() != null) {
				str_mobile = prt_Shopconfig.getMobile();
			}
			//获取店铺联系人的邮政编码
			if (prt_Shopconfig.getPostcode() != null) {
				str_postcode = prt_Shopconfig.getPostcode();
			}
			// 获取店铺联系电话
			if (prt_Shopconfig.getPhone() != null) {
				str_phone = prt_Shopconfig.getPhone();
			}
		}
		
    	// 订单信息
		if (prt_goodsorder != null) {
			// 获取订单号
			if (prt_goodsorder.getOrder_id() != null) {
				str_order_id = prt_goodsorder.getOrder_id();
				//截取后13位订单
				//str_order_id = str_order_id.substring(11, str_order_id.length()-1);
			}

			//获取从EMS请求过来的运单号
			if(prt_goodsorder.getSend_num() != null){
				str_order_num = prt_goodsorder.getSend_num().toString();
			}
			
			// 订单总金额
			if (prt_goodsorder.getTatal_amount() != null) {
				str_total_amount = prt_goodsorder.getTatal_amount()
						.toString();
			}

			// 订单总金额
			if (prt_goodsorder.getOrder_weight() != null) {
				str_order_weight = prt_goodsorder.getOrder_weight()
						.toString();
			}
			 
			// 收货人姓名
			if (prt_goodsorder.getConsignee() != null) {
				str_send_consignee = prt_goodsorder.getConsignee().toString();
			}
			// 收货人电话
			if (prt_goodsorder.getTelephone() != null) {
				str_send_telphone = prt_goodsorder.getTelephone().toString();
			}
			// 收货人手机
			if (prt_goodsorder.getMobile() != null) {
				str_send_mobile = prt_goodsorder.getMobile().toString();
			}
			// 收货人的地区：省 - 市 - 区（县）
			if (prt_goodsorder.getArea_attr() != null) {
				String area_name = AreaFuc.getAreaNameByMap(prt_goodsorder
						.getArea_attr());
				if (area_name != null && area_name != "") {
					String[] area_name_str = area_name.split(",");
					if(area_name_str.length==1){
						str_Province = area_name_str[0];
						str_City = "";
						str_County = "";
					}else if(area_name_str.length==2){
						str_Province = area_name_str[0];
						str_City = area_name_str[1];
						str_County = "";
					}else if(area_name_str.length==3){
						str_Province = area_name_str[0];
						str_City = area_name_str[1];
						str_County = area_name_str[2];
					}
				}
			}
			// 收货人地址
			if (prt_goodsorder.getBuy_address() != null) {
				str_send_address = prt_goodsorder.getBuy_address().toString();
			}
			// 收件人邮编
			if (prt_goodsorder.getZip_code() != null) {
				str_send_zipcode = prt_goodsorder.getZip_code().toString();
			}

		}

    	String xml="";
    	String soapAction = "http://os.ems.com.cn:8081/zkweb/bigaccount/getBigAccountDataAction.do";
 		xml= "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
 		xml+="<XMLInfo>";
 		xml+="<sysAccount>35012800006064</sysAccount>";//大客户号，必填，代码调试期间请务必传入大客户号：测试-A1234567890Z 正式-35012800006064
 		xml+="<passWord>e10adc3949ba59abbe56e057f20f883e</passWord>";//大客户密码，必填，密码明文32位md5加密后转小写，e10adc3949ba59abbe56e057f20f883e（123456）
 		xml+="<appKey>1</appKey>";//对接方平台id，作为接口调用方的身份凭据，由接口提供方提供
 		xml+="<printKind>2</printKind>";//打印类型，1为五联单打印，2为热敏打印，必填
 		xml+="<printDatas>";
 			xml+="<printData>";
 				xml+="<bigAccountDataId>"+str_order_id+"</bigAccountDataId>";//大客户数据的唯一标识，如某电商公司的配货单号，必填(订单如果打印条码,应为非13位数字)
 				xml+="<billno>"+str_order_num+"</billno>";//详情单号，和配货单号对应，必填（详情单号打印条形码必须128码）
 				xml+="<scontactor>"+str_contacts+"</scontactor>";//寄件人姓名
 				xml+="<scustMobile>"+str_mobile+"</scustMobile>";//寄件人联系方式1
 				xml+="<scustTelplus>1</scustTelplus>";//寄件人联系方式2(选填)
 				xml+="<scustPost>"+str_postcode+"</scustPost >";//寄件人邮编
 				xml+="<scustAddr>"+str_shopaddress+"</scustAddr>";//寄件人地址
 				xml+="<scustComp>1</scustComp>";//寄件人公司
 				xml+="<tcontactor>"+str_send_consignee+"</tcontactor>";//收件人姓名
 				xml+="<tcustMobile>"+str_send_mobile+"</tcustMobile>";//收件人联系方式1
 				xml+="<tcustTelplus>1</tcustTelplus>";//收件人联系方式2(选填)
 				xml+="<tcustPost>"+str_send_zipcode+"</tcustPost >";//收件人邮编
 				xml+="<tcustAddr>"+str_send_address+"</tcustAddr>";//收件人地址
 				xml+="<tcustComp>1</tcustComp>";//收件人公司
 				xml+="<tcustProvince>"+str_Province+"</tcustProvince>";//到件省
 				xml+="<tcustCity>"+str_City+"</tcustCity>";//到件市
 				xml+="<tcustCounty>"+str_County+"</tcustCounty>";//到件县
 				xml+="<weight>"+str_order_weight+"</weight>";//寄件重量
 				xml+="<length>1</length>";//物品长度
 				xml+="<insure>1</insure>";//保价，每件最高投保金额以人民币5万元为限
 				xml+="<insurance>1</insurance>";//保险
 				xml+="<fee>1</fee>";//小写金额，代收货款和收件人付费不保留小数点；标准快递和经济快递保留两位小数点
 				xml+="<feeUppercase>1</feeUppercase>";//大写金额（代收货款和收件人付费需要填写）
 				xml+="<businessType>"+businessType+"</businessType>";//业务类型，1为标准快递，2为代收货款，3为收件人付费，4为经济快递（传数字）
 				xml+="<cargoDesc>1</cargoDesc>"; //内件信息，根据货品的实际情况填写（对个别已与EMS和买家达成协议的，可只写货号，不写实际货物名称）
 				xml+="<cargoType>1</cargoType>";//内件类型：（文件、物品）
 				xml+="<remark>1</remark>";//备注
 				xml+="<deliveryclaim>1</deliveryclaim>";//对揽投员的投递要求，填写客户的个性化投递要求
 				xml+="<productCode>1</productCode>";//产品代码
 				xml+="<blank1>1</blank1>";//预留字段1
 				xml+="<blank2>1</blank2>";//预留字段2
 				xml+="<blank3>1</blank3>";//预留字段3
 				xml+="<blank4>1</blank4>";//预留字段4
 				xml+="<blank5>1</blank5>";//预留字段5
			xml+="</printData>";
		xml+="</printDatas>";
	xml+="</XMLInfo>";
	//xml = URLEncoder.encode(xml, "UTF-8");
 		 System.out.println("doPost加密前:"+xml.trim());
 		 //Base64加密
 		
 		 xml=BASE64.encodeBufferBase64(xml.getBytes());
 		 xml = URLEncoder.encode(xml, "UTF-8");
 		 String strs=new String(BASE64.decodeBufferBase64(xml));   
 		 
 		 System.out.println("doPost解密后:"+strs);
         URL localURL = new URL(soapAction);
         URLConnection connection = localURL.openConnection();
         HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
         
         URL url = new URL(soapAction);   
         connection.setDoOutput(true);  
         OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");        
         out.write("method=updatePrintDatas&xml="+xml); //向页面传递数据。post的关键所在！ 
         out.flush(); //刷新输出流缓冲区 
         out.close();  
         // 一旦发送成功，用以下方法就可以得到服务器的回应：  
         String sCurrentLine;  
         String sTotalString;  
         sCurrentLine = "";  
         sTotalString = "";  
         InputStream l_urlStream;  
         l_urlStream = connection.getInputStream();
         
         BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream,"utf-8"));  
         while ((sCurrentLine = l_reader.readLine()) != null) {  
             sTotalString += sCurrentLine;  
         }
         
         System.out.println("doPost请求后加密数据："+sTotalString);
         System.out.println("doPost请求后解密数据："+ BASE64.decryptBase64String(sTotalString));
         sTotalString = BASE64.decryptBase64String(sTotalString);
       //去掉中文字符，总是会报错
         sTotalString=sTotalString.replace(sTotalString.substring(sTotalString.indexOf("<errorDesc>"),sTotalString.indexOf("</response>")),"");
         System.out.println("去掉中文字符串后："+sTotalString);
       //包装成输入流InputStream
         InputStream   in_withcode   =   new   ByteArrayInputStream(sTotalString.getBytes("UTF-8")); 
         String result = dealXMLForResult(in_withcode);
         return result;
    	
    }
    
    /**@author Administrator QJY
	 * @Method Description :通过获取返回的单号xml文档解析出来单号
	 *  xml解析常用的三种解析方式：DOM，SAX，PULL，PULL解析类似SAX，但是其性能和效率方面，优化较好，在Android系统中开发APP，常用到PULL解析                  
	 ** 获取数据
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String dealXMLForResult(InputStream xml) throws Exception {
		StringBuffer sb = null;
		//利用PULL解析器工厂获取pull解析器实例
		XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
		pullParser.setInput(xml, "UTF-8");// 为Pull解析器设置要解析的XML数据
		int event = pullParser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {//开始文档事件
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				sb = new StringBuffer();
				break;

			case XmlPullParser.START_TAG:
				if("result".equals(pullParser.getName())){
					String result = pullParser.nextText();
					sb.append(result);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			event = pullParser.next();
		}
		System.out.println("result======================="+sb.toString());
		return sb.toString();
	}
     
	
}
