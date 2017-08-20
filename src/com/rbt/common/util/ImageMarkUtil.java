/*
 
 * Package:com.rbt.common.util
 * FileName: ImageMarkUtil.java 
 */
package com.rbt.common.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.rbt.function.SysconfigFuc;

/**
 * 图片水印处理
 * @author HXK
 */
public class ImageMarkUtil {
	
	private final static String CFG_IMAGEMARKDEGREE = "cfg_imagemarkdegree";
	private final static String CFG_IMAGEMARKALAPHA = "cfg_imagemarkalpha";
	private final static String CFG_IMAGEMARKWIDTH = "cfg_imagemarkwidth";
	private final static String CFG_IMAGEMARKHEIGHT = "cfg_imagemarkheight";
	private String CFG_IMAGEMARICON = SysconfigFuc.getSysValue("cfg_imagemaricon");
	private String CFG_IMAGEMARKTEXT = SysconfigFuc.getSysValue("cfg_imagemarktext");
    private final static String CFG_IMAGEFORNTSIZE = "cfg_imageforntsize";
	private String CFG_IMAGEMARK = SysconfigFuc.getSysValue("cfg_imagemark");
	private String CFG_IMAGEMARKTYPE = SysconfigFuc.getSysValue("cfg_imagemarktype");
	private String cfg_goodsdetail_content=SysconfigFuc.getSysValue("cfg_goodsdetail_content");//商品详细内容图片是否加水印 1 加 0 不加
	/*
	 * 商品详细页 详细内容图片水印处理方法
	 */
	@SuppressWarnings("static-access")
	public void imageMarkProcessGoodsDetail(String image_path) {

		// 实例化水印图片处理类
		String iconPath = "", logoText = "";
		Integer degree = 0, sWidth = 0, sHeight = 0, sforntsize = 0;
		Float transparency = 0.5f;// 水印透明度
		if (!ValidateUtil.isRequired(image_path)) {
			// 从系统配置表中获取配置图片水印信息
			// 水印图标
			iconPath = CFG_IMAGEMARICON;
			// 水印旋转度数
			degree = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKDEGREE));
			// 水印距离原图左边框的百分比
			sWidth = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKWIDTH));
			// 水印距离原图上边框的百分比
			sHeight = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKHEIGHT));
			// 水印透明度
			transparency = Float.parseFloat(SysconfigFuc.getSysValue(CFG_IMAGEMARKALAPHA));
			// 文字水印的字体大小
			sforntsize = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEFORNTSIZE));
			// 文字水印的标题的文字
			logoText = CFG_IMAGEMARKTEXT;
			//判断系统开启水印和详细内容支持图片水印 2个同时开启的时候 水印才生效
			if ("0".equals(CFG_IMAGEMARK)&&"1".equals(cfg_goodsdetail_content)) {
					// 判断为图片水印类型
					if (CFG_IMAGEMARKTYPE.equals("0")) {
						markImageByIcon(iconPath, image_path,image_path, degree, transparency, sWidth,sHeight);
					}
					// 判断为文字水印类型
					else if (CFG_IMAGEMARKTYPE.equals("1")) {
						markByText(logoText, image_path,image_path, degree, transparency, sWidth,sHeight, sforntsize);
					}
				}
			}

	}
	/*
	 * 图片水印处理方法
	 */
	@SuppressWarnings("static-access")
	public void imageMarkProcess(String image_path) {

		// 实例化水印图片处理类
		ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
		String iconPath = "", logoText = "";
		Integer degree = 0, sWidth = 0, sHeight = 0, sforntsize = 0;
		Float transparency = 0.5f;// 水印透明度
		if (!ValidateUtil.isRequired(image_path)) {
			// 从系统配置表中获取配置图片水印信息
			// 水印图标
			iconPath = CFG_IMAGEMARICON;
			// 水印旋转度数
			degree = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKDEGREE));
			// 水印距离原图左边框的百分比
			sWidth = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKWIDTH));
			// 水印距离原图上边框的百分比
			sHeight = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKHEIGHT));
			// 水印透明度
			transparency = Float.parseFloat(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKALAPHA));
			// 文字水印的字体大小
			sforntsize = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEFORNTSIZE));
			// 文字水印的标题的文字
			logoText = CFG_IMAGEMARKTEXT;
			if (CFG_IMAGEMARK.equals("0")) {
					// 判断为图片水印类型
					if (CFG_IMAGEMARKTYPE.equals("0")) {
						imageMarkUtil.markImageByIcon(iconPath, image_path,image_path, degree, transparency, sWidth,sHeight);
					}
					// 判断为文字水印类型
					else if (CFG_IMAGEMARKTYPE.equals("1")) {
						imageMarkUtil.markByText(logoText, image_path,image_path, degree, transparency, sWidth,sHeight, sforntsize);
					}
				}
			}

	}
	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 * @param transparency
	 *            水印透明度 如0.5f
	 * @param sWidth
	 *            水印图片离原图左边框距离百分比
	 * @param sHeight 水印图片离原图上边框距离百分比
	 */
	public  void markImageByIcon(String iconPath, String srcImgPath,String targerPath, Integer degree, float transparency,Integer sWidth, Integer sHeight) 
	{
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			//获取项目的根目录路径
		    String rootPath = PropertiesUtil.getRootpath();
			ImageIcon imgIcon = new ImageIcon(rootPath+iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = transparency; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			ImageIcon imgtarget = new ImageIcon(srcImgPath);
			Image theImg = imgtarget.getImage();
			int  width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			// 表示水印图片的位置
			g.drawImage(img,(int)(width*((float)sWidth/(float)100)), (int)(height*(float)sHeight/(float)100), null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给图片添加文字水印、可设置水印的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 * @param transparency
	 *            水印透明度 如0.5f
	 * @param sWidth
	 *            水印图片离原图左边框距离百分比
	 * @param sHeight
	 *           水印图片离原图上边框距离百分比
	 *@param sfontbold 字体是否加粗（0：加粗，1：正常）
	 *@param sforntsize 字体大小        
	 */
	public  void markByText(String logoText, String srcImgPath,String targerPath, Integer degree,
			float transparency,Integer sWidth, Integer sHeight ,Integer sforntsize) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}
			// 设置颜色
			g.setColor(Color.black);
			// 设置 Font(字体，字体样式，字体大小) 如font("宋体",Font.BOLD,20)
			g.setFont(new Font("宋体", Font.BOLD, sforntsize));
			// 设置透明度 1f 不透明，0.5f半透明，0f完全透明
			float alpha = transparency;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			ImageIcon imgIcon = new ImageIcon(srcImgPath);
			Image theImg = imgIcon.getImage();
			int width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(logoText, width*((float)sWidth/(float)100), height*(float)sHeight/(float)100);

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 图片合成
	 * hxk-2014-06-15
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 * @param transparency
	 *            水印透明度 如0.5f
	 * @param sWidth
	 *            水印图片离原图左边框距离百分比
	 * @param sHeight 水印图片离原图上边框距离百分比
	 */
	public  void markImageByAdd(String iconPath, String srcImgPath,String targerPath, Integer degree, float transparency,Integer sWidth, Integer sHeight) 
	{
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			//获取项目的根目录路径
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = transparency; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			ImageIcon imgtarget = new ImageIcon(srcImgPath);
			Image theImg = imgtarget.getImage();
			int  width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			// 表示水印图片的位置
			g.drawImage(img,sWidth,sHeight, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("合成成功");
	}
	
}
