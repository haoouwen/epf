package com.rbt.action;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.FileUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.SitemapFuc;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;
import com.rbt.function.SitemapFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.ICategoryService;
import com.rbt.service.IGoodsService;

public class SitemapAction extends AdminBaseAction implements Preparable{
	
	public String flag;

	//跳转
	public String list(){
		return goUrl("sitemap");
	}
	
	/**
	 * 创建sitemap
	 * @author LHY
	 * @throws Exception 
	 */
	public void createSitemap() throws Exception{
		HttpServletRequest request=getRequest();
		flag=request.getParameter("flag");
		String str=new SitemapFuc().createSitemap(flag);
		String path=PropertiesUtil.getRootpath();
		FileUtil fu=new FileUtil();
		PrintWriter out=this.getResponse().getWriter();
		FileWriter fw=new FileWriter(path+"/sitemap_goods.xml",true);
		BufferedWriter bw=new BufferedWriter(fw);  
		if(flag.equals("1")){
			//bw.write(str);    
			fu.writeTxt(path,"sitemap_goods.xml",str);
			out.write("1");
		}else{
			//bw.write(str);    
			fu.writeTxt(path,"sitemap_category.xml",str);
			out.write("2");
		}
		fw.flush();         
		bw.close();
		out.close();
    }

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
