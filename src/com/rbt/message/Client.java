package com.rbt.message;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.Constants;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Smshistory;
import com.rbt.service.ISmshistoryService;

public class Client extends CreateSpringContext{

	/*
	 * webservice服务器定义
	 */
	
//	private String serviceURL = "http://sdk105.entinfo.cn:8060/webservice.asmx";
	
//	private String serviceURL = "http://report.zucp.net:8060/reportservice.asmx";
	private String serviceURL = "http://sdk.entinfo.cn:8061/webservice.asmx";
	private String sn = "";// 序列号
	private String password = "";
	private String pwd = "";// 密码
	private String msgword = "";// 发送关键字
    private String phone="";//发送电信号码 

	/*
	 * 构造函数
	 */
	public Client(String sn, String password)
			throws UnsupportedEncodingException {
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
	 * 方法名称：mdgetSninfo 
	 * 功    能：获取信息
	 * 参    数：sn,pwd(软件序列号，加密密码md5(sn+password))
	 * 
	 */
	public String mdgetSninfo() {
		String result = "";
		String soapAction = "http://entinfo.cn/mdgetSninfo";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdgetSninfo xmlns=\"http://entinfo.cn/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "<mobile>" + "" + "</mobile>";
		xml += "<content>" + "" + "</content>";
		xml += "<ext>" + "" + "</ext>";
		xml += "<stime>" + "" + "</stime>";
		xml += "<rrid>" + "" + "</rrid>";
		xml += "<msgfmt>" + "" + "</msgfmt>";
		xml += "</mdgetSninfo>";
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
				Pattern pattern = Pattern.compile("<mdgetSninfoResult>(.*)</mdgetSninfoResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	
	/*
	 * 方法名称：mdgxsend 
	 * 功    能：发送个性短信 
	 * 参    数：mobile,content,ext,stime,rrid,msgfmt(手机号，内容，扩展码，定时时间，唯一标识，内容编码)
	 * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public String mdgxsend(String mobile, String content, String ext, String stime,
			String rrid, String msgfmt) {
		String result = "";
		String soapAction = "http://entinfo.cn/mdgxsend";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdgxsend xmlns=\"http://entinfo.cn/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + content + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "<stime>" + stime + "</stime>";
		xml += "<rrid>" + rrid + "</rrid>";
		xml += "<msgfmt>" + msgfmt + "</msgfmt>";
		xml += "</mdgxsend>";
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
				Pattern pattern = Pattern.compile("<mdgxsendResult>(.*)</mdgxsendResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	/*
	 * 方法名称：mdsmssend
	 * 功    能：发送短信 
	 * 参    数：mobile,content,ext,stime,rrid,msgfmt(手机号，内容，扩展码，定时时间，唯一标识，内容编码)
	 * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
	 */
	public String mt(String mobile, String content, String ext, String stime,
			String rrid,String msgfmt) {
		String result = "";
		String soapAction = "http://entinfo.cn/mdsmssend";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdsmssend  xmlns=\"http://entinfo.cn/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + content + msgword+ "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "<stime>" + stime + "</stime>";
		xml += "<rrid>" + rrid + "</rrid>";
		xml += "<msgfmt>" + msgfmt + "</msgfmt>";
		xml += "</mdsmssend>";
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
				Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			//插入短信发送历史
			 insertSNSlHistory(ext,content,mobile,"");
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
	public static void main(String[] args) throws UnsupportedEncodingException
	{

		String sn="SDK-VXT-010-00089";
		String pwd="7ab68-8E";
		Client client=new Client(sn,pwd);
		//获取信息
		//String result = client.mdgetSninfo();
		//System.out.print(result);
		//短信发送	
	        String content=URLEncoder.encode("788[EPF]", "utf8");
		String result_mt = client.mt("15959359124", content, "", "", "", "");
		System.out.print(result_mt);
		//个性短信发送
//		String result_gxmt = client.mdgxsend("13800138", "测试", "", "", "", "");
//		System.out.print(result_gxmt);

	}
	
}
