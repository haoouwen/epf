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
	//��ʼ���������
	String v_mid,key,v_url,v_oid,v_amount,v_moneytype,v_md5info;  //������봫�ݵĲ�������

	v_mid = paymode.getPay_account();		
	String m_url=SysconfigFuc.getSysValue("cfg_basehost");
	if(m_url!=null&&!m_url.equals("")&&!m_url.contains("http://")){
		m_url="http://"+m_url;
	}		                 	      // �̻��ţ�����Ϊ�����̻���20000400���滻Ϊ�Լ����̻��ż���
	v_url =m_url+"/mall-pay-receiveIndex.html";     // �̻��Զ��巵�ؽ���֧�������ҳ��
													      // MD5��ԿҪ�������ύҳ��ͬ����Send.asp��� key = "test" ,�޸�""���� test Ϊ������Կ
    key =paymode.getPasswd();;						  // �������û������MD5��Կ���½����Ϊ���ṩ�̻���̨����ַ��https://merchant3.chinabank.com.cn/
													      // ��½��������ĵ�����������ҵ������Ϲ����������Ϲ���Ķ������������С�MD5��Կ���á� 
													      // ����������һ��16λ���ϵ���Կ����ߣ���Կ���64λ��������16λ�Ѿ��㹻��
//****************************************


		//����������Ҫ�̻��޸�
		
    // v_oid=request.getParameter("v_oid");
	  if(request.getParameter("v_oid")!=null && !request.getParameter("v_oid").equals(""))  //�ж��Ƿ��д��ݶ�����
	  {
		  v_oid=request.getParameter("v_oid");
	  }
		else                         
	  {
		  Date currTime = new Date();
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-"+v_mid+"-hhmmss",Locale.US);
		  v_oid=sf.format(currTime);                        // �Ƽ������Ź��ɸ�ʽΪ ������-�̻���-Сʱ������
	  }                                  
	    v_amount=request.getParameter("v_amount");				// �������                   
		v_moneytype ="CNY";	                  				// ����
		v_md5info = "";										// ��ƴ�մ�MD5˽Կ���ܺ��ֵ

        String text = v_amount+v_moneytype+v_oid+v_mid+v_url+key;   // ƴ�ռ��ܴ�
		v_md5info = MD5.getMD5ofStr(text);                          // ����֧��ƽ̨��MD5ֵֻ�ϴ�д�ַ���������Сд��MD5ֵ��ת��Ϊ��д


		String remark1,remark2;

		remark1 = request.getParameter("remark1");               //��ע�ֶ�1 ʵ�ʶ���ID��
		remark2 = request.getParameter("remark2");               //remark2:��ʾ��cust_id,user_id,pay_type��  ���� pay_type��1����֧��  2����ֵ֧��
		
%>

<!--������ϢΪ��׼�� HTML ��ʽ + JAVA ���� ƴ�ն��ɵ� �������� ֧���ӿڱ�׼��ʾҳ�� -->

<html>

<body onLoad="javascript:document.E_FORM.submit()">
<form action="https://pay3.chinabank.com.cn/PayGate" method="POST" name="E_FORM">

  <!--���¼���Ϊ����֧����Ҫ��Ϣ����Ϣ������ȷ������Ϣ��Ӱ��֧�����У�-->
    
  <input type="hidden" name="v_md5info"    value="<%=v_md5info%>" size="100">
  <input type="hidden" name="v_mid"        value="<%=v_mid%>">
  <input type="hidden" name="v_oid"        value="<%=v_oid%>">
  <input type="hidden" name="v_amount"     value="<%=v_amount%>">
  <input type="hidden" name="v_moneytype"  value="<%=v_moneytype%>">
  <input type="hidden" name="v_url"        value="<%=v_url%>">

    
  <!--���¼�����Ϊ����֧����ɺ���֧��������Ϣһͬ������Ϣ����ҳ���ڴ�����������ݲ���ı�,�磺Receive.asp -->
    
  <input type="hidden"  name="remark1" value="<%=remark1%>">
  <input type="hidden"  name="remark2" value="<%=remark2%>">
  
  </form>

</body>
</html>
