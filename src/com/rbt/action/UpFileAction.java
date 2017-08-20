/*
 * Package:com.rbt.action
 * FileName: UpFileAction.java 
 */
package com.rbt.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.ImageMarkUtil;
import com.rbt.common.util.ImageZipUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.fastdfs.FastDFSFile;
import com.rbt.fastdfs.FileManager;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能 uploadify文件上传下载功能
 * @author 创建人 HXK
 * @date 创建日期 Jun 20, 2014
 */
@Controller
public class UpFileAction extends AdminBaseAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 定义系统参数配置默认名 cfg_imgtype：图片类型 cfg_attachtype：文件类型 cfg_mediatype：视频格式
	 * cfg_ddimg_width:图片默认宽度 cfg_imagemark 是否开启图片水印 cfg_imagemarktype
	 * 水印类型:("是":为图片水印)("否":为文字水印) cfg_imagemarkdegree 水印图片旋转角度
	 * cfg_imagemarkalpha 水印透明度：(0f为全透明、1f为完全不透明、0.5f为半透明) cfg_imagemarkwidth
	 * 水印图片在图片上的坐标位置(x,y)—x坐标位置百分比% cfg_imagemarkheight
	 * 水印图片在图片上的坐标位置(x,y)—y坐标位置百分比% cfg_imagemaricon 水印图片路径 cfg_imagemarktext
	 * 文字水印的文字标题 cfg_imageforntsize 文字水印的字体大小 cfg_imagemarkbold 是否文字水印字体加粗
	 */
	private final static String CFG_IMGTYPE = "cfg_imgtype";
	private final static String CFG_ATTACHTYPE = "cfg_attachtype";
	private final static String CFG_MEDIATYPE = "cfg_mediatype";
	private final static String CFG_DDIMG_WIDTH = "cfg_ddimg_width";
	// 控制缩略图大小(单位:px/像素)
	private final static String CFG_SMALLIMAGE = "cfg_smallimage";
	private final static String CFG_MIDDLEIMAGE = "cfg_middleimage";
	public int IMG_WIDTH = 100;// 图片默认宽度
	public File uploadifyfile;
	private final String UPLOADS_PATH = "uploads";// 上传文件根目录

	// images图片上传属性
	private static final String IMAGES_PATH = "images";// 存放图片文件夹名称
	public String IMAGE_TYPE = "";// 图片格式限制
	private static final int IMAGE_SIZE = 3;// 图片大小限制,单位是M

	// flash文件上传属性

	private static final String FLASH_PATH = "video";// 存放图片文件夹名称
	public String FLASH_TYPE = "";// 图片格式限制
	private static final int FLASH_SIZE = 50;// 图片大小限制,单位是M

	// File文件上传的属性

	private static final String FILE_PATH = "file";// 存放图片文件夹名称
	public String FILE_TYPE = "";// 图片格式限制
	private static final int FILE_SIZE = 10;// 图片大小限制,单位是M

	private String temp_file_path = "";

	public String temp_file_type = "";

	public int temp_file_size = 0;

	public String uploadifyfileFileName;

	public String isImgControl;// 判断是否需要控制缩略图大小
	public String isYin;// 判断是否加水印
	public String isImgControl_isYin;// 判断是否需要控制缩略图大小+水印

	// hong整理大中小图片添加的变量
	public String cust_id = "";// 用户编号
	public String issys = "";// 标记是否是系统图片
	public String module_type;// 标记那个模块上传

	private static final String SYSIMAGES_PATH = "sysimages";// 用于存放系统图片文件夹名称

	// 由于uploadfy在非IE浏览器底下是获取不到session的值，所以需要通过装饰器隐藏域传过来
	public String req_session_cust_type;// 获取的用户的类型:admin 运营商 member 会员
	public String req_session_cust_id;// 获取店铺ID
	String image_path = SysconfigFuc.getSysValue("cfg_image_path");// 获取图片配置路径
	private String cfg_bigimage = SysconfigFuc.getSysValue("cfg_bigimage");//商品图片最大图片控制
	
	
	
	/*************************************************/
	private File file;  
    private String fileFileContentType;  
    private String fileFileName;

	/*
	 * 主要是用于上传Flash的方法
	 */
	public String executeUpFlash() throws Exception {
		// 获取系统参数配置的视频格式
		this.FLASH_TYPE = SysconfigFuc.getSysValue(CFG_MEDIATYPE);
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.FLASH_PATH);
			this.setTemp_file_size(this.FLASH_SIZE);
			this.setTemp_file_type(this.FLASH_TYPE);
		}
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);

		String vertify_message = "";

		if (this.temp_file_type.indexOf(ftype) == -1) {
			vertify_message = this.temp_file_type + " is allowed";
		} else {

			// 获取表单的图片输入流、输出流
			InputStream is = new FileInputStream(this.uploadifyfile);
			// 创建文件
			OutputStream os = new FileOutputStream(new File(strPath
					+ File.separator + filename));

			// 保存文件
			try {
				byte[] buffer = new byte[1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
				vertify_message = "upload fail";
			} finally {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}

		// 浏览器访问的地址
		String file_url = image_path + "/" + UPLOADS_PATH + "/"
				+ this.temp_file_path + "/" + getYearMonthDay() + "/"
				+ filename;

		// 验证不通过则访问地址置空
		if (!vertify_message.equals("")) {
			file_url = "";
		}
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		response.getWriter().print(file_url);

		return Action.NONE;
	}

	/*
	 * 主要是用于上传文件的方法
	 */
	@SuppressWarnings("static-access")
	public String executeUpFile() throws Exception {

		if (!validateFactory.isRequired(uploadifyfileFileName)) {
			// 获取系统参数配置的文件类型
			this.FILE_TYPE = SysconfigFuc.getSysValue(CFG_ATTACHTYPE)
					.toString();
			if (this.temp_file_path.equals("")) {
				this.setTemp_file_path(this.FILE_PATH);
				this.setTemp_file_size(this.FILE_SIZE);
				this.setTemp_file_type(this.FILE_TYPE);
			}
			// 获取文件的存放文件位置
			String strPath = getImageSavePath();
			// 得到上传文件的扩展名
			String ftype = new FileUtil()
					.getFileExt(this.uploadifyfileFileName);
			// 获得当前的系统时间（小时分钟秒）来作为文件名
			String filename = createFileNameByDate(ftype);

			String vertify_message = "";

			if (this.temp_file_type.indexOf(ftype) == -1) {
				vertify_message = this.temp_file_type + " is allowed";
			} else {

				// 获取表单的图片输入流、输出流
				InputStream is = new FileInputStream(this.uploadifyfile);
				// 创建文件
				OutputStream os = new FileOutputStream(new File(strPath
						+ File.separator + filename));

				// 保存文件
				try {
					byte[] buffer = new byte[1024];
					while (is.read(buffer) > 0) {
						os.write(buffer);
					}
				} catch (Exception e) {
					e.printStackTrace();
					vertify_message = "upload fail";
				} finally {
					if (is != null) {
						is.close();
					}
					if (os != null) {
						os.close();
					}
				}
			}

			// 浏览器访问的地址
			String file_url = image_path + "/" + UPLOADS_PATH + "/"
					+ this.temp_file_path + "/" + getYearMonthDay() + "/"
					+ filename;

			// 验证不通过则访问地址置空
			if (!vertify_message.equals("")) {
				file_url = "";
			}
			HttpServletResponse response = getResponse();
			PrintWriter out = response.getWriter();
			// 返回给ckeditor
			response.getWriter().print(file_url);
		}
		return Action.NONE;
	}

	/**
	 * @Method Description :普通图片上传
	 * @author: HXK
	 * @date : Nov 12, 2015 2:35:44 PM
	 * @param 
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public String executeUpimages() throws Exception {
		if (!ValidateUtil.isRequired(uploadifyfileFileName)) {
			// 获取系统参数配置默认缩略图的宽度
			this.IMG_WIDTH = Integer.parseInt(SysconfigFuc.getSysValue(CFG_DDIMG_WIDTH));
			// 获取系统参数配置的图片类型
			this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
			if (this.temp_file_path.equals("")) {
				this.setTemp_file_path(this.IMAGES_PATH);
				this.setTemp_file_size(this.IMAGE_SIZE);
				this.setTemp_file_type(this.IMAGE_TYPE);
			}
			// 获取文件的存放文件位置
			String strPath = getImageSavePath();
			// 得到上传文件的扩展名
			String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
			// 获得当前的系统时间（小时分钟秒）来作为文件名
			String filename = createFileNameByDate(ftype);

			String vertify_message = "";

			if (this.temp_file_type.indexOf(ftype) == -1) {
				vertify_message = this.temp_file_type + " is allowed";
			} else {
				int cfg_bigimage_w_h =IMG_WIDTH;
				//if(!ValidateUtil.isRequired(cfg_bigimage)&&!"0".equals(cfg_bigimage)){
				//	cfg_bigimage_w_h=Integer.parseInt(cfg_bigimage);
				//}
				//图片上传大图
				ImageZipUtil.imageZipProceFlie(uploadifyfile,strPath+ File.separator + filename,cfg_bigimage_w_h,cfg_bigimage_w_h);
			}

			// 浏览器访问的地址
			String file_url = image_path + "/" + UPLOADS_PATH + "/"
					+ this.temp_file_path + "/" + getYearMonthDay() + "/"
					+ filename;

			// 验证不通过则访问地址置空
			if (!vertify_message.equals("")) {
				file_url = "";
			}
			HttpServletResponse response = getResponse();
			PrintWriter out = response.getWriter();
			// 返回给ckeditor
			response.getWriter().print(file_url);
		}
		return Action.NONE;
	}

	/*
	 * 图片压缩处理方法
	 */
	public static void zipimagesproces(String old_image_paths,String image_paths, String image_width,String image_height) {
		ImageZipUtil imageZipUtil = new ImageZipUtil();
		Integer sWidthImage = 0,sHeightImage=0;// 图片最大宽度，从配置表中取配置信息
		if (!ValidateUtil.isRequired(image_width)&&!"0".equals(image_width)&&!ValidateUtil.isRequired(image_height)&&!"0".equals(image_height)) {
			sWidthImage = Integer.parseInt(image_width);
			sHeightImage=Integer.parseInt(image_height);
			// 处理压缩的方法的，image_paths：图片的路径，sWidthImage：图片的最大宽度，1：为压缩清晰度
			imageZipUtil.imageZipProce(old_image_paths,image_paths, sWidthImage, sHeightImage);
		}
	}
	
	/*
	 * 图片压缩处理方法
	 */
	public static void zipimagesprocesApp(String image_paths, String image_width) {
		ImageZipUtil imageZipUtil = new ImageZipUtil();
		Integer sWidthImage = 0;// 图片最大宽度，从配置表中取配置信息
		if (image_width != null && !image_width.equals("")
				&& !image_width.equals("0")) {
			sWidthImage = Integer.parseInt(image_width);
			// 处理压缩的方法的，image_paths：图片的路径，sWidthImage：图片的最大宽度，1：为压缩清晰度
			imageZipUtil.imageZipProceApp(image_paths, sWidthImage, 1);
		}
	}
	
	// 获得当前的系统时间（小时分钟秒）来作为文件名
	// ftype：文件扩展名
	public String createFileNameByDate(String ftype) {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		Random r = new java.util.Random();
		String filename = sdf.format(current) + r.nextInt(1000) + "." + ftype;
		return filename;
	}

	// 获取图片存放的服务器地址
	public String getImageSavePath() {

		// 获取文件的存放文件位置
		String strPath = "";
		if ("".equals(image_path)) {
			strPath = PropertiesUtil.getRootpath() + UPLOADS_PATH
					+ File.separator + this.temp_file_path;
		} else {
			strPath = PropertiesUtil.getRootpath() + "/" + image_path + "/"
					+ UPLOADS_PATH + File.separator + this.temp_file_path;
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
	/**
	 * @author : HXM
	 * @throws Exception
	 * @date : May 4, 2014 11:05:11 AM
	 * @Method Description :主要用于商品的大，中，小 三种图片上传
	 */
	public void executeUpGoodsimages() throws Exception {
		//获取上传图片方式
		String way = SysconfigFuc.getSysValue("cfg_fastdfs");
		if(!ValidateUtil.isRequired(way) && way.equals("0")) {
			//上传到配置服务器
			FastdfsUpimages();
		} else {
			//上传到项目中
			executeImages();
		}
	}
	
	/**
	 * @author : HXM2
	 * @throws Exception
	 * @date : May 4, 2012 11:05:11 AM
	 * @Method Description :商品上传图片 ：主要用于商品的大，中，小 三种图片上传
	 */
	public String executeImages() throws Exception {
		initImageMsg();
		// 获取文件的存放文件位置
		String imgRootPath = getImageSavePath1();
		// 得到上传文件的扩展名
		String fType = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String fileName = createFileNameByDate(fType);
		// 创建商品文件夹
		String commonPath = imgRootPath;
		String smallGoodsImgFoldPath = commonPath + "small";
		String midGoodsImgFoldPath = commonPath + "mid";
		String goodsImgFoldPath = commonPath + "big";
		File path = new File(smallGoodsImgFoldPath);
		//创建小图图片路径
		if (!path.exists()) {
			path.mkdirs();
		}
		path = new File(midGoodsImgFoldPath);
		//创建中图图片路径
		if (!path.exists()) {
			path.mkdirs();
		}
		path = new File(goodsImgFoldPath);
		//创建大图图片路径
		if (!path.exists()) {
			path.mkdirs();
		}
		// 商品图片文件夹名
		String goodsImgFold = goodsImgFoldPath + File.separator + fileName;
		// 是否上传为图片类型
		if (this.temp_file_type.indexOf(fType) > -1) {
			int cfg_bigimage_w_h =2000;
			if(!ValidateUtil.isRequired(cfg_bigimage)&&!"0".equals(cfg_bigimage)){
				cfg_bigimage_w_h=Integer.parseInt(cfg_bigimage);
			}
			//图片上传大图
			ImageZipUtil.imageZipProceFlie(uploadifyfile,goodsImgFold,cfg_bigimage_w_h,cfg_bigimage_w_h);
		}
		
		//中图图片路径
		String midImgPath = goodsImgFold.replace("big", "mid");
		// 压缩中图
		String middle_rate = SysconfigFuc.getSysValue(CFG_MIDDLEIMAGE).toString();
		if (middle_rate.indexOf(",") > -1) {
			String middle[] = middle_rate.split(",");
			if (middle.length > 1) {
				//获取小图
				int width = Integer.parseInt(middle[0].toString());
				int height = Integer.parseInt(middle[1].toString());
				zipimagesproces(goodsImgFold,midImgPath, width+"",height+"");
				// 图片加水印处理方法
				//imageMarkProcess(midImgPath);
			}
		}
		// 图片压缩处理方法
		// 小图图片路径
		String smallImgPath = goodsImgFold.replace("big", "small");
		// 压缩小图 和加水印
		String small_rate = SysconfigFuc.getSysValue(CFG_SMALLIMAGE).toString();
		if (small_rate.indexOf(",") > -1) {
			String small[] = small_rate.split(",");
			if (small.length > 1) {
				int width = Integer.parseInt(small[0].toString());
				int height = Integer.parseInt(small[1].toString());
				//图片压缩
				zipimagesproces(goodsImgFold,smallImgPath, width+"",height+"");
				// 图片加水印处理方法
				//imageMarkProcess(smallImgPath);
			}
		}
		//大图图片水印
		ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
		imageMarkUtil.imageMarkProcess(goodsImgFold);
		// 输出大图图片路径
		String browse_url = image_path + "/" + UPLOADS_PATH + "/" + cust_id
				+ "/" + this.temp_file_path + "/" + module_type + "/"
				+ getYearMonthDay() + "/big/" + fileName;
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		out.print(browse_url);
		return Action.NONE;
	}
	
	
	/**
	 * @author : XBY
	 * @throws Exception
	 * @date : May 4, 2012 11:05:11 AM
	 * @Method Description :主要用于图片上传fastdfs
	 */
	public String FastdfsUpimages() throws Exception {
		// 获取表单的图片输入流、输出流
		FileInputStream fis = new FileInputStream(this.uploadifyfile);
	    byte[] file_buff = null;
	    if (fis != null) {
	    	int len = fis.available();
	    	file_buff = new byte[len];
	    	fis.read(file_buff);
	    }
	    //创建FastDFSFile对象
	    FastDFSFile file = new FastDFSFile("24", file_buff, "gif");
	    //执行上传
	    String fileAbsolutePath = FileManager.upload(file);
		System.out.println(fileAbsolutePath);
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		out.print(fileAbsolutePath);
		return Action.NONE;
	}
	
	
	
	/**
	 * @author :HXM （用于图片上传压缩大、中、小使用）
	 * @date : May 7, 2014 2:22:40 PM
	 * @Method Description :获取图片存放的服务器地址
	 */
	public String getImageSavePath1() {
		// 获取文件的存放文件位置
		String strPath = "";
		if ("".equals(image_path)) {
			strPath = PropertiesUtil.getRootpath() + UPLOADS_PATH
					+ File.separator + cust_id + File.separator
					+ this.temp_file_path;
		} else {
			strPath = PropertiesUtil.getRootpath() + "/" + image_path + "/"
					+ UPLOADS_PATH + File.separator + cust_id + File.separator
					+ this.temp_file_path;
		}
		// 获取当天日期作为文件夹
		String datePath = getYearMonthDay();
		// 文件最终保存的文件夹
		strPath = strPath + File.separator + module_type + File.separator
				+ datePath + File.separator;// hong 修改来这里
		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return strPath;
	}

	/**
	 * @author HXM
	 * @throws IOException
	 * @date : Jun 24, 2014 12:36:17 AM
	 * @Method Description : 程序生成背景图片
	 */
	private String createImage(String bg_image_url, int width, int height)
			throws IOException {
		// 创建BufferedImage对象
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取Graphics2D
		Graphics2D g2d = image.createGraphics();
		// 画图
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
		// 释放对象
		g2d.dispose();
		try {
			ImageIO.write(image, "jpg", new File(bg_image_url));
		} catch (Exception e) {
			System.out.println("bg_image_url:" + bg_image_url);
		}
		// 保存文件
		return bg_image_url;
	}

	/**
	 * @author HXM
	 * @date : Jun 22, 2014 2:12:03 AM
	 * @param start_image_path
	 *            小图片
	 * @param background_image_path
	 *            背景图片
	 * @Method Description : 图片合成处理方法
	 */
	public void imageComposeProcess(String start_image_path,
			String background_image_path) throws IOException {
		// 实例化合成图片处理类
		ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
		Integer degree = 0;// 合成图片旋转度数
		Integer objWidth = 0;// 图片距离右边框的宽度
		Integer objHeight = 0;// 图片距离上边框的高度
		Integer start_width = 0;// 小图片的宽度
		Integer start_height = 0;// 小图片的高度
		Integer bg_widht = 0;// 背景图片的宽度
		Integer bg_height = 0;// 背景图片的高度
		Float transparency;// 合成图片透明度
		transparency = 1f;// 1f表示不透明，0.5f半透明，0f完全透明
		Image startImg = ImageIO.read(new File(start_image_path));
		Image bgImg = null;
		try {
			bgImg = ImageIO.read(new File(background_image_path));
		} catch (Exception e) {
			System.out
					.println("background_image_path:" + background_image_path);
		}
		if (startImg != null && bgImg != null) {
			start_width = startImg.getWidth(null);
			start_height = startImg.getHeight(null);
			bg_widht = bgImg.getWidth(null);
			bg_height = bgImg.getHeight(null);
			if ((bg_widht - start_width) > 0) {
				objWidth = (bg_widht - start_width) / 2;
			}
			if ((bg_height - start_height) > 0) {
				objHeight = (bg_height - start_height) / 2;
			}
			imageMarkUtil.markImageByAdd(start_image_path,
					background_image_path, start_image_path, degree,
					transparency, objWidth, objHeight);
		}
		System.out.println("合成成功");
	}

	/**
	 * @author : HXM
	 * @date : May 7, 2014 3:46:10 PM
	 * @Method Description :初始化图片数据
	 */
	private void initImageMsg() {
		String uploadPara = this.getRequest().getParameter("img_cust_id");
		if (uploadPara != null) {
			String[] paraVal = uploadPara.split(",");
			if (paraVal != null && paraVal.length >= 1 && paraVal[0] != null) {
				cust_id = paraVal[0];
			}
			if (paraVal != null && paraVal.length == 2 && paraVal[1] != null) {
				issys = paraVal[1];
			}
		}
		if (module_type == null || "".equals(module_type.trim())) {
			module_type = "other";
		}

		// 获取系统参数配置默认缩略图的宽度
		this.IMG_WIDTH = Integer.parseInt(SysconfigFuc
				.getSysValue(CFG_DDIMG_WIDTH));
		// 获取系统参数配置的图片类型
		this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
		if (this.temp_file_path.equals("")) {
			if (issys.equals("sys")) {
				this.setTemp_file_path(this.SYSIMAGES_PATH);
			} else {
				this.setTemp_file_path(this.IMAGES_PATH);
			}
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
	}

	/**
	 * @author Administrator QJY
	 * @method 前台
	 * @throws Exception
	 */
	 public void upload() throws Exception{  
        
		 HttpServletResponse response = getResponse();
	     response.setCharacterEncoding("UTF-8");
	     PrintWriter out=response.getWriter();
		 String message = "";  
        //这里的返回类型设置是重点。否则会报错，必须设置成text/html;如果设置成application/json  IE下会有问题，chrome没问题的。  
        
     // 获取系统参数配置的图片类型
		this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.IMAGES_PATH);
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
        
		 // 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.fileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);
		String file_url = "";
		if (this.temp_file_type.indexOf(ftype) == -1) {
			message = "不允许上传此类文件！";
			file_url = "";
		} else if(!file.exists()){  
            message = "此文件不存在！";
            file_url = "";
        }else {
        	
            FileInputStream fis = new FileInputStream(file);
           
            FileOutputStream fos = new FileOutputStream(new File(strPath
					+ File.separator + filename));
            
            byte bt[] = new byte[1024];  
            while(fis.read(bt)>0){  
                fos.write(bt);  
            }  
            fis.close();  
            fos.flush();  
            fos.close();  
            message = "文件上传成功！";
         // 浏览器访问的地址
    		file_url = image_path + "/" + UPLOADS_PATH + "/"
    				+ this.temp_file_path + "/" + getYearMonthDay() + "/"
    				+ filename;
        }
		out.write(file_url);
        //ServletActionContext.getResponse().getWriter().write("{file_url:\""+file_url+"\"}");
        out.flush();  
        out.close();  
    }
	
	 /**
		 * @Method Description :普通图片上传
		 * @author: HXK
		 * @date : Nov 12, 2015 2:35:44 PM
		 * @param 
		 * @return String
		 */
	    public String imgpath;
		public String executeUpimagesSpace() throws Exception {
			if (!ValidateUtil.isRequired(uploadifyfileFileName)) {
				// 获取系统参数配置默认缩略图的宽度
				this.IMG_WIDTH = Integer.parseInt(SysconfigFuc.getSysValue(CFG_DDIMG_WIDTH));
				// 获取系统参数配置的图片类型
				this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
				if (this.temp_file_path.equals("")) {
					this.setTemp_file_path(this.IMAGES_PATH);
					this.setTemp_file_size(this.IMAGE_SIZE);
					this.setTemp_file_type(this.IMAGE_TYPE);
				}
				// 获取文件的存放文件位置
				//String strPath = getImageSavePath();
				File path = new File(imgpath);
				if (!path.exists()) {
					path.mkdirs();
				}
				// 得到上传文件的扩展名
				String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
				// 获得当前的系统时间（小时分钟秒）来作为文件名
				String filename = createFileNameByDate(ftype);

				String vertify_message = "";

				if (this.temp_file_type.indexOf(ftype) == -1) {
					vertify_message = this.temp_file_type + " is allowed";
				} else {
					int cfg_bigimage_w_h =IMG_WIDTH;
					if(!ValidateUtil.isRequired(cfg_bigimage)&&!"0".equals(cfg_bigimage)){
						cfg_bigimage_w_h=Integer.parseInt(cfg_bigimage);
					}
					//图片上传大图
					ImageZipUtil.imageZipProceFlie(uploadifyfile,imgpath+ File.separator + filename,cfg_bigimage_w_h,cfg_bigimage_w_h);
				}

				// 浏览器访问的地址
				String file_url =imgpath+ File.separator + filename;

				// 验证不通过则访问地址置空
				if (!vertify_message.equals("")) {
					file_url = "";
				}
				HttpServletResponse response = getResponse();
				PrintWriter out = response.getWriter();
				// 返回给ckeditor
				response.getWriter().print(file_url);
			}
			return Action.NONE;
		}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
	}

	// 获取当前年月日
	public String getYearMonthDay() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(current);
	}

	public File getUploadifyfile() {
		return uploadifyfile;
	}

	public void setUploadifyfile(File uploadifyfile) {
		this.uploadifyfile = uploadifyfile;
	}

	public String getUPLOADS_PATH() {
		return UPLOADS_PATH;
	}

	public String getIMAGES_PATH() {
		return IMAGES_PATH;
	}

	public String getTemp_file_path() {
		return temp_file_path;
	}

	public void setTemp_file_path(String temp_file_path) {
		this.temp_file_path = temp_file_path;
	}

	public String getTemp_file_type() {
		return temp_file_type;
	}

	public void setTemp_file_type(String temp_file_type) {
		this.temp_file_type = temp_file_type;
	}

	public int getTemp_file_size() {
		return temp_file_size;
	}

	public void setTemp_file_size(int temp_file_size) {
		this.temp_file_size = temp_file_size;
	}

	public String getIMAGE_TYPE() {
		return IMAGE_TYPE;
	}

	public int getIMAGE_SIZE() {
		return IMAGE_SIZE;
	}

	public String getUploadifyfileFileName() {
		return uploadifyfileFileName;
	}

	public void setUploadifyfileFileName(String uploadifyfileFileName) {
		this.uploadifyfileFileName = uploadifyfileFileName;
	}

	public String getIsImgControl() {
		return isImgControl;
	}

	public void setIsImgControl(String isImgControl) {
		this.isImgControl = isImgControl;
	}

	public String getIsYin() {
		return isYin;
	}

	public void setIsYin(String isYin) {
		this.isYin = isYin;
	}

	public String getIsImgControl_isYin() {
		return isImgControl_isYin;
	}

	public void setIsImgControl_isYin(String isImgControl_isYin) {
		this.isImgControl_isYin = isImgControl_isYin;
	}

	public String getReq_session_cust_type() {
		return req_session_cust_type;
	}

	public void setReq_session_cust_type(String req_session_cust_type) {
		this.req_session_cust_type = req_session_cust_type;
	}

	public String getReq_session_cust_id() {
		return req_session_cust_id;
	}

	public void setReq_session_cust_id(String req_session_cust_id) {
		this.req_session_cust_id = req_session_cust_id;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	
	
}
