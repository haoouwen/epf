/*
 
 * Package:com.rbt.common.util
 * FileName: ImagesizerUtil.java
 */
package com.rbt.common.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @function 功能 图像压缩工具
 * @author  创建人 QJY
 * @date  创建日期 Sep 2, 2014 8:50:44 AM
 */
public class ImagesizerUtil {
	
    public static final MediaTracker tracker = new MediaTracker(new Component() {
        private static final long serialVersionUID = 1234162663955668507L;} 
    );
    /**
     * @param originalFile 原图像
     * @param resizedFile 压缩后的图像
     * @param width 图像宽
     * @param format 图片格式 jpg, png, gif(非动画)
     * @throws IOException
     */
    public static void resize(File originalFile, File resizedFile, int width, String format) throws IOException {
    	//判断是否是gif格式的图片，采用另外的压缩方式
        if(format!=null && "gif".equals(format.toLowerCase())){
        	resize(originalFile, resizedFile, width, 1);
        	return;
        }
        FileInputStream fis = new FileInputStream(originalFile);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int readLength = -1;
        int bufferSize = 1024;
        byte bytes[] = new byte[bufferSize];
        while ((readLength = fis.read(bytes, 0, bufferSize)) != -1) {
            byteStream.write(bytes, 0, readLength);
        }
        byte[] in = byteStream.toByteArray();
        fis.close();
        byteStream.close();
        
    	Image inputImage = Toolkit.getDefaultToolkit().createImage( in );
        waitForImage( inputImage );
        int imageWidth = inputImage.getWidth( null );
        if ( imageWidth < 1 ) 
           throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
        int imageHeight = inputImage.getHeight( null );
        if ( imageHeight < 1 ) 
           throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
        
        // Create output image.
        int height = -1;
        double scaleW = (double) imageWidth / (double) width;
        double scaleY = (double) imageHeight / (double) height;
        if (scaleW >= 0 && scaleY >=0) {
            if (scaleW > scaleY) {
                height = -1;
            } else {
                width = -1;
            }
        }
        Image outputImage = inputImage.getScaledInstance( width, height, java.awt.Image.SCALE_DEFAULT);
        checkImage( outputImage );        
        encode(new FileOutputStream(resizedFile), outputImage, format);        
    }    

    /**
	 * 方法描述：检查图片像素是否符合要求
	 * 
	 * @return
	 * @throws Exception
	 */
    /** Checks the given image for valid width and height. */
    private static void checkImage( Image image ) {
       waitForImage( image );
       int imageWidth = image.getWidth( null );
       if ( imageWidth < 1 ) 
          throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
       int imageHeight = image.getHeight( null );
       if ( imageHeight < 1 ) 
          throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
    }

    /**
	 * 方法描述：等待图片被完全加载。
	 * 
	 * @return
	 * @throws Exception
	 */
    /** Waits for given image to load. Use before querying image height/width/colors. */
    private static void waitForImage( Image image ) {
       try {
          tracker.addImage( image, 0 );//将图像注册到tracker
          tracker.waitForID( 0 );//强迫这个程序等待这个id登记的图像被完全载入
          tracker.removeImage(image, 0);
       } catch( InterruptedException e ) { e.printStackTrace(); }
    } 

    /**
	 * 方法描述：对图像输出流中的图片进行解码，
	 * 
	 * @return
	 * @throws Exception
	 */
    /** Encodes the given image at the given quality to the output stream. */
    private static void encode( OutputStream outputStream, Image outputImage, String format ) 
       throws java.io.IOException {
       int outputWidth  = outputImage.getWidth( null );
       if ( outputWidth < 1 ) 
          throw new IllegalArgumentException( "output image width " + outputWidth + " is out of range" );
       int outputHeight = outputImage.getHeight( null );
       if ( outputHeight < 1 ) 
          throw new IllegalArgumentException( "output image height " + outputHeight + " is out of range" );

       // Get a buffered image from the image.
       BufferedImage bi = new BufferedImage( outputWidth, outputHeight,
          BufferedImage.TYPE_INT_RGB );                                                   
       Graphics2D biContext = bi.createGraphics();
       biContext.drawImage( outputImage, 0, 0, null );//画图
       ImageIO.write(bi, format, outputStream);
       outputStream.flush();      
    } 
    
	/**
	 * 缩放gif图片
	 * @param originalFile 原图片
	 * @param resizedFile 缩放后的图片
	 * @param newWidth 宽度
	 * @param quality 缩放比例 (等比例)
	 * @throws IOException
	 */
    private static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {
        if (quality < 0 || quality > 1) {
            throw new IllegalArgumentException("Quality has to be between 0 and 1");
        } 
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null; 
        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null); 
        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
        }
        // 保证图片所有像素都被加载
        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage(); 
        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),
                                                        BufferedImage.TYPE_INT_RGB); 
        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics(); 
        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose(); 
        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = {0, softenFactor, 0, softenFactor, 1-(softenFactor*4), softenFactor, 0, softenFactor, 0};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null); 
        // Write the jpeg to a file.
        FileOutputStream out = new FileOutputStream(resizedFile);        
        // Encodes image as a JPEG data stream
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage); 
        param.setQuality(quality, true); 
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
    }
    
}
