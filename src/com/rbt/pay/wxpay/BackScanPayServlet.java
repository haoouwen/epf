package com.rbt.pay.wxpay;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.util.XmlUtil;

public class BackScanPayServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2707856656462198124L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 微信加密签名
		 
		doPost(request, response);
	}
	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 System.out.println("########################in  BackServlet########################");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		try {
			XmlUtil.parseXml(request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
        
		 
	}
}
