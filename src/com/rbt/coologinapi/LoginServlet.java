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
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
    	
    	 PrintWriter out = response.getWriter();
    	 try {
             AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

             String accessToken   = null,
                    openID        = null;
             long tokenExpireIn = 0L;

             System.out.println("accessToken: "+accessTokenObj.getAccessToken());


             if (accessTokenObj.getAccessToken().equals("")) {
//                 我们的网站被CSRF攻击了或者用户取消了授权
//                 做一些数据统计工作
            	 System.out.println(request.getParameter("code"));
                 System.out.print("没有获取到响应参数");
             } else {
                 accessToken = accessTokenObj.getAccessToken();
                 tokenExpireIn = accessTokenObj.getExpireIn();

                 request.getSession().setAttribute("demo_access_token", accessToken);
                 request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                 // 利用获取到的accessToken 去获取当前用的openid -------- start
                 OpenID openIDObj =  new OpenID(accessToken);
                 openID = openIDObj.getUserOpenID();
                  
                 out.println("open_id: " + openID);
                 request.getSession().setAttribute("openid", openID);
                 // 利用获取到的accessToken 去获取当前用户的openid --------- end


                 out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息,并实现用户登录 ---------------------------- start </p>");
                 UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                 UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                 out.println("<br/>");
                 if (userInfoBean.getRet() == 0) {
                	 //获取用户昵称
                     String nickName = userInfoBean.getNickname();
                     request.getSession().setAttribute("nickName", nickName);
                     //获取qq空间头像
                     String qqPic = userInfoBean.getAvatar().getAvatarURL100();
                     request.getSession().setAttribute("qqPic", qqPic);
                     System.out.println("nickName: " + nickName);
                     response.sendRedirect("/mall/memberuser!loginqq.action");
                 } else {
                     out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                 }
                 out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息,并实现用户登录 ---------------------------- end </p>");


             }
         } catch (QQConnectException e) {
         }
    }
}
