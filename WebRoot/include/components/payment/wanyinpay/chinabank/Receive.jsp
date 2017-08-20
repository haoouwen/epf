<%@page contentType="text/html; charset=gb2312" language="java"%>
<%@page import="com.rbt.function.*"%>
<%@page import="com.rbt.model.Payment"%>
<jsp:useBean id="MD5" scope="request" class="beartool.MD5"/>
<%
//****************************************	// MD5密钥要跟订单提交页相同，如Send.asp里的 key = "test" ,修改""号内 test 为您的密钥
											// 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn
PaymentFuc payfunService=new PaymentFuc();
Payment paymode=new Payment();
paymode=payfunService.getPayment("onlinepay");
String key =paymode.getPasswd();						// 登陆后在上面的导航栏里可能找到“资料管理”，在资料管理的二级导航栏里有“MD5密钥设置” 
											// 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
//****************************************

//获取参数
	   String v_oid = request.getParameter("v_oid");		// 订单号
	 String v_pmode = request.getParameter("v_pmode");		// 支付方式中文说明，如"中行长城信用卡"
   String v_pstatus = request.getParameter("v_pstatus");	// 支付结果，20支付完成；30支付失败；
   String v_pstring = request.getParameter("v_pstring");	// 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
	String v_amount = request.getParameter("v_amount");		// 订单实际支付金额
  String v_moneytype = request.getParameter("v_moneytype");	// 币种
	String v_md5str = request.getParameter("v_md5str");		// MD5校验码
	 String remark1 = request.getParameter("remark1");		// 实际订单ID串
	 String remark2 = request.getParameter("remark2");		//remark2:表示“cust_id,user_id,pay_type”  其中 pay_type：1订单支付  2：充值支付
%>
<%

String text = v_oid+v_pstatus+v_amount+v_moneytype+key;
String v_md5 = MD5.getMD5ofStr(text).toUpperCase();

if (v_md5str.equals(v_md5))
{
	if ("30".equals(v_pstatus))
	{
		out.print("支付失败");
	}else if ("20".equals(v_pstatus)){
		// 支付成功，商户 根据自己业务做相应逻辑处理
		//此处加入商户系统的逻辑处理（例如判断金额，判断支付状态，更新订单状态等等）......
		//remark2:表示“cust_id,user_id,pay_type”  其中 pay_type：1订单支付  2：充值支付
		
		String remark2s[]=remark2.split(",");
		String pay_type=remark2s[2].toString();
		if(v_oid!=null&&!v_oid.equals("")&&v_amount!=null&&!v_amount.equals("")){
			if(pay_type!=null&&!pay_type.equals("")){
			    if(pay_type.equals("1")){
				    if(remark1!=null&&!remark1.equals("")){
				       GoodsOrderFuc weborder=new GoodsOrderFuc();
				       //weborder.useOnlinePay(remark1,"5",remark2);
					}	
			    }else if(pay_type.equals("2")){
			       FundrechargeFuc fcharge=new FundrechargeFuc();
			       fcharge.insertChongZhi("onlinepay",v_amount,v_oid,"",remark2);
			      
			    }
			}
		}
	}
}else{
    out.print("校验码未通过，不是银行传递回来的参数");
}
%>