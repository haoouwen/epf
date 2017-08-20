package com.rbt.function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.io.SAXReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.service.IAttrvalueService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IGoodsService;

public class SitemapFuc extends CreateSpringContext{
	IGoodsService goodsService = (IGoodsService)getContext().getBean("goodsService");
	IAttrvalueService attrvalueService = (IAttrvalueService)getContext().getBean("attrvalueService");
	ICategoryService categoryService = (ICategoryService)getContext().getBean("categoryService");
	public String flag="";
	
	/**
	 * 获取所有商品的id
	 * @return
	 */
	public List getGoodsId(){
		Map map=new HashMap();
		List list=this.goodsService.getAll(map);
		//List list=this.attrvalueService.getList(map);
		return list;
	}
	
	//获取分类
	public List getCate(){
		Map map=new HashMap();
		List list=this.categoryService.getList(map);
		return list;
	}
	
	public void Dom4j() throws Exception{
		//获取根目录
		String path=PropertiesUtil.getRootpath();
		SAXReader saxr=new SAXReader();
		Document doc=(Document) saxr.read(path+"/sitemap_baidu.xml");
		System.out.println(doc.toString());
		Element elm=doc.getRootElement();
		System.out.println(elm.toString());
	}
	/**
	 * 创建sitemap
	 * @author LHY
	 * @throws Exception 
	 */
	public String createSitemap(String flag) throws Exception{
		//Dom4j();
		//创建文档
        Document document = new Document();
        List list=null;
        if(flag.equals("1")){
        	list=getGoodsId();
        }else{
        	list=getCate();
        }
        System.out.println(list.size());
        if(list!=null&&list.size()>0){
        	 //创建根元素
            Element urlset = new Element("urlset");
            //设置命名空间
            urlset.setNamespace(Namespace.getNamespace("","http://www.sitemaps.org/schemas/sitemap/0.9"));   
            
        	Map map=new HashMap();
        	for (int i = 0; i <=list.size(); i++) {
        		if(i>=list.size()){
        			break;
        		}
				map=(HashMap) list.get(i);
				 //创建父元素
		        Element url = new Element("url");
		        //把元素加入到根元素中
		        urlset.addContent(url);
		        Element loc = new Element("loc");
		        //判断是创建商品sitemap还是分类sitemap
		        if(flag.equals("1")){
		        	loc.setText(SysconfigFuc.getSysValue("cfg_basehost")+"/mall-goodsdetail-"+map.get("goods_id")+".html");
		        }else{
		        	loc.setText(SysconfigFuc.getSysValue("cfg_basehost")+"/mall-goodslist-"+map.get("en_name")+".html");
		        }
		        url.addContent(loc);
		        
		        Element lastmod = new Element("lastmod");
		        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		        String date="";
		        if(flag.equals("1")){
		        	
		        	date=sdf.format(sdf.parse(map.get("in_date").toString()));
		        	//date=sdf.format(new Date());
		        }else{
		        	date=sdf.format(new Date());
		        }
		        lastmod.setText(date);
		        url.addContent(lastmod);
		        
		        Element changefreq = new Element("changefreq");
		        changefreq.setText("daily");
		        url.addContent(changefreq);
		        
		        Element priority = new Element("priority");
		        priority.setText("0.0");
		        url.addContent(priority);
			}
        	//把根元素加入到document中
            document.addContent(urlset);
        }
       
        //设置xml输出格式
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");//设置编码
        format.setIndent("    ");//设置缩进
        //得到xml输出流
        XMLOutputter out = new XMLOutputter(format);
        //将document转化为String
        String str=out.outputString(document);
        str=str.replace("<url xmlns=\"\">", "<url>");
		return str;
    }
	

	public static void main(String[]args) throws Exception{
		SitemapFuc sm=new SitemapFuc();
		sm.createSitemap("2");
	}
	
}
