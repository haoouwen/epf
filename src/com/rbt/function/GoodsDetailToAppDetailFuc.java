package com.rbt.function;

import java.awt.Image;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.UpFileAction;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.ImageZipUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.service.IGoodsService;
import com.rbt.service.ILinkService;

public class GoodsDetailToAppDetailFuc extends CreateSpringContext{
	
    
	
	/**
	 * @Method Description :生成手机端
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public void toSetApp() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		PrintWriter out = response.getWriter();
		String content=request.getParameter("content");
		String imgs=request.getParameter("imgs");
		if(content!=null&&imgs!=null){
			content=GoodsDetailToAppDetailFuc.DealSetAppDetail(content,imgs);
		}
		out.write(content);
		return;
	}
	/**
	 * @Method Description :生成手机端
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public static String DealSetAppDetail(String content ,String imgs) throws Exception {
		try {
			if(imgs!=null&&!"".equals(imgs)){
				String img[]=imgs.split("#");
				for (int i = 0; i < img.length; i++) {
					int p=img[i].indexOf("/uploads");
					//获取原本的图片路径
					String oldpath=img[i].substring(p+1);
					//替换好的新的图片路径
					String savenewpath="";
					if(oldpath.contains("images")){
						savenewpath=oldpath.replace("images", "appimg");
					}else {
						savenewpath=oldpath.replace("image", "appimg");
					}
					
					//存储最开始的路径
					String saveOldPath=oldpath;
					//获取服务器文件的绝对路径
					oldpath=PropertiesUtil.getRootpath()+oldpath.replace("/",File.separator);
					File file = new File(oldpath);
		            //文件不存在时
		            if(!file.exists()||file.length()<=0){
		            	System.out.println("====================/include/common/images/nopicture.png=======================");
		            	content=content.replace(img[i], "/include/common/images/nopicture.png");
		            	continue;
		            }
		            //得到新的服务器文件存储路劲
		            String newpath="";
		            if(oldpath.contains("images")){
		            	 newpath=oldpath.replace("images", "appimg").replace(file.getName(), "");
					}else {
						 newpath=oldpath.replace("image", "appimg").replace(file.getName(), "");
					}
		            //验证源文件路劲和目标路劲是不是同一个，如果相同，会出现图片被覆盖导致 图片大小为0，图片丢失
		            //且新的保存的路劲 必须包含appimg
		            if(oldpath!=null&&!"".equals(oldpath)&&!oldpath.equals(newpath+file.getName())&&(newpath+file.getName()).contains("appimg")){
		            	//获取新的文件存储路劲
			            File path = new File(newpath);
			    		if (!path.exists()) {
			    			//创建文件夹
			    			path.mkdirs();
			    		}
			    		ImageZipUtil izu=new ImageZipUtil();
			    		izu.imageZipProce(oldpath,newpath+file.getName(),5000,5000);
			    		File filenew = new File(newpath+file.getName());
			            Image srcFile = ImageIO.read(filenew);
			            //获取图片的实际大小 宽度
			            int w=(int)srcFile.getWidth(null);
			            if(w>620){
			    			UpFileAction.zipimagesprocesApp(newpath+file.getName(),"620");
			    		}
			            content=content.replace(saveOldPath, savenewpath);
		            }
			  }
		   }
		  System.out.println("===================="+imgs+"====update GoodsDetail TO AppDetail Success===================");
		} catch (Exception e) {
			System.out.println("===================="+imgs+"====update GoodsDetail TO AppDetail  Faile===================");
			content="";
		}
		
		return content;
	}
	/**
	 * @Method Description :生成手机端
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public static boolean DealDetailPic(String imgs) throws Exception {
		boolean flage=false;
		try {
			if(imgs!=null&&!"".equals(imgs)){
				String img[]=imgs.split("#");
				for (int i = 0; i < img.length; i++) {
					String imgpath="";
					imgpath=PropertiesUtil.getRootpath()+img[i].replace("/",File.separator);
					File file = new File(imgpath);
		            //文件不存在时
		            if(!file.exists()||file.length()<=0){
		            	flage=true;
		            	break;
		            }
			  }
		   }
		} catch (Exception e) {
			flage=true;
		}
		return flage;
	}
//	/**
//	 * @Method Description :生成手机端商品详细图片
//	 * @author : HZX
//	 * @date : Apr 13, 2015 3:41:00 PM
//	 */
//	public static void detailSetAppDetail(String oldpath,int width){
//		
//		String newpath= oldpath.replace("images", "appimg");
//		String newp=newpath;
//		String ns[]=newpath.split("\\\\");
//		System.out.println("**********newpath="+newpath+"*******************newp="+newp+"***********************");
//		String rString=ns[ns.length-1];
//		int nString=newpath.indexOf(rString);
//		newpath=newpath.substring(0, nString);
//		File path = new File(newpath);
//		if (!path.exists()) {
//			path.mkdirs();
//			System.out.println("***************copyFile******************");
//		}
//		FileUtil fu = new FileUtil();
//		fu.copyFile(oldpath, newp);
//		
//		//手机详情的宽度大于620 将进行压缩处理
//		if(width>620){
//			UpFileAction.zipimagesproces(newp,"620");
//		}
//		
//	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:05:27 PM
	 * @Method Description :通过地区ID查询出相关的友情链接
	 */
	@SuppressWarnings("unchecked")
	public static List getGoodList(){
		Map gMap = new HashMap();
		return getGoodsObj().getList(gMap);
	}
	
	//从Spring容器中获取业务Bean
	public static IGoodsService getGoodsObj(){
		return (IGoodsService)getContext().getBean("goodsService");
	}
}
