package com.rbt.pay.wxpay.service;

import com.rbt.pay.wxpay.common.Configure;
import com.rbt.pay.wxpay.common.ConfigureApp;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * User: rizenguo
 * Date: 2014/12/10
 * Time: 15:44
 * 服务的基类
 */
public class BaseService{

    //API的地址
    private String apiURL;

    //发请求的HTTPS请求器
    private IServiceRequest serviceRequest;
    
    //发请求的HTTPS请求器 PC 退款
    private IServiceRequestR serviceRequestR;
     //发请求的HTTPS请求器 app退款
    private IServiceRequestAppR serviceRequestAppR;
    

    public BaseService(String api) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        apiURL = api;
      
    }
    protected String sendPost(Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	  Class c = Class.forName(Configure.HttpsRequestClassName);
          serviceRequest = (IServiceRequest) c.newInstance();
    	return serviceRequest.sendPost(apiURL,xmlObj);
    }
    protected String sendPostInfo(Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	 Class cr = Class.forName(Configure.HttpsRequestRClassName);
         serviceRequestR = (IServiceRequestR) cr.newInstance();
    	return serviceRequestR.sendPostInfo(apiURL,xmlObj);
    }
    protected String sendPostAppInfo(Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ClassNotFoundException, InstantiationException, IllegalAccessException {
   	 Class cr = Class.forName(ConfigureApp.HttpsRequestRClassName);
   	serviceRequestAppR = (IServiceRequestAppR) cr.newInstance();
   	return serviceRequestAppR.sendPostInfoApp(apiURL,xmlObj);
   }

    /**
     * 供商户想自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(IServiceRequest request){
        serviceRequest = request;
    }
}
