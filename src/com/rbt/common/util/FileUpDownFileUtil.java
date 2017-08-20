package com.rbt.common.util;

import java.io.BufferedInputStream;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 * @Class Description : 文件上传下载功能
 * @author : LJQ
 * @date : Nov 10, 2014 9:58:18 AM
 */
public class FileUpDownFileUtil {
	//设置编码格式
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	//获取项目的根目录
	private String rootPath = PropertiesUtil.getRootpath();
	
	/**
	 * @Method Description :文件的下载功能
	 * @author : LJQ
     * @throws IOException 
     * @date : Nov 9, 2014 3:42:46 PM
	 */
	public void downloadFile(String path,String filename) throws IOException{
		String filePath = rootPath+path+filename;
		downCommonFile(filePath);
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @date : Jul 11, 2014 9:40:16 AM
	 * @Method Description : 前台通过请求路径加载文件功能
	 */
	public void downloadWebFile(String filePath) throws IOException{
		downCommonFile(rootPath+filePath);
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @date : Jul 11, 2014 9:35:18 AM
	 * @Method Description : 下载的共用方法
	 */
	public void downCommonFile(String filePath) throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(CONTENT_TYPE);
        // 创建file对象
        File file=new File(filePath);
        String filename="";
        if(filePath.indexOf("\\")>-1){
        	int len = filePath.lastIndexOf("\\");
        	System.out.println(len);
        	filename=filePath.substring(len+1,filePath.length());
        	System.out.println(filename);
        }
        // 设置response的编码方式
        response.setContentType("application/x-msdownload");
        // 写明要下载的文件的大小
        response.setContentLength((int)file.length());   
        // 解决中文乱码
        response.setHeader("Content-Disposition","attachment;filename="+filename);            
        // 读出文件到i/o流
	    FileInputStream fis=new FileInputStream(file);
	    BufferedInputStream buff=new BufferedInputStream(fis);
        byte [] b=new byte[1024];// 相当于我们的缓存
        long k=0;// 该值用于计算当前实际下载了多少字节
        // 从response对象中得到输出流,准备下载
        OutputStream myout=response.getOutputStream();
        // 开始循环下载
        while(k<file.length()){
            int j=buff.read(b,0,1024);
            k+=j;
            // 将b中的数据写到客户端的内存
            myout.write(b,0,j);
        }
        // 将写入到客户端的内存的数据,刷新到磁盘
        myout.flush();
	}
	
	public void downCommonFile(File file) throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(CONTENT_TYPE);
        String filename="",filePath="";
        filePath = file.getPath();
        if(filePath.indexOf("/")>-1){
        	int len = filePath.lastIndexOf("/");
        	System.out.println(len);
        	filename=filePath.substring(len+1,filePath.length());
        	System.out.println(filename);
        }
        // 设置response的编码方式
        response.setContentType("application/x-msdownload");
        // 写明要下载的文件的大小
        response.setContentLength((int)file.length());   
        // 解决中文乱码
        response.setHeader("Content-Disposition","attachment;filename="+filename);            
        // 读出文件到i/o流
	    FileInputStream fis=new FileInputStream(file);
	    BufferedInputStream buff=new BufferedInputStream(fis);
        byte [] b=new byte[1024];// 相当于我们的缓存
        long k=0;// 该值用于计算当前实际下载了多少字节
        // 从response对象中得到输出流,准备下载
        OutputStream myout=response.getOutputStream();
        // 开始循环下载
        while(k<file.length()){
            int j=buff.read(b,0,1024);
            k+=j;
            // 将b中的数据写到客户端的内存
            myout.write(b,0,j);
        }
        // 将写入到客户端的内存的数据,刷新到磁盘
        myout.flush();
	}
}
