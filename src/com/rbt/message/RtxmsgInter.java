package com.rbt.message;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.bcloud.msg.http.HttpSender;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Smshistory;
import com.rbt.service.ISmshistoryService;

public class RtxmsgInter  extends CreateSpringContext {

	/*
	 * webservice服务器定义http://sdk105.entinfo.cn/webservice.asmx
	 * public String service_old="http://117.79.237.3:8060/webservice.asmx";
	 */
	private String serviceURL = "http://117.79.237.3:8060/webservice.asmx";

	private String sn = "";// 序列号
	private String password = "";
	private String pwd = "";// 密码
	private String msgword = "";// 发送关键字
    private String phone="";//发送电信号码 

	
	/*
	 * 构造函数
	 */
	public RtxmsgInter()
			throws UnsupportedEncodingException {
		//this.sn = "SDK-CSL-010-00012";
		//this.password ="349960";
		//this.sn = "SDK-CSL-010-00065";
		//this.password ="780871";
		this.sn = SysconfigFuc.getSysValue("cfg_msg_account");
		this.password = SysconfigFuc.getSysValue("cfg_msg_password");
		this.pwd = this.getMD5(sn + password);
		this.msgword=SysconfigFuc.getSysValue("cfg_msg_word");
		this.phone=SysconfigFuc.getSysValue("cfg_msg_phone");
	}

	/*
	 * 方法名称：getMD5 
	 * 功    能：字符串MD5加密 
	 * 参    数：待转换字符串 
	 * 返 回 值：加密之后字符串
	 */
	public String getMD5(String sourceStr) throws UnsupportedEncodingException {
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 方法名称：register 
	 * 功    能：注册 
	 * 参    数：对应参数 省份，城市，行业，企业名称，联系人，电话，手机，电子邮箱，传真，地址，邮编 
	 * 返 回 值：注册结果（String）
	 */
	public String register(String province, String city, String trade,
			String entname, String linkman, String phone, String mobile,
			String email, String fax, String address, String postcode) {
		String result = "";
		String soapAction = "http://tempuri.org/Register";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
		xml += "<soap12:Body>";
		xml += "<Register xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + password + "</pwd>";
		xml += "<province>" + province + "</province>";
		xml += "<city>" + city + "</city>";
		xml += "<trade>" + trade + "</trade>";
		xml += "<entname>" + entname + "</entname>";
		xml += "<linkman>" + linkman + "</linkman>";
		xml += "<phone>" + phone + "</phone>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<email>" + email + "</email>";
		xml += "<fax>" + fax + "</fax>";
		xml += "<address>" + address + "</address>";
		xml += "<postcode>" + postcode + "</postcode>";
		xml += "</Register>";
		xml += "</soap12:Body>";
		xml += "</soap12:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern
						.compile("<RegisterResult>(.*)</RegisterResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			return new String(result.getBytes(), "gb2312");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：chargeFee 
	 * 功    能：充值 
	 * 参    数：充值卡号，充值密码 
	 * 返 回 值：操作结果（String）
	 */
	public String chargeFee(String cardno, String cardpwd) {
		String result = "";
		String soapAction = "http://tempuri.org/ChargUp";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
		xml += "<soap12:Body>";
		xml += "<ChargUp xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + password + "</pwd>";
		xml += "<cardno>" + cardno + "</cardno>";
		xml += "<cardpwd>" + cardpwd + "</cardpwd>";
		xml += "</ChargUp>";
		xml += "</soap12:Body>";
		xml += "</soap12:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern
						.compile("<ChargUpResult>(.*)</ChargUpResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			// return result;
			return new String(result.getBytes(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：getBalance 
	 * 功    能：获取余额 
	 * 参    数：无 
	 * 返 回 值：余额（String）
	 */
	public String getBalance() {
		String result = "";
		String soapAction = "http://tempuri.org/balance";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<balance xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "</balance>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern
						.compile("<balanceResult>(.*)</balanceResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			return new String(result.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException 
	 * @date : Mar 19, 2014 11:48:53 AM
	 * @Method Description :测试短信接口
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
	}
		
		
	/*
	 * 方法名称：mt 
	 * 功    能：发送短信 
	 * 参    数：mobile,content,title,ext,stime,rrid(手机号，内容，标题，扩展码，定时时间，唯一标识)
	 * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public void mt(String mobile,String title, String content, String ext, String stime,
			String rrid) {
		if(!ValidateUtil.isRequired(mobile)) {
			String uri = "http://222.73.117.156/msg/index.jsp";//应用地址
			String account = SysconfigFuc.getSysValue("cfg_msg_account");//账号
			String pswd = SysconfigFuc.getSysValue("cfg_msg_password");//密码
			String mobiles = mobile;//手机号码，多个号码使用","分割
			boolean needstatus = true;//是否需要状态报告，需要true，不需要false
			String product = null;//产品ID
			String extno = null;//扩展码
			 
			try {
				String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
				//插入短信发送历史
				 insertSNSlHistory(title,content,mobile,"");
				System.out.println(returnString);
				//TODO 处理返回值,参见HTTP协议文档
			} catch (Exception e) {
				//TODO 处理异常
				e.printStackTrace();
			}
		}
		
	}
   
	/*
	 * 方法名称：mo 
	 * 功    能：接收短信 
	 * 参    数：无 
	 * 返 回 值：接收到的信息
	 */
	public String mo() {
		String result = "";
		String soapAction = "http://tempuri.org/mo";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mo xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "</mo>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStream isr = httpconn.getInputStream();
			StringBuffer buff = new StringBuffer();
			byte[] byte_receive = new byte[10240];
			for (int i = 0; (i = isr.read(byte_receive)) != -1;) {
				buff.append(new String(byte_receive, 0, i));
			}
			isr.close();
			String result_before = buff.toString();
			int start = result_before.indexOf("<moResult>");
			int end = result_before.indexOf("</moResult>");
			result = result_before.substring(start + 10, end);

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * 方法名称：msgid 
	 * 功    能：获取msgid（发送成功的最后100次） 
	 * 参    数：无 
	 * 返 回 值：msgid串
	 */
	public String msgid() {
		String result = "";
		String soapAction = "http://tempuri.org/msgid";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<msgid xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "</msgid>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";

		URL url;
		try {
			url = new URL(serviceURL);

			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStream isr = httpconn.getInputStream();
			StringBuffer buff = new StringBuffer();
			byte[] byte_receive = new byte[10240];
			for (int i = 0; (i = isr.read(byte_receive)) != -1;) {
				buff.append(new String(byte_receive, 0, i));
			}
			isr.close();
			String result_before = buff.toString();
			int start = result_before.indexOf("<msgidResult>");
			int end = result_before.indexOf("</msgidResult>");
			result = result_before.substring(start+13, end);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/*
	 * 功能：发送邮件 celltitle：短信标题 content：短信正文 celltitle：收件人手机号码 cell_code：短信模版编码,cellnum:发送人手机号码
	 * 插入短信发送历史
	 * HXK
	 */
	public void insertSNSlHistory(String celltitle,String content,String cellphones,String cell_code ){
		ISmshistoryService smshistoryService = (ISmshistoryService)getContext().getBean("smshistoryService");
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		Smshistory smshistory=new Smshistory();
		String user_name_str="",user_id_str="";
		//获取当前的发送人名称USER_NAME
		if (session.getAttribute(Constants.USER_NAME) != null) {
			user_name_str = session.getAttribute(Constants.USER_NAME).toString();
		}else{
			user_name_str="系统操作";
		}
		//获取发送人的当前用户URSE_ID
		if (session.getAttribute(Constants.USER_ID) != null) {
			user_id_str = session.getAttribute(Constants.USER_ID).toString();
		}
		smshistory.setCell_code(cell_code);
		smshistory.setCellname(user_name_str);
		smshistory.setCellnum(this.phone);
		//2014-11-24 update QJY SMS短信记录表中cellphones为必须字段
		if(cellphones == null){
			smshistory.setCellphones("");
		}else{
			smshistory.setCellphones(cellphones);
		}
		smshistory.setCelltitle(celltitle);
		smshistory.setContent(content);
		smshistory.setUser_id(user_id_str);
		//执行插入短信发送历史
		smshistoryService.insert(smshistory);
		
	}
}
