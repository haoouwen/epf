/*
 
 * Package:com.rbt.common.util
 * FileName: ImageZipUtil.java 
 */
package com.rbt.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import com.rbt.function.SysconfigFuc;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @function 功能 图像压缩工具
 * @author  创建人 HXK
 */
public class ImageZipUtil {
    private int wideth;
    private int height;
    private String t = null;
    private final String UPLOADS_PATH = "uploads";// 上传文件根目录
    public void setT(String t) {
        this.t = t;
    }

    public void setWideth(int wideth) {
        // wideth=320;
        this.wideth = wideth;
    }

    public int getWideth() {
        return this.wideth;
    }

    public void setHeight(int height) {
        // height=240;
        this.height = height;
    }
    
    
    /**
	 * @Method Description :通过阿里云图片插件 处理图片压缩
	 * @author: 胡惜坤
	 * @date : Nov 13, 2015 10:13:44 AM
	 * @param oldFile：源文件路径，newFile 压缩后图片路径，width 压缩图片的宽 ，height压缩图片高度
	 * @return void
	 */
    public static void imageZipProceFlie(File oldFile,String newFile, int width, int height){
    	File in =oldFile;      //原图片
        File out = new File(newFile);       //目的图片
        ScaleParameter scaleParam = new ScaleParameter(width, height);  //将图像缩略到width x height以内，不足width x height则不做任何处理
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);
            wr.render();                            //触发图像处理
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
    }
    
    
    
    /**
	 * @Method Description :通过阿里云图片插件 处理图片压缩
	 * @author: 胡惜坤
	 * @date : Nov 13, 2015 10:13:44 AM
	 * @param oldFile：源文件路径，newFile 压缩后图片路径，width 压缩图片的宽 ，height压缩图片高度
	 * @return void
	 */
    public void imageZipProce(String oldFile,String newFile, int width, int height){
    	File in = new File(oldFile);      //原图片
        File out = new File(newFile);       //目的图片
        ScaleParameter scaleParam = new ScaleParameter(width, height);  //将图像缩略到width x height以内，不足width x height则不做任何处理
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);
            wr.render();                            //触发图像处理
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
    }
   
    /**
	 * @Method Description :通过阿里云图片插件 处理图片压缩
	 * @author: 胡惜坤
	 * @date : Nov 13, 2015 10:13:44 AM
	 * @param oldFile：源文件路径，newFile 压缩后图片路径，width 压缩图片的宽 ，height压缩图片高度
	 * @return void
	 */
    public void imageZipProceApp(String oldFile,String newFile, int width, int height){
    	File in = new File(oldFile);      //原图片
        File out = new File(newFile);       //目的图片
        ScaleParameter scaleParam = new ScaleParameter(width, height);  //将图像缩略到width x height以内，不足width x height则不做任何处理
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        WriteRender wr = null;
        try {
            inStream = new FileInputStream(in);
            outStream = new FileOutputStream(out);
            ImageRender rr = new ReadRender(inStream);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);
            wr.render();                            //触发图像处理
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
            IOUtils.closeQuietly(outStream);
            if (wr != null) {
                try {
                    wr.dispose();                   //释放simpleImage的内部资源
                } catch (SimpleImageException ignore) {
                    // skip ... 
                }
            }
        }
    }
    /**
    * 压缩图片方法
    * 
    * @param oldFile
    *            将要压缩的图片
    * @param width
    *            压缩宽
    * @param height
    *            压缩长
    * @param quality
    *            压缩清晰度 <b>建议为1.0</b>
    * @param smallIcon
    *            压缩图片后,添加的扩展名
    * @return
    */
    public String imageZipProce(String oldFile, int width, int height, float quality) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            File file = new File(oldFile);
            //文件不存在时
            if(!file.exists())return null;
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(file);
            int new_w=0,new_h=0;
            //获取图片的实际大小 高度
            int h=(int)srcFile.getHeight(null);
            //获取图片的实际大小 宽度
            int w=(int)srcFile.getWidth(null);
            // 为等比缩放计算输出的图片宽度及高度
            if((((double)w) >(double)width)||(((double)h)>(double) height))
            {
            	double rate=0;//算出图片比例值
            	//宽度大于等于高度
            	if(w>=h){
            	  rate = ((double) w) / (double) width;
            	}
            	//宽度小于高度
            	else if(h>w) {
            		rate = ((double) h) / (double) height;
				}
            	//构造新的比例的图片高度与宽度值
	            new_w = (int) (((double) w) / rate);
	            new_h = (int) (((double) h) / rate);
	            /** 宽,高设定 */
	            BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
	            tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
	            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
	            /** 压缩后的文件名 */
	            newImage = filePrex + oldFile.substring(filePrex.length());
	            /** 压缩之后临时存放位置 */
	            FileOutputStream out = new FileOutputStream(newImage);
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
	            /** 压缩质量 */
	            jep.setQuality(quality, true);
	            encoder.encode(tag, jep);
	            out.close();
	            srcFile.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }
    
    /**
     * 压缩图片方法
     * 
     * @param oldFile
     *            将要压缩的图片
     * @param width
     *            压缩宽
     * @param height
     *            压缩长
     * @param quality
     *            压缩清晰度 <b>建议为1.0</b>
     * @param smallIcon
     *            压缩图片后,添加的扩展名
     * @return
     */
     public String imageZipProceApp(String oldFile, int width, float quality) {
         if (oldFile == null) {
             return null;
         }
         String newImage = null;
         try {
             File file = new File(oldFile);
             //文件不存在时
             if(!file.exists())return null;
             /** 对服务器上的临时文件进行处理 */
             Image srcFile = ImageIO.read(file);
             int new_w=0,new_h=0;
             //获取图片的实际大小 高度
             int h=(int)srcFile.getHeight(null);
             //获取图片的实际大小 宽度
             int w=(int)srcFile.getWidth(null);
             // 为等比缩放计算输出的图片宽度及高度
             if((((double)w) >(double)width))
             {
             	double rate=0;//算出图片比例值
             	rate = ((double) w) / (double) width;
             	//构造新的比例的图片高度与宽度值
 	           // new_w =width;
 	            //new_h = (int) (((double) h) / rate);
             	new_w=w;
             	new_h=h;
 	            /** 宽,高设定 */
 	            BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
 	            tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
 	            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
 	            /** 压缩后的文件名 */
 	            newImage = filePrex + oldFile.substring(filePrex.length());
 	            /** 压缩之后临时存放位置 */
 	            FileOutputStream out = new FileOutputStream(newImage);
 	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
 	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
 	            /** 压缩质量 */
 	            jep.setQuality(Float.valueOf("0.5"), true);
 	            encoder.encode(tag, jep);
 	            out.close();
 	            srcFile.flush();
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return newImage;
     }
     /**
 	 * @Method Description :通过阿里云图片插件 上传图片 并返回图片最新路径
 	 * @author: 胡惜坤
 	 * @date : Nov 13, 2015 10:13:44 AM
 	 * @param oldFile：源文件路径，newFile 压缩后图片路径，width 压缩图片的宽 ，height压缩图片高度
 	 * @return void
 	 */
     public String imageUploadZipApp(String oldFile){
    	 int cfg_bigimage_w_h =1000;//控制图片最大宽度和高度
    	 String newFile="";//新的文件路劲和名称
    	 File in = new File(oldFile);      //原图片
	     // 获取文件的存放文件位置
		 String strPath = getImageSavePath("memberico");
		 // 得到上传文件的扩展名
		 String ftype = new FileUtil().getFileExt(oldFile);
		 System.out.println("=====ftype=="+ftype);
		 // 获得当前的系统时间（小时分钟秒）来作为文件名
		 String filename = createFileNameByDate(ftype);
		 newFile=strPath+ File.separator + filename;
		 System.out.println("====newFile==="+newFile);
         File out = new File(newFile);       //目的图片
         ScaleParameter scaleParam = new ScaleParameter(cfg_bigimage_w_h, cfg_bigimage_w_h);  //将图像缩略到width x height以内，不足width x height则不做任何处理
         FileInputStream inStream = null;
         FileOutputStream outStream = null;
         WriteRender wr = null;
         try {
             inStream = new FileInputStream(in);
             outStream = new FileOutputStream(out);
             ImageRender rr = new ReadRender(inStream);
             ImageRender sr = new ScaleRender(rr, scaleParam);
             wr = new WriteRender(sr, outStream);
             wr.render();                            //触发图像处理
         } catch(Exception e) {
             e.printStackTrace();
         } finally {
             IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
             IOUtils.closeQuietly(outStream);
             if (wr != null) {
                 try {
                     wr.dispose();                   //释放simpleImage的内部资源
                 } catch (SimpleImageException ignore) {
                     // skip ... 
                 }
             }
         }
         return newFile;
     }
  // ftype：文件扩展名
 	public String createFileNameByDate(String ftype) {
 		Date current = new Date();
 		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
 		Random r = new java.util.Random();
 		String filename = sdf.format(current) + r.nextInt(1000) + "." + ftype;
 		return filename;
 	}
 // 获取图片存放的服务器地址
	public String getImageSavePath(String imgpath) {
		String IMAGES_PATH = "images";
		// 获取文件的存放文件位置
		String strPath = "";
		if(!"".equals(imgpath)){
			//特定图片路径
			strPath = PropertiesUtil.getRootpath() + UPLOADS_PATH+ File.separator +imgpath+File.separator+IMAGES_PATH;
		}else {
			strPath = PropertiesUtil.getRootpath() + UPLOADS_PATH+ File.separator + IMAGES_PATH;
		}
		// 获取当天日期作为文件夹
		String datePath = getYearMonthDay();
		// 文件最终保存的文件夹
		strPath = strPath + File.separator + datePath;
		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return strPath;
	}
	// 获取当前年月日
	public String getYearMonthDay() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(current);
	}
	 public static void main(String[] args) { 
	        ImageZipUtil ddImageZipUtil=new ImageZipUtil();
	        System.out.println(ddImageZipUtil.imageUploadZipApp("d:/主图1.jpg"));
	        
	    }
    
}