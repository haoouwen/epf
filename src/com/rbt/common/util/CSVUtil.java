package com.rbt.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

/**
 * @function 功能  导出csv
 * @author WXP
 * @date  创建日期  Oct 8, 2014
 */
public class CSVUtil {

    public static File createCSVFile(List exportData, LinkedHashMap rowMapper , String fileName) {
    	String outPutPath=PropertiesUtil.getRootpath() + "csvs";
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath+ File.separator +fileName+".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator
                    .hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                        .next();
                csvFileOutputStream.write("\""
                        + propertyEntry.getValue().toString() + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();

           


            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) { 
                Object row = (Object) iterator.next(); 
                System.out.println(row);
            
                for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();) { 
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next(); 
                    System.out.println( BeanUtils.getProperty(row, propertyEntry.getKey().toString()));
                    csvFileOutputStream.write("\"" 
                            +  BeanUtils.getProperty(row, propertyEntry.getKey().toString()) + "\""); 
                   if (propertyIterator.hasNext()) { 
                       csvFileOutputStream.write(","); 
                    } 
               } 
                if (iterator.hasNext()) { 
                   csvFileOutputStream.newLine(); 
                } 
           } 
            csvFileOutputStream.flush(); 
        } catch (Exception e) { 
           e.printStackTrace(); 
        } finally { 
           try { 
                csvFileOutputStream.close(); 
            } catch (IOException e) { 
               e.printStackTrace();
           } 
       } 
        return csvFile;
    }
    
    public static void createCsvAndDownload(List exportData, LinkedHashMap rowMapper , String fileName){
    	try {
	    	File file = createCSVFile(exportData, rowMapper , fileName);
	    	String filePath = file.getPath();
	    	if(filePath != null && !filePath.equals("")){
	    		//fileUpDownFileUtil.downCommonFile(filePath);
	    		HttpServletResponse response = ServletActionContext.getResponse();
	    		response.setContentType("text/html; charset=UTF-8");
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
    	}
    	catch (IOException e) { 
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("1", "11");
        row1.put("2", "12");
        row1.put("3", "13");
        row1.put("4", "14");
        exportData.add(row1);
        row1 = new LinkedHashMap<String, String>();
        row1.put("1", "21");
        row1.put("2", "22");
        row1.put("3", "23");
        row1.put("4", "24");
        exportData.add(row1);
        List propertyNames = new ArrayList();
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "第一列");
        map.put("2", "第二列");
        map.put("3", "第三列");
        map.put("4", "第四列");
        CSVUtil.createCSVFile(exportData, map, "filaName");
    }
}