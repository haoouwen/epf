/*
 
 * Package:com.rbt.action
 * FileName: CkeditorUploadAction.java 
 */
package com.rbt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;


import com.opensymphony.xwork2.Action;

import com.rbt.common.util.ImageMarkUtil;
import com.rbt.common.util.ImageZipUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 Ckeditor文件上传功能
 * @author 创建人 HXK
 * @date 创建日期 Jun 20, 2014
 */
@Controller
public class CkeditorUploadAction extends AdminBaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2476872941803088465L;

	/*******************实体层****************/
	public File upload;

	/*********************字段******************/
	public String uploadContentType;//
	public String uploadFileName;//上传文件名
	public String CKEditorFuncNum;//
	public String CKEditor;//
	public String langCode;//
	public String type;//
	public String temp_file_path = "";//路径
	public String temp_file_type= "";//类型
	public int temp_file_size = 0;//大小
	private static final String UPLOADS_PATH = "uploads";//上传文件根目录
	private static final String IMAGE_PATH = "images";//存放图片文件夹名称
	private final static String CFG_IMGTYPE = "cfg_imgtype";//图片格式限制
	private  String IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
	private final static String CFG_IMGSIZE = "cfg_imgsize";//图片大小限制,单位是M
	private static  final int IMAGE_SIZE = Integer.parseInt( SysconfigFuc.getSysValue(CFG_IMGSIZE).toString()) ;
	private static final String FLASH_PATH = "video";//存放多媒体文件文件夹名称
	private final static String CFG_MEDIATYPE = "cfg_mediatype";//多媒体文件格式限制
	private static final String FLASH_TYPE = SysconfigFuc.getSysValue(CFG_MEDIATYPE).toString() ;
	private final static String CFG_FLASHSIZE = "cfg_flashsize";//多媒体文件大小限制,单位是M
	private static  final int FLASH_SIZE = Integer.parseInt( SysconfigFuc.getSysValue(CFG_FLASHSIZE).toString()) ;
	private static final String FILE_PATH = "file";//存放文件文件夹名称
	private final static String CFG_ATTACHTYPE = "cfg_attachtype";//文件格式限制
	private static final String FILE_TYPE = SysconfigFuc.getSysValue(CFG_ATTACHTYPE).toString() ;
	private final static String CFG_FILESIZE = "cfg_filesize";//文件大小限制,单位是M
	private static  final int FILE_SIZE = Integer.parseInt( SysconfigFuc.getSysValue(CFG_FILESIZE).toString()) ;
	public String upload_type = "1";//1,图片上传，2 文件，3.视频
	private String cfg_bigimage = SysconfigFuc.getSysValue("cfg_bigimage");//商品图片最大图片控制
	public String execute() throws Exception {
		if(this.temp_file_path.equals("")){
			this.setTemp_file_path(this.IMAGE_PATH);
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
		//获取文件的存放文件位置
		String strPath = getImageSavePath();
		//得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadFileName);
		//获得当前的系统时间（小时分钟秒）来作为文件名
        String filename=createFileNameByDate(ftype);
		
		String vertify_message = "";
		
		if(this.temp_file_type.indexOf(ftype) == -1){
			vertify_message = this.temp_file_type+" is allowed";
		}else{
			
			if("1".equals(upload_type)){
				int cfg_bigimage_w_h =1000;
				if(!ValidateUtil.isRequired(cfg_bigimage)&&!"0".equals(cfg_bigimage)){
					cfg_bigimage_w_h=Integer.parseInt(cfg_bigimage);
				}
				//图片上传大图
				ImageZipUtil.imageZipProceFlie(upload,strPath+ File.separator + filename,cfg_bigimage_w_h,cfg_bigimage_w_h);
			}else{
				//获取表单的图片输入流、输出流
				InputStream is = new FileInputStream(this.upload);
				//创建文件
				OutputStream os = new FileOutputStream(new File(strPath + File.separator + filename));
				
				//保存文件
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
		}
		
		//浏览器访问的地址
		String file_url = "/"+UPLOADS_PATH+"/"+this.temp_file_path+"/"+getYearMonthDay()+"/"+filename;
		
		if("1".equals(upload_type)){
			//如果是图片的话 那就进行水印
			// 实例化水印图片处理类
			ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
			imageMarkUtil.imageMarkProcessGoodsDetail(strPath + File.separator + filename);
		}
		//验证不通过则访问地址置空
		if(!vertify_message.equals("")){
			file_url = "";
		}
		
		HttpServletResponse response = getResponse();
		String fullContentType = "text/html;charset=UTF-8";
		response.setContentType(fullContentType);
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		out.write(getScript(vertify_message,file_url));
		
		return Action.NONE;
	}
	
	//flash 文件上传
	public String flashUpload() throws Exception {
		upload_type="3";
		this.setTemp_file_path(this.FLASH_PATH);
		this.setTemp_file_size(this.FLASH_SIZE);
		this.setTemp_file_type(this.FLASH_TYPE);
		return execute();
	}
	
	//file文件上传
	public String fileUpload() throws Exception {
		upload_type="2";
		this.setTemp_file_path(this.FILE_PATH);
		this.setTemp_file_size(this.FILE_SIZE);
		this.setTemp_file_type(this.FILE_TYPE);
		return execute();
	}
	
	
	//获得当前的系统时间（小时分钟秒）来作为文件名
	//ftype：文件扩展名
	public String createFileNameByDate(String ftype){
		Date current=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        String filename=sdf.format(current)+"."+ftype;
        return filename;
	}
	
	//获取图片存放的服务器地址
	public String getImageSavePath(){
		//获取文件的存放文件位置
		String strPath = PropertiesUtil.getRootpath()+UPLOADS_PATH+File.separator+this.temp_file_path;
		
		//获取当天日期作为文件夹
        String datePath=getYearMonthDay();
		
        //文件最终保存的文件夹
        strPath = strPath + File.separator + datePath;
        
		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return strPath;
	}
	
	//获取当前年月日
	public String getYearMonthDay(){
		Date current=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(current);
	}
	
	
	
	//返回ckeditor js脚本
	public String getScript(String verify_message,String file_url){
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		if(!verify_message.equals("")){
			sb.append("alert('"+verify_message+"');");
		}
		sb.append("window.parent.CKEDITOR.tools.callFunction('"+this.CKEditorFuncNum+"','"+file_url+"','');");
		sb.append("</script>");
		return sb.toString();
	}
	
	/**
	 * @return the temp_file_path
	 */
	public String getTemp_file_path() {
		return temp_file_path;
	}

	/**
	 * @param temp_file_path the temp_file_path to set
	 */
	public void setTemp_file_path(String temp_file_path) {
		this.temp_file_path = temp_file_path;
	}

	/**
	 * @return the temp_file_type
	 */
	public String getTemp_file_type() {
		return temp_file_type;
	}

	/**
	 * @param temp_file_type the temp_file_type to set
	 */
	public void setTemp_file_type(String temp_file_type) {
		this.temp_file_type = temp_file_type;
	}

	/**
	 * @return the temp_file_size
	 */
	public int getTemp_file_size() {
		return temp_file_size;
	}

	/**
	 * @param temp_file_size the temp_file_size to set
	 */
	public void setTemp_file_size(int temp_file_size) {
		this.temp_file_size = temp_file_size;
	}

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the cKEditorFuncNum
	 */
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	/**
	 * @param cKEditorFuncNum
	 *            the cKEditorFuncNum to set
	 */
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	/**
	 * @return the cKEditor
	 */
	public String getCKEditor() {
		return CKEditor;
	}

	/**
	 * @param cKEditor
	 *            the cKEditor to set
	 */
	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}

	/**
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode
	 *            the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType
	 *            the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
