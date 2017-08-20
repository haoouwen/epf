<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@page import="com.rbt.function.*"%>
<%@page import="com.rbt.model.Payment"%>
<jsp:useBean id="MD5" scope="request" class="beartool.MD5"/>
<%
	PaymentFuc payfunService=new PaymentFuc();
	Payment paymode=new Payment();
	paymode=payfunService.getPayment("onlinepay");	
	//初始化定义参数
	String v_mid,key,v_url,v_oid,v_amount,v_moneytype,v_md5info;  //定义必须传递的参数变量

	v_mid = paymode.getPay_account();		
	String m_url=SysconfigFuc.getSysValue("cfg_basehost");
	if(m_url!=null&&!m_url.equals("")&&!m_url.contains("http://")){
		m_url="http://"+m_url;
	}		                 	      // 商户号，这里为测试商户号20000400，替换为自己的商户号即可
	v_url =m_url+"/mall-pay-receiveIndex.html";     // 商户自定义返回接收支付结果的页面
													      // MD5密钥要跟订单提交页相同，如Send.asp里的 key = "test" ,修改""号内 test 为您的密钥
    key =paymode.getPasswd();;						  // 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn/
													      // 登陆后在上面的导航栏里可能找到“资料管理”，在资料管理的二级导航栏里有“MD5密钥设置” 
													      // 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
//****************************************


		//以上三项需要商户修改
		
    // v_oid=request.getParameter("v_oid");
	  if(request.getParameter("v_oid")!=null && !request.getParameter("v_oid").equals(""))  //判断是否有传递订单号
	  {
		  v_oid=request.getParameter("v_oid");
	  }
		else                         
	  {
		  Date currTime = new Date();
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-"+v_mid+"-hhmmss",Locale.US);
		  v_oid=sf.format(currTime);                        // 推荐订单号构成格式为 年月日-商户号-小时分钟秒
	  }                                  
	    v_amount=request.getParameter("v_amount");				// 订单金额                   
		v_moneytype ="CNY";	                  				// 币种
		v_md5info = "";										// 对拼凑串MD5私钥加密后的值

        String text = v_amount+v_moneytype+v_oid+v_mid+v_url+key;   // 拼凑加密串
		v_md5info = MD5.getMD5ofStr(text);                          // 网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写


		String remark1,remark2;

		remark1 = request.getParameter("remark1");               //备注字段1 实际订单ID串
		remark2 = request.getParameter("remark2");               //remark2:表示“cust_id,user_id,pay_type”  其中 pay_type：1订单支付  2：充值支付
		
%>

<!--以下信息为标准的 HTML 格式 + JAVA 语言 拼凑而成的 网银在线 支付接口标准演示页面 -->

<html>

<body onLoad="javascript:document.E_FORM.submit()">
<form action="https://pay3.chinabank.com.cn/PayGate" method="POST" name="E_FORM">

  <!--以下几项为网上支付重要信息，信息必须正确无误，信息会影响支付进行！-->
    
  <input type="hidden" name="v_md5info"    value="<%=v_md5info%>" size="100">
  <input type="hidden" name="v_mid"        value="<%=v_mid%>">
  <input type="hidden" name="v_oid"        value="<%=v_oid%>">
  <input type="hidden" name="v_amount"     value="<%=v_amount%>">
  <input type="hidden" name="v_moneytype"  value="<%=v_moneytype%>">
  <input type="hidden" name="v_url"        value="<%=v_url%>">

    
  <!--以下几项项为网上支付完成后，随支付反馈信息一同传给信息接收页，在传输过程中内容不会改变,如：Receive.asp -->
    
  <input type="hidden"  name="remark1" value="<%=remark1%>">
  <input type="hidden"  name="remark2" value="<%=remark2%>">
  
  </form>

</body>
</html>
