<%@page contentType="text/html; charset=gb2312" language="java"%>
<%@page import="com.rbt.function.*"%>
<%@page import="com.rbt.model.Payment"%>
<jsp:useBean id="MD5" scope="request" class="beartool.MD5"/>
<%

//****************************************	// MD5��ԿҪ�������ύҳ��ͬ����Send.asp��� key = "test" ,�޸�""���� test Ϊ������Կ
											// �������û������MD5��Կ���½����Ϊ���ṩ�̻���̨����ַ��https://merchant3.chinabank.com.cn/
PaymentFuc payfunService=new PaymentFuc();
Payment paymode=new Payment();
paymode=payfunService.getPayment("onlinepay");	
String key =paymode.getPasswd();					// ��½��������ĵ�����������ҵ������Ϲ����������Ϲ���Ķ������������С�MD5��Կ���á� 
											// ����������һ��16λ���ϵ���Կ����ߣ���Կ���64λ��������16λ�Ѿ��㹻��
//****************************************

//��ȡ����
	   String v_oid = request.getParameter("v_oid");		// ������
	 String v_pmode = request.getParameter("v_pmode");		// ֧����ʽ����˵������"���г������ÿ�"
   String v_pstatus = request.getParameter("v_pstatus");	// ֧�������20֧����ɣ�30֧��ʧ�ܣ�
   String v_pstring = request.getParameter("v_pstring");	// ��֧�������˵�����ɹ�ʱ��v_pstatus=20��Ϊ"֧���ɹ�"��֧��ʧ��ʱ��v_pstatus=30��Ϊ"֧��ʧ��"
	String v_amount = request.getParameter("v_amount");		// ����ʵ��֧�����
 String v_moneytype = request.getParameter("v_moneytype");	// ����
	String v_md5str = request.getParameter("v_md5str");		// MD5У����
	 String remark1 = request.getParameter("remark1");		// ʵ�ʶ���ID��
	 String remark2 = request.getParameter("remark2");		//remark2:��ʾ��cust_id,user_id,pay_type��  ���� pay_type��1����֧��  2����ֵ֧��

String text = v_oid+v_pstatus+v_amount+v_moneytype+key; //ƴ�ռ��ܴ�
String v_md5 = MD5.getMD5ofStr(text).toUpperCase();
if (v_md5str.equals(v_md5))
{
   out.print("ok"); //���߷�������֤ͨ����ֹͣ����

   if ("20".equals(v_pstatus))
	{ 
	// ֧���ɹ����̻� �����Լ�ҵ������Ӧ�߼�����
	// �˴������̻�ϵͳ���߼����������жϽ��ж�֧��״̬�����¶���״̬�ȵȣ�......
	//remark2:��ʾ��cust_id,user_id,pay_type��  ���� pay_type��1����֧��  2����ֵ֧��
	 if(remark2!=null&&!remark2.equals("")){
		 String remark2s[]=remark2.split(",");
			String pay_type=remark2s[2].toString();
			if(v_oid!=null&&!v_oid.equals("")&&v_amount!=null&&!v_amount.equals("")){
				if(pay_type!=null&&!pay_type.equals("")){
				    if(pay_type.equals("1")){
					    if(remark1!=null&&!remark1.equals("")){
					       GoodsOrderFuc weborder=new GoodsOrderFuc();
					      //weborder.AutoUseOnlinePay(remark1,"5",remark2);
						}	
				    }else if(pay_type.equals("2")){
				       FundrechargeFuc fcharge=new FundrechargeFuc();
				       //fcharge.AutoInsertChongZhi("onlinepay",v_amount,v_oid,remark2);
				      
				    }
				}
			}
	 }
	}
}else{

	out.print("error"); //���߷�������֤ʧ�ܣ������ط�

}
%>




