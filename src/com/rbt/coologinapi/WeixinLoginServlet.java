package com.rbt.coologinapi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * Date: 12-12-4
 * Time: 上午10:28
 */
public class WeixinLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
           if (request.getParameter("code").equals("")) {
//                 我们的网站被CSRF攻击了或者用户取消了授权
//                 做一些数据统计工作
                 System.out.print("没有获取到响应参数");
             } else {
                     request.getSession().setAttribute("code", request.getParameter("code"));
                     System.out.println(request.getParameter("code"));
                     response.sendRedirect("/mall/memberuser!loginweixin.action");
           } 
    }
}
